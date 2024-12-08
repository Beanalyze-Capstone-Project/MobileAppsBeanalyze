package com.capstone.beanalyze.ui.disease

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.capstone.beanalyze.databinding.FragmentDiseaseListBinding
import com.capstone.beanalyze.model.Disease
import com.capstone.beanalyze.model.DiseaseResponse
import com.capstone.beanalyze.network.ApiClient
import com.capstone.beanalyze.ui.adapter.AdapterInfo
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DiseaseListFragment : Fragment() {

    private var _binding: FragmentDiseaseListBinding? = null
    private val binding: FragmentDiseaseListBinding
        get() = _binding ?: throw IllegalStateException("Binding is accessed after being destroyed")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDiseaseListBinding.inflate(inflater, container, false)
        fetchDiseases()
        return _binding?.root
    }

    private fun fetchDiseases() {
        _binding?.let { binding ->
            binding.rvDisease.visibility = View.GONE
            binding.loadingAnimate.visibility = View.VISIBLE

            val apiService = ApiClient.create(requireContext())
            apiService.getDiseases().enqueue(object : Callback<DiseaseResponse> {
                override fun onResponse(call: Call<DiseaseResponse>, response: Response<DiseaseResponse>) {
                    binding.loadingAnimate.visibility = View.GONE
                    if (response.isSuccessful) {
                        val diseaseList = response.body()?.diseases.orEmpty()
                        setupRecyclerView(diseaseList)
                        binding.rvDisease.visibility = View.VISIBLE
                    } else {
                        Toast.makeText(requireContext(), "Failed to fetch data", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<DiseaseResponse>, t: Throwable) {
                    binding.loadingAnimate.visibility = View.GONE
                    Toast.makeText(requireContext(), "Error: ${t.message}", Toast.LENGTH_SHORT).show()
                }
            })
        }
    }

    private fun setupRecyclerView(diseaseList: List<Disease>) {
        _binding?.rvDisease?.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = AdapterInfo(diseaseList)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
