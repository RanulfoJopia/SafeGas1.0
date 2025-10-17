package com.example.safegas1.login.login.history

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.safegas1.R
import com.example.safegas1.login.login.dashboard.dashboardActivity
import com.example.safegas1.login.login.alert.AlertActivity // <-- Import alertActivity
import com.example.safegas1.login.login.settings.settingsActivity

class historyActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_history)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Initialize navigation buttons
        val btnDashboard = findViewById<ImageView>(R.id.btnDashboard)
        btnDashboard.setOnClickListener {
            startActivity(Intent(this, dashboardActivity::class.java))
            // finish() // optionally close this activity
        }

        val btnAlert = findViewById<ImageView>(R.id.btnAlert)
        btnAlert.setOnClickListener {
            startActivity(Intent(this, AlertActivity::class.java)) // <-- Navigate to alertActivity
            // finish()
        }

        val btnSettings = findViewById<ImageView>(R.id.btnSettings)
        btnSettings.setOnClickListener {
            startActivity(Intent(this, settingsActivity::class.java))
            // finish()
        }
    }
}
