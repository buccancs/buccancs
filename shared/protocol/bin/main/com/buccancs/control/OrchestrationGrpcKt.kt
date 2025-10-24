package com.buccancs.control

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
import io.grpc.kotlin.ClientCalls.bidiStreamingRpc
import io.grpc.kotlin.ClientCalls.clientStreamingRpc
import io.grpc.kotlin.ClientCalls.serverStreamingRpc
import io.grpc.kotlin.ClientCalls.unaryRpc
import io.grpc.kotlin.ServerCalls.bidiStreamingServerMethodDefinition
import io.grpc.kotlin.ServerCalls.clientStreamingServerMethodDefinition
import io.grpc.kotlin.ServerCalls.serverStreamingServerMethodDefinition
import io.grpc.kotlin.ServerCalls.unaryServerMethodDefinition
import io.grpc.kotlin.StubFor
import kotlinx.coroutines.flow.Flow
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext
import com.buccancs.control.CommandServiceGrpc.getServiceDescriptor as commandServiceGrpcGetServiceDescriptor
import com.buccancs.control.DataTransferServiceGrpc.getServiceDescriptor as dataTransferServiceGrpcGetServiceDescriptor
import com.buccancs.control.OrchestrationServiceGrpc.getServiceDescriptor as orchestrationServiceGrpcGetServiceDescriptor
import com.buccancs.control.PreviewServiceGrpc.getServiceDescriptor as previewServiceGrpcGetServiceDescriptor
import com.buccancs.control.SensorStreamServiceGrpc.getServiceDescriptor as sensorStreamServiceGrpcGetServiceDescriptor
import com.buccancs.control.TimeSyncServiceGrpc.getServiceDescriptor as timeSyncServiceGrpcGetServiceDescriptor

/**
 * Holder for Kotlin coroutine-based client and server APIs for
 * com.buccancs.control.OrchestrationService.
 */
public object OrchestrationServiceGrpcKt {
    public const val SERVICE_NAME: String =
        OrchestrationServiceGrpc.SERVICE_NAME

    @JvmStatic
    public val serviceDescriptor: ServiceDescriptor
        get() = orchestrationServiceGrpcGetServiceDescriptor()

    public val registerDeviceMethod: MethodDescriptor<DeviceRegistration, RegistrationAck>
        @JvmStatic
        get() = OrchestrationServiceGrpc.getRegisterDeviceMethod()

    public val startSessionMethod: MethodDescriptor<StartSessionRequest, CommandAck>
        @JvmStatic
        get() = OrchestrationServiceGrpc.getStartSessionMethod()

    public val stopSessionMethod: MethodDescriptor<StopSessionRequest, CommandAck>
        @JvmStatic
        get() = OrchestrationServiceGrpc.getStopSessionMethod()

    public val sendSyncSignalMethod: MethodDescriptor<SyncSignalRequest, CommandAck>
        @JvmStatic
        get() = OrchestrationServiceGrpc.getSendSyncSignalMethod()

    public val sendEventMarkerMethod: MethodDescriptor<EventMarkerRequest, CommandAck>
        @JvmStatic
        get() = OrchestrationServiceGrpc.getSendEventMarkerMethod()

    public val subscribeStatusMethod: MethodDescriptor<StatusSubscribeRequest, DeviceStatus>
        @JvmStatic
        get() = OrchestrationServiceGrpc.getSubscribeStatusMethod()

    public val reportStatusMethod: MethodDescriptor<DeviceStatus, CommandAck>
        @JvmStatic
        get() = OrchestrationServiceGrpc.getReportStatusMethod()

