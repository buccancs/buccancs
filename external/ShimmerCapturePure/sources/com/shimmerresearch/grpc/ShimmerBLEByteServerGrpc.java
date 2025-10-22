package com.shimmerresearch.grpc;

import com.google.common.util.concurrent.ListenableFuture;
import com.google.protobuf.Descriptors;
import com.shimmerresearch.grpc.ShimmerBLEGRPC;
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

import java.util.Iterator;

/* loaded from: classes2.dex */
public final class ShimmerBLEByteServerGrpc {
    public static final String SERVICE_NAME = "shimmerBLEGRPC.ShimmerBLEByteServer";
    private static final int METHODID_CONNECT_SHIMMER = 2;
    private static final int METHODID_DISCONNECT_SHIMMER = 3;
    private static final int METHODID_GET_DATA_STREAM = 0;
    private static final int METHODID_GET_TEST_DATA_STREAM = 1;
    private static final int METHODID_SAY_HELLO = 5;
    private static final int METHODID_SEND_DATA_STREAM = 6;
    private static final int METHODID_WRITE_BYTES_SHIMMER = 4;
    private static volatile MethodDescriptor<ShimmerBLEGRPC.Request, ShimmerBLEGRPC.StateStatus> getConnectShimmerMethod;
    private static volatile MethodDescriptor<ShimmerBLEGRPC.Request, ShimmerBLEGRPC.Reply> getDisconnectShimmerMethod;
    private static volatile MethodDescriptor<ShimmerBLEGRPC.StreamRequest, ShimmerBLEGRPC.ObjectClusterByteArray> getGetDataStreamMethod;
    private static volatile MethodDescriptor<ShimmerBLEGRPC.StreamRequest, ShimmerBLEGRPC.ObjectClusterByteArray> getGetTestDataStreamMethod;
    private static volatile MethodDescriptor<ShimmerBLEGRPC.Request, ShimmerBLEGRPC.Reply> getSayHelloMethod;
    private static volatile MethodDescriptor<ShimmerBLEGRPC.ObjectClusterByteArray, ShimmerBLEGRPC.Reply> getSendDataStreamMethod;
    private static volatile MethodDescriptor<ShimmerBLEGRPC.WriteBytes, ShimmerBLEGRPC.Reply> getWriteBytesShimmerMethod;
    private static volatile ServiceDescriptor serviceDescriptor;

    private ShimmerBLEByteServerGrpc() {
    }

    public static MethodDescriptor<ShimmerBLEGRPC.StreamRequest, ShimmerBLEGRPC.ObjectClusterByteArray> getGetDataStreamMethod() {
        MethodDescriptor<ShimmerBLEGRPC.StreamRequest, ShimmerBLEGRPC.ObjectClusterByteArray> methodDescriptorBuild = getGetDataStreamMethod;
        if (methodDescriptorBuild == null) {
            synchronized (ShimmerBLEByteServerGrpc.class) {
                methodDescriptorBuild = getGetDataStreamMethod;
                if (methodDescriptorBuild == null) {
                    methodDescriptorBuild = MethodDescriptor.newBuilder().setType(MethodDescriptor.MethodType.SERVER_STREAMING).setFullMethodName(MethodDescriptor.generateFullMethodName(SERVICE_NAME, "GetDataStream")).setSampledToLocalTracing(true).setRequestMarshaller(ProtoUtils.marshaller(ShimmerBLEGRPC.StreamRequest.getDefaultInstance())).setResponseMarshaller(ProtoUtils.marshaller(ShimmerBLEGRPC.ObjectClusterByteArray.getDefaultInstance())).setSchemaDescriptor(new ShimmerBLEByteServerMethodDescriptorSupplier("GetDataStream")).build();
                    getGetDataStreamMethod = methodDescriptorBuild;
                }
            }
        }
        return methodDescriptorBuild;
    }

