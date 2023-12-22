package com.capstone.learnfonify.ui.components

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.capstone.learnfonify.R
import com.capstone.learnfonify.ui.pages.coursedetail.CourseDetailViewModel
import com.kyy47.kyyairlines.common.UiState


@Composable
fun DetailBar(
    onInsertSavedCourse: () -> Unit,
    detailViewModel: CourseDetailViewModel,
    id: Int,
    onRemoveSavedCourse: (Int) -> Unit,
    linkUrl: String
) {

    val isSaved = remember {
        mutableStateOf(false)
    }

    detailViewModel.isSavedState.collectAsState(
        initial = UiState.Loading
    ).value.let { uiState ->
        when (uiState) {
            is UiState.Loading -> {
                detailViewModel.checkSavedCourse(id)
            }

            is UiState.Success -> {
                isSaved.value = uiState.data
            }

            is UiState.Error -> {
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = if (isSaved.value) painterResource(R.drawable.saved) else painterResource(
                    R.drawable.not_saved
                ),
                contentDescription = "save",
                modifier = Modifier
                    .size(46.dp)
                    .clickable {
                        if (isSaved.value) {
                            onRemoveSavedCourse(id)
                        } else {
                            onInsertSavedCourse()
                        }

                    }
            )

            Image(
                painter = painterResource(id = R.drawable.share),
                contentDescription = "share",
                modifier = Modifier.size(59.dp)

            )

            val context = LocalContext.current
            val uriHandler = LocalUriHandler.current

            Button(
                onClick = {
                    uriHandler.openUri(linkUrl)
                },
                shape = RoundedCornerShape(12.dp),
                contentPadding = PaddingValues(10.dp),
                colors = ButtonDefaults.buttonColors(

                ),
                modifier = Modifier
                    .clipToBounds()
                    .weight(1f),
            )
            {
                Text(
                    text = "Go To Course",
                    style = MaterialTheme.typography.titleSmall.copy(
                        fontWeight = FontWeight.Bold,
                        color = Color.Cyan
                    ),
                    modifier = Modifier
                        .padding(vertical = 6.dp, horizontal = 16.dp)
                )
            }

        }

    }
}

//@Preview(showBackground = true)
//@Composable
//fun DetailBarPreview() {
//    LearnfonifyTheme {
//        DetailBar(SavedCourseEntity())
//    }
//}
