package com.capstone.learnfonify.ui.pages.register


import android.R.style
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.capstone.learnfonify.R
import com.capstone.learnfonify.ui.theme.LearnfonifyTheme

@Composable
fun RegisterPage(
    onRegisterWithEmailClick: (String, String, String) -> Unit,
) {
    RegisterContent(onRegisterWithEmailClick = onRegisterWithEmailClick)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterContent(
    onRegisterWithEmailClick: (String, String, String) -> Unit,
) {
    var emailValue by remember {
        mutableStateOf("")
    }
    var passwordValue by remember {
        mutableStateOf("")
    }
    var usernameValue by remember {
        mutableStateOf("")
    }


    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(R.drawable.register),
            contentDescription = null,
            modifier = Modifier.size(200.dp),
            contentScale = ContentScale.Crop
        )
        Text(
            text = "LearnFonify", style = MaterialTheme.typography.headlineSmall.copy(
                fontWeight = FontWeight.Bold, textAlign = TextAlign.Center
            ), modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp)
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
        ) {
            Text(
                text = "Username", style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.Bold,
                ), modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 15.dp)
            )
            OutlinedTextField(
                value = usernameValue,
                onValueChange = { usernameValue = it },
                label = {
                    Text(
                        text = "Enter Your Username",
                        style = MaterialTheme.typography.titleSmall.copy(
                            fontWeight = FontWeight.Normal,
                        ),
                    )
                },
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                shape = RoundedCornerShape(20.dp),
                modifier = Modifier.fillMaxWidth(),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    unfocusedBorderColor = MaterialTheme.colorScheme.primary,
                ),


                )
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
        ) {
            Text(
                text = "Email", style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.Bold,
                ), modifier = Modifier
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
                modifier = Modifier.fillMaxWidth(),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    unfocusedBorderColor = MaterialTheme.colorScheme.primary,
                ),


                )
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
        ) {
            Text(
                text = "Password", style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.Bold,
                ), modifier = Modifier
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
                modifier = Modifier.fillMaxWidth(),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    unfocusedBorderColor = MaterialTheme.colorScheme.primary,
                ),
                visualTransformation = if (passwordValue.trim()
                        .equals("")
                ) VisualTransformation.None else PasswordVisualTransformation()


            )
        }
        Button(
            onClick = {
                onRegisterWithEmailClick(usernameValue, emailValue, passwordValue)
            },
            shape = RoundedCornerShape(12.dp),
            contentPadding = PaddingValues(10.dp),
            colors = ButtonDefaults.buttonColors(

            ),
            modifier = Modifier
                .padding(vertical = 20.dp, horizontal = 20.dp)
                .fillMaxWidth()
                .clipToBounds(),
        ) {
            Text(
                text = "Register",
                style = MaterialTheme.typography.titleSmall.copy(
                    fontWeight = FontWeight.Bold,
                ),
            )
        }

        Text(
            text = "or Continue With",
            style = MaterialTheme.typography.labelMedium.copy(
                fontWeight = FontWeight.Normal,
            ),
        )

        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .size(10.dp)
        )



    }

}

@Preview(showBackground = true)
@Composable
fun RegisterPreview() {
    LearnfonifyTheme {
//        RegisterContent()
    }

}