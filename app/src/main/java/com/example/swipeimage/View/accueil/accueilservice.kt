package com.example.swipeimage.View.accueil

import android.annotation.SuppressLint
import com.example.swipeimage.R




import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Button
import androidx.compose.material.ButtonColors
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.TextField
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.Clear
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDefaults.contentColor
import androidx.compose.material3.Text
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.swipeimage.ViewModel.ViewModel.RegistrationViewModel

import com.example.swipeimage.ui.theme.SwipeimageTheme
import kotlinx.coroutines.launch


/*class AccueilActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SwipeimageTheme {

                Surface(modifier = Modifier.background(color = Color(0xFFF0E4C3))

                ) {




                      //  accueil()
                    Nav()


                }
            }
        }
    }
}

*/




@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun accueil(navController: NavHostController,viewModel: RegistrationViewModel) {

    val coroutineScope = rememberCoroutineScope()

    var bottomState by remember {
        mutableStateOf("Accueil") }

androidx.compose.material.Scaffold(
    bottomBar = {
      //  BottomAppBar(modifier = Modifier.background(Color(0x00416A))) {
      //      Text(text = "hhhh")

    BottomNavigation(
        modifier = Modifier
            .clip(RoundedCornerShape(16.dp)),
        backgroundColor = Color(0xFF7E8DBD), // Définir la couleur de fond en bleu,
        contentColor= Color(0xFFCED2DD),
      // modifier = Modifier.padding(16.dp).border(width = 2.dp, color = Color.Black, shape = RoundedCornerShape(20.dp)).background(Color(0xFF7E8DBD)), // Ajoutez des marges si nécessaire

      //  elevation = 8.dp, // Ajoutez une élévation pour un effet de profondeur


    ) {

//acceuil
        BottomNavigationItem(
            selected = bottomState == "A Propos",

            onClick = { bottomState = "A Propos";  navController.navigate("A_Propos") },
        label = { Text(text = "A Propos", color = Color.White) },
            icon = {
                Icon(imageVector =Icons.Default.Home , contentDescription ="" )

            }


            )

        //profil

        BottomNavigationItem(
            selected = bottomState == "Contacter",
            onClick = { bottomState = "Contacter" },
            label = { Text(text = "Contacter", color = Color.White)  },
            icon = {
                Icon(imageVector =Icons.Default.Call , contentDescription ="" )

            }


        )
//inscription

        BottomNavigationItem(
            selected = bottomState == "Connecter",
            onClick = { bottomState = "Connecter" ; /* navController.navigate("Inscription") */////verifier la connexion:
                coroutineScope.launch {
                    /*  val V = viewModel.Verifier(
                          emailVal,
                          passwordVal
                      ) // Remplacez par les véritables informations d'identification de l'utilisateur*/

                    val LastConnection = viewModel.GetLastUserConnection()

                    if (LastConnection?.isConnected == true) {

                        navController.navigate("PrestataireService")
                        // Utilisateur déjà connecté, rediriger vers l'écran principal ou une autre interface
                    }else {
                        navController.navigate("Inscription")
                    }
                } },
            label = { Text(text = "Connecter", color = Color.White)  },
            icon = {
                Icon(imageVector =Icons.Default.AccountCircle , contentDescription ="" )

            }


        )


    }
        }
    ,

    content = {




    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(5.dp)
            .background(Color(0xFFF0E4C3)),

        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {


        Box(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                //  .width(367.dp)
                .fillMaxWidth()
                .height(200.dp)
                .background(
                    color = Color(0xFFF0E4C3),


                    )
        ) {
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




            /*Row(modifier = Modifier.align(Alignment.TopCenter)) {
                Image(
                    painter = painterResource(id = R.drawable.asset), contentDescription = "logo",
                    modifier = Modifier
                        .padding(10.dp)
                        .width(29.dp)
                        .height(42.dp)
                )




                Text(
                    text = "ServiLink",
                    modifier = Modifier
                        .padding(15.dp)
                        .width(151.dp)
                        .height(
                            29.dp
                        ),
                    style = TextStyle(fontSize = 24.sp),
                    fontWeight = FontWeight(700),
                    color = Color(0xFF403EAC)
                )

            } */


//na3mlou serachbar

            Row() {
                Button(
                    onClick = {
                       // navController.navigate("Inscription")

                        // aller a l'espace client
                        coroutineScope.launch {
                            /*  val V = viewModel.Verifier(
                                  emailVal,
                                  passwordVal
                              ) // Remplacez par les véritables informations d'identification de l'utilisateur*/

                            val LastConnection = viewModel.GetLastUserConnection()

                            if (LastConnection?.isConnected == true) {

                                navController.navigate("PrestataireService")
                                // Utilisateur déjà connecté, rediriger vers l'écran principal ou une autre interface
                            }else {
                                navController.navigate("Inscription")
                            }
                        }

                    }, modifier = Modifier
                        .padding(20.dp, 70.dp, 27.dp, 0.dp)
                        .width(159.dp)
                        .height(80.dp)
                      //  .background(color = Color(0xFF7E8DBD))
                            ,
                    shape = RoundedCornerShape(50.dp),
                    border = BorderStroke(width = 1.dp, color = Color(0xFF000000)),
                    colors = ButtonDefaults.buttonColors(Color(0xFF7E8DBD))
                ) {

                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {


                        Image(
                            painter = painterResource(id = R.drawable.personne),
                            contentDescription = "image description",
                            contentScale = ContentScale.FillBounds,
                            modifier = Modifier
                                .width(44.dp)
                                .height(43.dp)
                        )

                        Text(
                            text = "Espace Client ", modifier = Modifier
                                .width(159.dp)
                                .height(30.dp)
                                .align(Alignment.CenterHorizontally),
                                //.padding(start = 18.dp),



                            style = TextStyle(
                                fontSize = 9.sp,
                                lineHeight = 18.sp,
                                fontWeight = FontWeight(600),
                                color = Color(0xFFFFFFFF),
                                textAlign = TextAlign.Center // Center the text horizontally within the column
                            )
                        )


                    }

                }
///

                Button(
                    onClick = {

                   //     navController.navigate("Inscription")

                        //aller a l'espace prestataire
                        coroutineScope.launch {
                            /*  val V = viewModel.Verifier(
                                  emailVal,
                                  passwordVal
                              ) // Remplacez par les véritables informations d'identification de l'utilisateur*/

                            val LastConnection = viewModel.GetLastUserConnection()

                            if (LastConnection?.isConnected == true) {

                                navController.navigate("PrestataireService")
                                // Utilisateur déjà connecté, rediriger vers l'écran principal ou une autre interface
                            }else {
                                navController.navigate("Inscription")
                            }
                        }

                    }, modifier = Modifier


                        .padding(27.dp, 70.dp, 0.dp, 0.dp)
                        .width(159.dp)
                        .height(80.dp)
                        //.background(color = Color(0xFF7E8DBD))
                            ,
                    shape = RoundedCornerShape(50.dp),
                    border = BorderStroke(width = 1.dp, color = Color(0xFF000000)),
                    colors = ButtonDefaults.buttonColors(Color(0xFF7E8DBD))
                ) {

                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {

                        Image(
                            painter = painterResource(id = R.drawable.personnedeux),
                            contentDescription = "image description",
                            contentScale = ContentScale.FillBounds,
                            modifier = Modifier
                                .width(44.dp)
                                .height(43.dp)

                        )

                        Text(
                            modifier = Modifier
                                .width(159.dp)
                                .height(30.dp)
                                .align(Alignment.CenterHorizontally),

                            text = "Espace Prestataire",
                            style = TextStyle(
                                fontSize = 9.sp,
                                lineHeight = 18.sp,
                                fontWeight = FontWeight(600),
                                color = Color(0xFFFFFFFF),
                                textAlign = TextAlign.Center // Center the text horizontally within the column
                            )
                        )

                    }


                }

            }


        }

//lina tzdiha
// nzid arg o5er description wki ta3mel on click thezek wte5ou el arg heka

   //     val listItems = listOf(
     //       CardItem(1, "Description de l'Item 1", R.drawable.image1,"Explicatio 1 "),
       //     CardItem(2,  "Description de l'Item 2", R.drawable.image2,"Explicatio 2 "),
         //   CardItem(3,  "Description de l'Item 3", R.drawable.image3,"Explicatio 3 "),
           // CardItem(4,  "Description de l'Item 4", R.drawable.image4,"Explicatio 4 "),
            //CardItem(5, "Description de l'Item 5", R.drawable.image5,"Explicatio 5 ")
        //)
       // MyListWithCards(listItems,navController)
    ///---bich nekteb fct o5ra bich n'appeleha toul 5ater 7echti beha fil Prestatire:
        afficherliste(navController)

    }


    }
)
}








