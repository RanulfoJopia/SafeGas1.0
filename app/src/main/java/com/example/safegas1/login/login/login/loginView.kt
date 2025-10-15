package com.example.safegas1.login.login.login


interface LoginView {
    fun onLoginSuccess(message: String)
    fun onLoginError(message: String)
    fun onValidationError(message: String)
}
