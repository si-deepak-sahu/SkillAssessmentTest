package com.example.skillassessmenttest.ui.screens

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.skillassessmenttest.R
import com.example.skillassessmenttest.model.MatchDetailsModel
import com.example.skillassessmenttest.ui.component.FilledButtonComposable
import com.example.skillassessmenttest.ui.component.ImageComposable
import com.example.skillassessmenttest.ui.component.TextComposable
import com.example.skillassessmenttest.ui.model.TeamData
import com.example.skillassessmenttest.ui.theme.SkillAssessmentTestTheme
import com.example.skillassessmenttest.viewModel.MainViewModel

var date = ""
var time = ""
var venue = ""
var dateTimeVenue = ""

var teamName = "India"
var matchBw = ""
var teamData: HashMap<String, TeamData>? = null
var viewModel = MainViewModel()

class MatchDetails : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SkillAssessmentTestTheme {
                Surface(modifier = Modifier.fillMaxWidth()) {
                    MatchDetailsScreenUi()
                }
            }
        }

    }
}

@Composable
fun MatchDetailsScreenUi() {
    val context = LocalContext.current
    val apiData = viewModel.matchData.observeAsState().value
    SetDataToUi(apiData)

    ImageComposable(
        painterResource(id = R.drawable.team),
        "Team Image",
        ContentScale.Crop,
        Modifier
            .fillMaxWidth()
            .height(400.dp)
    )
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(400.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextComposable(
            3,
            dateTimeVenue,
            colorResource(R.color.off_white),
            14.sp,
            FontWeight.Normal,
            Modifier
                .padding(10.dp, 40.dp)
                .weight(1f)
        )
        TextComposable(
            3,
            matchBw,
            colorResource(R.color.white),
            30.sp,
            FontWeight.Bold,
            Modifier.padding(10.dp, 10.dp)
        )

        FilledButtonComposable(
            ButtonDefaults.buttonColors(colorResource(id = R.color.lavendar)),
            Modifier.padding(10.dp),
            "Match Details",
            FontWeight.Bold,
        ) {
            context.startActivity(Intent(context, PlayersDetails::class.java).apply {
                putExtra("list", teamData)
            })
        }
    }
}

@Composable
private fun SetDataToUi(apiData: MatchDetailsModel?) {
    if (apiData != null) {
        date = apiData.matchdetail.match.date
        time = apiData.matchdetail.match.time
        venue = apiData.matchdetail.venue.name
        dateTimeVenue = "$date | $time\n$venue"

        teamData = apiData.teams

        val strBuilder = StringBuilder()
        if (teamData != null) {
            for (i in teamData!!) {
                val key = i.key
                teamName = teamData!![key]?.nameFull.toString()
                strBuilder.appendLine(teamName).append("vs\n")
            }
        }

        if (strBuilder.isNotEmpty()) matchBw = strBuilder.substring(0, strBuilder.length - 4)
    }
}


//@Preview(showBackground = true)
//@Composable
//fun UiPreview() {
//    Ui()
//}