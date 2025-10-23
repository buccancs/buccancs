package io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.auth;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;
import io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.auth.Secret;

/* loaded from: classes3.dex */
public interface SecretOrBuilder extends MessageOrBuilder {
    GenericSecret getGenericSecret();

    GenericSecretOrBuilder getGenericSecretOrBuilder();

    String getName();

    ByteString getNameBytes();

    TlsSessionTicketKeys getSessionTicketKeys();

    TlsSessionTicketKeysOrBuilder getSessionTicketKeysOrBuilder();

    TlsCertificate getTlsCertificate();

    TlsCertificateOrBuilder getTlsCertificateOrBuilder();

    Secret.TypeCase getTypeCase();

    CertificateValidationContext getValidationContext();

    CertificateValidationContextOrBuilder getValidationContextOrBuilder();

    boolean hasGenericSecret();

    boolean hasSessionTicketKeys();

    boolean hasTlsCertificate();

    boolean hasValidationContext();
}
