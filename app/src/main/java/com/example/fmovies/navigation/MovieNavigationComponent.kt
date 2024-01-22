package com.example.fmovies.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.fmovies.DetailScreen
import com.example.fmovies.HomeScreen
import com.example.fmovies.data.getMovies

@Composable
fun MovieNavigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = MovieScreens.HOME_SCREEN.name
    ) {

        composable(route = MovieScreens.HOME_SCREEN.name) {
            //here we pass where this should lead us to
            HomeScreen(navController = navController)
        }

        composable(route = MovieScreens.DETAILS_SCREEN.name + "/movieId={imdbId}",
            arguments = listOf(navArgument(name = "imdbId") { NavType.StringType }
            )

        ) { backStackEntry ->

            val movieId = backStackEntry.arguments?.getString("imdbId") ?: ""

            // Use the movieId to find the corresponding Movie object
            val movie = getMovies().find { it.imdbID == movieId }

            if (movie != null) {
                DetailScreen(
                    navController = navController,
                    title = movie.title,
                    year = movie.year,
                    rated = movie.rated,
                    genre = movie.genre,
                    released = movie.released,
                    runtime = movie.runtime,
                    imdbID = movie.imdbID,
                    plot = movie.plot,
                    imdbRating = movie.imdbRating,
                    director = movie.director,
                    actors = movie.actors,
                    images = movie.images
                )

            }
        }
    }
}