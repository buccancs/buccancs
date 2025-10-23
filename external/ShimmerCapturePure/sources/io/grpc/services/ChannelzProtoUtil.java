package io.grpc.services;

import com.google.common.base.Preconditions;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.protobuf.Any;
import com.google.protobuf.ByteString;
import com.google.protobuf.Int64Value;
import com.google.protobuf.UninitializedMessageException;
import com.google.protobuf.util.Durations;
import com.google.protobuf.util.Timestamps;
import io.grpc.ConnectivityState;
import io.grpc.InternalChannelz;
import io.grpc.InternalInstrumented;
import io.grpc.InternalWithLogId;
import io.grpc.Status;
import io.grpc.channelz.v1.Address;
import io.grpc.channelz.v1.Channel;
import io.grpc.channelz.v1.ChannelConnectivityState;
import io.grpc.channelz.v1.ChannelData;
import io.grpc.channelz.v1.ChannelRef;
import io.grpc.channelz.v1.ChannelTrace;
import io.grpc.channelz.v1.ChannelTraceEvent;
import io.grpc.channelz.v1.GetServerSocketsResponse;
import io.grpc.channelz.v1.GetServersResponse;
import io.grpc.channelz.v1.GetTopChannelsResponse;
import io.grpc.channelz.v1.Security;
import io.grpc.channelz.v1.Server;
import io.grpc.channelz.v1.ServerData;
import io.grpc.channelz.v1.ServerRef;
import io.grpc.channelz.v1.Socket;
import io.grpc.channelz.v1.SocketData;
import io.grpc.channelz.v1.SocketOption;
import io.grpc.channelz.v1.SocketOptionLinger;
import io.grpc.channelz.v1.SocketOptionTcpInfo;
import io.grpc.channelz.v1.SocketOptionTimeout;
import io.grpc.channelz.v1.SocketRef;
import io.grpc.channelz.v1.Subchannel;
import io.grpc.channelz.v1.SubchannelRef;

import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.security.cert.CertificateEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;

/* loaded from: classes3.dex */
final class ChannelzProtoUtil {
    public static final String SO_LINGER = "SO_LINGER";
    public static final String SO_TIMEOUT = "SO_TIMEOUT";
    public static final String TCP_INFO = "TCP_INFO";
    private static final Logger logger = Logger.getLogger(ChannelzProtoUtil.class.getName());

    private ChannelzProtoUtil() {
    }

    static ChannelRef toChannelRef(InternalWithLogId internalWithLogId) {
        return ChannelRef.newBuilder().setChannelId(internalWithLogId.getLogId().getId()).setName(internalWithLogId.toString()).m7634build();
    }

    static SubchannelRef toSubchannelRef(InternalWithLogId internalWithLogId) {
        return SubchannelRef.newBuilder().setSubchannelId(internalWithLogId.getLogId().getId()).setName(internalWithLogId.toString()).m9061build();
    }

    static ServerRef toServerRef(InternalWithLogId internalWithLogId) {
        return ServerRef.newBuilder().setServerId(internalWithLogId.getLogId().getId()).setName(internalWithLogId.toString()).m8647build();
    }

    static SocketRef toSocketRef(InternalWithLogId internalWithLogId) {
        return SocketRef.newBuilder().setSocketId(internalWithLogId.getLogId().getId()).setName(internalWithLogId.toString()).m8969build();
    }

    static Server toServer(InternalInstrumented<InternalChannelz.ServerStats> internalInstrumented) {
        InternalChannelz.ServerStats serverStats = (InternalChannelz.ServerStats) getFuture(internalInstrumented.getStats());
        Server.Builder data = Server.newBuilder().setRef(toServerRef(internalInstrumented)).setData(toServerData(serverStats));
        Iterator<InternalInstrumented<InternalChannelz.SocketStats>> it2 = serverStats.listenSockets.iterator();
        while (it2.hasNext()) {
            data.addListenSocket(toSocketRef(it2.next()));
        }
        return data.m8555build();
    }

    static ServerData toServerData(InternalChannelz.ServerStats serverStats) {
        return ServerData.newBuilder().setCallsStarted(serverStats.callsStarted).setCallsSucceeded(serverStats.callsSucceeded).setCallsFailed(serverStats.callsFailed).setLastCallStartedTimestamp(Timestamps.fromNanos(serverStats.lastCallStartedNanos)).m8601build();
    }

