package com.task.app.data.models

import com.google.gson.annotations.SerializedName

data class MedicineResponse(
    val problems: List<Problem>
)

data class Problem(
    @SerializedName("Diabetes") val diabetes: List<Diabetes>?
)

data class Diabetes(
    val medications: List<Medications>?
)

data class Medications(
    val medicationsClasses: List<MedicationsClass>?
)

data class MedicationsClass(
    val className: List<Drug>?,
    val className2: List<Drug>?
)

data class Drug(
    val associatedDrug: List<DrugDetail>?,
    @SerializedName("associatedDrug#2") val associatedDrug2: List<DrugDetail>?
)

data class DrugDetail(
    val name: String,
    val dose: String,
    val strength: String
)
