package com.kurt.example.rickandmorty.core.domain.entities

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Character(
    val created: String,
    @Json(name = "episode")
    val episodes: List<String>,
    val gender: String,
    val id: Int,
    val image: String,
    val location: LocationShort,
    val origin: LocationShort,
    val name: String,
    val species: String,
    val status: String,
    val type: String,
    val url: String
)