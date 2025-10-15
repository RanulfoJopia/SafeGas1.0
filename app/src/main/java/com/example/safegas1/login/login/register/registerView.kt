package com.example.safegas1.login.login.register

interface RegisterView {
    fun onRegisterLoading(isLoading: Boolean)
    fun onRegisterSuccess()
    fun onRegisterError(message: String)
}
