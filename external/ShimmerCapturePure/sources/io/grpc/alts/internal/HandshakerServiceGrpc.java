package io.grpc.alts.internal;

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

/* loaded from: classes2.dex */
public final class HandshakerServiceGrpc {
    public static final String SERVICE_NAME = "grpc.gcp.HandshakerService";
    private static final int METHODID_DO_HANDSHAKE = 0;
    private static volatile MethodDescriptor<HandshakerReq, HandshakerResp> getDoHandshakeMethod;
    private static volatile ServiceDescriptor serviceDescriptor;

    private HandshakerServiceGrpc() {
    }

    public static MethodDescriptor<HandshakerReq, HandshakerResp> getDoHandshakeMethod() {
        MethodDescriptor<HandshakerReq, HandshakerResp> methodDescriptorBuild = getDoHandshakeMethod;
        if (methodDescriptorBuild == null) {
            synchronized (HandshakerServiceGrpc.class) {
                methodDescriptorBuild = getDoHandshakeMethod;
                if (methodDescriptorBuild == null) {
                    methodDescriptorBuild = MethodDescriptor.newBuilder().setType(MethodDescriptor.MethodType.BIDI_STREAMING).setFullMethodName(MethodDescriptor.generateFullMethodName(SERVICE_NAME, "DoHandshake")).setSampledToLocalTracing(true).setRequestMarshaller(ProtoUtils.marshaller(HandshakerReq.getDefaultInstance())).setResponseMarshaller(ProtoUtils.marshaller(HandshakerResp.getDefaultInstance())).setSchemaDescriptor(new HandshakerServiceMethodDescriptorSupplier("DoHandshake")).build();
                    getDoHandshakeMethod = methodDescriptorBuild;
                }
            }
        }
        return methodDescriptorBuild;
    }

    public static HandshakerServiceStub newStub(Channel channel) {
        return (HandshakerServiceStub) HandshakerServiceStub.newStub(new AbstractStub.StubFactory<HandshakerServiceStub>() { // from class: io.grpc.alts.internal.HandshakerServiceGrpc.1
            @Override // io.grpc.stub.AbstractStub.StubFactory
            public HandshakerServiceStub newStub(Channel channel2, CallOptions callOptions) {
                return new HandshakerServiceStub(channel2, callOptions);
            }
        }, channel);
    }

    public static HandshakerServiceBlockingStub newBlockingStub(Channel channel) {
        return (HandshakerServiceBlockingStub) HandshakerServiceBlockingStub.newStub(new AbstractStub.StubFactory<HandshakerServiceBlockingStub>() { // from class: io.grpc.alts.internal.HandshakerServiceGrpc.2
            @Override // io.grpc.stub.AbstractStub.StubFactory
            public HandshakerServiceBlockingStub newStub(Channel channel2, CallOptions callOptions) {
                return new HandshakerServiceBlockingStub(channel2, callOptions);
            }
        }, channel);
    }

    public static HandshakerServiceFutureStub newFutureStub(Channel channel) {
        return (HandshakerServiceFutureStub) HandshakerServiceFutureStub.newStub(new AbstractStub.StubFactory<HandshakerServiceFutureStub>() { // from class: io.grpc.alts.internal.HandshakerServiceGrpc.3
            @Override // io.grpc.stub.AbstractStub.StubFactory
            public HandshakerServiceFutureStub newStub(Channel channel2, CallOptions callOptions) {
                return new HandshakerServiceFutureStub(channel2, callOptions);
            }
        }, channel);
    }

    public static ServiceDescriptor getServiceDescriptor() {
        ServiceDescriptor serviceDescriptorBuild = serviceDescriptor;
        if (serviceDescriptorBuild == null) {
            synchronized (HandshakerServiceGrpc.class) {
                serviceDescriptorBuild = serviceDescriptor;
                if (serviceDescriptorBuild == null) {
                    serviceDescriptorBuild = ServiceDescriptor.newBuilder(SERVICE_NAME).setSchemaDescriptor(new HandshakerServiceFileDescriptorSupplier()).addMethod(getDoHandshakeMethod()).build();
                    serviceDescriptor = serviceDescriptorBuild;
                }
            }
        }
        return serviceDescriptorBuild;
    }

