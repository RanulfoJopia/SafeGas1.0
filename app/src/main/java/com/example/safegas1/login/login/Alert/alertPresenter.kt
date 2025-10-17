package com.example.safegas1.login.login.alert

import android.content.Context
import android.widget.Toast

class AlertPresenter(private val view: AlertView, private val context: Context) {

    private val model = AlertModel()

    fun loadUserAlerts() {
        view.showLoading(true)

        val sharedPref = context.getSharedPreferences("UserPrefs", Context.MODE_PRIVATE)
        val uid = sharedPref.getString("user_uid", null)

        if (uid == null) {
            view.showLoading(false)
            view.showError("User not logged in.")
            return
        }

        model.fetchUserAlerts(uid) { alerts, error ->
            view.showLoading(false)
            if (error != null) {
                view.showError(error)
            } else if (alerts != null && alerts.isNotEmpty()) {
                view.showLatestAlert(alerts.last()) // show newest
                view.showAlertList(alerts.reversed()) // show list
            } else {
                view.showError("No alerts available.")
            }
        }
    }
}
