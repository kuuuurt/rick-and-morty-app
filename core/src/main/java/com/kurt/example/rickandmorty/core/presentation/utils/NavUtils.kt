package com.kurt.example.rickandmorty.core.presentation.utils

import android.net.Uri
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import com.kurt.example.rickandmorty.core.R

/**
 * Copyright 2019, Kurt Renzo Acosta, All rights reserved.
 *
 * @author Kurt Renzo Acosta
 * @since 08/13/2019
 */

fun createDefaultNavOptions(destination: Int) = NavOptions.Builder()
    .setLaunchSingleTop(false)
    .setPopUpTo(destination, false)
    .setEnterAnim(R.anim.nav_default_enter_anim)
    .setExitAnim(R.anim.nav_default_exit_anim)
    .setPopEnterAnim(R.anim.nav_default_pop_enter_anim)
    .setPopExitAnim(R.anim.nav_default_pop_exit_anim)
    .build()

fun NavController.navigateUriWithDefaultOptions(uri: Uri, popUpTo: Int) {
    this.navigate(uri, createDefaultNavOptions(this.currentDestination?.id ?: -1))
}