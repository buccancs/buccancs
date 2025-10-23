package io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2;

import com.google.common.util.concurrent.ListenableFuture;
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

/* loaded from: classes3.dex */
public final class ClusterDiscoveryServiceGrpc {
    public static final String SERVICE_NAME = "envoy.api.v2.ClusterDiscoveryService";
    private static final int METHODID_DELTA_CLUSTERS = 2;
    private static final int METHODID_FETCH_CLUSTERS = 0;
    private static final int METHODID_STREAM_CLUSTERS = 1;
    private static volatile MethodDescriptor<DeltaDiscoveryRequest, DeltaDiscoveryResponse> getDeltaClustersMethod;
    private static volatile MethodDescriptor<DiscoveryRequest, DiscoveryResponse> getFetchClustersMethod;
    private static volatile MethodDescriptor<DiscoveryRequest, DiscoveryResponse> getStreamClustersMethod;
    private static volatile ServiceDescriptor serviceDescriptor;

    private ClusterDiscoveryServiceGrpc() {
    }

    public static MethodDescriptor<DiscoveryRequest, DiscoveryResponse> getStreamClustersMethod() {
        MethodDescriptor<DiscoveryRequest, DiscoveryResponse> methodDescriptorBuild = getStreamClustersMethod;
        if (methodDescriptorBuild == null) {
            synchronized (ClusterDiscoveryServiceGrpc.class) {
                methodDescriptorBuild = getStreamClustersMethod;
                if (methodDescriptorBuild == null) {
                    methodDescriptorBuild = MethodDescriptor.newBuilder().setType(MethodDescriptor.MethodType.BIDI_STREAMING).setFullMethodName(MethodDescriptor.generateFullMethodName(SERVICE_NAME, "StreamClusters")).setSampledToLocalTracing(true).setRequestMarshaller(ProtoUtils.marshaller(DiscoveryRequest.getDefaultInstance())).setResponseMarshaller(ProtoUtils.marshaller(DiscoveryResponse.getDefaultInstance())).setSchemaDescriptor(new ClusterDiscoveryServiceMethodDescriptorSupplier("StreamClusters")).build();
                    getStreamClustersMethod = methodDescriptorBuild;
                }
            }
        }
        return methodDescriptorBuild;
    }

    public static MethodDescriptor<DeltaDiscoveryRequest, DeltaDiscoveryResponse> getDeltaClustersMethod() {
        MethodDescriptor<DeltaDiscoveryRequest, DeltaDiscoveryResponse> methodDescriptorBuild = getDeltaClustersMethod;
        if (methodDescriptorBuild == null) {
            synchronized (ClusterDiscoveryServiceGrpc.class) {
                methodDescriptorBuild = getDeltaClustersMethod;
                if (methodDescriptorBuild == null) {
                    methodDescriptorBuild = MethodDescriptor.newBuilder().setType(MethodDescriptor.MethodType.BIDI_STREAMING).setFullMethodName(MethodDescriptor.generateFullMethodName(SERVICE_NAME, "DeltaClusters")).setSampledToLocalTracing(true).setRequestMarshaller(ProtoUtils.marshaller(DeltaDiscoveryRequest.getDefaultInstance())).setResponseMarshaller(ProtoUtils.marshaller(DeltaDiscoveryResponse.getDefaultInstance())).setSchemaDescriptor(new ClusterDiscoveryServiceMethodDescriptorSupplier("DeltaClusters")).build();
                    getDeltaClustersMethod = methodDescriptorBuild;
                }
            }
        }
        return methodDescriptorBuild;
    }

