package com.example.skillassessmenttest.ui.screens

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.skillassessmenttest.R
import com.example.skillassessmenttest.model.Batting
import com.example.skillassessmenttest.model.Bowling
import com.example.skillassessmenttest.model.PlayerInfoData
import com.example.skillassessmenttest.model.TeamData
import com.example.skillassessmenttest.ui.component.FilledButtonComposable
import com.example.skillassessmenttest.ui.component.ImageComposable
import com.example.skillassessmenttest.ui.component.NoRippleFilledButtonComposable
import com.example.skillassessmenttest.ui.component.OutlinedButtonComposable
import com.example.skillassessmenttest.ui.component.TextComposable
import com.example.skillassessmenttest.ui.theme.SkillAssessmentTestTheme
import com.example.skillassessmenttest.viewModel.MainViewModel

var toolbarTitle = ""
var captain = "Captain 🎖️"
var wicketKeeper = "Wicket Keeper 🧤"
lateinit var list: HashMap<String, TeamData>
private var mainViewModel = MainViewModel()
var bowlingData: Bowling? = null
var battingData: Batting? = null
var countryName: String? = "All"
var mergeList = ArrayList<PlayerInfoData>()

class PlayersList : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        list = intent.getSerializableExtra("list") as HashMap<String, TeamData>
        listModification("All")
        setContent {
            SkillAssessmentTestTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxWidth(),
                    color = colorResource(R.color.off_white)
                ) {
                    PlayerDetailsScreenUi(mainViewModel.teamListData)
                    PopUpUi()
                }
            }
        }
    }
}

fun listModification(s: String) {
    if (list.isNotEmpty()) {
        for (i in list) {
            val key = i.key
            val title = list[key]?.nameFull.toString().trim()
            if (title.trim() == s) {
                toolbarTitle = if (title == "India") {
                    "Indian Players"
                } else {
                    "$title Players"
                }
                mainViewModel.setTeamListData(list[key]?.players?.values?.let { ArrayList(it) })
                break
            } else if (s == "All") {
                toolbarTitle = "All Players"
                list[key]?.players?.values?.let { mergeList.addAll(it) }
                mainViewModel.setTeamListData(mergeList)
            }
        }
    }
}


@Composable
fun PlayerDetailsScreenUi(listData: List<PlayerInfoData>) {

    DisposableEffect(key1 = Unit, effect = {
        onDispose {
            //Code inside will work as the last thing after leaving the screen
            countryName = "All"
            mainViewModel.clearTeamListData()
        }
    })

    Column {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(colorResource(id = R.color.light_blue))
        ) {
            TextComposable(
                1,
                toolbarTitle,
                colorResource(R.color.off_white),
                22.sp,
                FontWeight.Bold,
                Modifier
                    .padding(10.dp, 15.dp)
                    .align(Alignment.TopCenter),
                false
            )
        }
        Box {
            LazyColumn(
                contentPadding = PaddingValues(bottom = 120.dp),
                modifier = Modifier
                    .fillMaxSize()
            )
            {
                if (listData.isNotEmpty()) {
                    itemsIndexed(listData) { index, player ->
                        CardItem(player) {
                            battingData = player.batting
                            bowlingData = player.bowling
                            mainViewModel.openDialog()
                            mainViewModel.SetPlayerName(mainViewModel.teamListData[index].nameFull)
                        }
                    }
                }
            }
            Row(
                Modifier
                    .height(IntrinsicSize.Min)
                    .padding(10.dp)
                    .align(Alignment.BottomCenter)
            ) {
                FilterButtons()
            }
        }
    }
}

