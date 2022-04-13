package com.example.livedata1

import android.content.Context
import com.example.livedata.DaoOfQuestion
import com.example.livedata.MyDatabase
import com.example.livedata.QuestionEntity

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

//    val questionList = arrayListOf<Question>(
//       Question( " result of 2 + 2 ",4) ,
//       Question(" result of  5 - 1 " , 4) ,
//        Question(" result of 100 * 21",2100)
//    )
}
//class Question(var question:String,var  answer: Int)