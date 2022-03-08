// Copyright 2000-2021 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowState
import androidx.compose.ui.window.application
import kotlin.math.pow
import kotlin.math.sqrt

@Composable
fun App(){
    var cuboUI by remember { mutableStateOf(Cube(100,Point(250f,250f,250f))) }
    var refresh by remember { mutableStateOf(0) }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()) {
        Box(modifier = Modifier.size(500.dp,500.dp).padding(20.dp)) {
            Text(refresh.toString(), color = Color.Transparent)
            for (v in cuboUI.vertices) {
                Box(modifier = Modifier.absoluteOffset(v.x.dp,v.z.dp).size(5.dp).background(color = Color.Red))
            }
            for (a in cuboUI.edges.keys) {
                for (b in cuboUI.edges[a]!!) {
                    var distance = sqrt((a.x-b.x).pow(2)+(a.z-b.z).pow(2))
                    var distances = listOf( distance/5, distance/4, distance/3, distance/2)
                    for (d in distances) {
                        var cX = a.x - (d*(a.x-b.x))/distance
                        var cZ = a.z - (d*(a.z-b.z))/distance
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
        Row {
            Button(onClick = {cuboUI.rotateY(1f);refresh++})  {Text("Rotate Y")}
            Button(onClick = {cuboUI.rotateX(1f);refresh++}) {Text("Rotate X")}
            Button(onClick = {cuboUI.rotateZ(1f);refresh++}) {Text("Rotate Z")}
        }
        Row {
            Button(onClick = {cuboUI.scaleY(10);refresh++})  {Text("Scale Y")}
            Button(onClick = {cuboUI.rotateX(1f);refresh++}) {Text("Move Y")}
            Button(onClick = {cuboUI.rotateZ(1f);refresh++}) {Text("Scale Z")}
        }
    }
}

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "Falso 3D",
        resizable = false,
        state = WindowState(size = DpSize(600.dp, 700.dp))
    ) {
        App()
    }
}
