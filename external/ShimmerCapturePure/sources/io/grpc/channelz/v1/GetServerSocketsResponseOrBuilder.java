package io.grpc.channelz.v1;

import com.google.protobuf.MessageOrBuilder;

import java.util.List;

/* loaded from: classes2.dex */
public interface GetServerSocketsResponseOrBuilder extends MessageOrBuilder {
    boolean getEnd();

    SocketRef getSocketRef(int i);

    int getSocketRefCount();

    List<SocketRef> getSocketRefList();

    SocketRefOrBuilder getSocketRefOrBuilder(int i);

    List<? extends SocketRefOrBuilder> getSocketRefOrBuilderList();
}
