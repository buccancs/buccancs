package com.buccancs.control.sync

import com.buccancs.control.sync.LocalControlGrpc.getServiceDescriptor
import io.grpc.CallOptions
import io.grpc.CallOptions.DEFAULT
import io.grpc.Channel
import io.grpc.Metadata
import io.grpc.MethodDescriptor
import io.grpc.ServerServiceDefinition
import io.grpc.ServerServiceDefinition.builder
import io.grpc.ServiceDescriptor
import io.grpc.Status.UNIMPLEMENTED
import io.grpc.StatusException
import io.grpc.kotlin.AbstractCoroutineServerImpl
import io.grpc.kotlin.AbstractCoroutineStub
import io.grpc.kotlin.ClientCalls.serverStreamingRpc
import io.grpc.kotlin.ClientCalls.unaryRpc
import io.grpc.kotlin.ServerCalls.serverStreamingServerMethodDefinition
import io.grpc.kotlin.ServerCalls.unaryServerMethodDefinition
import io.grpc.kotlin.StubFor
import kotlinx.coroutines.flow.Flow
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

/**
 * Holder for Kotlin coroutine-based client and server APIs for
 * com.buccancs.control.sync.LocalControl.
 */
public object LocalControlGrpcKt {
    public const val SERVICE_NAME: String = LocalControlGrpc.SERVICE_NAME

    @JvmStatic
    public val serviceDescriptor: ServiceDescriptor
        get() = getServiceDescriptor()

    public val pushCommandMethod: MethodDescriptor<CommandEnvelope, CommandAck>
        @JvmStatic
        get() = LocalControlGrpc.getPushCommandMethod()

    public val streamEventsMethod: MethodDescriptor<EventSubscription, ControlEvent>
        @JvmStatic
        get() = LocalControlGrpc.getStreamEventsMethod()

    /**
     * A stub for issuing RPCs to a(n) com.buccancs.control.sync.LocalControl service as suspending
     * coroutines.
     */
    @StubFor(LocalControlGrpc::class)
    public class LocalControlCoroutineStub @JvmOverloads constructor(
        channel: Channel,
        callOptions: CallOptions = DEFAULT,
    ) : AbstractCoroutineStub<LocalControlCoroutineStub>(channel, callOptions) {
        override fun build(channel: Channel, callOptions: CallOptions): LocalControlCoroutineStub =
            LocalControlCoroutineStub(channel, callOptions)

        /**
         * Executes this RPC and returns the response message, suspending until the RPC completes
         * with [`Status.OK`][io.grpc.Status].  If the RPC completes with another status, a
         * corresponding
         * [StatusException] is thrown.  If this coroutine is cancelled, the RPC is also cancelled
         * with the corresponding exception as a cause.
         *
         * @param request The request message to send to the server.
         *
         * @param headers Metadata to attach to the request.  Most users will not need this.
         *
         * @return The single response from the server.
         */
        public suspend fun pushCommand(request: CommandEnvelope, headers: Metadata = Metadata()):
                CommandAck = unaryRpc(
            channel,
            LocalControlGrpc.getPushCommandMethod(),
            request,
            callOptions,
            headers
        )

        /**
         * Returns a [Flow] that, when collected, executes this RPC and emits responses from the
         * server as they arrive.  That flow finishes normally if the server closes its response with
         * [`Status.OK`][io.grpc.Status], and fails by throwing a [StatusException] otherwise.  If
         * collecting the flow downstream fails exceptionally (including via cancellation), the RPC
         * is cancelled with that exception as a cause.
         *
         * @param request The request message to send to the server.
         *
         * @param headers Metadata to attach to the request.  Most users will not need this.
         *
         * @return A flow that, when collected, emits the responses from the server.
         */
        public fun streamEvents(request: EventSubscription, headers: Metadata = Metadata()):
                Flow<ControlEvent> = serverStreamingRpc(
            channel,
            LocalControlGrpc.getStreamEventsMethod(),
            request,
            callOptions,
            headers
        )
    }

    /**
     * Skeletal implementation of the com.buccancs.control.sync.LocalControl service based on Kotlin
     * coroutines.
     */
    public abstract class LocalControlCoroutineImplBase(
        coroutineContext: CoroutineContext = EmptyCoroutineContext,
    ) : AbstractCoroutineServerImpl(coroutineContext) {
        /**
         * Returns the response to an RPC for com.buccancs.control.sync.LocalControl.PushCommand.
         *
         * If this method fails with a [StatusException], the RPC will fail with the corresponding
         * [io.grpc.Status].  If this method fails with a [java.util.concurrent.CancellationException],
         * the RPC will fail
         * with status `Status.CANCELLED`.  If this method fails for any other reason, the RPC will
         * fail with `Status.UNKNOWN` with the exception as a cause.
         *
         * @param request The request from the client.
         */
        public open suspend fun pushCommand(request: CommandEnvelope): CommandAck =
            throw StatusException(UNIMPLEMENTED.withDescription("Method com.buccancs.control.sync.LocalControl.PushCommand is unimplemented"))

        /**
         * Returns a [Flow] of responses to an RPC for
         * com.buccancs.control.sync.LocalControl.StreamEvents.
         *
         * If creating or collecting the returned flow fails with a [StatusException], the RPC
         * will fail with the corresponding [io.grpc.Status].  If it fails with a
         * [java.util.concurrent.CancellationException], the RPC will fail with status
         * `Status.CANCELLED`.  If creating
         * or collecting the returned flow fails for any other reason, the RPC will fail with
         * `Status.UNKNOWN` with the exception as a cause.
         *
         * @param request The request from the client.
         */
        public open fun streamEvents(request: EventSubscription): Flow<ControlEvent> =
            throw StatusException(UNIMPLEMENTED.withDescription("Method com.buccancs.control.sync.LocalControl.StreamEvents is unimplemented"))

        final override fun bindService(): ServerServiceDefinition = builder(getServiceDescriptor())
            .addMethod(
                unaryServerMethodDefinition(
                    context = this.context,
                    descriptor = LocalControlGrpc.getPushCommandMethod(),
                    implementation = ::pushCommand
                )
            )
            .addMethod(
                serverStreamingServerMethodDefinition(
                    context = this.context,
                    descriptor = LocalControlGrpc.getStreamEventsMethod(),
                    implementation = ::streamEvents
                )
            ).build()
    }
}
