package com.buccancs.desktop

import com.buccancs.desktop.di.AppGraph
import java.util.concurrent.CountDownLatch
import kotlin.system.exitProcess

/**
 * Headless entry point that spins up the gRPC orchestrator without launching the Compose UI.
 * Use for automation or CLI-driven validation where only backend services are required.
 */
fun main() {
    val latch =
        CountDownLatch(
            1
        )

    val appGraph =
        try {
            AppGraph.create()
        } catch (ex: Exception) {
            System.err.println(
                "Failed to initialise AppGraph: ${ex.message}"
            )
            ex.printStackTrace()
            exitProcess(
                1
            )
        }

    val grpcServer =
        appGraph.provideGrpcServer()
    val mdnsBrowser =
        appGraph.provideMdnsServiceBrowser()

    val shutdownHook =
        Thread {
            println(
                "Shutting down Buccancs headless orchestrator…"
            )
            runCatching { mdnsBrowser.stop() }
            runCatching { grpcServer.stop() }
            runCatching { appGraph.shutdown() }
            latch.countDown()
        }.apply {
            name =
                "OrchestratorShutdown"
        }

    Runtime.getRuntime()
        .addShutdownHook(
            shutdownHook
        )

    try {
        grpcServer.start()
        println(
            "Buccancs headless orchestrator running on port 50051 (press Ctrl+C to stop)"
        )
        latch.await()
    } catch (ex: Exception) {
        System.err.println(
            "Failed to start headless orchestrator: ${ex.message}"
        )
        ex.printStackTrace()
        runCatching { appGraph.shutdown() }
        exitProcess(
            1
        )
    }
}
