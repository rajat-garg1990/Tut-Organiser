package com.example.dbtest.room

import androidx.room.*

@Dao
interface StudentDao {
    @Insert
    fun insertStudent(student: Student)

    @Query("SELECT * FROM student")
    fun getStudent():List<Student>

    @Query("SELECT * FROM student WHERE name LIKE :name")
    fun getStudentByName(name: String): List<Student>

    @Query("select * from student where enroll like :roll")
    fun getStudentByEnroll(roll: String): Student

    @Query("DELETE  FROM student WHERE name LIKE :name")
    fun deleteStudent(name: String)

    @Update
    fun updateStudent(student: Student)
}