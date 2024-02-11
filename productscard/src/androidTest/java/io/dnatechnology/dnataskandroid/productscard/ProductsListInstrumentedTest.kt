package io.dnatechnology.dnataskandroid.productscard

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.test.ext.junit.runners.AndroidJUnit4
import io.dnatechnology.dnataskandroid.productscard.presentation.ProductsViewModel
import io.dnatechnology.dnataskandroid.productscard.presentation.composable.ProductsView
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ProductsListInstrumentedTest {

    @get:Rule
    val composeTestRule = createComposeRule()
    @Test
    fun loadingTextIsVisibleOnStart() {
        composeTestRule.setContent {
            io.dnatechnology.dnataskandroid.core.design.theme.DNATaskAndroidTheme {
                ProductsView(productsViewModel = ProductsViewModel())
            }
        }

        composeTestRule.onNode(hasText("LOADING")).assertIsDisplayed()
    }

    @OptIn(ExperimentalTestApi::class)
    @Test
    fun productListIsVisibleAfterDownload() {
        composeTestRule.setContent {
            io.dnatechnology.dnataskandroid.core.design.theme.DNATaskAndroidTheme {
                ProductsView(productsViewModel = ProductsViewModel())
            }
        }

        composeTestRule.waitUntilDoesNotExist(hasText("LOADING"))
        composeTestRule.onNodeWithTag("ProductList").assertIsDisplayed()
    }
}