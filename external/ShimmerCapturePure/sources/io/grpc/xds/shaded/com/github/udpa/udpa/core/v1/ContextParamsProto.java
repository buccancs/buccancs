package io.grpc.xds.shaded.com.github.udpa.udpa.core.v1;

import com.google.protobuf.Descriptors;
import com.google.protobuf.ExtensionRegistry;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageV3;
import udpa.annotations.Status;

/* loaded from: classes3.dex */
public final class ContextParamsProto {
    static final Descriptors.Descriptor internal_static_udpa_core_v1_ContextParams_ParamsEntry_descriptor;
    static final GeneratedMessageV3.FieldAccessorTable internal_static_udpa_core_v1_ContextParams_ParamsEntry_fieldAccessorTable;
    static final Descriptors.Descriptor internal_static_udpa_core_v1_ContextParams_descriptor;
    static final GeneratedMessageV3.FieldAccessorTable internal_static_udpa_core_v1_ContextParams_fieldAccessorTable;
    private static Descriptors.FileDescriptor descriptor = Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(new String[]{"\n!udpa/core/v1/context_params.proto\u0012\fudpa.core.v1\u001a\u001dudpa/annotations/status.proto\"w\n\rContextParams\u00127\n\u0006params\u0018\u0001 \u0003(\u000b2'.udpa.core.v1.ContextParams.ParamsEntry\u001a-\n\u000bParamsEntry\u0012\u000b\n\u0003key\u0018\u0001 \u0001(\t\u0012\r\n\u0005value\u0018\u0002 \u0001(\t:\u00028\u0001B<\n\u001ccom.github.udpa.udpa.core.v1B\u0012ContextParamsProtoP\u0001º\u0080ÈÑ\u0006\u0002\b\u0001b\u0006proto3"}, new Descriptors.FileDescriptor[]{Status.getDescriptor()});

    static {
        Descriptors.Descriptor descriptor2 = (Descriptors.Descriptor) getDescriptor().getMessageTypes().get(0);
        internal_static_udpa_core_v1_ContextParams_descriptor = descriptor2;
        internal_static_udpa_core_v1_ContextParams_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor2, new String[]{"Params"});
        Descriptors.Descriptor descriptor3 = (Descriptors.Descriptor) descriptor2.getNestedTypes().get(0);
        internal_static_udpa_core_v1_ContextParams_ParamsEntry_descriptor = descriptor3;
        internal_static_udpa_core_v1_ContextParams_ParamsEntry_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor3, new String[]{"Key", "Value"});
        ExtensionRegistry extensionRegistryNewInstance = ExtensionRegistry.newInstance();
        extensionRegistryNewInstance.add(Status.fileStatus);
        Descriptors.FileDescriptor.internalUpdateFileDescriptor(descriptor, extensionRegistryNewInstance);
        Status.getDescriptor();
    }

    private ContextParamsProto() {
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
