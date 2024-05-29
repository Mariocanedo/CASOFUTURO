package com.example.casofuturo.Modelo.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.casofuturo.Modelo.local.CentroFuturoDao

import com.example.casofuturo.Modelo.local.entities.CoursesDetailEntity
import com.example.casofuturo.Modelo.local.entities.CoursesEntity
import com.example.casofuturo.Modelo.remote.CentroFuturoApi

@Database(entities=[CoursesEntity::class,CoursesDetailEntity::class], version = 1,
    exportSchema = false)
abstract class CourseDataBase: RoomDatabase() {

    // REFERENCIA AL DAO PARTE LOCAL
    abstract fun getCentroFuturoDao(): CentroFuturoDao

    companion object {
        @Volatile
        private var INSTANCE: CourseDataBase? = null

        fun getDataBase(context: Context): CourseDataBase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    CourseDataBase::class.java,
                    "CASOFUTURO"
                )
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }
}