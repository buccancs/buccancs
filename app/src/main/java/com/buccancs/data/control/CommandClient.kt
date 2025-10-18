package com.buccancs.data.control

import android.util.Log
import com.buccancs.control.CommandServiceGrpcKt
import com.buccancs.control.commandReceipt
import com.buccancs.control.commandSubscribeRequest
import com.buccancs.control.commands.CommandSerialization
import com.buccancs.control.commands.DeviceCommandPayload
import com.buccancs.data.orchestration.DeviceIdentityProvider
import com.buccancs.data.orchestration.GrpcChannelFactory
import com.buccancs.di.ApplicationScope
import com.buccancs.domain.repository.OrchestratorConfigRepository
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlinx.coroutines.withTimeout
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CommandClient @Inject constructor(
    @ApplicationScope private val scope: CoroutineScope,
    private val configRepository: OrchestratorConfigRepository,
    private val channelFactory: GrpcChannelFactory,
    private val identityProvider: DeviceIdentityProvider
) {
    private val _commands = MutableSharedFlow<DeviceCommandPayload>(extraBufferCapacity = 64)
    val commands: SharedFlow<DeviceCommandPayload> = _commands.asSharedFlow()
    private val ackMutex = Mutex()

    @Volatile
    private var latestStub: CommandServiceGrpcKt.CommandServiceCoroutineStub? = null

    init {
        scope.launch {
            val deviceId = identityProvider.deviceId()
            while (isActive) {
                try {
                    val config = configRepository.config.first()
                    val channel = channelFactory.channel(config)
                    val stub = CommandServiceGrpcKt.CommandServiceCoroutineStub(channel)
                    latestStub = stub
                    stub.subscribeCommands(
                        commandSubscribeRequest {
                            this.deviceId = deviceId
                            sessionId = ""
                            includeBroadcast = true
                        }
                    ).collect { envelope ->
                        val payload = CommandSerialization.json.decodeFromString(
                            DeviceCommandPayload.serializer(),
                            envelope.commandJson
                        )
                        _commands.emit(payload)
                    }
                    latestStub = null
                } catch (cancellation: CancellationException) {
                    throw cancellation
                } catch (ex: Exception) {
                    latestStub = null
                    Log.w(TAG, "Command stream interrupted, retrying", ex)
                    delay(RETRY_DELAY_MS)
                }
            }
        }
    }

    suspend fun reportReceipt(commandId: String, success: Boolean, message: String?) {
        val stub = latestStub ?: return
        val receipt = commandReceipt {
            this.commandId = commandId
            deviceId = identityProvider.deviceId()
            this.success = success
            if (!message.isNullOrBlank()) {
                this.message = message
            }
        }
        runCatching {
            withTimeout(10_000) {
                ackMutex.withLock {
                    stub.reportCommandReceipt(receipt)
                }
            }
        }.onFailure { ex ->
            Log.w(TAG, "Unable to report command receipt for $commandId: ${ex.message}", ex)
        }
    }

    private companion object {
        private const val RETRY_DELAY_MS = 3_000L
        private const val TAG = "CommandClient"
    }
}
