package com.cfg.testtask.utils.extension

import android.content.res.Resources
import android.util.TypedValue

/**
 * This method converts dp unit to equivalent pixels, depending on device density.
 * @param dp A value in dp (density independent pixels) unit. Which we need to convert into pixels
 * @return A float value to represent px equivalent to dp depending on device density
 */
fun Int.dpToPx(): Float {
    val metrics = Resources.getSystem().displayMetrics
    return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, this.toFloat(), metrics)
}