    public static MethodDescriptor<ShimmerBLEGRPC.StreamRequest, ShimmerBLEGRPC.ObjectClusterByteArray> getGetTestDataStreamMethod() {
        MethodDescriptor<ShimmerBLEGRPC.StreamRequest, ShimmerBLEGRPC.ObjectClusterByteArray> methodDescriptorBuild = getGetTestDataStreamMethod;
        if (methodDescriptorBuild == null) {
            synchronized (ShimmerBLEByteServerGrpc.class) {
                methodDescriptorBuild = getGetTestDataStreamMethod;
                if (methodDescriptorBuild == null) {
                    methodDescriptorBuild = MethodDescriptor.newBuilder().setType(MethodDescriptor.MethodType.SERVER_STREAMING).setFullMethodName(MethodDescriptor.generateFullMethodName(SERVICE_NAME, "GetTestDataStream")).setSampledToLocalTracing(true).setRequestMarshaller(ProtoUtils.marshaller(ShimmerBLEGRPC.StreamRequest.getDefaultInstance())).setResponseMarshaller(ProtoUtils.marshaller(ShimmerBLEGRPC.ObjectClusterByteArray.getDefaultInstance())).setSchemaDescriptor(new ShimmerBLEByteServerMethodDescriptorSupplier("GetTestDataStream")).build();
                    getGetTestDataStreamMethod = methodDescriptorBuild;
                }
            }
        }
        return methodDescriptorBuild;
    }

    public static MethodDescriptor<ShimmerBLEGRPC.ObjectClusterByteArray, ShimmerBLEGRPC.Reply> getSendDataStreamMethod() {
        MethodDescriptor<ShimmerBLEGRPC.ObjectClusterByteArray, ShimmerBLEGRPC.Reply> methodDescriptorBuild = getSendDataStreamMethod;
        if (methodDescriptorBuild == null) {
            synchronized (ShimmerBLEByteServerGrpc.class) {
                methodDescriptorBuild = getSendDataStreamMethod;
                if (methodDescriptorBuild == null) {
                    methodDescriptorBuild = MethodDescriptor.newBuilder().setType(MethodDescriptor.MethodType.CLIENT_STREAMING).setFullMethodName(MethodDescriptor.generateFullMethodName(SERVICE_NAME, "SendDataStream")).setSampledToLocalTracing(true).setRequestMarshaller(ProtoUtils.marshaller(ShimmerBLEGRPC.ObjectClusterByteArray.getDefaultInstance())).setResponseMarshaller(ProtoUtils.marshaller(ShimmerBLEGRPC.Reply.getDefaultInstance())).setSchemaDescriptor(new ShimmerBLEByteServerMethodDescriptorSupplier("SendDataStream")).build();
                    getSendDataStreamMethod = methodDescriptorBuild;
                }
            }
        }
        return methodDescriptorBuild;
    }

    public static MethodDescriptor<ShimmerBLEGRPC.Request, ShimmerBLEGRPC.StateStatus> getConnectShimmerMethod() {
        MethodDescriptor<ShimmerBLEGRPC.Request, ShimmerBLEGRPC.StateStatus> methodDescriptorBuild = getConnectShimmerMethod;
        if (methodDescriptorBuild == null) {
            synchronized (ShimmerBLEByteServerGrpc.class) {
                methodDescriptorBuild = getConnectShimmerMethod;
                if (methodDescriptorBuild == null) {
                    methodDescriptorBuild = MethodDescriptor.newBuilder().setType(MethodDescriptor.MethodType.SERVER_STREAMING).setFullMethodName(MethodDescriptor.generateFullMethodName(SERVICE_NAME, "ConnectShimmer")).setSampledToLocalTracing(true).setRequestMarshaller(ProtoUtils.marshaller(ShimmerBLEGRPC.Request.getDefaultInstance())).setResponseMarshaller(ProtoUtils.marshaller(ShimmerBLEGRPC.StateStatus.getDefaultInstance())).setSchemaDescriptor(new ShimmerBLEByteServerMethodDescriptorSupplier("ConnectShimmer")).build();
                    getConnectShimmerMethod = methodDescriptorBuild;
                }
            }
        }
        return methodDescriptorBuild;
    }

