package com.af.myjetpackcomposestudy

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import com.af.myjetpackcomposestudy.ui.movie.MoviePage
import com.af.myjetpackcomposestudy.ui.music.MusicPage
import com.af.myjetpackcomposestudy.ui.news.NewsPage
import com.af.myjetpackcomposestudy.ui.theme.MyJetpackComposeStudyTheme
import com.af.myjetpackcomposestudy.ui.theme.Purple500
import com.af.myjetpackcomposestudy.viewmodel.MainViewModel
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalPagerApi::class, ExperimentalFoundationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val viewModel : MainViewModel = viewModel()
            val selectedIndex by viewModel.getSelectIndex().observeAsState(0)
            MyJetpackComposeStudyTheme {
                Column {
                    val pagerState = rememberPagerState(
                        pageCount = 3,
                        initialPage = selectedIndex,
                        initialOffscreenLimit = 2
                    )
                    HorizontalPager(
                        state = pagerState,
                        dragEnabled = false,
                        modifier = Modifier.weight(1f)
                    ) { page ->
                        when (page) {
                            0 -> NewsPage()
                            1 -> MoviePage()
                            2 -> MusicPage()
                        }
                    }
                    BottomNavigationAlwaysShowLabelComponent(pagerState = pagerState)
                }
            }
        }
    }
}

val listItems = listOf("News", "Movie", "Music")

@OptIn(ExperimentalPagerApi::class)
@Composable
fun BottomNavigationAlwaysShowLabelComponent(pagerState: PagerState) {

    val viewModel: MainViewModel = viewModel()
    val selectedIndex by viewModel.getSelectIndex().observeAsState(0)
    val scope = rememberCoroutineScope()
    BottomNavigation {
        listItems.forEachIndexed { index, title ->
            BottomNavigationItem(icon = {
                    when (index) {
                        0 -> BottomIcon(Icons.Filled.Home, selectedIndex, index)
                        1 -> BottomIcon(Icons.Filled.List, selectedIndex, index)
                        2 -> BottomIcon(Icons.Filled.Favorite, selectedIndex, index)
                    }
                }, label = {
                    Text(
                        title,
                        color = if (selectedIndex == index) Purple500 else Color.Gray
                    )
                }, selected = selectedIndex == index, onClick = {
                    viewModel.setSelectIndex(index)
                    scope.launch {
                        pagerState.animateScrollToPage(index)
                    }
                })
        }
    }


}

@Composable
private fun BottomIcon(imageVector: ImageVector, selectedIndex: Int, index: Int) {
    Icon(
        imageVector,
        null,
        tint = if (selectedIndex == index) Purple500 else Color.Gray
    )
}

