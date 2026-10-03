package com.aksh.crickwatch.presentation.score

import com.aksh.crickwatch.data.model.Match


sealed interface ScoreUiState {

    data object Loading : ScoreUiState

    data class Success(
        val match: Match
    ) : ScoreUiState

    data class Error(
        val message: String
    ) : ScoreUiState
}