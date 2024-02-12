package io.dnatechnology.dnataskandroid.productscard.presentation.composable

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.runtime.currentCompositionLocalContext
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import io.dnatechnology.dnataskandroid.core.design.theme.Black
import io.dnatechnology.dnataskandroid.core.design.theme.White
import io.dnatechnology.dnataskandroid.productscard.R
import io.dnatechnology.dnataskandroid.productscard.presentation.PaymentStatus
import io.dnatechnology.dnataskandroid.productscard.presentation.ProductsCardViewModel
import io.dnatechnology.dnataskandroid.productscard.presentation.showMessage


@Composable
fun ProductsView(
    productsCardViewModel: ProductsCardViewModel = hiltViewModel()
) {
    LaunchedEffect(Unit) {
        productsCardViewModel.getProducts()
    }

    val productsCardUiState = productsCardViewModel.productCardUiState.collectAsState().value

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (productsCardUiState.products != null) {
            ProductsListContent(
                products = productsCardUiState.products,
                productsCardViewModel = productsCardViewModel
            )
        } else {
            Text(text = stringResource(R.string.loading))
        }

        if(productsCardUiState.basketProducts.isNotEmpty()) {
            BasketProductsContent(
                basketProducts = productsCardUiState.basketProducts,
                totalPrice = productsCardUiState.totalPrice
            )
        }

        Row(
            Modifier
                .background(White)
                .fillMaxWidth()
                .height(dimensionResource(id = R.dimen.pay_button_height))
                .clickable(
                    enabled = productsCardUiState.basketProducts.size > 0 && productsCardUiState.paymentStatus != PaymentStatus.IsProcessing,
                    onClick = {
                        productsCardViewModel.pay(productsCardUiState.basketProducts, productsCardUiState.totalPrice)
                    })
            ,
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically

        ) {
            Text(text = stringResource(R.string.pay), color = Black)
        }

        when (productsCardUiState.paymentStatus) {
            PaymentStatus.CardReaderFailed -> {
                LocalContext.current.showMessage(stringResource(R.string.card_reader_failed))
                productsCardViewModel.resetPaymentStatus()
            }
            PaymentStatus.IsProcessing -> LocalContext.current.showMessage(stringResource(R.string.payment_processing))
            PaymentStatus.PaymentFailed -> {
                LocalContext.current.showMessage(stringResource(R.string.payment_failed))
                productsCardViewModel.resetPaymentStatus()
            }
            PaymentStatus.PaymentSuccess -> {
                LocalContext.current.showMessage(stringResource(R.string.payment_success))
                productsCardViewModel.resetPaymentStatus()
            }
            PaymentStatus.None -> {}
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ProductsView()
}