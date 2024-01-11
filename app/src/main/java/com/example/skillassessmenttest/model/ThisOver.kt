package com.example.skillassessmenttest.model


import com.google.gson.annotations.SerializedName

data class ThisOver(
    @SerializedName("B")
    val b: String,
    @SerializedName("T")
    val t: String
)