    public static abstract class HandshakerServiceImplBase implements BindableService {
        public StreamObserver<HandshakerReq> doHandshake(StreamObserver<HandshakerResp> streamObserver) {
            return ServerCalls.asyncUnimplementedStreamingCall(HandshakerServiceGrpc.getDoHandshakeMethod(), streamObserver);
        }

        @Override // io.grpc.BindableService
        public final ServerServiceDefinition bindService() {
            return ServerServiceDefinition.builder(HandshakerServiceGrpc.getServiceDescriptor()).addMethod(HandshakerServiceGrpc.getDoHandshakeMethod(), ServerCalls.asyncBidiStreamingCall(new MethodHandlers(this, 0))).build();
        }
    }

    public static final class HandshakerServiceStub extends AbstractAsyncStub<HandshakerServiceStub> {
        private HandshakerServiceStub(Channel channel, CallOptions callOptions) {
            super(channel, callOptions);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // io.grpc.stub.AbstractStub
        public HandshakerServiceStub build(Channel channel, CallOptions callOptions) {
            return new HandshakerServiceStub(channel, callOptions);
        }

        public StreamObserver<HandshakerReq> doHandshake(StreamObserver<HandshakerResp> streamObserver) {
            return ClientCalls.asyncBidiStreamingCall(getChannel().newCall(HandshakerServiceGrpc.getDoHandshakeMethod(), getCallOptions()), streamObserver);
        }
    }

    public static final class HandshakerServiceBlockingStub extends AbstractBlockingStub<HandshakerServiceBlockingStub> {
        private HandshakerServiceBlockingStub(Channel channel, CallOptions callOptions) {
            super(channel, callOptions);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // io.grpc.stub.AbstractStub
        public HandshakerServiceBlockingStub build(Channel channel, CallOptions callOptions) {
            return new HandshakerServiceBlockingStub(channel, callOptions);
        }
    }

    public static final class HandshakerServiceFutureStub extends AbstractFutureStub<HandshakerServiceFutureStub> {
        private HandshakerServiceFutureStub(Channel channel, CallOptions callOptions) {
            super(channel, callOptions);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // io.grpc.stub.AbstractStub
        public HandshakerServiceFutureStub build(Channel channel, CallOptions callOptions) {
            return new HandshakerServiceFutureStub(channel, callOptions);
        }
    }

    private static final class MethodHandlers<Req, Resp> implements ServerCalls.UnaryMethod<Req, Resp>, ServerCalls.ServerStreamingMethod<Req, Resp>, ServerCalls.ClientStreamingMethod<Req, Resp>, ServerCalls.BidiStreamingMethod<Req, Resp> {
        private final int methodId;
        private final HandshakerServiceImplBase serviceImpl;

        MethodHandlers(HandshakerServiceImplBase handshakerServiceImplBase, int i) {
            this.serviceImpl = handshakerServiceImplBase;
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
                return (StreamObserver<Req>) this.serviceImpl.doHandshake(streamObserver);
            }
            throw new AssertionError();
        }
    }

    private static abstract class HandshakerServiceBaseDescriptorSupplier implements ProtoFileDescriptorSupplier, ProtoServiceDescriptorSupplier {
        HandshakerServiceBaseDescriptorSupplier() {
        }

        @Override // io.grpc.protobuf.ProtoFileDescriptorSupplier
        public Descriptors.FileDescriptor getFileDescriptor() {
            return HandshakerProto.getDescriptor();
        }

        @Override // io.grpc.protobuf.ProtoServiceDescriptorSupplier
        public Descriptors.ServiceDescriptor getServiceDescriptor() {
            return getFileDescriptor().findServiceByName("HandshakerService");
        }
    }

    private static final class HandshakerServiceFileDescriptorSupplier extends HandshakerServiceBaseDescriptorSupplier {
        HandshakerServiceFileDescriptorSupplier() {
        }
    }

    private static final class HandshakerServiceMethodDescriptorSupplier extends HandshakerServiceBaseDescriptorSupplier implements ProtoMethodDescriptorSupplier {
        private final String methodName;

        HandshakerServiceMethodDescriptorSupplier(String str) {
            this.methodName = str;
        }

        @Override // io.grpc.protobuf.ProtoMethodDescriptorSupplier
        public Descriptors.MethodDescriptor getMethodDescriptor() {
            return getServiceDescriptor().findMethodByName(this.methodName);
        }
    }
}
