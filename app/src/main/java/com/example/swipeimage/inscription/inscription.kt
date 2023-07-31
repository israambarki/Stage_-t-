package com.example.swipeimage.inscription

import android.annotation.SuppressLint
import android.widget.Toast
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
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
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.layout.ContentScale
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
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.swipeimage.R


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun Inscription(navController: NavController) {
    val context = LocalContext.current
    val scaffoldState = rememberScaffoldState()

    // class inscription contient tous:

    val nameVal = remember { mutableStateOf("") }
    val emailVal = remember { mutableStateOf("") }
    val phoneVal = remember { mutableStateOf("") }
    val passwordVal = remember { mutableStateOf("") }
    val confirmPasswordVal = remember { mutableStateOf("") }

    val passwordVisibility = remember { mutableStateOf(false) }
    val confirmPasswordVisibility = remember { mutableStateOf(false) }

    var SexeSelect = remember { mutableStateOf(true) }
    var InsType = remember { mutableStateOf(true) }




    Column(


        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier

            .fillMaxSize()
            .background(Color(0xFFF0E4C3))


        // .fillMaxWidth()

    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth(),
            contentAlignment = Alignment.TopCenter
        ) {
            Image(
                painter = painterResource(R.drawable.personne),
                contentDescription = "Register Image",
                modifier = Modifier
                    .width(200.dp)
                    .height(200.dp),
                contentScale = ContentScale.Fit
            )
        }

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
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    OutlinedTextField(
                        value = nameVal.value,
                        onValueChange = { nameVal.value = it },
                        label = { Text(text = "Nom", color = Black) },
                        placeholder = { Text(text = "Votre Nom", color = Black) },
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth(0.8f),
                        leadingIcon = {
                            Icon(imageVector = Icons.Filled.Person, contentDescription = "")

                        },

                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            unfocusedBorderColor = Black, textColor = Black
                        )
                    )

                    OutlinedTextField(
                        value = emailVal.value,
                        onValueChange = { emailVal.value = it },
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
                        )
                    )

                    OutlinedTextField(
                        value = phoneVal.value,
                        onValueChange = { phoneVal.value = it },
                        label = { Text(text = "numero de telephone", color = Black) },
                        placeholder = { Text(text = "+216 33333333", color = Black) },
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth(0.8f),
                        leadingIcon = {
                            Icon(imageVector = Icons.Filled.Phone, contentDescription = "")

                        },

                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            unfocusedBorderColor = Black, textColor = Black
                        )
                    )

                    OutlinedTextField(
                        value = passwordVal.value,
                        onValueChange = { passwordVal.value = it },
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


                        visualTransformation = if (passwordVisibility.value) VisualTransformation.None
                        else PasswordVisualTransformation()

                        ////
                        ////
                    )
                    OutlinedTextField(
                        value = confirmPasswordVal.value,
                        onValueChange = { confirmPasswordVal.value = it },
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
                        visualTransformation = if (confirmPasswordVisibility.value) VisualTransformation.None
                        else PasswordVisualTransformation()
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
                                    color = Color.Black
                                )
                                Row(
                                    modifier = Modifier.padding(start = 16.dp),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    RadioButton(
                                        selected = SexeSelect.value,
                                        onClick = { SexeSelect.value = true }
                                    )
                                    Text(
                                        text = Sexe.Homme.displayName,
                                        style = MaterialTheme.typography.body1
                                    )



                                    RadioButton(
                                        selected = !SexeSelect.value,
                                        onClick = { SexeSelect.value = false }
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
                                    text = "Type ",
                                    style = MaterialTheme.typography.h6,
                                    color = Color.Black
                                )
                                Row(
                                    modifier = Modifier.padding(start = 16.dp),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    RadioButton(
                                        selected = InsType.value,
                                        onClick = { InsType.value = true }
                                    )
                                    Text(
                                        text = TypeInscription.Client.displayName,
                                        style = MaterialTheme.typography.body1
                                    )


                                    RadioButton(
                                        selected = !InsType.value,
                                        onClick = { InsType.value = false }
                                    )
                                    Text(
                                        text = TypeInscription.Pres.displayName,
                                        style = MaterialTheme.typography.body1
                                    )

                                }

                            }




                    Spacer(modifier = Modifier.padding(5.dp))





                    Button(
                        colors = ButtonDefaults.buttonColors(backgroundColor = White),
                        onClick = {
                            if (nameVal.value.isEmpty()) {
                                Toast.makeText(
                                    context,
                                    "entrer votre nom !!",
                                    Toast.LENGTH_SHORT
                                ).show()
                            } else if (emailVal.value.isEmpty()) {
                                Toast.makeText(
                                    context,
                                    "entrer votre adresse mail!",
                                    Toast.LENGTH_SHORT
                                ).show()
                            } else if (phoneVal.value.isEmpty()) {
                                Toast.makeText(
                                    context,
                                    "entrer votre numero de telephone!",
                                    Toast.LENGTH_SHORT
                                ).show()
                            } else if (passwordVal.value.isEmpty()) {
                                Toast.makeText(
                                    context,
                                    "entrer votre mot de passe!",
                                    Toast.LENGTH_SHORT
                                ).show()
                            } else if (confirmPasswordVal.value.isEmpty()) {
                                Toast.makeText(
                                    context,
                                    "entrer une autre foie votre mot de passe !",
                                    Toast.LENGTH_SHORT
                                ).show()
                            } else {

                                //confirm password
                                if (confirmPasswordVal.value == passwordVal.value ){

                                Toast.makeText(
                                    context,
                                    "inscription avec succes!",
                                    Toast.LENGTH_SHORT
                                ).show()
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
                        border = BorderStroke(width = 1.dp, color = Color(0xFF000000))


                    ) {
                        Text(text = "Inscrivez vous ", fontSize = 20.sp, color = Black)
                    }

                    Spacer(modifier = Modifier.padding(5.dp))

                    Text(text = "Connectez-vous", color = Black,
                        modifier = Modifier.clickable {
                            /*navController.navigate("login_page")*/
                            navController.navigate("pagelogin")
                        }
                    )







                }
            }


        }
    }
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

@Composable
fun InscriptionPrev() {
    val navController = rememberNavController()
    Inscription(navController = navController)
}



@Preview(showBackground = true)
@Composable
fun afficheins(){
    
InscriptionPrev()

}