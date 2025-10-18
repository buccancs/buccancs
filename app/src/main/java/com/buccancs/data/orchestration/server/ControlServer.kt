package com.buccancs.data.orchestration.server

import com.buccancs.control.ProtocolVersion
import com.buccancs.control.sync.CommandAck
import com.buccancs.control.sync.CommandEnvelope
import com.buccancs.control.sync.ControlEvent
import com.buccancs.control.sync.EventSubscription
import com.buccancs.control.sync.LocalControlGrpcKt
import com.buccancs.data.orchestration.security.TokenIssuer
import com.buccancs.data.orchestration.server.EventPublisher.ControlServerEvent
import com.buccancs.di.ApplicationScope
import com.buccancs.di.IoDispatcher
import com.buccancs.util.nowInstant
import io.grpc.BindableService
import io.grpc.Server
import io.grpc.ServerBuilder
import io.grpc.Status
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ControlServer @Inject constructor(
    @param:ApplicationScope private val scope: CoroutineScope,
    private val authInterceptor: AuthInterceptor,
    private val eventPublisher: EventPublisher,
    private val tokenIssuer: TokenIssuer,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) {
    private val mutex =
        Mutex()
    private var server: Server? =
        null
    private var activeConfig: Config? =
        null

    suspend fun start(
        config: Config,
        additionalServices: List<BindableService> = emptyList()
    ) =
        withContext(
            ioDispatcher
        ) {
            mutex.withLock {
                if (server != null) return@withLock
                val builder =
                    ServerBuilder.forPort(
                        config.port
                    )
                        .intercept(
                            authInterceptor
                        )
                        .addService(
                            ControlServiceImpl(
                                config.sessionId
                            )
                        )
                additionalServices.forEach {
                    builder.addService(
                        it
                    )
                }
                val built =
                    builder.build()
                built.start()
                server =
                    built
                activeConfig =
                    config
                scope.launch(
                    ioDispatcher
                ) {
                    built.awaitTermination()
                    mutex.withLock {
                        server =
                            null
                        activeConfig =
                            null
                    }
                }
            }
        }

    suspend fun stop() =
        withContext(
            ioDispatcher
        ) {
            mutex.withLock {
                server?.shutdownNow()
                server =
                    null
                activeConfig =
                    null
            }
        }

    fun isRunning(): Boolean =
        server != null

    fun issueToken(
        sessionId: String,
        ttlMillis: Long = DEFAULT_TOKEN_TTL_MS
    ): TokenIssuer.IssuedToken {
        return tokenIssuer.issue(
            sessionId,
            ttlMillis
        )
    }

    fun state(): ControlServerState =
        ControlServerState(
            running = server != null,
            config = activeConfig
        )

    val events: SharedFlow<ControlServerEvent>
        get() = eventPublisher.events

    data class Config(
        val port: Int,
        val sessionId: String
    )

    data class ControlServerState(
        val running: Boolean,
        val config: Config?
    )

    private inner class ControlServiceImpl(
        private val sessionId: String
    ) : LocalControlGrpcKt.LocalControlCoroutineImplBase() {
        override suspend fun pushCommand(
            request: CommandEnvelope
        ): CommandAck {
            val clientVersion =
                request.protocolVersion.takeIf { it > 0 }
                    ?: ProtocolVersion.CURRENT
            if (!ProtocolVersion.isCompatible(
                    clientVersion
                )
            ) {
                throw Status.FAILED_PRECONDITION
                    .withDescription(
                        "Incompatible protocol version: ${
                            ProtocolVersion.versionString(
                                clientVersion
                            )
                        } " +
                                "(device: ${
                                    ProtocolVersion.versionString(
                                        ProtocolVersion.CURRENT
                                    )
                                })"
                    )
                    .asRuntimeException()
            }
            val verification =
                tokenIssuer.verify(
                    request.token,
                    request.sessionId
                )
            if (!verification.valid) {
                throw Status.UNAUTHENTICATED
                    .withDescription(
                        verification.message
                    )
                    .asRuntimeException()
            }
            val event =
                ControlServerEvent(
                    eventId = request.commandId.ifBlank { generateEventId() },
                    sessionId = request.sessionId,
                    type = "command",
                    detailJson = request.payloadJson,
                    timestamp = nowInstant()
                )
            eventPublisher.publish(
                event
            )
            return CommandAck.newBuilder()
                .setCommandId(
                    request.commandId
                )
                .setAccepted(
                    true
                )
                .build()
        }

        override fun streamEvents(
            request: EventSubscription
        ): kotlinx.coroutines.flow.Flow<ControlEvent> {
            val verification =
                tokenIssuer.verify(
                    request.token,
                    request.sessionId
                )
            if (!verification.valid) {
                throw Status.UNAUTHENTICATED
                    .withDescription(
                        verification.message
                    )
                    .asRuntimeException()
            }
            val source: SharedFlow<ControlServerEvent> =
                eventPublisher.events
            val sessionFilter =
                request.sessionId.takeIf { it.isNotBlank() }
                    ?: sessionId
            return source
                .filter { it.sessionId == sessionFilter }
                .map { it.toProto() }
        }

        private fun ControlServerEvent.toProto(): ControlEvent =
            ControlEvent.newBuilder()
                .setEventId(
                    eventId
                )
                .setSessionId(
                    sessionId
                )
                .setType(
                    type
                )
                .setDetailJson(
                    detailJson
                )
                .setTimestampEpochMs(
                    timestamp.toEpochMilliseconds()
                )
                .build()
    }

    private fun generateEventId(): String {
        return "evt-${nowInstant().toEpochMilliseconds()}"
    }

    companion object {
        private const val DEFAULT_TOKEN_TTL_MS =
            60_000L
        const val EVENT_TYPE_COMMAND =
            "command"
    }
}


