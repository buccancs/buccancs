package io.opencensus.proto.stats.v1;

import com.google.protobuf.Descriptors;
import com.google.protobuf.ExtensionRegistry;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageV3;
import com.google.protobuf.TimestampProto;

/* loaded from: classes4.dex */
public final class StatsProto {
    static final Descriptors.Descriptor internal_static_opencensus_proto_stats_v1_CountAggregation_descriptor;
    static final GeneratedMessageV3.FieldAccessorTable internal_static_opencensus_proto_stats_v1_CountAggregation_fieldAccessorTable;
    static final Descriptors.Descriptor internal_static_opencensus_proto_stats_v1_DistributionAggregation_descriptor;
    static final GeneratedMessageV3.FieldAccessorTable internal_static_opencensus_proto_stats_v1_DistributionAggregation_fieldAccessorTable;
    static final Descriptors.Descriptor internal_static_opencensus_proto_stats_v1_LastValueAggregation_descriptor;
    static final GeneratedMessageV3.FieldAccessorTable internal_static_opencensus_proto_stats_v1_LastValueAggregation_fieldAccessorTable;
    static final Descriptors.Descriptor internal_static_opencensus_proto_stats_v1_Measure_descriptor;
    static final GeneratedMessageV3.FieldAccessorTable internal_static_opencensus_proto_stats_v1_Measure_fieldAccessorTable;
    static final Descriptors.Descriptor internal_static_opencensus_proto_stats_v1_Measurement_descriptor;
    static final GeneratedMessageV3.FieldAccessorTable internal_static_opencensus_proto_stats_v1_Measurement_fieldAccessorTable;
    static final Descriptors.Descriptor internal_static_opencensus_proto_stats_v1_SumAggregation_descriptor;
    static final GeneratedMessageV3.FieldAccessorTable internal_static_opencensus_proto_stats_v1_SumAggregation_fieldAccessorTable;
    static final Descriptors.Descriptor internal_static_opencensus_proto_stats_v1_Tag_descriptor;
    static final GeneratedMessageV3.FieldAccessorTable internal_static_opencensus_proto_stats_v1_Tag_fieldAccessorTable;
    static final Descriptors.Descriptor internal_static_opencensus_proto_stats_v1_View_descriptor;
    static final GeneratedMessageV3.FieldAccessorTable internal_static_opencensus_proto_stats_v1_View_fieldAccessorTable;
    private static Descriptors.FileDescriptor descriptor;

