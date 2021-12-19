package com.example.kaninistocks

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [User::class], version = 1, exportSchema = false)
abstract class StockDatabase: RoomDatabase() {
    abstract fun UserDao(): UserDAO

    companion object{
        @Volatile
        private var INSTANCE:StockDatabase?=null
        fun getDatabase(context: Context):StockDatabase {
            val tempinstance= INSTANCE
            if(tempinstance!=null){
                return tempinstance
            }
            synchronized(this){
                val instance= Room.databaseBuilder(
                    context.applicationContext,
                    StockDatabase::class.java,
                    "Myy_database"
                ).allowMainThreadQueries().build()
                INSTANCE=instance
                return instance
            }
        }
    }
}