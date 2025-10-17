package com.example.safegas1.login.login.login

import android.content.Context
import com.google.firebase.database.*

class LoginPresenter(private val view: LoginView, private val context: Context) {

    private val database: DatabaseReference = FirebaseDatabase.getInstance().getReference("users")

    fun loginUser(email: String, password: String) {
        if (email.isEmpty() || password.isEmpty()) {
            view.onValidationError("Please fill in all fields")
            return
        }

        val query = database.orderByChild("email").equalTo(email)

        query.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    for (userSnap in snapshot.children) {
                        val dbPassword = userSnap.child("password").value.toString()
                        if (dbPassword == password) {

                            // ✅ Get uid from Firebase
                            val uid = userSnap.child("uid").value.toString()

                            // ✅ Save to SharedPreferences
                            val sharedPref = context.getSharedPreferences("UserPrefs", Context.MODE_PRIVATE)
                            sharedPref.edit().putString("user_uid", uid).apply()

                            view.onLoginSuccess("Login successful")
                            return
                        }
                    }
                    view.onLoginError("Invalid password")
                } else {
                    view.onLoginError("User not found")
                }
            }

            override fun onCancelled(error: DatabaseError) {
                view.onLoginError("Database error: ${error.message}")
            }
        })
    }
}
