package com.example.dbtest.room

import android.content.Context
import androidx.room.*

@Database(entities = [Student::class], version = 1)
abstract class StudentDatabase : RoomDatabase() {
    abstract fun studentDao(): StudentDao

    companion object {
        @Volatile
        var instance: StudentDatabase? = null
        operator fun invoke(context: Context) = instance ?: synchronized(Any()) {
            instance ?: buildDatabase(context).also { instance = it }
        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context,
            StudentDatabase::class.java, "StudentDB"
        ).allowMainThreadQueries().build()
    }
}