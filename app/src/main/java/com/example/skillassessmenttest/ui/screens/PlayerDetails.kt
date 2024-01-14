package com.example.skillassessmenttest.ui.screens

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.skillassessmenttest.R
import com.example.skillassessmenttest.ui.component.FilledButtonComposable
import com.example.skillassessmenttest.ui.component.ImageComposable
import com.example.skillassessmenttest.ui.component.OutlinedButtonComposable
import com.example.skillassessmenttest.ui.component.TextComposable
import com.example.skillassessmenttest.ui.model.PlayerInfoData
import com.example.skillassessmenttest.ui.model.TeamData
import com.example.skillassessmenttest.ui.theme.SkillAssessmentTestTheme

var toolbarTitle = ""
var captain = "Captain 🎖️"
var wicketKeeper = "Wicket Keeper 🧤"
lateinit var list: HashMap<String, TeamData>
var teamListData: ArrayList<PlayerInfoData>? = null

class PlayersDetails : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SkillAssessmentTestTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxWidth(),
                    color = colorResource(R.color.off_white)
                ) {
                    list = intent.getSerializableExtra("list") as HashMap<String, TeamData>
                    listModification("India")
                    PlayerDetailsScreenUi(teamListData)
                    PopUpUi(true)
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
                toolbarTitle = "$title Players"
                teamListData = list[key]?.players?.values?.let { ArrayList(it) }
                break
            }
        }
    }
}


@Composable
fun PlayerDetailsScreenUi(list: ArrayList<PlayerInfoData>?) {
    var popUpVisible by remember { mutableStateOf(false) }

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
                    .align(Alignment.TopCenter)
            )
        }
        Box {
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                if (!list.isNullOrEmpty()) items(list) { player ->
                    CardItem(player) {
                        popUpVisible = true
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
    var filter by remember { mutableStateOf(true) }

    if (filter) {
        FilledButton("India") {
            filter = true
            listModification("India")
        }
        OutlinedButton("New Zealand") {
            filter = false
            listModification("New Zealand")
        }
    } else {
        OutlinedButton("India") {
            filter = true
            listModification("India")
        }
        FilledButton("New Zealand") {
            filter = false
            listModification("New Zealand")
        }
    }
}

@Composable
private fun OutlinedButton(teamName: String, clickAction: () -> Unit) {
    OutlinedButtonComposable(
        ButtonDefaults.buttonColors(colorResource(id = R.color.white)),
        Modifier
            .padding(2.dp)
            .width(130.dp),
        teamName,
        FontWeight.Bold,
    ) {
        run { clickAction.invoke() }
    }
}

@Composable
private fun FilledButton(teamName: String, clickAction: () -> Unit) {
    FilledButtonComposable(
        ButtonDefaults.buttonColors(colorResource(id = R.color.lavendar)),
        Modifier
            .padding(2.dp)
            .width(130.dp),
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
                BoldFontText(player.nameFull.uppercase())
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
        modifier
    )
}

@Composable
fun PopUpUi(isVisible: Boolean) {
    if (isVisible) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(colorResource(R.color.background))
        ) {
            ElevatedCard(
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 6.dp
                ),
                colors = CardDefaults.cardColors(
                    containerColor = colorResource(R.color.lavendar),
                ),
                shape = RoundedCornerShape(15.dp),
                modifier = Modifier
                    .padding(40.dp, 20.dp, 40.dp, 40.dp) //margin
                    .fillMaxWidth()
                    .height(470.dp)
                    .padding(10.dp, 5.dp) //padding
                    .align(Alignment.Center)
            ) {
                Box {
                    Column {
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
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(0.dp, 0.dp, 0.dp, 10.dp),
                            Arrangement.SpaceEvenly
                        ) {
                            Column {
                                FilledButtonComposable(
                                    ButtonDefaults.buttonColors(colorResource(id = R.color.purple_200)),
                                    Modifier.padding(0.dp),
                                    "Batting",
                                    FontWeight.Bold,
                                ) {}
                                BoldFontText("RHB")
                                NormalFontText("Style", Modifier.padding(10.dp, 0.dp, 0.dp, 0.dp))
                                BoldFontText("31.03")
                                NormalFontText("Average", Modifier.padding(10.dp, 0.dp, 0.dp, 0.dp))
                                BoldFontText("73.7")
                                NormalFontText("Strike-rate", Modifier.padding(10.dp, 0.dp, 0.dp, 0.dp))
                                BoldFontText("1738")
                                NormalFontText("Runs", Modifier.padding(10.dp, 0.dp, 0.dp, 0.dp))
                            }
                            Column {
                                FilledButtonComposable(
                                    ButtonDefaults.buttonColors(colorResource(id = R.color.purple_200)),
                                    Modifier.padding(0.dp),
                                    "Bowling",
                                    FontWeight.Bold,
                                ) {}
                                BoldFontText("OB")
                                NormalFontText("Style", Modifier.padding(10.dp, 0.dp, 0.dp, 0.dp))
                                BoldFontText("31.45")
                                NormalFontText("Average", Modifier.padding(10.dp, 0.dp, 0.dp, 0.dp))
                                BoldFontText("4.97")
                                NormalFontText("Economy-rate", Modifier.padding(10.dp, 0.dp, 0.dp, 0.dp))
                                BoldFontText("24")
                                NormalFontText("Wickets", Modifier.padding(10.dp, 0.dp, 0.dp, 0.dp))
                            }
                        }
                    }
                    IconButton(
                        onClick = {
//                            popUpVisible = true
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
                }
            }
        }
    }
}

@Composable
private fun BoldFontText(text: String) {
    TextComposable(
        1,
        text,
        colorResource(R.color.black),
        20.sp,
        FontWeight.Bold,
        Modifier.padding(10.dp, 0.dp)
    )
}

@Preview(showBackground = true)
@Composable
fun UiPreview() {
//    PlayerDetailsScreenUi(teamListData)
    PopUpUi(true)
}