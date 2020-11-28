package com.example.dbtest.room

import android.content.Context
import androidx.room.*

@Database(entities = [Note::class],version = 1)
abstract class NoteDatabase : RoomDatabase(){
    abstract fun noteDao(): NoteDao
    companion object{
        @Volatile
        var instance: NoteDatabase?=null
        operator fun invoke(context: Context)= instance ?: synchronized(Any()){
            instance ?: buildDatabase(context).also{ instance=it}
        }

        private fun buildDatabase(context: Context)=Room.databaseBuilder(context,
            NoteDatabase::class.java,"NoteDB").allowMainThreadQueries().build()
    }
}