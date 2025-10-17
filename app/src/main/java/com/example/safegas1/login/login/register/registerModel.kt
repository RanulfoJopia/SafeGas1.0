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
        databaseReference.get().addOnSuccessListener { snapshot ->
            val nextIdNumber = snapshot.childrenCount.toInt() + 1
            val customId = "uid" + String.format("%03d", nextIdNumber)

            val user = User(fullName, email, password, location, customId)

            databaseReference.child(customId).setValue(user)
                .addOnSuccessListener { callback.onSuccess(customId) } // ✅ return UID here
                .addOnFailureListener { e -> callback.onError(e.message ?: "Unknown error") }

        }.addOnFailureListener {
            callback.onError("Failed to generate user ID.")
        }
    }

    interface RegisterCallback {
        fun onSuccess(uid: String)   // ✅ changed to include UID
        fun onError(message: String)
    }

    data class User(
        val fullName: String = "",
        val email: String = "",
        val password: String = "",
        val location: String = "",
        val uid: String = ""
    )
}
