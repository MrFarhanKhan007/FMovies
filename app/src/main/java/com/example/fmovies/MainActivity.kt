package com.example.fmovies

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.fmovies.navigation.MovieNavigation
import com.example.fmovies.ui.theme.FMoviesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FMoviesTheme {
                MovieNavigation()
            }
        }
    }
}

