package com.buccancs.data.orchestration
import com.buccancs.domain.model.OrchestratorConfig
import io.grpc.ManagedChannel
import io.grpc.ManagedChannelBuilder
import io.grpc.okhttp.OkHttpChannelBuilder
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import javax.inject.Inject
import javax.inject.Singleton
@Singleton
class GrpcChannelFactory @Inject constructor() {
    private val mutex = Mutex()
    private var cachedChannel: ManagedChannel? = null
    private var cachedConfig: OrchestratorConfig? = null
    suspend fun channel(config: OrchestratorConfig): ManagedChannel = mutex.withLock {
        val existing = cachedChannel
        if (
            existing != null &&
            cachedConfig == config &&
            !existing.isShutdown &&
            !existing.isTerminated
        ) {
            return@withLock existing
        }
        existing?.shutdownNow()
        val builder: ManagedChannelBuilder<*> = OkHttpChannelBuilder.forAddress(config.host, config.port)
        if (!config.useTls) {
            builder.usePlaintext()
        }
        val channel = builder.build()
        cachedChannel = channel
        cachedConfig = config
        channel
    }
}
