package com.task.app.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.task.app.ui.viewmodels.MedicineViewModel

@Composable
fun MedicineListScreen(navigator: Navigator, username: String) {
    val viewModel: MedicineViewModel = hiltViewModel()

    // Collect medicines and loading state
    val medicines = viewModel.medicines.collectAsState().value
    val isLoading = viewModel.isLoading.collectAsState().value

    val greetingMessage = getGreetingMessage()

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text("Hello $greetingMessage, $username!")
        Text("Here are your medicines:")

        if (isLoading) {
            // Show loading state
            Text("Loading medicines...", modifier = Modifier.padding(top = 16.dp))
        } else if (medicines.isEmpty()) {
            // Handle empty list state
            Text("No medicines available.", modifier = Modifier.padding(top = 16.dp))
        } else {
            // Render list of medicines
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(medicines) { medicine ->
                    MedicineCard(
                        medicineName = medicine.name,
                        medicineDose = medicine.dose,
                        medicineStrength = medicine.strength,
                        onClick = { navigator.navigateTo("details/${medicine.name.trim()}") }
                    )
                }
            }
        }
    }
}

@Composable
fun MedicineCard(
    medicineName: String,
    medicineDose: String,
    medicineStrength: String,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable(onClick = onClick),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(Modifier.padding(16.dp)) {
            Text("Name: $medicineName")
            Text("Dose: $medicineDose")
            Text("Strength: $medicineStrength")
        }
    }
}

@Composable
fun getGreetingMessage(): String {
    val currentHour = remember {
        java.util.Calendar.getInstance().get(java.util.Calendar.HOUR_OF_DAY)
    }

    return when (currentHour) {
        in 5..11 -> "Good Morning"
        in 12..16 -> "Good Afternoon"
        in 17..20 -> "Good Evening"
        else -> "Good Night"
    }
}