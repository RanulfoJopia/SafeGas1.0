package com.example.safegas1.login.login.dashboard

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.safegas1.R
import com.example.safegas1.login.login.Alert.alertActivity  // Import your Alert activity class
import com.example.safegas1.login.login.history.historyActivity

class dashboardActivity : AppCompatActivity(), DashboardView {

    // UI elements
    private lateinit var tvPpm: TextView
    private lateinit var tvStatus: TextView
    private lateinit var tvLocation: TextView
    private lateinit var tvActiveAlertsValue: TextView
    private lateinit var tvOnlineDevicesValue: TextView
    private lateinit var tvTodayAverageValue: TextView
    private lateinit var tvPeakValue: TextView
    private lateinit var tvLastUpdated: TextView
    private lateinit var imgSensor: ImageView

    private lateinit var presenter: DashboardPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)  // your dashboard layout XML

        // Initialize UI elements
        tvPpm = findViewById(R.id.tvPpm)
        tvStatus = findViewById(R.id.tvStatus)
        tvLocation = findViewById(R.id.tvLocation)
        tvActiveAlertsValue = findViewById(R.id.tvActiveAlertsValue)
        tvOnlineDevicesValue = findViewById(R.id.tvOnlineDevicesValue)
        tvTodayAverageValue = findViewById(R.id.tvTodayAverageValue)
        tvPeakValue = findViewById(R.id.tvPeakValue)
        tvLastUpdated = findViewById(R.id.tvLastUpdated)
        imgSensor = findViewById(R.id.imgSensor)

        // Initialize presenter
        presenter = DashboardPresenter(this)

        // Fetch sensor data
        presenter.fetchSensorData()

        // Initialize alert button and set click listener
        val btnAlert = findViewById<ImageView>(R.id.btnAlert)
        btnAlert.setOnClickListener {
            val intent = Intent(this, alertActivity::class.java)
            startActivity(intent)
        }

// Initialize history button and set click listener
        val btnHistory = findViewById<ImageView>(R.id.btnHistory)
        btnHistory.setOnClickListener {
            val intent = Intent(this, historyActivity::class.java)
            startActivity(intent)
        }

    }

    override fun showData(sensorData: SensorData) {
        tvPpm.text = "${sensorData.ppm} ppm"
        tvStatus.text = if (sensorData.status.isNotEmpty()) sensorData.status else "Sensor status unknown"
        tvLocation.text = if (sensorData.location.isNotEmpty()) sensorData.location else "Unknown location"
        tvActiveAlertsValue.text = sensorData.activeAlerts.toString()
        tvOnlineDevicesValue.text = sensorData.onlineDevices.toString()
        tvTodayAverageValue.text = "${sensorData.todayAverage} ppm"
        tvPeakValue.text = "${sensorData.peakToday} ppm"
        tvLastUpdated.text = "Last updated: ${sensorData.lastUpdated}"
    }

    override fun hideData() {
        // Clear or reset UI to default values
        tvPpm.text = "0 ppm"
        tvStatus.text = "Sensor status unknown"
        tvLocation.text = "Unknown location"
        tvActiveAlertsValue.text = "0"
        tvOnlineDevicesValue.text = "0"
        tvTodayAverageValue.text = "0 ppm"
        tvPeakValue.text = "0 ppm"
        tvLastUpdated.text = "Last updated: N/A"
    }

    override fun showError(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }
}
