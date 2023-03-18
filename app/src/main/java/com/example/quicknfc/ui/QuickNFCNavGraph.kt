package com.example.quicknfc.ui

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.quicknfc.ui.screen.Screen
import com.example.quicknfc.ui.screen.ReadScreen
import com.example.quicknfc.ui.screen.WriteScreen

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
            WriteScreen()
        }
    }
}