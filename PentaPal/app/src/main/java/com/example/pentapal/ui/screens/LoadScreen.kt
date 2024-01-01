package com.example.pentapal.ui.screens

import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.modifier.modifierLocalOf
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import com.example.pentapal.ui.navigation.Routes
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback
import kotlinx.coroutines.delay

@Composable
fun LoadScreen(navHostController: NavHostController) {
    LaunchedEffect(true) {
        delay(10000)
        navHostController.navigate(
            Routes.result.route
        ) {
            (navHostController.graph.findNode(Routes.generate.route)?.id)?.let {
                popUpTo(it) {
                    inclusive = true
                }
            }
        }
    }
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.SpaceAround
    ) {
        Text(
            text = "Generating...",
            modifier = Modifier.fillMaxWidth(),
            style = MaterialTheme.typography.h2,
            textAlign = TextAlign.Center
        )
        Spacer(modifier= Modifier.size(100.dp))
        AndroidView(modifier = Modifier.fillMaxWidth(), factory = { context ->
            AdView(context).apply {
                setAdSize(AdSize.MEDIUM_RECTANGLE)
                adUnitId = "ca-app-pub-3940256099942544/6300978111"
                loadAd(AdRequest.Builder().build())

            }
        })
    }
}