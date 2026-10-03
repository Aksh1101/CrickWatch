package com.aksh.crickwatch.presentation.home

import com.aksh.crickwatch.data.model.Match

sealed interface MatchUiState {

    data object Loading : MatchUiState

    data class Success(
        val matches: List<Match>
    ) : MatchUiState

    data class Error(
        val message: String
    ) : MatchUiState
}