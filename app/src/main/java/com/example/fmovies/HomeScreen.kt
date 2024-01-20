package com.example.fmovies

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.fmovies.navigation.MovieScreens

@Composable
fun HomeScreen(navController: NavController) {
    MyApp(navController = navController)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyApp(
    modifier: Modifier = Modifier,
    navController: NavController,
    movieList: List<String> = listOf(
        "Ted",
        "Ted 2",
        "Dumb and Dumber",
        "2012",
        "Saw",
        "Insidious",
        "The Conjuring",
        "Insidious: Chapter 2",
        "Furious 7",
        "The Conjuring 2",
        "Aquaman",
        "Malignant",
        "Insidious: Chapter 3",
        "Dead Silence"
    )
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(R.string.app_name),
                        color = Color.White,
                        fontFamily = FontFamily.Serif,
                        fontSize = 25.sp,
                        fontWeight = FontWeight.ExtraBold
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(Color(0xFFD11515)),
            )
        }
    )
    { paddingValues ->
        LazyColumn(modifier.padding(paddingValues)) {
            items(items = movieList) {
                MovieRow(movie = it) { movie ->
                    navController.navigate(route = MovieScreens.DETAILS_SCREEN.name + "/$movie")
                }
            }
        }
    }
}

@Composable
fun MovieRow(
    modifier: Modifier = Modifier,
    movie: String,
    onClick: (String) -> Unit
) {
    Card(
        modifier
            .padding(start = 20.dp, top = 15.dp, bottom = 10.dp, end = 20.dp)
            .fillMaxWidth()
            .height(130.dp)
            .clickable { onClick(movie) },
        shape = RoundedCornerShape(corner = CornerSize(10.dp)),
        colors = CardDefaults.cardColors(Color(0xFFEC3535)),
        elevation = CardDefaults.cardElevation(5.dp)
    ) {
        Row(
            modifier
                .fillMaxSize()
                .align(Alignment.CenterHorizontally)
                .padding(5.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            Icon(
                imageVector = Icons.Default.AccountBox,
                contentDescription = "Account Box",
                modifier
                    .size(100.dp)
                    .align(Alignment.CenterVertically),
                )
            Box(
                modifier
                    .align(Alignment.Top)
                    .padding(top = 20.dp)
            ) {
                Text(
                    text = movie,
                    modifier.padding(top = 5.dp),
                    color = Color.White,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.Serif
                )
            }
        }
    }
}

@Preview
@Composable
fun MyAppPreview() {
    MyApp(navController = rememberNavController())
}