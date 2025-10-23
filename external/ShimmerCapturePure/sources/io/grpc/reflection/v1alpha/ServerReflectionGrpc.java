package io.grpc.reflection.v1alpha;

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
public final class ServerReflectionGrpc {
    public static final String SERVICE_NAME = "grpc.reflection.v1alpha.ServerReflection";
    private static final int METHODID_SERVER_REFLECTION_INFO = 0;
    private static volatile MethodDescriptor<ServerReflectionRequest, ServerReflectionResponse> getServerReflectionInfoMethod;
    private static volatile ServiceDescriptor serviceDescriptor;

    private ServerReflectionGrpc() {
    }

    public static MethodDescriptor<ServerReflectionRequest, ServerReflectionResponse> getServerReflectionInfoMethod() {
        MethodDescriptor<ServerReflectionRequest, ServerReflectionResponse> methodDescriptorBuild = getServerReflectionInfoMethod;
        if (methodDescriptorBuild == null) {
            synchronized (ServerReflectionGrpc.class) {
                methodDescriptorBuild = getServerReflectionInfoMethod;
                if (methodDescriptorBuild == null) {
                    methodDescriptorBuild = MethodDescriptor.newBuilder().setType(MethodDescriptor.MethodType.BIDI_STREAMING).setFullMethodName(MethodDescriptor.generateFullMethodName(SERVICE_NAME, "ServerReflectionInfo")).setSampledToLocalTracing(true).setRequestMarshaller(ProtoUtils.marshaller(ServerReflectionRequest.getDefaultInstance())).setResponseMarshaller(ProtoUtils.marshaller(ServerReflectionResponse.getDefaultInstance())).setSchemaDescriptor(new ServerReflectionMethodDescriptorSupplier("ServerReflectionInfo")).build();
                    getServerReflectionInfoMethod = methodDescriptorBuild;
                }
            }
        }
        return methodDescriptorBuild;
    }

    public static ServerReflectionStub newStub(Channel channel) {
        return (ServerReflectionStub) ServerReflectionStub.newStub(new AbstractStub.StubFactory<ServerReflectionStub>() { // from class: io.grpc.reflection.v1alpha.ServerReflectionGrpc.1
            @Override // io.grpc.stub.AbstractStub.StubFactory
            public ServerReflectionStub newStub(Channel channel2, CallOptions callOptions) {
                return new ServerReflectionStub(channel2, callOptions);
            }
        }, channel);
    }

    public static ServerReflectionBlockingStub newBlockingStub(Channel channel) {
        return (ServerReflectionBlockingStub) ServerReflectionBlockingStub.newStub(new AbstractStub.StubFactory<ServerReflectionBlockingStub>() { // from class: io.grpc.reflection.v1alpha.ServerReflectionGrpc.2
            @Override // io.grpc.stub.AbstractStub.StubFactory
            public ServerReflectionBlockingStub newStub(Channel channel2, CallOptions callOptions) {
                return new ServerReflectionBlockingStub(channel2, callOptions);
            }
        }, channel);
    }

    public static ServerReflectionFutureStub newFutureStub(Channel channel) {
        return (ServerReflectionFutureStub) ServerReflectionFutureStub.newStub(new AbstractStub.StubFactory<ServerReflectionFutureStub>() { // from class: io.grpc.reflection.v1alpha.ServerReflectionGrpc.3
            @Override // io.grpc.stub.AbstractStub.StubFactory
            public ServerReflectionFutureStub newStub(Channel channel2, CallOptions callOptions) {
                return new ServerReflectionFutureStub(channel2, callOptions);
            }
        }, channel);
    }

    public static ServiceDescriptor getServiceDescriptor() {
        ServiceDescriptor serviceDescriptorBuild = serviceDescriptor;
        if (serviceDescriptorBuild == null) {
            synchronized (ServerReflectionGrpc.class) {
                serviceDescriptorBuild = serviceDescriptor;
                if (serviceDescriptorBuild == null) {
                    serviceDescriptorBuild = ServiceDescriptor.newBuilder(SERVICE_NAME).setSchemaDescriptor(new ServerReflectionFileDescriptorSupplier()).addMethod(getServerReflectionInfoMethod()).build();
                    serviceDescriptor = serviceDescriptorBuild;
                }
            }
        }
        return serviceDescriptorBuild;
    }

