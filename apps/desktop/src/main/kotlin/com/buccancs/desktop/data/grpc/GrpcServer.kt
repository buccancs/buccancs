package com.buccancs.desktop.data.grpc

import com.buccancs.desktop.data.aggregation.SessionAggregationService
import com.buccancs.desktop.data.grpc.services.CommandServiceImpl
import com.buccancs.desktop.data.grpc.services.DataTransferServiceImpl
import com.buccancs.desktop.data.grpc.services.OrchestrationServiceImpl
import com.buccancs.desktop.data.grpc.services.PreviewServiceImpl
import com.buccancs.desktop.data.grpc.services.SensorStreamServiceImpl
import com.buccancs.desktop.data.grpc.services.TimeSyncServiceImpl
import com.buccancs.desktop.data.recording.SensorRecordingManager
import com.buccancs.desktop.data.repository.CommandRepository
import com.buccancs.desktop.data.repository.DeviceRepository
import com.buccancs.desktop.data.repository.PreviewRepository
import com.buccancs.desktop.data.repository.SessionRepository
import io.grpc.Server
import io.grpc.netty.shaded.io.grpc.netty.NettyServerBuilder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.asExecutor
import org.slf4j.LoggerFactory
import java.util.concurrent.atomic.AtomicBoolean

class GrpcServer(
    private val port: Int,
    private val sessionRepository: SessionRepository,
    private val deviceRepository: DeviceRepository,
    private val previewRepository: PreviewRepository,
    private val sensorRecordingManager: SensorRecordingManager,
    private val commandRepository: CommandRepository,
    private val aggregationService: SessionAggregationService
) {
    private val logger =
        LoggerFactory.getLogger(
            GrpcServer::class.java
        )
    private val started =
        AtomicBoolean(
            false
        )

    private val server: Server =
        NettyServerBuilder
            .forPort(
                port
            )
            .executor(
                Dispatchers.Default.asExecutor()
            )
            .addService(
                OrchestrationServiceImpl(
                    sessionRepository,
                    deviceRepository,
                    commandRepository,
                    sensorRecordingManager
                )
            )
            .addService(
                CommandServiceImpl(
                    commandRepository,
                    deviceRepository
                )
            )
            .addService(
                TimeSyncServiceImpl(
                    deviceRepository
                )
            )
            .addService(
                PreviewServiceImpl(
                    sessionRepository,
                    deviceRepository,
                    previewRepository
                )
            )
            .addService(
                SensorStreamServiceImpl(
                    sessionRepository,
                    sensorRecordingManager
                )
            )
            .addService(
                DataTransferServiceImpl(
                    sessionRepository,
                    aggregationService
                )
            )
            .build()

    fun start() {
        if (started.compareAndSet(
                false,
                true
            )
        ) {
            try {
                server.start()
                logger.info(
                    "gRPC server started on port {}",
                    port
                )
                Runtime.getRuntime()
                    .addShutdownHook(
                        Thread {
                            stop()
                        })
            } catch (e: Exception) {
                started.set(
                    false
                )
                logger.error(
                    "Failed to start gRPC server on port $port",
                    e
                )
                throw IllegalStateException(
                    "Cannot start gRPC server on port $port. Port may already be in use.",
                    e
                )
            }
        }
    }

    fun stop() {
        if (started.compareAndSet(
                true,
                false
            )
        ) {
            server.shutdown()
            logger.info(
                "gRPC server stopped"
            )
        }
    }

    fun awaitTermination() {
        server.awaitTermination()
    }
}
