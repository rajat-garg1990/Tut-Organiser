package com.example.dbtest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dbtest.adapters.StudentAdapter
import com.example.dbtest.room.Student
import com.example.dbtest.room.StudentDatabase
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.android.synthetic.main.activity_add_student.topBar
import kotlinx.android.synthetic.main.activity_view_student.*

class ViewStudentActivity : AppCompatActivity() {

    //private lateinit var students: List<Student>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_student)

        topBar.setNavigationOnClickListener { finish() }
        var checkedSection = ""
        val db = StudentDatabase(this)
        var viewStudRecycler = findViewById<RecyclerView>(R.id.viewStudRecycler)
        viewAll.setOnClickListener {
            //students = db.studentDao().getStudent()
            viewStudRecycler.apply {
                layoutManager = GridLayoutManager(this@ViewStudentActivity, 2)
                adapter = StudentAdapter(db.studentDao().getStudent(), this@ViewStudentActivity)
            }
        }
        viewByClass.setOnClickListener {
            var sections = db.studentDao().getDistinctSections()
            //sections.add(sections.size+1, listOf("All"))
            MaterialAlertDialogBuilder(this).setTitle("Class and Section")
                .setSingleChoiceItems(sections, -1) { _, i ->
                    checkedSection = sections[i]
                }
                .setPositiveButton("OK") { _, _ ->
                    //students = db.studentDao().getStudentsByClass(checkedSection)
                    viewStudRecycler.apply {
                        layoutManager = GridLayoutManager(this@ViewStudentActivity, 2)
                        adapter = StudentAdapter(
                            db.studentDao().getStudentsByClass(checkedSection),
                            this@ViewStudentActivity
                        )
                    }
                }
                .setNegativeButton("Cancel") { _, _ -> }
                .show()
            //students=db.studentDao().getStudentsByClass()
        }
    }

    override fun onRestart() {
        super.onRestart()
        recreate()
    }
}