    public static abstract class ServerReflectionImplBase implements BindableService {
        public StreamObserver<ServerReflectionRequest> serverReflectionInfo(StreamObserver<ServerReflectionResponse> streamObserver) {
            return ServerCalls.asyncUnimplementedStreamingCall(ServerReflectionGrpc.getServerReflectionInfoMethod(), streamObserver);
        }

        @Override // io.grpc.BindableService
        public final ServerServiceDefinition bindService() {
            return ServerServiceDefinition.builder(ServerReflectionGrpc.getServiceDescriptor()).addMethod(ServerReflectionGrpc.getServerReflectionInfoMethod(), ServerCalls.asyncBidiStreamingCall(new MethodHandlers(this, 0))).build();
        }
    }

    public static final class ServerReflectionStub extends AbstractAsyncStub<ServerReflectionStub> {
        private ServerReflectionStub(Channel channel, CallOptions callOptions) {
            super(channel, callOptions);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // io.grpc.stub.AbstractStub
        public ServerReflectionStub build(Channel channel, CallOptions callOptions) {
            return new ServerReflectionStub(channel, callOptions);
        }

        public StreamObserver<ServerReflectionRequest> serverReflectionInfo(StreamObserver<ServerReflectionResponse> streamObserver) {
            return ClientCalls.asyncBidiStreamingCall(getChannel().newCall(ServerReflectionGrpc.getServerReflectionInfoMethod(), getCallOptions()), streamObserver);
        }
    }

    public static final class ServerReflectionBlockingStub extends AbstractBlockingStub<ServerReflectionBlockingStub> {
        private ServerReflectionBlockingStub(Channel channel, CallOptions callOptions) {
            super(channel, callOptions);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // io.grpc.stub.AbstractStub
        public ServerReflectionBlockingStub build(Channel channel, CallOptions callOptions) {
            return new ServerReflectionBlockingStub(channel, callOptions);
        }
    }

    public static final class ServerReflectionFutureStub extends AbstractFutureStub<ServerReflectionFutureStub> {
        private ServerReflectionFutureStub(Channel channel, CallOptions callOptions) {
            super(channel, callOptions);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // io.grpc.stub.AbstractStub
        public ServerReflectionFutureStub build(Channel channel, CallOptions callOptions) {
            return new ServerReflectionFutureStub(channel, callOptions);
        }
    }

    private static final class MethodHandlers<Req, Resp> implements ServerCalls.UnaryMethod<Req, Resp>, ServerCalls.ServerStreamingMethod<Req, Resp>, ServerCalls.ClientStreamingMethod<Req, Resp>, ServerCalls.BidiStreamingMethod<Req, Resp> {
        private final int methodId;
        private final ServerReflectionImplBase serviceImpl;

        MethodHandlers(ServerReflectionImplBase serverReflectionImplBase, int i) {
            this.serviceImpl = serverReflectionImplBase;
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
                return (StreamObserver<Req>) this.serviceImpl.serverReflectionInfo(streamObserver);
            }
            throw new AssertionError();
        }
    }

    private static abstract class ServerReflectionBaseDescriptorSupplier implements ProtoFileDescriptorSupplier, ProtoServiceDescriptorSupplier {
        ServerReflectionBaseDescriptorSupplier() {
        }

        @Override // io.grpc.protobuf.ProtoFileDescriptorSupplier
        public Descriptors.FileDescriptor getFileDescriptor() {
            return ServerReflectionProto.getDescriptor();
        }

        @Override // io.grpc.protobuf.ProtoServiceDescriptorSupplier
        public Descriptors.ServiceDescriptor getServiceDescriptor() {
            return getFileDescriptor().findServiceByName("ServerReflection");
        }
    }

    private static final class ServerReflectionFileDescriptorSupplier extends ServerReflectionBaseDescriptorSupplier {
        ServerReflectionFileDescriptorSupplier() {
        }
    }

    private static final class ServerReflectionMethodDescriptorSupplier extends ServerReflectionBaseDescriptorSupplier implements ProtoMethodDescriptorSupplier {
        private final String methodName;

        ServerReflectionMethodDescriptorSupplier(String str) {
            this.methodName = str;
        }

        @Override // io.grpc.protobuf.ProtoMethodDescriptorSupplier
        public Descriptors.MethodDescriptor getMethodDescriptor() {
            return getServiceDescriptor().findMethodByName(this.methodName);
        }
    }
}