    static Security toSecurity(InternalChannelz.Security security) {
        Preconditions.checkNotNull(security);
        Preconditions.checkState((security.tls != null) ^ (security.other != null), "one of tls or othersecurity must be non null");
        if (security.tls != null) {
            Security.Tls.Builder standardName = Security.Tls.newBuilder().setStandardName(security.tls.cipherSuiteStandardName);
            try {
                if (security.tls.localCert != null) {
                    standardName.setLocalCertificate(ByteString.copyFrom(security.tls.localCert.getEncoded()));
                }
                if (security.tls.remoteCert != null) {
                    standardName.setRemoteCertificate(ByteString.copyFrom(security.tls.remoteCert.getEncoded()));
                }
            } catch (CertificateEncodingException e) {
                logger.log(Level.FINE, "Caught exception", (Throwable) e);
            }
            return Security.newBuilder().setTls(standardName).m8417build();
        }
        Security.OtherSecurity.Builder name = Security.OtherSecurity.newBuilder().setName(security.other.name);
        if (security.other.any != null) {
            name.setValue((Any) security.other.any);
        }
        return Security.newBuilder().setOther(name).m8417build();
    }

    static Socket toSocket(InternalInstrumented<InternalChannelz.SocketStats> internalInstrumented) {
        InternalChannelz.SocketStats socketStats = (InternalChannelz.SocketStats) getFuture(internalInstrumented.getStats());
        Socket.Builder local = Socket.newBuilder().setRef(toSocketRef(internalInstrumented)).setLocal(toAddress(socketStats.local));
        if (socketStats.security != null) {
            local.setSecurity(toSecurity(socketStats.security));
        }
        if (socketStats.remote != null) {
            local.setRemote(toAddress(socketStats.remote));
        }
        local.setData(extractSocketData(socketStats));
        return local.m8693build();
    }

    static Address toAddress(SocketAddress socketAddress) {
        Preconditions.checkNotNull(socketAddress);
        Address.Builder builderNewBuilder = Address.newBuilder();
        if (socketAddress instanceof InetSocketAddress) {
            InetSocketAddress inetSocketAddress = (InetSocketAddress) socketAddress;
            builderNewBuilder.setTcpipAddress(Address.TcpIpAddress.newBuilder().setIpAddress(ByteString.copyFrom(inetSocketAddress.getAddress().getAddress())).setPort(inetSocketAddress.getPort()).m7403build());
        } else if (socketAddress.getClass().getName().endsWith("io.netty.channel.unix.DomainSocketAddress")) {
            builderNewBuilder.setUdsAddress(Address.UdsAddress.newBuilder().setFilename(socketAddress.toString()).m7449build());
        } else {
            builderNewBuilder.setOtherAddress(Address.OtherAddress.newBuilder().setName(socketAddress.toString()).m7357build());
        }
        return builderNewBuilder.m7311build();
    }

    static SocketData extractSocketData(InternalChannelz.SocketStats socketStats) {
        SocketData.Builder builderNewBuilder = SocketData.newBuilder();
        if (socketStats.data != null) {
            InternalChannelz.TransportStats transportStats = socketStats.data;
            builderNewBuilder.setStreamsStarted(transportStats.streamsStarted).setStreamsSucceeded(transportStats.streamsSucceeded).setStreamsFailed(transportStats.streamsFailed).setMessagesSent(transportStats.messagesSent).setMessagesReceived(transportStats.messagesReceived).setKeepAlivesSent(transportStats.keepAlivesSent).setLastLocalStreamCreatedTimestamp(Timestamps.fromNanos(transportStats.lastLocalStreamCreatedTimeNanos)).setLastRemoteStreamCreatedTimestamp(Timestamps.fromNanos(transportStats.lastRemoteStreamCreatedTimeNanos)).setLastMessageSentTimestamp(Timestamps.fromNanos(transportStats.lastMessageSentTimeNanos)).setLastMessageReceivedTimestamp(Timestamps.fromNanos(transportStats.lastMessageReceivedTimeNanos)).setLocalFlowControlWindow(Int64Value.of(transportStats.localFlowControlWindow)).setRemoteFlowControlWindow(Int64Value.of(transportStats.remoteFlowControlWindow));
        }
        builderNewBuilder.addAllOption(toSocketOptionsList(socketStats.socketOptions));
        return builderNewBuilder.m8739build();
    }

    static SocketOption toSocketOptionLinger(int i) throws UninitializedMessageException {
        SocketOptionLinger defaultInstance;
        if (i >= 0) {
            defaultInstance = SocketOptionLinger.newBuilder().setActive(true).setDuration(Durations.fromSeconds(i)).m8831build();
        } else {
            defaultInstance = SocketOptionLinger.getDefaultInstance();
        }
        return SocketOption.newBuilder().setName(SO_LINGER).setAdditional(Any.pack(defaultInstance)).m8785build();
    }

    static SocketOption toSocketOptionTimeout(String str, int i) {
        Preconditions.checkNotNull(str);
        return SocketOption.newBuilder().setName(str).setAdditional(Any.pack(SocketOptionTimeout.newBuilder().setDuration(Durations.fromMillis(i)).m8923build())).m8785build();
    }

