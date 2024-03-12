package com.af.myjetpackcomposestudy

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import com.google.accompanist.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.af.myjetpackcomposestudy.ui.theme.MyJetpackComposeStudyTheme
import com.af.myjetpackcomposestudy.utils.LogUtils
import com.af.myjetpackcomposestudy.utils.SpUtils
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator


class GuideActivity : ComponentActivity() {
    private val TAG : String = "GuideActivity"
    companion object {
        const val HAS_SHOW_GUIDE = "has_show_guide"
    }

    @ExperimentalPagerApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (SpUtils.getBoolean(HAS_SHOW_GUIDE)) {
            startMainActivity()
        } else {
            setContent {
                MyJetpackComposeStudyTheme {
                    GuidePage(listOf(R.drawable.ic_launcher_foreground,
                        R.drawable.ic_launcher_background, R.drawable.ic_launcher_foreground
                    )) {
                        SpUtils.setBoolean(HAS_SHOW_GUIDE, true)
                        startMainActivity()
                    }
                }
            }
        }
    }

    private fun startMainActivity() {
        LogUtils.logI(TAG,"startMainActivity")
        startActivity(Intent(this,MainActivity::class.java))
        finish()
    }

    @ExperimentalPagerApi
    @Composable
    fun GuidePage(images: List<Int>, startMainActivity: () -> Unit) {
        Box(modifier = Modifier.fillMaxSize()) {
            val pagerState = rememberPagerState(
                pageCount = images.size,
                initialOffscreenLimit = 2
            )
            HorizontalPager(state = pagerState, modifier = Modifier.fillMaxSize()) { page ->
                Image(
                    painter = painterResource(id = images[page]),
                    contentDescription = null,
                    contentScale = ContentScale.FillBounds,
                    modifier = Modifier.fillMaxSize()
                )
            }

            if(pagerState.currentPage == images.lastIndex) {
                Button(
                    onClick = startMainActivity,
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .padding(20.dp),
                    colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.primary)
                    ) {
                    Text(text = "开始体验",
                        color = Color.White,
                        fontSize = 20.sp
                        )
                }
            }

            HorizontalPagerIndicator(
                pagerState = pagerState,
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(20.dp),
                inactiveColor = Color.White,
                activeColor = MaterialTheme.colors.primary,
            )
        }
    }
}