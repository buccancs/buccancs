package io.grpc.xds.shaded.io.envoyproxy.envoy.service.discovery.v2;

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
import io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.DeltaDiscoveryRequest;
import io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.DeltaDiscoveryResponse;
import io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.DiscoveryRequest;
import io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.DiscoveryResponse;

/* loaded from: classes4.dex */
public final class SecretDiscoveryServiceGrpc {
    public static final String SERVICE_NAME = "envoy.service.discovery.v2.SecretDiscoveryService";
    private static final int METHODID_DELTA_SECRETS = 1;
    private static final int METHODID_FETCH_SECRETS = 0;
    private static final int METHODID_STREAM_SECRETS = 2;
    private static volatile MethodDescriptor<DeltaDiscoveryRequest, DeltaDiscoveryResponse> getDeltaSecretsMethod;
    private static volatile MethodDescriptor<DiscoveryRequest, DiscoveryResponse> getFetchSecretsMethod;
    private static volatile MethodDescriptor<DiscoveryRequest, DiscoveryResponse> getStreamSecretsMethod;
    private static volatile ServiceDescriptor serviceDescriptor;

    private SecretDiscoveryServiceGrpc() {
    }

    public static MethodDescriptor<DeltaDiscoveryRequest, DeltaDiscoveryResponse> getDeltaSecretsMethod() {
        MethodDescriptor<DeltaDiscoveryRequest, DeltaDiscoveryResponse> methodDescriptorBuild = getDeltaSecretsMethod;
        if (methodDescriptorBuild == null) {
            synchronized (SecretDiscoveryServiceGrpc.class) {
                methodDescriptorBuild = getDeltaSecretsMethod;
                if (methodDescriptorBuild == null) {
                    methodDescriptorBuild = MethodDescriptor.newBuilder().setType(MethodDescriptor.MethodType.BIDI_STREAMING).setFullMethodName(MethodDescriptor.generateFullMethodName(SERVICE_NAME, "DeltaSecrets")).setSampledToLocalTracing(true).setRequestMarshaller(ProtoUtils.marshaller(DeltaDiscoveryRequest.getDefaultInstance())).setResponseMarshaller(ProtoUtils.marshaller(DeltaDiscoveryResponse.getDefaultInstance())).setSchemaDescriptor(new SecretDiscoveryServiceMethodDescriptorSupplier("DeltaSecrets")).build();
                    getDeltaSecretsMethod = methodDescriptorBuild;
                }
            }
        }
        return methodDescriptorBuild;
    }

    public static MethodDescriptor<DiscoveryRequest, DiscoveryResponse> getStreamSecretsMethod() {
        MethodDescriptor<DiscoveryRequest, DiscoveryResponse> methodDescriptorBuild = getStreamSecretsMethod;
        if (methodDescriptorBuild == null) {
            synchronized (SecretDiscoveryServiceGrpc.class) {
                methodDescriptorBuild = getStreamSecretsMethod;
                if (methodDescriptorBuild == null) {
                    methodDescriptorBuild = MethodDescriptor.newBuilder().setType(MethodDescriptor.MethodType.BIDI_STREAMING).setFullMethodName(MethodDescriptor.generateFullMethodName(SERVICE_NAME, "StreamSecrets")).setSampledToLocalTracing(true).setRequestMarshaller(ProtoUtils.marshaller(DiscoveryRequest.getDefaultInstance())).setResponseMarshaller(ProtoUtils.marshaller(DiscoveryResponse.getDefaultInstance())).setSchemaDescriptor(new SecretDiscoveryServiceMethodDescriptorSupplier("StreamSecrets")).build();
                    getStreamSecretsMethod = methodDescriptorBuild;
                }
            }
        }
        return methodDescriptorBuild;
    }

