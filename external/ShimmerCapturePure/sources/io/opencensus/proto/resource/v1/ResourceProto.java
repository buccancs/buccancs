package io.opencensus.proto.resource.v1;

import com.google.protobuf.Descriptors;
import com.google.protobuf.ExtensionRegistry;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageV3;

/* loaded from: classes4.dex */
public final class ResourceProto {
    static final Descriptors.Descriptor internal_static_opencensus_proto_resource_v1_Resource_LabelsEntry_descriptor;
    static final GeneratedMessageV3.FieldAccessorTable internal_static_opencensus_proto_resource_v1_Resource_LabelsEntry_fieldAccessorTable;
    static final Descriptors.Descriptor internal_static_opencensus_proto_resource_v1_Resource_descriptor;
    static final GeneratedMessageV3.FieldAccessorTable internal_static_opencensus_proto_resource_v1_Resource_fieldAccessorTable;
    private static Descriptors.FileDescriptor descriptor;

    static {
        Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(new String[]{"\n+opencensus/proto/resource/v1/resource.proto\u0012\u001copencensus.proto.resource.v1\"\u008b\u0001\n\bResource\u0012\f\n\u0004type\u0018\u0001 \u0001(\t\u0012B\n\u0006labels\u0018\u0002 \u0003(\u000b22.opencensus.proto.resource.v1.Resource.LabelsEntry\u001a-\n\u000bLabelsEntry\u0012\u000b\n\u0003key\u0018\u0001 \u0001(\t\u0012\r\n\u0005value\u0018\u0002 \u0001(\t:\u00028\u0001By\n\u001fio.opencensus.proto.resource.v1B\rResourceProtoP\u0001ZEgithub.com/census-instrumentation/opencensus-proto/gen-go/resource/v1b\u0006proto3"}, new Descriptors.FileDescriptor[0], new Descriptors.FileDescriptor.InternalDescriptorAssigner() { // from class: io.opencensus.proto.resource.v1.ResourceProto.1
            public ExtensionRegistry assignDescriptors(Descriptors.FileDescriptor fileDescriptor) {
                Descriptors.FileDescriptor unused = ResourceProto.descriptor = fileDescriptor;
                return null;
            }
        });
        Descriptors.Descriptor descriptor2 = (Descriptors.Descriptor) getDescriptor().getMessageTypes().get(0);
        internal_static_opencensus_proto_resource_v1_Resource_descriptor = descriptor2;
        internal_static_opencensus_proto_resource_v1_Resource_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor2, new String[]{"Type", "Labels"});
        Descriptors.Descriptor descriptor3 = (Descriptors.Descriptor) descriptor2.getNestedTypes().get(0);
        internal_static_opencensus_proto_resource_v1_Resource_LabelsEntry_descriptor = descriptor3;
        internal_static_opencensus_proto_resource_v1_Resource_LabelsEntry_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor3, new String[]{"Key", "Value"});
    }

    private ResourceProto() {
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
