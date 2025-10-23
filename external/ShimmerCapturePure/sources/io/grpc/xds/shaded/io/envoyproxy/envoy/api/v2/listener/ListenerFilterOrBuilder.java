package io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.listener;

import com.google.protobuf.Any;
import com.google.protobuf.AnyOrBuilder;
import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;
import com.google.protobuf.Struct;
import com.google.protobuf.StructOrBuilder;
import io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.listener.ListenerFilter;

/* loaded from: classes5.dex */
public interface ListenerFilterOrBuilder extends MessageOrBuilder {
    @Deprecated
    Struct getConfig();

    @Deprecated
    StructOrBuilder getConfigOrBuilder();

    ListenerFilter.ConfigTypeCase getConfigTypeCase();

    ListenerFilterChainMatchPredicate getFilterDisabled();

    ListenerFilterChainMatchPredicateOrBuilder getFilterDisabledOrBuilder();

    String getName();

    ByteString getNameBytes();

    Any getTypedConfig();

    AnyOrBuilder getTypedConfigOrBuilder();

    @Deprecated
    boolean hasConfig();

    boolean hasFilterDisabled();

    boolean hasTypedConfig();
}
