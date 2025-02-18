package com.example.uts_dio

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.uts_dio.R

class MoneyConvertActivity : AppCompatActivity() {

    private lateinit var amountInput: EditText
    private lateinit var currencyFromSpinner: Spinner
    private lateinit var currencyToSpinner: Spinner
    private lateinit var convertButton: Button
    private lateinit var resultTextView: TextView
    private lateinit var backButton: ImageButton

    // Updated currency rates including IDR (for demonstration purposes)
    private val currencyRates = mapOf(
        "USD" to 1.0,
        "EUR" to 0.85,
        "GBP" to 0.75,
        "JPY" to 110.0,
        "AUD" to 1.35,
        "CAD" to 1.25,
        "IDR" to 14200.0 // Example rate: 1 USD = 14200 IDR (adjust as necessary)
    )

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_moneyconvert)

        amountInput = findViewById(R.id.amountInput)
        currencyFromSpinner = findViewById(R.id.currencyFromSpinner)
        currencyToSpinner = findViewById(R.id.currencyToSpinner)
        convertButton = findViewById(R.id.convertButton)
        resultTextView = findViewById(R.id.resultTextView)


        // Inisialisasi ImageButton Back
        val backButton: Button = findViewById(R.id.backButton)
        backButton.setOnClickListener {
            finish() // Kembali ke Activity sebelumnya
        }

        setupCurrencySpinners()

        convertButton.setOnClickListener { convertCurrency() }
    }

    private fun setupCurrencySpinners() {
        val currencies = currencyRates.keys.toList()
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, currencies)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        currencyFromSpinner.adapter = adapter
        currencyToSpinner.adapter = adapter
    }

    private fun convertCurrency() {
        val amountText = amountInput.text.toString()
        if (amountText.isBlank()) {
            Toast.makeText(this, "Please enter an amount", Toast.LENGTH_SHORT).show()
            return
        }

        val amount = amountText.toDoubleOrNull()
        if (amount == null) {
            Toast.makeText(this, "Please enter a valid number", Toast.LENGTH_SHORT).show()
            return
        }

        val fromCurrency = currencyFromSpinner.selectedItem.toString()
        val toCurrency = currencyToSpinner.selectedItem.toString()

        val convertedAmount = convert(amount, fromCurrency, toCurrency)
        resultTextView.text = String.format("%.2f", convertedAmount)
    }

    private fun convert(amount: Double, fromCurrency: String, toCurrency: String): Double {
        val fromRate = currencyRates[fromCurrency] ?: 1.0
        val toRate = currencyRates[toCurrency] ?: 1.0

        return amount / fromRate * toRate
    }
}
