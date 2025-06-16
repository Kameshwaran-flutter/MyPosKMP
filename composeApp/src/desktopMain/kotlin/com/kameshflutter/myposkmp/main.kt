package com.kameshflutter.myposkmp

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowPlacement
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        state = rememberWindowState(WindowPlacement.Maximized, width = Dp.Unspecified, height = Dp.Unspecified),
        title = "MyPosKMP",
    ) {
        App()
    }
}