    static SocketOption toSocketOptionTcpInfo(InternalChannelz.TcpInfo tcpInfo) throws UninitializedMessageException {
        return SocketOption.newBuilder().setName(TCP_INFO).setAdditional(Any.pack(SocketOptionTcpInfo.newBuilder().setTcpiState(tcpInfo.state).setTcpiCaState(tcpInfo.caState).setTcpiRetransmits(tcpInfo.retransmits).setTcpiProbes(tcpInfo.probes).setTcpiBackoff(tcpInfo.backoff).setTcpiOptions(tcpInfo.options).setTcpiSndWscale(tcpInfo.sndWscale).setTcpiRcvWscale(tcpInfo.rcvWscale).setTcpiRto(tcpInfo.rto).setTcpiAto(tcpInfo.ato).setTcpiSndMss(tcpInfo.sndMss).setTcpiRcvMss(tcpInfo.rcvMss).setTcpiUnacked(tcpInfo.unacked).setTcpiSacked(tcpInfo.sacked).setTcpiLost(tcpInfo.lost).setTcpiRetrans(tcpInfo.retrans).setTcpiFackets(tcpInfo.fackets).setTcpiLastDataSent(tcpInfo.lastDataSent).setTcpiLastAckSent(tcpInfo.lastAckSent).setTcpiLastDataRecv(tcpInfo.lastDataRecv).setTcpiLastAckRecv(tcpInfo.lastAckRecv).setTcpiPmtu(tcpInfo.pmtu).setTcpiRcvSsthresh(tcpInfo.rcvSsthresh).setTcpiRtt(tcpInfo.rtt).setTcpiRttvar(tcpInfo.rttvar).setTcpiSndSsthresh(tcpInfo.sndSsthresh).setTcpiSndCwnd(tcpInfo.sndCwnd).setTcpiAdvmss(tcpInfo.advmss).setTcpiReordering(tcpInfo.reordering).m8877build())).m8785build();
    }

    static SocketOption toSocketOptionAdditional(String str, String str2) {
        Preconditions.checkNotNull(str);
        Preconditions.checkNotNull(str2);
        return SocketOption.newBuilder().setName(str).setValue(str2).m8785build();
    }

    static List<SocketOption> toSocketOptionsList(InternalChannelz.SocketOptions socketOptions) {
        Preconditions.checkNotNull(socketOptions);
        ArrayList arrayList = new ArrayList();
        if (socketOptions.lingerSeconds != null) {
            arrayList.add(toSocketOptionLinger(socketOptions.lingerSeconds.intValue()));
        }
        if (socketOptions.soTimeoutMillis != null) {
            arrayList.add(toSocketOptionTimeout(SO_TIMEOUT, socketOptions.soTimeoutMillis.intValue()));
        }
        if (socketOptions.tcpInfo != null) {
            arrayList.add(toSocketOptionTcpInfo(socketOptions.tcpInfo));
        }
        for (Map.Entry<String, String> entry : socketOptions.others.entrySet()) {
            arrayList.add(toSocketOptionAdditional(entry.getKey(), entry.getValue()));
        }
        return arrayList;
    }

    static Channel toChannel(InternalInstrumented<InternalChannelz.ChannelStats> internalInstrumented) {
        InternalChannelz.ChannelStats channelStats = (InternalChannelz.ChannelStats) getFuture(internalInstrumented.getStats());
        Channel.Builder data = Channel.newBuilder().setRef(toChannelRef(internalInstrumented)).setData(extractChannelData(channelStats));
        Iterator<InternalWithLogId> it2 = channelStats.subchannels.iterator();
        while (it2.hasNext()) {
            data.addSubchannelRef(toSubchannelRef(it2.next()));
        }
        return data.m7495build();
    }

    static ChannelData extractChannelData(InternalChannelz.ChannelStats channelStats) {
        ChannelData.Builder builderNewBuilder = ChannelData.newBuilder();
        builderNewBuilder.setTarget(channelStats.target).setState(toChannelConnectivityState(channelStats.state)).setCallsStarted(channelStats.callsStarted).setCallsSucceeded(channelStats.callsSucceeded).setCallsFailed(channelStats.callsFailed).setLastCallStartedTimestamp(Timestamps.fromNanos(channelStats.lastCallStartedNanos));
        if (channelStats.channelTrace != null) {
            builderNewBuilder.setTrace(toChannelTrace(channelStats.channelTrace));
        }
        return builderNewBuilder.m7588build();
    }

    static ChannelConnectivityState toChannelConnectivityState(ConnectivityState connectivityState) {
        return ChannelConnectivityState.newBuilder().setState(toState(connectivityState)).m7541build();
    }

