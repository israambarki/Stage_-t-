package com.example.swipeimage.inscription.ViewModel

import android.app.Application
import android.widget.Toast
import androidx.compose.material3.Text
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

import androidx.compose.runtime.*
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.swipeimage.inscription.data.datasource.Datasource.InscriptionPresonne
import com.example.swipeimage.inscription.data.datasource.Datasource.PersonneDatabase
import kotlinx.coroutines.launch

/*class RegistrationViewModel(private val registrationUseCase: RegistrationUseCase) : ViewModel() {
    val Email = mutableStateOf("")
    val password = mutableStateOf("")

    suspend fun registerUser() {
        registrationUseCase.registerUser(Email.value, password.value)
    }
}*/

class RegistrationViewModel(application: Application) : AndroidViewModel(application) {
    private val userDao = PersonneDatabase.getInstance(application).PresonneDAO()


    //mail unique ?

    suspend fun isEmailUnique(email: String): Boolean {
        val count = userDao.countUsersWithEmail(email)
        return count == 0
    }


    fun registerUser(user: InscriptionPresonne) {
        viewModelScope.launch {
            val isUnique = isEmailUnique(user.mail) // Vérifier si l'e-mail est unique
            if (isUnique) {
                userDao.InsertPresonne(user)
            }else {
                // L'e-mail n'est pas unique, vous pouvez afficher un message d'erreur ici si nécessaire
            }
        }
    }
}