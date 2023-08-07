package com.example.swipeimage.inscription.data.datasource.Datasource

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.swipeimage.inscription.data.datasource.Datasource.InscriptionPresonne
import com.example.swipeimage.inscription.data.datasource.Datasource.PostDaw

//nous avons besoin de la base de donnee pour notre DAO
//configurez votre base de données :
//
//
@Database(entities =[InscriptionPresonne::class], version = 1)
abstract class PersonneDatabase : RoomDatabase(){

    //une focntion qui represente notre DAO:

abstract  fun PresonneDAO(): PostDaw

///
    companion object {
        private var INSTANCE: PersonneDatabase? = null

        fun getInstance(context: Context): PersonneDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    PersonneDatabase::class.java,
                    "InscriptionPresonne.db"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }




}