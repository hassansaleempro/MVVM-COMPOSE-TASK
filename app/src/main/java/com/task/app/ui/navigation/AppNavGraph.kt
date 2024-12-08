package com.task.app.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.task.app.ui.screens.DetailScreen
import com.task.app.ui.screens.LoginScreen
import com.task.app.ui.screens.MedicineListScreen

@Composable
fun AppNavGraph() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "login") {
        composable("login") { LoginScreen(navController) }
        composable("medicine_list/{username}") { backStackEntry ->
            val username = backStackEntry.arguments?.getString("username") ?: ""
            MedicineListScreen(navController, username)
        }
        composable("details/{medicineName}") { backStackEntry ->
            val medicineName = backStackEntry.arguments?.getString("medicineName") ?: ""
            DetailScreen(navController, medicineName)
        }
    }
}