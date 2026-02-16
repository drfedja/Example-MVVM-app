package com.axesoft.viewmodelexcerciseapp.ui.viewmodels

import com.axesoft.viewmodelexcerciseapp.ui.core.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(): BaseViewModel<MainViewModel.ViewState>() {

    override fun getInitialState(): ViewState = ViewState(
        goToNextPageText = "Go to next page",
        someList = listOf(
            "item 1",
            "item 2",
            "item 3",
            "item 4",
            "item 5",
            "item 6",
            "item 7",
            "item 8",
            "item 9",
            "item 10",
            "item 11",
            "item 12",
            "item 13",
            "item 14",
            "item 15",
            "item 16",
            "item 17"
        )
    )


    data class ViewState(
        val goToNextPageText: String,
        val someList: List<String>
    )
}