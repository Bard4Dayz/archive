package com.example.pentapal.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun BigAppButton (onClick: () -> Unit, text: String) {
    OutlinedButton(onClick = onClick,
        modifier = Modifier.fillMaxWidth().height(100.dp).padding(8.dp),
        shape = RoundedCornerShape(40.dp),
        elevation = ButtonDefaults.elevation(),
        border = BorderStroke(2.dp, MaterialTheme.colors.secondaryVariant),
        colors = ButtonDefaults.buttonColors()
    ) {
        Text(text = text)
    }
}