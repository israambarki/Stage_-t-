package com.example.swipeimage.View.Prestataire.AjoutService


import android.app.DatePickerDialog
import android.content.Context
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem

import androidx.compose.material3.Icon
//import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.KeyboardArrowDown
import androidx.compose.material.icons.outlined.KeyboardArrowUp
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.swipeimage.R
import java.text.SimpleDateFormat

//pour datepicker//+++++
import java.util.Calendar
import java.util.Locale

data class Service(
    val serviceName: String,
    var titre: String,
    var unité: String,
    var description: String,
    var date: String

)
//data class FormPres()

@Composable
fun Prestataire(onSubmit: (Service) -> Unit) {
    val context1 = LocalContext.current

    var titre by remember { mutableStateOf("") }
    var unité by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var date by remember { mutableStateOf("") }
    var selectedDate by remember { mutableStateOf(Calendar.getInstance()) }
    val context = LocalContext.current
    var selectedDateText by remember { mutableStateOf("") }
    val PrixValid by remember(unité) { mutableStateOf(unité.matches(Regex("\\d*"))) }





    var ErreurDes by remember { mutableStateOf(false) }
    var ErreurT by remember { mutableStateOf(false) }
    var ErreurU by remember { mutableStateOf(false) }
    var ErreurDate by remember { mutableStateOf(false) }

    val isDescriptionValid by remember(description) { mutableStateOf(description.length >= 20) }


   // var buttonColor by remember { mutableStateOf(Color) }


    var expanded by remember { mutableStateOf(false) }
    // C'est une variable d'état (state) qui est utilisée pour contrôler l'ouverture ou la fermeture du menu déroulant. Au début, le menu est fermé (expanded = false)


    // var selectedService by remember { mutableStateOf<Service?>(null) }


    var selectedText by remember { mutableStateOf("") }
//C'est une variable d'état qui est utilisée pour stocker le texte sélectionné par l'utilisateur dans le menu déroulant.


    val suggestions =
        listOf(Service("Service A",titre,unité,description,date), Service("Service B",titre,unité,description,date), Service("Service C",titre,unité,description,date))


    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,

        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            //.padding(16.dp)
            .background(color = Color(0xFFF0E4C3))
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth(),
            contentAlignment = Alignment.TopCenter
        ) {


            Image(
                painter = painterResource(R.drawable.form),
                contentDescription = "Form",
                modifier = Modifier
                    .width(100.dp)
                    .height(100.dp),
                contentScale = ContentScale.Fit
            )

        }

        Spacer(modifier = Modifier.height(2.dp))
        Text(
            text = "Prestataire",
            style = TextStyle(
                fontSize = 24.sp,

                fontWeight = FontWeight.Bold,
                color = Color(0xFF000000),
                textAlign = TextAlign.Center,
            )

        )
        Spacer(modifier = Modifier.height(2.dp))
        Text(
            text = "Ajouter votre demande ",
            style = TextStyle(
                fontSize = 16.sp,

                fontWeight = FontWeight(400),
                color = Color(0xFF000000),
                textAlign = TextAlign.Center
            )
        )




        Row(
            modifier = Modifier
                .fillMaxWidth(0.8f)
                //.padding(16.dp)
            ,
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center

        ) {

            // affiche le texte sélectionné et utilise l'icône de flèche vers le haut ou vers le bas
            //(Icons.Outlined.KeyboardArrowUp ou Icons.Outlined.KeyboardArrowDown)
            OutlinedTextField(
                value = selectedText,
                onValueChange = { selectedText = it },
                shape = RoundedCornerShape(24.dp),
                singleLine = true,
                modifier = Modifier
                    .fillMaxWidth(0.8f),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    unfocusedBorderColor = Color.Black, textColor = Color.Black
                ),
                isError = selectedText.isEmpty(),

                label = { Text("Choisir le Service") },
                // leadingIcon = { Icon(Icons.Outlined.List, contentDescription = "Service Icon") },
                trailingIcon = {
//C'est l'icône de l'IconButton qui permet d'ouvrir ou de fermer le menu déroulant. Lorsqu'il est cliqué, expanded est mis à jour pour ouvrir ou fermer le menu.
                    IconButton(onClick = {
                        expanded = !expanded
                        //Cette lambda est appelée lorsque l'utilisateur clique sur l'IconButton. Elle inverse la valeur de expanded, c'est-à-dire si le menu est ouvert, il le ferme, et s'il est fermé, il l'ouvre.
                    }) {
                        //C'est un bouton avec une icône. Il est utilisé pour afficher l'icône de la flèche vers le haut ou vers le bas à droite du TextField.
                        Icon(
                            if (expanded) Icons.Outlined.KeyboardArrowUp else Icons.Outlined.KeyboardArrowDown,
                            contentDescription = "Expand/Collapse"
                        )

                    }
                }
            )


            if (expanded) {
                val menuShape: Shape =
                    RoundedCornerShape(24.dp) // Définir le rayon de coin ici (8.dp dans cet exemple)


                // C'est le composant qui affiche le menu déroulant avec les suggestions lorsque expanded est true.
                DropdownMenu(
                    expanded = expanded,

                    onDismissRequest = {
                        expanded = false
                        //Cette lambda est appelée lorsque l'utilisateur clique en dehors du DropdownMenu, ce qui indique que le menu doit être fermé. Elle met à jour expanded à false.
                    },


                    modifier = Modifier
                        .fillMaxWidth(0.8f)
                        .clip(menuShape)
                    //  .border(width = 30.dp, color = Color.Black, shape = RoundedCornerShape(24.dp))
                    ,
                ) {
                    suggestions.forEach { service ->
                        DropdownMenuItem(
                            onClick = {
                                selectedText = service.serviceName
                                expanded = false
                            }
                        ) {
                            Text(service.serviceName)
                        }
                    }
                }
            }
        }
        //les autres textfields:
        OutlinedTextField(
            value = titre,
            onValueChange = { titre = it },
            shape = RoundedCornerShape(24.dp),
            label = { Text("Titre") },
            singleLine = true,
            leadingIcon = { Icon(Icons.Outlined.Info, contentDescription = "") },
            isError = titre.isEmpty(),
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .padding(16.dp)
               ,
            colors = TextFieldDefaults.outlinedTextFieldColors(
                unfocusedBorderColor = Color.Black, textColor = Color.Black
            ),
        )
        if (ErreurT) {
            Text(
                text = "Vous devez saisir un titre",
                color = Color.Red,
                modifier = Modifier.padding(bottom = 8.dp)
            )
        }




        OutlinedTextField(
            value = description,
            onValueChange = { description = it },
            shape = RoundedCornerShape(24.dp),
            label = { Text("Description") },
            placeholder = { Text(text = "Vous Devez decrire votre Service", color = Color.Black) },
            isError = description.isNotEmpty() && !isDescriptionValid,

          //  singleLine = true,
            leadingIcon = { Icon(Icons.Outlined.Info, contentDescription = "") },
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .padding(16.dp)
               ,
            colors = TextFieldDefaults.outlinedTextFieldColors(
                unfocusedBorderColor = Color.Black, textColor = Color.Black
            ),
        )


        if (ErreurDes) {

            Text(
                text = "Vous devez décrire votre service",
                color = Color.Red,
                modifier = Modifier.padding(bottom = 8.dp)
            )
        }else if (!isDescriptionValid){
            Text(
            text ="Vous devez décrire votre service"+"\n"+ "Vous devez écrire au minimum 20 caracteres",
            color = Color.Red,
            modifier = Modifier.padding(bottom = 8.dp),
                style = TextStyle(
                    textAlign = TextAlign.Center)
        )
        }





        OutlinedTextField(
            value = unité,
            onValueChange = { unité = it },
            shape = RoundedCornerShape(24.dp),
            label = { Text("Unité en DT") },
            placeholder = { Text(text = "Prix en Dinar Tunisien", color = Color.Black) },
            isError = unité.isNotEmpty() && !PrixValid,
            singleLine = true,
            leadingIcon = { Icon(Icons.Outlined.Info, contentDescription = "") },
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .padding(16.dp)
               ,
            colors = TextFieldDefaults.outlinedTextFieldColors(
                unfocusedBorderColor = Color.Black, textColor = Color.Black
            ),
        )


        if (ErreurU && !PrixValid) {
            Text(
                text = "Vous devez saisir une unité" +"\n"+"Le prix doit contenir uniquement des chiffres",
                color = Color.Red,
                modifier = Modifier.padding(bottom = 8.dp),
                style = TextStyle(
                    textAlign = TextAlign.Center)
            )
        }




