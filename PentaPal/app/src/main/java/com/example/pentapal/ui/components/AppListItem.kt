package com.example.pentapal.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.ViewCarousel
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun AppListItem(
    onViewPressed: () -> Unit = {},
    header: String,
    subtitle: String = "",
    extra: String = "",
) {
    Surface(
        elevation = 2.dp,
        shape = MaterialTheme.shapes.medium
    ) {
        Column() {
            Row(modifier = Modifier.padding(horizontal = 9.dp, vertical = 1.dp).fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Text(text = header, style = MaterialTheme.typography.h3)
                IconButton(modifier = Modifier.size(44.dp), onClick = onViewPressed) {
                    Icon(imageVector = Icons.Default.ViewCarousel, contentDescription = "Edit button")
                }
            }
            if(subtitle != ""){
                Row(modifier = Modifier.padding(horizontal = 9.dp, vertical = 1.dp)) {
                    Text(text = subtitle, style = MaterialTheme.typography.body1)
                }
            }
            if (extra != ""){
                Row(modifier = Modifier.padding(horizontal = 9.dp, vertical = 1.dp)) {
                    Text(text = extra, style = MaterialTheme.typography.body1)
                }
            }
        }
    }
}