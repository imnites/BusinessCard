package com.swe.businesscard.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [User::class], version = 1, exportSchema = false)
abstract class Database : RoomDatabase() {
  abstract fun userDAO(): UserDAO

  companion object {
    @Volatile
    private var INSTANCE: com.swe.businesscard.database.Database? = null

    fun getDatabase(context: Context): com.swe.businesscard.database.Database {
      return INSTANCE ?: synchronized(this) {
        val instance = Room.databaseBuilder(
          context.applicationContext,
          com.swe.businesscard.database.Database::class.java,
          "room_database"
        ).build()
        INSTANCE = instance
        instance
      }
    }
  }
}