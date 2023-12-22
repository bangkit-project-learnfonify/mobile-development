package com.capstone.learnfonify.ui.pages.splashscreen

import androidx.compose.animation.core.SpringSpec
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.capstone.learnfonify.R
import com.capstone.learnfonify.data.signin.SignInState
import com.capstone.learnfonify.ui.pages.login.LoginPage
import com.capstone.learnfonify.ui.theme.LearnfonifyTheme
import com.skydoves.orbital.Orbital
import com.skydoves.orbital.animateSharedElementTransition
import com.skydoves.orbital.rememberContentWithOrbitalScope

@Composable
fun LearnFornifySplashScreen(
    state: SignInState,
    onSignInClick: () -> Unit
) {
    var isTransFormed by rememberSaveable {
        mutableStateOf(false)
    }
    val shareImage = rememberContentWithOrbitalScope {
        AsyncImage(
            modifier = Modifier
                .animateSharedElementTransition(
                    orbitalScope = this,
                    movementSpec = SpringSpec(stiffness = 40f),
                    transformSpec = SpringSpec(stiffness = 40f)
                )
                .then(if (isTransFormed) {
                    Modifier
                        .size(200.dp)
                } else {
                    Modifier
                        .size(475.dp)
                        .padding(top = 24.dp)
                        .graphicsLayer {
                            this.translationX = -100.dp.toPx()
                        }
                }),
            model = ImageRequest.Builder(LocalContext.current)
                .data(R.drawable.login)
                .crossfade(true)
                .build(),
            contentDescription = "Learnfornify",
        )
    }

    Orbital(
    ) {
        if (isTransFormed) {
            LoginPage(state = state, onSignInClick = onSignInClick, shareElement = { shareImage() }
            )
        } else {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 42.dp)

            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column {
                        Row {
                            Text(
                                text = "Learn",
                                style = MaterialTheme.typography.displayLarge.copy(
                                    fontWeight = FontWeight.Bold,
                                ),
                            )
                            Text(
                                text = "Fonify",
                                style = MaterialTheme.typography.displayLarge.copy(
                                    fontWeight = FontWeight.Bold,
                                    color = Color.Cyan
                                ),
                            )
                        }
                        Text(
                            text = "we provide, you decide",
                            style = MaterialTheme.typography.headlineSmall.copy(
                                fontWeight = FontWeight.Normal,
                            ),

                            )

                    }


                }

                shareImage()

                Column(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Unleash your potential, now!",
                        style = MaterialTheme.typography.titleLarge.copy(
                            fontWeight = FontWeight.Light,
                            textAlign = TextAlign.Center
                        ),
                        modifier = Modifier
                            .fillMaxWidth(),
                    )
                    Button(
                        onClick = {
                            isTransFormed = !isTransFormed
                        },
                        shape = RoundedCornerShape(12.dp),
                        contentPadding = PaddingValues(10.dp),
                        colors = ButtonDefaults.buttonColors(

                        ),
                        modifier = Modifier
                            .clipToBounds()
                            .padding(bottom = 24.dp),
                    )
                    {
                        Text(
                            text = "Join Us",
                            style = MaterialTheme.typography.titleSmall.copy(
                                fontWeight = FontWeight.Bold,
                            ),
                            modifier = Modifier
                                .padding(vertical = 6.dp, horizontal = 16.dp)
                        )
                    }
                }


            }
        }
    }


}
