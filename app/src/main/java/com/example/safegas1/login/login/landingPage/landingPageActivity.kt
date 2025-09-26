package com.example.safegas1.login.login.landingPage

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button   // ✅ use normal Android Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.safegas1.R
import com.example.safegas1.login.login.login.loginActivity  // ✅ your login activity

class landingPageActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_landing_page)

        // ✅ Window insets
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // ✅ Find the normal button
        val btnGetStarted = findViewById<Button>(R.id.get_started_button)

        // ✅ On click → go to loginActivity
        btnGetStarted.setOnClickListener {
            val intent = Intent(this, loginActivity::class.java)
            startActivity(intent)

            // Optional: finish landing page so user can’t go back
            finish()
        }
    }
}