    /**
     * A stub for issuing RPCs to a(n) com.buccancs.control.OrchestrationService service as suspending
     * coroutines.
     */
    @StubFor(
        OrchestrationServiceGrpc::class
    )
    public class OrchestrationServiceCoroutineStub @JvmOverloads constructor(
        channel: Channel,
        callOptions: CallOptions = DEFAULT,
    ) : AbstractCoroutineStub<OrchestrationServiceCoroutineStub>(
        channel,
        callOptions
    ) {
        override fun build(
            channel: Channel,
            callOptions: CallOptions
        ):
                OrchestrationServiceCoroutineStub =
            OrchestrationServiceCoroutineStub(
                channel,
                callOptions
            )

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
        public suspend fun registerDevice(
            request: DeviceRegistration,
            headers: Metadata = Metadata()
        ):
                RegistrationAck =
            unaryRpc(
                channel,
                OrchestrationServiceGrpc.getRegisterDeviceMethod(),
                request,
                callOptions,
                headers
            )

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
        public suspend fun startSession(
            request: StartSessionRequest,
            headers: Metadata = Metadata()
        ):
                CommandAck =
            unaryRpc(
                channel,
                OrchestrationServiceGrpc.getStartSessionMethod(),
                request,
                callOptions,
                headers
            )

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
        public suspend fun stopSession(
            request: StopSessionRequest,
            headers: Metadata = Metadata()
        ):
                CommandAck =
            unaryRpc(
                channel,
                OrchestrationServiceGrpc.getStopSessionMethod(),
                request,
                callOptions,
                headers
            )

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
        public suspend fun sendSyncSignal(
            request: SyncSignalRequest,
            headers: Metadata = Metadata()
        ):
                CommandAck =
            unaryRpc(
                channel,
                OrchestrationServiceGrpc.getSendSyncSignalMethod(),
                request,
                callOptions,
                headers
            )

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
        public suspend fun sendEventMarker(
            request: EventMarkerRequest,
            headers: Metadata = Metadata()
        ):
                CommandAck =
            unaryRpc(
                channel,
                OrchestrationServiceGrpc.getSendEventMarkerMethod(),
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
        public fun subscribeStatus(
            request: StatusSubscribeRequest,
            headers: Metadata = Metadata()
        ):
                Flow<DeviceStatus> =
            serverStreamingRpc(
                channel,
                OrchestrationServiceGrpc.getSubscribeStatusMethod(),
                request,
                callOptions,
                headers
            )

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
        public suspend fun reportStatus(
            request: DeviceStatus,
            headers: Metadata = Metadata()
        ):
                CommandAck =
            unaryRpc(
                channel,
                OrchestrationServiceGrpc.getReportStatusMethod(),
                request,
                callOptions,
                headers
            )
    }

    /**
     * Skeletal implementation of the com.buccancs.control.OrchestrationService service based on
     * Kotlin coroutines.
     */
    public abstract class OrchestrationServiceCoroutineImplBase(
        coroutineContext: CoroutineContext = EmptyCoroutineContext,
    ) : AbstractCoroutineServerImpl(
        coroutineContext
    ) {
        /**
         * Returns the response to an RPC for com.buccancs.control.OrchestrationService.RegisterDevice.
         *
         * If this method fails with a [StatusException], the RPC will fail with the corresponding
         * [io.grpc.Status].  If this method fails with a [java.util.concurrent.CancellationException],
         * the RPC will fail
         * with status `Status.CANCELLED`.  If this method fails for any other reason, the RPC will
         * fail with `Status.UNKNOWN` with the exception as a cause.
         *
         * @param request The request from the client.
         */
        public open suspend fun registerDevice(
            request: DeviceRegistration
        ): RegistrationAck =
            throw StatusException(
                UNIMPLEMENTED.withDescription(
                    "Method com.buccancs.control.OrchestrationService.RegisterDevice is unimplemented"
                )
            )

        /**
         * Returns the response to an RPC for com.buccancs.control.OrchestrationService.StartSession.
         *
         * If this method fails with a [StatusException], the RPC will fail with the corresponding
         * [io.grpc.Status].  If this method fails with a [java.util.concurrent.CancellationException],
         * the RPC will fail
         * with status `Status.CANCELLED`.  If this method fails for any other reason, the RPC will
         * fail with `Status.UNKNOWN` with the exception as a cause.
         *
         * @param request The request from the client.
         */
        public open suspend fun startSession(
            request: StartSessionRequest
        ): CommandAck =
            throw StatusException(
                UNIMPLEMENTED.withDescription(
                    "Method com.buccancs.control.OrchestrationService.StartSession is unimplemented"
                )
            )

        /**
         * Returns the response to an RPC for com.buccancs.control.OrchestrationService.StopSession.
         *
         * If this method fails with a [StatusException], the RPC will fail with the corresponding
         * [io.grpc.Status].  If this method fails with a [java.util.concurrent.CancellationException],
         * the RPC will fail
         * with status `Status.CANCELLED`.  If this method fails for any other reason, the RPC will
         * fail with `Status.UNKNOWN` with the exception as a cause.
         *
         * @param request The request from the client.
         */
        public open suspend fun stopSession(
            request: StopSessionRequest
        ): CommandAck =
            throw StatusException(
                UNIMPLEMENTED.withDescription(
                    "Method com.buccancs.control.OrchestrationService.StopSession is unimplemented"
                )
            )

        /**
         * Returns the response to an RPC for com.buccancs.control.OrchestrationService.SendSyncSignal.
         *
         * If this method fails with a [StatusException], the RPC will fail with the corresponding
         * [io.grpc.Status].  If this method fails with a [java.util.concurrent.CancellationException],
         * the RPC will fail
         * with status `Status.CANCELLED`.  If this method fails for any other reason, the RPC will
         * fail with `Status.UNKNOWN` with the exception as a cause.
         *
         * @param request The request from the client.
         */
        public open suspend fun sendSyncSignal(
            request: SyncSignalRequest
        ): CommandAck =
            throw StatusException(
                UNIMPLEMENTED.withDescription(
                    "Method com.buccancs.control.OrchestrationService.SendSyncSignal is unimplemented"
                )
            )

        /**
         * Returns the response to an RPC for com.buccancs.control.OrchestrationService.SendEventMarker.
         *
         * If this method fails with a [StatusException], the RPC will fail with the corresponding
         * [io.grpc.Status].  If this method fails with a [java.util.concurrent.CancellationException],
         * the RPC will fail
         * with status `Status.CANCELLED`.  If this method fails for any other reason, the RPC will
         * fail with `Status.UNKNOWN` with the exception as a cause.
         *
         * @param request The request from the client.
         */
        public open suspend fun sendEventMarker(
            request: EventMarkerRequest
        ): CommandAck =
            throw StatusException(
                UNIMPLEMENTED.withDescription(
                    "Method com.buccancs.control.OrchestrationService.SendEventMarker is unimplemented"
                )
            )

        /**
         * Returns a [Flow] of responses to an RPC for
         * com.buccancs.control.OrchestrationService.SubscribeStatus.
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
        public open fun subscribeStatus(
            request: StatusSubscribeRequest
        ): Flow<DeviceStatus> =
            throw StatusException(
                UNIMPLEMENTED.withDescription(
                    "Method com.buccancs.control.OrchestrationService.SubscribeStatus is unimplemented"
                )
            )

        /**
         * Returns the response to an RPC for com.buccancs.control.OrchestrationService.ReportStatus.
         *
         * If this method fails with a [StatusException], the RPC will fail with the corresponding
         * [io.grpc.Status].  If this method fails with a [java.util.concurrent.CancellationException],
         * the RPC will fail
         * with status `Status.CANCELLED`.  If this method fails for any other reason, the RPC will
         * fail with `Status.UNKNOWN` with the exception as a cause.
         *
         * @param request The request from the client.
         */
        public open suspend fun reportStatus(
            request: DeviceStatus
        ): CommandAck =
            throw StatusException(
                UNIMPLEMENTED.withDescription(
                    "Method com.buccancs.control.OrchestrationService.ReportStatus is unimplemented"
                )
            )

        final override fun bindService(): ServerServiceDefinition =
            builder(
                orchestrationServiceGrpcGetServiceDescriptor()
            )
                .addMethod(
                    unaryServerMethodDefinition(
                        context = this.context,
                        descriptor = OrchestrationServiceGrpc.getRegisterDeviceMethod(),
                        implementation = ::registerDevice
                    )
                )
                .addMethod(
                    unaryServerMethodDefinition(
                        context = this.context,
                        descriptor = OrchestrationServiceGrpc.getStartSessionMethod(),
                        implementation = ::startSession
                    )
                )
                .addMethod(
                    unaryServerMethodDefinition(
                        context = this.context,
                        descriptor = OrchestrationServiceGrpc.getStopSessionMethod(),
                        implementation = ::stopSession
                    )
                )
                .addMethod(
                    unaryServerMethodDefinition(
                        context = this.context,
                        descriptor = OrchestrationServiceGrpc.getSendSyncSignalMethod(),
                        implementation = ::sendSyncSignal
                    )
                )
                .addMethod(
                    unaryServerMethodDefinition(
                        context = this.context,
                        descriptor = OrchestrationServiceGrpc.getSendEventMarkerMethod(),
                        implementation = ::sendEventMarker
                    )
                )
                .addMethod(
                    serverStreamingServerMethodDefinition(
                        context = this.context,
                        descriptor = OrchestrationServiceGrpc.getSubscribeStatusMethod(),
                        implementation = ::subscribeStatus
                    )
                )
                .addMethod(
                    unaryServerMethodDefinition(
                        context = this.context,
                        descriptor = OrchestrationServiceGrpc.getReportStatusMethod(),
                        implementation = ::reportStatus
                    )
                )
                .build()
    }
}

/**
 * Holder for Kotlin coroutine-based client and server APIs for
 * com.buccancs.control.TimeSyncService.
 */
public object TimeSyncServiceGrpcKt {
    public const val SERVICE_NAME: String =
        TimeSyncServiceGrpc.SERVICE_NAME

