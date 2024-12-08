package com.task.app.ui.screens

import androidx.compose.runtime.Composable
import org.junit.Assert.assertEquals
import org.junit.Test

class GreetingMessageTest {

    @Test
    fun testMorningGreeting() {
        val morningHour = 8 // Simulate 8 AM
        val greeting = getGreetingMessage(morningHour)
        assertEquals("Good Morning", greeting)
    }

    @Test
    fun testAfternoonGreeting() {
        val afternoonHour = 14 // Simulate 2 PM
        val greeting = getGreetingMessage(afternoonHour)
        assertEquals("Good Afternoon", greeting)
    }

    @Test
    fun testEveningGreeting() {
        val eveningHour = 18 // Simulate 6 PM
        val greeting = getGreetingMessage(eveningHour)
        assertEquals("Good Evening", greeting)
    }

    @Test
    fun testNightGreeting() {
        val nightHour = 22 // Simulate 10 PM
        val greeting = getGreetingMessage(nightHour)
        assertEquals("Good Night", greeting)
    }
}
