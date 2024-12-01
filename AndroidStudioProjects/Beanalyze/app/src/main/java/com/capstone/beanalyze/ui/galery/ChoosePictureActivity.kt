package com.capstone.beanalyze.ui.galery

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.capstone.beanalyze.MainActivity
import com.capstone.beanalyze.databinding.ActivityChoosePictureBinding
import com.capstone.beanalyze.ui.result.ResultAnalysis

class ChoosePictureActivity : AppCompatActivity() {

    private lateinit var binding: ActivityChoosePictureBinding
    private var currentImageUri: Uri? = null
    private val galeryLauncher = registerForActivityResult(ActivityResultContracts.GetContent())
    { uri: Uri? -> uri?.let {
        currentImageUri = it
        binding.ivPicture.setImageURI(it)
    } ?: run {
        Toast.makeText(this, "Tidak ada gambar yang dipilih", Toast.LENGTH_SHORT).
        show() }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChoosePictureBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnChoosePicture.setOnClickListener{
            galeryLauncher.launch("image/*")
        }
        binding.btnAnalysis.setOnClickListener{
            if (currentImageUri != null){
                val intent = Intent(this, ResultAnalysis::class.java).apply {
                    putExtra("IMAGE_URI",currentImageUri.toString())
                }
                startActivity(intent)
            } else {
                Toast.makeText(this, "Pilih gambar terlebih dahulu", Toast.LENGTH_SHORT).
                show()
            }
        }
        binding.tvBack.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}