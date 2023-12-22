package com.capstone.learnfonify.ui.pages.more

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.SaveAlt
import androidx.compose.material.icons.rounded.TypeSpecimen
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.capstone.learnfonify.data.ViewModelFactory
import com.capstone.learnfonify.data.local.entity.SavedCourseEntity
import com.capstone.learnfonify.data.response.CourseItem
import com.capstone.learnfonify.ui.components.MyCardCourse
import com.capstone.learnfonify.ui.pages.home.HomeViewModel
import com.capstone.learnfonify.ui.theme.LearnfonifyTheme
import com.kyy47.kyyairlines.common.UiState


@Composable
fun MorePage(
    context: Context,
    onNavigateToDetail: (Int) -> Unit,
    categoryCourses: String,
    moreViewModel: MoreViewModel = viewModel(
        factory = ViewModelFactory.getInstance(context)
    ),
) {
    MoreContent(
        onNavigateToDetail = onNavigateToDetail,
        categoryCourses = categoryCourses,
        moreViewModel = moreViewModel
    )

}


@Composable
fun MoreContent(
    onNavigateToDetail: (Int) -> Unit,
    categoryCourses: String,
    moreViewModel: MoreViewModel
) {


    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 24.dp, end = 16.dp, start = 16.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Rounded.TypeSpecimen,
                contentDescription = categoryCourses,
                modifier = Modifier
                    .size(38.dp)
            )
            Text(
                text = categoryCourses,
                style = MaterialTheme.typography.headlineMedium.copy(
                    fontWeight = FontWeight.Bold,
                ),
            )

        }

        moreViewModel.coursesState.collectAsState(
            initial = UiState.Loading
        ).value.let { uiState ->
            when (uiState) {
                is UiState.Loading -> {
                    moreViewModel.getCoursesFromCategory(categoryCourses)
                    Box(
                        modifier =
                        Modifier
                            .fillMaxWidth()
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
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 34.dp),
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {

                        items(uiState.data, key = { it.id }) { course ->
                            MyCardCourse(
                                course =
                                SavedCourseEntity(
                                    courseId = course.id,
                                    description = course.description.toString(),
                                    titleCourse = course.title.toString(),
                                    imgUrl = course.image.toString(),
                                ), onNagivateToDetail = onNavigateToDetail
                            )
                        }

                    }
                }

                is UiState.Error -> {
                }
            }

        }


    }

}

@Preview
@Composable
fun MorePagePrev() {
    LearnfonifyTheme {
//        MorePage()
    }
}