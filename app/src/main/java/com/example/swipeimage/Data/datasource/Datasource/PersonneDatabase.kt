package com.example.swipeimage.Data.datasource.Datasource

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.swipeimage.Data.datasource.Datasource.InscriptionPresonne
import com.example.swipeimage.Data.datasource.Datasource.PostDaw
import dagger.hilt.android.HiltAndroidApp

//nous avons besoin de la base de donnee pour notre DAO
//configurez votre base de données :
//
//

@Database(entities =[InscriptionPresonne::class], version = 7)

abstract class PersonneDatabase : RoomDatabase(){

    //une focntion qui represente notre DAO:
//Cette fonction ne contient pas de corps (implémentation) dans cette classe ou interface, et elle renvoie un objet de type PostDaw.
    // vous obtenez l'instance du DAO (c'est-à-dire une instance qui implémente
    // l'interface PostDaw) que vous pouvez utiliser pour accéder aux fonctions du DAO.
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

    val MIGRATION_2_3 = object : Migration(2, 3) {
        override fun migrate(database: SupportSQLiteDatabase) {
            // Ajouter une colonne 'nom' à la table 'InscriptionPresonne'
            // Ajouter les colonnes pour le sexe, le numéro de téléphone et l'URL de la photo
            database.execSQL("ALTER TABLE Inscription ADD COLUMN nom_utilisateur TEXT NOT NULL DEFAULT ''")
            database.execSQL("ALTER TABLE Inscription ADD COLUMN Sexe TEXT NOT NULL DEFAULT 'Homme'")
            database.execSQL("ALTER TABLE Inscription ADD COLUMN numero_de_telephone TEXT NOT NULL DEFAULT ''")

        }
    }
    val MIGRATION_3_4 = object : Migration(3, 4) {
        override fun migrate(database: SupportSQLiteDatabase) {

            database.execSQL("ALTER TABLE Inscription ADD COLUMN photo TEXT NOT NULL DEFAULT ''")
        }
    }


    val MIGRATION_4_5 = object : Migration(4, 5) {
        override fun migrate(database: SupportSQLiteDatabase) {

            database.execSQL("ALTER TABLE Inscription ADD COLUMN address TEXT NOT NULL DEFAULT ''")
        }
    }

    val MIGRATION_5_6 = object : Migration(5, 6) {
        override fun migrate(database: SupportSQLiteDatabase) {

            database.execSQL("ALTER TABLE Inscription ADD COLUMN pseudo TEXT NOT NULL DEFAULT ''")
            database.execSQL("ALTER TABLE Inscription ADD COLUMN Prenom TEXT NOT NULL DEFAULT ''")
            database.execSQL("ALTER TABLE Inscription ADD COLUMN naissance TEXT NOT NULL DEFAULT ''")
        }
    }

    val MIGRATION_6_7 = object : Migration(6, 7) {
        override fun migrate(database: SupportSQLiteDatabase) {

            database.execSQL("ALTER TABLE Inscription ADD COLUMN Type TEXT NOT NULL DEFAULT ''")
        }
    }



   /* val MIGRATION_1_3 = object : Migration(1, 3) {
        override fun migrate(database: SupportSQLiteDatabase) {
            // Ajouter les colonnes pour le sexe, le numéro de téléphone et l'URL de la photo
            database.execSQL("ALTER TABLE Inscription ADD COLUMN nom_utilisateur TEXT NOT NULL DEFAULT ''")
            database.execSQL("ALTER TABLE Inscription ADD COLUMN Sexe TEXT NOT NULL DEFAULT ''")
            database.execSQL("ALTER TABLE Inscription ADD COLUMN numero_de_telephone TEXT NOT NULL DEFAULT ''")
          //  database.execSQL("ALTER TABLE Inscription ADD COLUMN photoUrl TEXT")
        }
    }*/




    fun getInstance(context: Context): PersonneDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    PersonneDatabase::class.java,
                    "InscriptionPresonne.db"
                )
                    .addMigrations(MIGRATION_1_2,MIGRATION_2_3,MIGRATION_3_4,MIGRATION_4_5,MIGRATION_5_6,MIGRATION_6_7)

                    .build()
                INSTANCE = instance
                instance
            }
        }
    }

}