    public static MethodDescriptor<ShimmerBLEGRPC.Request, ShimmerBLEGRPC.Reply> getDisconnectShimmerMethod() {
        MethodDescriptor<ShimmerBLEGRPC.Request, ShimmerBLEGRPC.Reply> methodDescriptorBuild = getDisconnectShimmerMethod;
        if (methodDescriptorBuild == null) {
            synchronized (ShimmerBLEByteServerGrpc.class) {
                methodDescriptorBuild = getDisconnectShimmerMethod;
                if (methodDescriptorBuild == null) {
                    methodDescriptorBuild = MethodDescriptor.newBuilder().setType(MethodDescriptor.MethodType.UNARY).setFullMethodName(MethodDescriptor.generateFullMethodName(SERVICE_NAME, "DisconnectShimmer")).setSampledToLocalTracing(true).setRequestMarshaller(ProtoUtils.marshaller(ShimmerBLEGRPC.Request.getDefaultInstance())).setResponseMarshaller(ProtoUtils.marshaller(ShimmerBLEGRPC.Reply.getDefaultInstance())).setSchemaDescriptor(new ShimmerBLEByteServerMethodDescriptorSupplier("DisconnectShimmer")).build();
                    getDisconnectShimmerMethod = methodDescriptorBuild;
                }
            }
        }
        return methodDescriptorBuild;
    }

    public static MethodDescriptor<ShimmerBLEGRPC.WriteBytes, ShimmerBLEGRPC.Reply> getWriteBytesShimmerMethod() {
        MethodDescriptor<ShimmerBLEGRPC.WriteBytes, ShimmerBLEGRPC.Reply> methodDescriptorBuild = getWriteBytesShimmerMethod;
        if (methodDescriptorBuild == null) {
            synchronized (ShimmerBLEByteServerGrpc.class) {
                methodDescriptorBuild = getWriteBytesShimmerMethod;
                if (methodDescriptorBuild == null) {
                    methodDescriptorBuild = MethodDescriptor.newBuilder().setType(MethodDescriptor.MethodType.UNARY).setFullMethodName(MethodDescriptor.generateFullMethodName(SERVICE_NAME, "WriteBytesShimmer")).setSampledToLocalTracing(true).setRequestMarshaller(ProtoUtils.marshaller(ShimmerBLEGRPC.WriteBytes.getDefaultInstance())).setResponseMarshaller(ProtoUtils.marshaller(ShimmerBLEGRPC.Reply.getDefaultInstance())).setSchemaDescriptor(new ShimmerBLEByteServerMethodDescriptorSupplier("WriteBytesShimmer")).build();
                    getWriteBytesShimmerMethod = methodDescriptorBuild;
                }
            }
        }
        return methodDescriptorBuild;
    }

    public static MethodDescriptor<ShimmerBLEGRPC.Request, ShimmerBLEGRPC.Reply> getSayHelloMethod() {
        MethodDescriptor<ShimmerBLEGRPC.Request, ShimmerBLEGRPC.Reply> methodDescriptorBuild = getSayHelloMethod;
        if (methodDescriptorBuild == null) {
            synchronized (ShimmerBLEByteServerGrpc.class) {
                methodDescriptorBuild = getSayHelloMethod;
                if (methodDescriptorBuild == null) {
                    methodDescriptorBuild = MethodDescriptor.newBuilder().setType(MethodDescriptor.MethodType.UNARY).setFullMethodName(MethodDescriptor.generateFullMethodName(SERVICE_NAME, "SayHello")).setSampledToLocalTracing(true).setRequestMarshaller(ProtoUtils.marshaller(ShimmerBLEGRPC.Request.getDefaultInstance())).setResponseMarshaller(ProtoUtils.marshaller(ShimmerBLEGRPC.Reply.getDefaultInstance())).setSchemaDescriptor(new ShimmerBLEByteServerMethodDescriptorSupplier("SayHello")).build();
                    getSayHelloMethod = methodDescriptorBuild;
                }
            }
        }
        return methodDescriptorBuild;
    }

    public static ShimmerBLEByteServerStub newStub(Channel channel) {
        return (ShimmerBLEByteServerStub) ShimmerBLEByteServerStub.newStub(new AbstractStub.StubFactory<ShimmerBLEByteServerStub>() { // from class: com.shimmerresearch.grpc.ShimmerBLEByteServerGrpc.1
            @Override // io.grpc.stub.AbstractStub.StubFactory
            public ShimmerBLEByteServerStub newStub(Channel channel2, CallOptions callOptions) {
                return new ShimmerBLEByteServerStub(channel2, callOptions);
            }
        }, channel);
    }

