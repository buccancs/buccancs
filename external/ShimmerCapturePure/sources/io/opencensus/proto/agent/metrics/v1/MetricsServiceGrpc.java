package io.opencensus.proto.agent.metrics.v1;

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
import io.grpc.stub.AbstractStub;
import io.grpc.stub.ClientCalls;
import io.grpc.stub.ServerCalls;
import io.grpc.stub.StreamObserver;

/* loaded from: classes4.dex */
public final class MetricsServiceGrpc {
    public static final String SERVICE_NAME = "opencensus.proto.agent.metrics.v1.MetricsService";
    private static final int METHODID_EXPORT = 0;
    private static volatile MethodDescriptor<ExportMetricsServiceRequest, ExportMetricsServiceResponse> getExportMethod;
    private static volatile ServiceDescriptor serviceDescriptor;

    private MetricsServiceGrpc() {
    }

    public static MethodDescriptor<ExportMetricsServiceRequest, ExportMetricsServiceResponse> getExportMethod() {
        MethodDescriptor<ExportMetricsServiceRequest, ExportMetricsServiceResponse> methodDescriptorBuild = getExportMethod;
        if (methodDescriptorBuild == null) {
            synchronized (MetricsServiceGrpc.class) {
                methodDescriptorBuild = getExportMethod;
                if (methodDescriptorBuild == null) {
                    methodDescriptorBuild = MethodDescriptor.newBuilder().setType(MethodDescriptor.MethodType.BIDI_STREAMING).setFullMethodName(MethodDescriptor.generateFullMethodName(SERVICE_NAME, "Export")).setSampledToLocalTracing(true).setRequestMarshaller(ProtoUtils.marshaller(ExportMetricsServiceRequest.getDefaultInstance())).setResponseMarshaller(ProtoUtils.marshaller(ExportMetricsServiceResponse.getDefaultInstance())).setSchemaDescriptor(new MetricsServiceMethodDescriptorSupplier("Export")).build();
                    getExportMethod = methodDescriptorBuild;
                }
            }
        }
        return methodDescriptorBuild;
    }

    public static MetricsServiceStub newStub(Channel channel) {
        return new MetricsServiceStub(channel);
    }

    public static MetricsServiceBlockingStub newBlockingStub(Channel channel) {
        return new MetricsServiceBlockingStub(channel);
    }

    public static MetricsServiceFutureStub newFutureStub(Channel channel) {
        return new MetricsServiceFutureStub(channel);
    }

    public static ServiceDescriptor getServiceDescriptor() {
        ServiceDescriptor serviceDescriptorBuild = serviceDescriptor;
        if (serviceDescriptorBuild == null) {
            synchronized (MetricsServiceGrpc.class) {
                serviceDescriptorBuild = serviceDescriptor;
                if (serviceDescriptorBuild == null) {
                    serviceDescriptorBuild = ServiceDescriptor.newBuilder(SERVICE_NAME).setSchemaDescriptor(new MetricsServiceFileDescriptorSupplier()).addMethod(getExportMethod()).build();
                    serviceDescriptor = serviceDescriptorBuild;
                }
            }
        }
        return serviceDescriptorBuild;
    }

    public static abstract class MetricsServiceImplBase implements BindableService {
        public StreamObserver<ExportMetricsServiceRequest> export(StreamObserver<ExportMetricsServiceResponse> streamObserver) {
            return ServerCalls.asyncUnimplementedStreamingCall(MetricsServiceGrpc.getExportMethod(), streamObserver);
        }

        @Override // io.grpc.BindableService
        public final ServerServiceDefinition bindService() {
            return ServerServiceDefinition.builder(MetricsServiceGrpc.getServiceDescriptor()).addMethod(MetricsServiceGrpc.getExportMethod(), ServerCalls.asyncBidiStreamingCall(new MethodHandlers(this, 0))).build();
        }
    }

    public static final class MetricsServiceStub extends AbstractStub<MetricsServiceStub> {
        private MetricsServiceStub(Channel channel) {
            super(channel);
        }

