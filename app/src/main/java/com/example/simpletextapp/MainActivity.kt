package com.example.simpletextapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainScreen()
        }
    }
}

@Composable
fun MainScreen(viewModel: MainViewModel = MainViewModel()) {
    val newNameStateContent = viewModel.textFieldState.observeAsState("")

    Column(modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally) {
        GreetingMessage(newNameStateContent.value) {
            newName -> viewModel.onTextChanged(newName)
        }
    }
}

@Composable
fun GreetingMessage(textFieldValue: String,
                    textFieldUpdate: (newName: String) -> Unit) {
    TextField(value = textFieldValue, onValueChange = textFieldUpdate)

    Button(onClick = {  }) {
        Text(text = textFieldValue,
            style = MaterialTheme.typography.titleMedium)
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!",
        style = MaterialTheme.typography.bodyMedium)
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MainScreen()
}