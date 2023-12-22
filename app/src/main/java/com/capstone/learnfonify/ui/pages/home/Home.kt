package com.capstone.learnfonify.ui.pages.home

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
import com.capstone.learnfonify.R
import com.capstone.learnfonify.constants.DummyListMenu
import com.capstone.learnfonify.data.ViewModelFactory
import com.capstone.learnfonify.data.response.CategoryItem
import com.capstone.learnfonify.data.response.CourseItem
import com.capstone.learnfonify.di.Injection
import com.capstone.learnfonify.ui.theme.LearnfonifyTheme
import com.kyy47.kyyairlines.common.UiState


@Composable
fun HomePage
            (modifier: Modifier = Modifier,
             homeViewModel: HomeViewModel = viewModel(
                 factory = ViewModelFactory(Injection.provideRepository())
             ),
) {
    HomeContent(homeViewModel = homeViewModel)

}

@Composable
fun HomeContent(modifier: Modifier = Modifier,
                homeViewModel: HomeViewModel,
) {
   Column(
       modifier = Modifier
           .padding(16.dp)
           .fillMaxWidth()
           .verticalScroll(rememberScrollState())
   ) {
       Row(
           Modifier.fillMaxWidth(),
           horizontalArrangement = Arrangement.Center
       ) {
           Text(text = "Learn",
               style = MaterialTheme.typography.headlineSmall.copy(
                   fontWeight = FontWeight.Bold,
               ),
           )
           Text(text = "Fonify",
               style = MaterialTheme.typography.headlineSmall.copy(
                   fontWeight = FontWeight.Bold,
                   color = Color.Cyan
               ),
           )
       }
       Text(
           text = "Selamat pagi, Anna!",
           style = MaterialTheme.typography.labelSmall.copy(
               fontWeight = FontWeight.Normal,
               textAlign = TextAlign.Center,
               color = Color.Black
           ),
           modifier = Modifier
               .fillMaxWidth()
               .padding(top = 6.dp)
       )

       Image(painter = painterResource(R.drawable.carousel)
           , contentDescription = stringResource(R.string.setting),
           modifier = Modifier
               .fillMaxWidth()
               .padding(top = 20.dp),
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
        ){
            items(DummyListMenu.dummyListMenu, key = {it.id}){
                if (it.id != 2){
                    OutlinedButton(onClick = { /*TODO*/ },
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
                }else{
                    Button(
               onClick = { /*TODO*/ },
               colors = ButtonDefaults.buttonColors(
                   containerColor = Color.Cyan,
                   contentColor = Color.Black)
           ) {
               Text(
                   text =  it.menu,
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
       ).value.let {uiState ->
           when(uiState){
               is UiState.Loading -> {
                   homeViewModel.getCoursesFromCategory()
                   Box(modifier =
                   Modifier.fillMaxHeight()
                       .defaultMinSize(400.dp),
                       contentAlignment = Alignment.Center
                   ){
                       LinearProgressIndicator(
                           modifier = Modifier.width(120.dp)
                               .padding(top = 24.dp),
                           color = MaterialTheme.colorScheme.secondary,
                       )
                   }

               }
               is UiState.Success -> {
                   uiState.data.map {
                       MyListCourse(
                           courses = it,
                           modifier = Modifier
                               .padding(top = 24.dp)
                           )
                   }
                   }

               is UiState.Error -> {

               }
           }
       }

   }

}

//@Preview(showBackground = true, device = Devices.PIXEL_4)
//@Composable
//fun HomeContentPreview() {
//    LearnfonifyTheme {
//        HomeContent()
//    }
//}


@Composable
fun MyListCourse(
    modifier: Modifier = Modifier
        .padding(top = 24.dp),
    courses: List<CourseItem>
) {

    Column(
        modifier = modifier
    ) {
        Text(
            text = courses[0].category.toString(),
            style = MaterialTheme.typography.titleMedium.copy(
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Start
            )
        )
        LazyRow(
            modifier = Modifier
                .padding(top = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(
                start = 8.dp,
                end = 8.dp
            ),
        ) {

            items(courses, key = { it.id!!}) {course ->
                Box(
                    modifier = Modifier
                        .shadow(
                            elevation = 10.dp,
                            shape = RoundedCornerShape(8.dp)
                        )
                        .clip(RoundedCornerShape(24.dp))
                        .background(Color.White)
                        .size(196.dp)
                        .padding(12.dp),
                ) {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Image(
                            painter = painterResource(R.drawable.course_img),
                            contentDescription = stringResource(R.string.setting),
                            modifier = Modifier
                                .width(70.dp)
                                .clip(RoundedCornerShape(8.dp)),
                            contentScale = ContentScale.Crop,
                        )
                        Text(
                            text = course.title.toString(),
                            style = MaterialTheme.typography.titleMedium.copy(
                                fontWeight = FontWeight.Bold,
                                textAlign = TextAlign.Center
                            ),
                            maxLines = 2,
                            overflow = TextOverflow.Ellipsis,
                            modifier = Modifier.padding(top = 6.dp)
                        )
                        Text(
                            text = course.category.toString(),
                            style = MaterialTheme.typography.labelSmall.copy(
                                fontWeight = FontWeight.Light,
                                textAlign = TextAlign.Center
                            ),
                            modifier = Modifier.padding(top = 3.dp)
                        )
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .fillMaxHeight()
                                .padding(top = 8.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.Bottom
                        ) {
                            Text(
                                text = course.organizer.toString(),
                                style = MaterialTheme.typography.labelSmall.copy(
                                    fontWeight = FontWeight.Bold,
                                    textAlign = TextAlign.Start
                                ),
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis,
                                modifier = Modifier
                                    .padding(top = 6.dp)
                                    .width(70.dp)
                            )
                            Text(
                                text = course.fee.toString(),
                                style = MaterialTheme.typography.labelSmall.copy(
                                    fontWeight = FontWeight.Light,
                                    textAlign = TextAlign.Start
                                ),

                                )
                        }
                    }
                }
            }


        }
    }

}

