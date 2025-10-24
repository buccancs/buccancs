package com.buccancs.desktop

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.buccancs.desktop.di.AppGraph
import com.buccancs.desktop.ui.OrchestratorApp

fun main() =
    application {
        val appGraph =
            AppGraph.create()
        val viewModel =
            appGraph.provideAppViewModel()
        val grpcServer =
            appGraph.provideGrpcServer()
        val mdnsBrowser =
            appGraph.provideMdnsServiceBrowser()

        try {
            grpcServer.start()
        } catch (e: IllegalStateException) {
            println(
                "ERROR: ${e.message}"
            )
            println(
                "Please ensure no other Buccancs instance is running or change the port."
            )
            exitApplication()
            return@application
        }

        Window(
            onCloseRequest = {
                grpcServer.stop()
                mdnsBrowser.stop()
                appGraph.shutdown()
                exitApplication()
            },
            title = "Buccancs Orchestrator"
        ) {
            OrchestratorApp(
                viewModel = viewModel
            )
        }
    }
