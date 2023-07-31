package com.example.swipeimage.accueil.repository

import com.example.swipeimage.inscription.InscriptionPresonne
import com.example.swipeimage.inscription.PostDaw
import javax.inject.Inject

class PersonneRep @Inject constructor(private val dao : PostDaw){

suspend fun insert(personne : InscriptionPresonne) = dao.InsertPresonne(personne)

suspend fun delete (personne : InscriptionPresonne) = dao.updatePresonne(personne)

fun getAll() = dao.GetInfoInscription()

    fun getName(name : String) = dao.GetNom(name)


}