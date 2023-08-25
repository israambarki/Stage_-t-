package com.example.swipeimage.View.inscriptionconnecter

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonColors
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.RadioButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.swipeimage.R
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.input.ImeAction
import coil.compose.rememberImagePainter


import com.example.swipeimage.Data.datasource.Datasource.InscriptionPresonne
import com.example.swipeimage.Data.remote.Role
import com.example.swipeimage.View.Prestataire.AjoutService.openDatePickerDialog
import com.example.swipeimage.ViewModel.ViewModel.RegistrationViewModel
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Calendar
import java.util.Locale
import java.util.UUID


data class Inscri(
    val emailVal: String,
    var passwordVal: String,
                   )

@RequiresApi(Build.VERSION_CODES.O)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter", "CoroutineCreationDuringComposition")
@Composable

fun Inscription(navController: NavController, viewModel: RegistrationViewModel,
                onRegistrationComplete: () -> Unit) {
    val context = LocalContext.current
    val scaffoldState = rememberScaffoldState()

    // class inscription contient tous:


    // ... autres composants

    val coroutineScope = rememberCoroutineScope()


   // val nameVal = rememberSaveable { mutableStateOf("") }
    var nameVal by rememberSaveable { mutableStateOf("") }
    var pseudo by rememberSaveable { mutableStateOf("") }
    var Prenom by rememberSaveable { mutableStateOf("") }

    var naissance by remember { mutableStateOf("") }
    var selectedDate by remember { mutableStateOf(Calendar.getInstance()) }

//********
    //********var emailVal by remember { mutableStateOf("") }
    var emailVal by rememberSaveable { mutableStateOf("") }
///*******

   // val phoneVal = rememberSaveable { mutableStateOf("") }
    var phoneVal by rememberSaveable { mutableStateOf("") }

//*****
   // var passwordVal by remember { mutableStateOf("") }
    var passwordVal by rememberSaveable { mutableStateOf("") }
//******
    var confirmPasswordVal by remember { mutableStateOf("") }

    val passwordVisibility = remember { mutableStateOf(false) }
    val confirmPasswordVisibility = remember { mutableStateOf(false) }

  //  var SexeSelect = remember { mutableStateOf(true) }
    //import androidx.compose.runtime.*
    var InsType by rememberSaveable { mutableStateOf(TypeInscription.Client)}

   // var InsType = remember { mutableStateOf(true) }
    var SexeSelect by rememberSaveable { mutableStateOf(Sexe.Homme) }


    /////verification contraintes
   val isEmailValid by remember(emailVal) { mutableStateOf(emailVal.contains("@") && emailVal.length >= 6) }

    // Vérifier si le mot de passe contient au moins 8 caractères et des caractères spéciaux
    val isPasswordValid by remember(passwordVal) { mutableStateOf(passwordVal.length >= 8 &&  passwordVal.any { it.isLetterOrDigit().not() } &&
            passwordVal.any { it.isUpperCase() && passwordVal.any { it.isDigit() } &&
                    passwordVal.any { !it.isLetterOrDigit() }}
            ) }

    //donner mot de passe:
    var motdepassegenerer by remember { mutableStateOf(false) }

    //  s'il contient au moins une lettre majuscule (hasUppercase), un chiffre (hasDigit) et un caractère spécial (!it.isLetterOrDigit() equivalente a : it.isLetterOrDigit().not()).
    //Au moins une lettre majuscule (M)
    //Au moins un chiffre (2)
    //Au moins un caractère spécial (@)




// Ajout de l'état de connexion
  //  var isConnected by remember { mutableStateOf(false) }
    //val isConnected: Boolean by viewModel.Verifier().collectAsState()

    ////
    ////
    ////
    //// La logique de ROOM MVVM :

    var selectedImageUri by rememberSaveable { mutableStateOf<Uri?>(null) }

    /////
    var address by rememberSaveable { mutableStateOf("") }
    val openMapsLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) {
    }



    val rolesFromSwagger = listOf(
        Role(id = "bdf6c9d6-77f6-4ca1-89a8-b07588a8d3af", name = "Client"),
        Role(id = "94614254-1032-48a1-ac41-59b4b3d9d025", name = "Prestataire")
    )

    var selectedRoleId by remember { mutableStateOf("") }

