/*package com.example.swipeimage.inscription.DomainTest

import com.example.swipeimage.inscription.data.datasource.repository.PersonneRep

class LoginUseCase (private val userRepository: PersonneRep) {
    suspend fun loginUser(Email: String, password: String): Boolean {
        val user = userRepository.getFromEmail(Email)
        return user != null && user.passwordun == password
    }
}/*/