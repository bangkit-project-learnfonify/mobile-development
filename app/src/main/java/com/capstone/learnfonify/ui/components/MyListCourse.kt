package com.capstone.learnfonify.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowRight
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.capstone.learnfonify.R
import com.capstone.learnfonify.data.response.CourseItem

@Composable
fun MyListCourse(
    modifier: Modifier = Modifier
        .padding(top = 24.dp),
    courses: List<CourseItem>,
    onNagivateToDetail: (Int) -> Unit,
    onNagivateToMore: (String) -> Unit,
    titleList: String = courses[0].category.toString(),
) {

    Column(
        modifier = modifier
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = titleList,
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Start
                )
            )
            Button(
                onClick = {
                    onNagivateToMore(titleList)
                },
                shape = RoundedCornerShape(12.dp),
                contentPadding = PaddingValues(10.dp),
                colors = ButtonDefaults.buttonColors(

                ),
                modifier = Modifier
                    .clipToBounds()
                    .padding(horizontal = 16.dp),
            )
            {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Semua",
                        style = MaterialTheme.typography.titleSmall.copy(
                            fontWeight = FontWeight.Bold,
                        ),
                        modifier = Modifier
                            .padding(horizontal = 8.dp)
                    )
                    Spacer(
                        modifier = Modifier
                    )
                    Icon(
                        imageVector = Icons.Rounded.ArrowRight, contentDescription = "All",
                        modifier = Modifier
                            .size(26.dp)
                    )
                }

            }
        }

        LazyRow(
            modifier = Modifier
                .padding(top = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(
                start = 8.dp,
                end = 8.dp
            ),
        ) {

            items(courses, key = { it.id }) { course ->

                Box(
                    modifier = Modifier
                        .shadow(
                            elevation = 10.dp,
                            shape = RoundedCornerShape(8.dp)
                        )
                        .clip(RoundedCornerShape(24.dp))
                        .background(Color.White)
                        .size(196.dp)
                        .padding(12.dp)
                        .clickable { onNagivateToDetail(course.id) },
                ) {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {

                        AsyncImage(
                            model = course.image,
                            contentDescription = course.title,
                            modifier = Modifier
                                .size(70.dp)
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
                                .fillMaxHeight(),
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
                            if (course.fee != null) {
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
}

