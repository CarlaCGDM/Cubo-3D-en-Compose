// Copyright 2000-2021 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
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
import kotlin.math.abs
import kotlin.math.pow
import kotlin.math.sqrt

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun App() {
    var cubo = Cube(100,Point(300f,300f,300f))

    Box(modifier=Modifier.fillMaxSize()) {
        var cuboUI by remember { mutableStateOf(Cube(100,Point(300f,300f,300f))) }
        var grados by remember { mutableStateOf(0) }
        Row {
            Button(onClick = { cuboUI.rotateY(1f);grados += 1 }) {
                Text("Rotar 5ºY: $grados")
            }
            Button(onClick = { cuboUI.rotateX(1f);grados += 1 }) {
                Text("Rotar 5ºX: $grados")
            }
            Button(onClick = { cuboUI.rotateZ(1f);grados += 1 }) {
                Text("Rotar 5ºZ: $grados")
            }
        }
                Text("Rotar 5ºY: $grados")
                for (v in cuboUI.vertices) {
                    Box(
                        modifier = Modifier.absoluteOffset { IntOffset(v.x.toInt(),v.z.toInt()) }.size(6.dp)
                            .background(color = Color.Red)
                    )
                }
        for (a in cuboUI.edges.keys) {
            for (b in cuboUI.edges[a]!!) {
                //distancia entre los puntos:
                var d = sqrt((a.x-b.x).pow(2)+(a.z-b.z).pow(2))
                var distancias = listOf(d/8,d/4, d/3,d/2,d/3*2,d/4*2,d/8*2)

                for (distancia in distancias) {
                    var cX = a.x - (distancia*(a.x-b.x))/d
                    var cZ = a.z - (distancia*(a.z-b.z))/d
                    Box(modifier = Modifier.absoluteOffset {
                        IntOffset(
                            cX.toInt(),
                            cZ.toInt(),
                        )

                    }.size(4.dp).background(color = Color.Green))
                }
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
