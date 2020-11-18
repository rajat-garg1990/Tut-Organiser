package com.example.dbtest.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.dbtest.R
import com.example.dbtest.UpdateStudentActivity
import com.example.dbtest.room.Student
import com.example.dbtest.room.StudentDatabase
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.android.synthetic.main.list_item.view.*

class StudentAdapter(var students: List<Student>, var context: Context) :
    RecyclerView.Adapter<StudentAdapter.ViewHolder>() {
    var db = StudentDatabase(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return ViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.cardEnroll.text=students[position].enroll
        holder.itemView.cardName.text = students[position].name
        holder.itemView.cardRoll.text = students[position].roll
        holder.itemView.cardGender.text = students[position].gender
        holder.itemView.cardDob.text = "Birth: " + students[position].dob
        holder.itemView.cardArea.text = students[position].area
        holder.itemView.del_stud.setOnClickListener {
            MaterialAlertDialogBuilder(context).setTitle("Confirm Delete ?")
                .setPositiveButton("OK") { _, _ ->
                    db.studentDao().deleteStudent(holder.itemView.cardName.text.toString())
                    Toast.makeText(context, "Deleted student", Toast.LENGTH_LONG).show()
                }
                .setNegativeButton("Cancel"){_,_-> }.show()
        }
        holder.itemView.update_stud.setOnClickListener {
            var intent=Intent(context,UpdateStudentActivity::class.java)
            intent.putExtra("enroll",students[position].enroll)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return students.size
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    }
}
