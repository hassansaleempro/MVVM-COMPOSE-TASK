package com.task.app.ui.screens

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import org.junit.Rule
import org.junit.Test

class LoginScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testLoginNavigation() {
        var navigatedRoute: String? = null
        val mockNavigator = object : Navigator {
            override fun navigateTo(route: String) {
                navigatedRoute = route
            }
        }

        composeTestRule.setContent {
            LoginScreen(navigator = mockNavigator)
        }

        // Simulate user input and login click
        composeTestRule.onNodeWithText("Enter Username or Email").performTextInput("JohnDoe")
        composeTestRule.onNodeWithText("Login").performClick()

        // Verify navigation route
        assert(navigatedRoute == "medicine_list/JohnDoe")
    }
}
