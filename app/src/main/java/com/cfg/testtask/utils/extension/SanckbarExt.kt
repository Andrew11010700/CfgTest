package com.cfg.testtask.utils.extension

import android.content.res.ColorStateList
import android.graphics.text.LineBreaker
import android.os.Build
import android.view.Gravity
import android.widget.TextView
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import com.google.android.material.R
import com.google.android.material.snackbar.Snackbar

/**
 * Method responsible for changing the appearance of [Snackbar].
 *
 * The method changes the [Snackbar] background tint and applies text styles to
 * [R.id.snackbar_text] and [R.id.snackbar_action].
 *
 * @param bgColor background color resource id, for example [android.R.color.white].
 * @param textColor text color resource id, for example [android.R.color.black].
 * @param contentDesc text that will be announced for visually impaired users when TalkBack is enabled.
 */

fun Snackbar.decorate(
    @ColorRes bgColor: Int,
    @ColorRes textColor: Int,
    contentDesc: String
): Snackbar {

    val ctx = view.context
    ViewCompat.setBackgroundTintList(
        view,
        ColorStateList.valueOf(ContextCompat.getColor(ctx, bgColor)),
    )

    with(view.findViewById<TextView>(R.id.snackbar_text)) {
        setTextColor(ContextCompat.getColor(ctx, textColor))
        gravity = Gravity.CENTER_VERTICAL
        compoundDrawablePadding = 8.dpToPx().toInt()
        ellipsize = null
        contentDescription = contentDesc
        maxLines = 5
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            breakStrategy = LineBreaker.BREAK_STRATEGY_BALANCED
        }
    }

    with(view.findViewById<TextView>(R.id.snackbar_action)) {
        setTextColor(ContextCompat.getColor(ctx, textColor))
    }
    return this
}
