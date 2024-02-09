package io.dnatechnology.dnataskandroid

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.test.ext.junit.runners.AndroidJUnit4
import io.dnatechnology.dnataskandroid.ui.ProductsView
import io.dnatechnology.dnataskandroid.ui.theme.DNATaskAndroidTheme
import io.dnatechnology.dnataskandroid.ui.viewmodel.ProductsModel
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
            DNATaskAndroidTheme {
                ProductsView(productsModel = ProductsModel())
            }
        }

        composeTestRule.onNode(hasText("LOADING")).assertIsDisplayed()
    }

    @OptIn(ExperimentalTestApi::class)
    @Test
    fun productListIsVisibleAfterDownload() {
        composeTestRule.setContent {
            DNATaskAndroidTheme {
                ProductsView(productsModel = ProductsModel())
            }
        }

        composeTestRule.waitUntilDoesNotExist(hasText("LOADING"))
        composeTestRule.onNodeWithTag("ProductList").assertIsDisplayed()
    }
}