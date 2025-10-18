package com.example.safegas1.login.login.dashboard

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.safegas1.R
import com.example.safegas1.login.login.alert.alertActivity
import com.example.safegas1.login.login.history.historyActivity
import com.example.safegas1.login.login.settings.settingsActivity

class dashboardActivity : AppCompatActivity(), DashboardView {

    private lateinit var presenter: DashboardPresenter

    // UI
    private lateinit var tvPpm: TextView
    private lateinit var tvStatus: TextView
    private lateinit var tvLocation: TextView
    private lateinit var tvActiveAlertsValue: TextView
    private lateinit var tvOnlineDevicesValue: TextView
    private lateinit var tvTodayAverageValue: TextView
    private lateinit var tvPeakValue: TextView
    private lateinit var tvLastUpdated: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        // Initialize UI
        tvPpm = findViewById(R.id.tvPpm)
        tvStatus = findViewById(R.id.tvStatus)
        tvLocation = findViewById(R.id.tvLocation)
        tvActiveAlertsValue = findViewById(R.id.tvActiveAlertsValue)
        tvOnlineDevicesValue = findViewById(R.id.tvOnlineDevicesValue)
        tvTodayAverageValue = findViewById(R.id.tvTodayAverageValue)
        tvPeakValue = findViewById(R.id.tvPeakValue)
        tvLastUpdated = findViewById(R.id.tvLastUpdated)

        // Initialize Presenter
        presenter = DashboardPresenter(this, this)

        // Fetch the data
        presenter.fetchSensorData()

        // Buttons
        findViewById<ImageView>(R.id.btnAlert).setOnClickListener {
            startActivity(Intent(this, alertActivity::class.java))
        }

        findViewById<ImageView>(R.id.btnHistory).setOnClickListener {
            startActivity(Intent(this, historyActivity::class.java))
        }

        findViewById<ImageView>(R.id.btnSettings).setOnClickListener {
            startActivity(Intent(this, settingsActivity::class.java))
        }
    }

    override fun showData(sensorData: SensorData) {
        tvPpm.text = "${sensorData.ppm} ppm"
        tvStatus.text = sensorData.status.ifEmpty { "Sensor status unknown" }
        tvLocation.text = sensorData.location.ifEmpty { "Unknown location" }
        tvActiveAlertsValue.text = sensorData.activeAlerts.toString()
        tvOnlineDevicesValue.text = sensorData.onlineDevices.toString()
        tvTodayAverageValue.text = "${sensorData.todayAverage} ppm"
        tvPeakValue.text = "${sensorData.peakToday} ppm"
        tvLastUpdated.text = "Last updated: ${sensorData.lastUpdated}"
    }

    override fun hideData() {
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
