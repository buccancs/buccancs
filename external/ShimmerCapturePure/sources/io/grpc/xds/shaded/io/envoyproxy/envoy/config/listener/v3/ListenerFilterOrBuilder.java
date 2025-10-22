package io.grpc.xds.shaded.io.envoyproxy.envoy.config.listener.v3;

import com.google.protobuf.Any;
import com.google.protobuf.AnyOrBuilder;
import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;
import io.grpc.xds.shaded.io.envoyproxy.envoy.config.listener.v3.ListenerFilter;

/* loaded from: classes6.dex */
public interface ListenerFilterOrBuilder extends MessageOrBuilder {
    ListenerFilter.ConfigTypeCase getConfigTypeCase();

    ListenerFilterChainMatchPredicate getFilterDisabled();

    ListenerFilterChainMatchPredicateOrBuilder getFilterDisabledOrBuilder();

    String getName();

    ByteString getNameBytes();

    Any getTypedConfig();

    AnyOrBuilder getTypedConfigOrBuilder();

    boolean hasFilterDisabled();

    boolean hasTypedConfig();
}
