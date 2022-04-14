package com.example.livedata

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface DaoOfQuestion {
    @Query("SELECT * fROM QuestionEntity")
    fun getAll(): List<QuestionEntity>

    @Query("SELECT count(number) fROM QuestionEntity")
    fun getNumberQuestions(): LiveData<Int>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg questions: QuestionEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(question: QuestionEntity)

    @Query("SELECT * fROM QuestionEntity where number = :number")
        fun chooseQuestion(number:Int):QuestionEntity

    @Delete
    fun delete(question : QuestionEntity)


}