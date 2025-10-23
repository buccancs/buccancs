package io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.auth;

import com.google.protobuf.AnyProto;
import com.google.protobuf.Descriptors;
import com.google.protobuf.ExtensionRegistry;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageV3;
import com.google.protobuf.StructProto;
import com.google.protobuf.WrappersProto;
import io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.BaseProto;
import io.grpc.xds.shaded.io.envoyproxy.envoy.type.matcher.StringProto;
import io.grpc.xds.shaded.io.envoyproxy.pgv.validate.Validate;
import udpa.annotations.Migrate;
import udpa.annotations.Sensitive;
import udpa.annotations.Status;

/* loaded from: classes3.dex */
public final class CommonProto {
    static final Descriptors.Descriptor internal_static_envoy_api_v2_auth_CertificateValidationContext_descriptor;
    static final GeneratedMessageV3.FieldAccessorTable internal_static_envoy_api_v2_auth_CertificateValidationContext_fieldAccessorTable;
    static final Descriptors.Descriptor internal_static_envoy_api_v2_auth_PrivateKeyProvider_descriptor;
    static final GeneratedMessageV3.FieldAccessorTable internal_static_envoy_api_v2_auth_PrivateKeyProvider_fieldAccessorTable;
    static final Descriptors.Descriptor internal_static_envoy_api_v2_auth_TlsCertificate_descriptor;
    static final GeneratedMessageV3.FieldAccessorTable internal_static_envoy_api_v2_auth_TlsCertificate_fieldAccessorTable;
    static final Descriptors.Descriptor internal_static_envoy_api_v2_auth_TlsParameters_descriptor;
    static final GeneratedMessageV3.FieldAccessorTable internal_static_envoy_api_v2_auth_TlsParameters_fieldAccessorTable;
    static final Descriptors.Descriptor internal_static_envoy_api_v2_auth_TlsSessionTicketKeys_descriptor;
    static final GeneratedMessageV3.FieldAccessorTable internal_static_envoy_api_v2_auth_TlsSessionTicketKeys_fieldAccessorTable;
    private static Descriptors.FileDescriptor descriptor = Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(new String[]{"\n\u001eenvoy/api/v2/auth/common.proto\u0012\u0011envoy.api.v2.auth\u001a\u001cenvoy/api/v2/core/base.proto\u001a\u001fenvoy/type/matcher/string.proto\u001a\u0019google/protobuf/any.proto\u001a\u001cgoogle/protobuf/struct.proto\u001a\u001egoogle/protobuf/wrappers.proto\u001a\u001eudpa/annotations/migrate.proto\u001a udpa/annotations/sensitive.proto\u001a\u001dudpa/annotations/status.proto\u001a\u0017validate/validate.proto\"È\u0002\n\rTlsParameters\u0012\\\n\u001ctls_minimum_protocol_version\u0018\u0001 \u0001(\u000e2,.envoy.api.v2.auth.TlsParameters.TlsProtocolB\búB\u0005\u0082\u0001\u0002\u0010\u0001\u0012\\\n\u001ctls_maximum_protocol_version\u0018\u0002 \u0001(\u000e2,.envoy.api.v2.auth.TlsParameters.TlsProtocolB\búB\u0005\u0082\u0001\u0002\u0010\u0001\u0012\u0015\n\rcipher_suites\u0018\u0003 \u0003(\t\u0012\u0013\n\u000becdh_curves\u0018\u0004 \u0003(\t\"O\n\u000bTlsProtocol\u0012\f\n\bTLS_AUTO\u0010\u0000\u0012\u000b\n\u0007TLSv1_0\u0010\u0001\u0012\u000b\n\u0007TLSv1_1\u0010\u0002\u0012\u000b\n\u0007TLSv1_2\u0010\u0003\u0012\u000b\n\u0007TLSv1_3\u0010\u0004\"®\u0001\n\u0012PrivateKeyProvider\u0012\u001e\n\rprovider_name\u0018\u0001 \u0001(\tB\u0007úB\u0004r\u0002 \u0001\u00123\n\u0006config\u0018\u0002 \u0001(\u000b2\u0017.google.protobuf.StructB\b\u0018\u0001¸·\u008b¤\u0002\u0001H\u0000\u00124\n\ftyped_config\u0018\u0003 \u0001(\u000b2\u0014.google.protobuf.AnyB\u0006¸·\u008b¤\u0002\u0001H\u0000B\r\n\u000bconfig_type\"ý\u0002\n\u000eTlsCertificate\u00128\n\u0011certificate_chain\u0018\u0001 \u0001(\u000b2\u001d.envoy.api.v2.core.DataSource\u0012:\n\u000bprivate_key\u0018\u0002 \u0001(\u000b2\u001d.envoy.api.v2.core.DataSourceB\u0006¸·\u008b¤\u0002\u0001\u0012C\n\u0014private_key_provider\u0018\u0006 \u0001(\u000b2%.envoy.api.v2.auth.PrivateKeyProvider\u00127\n\bpassword\u0018\u0003 \u0001(\u000b2\u001d.envoy.api.v2.core.DataSourceB\u0006¸·\u008b¤\u0002\u0001\u00122\n\u000bocsp_staple\u0018\u0004 \u0001(\u000b2\u001d.envoy.api.v2.core.DataSource\u0012C\n\u001csigned_certificate_timestamp\u0018\u0005 \u0003(\u000b2\u001d.envoy.api.v2.core.DataSource\"S\n\u0014TlsSessionTicketKeys\u0012;\n\u0004keys\u0018\u0001 \u0003(\u000b2\u001d.envoy.api.v2.core.DataSourceB\u000eúB\u0005\u0092\u0001\u0002\b\u0001¸·\u008b¤\u0002\u0001\"ª\u0005\n\u001cCertificateValidationContext\u00121\n\ntrusted_ca\u0018\u0001 \u0001(\u000b2\u001d.envoy.api.v2.core.DataSource\u0012/\n\u0017verify_certificate_spki\u0018\u0003 \u0003(\tB\u000eúB\u000b\u0092\u0001\b\"\u0006r\u0004 ,(,\u0012/\n\u0017verify_certificate_hash\u0018\u0002 \u0003(\tB\u000eúB\u000b\u0092\u0001\b\"\u0006r\u0004 @(_\u0012#\n\u0017verify_subject_alt_name\u0018\u0004 \u0003(\tB\u0002\u0018\u0001\u0012B\n\u0017match_subject_alt_names\u0018\t \u0003(\u000b2!.envoy.type.matcher.StringMatcher\u00127\n\u0013require_ocsp_staple\u0018\u0005 \u0001(\u000b2\u001a.google.protobuf.BoolValue\u0012H\n$require_signed_certificate_timestamp\u0018\u0006 \u0001(\u000b2\u001a.google.protobuf.BoolValue\u0012*\n\u0003crl\u0018\u0007 \u0001(\u000b2\u001d.envoy.api.v2.core.DataSource\u0012!\n\u0019allow_expired_certificate\u0018\b \u0001(\b\u0012r\n\u0018trust_chain_verification\u0018\n \u0001(\u000e2F.envoy.api.v2.auth.CertificateValidationContext.TrustChainVerificationB\búB\u0005\u0082\u0001\u0002\u0010\u0001\"F\n\u0016TrustChainVerification\u0012\u0016\n\u0012VERIFY_TRUST_CHAIN\u0010\u0000\u0012\u0014\n\u0010ACCEPT_UNTRUSTED\u0010\u0001Bi\n\u001fio.envoyproxy.envoy.api.v2.authB\u000bCommonProtoP\u0001ò\u0098þ\u008f\u0005+\u0012)envoy.extensions.transport_sockets.tls.v3º\u0080ÈÑ\u0006\u0002\u0010\u0001b\u0006proto3"}, new Descriptors.FileDescriptor[]{BaseProto.getDescriptor(), StringProto.getDescriptor(), AnyProto.getDescriptor(), StructProto.getDescriptor(), WrappersProto.getDescriptor(), Migrate.getDescriptor(), Sensitive.getDescriptor(), Status.getDescriptor(), Validate.getDescriptor()});

