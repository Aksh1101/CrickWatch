package com.aksh.crickwatch.presentation.score

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.aksh.crickwatch.data.model.Match
import com.aksh.crickwatch.presentation.home.formatMatchDate
import com.aksh.crickwatch.presentation.home.getShortName

@Composable
fun ScoreContent(
    match: Match,
    onBackClick: () -> Unit
) {
    val status = when (match.ms) {
        "fixture" -> "UPCOMING"
        "result" -> "COMPLETED"
        else -> "● LIVE"
    }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {

        // Score content
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    horizontal = 14.dp,
                    vertical = 20.dp
                )
                .padding(bottom = 36.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Text(
                text = status,
                color = Color.White,
                fontWeight = FontWeight.Bold
            )

            if (match.ms != "fixture" && match.ms != "result") {
                Spacer(
                    modifier = Modifier.height(2.dp)
                )

                Text(
                    text = "Scores auto-refresh every 2 min",
                    color = Color.White,
                    fontSize = 8.sp
                )
            }

            Spacer(
                modifier = Modifier.height(10.dp)
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {

                ScoreTeam(
                    imageUrl = match.t1img,
                    team = getShortName(match.t1),
                    score = match.t1s
                )

                Text(
                    text = "VS",
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )

                ScoreTeam(
                    imageUrl = match.t2img,
                    team = getShortName(match.t2),
                    score = match.t2s
                )
            }

            Spacer(
                modifier = Modifier.height(10.dp)
            )

            if (match.ms == "fixture") {

                Text(
                    text = formatMatchDate(match.dateTimeGMT),
                    color = Color.White
                )

            } else {

                Text(
                    text = match.status,
                    color = Color.White,
                    fontWeight = FontWeight.Medium
                )
            }
        }

        // Bottom back button
        Button(
            onClick = onBackClick,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 12.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Blue.copy(0.4f)),

        ) {
            Text(
                text = "Back to Matches",
                color = Color.White,
                fontSize = 10.sp
            )
        }
    }
}



@Composable
private fun ScoreTeam(
    imageUrl: String,
    team: String,
    score: String?
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        AsyncImage(
            model = imageUrl,
            contentDescription = team,
            modifier = Modifier.size(36.dp)
        )

        Spacer(
            modifier = Modifier.height(4.dp)
        )

        Text(
            text = team,
            color = Color.White,
            fontWeight = FontWeight.Bold
        )

        Spacer(
            modifier = Modifier.height(2.dp)
        )

        Text(
            text = score?.takeIf { it.isNotBlank() } ?: "-",
            color = Color.White,
            fontWeight = FontWeight.Bold
        )
    }
}