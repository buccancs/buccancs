package io.grpc.channelz.v1;

import com.google.common.util.concurrent.ListenableFuture;
import com.google.protobuf.Descriptors;
import io.grpc.BindableService;
import io.grpc.CallOptions;
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
public final class ChannelzGrpc {
    public static final String SERVICE_NAME = "grpc.channelz.v1.Channelz";
    private static final int METHODID_GET_CHANNEL = 4;
    private static final int METHODID_GET_SERVER = 2;
    private static final int METHODID_GET_SERVERS = 1;
    private static final int METHODID_GET_SERVER_SOCKETS = 3;
    private static final int METHODID_GET_SOCKET = 6;
    private static final int METHODID_GET_SUBCHANNEL = 5;
    private static final int METHODID_GET_TOP_CHANNELS = 0;
    private static volatile MethodDescriptor<GetChannelRequest, GetChannelResponse> getGetChannelMethod;
    private static volatile MethodDescriptor<GetServerRequest, GetServerResponse> getGetServerMethod;
    private static volatile MethodDescriptor<GetServerSocketsRequest, GetServerSocketsResponse> getGetServerSocketsMethod;
    private static volatile MethodDescriptor<GetServersRequest, GetServersResponse> getGetServersMethod;
    private static volatile MethodDescriptor<GetSocketRequest, GetSocketResponse> getGetSocketMethod;
    private static volatile MethodDescriptor<GetSubchannelRequest, GetSubchannelResponse> getGetSubchannelMethod;
    private static volatile MethodDescriptor<GetTopChannelsRequest, GetTopChannelsResponse> getGetTopChannelsMethod;
    private static volatile ServiceDescriptor serviceDescriptor;

    private ChannelzGrpc() {
    }

    public static MethodDescriptor<GetTopChannelsRequest, GetTopChannelsResponse> getGetTopChannelsMethod() {
        MethodDescriptor<GetTopChannelsRequest, GetTopChannelsResponse> methodDescriptorBuild = getGetTopChannelsMethod;
        if (methodDescriptorBuild == null) {
            synchronized (ChannelzGrpc.class) {
                methodDescriptorBuild = getGetTopChannelsMethod;
                if (methodDescriptorBuild == null) {
                    methodDescriptorBuild = MethodDescriptor.newBuilder().setType(MethodDescriptor.MethodType.UNARY).setFullMethodName(MethodDescriptor.generateFullMethodName(SERVICE_NAME, "GetTopChannels")).setSampledToLocalTracing(true).setRequestMarshaller(ProtoUtils.marshaller(GetTopChannelsRequest.getDefaultInstance())).setResponseMarshaller(ProtoUtils.marshaller(GetTopChannelsResponse.getDefaultInstance())).setSchemaDescriptor(new ChannelzMethodDescriptorSupplier("GetTopChannels")).build();
                    getGetTopChannelsMethod = methodDescriptorBuild;
                }
            }
        }
        return methodDescriptorBuild;
    }

    public static MethodDescriptor<GetServersRequest, GetServersResponse> getGetServersMethod() {
        MethodDescriptor<GetServersRequest, GetServersResponse> methodDescriptorBuild = getGetServersMethod;
        if (methodDescriptorBuild == null) {
            synchronized (ChannelzGrpc.class) {
                methodDescriptorBuild = getGetServersMethod;
                if (methodDescriptorBuild == null) {
                    methodDescriptorBuild = MethodDescriptor.newBuilder().setType(MethodDescriptor.MethodType.UNARY).setFullMethodName(MethodDescriptor.generateFullMethodName(SERVICE_NAME, "GetServers")).setSampledToLocalTracing(true).setRequestMarshaller(ProtoUtils.marshaller(GetServersRequest.getDefaultInstance())).setResponseMarshaller(ProtoUtils.marshaller(GetServersResponse.getDefaultInstance())).setSchemaDescriptor(new ChannelzMethodDescriptorSupplier("GetServers")).build();
                    getGetServersMethod = methodDescriptorBuild;
                }
            }
        }
        return methodDescriptorBuild;
    }

