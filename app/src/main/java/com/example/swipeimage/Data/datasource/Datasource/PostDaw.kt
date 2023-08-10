package com.example.swipeimage.Data.datasource.Datasource

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow




//PostDaw qui définit les méthodes d'accès à la base de données
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

 //@Query("SELECT * FROM Inscription WHERE nom_utilisateur = :nom_utilisateur ")
  //fun GetNom(nom_utilisateur: String)


  //GetUserEmail vous permet de récupérer un utilisateur en fonction de son :Email.
    @Query("SELECT * FROM Inscription WHERE Email = :Email ")
    fun GetUserEmail(Email: String): InscriptionPresonne?


    // ca me permet de verifier est ce que le mail est unique ou non : donc il va compter le nombre de foi que le mail entré existe dans la base de donnee et il va faire une condition
    // en effet il va comparer le  count(*) a 0 s'ils sont egaux donc ce mail n'existe pas dans la BD
    //sinon il va me donner une erreur.
    @Query("SELECT COUNT(*) FROM Inscription WHERE Email = :email")
    suspend fun countUsersWithEmail(email: String): Int

    // Vérifier si un utilisateur est connecté en fonction de son e-mail et de son mot de passe
    @Query("SELECT * FROM Inscription WHERE Email = :email AND mot_de_passe = :password AND Connection = 1")
    suspend fun VerifierU(email: String, password: String): InscriptionPresonne?

   @Query("UPDATE Inscription SET Connection = :isConnected WHERE Email = :email AND mot_de_passe = :password")
    suspend fun UpdateConn(email: String, password: String, isConnected: Boolean)

    //récupérer l'utilisateur avec la dernière connexion (lim 1 ) , un seul -- avec le plus grand id ( le dernier)!
    @Query("SELECT * FROM Inscription ORDER BY id DESC LIMIT 1")
    suspend fun getLastLoggedInUser(): InscriptionPresonne?


}

