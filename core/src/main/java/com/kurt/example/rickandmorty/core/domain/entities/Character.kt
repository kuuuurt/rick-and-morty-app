package com.kurt.example.rickandmorty.core.domain.entities

data class Character(
    val created: String,
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