/*    val SexeSwagger = listOf(
        "Homme",
        "Femme"
    )*/

    //var selectedSexe by remember { mutableStateOf("") }





    val activityResultLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
    ) { uri: Uri? ->
        selectedImageUri = uri
        //   coroutineScope.launch {
        //     selectedImageUri?.let { uri ->
        //       viewModel.updatePhotoUrlForConnectedUser(uri.toString())
    }

//
    Column(


        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier

            .fillMaxSize()
            .background(Color(0xFFF0E4C3))



        // .fillMaxWidth()

    ) {
        //Box(
           // modifier = Modifier
          //      .fillMaxWidth(),
        //    contentAlignment = Alignment.TopCenter
      //  ) {
           /* Image(
                painter = painterResource(R.drawable.personne),
                contentDescription = "Register Image",
                modifier = Modifier
                    .width(200.dp)
                    .height(200.dp),
                contentScale = ContentScale.Fit
            )*/

            // Section de la photo de profil
            Box(
                modifier = Modifier
                    .padding(top = 15.dp)
                    .size(120.dp)
                    .clip(CircleShape)
                    .background(Color.Gray)

                    .clickable {
                        activityResultLauncher.launch("image/*")
                    }
            ) {

                // Utilisez rememberImagePainter pour charger l'image à partir de l'URI
                val imagePainter = selectedImageUri?.let { rememberImagePainter(data =it)
                }
                Image(
                    painter = imagePainter ?: rememberImagePainter(data = R.drawable.personne),
                    contentDescription = null,
                    modifier = Modifier

                        .fillMaxSize())
            }


     //   }

        Scaffold(
            modifier = Modifier.fillMaxSize(),
            scaffoldState = scaffoldState
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    //   .fillMaxWidth()
                    //     .clip(RoundedCornerShape(30.dp))
                    .background(Color(0xFFF0E4C3))
                    .padding(10.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Inscription",
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold
                )

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.verticalScroll(rememberScrollState())
                ) {

                    OutlinedTextField(
                        // value = nameVal.value,
                        // onValueChange = { nameVal.value = it },
                        value = pseudo,
                        onValueChange = { pseudo = it },
                        shape = RoundedCornerShape(24.dp),
                        label = { Text(text = "pseudo", color = Black) },
                        placeholder = { Text(text = "pseudo", color = Black) },
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth(0.8f),
                        leadingIcon = {
                            Icon(imageVector = Icons.Filled.Person, contentDescription = "")

                        },
                        //   isError = nameVal.value.isEmpty(),
                        isError = pseudo.isEmpty(),
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            unfocusedBorderColor = Black, textColor = Black
                        )
                    )



                    OutlinedTextField(
                       // value = nameVal.value,
                       // onValueChange = { nameVal.value = it },
                        value = nameVal,
                        onValueChange = { nameVal = it },
                     shape = RoundedCornerShape(24.dp),
                        label = { Text(text = "Nom", color = Black) },
                        placeholder = { Text(text = "Votre Nom", color = Black) },
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth(0.8f),
                        leadingIcon = {
                            Icon(imageVector = Icons.Filled.Person, contentDescription = "")

                        },
                     //   isError = nameVal.value.isEmpty(),
                        isError = nameVal.isEmpty(),
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            unfocusedBorderColor = Black, textColor = Black
                        )
                    )

                    OutlinedTextField(
                        // value = nameVal.value,
                        // onValueChange = { nameVal.value = it },
                        value = Prenom,
                        onValueChange = { Prenom = it },
                        shape = RoundedCornerShape(24.dp),
                        label = { Text(text = "Prenom", color = Black) },
                        placeholder = { Text(text = "Prenom", color = Black) },
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth(0.8f),
                        leadingIcon = {
                            Icon(imageVector = Icons.Filled.Person, contentDescription = "")

                        },
                        //   isError = nameVal.value.isEmpty(),
                        isError = Prenom.isEmpty(),
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            unfocusedBorderColor = Black, textColor = Black
                        )
                    )


                    OutlinedTextField(
                        value = naissance,
                        onValueChange = {},
                        readOnly = true,
                        label = { Text("Date (jj/mm/aaaa)") },
                        shape = RoundedCornerShape(24.dp),
                        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                        singleLine = true,
                        leadingIcon = {
                            androidx.compose.material3.Icon(
                                Icons.Default.DateRange,
                                contentDescription = null,
                              modifier =Modifier  .clickable {
                                openDatePickerDialog(context, selectedDate) { year, month, day ->
                                    selectedDate.set(year, month, day)

                                    // Mettre à jour le texte du champ de texte avec la nouvelle date sélectionnée
                                    val formattedDate = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(selectedDate.time)
                                    naissance = formattedDate
                                    // Assurez-vous d'utiliser un MutableState pour mettre à jour la valeur du champ de texte
                                    // Par exemple : selectedDateText.value = formattedDate


                                }
                            }
                            )
                        },
                        modifier = Modifier
                            .fillMaxWidth(0.8f)
           ,
                        isError = naissance.isEmpty()
                    )



                    OutlinedTextField(
                        value = emailVal,
                        onValueChange = { emailVal = it },

                        shape = RoundedCornerShape(24.dp),
                        label = { Text(text = "Adresse Mail ", color = Black) },
                        placeholder = { Text(text = "exemple@gmail.com", color = Black) },
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth(0.8f),
                        leadingIcon = {
                            Icon(imageVector = Icons.Filled.Email, contentDescription = "")

                        },

                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),


                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            unfocusedBorderColor = Black, textColor = Black
                        ),


                     isError = !isEmailValid && emailVal.isNotEmpty()// Afficher une erreur si l'adresse e-mail est non vide mais invalide
                    )

                    OutlinedTextField(
                      //  value = phoneVal.value,
                        //onValueChange = { phoneVal.value = it },
                        value = phoneVal,
                        onValueChange = { phoneVal = it },

                        shape = RoundedCornerShape(24.dp),
                        label = { Text(text = "numero de telephone", color = Black) },
                        placeholder = { Text(text = "+216 33333333", color = Black) },
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth(0.8f),
                        leadingIcon = {
                            Icon(imageVector = Icons.Filled.Phone, contentDescription = "")

                        },
                     //   isError = phoneVal.value.isEmpty() ,
                        isError = phoneVal.isEmpty() ,
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            unfocusedBorderColor = Black, textColor = Black
                        )
                    )

                    OutlinedTextField(
                        value = passwordVal,
                        onValueChange = { passwordVal = it },
                        shape = RoundedCornerShape(24.dp),
                        label = { Text(text = "mot de passe", color = Black) },
                        placeholder = { Text(text = "mot de passe", color = Black) },
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth(0.8f),


                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),


                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            unfocusedBorderColor = Black, textColor = Black
                        ),
                        trailingIcon = {
                            IconButton(
                                onClick = {
                                    passwordVisibility.value = !passwordVisibility.value
                                }
                            ) {
                                Icon(
                                    painter = painterResource(R.drawable.eyexml),
                                    contentDescription = "Password visibilité",

                                    tint = if (passwordVisibility.value) Color.Red else Color.Gray
                                )
                            }
                        },

                       isError = !isPasswordValid && passwordVal.isNotEmpty(),
                        //// Afficher une erreur si le mot de passe est non vide mais invalide

                        visualTransformation = if (passwordVisibility.value) VisualTransformation.None
                        else PasswordVisualTransformation()

                        ////
                        ////
                    )
                    // donner auto password generator:
                    Button(
                        onClick = {
                            passwordVal = generateRandomPassword() 
                            motdepassegenerer = true
                        }, shape = RoundedCornerShape(50.dp),
                        border = BorderStroke(width = 1.dp, color = Color(0xFF000000)),colors = ButtonDefaults.buttonColors(
                            backgroundColor =Color.Red)
                    ) {
                        Text("Générer un mot de passe")
                    }


               /*     if (motdepassegenerer) {
                        Spacer(modifier = Modifier.height(8.dp))
                        Text("Mot de passe généré : $passwordVal", fontSize = 12.sp,color = Black)
                    }*/





                    OutlinedTextField(
                        value = confirmPasswordVal,
                        onValueChange = { confirmPasswordVal = it },
                        shape = RoundedCornerShape(24.dp),
                        label = { Text(text = "Confirmation mot de passe", color = Black) },
                        placeholder = { Text(text = "Réécrivez votre mot de passe", color = Black) },
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth(0.8f),
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            unfocusedBorderColor = Black, textColor = Black
                        ),
                        trailingIcon = {
                            IconButton(
                                onClick = {
                                    confirmPasswordVisibility.value =
                                        !confirmPasswordVisibility.value
                                }
                            ) {
                                Icon(
                                    painter = painterResource(R.drawable.eyexml),
                                    contentDescription = "Password Eye",
                                    tint = if (confirmPasswordVisibility.value) Color.Red else Color.Gray
                                )
                            }
                        },

                        isError = (confirmPasswordVal != passwordVal),
                        //// Afficher une erreur si le mot de passe est non vide mais invalide

                        visualTransformation = if (confirmPasswordVisibility.value) VisualTransformation.None
                        else PasswordVisualTransformation()
                    )



                    OutlinedTextField(
                        value = address,
                        onValueChange = { newAddress ->
                            address = newAddress
                            // Utilisez l'API Places pour obtenir des suggestions d'adresses basées sur newAddress
                        },
                        label = { Text("Adresse", color = Black) },
                        shape = RoundedCornerShape(24.dp),
                        keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
                        isError = address.isEmpty() ,
                        placeholder = { Text(text = "Google Maps", color = Black) },
                        leadingIcon = {
                            Icon(imageVector = Icons.Filled.LocationOn, contentDescription = "", modifier = Modifier .clickable { // Ouvrez Google Maps avec l'intention de choisir une adresse
                                val intent = Intent(
                                    Intent.ACTION_VIEW,
                                    Uri.parse("geo:0,0?q=${Uri.encode(address)}")
                                )
                                openMapsLauncher.launch(intent)})

                        },
                        singleLine = true,
                        modifier = Modifier
                            .fillMaxWidth(0.8f)
                           ,
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            unfocusedBorderColor = Black, textColor = Black
                        ),

                    )


                    //na9ssin les radi

                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(10.dp),

                            ) {
                                Text(
                                    text = "Sexe: ",
                                    style = MaterialTheme.typography.h6,
                                    color = Black,
                                    modifier = Modifier.padding(end = 41.dp)
                                )
                                Row(
                                  //  modifier = Modifier.padding(start = 16.dp),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                  //  RadioButton(
                                    //    selected = SexeSelect.value,
                                      //  onClick = { SexeSelect.value = true }
                                   // )
                               //&& selectedSexe == SexeSwagger[0]
                                    //selectedSexe = SexeSwagger[0]
                                    RadioButton(
                                        selected = SexeSelect == Sexe.Homme  ,
                                        onClick = { SexeSelect = Sexe.Homme

                                        }
                                    )

                                    Text(
                                        text = Sexe.Homme.displayName,
                                        style = MaterialTheme.typography.body1
                                    )



                               //     RadioButton(
                                 //       selected = !SexeSelect.value,
                                   //     onClick = { SexeSelect.value = false }
                                    //)
                                    //&& selectedSexe == SexeSwagger[1]
                                    //selectedSexe = SexeSwagger[1]
                                    RadioButton(
                                        selected = SexeSelect == Sexe.Femme     ,
                                        onClick = { SexeSelect = Sexe.Femme
                                        }
                                    )
                                    Text(
                                        text = Sexe.Femme.displayName,
                                        style = MaterialTheme.typography.body1
                                    )
                                }
                            }

                            Spacer(modifier = Modifier.size(2.dp))

                            // Registration type selection radio buttons
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(8.dp),

                            ) {
                                Text(
                                    text = "Type:",
                                    style = MaterialTheme.typography.h6,
                                    color = Black,
                                    modifier = Modifier.padding(end = 50.dp)
                                )
                                Row(
                                  //  modifier = Modifier.padding(start = 16.dp),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                 //   RadioButton(
                                   //     selected = InsType.value,
                                     //   onClick = { InsType.value = true }
                                    //)
                                    //&& selectedRoleId == rolesFromSwagger[0].id
                                    // selectedRoleId = rolesFromSwagger[0].id
                                    RadioButton(
                                        selected = InsType== TypeInscription.Client  ,
                                        onClick = { InsType = TypeInscription.Client

                                        }
                                    )

                                    Text(
                                        text = TypeInscription.Client.displayName,
                                        style = MaterialTheme.typography.body1
                                    )


                                  //  RadioButton(
                                    //    selected = !InsType.value,
                                       // onClick = { InsType.value = false }
                                    //)
                                    //&& selectedRoleId == rolesFromSwagger[1].id
                                    // selectedRoleId = rolesFromSwagger[1].id
                                    RadioButton(
                                            selected = InsType == TypeInscription.Pres,
                                         onClick = { InsType= TypeInscription.Pres
                                                   },
                                        modifier = Modifier.padding(start = 17.dp)
                                        )

                                    Text(
                                        text = TypeInscription.Pres.displayName,
                                        style = MaterialTheme.typography.body1
                                    )

                                }

                            }


                    Spacer(modifier = Modifier.padding(5.dp))

                    Button(
                       // colors = ButtonDefaults.buttonColors(backgroundColor = White),
                        onClick = {


                          //  if (nameVal.value.isEmpty())
                                if (nameVal.isEmpty()){
                                Toast.makeText(
                                    context,
                                    "entrer votre nom !!",
                                    Toast.LENGTH_SHORT
                                ).show()
                            } else if (emailVal.isEmpty()){

                                Toast.makeText(
                                    context,
                                    "entrer votre adresse mail!",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                            else if(!isEmailValid){

                                Toast.makeText(
                                    context,
                                    "Votre mail doit contenir @ !!",
                                    Toast.LENGTH_SHORT
                                ).show()


                           }

                            //else if (phoneVal.value.isEmpty())
                                else if (phoneVal.isEmpty()) {
                                Toast.makeText(
                                    context,
                                    "entrer votre numero de telephone!",
                                    Toast.LENGTH_SHORT
                                ).show()
                            } else if (passwordVal.isEmpty()){

                                Toast.makeText(
                                    context,
                                    "entrer votre mot de passe!",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                           else if(!isPasswordValid){

                                Toast.makeText(
                                    context,
                                    "Vérifier si le mot de passe contient au moins 8 caractères et des caractères spéciaux, votre mot de passe doit contenir au moins une lettre majuscule",
                                    Toast.LENGTH_SHORT
                                ).show()

                            }
                            else if (confirmPasswordVal.isEmpty()) {
                                Toast.makeText(
                                    context,
                                    "entrer une autre foie votre mot de passe !",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }

                            else {

                                //confirm password
                                if (confirmPasswordVal == passwordVal){

                                    val newUser =  InscriptionPresonne(
                                        mail = emailVal, passwordun = passwordVal ,
                                        nom = nameVal,
                                        phonenumb = phoneVal,
                                        Sexe = SexeSelect.toString(), photo =  selectedImageUri.toString(),
                                    address = address, pseudo = pseudo, Prenom = Prenom, naissance = naissance, Type = InsType.toString()) //, phonenumb = phoneVal,nom= nameVal ,Sexe = SexeSelect
                                    /////


                                    coroutineScope.launch {
                                        val isUnique = viewModel.isEmailUnique(newUser.mail)
                                        if (!isUnique) {
                                            // L'e-mail n'est pas unique, afficher un message d'erreur
                                            Toast.makeText(
                                                context,
                                                "Cet e-mail est déjà utilisé",
                                                Toast.LENGTH_SHORT
                                            ).show()
                                            return@launch
                                        }

                                       viewModel.registerUser(newUser)

                                        viewModel.updateConnectionStatus(newUser.mail, newUser.passwordun,true) // Mise à jour de l'état de connexion
                                        onRegistrationComplete()

                                    Toast.makeText(
                                    context,
                                  //  "inscription avec succes Bienvenue ${nameVal.value.uppercase()}!",
                                        "inscription avec succes Bienvenue ${nameVal.uppercase()}!",
                                    Toast.LENGTH_SHORT
                                ).show()
                                  //  var PersonneRep = PersonneRep(emailVal,passwordVal)
                                    //onSubmit(InscriptionBdsql)
                                    //RegistrationUseCase(PersonneRep)

                                   // registerUser(nameVal ,emailVal, phoneVal,passwordVal , SexeSelect, InsType )

                                 //aller au service prestataire
                                    if (InsType == TypeInscription.Pres ){
                                        navController.navigate("Profil_Prestataire")
                                        viewModel.registerUser(
                                            pseudo = pseudo,
                                            nom = nameVal,
                                            prenom = Prenom,
                                            password = passwordVal,
                                            phoneNumber = phoneVal,
                                            mail = emailVal,
                                            adress = address,
                                            sex = SexeSelect.toString(),
                                            isActive = true,
                                            roleId = UUID.fromString("94614254-1032-48a1-ac41-59b4b3d9d025") )
                                    }else{
                                        navController.navigate("Profil_Client")

                                        // api send
                                        viewModel.registerUser(
                                            pseudo = pseudo,
                                            nom = nameVal,
                                            prenom = Prenom,
                                            password = passwordVal,
                                            phoneNumber = phoneVal,
                                            mail = emailVal,
                                            adress = address,
                                            sex = SexeSelect.toString(),
                                            isActive = true,

                                            roleId = UUID.fromString("bdf6c9d6-77f6-4ca1-89a8-b07588a8d3af") )
                                    }


                            }
                                }
                            else {
                                    Toast.makeText(
                                        context,
                                        "Verifier votre mot de passe!",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }

                            }
                        },
                        shape = RoundedCornerShape(50.dp),
                        border = BorderStroke(width = 1.dp, color = Color(0xFF000000)),

                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = if (nameVal.isNotEmpty() && emailVal.isNotEmpty() &&  phoneVal.isNotEmpty() && passwordVal.isNotEmpty() && confirmPasswordVal.isNotEmpty() && isPasswordValid && isEmailValid && (confirmPasswordVal== passwordVal) && address.isNotEmpty() ) {
                                // Couleur lorsque le bouton est activé et tous les champs sont remplis
//&& selectedSexe.isNotEmpty()
                                Color.Green
                            } else {
                                // Couleur lorsque le bouton est désactivé (c'est-à-dire lorsque certains champs sont vides)
                                Color.Red
                            }
                        ),


                        enabled = nameVal.isNotEmpty() && emailVal.isNotEmpty() &&  phoneVal.isNotEmpty() && passwordVal.isNotEmpty() && confirmPasswordVal.isNotEmpty()


                    ) {
                        Text(text = "Inscrivez vous ", fontSize = 20.sp, color = Black)
                    }

                    Spacer(modifier = Modifier.padding(5.dp))

                    Text(text = "Connectez-vous", color = Black,
                        modifier = Modifier.clickable {
                            /*navController.navigate("login_page")*/
                            navController.navigate("Login")
                        }
                    )


                }
                //ici coroutine::



            }


        }
    }
}


