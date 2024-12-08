package com.task.app.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.task.app.ui.viewmodels.MedicineViewModel
import java.net.URLDecoder
import java.nio.charset.StandardCharsets

@Composable
fun DetailScreen(navigator: Navigator, medicineName: String,viewModel: MedicineViewModel = hiltViewModel()) {
    val viewModel: MedicineViewModel = hiltViewModel()

    // Collect medicines state reactively
    val medicines = viewModel.medicines.collectAsState().value

    // Find the medicine by name from the state
    val medicine = medicines.find { it.name.equals(medicineName, ignoreCase = true) }

    // Debugging output
    println("Received medicineName: $medicineName")
    println("Medicine found: $medicine")

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        if (medicine != null) {
            Text("Name: ${medicine.name}")
            Text("Dose: ${medicine.dose}")
            Text("Strength: ${medicine.strength}")
        } else {
            Text("Medicine not found", modifier = Modifier.padding(16.dp))
        }
    }
}

