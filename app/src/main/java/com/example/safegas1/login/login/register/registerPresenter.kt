package com.example.safegas1.login.login.register

import android.content.Context

class RegisterPresenter(private val view: RegisterView, private val context: Context) {

    private val model = RegisterModel()

    fun register(
        fullName: String,
        email: String,
        password: String,
        confirmPassword: String,
        location: String,
        agreed: Boolean
    ) {
        if (fullName.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            view.onRegisterError("Please fill in all required fields.")
            return
        }

        if (password != confirmPassword) {
            view.onRegisterError("Passwords do not match.")
            return
        }

        if (!agreed) {
            view.onRegisterError("You must agree to the Terms and Privacy Policy.")
            return
        }

        view.onRegisterLoading(true)

        model.registerUser(fullName, email, password, location, object : RegisterModel.RegisterCallback {
            override fun onSuccess(uid: String) {
                view.onRegisterLoading(false)

                // ✅ Save UID locally
                val sharedPref = context.getSharedPreferences("UserPrefs", Context.MODE_PRIVATE)
                sharedPref.edit().putString("user_uid", uid).apply()

                view.onRegisterSuccess()
            }

            override fun onError(message: String) {
                view.onRegisterLoading(false)
                view.onRegisterError(message)
            }
        })
    }
}
