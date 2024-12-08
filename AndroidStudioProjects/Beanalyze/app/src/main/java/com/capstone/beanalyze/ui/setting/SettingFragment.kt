package com.capstone.beanalyze.ui.setting

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.capstone.beanalyze.databinding.FragmentSettingBinding
import com.capstone.beanalyze.model.response.ProfileResponse
import com.capstone.beanalyze.network.ApiClient
import com.capstone.beanalyze.ui.login.LoginActivity
import com.capstone.beanalyze.utils.SessionManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SettingFragment : Fragment() {

    private var _binding: FragmentSettingBinding? = null
    private val binding: FragmentSettingBinding
        get() = _binding ?: throw IllegalStateException("Binding is accessed after being destroyed")
    private var sessionManager: SessionManager? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSettingBinding.inflate(inflater, container, false)
        sessionManager = SessionManager(requireContext())

        fetchProfile()

        binding.btnBack.setOnClickListener {
            logoutUser()
        }

        return _binding?.root
    }

    private fun fetchProfile() {
        binding.loadingAnimate.visibility = View.VISIBLE
        val apiService = ApiClient.create(requireContext())
        apiService.getProfileUser().enqueue(object : Callback<ProfileResponse> {
            override fun onResponse(call: Call<ProfileResponse>, response: Response<ProfileResponse>) {
                binding.loadingAnimate.visibility = View.GONE
                if (response.isSuccessful) {
                    response.body()?.let { profile ->
                        showProfile(profile)
                    } ?: run {
                        Toast.makeText(requireContext(), "Profile not found", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(requireContext(), "Failed to fetch profile: ${response.message()}", Toast.LENGTH_SHORT).show()
                }
            }
            override fun onFailure(call: Call<ProfileResponse>, t: Throwable) {
                binding.loadingAnimate.visibility = View.GONE
                Toast.makeText(requireContext(), "Error: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun logoutUser() {
        sessionManager?.logout()
        val intent = Intent(requireContext(), LoginActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        sessionManager = null
    }

    private fun showProfile(profile: ProfileResponse) {
        _binding?.let { binding ->
            binding.settingUsername.text = profile.username
            binding.settingEmail.text = profile.email
        }
    }
}
