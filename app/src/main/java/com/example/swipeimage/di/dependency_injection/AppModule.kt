/*package com.example.swipeimage.di.dependency_injection

import android.content.Context
import androidx.room.Room
import com.example.swipeimage.Data.datasource.Datasource.PersonneDatabase
import com.example.swipeimage.Data.datasource.Datasource.PostDaw
import com.example.swipeimage.inscription.data.datasource.repository.Repository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
//singleton
object AppModule {


    //module pour fournir BD
    @Singleton
    @Provides
    fun providerDB(@ApplicationContext context: Context): PersonneDatabase {

        return Room.databaseBuilder(
            context.applicationContext,
            PersonneDatabase::class.java,
            "InscriptionPresonne.db"
        )
            .addMigrations(PersonneDatabase.MIGRATION_1_2) // Ajoutez vos migrations ici
            .build()

    }


    //module pour fournir dao
    @Provides
    fun providePersonneDao(database: PersonneDatabase): PostDaw {
        return database.PresonneDAO()
    }

    @Provides
    @Singleton
    fun provideRepository(personneDao: PostDaw): Repository {
        return Repository(personneDao)
    }
}


*/
