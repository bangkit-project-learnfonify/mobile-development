package com.capstone.learnfonify.ui.pages.community

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.horizontalScroll
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Chat
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.rounded.ChatBubble
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.capstone.learnfonify.R
import com.capstone.learnfonify.constants.DummyListMenu
import com.capstone.learnfonify.ui.components.CardCommunity
import com.capstone.learnfonify.ui.components.ItemChatCommunity
import com.capstone.learnfonify.ui.theme.LearnfonifyTheme

@Composable
fun CommunityPage() {
    CommunityContent()
}

@Composable
fun CommunityContent() {
    Column {
        Row(
            Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            horizontalArrangement = Arrangement.Center
        )
        {

            Text(
                text = "Learn",
                style = MaterialTheme.typography.headlineSmall.copy(
                    fontWeight = FontWeight.Bold,
                ),
            )
            Text(
                text = "Fonify",
                style = MaterialTheme.typography.headlineSmall.copy(
                    fontWeight = FontWeight.Bold,
                    color = colorResource(id = R.color.learnfornify_blue)
                ),
            )
        }

        Row(
            Modifier
                .fillMaxWidth()
                .padding(top = 16.dp, end = 16.dp, start = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Icon(
                    imageVector = Icons.Rounded.ChatBubble ,
                    contentDescription = "community",
                    modifier = Modifier
                        .size(14.dp)
                    )
                 Text(
                    text = "Komunitas",
                    style = MaterialTheme.typography.headlineSmall.copy(
                        fontWeight = FontWeight.Bold,
                    ),
                )
            }
            Icon(
                imageVector = Icons.Default.Search ,
                contentDescription = "search",
                modifier = Modifier
                    .size(34.dp)
            )

        }
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
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 34.dp, start = 16.dp, end = 16.dp)
                .horizontalScroll(rememberScrollState()),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            CardCommunity()
            CardCommunity()
            CardCommunity()
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .shadow(
                    elevation = 4.dp,
                    spotColor = Color(0x40000000),
                    ambientColor = Color(0x40000000)
                )
                .background(color = Color(0xFFF1F3F3), shape = RoundedCornerShape(size = 15.dp))
                .padding(top = 60.dp)
        ){
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .padding(24.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                ItemChatCommunity()
                ItemChatCommunity()
                ItemChatCommunity()
                ItemChatCommunity()
            }
        }

    }
}

@Preview(showBackground = true, device = Devices.PIXEL_4)
@Composable
fun CommunityContentPrev() {
    LearnfonifyTheme {
        CommunityContent()
    }
}

