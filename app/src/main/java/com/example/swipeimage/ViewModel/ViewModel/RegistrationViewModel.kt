package com.example.swipeimage.ViewModel.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.swipeimage.Data.datasource.Datasource.InscriptionPresonne
import com.example.swipeimage.Data.datasource.Datasource.PersonneDatabase
import com.example.swipeimage.inscription.data.datasource.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


class RegistrationViewModel(
    application: Application
) : AndroidViewModel(application) {

    // Obtenez une instance de PostDaw (votre DAO) en utilisant la fonction PresonneDAO()
    private val personneDao = PersonneDatabase.getInstance(application).PresonneDAO()
    private val repository = Repository(personneDao)

    // Méthode pour enregistrer un nouvel utilisateur
    fun registerUser(user: InscriptionPresonne) {
        viewModelScope.launch {
            val isUnique = repository.isEmailUnique(user.mail) // Vérifier si l'e-mail est unique
            if (isUnique) {
                repository.insertUser(user)
                // Conserver la connexion:
                repository.insertUser(user.copy(isConnected = true))
            } else {
                // L'e-mail n'est pas unique, vous pouvez afficher un message d'erreur ici si nécessaire
            }
        }
    }

   /* // Méthode pour vérifier si un utilisateur est connecté
    suspend fun isUserLoggedIn(email: String, password: String): Boolean {
        return repository.Verifier(email, password)
    }*/

   // Méthode pour mettre à jour l'état de connexion d'un utilisateur
    suspend fun updateConnectionStatus(email: String, password: String) {
        repository.updateConnectionStatus(email, password)
    }
    suspend fun isEmailUnique(email: String): Boolean {
        return repository.isEmailUnique(email)
    }
    suspend fun GetLastUserConnection(): InscriptionPresonne? {
        return repository.GetLastUserConnection()
    }


}