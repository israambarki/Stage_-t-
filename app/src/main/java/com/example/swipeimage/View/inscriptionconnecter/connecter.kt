package com.example.swipeimage.View.inscriptionconnecter

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api

import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.swipeimage.R

import kotlinx.coroutines.launch
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.lifecycleScope
import com.example.swipeimage.ViewModel.ViewModel.LoginViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginView(navController: NavController, viewModel: LoginViewModel,
              onLoginSuccess: () -> Unit) {
   // var mot_de_passe by remember { mutableStateOf("") }
    //var Email by remember { mutableStateOf("") }
    val context = LocalContext.current
    val focusManager = LocalFocusManager.current
    var emailVal by rememberSaveable { mutableStateOf("") }
    var passwordVal by rememberSaveable { mutableStateOf("") }
    val passwordVisibility = remember { mutableStateOf(false) }
    val coroutineScope = rememberCoroutineScope()



    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xFFF0E5C4)),
        contentAlignment = Alignment.BottomCenter
    ) {

      //  Image(
        //    painter = painterResource(R.drawable.back),
          //  contentDescription = "login",
           // modifier = Modifier
             //   .fillMaxWidth()
               // .height(250.dp)
   //     )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                ,
            horizontalAlignment = Alignment.CenterHorizontally,

            )
        {
            Image(
                painter = painterResource(R.drawable.login),
                contentDescription = "login",
                Modifier
                    .padding(1.dp)
                    .width(180.dp)
                    .height(180.dp)

              )
            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Bienvenue sur ServiLink",
                color = Color(0xFF00416A),
                textAlign = TextAlign.Center,
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold
            )


            Spacer(modifier = Modifier.height(20.dp))


            Text(
                text = " Connectez-vous",
                Modifier
                    .width(131.dp)
                    .height(20.dp),
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF00416A),
                textAlign = TextAlign.Center,
            )



            Spacer(modifier = Modifier.height(30.dp))

            OutlinedTextField(
                value = emailVal,
                onValueChange = { emailVal = it },
                shape = RoundedCornerShape(24.dp),
                label = {
                    Text(
                        text = "Email | numéro",
                        color = Color(0xFF000000),
                    )
                },
                placeholder = {
                    androidx.compose.material.Text(
                        text = "Votre mail/numero de telephone",
                        color = Color.Black
                    )
                },

                singleLine = true,
                modifier = Modifier.fillMaxWidth(0.8f),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    unfocusedBorderColor = Color.Black, textColor = Color.Black
                ),

                leadingIcon = {
                    Icon(imageVector = Icons.Filled.Email, contentDescription = "")

                },

                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),


               // keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next),
                keyboardActions = KeyboardActions(
                    onNext = {
                        focusManager.moveFocus(FocusDirection.Next)
                    }
                ),



                )
            Spacer(modifier = Modifier.height(30.dp))

            OutlinedTextField(
                value = passwordVal,
                onValueChange = { passwordVal = it },
                shape = RoundedCornerShape(24.dp),
                label = {
                    Text(
                        text = "mot de passe",
                        color = Color(0xFF000000)
                    )
                },
                placeholder = {
                    androidx.compose.material.Text(
                        text = "Entrer votre mot de passe",
                        color = Color.Black
                    )
                },
                singleLine = true,
                modifier = Modifier.fillMaxWidth(0.8f),

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

                colors = TextFieldDefaults.outlinedTextFieldColors(
                    unfocusedBorderColor = Color.Black, textColor = Color.Black
                ),
             //   visualTransformation = PasswordVisualTransformation(),
              //  keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                visualTransformation = if (passwordVisibility.value) VisualTransformation.None
                else PasswordVisualTransformation(),

                keyboardActions = KeyboardActions(
                    onDone = {
                        focusManager.clearFocus()
                    }
                ),
            )
            Spacer(modifier = Modifier.height(30.dp))

            Button(
                onClick = {
                    //viewModel.viewModelScope.launch
                    coroutineScope.launch {
                    val success = viewModel.loginUser(emailVal, passwordVal)
                    if (success) {
                       onLoginSuccess()
                      /*  Toast.makeText(
                            context,
                            "Bravo!!!!!",
                            Toast.LENGTH_SHORT
                        ).show()*/
                    } else {
                        // Handle login failure, show error message, etc.
                        Toast.makeText(
                            context,
                            "Verifier votre mot de passe ou votre mail",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                          }},
                shape = RoundedCornerShape(50.dp),
                border = BorderStroke(width = 1.dp, color = Color(0xFF000000)),
                colors = ButtonDefaults.buttonColors(Color(0xFF00416A)),
                modifier = Modifier

                    .align(Alignment.CenterHorizontally)
            ) {
                Text(text = "Se connecter", fontSize = 20.sp, color = Color.White)
            }
            Spacer(modifier = Modifier.height(30.dp))
            TextButton(onClick = { /*TODO*/ }) {
                Text(
                    text = "Mot de passe oublié?",
                    style = TextStyle(
                        fontSize = 18.sp,
                        fontFamily = FontFamily.Serif ,
                        fontWeight = FontWeight(400),
                        color = Color(0xFF00416A),
                        textAlign = TextAlign.Center,
                    )
                )
            }
            Spacer(modifier = Modifier.height(20.dp))

            TextButton(onClick = {
                /*navController.navigate("login_page")*/
                navController.navigate("Inscription") }) {
                Text(
                    text = "Vous n’avez pas de compte ? Créer le",
                    style = TextStyle(
                        fontSize = 18.sp,
                        fontWeight = FontWeight(400),
                        color = Color(0xFF00416A),
                    )
                )
            }

        }
    }
}


@Preview(showSystemUi = true, showBackground = true)
@Composable
fun LoginPrev() {
   // LoginView()
}