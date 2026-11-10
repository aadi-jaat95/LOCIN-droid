package com.locin.app.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.locin.app.ui.screens.*

@Composable
fun LocinApp() {
    val navController = rememberNavController()
    var sidebarExpanded by remember { mutableStateOf(false) }

    Row(modifier = Modifier.fillMaxSize()) {
        // Sidebar
        AnimatedVisibility(
            visible = sidebarExpanded,
            enter = slideInHorizontally(),
            exit = slideOutHorizontally()
        ) {
            Sidebar(
                navController = navController,
                onCollapse = { sidebarExpanded = false }
            )
        }

        // Main content
        Column(modifier = Modifier.fillMaxSize()) {
            TopAppBar(
                title = { Text("LOCIN") },
                navigationIcon = {
                    IconButton(onClick = { sidebarExpanded = !sidebarExpanded }) {
                        Icon(Icons.Default.Menu, contentDescription = "Menu")
                    }
                }
            )

            NavHost(
                navController = navController,
                startDestination = "reminders",
                modifier = Modifier.fillMaxSize()
            ) {
                composable("reminders") { RemindersScreen() }
                composable("exercises") { ExercisesScreen() }
                composable("app_blocking") { AppBlockingScreen() }
                composable("web_blocking") { WebBlockingScreen() }
                composable("stats") { StatsScreen() }
                composable("settings") { SettingsScreen() }
                composable("leaderboard") { LeaderboardScreen() }
                composable("schedule") { ScheduleScreen() }
            }
        }
    }
}

@Composable
fun Sidebar(
    navController: NavHostController,
    onCollapse: () -> Unit
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Column(
        modifier = Modifier
            .width(240.dp)
            .fillMaxHeight()
            .background(MaterialTheme.colorScheme.surface)
            .padding(16.dp)
    ) {
        Text(
            text = "locin",
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.primary
        )

        Spacer(modifier = Modifier.height(24.dp))

        SidebarItem(
            icon = Icons.Default.Notifications,
            label = "Reminders",
            selected = currentRoute == "reminders",
            onClick = { navController.navigate("reminders"); onCollapse() }
        )

        SidebarItem(
            icon = Icons.Default.Star,
            label = "Exercises",
            selected = currentRoute == "exercises",
            onClick = { navController.navigate("exercises"); onCollapse() }
        )

        SidebarItem(
            icon = Icons.Default.Phone,
            label = "App Blocking",
            selected = currentRoute == "app_blocking",
            onClick = { navController.navigate("app_blocking"); onCollapse() }
        )

        SidebarItem(
            icon = Icons.Default.Language,
            label = "Web Blocking",
            selected = currentRoute == "web_blocking",
            onClick = { navController.navigate("web_blocking"); onCollapse() }
        )

        SidebarItem(
            icon = Icons.Default.BarChart,
            label = "Stats",
            selected = currentRoute == "stats",
            onClick = { navController.navigate("stats"); onCollapse() }
        )

        SidebarItem(
            icon = Icons.Default.EmojiEvents,
            label = "Leaderboard",
            selected = currentRoute == "leaderboard",
            onClick = { navController.navigate("leaderboard"); onCollapse() }
        )

        SidebarItem(
            icon = Icons.Default.CalendarToday,
            label = "Schedule",
            selected = currentRoute == "schedule",
            onClick = { navController.navigate("schedule"); onCollapse() }
        )

        SidebarItem(
            icon = Icons.Default.Settings,
            label = "Settings",
            selected = currentRoute == "settings",
            onClick = { navController.navigate("settings"); onCollapse() }
        )
    }
}

@Composable
fun SidebarItem(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    label: String,
    selected: Boolean,
    onClick: () -> Unit
) {
    Surface(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        color = if (selected) MaterialTheme.colorScheme.primaryContainer
                else MaterialTheme.colorScheme.surface,
        shape = MaterialTheme.shapes.medium
    ) {
        Row(
            modifier = Modifier.padding(12.dp),
            horizontalArrangement = Arrangement.Start
        ) {
            Icon(
                imageVector = icon,
                contentDescription = label,
                tint = if (selected) MaterialTheme.colorScheme.onPrimaryContainer
                      else MaterialTheme.colorScheme.onSurface
            )
            Spacer(modifier = Modifier.width(12.dp))
            Text(
                text = label,
                color = if (selected) MaterialTheme.colorScheme.onPrimaryContainer
                       else MaterialTheme.colorScheme.onSurface
            )
        }
    }
}