    @JvmStatic
    public val serviceDescriptor: ServiceDescriptor
        get() = timeSyncServiceGrpcGetServiceDescriptor()

    public val pingMethod: MethodDescriptor<TimeSyncPing, TimeSyncPong>
        @JvmStatic
        get() = TimeSyncServiceGrpc.getPingMethod()

    public val reportMethod: MethodDescriptor<TimeSyncReport, CommandAck>
        @JvmStatic
        get() = TimeSyncServiceGrpc.getReportMethod()

    /**
     * A stub for issuing RPCs to a(n) com.buccancs.control.TimeSyncService service as suspending
     * coroutines.
     */
    @StubFor(
        TimeSyncServiceGrpc::class
    )
    public class TimeSyncServiceCoroutineStub @JvmOverloads constructor(
        channel: Channel,
        callOptions: CallOptions = DEFAULT,
    ) : AbstractCoroutineStub<TimeSyncServiceCoroutineStub>(
        channel,
        callOptions
    ) {
        override fun build(
            channel: Channel,
            callOptions: CallOptions
        ): TimeSyncServiceCoroutineStub =
            TimeSyncServiceCoroutineStub(
                channel,
                callOptions
            )

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
        public suspend fun ping(
            request: TimeSyncPing,
            headers: Metadata = Metadata()
        ): TimeSyncPong =
            unaryRpc(
                channel,
                TimeSyncServiceGrpc.getPingMethod(),
                request,
                callOptions,
                headers
            )

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
        public suspend fun report(
            request: TimeSyncReport,
            headers: Metadata = Metadata()
        ): CommandAck =
            unaryRpc(
                channel,
                TimeSyncServiceGrpc.getReportMethod(),
                request,
                callOptions,
                headers
            )
    }

