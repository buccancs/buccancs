package io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2;

import com.google.protobuf.Descriptors;
import com.google.protobuf.ExtensionRegistry;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageV3;
import com.google.protobuf.WrappersProto;
import io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.BaseProto;
import io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.ConfigSourceProto;
import io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.RouteComponentsProto;
import io.grpc.xds.shaded.io.envoyproxy.pgv.validate.Validate;
import udpa.annotations.Migrate;
import udpa.annotations.Status;

/* loaded from: classes3.dex */
public final class RouteProto {
    static final Descriptors.Descriptor internal_static_envoy_api_v2_RouteConfiguration_descriptor;
    static final GeneratedMessageV3.FieldAccessorTable internal_static_envoy_api_v2_RouteConfiguration_fieldAccessorTable;
    static final Descriptors.Descriptor internal_static_envoy_api_v2_Vhds_descriptor;
    static final GeneratedMessageV3.FieldAccessorTable internal_static_envoy_api_v2_Vhds_fieldAccessorTable;
    private static Descriptors.FileDescriptor descriptor = Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(new String[]{"\n\u0018envoy/api/v2/route.proto\u0012\fenvoy.api.v2\u001a\u001cenvoy/api/v2/core/base.proto\u001a%envoy/api/v2/core/config_source.proto\u001a)envoy/api/v2/route/route_components.proto\u001a\u001egoogle/protobuf/wrappers.proto\u001a\u001eudpa/annotations/migrate.proto\u001a\u001dudpa/annotations/status.proto\u001a\u0017validate/validate.proto\"\u009f\u0004\n\u0012RouteConfiguration\u0012\f\n\u0004name\u0018\u0001 \u0001(\t\u00126\n\rvirtual_hosts\u0018\u0002 \u0003(\u000b2\u001f.envoy.api.v2.route.VirtualHost\u0012 \n\u0004vhds\u0018\t \u0001(\u000b2\u0012.envoy.api.v2.Vhds\u0012/\n\u0015internal_only_headers\u0018\u0003 \u0003(\tB\u0010úB\r\u0092\u0001\n\"\br\u0006À\u0001\u0001È\u0001\u0000\u0012P\n\u0017response_headers_to_add\u0018\u0004 \u0003(\u000b2$.envoy.api.v2.core.HeaderValueOptionB\túB\u0006\u0092\u0001\u0003\u0010è\u0007\u00124\n\u001aresponse_headers_to_remove\u0018\u0005 \u0003(\tB\u0010úB\r\u0092\u0001\n\"\br\u0006À\u0001\u0001È\u0001\u0000\u0012O\n\u0016request_headers_to_add\u0018\u0006 \u0003(\u000b2$.envoy.api.v2.core.HeaderValueOptionB\túB\u0006\u0092\u0001\u0003\u0010è\u0007\u00123\n\u0019request_headers_to_remove\u0018\b \u0003(\tB\u0010úB\r\u0092\u0001\n\"\br\u0006À\u0001\u0001È\u0001\u0000\u0012+\n#most_specific_header_mutations_wins\u0018\n \u0001(\b\u00125\n\u0011validate_clusters\u0018\u0007 \u0001(\u000b2\u001a.google.protobuf.BoolValue\"H\n\u0004Vhds\u0012@\n\rconfig_source\u0018\u0001 \u0001(\u000b2\u001f.envoy.api.v2.core.ConfigSourceB\búB\u0005\u008a\u0001\u0002\u0010\u0001BO\n\u001aio.envoyproxy.envoy.api.v2B\nRouteProtoP\u0001ò\u0098þ\u008f\u0005\u0017\u0012\u0015envoy.config.route.v3º\u0080ÈÑ\u0006\u0002\u0010\u0001b\u0006proto3"}, new Descriptors.FileDescriptor[]{BaseProto.getDescriptor(), ConfigSourceProto.getDescriptor(), RouteComponentsProto.getDescriptor(), WrappersProto.getDescriptor(), Migrate.getDescriptor(), Status.getDescriptor(), Validate.getDescriptor()});

    static {
        Descriptors.Descriptor descriptor2 = (Descriptors.Descriptor) getDescriptor().getMessageTypes().get(0);
        internal_static_envoy_api_v2_RouteConfiguration_descriptor = descriptor2;
        internal_static_envoy_api_v2_RouteConfiguration_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor2, new String[]{"Name", "VirtualHosts", "Vhds", "InternalOnlyHeaders", "ResponseHeadersToAdd", "ResponseHeadersToRemove", "RequestHeadersToAdd", "RequestHeadersToRemove", "MostSpecificHeaderMutationsWins", "ValidateClusters"});
        Descriptors.Descriptor descriptor3 = (Descriptors.Descriptor) getDescriptor().getMessageTypes().get(1);
        internal_static_envoy_api_v2_Vhds_descriptor = descriptor3;
        internal_static_envoy_api_v2_Vhds_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor3, new String[]{"ConfigSource"});
        ExtensionRegistry extensionRegistryNewInstance = ExtensionRegistry.newInstance();
        extensionRegistryNewInstance.add(Migrate.fileMigrate);
        extensionRegistryNewInstance.add(Status.fileStatus);
        extensionRegistryNewInstance.add(Validate.rules);
        Descriptors.FileDescriptor.internalUpdateFileDescriptor(descriptor, extensionRegistryNewInstance);
        BaseProto.getDescriptor();
        ConfigSourceProto.getDescriptor();
        RouteComponentsProto.getDescriptor();
        WrappersProto.getDescriptor();
        Migrate.getDescriptor();
        Status.getDescriptor();
        Validate.getDescriptor();
    }

    private RouteProto() {
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
