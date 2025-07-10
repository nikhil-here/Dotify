package com.nikhil.here.dotify.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


private const val TAG = "DotifyV2"

@Composable
fun DottedTextV2(
    modifier: Modifier,
    text: String,
    textScale: Float
) {
    BoxWithConstraints(
        modifier = modifier
    ) {
        val width = this.maxWidth.value
        val height = this.maxHeight.value
        val textSize by remember(textScale) { mutableFloatStateOf(Math.min(width, height) * textScale) }
        val dotMatrix = List(30) { IntArray(30) }
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black)
        ) {
            Text(
                modifier = Modifier.align(Alignment.Center),
                text = text,
                style = TextStyle(
                    fontSize = textSize.sp
                ),
                color = MaterialTheme.colorScheme.primary
            )
            Column(
                modifier = Modifier
            ) {
                dotMatrix.forEach { row ->
                    Row {
                        row.forEach { alpha ->
                            //CornerCutout Box
                            Box(
                                modifier = Modifier
                                    .size(8.dp)
                                    .background(
                                        color = MaterialTheme.colorScheme.primaryContainer,
                                        shape = CornerCutoutShape
                                    )
                            )
                            //Horizontal Padding
                            Box(
                                modifier = Modifier
                                    .width(4.dp)
                                    .height(8.dp)
                                    .background(MaterialTheme.colorScheme.primaryContainer)
                            )
                        }
                    }
                    //Line Spacing
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(4.dp)
                            .background(MaterialTheme.colorScheme.primaryContainer)
                    )
                }
            }
        }
    }
}