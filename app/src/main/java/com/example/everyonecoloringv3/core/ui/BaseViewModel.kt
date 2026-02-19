package com.example.everyonecoloringv3.core.ui

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

abstract class BaseViewModel<S>(initialState: S, protected val ioDispatcher: CoroutineDispatcher) : ViewModel() {
    protected val mutableState = MutableStateFlow(initialState)
    val state: StateFlow<S> = mutableState
}
