package com.Project.skinsolve.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [HistoryEntity::class], version = 1)
abstract class SkinDatabase: RoomDatabase() {
    abstract fun historyDao(): HistoryDao
    companion object{
        @Volatile
        private var INSTANCE: SkinDatabase? = null
        @JvmStatic
        fun getDatabase(context: Context): SkinDatabase {
            if (INSTANCE == null){
                synchronized(SkinDatabase::class.java){
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                        SkinDatabase::class.java, "skin_database")
                        .allowMainThreadQueries()
                        .build()
                }
            }
            return INSTANCE as SkinDatabase
        }
    }
}