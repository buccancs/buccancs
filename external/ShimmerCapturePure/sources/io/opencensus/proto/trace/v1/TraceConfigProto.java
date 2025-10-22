package io.opencensus.proto.trace.v1;

import com.google.protobuf.Descriptors;
import com.google.protobuf.ExtensionRegistry;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageV3;

/* loaded from: classes4.dex */
public final class TraceConfigProto {
    static final Descriptors.Descriptor internal_static_opencensus_proto_trace_v1_ConstantSampler_descriptor;
    static final GeneratedMessageV3.FieldAccessorTable internal_static_opencensus_proto_trace_v1_ConstantSampler_fieldAccessorTable;
    static final Descriptors.Descriptor internal_static_opencensus_proto_trace_v1_ProbabilitySampler_descriptor;
    static final GeneratedMessageV3.FieldAccessorTable internal_static_opencensus_proto_trace_v1_ProbabilitySampler_fieldAccessorTable;
    static final Descriptors.Descriptor internal_static_opencensus_proto_trace_v1_RateLimitingSampler_descriptor;
    static final GeneratedMessageV3.FieldAccessorTable internal_static_opencensus_proto_trace_v1_RateLimitingSampler_fieldAccessorTable;
    static final Descriptors.Descriptor internal_static_opencensus_proto_trace_v1_TraceConfig_descriptor;
    static final GeneratedMessageV3.FieldAccessorTable internal_static_opencensus_proto_trace_v1_TraceConfig_fieldAccessorTable;
    private static Descriptors.FileDescriptor descriptor;

    static {
        Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(new String[]{"\n,opencensus/proto/trace/v1/trace_config.proto\u0012\u0019opencensus.proto.trace.v1\"\u0087\u0003\n\u000bTraceConfig\u0012L\n\u0013probability_sampler\u0018\u0001 \u0001(\u000b2-.opencensus.proto.trace.v1.ProbabilitySamplerH\u0000\u0012F\n\u0010constant_sampler\u0018\u0002 \u0001(\u000b2*.opencensus.proto.trace.v1.ConstantSamplerH\u0000\u0012O\n\u0015rate_limiting_sampler\u0018\u0003 \u0001(\u000b2..opencensus.proto.trace.v1.RateLimitingSamplerH\u0000\u0012 \n\u0018max_number_of_attributes\u0018\u0004 \u0001(\u0003\u0012!\n\u0019max_number_of_annotations\u0018\u0005 \u0001(\u0003\u0012$\n\u001cmax_number_of_message_events\u0018\u0006 \u0001(\u0003\u0012\u001b\n\u0013max_number_of_links\u0018\u0007 \u0001(\u0003B\t\n\u0007sampler\"1\n\u0012ProbabilitySampler\u0012\u001b\n\u0013samplingProbability\u0018\u0001 \u0001(\u0001\"¦\u0001\n\u000fConstantSampler\u0012M\n\bdecision\u0018\u0001 \u0001(\u000e2;.opencensus.proto.trace.v1.ConstantSampler.ConstantDecision\"D\n\u0010ConstantDecision\u0012\u000e\n\nALWAYS_OFF\u0010\u0000\u0012\r\n\tALWAYS_ON\u0010\u0001\u0012\u0011\n\rALWAYS_PARENT\u0010\u0002\"\"\n\u0013RateLimitingSampler\u0012\u000b\n\u0003qps\u0018\u0001 \u0001(\u0003Bv\n\u001cio.opencensus.proto.trace.v1B\u0010TraceConfigProtoP\u0001ZBgithub.com/census-instrumentation/opencensus-proto/gen-go/trace/v1b\u0006proto3"}, new Descriptors.FileDescriptor[0], new Descriptors.FileDescriptor.InternalDescriptorAssigner() { // from class: io.opencensus.proto.trace.v1.TraceConfigProto.1
            public ExtensionRegistry assignDescriptors(Descriptors.FileDescriptor fileDescriptor) {
                Descriptors.FileDescriptor unused = TraceConfigProto.descriptor = fileDescriptor;
                return null;
            }
        });
        Descriptors.Descriptor descriptor2 = (Descriptors.Descriptor) getDescriptor().getMessageTypes().get(0);
        internal_static_opencensus_proto_trace_v1_TraceConfig_descriptor = descriptor2;
        internal_static_opencensus_proto_trace_v1_TraceConfig_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor2, new String[]{"ProbabilitySampler", "ConstantSampler", "RateLimitingSampler", "MaxNumberOfAttributes", "MaxNumberOfAnnotations", "MaxNumberOfMessageEvents", "MaxNumberOfLinks", "Sampler"});
        Descriptors.Descriptor descriptor3 = (Descriptors.Descriptor) getDescriptor().getMessageTypes().get(1);
        internal_static_opencensus_proto_trace_v1_ProbabilitySampler_descriptor = descriptor3;
        internal_static_opencensus_proto_trace_v1_ProbabilitySampler_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor3, new String[]{"SamplingProbability"});
        Descriptors.Descriptor descriptor4 = (Descriptors.Descriptor) getDescriptor().getMessageTypes().get(2);
        internal_static_opencensus_proto_trace_v1_ConstantSampler_descriptor = descriptor4;
        internal_static_opencensus_proto_trace_v1_ConstantSampler_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor4, new String[]{"Decision"});
        Descriptors.Descriptor descriptor5 = (Descriptors.Descriptor) getDescriptor().getMessageTypes().get(3);
        internal_static_opencensus_proto_trace_v1_RateLimitingSampler_descriptor = descriptor5;
        internal_static_opencensus_proto_trace_v1_RateLimitingSampler_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor5, new String[]{"Qps"});
    }

    private TraceConfigProto() {
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
