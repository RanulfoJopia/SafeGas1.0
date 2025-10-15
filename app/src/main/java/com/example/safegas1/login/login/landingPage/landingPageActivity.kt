package com.example.safegas1.login.login.landingPage

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.safegas1.R
import com.example.safegas1.login.login.login.loginActivity  // ✅ Corrected import

class landingPageActivity : AppCompatActivity() {

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_landing_page)

        // Handle window insets for full-screen layouts
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // ✅ Find the Get Started button
        val btnGetStarted = findViewById<Button>(R.id.get_started_button)

        // ✅ Navigate to Login Activity when clicked
        btnGetStarted.setOnClickListener {
            val intent = Intent(this, loginActivity::class.java)
            startActivity(intent)

            // Optional: close this activity so user can’t go back using back button
            finish()
        }
    }
}
