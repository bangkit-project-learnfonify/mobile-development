package com.capstone.learnfonify.ui.pages.saved

import android.content.Context
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.SaveAlt
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.capstone.learnfonify.R
import com.capstone.learnfonify.constants.DummyListMenu
import com.capstone.learnfonify.data.ViewModelFactory
import com.capstone.learnfonify.ui.components.MyCardCourse
import com.capstone.learnfonify.ui.pages.home.HomeViewModel
import com.kyy47.kyyairlines.common.UiState


@Composable
fun SavedPage(
    context: Context,
    savedViewModel: SavedViewModel = viewModel(
        factory = ViewModelFactory.getInstance(context)
    ),
    onNagivateToDetail: (Int) -> Unit
) {
    SavedContent(
        context = context,
        savedViewModel = savedViewModel,
        onNagivateToDetail = onNagivateToDetail
    )
}

@Composable
fun SavedContent(
    context: Context,
    savedViewModel: SavedViewModel,
    onNagivateToDetail: (Int) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 24.dp, end = 16.dp, start = 16.dp, bottom = 0.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Rounded.SaveAlt,
                contentDescription = "Saved",
                modifier = Modifier
                    .size(38.dp)
            )
            Text(
                text = "Koleksi",
                style = MaterialTheme.typography.headlineMedium.copy(
                    fontWeight = FontWeight.Bold,
                ),
            )

        }
        savedViewModel.savedCourseState.collectAsState(
            initial = UiState.Loading
        ).value.let { uiState ->
            when (uiState) {
                is UiState.Loading -> {
                    savedViewModel.getSavedCourse()
                }

                is UiState.Success -> {
                    if (!uiState.data.isNullOrEmpty()) {

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
                                        border = BorderStroke(2.dp, colorResource(id = R.color.learnfornify_blue)),
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
                                            containerColor = colorResource(id = R.color.learnfornify_blue),
                                            contentColor = Color.White
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

                        LazyColumn(
                            modifier = Modifier
                                .fillMaxWidth()
                                .fillMaxHeight()
                                .padding(top = 16.dp),
                            verticalArrangement = Arrangement.spacedBy(16.dp)
                        ) {
                            items(uiState.data, key = { it.courseId }) {
                                MyCardCourse(it, onNagivateToDetail = onNagivateToDetail)

                            }

                        }

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
//fun StoredPagePrev() {
//    StoredPage(this)
//}