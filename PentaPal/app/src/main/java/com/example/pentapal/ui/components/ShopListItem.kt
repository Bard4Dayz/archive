package com.example.pentapal.ui.components

import androidx.compose.runtime.Composable
import com.example.pentapal.ui.models.Encounter
import com.example.pentapal.ui.models.Shop

@Composable
fun ShopListItem(
    shop: Shop,
    onViewPressed: () -> Unit = {}
) {
    AppListItem(header = shop.name ?: "No Name Found", subtitle = "No. of Skills: ${shop.options?.size}" ?: "", onViewPressed = onViewPressed)
}