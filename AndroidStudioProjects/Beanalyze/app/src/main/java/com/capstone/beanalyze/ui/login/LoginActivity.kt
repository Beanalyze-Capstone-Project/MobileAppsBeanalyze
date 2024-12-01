package com.capstone.beanalyze.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.capstone.beanalyze.MainActivity
import com.capstone.beanalyze.databinding.ActivityLoginBinding
import com.capstone.beanalyze.ui.register.RegisterActivity
import com.capstone.beanalyze.utils.SessionManager

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var loginViewModel: LoginViewModel
    private lateinit var sessionManager: SessionManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sessionManager = SessionManager(this)

        if (sessionManager.isLoggedIn()) {
            navigateToMain()
        }

        loginViewModel = ViewModelProvider(this)[LoginViewModel::class.java]

        binding.tvRegister.setOnClickListener {
            val intent = Intent(this@LoginActivity, RegisterActivity::class.java)
            startActivity(intent)
        }

        binding.btnlogin.setOnClickListener {
            val email = binding.etUsername.text.toString().trim()
            val password = binding.etPassword.text.toString().trim()

            if (email.isNotEmpty() && password.isNotEmpty()) {
                loginViewModel.loginUser(this, email, password)
            } else {
                Toast.makeText(this, "Username dan password tidak boleh kosong", Toast.LENGTH_SHORT).show()
            }
        }


        observeViewModel()
    }

    private fun observeViewModel() {
        loginViewModel.loginResult.observe(this) { result ->
            result.onSuccess { token ->
                Toast.makeText(this, "Login berhasil!", Toast.LENGTH_SHORT).show()
                sessionManager.saveLoginSession(token)
                navigateToMain()
            }
            result.onFailure { throwable ->
                Toast.makeText(this, "Login gagal: ${throwable.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun navigateToMain() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}
