package com.example.swipeimage.Data.remote


import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.*


    interface ApiService {
        @POST("User/CreateUser") // Remplacez "inscription" par le chemin de votre endpoint
        suspend fun registerUser(@Body request: InscriptionRequest):ApiResponse

        @POST("User/AuthenticatePerson")
        suspend fun loginUser(@Body request: ConnexionRequest): Response<ResponseReq>

        //service:

            @GET("api/ServiceCategory/GetAllServiceCategory")
            suspend fun getServices(): Response<AllServiceCategoryResponse>


        @POST("api/Service/CreateService")
        suspend fun createService(@Body createServiceRequest: CreateServiceRequest)

    }




