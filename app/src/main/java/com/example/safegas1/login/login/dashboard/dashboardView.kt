package com.example.safegas1.login.login.dashboard

interface DashboardView {
    fun showData(sensorData: SensorData)
    fun hideData()
    fun showError(message: String)
}
