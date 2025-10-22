package io.grpc.channelz.v1;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;

/* loaded from: classes2.dex */
public interface SocketOrBuilder extends MessageOrBuilder {
    SocketData getData();

    SocketDataOrBuilder getDataOrBuilder();

    Address getLocal();

    AddressOrBuilder getLocalOrBuilder();

    SocketRef getRef();

    SocketRefOrBuilder getRefOrBuilder();

    Address getRemote();

    String getRemoteName();

    ByteString getRemoteNameBytes();

    AddressOrBuilder getRemoteOrBuilder();

    Security getSecurity();

    SecurityOrBuilder getSecurityOrBuilder();

    boolean hasData();

    boolean hasLocal();

    boolean hasRef();

    boolean hasRemote();

    boolean hasSecurity();
}
