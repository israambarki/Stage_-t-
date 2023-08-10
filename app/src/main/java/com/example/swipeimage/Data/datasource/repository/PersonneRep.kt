package com.example.swipeimage.inscription.data.datasource.repository

import com.example.swipeimage.Data.datasource.Datasource.InscriptionPresonne
import com.example.swipeimage.Data.datasource.Datasource.PersonneDatabase
import com.example.swipeimage.Data.datasource.Datasource.PostDaw
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class Repository (private val personneDao: PostDaw) {

    suspend fun insertUser(user: InscriptionPresonne) {
        personneDao.InsertPresonne(user)
    }

    suspend fun GetLastUserConnection(): InscriptionPresonne? {
        return personneDao.getLastLoggedInUser()
    }

    // Vérifier si un utilisateur est connecté en utilisant l'e-mail et le mot de passe
    suspend fun Verifier(email: String, password: String): Boolean {
        val loggedInUser = personneDao.VerifierU(email, password)
        return loggedInUser != null
    }

    suspend fun isUserLoggedIn(email: String, password: String): Boolean {
        return personneDao.VerifierU(email, password) != null
    }

    /*fun getUserDetails(email: String): Flow<InscriptionPresonne> {
        return personneDao.GetUserEmail(email)
    }*/

    // Mettre à jour l'état de connexion de l'utilisateur après l'inscription
    suspend fun updateConnectionStatus(email: String, password: String) {
        personneDao.UpdateConn(email, password, true)
    }

    //mail unique ?
    suspend fun isEmailUnique(email: String): Boolean {
        val count = personneDao.countUsersWithEmail(email)
        return count == 0
    }




    suspend fun loginUser(email: String, password: String): Boolean {

        return withContext(Dispatchers.IO) {
            val user = personneDao.GetUserEmail(email)
            //return
            user != null && user.passwordun == password
        }
    }

}
