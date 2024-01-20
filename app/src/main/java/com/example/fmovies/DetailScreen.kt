package com.example.fmovies

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.LineBreak
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@Composable
fun DetailScreen(movieData: String?, navController: NavController) {
    MyAppDetails(movieData = movieData, navController = navController)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyAppDetails(
    modifier: Modifier = Modifier,
    movieData: String?,
    navController: NavController
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                },
                navigationIcon = {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Back Button",
                        modifier.clickable {
                            navController.popBackStack()
                        }
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(Color(0xFFD11515)),
            )
        },
    )
    { paddingValues ->
        paddingValues.calculateTopPadding()
        Box(
            modifier
                .fillMaxSize()
                .padding(10.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = movieData ?: "",
                fontSize = 50.sp,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.ExtraBold,
                style = TextStyle(lineBreak = LineBreak.Simple)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DetailScreenPreview() {
    MyAppDetails(movieData = "Dumb and Dumber", navController = rememberNavController())
}