package io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2;

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
public final class VirtualHostDiscoveryServiceGrpc {
    public static final String SERVICE_NAME = "envoy.api.v2.VirtualHostDiscoveryService";
    private static final int METHODID_DELTA_VIRTUAL_HOSTS = 0;
    private static volatile MethodDescriptor<DeltaDiscoveryRequest, DeltaDiscoveryResponse> getDeltaVirtualHostsMethod;
    private static volatile ServiceDescriptor serviceDescriptor;

    private VirtualHostDiscoveryServiceGrpc() {
    }

    public static MethodDescriptor<DeltaDiscoveryRequest, DeltaDiscoveryResponse> getDeltaVirtualHostsMethod() {
        MethodDescriptor<DeltaDiscoveryRequest, DeltaDiscoveryResponse> methodDescriptorBuild = getDeltaVirtualHostsMethod;
        if (methodDescriptorBuild == null) {
            synchronized (VirtualHostDiscoveryServiceGrpc.class) {
                methodDescriptorBuild = getDeltaVirtualHostsMethod;
                if (methodDescriptorBuild == null) {
                    methodDescriptorBuild = MethodDescriptor.newBuilder().setType(MethodDescriptor.MethodType.BIDI_STREAMING).setFullMethodName(MethodDescriptor.generateFullMethodName(SERVICE_NAME, "DeltaVirtualHosts")).setSampledToLocalTracing(true).setRequestMarshaller(ProtoUtils.marshaller(DeltaDiscoveryRequest.getDefaultInstance())).setResponseMarshaller(ProtoUtils.marshaller(DeltaDiscoveryResponse.getDefaultInstance())).setSchemaDescriptor(new VirtualHostDiscoveryServiceMethodDescriptorSupplier("DeltaVirtualHosts")).build();
                    getDeltaVirtualHostsMethod = methodDescriptorBuild;
                }
            }
        }
        return methodDescriptorBuild;
    }

    public static VirtualHostDiscoveryServiceStub newStub(Channel channel) {
        return (VirtualHostDiscoveryServiceStub) VirtualHostDiscoveryServiceStub.newStub(new AbstractStub.StubFactory<VirtualHostDiscoveryServiceStub>() { // from class: io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.VirtualHostDiscoveryServiceGrpc.1
            @Override // io.grpc.stub.AbstractStub.StubFactory
            public VirtualHostDiscoveryServiceStub newStub(Channel channel2, CallOptions callOptions) {
                return new VirtualHostDiscoveryServiceStub(channel2, callOptions);
            }
        }, channel);
    }

    public static VirtualHostDiscoveryServiceBlockingStub newBlockingStub(Channel channel) {
        return (VirtualHostDiscoveryServiceBlockingStub) VirtualHostDiscoveryServiceBlockingStub.newStub(new AbstractStub.StubFactory<VirtualHostDiscoveryServiceBlockingStub>() { // from class: io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.VirtualHostDiscoveryServiceGrpc.2
            @Override // io.grpc.stub.AbstractStub.StubFactory
            public VirtualHostDiscoveryServiceBlockingStub newStub(Channel channel2, CallOptions callOptions) {
                return new VirtualHostDiscoveryServiceBlockingStub(channel2, callOptions);
            }
        }, channel);
    }

    public static VirtualHostDiscoveryServiceFutureStub newFutureStub(Channel channel) {
        return (VirtualHostDiscoveryServiceFutureStub) VirtualHostDiscoveryServiceFutureStub.newStub(new AbstractStub.StubFactory<VirtualHostDiscoveryServiceFutureStub>() { // from class: io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.VirtualHostDiscoveryServiceGrpc.3
            @Override // io.grpc.stub.AbstractStub.StubFactory
            public VirtualHostDiscoveryServiceFutureStub newStub(Channel channel2, CallOptions callOptions) {
                return new VirtualHostDiscoveryServiceFutureStub(channel2, callOptions);
            }
        }, channel);
    }

    public static ServiceDescriptor getServiceDescriptor() {
        ServiceDescriptor serviceDescriptorBuild = serviceDescriptor;
        if (serviceDescriptorBuild == null) {
            synchronized (VirtualHostDiscoveryServiceGrpc.class) {
                serviceDescriptorBuild = serviceDescriptor;
                if (serviceDescriptorBuild == null) {
                    serviceDescriptorBuild = ServiceDescriptor.newBuilder(SERVICE_NAME).setSchemaDescriptor(new VirtualHostDiscoveryServiceFileDescriptorSupplier()).addMethod(getDeltaVirtualHostsMethod()).build();
                    serviceDescriptor = serviceDescriptorBuild;
                }
            }
        }
        return serviceDescriptorBuild;
    }

