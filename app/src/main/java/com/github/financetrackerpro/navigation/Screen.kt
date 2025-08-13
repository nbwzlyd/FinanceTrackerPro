package com.github.financetrackerpro.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.github.financetrackerpro.ui.screens.AddTransactionScreen
import com.github.financetrackerpro.ui.screens.BudgetScreen
import com.github.financetrackerpro.ui.screens.HomeScreen
import com.github.financetrackerpro.ui.screens.ReportScreen
import com.github.financetrackerpro.ui.screens.SettingsScreen

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object AddTransaction : Screen("add_transaction")
    object Budget : Screen("budget")
    object Report : Screen("report")
    object Settings : Screen("settings")
}

@Composable
fun AppNavigation(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route,
        modifier = modifier
    ) {
        composable(Screen.Home.route) {
            HomeScreen(
                onAddTransaction = {
                    navController.navigate(Screen.AddTransaction.route)
                }
            )
        }
        
        composable(Screen.AddTransaction.route) {
            AddTransactionScreen(
                onBack = {
                    navController.popBackStack()
                },
                onSave = {
                    navController.popBackStack()
                }
            )
        }
        
        composable(Screen.Budget.route) {
            BudgetScreen()
        }
        
        composable(Screen.Report.route) {
            ReportScreen()
        }
        
        composable(Screen.Settings.route) {
            SettingsScreen()
        }
    }
}
