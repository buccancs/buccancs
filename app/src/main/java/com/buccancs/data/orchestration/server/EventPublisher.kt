package com.buccancs.data.orchestration.server

import com.buccancs.di.ApplicationScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.time.Instant

@Singleton
class EventPublisher @Inject constructor(
    @ApplicationScope private val scope: CoroutineScope
) {
    private val eventsFlow = MutableSharedFlow<ControlServerEvent>(
        replay = 0,
        extraBufferCapacity = BUFFER_CAPACITY
    )

    val events: SharedFlow<ControlServerEvent> = eventsFlow.asSharedFlow()

    fun publish(event: ControlServerEvent) {
        if (!eventsFlow.tryEmit(event)) {
            scope.launch {
                eventsFlow.emit(event)
            }
        }
    }

    data class ControlServerEvent(
        val eventId: String,
        val sessionId: String,
        val type: String,
        val detailJson: String,
        val timestamp: Instant
    )

    private companion object {
        private const val BUFFER_CAPACITY = 64
    }
}

