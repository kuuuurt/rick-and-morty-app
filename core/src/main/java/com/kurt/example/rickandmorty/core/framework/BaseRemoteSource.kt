package com.kurt.example.rickandmorty.core.framework

import com.kurt.example.rickandmorty.core.framework.episodes.remote.EpisodesApi
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

/**
 * Copyright 2019, Kurt Renzo Acosta, All rights reserved.
 *
 * @author Kurt Renzo Acosta
 * @since 08/05/2019
 */
open class BaseRemoteSource<T>(clazz: Class<T>) {
    protected val retrofit = Retrofit.Builder()
        .baseUrl("https://rickandmortyapi.com/api/")
        .addConverterFactory(MoshiConverterFactory.create())
        .build()

    protected val api = retrofit.create(clazz)
}