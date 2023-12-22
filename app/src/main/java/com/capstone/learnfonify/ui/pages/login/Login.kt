package com.capstone.learnfonify.ui.pages.login


import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.capstone.learnfonify.R
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.capstone.learnfonify.data.ViewModelFactory
import com.capstone.learnfonify.data.signin.SignInState
import com.capstone.learnfonify.di.Injection
import com.capstone.learnfonify.ui.pages.coursedetail.CourseDetailContent
import com.capstone.learnfonify.ui.pages.home.HomeViewModel
import com.capstone.learnfonify.ui.theme.LearnfonifyTheme
import com.kyy47.kyyairlines.common.UiState

@Composable
fun LoginPage(
    onRegisterClick: () -> Unit,
    onSignInClick: () -> Unit,
    shareElement: @Composable () -> Unit,
    onLoginWithEmailClick: (String, String) -> Unit
) {
    LoginContent(
        onSignInClick, shareElement = shareElement,
        onRegisterClick = onRegisterClick,
        onLoginWithEmailClick = onLoginWithEmailClick
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginContent(
    onSignInClick: () -> Unit,
    shareElement: @Composable () -> Unit,
    onRegisterClick: () -> Unit,
    onLoginWithEmailClick: (String, String) -> Unit

) {


    var emailValue by remember {
        mutableStateOf("")
    }
    var passwordValue by remember {
        mutableStateOf("")
    }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        shareElement()
        Row (
            verticalAlignment = Alignment.CenterVertically
        ){
            Text(
                text = "Learn",
                style = MaterialTheme.typography.headlineSmall.copy(
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    color = Color.Black
                ),
            )
            Text(
                text = "Fonify",
                style = MaterialTheme.typography.headlineSmall.copy(
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    color = colorResource(id = R.color.learnfornify_blue)
                ),
            )
        }
       
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
        ) {
            Text(
                text = "Email",
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.Bold,
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 15.dp)
            )
            OutlinedTextField(
                value = emailValue,
                onValueChange = { emailValue = it },
                label = {
                    Text(
                        text = "Enter Your Email",
                        style = MaterialTheme.typography.titleSmall.copy(
                            fontWeight = FontWeight.Normal,
                        ),
                    )
                },
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                shape = RoundedCornerShape(20.dp),
                modifier = Modifier
                    .fillMaxWidth(),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    unfocusedBorderColor = colorResource(id = R.color.blue_light),
                    focusedBorderColor = colorResource(id = R.color.learnfornify_blue)
                ),


                )
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
        ) {
            Text(
                text = "Password",
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.Bold,
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 15.dp)
            )
            OutlinedTextField(
                value = passwordValue,
                onValueChange = { passwordValue = it },
                label = {
                    Text(
                        text = "Enter Your Password",
                        style = MaterialTheme.typography.titleSmall.copy(
                            fontWeight = FontWeight.Normal,

                            ),
                    )
                },
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                shape = RoundedCornerShape(20.dp),
                modifier = Modifier
                    .fillMaxWidth(),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    unfocusedBorderColor = colorResource(id = R.color.blue_light),
                    focusedBorderColor = colorResource(id = R.color.learnfornify_blue)
                ),
                visualTransformation = if (passwordValue.trim()
                        .equals("")
                ) VisualTransformation.None else PasswordVisualTransformation()


            )
        }
        Button(
            onClick = {
                onLoginWithEmailClick(emailValue, passwordValue)
            },
            shape = RoundedCornerShape(12.dp),
            contentPadding = PaddingValues(10.dp),
            colors = ButtonDefaults.buttonColors(containerColor =colorResource(id = R.color.learnfornify_blue) ),
            modifier = Modifier
                .padding(vertical = 20.dp, horizontal = 20.dp)
                .fillMaxWidth()
                .clipToBounds(),
        )
        {
            Text(
                text = "Login",
                style = MaterialTheme.typography.titleSmall.copy(
                    fontWeight = FontWeight.Bold,
                ),
            )
        }
        Text(
            text = "or",
            style = MaterialTheme.typography.labelLarge.copy(
                fontWeight = FontWeight.Normal,
            ),
        )
        Text(
            text = "register",
            style = MaterialTheme.typography.titleLarge.copy(
                fontWeight = FontWeight.Bold,
            ),
            modifier = Modifier
                .clickable { onRegisterClick() }
        )




        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .size(10.dp)
        )


    }

}

//@Preview(showBackground = true, device = Devices.PIXEL_4)
//@Composable
//fun LoginPagePrev() {
//    LearnfonifyTheme {
//        LoginContent(
//            onSignInClick = { /*TODO*/ },
//            onRegisterClick = {},
//            shareElement = {},
//            onLoginWithEmailClick = {
//
//            }
//                )
//    }
//}