package com.example.skillassessmenttest.networking

import com.example.skillassessmenttest.model.MatchDetailsModel
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("nzin01312019187360.json")
    fun getMatchDetails(): Call<MatchDetailsModel>
}