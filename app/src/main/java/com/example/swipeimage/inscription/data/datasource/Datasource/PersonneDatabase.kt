package com.example.swipeimage.inscription.data.datasource.Datasource

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.swipeimage.inscription.data.datasource.Datasource.InscriptionPresonne
import com.example.swipeimage.inscription.data.datasource.Datasource.PostDaw

//nous avons besoin de la base de donnee pour notre DAO
//configurez votre base de données :
//
//
@Database(entities =[InscriptionPresonne::class], version = 2)
abstract class PersonneDatabase : RoomDatabase(){

    //une focntion qui represente notre DAO:

abstract  fun PresonneDAO(): PostDaw

///
    companion object {
        private var INSTANCE: PersonneDatabase? = null

    // j'ai ajouté apres une colonne :
   val MIGRATION_1_2 = object : Migration(1, 2) {
        override fun migrate(database: SupportSQLiteDatabase) {
            // Ajouter une colonne 'nom' à la table 'InscriptionPresonne'
            database.execSQL("ALTER TABLE Inscription ADD COLUMN Connection INTEGER NOT NULL DEFAULT 0")
        }
    }

        fun getInstance(context: Context): PersonneDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    PersonneDatabase::class.java,
                    "InscriptionPresonne.db"
                )
                    .addMigrations(MIGRATION_1_2)
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }

}