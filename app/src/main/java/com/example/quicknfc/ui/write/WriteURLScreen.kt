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
import com.example.quicknfc.ui.theme.QuickNFCTheme
import com.example.quicknfc.ui.write.components.ErrorDialog
import com.example.quicknfc.ui.write.components.WriteDialog

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WriteURLScreen(viewModel: QuickNFCViewModel) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
    ) {
        val (textFields, button) = createRefs()
        var expanded by remember { mutableStateOf(false) }
        val status by viewModel.status.collectAsState()

        val protocolList = listOf("https://", "http://", "ftp://")
        var link by remember { mutableStateOf(TextFieldValue("")) }
        var protocol by remember { mutableStateOf(TextFieldValue(protocolList[0])) }
        
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .constrainAs(textFields) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start, 16.dp)
                    end.linkTo(parent.end, 16.dp)
                    bottom.linkTo(button.top)
                },
            horizontalArrangement = Arrangement.Center
        ) {
            ExposedDropdownMenuBox(expanded = expanded,
                onExpandedChange = { expanded = !expanded },
                modifier = Modifier
            ) {
                OutlinedTextField(
                    modifier = Modifier
                        .width(90.dp)
                        .menuAnchor(),
                    value = protocol,
                    onValueChange = { protocol = it },
                    label = { Text(text = "Protocol") },
                    readOnly = true
                )
                ExposedDropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
                    protocolList.forEach {
                        DropdownMenuItem(
                            text = { Text(text = it) },
                            onClick = {
                                protocol = TextFieldValue(it)
                                expanded = false }
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.width(16.dp))
            OutlinedTextField(
                modifier = Modifier,
                value = link,
                onValueChange = {
                    link = it
                },
                label = { Text (text = "Domain") },
            )
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
                viewModel.writeUri(protocol.text + link.text)
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
fun WriteLinkScreenPreview() {
    QuickNFCTheme(darkTheme = true) {
        Surface() {
            WriteURLScreen(QuickNFCViewModel())
        }
    }
}