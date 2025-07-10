package com.nikhil.here.dotify.ui

import android.graphics.Bitmap
import android.graphics.Bitmap.createBitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.util.Log
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.core.graphics.get
import kotlin.math.roundToInt

private const val TAG = "Dotify"

@Composable
fun DottedText(
    modifier: Modifier,
    text: String,
    textScale: Float
) {
    BoxWithConstraints(modifier) {
        val width = this.maxWidth
        Log.i(TAG, "DottedText: $text")
        val bitmap = remember(text, textScale) { renderTextToBitmap(text, textScale, this.maxWidth.value, this.maxHeight.value) }
        val dotMatrix = remember(bitmap) { bitmapToDotMatrix(bitmap, 30, 30) }
        DotMatrixDisplay(
            modifier = Modifier.fillMaxSize(),
            dotMatrix = dotMatrix,
            dotSize = 10.dp
        )
    }
}

@Composable
fun DotMatrixDisplay(
    modifier: Modifier = Modifier,
    dotMatrix: List<List<Int>>,
    dotSize: Dp
) {
    Log.i(TAG, "DotMatrixDisplay: \n${dotMatrix.joinToString("\n") { it.joinToString() }}.")
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        dotMatrix.forEach { row ->
            Row {
                row.forEach { alpha ->
                    // Animate color based on alpha value
                    val targetColor = if (alpha > 0) {
                        MaterialTheme.colorScheme.primary
                    } else {
                        MaterialTheme.colorScheme.primaryContainer
                    }

                    val animatedColor by animateColorAsState(
                        targetValue = targetColor,
                        animationSpec = tween(durationMillis = 300), // Adjust duration as needed
                        label = "DotColorAnimation"
                    )

                    Box(
                        modifier = Modifier
                            .size(dotSize)
                            .padding(1.dp)
                            .background(
                                color = animatedColor,
                                shape = CircleShape
                            )
                    )
                }
            }
        }
    }
}

fun renderTextToBitmap(
    renderText: String,
    renderTextScale: Float,
    width: Float,
    height: Float
): Bitmap {
    val bitmap = createBitmap(width.toInt(), height.toInt(), Bitmap.Config.ARGB_8888)
    val canvas = Canvas(bitmap)
    val textPaint = Paint().apply {
        color = Color.WHITE
        textSize = (Math.min(width, height) * renderTextScale)
        isAntiAlias = true
        textAlign = Paint.Align.CENTER
    }
    val x = width / 2f
    val y = (height / 2f) - ((textPaint.descent() + textPaint.ascent()) / 2f)
    canvas.drawText(renderText, x, y, textPaint)
    return bitmap
}

fun bitmapToDotMatrix(bitmap: Bitmap, rows: Int, cols: Int): List<List<Int>> {
    val scaledBitmap = Bitmap.createScaledBitmap(bitmap, cols, rows, true)
    val result = mutableListOf<List<Int>>()
    for (y in 0 until rows) {
        val row = mutableListOf<Int>()
        for (x in 0 until cols) {
            val pixel = scaledBitmap[x, y]
            val red = Color.red(pixel)
            val green = Color.green(pixel)
            val blue = Color.blue(pixel)
            val alpha = Color.alpha(pixel)
            print(" $red $green $blue $alpha ")
            row.add(alpha) // Threshold for "dark" pixel
        }
        println()
        result.add(row)
    }

    return result
}
