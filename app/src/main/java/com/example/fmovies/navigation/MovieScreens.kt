package com.example.fmovies.navigation

enum class MovieScreens {
    HOME_SCREEN,
    DETAILS_SCREEN;

    companion object {
        fun fromRoute(route: String?): MovieScreens =
            when (route?.substringBefore("/")) {
                HOME_SCREEN.name -> HOME_SCREEN
                DETAILS_SCREEN.name -> DETAILS_SCREEN
                null -> HOME_SCREEN
                else -> {
                    throw IllegalStateException("This $route is invalid!")
                }
            }
    }
}

