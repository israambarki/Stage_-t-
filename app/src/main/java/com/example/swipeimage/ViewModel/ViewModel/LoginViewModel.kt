package com.example.swipeimage.ViewModel.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.swipeimage.Data.datasource.Datasource.PersonneDatabase
import com.example.swipeimage.Data.remote.ApiClient
import com.example.swipeimage.Data.remote.ApiResponse
import com.example.swipeimage.Data.remote.ApiResponseLogin
import com.example.swipeimage.Data.remote.ConnexionRequest
import com.example.swipeimage.Data.remote.ResponseReq
import com.example.swipeimage.inscription.data.datasource.repository.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class LoginViewModel(application: Application) : AndroidViewModel(application) {


    // Obtenez une instance de ApiService à partir de votre ApiClient
    private val apiService = ApiClient.createApiService()
    val _loginResponse = MutableStateFlow(ApiResponseLogin<ResponseReq>(null, null, null, null))
    val loginResponse: StateFlow<ApiResponseLogin<ResponseReq>> = _loginResponse






    // Obtenez une instance de PostDaw (votre DAO) en utilisant la fonction PresonneDAO()
    //Cela signifie que vous avez accès aux méthodes définies dans votre DAO,

    private val userDao = PersonneDatabase.getInstance(application).PresonneDAO()
    private val userRepository = Repository(userDao,apiService)

    suspend fun loginUser(email: String, password: String): Boolean {
        return userRepository.loginUser(email, password)
    }
    //remetre a jour isconnected a true
    suspend fun updateConnectionStatus(email: String, password: String,isConnected : Boolean) {
        userRepository.updateConnectionStatus(email, password,isConnected)
    }



    //Consommation des API:
    suspend fun ApiLogin(email: String, password: String) {


            viewModelScope.launch {
                _loginResponse.value = ApiResponseLogin(null, "Loading...", null, null)
                val request = ConnexionRequest(email, password)
                val response = userRepository.ApiLogin(request)
                _loginResponse.value = response
            }
       // return userRepository.ApiLogin(request)
    }
}