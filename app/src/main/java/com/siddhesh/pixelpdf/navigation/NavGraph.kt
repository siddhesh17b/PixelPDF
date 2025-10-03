package com.siddhesh.pixelpdf.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.siddhesh.pixelpdf.ui.screens.LibraryHomeScreen
import com.siddhesh.pixelpdf.ui.screens.ReaderViewScreen
import com.siddhesh.pixelpdf.ui.screens.SettingsScreen

@Composable
fun NavGraph(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "library", modifier = modifier) {
        composable("library") {
            LibraryHomeScreen(navController = navController)
        }
        composable(
            route = "reader/{bookId}",
            arguments = listOf(navArgument("bookId") { type = NavType.StringType })
        ) {
            ReaderViewScreen(navController = navController)
        }
        composable("settings") {
            SettingsScreen(navController = navController)
        }
    }
}
