package com.capstone.learnfonify.ui.components

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.capstone.learnfonify.R


@Composable
fun DetailBar(
    onInsertSavedCourse: () -> Unit,
    isSaved: Boolean
              ) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(24.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = if(isSaved)   painterResource(R.drawable.saved) else  painterResource(R.drawable.not_saved),
            contentDescription = "save",
            modifier = Modifier.size(59.dp)
                .clickable {
                    onInsertSavedCourse()
                }
        )

        Image(
            painter = painterResource(id = R.drawable.share),
            contentDescription = "share",
            modifier = Modifier.size(59.dp)

        )

        Button(
            onClick = {

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

//@Preview(showBackground = true)
//@Composable
//fun DetailBarPreview() {
//    LearnfonifyTheme {
//        DetailBar(SavedCourseEntity())
//    }
//}