    public static MethodDescriptor<GetServerRequest, GetServerResponse> getGetServerMethod() {
        MethodDescriptor<GetServerRequest, GetServerResponse> methodDescriptorBuild = getGetServerMethod;
        if (methodDescriptorBuild == null) {
            synchronized (ChannelzGrpc.class) {
                methodDescriptorBuild = getGetServerMethod;
                if (methodDescriptorBuild == null) {
                    methodDescriptorBuild = MethodDescriptor.newBuilder().setType(MethodDescriptor.MethodType.UNARY).setFullMethodName(MethodDescriptor.generateFullMethodName(SERVICE_NAME, "GetServer")).setSampledToLocalTracing(true).setRequestMarshaller(ProtoUtils.marshaller(GetServerRequest.getDefaultInstance())).setResponseMarshaller(ProtoUtils.marshaller(GetServerResponse.getDefaultInstance())).setSchemaDescriptor(new ChannelzMethodDescriptorSupplier("GetServer")).build();
                    getGetServerMethod = methodDescriptorBuild;
                }
            }
        }
        return methodDescriptorBuild;
    }

    public static MethodDescriptor<GetServerSocketsRequest, GetServerSocketsResponse> getGetServerSocketsMethod() {
        MethodDescriptor<GetServerSocketsRequest, GetServerSocketsResponse> methodDescriptorBuild = getGetServerSocketsMethod;
        if (methodDescriptorBuild == null) {
            synchronized (ChannelzGrpc.class) {
                methodDescriptorBuild = getGetServerSocketsMethod;
                if (methodDescriptorBuild == null) {
                    methodDescriptorBuild = MethodDescriptor.newBuilder().setType(MethodDescriptor.MethodType.UNARY).setFullMethodName(MethodDescriptor.generateFullMethodName(SERVICE_NAME, "GetServerSockets")).setSampledToLocalTracing(true).setRequestMarshaller(ProtoUtils.marshaller(GetServerSocketsRequest.getDefaultInstance())).setResponseMarshaller(ProtoUtils.marshaller(GetServerSocketsResponse.getDefaultInstance())).setSchemaDescriptor(new ChannelzMethodDescriptorSupplier("GetServerSockets")).build();
                    getGetServerSocketsMethod = methodDescriptorBuild;
                }
            }
        }
        return methodDescriptorBuild;
    }

    public static MethodDescriptor<GetChannelRequest, GetChannelResponse> getGetChannelMethod() {
        MethodDescriptor<GetChannelRequest, GetChannelResponse> methodDescriptorBuild = getGetChannelMethod;
        if (methodDescriptorBuild == null) {
            synchronized (ChannelzGrpc.class) {
                methodDescriptorBuild = getGetChannelMethod;
                if (methodDescriptorBuild == null) {
                    methodDescriptorBuild = MethodDescriptor.newBuilder().setType(MethodDescriptor.MethodType.UNARY).setFullMethodName(MethodDescriptor.generateFullMethodName(SERVICE_NAME, "GetChannel")).setSampledToLocalTracing(true).setRequestMarshaller(ProtoUtils.marshaller(GetChannelRequest.getDefaultInstance())).setResponseMarshaller(ProtoUtils.marshaller(GetChannelResponse.getDefaultInstance())).setSchemaDescriptor(new ChannelzMethodDescriptorSupplier("GetChannel")).build();
                    getGetChannelMethod = methodDescriptorBuild;
                }
            }
        }
        return methodDescriptorBuild;
    }

    public static MethodDescriptor<GetSubchannelRequest, GetSubchannelResponse> getGetSubchannelMethod() {
        MethodDescriptor<GetSubchannelRequest, GetSubchannelResponse> methodDescriptorBuild = getGetSubchannelMethod;
        if (methodDescriptorBuild == null) {
            synchronized (ChannelzGrpc.class) {
                methodDescriptorBuild = getGetSubchannelMethod;
                if (methodDescriptorBuild == null) {
                    methodDescriptorBuild = MethodDescriptor.newBuilder().setType(MethodDescriptor.MethodType.UNARY).setFullMethodName(MethodDescriptor.generateFullMethodName(SERVICE_NAME, "GetSubchannel")).setSampledToLocalTracing(true).setRequestMarshaller(ProtoUtils.marshaller(GetSubchannelRequest.getDefaultInstance())).setResponseMarshaller(ProtoUtils.marshaller(GetSubchannelResponse.getDefaultInstance())).setSchemaDescriptor(new ChannelzMethodDescriptorSupplier("GetSubchannel")).build();
                    getGetSubchannelMethod = methodDescriptorBuild;
                }
            }
        }
        return methodDescriptorBuild;
    }

