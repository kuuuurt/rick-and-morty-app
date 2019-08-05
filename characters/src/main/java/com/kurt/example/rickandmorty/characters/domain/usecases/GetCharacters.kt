package com.kurt.example.rickandmorty.characters.domain.usecases

import com.kurt.example.rickandmorty.characters.domain.repositories.CharactersRepository
import javax.inject.Inject

/**
 * Copyright 2019, Kurt Renzo Acosta, All rights reserved.
 *
 * @author Kurt Renzo Acosta
 * @since 08/05/2019
 */
class GetCharacters @Inject constructor(
    private val charactersRepository: CharactersRepository
) {
    suspend operator fun invoke(page: Int? = null) = charactersRepository.getCharacters(page)
}