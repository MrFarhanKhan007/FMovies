package com.example.fmovies

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.example.fmovies.data.getMovies

@Composable
fun DetailScreen(
    navController: NavController,
    title: String,
    year: String,
    rated: String,
    released: String,
    runtime: String,
    genre: String,
    imdbRating: String,
    imdbID: String,
    plot: String,
    director: String,
    actors: String,
    images: List<String>
) {
    MyAppDetails(
        navController = navController,
        title = title,
        year = year,
        rated = rated,
        released = released,
        runtime = runtime,
        genre = genre,
        imdbRating = imdbRating,
        imdbID = imdbID,
        plot = plot,
        director = director,
        actors = actors,
        images = images
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyAppDetails(
    modifier: Modifier = Modifier,
    navController: NavController,
    title: String,
    year: String,
    rated: String,
    genre: String,
    released: String,
    runtime: String,
    imdbID: String,
    plot: String,
    imdbRating: String,
    director: String,
    actors: String,
    images: List<String>,
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
                colors = TopAppBarDefaults.topAppBarColors(Color(0xFFE53935)),
            )
        },
        modifier = Modifier.fillMaxSize(),
        containerColor = Color(0xF5252525)
    )
    { paddingValues ->
        paddingValues.calculateTopPadding()
        getMovies()
        Column {

            MovieDetailsRow(modifier, images, title, director, genre, runtime)
            Spacer(modifier.height(10.dp))
            Divider(modifier.height(1.dp), color = Color.LightGray)
            MovieInfo(
                title,
                year,
                rated,
                released,
                runtime,
                genre,
                director,
                actors,
                plot,
                imdbRating,
                imdbID,
                modifier
            )


        }
    }
}

@Composable
private fun MovieInfo(
    title: String,
    year: String,
    rated: String,
    released: String,
    runtime: String,
    genre: String,
    director: String,
    actors: String,
    plot: String,
    imdbRating: String,
    imdbID: String,
    modifier: Modifier
) {
    LazyColumn {
        items(1) {

            Text(
                text =
                "Title: $title \n" +
                        "Year: $year\n" +
                        "Rated: $rated \n" +
                        "Released: $released\n" +
                        "Runtime: $runtime\n" +
                        "Genre: $genre\n" +
                        "Director: $director\n" +
                        "Actors: $actors\n" +
                        "Plot: $plot\n" +
                        "IMDb Rating: $imdbRating\n" +
                        "IMDb ID: $imdbID",
                modifier.padding(10.dp),
                style = TextStyle(
                    color = Color.White,
                    fontSize = 20.sp,
                    lineHeight = 30.sp,
                    textAlign = TextAlign.Left,
                    fontWeight = FontWeight.SemiBold
                )
            )
        }
    }
}

@Composable
private fun MovieDetailsRow(
    modifier: Modifier,
    images: List<String>,
    title: String,
    director: String,
    genre: String,
    runtime: String
) {
    Row(
        modifier
            .padding(top = 75.dp, start = 10.dp)
            .fillMaxWidth()
    ) {

        AsyncImage(
            model = images[1],
            contentDescription = "Movie Image",
            modifier
                .size(125.dp)
                .padding()
                .clip(shape = RectangleShape),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier.width(15.dp))

        Column(modifier.padding()) {
            Text(
                text = title,
                style = TextStyle(
                    fontSize = 25.sp,
                    fontFamily = FontFamily.Default,
                    fontWeight = FontWeight.ExtraBold,
                    color = Color.White
                )
            )
            Spacer(modifier.height(10.dp))
            Text(
                text = "Directed By: $director",
                style = TextStyle(
                    color = Color.White,
                    fontFamily = FontFamily.Default,
                    fontWeight = FontWeight.Normal
                )
            )
            Text(
                text = "Genre: $genre",
                style = TextStyle(
                    color = Color.White,
                    fontFamily = FontFamily.Default,
                    fontWeight = FontWeight.Normal
                )
            )
            Text(
                text = "Runtime: $runtime",
                style = TextStyle(
                    color = Color.White,
                    fontFamily = FontFamily.Serif,
                    fontWeight = FontWeight.Normal
                )
            )

        }
    }
}


@Preview(showBackground = true)
@Composable
fun DetailScreenPreview() {
    MyAppDetails(
        navController = rememberNavController(),
        title = "The Game of Thrones",
        year = "2011–",
        rated = "TV-MA",
        released = "17 Apr 2011",
        runtime = "56 min",
        genre = "Adventure, Drama, Fantasy",
        director = "David Benioff, D.B. Weiss",
        actors = "Peter Dinklage, Lena Headey, Emilia Clarke, Kit Harington",
        plot = "While a civil war brews between several noble families in Westeros, the children of the former rulers of the land attempt to rise up to power. Meanwhile, a forgotten race, bent on destruction, plans to return after thousands of years in the North.",
        imdbRating = "9.5",
        imdbID = "tt0944947",
        images = listOf(
            "https://images-na.ssl-images-amazon.com/images/M/MV5BNDc1MGUyNzItNWRkOC00MjM1LWJjNjMtZTZlYWIxMGRmYzVlXkEyXkFqcGdeQXVyMzU3MDEyNjk@._V1_SX1777_CR0,0,1777,999_AL_.jpg",
            "https://images-na.ssl-images-amazon.com/images/M/MV5BZjZkN2M5ODgtMjQ2OC00ZjAxLWE1MjMtZDE0OTNmNGM0NWEwXkEyXkFqcGdeQXVyNjUxNzgwNTE@._V1_SX1777_CR0,0,1777,999_AL_.jpg",
            "https://images-na.ssl-images-amazon.com/images/M/MV5BMDk4Y2Y1MDAtNGVmMC00ZTlhLTlmMmQtYjcyN2VkNzUzZjg2XkEyXkFqcGdeQXVyMjk3NTUyOTc@._V1_SX1777_CR0,0,1777,999_AL_.jpg",
            "https://images-na.ssl-images-amazon.com/images/M/MV5BNjZjNWIzMzQtZWZjYy00ZTkwLWJiMTYtOWRkZDBhNWJhY2JmXkEyXkFqcGdeQXVyMjk3NTUyOTc@._V1_SX1777_CR0,0,1777,999_AL_.jpg",
            "https://images-na.ssl-images-amazon.com/images/M/MV5BNTMyMTRjZWEtM2UxMS00ZjU5LWIxMTYtZDA5YmJhZmRjYTc4XkEyXkFqcGdeQXVyMjk3NTUyOTc@._V1_SX1777_CR0,0,1777,999_AL_.jpg"
        )
    )
}