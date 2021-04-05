package com.example.dbtest.room

import androidx.room.*
import retrofit2.http.Body

@Dao
interface NoteDao {
    @Insert
    fun insertNote(note: Note)

    @Query("select * from note")
    fun getNote():List<Note>

    @Query("select * from note where body like:body")
    fun getNoteByBody(body: String):Note

    @Query("delete from note where title like :title")
    fun deleteNote(title: String)

    @Update
    fun updateNote(note: Note)
}