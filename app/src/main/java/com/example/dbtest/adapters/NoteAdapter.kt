package com.example.dbtest.adapters

import android.content.Context
import android.view.*
import androidx.recyclerview.widget.RecyclerView
import com.example.dbtest.R
import com.example.dbtest.room.Note
import kotlinx.android.synthetic.main.list_notes.view.*

class NoteAdapter(var note: List<Note>,var context: Context) : RecyclerView.Adapter<NoteAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.list_notes, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.noteTitle.text=note[position].title
        holder.itemView.noteBody.text=note[position].body
        holder.itemView.noteDate.text=note[position].date
    }

    override fun getItemCount(): Int {
        return note.size
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    }
}