package com.example.quicknfc

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.quicknfc.ui.QuickNFCNavGraph
import com.example.quicknfc.ui.screen.Screen
import com.example.quicknfc.ui.theme.QuickNFCTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QuickNFCApp() {
    QuickNFCTheme {
        val navController = rememberNavController()
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = {
                TopAppBar(
                    title = { Text(text = "QuickNFC") }
                )
            },
            bottomBar = { NavBar(navController = navController) }
        ) { innerPadding ->
            QuickNFCNavGraph(
                navController = navController,
                innerPadding = innerPadding
            )
        }
    }
}

@Composable
fun NavBar(navController: NavHostController) {
    val screens = listOf(
        Screen.Read,
        Screen.Write
    )

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    BottomAppBar {
        screens.forEach { screen ->
            NavigationBarItem(
                icon = { Icon(screen.icon, contentDescription = null) },
                label = { Text(screen.title) },
                selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                onClick = {
                    navController.navigate(screen.route) {
                        popUpTo(navController.graph.startDestinationId) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}

