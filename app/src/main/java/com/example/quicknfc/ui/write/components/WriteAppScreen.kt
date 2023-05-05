package com.example.quicknfc.ui.write.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.quicknfc.QuickNFCViewModel
import com.example.quicknfc.ui.theme.QuickNFCTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WriteAppScreen(viewModel: QuickNFCViewModel) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
    ) {

        val (content, button) = createRefs()
        var textFieldState by remember { mutableStateOf(TextFieldValue("")) }
        val status by viewModel.status.collectAsState()

        Column(
            modifier = Modifier
                .constrainAs(content) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    bottom.linkTo(button.top)
                },
            horizontalAlignment = Alignment.End,
        ) {
            OutlinedTextField(
                modifier = Modifier,
                value = textFieldState,
                onValueChange = {
                    textFieldState = it
                },
                label = { Text (text = "App") },
                placeholder = { Text(text = "com.example.quicknfc") },
            )
            OutlinedButton(
                modifier = Modifier.padding(top = 16.dp),
                onClick = { /*TODO*/ }
            ) {
                Text(text = "Select App")
            }
        }

        Button(
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .constrainAs(button) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    bottom.linkTo(parent.bottom, 16.dp)
                },
            onClick = {
                viewModel.writeText(textFieldState.text)
            },
        ) {
            Text(text = "Write", style = MaterialTheme.typography.titleMedium)
        }

        when (status) {
            QuickNFCViewModel.Status.Writing -> {
                WriteDialog { viewModel.cancelWrite() }
            }
            QuickNFCViewModel.Status.Error -> {
                ErrorDialog { viewModel.cancelWrite() }
            }
            else -> {}
        }
    }
}

@Preview()
@Composable
fun WriteAppScreenPreview() {
    QuickNFCTheme(darkTheme = true) {
        Surface() {
            WriteAppScreen(QuickNFCViewModel())
        }
    }
}