package com.example.lemonde

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.lemonde.ui.theme.LemondeTheme
import java.util.UUID
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LemondeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
                ) {
                    Lemon()
                }
            }
        }
    }
}

@Composable
fun Lemon() {
    var currentStep by remember { mutableStateOf(1) }

    var isSqueezed by remember {
        mutableStateOf(UUID.randomUUID().toString())
    }

    val requiredSqueezes by remember(isSqueezed) {
        val randomNumber = Random.nextInt(1, 10)
        Log.d("MainActivity","Lemon: random nuber is $randomNumber")
        mutableStateOf(randomNumber)
    }
    var squeezesCount by remember { mutableStateOf(0) }
   // var requiredTaps by remember { mutableStateOf(5) }
    Surface(
        modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
    ) {
        when (currentStep) {
            1 -> {
                LemonadeTemplate(
                    onClick = {
                        currentStep = 2


                    },
                    drawableRes = R.drawable.lemon_tree,
                    title = stringResource(id = R.string.title1),
                    description = stringResource(id = R.string.lemon1)
                )
            }

            2 -> {
                LemonadeTemplate(
                    onClick = {
                       // requiredTaps = Random.nextInt(1, 10) // Generate a new random required taps count
                        if (squeezesCount < requiredSqueezes) {
                            squeezesCount++

                        }
                       // requiredSqueezes = Random.nextInt(2, 5)
                        if(squeezesCount==requiredSqueezes) {
                            squeezesCount = 0
                            isSqueezed = UUID.randomUUID().toString()
                            currentStep = 3
                        }
                    },
                    drawableRes = R.drawable.lemon_squeeze,
                    title = stringResource(id = R.string.title2),
                    description = stringResource(id = R.string.lemon2)
                )
            }

            3 -> {

                LemonadeTemplate(
                    onClick = {
                        currentStep = 4
                    },
                    drawableRes = R.drawable.lemon_drink,
                    title = stringResource(id = R.string.title3),
                    description = stringResource(id = R.string.lemon3)
                )
            }

            4 -> {
                LemonadeTemplate(
                    onClick = {
                        currentStep = 1
                    },
                    drawableRes = R.drawable.lemon_restart,
                    title = stringResource(id = R.string.title4),
                    description = stringResource(id = R.string.lemon4)
                )
            }
        }
    }
}

@Composable
private fun LemonadeTemplate(
    onClick: () -> Unit, drawableRes: Int, title: String, description: String
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    ) {

        Image(painter = painterResource(drawableRes),
            contentDescription = description,
            modifier = Modifier
                .wrapContentSize()
                .clickable {
                    onClick()
                })
        Spacer(
            modifier = Modifier.height(
                32.dp
            )
        )
        Text(text = title)
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    LemondeTheme {
        Lemon()
    }
}