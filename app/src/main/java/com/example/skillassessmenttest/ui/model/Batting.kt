package com.example.skillassessmenttest.ui.model


import com.google.gson.annotations.SerializedName

data class Batting(
    @SerializedName("Average")
    val average: String,
    @SerializedName("Runs")
    val runs: String,
    @SerializedName("Strikerate")
    val strikerate: String,
    @SerializedName("Style")
    val style: String
)