fun generateRandomPassword(): String {

    val Majuscule = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
    val Chars = "0123456789"
    val specialChars = "!@#\$%^&*()_+\\-=\\[\\]{};':\",./<>?\\\\"
    val allChars = Majuscule + Chars + specialChars
    val random = java.util.Random()
    val passwordLength = 8
    val remainingChars = passwordLength - 3
    var i = 0
    val password = StringBuilder()
    // Ajoute une majuscule
    password.append(Majuscule[random.nextInt(Majuscule.length)])

    // Ajoute un chiffre
    password.append(Chars[random.nextInt(Chars.length)])

    // Ajoute un caractère spécial
    password.append(specialChars[random.nextInt(specialChars.length)])

    // Ajoute le reste des caractères aléatoirement


    while (i < remainingChars) {
        password.append(allChars[random.nextInt(allChars.length)])
        i++
    }

    val shuffledPassword = password.toMutableList().shuffled()
    return shuffledPassword.joinToString("")
}







fun openDatePickerDialog(
    context: Context,
    selectedDate: Calendar,
    onDateSelected: (year: Int, month: Int, day: Int) -> Unit
) {
    val datePicker = DatePickerDialog(
        context,
        { _, year, month, day ->
            onDateSelected(year, month, day)
        },
        selectedDate.get(Calendar.YEAR),
        selectedDate.get(Calendar.MONTH),
        selectedDate.get(Calendar.DAY_OF_MONTH)
    )
    datePicker.show()
}



/*@Composable
fun NavigatePage() {
    val navController = rememberNavController()

    NavHost(


        navController = navController,
        startDestination = "inscription",
        builder = {
            composable("connectez_vous_page", content = {})
            composable("inscription", content = { Inscription(navController = navController) })
        }
    )
}
*/

/*@Composable
fun InscriptionPrev() {
    val navController = rememberNavController()
    val registrationViewModel: RegistrationViewModel = viewModel()
    Inscription(navController = navController,registrationViewModel)
}*/



@Preview(showBackground = true)
@Composable
fun afficheins(){
    
//InscriptionPrev()

}