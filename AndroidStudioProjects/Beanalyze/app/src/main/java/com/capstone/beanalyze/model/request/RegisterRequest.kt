package com.capstone.beanalyze.model.request

data class RegisterRequest (
    val username: String,
    val password: String,
    val name: String,
    val city: String,
    val email: String
    )