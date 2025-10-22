package io.grpc.xds.shaded.io.envoyproxy.envoy.config.cluster.v3;

import com.google.protobuf.Descriptors;
import com.google.protobuf.DurationProto;
import com.google.protobuf.ExtensionRegistry;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageV3;
import com.google.protobuf.WrappersProto;
import io.grpc.xds.shaded.io.envoyproxy.pgv.validate.Validate;
import udpa.annotations.Status;
import udpa.annotations.Versioning;

/* loaded from: classes6.dex */
public final class OutlierDetectionProto {
    static final Descriptors.Descriptor internal_static_envoy_config_cluster_v3_OutlierDetection_descriptor;
    static final GeneratedMessageV3.FieldAccessorTable internal_static_envoy_config_cluster_v3_OutlierDetection_fieldAccessorTable;
    private static Descriptors.FileDescriptor descriptor = Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(new String[]{"\n/envoy/config/cluster/v3/outlier_detection.proto\u0012\u0017envoy.config.cluster.v3\u001a\u001egoogle/protobuf/duration.proto\u001a\u001egoogle/protobuf/wrappers.proto\u001a\u001dudpa/annotations/status.proto\u001a!udpa/annotations/versioning.proto\u001a\u0017validate/validate.proto\"Ì\u000b\n\u0010OutlierDetection\u00125\n\u000fconsecutive_5xx\u0018\u0001 \u0001(\u000b2\u001c.google.protobuf.UInt32Value\u00125\n\binterval\u0018\u0002 \u0001(\u000b2\u0019.google.protobuf.DurationB\búB\u0005ª\u0001\u0002*\u0000\u0012?\n\u0012base_ejection_time\u0018\u0003 \u0001(\u000b2\u0019.google.protobuf.DurationB\búB\u0005ª\u0001\u0002*\u0000\u0012C\n\u0014max_ejection_percent\u0018\u0004 \u0001(\u000b2\u001c.google.protobuf.UInt32ValueB\u0007úB\u0004*\u0002\u0018d\u0012H\n\u0019enforcing_consecutive_5xx\u0018\u0005 \u0001(\u000b2\u001c.google.protobuf.UInt32ValueB\u0007úB\u0004*\u0002\u0018d\u0012E\n\u0016enforcing_success_rate\u0018\u0006 \u0001(\u000b2\u001c.google.protobuf.UInt32ValueB\u0007úB\u0004*\u0002\u0018d\u0012@\n\u001asuccess_rate_minimum_hosts\u0018\u0007 \u0001(\u000b2\u001c.google.protobuf.UInt32Value\u0012A\n\u001bsuccess_rate_request_volume\u0018\b \u0001(\u000b2\u001c.google.protobuf.UInt32Value\u0012?\n\u0019success_rate_stdev_factor\u0018\t \u0001(\u000b2\u001c.google.protobuf.UInt32Value\u0012A\n\u001bconsecutive_gateway_failure\u0018\n \u0001(\u000b2\u001c.google.protobuf.UInt32Value\u0012T\n%enforcing_consecutive_gateway_failure\u0018\u000b \u0001(\u000b2\u001c.google.protobuf.UInt32ValueB\u0007úB\u0004*\u0002\u0018d\u0012*\n\"split_external_local_origin_errors\u0018\f \u0001(\b\u0012F\n consecutive_local_origin_failure\u0018\r \u0001(\u000b2\u001c.google.protobuf.UInt32Value\u0012Y\n*enforcing_consecutive_local_origin_failure\u0018\u000e \u0001(\u000b2\u001c.google.protobuf.UInt32ValueB\u0007úB\u0004*\u0002\u0018d\u0012R\n#enforcing_local_origin_success_rate\u0018\u000f \u0001(\u000b2\u001c.google.protobuf.UInt32ValueB\u0007úB\u0004*\u0002\u0018d\u0012K\n\u001cfailure_percentage_threshold\u0018\u0010 \u0001(\u000b2\u001c.google.protobuf.UInt32ValueB\u0007úB\u0004*\u0002\u0018d\u0012K\n\u001cenforcing_failure_percentage\u0018\u0011 \u0001(\u000b2\u001c.google.protobuf.UInt32ValueB\u0007úB\u0004*\u0002\u0018d\u0012X\n)enforcing_failure_percentage_local_origin\u0018\u0012 \u0001(\u000b2\u001c.google.protobuf.UInt32ValueB\u0007úB\u0004*\u0002\u0018d\u0012F\n failure_percentage_minimum_hosts\u0018\u0013 \u0001(\u000b2\u001c.google.protobuf.UInt32Value\u0012G\n!failure_percentage_request_volume\u0018\u0014 \u0001(\u000b2\u001c.google.protobuf.UInt32Value:,\u009aÅ\u0088\u001e'\n%envoy.api.v2.cluster.OutlierDetectionBH\n%io.envoyproxy.envoy.config.cluster.v3B\u0015OutlierDetectionProtoP\u0001º\u0080ÈÑ\u0006\u0002\u0010\u0002b\u0006proto3"}, new Descriptors.FileDescriptor[]{DurationProto.getDescriptor(), WrappersProto.getDescriptor(), Status.getDescriptor(), Versioning.getDescriptor(), Validate.getDescriptor()});

    static {
        Descriptors.Descriptor descriptor2 = (Descriptors.Descriptor) getDescriptor().getMessageTypes().get(0);
        internal_static_envoy_config_cluster_v3_OutlierDetection_descriptor = descriptor2;
        internal_static_envoy_config_cluster_v3_OutlierDetection_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor2, new String[]{"Consecutive5Xx", "Interval", "BaseEjectionTime", "MaxEjectionPercent", "EnforcingConsecutive5Xx", "EnforcingSuccessRate", "SuccessRateMinimumHosts", "SuccessRateRequestVolume", "SuccessRateStdevFactor", "ConsecutiveGatewayFailure", "EnforcingConsecutiveGatewayFailure", "SplitExternalLocalOriginErrors", "ConsecutiveLocalOriginFailure", "EnforcingConsecutiveLocalOriginFailure", "EnforcingLocalOriginSuccessRate", "FailurePercentageThreshold", "EnforcingFailurePercentage", "EnforcingFailurePercentageLocalOrigin", "FailurePercentageMinimumHosts", "FailurePercentageRequestVolume"});
        ExtensionRegistry extensionRegistryNewInstance = ExtensionRegistry.newInstance();
        extensionRegistryNewInstance.add(Status.fileStatus);
        extensionRegistryNewInstance.add(Versioning.versioning);
        extensionRegistryNewInstance.add(Validate.rules);
        Descriptors.FileDescriptor.internalUpdateFileDescriptor(descriptor, extensionRegistryNewInstance);
        DurationProto.getDescriptor();
        WrappersProto.getDescriptor();
        Status.getDescriptor();
        Versioning.getDescriptor();
        Validate.getDescriptor();
    }

    private OutlierDetectionProto() {
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
