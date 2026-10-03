package com.aksh.crickwatch.presentation.home


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.aksh.crickwatch.data.model.Match
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter
import kotlin.time.Instant




fun getShortName(team: String): String {
    return team
        .substringAfter("[", team)
        .substringBefore("]")
}



fun formatMatchDate(
    dateTimeGMT: String
): String {
    return try {
        val dateTime = LocalDateTime.parse(dateTimeGMT)

        val formatter = DateTimeFormatter.ofPattern(
            "MMM dd • h:mm a"
        )

        dateTime
            .atOffset(ZoneOffset.UTC)
            .atZoneSameInstant(ZoneId.systemDefault())
            .format(formatter)

    } catch (e: Exception) {
        ""
    }
}

fun getMatchPriority(match: Match): Int {
    return when (match.ms) {
        "fixture" -> 2
        "result" -> 3
        else -> 1
    }
}



@Composable
fun MatchCard(
    match: Match,
    onClick: () -> Unit
) {
    val status = when (match.ms) {
        "fixture" -> "UPCOMING"
        "result" -> "COMPLETED"
        else -> "● LIVE"
    }

    Card(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth(0.90f),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = 12.dp,
                    vertical = 12.dp
                ),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                text = status,
                style = MaterialTheme.typography.labelMedium,
                fontWeight = FontWeight.Bold
            )

            Spacer(
                modifier = Modifier.height(10.dp)
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {

                TeamInfo(
                    imageUrl = match.t1img,
                    shortName = getShortName(match.t1)
                )

                Spacer(
                    modifier = Modifier.width(10.dp)
                )

                Text(
                    text = "VS",
                    style = MaterialTheme.typography.labelSmall,
                    fontWeight = FontWeight.Bold
                )

                Spacer(
                    modifier = Modifier.width(10.dp)
                )

                TeamInfo(
                    imageUrl = match.t2img,
                    shortName = getShortName(match.t2)
                )
            }
        }
    }
}


@Composable
fun TeamInfo(
    imageUrl: String,
    shortName: String
) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {

        AsyncImage(
            model = imageUrl,
            contentDescription = shortName,
            modifier = Modifier.size(28.dp)
        )

        Spacer(
            modifier = Modifier.width(5.dp)
        )

        Text(
            text = shortName,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun HomeHeader() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = "CrickWatch",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )

        Text(
            text = "Live cricket on your wrist",
            style = MaterialTheme.typography.labelSmall,
            color = Color.White
        )

        Spacer(
            modifier = Modifier.height(6.dp)
        )
    }
}


