package com.example.uts_dio

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        // Menunggu selama 3 detik sebelum berpindah ke MainActivity
        Handler().postDelayed({
            val intent = Intent(this@SplashActivity, MainActivity::class.java)
            startActivity(intent)
            finish() // Menutup Splash Screen setelah berpindah ke MainActivity
        }, 3000) // Durasi splash screen dalam milidetik (3000ms = 3 detik)
    }
}
