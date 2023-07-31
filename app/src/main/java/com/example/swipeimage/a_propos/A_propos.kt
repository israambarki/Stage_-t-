package com.example.swipeimage.a_propos

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box

import androidx.compose.foundation.layout.Column

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.LocalTextStyle
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.BottomCenter
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.PointerIconDefaults.Text
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.swipeimage.R
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.yield

@SuppressLint("UnusedMaterialScaffoldPaddingParameter", "CoroutineCreationDuringComposition")
@ExperimentalPagerApi
@Composable
fun Apropos() {


    val pagestate = rememberPagerState(pageCount = imageview.size, initialOffscreenLimit = 3)


    var count by remember { mutableStateOf(0) }






    LaunchedEffect(Unit) {
        while (true) {
            yield()
            delay(2000)
            pagestate.animateScrollToPage(
                page = (pagestate.currentPage + 1) % (pagestate.pageCount),
                animationSpec = tween(600)
            )

        }

    }
    ///////
    //////

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xFFF0E4C3))

    )

    {
        //navbar za3ma za3ma:
        Row(
            modifier = Modifier
                .height(50.dp)
                .fillMaxWidth()
                .background(Color.White),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically

        ) {

            Image(
                painter = painterResource(R.drawable.servilink),
                contentDescription = "MyImage",
                modifier = Modifier
                    .height(25.dp),


                contentScale = ContentScale.Crop
            )
            Text("Servilinkkk", fontWeight = FontWeight.Bold)


        }


/////

        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = CenterHorizontally,

            ) {
//Spacer(modifier = Modifier.height(30.dp))
            //taw lil scroll image
            HorizontalPager(
                state = pagestate,
                modifier = Modifier

                    .padding(0.dp, 30.dp, 0.dp, 20.dp)
            ) {

                    page ->
                Card(
                    modifier = Modifier
                        .height(200.dp)
                        .width(200.dp)
                        .padding(10.dp)
                        .graphicsLayer(
                            clip = true,
                            shape = RoundedCornerShape(16.dp),

                            shadowElevation = 8.0F,
                            // translationX = 4.0F
                        )
                        .background(Color.White)
                        .wrapContentSize(align = Center)
                        .border(2.dp, Color.Gray, RoundedCornerShape(16.dp))
                ) {
                    val nat = imageview[page]
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(4.dp)
                            .background(Color.DarkGray)
                            .wrapContentWidth(align = CenterHorizontally)

                    ) {

                        Image(
                            painter = painterResource(
                                id = when (page) {
                                    0 -> R.drawable.image1
                                    1 -> R.drawable.image2
                                    2 -> R.drawable.image3
                                    3 -> R.drawable.image1

                                    //bich n7otha temchi lil activity ili mba3d taw nbadalha
                                    else -> R.drawable.download

                                }
                            ), contentDescription = "Imageee",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.fillMaxSize()
                        )
                    }
                }


            }



            HorizontalPagerIndicator(
                pagerState = pagestate, modifier = Modifier

                    .padding(5.dp)
            )


            //description
            Spacer(
                modifier = Modifier
                    .padding(5.dp)
            )
            /* androidx.compose.material3.Text(
            text = buildAnnotatedString {
                withStyle(style = SpanStyle(fontWeight = FontWeight.Bold, color = Color.DarkGray,) ) {
                    append("Depuis 2023\n")
                }
                withStyle(style = SpanStyle(fontWeight = FontWeight.Bold, color = Color.Red)) {
                    append("Nous offrons des services"+"\n" +
                            " de qualité")
                }
            },

            modifier = Modifier
                .padding(10.dp)
                .border(2.dp, Color.Black, RoundedCornerShape(16.dp))
                .padding(8.dp),


            style = MaterialTheme.typography.body2.copy(
                fontWeight = FontWeight.Bold,
                color = Color.DarkGray,
                shadow = Shadow(
                    color = Color.Black,
                    offset = Offset(1f, 1f),
                    blurRadius = 2f
                )
            )

        )*/



            Column(
                horizontalAlignment = CenterHorizontally,
                modifier = Modifier
                    .padding(10.dp)
                    .border(2.dp, Color.Black, RoundedCornerShape(16.dp))
                    .padding(8.dp)
            ) {
                androidx.compose.material3.Text(
                    text = "Depuis 2023",
                    style = MaterialTheme.typography.body2.copy(
                        fontWeight = FontWeight.Bold,
                        color = Color.DarkGray,
                        shadow = Shadow(
                            color = Color.Black,
                            offset = Offset(1f, 1f),
                            blurRadius = 2f
                        ),
                        textAlign = TextAlign.Center // Centrer le texte horizontalement
                    )
                )
                androidx.compose.material3.Text(
                    text = "Nous offrons des services" + "\n" + "de qualité",
                    style = MaterialTheme.typography.body2.copy(
                        fontWeight = FontWeight.Bold,
                        color = Color.Red,

                        shadow = Shadow(
                            color = Color.Black,
                            offset = Offset(1f, 1f),
                            blurRadius = 2f
                        ),
                        textAlign = TextAlign.Center // Centrer le texte horizontalement
                    )
                )

            }


                Box(
                    modifier = Modifier
                        .padding(8.dp) // Augmenter la marge du contenu à l'intérieur du Box
                        .height(150.dp)
                        .fillMaxWidth()
                        .background(
                            Color(0xFF3C4EB1),
                            shape = RoundedCornerShape(16.dp)
                        ) // Ajouter RoundedCornerShape ici
                        .border(2.dp, Color.Black, RoundedCornerShape(16.dp))


                        .shadow(4.dp, shape = RoundedCornerShape(16.dp)) // Ajout d'une ombre douce
                ) {

                    Text(
                        text = "ajudbbdvevdtvfcefucbaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaae",
                        Modifier.padding(4.dp).align(Center),
                        style = MaterialTheme.typography.body1.copy(
                            letterSpacing = 0.5.sp,
                            textAlign = TextAlign.Center
                        ),
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        fontSize = 12.sp, // Ajuster la taille du texte
                        lineHeight = 15.sp // Ajuster l'espacement des lignes

                    )
                }



                    Column() {


//statstique box:
                        var isVisible by remember { mutableStateOf(false) }
                        // Define the animation duration
                        val fadeInDuration = 5000

                        // Calculate the alpha value based on the visibility state
                        val alpha: Float by animateFloatAsState(
                            targetValue = if (isVisible) 1f else 0f,
                            animationSpec = tween(durationMillis = fadeInDuration)
                        )

                        LaunchedEffect(true) {
                            isVisible = true
                        }

                        AnimatedVisibility(
                            visible = isVisible,
                            enter = fadeIn(animationSpec = tween(durationMillis = fadeInDuration))
                        ) {
                            Text(
                                text = "ServiLink en chiffres",
                                Modifier
                                    .fillMaxWidth()
                                    .padding(bottom = 20.dp, top = 20.dp)
                                    .alpha(alpha),

                                style = TextStyle(
                                    fontSize = 20.sp,
                                    fontWeight = FontWeight.Bold,

                                    color = Color(0xFF000000),
                                    textAlign = TextAlign.Center,
                                    //alpha = alpha // Apply the animated alpha to fade in the text

                                )
                            )
                        }



                     Box(modifier = Modifier.fillMaxWidth().padding(start = 70.dp, top = 40.dp, end = 50.dp, bottom = 0.dp)){
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .fillMaxHeight()

                            ) {
                                Stat(
                                    icon = R.drawable.perone,
                                    chiffre = "+100",
                                    description = "Prestataires professionnels"
                                )
                                Stat(
                                    icon = R.drawable.pertwo,
                                    chiffre = "+80",
                                    description = "Services différents"
                                )
                                Stat(
                                    icon = R.drawable.perthree,
                                    chiffre = "+80",
                                    description = "Consultants a votre service"
                                )
                            }
                        }

                }
                }
            }

        }






