package com.cfg.testtask.base.events

sealed class MessageEvent(val message: String) {

    class Success(message: String): MessageEvent(message)

    class Error(message: String): MessageEvent(message)

}