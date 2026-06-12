package com.cfg.testtask.utils.flow


interface SingleFlow<T> {
    fun emit(value: T?)
    suspend fun collect(onCollect: (T) -> Unit)
}