    /**
     * Skeletal implementation of the com.buccancs.control.TimeSyncService service based on Kotlin
     * coroutines.
     */
    public abstract class TimeSyncServiceCoroutineImplBase(
        coroutineContext: CoroutineContext = EmptyCoroutineContext,
    ) : AbstractCoroutineServerImpl(
        coroutineContext
    ) {
        /**
         * Returns the response to an RPC for com.buccancs.control.TimeSyncService.Ping.
         *
         * If this method fails with a [StatusException], the RPC will fail with the corresponding
         * [io.grpc.Status].  If this method fails with a [java.util.concurrent.CancellationException],
         * the RPC will fail
         * with status `Status.CANCELLED`.  If this method fails for any other reason, the RPC will
         * fail with `Status.UNKNOWN` with the exception as a cause.
         *
         * @param request The request from the client.
         */
        public open suspend fun ping(
            request: TimeSyncPing
        ): TimeSyncPong =
            throw StatusException(
                UNIMPLEMENTED.withDescription(
                    "Method com.buccancs.control.TimeSyncService.Ping is unimplemented"
                )
            )

        /**
         * Returns the response to an RPC for com.buccancs.control.TimeSyncService.Report.
         *
         * If this method fails with a [StatusException], the RPC will fail with the corresponding
         * [io.grpc.Status].  If this method fails with a [java.util.concurrent.CancellationException],
         * the RPC will fail
         * with status `Status.CANCELLED`.  If this method fails for any other reason, the RPC will
         * fail with `Status.UNKNOWN` with the exception as a cause.
         *
         * @param request The request from the client.
         */
        public open suspend fun report(
            request: TimeSyncReport
        ): CommandAck =
            throw StatusException(
                UNIMPLEMENTED.withDescription(
                    "Method com.buccancs.control.TimeSyncService.Report is unimplemented"
                )
            )

        final override fun bindService(): ServerServiceDefinition =
            builder(
                timeSyncServiceGrpcGetServiceDescriptor()
            )
                .addMethod(
                    unaryServerMethodDefinition(
                        context = this.context,
                        descriptor = TimeSyncServiceGrpc.getPingMethod(),
                        implementation = ::ping
                    )
                )
                .addMethod(
                    unaryServerMethodDefinition(
                        context = this.context,
                        descriptor = TimeSyncServiceGrpc.getReportMethod(),
                        implementation = ::report
                    )
                )
                .build()
    }
}

/**
 * Holder for Kotlin coroutine-based client and server APIs for com.buccancs.control.PreviewService.
 */
public object PreviewServiceGrpcKt {
    public const val SERVICE_NAME: String =
        PreviewServiceGrpc.SERVICE_NAME