    public static MethodDescriptor<GetSocketRequest, GetSocketResponse> getGetSocketMethod() {
        MethodDescriptor<GetSocketRequest, GetSocketResponse> methodDescriptorBuild = getGetSocketMethod;
        if (methodDescriptorBuild == null) {
            synchronized (ChannelzGrpc.class) {
                methodDescriptorBuild = getGetSocketMethod;
                if (methodDescriptorBuild == null) {
                    methodDescriptorBuild = MethodDescriptor.newBuilder().setType(MethodDescriptor.MethodType.UNARY).setFullMethodName(MethodDescriptor.generateFullMethodName(SERVICE_NAME, "GetSocket")).setSampledToLocalTracing(true).setRequestMarshaller(ProtoUtils.marshaller(GetSocketRequest.getDefaultInstance())).setResponseMarshaller(ProtoUtils.marshaller(GetSocketResponse.getDefaultInstance())).setSchemaDescriptor(new ChannelzMethodDescriptorSupplier("GetSocket")).build();
                    getGetSocketMethod = methodDescriptorBuild;
                }
            }
        }
        return methodDescriptorBuild;
    }

    public static ChannelzStub newStub(io.grpc.Channel channel) {
        return (ChannelzStub) ChannelzStub.newStub(new AbstractStub.StubFactory<ChannelzStub>() { // from class: io.grpc.channelz.v1.ChannelzGrpc.1
            @Override // io.grpc.stub.AbstractStub.StubFactory
            public ChannelzStub newStub(io.grpc.Channel channel2, CallOptions callOptions) {
                return new ChannelzStub(channel2, callOptions);
            }
        }, channel);
    }

    public static ChannelzBlockingStub newBlockingStub(io.grpc.Channel channel) {
        return (ChannelzBlockingStub) ChannelzBlockingStub.newStub(new AbstractStub.StubFactory<ChannelzBlockingStub>() { // from class: io.grpc.channelz.v1.ChannelzGrpc.2
            @Override // io.grpc.stub.AbstractStub.StubFactory
            public ChannelzBlockingStub newStub(io.grpc.Channel channel2, CallOptions callOptions) {
                return new ChannelzBlockingStub(channel2, callOptions);
            }
        }, channel);
    }

    public static ChannelzFutureStub newFutureStub(io.grpc.Channel channel) {
        return (ChannelzFutureStub) ChannelzFutureStub.newStub(new AbstractStub.StubFactory<ChannelzFutureStub>() { // from class: io.grpc.channelz.v1.ChannelzGrpc.3
            @Override // io.grpc.stub.AbstractStub.StubFactory
            public ChannelzFutureStub newStub(io.grpc.Channel channel2, CallOptions callOptions) {
                return new ChannelzFutureStub(channel2, callOptions);
            }
        }, channel);
    }

    public static ServiceDescriptor getServiceDescriptor() {
        ServiceDescriptor serviceDescriptorBuild = serviceDescriptor;
        if (serviceDescriptorBuild == null) {
            synchronized (ChannelzGrpc.class) {
                serviceDescriptorBuild = serviceDescriptor;
                if (serviceDescriptorBuild == null) {
                    serviceDescriptorBuild = ServiceDescriptor.newBuilder(SERVICE_NAME).setSchemaDescriptor(new ChannelzFileDescriptorSupplier()).addMethod(getGetTopChannelsMethod()).addMethod(getGetServersMethod()).addMethod(getGetServerMethod()).addMethod(getGetServerSocketsMethod()).addMethod(getGetChannelMethod()).addMethod(getGetSubchannelMethod()).addMethod(getGetSocketMethod()).build();
                    serviceDescriptor = serviceDescriptorBuild;
                }
            }
        }
        return serviceDescriptorBuild;
    }

    public static abstract class ChannelzImplBase implements BindableService {
        public void getTopChannels(GetTopChannelsRequest getTopChannelsRequest, StreamObserver<GetTopChannelsResponse> streamObserver) {
            ServerCalls.asyncUnimplementedUnaryCall(ChannelzGrpc.getGetTopChannelsMethod(), streamObserver);
        }

        public void getServers(GetServersRequest getServersRequest, StreamObserver<GetServersResponse> streamObserver) {
            ServerCalls.asyncUnimplementedUnaryCall(ChannelzGrpc.getGetServersMethod(), streamObserver);
        }

        public void getServer(GetServerRequest getServerRequest, StreamObserver<GetServerResponse> streamObserver) {
            ServerCalls.asyncUnimplementedUnaryCall(ChannelzGrpc.getGetServerMethod(), streamObserver);
        }

