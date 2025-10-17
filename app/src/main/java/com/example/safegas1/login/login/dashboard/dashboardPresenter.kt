package com.example.safegas1.login.login.dashboard

import android.content.Context
import com.google.firebase.database.FirebaseDatabase

class DashboardPresenter(
    private val context: Context,
    private val view: DashboardView
) {

    private val database = FirebaseDatabase.getInstance().getReference("sensors")

    fun fetchSensorData() {
        // Get UID from SharedPreferences
        val sharedPref = context.getSharedPreferences("UserPrefs", Context.MODE_PRIVATE)
        val uid = sharedPref.getString("user_uid", null)

        if (uid == null) {
            view.showError("User not logged in. Please log in again.")
            view.hideData()
            return
        }

        // Fetch user’s dashboard data
        database.child(uid).child("dashboard").get()
            .addOnSuccessListener { snapshot ->
                if (snapshot.exists()) {
                    val data = snapshot.getValue(SensorData::class.java)
                    if (data != null) {
                        view.showData(data)
                    } else {
                        view.showError("Invalid dashboard data format.")
                        view.hideData()
                    }
                } else {
                    view.showError("No dashboard data found for this user.")
                    view.hideData()
                }
            }
            .addOnFailureListener { error ->
                view.showError("Failed to fetch data: ${error.message}")
                view.hideData()
            }
    }
}
