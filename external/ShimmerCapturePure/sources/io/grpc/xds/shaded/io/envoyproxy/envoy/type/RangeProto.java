package io.grpc.xds.shaded.io.envoyproxy.envoy.type;

import com.google.protobuf.Descriptors;
import com.google.protobuf.ExtensionRegistry;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageV3;
import udpa.annotations.Status;

/* loaded from: classes4.dex */
public final class RangeProto {
    static final Descriptors.Descriptor internal_static_envoy_type_DoubleRange_descriptor;
    static final GeneratedMessageV3.FieldAccessorTable internal_static_envoy_type_DoubleRange_fieldAccessorTable;
    static final Descriptors.Descriptor internal_static_envoy_type_Int32Range_descriptor;
    static final GeneratedMessageV3.FieldAccessorTable internal_static_envoy_type_Int32Range_fieldAccessorTable;
    static final Descriptors.Descriptor internal_static_envoy_type_Int64Range_descriptor;
    static final GeneratedMessageV3.FieldAccessorTable internal_static_envoy_type_Int64Range_fieldAccessorTable;
    private static Descriptors.FileDescriptor descriptor = Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(new String[]{"\n\u0016envoy/type/range.proto\u0012\nenvoy.type\u001a\u001dudpa/annotations/status.proto\"(\n\nInt64Range\u0012\r\n\u0005start\u0018\u0001 \u0001(\u0003\u0012\u000b\n\u0003end\u0018\u0002 \u0001(\u0003\"(\n\nInt32Range\u0012\r\n\u0005start\u0018\u0001 \u0001(\u0005\u0012\u000b\n\u0003end\u0018\u0002 \u0001(\u0005\")\n\u000bDoubleRange\u0012\r\n\u0005start\u0018\u0001 \u0001(\u0001\u0012\u000b\n\u0003end\u0018\u0002 \u0001(\u0001B0\n\u0018io.envoyproxy.envoy.typeB\nRangeProtoP\u0001º\u0080ÈÑ\u0006\u0002\u0010\u0001b\u0006proto3"}, new Descriptors.FileDescriptor[]{Status.getDescriptor()});

    static {
        Descriptors.Descriptor descriptor2 = (Descriptors.Descriptor) getDescriptor().getMessageTypes().get(0);
        internal_static_envoy_type_Int64Range_descriptor = descriptor2;
        internal_static_envoy_type_Int64Range_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor2, new String[]{"Start", "End"});
        Descriptors.Descriptor descriptor3 = (Descriptors.Descriptor) getDescriptor().getMessageTypes().get(1);
        internal_static_envoy_type_Int32Range_descriptor = descriptor3;
        internal_static_envoy_type_Int32Range_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor3, new String[]{"Start", "End"});
        Descriptors.Descriptor descriptor4 = (Descriptors.Descriptor) getDescriptor().getMessageTypes().get(2);
        internal_static_envoy_type_DoubleRange_descriptor = descriptor4;
        internal_static_envoy_type_DoubleRange_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor4, new String[]{"Start", "End"});
        ExtensionRegistry extensionRegistryNewInstance = ExtensionRegistry.newInstance();
        extensionRegistryNewInstance.add(Status.fileStatus);
        Descriptors.FileDescriptor.internalUpdateFileDescriptor(descriptor, extensionRegistryNewInstance);
        Status.getDescriptor();
    }

    private RangeProto() {
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
