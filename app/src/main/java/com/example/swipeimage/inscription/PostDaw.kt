package com.example.swipeimage.inscription

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow


@Dao
interface PostDaw {

    @Insert
    //mettre les donnees :
suspend fun InsertPresonne(Presonne: InscriptionPresonne);


    @Update
    suspend fun updatePresonne(Presonne: InscriptionPresonne)

    //recuperer les donnees
    @Query("select * from Inscription")
    //prendre les donnees:
   fun GetInfoInscription() : Flow<List<InscriptionPresonne>>

 @Query("SELECT * FROM Inscription WHERE nom_utilisateur = :nom_utilisateur ")
  fun GetNom(nom_utilisateur: String)
}

