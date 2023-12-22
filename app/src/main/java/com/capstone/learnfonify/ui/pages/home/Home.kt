package com.capstone.learnfonify.ui.pages.home

import android.content.Context
import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.FloatingActionButtonElevation
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.min
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.capstone.learnfonify.R
import com.capstone.learnfonify.constants.DummyListMenu
import com.capstone.learnfonify.data.ViewModelFactory
import com.capstone.learnfonify.data.response.CategoryItem
import com.capstone.learnfonify.data.response.CourseItem
import com.capstone.learnfonify.di.Injection
import com.capstone.learnfonify.ui.components.MyListCourse
import com.capstone.learnfonify.ui.components.ProfileButton
import com.capstone.learnfonify.ui.theme.LearnfonifyTheme
import com.kyy47.kyyairlines.common.UiState


@Composable
fun HomePage(
    context: Context,
    modifier: Modifier = Modifier,
    homeViewModel: HomeViewModel = viewModel(
        factory = ViewModelFactory.getInstance(context)
    ),
    username: String,
    urlProfile: String,
    onNagivateToDetail: (Int) -> Unit
) {
    HomeContent(
        homeViewModel = homeViewModel,
        username = username,
        urlProfile = urlProfile,
        onNagivateToDetail = onNagivateToDetail
    )
}

@Composable
fun HomeContent(
    modifier: Modifier = Modifier,
    homeViewModel: HomeViewModel,
    username: String,
    urlProfile: String,
    onNagivateToDetail: (Int) -> Unit
) {
    Column(
        modifier = Modifier
            .padding(16.dp, top = 16.dp, bottom = 0.dp)
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
    ) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 16.dp),
                horizontalArrangement = Arrangement.End
            ) {
                ProfileButton(urlProfile)
            }

            Row(
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            )
            {

                Text(
                    text = "Learn",
                    style = MaterialTheme.typography.headlineSmall.copy(
                        fontWeight = FontWeight.Bold,
                    ),
                )
                Text(
                    text = "Fonify",
                    style = MaterialTheme.typography.headlineSmall.copy(
                        fontWeight = FontWeight.Bold,
                        color = Color.Cyan
                    ),
                )
            }
        }

        Text(
            text = "Selamat pagi, $username!",
            style = MaterialTheme.typography.labelMedium.copy(
                fontWeight = FontWeight.Normal,
                textAlign = TextAlign.Center,
                color = Color.Black
            ),
            modifier = Modifier
                .fillMaxWidth()
        )

        Image(
            painter = painterResource(R.drawable.carousel),
            contentDescription = stringResource(R.string.setting),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp, end = 16.dp),
            contentScale = ContentScale.Crop,
        )


        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(
                start = 8.dp,
                end = 8.dp
            ),
            modifier = Modifier
                .padding(top = 16.dp)
        ) {
            items(DummyListMenu.dummyListMenu, key = { it.id }) {
                if (it.id != 2) {
                    OutlinedButton(
                        onClick = { /*TODO*/ },
                        border = BorderStroke(2.dp, Color.Cyan),
                        modifier = Modifier.defaultMinSize(minWidth = 50.dp)
                    ) {

                        Text(
                            text = it.menu,
                            style = MaterialTheme.typography.labelSmall.copy(
                                fontWeight = FontWeight.SemiBold,
                                textAlign = TextAlign.Start,
                                color = Color.Black
                            )
                        )


                    }
                } else {
                    Button(
                        onClick = { /*TODO*/ },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Cyan,
                            contentColor = Color.Black
                        )
                    ) {
                        Text(
                            text = it.menu,
                            style = MaterialTheme.typography.labelSmall.copy(
                                fontWeight = FontWeight.SemiBold,
                                textAlign = TextAlign.Start,
                                color = Color.Black
                            )
                        )
                    }
                }

            }
        }
        homeViewModel.uiState.collectAsState(
            initial = UiState.Loading
        ).value.let { uiState ->
            when (uiState) {
                is UiState.Loading -> {
                    homeViewModel.getCoursesFromCategory()
                    Box(
                        modifier =
                        Modifier
                            .fillMaxHeight()
                            .defaultMinSize(400.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        LinearProgressIndicator(
                            modifier = Modifier
                                .width(120.dp)
                                .padding(top = 24.dp),
                            color = MaterialTheme.colorScheme.secondary,
                        )
                    }

                }

                is UiState.Success -> {
//                   LazyColumn(){
//                       items(uiState.data, key = {}){
//                           MyListCourse(
//                            courses = it,
//                            modifier = Modifier
//                                .padding(top = 24.dp),
//                            onNagivateToDetail = onNagivateToDetail
//                        )
//                       }
//                   }

                    uiState.data.map {
                        MyListCourse(
                            courses = it,
                            modifier = Modifier
                                .padding(top = 24.dp),
                            onNagivateToDetail = onNagivateToDetail
                        )
                    }


                }

                is UiState.Error -> {
                    Text(
                        text = "Error when get data",
                        style = MaterialTheme.typography.headlineSmall.copy(
                            fontWeight = FontWeight.Normal,
                            textAlign = TextAlign.Center
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 24.dp)
                    )
                }
            }
        }
        Spacer(
            modifier = modifier
                .fillMaxWidth()
                .height(24.dp)
        )

    }

}

//@Preview(showBackground = true, device = Devices.PIXEL_4)
//@Composable
//fun HomeContentPreview() {
//    LearnfonifyTheme {
//        HomePage(username = "Joni", urlProfile = "", onNagivateToDetail = {})
//    }
//}