//liste des cards:

/*  njarebha mba3d: data class cardview(
    var image : Int,
    var text : String,
)*/



/*@Composable
fun MyListWithCards(listItems: List<ListItem>) {



    // État pour gérer l'affichage du reste de la liste
    val isExpanded = remember { mutableStateOf(false) }


    Column(modifier = Modifier

        .fillMaxSize().background(Color(0xFF7E8DBD)),
        horizontalAlignment = Alignment.CenterHorizontally


    ) {


        LazyColumn(
            modifier = Modifier

                .fillMaxSize()

                .align(Alignment.CenterHorizontally)
                .padding(9.dp)

        ) {
            items(listItems.take(if (isExpanded.value) listItems.size else 2)) { item ->
                Card(
                    modifier = Modifier
                        .width(200.dp)
                        .height(200.dp)

                        .padding(bottom = 16.dp),
                    elevation = 8.dp,
                    border =  BorderStroke(6.dp,Color.Black),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Column {
                        Image(
                            painter = painterResource(id = item.imageResId),
                            contentDescription = "Image",
                            contentScale = ContentScale.Fit,
                            modifier = Modifier
                                .align(Alignment.CenterHorizontally)
                                //.fillMaxWidth()
                                .width(100.dp)
                                .height(100.dp)
                        )

                        Text(
                            text = item.description,
                            style = TextStyle(fontSize = 10.sp),
                            color = Color.Black,
                            // modifier = Modifier.padding(top = 5.dp)
                            modifier = Modifier.align(Alignment.CenterHorizontally)
                        )


                        Button(
                            onClick = { /* Action du bouton "Description" */ },
                            modifier = Modifier
                                .align(Alignment.CenterHorizontally)
                                .width(100.dp)
                                .padding(top = 5.dp)
                                .background(color = Color(0xFF7E8DBD)),

                            shape = RoundedCornerShape(size = 24.dp)
                        ) {
                            Text("Description",style = TextStyle(fontSize = 10.sp))
                        }



                    }
                }
            }


        }

        // Bouton "Voir plus" à la fin de la liste
        if (listItems.size > 2 && !isExpanded.value) {
            Button(
                onClick = { isExpanded.value = true },

                modifier = Modifier
                    .width(70.dp)
                    .height(50.dp)

                    .background(color = Color.Gray)
                    .align(Alignment.CenterHorizontally),
                shape = RoundedCornerShape(24.dp)

            ) {
                Text("Voir plus")
            }
        }
    }
}


