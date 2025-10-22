package io.grpc.xds.shaded.io.envoyproxy.envoy.type;

import com.google.protobuf.Descriptors;
import com.google.protobuf.ExtensionRegistry;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageV3;
import io.grpc.xds.shaded.io.envoyproxy.pgv.validate.Validate;
import udpa.annotations.Status;

/* loaded from: classes4.dex */
public final class PercentProto {
    static final Descriptors.Descriptor internal_static_envoy_type_FractionalPercent_descriptor;
    static final GeneratedMessageV3.FieldAccessorTable internal_static_envoy_type_FractionalPercent_fieldAccessorTable;
    static final Descriptors.Descriptor internal_static_envoy_type_Percent_descriptor;
    static final GeneratedMessageV3.FieldAccessorTable internal_static_envoy_type_Percent_fieldAccessorTable;
    private static Descriptors.FileDescriptor descriptor = Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(new String[]{"\n\u0018envoy/type/percent.proto\u0012\nenvoy.type\u001a\u001dudpa/annotations/status.proto\u001a\u0017validate/validate.proto\"1\n\u0007Percent\u0012&\n\u0005value\u0018\u0001 \u0001(\u0001B\u0017úB\u0014\u0012\u0012\u0019\u0000\u0000\u0000\u0000\u0000\u0000Y@)\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\"³\u0001\n\u0011FractionalPercent\u0012\u0011\n\tnumerator\u0018\u0001 \u0001(\r\u0012L\n\u000bdenominator\u0018\u0002 \u0001(\u000e2-.envoy.type.FractionalPercent.DenominatorTypeB\búB\u0005\u0082\u0001\u0002\u0010\u0001\"=\n\u000fDenominatorType\u0012\u000b\n\u0007HUNDRED\u0010\u0000\u0012\u0010\n\fTEN_THOUSAND\u0010\u0001\u0012\u000b\n\u0007MILLION\u0010\u0002B2\n\u0018io.envoyproxy.envoy.typeB\fPercentProtoP\u0001º\u0080ÈÑ\u0006\u0002\u0010\u0001b\u0006proto3"}, new Descriptors.FileDescriptor[]{Status.getDescriptor(), Validate.getDescriptor()});

    static {
        Descriptors.Descriptor descriptor2 = (Descriptors.Descriptor) getDescriptor().getMessageTypes().get(0);
        internal_static_envoy_type_Percent_descriptor = descriptor2;
        internal_static_envoy_type_Percent_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor2, new String[]{"Value"});
        Descriptors.Descriptor descriptor3 = (Descriptors.Descriptor) getDescriptor().getMessageTypes().get(1);
        internal_static_envoy_type_FractionalPercent_descriptor = descriptor3;
        internal_static_envoy_type_FractionalPercent_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor3, new String[]{"Numerator", "Denominator"});
        ExtensionRegistry extensionRegistryNewInstance = ExtensionRegistry.newInstance();
        extensionRegistryNewInstance.add(Status.fileStatus);
        extensionRegistryNewInstance.add(Validate.rules);
        Descriptors.FileDescriptor.internalUpdateFileDescriptor(descriptor, extensionRegistryNewInstance);
        Status.getDescriptor();
        Validate.getDescriptor();
    }

    private PercentProto() {
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
