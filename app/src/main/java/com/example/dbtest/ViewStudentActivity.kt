package com.example.dbtest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dbtest.adapters.StudentAdapter
import com.example.dbtest.room.StudentDatabase
import kotlinx.android.synthetic.main.activity_add_student.topBar

class ViewStudentActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_student)
        topBar.setNavigationOnClickListener { finish() }
        val db=StudentDatabase(this)
        val students=db.studentDao().getStudent()
        var viewStudRecycler=findViewById<RecyclerView>(R.id.viewStudRecycler)
        viewStudRecycler.apply {
            layoutManager=GridLayoutManager(this@ViewStudentActivity,2)
            adapter= StudentAdapter(students,this@ViewStudentActivity)
        }
    }

    override fun onRestart() {
        super.onRestart()
        recreate()
    }
}