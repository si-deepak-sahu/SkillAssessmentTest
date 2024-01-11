package com.example.skillassessmenttest.model


import com.google.gson.annotations.SerializedName

data class BatsmenX(
    @SerializedName("Balls")
    val balls: String,
    @SerializedName("Batsman")
    val batsman: String,
    @SerializedName("Runs")
    val runs: String
)