package com.aksh.crickwatch.presentation.home


import android.R
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.wear.compose.foundation.lazy.ScalingLazyColumn
import androidx.wear.compose.foundation.lazy.items
import com.aksh.crickwatch.data.model.Match

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    onMatchClick: (String) -> Unit
) {

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    when (val state = uiState) {

        MatchUiState.Loading -> {
            LoadingScreen()
        }

        is MatchUiState.Success -> {
            MatchList(
                matches = state.matches,
                onMatchClick = onMatchClick
            )
        }

        is MatchUiState.Error -> {
            ErrorScreen(
                message = state.message
            )
        }
    }
}

@Composable
fun LoadingScreen() {

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        CircularProgressIndicator()

        Text(
            text = "Loading matches...",
            modifier = Modifier.padding(top = 8.dp),
            color = Color.White
        )
    }
}


@Composable
fun MatchList(
    matches: List<Match>,
    onMatchClick: (String) -> Unit
) {

    val sortedMatches = matches.sortedBy {
        getMatchPriority(it)
    }

    ScalingLazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(
            horizontal = 8.dp,
            vertical = 16.dp
        ),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {

        // Header
        item {
            HomeHeader()
        }

        // Match cards
        items(
            items = sortedMatches,
            key = { it.id }
        ) { match ->

            MatchCard(
                match = match,
                onClick = {
                    onMatchClick(match.id)
                }
            )
        }
    }
}


@Composable
fun ErrorScreen(
    message: String
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            text = "Unable to load matches",
            color = Color.White
        )

    }
}