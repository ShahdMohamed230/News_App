package com.example.news_app.Ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.example.news_app.databinding.ActivitySplashBinding

class SplashActivity : AppCompatActivity() {
    lateinit var binding:ActivitySplashBinding
    lateinit var handler: Handler
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getSupportActionBar()?.hide()
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.splashImage.animate().alpha(1f).duration=2000
        handler= Handler(Looper.getMainLooper())
        /*handler.postDelayed({
            binding.splashImage.animate().alpha(1f)
        },1000)*/
        handler.postDelayed({
            var intent= Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        },3000)
    }

}