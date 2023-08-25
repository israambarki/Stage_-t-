package com.example.swipeimage.Data.datasource.Datasource

import android.net.Uri
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "Inscription")
data class InscriptionPresonne(

    @ColumnInfo(name = "pseudo")
    val pseudo: String,

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,


    @ColumnInfo(name = "nom_utilisateur")
    val nom: String,
    @ColumnInfo(name = "Prenom")
    var Prenom: String,



    @ColumnInfo(name = "naissance")
    val naissance: String,


    @ColumnInfo(name = "Email")
    var mail: String,
    @ColumnInfo(name = "numero_de_telephone")
    val phonenumb: String,

    @ColumnInfo(name = "mot_de_passe")
    var passwordun: String,

    val Sexe: String,

    @ColumnInfo(name="address")
    val address :String,

    @ColumnInfo(name="Type")
    val Type :String,

    @ColumnInfo(name = "photo")
    val photo : String? = null,
    //val Type
    // Ajoutez le champ photoUrl pour stocker l'URI de la photo de profil
    //val photoUrl: String? = null,
    @ColumnInfo(name = "Connection")
    var isConnected: Boolean = false // Nouvel attribut pour l'état de connexion


                               )





