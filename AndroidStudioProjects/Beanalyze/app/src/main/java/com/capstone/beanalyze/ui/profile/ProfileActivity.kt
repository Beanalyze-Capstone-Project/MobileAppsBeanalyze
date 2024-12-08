package com.capstone.beanalyze.ui.profile

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.capstone.beanalyze.MainActivity
import com.capstone.beanalyze.databinding.ActivityProfileBinding
import com.capstone.beanalyze.model.response.ProfileResponse
import com.capstone.beanalyze.network.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProfileActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)
        fetchProfile()
        binding.btnHome.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }

    private fun fetchProfile() {
        binding.loadingAnimate.visibility = View.VISIBLE
        val apiService = ApiClient.create(this)
        apiService.getProfileUser().enqueue(object : Callback<ProfileResponse> {
            override fun onResponse(call: Call<ProfileResponse>, response: Response<ProfileResponse>) {
                binding.loadingAnimate.visibility = View.GONE
                if (response.isSuccessful) {
                    val profile = response.body()
                    if (profile != null) {
                        showProfile(profile)
                    } else {
                        Toast.makeText(this@ProfileActivity, "Profile not found", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(this@ProfileActivity, "Failed to fetch profile: ${response.message()}", Toast.LENGTH_SHORT).show()
                }
            }
            override fun onFailure(call: Call<ProfileResponse>, t: Throwable) {
                binding.loadingAnimate.visibility = View.GONE
                Toast.makeText(this@ProfileActivity, "Error: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun showProfile(profile: ProfileResponse) {
        binding.tvUsernameTxt.text = profile.username
        binding.tvEmailTxt.text = profile.email
        binding.tvNameTxt.text = profile.name
        binding.tvCityTxt.text = profile.city
    }
}
