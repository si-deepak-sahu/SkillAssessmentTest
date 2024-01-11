package com.example.skillassessmenttest.model


import com.google.gson.annotations.SerializedName

data class Officials(
    @SerializedName("Referee")
    val referee: String,
    @SerializedName("Umpires")
    val umpires: String
)