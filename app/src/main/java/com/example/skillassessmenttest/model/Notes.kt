package com.example.skillassessmenttest.model


import com.google.gson.annotations.SerializedName

data class Notes(
    @SerializedName("1")
    val x1: List<String>,
    @SerializedName("2")
    val x2: List<String>
)