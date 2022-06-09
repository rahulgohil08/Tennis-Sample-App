package com.theworld.androidtemplatewithnavcomponents

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.theworld.androidtemplatewithnavcomponents.utils.startNewActivity

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Handler(Looper.getMainLooper()).postDelayed({
            startNewActivity(MainActivity::class.java)
            finish()
        }, 2000)
    }
}