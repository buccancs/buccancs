package io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core;

import com.google.protobuf.Descriptors;
import com.google.protobuf.ExtensionRegistry;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageV3;
import com.google.protobuf.WrappersProto;
import com.shimmerresearch.verisense.sensors.SensorLIS2DW12;
import io.grpc.netty.shaded.io.netty.handler.codec.http.cookie.CookieHeaderNames;
import io.grpc.xds.shaded.io.envoyproxy.pgv.validate.Validate;
import udpa.annotations.Migrate;
import udpa.annotations.Status;

/* loaded from: classes3.dex */
public final class AddressProto {
    static final Descriptors.Descriptor internal_static_envoy_api_v2_core_Address_descriptor;
    static final GeneratedMessageV3.FieldAccessorTable internal_static_envoy_api_v2_core_Address_fieldAccessorTable;
    static final Descriptors.Descriptor internal_static_envoy_api_v2_core_BindConfig_descriptor;
    static final GeneratedMessageV3.FieldAccessorTable internal_static_envoy_api_v2_core_BindConfig_fieldAccessorTable;
    static final Descriptors.Descriptor internal_static_envoy_api_v2_core_CidrRange_descriptor;
    static final GeneratedMessageV3.FieldAccessorTable internal_static_envoy_api_v2_core_CidrRange_fieldAccessorTable;
    static final Descriptors.Descriptor internal_static_envoy_api_v2_core_Pipe_descriptor;
    static final GeneratedMessageV3.FieldAccessorTable internal_static_envoy_api_v2_core_Pipe_fieldAccessorTable;
    static final Descriptors.Descriptor internal_static_envoy_api_v2_core_SocketAddress_descriptor;
    static final GeneratedMessageV3.FieldAccessorTable internal_static_envoy_api_v2_core_SocketAddress_fieldAccessorTable;
    static final Descriptors.Descriptor internal_static_envoy_api_v2_core_TcpKeepalive_descriptor;
    static final GeneratedMessageV3.FieldAccessorTable internal_static_envoy_api_v2_core_TcpKeepalive_fieldAccessorTable;
    private static Descriptors.FileDescriptor descriptor = Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(new String[]{"\n\u001fenvoy/api/v2/core/address.proto\u0012\u0011envoy.api.v2.core\u001a%envoy/api/v2/core/socket_option.proto\u001a\u001egoogle/protobuf/wrappers.proto\u001a\u001eudpa/annotations/migrate.proto\u001a\u001dudpa/annotations/status.proto\u001a\u0017validate/validate.proto\"5\n\u0004Pipe\u0012\u0015\n\u0004path\u0018\u0001 \u0001(\tB\u0007úB\u0004r\u0002 \u0001\u0012\u0016\n\u0004mode\u0018\u0002 \u0001(\rB\búB\u0005*\u0003\u0018ÿ\u0003\"\u0088\u0002\n\rSocketAddress\u0012E\n\bprotocol\u0018\u0001 \u0001(\u000e2).envoy.api.v2.core.SocketAddress.ProtocolB\búB\u0005\u0082\u0001\u0002\u0010\u0001\u0012\u0018\n\u0007address\u0018\u0002 \u0001(\tB\u0007úB\u0004r\u0002 \u0001\u0012\u001f\n\nport_value\u0018\u0003 \u0001(\rB\túB\u0006*\u0004\u0018ÿÿ\u0003H\u0000\u0012\u0014\n\nnamed_port\u0018\u0004 \u0001(\tH\u0000\u0012\u0015\n\rresolver_name\u0018\u0005 \u0001(\t\u0012\u0013\n\u000bipv4_compat\u0018\u0006 \u0001(\b\"\u001c\n\bProtocol\u0012\u0007\n\u0003TCP\u0010\u0000\u0012\u0007\n\u0003UDP\u0010\u0001B\u0015\n\u000eport_specifier\u0012\u0003øB\u0001\"¶\u0001\n\fTcpKeepalive\u00126\n\u0010keepalive_probes\u0018\u0001 \u0001(\u000b2\u001c.google.protobuf.UInt32Value\u00124\n\u000ekeepalive_time\u0018\u0002 \u0001(\u000b2\u001c.google.protobuf.UInt32Value\u00128\n\u0012keepalive_interval\u0018\u0003 \u0001(\u000b2\u001c.google.protobuf.UInt32Value\"·\u0001\n\nBindConfig\u0012B\n\u000esource_address\u0018\u0001 \u0001(\u000b2 .envoy.api.v2.core.SocketAddressB\búB\u0005\u008a\u0001\u0002\u0010\u0001\u0012,\n\bfreebind\u0018\u0002 \u0001(\u000b2\u001a.google.protobuf.BoolValue\u00127\n\u000esocket_options\u0018\u0003 \u0003(\u000b2\u001f.envoy.api.v2.core.SocketOption\"~\n\u0007Address\u0012:\n\u000esocket_address\u0018\u0001 \u0001(\u000b2 .envoy.api.v2.core.SocketAddressH\u0000\u0012'\n\u0004pipe\u0018\u0002 \u0001(\u000b2\u0017.envoy.api.v2.core.PipeH\u0000B\u000e\n\u0007address\u0012\u0003øB\u0001\"h\n\tCidrRange\u0012\u001f\n\u000eaddress_prefix\u0018\u0001 \u0001(\tB\u0007úB\u0004r\u0002 \u0001\u0012:\n\nprefix_len\u0018\u0002 \u0001(\u000b2\u001c.google.protobuf.UInt32ValueB\búB\u0005*\u0003\u0018\u0080\u0001BU\n\u001fio.envoyproxy.envoy.api.v2.coreB\fAddressProtoP\u0001ò\u0098þ\u008f\u0005\u0016\u0012\u0014envoy.config.core.v3º\u0080ÈÑ\u0006\u0002\u0010\u0001b\u0006proto3"}, new Descriptors.FileDescriptor[]{SocketOptionProto.getDescriptor(), WrappersProto.getDescriptor(), Migrate.getDescriptor(), Status.getDescriptor(), Validate.getDescriptor()});

