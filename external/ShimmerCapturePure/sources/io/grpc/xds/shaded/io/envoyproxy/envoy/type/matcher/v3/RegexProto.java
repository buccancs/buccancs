package io.grpc.xds.shaded.io.envoyproxy.envoy.type.matcher.v3;

import com.google.protobuf.Descriptors;
import com.google.protobuf.ExtensionRegistry;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageV3;
import com.google.protobuf.WrappersProto;
import io.grpc.xds.shaded.io.envoyproxy.pgv.validate.Validate;
import udpa.annotations.Status;
import udpa.annotations.Versioning;

/* loaded from: classes4.dex */
public final class RegexProto {
    static final Descriptors.Descriptor internal_static_envoy_type_matcher_v3_RegexMatchAndSubstitute_descriptor;
    static final GeneratedMessageV3.FieldAccessorTable internal_static_envoy_type_matcher_v3_RegexMatchAndSubstitute_fieldAccessorTable;
    static final Descriptors.Descriptor internal_static_envoy_type_matcher_v3_RegexMatcher_GoogleRE2_descriptor;
    static final GeneratedMessageV3.FieldAccessorTable internal_static_envoy_type_matcher_v3_RegexMatcher_GoogleRE2_fieldAccessorTable;
    static final Descriptors.Descriptor internal_static_envoy_type_matcher_v3_RegexMatcher_descriptor;
    static final GeneratedMessageV3.FieldAccessorTable internal_static_envoy_type_matcher_v3_RegexMatcher_fieldAccessorTable;
    private static Descriptors.FileDescriptor descriptor = Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(new String[]{"\n!envoy/type/matcher/v3/regex.proto\u0012\u0015envoy.type.matcher.v3\u001a\u001egoogle/protobuf/wrappers.proto\u001a\u001dudpa/annotations/status.proto\u001a!udpa/annotations/versioning.proto\u001a\u0017validate/validate.proto\"¬\u0002\n\fRegexMatcher\u0012M\n\ngoogle_re2\u0018\u0001 \u0001(\u000b2-.envoy.type.matcher.v3.RegexMatcher.GoogleRE2B\búB\u0005\u008a\u0001\u0002\u0010\u0001H\u0000\u0012\u0016\n\u0005regex\u0018\u0002 \u0001(\tB\u0007úB\u0004r\u0002 \u0001\u001ay\n\tGoogleRE2\u0012:\n\u0010max_program_size\u0018\u0001 \u0001(\u000b2\u001c.google.protobuf.UInt32ValueB\u0002\u0018\u0001:0\u009aÅ\u0088\u001e+\n)envoy.type.matcher.RegexMatcher.GoogleRE2:&\u009aÅ\u0088\u001e!\n\u001fenvoy.type.matcher.RegexMatcherB\u0012\n\u000bengine_type\u0012\u0003øB\u0001\"¢\u0001\n\u0017RegexMatchAndSubstitute\u0012>\n\u0007pattern\u0018\u0001 \u0001(\u000b2#.envoy.type.matcher.v3.RegexMatcherB\búB\u0005\u008a\u0001\u0002\u0010\u0001\u0012\u0014\n\fsubstitution\u0018\u0002 \u0001(\t:1\u009aÅ\u0088\u001e,\n*envoy.type.matcher.RegexMatchAndSubstituteB;\n#io.envoyproxy.envoy.type.matcher.v3B\nRegexProtoP\u0001º\u0080ÈÑ\u0006\u0002\u0010\u0002b\u0006proto3"}, new Descriptors.FileDescriptor[]{WrappersProto.getDescriptor(), Status.getDescriptor(), Versioning.getDescriptor(), Validate.getDescriptor()});

    static {
        Descriptors.Descriptor descriptor2 = (Descriptors.Descriptor) getDescriptor().getMessageTypes().get(0);
        internal_static_envoy_type_matcher_v3_RegexMatcher_descriptor = descriptor2;
        internal_static_envoy_type_matcher_v3_RegexMatcher_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor2, new String[]{"GoogleRe2", "Regex", "EngineType"});
        Descriptors.Descriptor descriptor3 = (Descriptors.Descriptor) descriptor2.getNestedTypes().get(0);
        internal_static_envoy_type_matcher_v3_RegexMatcher_GoogleRE2_descriptor = descriptor3;
        internal_static_envoy_type_matcher_v3_RegexMatcher_GoogleRE2_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor3, new String[]{"MaxProgramSize"});
        Descriptors.Descriptor descriptor4 = (Descriptors.Descriptor) getDescriptor().getMessageTypes().get(1);
        internal_static_envoy_type_matcher_v3_RegexMatchAndSubstitute_descriptor = descriptor4;
        internal_static_envoy_type_matcher_v3_RegexMatchAndSubstitute_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor4, new String[]{"Pattern", "Substitution"});
        ExtensionRegistry extensionRegistryNewInstance = ExtensionRegistry.newInstance();
        extensionRegistryNewInstance.add(Status.fileStatus);
        extensionRegistryNewInstance.add(Versioning.versioning);
        extensionRegistryNewInstance.add(Validate.required);
        extensionRegistryNewInstance.add(Validate.rules);
        Descriptors.FileDescriptor.internalUpdateFileDescriptor(descriptor, extensionRegistryNewInstance);
        WrappersProto.getDescriptor();
        Status.getDescriptor();
        Versioning.getDescriptor();
        Validate.getDescriptor();
    }

    private RegexProto() {
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
