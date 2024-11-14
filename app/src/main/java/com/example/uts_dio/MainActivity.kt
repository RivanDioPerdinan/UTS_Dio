package com.example.uts_dio

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation)

        // Set up item selection listener for the BottomNavigationView
        bottomNavigationView.setOnItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.nav_temperature -> {
                    val intent = Intent(this, TemperatureActivity::class.java)
                    startActivity(intent)
                    true
                }
                R.id.nav_calculator -> {
                    val intent = Intent(this, CalculatorActivity::class.java)
                    startActivity(intent)
                    true
                }
                R.id.nav_bmi -> {
                    val intent = Intent(this, BMIActivity::class.java)
                    startActivity(intent)
                    true
                }
                R.id.nav_money_convert -> {
                    val intent = Intent(this, MoneyConvertActivity::class.java)
                    startActivity(intent)
                    true
                }
                else -> false
            }
        }
    }
}
