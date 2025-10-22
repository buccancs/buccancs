package io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.auth;

import com.google.protobuf.Descriptors;
import com.google.protobuf.ExtensionRegistry;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageV3;
import io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.BaseProto;
import io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.ConfigSourceProto;
import udpa.annotations.Migrate;
import udpa.annotations.Sensitive;
import udpa.annotations.Status;

/* loaded from: classes3.dex */
public final class SecretProto {
    static final Descriptors.Descriptor internal_static_envoy_api_v2_auth_GenericSecret_descriptor;
    static final GeneratedMessageV3.FieldAccessorTable internal_static_envoy_api_v2_auth_GenericSecret_fieldAccessorTable;
    static final Descriptors.Descriptor internal_static_envoy_api_v2_auth_SdsSecretConfig_descriptor;
    static final GeneratedMessageV3.FieldAccessorTable internal_static_envoy_api_v2_auth_SdsSecretConfig_fieldAccessorTable;
    static final Descriptors.Descriptor internal_static_envoy_api_v2_auth_Secret_descriptor;
    static final GeneratedMessageV3.FieldAccessorTable internal_static_envoy_api_v2_auth_Secret_fieldAccessorTable;
    private static Descriptors.FileDescriptor descriptor = Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(new String[]{"\n\u001eenvoy/api/v2/auth/secret.proto\u0012\u0011envoy.api.v2.auth\u001a\u001eenvoy/api/v2/auth/common.proto\u001a\u001cenvoy/api/v2/core/base.proto\u001a%envoy/api/v2/core/config_source.proto\u001a\u001eudpa/annotations/migrate.proto\u001a udpa/annotations/sensitive.proto\u001a\u001dudpa/annotations/status.proto\"F\n\rGenericSecret\u00125\n\u0006secret\u0018\u0001 \u0001(\u000b2\u001d.envoy.api.v2.core.DataSourceB\u0006¸·\u008b¤\u0002\u0001\"T\n\u000fSdsSecretConfig\u0012\f\n\u0004name\u0018\u0001 \u0001(\t\u00123\n\nsds_config\u0018\u0002 \u0001(\u000b2\u001f.envoy.api.v2.core.ConfigSource\"¯\u0002\n\u0006Secret\u0012\f\n\u0004name\u0018\u0001 \u0001(\t\u0012<\n\u000ftls_certificate\u0018\u0002 \u0001(\u000b2!.envoy.api.v2.auth.TlsCertificateH\u0000\u0012F\n\u0013session_ticket_keys\u0018\u0003 \u0001(\u000b2'.envoy.api.v2.auth.TlsSessionTicketKeysH\u0000\u0012M\n\u0012validation_context\u0018\u0004 \u0001(\u000b2/.envoy.api.v2.auth.CertificateValidationContextH\u0000\u0012:\n\u000egeneric_secret\u0018\u0005 \u0001(\u000b2 .envoy.api.v2.auth.GenericSecretH\u0000B\u0006\n\u0004typeBi\n\u001fio.envoyproxy.envoy.api.v2.authB\u000bSecretProtoP\u0001ò\u0098þ\u008f\u0005+\u0012)envoy.extensions.transport_sockets.tls.v3º\u0080ÈÑ\u0006\u0002\u0010\u0001b\u0006proto3"}, new Descriptors.FileDescriptor[]{CommonProto.getDescriptor(), BaseProto.getDescriptor(), ConfigSourceProto.getDescriptor(), Migrate.getDescriptor(), Sensitive.getDescriptor(), Status.getDescriptor()});

    static {
        Descriptors.Descriptor descriptor2 = (Descriptors.Descriptor) getDescriptor().getMessageTypes().get(0);
        internal_static_envoy_api_v2_auth_GenericSecret_descriptor = descriptor2;
        internal_static_envoy_api_v2_auth_GenericSecret_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor2, new String[]{"Secret"});
        Descriptors.Descriptor descriptor3 = (Descriptors.Descriptor) getDescriptor().getMessageTypes().get(1);
        internal_static_envoy_api_v2_auth_SdsSecretConfig_descriptor = descriptor3;
        internal_static_envoy_api_v2_auth_SdsSecretConfig_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor3, new String[]{"Name", "SdsConfig"});
        Descriptors.Descriptor descriptor4 = (Descriptors.Descriptor) getDescriptor().getMessageTypes().get(2);
        internal_static_envoy_api_v2_auth_Secret_descriptor = descriptor4;
        internal_static_envoy_api_v2_auth_Secret_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor4, new String[]{"Name", "TlsCertificate", "SessionTicketKeys", "ValidationContext", "GenericSecret", "Type"});
        ExtensionRegistry extensionRegistryNewInstance = ExtensionRegistry.newInstance();
        extensionRegistryNewInstance.add(Migrate.fileMigrate);
        extensionRegistryNewInstance.add(Status.fileStatus);
        extensionRegistryNewInstance.add(Sensitive.sensitive);
        Descriptors.FileDescriptor.internalUpdateFileDescriptor(descriptor, extensionRegistryNewInstance);
        CommonProto.getDescriptor();
        BaseProto.getDescriptor();
        ConfigSourceProto.getDescriptor();
        Migrate.getDescriptor();
        Sensitive.getDescriptor();
        Status.getDescriptor();
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
