package com.example.dbtest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dbtest.adapters.NoteAdapter
import com.example.dbtest.room.NoteDatabase
import kotlinx.android.synthetic.main.activity_notes.*

class NotesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notes)

        notesBar.setNavigationOnClickListener { finish() }
        val db=NoteDatabase(this)
        val notes=db.noteDao().getNote()
        var noteRecycler = findViewById<RecyclerView>(R.id.noteRecycler)
        noteRecycler.apply {
            layoutManager = GridLayoutManager(this@NotesActivity, 2)
            adapter=NoteAdapter(notes,context)
        }
        addNoteButton.setOnClickListener {
            startActivity(Intent(this, AddNoteActivity::class.java))
        }
    }

    override fun onRestart() {
        super.onRestart()
        recreate()
    }
}