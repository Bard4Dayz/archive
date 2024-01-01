package com.example.pentapal.ui.components

import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.gestures.horizontalDrag
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.ViewCarousel
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.input.pointer.positionChange
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.pentapal.ui.models.Enemy
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlin.math.roundToInt

@Composable
fun AppEnemyCard(deck: List<Enemy>, navHostController: NavHostController) {
    val state = rememberFlashcardState()
    val currentCardIndex = remember { mutableStateOf(0) }
    val scope = rememberCoroutineScope()
    Column(
        modifier= Modifier.fillMaxWidth().padding(vertical = 8.dp),
        verticalArrangement = Arrangement.SpaceAround,
        horizontalAlignment = Alignment.CenterHorizontally)
    {
        Surface(
            modifier = Modifier
                .height(500.dp)
                .width(350.dp)
                .graphicsLayer {
                    rotationY = state.rotation.value
                }
                .offset { IntOffset(state.offsetX.value.roundToInt(), 0) }
                .pointerInput(Unit) {
                    coroutineScope {
                        while (true) {
                            val pointerId =
                                awaitPointerEventScope { awaitFirstDown().id }
                            state.rotation.stop()
                            state.offsetX.stop()
                            awaitPointerEventScope {
                                horizontalDrag(pointerId) {
                                    launch {
                                        state.offsetX.snapTo(state.offsetX.value + it.positionChange().x)
                                    }
                                }
                            }
                            launch {
                                if (state.offsetX.value < -450f && currentCardIndex.value < deck.size - 1) {
                                    state.offsetX.animateTo(-1100f)
                                    state.offsetX.snapTo((1100f))
                                    currentCardIndex.value++
                                    state.flipState.value = SwipeState.UNFLIPPED
                                    state.offsetX.animateTo(0f)
                                } else if (state.offsetX.value > 450f && currentCardIndex.value > 0) {
                                    state.offsetX.animateTo(1100f)
                                    state.offsetX.snapTo(-1100f)
                                    currentCardIndex.value--
                                    state.flipState.value = SwipeState.UNFLIPPED
                                    state.offsetX.animateTo(0f)
                                } else {
                                    state.offsetX.animateTo(0f)
                                }
                            }
                        }
                    }
                },
            elevation = 2.dp,
            shape = MaterialTheme.shapes.medium
        ) {
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End){
                IconButton(onClick = {
                    scope.launch {
                        coroutineScope {
                            state.rotation.animateTo(-89f, animationSpec = tween(easing = LinearOutSlowInEasing))
                            state.rotation.snapTo(89f)
                            if (state.flipState.value == SwipeState.UNFLIPPED) {
                                state.flipState.value = SwipeState.FLIPPED
                            } else if (state.flipState.value == SwipeState.FLIPPED) {
                                state.flipState.value = SwipeState.UNFLIPPED
                            }
                            state.rotation.animateTo(0f)
                        }
                    }
                }){
                    Icon(Icons.Default.Refresh, contentDescription = "Flip Card")
                }
            }
            Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center, modifier = Modifier.fillMaxSize()){
                if (state.flipState.value == SwipeState.UNFLIPPED){
                    Row(modifier = Modifier
                        .padding(vertical = 4.dp, horizontal = 18.dp)){
                        Text(text = deck[currentCardIndex.value].name ?: "", style = MaterialTheme.typography.h2)
                    }
                    Row(modifier = Modifier
                        .padding(4.dp)){
                        Text(text = deck[currentCardIndex.value].type ?: "", style = MaterialTheme.typography.body2)
                    }
                    Row(modifier = Modifier
                        .padding(4.dp)){
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Text(text = "Speed", style = MaterialTheme.typography.body2)
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(text = "${deck[currentCardIndex.value].stats?.speed ?: 0}", style = MaterialTheme.typography.body1)
                        }
                        Spacer(modifier = Modifier.width(10.dp))
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Text(text = "Power", style = MaterialTheme.typography.body2)
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(text = "${deck[currentCardIndex.value].stats?.power ?: 0}", style = MaterialTheme.typography.body1)
                        }
                        Spacer(modifier = Modifier.width(10.dp))
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Text(text = "Vigor", style = MaterialTheme.typography.body2)
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(text = "${deck[currentCardIndex.value].stats?.vigor ?: 0}", style = MaterialTheme.typography.body1)
                        }
                        Spacer(modifier = Modifier.width(10.dp))
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Text(text = "Magic", style = MaterialTheme.typography.body2)
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(text = "${deck[currentCardIndex.value].stats?.magic ?: 0}", style = MaterialTheme.typography.body1)
                        }
                        Spacer(modifier = Modifier.width(10.dp))
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Text(text = "Focus", style = MaterialTheme.typography.body2)
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(text = "${deck[currentCardIndex.value].stats?.focus ?: 0}", style = MaterialTheme.typography.body1)
                        }
                    }
                    Row(modifier = Modifier
                        .padding(4.dp)){
                        Text(text = "Connection List", style = MaterialTheme.typography.h3)
                    }
                    for (slot in deck[currentCardIndex.value].slots!!){
                        Row(modifier = Modifier
                            .padding(4.dp)){
                            Text(text = "${slot.slot}: ${slot.connections}", style = MaterialTheme.typography.body2)
                        }
                    }
                } else {
                    Row(modifier = Modifier
                        .padding(1.dp)){
                        Text(text = "Skills", style = MaterialTheme.typography.body2)
                    }
                    for (slot in deck[currentCardIndex.value].slots!!){
                        Column(horizontalAlignment = Alignment.Start){
                            Row(modifier = Modifier
                                .padding(horizontal = 6.dp, vertical = 1.dp)){
                                Text(text = "${slot.slot}", style = MaterialTheme.typography.body2)
                            }
                            Row(modifier = Modifier
                                .padding(horizontal = 6.dp, vertical = 1.dp).fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween){
                                Text(text = slot.skillFull?.name ?: "", style = MaterialTheme.typography.h4)
                                IconButton(
                                    modifier = Modifier.size(34.dp),
                                    onClick = { navHostController.navigate("viewskill?id=${slot.skillFull?.id}") }) {
                                    Icon(imageVector = Icons.Default.ViewCarousel, contentDescription = "Edit button")
                                }
                            }
                        }

                    }
                    if ((deck[currentCardIndex.value].passive ?: "") != "")Row(modifier = Modifier
                        .padding(4.dp)){
                        Text(text = "Passive: ${deck[currentCardIndex.value].passive ?: ""}", style = MaterialTheme.typography.overline)
                    }
                }
            }
        }
        Text(text = "${currentCardIndex.value + 1}/${deck.size}", style = MaterialTheme.typography.overline)
    }
}