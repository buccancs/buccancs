package io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core;

import com.google.protobuf.BoolValue;
import com.google.protobuf.BoolValueOrBuilder;
import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;
import io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.Http1ProtocolOptions;

/* loaded from: classes3.dex */
public interface Http1ProtocolOptionsOrBuilder extends MessageOrBuilder {
    boolean getAcceptHttp10();

    BoolValue getAllowAbsoluteUrl();

    BoolValueOrBuilder getAllowAbsoluteUrlOrBuilder();

    String getDefaultHostForHttp10();

    ByteString getDefaultHostForHttp10Bytes();

    boolean getEnableTrailers();

    Http1ProtocolOptions.HeaderKeyFormat getHeaderKeyFormat();

    Http1ProtocolOptions.HeaderKeyFormatOrBuilder getHeaderKeyFormatOrBuilder();

    boolean hasAllowAbsoluteUrl();

    boolean hasHeaderKeyFormat();
}
