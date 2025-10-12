package com.buccancs.desktop

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.buccancs.desktop.di.AppGraph
import com.buccancs.desktop.ui.DesktopApp

fun main() = application {
    val appGraph = AppGraph.create()
    val viewModel = appGraph.provideAppViewModel()
    val grpcServer = appGraph.provideGrpcServer()
    grpcServer.start()
    Window(onCloseRequest = {
        grpcServer.stop()
        appGraph.shutdown()
        exitApplication()
    }, title = "Buccancs Control") {
        DesktopApp(viewModel = viewModel)
    }
}
