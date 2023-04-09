package com.example.quicknfc.ui.screen

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Download
import androidx.compose.material.icons.outlined.Publish
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screen(
    val route: String,
    val title: String,
    val icon: ImageVector?
) {
    object Read : Screen("read", "Read", Icons.Outlined.Download)
    object Write : Screen("write", "Write", Icons.Outlined.Publish)
    object WriteText : Screen("write_text", "Write Text", null)
    object WriteLink : Screen("write_link", "Write link", null)
    object  WriteApp : Screen("write_app", "Write App", null)
    object WriteWifi : Screen("write_wifi", "Write Wifi", null)


}
