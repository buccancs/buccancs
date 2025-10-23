package io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.transport_sockets.tls.v3;

import com.google.protobuf.AnyProto;
import com.google.protobuf.Descriptors;
import com.google.protobuf.DurationProto;
import com.google.protobuf.ExtensionRegistry;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageV3;
import com.google.protobuf.WrappersProto;
import io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.ExtensionProto;
import io.grpc.xds.shaded.io.envoyproxy.pgv.validate.Validate;
import udpa.annotations.Migrate;
import udpa.annotations.Status;
import udpa.annotations.Versioning;

/* loaded from: classes4.dex */
public final class TlsProto {
    static final Descriptors.Descriptor internal_static_envoy_extensions_transport_sockets_tls_v3_CommonTlsContext_CertificateProviderInstance_descriptor;
    static final GeneratedMessageV3.FieldAccessorTable internal_static_envoy_extensions_transport_sockets_tls_v3_CommonTlsContext_CertificateProviderInstance_fieldAccessorTable;
    static final Descriptors.Descriptor internal_static_envoy_extensions_transport_sockets_tls_v3_CommonTlsContext_CertificateProvider_descriptor;
    static final GeneratedMessageV3.FieldAccessorTable internal_static_envoy_extensions_transport_sockets_tls_v3_CommonTlsContext_CertificateProvider_fieldAccessorTable;
    static final Descriptors.Descriptor internal_static_envoy_extensions_transport_sockets_tls_v3_CommonTlsContext_CombinedCertificateValidationContext_descriptor;
    static final GeneratedMessageV3.FieldAccessorTable internal_static_envoy_extensions_transport_sockets_tls_v3_CommonTlsContext_CombinedCertificateValidationContext_fieldAccessorTable;
    static final Descriptors.Descriptor internal_static_envoy_extensions_transport_sockets_tls_v3_CommonTlsContext_descriptor;
    static final GeneratedMessageV3.FieldAccessorTable internal_static_envoy_extensions_transport_sockets_tls_v3_CommonTlsContext_fieldAccessorTable;
    static final Descriptors.Descriptor internal_static_envoy_extensions_transport_sockets_tls_v3_DownstreamTlsContext_descriptor;
    static final GeneratedMessageV3.FieldAccessorTable internal_static_envoy_extensions_transport_sockets_tls_v3_DownstreamTlsContext_fieldAccessorTable;
    static final Descriptors.Descriptor internal_static_envoy_extensions_transport_sockets_tls_v3_UpstreamTlsContext_descriptor;
    static final GeneratedMessageV3.FieldAccessorTable internal_static_envoy_extensions_transport_sockets_tls_v3_UpstreamTlsContext_fieldAccessorTable;
    private static Descriptors.FileDescriptor descriptor = Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(new String[]{"\n3envoy/extensions/transport_sockets/tls/v3/tls.proto\u0012)envoy.extensions.transport_sockets.tls.v3\u001a$envoy/config/core/v3/extension.proto\u001a6envoy/extensions/transport_sockets/tls/v3/common.proto\u001a6envoy/extensions/transport_sockets/tls/v3/secret.proto\u001a\u0019google/protobuf/any.proto\u001a\u001egoogle/protobuf/duration.proto\u001a\u001egoogle/protobuf/wrappers.proto\u001a\u001eudpa/annotations/migrate.proto\u001a\u001dudpa/annotations/status.proto\u001a!udpa/annotations/versioning.proto\u001a\u0017validate/validate.proto\"\u0086\u0002\n\u0012UpstreamTlsContext\u0012W\n\u0012common_tls_context\u0018\u0001 \u0001(\u000b2;.envoy.extensions.transport_sockets.tls.v3.CommonTlsContext\u0012\u0015\n\u0003sni\u0018\u0002 \u0001(\tB\búB\u0005r\u0003(ÿ\u0001\u0012\u001b\n\u0013allow_renegotiation\u0018\u0003 \u0001(\b\u00126\n\u0010max_session_keys\u0018\u0004 \u0001(\u000b2\u001c.google.protobuf.UInt32Value:+\u009aÅ\u0088\u001e&\n$envoy.api.v2.auth.UpstreamTlsContext\"î\u0004\n\u0014DownstreamTlsContext\u0012W\n\u0012common_tls_context\u0018\u0001 \u0001(\u000b2;.envoy.extensions.transport_sockets.tls.v3.CommonTlsContext\u0012>\n\u001arequire_client_certificate\u0018\u0002 \u0001(\u000b2\u001a.google.protobuf.BoolValue\u0012/\n\u000brequire_sni\u0018\u0003 \u0001(\u000b2\u001a.google.protobuf.BoolValue\u0012^\n\u0013session_ticket_keys\u0018\u0004 \u0001(\u000b2?.envoy.extensions.transport_sockets.tls.v3.TlsSessionTicketKeysH\u0000\u0012k\n%session_ticket_keys_sds_secret_config\u0018\u0005 \u0001(\u000b2:.envoy.extensions.transport_sockets.tls.v3.SdsSecretConfigH\u0000\u0012.\n$disable_stateless_session_resumption\u0018\u0007 \u0001(\bH\u0000\u0012D\n\u000fsession_timeout\u0018\u0006 \u0001(\u000b2\u0019.google.protobuf.DurationB\u0010úB\rª\u0001\n\u001a\u0006\b\u0080\u0080\u0080\u0080\u00102\u0000:-\u009aÅ\u0088\u001e(\n&envoy.api.v2.auth.DownstreamTlsContextB\u001a\n\u0018session_ticket_keys_type\"Ê\u0011\n\u0010CommonTlsContext\u0012L\n\ntls_params\u0018\u0001 \u0001(\u000b28.envoy.extensions.transport_sockets.tls.v3.TlsParameters\u0012S\n\u0010tls_certificates\u0018\u0002 \u0003(\u000b29.envoy.extensions.transport_sockets.tls.v3.TlsCertificate\u0012p\n\"tls_certificate_sds_secret_configs\u0018\u0006 \u0003(\u000b2:.envoy.extensions.transport_sockets.tls.v3.SdsSecretConfigB\búB\u0005\u0092\u0001\u0002\u0010\u0001\u0012}\n$tls_certificate_certificate_provider\u0018\t \u0001(\u000b2O.envoy.extensions.transport_sockets.tls.v3.CommonTlsContext.CertificateProvider\u0012\u008e\u0001\n-tls_certificate_certificate_provider_instance\u0018\u000b \u0001(\u000b2W.envoy.extensions.transport_sockets.tls.v3.CommonTlsContext.CertificateProviderInstance\u0012e\n\u0012validation_context\u0018\u0003 \u0001(\u000b2G.envoy.extensions.transport_sockets.tls.v3.CertificateValidationContextH\u0000\u0012j\n$validation_context_sds_secret_config\u0018\u0007 \u0001(\u000b2:.envoy.extensions.transport_sockets.tls.v3.SdsSecretConfigH\u0000\u0012\u0087\u0001\n\u001bcombined_validation_context\u0018\b \u0001(\u000b2`.envoy.extensions.transport_sockets.tls.v3.CommonTlsContext.CombinedCertificateValidationContextH\u0000\u0012\u0082\u0001\n'validation_context_certificate_provider\u0018\n \u0001(\u000b2O.envoy.extensions.transport_sockets.tls.v3.CommonTlsContext.CertificateProviderH\u0000\u0012\u0093\u0001\n0validation_context_certificate_provider_instance\u0018\f \u0001(\u000b2W.envoy.extensions.transport_sockets.tls.v3.CommonTlsContext.CertificateProviderInstanceH\u0000\u0012\u0016\n\u000ealpn_protocols\u0018\u0004 \u0003(\t\u001a\u007f\n\u0013CertificateProvider\u0012\u0015\n\u0004name\u0018\u0001 \u0001(\tB\u0007úB\u0004r\u0002 \u0001\u0012B\n\ftyped_config\u0018\u0002 \u0001(\u000b2*.envoy.config.core.v3.TypedExtensionConfigH\u0000B\r\n\u0006config\u0012\u0003øB\u0001\u001aN\n\u001bCertificateProviderInstance\u0012\u0015\n\rinstance_name\u0018\u0001 \u0001(\t\u0012\u0018\n\u0010certificate_name\u0018\u0002 \u0001(\t\u001aã\u0005\n$CombinedCertificateValidationContext\u0012u\n\u001adefault_validation_context\u0018\u0001 \u0001(\u000b2G.envoy.extensions.transport_sockets.tls.v3.CertificateValidationContextB\búB\u0005\u008a\u0001\u0002\u0010\u0001\u0012\u0094\u0001\n$validation_context_sds_secret_config\u0018\u0002 \u0001(\u000b2:.envoy.extensions.transport_sockets.tls.v3.SdsSecretConfigB*úB\u0005\u008a\u0001\u0002\u0010\u0001ò\u0098þ\u008f\u0005\u001c\u0012\u001adynamic_validation_context\u0012¤\u0001\n'validation_context_certificate_provider\u0018\u0003 \u0001(\u000b2O.envoy.extensions.transport_sockets.tls.v3.CommonTlsContext.CertificateProviderB\"ò\u0098þ\u008f\u0005\u001c\u0012\u001adynamic_validation_context\u0012µ\u0001\n0validation_context_certificate_provider_instance\u0018\u0004 \u0001(\u000b2W.envoy.extensions.transport_sockets.tls.v3.CommonTlsContext.CertificateProviderInstanceB\"ò\u0098þ\u008f\u0005\u001c\u0012\u001adynamic_validation_context:N\u009aÅ\u0088\u001eI\nGenvoy.api.v2.auth.CommonTlsContext.CombinedCertificateValidationContext:)\u009aÅ\u0088\u001e$\n\"envoy.api.v2.auth.CommonTlsContextB\u0019\n\u0017validation_context_typeJ\u0004\b\u0005\u0010\u0006BM\n7io.envoyproxy.envoy.extensions.transport_sockets.tls.v3B\bTlsProtoP\u0001º\u0080ÈÑ\u0006\u0002\u0010\u0002b\u0006proto3"}, new Descriptors.FileDescriptor[]{ExtensionProto.getDescriptor(), CommonProto.getDescriptor(), SecretProto.getDescriptor(), AnyProto.getDescriptor(), DurationProto.getDescriptor(), WrappersProto.getDescriptor(), Migrate.getDescriptor(), Status.getDescriptor(), Versioning.getDescriptor(), Validate.getDescriptor()});

