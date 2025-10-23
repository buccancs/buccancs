package io.grpc.xds.shaded.io.envoyproxy.envoy.service.load_stats.v2;

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

/* loaded from: classes4.dex */
public final class LoadReportingServiceGrpc {
    public static final String SERVICE_NAME = "envoy.service.load_stats.v2.LoadReportingService";
    private static final int METHODID_STREAM_LOAD_STATS = 0;
    private static volatile MethodDescriptor<LoadStatsRequest, LoadStatsResponse> getStreamLoadStatsMethod;
    private static volatile ServiceDescriptor serviceDescriptor;

    private LoadReportingServiceGrpc() {
    }

    public static MethodDescriptor<LoadStatsRequest, LoadStatsResponse> getStreamLoadStatsMethod() {
        MethodDescriptor<LoadStatsRequest, LoadStatsResponse> methodDescriptorBuild = getStreamLoadStatsMethod;
        if (methodDescriptorBuild == null) {
            synchronized (LoadReportingServiceGrpc.class) {
                methodDescriptorBuild = getStreamLoadStatsMethod;
                if (methodDescriptorBuild == null) {
                    methodDescriptorBuild = MethodDescriptor.newBuilder().setType(MethodDescriptor.MethodType.BIDI_STREAMING).setFullMethodName(MethodDescriptor.generateFullMethodName(SERVICE_NAME, "StreamLoadStats")).setSampledToLocalTracing(true).setRequestMarshaller(ProtoUtils.marshaller(LoadStatsRequest.getDefaultInstance())).setResponseMarshaller(ProtoUtils.marshaller(LoadStatsResponse.getDefaultInstance())).setSchemaDescriptor(new LoadReportingServiceMethodDescriptorSupplier("StreamLoadStats")).build();
                    getStreamLoadStatsMethod = methodDescriptorBuild;
                }
            }
        }
        return methodDescriptorBuild;
    }

    public static LoadReportingServiceStub newStub(Channel channel) {
        return (LoadReportingServiceStub) LoadReportingServiceStub.newStub(new AbstractStub.StubFactory<LoadReportingServiceStub>() { // from class: io.grpc.xds.shaded.io.envoyproxy.envoy.service.load_stats.v2.LoadReportingServiceGrpc.1
            @Override // io.grpc.stub.AbstractStub.StubFactory
            public LoadReportingServiceStub newStub(Channel channel2, CallOptions callOptions) {
                return new LoadReportingServiceStub(channel2, callOptions);
            }
        }, channel);
    }

    public static LoadReportingServiceBlockingStub newBlockingStub(Channel channel) {
        return (LoadReportingServiceBlockingStub) LoadReportingServiceBlockingStub.newStub(new AbstractStub.StubFactory<LoadReportingServiceBlockingStub>() { // from class: io.grpc.xds.shaded.io.envoyproxy.envoy.service.load_stats.v2.LoadReportingServiceGrpc.2
            @Override // io.grpc.stub.AbstractStub.StubFactory
            public LoadReportingServiceBlockingStub newStub(Channel channel2, CallOptions callOptions) {
                return new LoadReportingServiceBlockingStub(channel2, callOptions);
            }
        }, channel);
    }

    public static LoadReportingServiceFutureStub newFutureStub(Channel channel) {
        return (LoadReportingServiceFutureStub) LoadReportingServiceFutureStub.newStub(new AbstractStub.StubFactory<LoadReportingServiceFutureStub>() { // from class: io.grpc.xds.shaded.io.envoyproxy.envoy.service.load_stats.v2.LoadReportingServiceGrpc.3
            @Override // io.grpc.stub.AbstractStub.StubFactory
            public LoadReportingServiceFutureStub newStub(Channel channel2, CallOptions callOptions) {
                return new LoadReportingServiceFutureStub(channel2, callOptions);
            }
        }, channel);
    }

    public static ServiceDescriptor getServiceDescriptor() {
        ServiceDescriptor serviceDescriptorBuild = serviceDescriptor;
        if (serviceDescriptorBuild == null) {
            synchronized (LoadReportingServiceGrpc.class) {
                serviceDescriptorBuild = serviceDescriptor;
                if (serviceDescriptorBuild == null) {
                    serviceDescriptorBuild = ServiceDescriptor.newBuilder(SERVICE_NAME).setSchemaDescriptor(new LoadReportingServiceFileDescriptorSupplier()).addMethod(getStreamLoadStatsMethod()).build();
                    serviceDescriptor = serviceDescriptorBuild;
                }
            }
        }
        return serviceDescriptorBuild;
    }

