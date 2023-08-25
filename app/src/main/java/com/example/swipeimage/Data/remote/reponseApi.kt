package com.example.swipeimage.Data.remote



data class ApiResponse(val data: Any?, val message: String, val statusCode: String)


// Response.kt
data class ResponseReq(
    val data: String?,
    val message: String?,
    val statusCode: Int?,
    val roleName: String?
)

data class ApiResponseLogin<T>(
    val data: T? ,
    val message: String?,
    val statusCode:  Int?, // Utilisez HttpStatusCode à la place de String
    val roleName: String?
)











