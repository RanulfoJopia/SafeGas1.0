package com.example.safegas1.login.login.register

import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class RegisterModel {

    private val databaseReference: DatabaseReference =
        FirebaseDatabase.getInstance().getReference("users")

    fun registerUser(
        fullName: String,
        email: String,
        password: String,
        location: String,
        callback: RegisterCallback
    ) {
        val userId = databaseReference.push().key ?: return callback.onError("Database error.")

        val user = User(fullName, email, password, location)

        databaseReference.child(userId).setValue(user)
            .addOnSuccessListener { callback.onSuccess() }
            .addOnFailureListener { e -> callback.onError(e.message ?: "Unknown error") }
    }

    interface RegisterCallback {
        fun onSuccess()
        fun onError(message: String)
    }

    data class User(
        val fullName: String = "",
        val email: String = "",
        val password: String = "",
        val location: String = ""
    )
}
