package com.example.quicknfc.ui.read.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.IntegrationInstructions
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TechAvailable(techAvailable: Iterable<String>) {
    ListItem(
        headlineText = { Text(text = "Tech Available") },
        supportingText = { Text(text = techAvailable.joinToString(", ")) },
        leadingContent = {
            Icon(imageVector = Icons.Outlined.Info, contentDescription = null)
        }
    )
    Divider()
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DataFormat(type: String) {
    ListItem(
        headlineText = { Text(text = "Data Format") },
        supportingText = { Text(text = type) },
        leadingContent = {
            Icon(imageVector = Icons.Outlined.IntegrationInstructions , contentDescription = null)
        }
    )
    Divider()
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MemorySize(memorySize: Int) {
    ListItem(
        headlineText = { Text(text = "Memory Size") },
        supportingText = { Text(text = "$memorySize bytes" ) },
        leadingContent = {
            Icon(imageVector = Icons.Outlined.Info, contentDescription = null)
        }
    )
    Divider()
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CanMakeReadOnly(canMakeReadOnly: Boolean) {
    ListItem(
        headlineText = { Text(text = "Can Make Read-Only") },
        supportingText = { Text(text = if (canMakeReadOnly) "Yes" else "No" ) },
        leadingContent = {
            Icon(imageVector = Icons.Outlined.Lock, contentDescription = null)
        }
    )
    Divider()
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun IsWritable(isWritable: Boolean) {
    ListItem(
        headlineText = { Text(text = "Is Writable") },
        supportingText = { Text(text = if (isWritable) "Yes" else "No" ) },
        leadingContent = {
            Icon(imageVector = Icons.Outlined.Edit, contentDescription = null)
        }
    )
    Divider()
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Sak(sak: String) {
    ListItem(
        headlineText = { Text(text = "SAK") },
        supportingText = { Text(text = sak) },
        leadingContent = {
            Icon(imageVector = Icons.Outlined.Info, contentDescription = null)
        }
    )
    Divider()
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Atqa(atqa: String) {
    ListItem(
        headlineText = { Text(text = "ATQA") },
        supportingText = { Text(text = atqa) },
        leadingContent = {
            Icon(imageVector = Icons.Outlined.Info, contentDescription = null)
        }
    )
    Divider()
}


