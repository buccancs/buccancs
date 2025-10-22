package io.grpc.xds.shaded.io.envoyproxy.envoy.service.discovery.v3;

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
public final class AggregatedDiscoveryServiceGrpc {
    public static final String SERVICE_NAME = "envoy.service.discovery.v3.AggregatedDiscoveryService";
    private static final int METHODID_DELTA_AGGREGATED_RESOURCES = 1;
    private static final int METHODID_STREAM_AGGREGATED_RESOURCES = 0;
    private static volatile MethodDescriptor<DeltaDiscoveryRequest, DeltaDiscoveryResponse> getDeltaAggregatedResourcesMethod;
    private static volatile MethodDescriptor<DiscoveryRequest, DiscoveryResponse> getStreamAggregatedResourcesMethod;
    private static volatile ServiceDescriptor serviceDescriptor;

    private AggregatedDiscoveryServiceGrpc() {
    }

    public static MethodDescriptor<DiscoveryRequest, DiscoveryResponse> getStreamAggregatedResourcesMethod() {
        MethodDescriptor<DiscoveryRequest, DiscoveryResponse> methodDescriptorBuild = getStreamAggregatedResourcesMethod;
        if (methodDescriptorBuild == null) {
            synchronized (AggregatedDiscoveryServiceGrpc.class) {
                methodDescriptorBuild = getStreamAggregatedResourcesMethod;
                if (methodDescriptorBuild == null) {
                    methodDescriptorBuild = MethodDescriptor.newBuilder().setType(MethodDescriptor.MethodType.BIDI_STREAMING).setFullMethodName(MethodDescriptor.generateFullMethodName(SERVICE_NAME, "StreamAggregatedResources")).setSampledToLocalTracing(true).setRequestMarshaller(ProtoUtils.marshaller(DiscoveryRequest.getDefaultInstance())).setResponseMarshaller(ProtoUtils.marshaller(DiscoveryResponse.getDefaultInstance())).setSchemaDescriptor(new AggregatedDiscoveryServiceMethodDescriptorSupplier("StreamAggregatedResources")).build();
                    getStreamAggregatedResourcesMethod = methodDescriptorBuild;
                }
            }
        }
        return methodDescriptorBuild;
    }

    public static MethodDescriptor<DeltaDiscoveryRequest, DeltaDiscoveryResponse> getDeltaAggregatedResourcesMethod() {
        MethodDescriptor<DeltaDiscoveryRequest, DeltaDiscoveryResponse> methodDescriptorBuild = getDeltaAggregatedResourcesMethod;
        if (methodDescriptorBuild == null) {
            synchronized (AggregatedDiscoveryServiceGrpc.class) {
                methodDescriptorBuild = getDeltaAggregatedResourcesMethod;
                if (methodDescriptorBuild == null) {
                    methodDescriptorBuild = MethodDescriptor.newBuilder().setType(MethodDescriptor.MethodType.BIDI_STREAMING).setFullMethodName(MethodDescriptor.generateFullMethodName(SERVICE_NAME, "DeltaAggregatedResources")).setSampledToLocalTracing(true).setRequestMarshaller(ProtoUtils.marshaller(DeltaDiscoveryRequest.getDefaultInstance())).setResponseMarshaller(ProtoUtils.marshaller(DeltaDiscoveryResponse.getDefaultInstance())).setSchemaDescriptor(new AggregatedDiscoveryServiceMethodDescriptorSupplier("DeltaAggregatedResources")).build();
                    getDeltaAggregatedResourcesMethod = methodDescriptorBuild;
                }
            }
        }
        return methodDescriptorBuild;
    }

    public static AggregatedDiscoveryServiceStub newStub(Channel channel) {
        return (AggregatedDiscoveryServiceStub) AggregatedDiscoveryServiceStub.newStub(new AbstractStub.StubFactory<AggregatedDiscoveryServiceStub>() { // from class: io.grpc.xds.shaded.io.envoyproxy.envoy.service.discovery.v3.AggregatedDiscoveryServiceGrpc.1
            @Override // io.grpc.stub.AbstractStub.StubFactory
            public AggregatedDiscoveryServiceStub newStub(Channel channel2, CallOptions callOptions) {
                return new AggregatedDiscoveryServiceStub(channel2, callOptions);
            }
        }, channel);
    }

