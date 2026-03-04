package com.axesoft.viewmodelexcerciseapp.ui.core

import android.util.Log
import com.google.android.gms.common.api.ApiException
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.InvocationKind
import kotlin.contracts.contract
import kotlin.coroutines.CoroutineContext

class CoroutineContextHandler @Inject constructor(): CoroutineScope, ICoroutineContextHandler {

    private val error = MutableSharedFlow<String>()

    private val handler = CoroutineExceptionHandler { _, error ->
        handleCoroutineError(error)
    }

    private val scope: CoroutineScope = CoroutineScope(
        SupervisorJob() + Dispatchers.Main.immediate + handler
    )

    override val coroutineContext: CoroutineContext
    get() = scope.coroutineContext + coroutineExceptionHandler

    fun handleError(error: Throwable) {
        if (error is ApiException) {
            launch {
                this@CoroutineContextHandler.error.emit(error.stackTraceToString())
            }
        } else {
            launch {
                this@CoroutineContextHandler.error.emit("Exception happened")
            }
        }
    }

    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, error ->
        handleCoroutineError(error)
    }

    fun handleCoroutineError(error: Throwable) {
        handleError(error)
        Log.e(this::class.simpleName, error.message ?: error.toString())
    }

    override fun onClear() {
        cancel()

    }

    override fun launch(block: suspend CoroutineScope.() -> Unit): Job =
        scope.launch(block = block)

}

interface ICoroutineContextHandler {
    fun onClear()

    fun launch(block: suspend CoroutineScope.() -> Unit) : Job
}

@OptIn(ExperimentalContracts::class)
@SinceKotlin("1.3")
inline fun <T> Result<T>.onFailureHandler(
    action: (exception: Throwable) -> Unit = {}
): Result<T> {
    contract {
        callsInPlace(action, InvocationKind.AT_MOST_ONCE)
    }
    exceptionOrNull()?.let {
        val handler = CoroutineContextHandler()
        handler.handleError(it)
        action(it)
    }
    return this
}
