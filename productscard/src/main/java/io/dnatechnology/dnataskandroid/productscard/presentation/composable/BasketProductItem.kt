package io.dnatechnology.dnataskandroid.productscard.presentation.composable

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import io.dnatechnology.dnataskandroid.core.design.theme.Black
import io.dnatechnology.dnataskandroid.core.design.theme.Typography
import io.dnatechnology.dnataskandroid.productscard.domain.model.BasketProduct

@Composable
fun BasketProductItem(
    basketProduct: BasketProduct
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Text(
            text = basketProduct.productAmount.toString(),
            style = Typography.bodyMedium,
            color = Black
        )
        Text(
            text = basketProduct.product.name,
            style = Typography.bodyMedium,
            color = Black,
        )
    }
}