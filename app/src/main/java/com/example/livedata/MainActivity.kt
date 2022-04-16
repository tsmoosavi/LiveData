package com.example.livedata

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.TestLooperManager
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.example.livedata.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    val vm : MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        initViews()
    }

    private fun initViews() {

       binding.checkAnswerBtn.setOnClickListener{
           vm.checkAnswer(binding.answer.text.toString().toInt())
        }
        binding.randomQuestion.setOnClickListener{
            vm.addRandomQuestion()
            binding.progressBar.max = vm.questionCountLiveData.value!!
            vm.nextEnabledLiveData.value = true
        }
        binding.button1.setOnClickListener{
            vm.nextClicked()
                binding.answer.text = null
        }
        binding.backButton.setOnClickListener{
            vm.backClicked()
          binding.answer.text = null
        }
        val numberObserver= Observer<Int>{ number->
            binding.tvNumber.text = number.toString()
            binding.progressBar.progress = number
        }
        // راه مختصر برای observe
        vm.message.observe(this) {
        binding.message.text = it
        }
//        vm.colorOfScore.observe(this){
//            when(it){
//                "red" ->binding.score.setTextColor(getResources().getColor(R.color.red))
//                "orange" ->binding.score.setTextColor(getResources().getColor(R.color.orange))
//                "green" ->binding.score.setTextColor(getResources().getColor(R.color.green))
//                else ->binding.score.setTextColor(getResources().getColor(R.color.black))
//            }
//        }

        val buttonEnabledObserver = Observer<Boolean> {  enabled->
            binding.button1.isEnabled = enabled
        }
        val backButtonEnableObserver = Observer<Boolean> { enable ->
            binding.backButton.isEnabled = enable
        }
        val questionObserver = Observer<String> { question->
          binding.tvQuestion.text = question
        }
        val score = Observer<Int> { score ->
            binding.scoreValue = score
        }
        val checkAnswerEnableObserver = Observer<Boolean> {enable->
          binding.checkAnswerBtn.isEnabled = enable
        }
        vm.questionCountLiveData.observe(this) {number->
            binding.questionCount = number
            binding.progressBar.max = number
        }

        vm.questionTextLiveData.observe(this,questionObserver)
        vm.nextEnabledLiveData.observe(this,buttonEnabledObserver)
        vm.backEnabledLiveData.observe(this,backButtonEnableObserver)
        vm.numberLiveData.observe(this,numberObserver)
        vm.scoreLiveData.observe(this,score)
        vm.checkAnswerEnableLiveData.observe(this,checkAnswerEnableObserver)
//        progressBar.max = vm.questionCountLiveData.value!!
    }

}