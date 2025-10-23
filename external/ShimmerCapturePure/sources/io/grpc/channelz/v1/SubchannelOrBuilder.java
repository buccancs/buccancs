package io.grpc.channelz.v1;

import com.google.protobuf.MessageOrBuilder;

import java.util.List;

/* loaded from: classes2.dex */
public interface SubchannelOrBuilder extends MessageOrBuilder {
    ChannelRef getChannelRef(int i);

    int getChannelRefCount();

    List<ChannelRef> getChannelRefList();

    ChannelRefOrBuilder getChannelRefOrBuilder(int i);

    List<? extends ChannelRefOrBuilder> getChannelRefOrBuilderList();

    ChannelData getData();

    ChannelDataOrBuilder getDataOrBuilder();

    SubchannelRef getRef();

    SubchannelRefOrBuilder getRefOrBuilder();

    SocketRef getSocketRef(int i);

    int getSocketRefCount();

    List<SocketRef> getSocketRefList();

    SocketRefOrBuilder getSocketRefOrBuilder(int i);

    List<? extends SocketRefOrBuilder> getSocketRefOrBuilderList();

    SubchannelRef getSubchannelRef(int i);

    int getSubchannelRefCount();

    List<SubchannelRef> getSubchannelRefList();

    SubchannelRefOrBuilder getSubchannelRefOrBuilder(int i);

    List<? extends SubchannelRefOrBuilder> getSubchannelRefOrBuilderList();

    boolean hasData();

    boolean hasRef();
}
