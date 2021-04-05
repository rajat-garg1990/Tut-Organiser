package com.example.dbtest.adapters

import android.content.Context
import android.content.Intent
import android.view.*
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.dbtest.R
import com.example.dbtest.ViewNoteActivity
import com.example.dbtest.room.Note
import com.example.dbtest.room.NoteDatabase
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.android.synthetic.main.list_notes.view.*

class NoteAdapter(var note: List<Note>,var context: Context) : RecyclerView.Adapter<NoteAdapter.ViewHolder>() {

    val db=NoteDatabase(context)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.list_notes, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.noteTitle.text=note[position].title
        holder.itemView.noteBody.text=note[position].body
        holder.itemView.noteDate.text=note[position].date
        try{
            holder.itemView.cardNotes.setOnClickListener {
                val intent = Intent(context, ViewNoteActivity::class.java)
                intent.putExtra("body", holder.itemView.noteBody.text)
                context.startActivity(intent)
            }
            holder.itemView.cardNotes.setOnLongClickListener {
                MaterialAlertDialogBuilder(context).setTitle("Do you want to delete this note?")
                    .setPositiveButton("Delete") { _, _ ->
                        db.noteDao().deleteNote(note[position].title)
                        Toast.makeText(context, "Note Deleted!! Pull to refresh", Toast.LENGTH_LONG)
                            .show()
                    }.setNegativeButton("Cancel") { _, _ -> }
                    .show()
                return@setOnLongClickListener true
            }
        }catch (e:Exception){
            MaterialAlertDialogBuilder(context).setTitle("Error!! Swipe to refresh")
                .setPositiveButton("OK"){_,_->}
                .show()
        }
    }

    override fun getItemCount(): Int {
        return note.size
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    }
}