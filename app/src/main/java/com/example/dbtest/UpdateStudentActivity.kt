package com.example.dbtest

import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import com.example.dbtest.room.Student
import com.example.dbtest.room.StudentDatabase
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.android.synthetic.main.activity_add_student.topBar
import kotlinx.android.synthetic.main.activity_update_student.*
import java.text.SimpleDateFormat
import java.util.*

class UpdateStudentActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_student)

        topBar.setNavigationOnClickListener { finish() }
        var gender: String = ""
        var enroll = intent.getStringExtra("enroll")
        val db = StudentDatabase(this)
        val student = enroll?.let { db.studentDao().getStudentByEnroll(it) }
        var textEnroll = findViewById<TextView>(R.id.searchEnroll)
        var searchRoll = findViewById<TextView>(R.id.searchRoll)
        var updateName = findViewById<TextView>(R.id.updateName)
        var updateDob = findViewById<TextView>(R.id.updateDob)
        var updateGender = findViewById<RadioGroup>(R.id.updateGender)
        var updateRes = findViewById<TextView>(R.id.updateRes)
        var updateCity = findViewById<TextView>(R.id.updateCity)
        var updateSection = findViewById<TextView>(R.id.updateSection)
        textEnroll.text = enroll
        if (student != null) {
            updateCity.text = student.city
            updateSection.text = student.section
            searchRoll.text = student.roll
            updateName.text = student.name
            updateDob.text = student.dob
            updateRes.text = student.area
            gender = if (student.gender == "Male") {
                updateGender.check(R.id.male)
                "Male"
            } else {
                updateGender.check(R.id.female)
                "Female"
            }
        }
        updateDobButton.setOnClickListener {
            selectDate()
        }
        updateGender.setOnCheckedChangeListener { _, i ->
            gender = when (i) {
                R.id.male -> male.text.toString()
                R.id.female -> female.text.toString()
                else -> gender
            }
        }
        var updateStudent = findViewById<Button>(R.id.updateStudent)
        updateStudent.setOnClickListener {
            if (student != null) {
                student.enroll = enroll!!
                student.roll = searchRoll.text.toString()
                student.name = updateName.text.toString()
                student.dob = updateDob.text.toString()
                student.area = updateRes.text.toString()
                student.gender = gender
                student.city=updateCity.text.toString()
                student.section=updateSection.text.toString()
                /*db.studentDao().deleteStudent(student.name)
                db.studentDao().insertStudent(student)*/
                db.studentDao().updateStudent(student)
                Toast.makeText(this, "UPDATED", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun selectDate() {
        var format = SimpleDateFormat("dd/MM/YYYY", Locale.US)
        var now = Calendar.getInstance()
        var datePicker =
            DatePickerDialog(this, DatePickerDialog.OnDateSetListener { view, i, i2, i3 ->
                var selected = Calendar.getInstance()
                selected.set(Calendar.YEAR, i)
                selected.set(Calendar.MONTH, i2)
                selected.set(Calendar.DAY_OF_MONTH, i3)
                var date = format.format(selected.time)
                updateDob.setText(date)
            }, now.get(Calendar.YEAR), now.get(Calendar.MONTH), now.get(Calendar.DAY_OF_MONTH))
        datePicker.show()
    }
}