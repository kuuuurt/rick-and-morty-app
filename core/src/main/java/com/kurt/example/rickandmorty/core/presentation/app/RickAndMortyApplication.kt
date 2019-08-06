package com.kurt.example.rickandmorty.core.presentation.app

import android.app.Activity
import android.app.Application
import android.app.Service
import android.content.Context
import androidx.fragment.app.Fragment
import com.kurt.example.rickandmorty.core.di.CoreComponent
import com.kurt.example.rickandmorty.core.di.DaggerCoreComponent

/**
 * Copyright 2019, Kurt Renzo Acosta, All rights reserved.
 *
 * @author Kurt Renzo Acosta
 * @since 08/06/2019
 */
class RickAndMortyApplication : Application() {
    private val coreComponent: CoreComponent by lazy {
        DaggerCoreComponent.create()
    }

    companion object {
        lateinit var appContext: Context
        @JvmStatic
        fun coreComponent(context: Context) =
            (context.applicationContext as RickAndMortyApplication).coreComponent
    }
}

fun Activity.coreComponent() = RickAndMortyApplication.coreComponent(this)
fun Fragment.coreComponent() = RickAndMortyApplication.coreComponent(requireContext())
fun Service.coreComponent() = RickAndMortyApplication.coreComponent(this)