//faire l'appel a chaque foi pour chaque stat, chiffre:
//5ater n7eb 3ala 3 cards w3andhom fard contenu donc 5tart na3mel fonction wn3aytelha


@Composable
fun Stat(icon: Int, chiffre: String, description: String) {
    Card(
        modifier = Modifier
            .width(94.dp)
            .height(75.dp)
            .clip(RoundedCornerShape(16.dp)),
           // .background(color = Color(0xFFF28500)),
       // shape = RoundedCornerShape(16.dp), // Set the corner radius here
        backgroundColor = Color(0xFFF28500),
                border = BorderStroke(2.dp, Color.Black) // Set the border properties here


    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = CenterHorizontally
        ) {
            Icon(
                painter = painterResource(id = icon),
                contentDescription = "",
                tint = Color.White
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = chiffre,
                modifier = Modifier
                    .width(24.dp)
                    .height(15.dp),

                style = TextStyle(
                fontSize = 12.sp,

                fontWeight = FontWeight(500),
                color = Color(0xFF000000),
                    textAlign = TextAlign.Center
            ))
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = description,
                modifier = Modifier
                    .width(63.dp)
                    .height(19.dp),
                style = TextStyle(
                fontSize = 11.sp,

                fontWeight = FontWeight(500),
                color = Color(0xFF273088),
                    textAlign = TextAlign.Center
            ))
        }
    }
}
