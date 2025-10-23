package io.grpc.xds.shaded.io.envoyproxy.envoy.config.filter.network.http_connection_manager.v2;

import com.google.protobuf.Any;
import com.google.protobuf.AnyOrBuilder;
import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;
import com.google.protobuf.Struct;
import com.google.protobuf.StructOrBuilder;
import io.grpc.xds.shaded.io.envoyproxy.envoy.config.filter.network.http_connection_manager.v2.HttpFilter;

/* loaded from: classes6.dex */
public interface HttpFilterOrBuilder extends MessageOrBuilder {
    @Deprecated
    Struct getConfig();

    @Deprecated
    StructOrBuilder getConfigOrBuilder();

    HttpFilter.ConfigTypeCase getConfigTypeCase();

    String getName();

    ByteString getNameBytes();

    Any getTypedConfig();

    AnyOrBuilder getTypedConfigOrBuilder();

    @Deprecated
    boolean hasConfig();

    boolean hasTypedConfig();
}
