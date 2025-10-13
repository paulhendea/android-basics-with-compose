package com.example.composearticle

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composearticle.ui.theme.ComposeArticleTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposeArticleTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Article(
                        image = painterResource(R.drawable.bg_compose_background),
                        title = stringResource(R.string.article_title),
                        content = listOf(
                            stringResource(R.string.article_paragraph_1),
                            stringResource(R.string.article_paragraph_2)
                        ),
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Article(image: Painter, title: String, content: List<String>, modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        Image(
            painter = image,
            contentDescription = null,
        )
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = title,
                fontSize = 24.sp,
            )
            content.map { content -> Text(
                text = content,
                textAlign = TextAlign.Justify,
            )}
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ComposeArticleTheme {
        Article(
            image = painterResource(R.drawable.bg_compose_background),
            title = stringResource(R.string.article_title),
            content = listOf(
                stringResource(R.string.article_paragraph_1),
                stringResource(R.string.article_paragraph_2)
            ),
        )
    }
}