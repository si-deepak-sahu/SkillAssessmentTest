package com.example.skillassessmenttest.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.interaction.Interaction
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.TextUnit
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

@Composable
fun TextComposable(maxLines: Int, text: String, color: Color, textSize: TextUnit, fontStyle: FontWeight, modifier: Modifier, includeFontPadding: Boolean) {
    Text(
        maxLines = maxLines,
        text = text,
        color = color,
        textAlign = TextAlign.Center,
        fontSize = textSize,
        overflow = TextOverflow.Ellipsis,
        fontWeight = fontStyle,
        modifier = modifier,
        style = TextStyle(
            platformStyle = PlatformTextStyle(
                includeFontPadding = includeFontPadding
            ),
        )
    )
}

@Composable
fun FilledButtonComposable(buttonColor: ButtonColors, modifier: Modifier, btnText: String, fontStyle: FontWeight, clickAction: () -> Unit) {
    Button(
        onClick = clickAction::invoke,
        colors = buttonColor,
        modifier = modifier,
    ) {
        Text(btnText,
            fontWeight = fontStyle )
    }
}

@Composable
fun NoRippleFilledButtonComposable(buttonColor: ButtonColors, modifier: Modifier, btnText: String, fontStyle: FontWeight, clickAction: () -> Unit) {
    Button(
        onClick = clickAction::invoke,
        colors = buttonColor,
        modifier = modifier,
        interactionSource = NoRippleInteractionSource()
    ) {
        Text(btnText,
            fontWeight = fontStyle )
    }
}

class NoRippleInteractionSource : MutableInteractionSource {
    override val interactions: Flow<Interaction> = emptyFlow()
    override suspend fun emit(interaction: Interaction) {}
    override fun tryEmit(interaction: Interaction) = true
}

@Composable
fun OutlinedButtonComposable(buttonColor: ButtonColors, modifier: Modifier, btnText: String, fontStyle: FontWeight, clickAction: () -> Unit) {
    OutlinedButton(
        onClick = clickAction::invoke,
        colors = buttonColor,
        modifier = modifier
    ) {
        Text(btnText,
            fontWeight = fontStyle )
    }
}

@Composable
fun ImageComposable(painterResource: Painter, imageDescription: String, scaleType: ContentScale, modifier: Modifier) {
    Image(
        painter =painterResource,
        contentDescription = imageDescription,
        contentScale = scaleType,
        modifier = modifier
    )
}

