package com.capstone.beanalyze.network

import com.capstone.beanalyze.model.DiseaseResponse
import com.capstone.beanalyze.model.LoginRequest
import com.capstone.beanalyze.model.ProfileResponse
import com.capstone.beanalyze.model.RegisterRequest
import com.capstone.beanalyze.model.Response
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {
    @POST("register")
    fun userRegister(@Body request: RegisterRequest): Call<Response>

    @POST("login")
    fun userLogin(@Body request: LoginRequest): Call<Response>

    @GET("disease")
    fun getDiseases(): Call<DiseaseResponse>

    @GET("user")
    fun getProfileUser(): Call<ProfileResponse>
}