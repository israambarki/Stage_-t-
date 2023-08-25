package com.example.swipeimage.ViewModel.ViewModel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.swipeimage.Data.datasource.Datasource.InscriptionPresonne
import com.example.swipeimage.Data.datasource.Datasource.PersonneDatabase
import com.example.swipeimage.Data.remote.ApiClient
import com.example.swipeimage.Data.remote.ApiClient.createApiService
import com.example.swipeimage.Data.remote.ApiResponse
import com.example.swipeimage.Data.remote.ApiService
import com.example.swipeimage.Data.remote.InscriptionRequest

import com.example.swipeimage.inscription.data.datasource.repository.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.util.UUID
import javax.inject.Inject


class RegistrationViewModel(
    application: Application
) : AndroidViewModel(application) {


    private val _registrationStatus = MutableStateFlow<ApiResponse?>(null)
    val registrationStatus: StateFlow<ApiResponse?> = _registrationStatus

    // Obtenez une instance de ApiService à partir de votre ApiClient
    private val apiService = createApiService()

    // Obtenez une instance de PostDaw (votre DAO) en utilisant la fonction PresonneDAO()
    private val personneDao = PersonneDatabase.getInstance(application).PresonneDAO()
    private val repository = Repository(personneDao, apiService)

    // Méthode pour enregistrer un nouvel utilisateur
    fun registerUser(user: InscriptionPresonne) {
        viewModelScope.launch {
            val isUnique = repository.isEmailUnique(user.mail) // Vérifier si l'e-mail est unique
            if (isUnique) {
                //   repository.insertUser(user)
                // Conserver la connexion:
                //modif pour chouf eviter duplication:
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
    suspend fun updateConnectionStatus(email: String, password: String, isConnected: Boolean) {
        repository.updateConnectionStatus(email, password, isConnected)
    }

    suspend fun isEmailUnique(email: String): Boolean {
        return repository.isEmailUnique(email)
    }

    suspend fun GetLastUserConnection(): InscriptionPresonne? {
        return repository.GetLastUserConnection()
    }

    suspend fun GetUserConnection(): InscriptionPresonne? {
        return repository.GetUserConnection()
    }


    suspend fun logout() {
        val lastConnection = GetLastUserConnection()
        if (lastConnection?.isConnected == true) {
            repository.updateConnectionStatus(lastConnection.mail, lastConnection.passwordun, false)
        }
    }

    //surpprimer l'utilisateur:
    suspend fun logoutAndDeleteUser() {
        val connectedUser = GetLastUserConnection()
        if (connectedUser != null && connectedUser?.isConnected == true) {
            repository.updateConnectionStatus(connectedUser.mail, connectedUser.passwordun, false)
            repository.deleteUser(connectedUser)
        }
    }

    //va pas dependre de lastconnection :
    suspend fun Deconnecter() {
        repository.deconnecter()
    }

    suspend fun updatePhotoUrlForConnectedUser(photoUrl: String) {
        val connectedUser = repository.GetUserConnection()

        connectedUser?.let { user ->
            val updatedUser = user.copy(photo = photoUrl)
            repository.updateUser(updatedUser)
        }
    }


    suspend fun Delete() {
        repository.deleteAll()
    }


    //suspend fun updateUser(newPhotoUrl: String) {
    //  repository.updateUser(newPhotoUrl)
    //}


    ///// APIIIII:
    fun registerUser(
        pseudo: String,
        nom: String,
        prenom: String,
        password: String,
        phoneNumber: String,
        mail: String,
        adress: String,
        sex: String,
        isActive : Boolean,
        roleId : UUID
    ) {

            val request = InscriptionRequest(
                pseudo,
                nom,
                prenom,
                password,
                phoneNumber,
                mail,
                adress,
                sex,
                isActive,
                roleId
            )
        GlobalScope.launch(Dispatchers.IO){

            try {
                val Rep = repository.registerUser(request)
                _registrationStatus.value = Rep
            }catch (e: Exception) {
                // Traitez la réponse :
                Log.e("TAG", "Une erreur s'est produite : ${e.message}")
            }
            Log.e("TAG", "Une erreur s'est produite: ${request}")
        }
       /* viewModelScope.launch {
            try {
                val Rep = repository.registerUser(request)
                _registrationStatus.value = Rep
            }catch (e: Exception) {
                // Traitez la réponse :
                e.printStackTrace()
                Log.e("TAG", "Une erreur s'est produite : ${e.message}")
            }
        }*/
    }
}


