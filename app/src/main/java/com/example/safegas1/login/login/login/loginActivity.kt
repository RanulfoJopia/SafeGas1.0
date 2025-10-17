package com.example.safegas1.login.login.login

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.safegas1.R
import com.example.safegas1.login.login.dashboard.dashboardActivity
import com.example.safegas1.login.login.register.registerActivity

class loginActivity : AppCompatActivity(), LoginView {

    private lateinit var presenter: LoginPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // ✅ Pass context to presenter
        presenter = LoginPresenter(this, this)

        val bottomCreate = findViewById<TextView>(R.id.bottomCreate)
        val btnSignIn = findViewById<Button>(R.id.btnSignIn)
        val emailField = findViewById<EditText>(R.id.email)
        val passwordField = findViewById<EditText>(R.id.password)

        bottomCreate.setOnClickListener {
            val intent = Intent(this, registerActivity::class.java)
            startActivity(intent)
        }

        btnSignIn.setOnClickListener {
            val email = emailField.text.toString().trim()
            val password = passwordField.text.toString().trim()
            presenter.loginUser(email, password)
        }
    }

    override fun onLoginSuccess(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        startActivity(Intent(this, dashboardActivity::class.java))
        finish()
    }

    override fun onLoginError(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun onValidationError(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
