package com.task.app.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.task.app.ui.viewmodels.MedicineViewModel

@Composable
fun DetailScreen(navController: NavController, medicineName: String) {
    val viewModel: MedicineViewModel = hiltViewModel()

    // Retrieve medicine by name
    val medicine = viewModel.getMedicineByName(medicineName)

    if (medicine != null) {
        Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
            Text("Name: ${medicine.name}")
            Text("Dose: ${medicine.dose}")
            Text("Strength: ${medicine.strength}")
        }
    } else {
        Text("Medicine not found", modifier = Modifier.padding(16.dp))
    }
}
