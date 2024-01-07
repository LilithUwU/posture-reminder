package com.example.straightspinereminder

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.animation.Animation
import android.view.animation.ScaleAnimation
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        val appNameTv: TextView = findViewById(R.id.app_name_tv)
        val scaleAnimation = ScaleAnimation(
            0.1f,
            1.0f,
            0.1f,
            1.0f,
            Animation.RELATIVE_TO_SELF, 0.5f,  // pivotX
            Animation.RELATIVE_TO_SELF, 0.5f   // pivotY
        )
        scaleAnimation.duration = 1000
        appNameTv.startAnimation(scaleAnimation)

        Handler(Looper.getMainLooper()).postDelayed(
            {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            }, 1000
        )
    }
}