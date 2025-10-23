package io.grpc.xds.shaded.com.github.udpa.udpa.service.orca.v1;

import com.google.protobuf.Descriptors;
import io.grpc.BindableService;
import io.grpc.CallOptions;
import io.grpc.Channel;
import io.grpc.MethodDescriptor;
import io.grpc.ServerServiceDefinition;
import io.grpc.ServiceDescriptor;
import io.grpc.protobuf.ProtoFileDescriptorSupplier;
import io.grpc.protobuf.ProtoMethodDescriptorSupplier;
import io.grpc.protobuf.ProtoServiceDescriptorSupplier;
import io.grpc.protobuf.ProtoUtils;
import io.grpc.stub.AbstractAsyncStub;
import io.grpc.stub.AbstractBlockingStub;
import io.grpc.stub.AbstractFutureStub;
import io.grpc.stub.AbstractStub;
import io.grpc.stub.ClientCalls;
import io.grpc.stub.ServerCalls;
import io.grpc.stub.StreamObserver;
import io.grpc.xds.shaded.com.github.udpa.udpa.data.orca.v1.OrcaLoadReport;

import java.util.Iterator;

/* loaded from: classes3.dex */
public final class OpenRcaServiceGrpc {
    public static final String SERVICE_NAME = "udpa.service.orca.v1.OpenRcaService";
    private static final int METHODID_STREAM_CORE_METRICS = 0;
    private static volatile MethodDescriptor<OrcaLoadReportRequest, OrcaLoadReport> getStreamCoreMetricsMethod;
    private static volatile ServiceDescriptor serviceDescriptor;

    private OpenRcaServiceGrpc() {
    }

    public static MethodDescriptor<OrcaLoadReportRequest, OrcaLoadReport> getStreamCoreMetricsMethod() {
        MethodDescriptor<OrcaLoadReportRequest, OrcaLoadReport> methodDescriptorBuild = getStreamCoreMetricsMethod;
        if (methodDescriptorBuild == null) {
            synchronized (OpenRcaServiceGrpc.class) {
                methodDescriptorBuild = getStreamCoreMetricsMethod;
                if (methodDescriptorBuild == null) {
                    methodDescriptorBuild = MethodDescriptor.newBuilder().setType(MethodDescriptor.MethodType.SERVER_STREAMING).setFullMethodName(MethodDescriptor.generateFullMethodName(SERVICE_NAME, "StreamCoreMetrics")).setSampledToLocalTracing(true).setRequestMarshaller(ProtoUtils.marshaller(OrcaLoadReportRequest.getDefaultInstance())).setResponseMarshaller(ProtoUtils.marshaller(OrcaLoadReport.getDefaultInstance())).setSchemaDescriptor(new OpenRcaServiceMethodDescriptorSupplier("StreamCoreMetrics")).build();
                    getStreamCoreMetricsMethod = methodDescriptorBuild;
                }
            }
        }
        return methodDescriptorBuild;
    }

    public static OpenRcaServiceStub newStub(Channel channel) {
        return (OpenRcaServiceStub) OpenRcaServiceStub.newStub(new AbstractStub.StubFactory<OpenRcaServiceStub>() { // from class: io.grpc.xds.shaded.com.github.udpa.udpa.service.orca.v1.OpenRcaServiceGrpc.1
            @Override // io.grpc.stub.AbstractStub.StubFactory
            public OpenRcaServiceStub newStub(Channel channel2, CallOptions callOptions) {
                return new OpenRcaServiceStub(channel2, callOptions);
            }
        }, channel);
    }

    public static OpenRcaServiceBlockingStub newBlockingStub(Channel channel) {
        return (OpenRcaServiceBlockingStub) OpenRcaServiceBlockingStub.newStub(new AbstractStub.StubFactory<OpenRcaServiceBlockingStub>() { // from class: io.grpc.xds.shaded.com.github.udpa.udpa.service.orca.v1.OpenRcaServiceGrpc.2
            @Override // io.grpc.stub.AbstractStub.StubFactory
            public OpenRcaServiceBlockingStub newStub(Channel channel2, CallOptions callOptions) {
                return new OpenRcaServiceBlockingStub(channel2, callOptions);
            }
        }, channel);
    }

    public static OpenRcaServiceFutureStub newFutureStub(Channel channel) {
        return (OpenRcaServiceFutureStub) OpenRcaServiceFutureStub.newStub(new AbstractStub.StubFactory<OpenRcaServiceFutureStub>() { // from class: io.grpc.xds.shaded.com.github.udpa.udpa.service.orca.v1.OpenRcaServiceGrpc.3
            @Override // io.grpc.stub.AbstractStub.StubFactory
            public OpenRcaServiceFutureStub newStub(Channel channel2, CallOptions callOptions) {
                return new OpenRcaServiceFutureStub(channel2, callOptions);
            }
        }, channel);
    }

    public static ServiceDescriptor getServiceDescriptor() {
        ServiceDescriptor serviceDescriptorBuild = serviceDescriptor;
        if (serviceDescriptorBuild == null) {
            synchronized (OpenRcaServiceGrpc.class) {
                serviceDescriptorBuild = serviceDescriptor;
                if (serviceDescriptorBuild == null) {
                    serviceDescriptorBuild = ServiceDescriptor.newBuilder(SERVICE_NAME).setSchemaDescriptor(new OpenRcaServiceFileDescriptorSupplier()).addMethod(getStreamCoreMetricsMethod()).build();
                    serviceDescriptor = serviceDescriptorBuild;
                }
            }
        }
        return serviceDescriptorBuild;
    }

