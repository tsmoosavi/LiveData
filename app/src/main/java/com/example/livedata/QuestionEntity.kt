package com.example.livedata

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class QuestionEntity (
    @PrimaryKey(autoGenerate = true) val number: Int,
    val questionText : String,
    val answer : Int
)