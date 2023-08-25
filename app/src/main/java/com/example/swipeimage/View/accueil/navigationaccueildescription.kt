package com.example.swipeimage.View.accueil

import android.app.Application
import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.swipeimage.ViewModel.ViewModel.RegistrationViewModel
import com.example.swipeimage.View.Prestataire.AjoutService.Prestataire
import com.example.swipeimage.View.Prestataire.AjoutService.ServiceListPage
import com.example.swipeimage.View.Profil.UserProfileScreen

import com.example.swipeimage.View.slider.Sliding
import com.example.swipeimage.View.a_propos.Apropos
import com.example.swipeimage.View.inscriptionconnecter.Inscription
import com.example.swipeimage.View.inscriptionconnecter.LoginView
import com.example.swipeimage.ViewModel.ViewModel.LoginViewModel
import com.example.swipeimage.ViewModel.ViewModel.ServiceViewModel


import com.google.accompanist.pager.ExperimentalPagerApi

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalPagerApi::class)
@Composable
fun Nav(){


    val navcontroller = rememberNavController()
   val ViewModel: RegistrationViewModel = viewModel()
    
    val ViewModell: LoginViewModel = viewModel()

    val ViewModelS: ServiceViewModel = viewModel()
    val context = LocalContext.current

    NavHost(navController = navcontroller, startDestination ="sliding"){

        composable("Accueil")

            {
            accueil(navcontroller,viewModel = ViewModel)

        }

        composable("Inscription")

        {
            Inscription(navcontroller, viewModel = ViewModel){

             navcontroller.navigate("Accueil")    //hethi n3awthoha bil profil ...

               }

        }



       composable("Login") {
            LoginView(navcontroller,viewModel = ViewModell){
                navcontroller.navigate("Accueil")

            }
        }

        composable("PrestataireService"){
            Prestataire(onSubmit = {
                navcontroller.navigate("Services")
            },ViewModelS)
        }


        // services
        composable("Services"){
            ServiceListPage(ViewModelS,navcontroller)
        }



        composable("Profil_Prestataire"){
            UserProfileScreen(navcontroller,viewModel= ViewModel)

        }

        composable("Profil_Client"){
           // UserProfileScreen(navcontroller,viewModel= ViewModel)

            Toast.makeText(
                context,
                "Vous etes un Client",
                Toast.LENGTH_SHORT
            ).show()

        }



        composable("A_Propos")

        {
            Apropos()

        }






        composable("Description/{image}/{description}",
            arguments = listOf(
                navArgument(name = "image"){
                    type = NavType.IntType
                },
                navArgument(name = "description"){
                    type = NavType.StringType
                }


            )){


           // it.arguments?.let { it1 -> description(image = it1.getInt("image")) }
            val imageId = it.arguments?.getInt("image")

            if (imageId != null) {
                description(image = imageId,

                    description = it.arguments?.getString("description"))
            } else {
                // Gérer le cas où l'argument "image" est manquant ou n'est pas de type Int
            }
        }



        composable("sliding") { Sliding(navcontroller) }

    }



}