    private static ChannelTrace toChannelTrace(InternalChannelz.ChannelTrace channelTrace) {
        return ChannelTrace.newBuilder().setNumEventsLogged(channelTrace.numEventsLogged).setCreationTimestamp(Timestamps.fromNanos(channelTrace.creationTimeNanos)).addAllEvents(toChannelTraceEvents(channelTrace.events)).m7680build();
    }

    private static List<ChannelTraceEvent> toChannelTraceEvents(List<InternalChannelz.ChannelTrace.Event> list) {
        ArrayList arrayList = new ArrayList();
        for (InternalChannelz.ChannelTrace.Event event : list) {
            ChannelTraceEvent.Builder timestamp = ChannelTraceEvent.newBuilder().setDescription(event.description).setSeverity(ChannelTraceEvent.Severity.valueOf(event.severity.name())).setTimestamp(Timestamps.fromNanos(event.timestampNanos));
            if (event.channelRef != null) {
                timestamp.setChannelRef(toChannelRef(event.channelRef));
            }
            if (event.subchannelRef != null) {
                timestamp.setSubchannelRef(toSubchannelRef(event.subchannelRef));
            }
            arrayList.add(timestamp.m7726build());
        }
        return Collections.unmodifiableList(arrayList);
    }

    static ChannelConnectivityState.State toState(ConnectivityState connectivityState) {
        if (connectivityState == null) {
            return ChannelConnectivityState.State.UNKNOWN;
        }
        try {
            return (ChannelConnectivityState.State) Enum.valueOf(ChannelConnectivityState.State.class, connectivityState.name());
        } catch (IllegalArgumentException unused) {
            return ChannelConnectivityState.State.UNKNOWN;
        }
    }

    static Subchannel toSubchannel(InternalInstrumented<InternalChannelz.ChannelStats> internalInstrumented) {
        InternalChannelz.ChannelStats channelStats = (InternalChannelz.ChannelStats) getFuture(internalInstrumented.getStats());
        Subchannel.Builder data = Subchannel.newBuilder().setRef(toSubchannelRef(internalInstrumented)).setData(extractChannelData(channelStats));
        Preconditions.checkState(channelStats.sockets.isEmpty() || channelStats.subchannels.isEmpty());
        Iterator<InternalWithLogId> it2 = channelStats.sockets.iterator();
        while (it2.hasNext()) {
            data.addSocketRef(toSocketRef(it2.next()));
        }
        Iterator<InternalWithLogId> it3 = channelStats.subchannels.iterator();
        while (it3.hasNext()) {
            data.addSubchannelRef(toSubchannelRef(it3.next()));
        }
        return data.m9015build();
    }

    static GetTopChannelsResponse toGetTopChannelResponse(InternalChannelz.RootChannelList rootChannelList) {
        GetTopChannelsResponse.Builder end = GetTopChannelsResponse.newBuilder().setEnd(rootChannelList.end);
        Iterator<InternalInstrumented<InternalChannelz.ChannelStats>> it2 = rootChannelList.channels.iterator();
        while (it2.hasNext()) {
            end.addChannel(toChannel(it2.next()));
        }
        return end.m8371build();
    }

    static GetServersResponse toGetServersResponse(InternalChannelz.ServerList serverList) {
        GetServersResponse.Builder end = GetServersResponse.newBuilder().setEnd(serverList.end);
        Iterator<InternalInstrumented<InternalChannelz.ServerStats>> it2 = serverList.servers.iterator();
        while (it2.hasNext()) {
            end.addServer(toServer(it2.next()));
        }
        return end.m8095build();
    }

    static GetServerSocketsResponse toGetServerSocketsResponse(InternalChannelz.ServerSocketsList serverSocketsList) {
        GetServerSocketsResponse.Builder end = GetServerSocketsResponse.newBuilder().setEnd(serverSocketsList.end);
        Iterator<InternalWithLogId> it2 = serverSocketsList.sockets.iterator();
        while (it2.hasNext()) {
            end.addSocketRef(toSocketRef(it2.next()));
        }
        return end.m8003build();
    }

    private static <T> T getFuture(ListenableFuture<T> listenableFuture) {
        try {
            T t = listenableFuture.get();
            if (t != null) {
                return t;
            }
            throw Status.UNIMPLEMENTED.withDescription("The entity's stats can not be retrieved. If this is an InProcessTransport this is expected.").asRuntimeException();
        } catch (InterruptedException e) {
            throw Status.INTERNAL.withCause(e).asRuntimeException();
        } catch (ExecutionException e2) {
            throw Status.INTERNAL.withCause(e2).asRuntimeException();
        }
    }
}
