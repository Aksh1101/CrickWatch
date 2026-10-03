package com.aksh.crickwatch.presentation.score


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aksh.crickwatch.data.repository.CricketRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class ScoreViewModel @Inject constructor(
    private val repository: CricketRepository
) : ViewModel() {

    private val _uiState =
        MutableStateFlow<ScoreUiState>(ScoreUiState.Loading)

    val uiState = _uiState.asStateFlow()

    fun getMatch(matchId: String) {

        viewModelScope.launch {

            _uiState.value = ScoreUiState.Loading

            try {

                val response = repository.getCricketScores()

                val match = response.data.firstOrNull {
                    it.id == matchId
                }

                if (match != null) {

                    _uiState.value = ScoreUiState.Success(
                        match = match
                    )

                } else {

                    _uiState.value = ScoreUiState.Error(
                        message = "Match not found"
                    )
                }

            } catch (e: Exception) {

                _uiState.value = ScoreUiState.Error(
                    message = e.message ?: "Unable to load match"
                )
            }
        }
    }
}