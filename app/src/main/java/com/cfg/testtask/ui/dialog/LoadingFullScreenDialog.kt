package com.cfg.testtask.ui.dialog

import com.cfg.testtask.R
import com.cfg.testtask.base.dialog.BaseFullScreenDialog

class LoadingFullScreenDialog : BaseFullScreenDialog() {

    override val layoutId: Int = R.layout.dialog_loading_full_screen

    override var onBackPressedAction: () -> Unit = {}

    override fun getTheme(): Int = R.style.FullScreenDialog_LoadingDialog

    companion object {
        const val PROGRESS_DIALOG_TAG = "progressDialog"
    }

}