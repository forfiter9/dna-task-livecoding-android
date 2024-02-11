package io.dnatechnology.dnataskandroid

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import dagger.hilt.android.AndroidEntryPoint
import io.dnatechnology.dnataskandroid.core.design.theme.DNATaskAndroidTheme
import io.dnatechnology.dnataskandroid.core.design.theme.MainBackground
import io.dnatechnology.dnataskandroid.productscard.presentation.composable.ProductsView

@AndroidEntryPoint
class RootComposeActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DNATaskAndroidTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MainBackground
                ) {
                    ProductsView()
                }
            }
        }
    }
}
