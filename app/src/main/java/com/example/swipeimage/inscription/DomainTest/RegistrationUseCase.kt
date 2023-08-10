/*package com.example.swipeimage.inscription.DomainTest

import com.example.swipeimage.View.inscriptionconnecter.Inscri
import com.example.swipeimage.inscription.data.datasource.Datasource.InscriptionPresonne
import com.example.swipeimage.inscription.data.datasource.repository.PersonneRep

class RegistrationUseCase(private val userRepository: PersonneRep) {
    suspend fun registerUser(Email: String, password: String) {
        val newUser = InscriptionPresonne(id = 0, name = Email, passwordun = password)
        userRepository.insert(newUser)
    }
}*/
