package com.example.swipeimage.inscription.data.datasource.repository

import com.example.swipeimage.Data.datasource.Datasource.InscriptionPresonne
import com.example.swipeimage.Data.datasource.Datasource.PostDaw
import com.example.swipeimage.Data.remote.AllServiceCategoryResponse
import com.example.swipeimage.Data.remote.ApiResponse
import com.example.swipeimage.Data.remote.ApiResponseLogin

import com.example.swipeimage.Data.remote.ApiService
import com.example.swipeimage.Data.remote.ConnexionRequest
import com.example.swipeimage.Data.remote.CreateServiceRequest
import com.example.swipeimage.Data.remote.InscriptionRequest
import com.example.swipeimage.Data.remote.ResponseReq

//import com.example.swipeimage.Data.remote.Response
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import java.lang.Exception


class Repository (private val personneDao: PostDaw,private val apiService: ApiService) {


    suspend fun insertUser(user: InscriptionPresonne) {
        personneDao.InsertPresonne(user)
    }

    suspend fun GetLastUserConnection(): InscriptionPresonne? {
        return personneDao.getLastLoggedInUser()
    }

    suspend fun GetUserConnection(): InscriptionPresonne? {
        return personneDao.GetUserConnection()
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
    suspend fun updateConnectionStatus(email: String, password: String, isConnected: Boolean) {
        personneDao.UpdateConn(email, password, isConnected)
    }


    /*suspend fun updateConnectionStatusDeconn(email: String, password: String) {
        personneDao.UpdateConn(email, password, false)
    }*/

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

    //surpprimer l'utilsateur :
    suspend fun deleteUser(user: InscriptionPresonne) {
        personneDao.deleteUser(user)
    }

    suspend fun deleteAll() {
        personneDao.SetId()
        personneDao.deleteAll()
    }

    suspend fun deconnecter() {

        personneDao.deconnecter()

    }


    suspend fun updateUser(user: InscriptionPresonne) {
        personneDao.updateUser(user)
    }

    //   suspend fun updateUser(newPhotoUrl: String) {
    //     personneDao.updateConnectedUserPhotoUrl(newPhotoUrl)
    //}


    /////Gestion des API:
    suspend fun registerUser(request: InscriptionRequest): ApiResponse {
        return apiService.registerUser(request)
    }

    suspend fun ApiLogin(request: ConnexionRequest): ApiResponseLogin<ResponseReq> {


        val request = ConnexionRequest(request.email,request.password)
        //  val response = apiService.loginUser(request)

        // Handle the API response and create ApiResponse object
        //200 indique une réponse réussie, tandis que d'autres codes indiquent des erreurs spécifiques
        //(par exemple, 400 pour une mauvaise requête, 401 pour une authentification invalide

        return try {
            val response: Response<ResponseReq> = apiService.loginUser(request)
            if (response.isSuccessful) {
                val responseBody: ResponseReq? = response.body()
                ApiResponseLogin(
                    responseBody,
                    response.message(),
                    response.code(),
                    responseBody?.roleName
                )
            } else {
                ApiResponseLogin(null, response.message(), response.code(), null)
            }
        } catch (e: Exception) {
            ApiResponseLogin(null, e.message, null, null)
        }

    }

    //afficher ts les services :
   /* suspend fun getServices(): ApiResponseService<List<Service>> {
      /*  return try {
            val response = apiService.getServices()
            if (response.isSuccessful) {
                ApiResponseService(response.body(), response.message(), response.code())
            } else {
                ApiResponseService(null, response.message(), response.code())
            }
        } catch (e: Exception) {
            ApiResponseService(null, e.message, null)

   }*/
        return try {
            val response = apiService.getServices()
            if (response.isSuccessful) {
                val services = response.body()?.map { serviceResponse ->
                    Service(UUID.fromString(serviceResponse.id.toString()), serviceResponse.name)
                }
                ApiResponseService.success(services)
            } else {
                ApiResponseService.error(response.message(), null)
            }
        } catch (e: Exception) {
            ApiResponseService.error(e.message ?: "Erreur lors de la récupération des services", null)
        }

    }*/

    suspend fun getServices(): Response<AllServiceCategoryResponse> {
        return apiService.getServices()
    }
//creer service :
suspend fun createService(createServiceRequest: CreateServiceRequest) {
    // Appelez l'API pour créer le service en utilisant createServiceRequest
    // Assurez-vous d'envoyer les données appropriées à l'API
    apiService.createService(createServiceRequest)
}


}