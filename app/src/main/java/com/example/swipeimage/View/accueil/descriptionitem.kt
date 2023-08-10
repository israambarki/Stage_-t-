package com.example.swipeimage.View.accueil

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun description(image :Int,description :String?){


  //  val imageId = remember { navController.previousBackStackEntry?.arguments?.getInt("image") }

    Column( modifier = Modifier

        .fillMaxSize()
        .padding(5.dp)
        .background(Color(0xFFF0E4C3)),

        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally


    ) {
        Card(modifier = Modifier
            .fillMaxWidth()
            .weight(1.0f)) {



            Image(
                painter = painterResource(id = image),
                contentDescription = "Image",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )


        }
        Spacer(modifier = Modifier.size(16.dp))

        Text(text = "Description",

            style = MaterialTheme.typography.h5.copy(
                fontWeight = FontWeight.Bold,
                color = Color.DarkGray,
                shadow = Shadow(
                    color = Color.Black,
                    offset = Offset(1f, 1f),
                    blurRadius = 2f
                )
            ),
            modifier = Modifier
                .padding(10.dp)
                .border(2.dp, Color.Black, RoundedCornerShape(2.dp))
                .padding(8.dp)
        )



        Spacer(modifier = Modifier.size(16.dp))


        Box( modifier = Modifier
            .weight(2.0f)
            .padding(16.dp)
            .background(Color.Gray),
            contentAlignment = Alignment.Center) {


            Text(text ="Ce service:"+"\n"+" $description",

                textAlign = TextAlign.Center, // Center the text horizontally
                modifier = Modifier.fillMaxSize().align(Alignment.Center)
    )
            

        }




    }


}



@Preview(showBackground = true)
@Composable
fun afficherdesc(){

   // description()


}
