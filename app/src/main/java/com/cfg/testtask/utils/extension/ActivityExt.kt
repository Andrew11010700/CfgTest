package com.cfg.testtask.utils.extension

import android.app.Activity
import android.view.View
import com.google.android.material.snackbar.Snackbar

fun Activity.showSnackMessage(
    msg: String,
    textColor: Int,
    bgColor: Int,
    duration: Int
) {
    val root: View? = window?.decorView?.findViewById(android.R.id.content)
    root?.let {
        Snackbar.make(root, msg, duration).decorate(bgColor, textColor, msg).show()
    }
}