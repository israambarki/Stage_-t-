package com.example.swipeimage.View.Prestataire.AjoutService

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.swipeimage.ViewModel.ViewModel.ServiceViewModel

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ServiceListPage(viewModel: ServiceViewModel,navController: NavController) {


    // State for managing the visibility of the "Add Service" dialog
    var isAddServiceDialogVisible by remember { mutableStateOf(false) }



    val services = viewModel.services.value

    Scaffold(

        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF0E4C3)),

        topBar = {
            TopAppBar(
                title = { Text("Services disponibles") }
            )
        }
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(16.dp)
        ) {
            items(services) { service ->
                ServiceItem(service)
                Divider()
            }
        }
        // boutton flotante:


        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.BottomEnd
        ){
        Column(
            modifier = Modifier.padding(16.dp),
        ) {
            // Your list of services here
            // Floating Action Button to add a new service
            FloatingActionButton(
                onClick = { isAddServiceDialogVisible = true },
                content = { Icon(imageVector = Icons.Default.Add, contentDescription = "Add Service") }
            )
        }}

        // Dialog to add a new service
        if (isAddServiceDialogVisible) {
            AlertDialog(
                onDismissRequest = { isAddServiceDialogVisible = false },
                title = { Text(text = "Ajouter Service") },
                confirmButton = {
                    Button(
                        onClick = {
                            navController.navigate("PrestataireService")
                            // Handle adding the new service here --->: to access the entered values
                            isAddServiceDialogVisible = false
                        }
                    ) {
                        Text(text = "Ajouter")
                    }
                },
                dismissButton = {
                    Button(
                        onClick = { isAddServiceDialogVisible = false }
                    ) {
                        Text(text = "Annuler")
                    }
                },
              /*  text = {
                    // Content of the dialog to add a new service
                   Column {
                        TextField(
                            value = newServiceTitle,
                            onValueChange = { newServiceTitle = it },
                            label = { Text("Title") }
                        )
                        TextField(
                            value = newServiceDescription,
                            onValueChange = { newServiceDescription = it },
                            label = { Text("Description") }
                        )
                        TextField(
                            value = newServiceUnit,
                            onValueChange = { newServiceUnit = it },
                            label = { Text("Unit") }
                        )
                    }
                }*/
            )
        }






    }
}

@Composable
fun ServiceItem(service: ServiceBox) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = 4.dp
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(text = "Catégorie : ${service.selectedText}", style = TextStyle(
                color = Color(0xFF070707),
                textAlign = TextAlign.Start ))
            Text(text = "Titre : ${service.titre}",style = TextStyle(
                color = Color.Black,
                textAlign = TextAlign.Start, fontWeight = FontWeight.Bold)
            )
            Text(text = "Description : ${service.description}",style = TextStyle(
                color = Color.Black,
                textAlign = TextAlign.Start))
            Text(text = "Unité : ${service.unité}",style = TextStyle(
                color = Color.Black,
                textAlign = TextAlign.End, fontWeight = FontWeight.Bold,))
            Text(text = "Date : ${service.selectedDateText}",style = TextStyle(
                color = Color.Black,
                textAlign = TextAlign.End, fontWeight = FontWeight.Bold))
        }
    }
}