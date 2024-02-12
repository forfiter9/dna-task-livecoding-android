package io.dnatechnology.dnataskandroid.productscard.presentation.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import io.dnatechnology.dnataskandroid.core.design.theme.Black
import io.dnatechnology.dnataskandroid.core.design.theme.Typography
import io.dnatechnology.dnataskandroid.core.design.theme.White
import io.dnatechnology.dnataskandroid.productscard.R
import io.dnatechnology.dnataskandroid.productscard.data.source.remote.api.Product
import io.dnatechnology.dnataskandroid.productscard.presentation.ProductsCardViewModel

@Composable
fun ProductItem(
    product: Product,
    productsCardViewModel: ProductsCardViewModel
){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = dimensionResource(R.dimen.product_item_padding_horizontal))
            .padding(bottom = dimensionResource(R.dimen.product_item_padding_bottom))
            .background(White)
            .border(dimensionResource(R.dimen.product_item_border), Black)
            .clickable {
                productsCardViewModel.addToCart(product)
            }
    ) {
        Text(
            text = product.toString(),
            style = Typography.bodyMedium,
            color = Black,
            modifier = Modifier
                .padding(horizontal = dimensionResource(R.dimen.product_item_text_padding))
                .padding(vertical = dimensionResource(R.dimen.product_item_text_padding))
        )
    }
}