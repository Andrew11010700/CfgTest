package com.cfg.testtask.base.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import com.cfg.testtask.R

abstract class BaseFullScreenDialog : DialogFragment() {

    protected abstract val layoutId: Int

    abstract var onBackPressedAction: () -> Unit

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requireActivity().onBackPressedDispatcher.addCallback(
            this,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    onBackPressedAction()
                }
            },
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return inflater.inflate(layoutId, container, false)
    }


    override fun getTheme(): Int = R.style.FullScreenDialog

    override fun dismiss() {
        if (isAdded) super.dismissAllowingStateLoss()
    }

    open fun show(manager: FragmentManager?) {
        if (manager != null && !isAdded) {
            super.show(manager, tag)
        }
    }

}