    static {
        Descriptors.Descriptor descriptor2 = (Descriptors.Descriptor) getDescriptor().getMessageTypes().get(0);
        internal_static_envoy_extensions_transport_sockets_tls_v3_UpstreamTlsContext_descriptor = descriptor2;
        internal_static_envoy_extensions_transport_sockets_tls_v3_UpstreamTlsContext_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor2, new String[]{"CommonTlsContext", "Sni", "AllowRenegotiation", "MaxSessionKeys"});
        Descriptors.Descriptor descriptor3 = (Descriptors.Descriptor) getDescriptor().getMessageTypes().get(1);
        internal_static_envoy_extensions_transport_sockets_tls_v3_DownstreamTlsContext_descriptor = descriptor3;
        internal_static_envoy_extensions_transport_sockets_tls_v3_DownstreamTlsContext_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor3, new String[]{"CommonTlsContext", "RequireClientCertificate", "RequireSni", "SessionTicketKeys", "SessionTicketKeysSdsSecretConfig", "DisableStatelessSessionResumption", "SessionTimeout", "SessionTicketKeysType"});
        Descriptors.Descriptor descriptor4 = (Descriptors.Descriptor) getDescriptor().getMessageTypes().get(2);
        internal_static_envoy_extensions_transport_sockets_tls_v3_CommonTlsContext_descriptor = descriptor4;
        internal_static_envoy_extensions_transport_sockets_tls_v3_CommonTlsContext_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor4, new String[]{"TlsParams", "TlsCertificates", "TlsCertificateSdsSecretConfigs", "TlsCertificateCertificateProvider", "TlsCertificateCertificateProviderInstance", "ValidationContext", "ValidationContextSdsSecretConfig", "CombinedValidationContext", "ValidationContextCertificateProvider", "ValidationContextCertificateProviderInstance", "AlpnProtocols", "ValidationContextType"});
        Descriptors.Descriptor descriptor5 = (Descriptors.Descriptor) descriptor4.getNestedTypes().get(0);
        internal_static_envoy_extensions_transport_sockets_tls_v3_CommonTlsContext_CertificateProvider_descriptor = descriptor5;
        internal_static_envoy_extensions_transport_sockets_tls_v3_CommonTlsContext_CertificateProvider_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor5, new String[]{"Name", "TypedConfig", "Config"});
        Descriptors.Descriptor descriptor6 = (Descriptors.Descriptor) descriptor4.getNestedTypes().get(1);
        internal_static_envoy_extensions_transport_sockets_tls_v3_CommonTlsContext_CertificateProviderInstance_descriptor = descriptor6;
        internal_static_envoy_extensions_transport_sockets_tls_v3_CommonTlsContext_CertificateProviderInstance_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor6, new String[]{"InstanceName", "CertificateName"});
        Descriptors.Descriptor descriptor7 = (Descriptors.Descriptor) descriptor4.getNestedTypes().get(2);
        internal_static_envoy_extensions_transport_sockets_tls_v3_CommonTlsContext_CombinedCertificateValidationContext_descriptor = descriptor7;
        internal_static_envoy_extensions_transport_sockets_tls_v3_CommonTlsContext_CombinedCertificateValidationContext_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor7, new String[]{"DefaultValidationContext", "ValidationContextSdsSecretConfig", "ValidationContextCertificateProvider", "ValidationContextCertificateProviderInstance"});
        ExtensionRegistry extensionRegistryNewInstance = ExtensionRegistry.newInstance();
        extensionRegistryNewInstance.add(Migrate.fieldMigrate);
        extensionRegistryNewInstance.add(Status.fileStatus);
        extensionRegistryNewInstance.add(Versioning.versioning);
        extensionRegistryNewInstance.add(Validate.required);
        extensionRegistryNewInstance.add(Validate.rules);
        Descriptors.FileDescriptor.internalUpdateFileDescriptor(descriptor, extensionRegistryNewInstance);
        ExtensionProto.getDescriptor();
        CommonProto.getDescriptor();
        SecretProto.getDescriptor();
        AnyProto.getDescriptor();
        DurationProto.getDescriptor();
        WrappersProto.getDescriptor();
        Migrate.getDescriptor();
        Status.getDescriptor();
        Versioning.getDescriptor();
        Validate.getDescriptor();
    }

    private TlsProto() {
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
