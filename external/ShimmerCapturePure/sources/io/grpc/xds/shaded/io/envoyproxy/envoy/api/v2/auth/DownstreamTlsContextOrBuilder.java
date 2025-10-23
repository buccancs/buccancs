package io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.auth;

import com.google.protobuf.BoolValue;
import com.google.protobuf.BoolValueOrBuilder;
import com.google.protobuf.Duration;
import com.google.protobuf.DurationOrBuilder;
import com.google.protobuf.MessageOrBuilder;
import io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.auth.DownstreamTlsContext;

/* loaded from: classes3.dex */
public interface DownstreamTlsContextOrBuilder extends MessageOrBuilder {
    CommonTlsContext getCommonTlsContext();

    CommonTlsContextOrBuilder getCommonTlsContextOrBuilder();

    boolean getDisableStatelessSessionResumption();

    BoolValue getRequireClientCertificate();

    BoolValueOrBuilder getRequireClientCertificateOrBuilder();

    BoolValue getRequireSni();

    BoolValueOrBuilder getRequireSniOrBuilder();

    TlsSessionTicketKeys getSessionTicketKeys();

    TlsSessionTicketKeysOrBuilder getSessionTicketKeysOrBuilder();

    SdsSecretConfig getSessionTicketKeysSdsSecretConfig();

    SdsSecretConfigOrBuilder getSessionTicketKeysSdsSecretConfigOrBuilder();

    DownstreamTlsContext.SessionTicketKeysTypeCase getSessionTicketKeysTypeCase();

    Duration getSessionTimeout();

    DurationOrBuilder getSessionTimeoutOrBuilder();

    boolean hasCommonTlsContext();

    boolean hasRequireClientCertificate();

    boolean hasRequireSni();

    boolean hasSessionTicketKeys();

    boolean hasSessionTicketKeysSdsSecretConfig();

    boolean hasSessionTimeout();
}
