package com.buccancs.desktop.di

import com.buccancs.desktop.data.aggregation.SessionAggregationService
import com.buccancs.desktop.data.discovery.MdnsServiceBrowser
import com.buccancs.desktop.data.encryption.EncryptionKeyProvider
import com.buccancs.desktop.data.encryption.EncryptionManager
import com.buccancs.desktop.data.erasure.SubjectErasureManager
import com.buccancs.desktop.data.grpc.GrpcServer
import com.buccancs.desktop.data.monitor.DeviceConnectionMonitor
import com.buccancs.desktop.data.recording.SensorRecordingManager
import com.buccancs.desktop.data.repository.CommandRepository
import com.buccancs.desktop.data.repository.DeviceRepository
import com.buccancs.desktop.data.repository.PreviewRepository
import com.buccancs.desktop.data.repository.SessionRepository
import com.buccancs.desktop.data.retention.DataRetentionManager
import com.buccancs.desktop.domain.policy.RetentionPolicy
import com.buccancs.desktop.viewmodel.AppViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths

class AppGraph private constructor(
    private val sessionRepository: SessionRepository,
    private val deviceRepository: DeviceRepository,
    private val previewRepository: PreviewRepository,
    private val commandRepository: CommandRepository,
    private val retentionManager: DataRetentionManager,
    private val grpcServer: GrpcServer,
    private val appViewModel: AppViewModel,
    private val subjectErasureManager: SubjectErasureManager,
    private val mdnsServiceBrowser: MdnsServiceBrowser,
    private val appScope: CoroutineScope,
    private val connectionMonitor: DeviceConnectionMonitor
) {
    fun provideAppViewModel(): AppViewModel = appViewModel
    fun provideGrpcServer(): GrpcServer = grpcServer
    fun provideSubjectErasureManager(): SubjectErasureManager = subjectErasureManager
    fun provideMdnsServiceBrowser(): MdnsServiceBrowser = mdnsServiceBrowser
    fun shutdown() {
        mdnsServiceBrowser.stop()
        connectionMonitor.stop()
        appScope.cancel()
    }

    companion object {
        private const val DEFAULT_PORT = 50051
        private const val SESSION_CAP_BYTES = 10L * 1024 * 1024 * 1024
        fun create(): AppGraph {
            val baseDir = resolveBaseDirectory()
            val keyProvider = EncryptionKeyProvider(baseDir.resolve("keysets").resolve("desktop-aead.json"))
            val encryptionManager = EncryptionManager(keyProvider)
            val retentionPolicy = RetentionPolicy(
                perSessionCapBytes = SESSION_CAP_BYTES,
                perDeviceCapBytes = SESSION_CAP_BYTES,
                globalCapBytes = SESSION_CAP_BYTES * 5
            )
            val retentionManager = DataRetentionManager(retentionPolicy)
            val sessionRepository = SessionRepository(
                baseDir.resolve("sessions"),
                encryptionManager,
                retentionManager
            )
            val deviceRepository = DeviceRepository()
            val previewRepository = PreviewRepository()
            val commandRepository = CommandRepository()
            val appScope = CoroutineScope(SupervisorJob() + Dispatchers.Default)
            val recordingManager = SensorRecordingManager(sessionRepository)
            val aggregationService = SessionAggregationService(sessionRepository)
            val grpcServer = GrpcServer(
                port = DEFAULT_PORT,
                sessionRepository = sessionRepository,
                deviceRepository = deviceRepository,
                previewRepository = previewRepository,
                sensorRecordingManager = recordingManager,
                commandRepository = commandRepository,
                aggregationService = aggregationService
            )
            val erasureManager = SubjectErasureManager(sessionRepository)
            val viewModel = AppViewModel(
                sessionRepository = sessionRepository,
                deviceRepository = deviceRepository,
                retentionManager = retentionManager,
                previewRepository = previewRepository,
                subjectErasureManager = erasureManager,
                commandRepository = commandRepository
            )
            val connectionMonitor = DeviceConnectionMonitor(
                deviceRepository = deviceRepository,
                sessionRepository = sessionRepository,
                scope = appScope
            )
            connectionMonitor.start()
            val mdnsServiceBrowser = MdnsServiceBrowser(appScope)
            mdnsServiceBrowser.start()
            return AppGraph(
                sessionRepository = sessionRepository,
                deviceRepository = deviceRepository,
                previewRepository = previewRepository,
                commandRepository = commandRepository,
                retentionManager = retentionManager,
                grpcServer = grpcServer,
                appViewModel = viewModel,
                subjectErasureManager = erasureManager,
                mdnsServiceBrowser = mdnsServiceBrowser,
                appScope = appScope,
                connectionMonitor = connectionMonitor
            )
        }

        private fun resolveBaseDirectory(): Path {
            val home = Paths.get(System.getProperty("user.home"), ".buccancs")
            Files.createDirectories(home)
            return home
        }
    }
}