    static {
        Descriptors.Descriptor descriptor2 = (Descriptors.Descriptor) getDescriptor().getMessageTypes().get(0);
        internal_static_envoy_api_v2_core_Pipe_descriptor = descriptor2;
        internal_static_envoy_api_v2_core_Pipe_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor2, new String[]{CookieHeaderNames.PATH, SensorLIS2DW12.GuiLabelConfig.LIS2DW12_MODE});
        Descriptors.Descriptor descriptor3 = (Descriptors.Descriptor) getDescriptor().getMessageTypes().get(1);
        internal_static_envoy_api_v2_core_SocketAddress_descriptor = descriptor3;
        internal_static_envoy_api_v2_core_SocketAddress_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor3, new String[]{"Protocol", "Address", "PortValue", "NamedPort", "ResolverName", "Ipv4Compat", "PortSpecifier"});
        Descriptors.Descriptor descriptor4 = (Descriptors.Descriptor) getDescriptor().getMessageTypes().get(2);
        internal_static_envoy_api_v2_core_TcpKeepalive_descriptor = descriptor4;
        internal_static_envoy_api_v2_core_TcpKeepalive_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor4, new String[]{"KeepaliveProbes", "KeepaliveTime", "KeepaliveInterval"});
        Descriptors.Descriptor descriptor5 = (Descriptors.Descriptor) getDescriptor().getMessageTypes().get(3);
        internal_static_envoy_api_v2_core_BindConfig_descriptor = descriptor5;
        internal_static_envoy_api_v2_core_BindConfig_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor5, new String[]{"SourceAddress", "Freebind", "SocketOptions"});
        Descriptors.Descriptor descriptor6 = (Descriptors.Descriptor) getDescriptor().getMessageTypes().get(4);
        internal_static_envoy_api_v2_core_Address_descriptor = descriptor6;
        internal_static_envoy_api_v2_core_Address_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor6, new String[]{"SocketAddress", "Pipe", "Address"});
        Descriptors.Descriptor descriptor7 = (Descriptors.Descriptor) getDescriptor().getMessageTypes().get(5);
        internal_static_envoy_api_v2_core_CidrRange_descriptor = descriptor7;
        internal_static_envoy_api_v2_core_CidrRange_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor7, new String[]{"AddressPrefix", "PrefixLen"});
        ExtensionRegistry extensionRegistryNewInstance = ExtensionRegistry.newInstance();
        extensionRegistryNewInstance.add(Migrate.fileMigrate);
        extensionRegistryNewInstance.add(Status.fileStatus);
        extensionRegistryNewInstance.add(Validate.required);
        extensionRegistryNewInstance.add(Validate.rules);
        Descriptors.FileDescriptor.internalUpdateFileDescriptor(descriptor, extensionRegistryNewInstance);
        SocketOptionProto.getDescriptor();
        WrappersProto.getDescriptor();
        Migrate.getDescriptor();
        Status.getDescriptor();
        Validate.getDescriptor();
    }

    private AddressProto() {
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
