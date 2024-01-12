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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.skillassessmenttest.R
import com.example.skillassessmenttest.ui.component.FilledButtonComposable
import com.example.skillassessmenttest.ui.component.ImageComposable
import com.example.skillassessmenttest.ui.component.OutlinedButtonComposable
import com.example.skillassessmenttest.ui.component.TextComposable
import com.example.skillassessmenttest.ui.theme.SkillAssessmentTestTheme


var playerName = "M.S Dhoni"
var captain = "Captain | "
var wicketKeeper = "Wicket Keeper"
val listA = listOf("Example", "Android", "Tutorial", "Jetpack", "Compose", "List", "Example", "Simple", "List", "Example", "Simple", "List", "Example", "Simple", "List", "Example", "Simple", "List")

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
                    PlayerUi()
                }
            }
        }
    }
}

@Composable
fun PlayerUi() {
    Column {
        Box(modifier = Modifier.fillMaxWidth()) {
            TextComposable(
                1,
                "Indian players",
                colorResource(R.color.black),
                22.sp,
                FontWeight.Bold,
                Modifier
                    .padding(10.dp, 10.dp)
                    .align(Alignment.TopCenter)
            )
        }
        Box {
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(listA) { CardItem() }
            }
            Row(
                Modifier
                    .height(IntrinsicSize.Min)
                    .padding(10.dp)
                    .align(Alignment.BottomCenter)
            ) {
                FilterButtons(true)
            }
        }
    }
}

@Composable
fun FilterButtons(filterCheck: Boolean) {
    var fillter by remember { mutableStateOf(filterCheck) }

    if (filterCheck) {
        FilledButtonComposable(
            ButtonDefaults.buttonColors(colorResource(id = R.color.lavendar)),
            Modifier
                .padding(2.dp)
                .width(130.dp),
            "India",
            FontWeight.Bold,
        ) { fillter = true}
        OutlinedButtonComposable(
            ButtonDefaults.buttonColors(colorResource(id = R.color.white)),
            Modifier
                .padding(2.dp)
                .width(130.dp),
            "New Zealand",
            FontWeight.Bold,
        ) { fillter = false}
    }else{
        OutlinedButtonComposable(
            ButtonDefaults.buttonColors(colorResource(id = R.color.white)),
            Modifier
                .padding(2.dp)
                .width(130.dp),
            "India",
            FontWeight.Bold,
        ) { fillter = true}
        FilledButtonComposable(
            ButtonDefaults.buttonColors(colorResource(id = R.color.lavendar)),
            Modifier
                .padding(2.dp)
                .width(130.dp),
            "New Zealand",
            FontWeight.Bold,
        ) { fillter = false}
    }
}

@Composable
private fun CardItem() {
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
            .padding(10.dp, 5.dp)
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
                TextComposable(
                    1,
                    playerName.uppercase(),
                    colorResource(R.color.black),
                    20.sp,
                    FontWeight.Bold,
                    Modifier.padding(10.dp, 0.dp)
                )
                Row {
                    TextComposable(
                        1,
                        captain,
                        colorResource(R.color.gray),
                        14.sp,
                        FontWeight.Normal,
                        Modifier.padding(10.dp, 0.dp, 0.dp, 0.dp)
                    )
                    TextComposable(
                        1,
                        wicketKeeper,
                        colorResource(R.color.gray),
                        14.sp,
                        FontWeight.Normal,
                        Modifier.padding(0.dp, 0.dp)
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    SkillAssessmentTestTheme {
        PlayerUi()
    }
}