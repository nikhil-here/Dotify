package com.nikhil.here.dotify.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.GenericShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nikhil.here.dotify.ui.theme.DotifyTheme

val CornerCutoutShape = GenericShape { size, _ ->
    val cornerRadius = size.width / 2f

    // Draw Rectangle
    val outerRect = Path().apply { addRect(Rect(Offset.Zero, size)) }

    // Draw Oval
    val centerOval = Path().apply {
        addOval(
            Rect(
                Offset(size.width / 2, size.height / 2),
                cornerRadius
            ),
        )
    }

    //Subtract Rect - Oval
    val result = outerRect.minus(centerOval)

    addPath(result)
}

@Preview
@Composable
private fun PreviewCutoutCorner() {
    DotifyTheme {
        Box(
            modifier = Modifier
                .size(300.dp)
                .background(
                    color = MaterialTheme.colorScheme.primaryContainer,
                    shape = CornerCutoutShape
                )
        )
    }
}