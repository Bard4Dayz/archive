package com.example.pentapal.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.*

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun AppDropdown(onValueChange: (String) -> Unit, options: List<String>, label: String) {
    var expanded by remember {mutableStateOf(false)}
    var selectedOption by remember { mutableStateOf(options[0]) }

    Column(modifier = Modifier.padding(8.dp)){
        ExposedDropdownMenuBox(expanded = expanded, onExpandedChange = {expanded = !expanded}) {
            TextField(
                modifier = Modifier.width(170.dp),
                readOnly = true,
                value = selectedOption,
                onValueChange = {},
                label = {Text(label)},
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(
                        expanded = !expanded
                    )
                },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    backgroundColor = MaterialTheme.colors.primary,
                    focusedLabelColor = MaterialTheme.colors.secondary
                )
            )
            DropdownMenu(
                modifier = Modifier.width(170.dp),
                expanded = expanded,
                onDismissRequest = {expanded = false},
            ){
                options.forEach { selectionOption ->
                    DropdownMenuItem(onClick = {
                        selectedOption = selectionOption
                        expanded = false
                        onValueChange(selectedOption)
                    }) {
                        Text(selectionOption)
                    }
                }
            }
        }
    }
}