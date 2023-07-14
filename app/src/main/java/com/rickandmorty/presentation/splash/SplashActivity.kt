package com.rickandmorty.presentation.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.rickandmorty.databinding.ActivitySplashBinding
import com.rickandmorty.presentation.home.CharacterListActivity

class SplashActivity : AppCompatActivity() {

    lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        splashScreenDuration()
    }

    private fun splashScreenDuration() {
        val splashScreenDuration = 3000L // 3 segundos
        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this@SplashActivity, CharacterListActivity::class.java)
            startActivity(intent)
            finish()
        }, splashScreenDuration)
    }
}