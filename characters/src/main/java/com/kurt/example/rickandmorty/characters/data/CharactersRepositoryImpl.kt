package com.kurt.example.rickandmorty.characters.data

import com.kurt.example.rickandmorty.characters.domain.repositories.CharactersRepository
import com.kurt.example.rickandmorty.core.domain.entities.Character
import javax.inject.Inject

/**
 * Copyright 2019, Kurt Renzo Acosta, All rights reserved.
 *
 * @author Kurt Renzo Acosta
 * @since 08/05/2019
 */
class CharactersRepositoryImpl @Inject constructor(
    private val remoteSource: CharactersRemoteSource
) : CharactersRepository {
    override suspend fun getCharacters(page: Int?): List<Character> = remoteSource.getCharacters(page)
    override suspend fun getCharacter(characterId: Int) = remoteSource.getCharacter(characterId)
}