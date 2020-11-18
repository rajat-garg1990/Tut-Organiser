package com.example.dbtest.room

import androidx.room.*

@Entity
data class Student(
    @PrimaryKey
    var enroll: String="",
    var roll: String="",
    var name: String = "",
    var dob: String = "",
    var gender: String = "",
    var area: String = ""
)