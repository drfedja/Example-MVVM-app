package com.axesoft.viewmodelexcerciseapp.ui.viewmodels

import com.axesoft.viewmodelexcerciseapp.ui.core.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NextPageViewModel @Inject constructor(): BaseViewModel<NextPageViewModel.ViewState>() {
    override fun getInitialState(): ViewState =
        ViewState(
            message = "This is the next page"
        )

    data class ViewState(val message: String)
}
