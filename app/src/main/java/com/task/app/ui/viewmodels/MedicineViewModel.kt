package com.task.app.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.task.app.data.MedicineDao
import com.task.app.data.api.ApiService
import com.task.app.data.models.Medicine
import com.task.app.data.models.MedicineResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MedicineViewModel @Inject constructor(
    private val apiService: ApiService,
    private val medicineDao: MedicineDao
) : ViewModel() {

    private val _medicines = MutableStateFlow<List<Medicine>>(emptyList())
    val medicines: StateFlow<List<Medicine>> get() = _medicines

    private val _isLoading = MutableStateFlow(true) // Initial state as loading
    val isLoading: StateFlow<Boolean> get() = _isLoading

    init {
        fetchAndStoreMedicines()
    }

    private fun fetchAndStoreMedicines() {
        viewModelScope.launch {
            try {
                _isLoading.value = true // Start loading
                val response = apiService.getMedicines()
                val medicineList = parseResponse(response)
                medicineDao.insertMedicines(medicineList)
                _medicines.value = medicineList // Update medicines list
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                _isLoading.value = false // Loading finished
            }
        }
    }

    private fun parseResponse(response: MedicineResponse): List<Medicine> {
        val medicines = mutableListOf<Medicine>()
        response.problems.forEach { problem ->
            problem.diabetes?.forEach { diabetes ->
                diabetes.medications?.forEach { medication ->
                    medication.medicationsClasses?.forEach { medClass ->
                        medClass.className?.forEach { drug ->
                            drug.associatedDrug?.forEach { associatedDrug ->
                                medicines.add(
                                    Medicine(
                                        id = associatedDrug.name,
                                        name = associatedDrug.name,
                                        dose = associatedDrug.dose,
                                        strength = associatedDrug.strength
                                    )
                                )
                            }
                        }
                    }
                }
            }
        }
        return medicines
    }

    /**
     * Get medicine details by ID
     */
    fun getMedicineByName(name: String): Medicine? {
        return _medicines.value.find { it.name == name }
    }

}