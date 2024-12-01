package com.capstone.beanalyze.ui.login

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.capstone.beanalyze.model.LoginRequest
import com.capstone.beanalyze.model.Response
import com.capstone.beanalyze.network.ApiClient
import retrofit2.Call
import retrofit2.Callback

class LoginViewModel : ViewModel() {

    private val _loginResult = MutableLiveData<Result<String>>()
    val loginResult: LiveData<Result<String>> get() = _loginResult

    fun loginUser(context: Context, email: String, password: String) {
        val request = LoginRequest(email, password)
        val apiService = ApiClient.create(context)
        apiService.userLogin(request).enqueue(object : Callback<Response> {
            override fun onResponse(call: Call<Response>, response: retrofit2.Response<Response>) {
                if (response.isSuccessful) {
                    val token = response.body()?.token
                    _loginResult.postValue(Result.success(token ?: ""))
                } else {
                    _loginResult.postValue(Result.failure(Throwable("Login gagal: ${response.message()}")))
                }
            }
            override fun onFailure(call: Call<Response>, t: Throwable) {
                _loginResult.postValue(Result.failure(t))
            }
        })
    }

}