    public static MethodDescriptor<DiscoveryRequest, DiscoveryResponse> getFetchClustersMethod() {
        MethodDescriptor<DiscoveryRequest, DiscoveryResponse> methodDescriptorBuild = getFetchClustersMethod;
        if (methodDescriptorBuild == null) {
            synchronized (ClusterDiscoveryServiceGrpc.class) {
                methodDescriptorBuild = getFetchClustersMethod;
                if (methodDescriptorBuild == null) {
                    methodDescriptorBuild = MethodDescriptor.newBuilder().setType(MethodDescriptor.MethodType.UNARY).setFullMethodName(MethodDescriptor.generateFullMethodName(SERVICE_NAME, "FetchClusters")).setSampledToLocalTracing(true).setRequestMarshaller(ProtoUtils.marshaller(DiscoveryRequest.getDefaultInstance())).setResponseMarshaller(ProtoUtils.marshaller(DiscoveryResponse.getDefaultInstance())).setSchemaDescriptor(new ClusterDiscoveryServiceMethodDescriptorSupplier("FetchClusters")).build();
                    getFetchClustersMethod = methodDescriptorBuild;
                }
            }
        }
        return methodDescriptorBuild;
    }

    public static ClusterDiscoveryServiceStub newStub(Channel channel) {
        return (ClusterDiscoveryServiceStub) ClusterDiscoveryServiceStub.newStub(new AbstractStub.StubFactory<ClusterDiscoveryServiceStub>() { // from class: io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.ClusterDiscoveryServiceGrpc.1
            @Override // io.grpc.stub.AbstractStub.StubFactory
            public ClusterDiscoveryServiceStub newStub(Channel channel2, CallOptions callOptions) {
                return new ClusterDiscoveryServiceStub(channel2, callOptions);
            }
        }, channel);
    }

    public static ClusterDiscoveryServiceBlockingStub newBlockingStub(Channel channel) {
        return (ClusterDiscoveryServiceBlockingStub) ClusterDiscoveryServiceBlockingStub.newStub(new AbstractStub.StubFactory<ClusterDiscoveryServiceBlockingStub>() { // from class: io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.ClusterDiscoveryServiceGrpc.2
            @Override // io.grpc.stub.AbstractStub.StubFactory
            public ClusterDiscoveryServiceBlockingStub newStub(Channel channel2, CallOptions callOptions) {
                return new ClusterDiscoveryServiceBlockingStub(channel2, callOptions);
            }
        }, channel);
    }

    public static ClusterDiscoveryServiceFutureStub newFutureStub(Channel channel) {
        return (ClusterDiscoveryServiceFutureStub) ClusterDiscoveryServiceFutureStub.newStub(new AbstractStub.StubFactory<ClusterDiscoveryServiceFutureStub>() { // from class: io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.ClusterDiscoveryServiceGrpc.3
            @Override // io.grpc.stub.AbstractStub.StubFactory
            public ClusterDiscoveryServiceFutureStub newStub(Channel channel2, CallOptions callOptions) {
                return new ClusterDiscoveryServiceFutureStub(channel2, callOptions);
            }
        }, channel);
    }

    public static ServiceDescriptor getServiceDescriptor() {
        ServiceDescriptor serviceDescriptorBuild = serviceDescriptor;
        if (serviceDescriptorBuild == null) {
            synchronized (ClusterDiscoveryServiceGrpc.class) {
                serviceDescriptorBuild = serviceDescriptor;
                if (serviceDescriptorBuild == null) {
                    serviceDescriptorBuild = ServiceDescriptor.newBuilder(SERVICE_NAME).setSchemaDescriptor(new ClusterDiscoveryServiceFileDescriptorSupplier()).addMethod(getStreamClustersMethod()).addMethod(getDeltaClustersMethod()).addMethod(getFetchClustersMethod()).build();
                    serviceDescriptor = serviceDescriptorBuild;
                }
            }
        }
        return serviceDescriptorBuild;
    }

    public static abstract class ClusterDiscoveryServiceImplBase implements BindableService {
        public StreamObserver<DiscoveryRequest> streamClusters(StreamObserver<DiscoveryResponse> streamObserver) {
            return ServerCalls.asyncUnimplementedStreamingCall(ClusterDiscoveryServiceGrpc.getStreamClustersMethod(), streamObserver);
        }

