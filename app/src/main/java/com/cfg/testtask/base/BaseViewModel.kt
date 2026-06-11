package com.cfg.testtask.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cfg.testtask.R
import com.cfg.testtask.base.events.MessageEvent
import com.cfg.testtask.base.events.NavAction
import com.cfg.testtask.utils.resource.ResourceProvider
import kotlinx.coroutines.CompletableJob
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.net.UnknownHostException
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

abstract class BaseViewModel: ViewModel() {

    protected val _isShowLoadingDialog = MutableSharedFlow<Boolean>()
    val isShowLoadingDialog = _isShowLoadingDialog.asSharedFlow()

    private val _navAction = MutableSharedFlow<NavAction>()
    val navAction = _navAction.asSharedFlow()

    private val _message = MutableSharedFlow<MessageEvent>()
    val message = _message.asSharedFlow()

    @Inject
    lateinit var res: ResourceProvider

    private var job: CompletableJob = Job()
        get() {
            if (field.isCancelled) field = Job()
            return field
        }

    protected val exceptionHandler: CoroutineExceptionHandler =
        CoroutineExceptionHandler { _: CoroutineContext, throwable: Throwable ->
            viewModelScope.launch(job) {
                handleError(throwable)
            }
        }

    private suspend fun handleError(throwable: Throwable) {
        when (throwable) {
            is UnknownHostException -> handleUnknownHostException()
            is HttpException -> handleHttpException()
            else -> showMessage(MessageEvent.Error(res.getString(R.string.server_error)))
        }
    }

    protected open suspend fun handleUnknownHostException() {
        showMessage(MessageEvent.Error(res.getString(R.string.internet_error)))
    }

    protected open suspend fun handleHttpException() {
        showMessage(MessageEvent.Error(res.getString(R.string.server_error)))
    }

    protected fun navigate(navAction: NavAction) {
        launchWithHandler(showLoading = false) {
            _navAction.emit(navAction)
        }
    }

    protected suspend fun showMessage(messageEvent: MessageEvent) {
        _message.emit(messageEvent)
    }

    protected fun launchWithHandler(
        dispatcher: CoroutineDispatcher = Dispatchers.IO,
        showLoading: Boolean = true,
        handler: CoroutineExceptionHandler = exceptionHandler,
        block: suspend CoroutineScope.() -> Unit
    ): Job {
        return viewModelScope.launch(handler) {
            if (showLoading) _isShowLoadingDialog.emit(true)
            try {
                withContext(dispatcher) {
                    block()
                }
            } finally {
                if (showLoading) _isShowLoadingDialog.emit(false)
            }
        }
    }

}