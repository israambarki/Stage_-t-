package com.example.swipeimage.inscription

import android.provider.ContactsContract.CommonDataKinds.Email
import android.provider.ContactsContract.CommonDataKinds.Phone
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey


@Entity(tableName = "Inscription")
data class InscriptionPresonne(

@ColumnInfo("nom_utilisateur")
                               val name : String,
                               @PrimaryKey
                               val mail : String,
                               @PrimaryKey
                               val phonenumb : String,

                               var passwordun: String,
                               @Ignore
                               var confpass : String,

                               val Sexe : String,
                               val Type : String


                               )





