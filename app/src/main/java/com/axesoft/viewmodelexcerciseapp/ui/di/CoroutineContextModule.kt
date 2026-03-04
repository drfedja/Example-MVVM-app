package com.axesoft.viewmodelexcerciseapp.ui.di

import com.axesoft.viewmodelexcerciseapp.ui.core.CoroutineContextHandler
import com.axesoft.viewmodelexcerciseapp.ui.core.ICoroutineContextHandler
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
internal class CoroutineContextModule {

    @Provides
    fun provideCoroutineContext(
        contextHandler: CoroutineContextHandler
    ) : ICoroutineContextHandler = contextHandler
}