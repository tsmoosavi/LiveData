package com.example.livedata1

object QuestionRepository {
    val questionList = arrayListOf<Question>(
       Question( " result of 2 + 2 ",4) ,
       Question(" result of  5 - 1 " , 4) ,
        Question(" result of 100 * 21",2100)
    )
}
class Question(var question:String,var  answer: Int)