/*
        OutlinedTextField(
            value = date,
            onValueChange = { date = formatToDateString(it) },
            shape = RoundedCornerShape(24.dp),
            label = { Text("Date (jj/mm/aaaa)") },

            singleLine = true,
            leadingIcon = { Icon(Icons.Outlined.DateRange, contentDescription = "Date Icon") },
            isError = date.isEmpty(),

            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text,imeAction = ImeAction.Done),

            modifier = Modifier
                .fillMaxWidth(0.8f)
                .padding(16.dp)
                ,
            colors = TextFieldDefaults.outlinedTextFieldColors(
                unfocusedBorderColor = Color.Black, textColor = Color.Black
            ),
        )
        if (ErreurDate) {
            Text(
                text = "Vous devez saisir une date",
                color = Color.Red,
                modifier = Modifier.padding(bottom = 8.dp),
                style = TextStyle(
                    textAlign = TextAlign.Center)
            )
        }

*/

        // Afficher le dialogue de sélection de date lorsqu'on clique sur le champ de texte
        OutlinedTextField(
            value = selectedDateText,
            onValueChange = {},
            readOnly = true,
            label = { Text("Date (jj/mm/aaaa)") },
            shape = RoundedCornerShape(24.dp),
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
            singleLine = true,
            leadingIcon = { Icon(Icons.Default.DateRange, contentDescription = null) },
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .padding(16.dp)
                .clickable {
                    openDatePickerDialog(context, selectedDate) { year, month, day ->
                        selectedDate.set(year, month, day)

                        // Mettre à jour le texte du champ de texte avec la nouvelle date sélectionnée
                        val formattedDate = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(selectedDate.time)
                        selectedDateText = formattedDate
                        // Assurez-vous d'utiliser un MutableState pour mettre à jour la valeur du champ de texte
                        // Par exemple : selectedDateText.value = formattedDate


                    }
                },
            isError = selectedDateText.isEmpty()
        )










        Button(modifier = Modifier.padding(top = 100.dp),

            onClick = {

                //verifier si ts les champs sont remplies
                if (selectedText.isNotEmpty() && titre.isNotEmpty() && description.isNotEmpty() && date.isNotEmpty() && unité.isNotEmpty() && isDescriptionValid) {
                val service = Service(selectedText, titre,unité,description,date)
                onSubmit(service)}
                else{
                    ErreurT = titre.isEmpty()
   //met à jour la variable ErreurT en vérifiant si
                    // la variable title (contenant la valeur du champ "Titre") est vide. Si le champ "Titre" est vide,
                    // ErreurT sera mis à true et donc je vais au condition:  (ErreurT && titre.isEmpty()) --> (True and True): entrer , sinon il sera mis à false


                    ErreurDes = description.isEmpty()
                    ErreurDate = date.isEmpty()
                    ErreurU = unité.isEmpty()


                  //  buttonColor = Color.Red


                }
            },
           // modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(50.dp),
            border = BorderStroke(width = 1.dp, color = Color(0xFF000000)),
            colors = ButtonDefaults.buttonColors(
             backgroundColor =
                 if (selectedText.isNotEmpty() && titre.isNotEmpty() && unité.isNotEmpty() && description.isNotEmpty() && date.isNotEmpty() && isDescriptionValid) {
                    // Couleur lorsque le bouton est activé et tous les champs sont remplis
                     Color.Green
                    // buttonColor

                } else {
                    // Couleur lorsque le bouton est désactivé (c'est-à-dire lorsque certains champs sont vides)
                Color.Red
                // buttonColor
                }

              //  backgroundColor = buttonColor

            ),
            enabled = selectedText.isNotEmpty() && titre.isNotEmpty() &&  unité.isNotEmpty() && description.isNotEmpty() && date.isNotEmpty() ,

        ) {
            Text("Ajouter le service", fontSize = 20.sp, color = Color.Black)
        }



    }
