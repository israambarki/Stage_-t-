package com.example.swipeimage.accueil

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.swipeimage.Sliding
import com.example.swipeimage.a_propos.Apropos
import com.example.swipeimage.inscription.Inscription
import com.google.accompanist.pager.ExperimentalPagerApi

@OptIn(ExperimentalPagerApi::class)
@Composable
fun Nav(){


    val navcontroller = rememberNavController()

    NavHost(navController = navcontroller, startDestination ="sliding"){

        composable("Accueil")

            {
            accueil(navcontroller)

        }

        composable("Inscription")

        {
            Inscription(navcontroller)

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
