package com.example.skillassessmenttest.model


import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Batting(
    @SerializedName("Average")
    val average: String,
    @SerializedName("Runs")
    val runs: String,
    @SerializedName("Strikerate")
    val strikerate: String,
    @SerializedName("Style")
    val style: String
): Serializable