*/
/*@Composable
fun MyListWithCards(listItems: List<ListItem>) {

    val isExpanded = remember { mutableStateOf(false) }

    Column {


        LazyColumn(
            modifier = Modifier
                .width(200.dp)
                .padding(10.dp)
                .align(Alignment.CenterHorizontally)
        ) {
            items(listItems.take(if (isExpanded.value) listItems.size else 2)) { item ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()

                        .padding(bottom = 10.dp),
                    elevation = 8.dp,
                    shape = RoundedCornerShape(8.dp) // Rounded corners for the Card
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                    ) {
                        Image(
                            painter = painterResource(id = item.imageResId),
                            contentDescription = "Image",
                            modifier = Modifier
                                .fillMaxWidth()
                                .aspectRatio(1f) // Square aspect ratio
                                .clip(RoundedCornerShape(8.dp)) // Rounded corners for the Image
                        )

                        Button(
                            onClick = { /* Action du bouton "Description" */ },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 16.dp)
                                .background(
                                    color = Color(0xFF368CF4),
                                    shape = RoundedCornerShape(24.dp)
                                ), // Rounded corners for the Button
                        ) {
                            Text("Description")
                        }


                        Text(
                            text = item.description,
                            style = TextStyle(fontSize = 16.sp),
                            color = Color.Black,
                            modifier = Modifier.padding(top = 8.dp).align(Alignment.CenterHorizontally)
                        )
                    }
                }
            }


        }


        // Bouton "Voir plus" à la fin de la liste
        if (listItems.size > 2 && !isExpanded.value) {
            RoundedButton(
                onClick = { isExpanded.value = true },
                modifier = Modifier
                    .width(100.dp)
                    .padding(top = 10.dp)

                    .background(color = Color(0xFF368CF4))
                    .align(Alignment.CenterHorizontally),
            ) {
                Text("Voir plus")
            }

        }
    }
}

