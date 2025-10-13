package com.example.tipcalculator

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performScrollTo
import androidx.compose.ui.test.performTextClearance
import androidx.compose.ui.test.performTextInput
import com.example.tipcalculator.ui.theme.TipCalculatorTheme
import org.junit.Rule
import org.junit.Test
import java.text.NumberFormat

class TipUITests {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun calculate_20_percent_tip() {
        composeTestRule.setContent {
            TipCalculatorTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    TipTimeLayout(
                        modifier = Modifier
                            .padding(innerPadding)
                            .fillMaxSize()
                    )
                }
            }
        }

        composeTestRule
            .onNodeWithText("Bill Amount")
            .performTextInput("10")
        composeTestRule
            .onNodeWithText("Tip Percentage")
            .apply {
                performTextClearance()
                performTextInput("20")
            }

        val expectedTip = NumberFormat.getCurrencyInstance().format(2)
        composeTestRule
            .onNodeWithText("Tip Amount: $expectedTip")
            .performScrollTo()
            .assertExists("No node with this text was found.")
    }
}