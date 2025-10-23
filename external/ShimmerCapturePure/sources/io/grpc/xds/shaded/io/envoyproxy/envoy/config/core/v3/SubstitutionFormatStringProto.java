package io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3;

import com.google.protobuf.Descriptors;
import com.google.protobuf.ExtensionRegistry;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageV3;
import com.google.protobuf.StructProto;
import io.grpc.xds.shaded.io.envoyproxy.pgv.validate.Validate;
import udpa.annotations.Status;

/* loaded from: classes6.dex */
public final class SubstitutionFormatStringProto {
    static final Descriptors.Descriptor internal_static_envoy_config_core_v3_SubstitutionFormatString_descriptor;
    static final GeneratedMessageV3.FieldAccessorTable internal_static_envoy_config_core_v3_SubstitutionFormatString_fieldAccessorTable;
    private static Descriptors.FileDescriptor descriptor = Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(new String[]{"\n5envoy/config/core/v3/substitution_format_string.proto\u0012\u0014envoy.config.core.v3\u001a\u001cgoogle/protobuf/struct.proto\u001a\u001dudpa/annotations/status.proto\u001a\u0017validate/validate.proto\"\u0083\u0001\n\u0018SubstitutionFormatString\u0012\u001e\n\u000btext_format\u0018\u0001 \u0001(\tB\u0007úB\u0004r\u0002 \u0001H\u0000\u00128\n\u000bjson_format\u0018\u0002 \u0001(\u000b2\u0017.google.protobuf.StructB\búB\u0005\u008a\u0001\u0002\u0010\u0001H\u0000B\r\n\u0006format\u0012\u0003øB\u0001BM\n\"io.envoyproxy.envoy.config.core.v3B\u001dSubstitutionFormatStringProtoP\u0001º\u0080ÈÑ\u0006\u0002\u0010\u0002b\u0006proto3"}, new Descriptors.FileDescriptor[]{StructProto.getDescriptor(), Status.getDescriptor(), Validate.getDescriptor()});

    static {
        Descriptors.Descriptor descriptor2 = (Descriptors.Descriptor) getDescriptor().getMessageTypes().get(0);
        internal_static_envoy_config_core_v3_SubstitutionFormatString_descriptor = descriptor2;
        internal_static_envoy_config_core_v3_SubstitutionFormatString_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor2, new String[]{"TextFormat", "JsonFormat", "Format"});
        ExtensionRegistry extensionRegistryNewInstance = ExtensionRegistry.newInstance();
        extensionRegistryNewInstance.add(Status.fileStatus);
        extensionRegistryNewInstance.add(Validate.required);
        extensionRegistryNewInstance.add(Validate.rules);
        Descriptors.FileDescriptor.internalUpdateFileDescriptor(descriptor, extensionRegistryNewInstance);
        StructProto.getDescriptor();
        Status.getDescriptor();
        Validate.getDescriptor();
    }

    private SubstitutionFormatStringProto() {
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
