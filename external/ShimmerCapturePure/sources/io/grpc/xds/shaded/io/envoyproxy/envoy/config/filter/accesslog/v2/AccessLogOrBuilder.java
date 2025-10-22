package io.grpc.xds.shaded.io.envoyproxy.envoy.config.filter.accesslog.v2;

import com.google.protobuf.Any;
import com.google.protobuf.AnyOrBuilder;
import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;
import com.google.protobuf.Struct;
import com.google.protobuf.StructOrBuilder;
import io.grpc.xds.shaded.io.envoyproxy.envoy.config.filter.accesslog.v2.AccessLog;

/* loaded from: classes6.dex */
public interface AccessLogOrBuilder extends MessageOrBuilder {
    @Deprecated
    Struct getConfig();

    @Deprecated
    StructOrBuilder getConfigOrBuilder();

    AccessLog.ConfigTypeCase getConfigTypeCase();

    AccessLogFilter getFilter();

    AccessLogFilterOrBuilder getFilterOrBuilder();

    String getName();

    ByteString getNameBytes();

    Any getTypedConfig();

    AnyOrBuilder getTypedConfigOrBuilder();

    @Deprecated
    boolean hasConfig();

    boolean hasFilter();

    boolean hasTypedConfig();
}
