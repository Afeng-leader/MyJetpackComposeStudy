package com.af.myjetpackcomposestudy.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel

class MainViewModel(private val savedStateHandle: SavedStateHandle) : ViewModel() {

    private val HOME_PAGE_SELECTED_INDEX = "home_page_selected_index"

    private val mSelectedLiveData = MutableLiveData<Int>()

    fun getSelectIndex() : MutableLiveData<Int> {
        if (mSelectedLiveData.value == null) {
            val index = savedStateHandle.get(HOME_PAGE_SELECTED_INDEX) ?: 0
            mSelectedLiveData.postValue(index)
        }
        return mSelectedLiveData
    }

    fun setSelectIndex(selectIndex: Int)  {
        savedStateHandle.set(HOME_PAGE_SELECTED_INDEX, selectIndex)
        mSelectedLiveData.value = selectIndex

    }

}