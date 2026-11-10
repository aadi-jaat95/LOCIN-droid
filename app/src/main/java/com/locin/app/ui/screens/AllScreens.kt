package com.locin.app.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ExercisesScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {
        Text("Exercises", style = MaterialTheme.typography.headlineMedium)
        Text("Short attention drills", style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onSurfaceVariant)

        Spacer(modifier = Modifier.height(16.dp))

        ExerciseCard("Dot Focus", "Track a moving dot", "2-3 MIN", "EASY")
        ExerciseCard("Target Practice", "Click the center", "3-5 MIN", "MEDIUM")
        ExerciseCard("Pattern Memory", "Remember patterns", "5 MIN", "HARD")
        ExerciseCard("Clock Focus", "Follow the second hand", "2 MIN", "EASY")
    }
}

@Composable
fun ExerciseCard(title: String, desc: String, duration: String, level: String) {
    Card(modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(title, style = MaterialTheme.typography.titleLarge)
            Text(desc, style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onSurfaceVariant)
            Spacer(modifier = Modifier.height(8.dp))
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                AssistChip(onClick = {}, label = { Text(duration) })
                AssistChip(onClick = {}, label = { Text(level) })
            }
            Spacer(modifier = Modifier.height(8.dp))
            Button(onClick = {}, modifier = Modifier.fillMaxWidth()) {
                Text("Start ▶")
            }
        }
    }
}

@Composable
fun AppBlockingScreen() {
    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text("App Blocking", style = MaterialTheme.typography.headlineMedium)
        Text("Block apps or set daily timers", style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onSurfaceVariant)
        Spacer(modifier = Modifier.height(16.dp))
        Card(modifier = Modifier.fillMaxWidth()) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text("To enable app blocking:", style = MaterialTheme.typography.titleMedium)
                Text("1. Grant Usage Stats permission", style = MaterialTheme.typography.bodyMedium)
                Text("2. Enable Accessibility Service", style = MaterialTheme.typography.bodyMedium)
                Spacer(modifier = Modifier.height(8.dp))
                Button(onClick = {}, modifier = Modifier.fillMaxWidth()) {
                    Text("Grant Permissions")
                }
            }
        }
    }
}

@Composable
fun WebBlockingScreen() {
    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text("Web Blocking", style = MaterialTheme.typography.headlineMedium)
        Text("Block websites and YouTube content", style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onSurfaceVariant)
        Spacer(modifier = Modifier.height(16.dp))
        Card(modifier = Modifier.fillMaxWidth()) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text("Smart YouTube Filtering", style = MaterialTheme.typography.titleMedium)
                Text("Only educational channels allowed", style = MaterialTheme.typography.bodyMedium)
                Spacer(modifier = Modifier.height(8.dp))
                Button(onClick = {}, modifier = Modifier.fillMaxWidth()) {
                    Text("Manage Channels")
                }
            }
        }
    }
}

@Composable
fun StatsScreen() {
    Column(modifier = Modifier.fillMaxSize().verticalScroll(rememberScrollState()).padding(16.dp)) {
        Text("Stats", style = MaterialTheme.typography.headlineMedium)
        Text("Track your progress", style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onSurfaceVariant)
        Spacer(modifier = Modifier.height(16.dp))

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            StatCard(Modifier.weight(1f), "FOCUS TODAY", "0m")
            StatCard(Modifier.weight(1f), "LAST 7 DAYS", "0m")
        }
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            StatCard(Modifier.weight(1f), "STREAK", "0 days")
            StatCard(Modifier.weight(1f), "BEST DAY", "0m")
        }
    }
}

@Composable
fun SettingsScreen() {
    Column(modifier = Modifier.fillMaxSize().verticalScroll(rememberScrollState()).padding(16.dp)) {
        Text("Settings", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(16.dp))

        Card(modifier = Modifier.fillMaxWidth()) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text("Profile", style = MaterialTheme.typography.titleMedium)
                OutlinedTextField(value = "", onValueChange = {}, label = { Text("Name") }, modifier = Modifier.fillMaxWidth())
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(value = "", onValueChange = {}, label = { Text("Class/Grade") }, modifier = Modifier.fillMaxWidth())
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Card(modifier = Modifier.fillMaxWidth()) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text("Reminders", style = MaterialTheme.typography.titleMedium)
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                    Text("Interval")
                    Text("2 minutes")
                }
            }
        }
    }
}

@Composable
fun LeaderboardScreen() {
    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text("Leaderboard", style = MaterialTheme.typography.headlineMedium)
        Text("Top studiers worldwide", style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onSurfaceVariant)
        Spacer(modifier = Modifier.height(16.dp))

        Card(modifier = Modifier.fillMaxWidth()) {
            Column(modifier = Modifier.padding(16.dp)) {
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                    Text("👥 Users focusing now:", style = MaterialTheme.typography.titleMedium)
                    Text("0", style = MaterialTheme.typography.titleMedium, color = MaterialTheme.colorScheme.primary)
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))
        Text("Top 10 This Week", style = MaterialTheme.typography.titleMedium)
        // Leaderboard entries would go here
    }
}

@Composable
fun ScheduleScreen() {
    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text("Schedule", style = MaterialTheme.typography.headlineMedium)
        Text("Plan your study sessions", style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onSurfaceVariant)
        Spacer(modifier = Modifier.height(16.dp))

        FloatingActionButton(
            onClick = {},
            modifier = Modifier.padding(16.dp)
        ) {
            Icon(Icons.Default.Add, contentDescription = "Add Schedule")
        }

        Text("No schedules yet. Tap + to create one.", style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onSurfaceVariant)
    }
}
