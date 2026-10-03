package com.aksh.crickwatch.presentation.navigation


import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.aksh.crickwatch.presentation.home.HomeScreen
import com.aksh.crickwatch.presentation.score.ScoreScreen

@Composable
fun AppNavHost(
    navController: NavHostController
) {

    NavHost(
        navController = navController,
        startDestination = Destination.Home.route
    ) {

        composable(
            route = Destination.Home.route
        ) {

            HomeScreen(
                onMatchClick = { matchId ->

                    navController.navigate(
                        Destination.Score.createRoute(matchId)
                    )
                }
            )
        }

        composable(
            route = Destination.Score.route
        ) { backStackEntry ->

            val matchId =
                backStackEntry.arguments?.getString("matchId")

            if (matchId != null) {

                ScoreScreen(
                    matchId = matchId,
                    onBackClick = {
                        navController.popBackStack()
                    },
                    viewModel = hiltViewModel()
                )
            }
        }
    }
}