        public void getServerSockets(GetServerSocketsRequest getServerSocketsRequest, StreamObserver<GetServerSocketsResponse> streamObserver) {
            ServerCalls.asyncUnimplementedUnaryCall(ChannelzGrpc.getGetServerSocketsMethod(), streamObserver);
        }

        public void getChannel(GetChannelRequest getChannelRequest, StreamObserver<GetChannelResponse> streamObserver) {
            ServerCalls.asyncUnimplementedUnaryCall(ChannelzGrpc.getGetChannelMethod(), streamObserver);
        }

        public void getSubchannel(GetSubchannelRequest getSubchannelRequest, StreamObserver<GetSubchannelResponse> streamObserver) {
            ServerCalls.asyncUnimplementedUnaryCall(ChannelzGrpc.getGetSubchannelMethod(), streamObserver);
        }

        public void getSocket(GetSocketRequest getSocketRequest, StreamObserver<GetSocketResponse> streamObserver) {
            ServerCalls.asyncUnimplementedUnaryCall(ChannelzGrpc.getGetSocketMethod(), streamObserver);
        }

        @Override // io.grpc.BindableService
        public final ServerServiceDefinition bindService() {
            return ServerServiceDefinition.builder(ChannelzGrpc.getServiceDescriptor()).addMethod(ChannelzGrpc.getGetTopChannelsMethod(), ServerCalls.asyncUnaryCall(new MethodHandlers(this, 0))).addMethod(ChannelzGrpc.getGetServersMethod(), ServerCalls.asyncUnaryCall(new MethodHandlers(this, 1))).addMethod(ChannelzGrpc.getGetServerMethod(), ServerCalls.asyncUnaryCall(new MethodHandlers(this, 2))).addMethod(ChannelzGrpc.getGetServerSocketsMethod(), ServerCalls.asyncUnaryCall(new MethodHandlers(this, 3))).addMethod(ChannelzGrpc.getGetChannelMethod(), ServerCalls.asyncUnaryCall(new MethodHandlers(this, 4))).addMethod(ChannelzGrpc.getGetSubchannelMethod(), ServerCalls.asyncUnaryCall(new MethodHandlers(this, 5))).addMethod(ChannelzGrpc.getGetSocketMethod(), ServerCalls.asyncUnaryCall(new MethodHandlers(this, 6))).build();
        }
    }

    public static final class ChannelzStub extends AbstractAsyncStub<ChannelzStub> {
        private ChannelzStub(io.grpc.Channel channel, CallOptions callOptions) {
            super(channel, callOptions);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // io.grpc.stub.AbstractStub
        public ChannelzStub build(io.grpc.Channel channel, CallOptions callOptions) {
            return new ChannelzStub(channel, callOptions);
        }

        public void getTopChannels(GetTopChannelsRequest getTopChannelsRequest, StreamObserver<GetTopChannelsResponse> streamObserver) {
            ClientCalls.asyncUnaryCall(getChannel().newCall(ChannelzGrpc.getGetTopChannelsMethod(), getCallOptions()), getTopChannelsRequest, streamObserver);
        }

        public void getServers(GetServersRequest getServersRequest, StreamObserver<GetServersResponse> streamObserver) {
            ClientCalls.asyncUnaryCall(getChannel().newCall(ChannelzGrpc.getGetServersMethod(), getCallOptions()), getServersRequest, streamObserver);
        }

        public void getServer(GetServerRequest getServerRequest, StreamObserver<GetServerResponse> streamObserver) {
            ClientCalls.asyncUnaryCall(getChannel().newCall(ChannelzGrpc.getGetServerMethod(), getCallOptions()), getServerRequest, streamObserver);
        }

        public void getServerSockets(GetServerSocketsRequest getServerSocketsRequest, StreamObserver<GetServerSocketsResponse> streamObserver) {
            ClientCalls.asyncUnaryCall(getChannel().newCall(ChannelzGrpc.getGetServerSocketsMethod(), getCallOptions()), getServerSocketsRequest, streamObserver);
        }

        public void getChannel(GetChannelRequest getChannelRequest, StreamObserver<GetChannelResponse> streamObserver) {
            ClientCalls.asyncUnaryCall(getChannel().newCall(ChannelzGrpc.getGetChannelMethod(), getCallOptions()), getChannelRequest, streamObserver);
        }

        public void getSubchannel(GetSubchannelRequest getSubchannelRequest, StreamObserver<GetSubchannelResponse> streamObserver) {
            ClientCalls.asyncUnaryCall(getChannel().newCall(ChannelzGrpc.getGetSubchannelMethod(), getCallOptions()), getSubchannelRequest, streamObserver);
        }

        public void getSocket(GetSocketRequest getSocketRequest, StreamObserver<GetSocketResponse> streamObserver) {
            ClientCalls.asyncUnaryCall(getChannel().newCall(ChannelzGrpc.getGetSocketMethod(), getCallOptions()), getSocketRequest, streamObserver);
        }
    }

