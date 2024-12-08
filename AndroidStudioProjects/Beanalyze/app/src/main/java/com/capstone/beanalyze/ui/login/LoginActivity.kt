package com.capstone.beanalyze.ui.login

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.capstone.beanalyze.MainActivity
import com.capstone.beanalyze.R
import com.capstone.beanalyze.databinding.ActivityLoginBinding
import com.capstone.beanalyze.model.LoginRequest
import com.capstone.beanalyze.model.Response
import com.capstone.beanalyze.network.ApiClient
import com.capstone.beanalyze.ui.register.RegisterActivity
import com.capstone.beanalyze.utils.SessionManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response as RetrofitResponse

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var sessionManager: SessionManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sessionManager = SessionManager(this)

        binding.tvRegister.setOnClickListener {
            val intent = Intent(this@LoginActivity, RegisterActivity::class.java)
            startActivity(intent)
        }

        binding.btnlogin.setOnClickListener {
            val email = binding.etUsername.text.toString().trim()
            val password = binding.etPassword.text.toString().trim()

            if (email.isNotEmpty() && password.isNotEmpty()) {
                loginUser(email, password)
            } else {
                Toast.makeText(this, "Username and password cannot be empty", Toast.LENGTH_SHORT).show()
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

    override fun onResume() {
        super.onResume()

        if (sessionManager.isLoggedIn()) {
            navigateToMain()
        }
    }

    private fun loginUser(email: String, password: String) {
        val apiService = ApiClient.create(this)
        val request = LoginRequest(email, password)

        apiService.userLogin(request).enqueue(object : Callback<Response> {
            override fun onResponse(call: Call<Response>, response: retrofit2.Response<Response>) {
                if (response.isSuccessful) {
                    val token = response.body()?.token
                    if (token != null) {
                        sessionManager.saveLoginSession(token)
                        Toast.makeText(this@LoginActivity, "Login successful !", Toast.LENGTH_SHORT).show()
                        navigateToMain()
                    } else {
                        Toast.makeText(this@LoginActivity, "Token invalid!", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(this@LoginActivity, "Login failure", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<Response>, t: Throwable) {
                Toast.makeText(this@LoginActivity, "", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun navigateToMain() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}