//afficher le texte sélectionné et un IconButton pour ouvrir ou fermer le menu déroulant. Le DropdownMenu affiche les suggestions lorsque le menu est ouvert, et lorsque l'utilisateur choisit une suggestion, le texte est mis à jour et le menu est fermé

    // Observer les changements dans enabled pour mettre à jour la couleur du bouton

}


/*
//Date version 1
private fun formatToDateString(input: String): String {
    val trimmedInput = input.filter { it.isDigit() }
    //input.filter { it.isDigit() } Cette ligne utilise la fonction filter pour conserver uniquement les caractères numériques de la chaîne input. Cela élimine tous les caractères non numériques, laissant seulement les chiffres.

//Cette instruction vérifie si la longueur de trimmedInput est inférieure ou égale à 2. Si c'est le cas, cela signifie que l'utilisateur est en train de saisir le jour de la date, et nous retournons simplement cette valeur (par exemple, "12", "03", etc.). Dans ce cas, nous n'avons pas encore besoin de formater la date complète, car l'utilisateur n'a pas encore entré le mois et l'année complets.
    if (trimmedInput.length <= 9) {
        return trimmedInput
    }
   //return " ${trimmedInput.take(2)}/${trimmedInput.drop(2).take(2)}/${trimmedInput.drop(4).take(4)} "
    return "${trimmedInput.substring(0, 2)}/${trimmedInput.substring(2, 4)}/${trimmedInput.substring(4)}"
}

*/


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



@Preview
@Composable
fun DropdownMenuExamplePreview() {
    Prestataire(onSubmit = {})
}