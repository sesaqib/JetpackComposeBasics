package com.codingsector.jetpackcomposebasics

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.codingsector.jetpackcomposebasics.ui.theme.JetpackComposeBasicsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApp { MyScreenContent() }
        }
    }
}

@Composable
fun MyApp(content: @Composable () -> Unit) {
    JetpackComposeBasicsTheme {
        Surface(color = Color.Yellow) {
            content()
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(
        text = "Hello $name!",
        modifier = Modifier.padding(24.dp),
        style = MaterialTheme.typography.h1
    )
}

@Preview(showBackground = true ,name = "Text preview")
@Composable
fun DefaultPreview() {
//    MyApp {
//        MyScreenContent()
//    }
    JetpackComposeBasicsTheme {
        Greeting("Android")
    }
}

@Composable
//fun MyScreenContent(names: List<String> = listOf("Android", "there")) {
fun MyScreenContent(names: List<String> = List(1000) { "Hello Android #$it" }) {
    val counterState = remember { mutableStateOf(0) }

    Column(modifier = Modifier.fillMaxHeight()) {
        NameList(names = names, Modifier.weight(1f))
//        Divider(color = Color.Transparent, thickness = 32.dp)
        Counter(count = counterState.value, updateCount = {
            counterState.value = it
        })
    }
}

@Composable
fun Counter(count: Int, updateCount: (Int) -> Unit) {

    Button(
        onClick = { updateCount(count + 1) },
        colors = ButtonDefaults.buttonColors(
            backgroundColor = if (count > 5) Color.Green else Color.White
        )
    ) {
        Text("I've been clicked $count times")
    }
}

@Composable
fun NameList(names: List<String>, modifier: Modifier = Modifier) {
//    Column(modifier = modifier) {
//        for (name in names) {
//            Greeting(name = name)
//            Divider(color = Color.Black)
//        }
//     }

    LazyColumn(modifier = modifier) {
        items(items = names) {
            Greeting(name = it)
            Divider(color = Color.Black)
        }
    }
}