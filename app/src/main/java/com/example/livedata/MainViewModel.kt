package com.example.livedata

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.livedata1.QuestionRepository

class MainViewModel: ViewModel() {
    val questionLiveData = MutableLiveData<String>(QuestionRepository.questionList[0].question)
    val questionCount = QuestionRepository.questionList.size-1
    val scoreLiveData = MutableLiveData<Int>(0)
    val numberLiveData= MutableLiveData<Int>(0)
    var halfQuestionListSize = QuestionRepository.questionList.size/2
    var colorOfScore : LiveData<String> = Transformations.map(scoreLiveData){
        when(it){
            in 0 .. 5 -> "red"
            in 6 .. 10 -> "orange"
            else -> "green"
        }
    }

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
//        if (numberLiveData.value!! > 0){
//            numberLiveData.value = numberLiveData.value?.minus(1)
//        }
//        if (numberLiveData.value!! >= 0){
//            numberLiveData.value?.let{number->
//                questionLiveData.value = QuestionRepository.questionList[number]
//            }
//        }
        numberLiveData.value = numberLiveData.value?.minus(1)
        numberLiveData.value?.let{number->
                questionLiveData.value = QuestionRepository.questionList[number].question
            }

        if (numberLiveData.value!! == 0) {
            backEnabledLiveData.value = false
        }

    }
    fun nextClicked(){
        checkAnswerEnableLiveData.value = true
        backEnabledLiveData.value = true
//        if (numberLiveData.value!! < questionCount){
//            numberLiveData.value = numberLiveData.value?.plus(1)
//        }
//         if (numberLiveData.value!! <= questionCount){
//             numberLiveData.value?.let{number->
//                 questionLiveData.value = QuestionRepository.questionList[number]
//             }
//        }
        numberLiveData.value = numberLiveData.value?.plus(1)
        numberLiveData.value?.let{number->
                 questionLiveData.value = QuestionRepository.questionList[number].question
             }
         if (numberLiveData.value!! == questionCount) {
             nextEnabledLiveData.value = false
         }


    }

    fun checkAnswer(answer:Int) {
        checkAnswerEnableLiveData.value = false
        if(answer == QuestionRepository.questionList[numberLiveData.value!!].answer){
            scoreLiveData.value =   scoreLiveData.value?.plus(5)
        }
        else{
            scoreLiveData.value =   scoreLiveData.value?.minus(2)
        }
    }
}