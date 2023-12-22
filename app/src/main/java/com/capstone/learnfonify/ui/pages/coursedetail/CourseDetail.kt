package com.capstone.learnfonify.ui.pages.coursedetail

import android.content.Context
import android.util.Log
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.zIndex
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.capstone.learnfonify.R
import com.capstone.learnfonify.data.ViewModelFactory
import com.capstone.learnfonify.data.local.entity.SavedCourseEntity
import com.capstone.learnfonify.data.response.DetailCourseItem
import com.capstone.learnfonify.di.Injection
import com.capstone.learnfonify.ui.components.DetailBar
import com.capstone.learnfonify.ui.components.RatingBar
import com.capstone.learnfonify.ui.theme.LearnfonifyTheme
import com.kyy47.kyyairlines.common.UiState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@Composable
fun CourseDetailPage(
    context: Context,
    detailViewModel: CourseDetailViewModel = viewModel(
        factory = ViewModelFactory.getInstance(context)
    ),
    courseId: Int,
) {




    detailViewModel.uiState.collectAsState(
        initial = UiState.Loading
    ).value.let { uiState ->
        when (uiState) {
            is UiState.Loading -> {
                detailViewModel.getCourseFromId(courseId)
                Box(
                    modifier =
                    Modifier
                        .fillMaxHeight()
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
                CourseDetailContent(
                    uiState.data,
                    onInsertSavedCourse = {
                        detailViewModel.insertToSavedCourse(
                            SavedCourseEntity(
                               uiState.data.id,
                               uiState.data.title,
                               uiState.data.description,
                               uiState.data.image.toString(),
                            ),
                        )

                    },
                    onRemoveSavedCourse = {
                        detailViewModel.removeSavedCourse(it)
                    },
                    detailViewModel,
                    uiState.data.id
                    )
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
    onInsertSavedCourse: () -> Unit,
    onRemoveSavedCourse: (Int) ->Unit,
    detailViewModel: CourseDetailViewModel,
    id: Int
) {
    var isDisplayModal by remember {
        mutableStateOf(false)
    }
    var isDoneGiveRating by remember {
        mutableStateOf(false)
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .then(
                if (!isDisplayModal) {
                    Modifier.verticalScroll(rememberScrollState())
                } else {
                    Modifier.blur(radius = 8.dp)
                }
            ),

        ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .zIndex(1f),
            contentAlignment = Alignment.Center

        ) {
            AsyncImage(
                model = course.image,
                contentDescription = course.title,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(356.dp)
                    .blur(radius = 8.dp),

                )
            AsyncImage(
                model = course.image,
                contentDescription = course.title,
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
                    .padding(8.dp),
            ) {
                Column(
                    Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {


                    Text(
                        text = course.title.toString(),
                        style = MaterialTheme.typography.headlineSmall.copy(
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center
                        ),
                    )
                    Text(
                        text = course.category.toString(),
                        style = MaterialTheme.typography.labelSmall.copy(
                            fontWeight = FontWeight.Normal,
                            textAlign = TextAlign.Center
                        ),
                        modifier = Modifier
                            .clip(RoundedCornerShape(16.dp))
                            .background(Color.Cyan) // Set background color
                            .border(2.dp, Color.LightGray) // Set border with thickness and color
                            .padding(horizontal = 8.dp, vertical = 4.dp)

                    )
                    if(!course.rating.isNullOrEmpty()){
                        RatingBar(onRatingChange = {
                        },
                            rating = course.rating.toDouble(),
                            starsColor = Color.Black
                        )
                    }


                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 32.dp),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        if(!course.fee.isNullOrEmpty()){
                            Text(
                                text = course.fee.toString(),
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
                        }

                        Text(
                            text = "Kursus",
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
                    Spacer(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 10.dp)
                            .height(1.dp)
                            .background(Color.Gray)
                    )
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = course.organizer.toString(),
                            style = MaterialTheme.typography.labelMedium.copy(
                                fontWeight = FontWeight.SemiBold,
                                textAlign = TextAlign.Center
                            )
                        )
                        if(!course.instructor.isNullOrEmpty()){
                            Text(
                                text = course.instructor.toString(),
                                style = MaterialTheme.typography.labelMedium.copy(
                                    fontWeight = FontWeight.Normal,
                                    textAlign = TextAlign.Center
                                )
                            )
                        }

                    }
                    Spacer(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 10.dp)
                            .height(1.dp)
                            .background(Color.Gray)
                    )
                    Text(
                        text = course.description.toString(),
                        style = MaterialTheme.typography.labelSmall.copy(
                            fontWeight = FontWeight.Light,
                            textAlign = TextAlign.Start
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                    )
                    Spacer(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 10.dp)
                            .height(8.dp)
                    )
                    if (!isDoneGiveRating) {
                        Button(
                            onClick = {
                                isDisplayModal = true
                            },
                            shape = RoundedCornerShape(12.dp),
                            contentPadding = PaddingValues(10.dp),
                            colors = ButtonDefaults.buttonColors(

                            ),
                            modifier = Modifier
                                .clipToBounds()
                                .padding(bottom = 24.dp),
                        )
                        {
                            Text(
                                text = "beri rating",
                                style = MaterialTheme.typography.titleSmall.copy(
                                    fontWeight = FontWeight.Bold,
                                ),
                                modifier = Modifier
                                    .padding(vertical = 4.dp, horizontal = 8.dp)
                            )
                        }
                    }

                }
            }
        }

        DetailBar(onInsertSavedCourse = onInsertSavedCourse,
            detailViewModel = detailViewModel,
            id = id,
            onRemoveSavedCourse = onRemoveSavedCourse
            )

        var rating by remember {
            mutableDoubleStateOf(0.0)
        }
        if (isDisplayModal) {
            StarDialog(
                onDismissRequest = {
                    isDisplayModal = false
                },
                onConfirmation = {
                    isDisplayModal = false
                    isDoneGiveRating = true
                },
                onRatingChange = {
                    rating = it
                },
                rating = rating,
            )

        }

    }
}

//@Preview(showBackground = true, device = Devices.PIXEL_4)
//@Composable
//fun CourseDetailPagePreview() {
//    LearnfonifyTheme {
//        CourseDetailPage(courseId = 12)
//    }
//}

@Composable
fun StarDialog(
    onDismissRequest: () -> Unit,
    onConfirmation: () -> Unit,
    rating: Double,
    onRatingChange: (Double) -> Unit,
) {
    Dialog(onDismissRequest = { onDismissRequest() }) {
        // Draw a rectangle shape with rounded corners inside the dialog
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White),
            shape = RoundedCornerShape(16.dp),
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White)
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {


                Text(
                    text = "Berikan Rating Course Ini",
                    style = MaterialTheme.typography.labelLarge.copy(
                        fontWeight = FontWeight.SemiBold,
                        textAlign = TextAlign.Center,
                        color = Color.Black
                    )
                )
                RatingBar(
                    modifier = Modifier
                        .size(55.dp)
                        .padding(top = 6.dp),
                    rating = rating,
                    starsColor = Color.Green,
                    onRatingChange = onRatingChange
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                ) {
                    TextButton(
                        onClick = { onDismissRequest() },
                        modifier = Modifier
                            .padding(8.dp)
                            .background(Color.Red),
                    ) {
                        Text("Batal")
                    }
                    TextButton(
                        onClick = { onConfirmation() },
                        modifier = Modifier
                            .padding(8.dp)
                            .background(Color.Cyan),
                    ) {
                        Text("Kirim")
                    }
                }
            }


        }
    }
}
