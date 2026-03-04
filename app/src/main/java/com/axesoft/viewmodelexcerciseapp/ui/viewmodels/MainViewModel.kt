package com.axesoft.viewmodelexcerciseapp.ui.viewmodels

import com.axesoft.viewmodelexcerciseapp.ui.core.BaseViewModel
import com.axesoft.viewmodelexcerciseapp.ui.core.ICoroutineContextHandler
import com.axesoft.viewmodelexcerciseapp.ui.core.onFailureHandler
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    contextHandler: ICoroutineContextHandler
) : BaseViewModel<MainViewModel.ViewState>() {

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

    init {
        contextHandler.launch {
            doSomethingRandom()
                .onSuccess {
                    println("vrednost ${it.result}")
                }.onFailureHandler {
                    println("exception handled ${it.message}")
                }
        }
    }

    data class Res(
        val result: String
    )

    suspend fun doSomethingRandom(): Result<Res> {
        delay(500) // simulacija asinhronog poziva (npr. network)

        return Result.failure(Exception("Nesto se desilo"))

    }

    fun reduce(
        goToNextPageText: String? = null,
        someList: List<String>? = null
    ) {
        updateState {
            it.copy(
                goToNextPageText = goToNextPageText ?: it.goToNextPageText,
                someList = someList ?: it.someList
            )
        }
    }

    data class ViewState(
        val goToNextPageText: String,
        val someList: List<String>
    )
}