package io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.transport_sockets.tls.v3;

import com.google.protobuf.Descriptors;
import com.google.protobuf.ExtensionRegistry;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageV3;
import io.grpc.xds.shaded.com.github.udpa.udpa.core.v1.ResourceLocatorProto;
import io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.BaseProto;
import io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.ConfigSourceProto;
import udpa.annotations.Migrate;
import udpa.annotations.Sensitive;
import udpa.annotations.Status;
import udpa.annotations.Versioning;

/* loaded from: classes4.dex */
public final class SecretProto {
    static final Descriptors.Descriptor internal_static_envoy_extensions_transport_sockets_tls_v3_GenericSecret_descriptor;
    static final GeneratedMessageV3.FieldAccessorTable internal_static_envoy_extensions_transport_sockets_tls_v3_GenericSecret_fieldAccessorTable;
    static final Descriptors.Descriptor internal_static_envoy_extensions_transport_sockets_tls_v3_SdsSecretConfig_descriptor;
    static final GeneratedMessageV3.FieldAccessorTable internal_static_envoy_extensions_transport_sockets_tls_v3_SdsSecretConfig_fieldAccessorTable;
    static final Descriptors.Descriptor internal_static_envoy_extensions_transport_sockets_tls_v3_Secret_descriptor;
    static final GeneratedMessageV3.FieldAccessorTable internal_static_envoy_extensions_transport_sockets_tls_v3_Secret_fieldAccessorTable;
    private static Descriptors.FileDescriptor descriptor = Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(new String[]{"\n6envoy/extensions/transport_sockets/tls/v3/secret.proto\u0012)envoy.extensions.transport_sockets.tls.v3\u001a\u001fenvoy/config/core/v3/base.proto\u001a(envoy/config/core/v3/config_source.proto\u001a6envoy/extensions/transport_sockets/tls/v3/common.proto\u001a#udpa/core/v1/resource_locator.proto\u001a\u001eudpa/annotations/migrate.proto\u001a udpa/annotations/sensitive.proto\u001a\u001dudpa/annotations/status.proto\u001a!udpa/annotations/versioning.proto\"q\n\rGenericSecret\u00128\n\u0006secret\u0018\u0001 \u0001(\u000b2 .envoy.config.core.v3.DataSourceB\u0006¸·\u008b¤\u0002\u0001:&\u009aÅ\u0088\u001e!\n\u001fenvoy.api.v2.auth.GenericSecret\"î\u0001\n\u000fSdsSecretConfig\u0012$\n\u0004name\u0018\u0001 \u0001(\tB\u0016ò\u0098þ\u008f\u0005\u0010\u0012\u000ename_specifier\u0012S\n\u0014sds_resource_locator\u0018\u0003 \u0001(\u000b2\u001d.udpa.core.v1.ResourceLocatorB\u0016ò\u0098þ\u008f\u0005\u0010\u0012\u000ename_specifier\u00126\n\nsds_config\u0018\u0002 \u0001(\u000b2\".envoy.config.core.v3.ConfigSource:(\u009aÅ\u0088\u001e#\n!envoy.api.v2.auth.SdsSecretConfig\"°\u0003\n\u0006Secret\u0012\f\n\u0004name\u0018\u0001 \u0001(\t\u0012T\n\u000ftls_certificate\u0018\u0002 \u0001(\u000b29.envoy.extensions.transport_sockets.tls.v3.TlsCertificateH\u0000\u0012^\n\u0013session_ticket_keys\u0018\u0003 \u0001(\u000b2?.envoy.extensions.transport_sockets.tls.v3.TlsSessionTicketKeysH\u0000\u0012e\n\u0012validation_context\u0018\u0004 \u0001(\u000b2G.envoy.extensions.transport_sockets.tls.v3.CertificateValidationContextH\u0000\u0012R\n\u000egeneric_secret\u0018\u0005 \u0001(\u000b28.envoy.extensions.transport_sockets.tls.v3.GenericSecretH\u0000:\u001f\u009aÅ\u0088\u001e\u001a\n\u0018envoy.api.v2.auth.SecretB\u0006\n\u0004typeBP\n7io.envoyproxy.envoy.extensions.transport_sockets.tls.v3B\u000bSecretProtoP\u0001º\u0080ÈÑ\u0006\u0002\u0010\u0002b\u0006proto3"}, new Descriptors.FileDescriptor[]{BaseProto.getDescriptor(), ConfigSourceProto.getDescriptor(), CommonProto.getDescriptor(), ResourceLocatorProto.getDescriptor(), Migrate.getDescriptor(), Sensitive.getDescriptor(), Status.getDescriptor(), Versioning.getDescriptor()});

    static {
        Descriptors.Descriptor descriptor2 = (Descriptors.Descriptor) getDescriptor().getMessageTypes().get(0);
        internal_static_envoy_extensions_transport_sockets_tls_v3_GenericSecret_descriptor = descriptor2;
        internal_static_envoy_extensions_transport_sockets_tls_v3_GenericSecret_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor2, new String[]{"Secret"});
        Descriptors.Descriptor descriptor3 = (Descriptors.Descriptor) getDescriptor().getMessageTypes().get(1);
        internal_static_envoy_extensions_transport_sockets_tls_v3_SdsSecretConfig_descriptor = descriptor3;
        internal_static_envoy_extensions_transport_sockets_tls_v3_SdsSecretConfig_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor3, new String[]{"Name", "SdsResourceLocator", "SdsConfig"});
        Descriptors.Descriptor descriptor4 = (Descriptors.Descriptor) getDescriptor().getMessageTypes().get(2);
        internal_static_envoy_extensions_transport_sockets_tls_v3_Secret_descriptor = descriptor4;
        internal_static_envoy_extensions_transport_sockets_tls_v3_Secret_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor4, new String[]{"Name", "TlsCertificate", "SessionTicketKeys", "ValidationContext", "GenericSecret", "Type"});
        ExtensionRegistry extensionRegistryNewInstance = ExtensionRegistry.newInstance();
        extensionRegistryNewInstance.add(Migrate.fieldMigrate);
        extensionRegistryNewInstance.add(Status.fileStatus);
        extensionRegistryNewInstance.add(Sensitive.sensitive);
        extensionRegistryNewInstance.add(Versioning.versioning);
        Descriptors.FileDescriptor.internalUpdateFileDescriptor(descriptor, extensionRegistryNewInstance);
        BaseProto.getDescriptor();
        ConfigSourceProto.getDescriptor();
        CommonProto.getDescriptor();
        ResourceLocatorProto.getDescriptor();
        Migrate.getDescriptor();
        Sensitive.getDescriptor();
        Status.getDescriptor();
        Versioning.getDescriptor();
    }

    private SecretProto() {
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
