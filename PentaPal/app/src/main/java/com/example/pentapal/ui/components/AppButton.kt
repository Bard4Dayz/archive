package com.example.pentapal.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


@Composable
fun AppButton (onClick: () -> Unit, text: String) {
    OutlinedButton(onClick = onClick,
        modifier = Modifier.padding(8.dp),
        shape = MaterialTheme.shapes.medium,
        elevation = ButtonDefaults.elevation(),
        border = BorderStroke(2.dp, MaterialTheme.colors.secondaryVariant),
        colors = ButtonDefaults.buttonColors()
    ) {
        Text(text = text)
    }
}