    public static ShimmerBLEByteServerBlockingStub newBlockingStub(Channel channel) {
        return (ShimmerBLEByteServerBlockingStub) ShimmerBLEByteServerBlockingStub.newStub(new AbstractStub.StubFactory<ShimmerBLEByteServerBlockingStub>() { // from class: com.shimmerresearch.grpc.ShimmerBLEByteServerGrpc.2
            @Override // io.grpc.stub.AbstractStub.StubFactory
            public ShimmerBLEByteServerBlockingStub newStub(Channel channel2, CallOptions callOptions) {
                return new ShimmerBLEByteServerBlockingStub(channel2, callOptions);
            }
        }, channel);
    }

    public static ShimmerBLEByteServerFutureStub newFutureStub(Channel channel) {
        return (ShimmerBLEByteServerFutureStub) ShimmerBLEByteServerFutureStub.newStub(new AbstractStub.StubFactory<ShimmerBLEByteServerFutureStub>() { // from class: com.shimmerresearch.grpc.ShimmerBLEByteServerGrpc.3
            @Override // io.grpc.stub.AbstractStub.StubFactory
            public ShimmerBLEByteServerFutureStub newStub(Channel channel2, CallOptions callOptions) {
                return new ShimmerBLEByteServerFutureStub(channel2, callOptions);
            }
        }, channel);
    }

    public static ServiceDescriptor getServiceDescriptor() {
        ServiceDescriptor serviceDescriptorBuild = serviceDescriptor;
        if (serviceDescriptorBuild == null) {
            synchronized (ShimmerBLEByteServerGrpc.class) {
                serviceDescriptorBuild = serviceDescriptor;
                if (serviceDescriptorBuild == null) {
                    serviceDescriptorBuild = ServiceDescriptor.newBuilder(SERVICE_NAME).setSchemaDescriptor(new ShimmerBLEByteServerFileDescriptorSupplier()).addMethod(getGetDataStreamMethod()).addMethod(getGetTestDataStreamMethod()).addMethod(getSendDataStreamMethod()).addMethod(getConnectShimmerMethod()).addMethod(getDisconnectShimmerMethod()).addMethod(getWriteBytesShimmerMethod()).addMethod(getSayHelloMethod()).build();
                    serviceDescriptor = serviceDescriptorBuild;
                }
            }
        }
        return serviceDescriptorBuild;
    }

    public static abstract class ShimmerBLEByteServerImplBase implements BindableService {
        public void getDataStream(ShimmerBLEGRPC.StreamRequest streamRequest, StreamObserver<ShimmerBLEGRPC.ObjectClusterByteArray> streamObserver) {
            ServerCalls.asyncUnimplementedUnaryCall(ShimmerBLEByteServerGrpc.getGetDataStreamMethod(), streamObserver);
        }

        public void getTestDataStream(ShimmerBLEGRPC.StreamRequest streamRequest, StreamObserver<ShimmerBLEGRPC.ObjectClusterByteArray> streamObserver) {
            ServerCalls.asyncUnimplementedUnaryCall(ShimmerBLEByteServerGrpc.getGetTestDataStreamMethod(), streamObserver);
        }

        public StreamObserver<ShimmerBLEGRPC.ObjectClusterByteArray> sendDataStream(StreamObserver<ShimmerBLEGRPC.Reply> streamObserver) {
            return ServerCalls.asyncUnimplementedStreamingCall(ShimmerBLEByteServerGrpc.getSendDataStreamMethod(), streamObserver);
        }

        public void connectShimmer(ShimmerBLEGRPC.Request request, StreamObserver<ShimmerBLEGRPC.StateStatus> streamObserver) {
            ServerCalls.asyncUnimplementedUnaryCall(ShimmerBLEByteServerGrpc.getConnectShimmerMethod(), streamObserver);
        }

        public void disconnectShimmer(ShimmerBLEGRPC.Request request, StreamObserver<ShimmerBLEGRPC.Reply> streamObserver) {
            ServerCalls.asyncUnimplementedUnaryCall(ShimmerBLEByteServerGrpc.getDisconnectShimmerMethod(), streamObserver);
        }

        public void writeBytesShimmer(ShimmerBLEGRPC.WriteBytes writeBytes, StreamObserver<ShimmerBLEGRPC.Reply> streamObserver) {
            ServerCalls.asyncUnimplementedUnaryCall(ShimmerBLEByteServerGrpc.getWriteBytesShimmerMethod(), streamObserver);
        }

