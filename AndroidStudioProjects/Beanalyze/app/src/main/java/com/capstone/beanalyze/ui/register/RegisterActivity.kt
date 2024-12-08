package com.capstone.beanalyze.ui.register

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.capstone.beanalyze.R
import com.capstone.beanalyze.databinding.ActivityRegisterBinding
import com.capstone.beanalyze.model.request.RegisterRequest
import com.capstone.beanalyze.network.ApiClient
import com.capstone.beanalyze.ui.login.LoginActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tvBackToLogin.setOnClickListener {
            val intent = Intent(this@RegisterActivity, LoginActivity::class.java)
            startActivity(intent)
        }

        binding.btnregister.setOnClickListener {
            val username = binding.etUsername.text.toString().trim()
            val password = binding.etPassword.text.toString().trim()
            val name = binding.etName.text.toString().trim()
            val city = binding.etCity.text.toString().trim()
            val email = binding.etEmail.text.toString().trim()
            if (validateInput(username, password, name, city, email)) {
                userRegister(username, password, name, city, email)
            }
        }
        binding.btnTogglePassword.setOnClickListener {
            val isPasswordVisible = binding.etPassword.inputType != android.text.InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
            if (isPasswordVisible) {
                binding.etPassword.inputType = android.text.InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
                binding.btnTogglePassword.setImageResource(R.drawable.baseline_visibility)
            } else {
                binding.etPassword.inputType = android.text.InputType.TYPE_CLASS_TEXT or android.text.InputType.TYPE_TEXT_VARIATION_PASSWORD
                binding.btnTogglePassword.setImageResource(R.drawable.baseline_visibility_off)
            }
            binding.etPassword.setSelection(binding.etPassword.text.length)
        }
    }

    private fun validateInput(
        username: String,
        password: String,
        name: String,
        city: String,
        email: String
    ): Boolean {
        var  isUserValid = true
        when {
            username.isEmpty() -> {
                binding.etUsername.error = "Username cannot be empty"
                isUserValid = false
            }
            username.contains(" ") -> {
                binding.etUsername.error = "Username cannot contain spaces!"
                isUserValid = false
            }
        }
        when {
            password.isEmpty() -> {
                binding.etPassword.error = "Password cannot be empty"
                isUserValid = false
            }
            password.length < 6 -> { // Perbaikan di sini
                binding.etPassword.error = "Password must be at least 6 characters!"
                isUserValid = false
            }
        }
        when {
            email.isEmpty() -> {
                binding.etEmail.error = "Email cannot be empty"
                isUserValid = false
            }
            !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches() -> {
                binding.etEmail.error = "Invalid email format"
                isUserValid = false
            }
        }
        if (name.isEmpty()) {
            binding.etName.error = "Name cannot be empty"
            isUserValid = false
        }
        if (city.isEmpty()) {
            binding.etCity.error = "City cannot be empty"
            isUserValid = false
        }
        return isUserValid
    }

    private fun showToast(message: String) {
        Toast.makeText(this@RegisterActivity, message, Toast.LENGTH_SHORT).show()
    }

    private fun userRegister(username: String, password: String, name: String, city: String, email: String) {
        val request = RegisterRequest(username, password, name, city, email)
        val apiService = ApiClient.create(this)

        apiService.userRegister(request).enqueue(object : Callback<com.capstone.beanalyze.model.response.Response> {
            override fun onResponse(call: Call<com.capstone.beanalyze.model.response.Response>, response: Response<com.capstone.beanalyze.model.response.Response>) {
                if (response.isSuccessful) {
                    Toast.makeText(this@RegisterActivity, "Registration successful !", Toast.LENGTH_SHORT).show()
                    navigateToLogin()
                } else {
                    Toast.makeText(this@RegisterActivity, "Registration failed", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<com.capstone.beanalyze.model.response.Response>, t: Throwable) {
                Toast.makeText(this@RegisterActivity, "Error: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }


    private fun navigateToLogin() {
        val intent = Intent(this@RegisterActivity, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }
}
