package com.example.skillassessmenttest.model


import com.google.gson.annotations.SerializedName
import java.io.Serializable

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
): Serializable