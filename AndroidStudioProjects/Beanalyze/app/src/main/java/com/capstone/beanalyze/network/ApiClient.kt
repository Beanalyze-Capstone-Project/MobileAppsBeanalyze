package com.capstone.beanalyze.network

import android.content.Context
import com.capstone.beanalyze.BuildConfig
import com.capstone.beanalyze.utils.SessionManager
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {

    fun create(context: Context): ApiService {
        val sessionManager = SessionManager(context)

        val client = OkHttpClient.Builder().addInterceptor { chain ->
            val token = sessionManager.getToken() ?: ""
            val request = chain.request().newBuilder()
                .addHeader("Authorization", "Bearer $token")
                .build()
            chain.proceed(request)
        }.build()

        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
}
