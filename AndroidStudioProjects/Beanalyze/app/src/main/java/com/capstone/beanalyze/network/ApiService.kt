package com.capstone.beanalyze.network

import com.capstone.beanalyze.model.ClassificationResponse
import com.capstone.beanalyze.model.DiseaseResponse
import com.capstone.beanalyze.model.LoginRequest
import com.capstone.beanalyze.model.ProfileResponse
import com.capstone.beanalyze.model.RegisterRequest
import com.capstone.beanalyze.model.Response
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface ApiService {
    @POST("register")
    fun userRegister(@Body request: RegisterRequest): Call<Response>

    @POST("login")
    fun userLogin(@Body request: LoginRequest): Call<Response>

    @GET("disease")
    fun getDiseases(): Call<DiseaseResponse>

    @GET("user")
    fun getProfileUser(): Call<ProfileResponse>

    @Multipart
    @POST("classification")
    fun classifyImage(
        @Part image: MultipartBody.Part
    ): Call<ClassificationResponse>
}