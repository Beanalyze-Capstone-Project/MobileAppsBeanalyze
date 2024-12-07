package com.capstone.beanalyze.utils

import android.content.Context
import android.content.SharedPreferences
import android.util.Log

class SessionManager(context: Context) {
    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("UserSession", Context.MODE_PRIVATE)

    fun saveLoginSession(token: String) {
        sharedPreferences.edit()
            .putString("TOKEN", token)
            .putBoolean("IS_LOGGED_IN", true)
            .apply()
        Log.d("SessionManager", "Token saved successfully")
    }

    fun isLoggedIn(): Boolean =
        sharedPreferences.getBoolean("IS_LOGGED_IN", false)

    fun getToken(): String? =
        sharedPreferences.getString("TOKEN", null)

    fun logout() {
        sharedPreferences.edit().clear().apply()
    }
}