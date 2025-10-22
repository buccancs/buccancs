package io.grpc.xds.shaded.io.envoyproxy.envoy.config.filter.http.fault.v2;

import com.google.protobuf.Descriptors;
import com.google.protobuf.ExtensionRegistry;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageV3;
import com.google.protobuf.WrappersProto;
import io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.RouteComponentsProto;
import io.grpc.xds.shaded.io.envoyproxy.envoy.type.PercentProto;
import io.grpc.xds.shaded.io.envoyproxy.pgv.validate.Validate;
import udpa.annotations.Migrate;
import udpa.annotations.Status;

/* loaded from: classes6.dex */
public final class FaultProto {
    static final Descriptors.Descriptor internal_static_envoy_config_filter_http_fault_v2_FaultAbort_HeaderAbort_descriptor;
    static final GeneratedMessageV3.FieldAccessorTable internal_static_envoy_config_filter_http_fault_v2_FaultAbort_HeaderAbort_fieldAccessorTable;
    static final Descriptors.Descriptor internal_static_envoy_config_filter_http_fault_v2_FaultAbort_descriptor;
    static final GeneratedMessageV3.FieldAccessorTable internal_static_envoy_config_filter_http_fault_v2_FaultAbort_fieldAccessorTable;
    static final Descriptors.Descriptor internal_static_envoy_config_filter_http_fault_v2_HTTPFault_descriptor;
    static final GeneratedMessageV3.FieldAccessorTable internal_static_envoy_config_filter_http_fault_v2_HTTPFault_fieldAccessorTable;
    private static Descriptors.FileDescriptor descriptor = Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(new String[]{"\n-envoy/config/filter/http/fault/v2/fault.proto\u0012!envoy.config.filter.http.fault.v2\u001a)envoy/api/v2/route/route_components.proto\u001a(envoy/config/filter/fault/v2/fault.proto\u001a\u0018envoy/type/percent.proto\u001a\u001egoogle/protobuf/wrappers.proto\u001a\u001eudpa/annotations/migrate.proto\u001a\u001dudpa/annotations/status.proto\u001a\u0017validate/validate.proto\"Þ\u0001\n\nFaultAbort\u0012\"\n\u000bhttp_status\u0018\u0002 \u0001(\rB\u000búB\b*\u0006\u0010Ø\u0004(È\u0001H\u0000\u0012Q\n\fheader_abort\u0018\u0004 \u0001(\u000b29.envoy.config.filter.http.fault.v2.FaultAbort.HeaderAbortH\u0000\u00121\n\npercentage\u0018\u0003 \u0001(\u000b2\u001d.envoy.type.FractionalPercent\u001a\r\n\u000bHeaderAbortB\u0011\n\nerror_type\u0012\u0003øB\u0001J\u0004\b\u0001\u0010\u0002\"¿\u0004\n\tHTTPFault\u00127\n\u0005delay\u0018\u0001 \u0001(\u000b2(.envoy.config.filter.fault.v2.FaultDelay\u0012<\n\u0005abort\u0018\u0002 \u0001(\u000b2-.envoy.config.filter.http.fault.v2.FaultAbort\u0012\u0018\n\u0010upstream_cluster\u0018\u0003 \u0001(\t\u00122\n\u0007headers\u0018\u0004 \u0003(\u000b2!.envoy.api.v2.route.HeaderMatcher\u0012\u0018\n\u0010downstream_nodes\u0018\u0005 \u0003(\t\u00127\n\u0011max_active_faults\u0018\u0006 \u0001(\u000b2\u001c.google.protobuf.UInt32Value\u0012I\n\u0013response_rate_limit\u0018\u0007 \u0001(\u000b2,.envoy.config.filter.fault.v2.FaultRateLimit\u0012\u001d\n\u0015delay_percent_runtime\u0018\b \u0001(\t\u0012\u001d\n\u0015abort_percent_runtime\u0018\t \u0001(\t\u0012\u001e\n\u0016delay_duration_runtime\u0018\n \u0001(\t\u0012!\n\u0019abort_http_status_runtime\u0018\u000b \u0001(\t\u0012!\n\u0019max_active_faults_runtime\u0018\f \u0001(\t\u0012+\n#response_rate_limit_percent_runtime\u0018\r \u0001(\tBu\n/io.envoyproxy.envoy.config.filter.http.fault.v2B\nFaultProtoP\u0001ò\u0098þ\u008f\u0005(\u0012&envoy.extensions.filters.http.fault.v3º\u0080ÈÑ\u0006\u0002\u0010\u0001b\u0006proto3"}, new Descriptors.FileDescriptor[]{RouteComponentsProto.getDescriptor(), io.grpc.xds.shaded.io.envoyproxy.envoy.config.filter.fault.v2.FaultProto.getDescriptor(), PercentProto.getDescriptor(), WrappersProto.getDescriptor(), Migrate.getDescriptor(), Status.getDescriptor(), Validate.getDescriptor()});

    static {
        Descriptors.Descriptor descriptor2 = (Descriptors.Descriptor) getDescriptor().getMessageTypes().get(0);
        internal_static_envoy_config_filter_http_fault_v2_FaultAbort_descriptor = descriptor2;
        internal_static_envoy_config_filter_http_fault_v2_FaultAbort_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor2, new String[]{"HttpStatus", "HeaderAbort", "Percentage", "ErrorType"});
        Descriptors.Descriptor descriptor3 = (Descriptors.Descriptor) descriptor2.getNestedTypes().get(0);
        internal_static_envoy_config_filter_http_fault_v2_FaultAbort_HeaderAbort_descriptor = descriptor3;
        internal_static_envoy_config_filter_http_fault_v2_FaultAbort_HeaderAbort_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor3, new String[0]);
        Descriptors.Descriptor descriptor4 = (Descriptors.Descriptor) getDescriptor().getMessageTypes().get(1);
        internal_static_envoy_config_filter_http_fault_v2_HTTPFault_descriptor = descriptor4;
        internal_static_envoy_config_filter_http_fault_v2_HTTPFault_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor4, new String[]{"Delay", "Abort", "UpstreamCluster", "Headers", "DownstreamNodes", "MaxActiveFaults", "ResponseRateLimit", "DelayPercentRuntime", "AbortPercentRuntime", "DelayDurationRuntime", "AbortHttpStatusRuntime", "MaxActiveFaultsRuntime", "ResponseRateLimitPercentRuntime"});
        ExtensionRegistry extensionRegistryNewInstance = ExtensionRegistry.newInstance();
        extensionRegistryNewInstance.add(Migrate.fileMigrate);
        extensionRegistryNewInstance.add(Status.fileStatus);
        extensionRegistryNewInstance.add(Validate.required);
        extensionRegistryNewInstance.add(Validate.rules);
        Descriptors.FileDescriptor.internalUpdateFileDescriptor(descriptor, extensionRegistryNewInstance);
        RouteComponentsProto.getDescriptor();
        io.grpc.xds.shaded.io.envoyproxy.envoy.config.filter.fault.v2.FaultProto.getDescriptor();
        PercentProto.getDescriptor();
        WrappersProto.getDescriptor();
        Migrate.getDescriptor();
        Status.getDescriptor();
        Validate.getDescriptor();
    }

    private FaultProto() {
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