    public static abstract class OpenRcaServiceImplBase implements BindableService {
        public void streamCoreMetrics(OrcaLoadReportRequest orcaLoadReportRequest, StreamObserver<OrcaLoadReport> streamObserver) {
            ServerCalls.asyncUnimplementedUnaryCall(OpenRcaServiceGrpc.getStreamCoreMetricsMethod(), streamObserver);
        }

        @Override // io.grpc.BindableService
        public final ServerServiceDefinition bindService() {
            return ServerServiceDefinition.builder(OpenRcaServiceGrpc.getServiceDescriptor()).addMethod(OpenRcaServiceGrpc.getStreamCoreMetricsMethod(), ServerCalls.asyncServerStreamingCall(new MethodHandlers(this, 0))).build();
        }
    }

    public static final class OpenRcaServiceStub extends AbstractAsyncStub<OpenRcaServiceStub> {
        private OpenRcaServiceStub(Channel channel, CallOptions callOptions) {
            super(channel, callOptions);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // io.grpc.stub.AbstractStub
        public OpenRcaServiceStub build(Channel channel, CallOptions callOptions) {
            return new OpenRcaServiceStub(channel, callOptions);
        }

        public void streamCoreMetrics(OrcaLoadReportRequest orcaLoadReportRequest, StreamObserver<OrcaLoadReport> streamObserver) {
            ClientCalls.asyncServerStreamingCall(getChannel().newCall(OpenRcaServiceGrpc.getStreamCoreMetricsMethod(), getCallOptions()), orcaLoadReportRequest, streamObserver);
        }
    }

    public static final class OpenRcaServiceBlockingStub extends AbstractBlockingStub<OpenRcaServiceBlockingStub> {
        private OpenRcaServiceBlockingStub(Channel channel, CallOptions callOptions) {
            super(channel, callOptions);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // io.grpc.stub.AbstractStub
        public OpenRcaServiceBlockingStub build(Channel channel, CallOptions callOptions) {
            return new OpenRcaServiceBlockingStub(channel, callOptions);
        }

        public Iterator<OrcaLoadReport> streamCoreMetrics(OrcaLoadReportRequest orcaLoadReportRequest) {
            return ClientCalls.blockingServerStreamingCall(getChannel(), OpenRcaServiceGrpc.getStreamCoreMetricsMethod(), getCallOptions(), orcaLoadReportRequest);
        }
    }

    public static final class OpenRcaServiceFutureStub extends AbstractFutureStub<OpenRcaServiceFutureStub> {
        private OpenRcaServiceFutureStub(Channel channel, CallOptions callOptions) {
            super(channel, callOptions);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // io.grpc.stub.AbstractStub
        public OpenRcaServiceFutureStub build(Channel channel, CallOptions callOptions) {
            return new OpenRcaServiceFutureStub(channel, callOptions);
        }
    }

    private static final class MethodHandlers<Req, Resp> implements ServerCalls.UnaryMethod<Req, Resp>, ServerCalls.ServerStreamingMethod<Req, Resp>, ServerCalls.ClientStreamingMethod<Req, Resp>, ServerCalls.BidiStreamingMethod<Req, Resp> {
        private final int methodId;
        private final OpenRcaServiceImplBase serviceImpl;

        MethodHandlers(OpenRcaServiceImplBase openRcaServiceImplBase, int i) {
            this.serviceImpl = openRcaServiceImplBase;
            this.methodId = i;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override
        // io.grpc.stub.ServerCalls.UnaryMethod, io.grpc.stub.ServerCalls.UnaryRequestMethod, io.grpc.stub.ServerCalls.ServerStreamingMethod
        public void invoke(Req req, StreamObserver<Resp> streamObserver) {
            if (this.methodId == 0) {
                this.serviceImpl.streamCoreMetrics((OrcaLoadReportRequest) req, streamObserver);
                return;
            }
            throw new AssertionError();
        }

        @Override
        // io.grpc.stub.ServerCalls.ClientStreamingMethod, io.grpc.stub.ServerCalls.StreamingRequestMethod, io.grpc.stub.ServerCalls.BidiStreamingMethod
        public StreamObserver<Req> invoke(StreamObserver<Resp> streamObserver) {
            throw new AssertionError();
        }
    }

    private static abstract class OpenRcaServiceBaseDescriptorSupplier implements ProtoFileDescriptorSupplier, ProtoServiceDescriptorSupplier {
        OpenRcaServiceBaseDescriptorSupplier() {
        }

        @Override // io.grpc.protobuf.ProtoFileDescriptorSupplier
        public Descriptors.FileDescriptor getFileDescriptor() {
            return OrcaProto.getDescriptor();
        }

        @Override // io.grpc.protobuf.ProtoServiceDescriptorSupplier
        public Descriptors.ServiceDescriptor getServiceDescriptor() {
            return getFileDescriptor().findServiceByName("OpenRcaService");
        }
    }

    private static final class OpenRcaServiceFileDescriptorSupplier extends OpenRcaServiceBaseDescriptorSupplier {
        OpenRcaServiceFileDescriptorSupplier() {
        }
    }

    private static final class OpenRcaServiceMethodDescriptorSupplier extends OpenRcaServiceBaseDescriptorSupplier implements ProtoMethodDescriptorSupplier {
        private final String methodName;

        OpenRcaServiceMethodDescriptorSupplier(String str) {
            this.methodName = str;
        }

        @Override // io.grpc.protobuf.ProtoMethodDescriptorSupplier
        public Descriptors.MethodDescriptor getMethodDescriptor() {
            return getServiceDescriptor().findMethodByName(this.methodName);
        }
    }
}
