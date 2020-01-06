package com.example.android.darkskykotlin.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.android.darkskykotlin.models.Currently

@Database(entities = [Currently::class], version = 1, exportSchema = false)
abstract class WeatherDatabase : RoomDatabase() {

   abstract val WeatherDatabaseDao: WeatherDao

   companion object {

       @Volatile
       private var INSTANCE: WeatherDatabase? = null

       fun getInstance(context: Context): WeatherDatabase {
           synchronized(this) {
               var instance = INSTANCE

               if (instance == null) {
                   instance = Room.databaseBuilder(
                           context.applicationContext,
                           WeatherDatabase::class.java,
                           "sleep_history_database"
                   )
                           .fallbackToDestructiveMigration()
                           .build()
                   INSTANCE = instance
               }
               return instance
           }
       }
   }
}