@Composable
fun FilterButtons() {
    var filter by remember { mutableStateOf("All") }

    when (filter) {
        "All" -> {
            Column {
                Row {
                    OutlinedButton(
                        stringResource(R.string.india),
                        Modifier
                            .padding(2.dp)
                            .width(130.dp)
                    ) {
                        mainViewModel.clearTeamListData()
                        filter = "India"
                        countryName = "India"
                        listModification(countryName.toString())
                    }
                    OutlinedButton(
                        stringResource(R.string.new_zealand),
                        Modifier
                            .padding(2.dp)
                            .width(130.dp)
                    ) {
                        mainViewModel.clearTeamListData()
                        filter = "New Zealand"
                        countryName = "New Zealand"
                        listModification(countryName.toString())
                    }
                }
                FilledButton(
                    stringResource(R.string.all),
                    Modifier
                        .padding(2.dp)
                        .width(260.dp)
                ) {
                    mainViewModel.clearTeamListData()
                    filter = "All"
                    countryName = "All"
                    listModification(countryName.toString())
                }
            }
        }
        "India" -> {
            Column {
                Row {
                    FilledButton(
                        stringResource(R.string.india),
                        Modifier
                            .padding(2.dp)
                            .width(130.dp)
                    ) {
                        mainViewModel.clearTeamListData()
                        filter = "India"
                        countryName = "India"
                        listModification(countryName.toString())
                    }
                    OutlinedButton(
                        stringResource(R.string.new_zealand),
                        Modifier
                            .padding(2.dp)
                            .width(130.dp)
                    ) {
                        mainViewModel.clearTeamListData()
                        filter = "New Zealand"
                        countryName = "New Zealand"
                        listModification(countryName.toString())
                    }
                }
                OutlinedButton(
                    stringResource(R.string.all),
                    Modifier
                        .padding(2.dp)
                        .width(260.dp)
                ) {
                    mainViewModel.clearTeamListData()
                    filter = "All"
                    countryName = "All"
                    listModification(countryName.toString())
                }
            }
        }
        "New Zealand" -> {
            Column {
                Row {
                    OutlinedButton(
                        stringResource(R.string.india),
                        Modifier
                            .padding(2.dp)
                            .width(130.dp)
                    ) {
                        mainViewModel.clearTeamListData()
                        filter = "India"
                        countryName = "India"
                        listModification(countryName.toString())
                    }
                    FilledButton(
                        stringResource(R.string.new_zealand),
                        Modifier
                            .padding(2.dp)
                            .width(130.dp)
                    ) {
                        mainViewModel.clearTeamListData()
                        filter = "New Zealand"
                        countryName = "New Zealand"
                        listModification(countryName.toString())
                    }
                }
                OutlinedButton(
                    stringResource(R.string.all),
                    Modifier
                        .padding(2.dp)
                        .width(260.dp)
                ) {
                    mainViewModel.clearTeamListData()
                    filter = "All"
                    countryName = "All"
                    listModification(countryName.toString())
                }
            }
        }
    }
}

@Composable
private fun OutlinedButton(teamName: String, modifier: Modifier, clickAction: () -> Unit) {
    OutlinedButtonComposable(
        ButtonDefaults.buttonColors(colorResource(id = R.color.white)),
        modifier = modifier,
        teamName,
        FontWeight.Bold,
    ) {
        run { clickAction.invoke() }
    }
}

@Composable
private fun FilledButton(teamName: String, modifier: Modifier, clickAction: () -> Unit) {
    FilledButtonComposable(
        ButtonDefaults.buttonColors(colorResource(id = R.color.lavendar)),
        modifier = modifier,
        teamName,
        FontWeight.Bold,
    ) {
        run { clickAction.invoke() }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun CardItem(player: PlayerInfoData, onClickStartSource: () -> Unit) {
    ElevatedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
        colors = CardDefaults.cardColors(
            containerColor = colorResource(R.color.off_white),
        ),
        shape = RoundedCornerShape(15.dp),
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp)
            .padding(10.dp, 5.dp),
        onClick = { onClickStartSource.invoke() },
    ) {
        Row {
            ImageComposable(
                painterResource(id = R.drawable.player),
                "Player Image",
                ContentScale.Crop,
                modifier = Modifier
                    .width(130.dp)
                    .clip(RoundedCornerShape(10))
                    .background(colorResource(R.color.light_yellow))
            )
            Column(
                verticalArrangement = Arrangement.Bottom,
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(0.dp, 40.dp)
            ) {
                BoldFontText(player.nameFull.uppercase(), Modifier.padding(10.dp, 0.dp))
                Row {
                    if (player.iscaptain) NormalFontText(captain, Modifier.padding(10.dp, 0.dp, 0.dp, 0.dp)) else NormalFontText("", Modifier.padding(0.dp, 0.dp, 0.dp, 0.dp))
                    if (player.iskeeper) NormalFontText(wicketKeeper, Modifier.padding(10.dp, 0.dp, 0.dp, 0.dp)) else NormalFontText("", Modifier.padding(0.dp, 0.dp, 0.dp, 0.dp))
                }
                if (player.iskeeper && player.iscaptain) {
                    NormalFontText("$captain |", Modifier.padding(10.dp, 0.dp, 0.dp, 0.dp))
                    NormalFontText(wicketKeeper, Modifier.padding(10.dp, 0.dp, 0.dp, 0.dp))
                }
            }
        }
    }
}

@Composable
private fun NormalFontText(text: String, modifier: Modifier) {
    TextComposable(
        1,
        text,
        colorResource(R.color.gray),
        14.sp,
        FontWeight.Normal,
        modifier,
        false
    )
}

