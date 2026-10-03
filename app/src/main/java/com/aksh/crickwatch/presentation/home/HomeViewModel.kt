package com.aksh.crickwatch.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aksh.crickwatch.data.repository.CricketRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: CricketRepository
) : ViewModel() {

    private val _uiState =
        MutableStateFlow<MatchUiState>(MatchUiState.Loading)

    val uiState = _uiState.asStateFlow()

    init {
        getMatches()
    }

    private fun getMatches() {
        viewModelScope.launch {
            try {
                val response = repository.getCricketScores()

                _uiState.value = MatchUiState.Success(
                    matches = response.data
                )

            } catch (e: Exception) {

                _uiState.value = MatchUiState.Error(
                    message = e.message ?: "Something went wrong"
                )
            }
        }
    }
}