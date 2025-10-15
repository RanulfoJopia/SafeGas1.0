package com.example.safegas1.login.login.register

import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.safegas1.R

class registerActivity : AppCompatActivity(), RegisterView {

    private lateinit var etFullName: EditText
    private lateinit var etEmail: EditText
    private lateinit var etPassword: EditText
    private lateinit var etConfirmPassword: EditText
    private lateinit var etLocation: EditText
    private lateinit var checkTerms: CheckBox
    private lateinit var btnCreateAccount: Button
    private lateinit var progressBar: ProgressBar

    private lateinit var presenter: RegisterPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_register)

        // ✅ Apply window insets
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.root)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // ✅ Initialize views
        etFullName = findViewById(R.id.etFullName)
        etEmail = findViewById(R.id.etEmail)
        etPassword = findViewById(R.id.etPassword)
        etConfirmPassword = findViewById(R.id.etConfirmPassword)
        etLocation = findViewById(R.id.etLocation)
        checkTerms = findViewById(R.id.checkTerms)
        btnCreateAccount = findViewById(R.id.btnCreateAccount)

        // Optional: add a progress indicator for loading state
        progressBar = ProgressBar(this).apply { visibility = View.GONE }

        // ✅ Initialize presenter
        presenter = RegisterPresenter(this)

        // ✅ Button click
        btnCreateAccount.setOnClickListener {
            val fullName = etFullName.text.toString().trim()
            val email = etEmail.text.toString().trim()
            val password = etPassword.text.toString().trim()
            val confirmPassword = etConfirmPassword.text.toString().trim()
            val location = etLocation.text.toString().trim()
            val agreed = checkTerms.isChecked

            presenter.register(fullName, email, password, confirmPassword, location, agreed)
        }
    }

    // 🔹 Implement RegisterView interface methods
    override fun onRegisterLoading(isLoading: Boolean) {
        btnCreateAccount.isEnabled = !isLoading
        if (isLoading) {
            Toast.makeText(this, "Registering...", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onRegisterSuccess() {
        Toast.makeText(this, "Registration successful!", Toast.LENGTH_LONG).show()
        finish() // or navigate to login screen
    }

    override fun onRegisterError(message: String) {
        Toast.makeText(this, "Error: $message", Toast.LENGTH_SHORT).show()
    }
}
