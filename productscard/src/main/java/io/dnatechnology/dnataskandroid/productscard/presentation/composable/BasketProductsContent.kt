package io.dnatechnology.dnataskandroid.productscard.presentation.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import io.dnatechnology.dnataskandroid.core.design.theme.White
import io.dnatechnology.dnataskandroid.productscard.R
import io.dnatechnology.dnataskandroid.productscard.domain.model.BasketProduct
import java.math.RoundingMode

@Composable
fun BasketProductsContent(
    basketProducts: List<BasketProduct>,
    totalPrice: Double
) {
    Column(
        verticalArrangement = Arrangement.Top,
        modifier = Modifier
            .background(White)
            .wrapContentSize(Alignment.TopCenter)
            .padding(dimensionResource(id = R.dimen.product_item_padding_bottom))
    )
    {
        Text(text = stringResource(id = R.string.basket))

        for (product in basketProducts) {
            BasketProductItem(basketProduct = product)
        }

        Text(text = stringResource(id = R.string.total_price) + totalPrice.toBigDecimal().setScale(2, RoundingMode.UP))
    }
}