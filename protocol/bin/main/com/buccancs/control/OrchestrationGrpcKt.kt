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
import kotlin.String
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext
import kotlin.jvm.JvmOverloads
import kotlin.jvm.JvmStatic
import kotlinx.coroutines.flow.Flow
import com.buccancs.control.CommandServiceGrpc.getServiceDescriptor as commandServiceGrpcGetServiceDescriptor
import com.buccancs.control.DataTransferServiceGrpc.getServiceDescriptor as dataTransferServiceGrpcGetServiceDescriptor
import com.buccancs.control.OrchestrationServiceGrpc.getServiceDescriptor as orchestrationServiceGrpcGetServiceDescriptor
import com.buccancs.control.PreviewServiceGrpc.getServiceDescriptor as previewServiceGrpcGetServiceDescriptor
import com.buccancs.control.SensorStreamServiceGrpc.getServiceDescriptor as sensorStreamServiceGrpcGetServiceDescriptor
import com.buccancs.control.TimeSyncServiceGrpc.getServiceDescriptor as timeSyncServiceGrpcGetServiceDescriptor

public object OrchestrationServiceGrpcKt {
  public const val SERVICE_NAME: String = OrchestrationServiceGrpc.SERVICE_NAME

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

    @StubFor(OrchestrationServiceGrpc::class)
  public class OrchestrationServiceCoroutineStub @JvmOverloads constructor(
    channel: Channel,
    callOptions: CallOptions = DEFAULT,
  ) : AbstractCoroutineStub<OrchestrationServiceCoroutineStub>(channel, callOptions) {
    override fun build(channel: Channel, callOptions: CallOptions):
        OrchestrationServiceCoroutineStub = OrchestrationServiceCoroutineStub(channel, callOptions)

        public suspend fun registerDevice(request: DeviceRegistration, headers: Metadata = Metadata()):
        RegistrationAck = unaryRpc(
      channel,
      OrchestrationServiceGrpc.getRegisterDeviceMethod(),
      request,
      callOptions,
      headers
    )

        public suspend fun startSession(request: StartSessionRequest, headers: Metadata = Metadata()):
        CommandAck = unaryRpc(
      channel,
      OrchestrationServiceGrpc.getStartSessionMethod(),
      request,
      callOptions,
      headers
    )

        public suspend fun stopSession(request: StopSessionRequest, headers: Metadata = Metadata()):
        CommandAck = unaryRpc(
      channel,
      OrchestrationServiceGrpc.getStopSessionMethod(),
      request,
      callOptions,
      headers
    )

        public suspend fun sendSyncSignal(request: SyncSignalRequest, headers: Metadata = Metadata()):
        CommandAck = unaryRpc(
      channel,
      OrchestrationServiceGrpc.getSendSyncSignalMethod(),
      request,
      callOptions,
      headers
    )

        public suspend fun sendEventMarker(request: EventMarkerRequest, headers: Metadata = Metadata()):
        CommandAck = unaryRpc(
      channel,
      OrchestrationServiceGrpc.getSendEventMarkerMethod(),
      request,
      callOptions,
      headers
    )

        public fun subscribeStatus(request: StatusSubscribeRequest, headers: Metadata = Metadata()):
        Flow<DeviceStatus> = serverStreamingRpc(
      channel,
      OrchestrationServiceGrpc.getSubscribeStatusMethod(),
      request,
      callOptions,
      headers
    )

        public suspend fun reportStatus(request: DeviceStatus, headers: Metadata = Metadata()):
        CommandAck = unaryRpc(
      channel,
      OrchestrationServiceGrpc.getReportStatusMethod(),
      request,
      callOptions,
      headers
    )
  }

