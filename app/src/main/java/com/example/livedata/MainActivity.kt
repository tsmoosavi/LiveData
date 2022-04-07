package com.example.livedata

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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
        progressBar.max = vm.questionCount
        buttonNext.setOnClickListener{
            vm.nextClicked()
        }
        backButton.setOnClickListener{
            vm.backClicked()
        }
        val numberObserver= Observer<Int>{ number->
            textView.text = number.toString()
            progressBar.progress = number
        }
        // راه مختصر برای observe
        vm.message.observe(this) {
            messageView.text = it
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
        vm.questionLiveData.observe(this,questionObserver)
        vm.nextEnabledLiveData.observe(this,buttonEnabledObserver)
        vm.backEnabledLiveData.observe(this,backButtonEnableObserver)
        vm.numberLiveData.observe(this,numberObserver)
    }
}