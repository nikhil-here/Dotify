package com.nikhil.here.dotify

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.nikhil.here.dotify.ui.DottedText
import com.nikhil.here.dotify.ui.DottedTextV2
import com.nikhil.here.dotify.ui.theme.DotifyTheme
import kotlin.random.Random

private const val TAG = "MainActivity"

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DotifyTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column(
                        modifier = Modifier.fillMaxSize().padding(innerPadding).background(
                            MaterialTheme.colorScheme.primaryContainer),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        var text by remember {
                            mutableStateOf("NIKHIL")
                        }
                        var scale by remember {
                            mutableStateOf(0.3f)
                        }
                        DottedTextV2(
                            modifier = Modifier.size(300.dp),
                            text = text,
                            textScale = scale
                        )
                        Spacer(modifier = Modifier.height(8.dp))

                        Slider(
                            value = scale,
                            modifier = Modifier.fillMaxWidth(0.8f),
                            onValueChange = {
                                scale = it
                            }
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        Button(
                            onClick = {
                                text = Random.nextInt(0, 9).toString()
                            }
                        ) {
                            Text("RANDOM")
                        }
                    }
                }
            }
        }
    }
}
