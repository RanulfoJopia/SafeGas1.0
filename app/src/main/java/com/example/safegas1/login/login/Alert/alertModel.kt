package com.example.safegas1.login.login.alert

import com.google.firebase.database.*

class AlertModel {

    private val database = FirebaseDatabase.getInstance().getReference("alerts")

    fun fetchUserAlerts(uid: String, callback: (List<AlertData>?, String?) -> Unit) {
        if (uid.isEmpty()) {
            callback(null, "User UID is missing.")
            return
        }

        database.child(uid).addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val alerts = mutableListOf<AlertData>()
                if (snapshot.exists()) {
                    for (alertSnap in snapshot.children) {
                        val alertType = alertSnap.child("type").value?.toString() ?: "Unknown"
                        val alertMessage = alertSnap.child("message").value?.toString() ?: "No message"
                        val alertTime = alertSnap.child("time").value?.toString() ?: "Unknown time"
                        val alertLevel = alertSnap.child("level").value?.toString() ?: "Unknown"
                        val alertLocation = alertSnap.child("location").value?.toString() ?: "Unknown"

                        alerts.add(
                            AlertData(
                                alertType = alertType,
                                alertMessage = "$alertLevel at $alertLocation: $alertMessage",
                                alertTime = alertTime
                            )
                        )
                    }
                    callback(alerts, null)
                } else {
                    callback(emptyList(), "No alerts found for this user.")
                }
            }

            override fun onCancelled(error: DatabaseError) {
                callback(null, "Database error: ${error.message}")
            }
        })
    }

    data class AlertData(
        val alertType: String,
        val alertMessage: String,
        val alertTime: String
    )
}
