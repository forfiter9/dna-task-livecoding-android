package io.dnatechnology.dnataskandroid.productscard.presentation.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import io.dnatechnology.dnataskandroid.core.design.theme.Black
import io.dnatechnology.dnataskandroid.core.design.theme.White
import io.dnatechnology.dnataskandroid.productscard.R
import io.dnatechnology.dnataskandroid.productscard.presentation.ProductsViewModel


@Composable
fun ProductsView(productsViewModel: ProductsViewModel) {
    LaunchedEffect(Unit) {
        productsViewModel.getProducts()
    }
    val products = productsViewModel.products.collectAsState().value
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (products != null) {
            ProductsListContent(
                products = products,
                productsViewModel = productsViewModel
            )
        } else {
            Text(text = stringResource(R.string.loading))
        }

        Row(
            Modifier
                .background(White)
                .fillMaxWidth()
                .height(dimensionResource(id = R.dimen.pay_button_height)),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = stringResource(R.string.pay), color = Black)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ProductsView(productsViewModel = ProductsViewModel())
}