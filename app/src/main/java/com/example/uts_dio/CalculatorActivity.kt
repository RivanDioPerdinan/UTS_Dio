package com.example.uts_dio

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class CalculatorActivity : AppCompatActivity() {

    private lateinit var resultTextView: TextView
    private var currentInput = StringBuilder()
    private var operator: String? = null
    private var firstNumber: Double? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calculator)

        resultTextView = findViewById(R.id.resultTextView)

        val buttonIds = arrayOf(
            R.id.button0, R.id.button1, R.id.button2, R.id.button3,
            R.id.button4, R.id.button5, R.id.button6, R.id.button7,
            R.id.button8, R.id.button9, R.id.buttonAdd, R.id.buttonSubtract,
            R.id.buttonMultiply, R.id.buttonDivide, R.id.buttonClear,
            R.id.buttonDelete, R.id.buttonEquals, R.id.buttonPercentage
        )

        for (id in buttonIds) {
            findViewById<Button>(id).setOnClickListener { onButtonClick(it) }
        }

        val backButton: Button = findViewById(R.id.backButton)
        backButton.setOnClickListener {
            finish() // Kembali ke Activity sebelumnya
        }
    }

    private fun onButtonClick(view: View) {
        val button = view as Button
        val buttonText = button.text.toString()

        when (buttonText) {
            "C" -> clear()
            "Del" -> deleteLast()
            "=" -> calculateResult()
            "%" -> calculatePercentage()
            else -> handleInput(buttonText)
        }
    }

    private fun clear() {
        currentInput.clear()
        resultTextView.text = ""
        firstNumber = null
        operator = null
    }

    private fun deleteLast() {
        if (currentInput.isNotEmpty()) {
            currentInput.deleteCharAt(currentInput.length - 1)
            resultTextView.text = currentInput.toString()
        }
    }

    private fun handleInput(input: String) {
        if (input in listOf("+", "-", "*", "/")) {
            if (currentInput.isNotEmpty()) {
                firstNumber = currentInput.toString().toDouble()
                operator = input
                currentInput.clear()
            }
        } else {
            currentInput.append(input)
        }
        resultTextView.text = currentInput.toString()
    }

    private fun calculateResult() {
        if (firstNumber != null && operator != null && currentInput.isNotEmpty()) {
            val secondNumber = currentInput.toString().toDouble()
            val result = when (operator) {
                "+" -> firstNumber!! + secondNumber
                "-" -> firstNumber!! - secondNumber
                "*" -> firstNumber!! * secondNumber
                "/" -> if (secondNumber != 0.0) firstNumber!! / secondNumber else "Error"
                else -> 0.0
            }
            currentInput.clear()
            resultTextView.text = result.toString()
            firstNumber = null
            operator = null
        }
    }

    private fun calculatePercentage() {
        if (currentInput.isNotEmpty()) {
            val number = currentInput.toString().toDouble()
            val result = number / 100
            resultTextView.text = result.toString()
            currentInput.clear()
        }
    }
}
