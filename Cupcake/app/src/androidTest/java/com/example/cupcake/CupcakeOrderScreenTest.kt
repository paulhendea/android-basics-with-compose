package com.example.cupcake

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.example.cupcake.ui.SelectOptionScreen
import com.example.cupcake.ui.StartOrderScreen
import org.junit.Rule
import org.junit.Test

class CupcakeOrderScreenTest {

    @get:Rule
    val composeTestResult = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun selectOptionScreen_verifyContent() {
        val flavors = listOf("Vanilla", "Chocolate", "Hazelnut", "Cookie", "Mango")
        val subtotal = "$100"

        composeTestResult.setContent {
            SelectOptionScreen(
                subtotal = subtotal,
                options = flavors,
                onSelectionChanged = {},
                onNextButtonClicked = {},
                onCancelButtonClicked = {},
            )
        }

        flavors.forEach { flavor ->
            composeTestResult.onNodeWithText(flavor).assertIsDisplayed()
        }

        val subtotalText = composeTestResult.activity.getString(R.string.subtotal_price, subtotal)
        composeTestResult.onNodeWithText(subtotalText).assertIsDisplayed()

        val nextButton = composeTestResult.onNodeWithStringId(R.string.next)
        nextButton.assertIsNotEnabled()

        composeTestResult.onNodeWithText(flavors.first()).performClick()
        nextButton.assertIsEnabled()
    }

    @Test
    fun startScreen_verifyContent() {
        val quantityOptions = listOf(
            Pair(R.string.one_cupcake, 1),
            Pair(R.string.six_cupcakes, 6),
            Pair(R.string.twelve_cupcakes, 12),
        )

        composeTestResult.setContent {
            StartOrderScreen(
                quantityOptions = quantityOptions,
                onNextButtonClicked = {},
            )
        }

        quantityOptions.forEach { option ->
            val optionText = composeTestResult.activity.getString(option.first)
            composeTestResult.onNodeWithText(optionText).assertIsDisplayed()
        }
    }
}