    public static final class ChannelzBlockingStub extends AbstractBlockingStub<ChannelzBlockingStub> {
        private ChannelzBlockingStub(io.grpc.Channel channel, CallOptions callOptions) {
            super(channel, callOptions);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // io.grpc.stub.AbstractStub
        public ChannelzBlockingStub build(io.grpc.Channel channel, CallOptions callOptions) {
            return new ChannelzBlockingStub(channel, callOptions);
        }

        public GetTopChannelsResponse getTopChannels(GetTopChannelsRequest getTopChannelsRequest) {
            return (GetTopChannelsResponse) ClientCalls.blockingUnaryCall(getChannel(), ChannelzGrpc.getGetTopChannelsMethod(), getCallOptions(), getTopChannelsRequest);
        }

        public GetServersResponse getServers(GetServersRequest getServersRequest) {
            return (GetServersResponse) ClientCalls.blockingUnaryCall(getChannel(), ChannelzGrpc.getGetServersMethod(), getCallOptions(), getServersRequest);
        }

        public GetServerResponse getServer(GetServerRequest getServerRequest) {
            return (GetServerResponse) ClientCalls.blockingUnaryCall(getChannel(), ChannelzGrpc.getGetServerMethod(), getCallOptions(), getServerRequest);
        }

        public GetServerSocketsResponse getServerSockets(GetServerSocketsRequest getServerSocketsRequest) {
            return (GetServerSocketsResponse) ClientCalls.blockingUnaryCall(getChannel(), ChannelzGrpc.getGetServerSocketsMethod(), getCallOptions(), getServerSocketsRequest);
        }

        public GetChannelResponse getChannel(GetChannelRequest getChannelRequest) {
            return (GetChannelResponse) ClientCalls.blockingUnaryCall(getChannel(), ChannelzGrpc.getGetChannelMethod(), getCallOptions(), getChannelRequest);
        }

        public GetSubchannelResponse getSubchannel(GetSubchannelRequest getSubchannelRequest) {
            return (GetSubchannelResponse) ClientCalls.blockingUnaryCall(getChannel(), ChannelzGrpc.getGetSubchannelMethod(), getCallOptions(), getSubchannelRequest);
        }

        public GetSocketResponse getSocket(GetSocketRequest getSocketRequest) {
            return (GetSocketResponse) ClientCalls.blockingUnaryCall(getChannel(), ChannelzGrpc.getGetSocketMethod(), getCallOptions(), getSocketRequest);
        }
    }

    public static final class ChannelzFutureStub extends AbstractFutureStub<ChannelzFutureStub> {
        private ChannelzFutureStub(io.grpc.Channel channel, CallOptions callOptions) {
            super(channel, callOptions);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // io.grpc.stub.AbstractStub
        public ChannelzFutureStub build(io.grpc.Channel channel, CallOptions callOptions) {
            return new ChannelzFutureStub(channel, callOptions);
        }

        public ListenableFuture<GetTopChannelsResponse> getTopChannels(GetTopChannelsRequest getTopChannelsRequest) {
            return ClientCalls.futureUnaryCall(getChannel().newCall(ChannelzGrpc.getGetTopChannelsMethod(), getCallOptions()), getTopChannelsRequest);
        }

        public ListenableFuture<GetServersResponse> getServers(GetServersRequest getServersRequest) {
            return ClientCalls.futureUnaryCall(getChannel().newCall(ChannelzGrpc.getGetServersMethod(), getCallOptions()), getServersRequest);
        }

        public ListenableFuture<GetServerResponse> getServer(GetServerRequest getServerRequest) {
            return ClientCalls.futureUnaryCall(getChannel().newCall(ChannelzGrpc.getGetServerMethod(), getCallOptions()), getServerRequest);
        }

        public ListenableFuture<GetServerSocketsResponse> getServerSockets(GetServerSocketsRequest getServerSocketsRequest) {
            return ClientCalls.futureUnaryCall(getChannel().newCall(ChannelzGrpc.getGetServerSocketsMethod(), getCallOptions()), getServerSocketsRequest);
        }

        public ListenableFuture<GetChannelResponse> getChannel(GetChannelRequest getChannelRequest) {
            return ClientCalls.futureUnaryCall(getChannel().newCall(ChannelzGrpc.getGetChannelMethod(), getCallOptions()), getChannelRequest);
        }

        public ListenableFuture<GetSubchannelResponse> getSubchannel(GetSubchannelRequest getSubchannelRequest) {
            return ClientCalls.futureUnaryCall(getChannel().newCall(ChannelzGrpc.getGetSubchannelMethod(), getCallOptions()), getSubchannelRequest);
        }

        public ListenableFuture<GetSocketResponse> getSocket(GetSocketRequest getSocketRequest) {
            return ClientCalls.futureUnaryCall(getChannel().newCall(ChannelzGrpc.getGetSocketMethod(), getCallOptions()), getSocketRequest);
        }
    }

