package com.example.dbtest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dbtest.adapters.StudentAdapter
import com.example.dbtest.room.StudentDatabase
import kotlinx.android.synthetic.main.activity_add_student.*
import kotlinx.android.synthetic.main.list_item.*

class ModifyStudentActivity : AppCompatActivity() {
    lateinit var queryName: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_modify_student)

        topBar.setNavigationOnClickListener { finish() }
        var searchName = findViewById<TextView>(R.id.searchName)
        queryName = searchName.text.toString()
        performSearch()
    }

    private fun performSearch() {
        val db = StudentDatabase(this)
        var studentRecycler = findViewById<RecyclerView>(R.id.studentRecycler)
        var student = db.studentDao().getStudentByName("%$queryName%")
        var searchButton = findViewById<Button>(R.id.searchButton)
        searchButton.setOnClickListener {
            studentRecycler.apply {
                layoutManager = GridLayoutManager(this@ModifyStudentActivity, 2)
                adapter = StudentAdapter(student, this@ModifyStudentActivity)
            }
        }
    }

    override fun onRestart() {
        super.onRestart()
        recreate()
        performSearch()
    }
}