        public StreamObserver<DeltaDiscoveryRequest> deltaClusters(StreamObserver<DeltaDiscoveryResponse> streamObserver) {
            return ServerCalls.asyncUnimplementedStreamingCall(ClusterDiscoveryServiceGrpc.getDeltaClustersMethod(), streamObserver);
        }

        public void fetchClusters(DiscoveryRequest discoveryRequest, StreamObserver<DiscoveryResponse> streamObserver) {
            ServerCalls.asyncUnimplementedUnaryCall(ClusterDiscoveryServiceGrpc.getFetchClustersMethod(), streamObserver);
        }

        @Override // io.grpc.BindableService
        public final ServerServiceDefinition bindService() {
            return ServerServiceDefinition.builder(ClusterDiscoveryServiceGrpc.getServiceDescriptor()).addMethod(ClusterDiscoveryServiceGrpc.getStreamClustersMethod(), ServerCalls.asyncBidiStreamingCall(new MethodHandlers(this, 1))).addMethod(ClusterDiscoveryServiceGrpc.getDeltaClustersMethod(), ServerCalls.asyncBidiStreamingCall(new MethodHandlers(this, 2))).addMethod(ClusterDiscoveryServiceGrpc.getFetchClustersMethod(), ServerCalls.asyncUnaryCall(new MethodHandlers(this, 0))).build();
        }
    }

    public static final class ClusterDiscoveryServiceStub extends AbstractAsyncStub<ClusterDiscoveryServiceStub> {
        private ClusterDiscoveryServiceStub(Channel channel, CallOptions callOptions) {
            super(channel, callOptions);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // io.grpc.stub.AbstractStub
        public ClusterDiscoveryServiceStub build(Channel channel, CallOptions callOptions) {
            return new ClusterDiscoveryServiceStub(channel, callOptions);
        }

        public StreamObserver<DiscoveryRequest> streamClusters(StreamObserver<DiscoveryResponse> streamObserver) {
            return ClientCalls.asyncBidiStreamingCall(getChannel().newCall(ClusterDiscoveryServiceGrpc.getStreamClustersMethod(), getCallOptions()), streamObserver);
        }

        public StreamObserver<DeltaDiscoveryRequest> deltaClusters(StreamObserver<DeltaDiscoveryResponse> streamObserver) {
            return ClientCalls.asyncBidiStreamingCall(getChannel().newCall(ClusterDiscoveryServiceGrpc.getDeltaClustersMethod(), getCallOptions()), streamObserver);
        }

        public void fetchClusters(DiscoveryRequest discoveryRequest, StreamObserver<DiscoveryResponse> streamObserver) {
            ClientCalls.asyncUnaryCall(getChannel().newCall(ClusterDiscoveryServiceGrpc.getFetchClustersMethod(), getCallOptions()), discoveryRequest, streamObserver);
        }
    }

    public static final class ClusterDiscoveryServiceBlockingStub extends AbstractBlockingStub<ClusterDiscoveryServiceBlockingStub> {
        private ClusterDiscoveryServiceBlockingStub(Channel channel, CallOptions callOptions) {
            super(channel, callOptions);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // io.grpc.stub.AbstractStub
        public ClusterDiscoveryServiceBlockingStub build(Channel channel, CallOptions callOptions) {
            return new ClusterDiscoveryServiceBlockingStub(channel, callOptions);
        }

        public DiscoveryResponse fetchClusters(DiscoveryRequest discoveryRequest) {
            return (DiscoveryResponse) ClientCalls.blockingUnaryCall(getChannel(), ClusterDiscoveryServiceGrpc.getFetchClustersMethod(), getCallOptions(), discoveryRequest);
        }
    }

