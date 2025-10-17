package com.example.safegas1.login.login.settings

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.safegas1.R
import com.example.safegas1.login.login.alert.AlertActivity
import com.example.safegas1.login.login.dashboard.dashboardActivity
import com.example.safegas1.login.login.history.historyActivity

class settingsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_settings)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Initialize dashboard button and set click listener
        val btnDashboard = findViewById<ImageView>(R.id.btnDashboard)
        btnDashboard.setOnClickListener {
            val intent = Intent(this, dashboardActivity::class.java) // Navigate to dashboardActivity, not settingsActivity
            startActivity(intent)
        }

        // Initialize alert button and set click listener
        val btnAlert = findViewById<ImageView>(R.id.btnAlert)
        btnAlert.setOnClickListener {
            val intent = Intent(this, AlertActivity::class.java)
            startActivity(intent)
        }

        // Initialize history button and set click listener
        val btnHistory = findViewById<ImageView>(R.id.btnHistory)
        btnHistory.setOnClickListener {
            val intent = Intent(this, historyActivity::class.java)
            startActivity(intent)
        }
    }
}
