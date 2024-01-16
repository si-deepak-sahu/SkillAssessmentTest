package com.example.skillassessmenttest.model
import com.google.gson.annotations.SerializedName

data class MatchDetailsModel(
    @SerializedName("Matchdetail")
    val matchdetail: Matchdetail,
    @SerializedName("Nuggets")
    val nuggets: List<String>,
    @SerializedName("Innings")
    val innings: List<Inning>,
    @SerializedName("Teams")
    val teams: HashMap<String, TeamData>,
    @SerializedName("Notes")
    val notes: Notes
)