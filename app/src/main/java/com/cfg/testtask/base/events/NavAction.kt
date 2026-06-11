package com.cfg.testtask.base.events

import androidx.navigation.NavDirections

sealed class NavAction {

    data class ToDirection(val direction: NavDirections) : NavAction()

    data object NavigationUp : NavAction()

}