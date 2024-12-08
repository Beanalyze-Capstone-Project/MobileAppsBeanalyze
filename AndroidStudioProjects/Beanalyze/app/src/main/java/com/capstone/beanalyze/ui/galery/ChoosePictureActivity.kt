package com.capstone.beanalyze.ui.galery

import android.app.ProgressDialog
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.capstone.beanalyze.MainActivity
import com.capstone.beanalyze.databinding.ActivityChoosePictureBinding
import com.capstone.beanalyze.model.response.ClassificationResponse
import com.capstone.beanalyze.network.ApiClient
import com.capstone.beanalyze.ui.result.ResultAnalysis
import com.capstone.beanalyze.utils.SessionManager
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File
import java.io.FileOutputStream

class ChoosePictureActivity : AppCompatActivity() {

    private lateinit var sessionManager: SessionManager
    private lateinit var binding: ActivityChoosePictureBinding
    private var currentImageUri: Uri? = null
    private lateinit var progressDialog: ProgressDialog
    private val galleryLauncher = registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
        uri?.let {
            currentImageUri = it
            binding.ivPicture.setImageURI(it)
        } ?: run {
            Toast.makeText(this, "No image selected", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChoosePictureBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sessionManager = SessionManager(this)

        binding.btnChoosePicture.setOnClickListener {
            selectImage()
        }

        binding.btnAnalysis.setOnClickListener {
            analyzeImage()
        }

        binding.tvBack.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }

    private fun selectImage() {
        galleryLauncher.launch("image/*")
    }

    private fun analyzeImage() {
        currentImageUri?.let { uri ->
            val imageFile = getFileFromUri(uri)
            if (imageFile != null) {
                classifyImage(imageFile)
            } else {
                Toast.makeText(this, "Failed to get image", Toast.LENGTH_SHORT).show()
            }
        } ?: Toast.makeText(this, "Select an image first", Toast.LENGTH_SHORT).show()
    }

    private fun getFileFromUri(uri: Uri): File? {
        return try {
            val inputStream = contentResolver.openInputStream(uri)
            val tempFile = File.createTempFile("image", ".jpg", cacheDir)
            tempFile.deleteOnExit()
            inputStream?.use { input ->
                FileOutputStream(tempFile).use { output ->
                    input.copyTo(output)
                }
            }
            compressImage(tempFile)
        } catch (e: Exception) {
            Log.e("FileConversion", "Error converting image", e)
            null
        }
    }

    private fun compressImage(file: File): File {
        return try {
            val bitmap = BitmapFactory.decodeFile(file.path)
            val outputFile = File(cacheDir, "compressed_image.jpg")
            FileOutputStream(outputFile).use { out ->
                bitmap.compress(Bitmap.CompressFormat.JPEG, 50, out)
            }
            outputFile
        } catch (e: Exception) {
            Log.e("ImageCompression", "Error compressing image", e)
            file
        }
    }

    private fun classifyImage(imageFile: File) {
        showProgressDialog()

        val apiService = ApiClient.create(this)
        val requestBody = imageFile.asRequestBody("image/jpeg".toMediaTypeOrNull())
        val imagePart = MultipartBody.Part.createFormData("image", imageFile.name, requestBody)

        apiService.classifyImage(imagePart).enqueue(object : Callback<ClassificationResponse> {
            override fun onResponse(
                call: Call<ClassificationResponse>,
                response: Response<ClassificationResponse>
            ) {
                hideProgressDialog()
                if (response.isSuccessful) {
                    response.body()?.let { result ->
                        navigateToResult(result)
                    }
                } else {
                    handleError(response.code())
                }
            }

            override fun onFailure(call: Call<ClassificationResponse>, t: Throwable) {
                hideProgressDialog()
                Toast.makeText(this@ChoosePictureActivity, "Network error: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun showProgressDialog() {
        progressDialog = ProgressDialog(this).apply {
            setMessage("Uploading image...")
            setCancelable(false)
            show()
        }
    }

    private fun hideProgressDialog() {
        if (::progressDialog.isInitialized && progressDialog.isShowing) {
            progressDialog.dismiss()
        }
    }

    private fun navigateToResult(result: ClassificationResponse) {
        val intent = Intent(this, ResultAnalysis::class.java).apply {
            putExtra("RESULT", result)
            putExtra("IMAGE_URI", currentImageUri.toString())
        }
        startActivity(intent)
    }

    private fun handleError(code: Int) {
        when (code) {
            400 -> showError("Image is required.")
            401 -> showError("Unauthorized. Please log in again.")
            404 -> showError("Disease type not found.")
            500 -> showError("Server error. Please try again later.")
            else -> showError("Unexpected error occurred.")
        }
    }

    private fun showError(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }
}
