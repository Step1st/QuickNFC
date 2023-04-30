package com.example.quicknfc

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.quicknfc.ui.Screen
import com.example.quicknfc.ui.screen.ReadScreen
import com.example.quicknfc.ui.write.WriteLinkScreen
import com.example.quicknfc.ui.write.WriteScreen
import com.example.quicknfc.ui.write.WriteTextScreen

@Composable
fun QuickNFCNavGraph(
    viewModel: QuickNFCViewModel,
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
            ReadScreen(viewModel)
        }
        composable(Screen.Write.route) {
            WriteScreen(navController)
        }

        composable(Screen.WriteText.route) {
            WriteTextScreen(viewModel)
        }
        composable(Screen.WriteLink.route) {
            WriteLinkScreen(viewModel)
        }

    }
}