package com.kurt.example.rickandmorty.core.framework.models

/**
 * Copyright 2019, Kurt Renzo Acosta, All rights reserved.
 *
 * @author Kurt Renzo Acosta
 * @since 08/05/2019
 */

data class RickAndMortyResponse<T>(
    val info: Info,
    val results: List<T>
)