    public static final class ClusterDiscoveryServiceFutureStub extends AbstractFutureStub<ClusterDiscoveryServiceFutureStub> {
        private ClusterDiscoveryServiceFutureStub(Channel channel, CallOptions callOptions) {
            super(channel, callOptions);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // io.grpc.stub.AbstractStub
        public ClusterDiscoveryServiceFutureStub build(Channel channel, CallOptions callOptions) {
            return new ClusterDiscoveryServiceFutureStub(channel, callOptions);
        }

        public ListenableFuture<DiscoveryResponse> fetchClusters(DiscoveryRequest discoveryRequest) {
            return ClientCalls.futureUnaryCall(getChannel().newCall(ClusterDiscoveryServiceGrpc.getFetchClustersMethod(), getCallOptions()), discoveryRequest);
        }
    }

    private static final class MethodHandlers<Req, Resp> implements ServerCalls.UnaryMethod<Req, Resp>, ServerCalls.ServerStreamingMethod<Req, Resp>, ServerCalls.ClientStreamingMethod<Req, Resp>, ServerCalls.BidiStreamingMethod<Req, Resp> {
        private final int methodId;
        private final ClusterDiscoveryServiceImplBase serviceImpl;

        MethodHandlers(ClusterDiscoveryServiceImplBase clusterDiscoveryServiceImplBase, int i) {
            this.serviceImpl = clusterDiscoveryServiceImplBase;
            this.methodId = i;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override
        // io.grpc.stub.ServerCalls.UnaryMethod, io.grpc.stub.ServerCalls.UnaryRequestMethod, io.grpc.stub.ServerCalls.ServerStreamingMethod
        public void invoke(Req req, StreamObserver<Resp> streamObserver) {
            if (this.methodId == 0) {
                this.serviceImpl.fetchClusters((DiscoveryRequest) req, streamObserver);
                return;
            }
            throw new AssertionError();
        }

        @Override
        // io.grpc.stub.ServerCalls.ClientStreamingMethod, io.grpc.stub.ServerCalls.StreamingRequestMethod, io.grpc.stub.ServerCalls.BidiStreamingMethod
        public StreamObserver<Req> invoke(StreamObserver<Resp> streamObserver) {
            int i = this.methodId;
            if (i == 1) {
                return (StreamObserver<Req>) this.serviceImpl.streamClusters(streamObserver);
            }
            if (i == 2) {
                return (StreamObserver<Req>) this.serviceImpl.deltaClusters(streamObserver);
            }
            throw new AssertionError();
        }
    }

    private static abstract class ClusterDiscoveryServiceBaseDescriptorSupplier implements ProtoFileDescriptorSupplier, ProtoServiceDescriptorSupplier {
        ClusterDiscoveryServiceBaseDescriptorSupplier() {
        }

        @Override // io.grpc.protobuf.ProtoFileDescriptorSupplier
        public Descriptors.FileDescriptor getFileDescriptor() {
            return CdsProto.getDescriptor();
        }

        @Override // io.grpc.protobuf.ProtoServiceDescriptorSupplier
        public Descriptors.ServiceDescriptor getServiceDescriptor() {
            return getFileDescriptor().findServiceByName("ClusterDiscoveryService");
        }
    }

    private static final class ClusterDiscoveryServiceFileDescriptorSupplier extends ClusterDiscoveryServiceBaseDescriptorSupplier {
        ClusterDiscoveryServiceFileDescriptorSupplier() {
        }
    }

    private static final class ClusterDiscoveryServiceMethodDescriptorSupplier extends ClusterDiscoveryServiceBaseDescriptorSupplier implements ProtoMethodDescriptorSupplier {
        private final String methodName;

        ClusterDiscoveryServiceMethodDescriptorSupplier(String str) {
            this.methodName = str;
        }

        @Override // io.grpc.protobuf.ProtoMethodDescriptorSupplier
        public Descriptors.MethodDescriptor getMethodDescriptor() {
            return getServiceDescriptor().findMethodByName(this.methodName);
        }
    }
}
