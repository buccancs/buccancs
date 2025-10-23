package io.grpc.channelz.v1;

import com.google.protobuf.MessageOrBuilder;

import java.util.List;

/* loaded from: classes2.dex */
public interface GetServersResponseOrBuilder extends MessageOrBuilder {
    boolean getEnd();

    Server getServer(int i);

    int getServerCount();

    List<Server> getServerList();

    ServerOrBuilder getServerOrBuilder(int i);

    List<? extends ServerOrBuilder> getServerOrBuilderList();
}