    @JvmStatic
    public val serviceDescriptor: ServiceDescriptor
        get() = previewServiceGrpcGetServiceDescriptor()

    public val streamPreviewMethod: MethodDescriptor<PreviewFrame, PreviewAck>
        @JvmStatic
        get() = PreviewServiceGrpc.getStreamPreviewMethod()

    /**
     * A stub for issuing RPCs to a(n) com.buccancs.control.PreviewService service as suspending
     * coroutines.
     */
    @StubFor(
        PreviewServiceGrpc::class
    )
    public class PreviewServiceCoroutineStub @JvmOverloads constructor(
        channel: Channel,
        callOptions: CallOptions = DEFAULT,
    ) : AbstractCoroutineStub<PreviewServiceCoroutineStub>(
        channel,
        callOptions
    ) {
        override fun build(
            channel: Channel,
            callOptions: CallOptions
        ): PreviewServiceCoroutineStub =
            PreviewServiceCoroutineStub(
                channel,
                callOptions
            )

        /**
         * Executes this RPC and returns the response message, suspending until the RPC completes
         * with [`Status.OK`][io.grpc.Status].  If the RPC completes with another status, a
         * corresponding
         * [StatusException] is thrown.  If this coroutine is cancelled, the RPC is also cancelled
         * with the corresponding exception as a cause.
         *
         * This function collects the [Flow] of requests.  If the server terminates the RPC
         * for any reason before collection of requests is complete, the collection of requests
         * will be cancelled.  If the collection of requests completes exceptionally for any other
         * reason, the RPC will be cancelled for that reason and this method will throw that
         * exception.
         *
         * @param requests A [Flow] of request messages.
         *
         * @param headers Metadata to attach to the request.  Most users will not need this.
         *
         * @return The single response from the server.
         */
        public suspend fun streamPreview(
            requests: Flow<PreviewFrame>,
            headers: Metadata = Metadata()
        ):
                PreviewAck =
            clientStreamingRpc(
                channel,
                PreviewServiceGrpc.getStreamPreviewMethod(),
                requests,
                callOptions,
                headers
            )
    }

    /**
     * Skeletal implementation of the com.buccancs.control.PreviewService service based on Kotlin
     * coroutines.
     */
    public abstract class PreviewServiceCoroutineImplBase(
        coroutineContext: CoroutineContext = EmptyCoroutineContext,
    ) : AbstractCoroutineServerImpl(
        coroutineContext
    ) {
        /**
         * Returns the response to an RPC for com.buccancs.control.PreviewService.StreamPreview.
         *
         * If this method fails with a [StatusException], the RPC will fail with the corresponding
         * [io.grpc.Status].  If this method fails with a [java.util.concurrent.CancellationException],
         * the RPC will fail
         * with status `Status.CANCELLED`.  If this method fails for any other reason, the RPC will
         * fail with `Status.UNKNOWN` with the exception as a cause.
         *
         * @param requests A [Flow] of requests from the client.  This flow can be
         *        collected only once and throws [java.lang.IllegalStateException] on attempts to
         * collect
         *        it more than once.
         */
        public open suspend fun streamPreview(
            requests: Flow<PreviewFrame>
        ): PreviewAck =
            throw StatusException(
                UNIMPLEMENTED.withDescription(
                    "Method com.buccancs.control.PreviewService.StreamPreview is unimplemented"
                )
            )

        final override fun bindService(): ServerServiceDefinition =
            builder(
                previewServiceGrpcGetServiceDescriptor()
            )
                .addMethod(
                    clientStreamingServerMethodDefinition(
                        context = this.context,
                        descriptor = PreviewServiceGrpc.getStreamPreviewMethod(),
                        implementation = ::streamPreview
                    )
                )
                .build()
    }
}

/**
 * Holder for Kotlin coroutine-based client and server APIs for
 * com.buccancs.control.DataTransferService.
 */
public object DataTransferServiceGrpcKt {
    public const val SERVICE_NAME: String =
        DataTransferServiceGrpc.SERVICE_NAME

