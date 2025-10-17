package com.example.safegas1.login.login.alert

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.safegas1.R
import com.example.safegas1.login.login.dashboard.dashboardActivity
import com.example.safegas1.login.login.history.historyActivity
import com.example.safegas1.login.login.settings.settingsActivity

class AlertActivity : AppCompatActivity(), AlertView {

    private lateinit var presenter: AlertPresenter
    private lateinit var alertsContainer: LinearLayout
    private lateinit var alertTypeText: TextView
    private lateinit var alertMessageText: TextView
    private lateinit var alertTimeText: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_alert)

        alertsContainer = findViewById(R.id.alertsContainer)
        alertTypeText = findViewById(R.id.alertTypeText)
        alertMessageText = findViewById(R.id.alertMessageText)
        alertTimeText = findViewById(R.id.alertTimeText)

        presenter = AlertPresenter(this, this)
        presenter.loadUserAlerts()
    }

    override fun showLatestAlert(alert: AlertModel.AlertData) {
        alertTypeText.text = "Type: ${alert.alertType}"
        alertMessageText.text = "Message: ${alert.alertMessage}"
        alertTimeText.text = "Time: ${alert.alertTime}"
    }

    override fun showAlertList(alerts: List<AlertModel.AlertData>) {
        alertsContainer.removeAllViews()

        for (alert in alerts) {
            val item = TextView(this)
            item.text = "• ${alert.alertType} - ${alert.alertMessage} (${alert.alertTime})"
            item.textSize = 14f
            item.setPadding(8, 8, 8, 8)
            alertsContainer.addView(item)
        }
        findViewById<ImageView>(R.id.btnDashboard).setOnClickListener {
            startActivity(Intent(this, dashboardActivity::class.java))
        }

        findViewById<ImageView>(R.id.btnHistory).setOnClickListener {
            startActivity(Intent(this, historyActivity::class.java))
        }

        findViewById<ImageView>(R.id.btnSettings).setOnClickListener {
            startActivity(Intent(this, settingsActivity::class.java))
        }
    }

    override fun showError(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun showLoading(isLoading: Boolean) {
        // optional: add progress indicator
    }
}
