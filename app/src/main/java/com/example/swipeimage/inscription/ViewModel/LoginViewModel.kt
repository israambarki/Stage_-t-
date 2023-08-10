/*package com.example.swipeimage.inscription.ViewModel

import android.app.Application
import android.provider.ContactsContract.CommonDataKinds.Email
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel

import com.example.swipeimage.Data.datasource.Datasource.PersonneDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/*class LoginViewModel(private val loginUseCase: LoginUseCase) : ViewModel() {
    val Email = mutableStateOf("")
    val password = mutableStateOf("")
    val loginStatus = mutableStateOf(false)

    suspend fun loginUser() {
        val isLoggedIn = loginUseCase.loginUser(Email.value, password.value)
        loginStatus.value = isLoggedIn
    }
}*/

class LoginViewModel(application: Application) : AndroidViewModel(application) {
    private val userDao = PersonneDatabase.getInstance(application).PresonneDAO()

     suspend fun loginUser(email: String, password: String): Boolean {

         return withContext(Dispatchers.IO) {
             val user = userDao.GetUserEmail(email)
             //return
             user != null && user.passwordun == password
         }
     }
}*/
