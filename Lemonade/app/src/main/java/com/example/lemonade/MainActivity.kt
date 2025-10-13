package com.example.lemonade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lemonade.ui.theme.LemonadeTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LemonadeTheme {
                Scaffold(
                    topBar = {
                        TopAppBar(
                            title = {
                                Text(stringResource(R.string.app_name))
                            }
                        )
                    },
                    modifier = Modifier.fillMaxSize()
                ) { innerPadding ->
                    LemonadeApp(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun LemonadeApp(modifier: Modifier = Modifier) {
    var step by remember { mutableIntStateOf(1) }
    var squeezeCount by remember { mutableIntStateOf(0) }

    when (step) {
        1 -> LemonadeStep(
            image = painterResource(R.drawable.lemon_tree),
            contentDescription = stringResource(R.string.lemon_tree_content_description),
            text = stringResource(R.string.lemon_select),
            modifier = modifier
        ) {
            squeezeCount = (2..4).random()
            step = 2
        }

        2 -> LemonadeStep(
            image = painterResource(R.drawable.lemon_squeeze),
            contentDescription = stringResource(R.string.lemon_content_description),
            text = stringResource(R.string.lemon_squeeze).format(squeezeCount),
            modifier = modifier
        ) {
            squeezeCount--
            if (squeezeCount == 0) step = 3
        }

        3 -> LemonadeStep(
            image = painterResource(R.drawable.lemon_drink),
            contentDescription = stringResource(R.string.lemonade_content_description),
            text = stringResource(R.string.lemon_drink),
            modifier = modifier
        ) {
            step = 4
        }

        4 -> LemonadeStep(
            image = painterResource(R.drawable.lemon_restart),
            contentDescription = stringResource(R.string.empty_glass_content_description),
            text = stringResource(R.string.lemon_empty_glass),
            modifier = modifier
        ) {
            step = 1
        }
    }
}

@Composable
fun LemonadeStep(
    image: Painter,
    contentDescription: String,
    text: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Button(
            onClick = onClick,
            shape = RoundedCornerShape(4.dp),
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.tertiaryContainer)
        ) {
            Image(
                painter = image,
                contentDescription = contentDescription
            )
        }
        Spacer(Modifier.height(16.dp))
        Text(
            text = text,
            fontSize = 18.sp
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    LemonadeTheme {
        LemonadeApp()
    }
}