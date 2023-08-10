package com.example.swipeimage.ViewModel.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.swipeimage.Data.datasource.Datasource.PersonneDatabase
import com.example.swipeimage.inscription.data.datasource.repository.Repository

class LoginViewModel(application: Application) : AndroidViewModel(application) {


    // Obtenez une instance de PostDaw (votre DAO) en utilisant la fonction PresonneDAO()
    //Cela signifie que vous avez accès aux méthodes définies dans votre DAO,

    private val userDao = PersonneDatabase.getInstance(application).PresonneDAO()
    private val userRepository = Repository(userDao)

    suspend fun loginUser(email: String, password: String): Boolean {
        return userRepository.loginUser(email, password)
    }
}