    @JvmStatic
    public val serviceDescriptor: ServiceDescriptor
        get() = dataTransferServiceGrpcGetServiceDescriptor()

    public val uploadMethod: MethodDescriptor<DataTransferRequest, DataTransferStatus>
        @JvmStatic
        get() = DataTransferServiceGrpc.getUploadMethod()

    /**
     * A stub for issuing RPCs to a(n) com.buccancs.control.DataTransferService service as suspending
     * coroutines.
     */
    @StubFor(
        DataTransferServiceGrpc::class
    )
    public class DataTransferServiceCoroutineStub @JvmOverloads constructor(
        channel: Channel,
        callOptions: CallOptions = DEFAULT,
    ) : AbstractCoroutineStub<DataTransferServiceCoroutineStub>(
        channel,
        callOptions
    ) {
        override fun build(
            channel: Channel,
            callOptions: CallOptions
        ): DataTransferServiceCoroutineStub =
            DataTransferServiceCoroutineStub(
                channel,
                callOptions
            )

        /**
         * Returns a [Flow] that, when collected, executes this RPC and emits responses from the
         * server as they arrive.  That flow finishes normally if the server closes its response with
         * [`Status.OK`][io.grpc.Status], and fails by throwing a [StatusException] otherwise.  If
         * collecting the flow downstream fails exceptionally (including via cancellation), the RPC
         * is cancelled with that exception as a cause.
         *
         * The [Flow] of requests is collected once each time the [Flow] of responses is
         * collected. If collection of the [Flow] of responses completes normally or
         * exceptionally before collection of `requests` completes, the collection of
         * `requests` is cancelled.  If the collection of `requests` completes
         * exceptionally for any other reason, then the collection of the [Flow] of responses
         * completes exceptionally for the same reason and the RPC is cancelled with that reason.
         *
         * @param requests A [Flow] of request messages.
         *
         * @param headers Metadata to attach to the request.  Most users will not need this.
         *
         * @return A flow that, when collected, emits the responses from the server.
         */
        public fun upload(
            requests: Flow<DataTransferRequest>,
            headers: Metadata = Metadata()
        ):
                Flow<DataTransferStatus> =
            bidiStreamingRpc(
                channel,
                DataTransferServiceGrpc.getUploadMethod(),
                requests,
                callOptions,
                headers
            )
    }

    /**
     * Skeletal implementation of the com.buccancs.control.DataTransferService service based on Kotlin
     * coroutines.
     */
    public abstract class DataTransferServiceCoroutineImplBase(
        coroutineContext: CoroutineContext = EmptyCoroutineContext,
    ) : AbstractCoroutineServerImpl(
        coroutineContext
    ) {
        /**
         * Returns a [Flow] of responses to an RPC for com.buccancs.control.DataTransferService.Upload.
         *
         * If creating or collecting the returned flow fails with a [StatusException], the RPC
         * will fail with the corresponding [io.grpc.Status].  If it fails with a
         * [java.util.concurrent.CancellationException], the RPC will fail with status
         * `Status.CANCELLED`.  If creating
         * or collecting the returned flow fails for any other reason, the RPC will fail with
         * `Status.UNKNOWN` with the exception as a cause.
         *
         * @param requests A [Flow] of requests from the client.  This flow can be
         *        collected only once and throws [java.lang.IllegalStateException] on attempts to
         * collect
         *        it more than once.
         */
        public open fun upload(
            requests: Flow<DataTransferRequest>
        ): Flow<DataTransferStatus> =
            throw StatusException(
                UNIMPLEMENTED.withDescription(
                    "Method com.buccancs.control.DataTransferService.Upload is unimplemented"
                )
            )

        final override fun bindService(): ServerServiceDefinition =
            builder(
                dataTransferServiceGrpcGetServiceDescriptor()
            )
                .addMethod(
                    bidiStreamingServerMethodDefinition(
                        context = this.context,
                        descriptor = DataTransferServiceGrpc.getUploadMethod(),
                        implementation = ::upload
                    )
                )
                .build()
    }
}

/**
 * Holder for Kotlin coroutine-based client and server APIs for
 * com.buccancs.control.SensorStreamService.
 */
public object SensorStreamServiceGrpcKt {
    public const val SERVICE_NAME: String =
        SensorStreamServiceGrpc.SERVICE_NAME