    public abstract class OrchestrationServiceCoroutineImplBase(
    coroutineContext: CoroutineContext = EmptyCoroutineContext,
  ) : AbstractCoroutineServerImpl(coroutineContext) {
        public open suspend fun registerDevice(request: DeviceRegistration): RegistrationAck = throw
        StatusException(UNIMPLEMENTED.withDescription("Method com.buccancs.control.OrchestrationService.RegisterDevice is unimplemented"))

        public open suspend fun startSession(request: StartSessionRequest): CommandAck = throw
        StatusException(UNIMPLEMENTED.withDescription("Method com.buccancs.control.OrchestrationService.StartSession is unimplemented"))

        public open suspend fun stopSession(request: StopSessionRequest): CommandAck = throw
        StatusException(UNIMPLEMENTED.withDescription("Method com.buccancs.control.OrchestrationService.StopSession is unimplemented"))

        public open suspend fun sendSyncSignal(request: SyncSignalRequest): CommandAck = throw
        StatusException(UNIMPLEMENTED.withDescription("Method com.buccancs.control.OrchestrationService.SendSyncSignal is unimplemented"))

        public open suspend fun sendEventMarker(request: EventMarkerRequest): CommandAck = throw
        StatusException(UNIMPLEMENTED.withDescription("Method com.buccancs.control.OrchestrationService.SendEventMarker is unimplemented"))

        public open fun subscribeStatus(request: StatusSubscribeRequest): Flow<DeviceStatus> = throw
        StatusException(UNIMPLEMENTED.withDescription("Method com.buccancs.control.OrchestrationService.SubscribeStatus is unimplemented"))

        public open suspend fun reportStatus(request: DeviceStatus): CommandAck = throw
        StatusException(UNIMPLEMENTED.withDescription("Method com.buccancs.control.OrchestrationService.ReportStatus is unimplemented"))

    final override fun bindService(): ServerServiceDefinition =
        builder(orchestrationServiceGrpcGetServiceDescriptor())
      .addMethod(unaryServerMethodDefinition(
      context = this.context,
      descriptor = OrchestrationServiceGrpc.getRegisterDeviceMethod(),
      implementation = ::registerDevice
    ))
      .addMethod(unaryServerMethodDefinition(
      context = this.context,
      descriptor = OrchestrationServiceGrpc.getStartSessionMethod(),
      implementation = ::startSession
    ))
      .addMethod(unaryServerMethodDefinition(
      context = this.context,
      descriptor = OrchestrationServiceGrpc.getStopSessionMethod(),
      implementation = ::stopSession
    ))
      .addMethod(unaryServerMethodDefinition(
      context = this.context,
      descriptor = OrchestrationServiceGrpc.getSendSyncSignalMethod(),
      implementation = ::sendSyncSignal
    ))
      .addMethod(unaryServerMethodDefinition(
      context = this.context,
      descriptor = OrchestrationServiceGrpc.getSendEventMarkerMethod(),
      implementation = ::sendEventMarker
    ))
      .addMethod(serverStreamingServerMethodDefinition(
      context = this.context,
      descriptor = OrchestrationServiceGrpc.getSubscribeStatusMethod(),
      implementation = ::subscribeStatus
    ))
      .addMethod(unaryServerMethodDefinition(
      context = this.context,
      descriptor = OrchestrationServiceGrpc.getReportStatusMethod(),
      implementation = ::reportStatus
    )).build()
  }
}

public object TimeSyncServiceGrpcKt {
  public const val SERVICE_NAME: String = TimeSyncServiceGrpc.SERVICE_NAME

  @JvmStatic
  public val serviceDescriptor: ServiceDescriptor
    get() = timeSyncServiceGrpcGetServiceDescriptor()

  public val pingMethod: MethodDescriptor<TimeSyncPing, TimeSyncPong>
    @JvmStatic
    get() = TimeSyncServiceGrpc.getPingMethod()

  public val reportMethod: MethodDescriptor<TimeSyncReport, CommandAck>
    @JvmStatic
    get() = TimeSyncServiceGrpc.getReportMethod()

    @StubFor(TimeSyncServiceGrpc::class)
  public class TimeSyncServiceCoroutineStub @JvmOverloads constructor(
    channel: Channel,
    callOptions: CallOptions = DEFAULT,
  ) : AbstractCoroutineStub<TimeSyncServiceCoroutineStub>(channel, callOptions) {
    override fun build(channel: Channel, callOptions: CallOptions): TimeSyncServiceCoroutineStub =
        TimeSyncServiceCoroutineStub(channel, callOptions)

        public suspend fun ping(request: TimeSyncPing, headers: Metadata = Metadata()): TimeSyncPong =
        unaryRpc(
      channel,
      TimeSyncServiceGrpc.getPingMethod(),
      request,
      callOptions,
      headers
    )

        public suspend fun report(request: TimeSyncReport, headers: Metadata = Metadata()): CommandAck =
        unaryRpc(
      channel,
      TimeSyncServiceGrpc.getReportMethod(),
      request,
      callOptions,
      headers
    )
  }

    public abstract class TimeSyncServiceCoroutineImplBase(
    coroutineContext: CoroutineContext = EmptyCoroutineContext,
  ) : AbstractCoroutineServerImpl(coroutineContext) {
        public open suspend fun ping(request: TimeSyncPing): TimeSyncPong = throw
        StatusException(UNIMPLEMENTED.withDescription("Method com.buccancs.control.TimeSyncService.Ping is unimplemented"))

        public open suspend fun report(request: TimeSyncReport): CommandAck = throw
        StatusException(UNIMPLEMENTED.withDescription("Method com.buccancs.control.TimeSyncService.Report is unimplemented"))

    final override fun bindService(): ServerServiceDefinition =
        builder(timeSyncServiceGrpcGetServiceDescriptor())
      .addMethod(unaryServerMethodDefinition(
      context = this.context,
      descriptor = TimeSyncServiceGrpc.getPingMethod(),
      implementation = ::ping
    ))
      .addMethod(unaryServerMethodDefinition(
      context = this.context,
      descriptor = TimeSyncServiceGrpc.getReportMethod(),
      implementation = ::report
    )).build()
  }
}

