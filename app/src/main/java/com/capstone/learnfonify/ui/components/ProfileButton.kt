package com.capstone.learnfonify.ui.components

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.capstone.learnfonify.R


@Composable
fun ProfileButton(url: String) {

    AsyncImage(model = url,
        contentDescription = "Profile",
        error = painterResource(R.drawable.google),
        modifier = Modifier
            .size(38.dp)
            .clip(CircleShape)
    )

}