    static {
        Descriptors.Descriptor descriptor2 = (Descriptors.Descriptor) getDescriptor().getMessageTypes().get(0);
        internal_static_envoy_api_v2_auth_TlsParameters_descriptor = descriptor2;
        internal_static_envoy_api_v2_auth_TlsParameters_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor2, new String[]{"TlsMinimumProtocolVersion", "TlsMaximumProtocolVersion", "CipherSuites", "EcdhCurves"});
        Descriptors.Descriptor descriptor3 = (Descriptors.Descriptor) getDescriptor().getMessageTypes().get(1);
        internal_static_envoy_api_v2_auth_PrivateKeyProvider_descriptor = descriptor3;
        internal_static_envoy_api_v2_auth_PrivateKeyProvider_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor3, new String[]{"ProviderName", "Config", "TypedConfig", "ConfigType"});
        Descriptors.Descriptor descriptor4 = (Descriptors.Descriptor) getDescriptor().getMessageTypes().get(2);
        internal_static_envoy_api_v2_auth_TlsCertificate_descriptor = descriptor4;
        internal_static_envoy_api_v2_auth_TlsCertificate_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor4, new String[]{"CertificateChain", "PrivateKey", "PrivateKeyProvider", "Password", "OcspStaple", "SignedCertificateTimestamp"});
        Descriptors.Descriptor descriptor5 = (Descriptors.Descriptor) getDescriptor().getMessageTypes().get(3);
        internal_static_envoy_api_v2_auth_TlsSessionTicketKeys_descriptor = descriptor5;
        internal_static_envoy_api_v2_auth_TlsSessionTicketKeys_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor5, new String[]{"Keys"});
        Descriptors.Descriptor descriptor6 = (Descriptors.Descriptor) getDescriptor().getMessageTypes().get(4);
        internal_static_envoy_api_v2_auth_CertificateValidationContext_descriptor = descriptor6;
        internal_static_envoy_api_v2_auth_CertificateValidationContext_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor6, new String[]{"TrustedCa", "VerifyCertificateSpki", "VerifyCertificateHash", "VerifySubjectAltName", "MatchSubjectAltNames", "RequireOcspStaple", "RequireSignedCertificateTimestamp", "Crl", "AllowExpiredCertificate", "TrustChainVerification"});
        ExtensionRegistry extensionRegistryNewInstance = ExtensionRegistry.newInstance();
        extensionRegistryNewInstance.add(Migrate.fileMigrate);
        extensionRegistryNewInstance.add(Status.fileStatus);
        extensionRegistryNewInstance.add(Sensitive.sensitive);
        extensionRegistryNewInstance.add(Validate.rules);
        Descriptors.FileDescriptor.internalUpdateFileDescriptor(descriptor, extensionRegistryNewInstance);
        BaseProto.getDescriptor();
        StringProto.getDescriptor();
        AnyProto.getDescriptor();
        StructProto.getDescriptor();
        WrappersProto.getDescriptor();
        Migrate.getDescriptor();
        Sensitive.getDescriptor();
        Status.getDescriptor();
        Validate.getDescriptor();
    }

    private CommonProto() {
    }

    public static Descriptors.FileDescriptor getDescriptor() {
        return descriptor;
    }

    public static void registerAllExtensions(ExtensionRegistryLite extensionRegistryLite) {
    }

    public static void registerAllExtensions(ExtensionRegistry extensionRegistry) {
        registerAllExtensions((ExtensionRegistryLite) extensionRegistry);
    }
}
