package com.example.safegas1.login.login.dashboard

data class SensorData(
    val activeAlerts: Int = 0,
    val lastUpdated: String = "",
    val location: String = "",
    val onlineDevices: Int = 0,
    val peakToday: Int = 0,
    val ppm: Int = 0,
    val status: String = "",
    val todayAverage: Int = 0
)
