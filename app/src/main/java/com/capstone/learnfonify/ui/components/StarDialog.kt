package com.capstone.learnfonify.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.capstone.learnfonify.R


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
                    starsColor = colorResource(id = R.color.learnfornify_blue),
                    onRatingChange = onRatingChange
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp),
                    horizontalArrangement = Arrangement.Center,
                ) {
                    Button(
                        onClick = {
                            onDismissRequest()
                        },
                        shape = RoundedCornerShape(12.dp),
                        contentPadding = PaddingValues(10.dp),
                        colors = ButtonDefaults.buttonColors(containerColor =colorResource(id = R.color.learnfornify_pink) ),
                        modifier = Modifier
                            .clipToBounds()
                            .padding(bottom = 8.dp),
                    )

                    {
                        Text(
                            text = "Batal",
                            style = MaterialTheme.typography.titleSmall.copy(
                                fontWeight = FontWeight.Bold,
                            ),
                            modifier = Modifier
                                .padding(horizontal = 4.dp)
                        )
                    }
                    Spacer(modifier = Modifier
                        .width(16.dp)
                        .height(2.dp))
                    Button(
                        onClick = {
                            onConfirmation()
                        },
                        shape = RoundedCornerShape(12.dp),
                        contentPadding = PaddingValues(10.dp),
                        colors = ButtonDefaults.buttonColors(containerColor =colorResource(id = R.color.learnfornify_blue) ),
                        modifier = Modifier
                            .clipToBounds()
                            .padding(bottom = 8.dp),
                    )
                    {
                        Text(
                            text = "Kirim",
                            style = MaterialTheme.typography.titleSmall.copy(
                                fontWeight = FontWeight.Bold,
                            ),
                            modifier = Modifier
                                .padding(horizontal = 4.dp)
                        )
                    }
                }
            }


        }
    }
}