    public static MethodDescriptor<DiscoveryRequest, DiscoveryResponse> getFetchSecretsMethod() {
        MethodDescriptor<DiscoveryRequest, DiscoveryResponse> methodDescriptorBuild = getFetchSecretsMethod;
        if (methodDescriptorBuild == null) {
            synchronized (SecretDiscoveryServiceGrpc.class) {
                methodDescriptorBuild = getFetchSecretsMethod;
                if (methodDescriptorBuild == null) {
                    methodDescriptorBuild = MethodDescriptor.newBuilder().setType(MethodDescriptor.MethodType.UNARY).setFullMethodName(MethodDescriptor.generateFullMethodName(SERVICE_NAME, "FetchSecrets")).setSampledToLocalTracing(true).setRequestMarshaller(ProtoUtils.marshaller(DiscoveryRequest.getDefaultInstance())).setResponseMarshaller(ProtoUtils.marshaller(DiscoveryResponse.getDefaultInstance())).setSchemaDescriptor(new SecretDiscoveryServiceMethodDescriptorSupplier("FetchSecrets")).build();
                    getFetchSecretsMethod = methodDescriptorBuild;
                }
            }
        }
        return methodDescriptorBuild;
    }

    public static SecretDiscoveryServiceStub newStub(Channel channel) {
        return (SecretDiscoveryServiceStub) SecretDiscoveryServiceStub.newStub(new AbstractStub.StubFactory<SecretDiscoveryServiceStub>() { // from class: io.grpc.xds.shaded.io.envoyproxy.envoy.service.discovery.v2.SecretDiscoveryServiceGrpc.1
            @Override // io.grpc.stub.AbstractStub.StubFactory
            public SecretDiscoveryServiceStub newStub(Channel channel2, CallOptions callOptions) {
                return new SecretDiscoveryServiceStub(channel2, callOptions);
            }
        }, channel);
    }

    public static SecretDiscoveryServiceBlockingStub newBlockingStub(Channel channel) {
        return (SecretDiscoveryServiceBlockingStub) SecretDiscoveryServiceBlockingStub.newStub(new AbstractStub.StubFactory<SecretDiscoveryServiceBlockingStub>() { // from class: io.grpc.xds.shaded.io.envoyproxy.envoy.service.discovery.v2.SecretDiscoveryServiceGrpc.2
            @Override // io.grpc.stub.AbstractStub.StubFactory
            public SecretDiscoveryServiceBlockingStub newStub(Channel channel2, CallOptions callOptions) {
                return new SecretDiscoveryServiceBlockingStub(channel2, callOptions);
            }
        }, channel);
    }

    public static SecretDiscoveryServiceFutureStub newFutureStub(Channel channel) {
        return (SecretDiscoveryServiceFutureStub) SecretDiscoveryServiceFutureStub.newStub(new AbstractStub.StubFactory<SecretDiscoveryServiceFutureStub>() { // from class: io.grpc.xds.shaded.io.envoyproxy.envoy.service.discovery.v2.SecretDiscoveryServiceGrpc.3
            @Override // io.grpc.stub.AbstractStub.StubFactory
            public SecretDiscoveryServiceFutureStub newStub(Channel channel2, CallOptions callOptions) {
                return new SecretDiscoveryServiceFutureStub(channel2, callOptions);
            }
        }, channel);
    }

    public static ServiceDescriptor getServiceDescriptor() {
        ServiceDescriptor serviceDescriptorBuild = serviceDescriptor;
        if (serviceDescriptorBuild == null) {
            synchronized (SecretDiscoveryServiceGrpc.class) {
                serviceDescriptorBuild = serviceDescriptor;
                if (serviceDescriptorBuild == null) {
                    serviceDescriptorBuild = ServiceDescriptor.newBuilder(SERVICE_NAME).setSchemaDescriptor(new SecretDiscoveryServiceFileDescriptorSupplier()).addMethod(getDeltaSecretsMethod()).addMethod(getStreamSecretsMethod()).addMethod(getFetchSecretsMethod()).build();
                    serviceDescriptor = serviceDescriptorBuild;
                }
            }
        }
        return serviceDescriptorBuild;
    }

