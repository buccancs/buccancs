package io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.transport_sockets.tls.v3;

import com.google.protobuf.BoolValue;
import com.google.protobuf.BoolValueOrBuilder;
import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;
import io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.DataSource;
import io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.DataSourceOrBuilder;
import io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.transport_sockets.tls.v3.CertificateValidationContext;
import io.grpc.xds.shaded.io.envoyproxy.envoy.type.matcher.v3.StringMatcher;
import io.grpc.xds.shaded.io.envoyproxy.envoy.type.matcher.v3.StringMatcherOrBuilder;

import java.util.List;

/* loaded from: classes4.dex */
public interface CertificateValidationContextOrBuilder extends MessageOrBuilder {
    boolean getAllowExpiredCertificate();

    DataSource getCrl();

    DataSourceOrBuilder getCrlOrBuilder();

    StringMatcher getMatchSubjectAltNames(int i);

    int getMatchSubjectAltNamesCount();

    List<StringMatcher> getMatchSubjectAltNamesList();

    StringMatcherOrBuilder getMatchSubjectAltNamesOrBuilder(int i);

    List<? extends StringMatcherOrBuilder> getMatchSubjectAltNamesOrBuilderList();

    BoolValue getRequireOcspStaple();

    BoolValueOrBuilder getRequireOcspStapleOrBuilder();

    BoolValue getRequireSignedCertificateTimestamp();

    BoolValueOrBuilder getRequireSignedCertificateTimestampOrBuilder();

    CertificateValidationContext.TrustChainVerification getTrustChainVerification();

    int getTrustChainVerificationValue();

    DataSource getTrustedCa();

    DataSourceOrBuilder getTrustedCaOrBuilder();

    String getVerifyCertificateHash(int i);

    ByteString getVerifyCertificateHashBytes(int i);

    int getVerifyCertificateHashCount();

    /* renamed from: getVerifyCertificateHashList */
    List<String> mo31599getVerifyCertificateHashList();

    String getVerifyCertificateSpki(int i);

    ByteString getVerifyCertificateSpkiBytes(int i);

    int getVerifyCertificateSpkiCount();

    /* renamed from: getVerifyCertificateSpkiList */
    List<String> mo31600getVerifyCertificateSpkiList();

    boolean hasCrl();

    boolean hasRequireOcspStaple();

    boolean hasRequireSignedCertificateTimestamp();

    boolean hasTrustedCa();
}
