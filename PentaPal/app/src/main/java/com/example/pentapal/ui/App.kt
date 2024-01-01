package com.example.pentapal.ui

import androidx.compose.runtime.Composable
import com.example.pentapal.ui.navigation.RootNavigation
import com.example.pentapal.ui.theme.PentaPalTheme

@Composable
fun App() {
    PentaPalTheme() {
        RootNavigation()
    }
}