    public static abstract class SecretDiscoveryServiceImplBase implements BindableService {
        public StreamObserver<DeltaDiscoveryRequest> deltaSecrets(StreamObserver<DeltaDiscoveryResponse> streamObserver) {
            return ServerCalls.asyncUnimplementedStreamingCall(SecretDiscoveryServiceGrpc.getDeltaSecretsMethod(), streamObserver);
        }

        public StreamObserver<DiscoveryRequest> streamSecrets(StreamObserver<DiscoveryResponse> streamObserver) {
            return ServerCalls.asyncUnimplementedStreamingCall(SecretDiscoveryServiceGrpc.getStreamSecretsMethod(), streamObserver);
        }

        public void fetchSecrets(DiscoveryRequest discoveryRequest, StreamObserver<DiscoveryResponse> streamObserver) {
            ServerCalls.asyncUnimplementedUnaryCall(SecretDiscoveryServiceGrpc.getFetchSecretsMethod(), streamObserver);
        }

        @Override // io.grpc.BindableService
        public final ServerServiceDefinition bindService() {
            return ServerServiceDefinition.builder(SecretDiscoveryServiceGrpc.getServiceDescriptor()).addMethod(SecretDiscoveryServiceGrpc.getDeltaSecretsMethod(), ServerCalls.asyncBidiStreamingCall(new MethodHandlers(this, 1))).addMethod(SecretDiscoveryServiceGrpc.getStreamSecretsMethod(), ServerCalls.asyncBidiStreamingCall(new MethodHandlers(this, 2))).addMethod(SecretDiscoveryServiceGrpc.getFetchSecretsMethod(), ServerCalls.asyncUnaryCall(new MethodHandlers(this, 0))).build();
        }
    }

    public static final class SecretDiscoveryServiceStub extends AbstractAsyncStub<SecretDiscoveryServiceStub> {
        private SecretDiscoveryServiceStub(Channel channel, CallOptions callOptions) {
            super(channel, callOptions);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // io.grpc.stub.AbstractStub
        public SecretDiscoveryServiceStub build(Channel channel, CallOptions callOptions) {
            return new SecretDiscoveryServiceStub(channel, callOptions);
        }

        public StreamObserver<DeltaDiscoveryRequest> deltaSecrets(StreamObserver<DeltaDiscoveryResponse> streamObserver) {
            return ClientCalls.asyncBidiStreamingCall(getChannel().newCall(SecretDiscoveryServiceGrpc.getDeltaSecretsMethod(), getCallOptions()), streamObserver);
        }

        public StreamObserver<DiscoveryRequest> streamSecrets(StreamObserver<DiscoveryResponse> streamObserver) {
            return ClientCalls.asyncBidiStreamingCall(getChannel().newCall(SecretDiscoveryServiceGrpc.getStreamSecretsMethod(), getCallOptions()), streamObserver);
        }

        public void fetchSecrets(DiscoveryRequest discoveryRequest, StreamObserver<DiscoveryResponse> streamObserver) {
            ClientCalls.asyncUnaryCall(getChannel().newCall(SecretDiscoveryServiceGrpc.getFetchSecretsMethod(), getCallOptions()), discoveryRequest, streamObserver);
        }
    }

    public static final class SecretDiscoveryServiceBlockingStub extends AbstractBlockingStub<SecretDiscoveryServiceBlockingStub> {
        private SecretDiscoveryServiceBlockingStub(Channel channel, CallOptions callOptions) {
            super(channel, callOptions);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // io.grpc.stub.AbstractStub
        public SecretDiscoveryServiceBlockingStub build(Channel channel, CallOptions callOptions) {
            return new SecretDiscoveryServiceBlockingStub(channel, callOptions);
        }

        public DiscoveryResponse fetchSecrets(DiscoveryRequest discoveryRequest) {
            return (DiscoveryResponse) ClientCalls.blockingUnaryCall(getChannel(), SecretDiscoveryServiceGrpc.getFetchSecretsMethod(), getCallOptions(), discoveryRequest);
        }
    }