        private MetricsServiceStub(Channel channel, CallOptions callOptions) {
            super(channel, callOptions);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // io.grpc.stub.AbstractStub
        public MetricsServiceStub build(Channel channel, CallOptions callOptions) {
            return new MetricsServiceStub(channel, callOptions);
        }

        public StreamObserver<ExportMetricsServiceRequest> export(StreamObserver<ExportMetricsServiceResponse> streamObserver) {
            return ClientCalls.asyncBidiStreamingCall(getChannel().newCall(MetricsServiceGrpc.getExportMethod(), getCallOptions()), streamObserver);
        }
    }

    public static final class MetricsServiceBlockingStub extends AbstractStub<MetricsServiceBlockingStub> {
        private MetricsServiceBlockingStub(Channel channel) {
            super(channel);
        }

        private MetricsServiceBlockingStub(Channel channel, CallOptions callOptions) {
            super(channel, callOptions);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // io.grpc.stub.AbstractStub
        public MetricsServiceBlockingStub build(Channel channel, CallOptions callOptions) {
            return new MetricsServiceBlockingStub(channel, callOptions);
        }
    }

    public static final class MetricsServiceFutureStub extends AbstractStub<MetricsServiceFutureStub> {
        private MetricsServiceFutureStub(Channel channel) {
            super(channel);
        }

        private MetricsServiceFutureStub(Channel channel, CallOptions callOptions) {
            super(channel, callOptions);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // io.grpc.stub.AbstractStub
        public MetricsServiceFutureStub build(Channel channel, CallOptions callOptions) {
            return new MetricsServiceFutureStub(channel, callOptions);
        }
    }

    private static final class MethodHandlers<Req, Resp> implements ServerCalls.UnaryMethod<Req, Resp>, ServerCalls.ServerStreamingMethod<Req, Resp>, ServerCalls.ClientStreamingMethod<Req, Resp>, ServerCalls.BidiStreamingMethod<Req, Resp> {
        private final int methodId;
        private final MetricsServiceImplBase serviceImpl;

        MethodHandlers(MetricsServiceImplBase metricsServiceImplBase, int i) {
            this.serviceImpl = metricsServiceImplBase;
            this.methodId = i;
        }

        @Override
        // io.grpc.stub.ServerCalls.UnaryMethod, io.grpc.stub.ServerCalls.UnaryRequestMethod, io.grpc.stub.ServerCalls.ServerStreamingMethod
        public void invoke(Req req, StreamObserver<Resp> streamObserver) {
            throw new AssertionError();
        }

        @Override
        // io.grpc.stub.ServerCalls.ClientStreamingMethod, io.grpc.stub.ServerCalls.StreamingRequestMethod, io.grpc.stub.ServerCalls.BidiStreamingMethod
        public StreamObserver<Req> invoke(StreamObserver<Resp> streamObserver) {
            if (this.methodId == 0) {
                return (StreamObserver<Req>) this.serviceImpl.export(streamObserver);
            }
            throw new AssertionError();
        }
    }

    private static abstract class MetricsServiceBaseDescriptorSupplier implements ProtoFileDescriptorSupplier, ProtoServiceDescriptorSupplier {
        MetricsServiceBaseDescriptorSupplier() {
        }

        @Override // io.grpc.protobuf.ProtoFileDescriptorSupplier
        public Descriptors.FileDescriptor getFileDescriptor() {
            return MetricsServiceProto.getDescriptor();
        }

        @Override // io.grpc.protobuf.ProtoServiceDescriptorSupplier
        public Descriptors.ServiceDescriptor getServiceDescriptor() {
            return getFileDescriptor().findServiceByName("MetricsService");
        }
    }

    private static final class MetricsServiceFileDescriptorSupplier extends MetricsServiceBaseDescriptorSupplier {
        MetricsServiceFileDescriptorSupplier() {
        }
    }

    private static final class MetricsServiceMethodDescriptorSupplier extends MetricsServiceBaseDescriptorSupplier implements ProtoMethodDescriptorSupplier {
        private final String methodName;

        MetricsServiceMethodDescriptorSupplier(String str) {
            this.methodName = str;
        }

        @Override // io.grpc.protobuf.ProtoMethodDescriptorSupplier
        public Descriptors.MethodDescriptor getMethodDescriptor() {
            return getServiceDescriptor().findMethodByName(this.methodName);
        }
    }
}
