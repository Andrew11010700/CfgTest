package com.cfg.testtask.utils.resource

import androidx.annotation.StringRes

interface ResourceProvider {

    fun getString(@StringRes resId: Int): String

}