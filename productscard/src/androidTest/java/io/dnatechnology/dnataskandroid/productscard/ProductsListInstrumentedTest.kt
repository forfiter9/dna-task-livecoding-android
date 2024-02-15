package io.dnatechnology.dnataskandroid.productscard

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import io.dnatechnology.dnataskandroid.core.design.theme.DNATaskAndroidTheme
import io.dnatechnology.dnataskandroid.productscard.presentation.composable.ProductsView
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class ProductsListInstrumentedTest {

    @get:Rule
    val composeTestRule = createComposeRule()
    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Before
    fun setUp() {
        hiltRule.inject()
    }

    @Test
    fun loadingTextIsVisibleOnStart() {
        composeTestRule.setContent {
            DNATaskAndroidTheme {
                ProductsView()
            }
        }

        composeTestRule.onNode(hasText("LOADING")).assertIsDisplayed()
    }

    @OptIn(ExperimentalTestApi::class)
    @Test
    fun productListIsVisibleAfterDownload() {
        composeTestRule.setContent {
            DNATaskAndroidTheme {
                ProductsView()
            }
        }

        composeTestRule.waitUntilDoesNotExist(hasText("LOADING"))
        composeTestRule.onNodeWithTag("ProductList").assertIsDisplayed()
    }
}