public object PreviewServiceGrpcKt {
  public const val SERVICE_NAME: String = PreviewServiceGrpc.SERVICE_NAME

  @JvmStatic
  public val serviceDescriptor: ServiceDescriptor
    get() = previewServiceGrpcGetServiceDescriptor()

  public val streamPreviewMethod: MethodDescriptor<PreviewFrame, PreviewAck>
    @JvmStatic
    get() = PreviewServiceGrpc.getStreamPreviewMethod()

    @StubFor(PreviewServiceGrpc::class)
  public class PreviewServiceCoroutineStub @JvmOverloads constructor(
    channel: Channel,
    callOptions: CallOptions = DEFAULT,
  ) : AbstractCoroutineStub<PreviewServiceCoroutineStub>(channel, callOptions) {
    override fun build(channel: Channel, callOptions: CallOptions): PreviewServiceCoroutineStub =
        PreviewServiceCoroutineStub(channel, callOptions)

        public suspend fun streamPreview(requests: Flow<PreviewFrame>, headers: Metadata = Metadata()):
        PreviewAck = clientStreamingRpc(
      channel,
      PreviewServiceGrpc.getStreamPreviewMethod(),
      requests,
      callOptions,
      headers
    )
  }

    public abstract class PreviewServiceCoroutineImplBase(
    coroutineContext: CoroutineContext = EmptyCoroutineContext,
  ) : AbstractCoroutineServerImpl(coroutineContext) {
        public open suspend fun streamPreview(requests: Flow<PreviewFrame>): PreviewAck = throw
        StatusException(UNIMPLEMENTED.withDescription("Method com.buccancs.control.PreviewService.StreamPreview is unimplemented"))

    final override fun bindService(): ServerServiceDefinition =
        builder(previewServiceGrpcGetServiceDescriptor())
      .addMethod(clientStreamingServerMethodDefinition(
      context = this.context,
      descriptor = PreviewServiceGrpc.getStreamPreviewMethod(),
      implementation = ::streamPreview
    )).build()
  }
}

public object DataTransferServiceGrpcKt {
  public const val SERVICE_NAME: String = DataTransferServiceGrpc.SERVICE_NAME

  @JvmStatic
  public val serviceDescriptor: ServiceDescriptor
    get() = dataTransferServiceGrpcGetServiceDescriptor()

  public val uploadMethod: MethodDescriptor<DataTransferRequest, DataTransferStatus>
    @JvmStatic
    get() = DataTransferServiceGrpc.getUploadMethod()

    @StubFor(DataTransferServiceGrpc::class)
  public class DataTransferServiceCoroutineStub @JvmOverloads constructor(
    channel: Channel,
    callOptions: CallOptions = DEFAULT,
  ) : AbstractCoroutineStub<DataTransferServiceCoroutineStub>(channel, callOptions) {
    override fun build(channel: Channel, callOptions: CallOptions): DataTransferServiceCoroutineStub
        = DataTransferServiceCoroutineStub(channel, callOptions)

        public fun upload(requests: Flow<DataTransferRequest>, headers: Metadata = Metadata()):
        Flow<DataTransferStatus> = bidiStreamingRpc(
      channel,
      DataTransferServiceGrpc.getUploadMethod(),
      requests,
      callOptions,
      headers
    )
  }

    public abstract class DataTransferServiceCoroutineImplBase(
    coroutineContext: CoroutineContext = EmptyCoroutineContext,
  ) : AbstractCoroutineServerImpl(coroutineContext) {
        public open fun upload(requests: Flow<DataTransferRequest>): Flow<DataTransferStatus> = throw
        StatusException(UNIMPLEMENTED.withDescription("Method com.buccancs.control.DataTransferService.Upload is unimplemented"))

    final override fun bindService(): ServerServiceDefinition =
        builder(dataTransferServiceGrpcGetServiceDescriptor())
      .addMethod(bidiStreamingServerMethodDefinition(
      context = this.context,
      descriptor = DataTransferServiceGrpc.getUploadMethod(),
      implementation = ::upload
    )).build()
  }
}

public object SensorStreamServiceGrpcKt {
  public const val SERVICE_NAME: String = SensorStreamServiceGrpc.SERVICE_NAME

  @JvmStatic
  public val serviceDescriptor: ServiceDescriptor
    get() = sensorStreamServiceGrpcGetServiceDescriptor()

