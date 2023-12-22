package com.capstone.learnfonify.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.capstone.learnfonify.R
import com.capstone.learnfonify.ui.theme.LearnfonifyTheme


@Composable
fun CardCommunity() {
    Box(
        modifier = Modifier
            .shadow(
                elevation = 8.dp,
                spotColor = Color(0x40000000),
                ambientColor = Color(0x40000000)
            )
            .width(178.dp)
            .height(125.dp)
            .background(color = Color(0xFFFFFFFF), shape = RoundedCornerShape(size = 12.dp))
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.TopEnd
            ){
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.card_dummy),
                        contentDescription = "image description",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .width(44.dp)
                            .height(39.dp)
                    )
                    Text(
                        text = "SQL Dev",
                        style = TextStyle(
                            fontSize = 15.sp,
                            fontWeight = FontWeight(700),
                            color = Color(0xFF000000),
                        )
                    )

                }
                Box(modifier = Modifier
                    .width(19.dp)
                    .height(18.dp)
                    .background(color = Color(0xFFFF00B8), shape = RoundedCornerShape(size = 9.dp)),
                    contentAlignment = Alignment.Center
                ){
                    Text(
                        text = "9+",
                        style = TextStyle(
                            fontSize = 8.sp,
                            fontWeight = FontWeight(700),
                            color = Color(0xFFFFFFFF),
                        )
                    )
                }
            }
            Row(
                modifier = Modifier
                    .padding(top = 16.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.profile_dummy),
                    contentDescription = "image description",
                    contentScale = ContentScale.FillBounds,
                    modifier = Modifier
                        .width(33.dp)
                        .height(33.dp)
                )
                Image(
                    painter = painterResource(id = R.drawable.profile_dummy),
                    contentDescription = "image description",
                    contentScale = ContentScale.FillBounds,
                    modifier = Modifier
                        .width(33.dp)
                        .height(33.dp)
                        .graphicsLayer {
                            this.translationX = -8.dp.toPx()
                        }
                )
                Image(
                    painter = painterResource(id = R.drawable.profile_dummy),
                    contentDescription = "image description",
                    contentScale = ContentScale.FillBounds,
                    modifier = Modifier
                        .width(33.dp)
                        .height(33.dp)
                        .graphicsLayer {
                            this.translationX = -16.dp.toPx()
                        }
                )

            }


        }
    }
}

@Preview
@Composable
fun CardCommunityPrev() {
    LearnfonifyTheme {
        CardCommunity()
    }
}