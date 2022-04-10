package com.example.livedata

import androidx.room.*
import com.example.livedata1.Question

@Dao
interface DaoOfQuestion {
    @Query("SELECT * fROM QuestionEntity")
    fun getAll(): List<QuestionEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg questions: QuestionEntity)

    @Delete
    fun delete(question : QuestionEntity)

}