package com.task.app.ui.screens

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import org.junit.Rule
import org.junit.Test

class MedicineCardTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testMedicineCardClick() {
        var isClicked = false

        composeTestRule.setContent {
            MedicineCard(
                medicineName = "Aspirin",
                medicineDose = "500mg",
                medicineStrength = "Strong",
                onClick = { isClicked = true }
            )
        }

        // Verify text exists
        composeTestRule.onNodeWithText("Name: Aspirin").assertExists()

        // Perform click
        composeTestRule.onNodeWithText("Name: Aspirin").performClick()

        // Assert click action was triggered
        assert(isClicked) // Verifies the onClick lambda was called
    }
}
