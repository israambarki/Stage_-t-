package com.example.swipeimage.View.Profil

import android.annotation.SuppressLint
import android.net.Uri
import android.os.Build
import android.widget.DatePicker
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.AlertDialog
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.swipeimage.ViewModel.ViewModel.RegistrationViewModel
import kotlinx.coroutines.launch
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.input.pointer.pointerHoverIcon
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.window.Dialog
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.example.swipeimage.Data.datasource.Datasource.InscriptionPresonne
import com.example.swipeimage.R
import com.google.android.material.datepicker.MaterialDatePicker
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.Calendar


@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalCoilApi::class)
@SuppressLint("CoroutineCreationDuringComposition", "UnusedMaterialScaffoldPaddingParameter")
@Composable
fun UserProfileScreen(navController: NavController,
                      viewModel: RegistrationViewModel
) {
   // val isConnected by viewModel.isConnected.collectAsState()

    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()

    var selectedImageUri by rememberSaveable { mutableStateOf<Uri?>(null) }

    var Experience by rememberSaveable { mutableStateOf("") }
    var Certification by rememberSaveable { mutableStateOf("") }
    var selectedDate by remember { mutableStateOf(Calendar.getInstance()) }
    var isDatePickerVisible by remember { mutableStateOf(false) }

    var day by remember { mutableStateOf(Calendar.getInstance().get(Calendar.DAY_OF_MONTH)) }
    var month by remember { mutableStateOf(Calendar.getInstance().get(Calendar.MONTH) + 1) }
    var year by remember { mutableStateOf(Calendar.getInstance().get(Calendar.YEAR)) }

    // Utiliser un LaunchedEffect pour appeler GetLastUserConnection()
    // @Composable invocations can only happen from the context of a @Composable function
    //et avec cette aproche rien n'est passée
    //ChatGPT
    //La raison pour laquelle vous obtenez une erreur lors de l'utilisation de coroutineScope.launch sans @Composable est que coroutineScope.launch crée une nouvelle coroutine, et Compose nécessite que les opérations composable soient exécutées à l'intérieur d'une coroutine de composition.
    // C'est pourquoi vous devez vous assurer que votre code @Composable est exécuté à l'intérieur de cette coroutine.
    val connectedUser by remember {
        mutableStateOf<InscriptionPresonne?>(null)
            .apply {
                coroutineScope.launch {
                    //value = viewModel.GetLastUserConnection()
                    value = viewModel.GetUserConnection()
                }
            }
    }




   /* val activityResultLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
    ) { uri: Uri? ->
         selectedImageUri = uri
        coroutineScope.launch {
            selectedImageUri?.let { uri ->
                viewModel.updatePhotoUrlForConnectedUser(uri.toString())
            }



           // viewModel.updatePhotoUrlForConnectedUser(selectedImageUri.toString())
        }

                //Mettre à jour l'URL de la photo dans la base de données
               // viewModel.updatePhotoUrlForConnectedUser(it.toString())
        // Mettre à jour l'URI de la photo de profil dans la base de données
      //  coroutineScope.launch {

        //connectedUser?.let {
          //  val updatedUser = it.copy(photoUrl = uri.toString())
            //viewModel.updateUser(updatedUser.toString())
        //}
     //   }
    }*/

    // Utiliser un LaunchedEffect pour appeler GetLastUserConnection()




        var bottomState by remember {
            mutableStateOf("Profil_Prestataire") }

        androidx.compose.material.Scaffold(
            bottomBar = {
                //  BottomAppBar(modifier = Modifier.background(Color(0x00416A))) {
                //      Text(text = "hhhh")

                BottomNavigation(
                    modifier = Modifier

                        .clip(RoundedCornerShape(16.dp))
                        ,
                    elevation = 10.dp,
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
                            Icon(imageVector = Icons.Default.Home , contentDescription ="" )

                        }


                    )

                    //profil

                    BottomNavigationItem(
                        selected = bottomState == "Contacter",
                        onClick = { bottomState = "Contacter" },
                        label = { Text(text = "Contacter", color = Color.White)  },
                        icon = {
                            Icon(imageVector = Icons.Default.Call , contentDescription ="" )

                        }


                    )
//inscription

                    BottomNavigationItem(
                        selected = bottomState == "Accueil",
                        onClick = { bottomState = "Accueil"
                            navController.navigate("Accueil") ; /* navController.navigate("Inscription") */////verifier la connexion:
                            },
                        label = { Text(text = "Accueil", color = Color.White)  },
                        icon = {
                            Icon(imageVector = Icons.Default.ExitToApp , contentDescription ="" )

                        }

                    )

                }
            }
            ,

            content = {

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color(0xFFF0E4C3))
                    //    .padding(5.dp)
                    ,
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.CenterHorizontally

                ) {


                    Row(
                        modifier = Modifier
                            .height(50.dp)
                            .fillMaxWidth()
                            .background(Color.White),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically

                    ) {

                        Text("Profil Prestataire", fontWeight = FontWeight.Bold)

                    }



                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color(0xFFF0E4C3))
                        //    .padding(5.dp)
                        .verticalScroll(rememberScrollState()),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally

                ) {


// Section de la photo de profil
                    Box(
                        modifier = Modifier
                          //  .padding(top = 5.dp)
                            .size(150.dp)
                            .clip(CircleShape)
                            .border(width=5.dp, color = Color.Black, shape = CircleShape)
                           .background(Color.Gray)
                        //  .clickable {
                        //    activityResultLauncher.launch("image/*")
                        // }
                    ) {
                        // Utilisez rememberImagePainter pour charger l'image à partir de l'URI
                        val imagePainter =
                            connectedUser?.photo?.let { rememberImagePainter(data = it) }
                        Image(
                            painter = imagePainter ?: rememberImagePainter(data = R.drawable.personne),
                            contentDescription = "Photo de profil", // Indiquez une description appropriée
                            modifier = Modifier.fillMaxSize()
                        )


                    }


                    Column(
                            modifier = Modifier
                                .fillMaxSize(),
                               // .clip(shape = RectangleShape)
                              //  .background(Color(0xFFF5F4F2)),
                                //    .padding(5.dp)
                               // .verticalScroll(rememberScrollState()),
                            verticalArrangement = Arrangement.Top,
                            horizontalAlignment = Alignment.CenterHorizontally

                        ) {


                            // Vérifier si l'utilisateur est connecté et afficher le mail
                            connectedUser?.let {


                                Text(
                                    text = "${it.pseudo}",

                                    textAlign = TextAlign.Center, // Center the text horizontally
                                    modifier = Modifier.fillMaxSize(),
                                    style = TextStyle(
                                        fontSize = 18.sp,
                                        fontFamily = FontFamily.Serif,
                                        fontWeight = FontWeight(400),
                                        color = Color(0xFF00416A),
                                        textAlign = TextAlign.Center,
                                    )
                                )

                                Text(
                                    text = "${it.nom}" + "" + "${it.Prenom}",

                                    textAlign = TextAlign.Center, // Center the text horizontally
                                    modifier = Modifier.fillMaxSize(),
                                    style = TextStyle(
                                        fontSize = 18.sp,
                                        fontFamily = FontFamily.Serif,
                                        fontWeight = FontWeight(400),
                                        color = Color(0xFF00416A),
                                        textAlign = TextAlign.Center,
                                    )
                                )




                                Text(
                                    text = "mail: ${it.mail}",
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 18.sp,
                                    color = Color.Black,
                                    modifier = Modifier.padding(bottom = 16.dp),
                                    style = TextStyle(
                                        fontSize = 18.sp,
                                        fontFamily = FontFamily.Serif,
                                        fontWeight = FontWeight(400),
                                        color = Color(0xFF00416A),
                                        textAlign = TextAlign.Center,
                                    )
                                )

                                Row() {


                                Text(
                                    text = "address:${it.address} ",

                                    textAlign = TextAlign.Center, // Center the text horizontally
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .padding(end = 7.dp),
                                    style = TextStyle(
                                        fontSize = 18.sp,
                                        fontFamily = FontFamily.Serif,
                                        fontWeight = FontWeight(400),
                                        color = Color(0xFF00416A),
                                        textAlign = TextAlign.Center,
                                    )
                                )

                                Icon(imageVector = Icons.Filled.LocationOn, contentDescription = "")
                                }






                                Text(
                                    text = "${it.naissance}",

                                    textAlign = TextAlign.Center, // Center the text horizontally
                                    modifier = Modifier.fillMaxSize(),
                                    style = TextStyle(
                                        fontSize = 18.sp,
                                        fontFamily = FontFamily.Serif,
                                        fontWeight = FontWeight(400),
                                        color = Color(0xFF00416A),
                                        textAlign = TextAlign.Center,
                                    )
                                )



                                Text(
                                    text = "Sexe: ${it.Sexe}",

                                    textAlign = TextAlign.Center, // Center the text horizontally
                                    modifier = Modifier.fillMaxSize(),
                                    style = TextStyle(
                                        fontSize = 18.sp,
                                        fontFamily = FontFamily.Serif,
                                        fontWeight = FontWeight(400),
                                        color = Color(0xFF00416A),
                                        textAlign = TextAlign.Center,
                                    )
                                )

                                Text(
                                    text = "numero: ${it.phonenumb}",

                                    textAlign = TextAlign.Center, // Center the text horizontally
                                    modifier = Modifier.fillMaxSize(),
                                    style = TextStyle(
                                        fontSize = 18.sp,
                                        fontFamily = FontFamily.Serif,
                                        fontWeight = FontWeight(400),
                                        color = Color(0xFF00416A),
                                        textAlign = TextAlign.Center,
                                    )
                                )


                        }
                    }
                    
                    Spacer(modifier = Modifier.height(6.dp))
                    

                    Box(
                        Modifier
                            .width(1446.dp)
                            .height(150.dp)
                            .background(
                                color = Color(0xFFFFFFFF),
                                shape = RoundedCornerShape(size = 20.dp)
                            )) {

                        Column() {

                            Row() {
                                Text(
                                    text = "Qualification :",
                                    style = TextStyle(
                                        fontSize = 15.sp,

                                        fontWeight = FontWeight(400),
                                        color = Color(0xFF00416A),
                                        textDecoration = TextDecoration.Underline,
                                    )
                                )
                            }
                           Row() {
                             Image(painter =painterResource(id = R.drawable.exp), contentDescription ="" )
                         /*   Text(
                                text = "Expérience de travail:",
                                        style = TextStyle(
                                        fontSize = 36.sp,

                                fontWeight = FontWeight(400),
                                color = Color(0xFF263238),
                            )
                            )*/

                               OutlinedTextField(
                                   // value = nameVal.value,
                                   // onValueChange = { nameVal.value = it },
                                   value = Experience,
                                   onValueChange = { Experience = it },
                                   shape = RoundedCornerShape(24.dp),
                                   label = { androidx.compose.material.Text(text = "Expérience de travail:", color = Color.Black) },
                                   placeholder = { androidx.compose.material.Text(text = "Expérience de travail:", color = Color.Black) },
                                   singleLine = true,
                                   modifier = Modifier
                                       .fillMaxWidth(0.8f)
                                       .size(17.dp),
                                   //   isError = nameVal.value.isEmpty(),
                               //    isError = Experience.isEmpty(),
                                   colors = TextFieldDefaults.outlinedTextFieldColors(
                                       unfocusedBorderColor = Color.Black, textColor = Color.Black
                                   )
                               )




                           }


                            Row() {

                                Image(painter =painterResource(id = R.drawable.cer), contentDescription ="" )

                                Text(
                                    text = "Certification:",
                                    style = TextStyle(
                                        fontSize = 15.sp,

                                        fontWeight = FontWeight(400),
                                        color = Color(0xFF263238),
                                    )
                                )

                            }

                        }

                    }


                    Row(modifier = Modifier.padding(top = 5.dp)) {

                        Icon(imageVector = Icons.Default.AddCircle,
                            contentDescription = "",
                            modifier = Modifier
                                .clickable { navController.navigate("PrestataireService") }
                                .size(50.dp)
                        )
                        Text(
                            text = "Ajouter Un Service",
                            fontSize = 15.sp,
                            color = Color.Black,
                            modifier = Modifier.padding(top = 5.dp)
                        )
                    }


                    /*   Button(
                        onClick = { navController.navigate("PrestataireService") },

                        shape = RoundedCornerShape(50.dp),
                        border = BorderStroke(width = 1.dp, color = Color(0xFF000000)),
                        colors = ButtonDefaults.buttonColors(Color(0xFF00416A)),
                        modifier = Modifier
                            .padding(top = 10.dp, bottom = 10.dp)
                          //  .align(Alignment.CenterHorizontally)

                            .width(200.dp)
                    ) {
                        Icon(imageVector = Icons.Default.Add, contentDescription = "add")
                        Text(text = "Ajouter Un Service", fontSize = 10.sp, color = Color.White)
                    }*/


                  //  Spacer(modifier = Modifier.height(5.dp))

                    Button(
                        onClick = {
                            coroutineScope.launch {
                                // Se déconnecter et retourner à l'écran d'accueil

                                //  viewModel.logout()
                                //   viewModel.logoutAndDeleteUser()
                                 //viewModel.Delete()
                                viewModel.Deconnecter()
                                Toast.makeText(
                                    context,
                                    "deconnexion avec succes",
                                    Toast.LENGTH_SHORT
                                ).show()

                                navController.navigate("Accueil")
                            }
                        },
                        shape = RoundedCornerShape(50.dp),
                        border = BorderStroke(width = 1.dp, color = Color(0xFF000000)),
                        colors = ButtonDefaults.buttonColors(Color(0xFF00416A)),
                        modifier = Modifier
                            .padding(bottom = 10.dp)
                            //.align(Alignment.CenterHorizontally)
                          //  .width(200.dp)
                    ) {
                        Text(text = "Se déconnecter", fontSize = 10.sp, color = Color.White)
                    }
                }
            }
            }
        )


}












@RequiresApi(Build.VERSION_CODES.O)
@Preview(showSystemUi = true, showBackground = true)
@Composable
fun ProfilScroll() {
    val navController = rememberNavController()
    val registrationViewModel: RegistrationViewModel = viewModel()
    UserProfileScreen(navController = navController, viewModel = registrationViewModel )
}