    public static abstract class LoadReportingServiceImplBase implements BindableService {
        public StreamObserver<LoadStatsRequest> streamLoadStats(StreamObserver<LoadStatsResponse> streamObserver) {
            return ServerCalls.asyncUnimplementedStreamingCall(LoadReportingServiceGrpc.getStreamLoadStatsMethod(), streamObserver);
        }

        @Override // io.grpc.BindableService
        public final ServerServiceDefinition bindService() {
            return ServerServiceDefinition.builder(LoadReportingServiceGrpc.getServiceDescriptor()).addMethod(LoadReportingServiceGrpc.getStreamLoadStatsMethod(), ServerCalls.asyncBidiStreamingCall(new MethodHandlers(this, 0))).build();
        }
    }

    public static final class LoadReportingServiceStub extends AbstractAsyncStub<LoadReportingServiceStub> {
        private LoadReportingServiceStub(Channel channel, CallOptions callOptions) {
            super(channel, callOptions);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // io.grpc.stub.AbstractStub
        public LoadReportingServiceStub build(Channel channel, CallOptions callOptions) {
            return new LoadReportingServiceStub(channel, callOptions);
        }

        public StreamObserver<LoadStatsRequest> streamLoadStats(StreamObserver<LoadStatsResponse> streamObserver) {
            return ClientCalls.asyncBidiStreamingCall(getChannel().newCall(LoadReportingServiceGrpc.getStreamLoadStatsMethod(), getCallOptions()), streamObserver);
        }
    }

    public static final class LoadReportingServiceBlockingStub extends AbstractBlockingStub<LoadReportingServiceBlockingStub> {
        private LoadReportingServiceBlockingStub(Channel channel, CallOptions callOptions) {
            super(channel, callOptions);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // io.grpc.stub.AbstractStub
        public LoadReportingServiceBlockingStub build(Channel channel, CallOptions callOptions) {
            return new LoadReportingServiceBlockingStub(channel, callOptions);
        }
    }

    public static final class LoadReportingServiceFutureStub extends AbstractFutureStub<LoadReportingServiceFutureStub> {
        private LoadReportingServiceFutureStub(Channel channel, CallOptions callOptions) {
            super(channel, callOptions);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // io.grpc.stub.AbstractStub
        public LoadReportingServiceFutureStub build(Channel channel, CallOptions callOptions) {
            return new LoadReportingServiceFutureStub(channel, callOptions);
        }
    }

    private static final class MethodHandlers<Req, Resp> implements ServerCalls.UnaryMethod<Req, Resp>, ServerCalls.ServerStreamingMethod<Req, Resp>, ServerCalls.ClientStreamingMethod<Req, Resp>, ServerCalls.BidiStreamingMethod<Req, Resp> {
        private final int methodId;
        private final LoadReportingServiceImplBase serviceImpl;

        MethodHandlers(LoadReportingServiceImplBase loadReportingServiceImplBase, int i) {
            this.serviceImpl = loadReportingServiceImplBase;
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
                return (StreamObserver<Req>) this.serviceImpl.streamLoadStats(streamObserver);
            }
            throw new AssertionError();
        }
    }

    private static abstract class LoadReportingServiceBaseDescriptorSupplier implements ProtoFileDescriptorSupplier, ProtoServiceDescriptorSupplier {
        LoadReportingServiceBaseDescriptorSupplier() {
        }

        @Override // io.grpc.protobuf.ProtoFileDescriptorSupplier
        public Descriptors.FileDescriptor getFileDescriptor() {
            return LrsProto.getDescriptor();
        }

        @Override // io.grpc.protobuf.ProtoServiceDescriptorSupplier
        public Descriptors.ServiceDescriptor getServiceDescriptor() {
            return getFileDescriptor().findServiceByName("LoadReportingService");
        }
    }

    private static final class LoadReportingServiceFileDescriptorSupplier extends LoadReportingServiceBaseDescriptorSupplier {
        LoadReportingServiceFileDescriptorSupplier() {
        }
    }

    private static final class LoadReportingServiceMethodDescriptorSupplier extends LoadReportingServiceBaseDescriptorSupplier implements ProtoMethodDescriptorSupplier {
        private final String methodName;

        LoadReportingServiceMethodDescriptorSupplier(String str) {
            this.methodName = str;
        }

        @Override // io.grpc.protobuf.ProtoMethodDescriptorSupplier
        public Descriptors.MethodDescriptor getMethodDescriptor() {
            return getServiceDescriptor().findMethodByName(this.methodName);
        }
    }
}
