package io.grpc.xds.shaded.io.envoyproxy.envoy.config.listener.v3;

import com.google.api.AnnotationsProto;
import com.google.protobuf.Descriptors;
import com.google.protobuf.DurationProto;
import com.google.protobuf.ExtensionRegistry;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageV3;
import com.google.protobuf.WrappersProto;
import io.grpc.xds.shaded.com.github.udpa.udpa.core.v1.CollectionEntryProto;
import io.grpc.xds.shaded.io.envoyproxy.envoy.config.accesslog.v3.AccesslogProto;
import io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.AddressProto;
import io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.BaseProto;
import io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.SocketOptionProto;
import io.grpc.xds.shaded.io.envoyproxy.pgv.validate.Validate;
import udpa.annotations.Security;
import udpa.annotations.Status;
import udpa.annotations.Versioning;

/* loaded from: classes6.dex */
public final class ListenerProto {
    static final Descriptors.Descriptor internal_static_envoy_config_listener_v3_ListenerCollection_descriptor;
    static final GeneratedMessageV3.FieldAccessorTable internal_static_envoy_config_listener_v3_ListenerCollection_fieldAccessorTable;
    static final Descriptors.Descriptor internal_static_envoy_config_listener_v3_Listener_ConnectionBalanceConfig_ExactBalance_descriptor;
    static final GeneratedMessageV3.FieldAccessorTable internal_static_envoy_config_listener_v3_Listener_ConnectionBalanceConfig_ExactBalance_fieldAccessorTable;
    static final Descriptors.Descriptor internal_static_envoy_config_listener_v3_Listener_ConnectionBalanceConfig_descriptor;
    static final GeneratedMessageV3.FieldAccessorTable internal_static_envoy_config_listener_v3_Listener_ConnectionBalanceConfig_fieldAccessorTable;
    static final Descriptors.Descriptor internal_static_envoy_config_listener_v3_Listener_DeprecatedV1_descriptor;
    static final GeneratedMessageV3.FieldAccessorTable internal_static_envoy_config_listener_v3_Listener_DeprecatedV1_fieldAccessorTable;
    static final Descriptors.Descriptor internal_static_envoy_config_listener_v3_Listener_descriptor;
    static final GeneratedMessageV3.FieldAccessorTable internal_static_envoy_config_listener_v3_Listener_fieldAccessorTable;
    private static Descriptors.FileDescriptor descriptor = Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(new String[]{"\n'envoy/config/listener/v3/listener.proto\u0012\u0018envoy.config.listener.v3\u001a)envoy/config/accesslog/v3/accesslog.proto\u001a\"envoy/config/core/v3/address.proto\u001a\u001fenvoy/config/core/v3/base.proto\u001a(envoy/config/core/v3/socket_option.proto\u001a+envoy/config/listener/v3/api_listener.proto\u001a2envoy/config/listener/v3/listener_components.proto\u001a2envoy/config/listener/v3/udp_listener_config.proto\u001a\u001cgoogle/api/annotations.proto\u001a\u001egoogle/protobuf/duration.proto\u001a\u001egoogle/protobuf/wrappers.proto\u001a#udpa/core/v1/collection_entry.proto\u001a\u001fudpa/annotations/security.proto\u001a\u001dudpa/annotations/status.proto\u001a!udpa/annotations/versioning.proto\u001a\u0017validate/validate.proto\"D\n\u0012ListenerCollection\u0012.\n\u0007entries\u0018\u0001 \u0001(\u000b2\u001d.udpa.core.v1.CollectionEntry\"\u0091\r\n\bListener\u0012\f\n\u0004name\u0018\u0001 \u0001(\t\u00128\n\u0007address\u0018\u0002 \u0001(\u000b2\u001d.envoy.config.core.v3.AddressB\búB\u0005\u008a\u0001\u0002\u0010\u0001\u0012<\n\rfilter_chains\u0018\u0003 \u0003(\u000b2%.envoy.config.listener.v3.FilterChain\u0012P\n!per_connection_buffer_limit_bytes\u0018\u0005 \u0001(\u000b2\u001c.google.protobuf.UInt32ValueB\u0007\u008a\u0093·*\u0002\b\u0001\u00120\n\bmetadata\u0018\u0006 \u0001(\u000b2\u001e.envoy.config.core.v3.Metadata\u0012F\n\rdeprecated_v1\u0018\u0007 \u0001(\u000b2/.envoy.config.listener.v3.Listener.DeprecatedV1\u0012@\n\ndrain_type\u0018\b \u0001(\u000e2,.envoy.config.listener.v3.Listener.DrainType\u0012B\n\u0010listener_filters\u0018\t \u0003(\u000b2(.envoy.config.listener.v3.ListenerFilter\u0012;\n\u0018listener_filters_timeout\u0018\u000f \u0001(\u000b2\u0019.google.protobuf.Duration\u0012,\n$continue_on_listener_filters_timeout\u0018\u0011 \u0001(\b\u0012/\n\u000btransparent\u0018\n \u0001(\u000b2\u001a.google.protobuf.BoolValue\u0012,\n\bfreebind\u0018\u000b \u0001(\u000b2\u001a.google.protobuf.BoolValue\u0012:\n\u000esocket_options\u0018\r \u0003(\u000b2\".envoy.config.core.v3.SocketOption\u0012@\n\u001atcp_fast_open_queue_length\u0018\f \u0001(\u000b2\u001c.google.protobuf.UInt32Value\u0012A\n\u0011traffic_direction\u0018\u0010 \u0001(\u000e2&.envoy.config.core.v3.TrafficDirection\u0012H\n\u0013udp_listener_config\u0018\u0012 \u0001(\u000b2+.envoy.config.listener.v3.UdpListenerConfig\u0012;\n\fapi_listener\u0018\u0013 \u0001(\u000b2%.envoy.config.listener.v3.ApiListener\u0012]\n\u0019connection_balance_config\u0018\u0014 \u0001(\u000b2:.envoy.config.listener.v3.Listener.ConnectionBalanceConfig\u0012\u0012\n\nreuse_port\u0018\u0015 \u0001(\b\u00128\n\naccess_log\u0018\u0016 \u0003(\u000b2$.envoy.config.accesslog.v3.AccessLog\u001ak\n\fDeprecatedV1\u00120\n\fbind_to_port\u0018\u0001 \u0001(\u000b2\u001a.google.protobuf.BoolValue:)\u009aÅ\u0088\u001e$\n\"envoy.api.v2.Listener.DeprecatedV1\u001a\u0099\u0002\n\u0017ConnectionBalanceConfig\u0012`\n\rexact_balance\u0018\u0001 \u0001(\u000b2G.envoy.config.listener.v3.Listener.ConnectionBalanceConfig.ExactBalanceH\u0000\u001aQ\n\fExactBalance:A\u009aÅ\u0088\u001e<\n:envoy.api.v2.Listener.ConnectionBalanceConfig.ExactBalance:4\u009aÅ\u0088\u001e/\n-envoy.api.v2.Listener.ConnectionBalanceConfigB\u0013\n\fbalance_type\u0012\u0003øB\u0001\")\n\tDrainType\u0012\u000b\n\u0007DEFAULT\u0010\u0000\u0012\u000f\n\u000bMODIFY_ONLY\u0010\u0001:\u001c\u009aÅ\u0088\u001e\u0017\n\u0015envoy.api.v2.ListenerJ\u0004\b\u000e\u0010\u000fJ\u0004\b\u0004\u0010\u0005R\u0010use_original_dstBA\n&io.envoyproxy.envoy.config.listener.v3B\rListenerProtoP\u0001º\u0080ÈÑ\u0006\u0002\u0010\u0002b\u0006proto3"}, new Descriptors.FileDescriptor[]{AccesslogProto.getDescriptor(), AddressProto.getDescriptor(), BaseProto.getDescriptor(), SocketOptionProto.getDescriptor(), ApiListenerProto.getDescriptor(), ListenerComponentsProto.getDescriptor(), UdpListenerConfigProto.getDescriptor(), AnnotationsProto.getDescriptor(), DurationProto.getDescriptor(), WrappersProto.getDescriptor(), CollectionEntryProto.getDescriptor(), Security.getDescriptor(), Status.getDescriptor(), Versioning.getDescriptor(), Validate.getDescriptor()});

