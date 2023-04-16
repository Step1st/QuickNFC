package com.example.quicknfc.ui.write

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Link
import androidx.compose.material.icons.outlined.TextFields
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.quicknfc.ui.write.components.WriteItem
import com.example.quicknfc.ui.Screen

@Composable
fun WriteScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        WriteItem("Write Text", Icons.Outlined.TextFields) {
            navController.navigate(Screen.WriteText.route)
        }
        WriteItem("Write Link", Icons.Outlined.Link) {
            navController.navigate(Screen.WriteLink.route)
        }
    }
}
@Preview(showBackground = true)
@Composable
fun WriteScreenPreview() {
    WriteScreen(navController = rememberNavController())
}
