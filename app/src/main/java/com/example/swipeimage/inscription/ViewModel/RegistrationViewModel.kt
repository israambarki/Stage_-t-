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


    suspend fun GetLastUserConnection(): InscriptionPresonne? {
        return userDao.getLastLoggedInUser()
    }



    // Vérifier si un utilisateur est connecté en utilisant l'e-mail et le mot de passe
    suspend fun Verifier(email: String, password: String): Boolean {
        val loggedInUser = userDao.VerifierU(email, password)
        return loggedInUser != null
    }

    // Mettre à jour l'état de connexion de l'utilisateur après l'inscription
    suspend fun updateConnectionStatus(email: String, password: String) {
        userDao.UpdateConn(email, password, true)
    }

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
//conserver la connexion:
                userDao.InsertPresonne(user.copy(isConnected = true)) // Mettre à jour l'état de connexion
            }else {
                // L'e-mail n'est pas unique, vous pouvez afficher un message d'erreur ici si nécessaire
            }
        }
    }
}