package com.aksh.crickwatch.presentation.navigation


sealed interface Destination {

    val route: String

    data object Home : Destination {
        override val route = "home"
    }

    data object Score : Destination {
        override val route = "score/{matchId}"

        fun createRoute(matchId: String): String {
            return "score/$matchId"
        }
    }
}