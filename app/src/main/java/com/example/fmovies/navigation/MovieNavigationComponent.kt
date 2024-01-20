package com.example.fmovies.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.fmovies.DetailScreen
import com.example.fmovies.HomeScreen

@Composable
fun MovieNavigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = MovieScreens.HOME_SCREEN.name
    ) {

        composable(
            route = MovieScreens.HOME_SCREEN.name,
        ) {
            //here we pass where this should lead us to
            HomeScreen(navController = navController)
        }

        composable(
            route = MovieScreens.DETAILS_SCREEN.name + "/{movie}",
            arguments = listOf(navArgument(name = "movie") { NavType.StringType })
        ) { backStackEntry ->
            DetailScreen(
                backStackEntry.arguments?.getString("movie"),
                navController = navController
            )

        }
    }
}