        public void sayHello(ShimmerBLEGRPC.Request request, StreamObserver<ShimmerBLEGRPC.Reply> streamObserver) {
            ServerCalls.asyncUnimplementedUnaryCall(ShimmerBLEByteServerGrpc.getSayHelloMethod(), streamObserver);
        }

        @Override // io.grpc.BindableService
        public final ServerServiceDefinition bindService() {
            return ServerServiceDefinition.builder(ShimmerBLEByteServerGrpc.getServiceDescriptor()).addMethod(ShimmerBLEByteServerGrpc.getGetDataStreamMethod(), ServerCalls.asyncServerStreamingCall(new MethodHandlers(this, 0))).addMethod(ShimmerBLEByteServerGrpc.getGetTestDataStreamMethod(), ServerCalls.asyncServerStreamingCall(new MethodHandlers(this, 1))).addMethod(ShimmerBLEByteServerGrpc.getSendDataStreamMethod(), ServerCalls.asyncClientStreamingCall(new MethodHandlers(this, 6))).addMethod(ShimmerBLEByteServerGrpc.getConnectShimmerMethod(), ServerCalls.asyncServerStreamingCall(new MethodHandlers(this, 2))).addMethod(ShimmerBLEByteServerGrpc.getDisconnectShimmerMethod(), ServerCalls.asyncUnaryCall(new MethodHandlers(this, 3))).addMethod(ShimmerBLEByteServerGrpc.getWriteBytesShimmerMethod(), ServerCalls.asyncUnaryCall(new MethodHandlers(this, 4))).addMethod(ShimmerBLEByteServerGrpc.getSayHelloMethod(), ServerCalls.asyncUnaryCall(new MethodHandlers(this, 5))).build();
        }
    }

    public static final class ShimmerBLEByteServerStub extends AbstractAsyncStub<ShimmerBLEByteServerStub> {
        private ShimmerBLEByteServerStub(Channel channel, CallOptions callOptions) {
            super(channel, callOptions);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // io.grpc.stub.AbstractStub
        public ShimmerBLEByteServerStub build(Channel channel, CallOptions callOptions) {
            return new ShimmerBLEByteServerStub(channel, callOptions);
        }

        public void getDataStream(ShimmerBLEGRPC.StreamRequest streamRequest, StreamObserver<ShimmerBLEGRPC.ObjectClusterByteArray> streamObserver) {
            ClientCalls.asyncServerStreamingCall(getChannel().newCall(ShimmerBLEByteServerGrpc.getGetDataStreamMethod(), getCallOptions()), streamRequest, streamObserver);
        }

        public void getTestDataStream(ShimmerBLEGRPC.StreamRequest streamRequest, StreamObserver<ShimmerBLEGRPC.ObjectClusterByteArray> streamObserver) {
            ClientCalls.asyncServerStreamingCall(getChannel().newCall(ShimmerBLEByteServerGrpc.getGetTestDataStreamMethod(), getCallOptions()), streamRequest, streamObserver);
        }

        public StreamObserver<ShimmerBLEGRPC.ObjectClusterByteArray> sendDataStream(StreamObserver<ShimmerBLEGRPC.Reply> streamObserver) {
            return ClientCalls.asyncClientStreamingCall(getChannel().newCall(ShimmerBLEByteServerGrpc.getSendDataStreamMethod(), getCallOptions()), streamObserver);
        }

        public void connectShimmer(ShimmerBLEGRPC.Request request, StreamObserver<ShimmerBLEGRPC.StateStatus> streamObserver) {
            ClientCalls.asyncServerStreamingCall(getChannel().newCall(ShimmerBLEByteServerGrpc.getConnectShimmerMethod(), getCallOptions()), request, streamObserver);
        }

        public void disconnectShimmer(ShimmerBLEGRPC.Request request, StreamObserver<ShimmerBLEGRPC.Reply> streamObserver) {
            ClientCalls.asyncUnaryCall(getChannel().newCall(ShimmerBLEByteServerGrpc.getDisconnectShimmerMethod(), getCallOptions()), request, streamObserver);
        }

        public void writeBytesShimmer(ShimmerBLEGRPC.WriteBytes writeBytes, StreamObserver<ShimmerBLEGRPC.Reply> streamObserver) {
            ClientCalls.asyncUnaryCall(getChannel().newCall(ShimmerBLEByteServerGrpc.getWriteBytesShimmerMethod(), getCallOptions()), writeBytes, streamObserver);
        }

        public void sayHello(ShimmerBLEGRPC.Request request, StreamObserver<ShimmerBLEGRPC.Reply> streamObserver) {
            ClientCalls.asyncUnaryCall(getChannel().newCall(ShimmerBLEByteServerGrpc.getSayHelloMethod(), getCallOptions()), request, streamObserver);
        }
    }

