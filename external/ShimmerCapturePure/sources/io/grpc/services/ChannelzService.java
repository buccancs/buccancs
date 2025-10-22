package io.grpc.services;

import io.grpc.InternalChannelz;
import io.grpc.InternalInstrumented;
import io.grpc.Status;
import io.grpc.StatusRuntimeException;
import io.grpc.channelz.v1.ChannelzGrpc;
import io.grpc.channelz.v1.GetChannelRequest;
import io.grpc.channelz.v1.GetChannelResponse;
import io.grpc.channelz.v1.GetServerSocketsRequest;
import io.grpc.channelz.v1.GetServerSocketsResponse;
import io.grpc.channelz.v1.GetServersRequest;
import io.grpc.channelz.v1.GetServersResponse;
import io.grpc.channelz.v1.GetSocketRequest;
import io.grpc.channelz.v1.GetSocketResponse;
import io.grpc.channelz.v1.GetSubchannelRequest;
import io.grpc.channelz.v1.GetSubchannelResponse;
import io.grpc.channelz.v1.GetTopChannelsRequest;
import io.grpc.channelz.v1.GetTopChannelsResponse;
import io.grpc.stub.StreamObserver;

/* loaded from: classes3.dex */
public final class ChannelzService extends ChannelzGrpc.ChannelzImplBase {
    private final InternalChannelz channelz;
    private final int maxPageSize;

    ChannelzService(InternalChannelz internalChannelz, int i) {
        this.channelz = internalChannelz;
        this.maxPageSize = i;
    }

    public static ChannelzService newInstance(int i) {
        return new ChannelzService(InternalChannelz.instance(), i);
    }

    @Override // io.grpc.channelz.v1.ChannelzGrpc.ChannelzImplBase
    public void getTopChannels(GetTopChannelsRequest getTopChannelsRequest, StreamObserver<GetTopChannelsResponse> streamObserver) {
        try {
            streamObserver.onNext(ChannelzProtoUtil.toGetTopChannelResponse(this.channelz.getRootChannels(getTopChannelsRequest.getStartChannelId(), this.maxPageSize)));
            streamObserver.onCompleted();
        } catch (StatusRuntimeException e) {
            streamObserver.onError(e);
        }
    }

    @Override // io.grpc.channelz.v1.ChannelzGrpc.ChannelzImplBase
    public void getChannel(GetChannelRequest getChannelRequest, StreamObserver<GetChannelResponse> streamObserver) {
        InternalInstrumented<InternalChannelz.ChannelStats> rootChannel = this.channelz.getRootChannel(getChannelRequest.getChannelId());
        if (rootChannel == null) {
            streamObserver.onError(Status.NOT_FOUND.withDescription("Can't find channel " + getChannelRequest.getChannelId()).asRuntimeException());
            return;
        }
        try {
            streamObserver.onNext(GetChannelResponse.newBuilder().setChannel(ChannelzProtoUtil.toChannel(rootChannel)).m7819build());
            streamObserver.onCompleted();
        } catch (StatusRuntimeException e) {
            streamObserver.onError(e);
        }
    }

    @Override // io.grpc.channelz.v1.ChannelzGrpc.ChannelzImplBase
    public void getServers(GetServersRequest getServersRequest, StreamObserver<GetServersResponse> streamObserver) {
        try {
            streamObserver.onNext(ChannelzProtoUtil.toGetServersResponse(this.channelz.getServers(getServersRequest.getStartServerId(), this.maxPageSize)));
            streamObserver.onCompleted();
        } catch (StatusRuntimeException e) {
            streamObserver.onError(e);
        }
    }

    @Override // io.grpc.channelz.v1.ChannelzGrpc.ChannelzImplBase
    public void getSubchannel(GetSubchannelRequest getSubchannelRequest, StreamObserver<GetSubchannelResponse> streamObserver) {
        InternalInstrumented<InternalChannelz.ChannelStats> subchannel = this.channelz.getSubchannel(getSubchannelRequest.getSubchannelId());
        if (subchannel == null) {
            streamObserver.onError(Status.NOT_FOUND.withDescription("Can't find subchannel " + getSubchannelRequest.getSubchannelId()).asRuntimeException());
            return;
        }
        try {
            streamObserver.onNext(GetSubchannelResponse.newBuilder().setSubchannel(ChannelzProtoUtil.toSubchannel(subchannel)).m8279build());
            streamObserver.onCompleted();
        } catch (StatusRuntimeException e) {
            streamObserver.onError(e);
        }
    }

    @Override // io.grpc.channelz.v1.ChannelzGrpc.ChannelzImplBase
    public void getSocket(GetSocketRequest getSocketRequest, StreamObserver<GetSocketResponse> streamObserver) {
        InternalInstrumented<InternalChannelz.SocketStats> socket = this.channelz.getSocket(getSocketRequest.getSocketId());
        if (socket == null) {
            streamObserver.onError(Status.NOT_FOUND.withDescription("Can't find socket " + getSocketRequest.getSocketId()).asRuntimeException());
            return;
        }
        try {
            streamObserver.onNext(GetSocketResponse.newBuilder().setSocket(ChannelzProtoUtil.toSocket(socket)).m8187build());
            streamObserver.onCompleted();
        } catch (StatusRuntimeException e) {
            streamObserver.onError(e);
        }
    }

    @Override // io.grpc.channelz.v1.ChannelzGrpc.ChannelzImplBase
    public void getServerSockets(GetServerSocketsRequest getServerSocketsRequest, StreamObserver<GetServerSocketsResponse> streamObserver) {
        InternalChannelz.ServerSocketsList serverSockets = this.channelz.getServerSockets(getServerSocketsRequest.getServerId(), getServerSocketsRequest.getStartSocketId(), this.maxPageSize);
        if (serverSockets == null) {
            streamObserver.onError(Status.NOT_FOUND.withDescription("Can't find server " + getServerSocketsRequest.getServerId()).asRuntimeException());
            return;
        }
        try {
            streamObserver.onNext(ChannelzProtoUtil.toGetServerSocketsResponse(serverSockets));
            streamObserver.onCompleted();
        } catch (StatusRuntimeException e) {
            streamObserver.onError(e);
        }
    }
}
