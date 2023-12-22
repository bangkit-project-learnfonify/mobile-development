package com.capstone.learnfonify.ui.pages.profile

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.BookOnline
import androidx.compose.material.icons.rounded.Email
import androidx.compose.material.icons.rounded.Logout
import androidx.compose.material.icons.rounded.Person4
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.capstone.learnfonify.R
import com.capstone.learnfonify.data.ViewModelFactory
import com.capstone.learnfonify.data.response.UserFromIdItem
import com.capstone.learnfonify.ui.navigation.Screen
import com.capstone.learnfonify.ui.pages.coursedetail.CourseDetailViewModel
import com.capstone.learnfonify.ui.theme.LearnfonifyTheme
import com.kyy47.kyyairlines.common.UiState


@Composable
fun ProfilePage(
    context: Context,
    navController: NavHostController,
    id: Int,
    profileViewModel: ProfileViewModel = viewModel(
        factory = ViewModelFactory.getInstance(context)
    )
    ) {

    profileViewModel.userState.collectAsState(
        initial = UiState.Loading
    ).value.let { uiState ->
        when (uiState) {
            is UiState.Loading -> {
                profileViewModel.getUserFromId(id)
            }

            is UiState.Success -> {
                ProfileContent(
                   onSignOut= {

                    profileViewModel.deleteToken()
                       navController.navigate(Screen.SplashLogin.route)

                    },
                    userItem = uiState.data
                    )
            }
            is UiState.Error -> {
            }
        }


}
}

@Composable
fun ProfileContent(
    onSignOut: () -> Unit,
    userItem: UserFromIdItem
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            Icon(imageVector = Icons.Rounded.Logout,
                contentDescription = "logout",
                modifier = Modifier
                    .size(44.dp)
                    .clickable {
                        onSignOut()
                    }
            )
        }
        Image(
            painter = painterResource(id = R.drawable.profile_dummy),
            contentDescription = "profile",
            modifier = Modifier
                .size(310.dp)
                .padding(top = 24.dp)
        )
        Text(
            text = userItem.fullname.toString(),
            style = MaterialTheme.typography.headlineSmall.copy(
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            ),
        )
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .padding(bottom = 24.dp)
        ) {
            Icon(
                imageVector = Icons.Rounded.Email,
                contentDescription = "email",
                modifier = Modifier
                    .size(28.dp)
                    .padding(end = 6.dp)
            )
            Text(
                text = userItem.email.toString(),
                style = MaterialTheme.typography.titleSmall.copy(
                    fontWeight = FontWeight.Normal,
                    textAlign = TextAlign.Center,
                    color = Color.Black
                ),
            )
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            Text(
                text = "Informasi",
                style = MaterialTheme.typography.headlineSmall.copy(
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Start
                ),
                modifier = Modifier
                    .padding(bottom = 16.dp)
            )

            if(userItem.age != null){
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Rounded.Person4,
                        contentDescription = "umur",
                        modifier = Modifier
                            .size(34.dp)
                            .padding(end = 8.dp)
                    )
                    Text(
                        text = userItem.age.toString() + " tahun",
                        style = MaterialTheme.typography.titleSmall.copy(
                            fontWeight = FontWeight.Normal,
                            textAlign = TextAlign.Start
                        ),
                        modifier = Modifier
                            .weight(1f)
                            .clip(RoundedCornerShape(16.dp))
                            .background(Color.Transparent) // Set background color
                            .border(
                                2.dp,
                                Color.Black
                            ) // Set border with thickness and color
                            .padding(horizontal = 8.dp, vertical = 8.dp), //
                    )
                }
            }

            if(userItem.majoring != null){
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Rounded.BookOnline,
                        contentDescription = "logout",
                        modifier = Modifier
                            .size(34.dp)
                            .padding(end = 8.dp)
                    )
                    Text(
                        text = userItem.majoring.toString(),
                        style = MaterialTheme.typography.titleSmall.copy(
                            fontWeight = FontWeight.Normal,
                            textAlign = TextAlign.Start
                        ),
                        modifier = Modifier
                            .weight(1f)
                            .clip(RoundedCornerShape(16.dp))
                            .background(Color.Transparent) // Set background color
                            .border(
                                2.dp,
                                Color.Black
                            ) // Set border with thickness and color
                            .padding(horizontal = 8.dp, vertical = 8.dp), //
                    )
                }
            }

        }

    }
//

}

@Preview(showBackground = true, device = Devices.PIXEL_4)
@Composable
fun ProfilePagePreview() {
    LearnfonifyTheme {
//        ProfileContent(onSignOut = {} ,id =  22, context = LocalContext.current)
    }
}