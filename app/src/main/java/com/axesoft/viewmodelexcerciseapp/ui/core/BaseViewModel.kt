package com.axesoft.viewmodelexcerciseapp.ui.core

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.axesoft.viewmodelexcerciseapp.ui.core.ScreenResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

abstract class BaseViewModel<State: Any> : ViewModel() {

    private var _navGraphDestinationId: Int? = null
    private var screenResultRegistrar: ScreenResultRegistrar? = null

    val navGraphDestinationId: Int?
        get() = _navGraphDestinationId

    protected val _state by lazy { MutableStateFlow(getInitialState()) }
    val state: StateFlow<State> by lazy { _state}

    protected abstract fun getInitialState(): State

    fun checkForScreenResult(savedState: SavedStateHandle?) {
        screenResultRegistrar?.let {
            it.screenResultType.consumeResult(savedState).let { result ->
                handleScreenResult(screenResult = result, additionalInfo = it.additionalInfo)
            }
        }
        screenResultRegistrar = null
    }

    protected open fun handleScreenResult(screenResult: Any?, additionalInfo: Any?) {
        handleScreenResult(screenResult = screenResult, additionalInfo = additionalInfo)
    }

    private class ScreenResultRegistrar(val screenResultType: ScreenResult<*>, val additionalInfo: Any?)
}