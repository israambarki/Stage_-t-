/*package com.example.swipeimage.inscription.data.datasource.repository

import com.example.swipeimage.inscription.data.datasource.Datasource.InscriptionPresonne
import com.example.swipeimage.inscription.data.datasource.Datasource.PostDaw
import javax.inject.Inject

//class PersonneRep @Inject constructor(private val dao : PostDaw){
class PersonneRep (private val dao : PostDaw){


suspend fun insert(personne : InscriptionPresonne) = dao.InsertPresonne(personne)

suspend fun delete (personne : InscriptionPresonne) = dao.updatePresonne(personne)

fun getAll() = dao.GetInfoInscription()

   // fun getName(name : String) = dao.GetNom(name)

    fun getFromEmail(Email : String) = dao.GetUserEmail(Email)


}
*/