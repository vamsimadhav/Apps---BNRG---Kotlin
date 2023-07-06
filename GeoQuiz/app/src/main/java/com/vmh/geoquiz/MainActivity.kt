package com.vmh.geoquiz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import com.vmh.geoquiz.databinding.ActivityMainBinding

private const val TAG = "MainActivity"
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val quizViewModel: QuizViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Log.d(TAG, "Got a QuizViewModel : $quizViewModel")

        binding.questionTextView.setOnClickListener{
            quizViewModel.moveToNext()
            refreshButton()
            updateQuestion()
        }

        binding.trueButton.setOnClickListener{
            checkAnswer(true)
        }

        binding.falseButton.setOnClickListener{
            checkAnswer(false)
        }

        binding.nextButton.setOnClickListener{
            quizViewModel.moveToNext()
            refreshButton()
            updateQuestion()
        }

        binding.prevButton.setOnClickListener{
            quizViewModel.moveToPrev()
            refreshButton()
            updateQuestion()
        }

        updateQuestion()
    }

    private fun refreshButton(){
        binding.trueButton.isEnabled = true
        binding.falseButton.isEnabled = true
    }

    private fun updateQuestion(){
        val questionText = quizViewModel.currentQuestionText
        binding.questionTextView.setText(questionText)
    }

    private fun checkAnswer(userAnswer: Boolean){
        val questionAnswer = quizViewModel.currentQuestionAnswer

        quizViewModel.updateUserAnswer(userAnswer, questionAnswer)

        binding.trueButton.isEnabled = false
        binding.falseButton.isEnabled = false

        val message =  if(userAnswer == questionAnswer){
            R.string.true_correct
        }else{
            R.string.false_incorrect
        }

        Toast.makeText(
            this,
            message,
            Toast.LENGTH_SHORT
        ).show()
    }
}