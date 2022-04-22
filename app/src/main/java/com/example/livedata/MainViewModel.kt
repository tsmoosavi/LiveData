package com.example.livedata

import android.app.Application
import androidx.lifecycle.*
import com.example.livedata1.QuestionRepository

class MainViewModel(app: Application):AndroidViewModel(app) {
     var questionList :List<QuestionEntity>
    val questionTextLiveData = MutableLiveData<String>()
    val questionLiveData = MutableLiveData<QuestionEntity>()
    var questionCountLiveData : LiveData<Int>
    val numberLiveData= MutableLiveData<Int>(1)
//        MutableLiveData<String>(QuestionRepository.questionList[0].question)
    init {
        QuestionRepository.initDB(app.applicationContext)
        questionList = QuestionRepository.getQuestions()
        questionTextLiveData.value = QuestionRepository.getChosenQuestion(1).questionText
        questionCountLiveData = QuestionRepository.getNumberOfQuestion()

    }

    val scoreLiveData = MutableLiveData<Int>(0)
    var halfQuestionListSize =questionList.size/2
//    var colorOfScore : LiveData<String> = Transformations.map(scoreLiveData){
//        when(it){
//            in 0 .. 5 -> "red"
//            in 6 .. 10 -> "orange"
//            in 11..15-> "green"
//            else -> "black"
//        }
//    }

    val message :LiveData<String> = Transformations.map(numberLiveData) {
        when (it) {
            in 0..halfQuestionListSize -> "Hurry up"
            else -> "You almost done"
            //راه استاد:
//          number->
//          if(number<=questionCount/2 )321
//              "go ahead"
//          else
//              "you are reaching the end"
        }
    }

    var nextEnabledLiveData = MutableLiveData<Boolean>(true)

    var backEnabledLiveData = MutableLiveData<Boolean>(false)

    var checkAnswerEnableLiveData = MutableLiveData<Boolean>(true)

    fun backClicked(){
        checkAnswerEnableLiveData.value = true
        nextEnabledLiveData.value =true

        numberLiveData.value = numberLiveData.value?.minus(1)
        numberLiveData.value?.let{number->
                questionTextLiveData.value = QuestionRepository.getChosenQuestion(number).questionText
            }

        if (numberLiveData.value!! == 1) {
            backEnabledLiveData.value = false
        }

    }
    fun nextClicked(){
        checkAnswerEnableLiveData.value = true
        backEnabledLiveData.value = true
        numberLiveData.value = numberLiveData.value?.plus(1)
        numberLiveData.value?.let{number->
                 questionTextLiveData.value = QuestionRepository.getChosenQuestion(number).questionText
             }
         if (numberLiveData.value!! == questionCountLiveData.value) {
             nextEnabledLiveData.value = false
         }


    }

    fun checkAnswer(answer:Int) {
        checkAnswerEnableLiveData.value = false
        if(answer == QuestionRepository.getChosenQuestion(numberLiveData.value!!).answer){
            scoreLiveData.value =   scoreLiveData.value?.plus(5)
        }
        else{
            scoreLiveData.value =   scoreLiveData.value?.minus(2)
        }
    }
    fun randomQuestion(){
        QuestionRepository.newRandomQuestion()
    }
    fun getChosenQuestion(number:Int):LiveData<QuestionEntity>{
        questionLiveData.value =  QuestionRepository.getChosenQuestion(number)
        return questionLiveData
    }
    fun countQuestion():LiveData<Int>{
        questionCountLiveData = QuestionRepository.getNumberOfQuestion()
        return questionCountLiveData

    }
    fun addRandomQuestion():QuestionEntity{
        QuestionRepository.addQuestion()
        return QuestionRepository.newRandomQuestion()
    }
}