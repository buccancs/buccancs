package io.grpc.xds.shaded.io.envoyproxy.envoy.type.tracing.v2;

import com.google.protobuf.Descriptors;
import com.google.protobuf.ExtensionRegistry;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageV3;
import io.grpc.xds.shaded.io.envoyproxy.envoy.type.metadata.v2.MetadataProto;
import io.grpc.xds.shaded.io.envoyproxy.pgv.validate.Validate;
import udpa.annotations.Status;

/* loaded from: classes4.dex */
public final class CustomTagProto {
    static final Descriptors.Descriptor internal_static_envoy_type_tracing_v2_CustomTag_Environment_descriptor;
    static final GeneratedMessageV3.FieldAccessorTable internal_static_envoy_type_tracing_v2_CustomTag_Environment_fieldAccessorTable;
    static final Descriptors.Descriptor internal_static_envoy_type_tracing_v2_CustomTag_Header_descriptor;
    static final GeneratedMessageV3.FieldAccessorTable internal_static_envoy_type_tracing_v2_CustomTag_Header_fieldAccessorTable;
    static final Descriptors.Descriptor internal_static_envoy_type_tracing_v2_CustomTag_Literal_descriptor;
    static final GeneratedMessageV3.FieldAccessorTable internal_static_envoy_type_tracing_v2_CustomTag_Literal_fieldAccessorTable;
    static final Descriptors.Descriptor internal_static_envoy_type_tracing_v2_CustomTag_Metadata_descriptor;
    static final GeneratedMessageV3.FieldAccessorTable internal_static_envoy_type_tracing_v2_CustomTag_Metadata_fieldAccessorTable;
    static final Descriptors.Descriptor internal_static_envoy_type_tracing_v2_CustomTag_descriptor;
    static final GeneratedMessageV3.FieldAccessorTable internal_static_envoy_type_tracing_v2_CustomTag_fieldAccessorTable;
    private static Descriptors.FileDescriptor descriptor = Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(new String[]{"\n&envoy/type/tracing/v2/custom_tag.proto\u0012\u0015envoy.type.tracing.v2\u001a%envoy/type/metadata/v2/metadata.proto\u001a\u001dudpa/annotations/status.proto\u001a\u0017validate/validate.proto\"ã\u0004\n\tCustomTag\u0012\u0014\n\u0003tag\u0018\u0001 \u0001(\tB\u0007úB\u0004r\u0002 \u0001\u0012;\n\u0007literal\u0018\u0002 \u0001(\u000b2(.envoy.type.tracing.v2.CustomTag.LiteralH\u0000\u0012C\n\u000benvironment\u0018\u0003 \u0001(\u000b2,.envoy.type.tracing.v2.CustomTag.EnvironmentH\u0000\u0012A\n\u000erequest_header\u0018\u0004 \u0001(\u000b2'.envoy.type.tracing.v2.CustomTag.HeaderH\u0000\u0012=\n\bmetadata\u0018\u0005 \u0001(\u000b2).envoy.type.tracing.v2.CustomTag.MetadataH\u0000\u001a!\n\u0007Literal\u0012\u0016\n\u0005value\u0018\u0001 \u0001(\tB\u0007úB\u0004r\u0002 \u0001\u001a;\n\u000bEnvironment\u0012\u0015\n\u0004name\u0018\u0001 \u0001(\tB\u0007úB\u0004r\u0002 \u0001\u0012\u0015\n\rdefault_value\u0018\u0002 \u0001(\t\u001a<\n\u0006Header\u0012\u001b\n\u0004name\u0018\u0001 \u0001(\tB\rúB\nr\b \u0001À\u0001\u0001È\u0001\u0000\u0012\u0015\n\rdefault_value\u0018\u0002 \u0001(\t\u001a\u0090\u0001\n\bMetadata\u00122\n\u0004kind\u0018\u0001 \u0001(\u000b2$.envoy.type.metadata.v2.MetadataKind\u00129\n\fmetadata_key\u0018\u0002 \u0001(\u000b2#.envoy.type.metadata.v2.MetadataKey\u0012\u0015\n\rdefault_value\u0018\u0003 \u0001(\tB\u000b\n\u0004type\u0012\u0003øB\u0001B?\n#io.envoyproxy.envoy.type.tracing.v2B\u000eCustomTagProtoP\u0001º\u0080ÈÑ\u0006\u0002\u0010\u0001b\u0006proto3"}, new Descriptors.FileDescriptor[]{MetadataProto.getDescriptor(), Status.getDescriptor(), Validate.getDescriptor()});

    static {
        Descriptors.Descriptor descriptor2 = (Descriptors.Descriptor) getDescriptor().getMessageTypes().get(0);
        internal_static_envoy_type_tracing_v2_CustomTag_descriptor = descriptor2;
        internal_static_envoy_type_tracing_v2_CustomTag_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor2, new String[]{"Tag", "Literal", "Environment", "RequestHeader", "Metadata", "Type"});
        Descriptors.Descriptor descriptor3 = (Descriptors.Descriptor) descriptor2.getNestedTypes().get(0);
        internal_static_envoy_type_tracing_v2_CustomTag_Literal_descriptor = descriptor3;
        internal_static_envoy_type_tracing_v2_CustomTag_Literal_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor3, new String[]{"Value"});
        Descriptors.Descriptor descriptor4 = (Descriptors.Descriptor) descriptor2.getNestedTypes().get(1);
        internal_static_envoy_type_tracing_v2_CustomTag_Environment_descriptor = descriptor4;
        internal_static_envoy_type_tracing_v2_CustomTag_Environment_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor4, new String[]{"Name", "DefaultValue"});
        Descriptors.Descriptor descriptor5 = (Descriptors.Descriptor) descriptor2.getNestedTypes().get(2);
        internal_static_envoy_type_tracing_v2_CustomTag_Header_descriptor = descriptor5;
        internal_static_envoy_type_tracing_v2_CustomTag_Header_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor5, new String[]{"Name", "DefaultValue"});
        Descriptors.Descriptor descriptor6 = (Descriptors.Descriptor) descriptor2.getNestedTypes().get(3);
        internal_static_envoy_type_tracing_v2_CustomTag_Metadata_descriptor = descriptor6;
        internal_static_envoy_type_tracing_v2_CustomTag_Metadata_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor6, new String[]{"Kind", "MetadataKey", "DefaultValue"});
        ExtensionRegistry extensionRegistryNewInstance = ExtensionRegistry.newInstance();
        extensionRegistryNewInstance.add(Status.fileStatus);
        extensionRegistryNewInstance.add(Validate.required);
        extensionRegistryNewInstance.add(Validate.rules);
        Descriptors.FileDescriptor.internalUpdateFileDescriptor(descriptor, extensionRegistryNewInstance);
        MetadataProto.getDescriptor();
        Status.getDescriptor();
        Validate.getDescriptor();
    }

    private CustomTagProto() {
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
