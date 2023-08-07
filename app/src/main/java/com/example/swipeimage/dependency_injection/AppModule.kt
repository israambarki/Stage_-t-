package com.example.swipeimage.dependency_injection

import android.content.Context
import androidx.room.Room
import com.example.swipeimage.inscription.data.datasource.Datasource.PersonneDatabase
import com.example.swipeimage.inscription.data.datasource.Datasource.PostDaw
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

    @Singleton
    @Provides
    fun providerDB(@ApplicationContext context: Context) : PersonneDatabase {

return Room.databaseBuilder(
    context.applicationContext,
    PersonneDatabase::class.java,
    "InscriptionPresonne.db"
).build()

    }

//dao
    @Provides
    fun providerPersonne( personne : PersonneDatabase): PostDaw = personne.PresonneDAO()



    }




