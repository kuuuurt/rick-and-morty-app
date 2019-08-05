package com.kurt.example.rickandmorty.core.domain.entities

data class Episode(
    val airDate: String,
    val characters: List<Character>,
    val created: String,
    val episode: String,
    val id: Int,
    val name: String,
    val url: String
)