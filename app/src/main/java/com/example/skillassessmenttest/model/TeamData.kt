package com.example.skillassessmenttest.model


import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class TeamData(
    @SerializedName("Name_Full")
    val nameFull: String,
    @SerializedName("Name_Short")
    val nameShort: String,
    @SerializedName("Players")
    val players: HashMap<String, PlayerInfoData>
):Serializable