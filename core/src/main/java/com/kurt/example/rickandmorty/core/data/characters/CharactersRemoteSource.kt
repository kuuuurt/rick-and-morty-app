package com.kurt.example.rickandmorty.core.data.characters

import com.kurt.example.rickandmorty.core.domain.entities.Character

/**
 * Copyright 2019, Kurt Renzo Acosta, All rights reserved.
 *
 * @author Kurt Renzo Acosta
 * @since 08/05/2019
 */
interface CharactersRemoteSource {
    suspend fun getAllCharacters(page: Int? = null): List<Character>
    suspend fun getCharacters(characterIds: List<Int>): List<Character>
    suspend fun getCharacter(characterId: Int): Character
}