    public static final class SecretDiscoveryServiceFutureStub extends AbstractFutureStub<SecretDiscoveryServiceFutureStub> {
        private SecretDiscoveryServiceFutureStub(Channel channel, CallOptions callOptions) {
            super(channel, callOptions);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // io.grpc.stub.AbstractStub
        public SecretDiscoveryServiceFutureStub build(Channel channel, CallOptions callOptions) {
            return new SecretDiscoveryServiceFutureStub(channel, callOptions);
        }

        public ListenableFuture<DiscoveryResponse> fetchSecrets(DiscoveryRequest discoveryRequest) {
            return ClientCalls.futureUnaryCall(getChannel().newCall(SecretDiscoveryServiceGrpc.getFetchSecretsMethod(), getCallOptions()), discoveryRequest);
        }
    }

    private static final class MethodHandlers<Req, Resp> implements ServerCalls.UnaryMethod<Req, Resp>, ServerCalls.ServerStreamingMethod<Req, Resp>, ServerCalls.ClientStreamingMethod<Req, Resp>, ServerCalls.BidiStreamingMethod<Req, Resp> {
        private final int methodId;
        private final SecretDiscoveryServiceImplBase serviceImpl;

        MethodHandlers(SecretDiscoveryServiceImplBase secretDiscoveryServiceImplBase, int i) {
            this.serviceImpl = secretDiscoveryServiceImplBase;
            this.methodId = i;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override
        // io.grpc.stub.ServerCalls.UnaryMethod, io.grpc.stub.ServerCalls.UnaryRequestMethod, io.grpc.stub.ServerCalls.ServerStreamingMethod
        public void invoke(Req req, StreamObserver<Resp> streamObserver) {
            if (this.methodId == 0) {
                this.serviceImpl.fetchSecrets((DiscoveryRequest) req, streamObserver);
                return;
            }
            throw new AssertionError();
        }

        @Override
        // io.grpc.stub.ServerCalls.ClientStreamingMethod, io.grpc.stub.ServerCalls.StreamingRequestMethod, io.grpc.stub.ServerCalls.BidiStreamingMethod
        public StreamObserver<Req> invoke(StreamObserver<Resp> streamObserver) {
            int i = this.methodId;
            if (i == 1) {
                return (StreamObserver<Req>) this.serviceImpl.deltaSecrets(streamObserver);
            }
            if (i == 2) {
                return (StreamObserver<Req>) this.serviceImpl.streamSecrets(streamObserver);
            }
            throw new AssertionError();
        }
    }

    private static abstract class SecretDiscoveryServiceBaseDescriptorSupplier implements ProtoFileDescriptorSupplier, ProtoServiceDescriptorSupplier {
        SecretDiscoveryServiceBaseDescriptorSupplier() {
        }

        @Override // io.grpc.protobuf.ProtoFileDescriptorSupplier
        public Descriptors.FileDescriptor getFileDescriptor() {
            return SdsProto.getDescriptor();
        }

        @Override // io.grpc.protobuf.ProtoServiceDescriptorSupplier
        public Descriptors.ServiceDescriptor getServiceDescriptor() {
            return getFileDescriptor().findServiceByName("SecretDiscoveryService");
        }
    }

    private static final class SecretDiscoveryServiceFileDescriptorSupplier extends SecretDiscoveryServiceBaseDescriptorSupplier {
        SecretDiscoveryServiceFileDescriptorSupplier() {
        }
    }

    private static final class SecretDiscoveryServiceMethodDescriptorSupplier extends SecretDiscoveryServiceBaseDescriptorSupplier implements ProtoMethodDescriptorSupplier {
        private final String methodName;

        SecretDiscoveryServiceMethodDescriptorSupplier(String str) {
            this.methodName = str;
        }

        @Override // io.grpc.protobuf.ProtoMethodDescriptorSupplier
        public Descriptors.MethodDescriptor getMethodDescriptor() {
            return getServiceDescriptor().findMethodByName(this.methodName);
        }
    }
}