@Composable
fun RoundedButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    content: @Composable RowScope.() -> Unit
) {
    Button(
        onClick = onClick,
        modifier = modifier,
        shape = RoundedCornerShape(24.dp), // Rounded corners for the Button
        content = content
    )
}
*/


//chnoi feha card
data class CardItem(val id: Int, val description: String, val imageResId: Int, val explication: String)

@Composable
fun MyListWithCards(listItems: List<CardItem>, navController: NavHostController) {
    val isExpanded = remember { mutableStateOf(false) }

    val searchText = remember { mutableStateOf(TextFieldValue()) }

//filtrer les deux en
    val filteredList = listItems.filter { item ->
        item.description.contains(searchText.value.text, ignoreCase = true) ||
                item.explication.contains(searchText.value.text, ignoreCase = true)
    }
    // Calculate the number of rows (2 cards per row)
    val rows = if (isExpanded.value) {
        //listItems.chunked(2)
        filteredList.chunked(2)
    } else {
        //listItems.take(4).chunked(2)
        filteredList.take(4).chunked(2)
    }


    Column() {

        TextField(
            value = searchText.value,
            onValueChange = { searchText.value = it },
            label = { Text("Recherche") },
            placeholder = { Text(text = "Rechercher un service")},
            leadingIcon = {
                Icon(
                    imageVector = Icons.Outlined.Search,
                    contentDescription = ""
                )
            },
            trailingIcon = {
                IconButton(onClick = { searchText.value = TextFieldValue() }) {
                    Icon(
                        imageVector = Icons.Outlined.Clear,
                        contentDescription = ""
                    )
                }
            },

            singleLine = true,
            modifier = Modifier
                .padding(10.dp)
                .clip(RoundedCornerShape(16.dp))
                .fillMaxWidth()

                .background(Color.White))


        LazyColumn(



        ) {
            items(rows
            ) { rowItems ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    rowItems.forEach { item ->
                        Card(
                            modifier = Modifier
                                .weight(1f)
                                .padding(5.dp)
                                .width(128.dp)
                                .height(128.dp), // Set the desired height for the Card
                            elevation = 8.dp,
                            shape = RoundedCornerShape(8.dp),
                            border = BorderStroke(width = 1.dp, color = Color(0xFF000000))
                        ) {
                            Column(
                                modifier = Modifier.fillMaxSize(),
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Image(
                                    painter = painterResource(id = item.imageResId),
                                    contentDescription = "Image",
                                    contentScale = ContentScale.FillBounds,
                                    modifier = Modifier
                                        .width(114.dp)
                                        .height(67.dp)
                                        .padding(top = 4.dp, bottom = 2.dp)
                                )



                                Text(
                                    text = item.description,
                                    style = TextStyle(fontSize = 9.sp),
                                    color = Color.Black,
                                    modifier = Modifier.padding( bottom = 1.dp)
                                )

                                Button(
                                    onClick = {

navController.navigate("Description/${item.imageResId}/${item.explication}")


                                              /* Action du bouton "Description" */ },
                                    modifier =

                                    Modifier
                                        .padding(2.dp)
                                        .width(117.dp)
                                        .height(30.dp)


                                        .align(Alignment.CenterHorizontally),

                                    shape = RoundedCornerShape(50.dp),
                                    border = BorderStroke(width = 1.dp, color = Color(0xFF000000)),
                                    colors = ButtonDefaults.buttonColors(Color(0xFFBBC3DE))
                                ) {
                                    Text("Description", Modifier

                                        .width(91.dp)
                                        .height(27.dp),
                                        style = TextStyle(
                                            fontSize = 9.sp,
                                            fontWeight = FontWeight(1000),
                                            textAlign = TextAlign.Center
                                        ))
                                }
                            }
                        }
                    }
                }
            }

        }


        // Bouton "Voir plus" à la fin de la liste
        if (listItems.size > 4 && !isExpanded.value) {
            Button(
                onClick = { isExpanded.value = true },
                modifier = Modifier
                    .padding(top = 3.dp, bottom = 30.dp)
                    .width(120.dp)
                    .height(70.dp)
                    .align(Alignment.CenterHorizontally)
                    .padding(top = 3.dp, bottom = 15.dp)
                 //  .align(Alignment.CenterHorizontally)
                        ,

                shape = RoundedCornerShape(50.dp),
                border = BorderStroke(width = 3.dp, color = Color(0xFFEE0707)),
                colors = ButtonDefaults.buttonColors(Color(0xFFF0E5C4))
                  //  .background(color = Color.White),
                //shape = RoundedCornerShape(size = 24.dp)

            ) {
                Text("Voir plus",
                modifier = Modifier.fillMaxWidth(),
                style = TextStyle(
                    fontWeight = FontWeight.Bold, // Make the text bold
                    color = Color.Red,
                    textAlign = TextAlign.Center // Center the text horizontally within the column
                )
                    )
            }


        }
    }

}

@Composable
fun afficherliste(navController: NavHostController){
    val listItems = listOf(
        CardItem(1, "Description de l'Item 1", R.drawable.image1,"Explicatio 1 "),
        CardItem(2,  "Description de l'Item 2", R.drawable.image2,"Explicatio 2 "),
        CardItem(3,  "Description de l'Item 3", R.drawable.image3,"Explicatio 3 "),
        CardItem(4,  "Description de l'Item 4", R.drawable.image4,"Explicatio 4 "),
        CardItem(5, "Description de l'Item 5", R.drawable.image5,"Explicatio 5 ")
    )
    MyListWithCards(listItems,navController)


}








@Preview(name = "accueil", showBackground = true)
@Composable
fun MyListWithCardsPreview() {
  /*  val listItems = listOf(
        CardItem(1, "Description de l'Item 1", R.drawable.image1),
        CardItem(2,  "Description de l'Item 2", R.drawable.image2),
        CardItem(3,  "Description de l'Item 3", R.drawable.image3),
        CardItem(4,  "Description de l'Item 4", R.drawable.image4),
        CardItem(5, "Description de l'Item 5", R.drawable.image5)
    )
    MyListWithCards(listItems)*/

   // accueil()
}

