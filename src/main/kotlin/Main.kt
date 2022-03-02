// Copyright 2000-2021 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.
import androidx.compose.material.MaterialTheme
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowState
import androidx.compose.ui.window.application
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.lang.Thread.sleep

//Puntos:
//Cara frontal:
var p1 = mutableListOf(200,200,0) //esquina superior izquierda
var p2 = mutableListOf(400,200,0) //esquina superior derecha
var p3 = mutableListOf(200,400,0) //esquina inferior izquierda
var p4 = mutableListOf(400,400,0) //esquina inferior derecha

//Cara trasera:

var p5 = mutableListOf(200,200,2)//esquina superior izquierda
var p6 = mutableListOf(400,200,2) //esquina superior derecha
var p7 = mutableListOf(200,400,2) //esquina inferior izquierda
var p8 = mutableListOf(400,400,2) //esquina inferior derecha

//Relaciones:
val edges = mapOf(
    p1 to listOf(p2,p3,p5),
    p2 to listOf(p1,p4,p6),
    p3 to listOf(p1,p4,p7),
    p4 to listOf(p2,p3,p8),
    p5 to listOf(p1,p6,p7),
    p6 to listOf(p2,p5,p8),
    p7 to listOf(p3,p5,p8),
    p8 to listOf(p4,p7,p6),
)

var vertices = listOf(p1,p2,p3,p4,p5,p6,p7,p8)



@Composable
fun App() {
    var height by remember { mutableStateOf(0) }
    var width by remember { mutableStateOf(0) }

    rememberCoroutineScope().launch {
        while (true) {
            delay(50)
            if (height == 25) {height = -25} else {height++}
            if (width == 25) {width = -25} else {width++}
        }
    }

    Box(modifier=Modifier.fillMaxSize()) {
        for (v in vertices) {
            Box(
                modifier = Modifier.absoluteOffset { IntOffset(v[0] + height*v[2], v[1] + width*v[2]) }.size(10.dp)
                    .background(color = Color.Red)
            )
        }
        for (a in edges.keys) {
            for (b in edges[a]!!) {
                Box(modifier = Modifier.absoluteOffset {
                    IntOffset(
                        ((a[0] + b[0]) / 2)+(height)*((a[2] + b[2]) / 2),
                        ((a[1] + b[1]) / 2)+(width)*((a[2] + b[2]) / 2),
                    )
                }.size(5.dp).background(color = Color.Green))
                
            }
        }

    }

}

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "Falso 3D",
        resizable = false,
        state = WindowState(size = DpSize(600.dp, 600.dp))
    ) {
        App()
    }
}
