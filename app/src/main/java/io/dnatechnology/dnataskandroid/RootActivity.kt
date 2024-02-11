package io.dnatechnology.dnataskandroid

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import io.dnatechnology.dnataskandroid.productscard.presentation.composable.ProductsView
import io.dnatechnology.dnataskandroid.core.design.theme.DNATaskAndroidTheme
import io.dnatechnology.dnataskandroid.core.design.theme.MainBackground
import io.dnatechnology.dnataskandroid.productscard.presentation.ProductsViewModel

class RootComposeActivity : ComponentActivity() {

    private val productsViewModel: ProductsViewModel by viewModels<ProductsViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DNATaskAndroidTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MainBackground
                ) {
                    ProductsView(
                        productsViewModel = productsViewModel
                    )
                }
            }
        }
    }
}
