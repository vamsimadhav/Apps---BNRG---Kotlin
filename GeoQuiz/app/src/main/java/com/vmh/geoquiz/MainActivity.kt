package com.vmh.geoquiz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.android.material.button.MaterialButton
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    private lateinit var trueButton: MaterialButton
    private lateinit var falseButton: MaterialButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        trueButton = findViewById(R.id.trueButton)
        falseButton = findViewById(R.id.falseButton)

        trueButton.setOnClickListener{ view: View ->
            Toast.makeText(
                    view.context,
                R.string.true_correct,
                    Toast.LENGTH_SHORT
            ).show()

            //TODO: Use Snackbar Instead of Toast
//            Snackbar.make(
//                this,
//                trueButton,
//                "Correct",
//                BaseTransientBottomBar.LENGTH_SHORT
//            ).show()
        }

        falseButton.setOnClickListener{ view: View ->
            Toast.makeText(
                view.context,
                R.string.false_incorrect,
                Toast.LENGTH_SHORT
            ).show()

        //TODO: Use Snackbar Instead of Toast
//            Snackbar.make(
//                this,
//                falseButton,
//                "Incorrect",
//                BaseTransientBottomBar.LENGTH_SHORT
//            ).show()
        }
    }
}