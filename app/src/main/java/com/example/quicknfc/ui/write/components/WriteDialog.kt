package com.example.quicknfc.ui.write.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Nfc
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable


@Composable
fun WriteDialog(onCancel: () -> Unit) {
    AlertDialog(
        onDismissRequest = { onCancel() },
        title = { Text(text = "Write to NFC tag") },
        text = { Text(text = "Approach the tag") },
        icon = { Icon(Icons.Outlined.Nfc, contentDescription = null) },
        confirmButton = {
            TextButton(
                onClick = { onCancel() },
            ) {
                Text(text = "Cancel")
            }
        }
    )
}