package io.grpc.xds.shaded.io.envoyproxy.envoy.config.cluster.v3;

import com.google.protobuf.Descriptors;
import com.google.protobuf.ExtensionRegistry;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageV3;
import com.google.protobuf.WrappersProto;
import io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.BaseProto;
import io.grpc.xds.shaded.io.envoyproxy.envoy.type.v3.PercentProto;
import io.grpc.xds.shaded.io.envoyproxy.pgv.validate.Validate;
import udpa.annotations.Status;
import udpa.annotations.Versioning;

/* loaded from: classes2.dex */
public final class CircuitBreakerProto {
    static final Descriptors.Descriptor internal_static_envoy_config_cluster_v3_CircuitBreakers_Thresholds_RetryBudget_descriptor;
    static final GeneratedMessageV3.FieldAccessorTable internal_static_envoy_config_cluster_v3_CircuitBreakers_Thresholds_RetryBudget_fieldAccessorTable;
    static final Descriptors.Descriptor internal_static_envoy_config_cluster_v3_CircuitBreakers_Thresholds_descriptor;
    static final GeneratedMessageV3.FieldAccessorTable internal_static_envoy_config_cluster_v3_CircuitBreakers_Thresholds_fieldAccessorTable;
    static final Descriptors.Descriptor internal_static_envoy_config_cluster_v3_CircuitBreakers_descriptor;
    static final GeneratedMessageV3.FieldAccessorTable internal_static_envoy_config_cluster_v3_CircuitBreakers_fieldAccessorTable;
    private static Descriptors.FileDescriptor descriptor = Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(new String[]{"\n-envoy/config/cluster/v3/circuit_breaker.proto\u0012\u0017envoy.config.cluster.v3\u001a\u001fenvoy/config/core/v3/base.proto\u001a\u001benvoy/type/v3/percent.proto\u001a\u001egoogle/protobuf/wrappers.proto\u001a\u001dudpa/annotations/status.proto\u001a!udpa/annotations/versioning.proto\u001a\u0017validate/validate.proto\"Ø\u0006\n\u000fCircuitBreakers\u0012G\n\nthresholds\u0018\u0001 \u0003(\u000b23.envoy.config.cluster.v3.CircuitBreakers.Thresholds\u001aÎ\u0005\n\nThresholds\u0012A\n\bpriority\u0018\u0001 \u0001(\u000e2%.envoy.config.core.v3.RoutingPriorityB\búB\u0005\u0082\u0001\u0002\u0010\u0001\u00125\n\u000fmax_connections\u0018\u0002 \u0001(\u000b2\u001c.google.protobuf.UInt32Value\u0012:\n\u0014max_pending_requests\u0018\u0003 \u0001(\u000b2\u001c.google.protobuf.UInt32Value\u00122\n\fmax_requests\u0018\u0004 \u0001(\u000b2\u001c.google.protobuf.UInt32Value\u00121\n\u000bmax_retries\u0018\u0005 \u0001(\u000b2\u001c.google.protobuf.UInt32Value\u0012U\n\fretry_budget\u0018\b \u0001(\u000b2?.envoy.config.cluster.v3.CircuitBreakers.Thresholds.RetryBudget\u0012\u0017\n\u000ftrack_remaining\u0018\u0006 \u0001(\b\u0012:\n\u0014max_connection_pools\u0018\u0007 \u0001(\u000b2\u001c.google.protobuf.UInt32Value\u001a¾\u0001\n\u000bRetryBudget\u0012.\n\u000ebudget_percent\u0018\u0001 \u0001(\u000b2\u0016.envoy.type.v3.Percent\u0012;\n\u0015min_retry_concurrency\u0018\u0002 \u0001(\u000b2\u001c.google.protobuf.UInt32Value:B\u009aÅ\u0088\u001e=\n;envoy.api.v2.cluster.CircuitBreakers.Thresholds.RetryBudget:6\u009aÅ\u0088\u001e1\n/envoy.api.v2.cluster.CircuitBreakers.Thresholds:+\u009aÅ\u0088\u001e&\n$envoy.api.v2.cluster.CircuitBreakersBF\n%io.envoyproxy.envoy.config.cluster.v3B\u0013CircuitBreakerProtoP\u0001º\u0080ÈÑ\u0006\u0002\u0010\u0002b\u0006proto3"}, new Descriptors.FileDescriptor[]{BaseProto.getDescriptor(), PercentProto.getDescriptor(), WrappersProto.getDescriptor(), Status.getDescriptor(), Versioning.getDescriptor(), Validate.getDescriptor()});

    static {
        Descriptors.Descriptor descriptor2 = (Descriptors.Descriptor) getDescriptor().getMessageTypes().get(0);
        internal_static_envoy_config_cluster_v3_CircuitBreakers_descriptor = descriptor2;
        internal_static_envoy_config_cluster_v3_CircuitBreakers_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor2, new String[]{"Thresholds"});
        Descriptors.Descriptor descriptor3 = (Descriptors.Descriptor) descriptor2.getNestedTypes().get(0);
        internal_static_envoy_config_cluster_v3_CircuitBreakers_Thresholds_descriptor = descriptor3;
        internal_static_envoy_config_cluster_v3_CircuitBreakers_Thresholds_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor3, new String[]{"Priority", "MaxConnections", "MaxPendingRequests", "MaxRequests", "MaxRetries", "RetryBudget", "TrackRemaining", "MaxConnectionPools"});
        Descriptors.Descriptor descriptor4 = (Descriptors.Descriptor) descriptor3.getNestedTypes().get(0);
        internal_static_envoy_config_cluster_v3_CircuitBreakers_Thresholds_RetryBudget_descriptor = descriptor4;
        internal_static_envoy_config_cluster_v3_CircuitBreakers_Thresholds_RetryBudget_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor4, new String[]{"BudgetPercent", "MinRetryConcurrency"});
        ExtensionRegistry extensionRegistryNewInstance = ExtensionRegistry.newInstance();
        extensionRegistryNewInstance.add(Status.fileStatus);
        extensionRegistryNewInstance.add(Versioning.versioning);
        extensionRegistryNewInstance.add(Validate.rules);
        Descriptors.FileDescriptor.internalUpdateFileDescriptor(descriptor, extensionRegistryNewInstance);
        BaseProto.getDescriptor();
        PercentProto.getDescriptor();
        WrappersProto.getDescriptor();
        Status.getDescriptor();
        Versioning.getDescriptor();
        Validate.getDescriptor();
    }

    private CircuitBreakerProto() {
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
