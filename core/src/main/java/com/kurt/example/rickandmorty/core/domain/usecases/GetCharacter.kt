package com.kurt.example.rickandmorty.core.domain.usecases

import com.kurt.example.rickandmorty.core.domain.repositories.CharactersRepository
import javax.inject.Inject

/**
 * Copyright 2019, Kurt Renzo Acosta, All rights reserved.
 *
 * @author Kurt Renzo Acosta
 * @since 08/13/2019
 */
class GetCharacter @Inject constructor(
    private val charactersRepository: CharactersRepository
) {
    suspend operator fun invoke(characterId: Int) = charactersRepository.getCharacter(characterId)
}