package io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.auth;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;
import com.google.protobuf.UInt32Value;
import com.google.protobuf.UInt32ValueOrBuilder;

/* loaded from: classes3.dex */
public interface UpstreamTlsContextOrBuilder extends MessageOrBuilder {
    boolean getAllowRenegotiation();

    CommonTlsContext getCommonTlsContext();

    CommonTlsContextOrBuilder getCommonTlsContextOrBuilder();

    UInt32Value getMaxSessionKeys();

    UInt32ValueOrBuilder getMaxSessionKeysOrBuilder();

    String getSni();

    ByteString getSniBytes();

    boolean hasCommonTlsContext();

    boolean hasMaxSessionKeys();
}
