package com.task.app.ui.screens

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

class LoginScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun navigatesToMedicineListOnLogin() {
        val fakeNavigator = FakeNavigator()

        composeTestRule.setContent {
            LoginScreen(navigator = fakeNavigator)
        }

        composeTestRule.onNodeWithText("Enter Username or Email")
            .performTextInput("TestUser")
        composeTestRule.onNodeWithText("Login")
            .performClick()

        // Verify the navigation route
        assertEquals("medicine_list/TestUser", fakeNavigator.lastRoute)
    }

    class FakeNavigator : Navigator {
        var lastRoute: String? = null

        override fun navigateTo(route: String) {
            lastRoute = route
        }
    }
}
