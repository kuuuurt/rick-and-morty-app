package com.kurt.example.rickandmorty.core.domain.repositories

import com.kurt.example.rickandmorty.core.domain.entities.Character
import com.kurt.example.rickandmorty.core.domain.entities.Episode

/**
 * Copyright 2019, Kurt Renzo Acosta, All rights reserved.
 *
 * @author Kurt Renzo Acosta
 * @since 08/05/2019
 */
interface CharactersRepository {
    suspend fun getAllCharacters(page: Int? = null): List<Character>
    suspend fun getCharacters(characterIds: List<Int>): List<Character>
    suspend fun getCharacter(characterId: Int): Character
}