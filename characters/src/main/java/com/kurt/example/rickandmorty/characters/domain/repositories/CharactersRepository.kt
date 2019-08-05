package com.kurt.example.rickandmorty.characters.domain.repositories

import com.kurt.example.rickandmorty.core.domain.entities.Character

/**
 * Copyright 2019, Kurt Renzo Acosta, All rights reserved.
 *
 * @author Kurt Renzo Acosta
 * @since 08/05/2019
 */
interface CharactersRepository {
    suspend fun getCharacters(): List<Character>
    suspend fun getCharacter(characterId: Int): Character
}