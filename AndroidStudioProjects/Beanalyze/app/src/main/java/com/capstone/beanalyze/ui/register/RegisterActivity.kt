package com.capstone.beanalyze.ui.register

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.capstone.beanalyze.databinding.ActivityRegisterBinding
import com.capstone.beanalyze.model.RegisterRequest
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
                userRegister(this@RegisterActivity, username, password, name, city, email)
            }
        }
    }
    private fun validateInput(
        username: String,
        password: String,
        name: String,
        city: String,
        email: String
    ): Boolean {
        return when {
            username.isEmpty() -> {
                showToast("Username tidak boleh kosong")
                false
            }
            password.isEmpty() -> {
                showToast("Password tidak boleh kosong")
                false
            }
            name.isEmpty() -> {
                showToast("Nama tidak boleh kosong")
                false
            }
            city.isEmpty() -> {
                showToast("Kota tidak boleh kosong")
                false
            }
            email.isEmpty() -> {
                showToast("Email tidak boleh kosong")
                false
            }
            else -> true
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(this@RegisterActivity, message, Toast.LENGTH_SHORT).show()
    }
    private fun userRegister(context: Context, username: String, password: String, name: String, city: String, email: String) {
        val request = RegisterRequest(username, password, name, city, email)
        val apiService = ApiClient.create(context)

        apiService.userRegister(request).enqueue(object : Callback<com.capstone.beanalyze.model.Response> {
            override fun onResponse(call: Call<com.capstone.beanalyze.model.Response>, response: Response<com.capstone.beanalyze.model.Response>) {
                if (response.isSuccessful) {
                    Toast.makeText(this@RegisterActivity, "Registrasi berhasil!", Toast.LENGTH_SHORT).show()
                    navigateToLogin()
                } else {
                    Toast.makeText(this@RegisterActivity, "Gagal registrasi: ${response.message()}", Toast.LENGTH_SHORT).show()
                }
            }
            override fun onFailure(call: Call<com.capstone.beanalyze.model.Response>, t: Throwable) {
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
