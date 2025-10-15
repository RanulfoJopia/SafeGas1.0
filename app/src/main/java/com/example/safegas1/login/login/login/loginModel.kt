package com.example.safegas1.login

import com.google.firebase.database.*

class LoginModel {

    private val database: DatabaseReference = FirebaseDatabase.getInstance().getReference("users")

    interface LoginCallback {
        fun onSuccess()
        fun onFailure(message: String)
    }

    fun authenticateUser(email: String, password: String, callback: LoginCallback) {
        val query = database.orderByChild("email").equalTo(email)

        query.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    for (userSnap in snapshot.children) {
                        val dbPassword = userSnap.child("password").value.toString()
                        if (dbPassword == password) {
                            callback.onSuccess()
                            return
                        }
                    }
                    callback.onFailure("Invalid password")
                } else {
                    callback.onFailure("User not found")
                }
            }

            override fun onCancelled(error: DatabaseError) {
                callback.onFailure("Database error: ${error.message}")
            }
        })
    }
}
