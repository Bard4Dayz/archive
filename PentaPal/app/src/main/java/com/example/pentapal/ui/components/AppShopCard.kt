package com.example.pentapal.ui.components

import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.gestures.horizontalDrag
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
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
import com.example.pentapal.ui.models.skillCost
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import java.util.stream.IntStream
import kotlin.math.roundToInt

@Composable
fun AppShopCard(deck: List<skillCost>) {
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
            Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxSize()){
                if (state.flipState.value == SwipeState.UNFLIPPED){
                    Row(modifier = Modifier
                        .padding(vertical = 4.dp, horizontal = 18.dp)){
                        Text(text = deck[currentCardIndex.value].skillFull?.name ?: "", style = MaterialTheme.typography.h2)
                    }
                    Row(modifier = Modifier
                        .padding(4.dp)){
                        Text(text = "Level: ${deck[currentCardIndex.value].skillFull?.level ?: 0}", style = MaterialTheme.typography.body2)
                    }
                    Row(modifier = Modifier
                        .padding(4.dp)){
                        var slots = ""
                        for (i in IntStream.range(0, deck[currentCardIndex.value].skillFull?.slots!!.size)){
                            slots += (deck[currentCardIndex.value].skillFull?.slots?.get(i) ?: "") + "/"
                        }
                        slots = slots.slice(IntRange(0, slots.length-2))
                        Text(text = "${deck[currentCardIndex.value].skillFull?.type ?: ""} | ${slots}", style = MaterialTheme.typography.body2)
                    }
                    if (deck[currentCardIndex.value].skillFull?.traits?.get(0) != "") Row(modifier = Modifier
                        .padding(4.dp)){
                        var traits = ""
                        for (i in IntStream.range(0, deck[currentCardIndex.value].skillFull?.traits!!.size)){
                            traits += (deck[currentCardIndex.value].skillFull?.traits?.get(i) ?: "") + "/"
                        }
                        traits = traits.slice(IntRange(0, traits.length-2))
                        Text(text = traits, style = MaterialTheme.typography.body2)
                    }
                    if((deck[currentCardIndex.value].skillFull?.description ?: "").length <= 300){
                        Row(modifier = Modifier
                            .padding(4.dp)){
                            Text(text = deck[currentCardIndex.value].skillFull?.description ?: "", style = MaterialTheme.typography.body1)
                        }
                    }
                    else if((deck[currentCardIndex.value].skillFull?.description ?: "").length <= 500) {
                        Row(modifier = Modifier
                            .padding(4.dp)){
                            Text(text = deck[currentCardIndex.value].skillFull?.description ?: "", style = MaterialTheme.typography.overline)
                        }
                    } else {
                        Row(modifier = Modifier
                            .padding(4.dp)){
                            Text(text = (deck[currentCardIndex.value].skillFull?.description ?: "").slice(IntRange(0, 460)), style = MaterialTheme.typography.overline)
                        }
                        Row(modifier = Modifier
                            .padding(4.dp)){
                            Text(text = "Continued on back...", style = MaterialTheme.typography.overline)
                        }
                    }
                    Row(modifier = Modifier
                        .padding(4.dp)){
                        Text(text = "Price: ${deck[currentCardIndex.value].price ?: 0}", style = MaterialTheme.typography.body2)
                    }
                } else {
                    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
                        if((deck[currentCardIndex.value].skillFull?.description ?: "").length > 500) {
                            Row(modifier = Modifier
                                .padding(4.dp)){
                                Text(text = (deck[currentCardIndex.value].skillFull?.description ?: "").slice(IntRange(461, (deck[currentCardIndex.value]?.skillFull?.description ?: "").length-1)), style = MaterialTheme.typography.overline)
                            }
                        }
                        Row(
                            modifier = Modifier
                                .padding(4.dp)){
                            Text(text = deck[currentCardIndex.value].skillFull?.set ?: "", style = MaterialTheme.typography.h3)
                        }
                    }
                }
            }
        }
        Text(text = "${currentCardIndex.value + 1}/${deck.size}", style = MaterialTheme.typography.overline)
    }
}