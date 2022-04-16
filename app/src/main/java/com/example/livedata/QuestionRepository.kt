package com.example.livedata1

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.livedata.DaoOfQuestion
import com.example.livedata.MyDatabase
import com.example.livedata.QuestionEntity
import kotlin.random.Random

object QuestionRepository {
    var db: MyDatabase? = null
    var questionDao: DaoOfQuestion? = null
    fun initDB(context:Context ){
        db = MyDatabase.getAppDataBase(context)
        questionDao = db?.daoOfQuestion()
         addTestData()
    }

   fun addTestData() {
        questionDao?.insertAll(
            QuestionEntity( 1," result of 2 + 2 ",4) ,
            QuestionEntity(2," result of  5 - 1 " , 4) ,
            QuestionEntity(3," result of 100 * 21",2100))
    }
   fun getQuestions():List<QuestionEntity>{
        return db!!.daoOfQuestion().getAll()
    }
   fun newRandomQuestion(): QuestionEntity {
        var a =(0 .. 100).random()
        var b =(0 .. 100).random()
        var result = a - b
        var questionText = "result of $a - $b"
        return QuestionEntity(0,questionText,result)
    }

   fun addQuestion(){
       db!!.daoOfQuestion().insert(newRandomQuestion())
   }
    fun getNumberOfQuestion():LiveData<Int>{
        return db!!.daoOfQuestion().getNumberQuestions()
    }
    fun getChosenQuestion(number:Int):QuestionEntity{
        return db!!.daoOfQuestion().chooseQuestion(number)
    }


}


