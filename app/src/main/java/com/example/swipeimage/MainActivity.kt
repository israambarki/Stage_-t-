package com.example.swipeimage

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.swipeimage.accueil.Nav
import com.example.swipeimage.accueil.accueil
import com.example.swipeimage.ui.theme.SwipeimageTheme
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.calculateCurrentOffsetForPage
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.delay
import kotlinx.coroutines.yield
import kotlin.math.absoluteValue

class MainActivity : ComponentActivity() {




   @ExperimentalPagerApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        setContent {



            /*          val pagecount = rememberPagerState(pageCount = imagepass.size)

                  //start implementation
                    SwipeimageTheme {
        // put that in a box so we can adjust the alignment, we can align control on the bottom
                        Box(modifier = Modifier.fillMaxSize()){

        HorizontalPager(state = pagecount,

        ) {

        } */

            SwipeimageTheme {
                //Sliding()
             //   MainScreen()
                Nav()
            }



                }


            }



        }

@ExperimentalPagerApi
@Composable
fun Sliding(navController: NavHostController){
val pagestate = rememberPagerState(pageCount = imagepass.size, initialOffscreenLimit = 4 )
    
    LaunchedEffect(Unit){
        while(true){
            yield()
            delay(5000)
            pagestate.animateScrollToPage(page = (pagestate.currentPage + 1) % (pagestate.pageCount), animationSpec = tween(600) )

        }

    }
Column(modifier = Modifier

    .fillMaxSize()
    .background(color = Color(0xFFF0E4C3))

) {
    //navbar za3ma za3ma:
    Row( modifier = Modifier
        .height(50.dp)
        .fillMaxWidth()
        .background(Color.White),
        horizontalArrangement= Arrangement.Center,
    verticalAlignment= Alignment.CenterVertically

    ){

        Image(
            painter = painterResource(R.drawable.servilink),
            contentDescription = "MyImage",
            modifier = Modifier
                .height(25.dp),


            contentScale = ContentScale.Crop
        )
        Text("Servilinkkk", fontWeight = FontWeight.Bold)


    }



//Spacer(modifier = Modifier.height(30.dp))
    //taw lil scroll image
    HorizontalPager(state = pagestate,
    modifier = Modifier
        .weight(1f)
        .padding(0.dp, 40.dp, 0.dp, 40.dp)) {

        page ->
        Card(modifier = Modifier.graphicsLayer {
        }) {
            val nat = imagepass[page]
            Box(modifier = Modifier
                .fillMaxSize()
                .background(Color.DarkGray)
                .align(CenterHorizontally)){
                
                Image(painter = painterResource(id = when(page){
                    0-> R.drawable.oumaymalimeme
                    1-> R.drawable.deux
                    2-> R.drawable.troi
                    3-> R.drawable.oumaymalimeme

                    //bich n7otha temchi lil activity ili mba3d taw nbadalha
                    else -> R.drawable.download

                }), contentDescription = "Imageee",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
                //description
                Spacer(modifier = Modifier
                    .padding(5.dp))

Box(modifier = Modifier
    .background(Color.Black)
    .fillMaxWidth()
    .align(Alignment.BottomCenter)) {
    Text(
        text = nat.descriptionn,
        Modifier.align(Alignment.Center),
        style = MaterialTheme.typography.bodyLarge,
        color = Color.White,
        fontWeight = FontWeight.Bold
    )
}


                
            }



        }


        }






    Button(
        onClick = {
        navController.navigate("Accueil")

    },

        modifier = Modifier
            .padding(top = 3.dp, bottom = 30.dp)
            .width(120.dp)
            .height(70.dp)
            .align(Alignment.CenterHorizontally)
            .padding(top = 3.dp, bottom = 15.dp)
        //  .align(Alignment.CenterHorizontally)
        ,

        shape = RoundedCornerShape(50.dp),
        border = BorderStroke(width = 3.dp, color = Color.Black),
        colors = ButtonDefaults.buttonColors(Color(0xFF00416A))

        ) {

        Text(text = "Accueil", color = Color.White)
    }




    HorizontalPagerIndicator(pagerState = pagestate, modifier = Modifier
        .align(Alignment.CenterHorizontally)
        .padding(16.dp))

}

}


/*@OptIn(ExperimentalPagerApi::class)
@Composable
fun MainScreen() {
    val navController = rememberNavController()



    NavHost(navController, startDestination = "sliding") {
        composable("sliding") { Sliding(navController) }
    //Zedtha navcontroller ki 3malt fil accueil navcontroller 3al description
    composable("accueilservice") { accueil(navController) }

    }
}*/



@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    SwipeimageTheme {

    }
}