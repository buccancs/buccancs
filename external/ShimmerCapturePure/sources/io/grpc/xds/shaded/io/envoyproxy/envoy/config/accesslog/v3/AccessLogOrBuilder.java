package io.grpc.xds.shaded.io.envoyproxy.envoy.config.accesslog.v3;

import com.google.protobuf.Any;
import com.google.protobuf.AnyOrBuilder;
import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;
import io.grpc.xds.shaded.io.envoyproxy.envoy.config.accesslog.v3.AccessLog;

/* loaded from: classes2.dex */
public interface AccessLogOrBuilder extends MessageOrBuilder {
    AccessLog.ConfigTypeCase getConfigTypeCase();

    AccessLogFilter getFilter();

    AccessLogFilterOrBuilder getFilterOrBuilder();

    String getName();

    ByteString getNameBytes();

    Any getTypedConfig();

    AnyOrBuilder getTypedConfigOrBuilder();

    boolean hasFilter();

    boolean hasTypedConfig();
}
