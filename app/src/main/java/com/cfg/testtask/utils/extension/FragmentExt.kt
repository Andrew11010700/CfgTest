package com.cfg.testtask.utils.extension

import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.databinding.ViewDataBinding
import com.cfg.testtask.utils.delegate.FragmentDataBindingDelegate
import com.cfg.testtask.utils.delegate.dataBinding as createDataBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

fun Fragment.launchInViewLifecycleOwner(
    state: Lifecycle.State = Lifecycle.State.STARTED,
    block: suspend CoroutineScope.() -> Unit,
) {
    viewLifecycleOwner.lifecycleScope.launch {
        repeatOnLifecycle(state, block)
    }
}

fun Fragment.launchInViewScope(block: suspend CoroutineScope.() -> Unit): Job? {
    return viewLifecycleOwner.lifecycleScope.launch {
        block()
    }
}

fun <T : ViewDataBinding> Fragment.dataBinding(): FragmentDataBindingDelegate<T> = createDataBinding()