    public static final class ShimmerBLEByteServerBlockingStub extends AbstractBlockingStub<ShimmerBLEByteServerBlockingStub> {
        private ShimmerBLEByteServerBlockingStub(Channel channel, CallOptions callOptions) {
            super(channel, callOptions);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // io.grpc.stub.AbstractStub
        public ShimmerBLEByteServerBlockingStub build(Channel channel, CallOptions callOptions) {
            return new ShimmerBLEByteServerBlockingStub(channel, callOptions);
        }

        public Iterator<ShimmerBLEGRPC.ObjectClusterByteArray> getDataStream(ShimmerBLEGRPC.StreamRequest streamRequest) {
            return ClientCalls.blockingServerStreamingCall(getChannel(), ShimmerBLEByteServerGrpc.getGetDataStreamMethod(), getCallOptions(), streamRequest);
        }

        public Iterator<ShimmerBLEGRPC.ObjectClusterByteArray> getTestDataStream(ShimmerBLEGRPC.StreamRequest streamRequest) {
            return ClientCalls.blockingServerStreamingCall(getChannel(), ShimmerBLEByteServerGrpc.getGetTestDataStreamMethod(), getCallOptions(), streamRequest);
        }

        public Iterator<ShimmerBLEGRPC.StateStatus> connectShimmer(ShimmerBLEGRPC.Request request) {
            return ClientCalls.blockingServerStreamingCall(getChannel(), ShimmerBLEByteServerGrpc.getConnectShimmerMethod(), getCallOptions(), request);
        }

        public ShimmerBLEGRPC.Reply disconnectShimmer(ShimmerBLEGRPC.Request request) {
            return (ShimmerBLEGRPC.Reply) ClientCalls.blockingUnaryCall(getChannel(), ShimmerBLEByteServerGrpc.getDisconnectShimmerMethod(), getCallOptions(), request);
        }

        public ShimmerBLEGRPC.Reply writeBytesShimmer(ShimmerBLEGRPC.WriteBytes writeBytes) {
            return (ShimmerBLEGRPC.Reply) ClientCalls.blockingUnaryCall(getChannel(), ShimmerBLEByteServerGrpc.getWriteBytesShimmerMethod(), getCallOptions(), writeBytes);
        }

        public ShimmerBLEGRPC.Reply sayHello(ShimmerBLEGRPC.Request request) {
            return (ShimmerBLEGRPC.Reply) ClientCalls.blockingUnaryCall(getChannel(), ShimmerBLEByteServerGrpc.getSayHelloMethod(), getCallOptions(), request);
        }
    }

    public static final class ShimmerBLEByteServerFutureStub extends AbstractFutureStub<ShimmerBLEByteServerFutureStub> {
        private ShimmerBLEByteServerFutureStub(Channel channel, CallOptions callOptions) {
            super(channel, callOptions);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // io.grpc.stub.AbstractStub
        public ShimmerBLEByteServerFutureStub build(Channel channel, CallOptions callOptions) {
            return new ShimmerBLEByteServerFutureStub(channel, callOptions);
        }

        public ListenableFuture<ShimmerBLEGRPC.Reply> disconnectShimmer(ShimmerBLEGRPC.Request request) {
            return ClientCalls.futureUnaryCall(getChannel().newCall(ShimmerBLEByteServerGrpc.getDisconnectShimmerMethod(), getCallOptions()), request);
        }

        public ListenableFuture<ShimmerBLEGRPC.Reply> writeBytesShimmer(ShimmerBLEGRPC.WriteBytes writeBytes) {
            return ClientCalls.futureUnaryCall(getChannel().newCall(ShimmerBLEByteServerGrpc.getWriteBytesShimmerMethod(), getCallOptions()), writeBytes);
        }

        public ListenableFuture<ShimmerBLEGRPC.Reply> sayHello(ShimmerBLEGRPC.Request request) {
            return ClientCalls.futureUnaryCall(getChannel().newCall(ShimmerBLEByteServerGrpc.getSayHelloMethod(), getCallOptions()), request);
        }
    }

