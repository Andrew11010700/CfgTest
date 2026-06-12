package com.cfg.testtask.utils.flow

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class SingleFlowEvent<T>(private val scope: CoroutineScope): SingleFlow<T> {

    private val flow: MutableStateFlow<T?> = MutableStateFlow(null)
    private var collected = false

    override fun emit(value: T?) {
        collected = false
        scope.launch {
            flow.emit(value)
        }
    }

    override suspend fun collect(onCollect: (T) -> Unit) {
        flow.mapNotNull { v: T? ->
            if (collected) null
            else v
        }.collect { v: T ->
            onCollect(v)
            flow.emit(null)
            collected = true
        }
    }

}