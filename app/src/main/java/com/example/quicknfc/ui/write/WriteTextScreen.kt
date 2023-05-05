package com.example.quicknfc.ui.write

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.quicknfc.QuickNFCViewModel
import com.example.quicknfc.ui.write.components.ErrorDialog
import com.example.quicknfc.ui.write.components.WriteDialog

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WriteTextScreen(viewModel: QuickNFCViewModel) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
    ) {

        val (textField, button) = createRefs()
        var textFieldState by remember { mutableStateOf(TextFieldValue("")) }
        val status by viewModel.status.collectAsState()

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

@Preview(showBackground = true)
@Composable
fun WriteTextScreenPreview() {
    WriteTextScreen(QuickNFCViewModel())
}