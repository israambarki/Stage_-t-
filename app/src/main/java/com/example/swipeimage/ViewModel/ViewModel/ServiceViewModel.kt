package com.example.swipeimage.ViewModel.ViewModel

import android.app.Application
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.swipeimage.View.Prestataire.AjoutService.ServiceBox
import androidx.compose.runtime.State
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.swipeimage.Data.datasource.Datasource.PersonneDatabase

import com.example.swipeimage.Data.remote.ApiClient
import com.example.swipeimage.Data.remote.CreateServiceRequest
import com.example.swipeimage.Data.remote.ServiceCategoryDTO

import com.example.swipeimage.inscription.data.datasource.repository.Repository
import kotlinx.coroutines.Dispatchers

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

import kotlinx.coroutines.launch
import java.util.UUID

class ServiceViewModel(application: Application ) : AndroidViewModel(application) {
    private val _services = mutableStateOf<List<ServiceBox>>(emptyList()) // la liste
    val services: State<List<ServiceBox>> = _services

    suspend fun addService(service: ServiceBox) {
        _services.value = _services.value + service
    }

    //les services
    // private val _servicesResponse = MutableStateFlow(ApiResponseService<List<Service>>(null, "", null))
    // val servicesResponse: StateFlow<ApiResponseService<List<Service>>> = _servicesResponse
    // Obtenez une instance de ApiService à partir de votre ApiClient
    private val apiService = ApiClient.createApiService()

    // Obtenez une instance de PostDaw (votre DAO) en utilisant la fonction PresonneDAO()
    private val personneDao = PersonneDatabase.getInstance(application).PresonneDAO()
    //private val repository = ServicesRepository()

    private val repository = Repository(personneDao, apiService)
   /* fun fetchServices() {
        viewModelScope.launch {
            _servicesResponse.value = ApiResponseService(null, "Loading...", null)
            val response = repository.getServices() // Assurez-vous d'appeler correctement votre fonction d'appel à l'API ici
            _servicesResponse.value = response
        }
    }*/

    /*   suspend fun getServicesNames(): Flow<List<String>> {
        return flow {
            val names = repository.getServicesNames()
            emit(names)
        }.flowOn(Dispatchers.IO)
    }*/



    private val _servicesNames = MutableStateFlow<List<String>>(emptyList())
    val servicesNames = _servicesNames

    init {
        refreshServices()
    }

    fun refreshServices() {
        viewModelScope.launch {
            try {
                val response = repository.getServices()
                if (response.isSuccessful) {
                    val serviceList = response.body()?.data ?: emptyList()
                    val serviceNames = serviceList.map { it.name }

                    _servicesNames.value = serviceNames
                }
            } catch (e: Exception) {
                e.printStackTrace()
                // Handle error appropriately
            }
        }
    }

    //service creation :



    fun createService(createServiceRequest: CreateServiceRequest) {
        viewModelScope.launch {
            repository.createService(createServiceRequest)
        }
    }



}






