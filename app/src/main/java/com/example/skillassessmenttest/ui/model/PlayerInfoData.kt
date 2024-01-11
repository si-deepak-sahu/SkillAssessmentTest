package com.example.skillassessmenttest.ui.model


import com.google.gson.annotations.SerializedName

data class PlayerInfoData(
    @SerializedName("Batting")
    val batting: Batting,
    @SerializedName("Bowling")
    val bowling: Bowling,
    @SerializedName("Iscaptain")
    val iscaptain: Boolean,
    @SerializedName("Iskeeper")
    val iskeeper: Boolean,
    @SerializedName("Name_Full")
    val nameFull: String,
    @SerializedName("Position")
    val position: String
)