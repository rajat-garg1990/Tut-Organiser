package com.example.dbtest.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Note(
    var title: String = "",
    @PrimaryKey
    var body: String = "",
    var date: String = ""
)