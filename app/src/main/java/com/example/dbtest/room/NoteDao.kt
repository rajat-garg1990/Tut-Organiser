package com.example.dbtest.room

import androidx.room.*

@Dao
interface NoteDao {
    @Insert
    fun insertNote(note: Note)

    @Query("select * from note")
    fun getNote():List<Note>

    @Query("delete from note where title like :title")
    fun deleteNote(title: String)
}