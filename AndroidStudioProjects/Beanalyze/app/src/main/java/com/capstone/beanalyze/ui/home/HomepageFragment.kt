package com.capstone.beanalyze.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.capstone.beanalyze.databinding.FragmentHomepageBinding
import com.capstone.beanalyze.ui.camera.CameraActivity
import com.capstone.beanalyze.ui.galery.ChoosePictureActivity
import com.capstone.beanalyze.ui.profile.ProfileActivity

class HomepageFragment : Fragment() {

    private var _binding: FragmentHomepageBinding? = null
    private val binding: FragmentHomepageBinding
        get() = _binding ?: throw IllegalStateException("Binding is accessed after being destroyed")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomepageBinding.inflate(inflater, container, false)
        setupCardClickListeners()
        return _binding?.root
    }

    private fun setupCardClickListeners() {
        _binding?.let { binding ->
            binding.cardTakePhoto.setOnClickListener {
                val intent = Intent(requireContext(), CameraActivity::class.java)
                startActivity(intent)
            }

            binding.cardGalery.setOnClickListener {
                val intent = Intent(requireContext(), ChoosePictureActivity::class.java)
                startActivity(intent)
            }

            binding.cardProfile.setOnClickListener {
                val intent = Intent(requireContext(), ProfileActivity::class.java)
                startActivity(intent)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
