package io.grpc.xds.shaded.io.envoyproxy.envoy.type.matcher;

import com.google.protobuf.Descriptors;
import com.google.protobuf.ExtensionRegistry;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageV3;
import io.grpc.xds.shaded.io.envoyproxy.envoy.type.RangeProto;
import io.grpc.xds.shaded.io.envoyproxy.pgv.validate.Validate;
import udpa.annotations.Status;

/* loaded from: classes4.dex */
public final class NumberProto {
    static final Descriptors.Descriptor internal_static_envoy_type_matcher_DoubleMatcher_descriptor;
    static final GeneratedMessageV3.FieldAccessorTable internal_static_envoy_type_matcher_DoubleMatcher_fieldAccessorTable;
    private static Descriptors.FileDescriptor descriptor = Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(new String[]{"\n\u001fenvoy/type/matcher/number.proto\u0012\u0012envoy.type.matcher\u001a\u0016envoy/type/range.proto\u001a\u001dudpa/annotations/status.proto\u001a\u0017validate/validate.proto\"`\n\rDoubleMatcher\u0012(\n\u0005range\u0018\u0001 \u0001(\u000b2\u0017.envoy.type.DoubleRangeH\u0000\u0012\u000f\n\u0005exact\u0018\u0002 \u0001(\u0001H\u0000B\u0014\n\rmatch_pattern\u0012\u0003øB\u0001B9\n io.envoyproxy.envoy.type.matcherB\u000bNumberProtoP\u0001º\u0080ÈÑ\u0006\u0002\u0010\u0001b\u0006proto3"}, new Descriptors.FileDescriptor[]{RangeProto.getDescriptor(), Status.getDescriptor(), Validate.getDescriptor()});

    static {
        Descriptors.Descriptor descriptor2 = (Descriptors.Descriptor) getDescriptor().getMessageTypes().get(0);
        internal_static_envoy_type_matcher_DoubleMatcher_descriptor = descriptor2;
        internal_static_envoy_type_matcher_DoubleMatcher_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor2, new String[]{"Range", "Exact", "MatchPattern"});
        ExtensionRegistry extensionRegistryNewInstance = ExtensionRegistry.newInstance();
        extensionRegistryNewInstance.add(Status.fileStatus);
        extensionRegistryNewInstance.add(Validate.required);
        Descriptors.FileDescriptor.internalUpdateFileDescriptor(descriptor, extensionRegistryNewInstance);
        RangeProto.getDescriptor();
        Status.getDescriptor();
        Validate.getDescriptor();
    }

    private NumberProto() {
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
