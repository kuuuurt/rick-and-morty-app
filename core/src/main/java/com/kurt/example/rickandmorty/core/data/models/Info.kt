package com.kurt.example.rickandmorty.core.data.models

/**
 * Copyright 2019, Kurt Renzo Acosta, All rights reserved.
 *
 * @author Kurt Renzo Acosta
 * @since 08/05/2019
 */

data class Info(
    val count: Int,
    val pages: Int,
    val next: String,
    val prev: String
)