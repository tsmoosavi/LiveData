package com.example.livedata

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.TestLooperManager
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.activity.viewModels
import androidx.lifecycle.Observer

class MainActivity : AppCompatActivity() {
    val vm : MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
    }

    private fun initViews() {
        var textView = findViewById<TextView>(R.id.tvNumber)
        var buttonNext = findViewById<Button>(R.id.button1)
        var backButton = findViewById<Button>(R.id.backButton)
        var progressBar = findViewById<ProgressBar>(R.id.progressBar)
        var questionText = findViewById<TextView>(R.id.tvQuestion)
        var messageView = findViewById<TextView>(R.id.message)
        var checkAnswerBtn = findViewById<Button>(R.id.checkAnswerBtn)
        var answerTxv = findViewById<TextView>(R.id.answer)
        var scoreTxv = findViewById<TextView>(R.id.score)
        var questionCount= findViewById<TextView>(R.id.numberOfAllQuestion)
        var addQuestionBtn = findViewById<Button>(R.id.randomQuestion)
        progressBar.max = vm.questionCount
        checkAnswerBtn.setOnClickListener{
           vm.checkAnswer(answerTxv.text.toString().toInt())
        }
        addQuestionBtn.setOnClickListener{

            questionText.text =  vm.addRandomQuestion().questionText

        }
        buttonNext.setOnClickListener{
            vm.nextClicked()
            answerTxv.text = null
        }
        backButton.setOnClickListener{
            vm.backClicked()
            answerTxv.text = null
        }
        val numberObserver= Observer<Int>{ number->
            textView.text = number.toString()
            progressBar.progress = number
        }
        // راه مختصر برای observe
        vm.message.observe(this) {
            messageView.text = it
        }
        vm.colorOfScore.observe(this){
            when(it){
                "red" -> scoreTxv.setTextColor(getResources().getColor(R.color.red))
                "orange" ->scoreTxv.setTextColor(getResources().getColor(R.color.orange))
                else ->scoreTxv.setTextColor(getResources().getColor(R.color.green))
            }
        }
        val buttonEnabledObserver = Observer<Boolean> {  enabled->
            buttonNext.isEnabled = enabled
        }
        val backButtonEnableObserver = Observer<Boolean> { enable ->
            backButton.isEnabled = enable
        }
        val questionObserver = Observer<String> { question->
            questionText.text = question
        }
        val score = Observer<Int> { score ->
            scoreTxv.text = score.toString()
        }
        val checkAnswerEnableObserver = Observer<Boolean> {enable->
            checkAnswerBtn.isEnabled = enable
        }
        vm.questionCountLiveData.observe(this) {number->
            questionCount.text = number.toString()
        }

        vm.questionTextLiveData.observe(this,questionObserver)
        vm.nextEnabledLiveData.observe(this,buttonEnabledObserver)
        vm.backEnabledLiveData.observe(this,backButtonEnableObserver)
        vm.numberLiveData.observe(this,numberObserver)
        vm.scoreLiveData.observe(this,score)
        vm.checkAnswerEnableLiveData.observe(this,checkAnswerEnableObserver)
    }

}