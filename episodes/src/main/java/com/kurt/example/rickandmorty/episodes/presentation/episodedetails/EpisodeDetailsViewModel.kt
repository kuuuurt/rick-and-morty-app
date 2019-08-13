package com.kurt.example.rickandmorty.episodes.presentation.episodedetails

import androidx.lifecycle.*
import com.kurt.example.rickandmorty.core.domain.entities.Character
import com.kurt.example.rickandmorty.core.domain.entities.Episode
import com.kurt.example.rickandmorty.core.domain.usecases.GetCharacter
import com.kurt.example.rickandmorty.core.domain.usecases.GetEpisode
import com.kurt.example.rickandmorty.core.presentation.UiState
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.launch

/**
 * Copyright 2019, Kurt Renzo Acosta, All rights reserved.
 *
 * @author Kurt Renzo Acosta
 * @since 08/13/2019
 */
class EpisodeDetailsViewModel(
    private val episodeId: Int,
    private val getEpisode: GetEpisode,
    private val getCharacter: GetCharacter
) : ViewModel() {
    class Factory(
        private val episodeId: Int,
        private val getEpisode: GetEpisode,
        private val getCharacter: GetCharacter
    ) : ViewModelProvider.NewInstanceFactory() {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(EpisodeDetailsViewModel::class.java)) {
                return EpisodeDetailsViewModel(episodeId, getEpisode, getCharacter) as T
            }
            throw IllegalArgumentException("ViewModel not found")
        }
    }

    private val _episode = MutableLiveData<Episode>()
    val episode: LiveData<Episode> = _episode

    private val _getEpisodeState = MutableLiveData<UiState>()
    val getEpisodeState: LiveData<UiState> = _getEpisodeState

    private val _characters = MutableLiveData<List<Character>>()
    val characters: LiveData<List<Character>> = _characters

    private val _getCharactersState = MutableLiveData<UiState>()
    val getCharactersState: LiveData<UiState> = _getCharactersState

    init {
        viewModelScope.launch(CoroutineExceptionHandler { _, throwable ->
            _getEpisodeState.postValue(UiState.Error(throwable))
        }) {
            _getEpisodeState.postValue(UiState.Loading)
            val episode = getEpisode(episodeId)
            _episode.postValue(episode)
            _getEpisodeState.postValue(UiState.Complete)

            launch(CoroutineExceptionHandler { _, throwable ->
                _getCharactersState.postValue(UiState.Error(throwable))
            } ) {
                _getCharactersState.postValue(UiState.Loading)
                val deferredCharacters = episode.characters
                    .map { it.split("/").last().toInt() }
                    .map { async { getCharacter(it) }}

                val characters = deferredCharacters.awaitAll()
                _characters.postValue(characters)
                _getCharactersState.postValue(UiState.Complete)
            }

        }
    }
}