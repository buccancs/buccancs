package io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.cluster;

import com.google.protobuf.Descriptors;
import com.google.protobuf.ExtensionRegistry;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageV3;
import com.google.protobuf.WrappersProto;
import io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.BaseProto;
import io.grpc.xds.shaded.io.envoyproxy.envoy.type.PercentProto;
import io.grpc.xds.shaded.io.envoyproxy.pgv.validate.Validate;
import udpa.annotations.Migrate;
import udpa.annotations.Status;

/* loaded from: classes3.dex */
public final class CircuitBreakerProto {
    static final Descriptors.Descriptor internal_static_envoy_api_v2_cluster_CircuitBreakers_Thresholds_RetryBudget_descriptor;
    static final GeneratedMessageV3.FieldAccessorTable internal_static_envoy_api_v2_cluster_CircuitBreakers_Thresholds_RetryBudget_fieldAccessorTable;
    static final Descriptors.Descriptor internal_static_envoy_api_v2_cluster_CircuitBreakers_Thresholds_descriptor;
    static final GeneratedMessageV3.FieldAccessorTable internal_static_envoy_api_v2_cluster_CircuitBreakers_Thresholds_fieldAccessorTable;
    static final Descriptors.Descriptor internal_static_envoy_api_v2_cluster_CircuitBreakers_descriptor;
    static final GeneratedMessageV3.FieldAccessorTable internal_static_envoy_api_v2_cluster_CircuitBreakers_fieldAccessorTable;
    private static Descriptors.FileDescriptor descriptor = Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(new String[]{"\n*envoy/api/v2/cluster/circuit_breaker.proto\u0012\u0014envoy.api.v2.cluster\u001a\u001cenvoy/api/v2/core/base.proto\u001a\u0018envoy/type/percent.proto\u001a\u001egoogle/protobuf/wrappers.proto\u001a\u001eudpa/annotations/migrate.proto\u001a\u001dudpa/annotations/status.proto\u001a\u0017validate/validate.proto\"¢\u0005\n\u000fCircuitBreakers\u0012D\n\nthresholds\u0018\u0001 \u0003(\u000b20.envoy.api.v2.cluster.CircuitBreakers.Thresholds\u001aÈ\u0004\n\nThresholds\u0012>\n\bpriority\u0018\u0001 \u0001(\u000e2\".envoy.api.v2.core.RoutingPriorityB\búB\u0005\u0082\u0001\u0002\u0010\u0001\u00125\n\u000fmax_connections\u0018\u0002 \u0001(\u000b2\u001c.google.protobuf.UInt32Value\u0012:\n\u0014max_pending_requests\u0018\u0003 \u0001(\u000b2\u001c.google.protobuf.UInt32Value\u00122\n\fmax_requests\u0018\u0004 \u0001(\u000b2\u001c.google.protobuf.UInt32Value\u00121\n\u000bmax_retries\u0018\u0005 \u0001(\u000b2\u001c.google.protobuf.UInt32Value\u0012R\n\fretry_budget\u0018\b \u0001(\u000b2<.envoy.api.v2.cluster.CircuitBreakers.Thresholds.RetryBudget\u0012\u0017\n\u000ftrack_remaining\u0018\u0006 \u0001(\b\u0012:\n\u0014max_connection_pools\u0018\u0007 \u0001(\u000b2\u001c.google.protobuf.UInt32Value\u001aw\n\u000bRetryBudget\u0012+\n\u000ebudget_percent\u0018\u0001 \u0001(\u000b2\u0013.envoy.type.Percent\u0012;\n\u0015min_retry_concurrency\u0018\u0002 \u0001(\u000b2\u001c.google.protobuf.UInt32ValueB\u0094\u0001\n\"io.envoyproxy.envoy.api.v2.clusterB\u0013CircuitBreakerProtoP\u0001ª\u0002\u0016Envoy.Api.V2.ClusterNSê\u0002\u0016Envoy.Api.V2.ClusterNSò\u0098þ\u008f\u0005\u0019\u0012\u0017envoy.config.cluster.v3º\u0080ÈÑ\u0006\u0002\u0010\u0001b\u0006proto3"}, new Descriptors.FileDescriptor[]{BaseProto.getDescriptor(), PercentProto.getDescriptor(), WrappersProto.getDescriptor(), Migrate.getDescriptor(), Status.getDescriptor(), Validate.getDescriptor()});

    static {
        Descriptors.Descriptor descriptor2 = (Descriptors.Descriptor) getDescriptor().getMessageTypes().get(0);
        internal_static_envoy_api_v2_cluster_CircuitBreakers_descriptor = descriptor2;
        internal_static_envoy_api_v2_cluster_CircuitBreakers_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor2, new String[]{"Thresholds"});
        Descriptors.Descriptor descriptor3 = (Descriptors.Descriptor) descriptor2.getNestedTypes().get(0);
        internal_static_envoy_api_v2_cluster_CircuitBreakers_Thresholds_descriptor = descriptor3;
        internal_static_envoy_api_v2_cluster_CircuitBreakers_Thresholds_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor3, new String[]{"Priority", "MaxConnections", "MaxPendingRequests", "MaxRequests", "MaxRetries", "RetryBudget", "TrackRemaining", "MaxConnectionPools"});
        Descriptors.Descriptor descriptor4 = (Descriptors.Descriptor) descriptor3.getNestedTypes().get(0);
        internal_static_envoy_api_v2_cluster_CircuitBreakers_Thresholds_RetryBudget_descriptor = descriptor4;
        internal_static_envoy_api_v2_cluster_CircuitBreakers_Thresholds_RetryBudget_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor4, new String[]{"BudgetPercent", "MinRetryConcurrency"});
        ExtensionRegistry extensionRegistryNewInstance = ExtensionRegistry.newInstance();
        extensionRegistryNewInstance.add(Migrate.fileMigrate);
        extensionRegistryNewInstance.add(Status.fileStatus);
        extensionRegistryNewInstance.add(Validate.rules);
        Descriptors.FileDescriptor.internalUpdateFileDescriptor(descriptor, extensionRegistryNewInstance);
        BaseProto.getDescriptor();
        PercentProto.getDescriptor();
        WrappersProto.getDescriptor();
        Migrate.getDescriptor();
        Status.getDescriptor();
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