    public static AggregatedDiscoveryServiceBlockingStub newBlockingStub(Channel channel) {
        return (AggregatedDiscoveryServiceBlockingStub) AggregatedDiscoveryServiceBlockingStub.newStub(new AbstractStub.StubFactory<AggregatedDiscoveryServiceBlockingStub>() { // from class: io.grpc.xds.shaded.io.envoyproxy.envoy.service.discovery.v3.AggregatedDiscoveryServiceGrpc.2
            @Override // io.grpc.stub.AbstractStub.StubFactory
            public AggregatedDiscoveryServiceBlockingStub newStub(Channel channel2, CallOptions callOptions) {
                return new AggregatedDiscoveryServiceBlockingStub(channel2, callOptions);
            }
        }, channel);
    }

    public static AggregatedDiscoveryServiceFutureStub newFutureStub(Channel channel) {
        return (AggregatedDiscoveryServiceFutureStub) AggregatedDiscoveryServiceFutureStub.newStub(new AbstractStub.StubFactory<AggregatedDiscoveryServiceFutureStub>() { // from class: io.grpc.xds.shaded.io.envoyproxy.envoy.service.discovery.v3.AggregatedDiscoveryServiceGrpc.3
            @Override // io.grpc.stub.AbstractStub.StubFactory
            public AggregatedDiscoveryServiceFutureStub newStub(Channel channel2, CallOptions callOptions) {
                return new AggregatedDiscoveryServiceFutureStub(channel2, callOptions);
            }
        }, channel);
    }

    public static ServiceDescriptor getServiceDescriptor() {
        ServiceDescriptor serviceDescriptorBuild = serviceDescriptor;
        if (serviceDescriptorBuild == null) {
            synchronized (AggregatedDiscoveryServiceGrpc.class) {
                serviceDescriptorBuild = serviceDescriptor;
                if (serviceDescriptorBuild == null) {
                    serviceDescriptorBuild = ServiceDescriptor.newBuilder(SERVICE_NAME).setSchemaDescriptor(new AggregatedDiscoveryServiceFileDescriptorSupplier()).addMethod(getStreamAggregatedResourcesMethod()).addMethod(getDeltaAggregatedResourcesMethod()).build();
                    serviceDescriptor = serviceDescriptorBuild;
                }
            }
        }
        return serviceDescriptorBuild;
    }

    public static abstract class AggregatedDiscoveryServiceImplBase implements BindableService {
        public StreamObserver<DiscoveryRequest> streamAggregatedResources(StreamObserver<DiscoveryResponse> streamObserver) {
            return ServerCalls.asyncUnimplementedStreamingCall(AggregatedDiscoveryServiceGrpc.getStreamAggregatedResourcesMethod(), streamObserver);
        }

        public StreamObserver<DeltaDiscoveryRequest> deltaAggregatedResources(StreamObserver<DeltaDiscoveryResponse> streamObserver) {
            return ServerCalls.asyncUnimplementedStreamingCall(AggregatedDiscoveryServiceGrpc.getDeltaAggregatedResourcesMethod(), streamObserver);
        }

        @Override // io.grpc.BindableService
        public final ServerServiceDefinition bindService() {
            return ServerServiceDefinition.builder(AggregatedDiscoveryServiceGrpc.getServiceDescriptor()).addMethod(AggregatedDiscoveryServiceGrpc.getStreamAggregatedResourcesMethod(), ServerCalls.asyncBidiStreamingCall(new MethodHandlers(this, 0))).addMethod(AggregatedDiscoveryServiceGrpc.getDeltaAggregatedResourcesMethod(), ServerCalls.asyncBidiStreamingCall(new MethodHandlers(this, 1))).build();
        }
    }

