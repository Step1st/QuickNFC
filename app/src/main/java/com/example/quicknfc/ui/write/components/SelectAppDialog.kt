package com.example.quicknfc.ui.write.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties

@Composable
fun SelectAppDialog() {
    Dialog(
        onDismissRequest = { /*TODO*/ },
        properties = DialogProperties(dismissOnBackPress = false, dismissOnClickOutside = false)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Write Successful")
            Button(
                modifier = Modifier.padding(top = 16.dp),
                onClick = { /*TODO*/ }
            ) {
                Text(text = "Done")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SelectAppDialogPreview() {
    SelectAppDialog()
}