package com.example.composequadrant

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.snapping.SnapPosition
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composequadrant.ui.theme.ComposeQuadrantTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposeQuadrantTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    QuadrantScreen(
                        content = listOf(
                            QuadrantClass(
                                title = stringResource(R.string.quadrant_1_title),
                                body = stringResource(R.string.quadrant_1_body),
                            ),
                            QuadrantClass(
                                title = stringResource(R.string.quadrant_2_title),
                                body = stringResource(R.string.quadrant_2_body),
                            ),
                            QuadrantClass(
                                title = stringResource(R.string.quadrant_3_title),
                                body = stringResource(R.string.quadrant_3_body),
                            ),
                            QuadrantClass(
                                title = stringResource(R.string.quadrant_4_title),
                                body = stringResource(R.string.quadrant_4_body),
                            ),
                        ),
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

class QuadrantClass(val title: String, val body: String)

@Composable
fun Quadrant(title: String, body: String, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(
            space = 16.dp,
            alignment = Alignment.CenterVertically
        ),
    ) {
        Text(
            text = title,
            fontWeight = FontWeight.Bold,
        )
        Text(
            text = body,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun QuadrantScreen(content: List<QuadrantClass>, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
    ) {
        Row(Modifier.weight(1F)) {
            Quadrant(
                title = content[0].title,
                body = content[0].body,
                modifier = Modifier
                    .background(colorResource(R.color.purple_1))
                    .weight(1F)
            )
            Quadrant(
                title = content[1].title,
                body = content[1].body,
                modifier = Modifier
                    .background(colorResource(R.color.purple_2))
                    .weight(1F)
            )
        }
        Row(Modifier.weight(1F)) {
            Quadrant(
                title = content[2].title,
                body = content[2].body,
                modifier = Modifier
                    .background(colorResource(R.color.purple_3))
                    .weight(1F)
            )
            Quadrant(
                title = content[3].title,
                body = content[3].body,
                modifier = Modifier
                    .background(colorResource(R.color.purple_4))
                    .weight(1F)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ComposeQuadrantTheme {
        QuadrantScreen(
            content = listOf(
                QuadrantClass(
                    title = stringResource(R.string.quadrant_1_title),
                    body = stringResource(R.string.quadrant_1_body),
                ),
                QuadrantClass(
                    title = stringResource(R.string.quadrant_2_title),
                    body = stringResource(R.string.quadrant_2_body),
                ),
                QuadrantClass(
                    title = stringResource(R.string.quadrant_3_title),
                    body = stringResource(R.string.quadrant_3_body),
                ),
                QuadrantClass(
                    title = stringResource(R.string.quadrant_4_title),
                    body = stringResource(R.string.quadrant_4_body),
                ),
            ),
            modifier = Modifier.fillMaxSize()
        )
    }
}