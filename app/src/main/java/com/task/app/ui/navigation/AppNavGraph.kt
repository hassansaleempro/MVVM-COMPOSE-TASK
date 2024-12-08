package com.task.app.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.task.app.ui.screens.DetailScreen
import com.task.app.ui.screens.LoginScreen
import com.task.app.ui.screens.MedicineListScreen
import com.task.app.ui.screens.Navigator

@Composable
fun AppNavGraph() {
    val navController = rememberNavController()
    val navigator = object : Navigator {
        override fun navigateTo(route: String) {
            navController.navigate(route)
        }
    }

    NavHost(navController = navController, startDestination = "login") {
        composable("login") { LoginScreen(navigator) }
        composable("medicine_list/{username}") { backStackEntry ->
            val username = backStackEntry.arguments?.getString("username") ?: ""
            MedicineListScreen(navigator, username)
        }
        composable("details/{medicineName}") { backStackEntry ->
            val medicineName = backStackEntry.arguments?.getString("medicineName") ?: ""
            DetailScreen(navigator, medicineName)
        }
    }
}
