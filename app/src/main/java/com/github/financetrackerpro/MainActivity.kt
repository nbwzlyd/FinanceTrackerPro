package com.github.financetrackerpro
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.github.financetrackerpro.ui.theme.FinanceTrackerTheme
import com.github.financetrackerpro.ui.theme.Blue
import com.github.financetrackerpro.ui.screens.BudgetScreen
import com.github.financetrackerpro.ui.screens.HomeScreen
import com.github.financetrackerpro.ui.screens.RecordScreen
import com.github.financetrackerpro.ui.screens.ReportScreen
import com.github.financetrackerpro.ui.screens.SettingsScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FinanceTrackerTheme {
                MainScreen()
            }
        }
    }
}

sealed class BottomNavItem(val route: String, val icon: ImageVector, val title: String) {
    object Home : BottomNavItem("home", Icons.Default.Home, "首页")
    object Record : BottomNavItem("record", Icons.Default.Add, "记账")
    object Budget : BottomNavItem("budget", Icons.Default.AccountBox, "预算")
    object Report : BottomNavItem("report", Icons.Default.ShoppingCart, "报表")
    object Settings : BottomNavItem("settings", Icons.Default.Settings, "设置")
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val navItems = listOf(
        BottomNavItem.Home,
        BottomNavItem.Record,
        BottomNavItem.Budget,
        BottomNavItem.Report,
        BottomNavItem.Settings
    )

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = when (currentRoute) {
                            BottomNavItem.Home.route -> "财务管理"
                            BottomNavItem.Record.route -> "记录交易"
                            BottomNavItem.Budget.route -> "预算管理"
                            BottomNavItem.Report.route -> "财务报表"
                            BottomNavItem.Settings.route -> "设置"
                            else -> "财务管理"
                        }
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surface,
                    titleContentColor = MaterialTheme.colorScheme.onSurface
                )
            )
        },
        bottomBar = {
            NavigationBar {
                navItems.forEach { item ->
                    NavigationBarItem(
                        icon = { Icon(item.icon, contentDescription = item.title) },
                        label = { Text(item.title) },
                        selected = currentRoute == item.route,
                        onClick = {
                            navController.navigate(item.route) {
                                // 避免创建重复的栈顶路由
                                navController.graph.startDestinationRoute?.let { route ->
                                    popUpTo(route) {
                                        saveState = true
                                    }
                                }
                                // 避免相同路由的重复点击
                                launchSingleTop = true
                                // 恢复状态
                                restoreState = true
                            }
                        },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = Blue,
                            selectedTextColor = Blue,
                            indicatorColor = MaterialTheme.colorScheme.surface
                        )
                    )
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = BottomNavItem.Home.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(BottomNavItem.Home.route) {
                HomeScreen{

                }
            }
            composable(BottomNavItem.Record.route) {
                RecordScreen()
            }
            composable(BottomNavItem.Budget.route) {
                BudgetScreen()
            }
            composable(BottomNavItem.Report.route) {
                ReportScreen()
            }
            composable(BottomNavItem.Settings.route) {
                SettingsScreen()
            }
        }
    }
}