  public val streamMethod: MethodDescriptor<SensorSampleBatch, SensorStreamAck>
    @JvmStatic
    get() = SensorStreamServiceGrpc.getStreamMethod()

    @StubFor(SensorStreamServiceGrpc::class)
  public class SensorStreamServiceCoroutineStub @JvmOverloads constructor(
    channel: Channel,
    callOptions: CallOptions = DEFAULT,
  ) : AbstractCoroutineStub<SensorStreamServiceCoroutineStub>(channel, callOptions) {
    override fun build(channel: Channel, callOptions: CallOptions): SensorStreamServiceCoroutineStub
        = SensorStreamServiceCoroutineStub(channel, callOptions)

        public suspend fun stream(requests: Flow<SensorSampleBatch>, headers: Metadata = Metadata()):
        SensorStreamAck = clientStreamingRpc(
      channel,
      SensorStreamServiceGrpc.getStreamMethod(),
      requests,
      callOptions,
      headers
    )
  }

    public abstract class SensorStreamServiceCoroutineImplBase(
    coroutineContext: CoroutineContext = EmptyCoroutineContext,
  ) : AbstractCoroutineServerImpl(coroutineContext) {
        public open suspend fun stream(requests: Flow<SensorSampleBatch>): SensorStreamAck = throw
        StatusException(UNIMPLEMENTED.withDescription("Method com.buccancs.control.SensorStreamService.Stream is unimplemented"))

    final override fun bindService(): ServerServiceDefinition =
        builder(sensorStreamServiceGrpcGetServiceDescriptor())
      .addMethod(clientStreamingServerMethodDefinition(
      context = this.context,
      descriptor = SensorStreamServiceGrpc.getStreamMethod(),
      implementation = ::stream
    )).build()
  }
}

public object CommandServiceGrpcKt {
  public const val SERVICE_NAME: String = CommandServiceGrpc.SERVICE_NAME

  @JvmStatic
  public val serviceDescriptor: ServiceDescriptor
    get() = commandServiceGrpcGetServiceDescriptor()

  public val subscribeCommandsMethod: MethodDescriptor<CommandSubscribeRequest, CommandEnvelope>
    @JvmStatic
    get() = CommandServiceGrpc.getSubscribeCommandsMethod()

  public val reportCommandReceiptMethod: MethodDescriptor<CommandReceipt, CommandAck>
    @JvmStatic
    get() = CommandServiceGrpc.getReportCommandReceiptMethod()

    @StubFor(CommandServiceGrpc::class)
  public class CommandServiceCoroutineStub @JvmOverloads constructor(
    channel: Channel,
    callOptions: CallOptions = DEFAULT,
  ) : AbstractCoroutineStub<CommandServiceCoroutineStub>(channel, callOptions) {
    override fun build(channel: Channel, callOptions: CallOptions): CommandServiceCoroutineStub =
        CommandServiceCoroutineStub(channel, callOptions)

        public fun subscribeCommands(request: CommandSubscribeRequest, headers: Metadata = Metadata()):
        Flow<CommandEnvelope> = serverStreamingRpc(
      channel,
      CommandServiceGrpc.getSubscribeCommandsMethod(),
      request,
      callOptions,
      headers
    )

        public suspend fun reportCommandReceipt(request: CommandReceipt, headers: Metadata =
        Metadata()): CommandAck = unaryRpc(
      channel,
      CommandServiceGrpc.getReportCommandReceiptMethod(),
      request,
      callOptions,
      headers
    )
  }

    public abstract class CommandServiceCoroutineImplBase(
    coroutineContext: CoroutineContext = EmptyCoroutineContext,
  ) : AbstractCoroutineServerImpl(coroutineContext) {
        public open fun subscribeCommands(request: CommandSubscribeRequest): Flow<CommandEnvelope> =
        throw
        StatusException(UNIMPLEMENTED.withDescription("Method com.buccancs.control.CommandService.SubscribeCommands is unimplemented"))

        public open suspend fun reportCommandReceipt(request: CommandReceipt): CommandAck = throw
        StatusException(UNIMPLEMENTED.withDescription("Method com.buccancs.control.CommandService.ReportCommandReceipt is unimplemented"))

    final override fun bindService(): ServerServiceDefinition =
        builder(commandServiceGrpcGetServiceDescriptor())
      .addMethod(serverStreamingServerMethodDefinition(
      context = this.context,
      descriptor = CommandServiceGrpc.getSubscribeCommandsMethod(),
      implementation = ::subscribeCommands
    ))
      .addMethod(unaryServerMethodDefinition(
      context = this.context,
      descriptor = CommandServiceGrpc.getReportCommandReceiptMethod(),
      implementation = ::reportCommandReceipt
    )).build()
  }
}
