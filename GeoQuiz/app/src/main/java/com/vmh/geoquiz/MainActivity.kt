package com.vmh.geoquiz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.android.material.button.MaterialButton
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar
import com.vmh.geoquiz.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val questionBank = listOf(
        Question(R.string.question_australia, true),
        Question(R.string.question_oceans, true),
        Question(R.string.question_mideast, false),
        Question(R.string.question_africa, false),
        Question(R.string.question_americas, true),
        Question(R.string.question_asia, true)
    )

    private val questionBankLength = questionBank.size

    private var currentIndex = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.questionTextView.setOnClickListener{
            currentIndex = (currentIndex + 1) % questionBank.size
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
            currentIndex = (currentIndex + 1) % questionBank.size
            refreshButton()
            updateQuestion()
        }

        binding.prevButton.setOnClickListener{
            if (currentIndex > 0) {
                currentIndex = (currentIndex - 1)
                refreshButton()
                updateQuestion()
            } else {
                currentIndex = questionBank.size - 1
                refreshButton()
                updateQuestion()
            }
        }

        updateQuestion()
    }

    private fun refreshButton(){
        binding.trueButton.isEnabled = true
        binding.falseButton.isEnabled = true
    }

    private fun updateQuestion(){
        val questionText = questionBank[currentIndex].questionRes
        binding.questionTextView.setText(questionText)
    }

    private fun checkAnswer(userAnswer: Boolean){
        val questionAnswer = questionBank[currentIndex].answer
        questionBank[currentIndex].answered = true

        questionBank[currentIndex].userAnswer = userAnswer == questionAnswer

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