package com.example.safegas1.login.login.dashboard

import com.google.firebase.database.FirebaseDatabase

class DashboardPresenter(private val view: DashboardView) {

    // Hardcoded user UID from your Firebase database JSON
    private val uid = "uid123"

    fun fetchSensorData() {
        val databaseRef = FirebaseDatabase.getInstance().getReference("$uid/dashboard")

        databaseRef.get()
            .addOnSuccessListener { snapshot ->
                if (snapshot.exists()) {
                    val data = snapshot.getValue(SensorData::class.java)
                    if (data != null) {
                        view.showData(data)
                    } else {
                        view.hideData()
                        view.showError("Sensor data is missing.")
                    }
                } else {
                    view.hideData()
                    view.showError("No sensor data found.")
                }
            }
            .addOnFailureListener { error ->
                view.hideData()
                view.showError("Failed to fetch data: ${error.message}")
            }
    }
}