    private static final class MethodHandlers<Req, Resp> implements ServerCalls.UnaryMethod<Req, Resp>, ServerCalls.ServerStreamingMethod<Req, Resp>, ServerCalls.ClientStreamingMethod<Req, Resp>, ServerCalls.BidiStreamingMethod<Req, Resp> {
        private final int methodId;
        private final ShimmerBLEByteServerImplBase serviceImpl;

        MethodHandlers(ShimmerBLEByteServerImplBase shimmerBLEByteServerImplBase, int i) {
            this.serviceImpl = shimmerBLEByteServerImplBase;
            this.methodId = i;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override
        // io.grpc.stub.ServerCalls.UnaryMethod, io.grpc.stub.ServerCalls.UnaryRequestMethod, io.grpc.stub.ServerCalls.ServerStreamingMethod
        public void invoke(Req req, StreamObserver<Resp> streamObserver) {
            int i = this.methodId;
            if (i == 0) {
                this.serviceImpl.getDataStream((ShimmerBLEGRPC.StreamRequest) req, streamObserver);
                return;
            }
            if (i == 1) {
                this.serviceImpl.getTestDataStream((ShimmerBLEGRPC.StreamRequest) req, streamObserver);
                return;
            }
            if (i == 2) {
                this.serviceImpl.connectShimmer((ShimmerBLEGRPC.Request) req, streamObserver);
                return;
            }
            if (i == 3) {
                this.serviceImpl.disconnectShimmer((ShimmerBLEGRPC.Request) req, streamObserver);
            } else if (i == 4) {
                this.serviceImpl.writeBytesShimmer((ShimmerBLEGRPC.WriteBytes) req, streamObserver);
            } else {
                if (i == 5) {
                    this.serviceImpl.sayHello((ShimmerBLEGRPC.Request) req, streamObserver);
                    return;
                }
                throw new AssertionError();
            }
        }

        @Override
        // io.grpc.stub.ServerCalls.ClientStreamingMethod, io.grpc.stub.ServerCalls.StreamingRequestMethod, io.grpc.stub.ServerCalls.BidiStreamingMethod
        public StreamObserver<Req> invoke(StreamObserver<Resp> streamObserver) {
            if (this.methodId == 6) {
                return (StreamObserver<Req>) this.serviceImpl.sendDataStream(streamObserver);
            }
            throw new AssertionError();
        }
    }

    private static abstract class ShimmerBLEByteServerBaseDescriptorSupplier implements ProtoFileDescriptorSupplier, ProtoServiceDescriptorSupplier {
        ShimmerBLEByteServerBaseDescriptorSupplier() {
        }

        @Override // io.grpc.protobuf.ProtoFileDescriptorSupplier
        public Descriptors.FileDescriptor getFileDescriptor() {
            return ShimmerBLEGRPC.getDescriptor();
        }

        @Override // io.grpc.protobuf.ProtoServiceDescriptorSupplier
        public Descriptors.ServiceDescriptor getServiceDescriptor() {
            return getFileDescriptor().findServiceByName("ShimmerBLEByteServer");
        }
    }

    private static final class ShimmerBLEByteServerFileDescriptorSupplier extends ShimmerBLEByteServerBaseDescriptorSupplier {
        ShimmerBLEByteServerFileDescriptorSupplier() {
        }
    }

    private static final class ShimmerBLEByteServerMethodDescriptorSupplier extends ShimmerBLEByteServerBaseDescriptorSupplier implements ProtoMethodDescriptorSupplier {
        private final String methodName;

        ShimmerBLEByteServerMethodDescriptorSupplier(String str) {
            this.methodName = str;
        }

        @Override // io.grpc.protobuf.ProtoMethodDescriptorSupplier
        public Descriptors.MethodDescriptor getMethodDescriptor() {
            return getServiceDescriptor().findMethodByName(this.methodName);
        }
    }
}
