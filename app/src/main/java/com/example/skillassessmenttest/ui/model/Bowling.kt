package com.example.skillassessmenttest.ui.model


import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Bowling(
    @SerializedName("Average")
    val average: String,
    @SerializedName("Economyrate")
    val economyrate: String,
    @SerializedName("Style")
    val style: String,
    @SerializedName("Wickets")
    val wickets: String
): Serializable