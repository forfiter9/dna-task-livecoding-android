package io.dnatechnology.dnataskandroid.productscard.presentation.composable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import io.dnatechnology.dnataskandroid.productscard.R
import io.dnatechnology.dnataskandroid.productscard.data.source.remote.api.Product
import io.dnatechnology.dnataskandroid.productscard.presentation.ProductsCardViewModel

@Composable
fun ProductsListContent(
    products: List<Product>,
    productsCardViewModel: ProductsCardViewModel
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.product_list_content_vertical_space)),
        modifier = Modifier.testTag(stringResource(R.string.product_list_test_tag))
    )
    {
        for (product in products) {
            ProductItem(product = product, productsCardViewModel = productsCardViewModel)
        }
    }
}