package com.example.safegas1.login.login.login

import com.google.firebase.database.*

class LoginPresenter(private val view: LoginView) {

    private val database: DatabaseReference = FirebaseDatabase.getInstance().getReference("users")

    fun loginUser(email: String, password: String) {
        if (email.isEmpty() || password.isEmpty()) {
            view.onValidationError("Please fill in all fields")
            return
        }

        // Query Firebase for user with matching email
        val query = database.orderByChild("email").equalTo(email)

        query.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    for (userSnap in snapshot.children) {
                        val dbPassword = userSnap.child("password").value.toString()
                        if (dbPassword == password) {
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
