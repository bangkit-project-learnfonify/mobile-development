package com.capstone.learnfonify.ui.pages.coursedetail

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.lifecycle.viewmodel.compose.viewModel
import com.capstone.learnfonify.R
import com.capstone.learnfonify.data.ViewModelFactory
import com.capstone.learnfonify.data.response.DetailCourseItem
import com.capstone.learnfonify.di.Injection
import com.capstone.learnfonify.ui.pages.home.MyListCourse
import com.capstone.learnfonify.ui.theme.LearnfonifyTheme
import com.kyy47.kyyairlines.common.UiState

@Composable
fun CourseDetailPage(
    detailViewModel: CourseDetailViewModel = viewModel(
        factory = ViewModelFactory(Injection.provideRepository())
    ),
    courseId: Int
) {
    detailViewModel.uiState.collectAsState(
        initial = UiState.Loading
    ).value.let {uiState ->
        when(uiState){
            is UiState.Loading -> {
                detailViewModel.getCourseFromId(courseId)
                Box(modifier =
                Modifier
                    .fillMaxHeight()
                    .fillMaxHeight()
                    .defaultMinSize(400.dp),
                    contentAlignment = Alignment.Center
                ){
                    LinearProgressIndicator(
                        modifier = Modifier
                            .width(120.dp)
                            .padding(top = 24.dp),
                        color = MaterialTheme.colorScheme.secondary,
                    )
                }

            }
            is UiState.Success -> {
                CourseDetailContent(uiState.data)
            }

            is UiState.Error -> {
                Text(text = "No data to Show")
            }
        }
    }

}

@Composable
fun CourseDetailContent(
    course: DetailCourseItem,
){
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
    ) {
        Box(modifier = Modifier
            .fillMaxWidth()
            .zIndex(1f),
            contentAlignment = Alignment.Center

            ){
            Image(painter = painterResource(id = R.drawable.course_img_detail)
                , contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .blur(radius = 8.dp),

                )
            Image(painter = painterResource(id = R.drawable.course_img_detail)
                , contentDescription = null,
                modifier = Modifier
                    .size(286.dp),
                )

        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp)
                .graphicsLayer {
                    this.translationY = -32.dp.toPx()
                }
                .zIndex(5f)

        ) {
            Box(
                modifier = Modifier
                    .shadow(
                        elevation = 10.dp,
                        shape = RoundedCornerShape(6.dp)
                    )
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(24.dp))
                    .background(Color.White)
                    .padding(8.dp)

                    ,
            ) {
                Column(
                    Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = course.title.toString(),
                        style = MaterialTheme.typography.headlineSmall.copy(
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center
                        ),
                    )
                    Text(text = course.category.toString() ,
                        style = MaterialTheme.typography.labelSmall.copy(
                            fontWeight = FontWeight.Normal,
                            textAlign = TextAlign.Center
                        ),
                        modifier = Modifier
                            .clip(RoundedCornerShape(16.dp))
                            .background(Color.Cyan) // Set background color
                            .border(2.dp, Color.LightGray) // Set border with thickness and color
                            .padding(horizontal = 8.dp, vertical = 4.dp), //
                    )
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 32.dp),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Text(text = course.fee.toString(),
                            style = MaterialTheme.typography.labelSmall.copy(
                                fontWeight = FontWeight.Normal,
                                textAlign = TextAlign.Center,
                                color = Color.White
                            ),
                            modifier = Modifier
                                .clip(RoundedCornerShape(16.dp))
                                .background(Color.Magenta) // Set background color
                                .border(1.dp, Color.Black) // Set border with thickness and color
                                .padding(horizontal = 8.dp, vertical = 4.dp), //
                        )
                        Text(text = "Kursus",
                            style = MaterialTheme.typography.labelSmall.copy(
                                fontWeight = FontWeight.Normal,
                                textAlign = TextAlign.Center
                            ),
                            modifier = Modifier
                                .clip(RoundedCornerShape(16.dp))
                                .background(Color.Transparent) // Set background color
                                .border(
                                    1.dp,
                                    Color.LightGray
                                ) // Set border with thickness and color
                                .padding(horizontal = 8.dp, vertical = 4.dp), //
                        )
                    }
                    Spacer(modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 10.dp)
                        .height(1.dp)
                        .background(Color.Gray))
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(text = course.organizer.toString(),
                            style = MaterialTheme.typography.labelMedium.copy(
                                fontWeight = FontWeight.SemiBold,
                                textAlign = TextAlign.Center
                            ))
                        Text(text = course.instructor.toString(),
                            style = MaterialTheme.typography.labelMedium.copy(
                                fontWeight = FontWeight.Normal,
                                textAlign = TextAlign.Center
                            ))
                    }
                    Spacer(modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 10.dp)
                        .height(1.dp)
                        .background(Color.Gray))
                    Text(text = course.description.toString(),
                        style = MaterialTheme.typography.labelSmall.copy(
                            fontWeight = FontWeight.Light,
                            textAlign = TextAlign.Start
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                    )
                }
            }
        }

    }
}

@Preview(showBackground = true, device = Devices.PIXEL_4)
@Composable
fun CourseDetailPagePreview(){
    LearnfonifyTheme {
        CourseDetailPage(courseId = 12)
    }
}