    public static final class AggregatedDiscoveryServiceStub extends AbstractAsyncStub<AggregatedDiscoveryServiceStub> {
        private AggregatedDiscoveryServiceStub(Channel channel, CallOptions callOptions) {
            super(channel, callOptions);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // io.grpc.stub.AbstractStub
        public AggregatedDiscoveryServiceStub build(Channel channel, CallOptions callOptions) {
            return new AggregatedDiscoveryServiceStub(channel, callOptions);
        }

        public StreamObserver<DiscoveryRequest> streamAggregatedResources(StreamObserver<DiscoveryResponse> streamObserver) {
            return ClientCalls.asyncBidiStreamingCall(getChannel().newCall(AggregatedDiscoveryServiceGrpc.getStreamAggregatedResourcesMethod(), getCallOptions()), streamObserver);
        }

        public StreamObserver<DeltaDiscoveryRequest> deltaAggregatedResources(StreamObserver<DeltaDiscoveryResponse> streamObserver) {
            return ClientCalls.asyncBidiStreamingCall(getChannel().newCall(AggregatedDiscoveryServiceGrpc.getDeltaAggregatedResourcesMethod(), getCallOptions()), streamObserver);
        }
    }

    public static final class AggregatedDiscoveryServiceBlockingStub extends AbstractBlockingStub<AggregatedDiscoveryServiceBlockingStub> {
        private AggregatedDiscoveryServiceBlockingStub(Channel channel, CallOptions callOptions) {
            super(channel, callOptions);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // io.grpc.stub.AbstractStub
        public AggregatedDiscoveryServiceBlockingStub build(Channel channel, CallOptions callOptions) {
            return new AggregatedDiscoveryServiceBlockingStub(channel, callOptions);
        }
    }

    public static final class AggregatedDiscoveryServiceFutureStub extends AbstractFutureStub<AggregatedDiscoveryServiceFutureStub> {
        private AggregatedDiscoveryServiceFutureStub(Channel channel, CallOptions callOptions) {
            super(channel, callOptions);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // io.grpc.stub.AbstractStub
        public AggregatedDiscoveryServiceFutureStub build(Channel channel, CallOptions callOptions) {
            return new AggregatedDiscoveryServiceFutureStub(channel, callOptions);
        }
    }

    private static final class MethodHandlers<Req, Resp> implements ServerCalls.UnaryMethod<Req, Resp>, ServerCalls.ServerStreamingMethod<Req, Resp>, ServerCalls.ClientStreamingMethod<Req, Resp>, ServerCalls.BidiStreamingMethod<Req, Resp> {
        private final int methodId;
        private final AggregatedDiscoveryServiceImplBase serviceImpl;

        MethodHandlers(AggregatedDiscoveryServiceImplBase aggregatedDiscoveryServiceImplBase, int i) {
            this.serviceImpl = aggregatedDiscoveryServiceImplBase;
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
            int i = this.methodId;
            if (i == 0) {
                return (StreamObserver<Req>) this.serviceImpl.streamAggregatedResources(streamObserver);
            }
            if (i == 1) {
                return (StreamObserver<Req>) this.serviceImpl.deltaAggregatedResources(streamObserver);
            }
            throw new AssertionError();
        }
    }

    private static abstract class AggregatedDiscoveryServiceBaseDescriptorSupplier implements ProtoFileDescriptorSupplier, ProtoServiceDescriptorSupplier {
        AggregatedDiscoveryServiceBaseDescriptorSupplier() {
        }

        @Override // io.grpc.protobuf.ProtoFileDescriptorSupplier
        public Descriptors.FileDescriptor getFileDescriptor() {
            return AdsProto.getDescriptor();
        }

        @Override // io.grpc.protobuf.ProtoServiceDescriptorSupplier
        public Descriptors.ServiceDescriptor getServiceDescriptor() {
            return getFileDescriptor().findServiceByName("AggregatedDiscoveryService");
        }
    }

    private static final class AggregatedDiscoveryServiceFileDescriptorSupplier extends AggregatedDiscoveryServiceBaseDescriptorSupplier {
        AggregatedDiscoveryServiceFileDescriptorSupplier() {
        }
    }

    private static final class AggregatedDiscoveryServiceMethodDescriptorSupplier extends AggregatedDiscoveryServiceBaseDescriptorSupplier implements ProtoMethodDescriptorSupplier {
        private final String methodName;

        AggregatedDiscoveryServiceMethodDescriptorSupplier(String str) {
            this.methodName = str;
        }

        @Override // io.grpc.protobuf.ProtoMethodDescriptorSupplier
        public Descriptors.MethodDescriptor getMethodDescriptor() {
            return getServiceDescriptor().findMethodByName(this.methodName);
        }
    }
}
