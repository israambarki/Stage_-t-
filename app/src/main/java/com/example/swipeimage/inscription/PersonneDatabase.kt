package com.example.swipeimage.inscription

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(entities =[InscriptionPresonne::class], version = 1 )
abstract class PersonneDatabase : RoomDatabase(){

    //une focntion qui represente notre DAO:

abstract  fun PresonneDAO(): PostDaw


}