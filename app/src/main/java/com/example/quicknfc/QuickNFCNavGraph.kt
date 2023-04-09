package com.example.quicknfc

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.quicknfc.ui.screen.Screen
import com.example.quicknfc.ui.screen.ReadScreen
import com.example.quicknfc.ui.screen.write.WriteLinkScreen
import com.example.quicknfc.ui.screen.write.WriteScreen
import com.example.quicknfc.ui.screen.write.WriteTextScreen

@Composable
fun QuickNFCNavGraph(
    navController: NavHostController,
    startDestination: String = Screen.Read.route,
    innerPadding: PaddingValues = PaddingValues()
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = Modifier.padding(innerPadding)
    ) {
        composable(Screen.Read.route) {
            ReadScreen()
        }
        composable(Screen.Write.route) {
            WriteScreen(navController)
        }

        composable(Screen.WriteText.route) {
            WriteTextScreen()
        }
        composable(Screen.WriteLink.route) {
            WriteLinkScreen()
        }

    }
}