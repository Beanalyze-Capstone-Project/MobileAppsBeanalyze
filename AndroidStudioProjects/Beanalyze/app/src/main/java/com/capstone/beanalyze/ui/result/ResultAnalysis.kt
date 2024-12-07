package com.capstone.beanalyze.ui.result

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.capstone.beanalyze.MainActivity
import com.capstone.beanalyze.databinding.ActivityResultAnalysisBinding
import com.capstone.beanalyze.model.ClassificationResponse
import java.io.FileNotFoundException

class ResultAnalysis : AppCompatActivity() {

    private lateinit var binding: ActivityResultAnalysisBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultAnalysisBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val result = intent.getParcelableExtra<ClassificationResponse>("RESULT")
        val imageUriString = intent.getStringExtra("IMAGE_URI")

        if (imageUriString.isNullOrEmpty()) {
            Toast.makeText(this, "Image data is missing", Toast.LENGTH_SHORT).show()
            finish()
            return
        }

        val imageUri = Uri.parse(imageUriString)
        val bitmap = uriToBitmap(this, imageUri)
        if (bitmap != null) {
            binding.ivResult.setImageBitmap(bitmap)
        } else {
            Toast.makeText(this, "Failed to load image", Toast.LENGTH_SHORT).show()
        }

        if (result != null) {
            displayResult(result)
        } else {
            Toast.makeText(this, "No analysis result available", Toast.LENGTH_SHORT).show()
        }

        binding.btnBack.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }

    private fun displayResult(result: ClassificationResponse) {
        binding.tvDiseaseName.text = result.disease_name
        binding.tvConfidence.text = "${result.confident}%"
        binding.tvImpact.text = result.impact
        binding.tvCause.text = result.cause
        binding.tvIdentification.text = result.identification
        binding.tvSolution.text = result.solution
    }

    private fun uriToBitmap(context: Context, uri: Uri): Bitmap? {
        return try {
            val inputStream = context.contentResolver.openInputStream(uri)
            BitmapFactory.decodeStream(inputStream)
        } catch (e: FileNotFoundException) {
            e.printStackTrace()
            null
        }
    }
}
