package com.example.dbtest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import com.example.dbtest.room.NoteDatabase
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.android.synthetic.main.activity_view_note.*

class ViewNoteActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_note)

        viewNoteBar.setNavigationOnClickListener { finish() }
        var notebody=intent.getStringExtra("body")
        val db=NoteDatabase(this)
        var note= notebody?.let { db.noteDao().getNoteByBody(it) }
        var title=findViewById<TextView>(R.id.viewNoteTitle)
        var body=findViewById<TextView>(R.id.viewNoteBody)
        var time=findViewById<TextView>(R.id.viewNoteTime)
        if (note != null) {
            title.text = note.title
            body.text = note.body
            time.text = note.date
            deleteNote.setOnClickListener {
                MaterialAlertDialogBuilder(this).setTitle("Are you sure you want to delete?")
                    .setPositiveButton("OK") { _, _ ->
                        db.noteDao().deleteNote(note.title)
                        Toast.makeText(this, "Note Deleted", Toast.LENGTH_LONG).show()
                        finish()
                    }
                    .setNegativeButton("cancel") { _, _ -> }
                    .show()
            }
            updateNote.setOnClickListener {
                note.title = title.text.toString()
                note.body = body.text.toString()
                note.date = time.text.toString()
                db.noteDao().updateNote(note)
                Toast.makeText(this, "UPDATED", Toast.LENGTH_LONG).show()
                finish()
            }
        }else  finish()
    }
}