    @JvmStatic
    public val serviceDescriptor: ServiceDescriptor
        get() = sensorStreamServiceGrpcGetServiceDescriptor()

    public val streamMethod: MethodDescriptor<SensorSampleBatch, SensorStreamAck>
        @JvmStatic
        get() = SensorStreamServiceGrpc.getStreamMethod()

    /**
     * A stub for issuing RPCs to a(n) com.buccancs.control.SensorStreamService service as suspending
     * coroutines.
     */
    @StubFor(
        SensorStreamServiceGrpc::class
    )
    public class SensorStreamServiceCoroutineStub @JvmOverloads constructor(
        channel: Channel,
        callOptions: CallOptions = DEFAULT,
    ) : AbstractCoroutineStub<SensorStreamServiceCoroutineStub>(
        channel,
        callOptions
    ) {
        override fun build(
            channel: Channel,
            callOptions: CallOptions
        ): SensorStreamServiceCoroutineStub =
            SensorStreamServiceCoroutineStub(
                channel,
                callOptions
            )

        /**
         * Executes this RPC and returns the response message, suspending until the RPC completes
         * with [`Status.OK`][io.grpc.Status].  If the RPC completes with another status, a
         * corresponding
         * [StatusException] is thrown.  If this coroutine is cancelled, the RPC is also cancelled
         * with the corresponding exception as a cause.
         *
         * This function collects the [Flow] of requests.  If the server terminates the RPC
         * for any reason before collection of requests is complete, the collection of requests
         * will be cancelled.  If the collection of requests completes exceptionally for any other
         * reason, the RPC will be cancelled for that reason and this method will throw that
         * exception.
         *
         * @param requests A [Flow] of request messages.
         *
         * @param headers Metadata to attach to the request.  Most users will not need this.
         *
         * @return The single response from the server.
         */
        public suspend fun stream(
            requests: Flow<SensorSampleBatch>,
            headers: Metadata = Metadata()
        ):
                SensorStreamAck =
            clientStreamingRpc(
                channel,
                SensorStreamServiceGrpc.getStreamMethod(),
                requests,
                callOptions,
                headers
            )
    }

    /**
     * Skeletal implementation of the com.buccancs.control.SensorStreamService service based on Kotlin
     * coroutines.
     */
    public abstract class SensorStreamServiceCoroutineImplBase(
        coroutineContext: CoroutineContext = EmptyCoroutineContext,
    ) : AbstractCoroutineServerImpl(
        coroutineContext
    ) {
        /**
         * Returns the response to an RPC for com.buccancs.control.SensorStreamService.Stream.
         *
         * If this method fails with a [StatusException], the RPC will fail with the corresponding
         * [io.grpc.Status].  If this method fails with a [java.util.concurrent.CancellationException],
         * the RPC will fail
         * with status `Status.CANCELLED`.  If this method fails for any other reason, the RPC will
         * fail with `Status.UNKNOWN` with the exception as a cause.
         *
         * @param requests A [Flow] of requests from the client.  This flow can be
         *        collected only once and throws [java.lang.IllegalStateException] on attempts to
         * collect
         *        it more than once.
         */
        public open suspend fun stream(
            requests: Flow<SensorSampleBatch>
        ): SensorStreamAck =
            throw StatusException(
                UNIMPLEMENTED.withDescription(
                    "Method com.buccancs.control.SensorStreamService.Stream is unimplemented"
                )
            )

        final override fun bindService(): ServerServiceDefinition =
            builder(
                sensorStreamServiceGrpcGetServiceDescriptor()
            )
                .addMethod(
                    clientStreamingServerMethodDefinition(
                        context = this.context,
                        descriptor = SensorStreamServiceGrpc.getStreamMethod(),
                        implementation = ::stream
                    )
                )
                .build()
    }
}

/**
 * Holder for Kotlin coroutine-based client and server APIs for com.buccancs.control.CommandService.
 */
public object CommandServiceGrpcKt {
    public const val SERVICE_NAME: String =
        CommandServiceGrpc.SERVICE_NAME

