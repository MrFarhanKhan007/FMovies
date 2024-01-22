package com.example.fmovies

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.example.fmovies.data.Movie
import com.example.fmovies.data.getMovies
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
    movieList: List<Movie> = getMovies()
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(R.string.app_name),
                        color = Color.Black,
                        fontFamily = FontFamily.SansSerif,
                        fontSize = 25.sp,
                        fontWeight = FontWeight.ExtraBold
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(Color(0xFFE53935)),
            )
        },
        modifier = Modifier.fillMaxSize(),
        containerColor = Color(0xF5252525)
    )
    { paddingValues ->
        LazyColumn(modifier.padding(paddingValues)) {
            items(items = movieList) {
                MovieRow(movie = it) { movie ->
                    navController.navigate(route = MovieScreens.DETAILS_SCREEN.name + "/movieId=${movie}")
                }
            }
        }
    }
}

@Composable
fun MovieRow(
    modifier: Modifier = Modifier,
    movie: Movie,
    onClick: (String) -> Unit
) {
    Card(
        modifier
            .padding(start = 20.dp, top = 15.dp, bottom = 10.dp, end = 20.dp)
            .fillMaxWidth()
            .height(130.dp)
            .clickable {
                onClick(movie.imdbID)
            },
        shape = RoundedCornerShape(corner = CornerSize(10.dp)),
        colors = CardDefaults.cardColors(Color.Black),
        elevation = CardDefaults.cardElevation(5.dp)
    ) {
        Row(
            modifier
                .fillMaxSize()
                .align(Alignment.CenterHorizontally)
                .padding(10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {

            AsyncImage(
                model = movie.images[1],
                contentDescription = null,
                modifier
                    .size(100.dp)
                    .clip(shape = RoundedCornerShape(corner = CornerSize(10.dp))),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier.width(10.dp))
            Column(modifier.align(Alignment.CenterVertically)) {

                Text(
                    text = movie.title,
                    fontSize = 20.sp,
                    color = Color(0xFFE53935),
                    fontWeight = FontWeight.ExtraBold,
                    fontFamily = FontFamily.Default
                )
                Spacer(modifier.height(5.dp))
                Text(
                    text = "Directed By: ${movie.director}",
                    color = Color(0xFFE53935),
                    fontSize = 15.sp,
                    lineHeight = 20.sp,
                    fontWeight = FontWeight.Normal,
                    fontFamily = FontFamily.Default
                )

                Text(
                    text = "Released: ${movie.released}",
                    color = Color(0xFFE53935),
                    fontSize = 15.sp,
                    lineHeight = 20.sp,
                    fontWeight = FontWeight.Normal,
                    fontFamily = FontFamily.Default
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

@Preview
@Composable
fun MovieRowPreview() {
    MovieRow(movie = Movie(
        "The Game of Thrones",
        "2011–",
        "TV-MA",
        "17 Apr 2011",
        "56 min",
        "Adventure, Drama, Fantasy",
        "David Benioff, D.B. Weiss",
        "Peter Dinklage, Lena Headey, Emilia Clarke, Kit Harington",
        "While a civil war brews between several noble families in Westeros, the children of the former rulers of the land attempt to rise up to power. Meanwhile, a forgotten race, bent on destruction, plans to return after thousands of years in the North.",
        "9.5",
        "tt0944947",
        listOf(
            "https://images-na.ssl-images-amazon.com/images/M/MV5BNDc1MGUyNzItNWRkOC00MjM1LWJjNjMtZTZlYWIxMGRmYzVlXkEyXkFqcGdeQXVyMzU3MDEyNjk@._V1_SX1777_CR0,0,1777,999_AL_.jpg",
            "https://images-na.ssl-images-amazon.com/images/M/MV5BZjZkN2M5ODgtMjQ2OC00ZjAxLWE1MjMtZDE0OTNmNGM0NWEwXkEyXkFqcGdeQXVyNjUxNzgwNTE@._V1_SX1777_CR0,0,1777,999_AL_.jpg",
            "https://images-na.ssl-images-amazon.com/images/M/MV5BMDk4Y2Y1MDAtNGVmMC00ZTlhLTlmMmQtYjcyN2VkNzUzZjg2XkEyXkFqcGdeQXVyMjk3NTUyOTc@._V1_SX1777_CR0,0,1777,999_AL_.jpg",
            "https://images-na.ssl-images-amazon.com/images/M/MV5BNjZjNWIzMzQtZWZjYy00ZTkwLWJiMTYtOWRkZDBhNWJhY2JmXkEyXkFqcGdeQXVyMjk3NTUyOTc@._V1_SX1777_CR0,0,1777,999_AL_.jpg",
            "https://images-na.ssl-images-amazon.com/images/M/MV5BNTMyMTRjZWEtM2UxMS00ZjU5LWIxMTYtZDA5YmJhZmRjYTc4XkEyXkFqcGdeQXVyMjk3NTUyOTc@._V1_SX1777_CR0,0,1777,999_AL_.jpg"
        )
    ), onClick = {})
}