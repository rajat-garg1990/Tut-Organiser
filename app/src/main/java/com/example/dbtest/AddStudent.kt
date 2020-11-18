package com.example.dbtest

import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import com.example.dbtest.room.Student
import com.example.dbtest.room.StudentDatabase
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_add_student.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class AddStudent : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_student)

        topBar.setNavigationOnClickListener { finish() }
        var textRoll = findViewById<TextView>(R.id.textRoll)
        var textName = findViewById<TextView>(R.id.textName)
        var textDob = findViewById<TextView>(R.id.textDob)
        var rgGender = findViewById<RadioGroup>(R.id.rgGender)
        var textRes = findViewById<TextView>(R.id.textRes)
        var textEnroll = findViewById<TextView>(R.id.textEnroll)
        var addStud = findViewById<Button>(R.id.addStud)
        var gender: String = ""
        pickDob.setOnClickListener {
            selectDate()
        }
        rgGender.setOnCheckedChangeListener { _, i ->
            when (i) {
                R.id.btMale -> gender = btMale.text.toString()
                R.id.btFemale -> gender = btFemale.text.toString()
            }
        }
        var db = StudentDatabase(this)
        addStud.setOnClickListener {
            if (textName.text.isEmpty() || textDob.text.isEmpty() || gender.isEmpty() || textRoll.text.isEmpty()) {
                MaterialAlertDialogBuilder(this).setTitle("Fields with ' * ' cannot be empty")
                    .setPositiveButton("OK") { _, _ -> }.show()
            } else {
                var student = Student()
                student.enroll = textEnroll.text.toString()
                student.roll = textRoll.text.toString()
                student.name = textName.text.toString()
                student.dob = textDob.text.toString()
                student.gender = gender
                student.area = textRes.text.toString()
                db.studentDao().insertStudent(student)
                Snackbar.make(it, "Added Successfully !!", Snackbar.LENGTH_INDEFINITE)
                    .setAction("View") {
                        startActivity(Intent(this@AddStudent, ViewStudentActivity::class.java))
                    }
                    .show()
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
                textDob.setText(date)
            }, now.get(Calendar.YEAR), now.get(Calendar.MONTH), now.get(Calendar.DAY_OF_MONTH))
        datePicker.show()
    }
}