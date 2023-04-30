package com.example.quicknfc.ui.write.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Error
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.quicknfc.ui.theme.QuickNFCTheme

@Composable
fun ErrorDialog(onConfirm: () -> Unit) {
    AlertDialog(
        onDismissRequest = { onConfirm() },
        title = { Text(text = "Error") },
        text = { Text(text = "Failed to write to tag, your tag may be incompatible.") },
        icon = { Icon(Icons.Outlined.Error, contentDescription = null) },
        confirmButton = {
            TextButton(
                onClick = { onConfirm() },
            ) {
                Text(text = "OK")
            }
        },
        iconContentColor = MaterialTheme.colorScheme.error,
        titleContentColor = MaterialTheme.colorScheme.error,
        textContentColor = MaterialTheme.colorScheme.error,
    )

}

@Preview
@Composable
fun ErrorDialogPreview() {
    QuickNFCTheme(darkTheme = true) {
        ErrorDialog {}
    }
}