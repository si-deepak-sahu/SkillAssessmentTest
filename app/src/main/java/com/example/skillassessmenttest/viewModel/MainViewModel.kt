package com.example.skillassessmenttest.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.skillassessmenttest.model.MatchDetailsModel
import com.example.skillassessmenttest.networking.ApiConfig
import com.example.skillassessmenttest.ui.model.TeamData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel : ViewModel() {

    private val _matchData = MutableLiveData<MatchDetailsModel>()
    val matchData: LiveData<MatchDetailsModel> get() = _matchData

    private val _teamData = MutableLiveData<TeamData>()
    val teamData: LiveData<TeamData> get() = _teamData

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> get() = _isLoading

    private val _isError = MutableLiveData<Boolean>()
    val isError: LiveData<Boolean> get() = _isError

    var errorMessage: String = ""
        private set

    init {
        fetchMatchData()
    }
    private fun fetchMatchData() {

        _isLoading.value = true
        _isError.value = false

        val client = ApiConfig.getApiService().getMatchDetails()

        // Send API request using Retrofit
        client.enqueue(object : Callback<MatchDetailsModel> {

            override fun onResponse(
                call: Call<MatchDetailsModel>,
                response: Response<MatchDetailsModel>
            ) {
                val responseBody = response.body()
                if (!response.isSuccessful || responseBody == null) {
                    onError("Data Processing Error")
                    return
                }

                _isLoading.value = false
                _matchData.postValue(responseBody)
            }

            override fun onFailure(call: Call<MatchDetailsModel>, t: Throwable) {
                onError(t.message)
                t.printStackTrace()
            }

        })
    }

    private fun onError(inputMessage: String?) {

        val message = if (inputMessage.isNullOrBlank() or inputMessage.isNullOrEmpty()) "Unknown Error"
        else inputMessage

        errorMessage = StringBuilder("ERROR: ")
            .append("$message some data may not displayed properly").toString()

        _isError.value = true
        _isLoading.value = false
    }

}