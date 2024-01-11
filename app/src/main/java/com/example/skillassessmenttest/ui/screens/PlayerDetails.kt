package com.example.skillassessmenttest.ui.screens

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.skillassessmenttest.R
import com.example.skillassessmenttest.ui.component.TextComposable
import com.example.skillassessmenttest.ui.theme.SkillAssessmentTestTheme

class PlayersDetails : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SkillAssessmentTestTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = colorResource(R.color.purple_200)
                ) {
                    TextComposable(
                        3,
                        "Team & Players Screen",
                        colorResource(R.color.off_white),
                        20.sp,
                        FontWeight.Normal,
                        Modifier
                            .padding(10.dp, 40.dp)
                    )
                }
            }
        }
    }
}