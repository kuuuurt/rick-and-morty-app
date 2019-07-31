package com.marvel.example.core.presentation

/**
 * Copyright 2018, Kurt Renzo Acosta, All rights reserved.
 *
 * @author Kurt Renzo Acosta
 * @since 20/11/2018
 */
sealed class UiState {
    object Complete : UiState()
    object Loading : UiState()
    class Error(val errorMessage: String) : UiState()
}