    @JvmStatic
    public val serviceDescriptor: ServiceDescriptor
        get() = commandServiceGrpcGetServiceDescriptor()

    public val subscribeCommandsMethod: MethodDescriptor<CommandSubscribeRequest, CommandEnvelope>
        @JvmStatic
        get() = CommandServiceGrpc.getSubscribeCommandsMethod()

    public val reportCommandReceiptMethod: MethodDescriptor<CommandReceipt, CommandAck>
        @JvmStatic
        get() = CommandServiceGrpc.getReportCommandReceiptMethod()

    /**
     * A stub for issuing RPCs to a(n) com.buccancs.control.CommandService service as suspending
     * coroutines.
     */
    @StubFor(
        CommandServiceGrpc::class
    )
    public class CommandServiceCoroutineStub @JvmOverloads constructor(
        channel: Channel,
        callOptions: CallOptions = DEFAULT,
    ) : AbstractCoroutineStub<CommandServiceCoroutineStub>(
        channel,
        callOptions
    ) {
        override fun build(
            channel: Channel,
            callOptions: CallOptions
        ): CommandServiceCoroutineStub =
            CommandServiceCoroutineStub(
                channel,
                callOptions
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
        public fun subscribeCommands(
            request: CommandSubscribeRequest,
            headers: Metadata = Metadata()
        ):
                Flow<CommandEnvelope> =
            serverStreamingRpc(
                channel,
                CommandServiceGrpc.getSubscribeCommandsMethod(),
                request,
                callOptions,
                headers
            )

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
        public suspend fun reportCommandReceipt(
            request: CommandReceipt,
            headers: Metadata =
                Metadata()
        ): CommandAck =
            unaryRpc(
                channel,
                CommandServiceGrpc.getReportCommandReceiptMethod(),
                request,
                callOptions,
                headers
            )
    }

    /**
     * Skeletal implementation of the com.buccancs.control.CommandService service based on Kotlin
     * coroutines.
     */
    public abstract class CommandServiceCoroutineImplBase(
        coroutineContext: CoroutineContext = EmptyCoroutineContext,
    ) : AbstractCoroutineServerImpl(
        coroutineContext
    ) {
        /**
         * Returns a [Flow] of responses to an RPC for
         * com.buccancs.control.CommandService.SubscribeCommands.
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
        public open fun subscribeCommands(
            request: CommandSubscribeRequest
        ): Flow<CommandEnvelope> =
            throw StatusException(
                UNIMPLEMENTED.withDescription(
                    "Method com.buccancs.control.CommandService.SubscribeCommands is unimplemented"
                )
            )

        /**
         * Returns the response to an RPC for com.buccancs.control.CommandService.ReportCommandReceipt.
         *
         * If this method fails with a [StatusException], the RPC will fail with the corresponding
         * [io.grpc.Status].  If this method fails with a [java.util.concurrent.CancellationException],
         * the RPC will fail
         * with status `Status.CANCELLED`.  If this method fails for any other reason, the RPC will
         * fail with `Status.UNKNOWN` with the exception as a cause.
         *
         * @param request The request from the client.
         */
        public open suspend fun reportCommandReceipt(
            request: CommandReceipt
        ): CommandAck =
            throw StatusException(
                UNIMPLEMENTED.withDescription(
                    "Method com.buccancs.control.CommandService.ReportCommandReceipt is unimplemented"
                )
            )

        final override fun bindService(): ServerServiceDefinition =
            builder(
                commandServiceGrpcGetServiceDescriptor()
            )
                .addMethod(
                    serverStreamingServerMethodDefinition(
                        context = this.context,
                        descriptor = CommandServiceGrpc.getSubscribeCommandsMethod(),
                        implementation = ::subscribeCommands
                    )
                )
                .addMethod(
                    unaryServerMethodDefinition(
                        context = this.context,
                        descriptor = CommandServiceGrpc.getReportCommandReceiptMethod(),
                        implementation = ::reportCommandReceipt
                    )
                )
                .build()
    }
}
