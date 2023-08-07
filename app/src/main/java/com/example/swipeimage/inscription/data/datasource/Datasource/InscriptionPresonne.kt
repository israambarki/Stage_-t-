package com.example.swipeimage.inscription.data.datasource.Datasource

import android.provider.ContactsContract.CommonDataKinds.Email
import android.provider.ContactsContract.CommonDataKinds.Phone
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey


@Entity(tableName = "Inscription")
data class InscriptionPresonne(


    @PrimaryKey(autoGenerate = true)
    val id : Int = 0 ,

   // @ColumnInfo("nom_utilisateur")
     //                       val name : String,
    @ColumnInfo(name = "Email")
    var mail : String,
  //  @ColumnInfo("numero de telephone")
         // val phonenumb : String,
    @ColumnInfo(name = "mot_de_passe")
    var passwordun: String,
       //                        @Ignore
         //                      var confpass : String,

           //                    val Sexe : String,
             //                  val Type : String


                               )