    static {
        Descriptors.Descriptor descriptor2 = (Descriptors.Descriptor) getDescriptor().getMessageTypes().get(0);
        internal_static_envoy_config_listener_v3_ListenerCollection_descriptor = descriptor2;
        internal_static_envoy_config_listener_v3_ListenerCollection_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor2, new String[]{"Entries"});
        Descriptors.Descriptor descriptor3 = (Descriptors.Descriptor) getDescriptor().getMessageTypes().get(1);
        internal_static_envoy_config_listener_v3_Listener_descriptor = descriptor3;
        internal_static_envoy_config_listener_v3_Listener_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor3, new String[]{"Name", "Address", "FilterChains", "PerConnectionBufferLimitBytes", "Metadata", "DeprecatedV1", "DrainType", "ListenerFilters", "ListenerFiltersTimeout", "ContinueOnListenerFiltersTimeout", "Transparent", "Freebind", "SocketOptions", "TcpFastOpenQueueLength", "TrafficDirection", "UdpListenerConfig", "ApiListener", "ConnectionBalanceConfig", "ReusePort", "AccessLog"});
        Descriptors.Descriptor descriptor4 = (Descriptors.Descriptor) descriptor3.getNestedTypes().get(0);
        internal_static_envoy_config_listener_v3_Listener_DeprecatedV1_descriptor = descriptor4;
        internal_static_envoy_config_listener_v3_Listener_DeprecatedV1_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor4, new String[]{"BindToPort"});
        Descriptors.Descriptor descriptor5 = (Descriptors.Descriptor) descriptor3.getNestedTypes().get(1);
        internal_static_envoy_config_listener_v3_Listener_ConnectionBalanceConfig_descriptor = descriptor5;
        internal_static_envoy_config_listener_v3_Listener_ConnectionBalanceConfig_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor5, new String[]{"ExactBalance", "BalanceType"});
        Descriptors.Descriptor descriptor6 = (Descriptors.Descriptor) descriptor5.getNestedTypes().get(0);
        internal_static_envoy_config_listener_v3_Listener_ConnectionBalanceConfig_ExactBalance_descriptor = descriptor6;
        internal_static_envoy_config_listener_v3_Listener_ConnectionBalanceConfig_ExactBalance_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor6, new String[0]);
        ExtensionRegistry extensionRegistryNewInstance = ExtensionRegistry.newInstance();
        extensionRegistryNewInstance.add(Status.fileStatus);
        extensionRegistryNewInstance.add(Security.security);
        extensionRegistryNewInstance.add(Versioning.versioning);
        extensionRegistryNewInstance.add(Validate.required);
        extensionRegistryNewInstance.add(Validate.rules);
        Descriptors.FileDescriptor.internalUpdateFileDescriptor(descriptor, extensionRegistryNewInstance);
        AccesslogProto.getDescriptor();
        AddressProto.getDescriptor();
        BaseProto.getDescriptor();
        SocketOptionProto.getDescriptor();
        ApiListenerProto.getDescriptor();
        ListenerComponentsProto.getDescriptor();
        UdpListenerConfigProto.getDescriptor();
        AnnotationsProto.getDescriptor();
        DurationProto.getDescriptor();
        WrappersProto.getDescriptor();
        CollectionEntryProto.getDescriptor();
        Security.getDescriptor();
        Status.getDescriptor();
        Versioning.getDescriptor();
        Validate.getDescriptor();
    }

    private ListenerProto() {
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
