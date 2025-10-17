package com.example.safegas1.login.login.alert

interface AlertView {
    fun showLatestAlert(alert: AlertModel.AlertData)
    fun showAlertList(alerts: List<AlertModel.AlertData>)
    fun showError(message: String)
    fun showLoading(isLoading: Boolean)
}
