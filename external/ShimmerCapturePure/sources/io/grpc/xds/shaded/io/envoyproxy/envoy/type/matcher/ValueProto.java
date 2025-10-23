package io.grpc.xds.shaded.io.envoyproxy.envoy.type.matcher;

import com.google.protobuf.Descriptors;
import com.google.protobuf.ExtensionRegistry;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageV3;
import io.grpc.xds.shaded.io.envoyproxy.pgv.validate.Validate;
import udpa.annotations.Status;

/* loaded from: classes4.dex */
public final class ValueProto {
    static final Descriptors.Descriptor internal_static_envoy_type_matcher_ListMatcher_descriptor;
    static final GeneratedMessageV3.FieldAccessorTable internal_static_envoy_type_matcher_ListMatcher_fieldAccessorTable;
    static final Descriptors.Descriptor internal_static_envoy_type_matcher_ValueMatcher_NullMatch_descriptor;
    static final GeneratedMessageV3.FieldAccessorTable internal_static_envoy_type_matcher_ValueMatcher_NullMatch_fieldAccessorTable;
    static final Descriptors.Descriptor internal_static_envoy_type_matcher_ValueMatcher_descriptor;
    static final GeneratedMessageV3.FieldAccessorTable internal_static_envoy_type_matcher_ValueMatcher_fieldAccessorTable;
    private static Descriptors.FileDescriptor descriptor = Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(new String[]{"\n\u001eenvoy/type/matcher/value.proto\u0012\u0012envoy.type.matcher\u001a\u001fenvoy/type/matcher/number.proto\u001a\u001fenvoy/type/matcher/string.proto\u001a\u001dudpa/annotations/status.proto\u001a\u0017validate/validate.proto\"Ï\u0002\n\fValueMatcher\u0012@\n\nnull_match\u0018\u0001 \u0001(\u000b2*.envoy.type.matcher.ValueMatcher.NullMatchH\u0000\u00129\n\fdouble_match\u0018\u0002 \u0001(\u000b2!.envoy.type.matcher.DoubleMatcherH\u0000\u00129\n\fstring_match\u0018\u0003 \u0001(\u000b2!.envoy.type.matcher.StringMatcherH\u0000\u0012\u0014\n\nbool_match\u0018\u0004 \u0001(\bH\u0000\u0012\u0017\n\rpresent_match\u0018\u0005 \u0001(\bH\u0000\u00125\n\nlist_match\u0018\u0006 \u0001(\u000b2\u001f.envoy.type.matcher.ListMatcherH\u0000\u001a\u000b\n\tNullMatchB\u0014\n\rmatch_pattern\u0012\u0003øB\u0001\"W\n\u000bListMatcher\u00122\n\u0006one_of\u0018\u0001 \u0001(\u000b2 .envoy.type.matcher.ValueMatcherH\u0000B\u0014\n\rmatch_pattern\u0012\u0003øB\u0001B8\n io.envoyproxy.envoy.type.matcherB\nValueProtoP\u0001º\u0080ÈÑ\u0006\u0002\u0010\u0001b\u0006proto3"}, new Descriptors.FileDescriptor[]{NumberProto.getDescriptor(), StringProto.getDescriptor(), Status.getDescriptor(), Validate.getDescriptor()});

    static {
        Descriptors.Descriptor descriptor2 = (Descriptors.Descriptor) getDescriptor().getMessageTypes().get(0);
        internal_static_envoy_type_matcher_ValueMatcher_descriptor = descriptor2;
        internal_static_envoy_type_matcher_ValueMatcher_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor2, new String[]{"NullMatch", "DoubleMatch", "StringMatch", "BoolMatch", "PresentMatch", "ListMatch", "MatchPattern"});
        Descriptors.Descriptor descriptor3 = (Descriptors.Descriptor) descriptor2.getNestedTypes().get(0);
        internal_static_envoy_type_matcher_ValueMatcher_NullMatch_descriptor = descriptor3;
        internal_static_envoy_type_matcher_ValueMatcher_NullMatch_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor3, new String[0]);
        Descriptors.Descriptor descriptor4 = (Descriptors.Descriptor) getDescriptor().getMessageTypes().get(1);
        internal_static_envoy_type_matcher_ListMatcher_descriptor = descriptor4;
        internal_static_envoy_type_matcher_ListMatcher_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor4, new String[]{"OneOf", "MatchPattern"});
        ExtensionRegistry extensionRegistryNewInstance = ExtensionRegistry.newInstance();
        extensionRegistryNewInstance.add(Status.fileStatus);
        extensionRegistryNewInstance.add(Validate.required);
        Descriptors.FileDescriptor.internalUpdateFileDescriptor(descriptor, extensionRegistryNewInstance);
        NumberProto.getDescriptor();
        StringProto.getDescriptor();
        Status.getDescriptor();
        Validate.getDescriptor();
    }

    private ValueProto() {
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
