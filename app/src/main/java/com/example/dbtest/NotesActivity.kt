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
        val db = NoteDatabase(this)
        val notes = db.noteDao().getNote()
        var noteRecycler = findViewById<RecyclerView>(R.id.noteRecycler)
        val adapter=NoteAdapter(notes, this)
        noteRecycler.adapter = adapter
        noteRecycler.layoutManager = GridLayoutManager(this, 2)
        swipe.setOnRefreshListener {
            adapter.notifyDataSetChanged()
            swipe.isRefreshing=false
            recreate()

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