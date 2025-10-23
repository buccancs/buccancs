package io.grpc.channelz.v1;

import com.google.protobuf.MessageOrBuilder;

import java.util.List;

/* loaded from: classes2.dex */
public interface ServerOrBuilder extends MessageOrBuilder {
    ServerData getData();

    ServerDataOrBuilder getDataOrBuilder();

    SocketRef getListenSocket(int i);

    int getListenSocketCount();

    List<SocketRef> getListenSocketList();

    SocketRefOrBuilder getListenSocketOrBuilder(int i);

    List<? extends SocketRefOrBuilder> getListenSocketOrBuilderList();

    ServerRef getRef();

    ServerRefOrBuilder getRefOrBuilder();

    boolean hasData();

    boolean hasRef();
}
