package com.kurt.example.rickandmorty.core.domain.entities

data class Location(
    val created: String,
    val dimension: String,
    val id: Int,
    val name: String,
    val residents: List<Character>,
    val type: String,
    val url: String
)