    public static abstract class VirtualHostDiscoveryServiceImplBase implements BindableService {
        public StreamObserver<DeltaDiscoveryRequest> deltaVirtualHosts(StreamObserver<DeltaDiscoveryResponse> streamObserver) {
            return ServerCalls.asyncUnimplementedStreamingCall(VirtualHostDiscoveryServiceGrpc.getDeltaVirtualHostsMethod(), streamObserver);
        }

        @Override // io.grpc.BindableService
        public final ServerServiceDefinition bindService() {
            return ServerServiceDefinition.builder(VirtualHostDiscoveryServiceGrpc.getServiceDescriptor()).addMethod(VirtualHostDiscoveryServiceGrpc.getDeltaVirtualHostsMethod(), ServerCalls.asyncBidiStreamingCall(new MethodHandlers(this, 0))).build();
        }
    }

    public static final class VirtualHostDiscoveryServiceStub extends AbstractAsyncStub<VirtualHostDiscoveryServiceStub> {
        private VirtualHostDiscoveryServiceStub(Channel channel, CallOptions callOptions) {
            super(channel, callOptions);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // io.grpc.stub.AbstractStub
        public VirtualHostDiscoveryServiceStub build(Channel channel, CallOptions callOptions) {
            return new VirtualHostDiscoveryServiceStub(channel, callOptions);
        }

        public StreamObserver<DeltaDiscoveryRequest> deltaVirtualHosts(StreamObserver<DeltaDiscoveryResponse> streamObserver) {
            return ClientCalls.asyncBidiStreamingCall(getChannel().newCall(VirtualHostDiscoveryServiceGrpc.getDeltaVirtualHostsMethod(), getCallOptions()), streamObserver);
        }
    }

    public static final class VirtualHostDiscoveryServiceBlockingStub extends AbstractBlockingStub<VirtualHostDiscoveryServiceBlockingStub> {
        private VirtualHostDiscoveryServiceBlockingStub(Channel channel, CallOptions callOptions) {
            super(channel, callOptions);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // io.grpc.stub.AbstractStub
        public VirtualHostDiscoveryServiceBlockingStub build(Channel channel, CallOptions callOptions) {
            return new VirtualHostDiscoveryServiceBlockingStub(channel, callOptions);
        }
    }

    public static final class VirtualHostDiscoveryServiceFutureStub extends AbstractFutureStub<VirtualHostDiscoveryServiceFutureStub> {
        private VirtualHostDiscoveryServiceFutureStub(Channel channel, CallOptions callOptions) {
            super(channel, callOptions);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // io.grpc.stub.AbstractStub
        public VirtualHostDiscoveryServiceFutureStub build(Channel channel, CallOptions callOptions) {
            return new VirtualHostDiscoveryServiceFutureStub(channel, callOptions);
        }
    }

    private static final class MethodHandlers<Req, Resp> implements ServerCalls.UnaryMethod<Req, Resp>, ServerCalls.ServerStreamingMethod<Req, Resp>, ServerCalls.ClientStreamingMethod<Req, Resp>, ServerCalls.BidiStreamingMethod<Req, Resp> {
        private final int methodId;
        private final VirtualHostDiscoveryServiceImplBase serviceImpl;

        MethodHandlers(VirtualHostDiscoveryServiceImplBase virtualHostDiscoveryServiceImplBase, int i) {
            this.serviceImpl = virtualHostDiscoveryServiceImplBase;
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
                return (StreamObserver<Req>) this.serviceImpl.deltaVirtualHosts(streamObserver);
            }
            throw new AssertionError();
        }
    }

    private static abstract class VirtualHostDiscoveryServiceBaseDescriptorSupplier implements ProtoFileDescriptorSupplier, ProtoServiceDescriptorSupplier {
        VirtualHostDiscoveryServiceBaseDescriptorSupplier() {
        }

        @Override // io.grpc.protobuf.ProtoFileDescriptorSupplier
        public Descriptors.FileDescriptor getFileDescriptor() {
            return RdsProto.getDescriptor();
        }

        @Override // io.grpc.protobuf.ProtoServiceDescriptorSupplier
        public Descriptors.ServiceDescriptor getServiceDescriptor() {
            return getFileDescriptor().findServiceByName("VirtualHostDiscoveryService");
        }
    }

    private static final class VirtualHostDiscoveryServiceFileDescriptorSupplier extends VirtualHostDiscoveryServiceBaseDescriptorSupplier {
        VirtualHostDiscoveryServiceFileDescriptorSupplier() {
        }
    }

    private static final class VirtualHostDiscoveryServiceMethodDescriptorSupplier extends VirtualHostDiscoveryServiceBaseDescriptorSupplier implements ProtoMethodDescriptorSupplier {
        private final String methodName;

        VirtualHostDiscoveryServiceMethodDescriptorSupplier(String str) {
            this.methodName = str;
        }

        @Override // io.grpc.protobuf.ProtoMethodDescriptorSupplier
        public Descriptors.MethodDescriptor getMethodDescriptor() {
            return getServiceDescriptor().findMethodByName(this.methodName);
        }
    }
}
