package com.capstone.beanalyze.model

data class DiseaseResponse(
    val diseases: List<Disease>
)

data class Disease(
    val id: Int,
    val name: String,
    val slugs: String,
    val image_url: String,
    val impact: String,
    val cause: String,
    val identification: String,
    val solution: String,
    val date_created: String,
    val date_updated: String
)
