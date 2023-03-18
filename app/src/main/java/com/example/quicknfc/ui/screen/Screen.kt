package com.example.quicknfc.ui.screen

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Download
import androidx.compose.material.icons.outlined.Publish
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screen(
    val route: String,
    val title: String,
    val icon: ImageVector
) {
    object Read : Screen("read", "Read", Icons.Outlined.Download)
    object Write : Screen("write", "Write", Icons.Outlined.Publish)
}