    private static final class MethodHandlers<Req, Resp> implements ServerCalls.UnaryMethod<Req, Resp>, ServerCalls.ServerStreamingMethod<Req, Resp>, ServerCalls.ClientStreamingMethod<Req, Resp>, ServerCalls.BidiStreamingMethod<Req, Resp> {
        private final int methodId;
        private final ChannelzImplBase serviceImpl;

        MethodHandlers(ChannelzImplBase channelzImplBase, int i) {
            this.serviceImpl = channelzImplBase;
            this.methodId = i;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override
        // io.grpc.stub.ServerCalls.UnaryMethod, io.grpc.stub.ServerCalls.UnaryRequestMethod, io.grpc.stub.ServerCalls.ServerStreamingMethod
        public void invoke(Req req, StreamObserver<Resp> streamObserver) {
            switch (this.methodId) {
                case 0:
                    this.serviceImpl.getTopChannels((GetTopChannelsRequest) req, streamObserver);
                    return;
                case 1:
                    this.serviceImpl.getServers((GetServersRequest) req, streamObserver);
                    return;
                case 2:
                    this.serviceImpl.getServer((GetServerRequest) req, streamObserver);
                    return;
                case 3:
                    this.serviceImpl.getServerSockets((GetServerSocketsRequest) req, streamObserver);
                    return;
                case 4:
                    this.serviceImpl.getChannel((GetChannelRequest) req, streamObserver);
                    return;
                case 5:
                    this.serviceImpl.getSubchannel((GetSubchannelRequest) req, streamObserver);
                    return;
                case 6:
                    this.serviceImpl.getSocket((GetSocketRequest) req, streamObserver);
                    return;
                default:
                    throw new AssertionError();
            }
        }

        @Override
        // io.grpc.stub.ServerCalls.ClientStreamingMethod, io.grpc.stub.ServerCalls.StreamingRequestMethod, io.grpc.stub.ServerCalls.BidiStreamingMethod
        public StreamObserver<Req> invoke(StreamObserver<Resp> streamObserver) {
            throw new AssertionError();
        }
    }

    private static abstract class ChannelzBaseDescriptorSupplier implements ProtoFileDescriptorSupplier, ProtoServiceDescriptorSupplier {
        ChannelzBaseDescriptorSupplier() {
        }

        @Override // io.grpc.protobuf.ProtoFileDescriptorSupplier
        public Descriptors.FileDescriptor getFileDescriptor() {
            return ChannelzProto.getDescriptor();
        }

        @Override // io.grpc.protobuf.ProtoServiceDescriptorSupplier
        public Descriptors.ServiceDescriptor getServiceDescriptor() {
            return getFileDescriptor().findServiceByName("Channelz");
        }
    }

    private static final class ChannelzFileDescriptorSupplier extends ChannelzBaseDescriptorSupplier {
        ChannelzFileDescriptorSupplier() {
        }
    }

    private static final class ChannelzMethodDescriptorSupplier extends ChannelzBaseDescriptorSupplier implements ProtoMethodDescriptorSupplier {
        private final String methodName;

        ChannelzMethodDescriptorSupplier(String str) {
            this.methodName = str;
        }

        @Override // io.grpc.protobuf.ProtoMethodDescriptorSupplier
        public Descriptors.MethodDescriptor getMethodDescriptor() {
            return getServiceDescriptor().findMethodByName(this.methodName);
        }
    }
}
