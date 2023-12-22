package com.capstone.learnfonify.ui.pages.saved

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.SaveAlt
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.capstone.learnfonify.ui.components.MyCardSaved


@Composable
fun StoredPage() {
    StoredContent()
}

@Composable
fun StoredContent() {
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
                imageVector = Icons.Rounded.SaveAlt,
                contentDescription = "Saved" ,
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

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 34.dp)
            ,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
        MyCardSaved()
            MyCardSaved()
        }
    }
}

@Preview(showBackground = true, device = Devices.PIXEL_4)
@Composable
fun StoredPagePrev() {
    StoredPage()
}