package com.kurt.example.rickandmorty.core.domain.usecases

import com.kurt.example.rickandmorty.core.domain.repositories.EpisodesRepository
import javax.inject.Inject

/**
 * Copyright 2019, Kurt Renzo Acosta, All rights reserved.
 *
 * @author Kurt Renzo Acosta
 * @since 08/09/2019
 */
class GetAllEpisodes @Inject constructor(private val episodesRepository: EpisodesRepository) {
    suspend operator fun invoke(page: Int? = null) = episodesRepository.getAllEpisodes(page)
}