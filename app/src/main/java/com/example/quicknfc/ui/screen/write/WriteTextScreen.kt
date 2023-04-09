package com.example.quicknfc.ui.screen.write

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WriteTextScreen() {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
    ) {
        val (textField, button) = createRefs()
        var textFieldState by remember { mutableStateOf(TextFieldValue("")) }
        OutlinedTextField(
            modifier = Modifier
                .constrainAs(textField) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    bottom.linkTo(button.top)
                },
            value = textFieldState,
            onValueChange = {
                textFieldState = it
            },
            label = { Text (text = "Plain Text") },
        )
        Button(
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .constrainAs(button) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    bottom.linkTo(parent.bottom, 16.dp)
                },
            onClick = {
                Log.d("WriteTextScreen", "Write text: $textFieldState")
            },
        ) {
            Text(text = "Write", style = MaterialTheme.typography.titleMedium)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun WriteTextScreenPreview() {
    WriteTextScreen()
}