package com.kurt.example.rickandmorty.core.domain.usecases

import com.kurt.example.rickandmorty.core.domain.repositories.CharactersRepository
import com.kurt.example.rickandmorty.core.domain.repositories.EpisodesRepository
import javax.inject.Inject

/**
 * Copyright 2019, Kurt Renzo Acosta, All rights reserved.
 *
 * @author Kurt Renzo Acosta
 * @since 08/05/2019
 */
class GetAllCharacters @Inject constructor(
    private val charactersRepository: CharactersRepository,
    private val episodesRepository: EpisodesRepository
) {
    suspend operator fun invoke(page: Int? = null) = charactersRepository.getAllCharacters(page)

}