package io.grpc.xds.shaded.io.envoyproxy.envoy.service.load_stats.v3;

import com.google.protobuf.Descriptors;
import com.google.protobuf.DurationProto;
import com.google.protobuf.ExtensionRegistry;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageV3;
import io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.BaseProto;
import io.grpc.xds.shaded.io.envoyproxy.envoy.config.endpoint.v3.LoadReportProto;
import io.grpc.xds.shaded.io.envoyproxy.pgv.validate.Validate;
import udpa.annotations.Status;
import udpa.annotations.Versioning;

/* loaded from: classes4.dex */
public final class LrsProto {
    static final Descriptors.Descriptor internal_static_envoy_service_load_stats_v3_LoadStatsRequest_descriptor;
    static final GeneratedMessageV3.FieldAccessorTable internal_static_envoy_service_load_stats_v3_LoadStatsRequest_fieldAccessorTable;
    static final Descriptors.Descriptor internal_static_envoy_service_load_stats_v3_LoadStatsResponse_descriptor;
    static final GeneratedMessageV3.FieldAccessorTable internal_static_envoy_service_load_stats_v3_LoadStatsResponse_fieldAccessorTable;
    private static Descriptors.FileDescriptor descriptor = Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(new String[]{"\n%envoy/service/load_stats/v3/lrs.proto\u0012\u001benvoy.service.load_stats.v3\u001a\u001fenvoy/config/core/v3/base.proto\u001a*envoy/config/endpoint/v3/load_report.proto\u001a\u001egoogle/protobuf/duration.proto\u001a\u001dudpa/annotations/status.proto\u001a!udpa/annotations/versioning.proto\u001a\u0017validate/validate.proto\"°\u0001\n\u0010LoadStatsRequest\u0012(\n\u0004node\u0018\u0001 \u0001(\u000b2\u001a.envoy.config.core.v3.Node\u0012=\n\rcluster_stats\u0018\u0002 \u0003(\u000b2&.envoy.config.endpoint.v3.ClusterStats:3\u009aÅ\u0088\u001e.\n,envoy.service.load_stats.v2.LoadStatsRequest\"×\u0001\n\u0011LoadStatsResponse\u0012\u0010\n\bclusters\u0018\u0001 \u0003(\t\u0012\u0019\n\u0011send_all_clusters\u0018\u0004 \u0001(\b\u0012:\n\u0017load_reporting_interval\u0018\u0002 \u0001(\u000b2\u0019.google.protobuf.Duration\u0012#\n\u001breport_endpoint_granularity\u0018\u0003 \u0001(\b:4\u009aÅ\u0088\u001e/\n-envoy.service.load_stats.v2.LoadStatsResponse2\u008e\u0001\n\u0014LoadReportingService\u0012v\n\u000fStreamLoadStats\u0012-.envoy.service.load_stats.v3.LoadStatsRequest\u001a..envoy.service.load_stats.v3.LoadStatsResponse\"\u0000(\u00010\u0001BB\n)io.envoyproxy.envoy.service.load_stats.v3B\bLrsProtoP\u0001\u0088\u0001\u0001º\u0080ÈÑ\u0006\u0002\u0010\u0002b\u0006proto3"}, new Descriptors.FileDescriptor[]{BaseProto.getDescriptor(), LoadReportProto.getDescriptor(), DurationProto.getDescriptor(), Status.getDescriptor(), Versioning.getDescriptor(), Validate.getDescriptor()});

    static {
        Descriptors.Descriptor descriptor2 = (Descriptors.Descriptor) getDescriptor().getMessageTypes().get(0);
        internal_static_envoy_service_load_stats_v3_LoadStatsRequest_descriptor = descriptor2;
        internal_static_envoy_service_load_stats_v3_LoadStatsRequest_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor2, new String[]{"Node", "ClusterStats"});
        Descriptors.Descriptor descriptor3 = (Descriptors.Descriptor) getDescriptor().getMessageTypes().get(1);
        internal_static_envoy_service_load_stats_v3_LoadStatsResponse_descriptor = descriptor3;
        internal_static_envoy_service_load_stats_v3_LoadStatsResponse_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor3, new String[]{"Clusters", "SendAllClusters", "LoadReportingInterval", "ReportEndpointGranularity"});
        ExtensionRegistry extensionRegistryNewInstance = ExtensionRegistry.newInstance();
        extensionRegistryNewInstance.add(Status.fileStatus);
        extensionRegistryNewInstance.add(Versioning.versioning);
        Descriptors.FileDescriptor.internalUpdateFileDescriptor(descriptor, extensionRegistryNewInstance);
        BaseProto.getDescriptor();
        LoadReportProto.getDescriptor();
        DurationProto.getDescriptor();
        Status.getDescriptor();
        Versioning.getDescriptor();
        Validate.getDescriptor();
    }

    private LrsProto() {
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
