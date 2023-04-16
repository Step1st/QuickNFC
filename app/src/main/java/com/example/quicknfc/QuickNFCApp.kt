package com.example.quicknfc

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.quicknfc.ui.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QuickNFCApp(isNfcAvailable: State<Boolean>, onNfcSettingsClick: () -> Unit) {
    val snackbarHostState = remember { SnackbarHostState() }
    val navController = rememberNavController()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = { Text(text = "QuickNFC") }
            )},
        bottomBar = { BottomNavBar(navController = navController) },
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { innerPadding ->
        QuickNFCNavGraph(
            navController = navController,
            innerPadding = innerPadding
       )

        LaunchedEffect(isNfcAvailable, snackbarHostState) {
            if (!isNfcAvailable.value) {
                val result = snackbarHostState.showSnackbar(
                    message = "NFC is not available on this device",
                    duration = SnackbarDuration.Indefinite,
                    actionLabel = "Enable NFC",

                )
                when (result) {
                    SnackbarResult.ActionPerformed -> {
                        onNfcSettingsClick()
                    }
                    SnackbarResult.Dismissed -> {

                    }
                }
            }
        }
    }
}

@Composable
fun BottomNavBar(navController: NavHostController) {
    val screens = listOf(
        Screen.Read,
        Screen.Write
    )

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    BottomAppBar {
        screens.forEach { screen ->
            NavigationBarItem(
                icon = { Icon(screen.icon!!, contentDescription = null) },
                label = { Text(screen.title) },
                selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                onClick = {
                    //TODO: Go over this code
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