    static {
        Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(new String[]{"\n%opencensus/proto/stats/v1/stats.proto\u0012\u0019opencensus.proto.stats.v1\u001a\u001fgoogle/protobuf/timestamp.proto\"!\n\u0003Tag\u0012\u000b\n\u0003key\u0018\u0001 \u0001(\t\u0012\r\n\u0005value\u0018\u0002 \u0001(\t\"¦\u0001\n\u0007Measure\u0012\f\n\u0004name\u0018\u0001 \u0001(\t\u0012\u0013\n\u000bdescription\u0018\u0002 \u0001(\t\u0012\f\n\u0004unit\u0018\u0003 \u0001(\t\u00125\n\u0004type\u0018\u0004 \u0001(\u000e2'.opencensus.proto.stats.v1.Measure.Type\"3\n\u0004Type\u0012\u0014\n\u0010TYPE_UNSPECIFIED\u0010\u0000\u0012\t\n\u0005INT64\u0010\u0001\u0012\n\n\u0006DOUBLE\u0010\u0002\"¹\u0003\n\u0004View\u0012\f\n\u0004name\u0018\u0001 \u0001(\t\u0012\u0013\n\u000bdescription\u0018\u0002 \u0001(\t\u00123\n\u0007measure\u0018\u0003 \u0001(\u000b2\".opencensus.proto.stats.v1.Measure\u0012\u000f\n\u0007columns\u0018\u0004 \u0003(\t\u0012H\n\u0011count_aggregation\u0018\u0005 \u0001(\u000b2+.opencensus.proto.stats.v1.CountAggregationH\u0000\u0012D\n\u000fsum_aggregation\u0018\u0006 \u0001(\u000b2).opencensus.proto.stats.v1.SumAggregationH\u0000\u0012Q\n\u0016last_value_aggregation\u0018\u0007 \u0001(\u000b2/.opencensus.proto.stats.v1.LastValueAggregationH\u0000\u0012V\n\u0018distribution_aggregation\u0018\b \u0001(\u000b22.opencensus.proto.stats.v1.DistributionAggregationH\u0000B\r\n\u000baggregation\"\u0012\n\u0010CountAggregation\"\u0010\n\u000eSumAggregation\"\u0016\n\u0014LastValueAggregation\"0\n\u0017DistributionAggregation\u0012\u0015\n\rbucket_bounds\u0018\u0001 \u0003(\u0001\"±\u0001\n\u000bMeasurement\u0012,\n\u0004tags\u0018\u0001 \u0003(\u000b2\u001e.opencensus.proto.stats.v1.Tag\u0012\u0014\n\fmeasure_name\u0018\u0002 \u0001(\t\u0012\u0016\n\fdouble_value\u0018\u0003 \u0001(\u0001H\u0000\u0012\u0013\n\tint_value\u0018\u0004 \u0001(\u0003H\u0000\u0012(\n\u0004time\u0018\u0005 \u0001(\u000b2\u001a.google.protobuf.TimestampB\u0007\n\u0005valueBp\n\u001cio.opencensus.proto.stats.v1B\nStatsProtoP\u0001ZBgithub.com/census-instrumentation/opencensus-proto/gen-go/stats/v1b\u0006proto3"}, new Descriptors.FileDescriptor[]{TimestampProto.getDescriptor()}, new Descriptors.FileDescriptor.InternalDescriptorAssigner() { // from class: io.opencensus.proto.stats.v1.StatsProto.1
            public ExtensionRegistry assignDescriptors(Descriptors.FileDescriptor fileDescriptor) {
                Descriptors.FileDescriptor unused = StatsProto.descriptor = fileDescriptor;
                return null;
            }
        });
        Descriptors.Descriptor descriptor2 = (Descriptors.Descriptor) getDescriptor().getMessageTypes().get(0);
        internal_static_opencensus_proto_stats_v1_Tag_descriptor = descriptor2;
        internal_static_opencensus_proto_stats_v1_Tag_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor2, new String[]{"Key", "Value"});
        Descriptors.Descriptor descriptor3 = (Descriptors.Descriptor) getDescriptor().getMessageTypes().get(1);
        internal_static_opencensus_proto_stats_v1_Measure_descriptor = descriptor3;
        internal_static_opencensus_proto_stats_v1_Measure_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor3, new String[]{"Name", "Description", "Unit", "Type"});
        Descriptors.Descriptor descriptor4 = (Descriptors.Descriptor) getDescriptor().getMessageTypes().get(2);
        internal_static_opencensus_proto_stats_v1_View_descriptor = descriptor4;
        internal_static_opencensus_proto_stats_v1_View_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor4, new String[]{"Name", "Description", "Measure", "Columns", "CountAggregation", "SumAggregation", "LastValueAggregation", "DistributionAggregation", "Aggregation"});
        Descriptors.Descriptor descriptor5 = (Descriptors.Descriptor) getDescriptor().getMessageTypes().get(3);
        internal_static_opencensus_proto_stats_v1_CountAggregation_descriptor = descriptor5;
        internal_static_opencensus_proto_stats_v1_CountAggregation_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor5, new String[0]);
        Descriptors.Descriptor descriptor6 = (Descriptors.Descriptor) getDescriptor().getMessageTypes().get(4);
        internal_static_opencensus_proto_stats_v1_SumAggregation_descriptor = descriptor6;
        internal_static_opencensus_proto_stats_v1_SumAggregation_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor6, new String[0]);
        Descriptors.Descriptor descriptor7 = (Descriptors.Descriptor) getDescriptor().getMessageTypes().get(5);
        internal_static_opencensus_proto_stats_v1_LastValueAggregation_descriptor = descriptor7;
        internal_static_opencensus_proto_stats_v1_LastValueAggregation_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor7, new String[0]);
        Descriptors.Descriptor descriptor8 = (Descriptors.Descriptor) getDescriptor().getMessageTypes().get(6);
        internal_static_opencensus_proto_stats_v1_DistributionAggregation_descriptor = descriptor8;
        internal_static_opencensus_proto_stats_v1_DistributionAggregation_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor8, new String[]{"BucketBounds"});
        Descriptors.Descriptor descriptor9 = (Descriptors.Descriptor) getDescriptor().getMessageTypes().get(7);
        internal_static_opencensus_proto_stats_v1_Measurement_descriptor = descriptor9;
        internal_static_opencensus_proto_stats_v1_Measurement_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor9, new String[]{"Tags", "MeasureName", "DoubleValue", "IntValue", "Time", "Value"});
        TimestampProto.getDescriptor();
    }

    private StatsProto() {
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
