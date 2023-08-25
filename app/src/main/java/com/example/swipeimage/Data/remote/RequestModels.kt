package com.example.swipeimage.Data.remote

import com.google.gson.annotations.SerializedName
import retrofit2.http.Body
import retrofit2.http.POST
import java.time.LocalDateTime
import java.util.UUID


data class Role(
     val id: String,
     val name: String
)


data class InscriptionRequest(
    @SerializedName("pseudo") val pseudo: String,
    @SerializedName("firstName") val nom: String,
    @SerializedName("lastName") val Prenom: String,
    @SerializedName("password") val passwordun: String,
    @SerializedName("phoneNumber") val phonenumb: String,
    @SerializedName("mail") val mail: String,
    @SerializedName("adress") val Address: String,
    @SerializedName("sex") val Sex: String,
    @SerializedName("isActive") val isActive: Boolean,
    @SerializedName("roleId") val roleId : UUID
)


data class ConnexionRequest(
    @SerializedName("login")   val email: String,
    @SerializedName("password") val password: String)

//service :



data class AllServiceCategoryResponse(
    val data: List<ServiceNames>,
    val message: String,
    val statusCode: String
)

data class ServiceNames(
    @SerializedName("id")  val id: UUID,
    @SerializedName("name")   val name: String
)


data class ServiceCategoryDTO(
    val id: UUID,
    val name: String )



data class CreateServiceRequest(

    @SerializedName("title") val user: GetUserDTO,
    @SerializedName("title")   val title: String,
    @SerializedName("description") val description: String,
    @SerializedName("serviceCategory") var serviceCategory: UUID,
    @SerializedName("price") val price: Float
)

data class GetUserDTO(

    @SerializedName("pseudo") val pseudo: String,
    @SerializedName("firstName") val nom: String,
    @SerializedName("lastName") val Prenom: String,
    @SerializedName("password") val passwordun: String,
    @SerializedName("phoneNumber") val phonenumb: String,
    @SerializedName("mail") val mail: String,
    @SerializedName("adress") val Address: String,
    @SerializedName("sex") val Sex: String,
    @SerializedName("isActive") val isActive: Boolean,
    @SerializedName("roleId") val roleId : UUID

)

