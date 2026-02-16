package com.axesoft.viewmodelexcerciseapp.ui.core

import androidx.lifecycle.SavedStateHandle

sealed class ScreenResult<T> {
    abstract fun getUUID(): String

    internal fun consumeResult(savedState: SavedStateHandle?): T? {
        return savedState?.let {
            savedState.get<T>(this.getUUID())?.let {
                savedState.remove<T>(getUUID())
                it
            }
        }
    }
}
