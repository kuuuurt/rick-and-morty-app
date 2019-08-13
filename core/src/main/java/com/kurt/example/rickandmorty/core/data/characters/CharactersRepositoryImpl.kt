package com.kurt.example.rickandmorty.core.data.characters

import com.kurt.example.rickandmorty.core.domain.repositories.CharactersRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
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
    override suspend fun getCharacters(page: Int?) =
        withContext(Dispatchers.IO) { remoteSource.getCharacters(page) }

    override suspend fun getCharacter(characterId: Int) =
        withContext(Dispatchers.IO) { remoteSource.getCharacter(characterId) }
}