@Composable
fun PopUpUi() {
    if (mainViewModel.isPopUpShown) {
        Dialog(onDismissRequest = {}, properties = DialogProperties(usePlatformDefaultWidth = false)) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(colorResource(R.color.transparent))
            ) {
                ElevatedCard(
                    elevation = CardDefaults.cardElevation(
                        defaultElevation = 6.dp
                    ),
                    colors = CardDefaults.cardColors(
                        containerColor = colorResource(R.color.off_white),
                    ),
                    shape = RoundedCornerShape(15.dp),
                    modifier = Modifier
                        .padding(40.dp, 20.dp, 40.dp, 40.dp) //margin
                        .fillMaxWidth()
                        .padding(10.dp, 5.dp) //padding
                        .align(Alignment.Center)
                ) {
                    Column {
                        Box(modifier = Modifier.height(200.dp)) {
                            ImageComposable(
                                painterResource(id = R.drawable.player),
                                "Player Image",
                                ContentScale.Inside,
                                modifier = Modifier
                                    .padding(5.dp)
                                    .fillMaxWidth()
                                    .size(200.dp)
                                    .clip(RoundedCornerShape(5))
                                    .background(colorResource(R.color.light_yellow))
                            )
                            IconButton(
                                onClick = {
                                    mainViewModel.closeDialog()
                                },
                                modifier = Modifier
                                    .padding(10.dp)
                                    .clip(CircleShape)
                                    .background(White)
                                    .size(20.dp)
                                    .align(Alignment.TopEnd)
                            ) {
                                Icon(
                                    imageVector = Icons.Rounded.Close,
                                    contentDescription = "",
                                    Modifier.size(15.dp)
                                )
                            }
                            BoldFontText(
                                mainViewModel.playerName,
                                Modifier
                                    .padding(5.dp)
                                    .fillMaxWidth()
                                    .clip(RoundedCornerShape(0.dp, 0.dp, 10.dp, 10.dp))
                                    .align(Alignment.BottomStart)
                                    .background(
                                        brush = Brush.verticalGradient(
                                            colors = listOf(
                                                colorResource(R.color.transparent),
                                                colorResource(R.color.light_yellow)
                                            )
                                        )
                                    )
                                    .padding(10.dp, 15.dp, 10.dp, 2.dp)
                            )
                        }
                        if (bowlingData != null && battingData != null) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(0.dp, 0.dp, 0.dp, 10.dp),
                                Arrangement.SpaceEvenly
                            ) {
                                Column {
                                    NoRippleFilledButtonComposable(
                                        ButtonDefaults.buttonColors(colorResource(id = R.color.lavendar)),
                                        Modifier.padding(0.dp, 5.dp),
                                        "Batting",
                                        FontWeight.Bold,
                                    ) {}
                                    if (battingData!!.style.isNotEmpty()) {
                                        BoldFontText(battingData!!.style, Modifier.padding(10.dp, 0.dp))
                                        NormalFontText("Style", Modifier.padding(10.dp, 0.dp, 0.dp, 7.dp))
                                    }
                                    if (battingData!!.average.isNotEmpty()) {
                                        BoldFontText(battingData!!.average, Modifier.padding(10.dp, 0.dp))
                                        NormalFontText("Average", Modifier.padding(10.dp, 0.dp, 0.dp, 7.dp))
                                    }
                                    if (battingData!!.strikerate.isNotEmpty()) {
                                        BoldFontText(battingData!!.strikerate, Modifier.padding(10.dp, 0.dp))
                                        NormalFontText("Strike-rate", Modifier.padding(10.dp, 0.dp, 0.dp, 7.dp))
                                    }
                                    if (battingData!!.runs.isNotEmpty()) {
                                        BoldFontText(battingData!!.runs, Modifier.padding(10.dp, 0.dp))
                                        NormalFontText("Runs", Modifier.padding(10.dp, 0.dp, 0.dp, 7.dp))
                                    }
                                }
                                Column {
                                    NoRippleFilledButtonComposable(
                                        ButtonDefaults.buttonColors(colorResource(id = R.color.lavendar)),
                                        Modifier.padding(0.dp, 5.dp),
                                        "Bowling",
                                        FontWeight.Bold,
                                    ) {}
                                    if (bowlingData!!.style.isNotEmpty()) {
                                        BoldFontText(bowlingData!!.style, Modifier.padding(10.dp, 0.dp))
                                        NormalFontText("Style", Modifier.padding(10.dp, 0.dp, 0.dp, 7.dp))
                                    }
                                    if (bowlingData!!.average.isNotEmpty()) {
                                        BoldFontText(bowlingData!!.average, Modifier.padding(10.dp, 0.dp))
                                        NormalFontText("Average", Modifier.padding(10.dp, 0.dp, 0.dp, 7.dp))
                                    }
                                    if (bowlingData!!.economyrate.isNotEmpty()) {
                                        BoldFontText(bowlingData!!.economyrate, Modifier.padding(10.dp, 0.dp))
                                        NormalFontText("Economy-rate", Modifier.padding(10.dp, 0.dp, 0.dp, 7.dp))
                                    }
                                    if (bowlingData!!.wickets.isNotEmpty()) {
                                        BoldFontText(bowlingData!!.wickets, Modifier.padding(10.dp, 0.dp))
                                        NormalFontText("Wickets", Modifier.padding(10.dp, 0.dp, 0.dp, 7.dp))
                                    }
                                }
                            }
                        }
                    }

                }
            }

        }
    }
}

@Composable
private fun BoldFontText(text: String, modifier: Modifier) {
    TextComposable(
        1,
        text,
        colorResource(R.color.black),
        20.sp,
        FontWeight.Bold,
        modifier,
        false
    )
}

//@Preview(showBackground = true)
//@Composable
//fun UiPreview() {
//    PlayerDetailsScreenUi(teamListData)
//    PopUpUi()
//}

