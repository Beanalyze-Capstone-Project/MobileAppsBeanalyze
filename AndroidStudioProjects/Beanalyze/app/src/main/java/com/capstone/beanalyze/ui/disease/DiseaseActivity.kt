package com.capstone.beanalyze.ui.disease

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.capstone.beanalyze.MainActivity
import com.capstone.beanalyze.databinding.ActivityDiseaseBinding

class DiseaseActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDiseaseBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDiseaseBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val diseaseImage = intent.getStringExtra("DISEASE_IMAGE")
        val diseaseName = intent.getStringExtra("DISEASE_NAME")
        val diseaseImpact = intent.getStringExtra("DISEASE_IMPACT")
        val diseaseCause = intent.getStringExtra("DISEASE_CAUSE")
        val diseaseIdentification = intent.getStringExtra("DISEASE_IDENTIFICATION")
        val diseaseSolution = intent.getStringExtra("DISEASE_SOLUTION")

        Glide.with(this)
            .load(diseaseImage)
            .into(binding.imgDisease)
        binding.tvDiseaseTxt.text = diseaseName
        binding.tvImpactTxt.text = diseaseImpact
        binding.tvCauseTxt.text = diseaseCause
        binding.tvIdentificationTxt.text = diseaseIdentification
        binding.tvSolutiontxt.text = diseaseSolution
        binding.btnBack.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}