package com.capstone.beanalyze.ui.result

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.capstone.beanalyze.MainActivity
import com.capstone.beanalyze.databinding.ActivityResultAnalysisBinding

class ResultAnalysis : AppCompatActivity() {
    private lateinit var binding: ActivityResultAnalysisBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultAnalysisBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val imageUri = intent.getStringExtra("IMAGE_URI")?.let { Uri.parse(it) }
        imageUri?.let{
            binding.resultImage.setImageURI(it)
        }
        binding.btnBack.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}