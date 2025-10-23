package io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.filters.network.http_connection_manager.v3;

import com.google.protobuf.AnyProto;
import com.google.protobuf.Descriptors;
import com.google.protobuf.DurationProto;
import com.google.protobuf.ExtensionRegistry;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageV3;
import com.google.protobuf.StructProto;
import com.google.protobuf.WrappersProto;
import envoy.annotations.Deprecation;
import io.grpc.xds.shaded.com.github.udpa.udpa.core.v1.ResourceLocatorProto;
import io.grpc.xds.shaded.io.envoyproxy.envoy.config.accesslog.v3.AccesslogProto;
import io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.BaseProto;
import io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.ConfigSourceProto;
import io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.ExtensionProto;
import io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.ProtocolProto;
import io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.SubstitutionFormatStringProto;
import io.grpc.xds.shaded.io.envoyproxy.envoy.config.route.v3.RouteProto;
import io.grpc.xds.shaded.io.envoyproxy.envoy.config.route.v3.ScopedRouteProto;
import io.grpc.xds.shaded.io.envoyproxy.envoy.config.trace.v3.HttpTracerProto;
import io.grpc.xds.shaded.io.envoyproxy.envoy.type.tracing.v3.CustomTagProto;
import io.grpc.xds.shaded.io.envoyproxy.envoy.type.v3.PercentProto;
import io.grpc.xds.shaded.io.envoyproxy.pgv.validate.Validate;
import udpa.annotations.Migrate;
import udpa.annotations.Security;
import udpa.annotations.Status;
import udpa.annotations.Versioning;

/* loaded from: classes4.dex */
public final class HttpConnectionManagerProto {
    static final Descriptors.Descriptor internal_static_envoy_extensions_filters_network_http_connection_manager_v3_HttpConnectionManager_InternalAddressConfig_descriptor;
    static final GeneratedMessageV3.FieldAccessorTable internal_static_envoy_extensions_filters_network_http_connection_manager_v3_HttpConnectionManager_InternalAddressConfig_fieldAccessorTable;
    static final Descriptors.Descriptor internal_static_envoy_extensions_filters_network_http_connection_manager_v3_HttpConnectionManager_SetCurrentClientCertDetails_descriptor;
    static final GeneratedMessageV3.FieldAccessorTable internal_static_envoy_extensions_filters_network_http_connection_manager_v3_HttpConnectionManager_SetCurrentClientCertDetails_fieldAccessorTable;
    static final Descriptors.Descriptor internal_static_envoy_extensions_filters_network_http_connection_manager_v3_HttpConnectionManager_Tracing_descriptor;
    static final GeneratedMessageV3.FieldAccessorTable internal_static_envoy_extensions_filters_network_http_connection_manager_v3_HttpConnectionManager_Tracing_fieldAccessorTable;
    static final Descriptors.Descriptor internal_static_envoy_extensions_filters_network_http_connection_manager_v3_HttpConnectionManager_UpgradeConfig_descriptor;
    static final GeneratedMessageV3.FieldAccessorTable internal_static_envoy_extensions_filters_network_http_connection_manager_v3_HttpConnectionManager_UpgradeConfig_fieldAccessorTable;
    static final Descriptors.Descriptor internal_static_envoy_extensions_filters_network_http_connection_manager_v3_HttpConnectionManager_descriptor;
    static final GeneratedMessageV3.FieldAccessorTable internal_static_envoy_extensions_filters_network_http_connection_manager_v3_HttpConnectionManager_fieldAccessorTable;
    static final Descriptors.Descriptor internal_static_envoy_extensions_filters_network_http_connection_manager_v3_HttpFilter_descriptor;
    static final GeneratedMessageV3.FieldAccessorTable internal_static_envoy_extensions_filters_network_http_connection_manager_v3_HttpFilter_fieldAccessorTable;
    static final Descriptors.Descriptor internal_static_envoy_extensions_filters_network_http_connection_manager_v3_LocalReplyConfig_descriptor;
    static final GeneratedMessageV3.FieldAccessorTable internal_static_envoy_extensions_filters_network_http_connection_manager_v3_LocalReplyConfig_fieldAccessorTable;
    static final Descriptors.Descriptor internal_static_envoy_extensions_filters_network_http_connection_manager_v3_Rds_descriptor;
    static final GeneratedMessageV3.FieldAccessorTable internal_static_envoy_extensions_filters_network_http_connection_manager_v3_Rds_fieldAccessorTable;
    static final Descriptors.Descriptor internal_static_envoy_extensions_filters_network_http_connection_manager_v3_RequestIDExtension_descriptor;
    static final GeneratedMessageV3.FieldAccessorTable internal_static_envoy_extensions_filters_network_http_connection_manager_v3_RequestIDExtension_fieldAccessorTable;
    static final Descriptors.Descriptor internal_static_envoy_extensions_filters_network_http_connection_manager_v3_ResponseMapper_descriptor;
    static final GeneratedMessageV3.FieldAccessorTable internal_static_envoy_extensions_filters_network_http_connection_manager_v3_ResponseMapper_fieldAccessorTable;
    static final Descriptors.Descriptor internal_static_envoy_extensions_filters_network_http_connection_manager_v3_ScopedRds_descriptor;
    static final GeneratedMessageV3.FieldAccessorTable internal_static_envoy_extensions_filters_network_http_connection_manager_v3_ScopedRds_fieldAccessorTable;
    static final Descriptors.Descriptor internal_static_envoy_extensions_filters_network_http_connection_manager_v3_ScopedRouteConfigurationsList_descriptor;
    static final GeneratedMessageV3.FieldAccessorTable internal_static_envoy_extensions_filters_network_http_connection_manager_v3_ScopedRouteConfigurationsList_fieldAccessorTable;
    static final Descriptors.Descriptor internal_static_envoy_extensions_filters_network_http_connection_manager_v3_ScopedRoutes_ScopeKeyBuilder_FragmentBuilder_HeaderValueExtractor_KvElement_descriptor;
    static final GeneratedMessageV3.FieldAccessorTable internal_static_envoy_extensions_filters_network_http_connection_manager_v3_ScopedRoutes_ScopeKeyBuilder_FragmentBuilder_HeaderValueExtractor_KvElement_fieldAccessorTable;
    static final Descriptors.Descriptor internal_static_envoy_extensions_filters_network_http_connection_manager_v3_ScopedRoutes_ScopeKeyBuilder_FragmentBuilder_HeaderValueExtractor_descriptor;
    static final GeneratedMessageV3.FieldAccessorTable internal_static_envoy_extensions_filters_network_http_connection_manager_v3_ScopedRoutes_ScopeKeyBuilder_FragmentBuilder_HeaderValueExtractor_fieldAccessorTable;
    static final Descriptors.Descriptor internal_static_envoy_extensions_filters_network_http_connection_manager_v3_ScopedRoutes_ScopeKeyBuilder_FragmentBuilder_descriptor;
    static final GeneratedMessageV3.FieldAccessorTable internal_static_envoy_extensions_filters_network_http_connection_manager_v3_ScopedRoutes_ScopeKeyBuilder_FragmentBuilder_fieldAccessorTable;
    static final Descriptors.Descriptor internal_static_envoy_extensions_filters_network_http_connection_manager_v3_ScopedRoutes_ScopeKeyBuilder_descriptor;
    static final GeneratedMessageV3.FieldAccessorTable internal_static_envoy_extensions_filters_network_http_connection_manager_v3_ScopedRoutes_ScopeKeyBuilder_fieldAccessorTable;
    static final Descriptors.Descriptor internal_static_envoy_extensions_filters_network_http_connection_manager_v3_ScopedRoutes_descriptor;
    static final GeneratedMessageV3.FieldAccessorTable internal_static_envoy_extensions_filters_network_http_connection_manager_v3_ScopedRoutes_fieldAccessorTable;
    private static Descriptors.FileDescriptor descriptor = Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(new String[]{"\nYenvoy/extensions/filters/network/http_connection_manager/v3/http_connection_manager.proto\u0012;envoy.extensions.filters.network.http_connection_manager.v3\u001a)envoy/config/accesslog/v3/accesslog.proto\u001a\u001fenvoy/config/core/v3/base.proto\u001a(envoy/config/core/v3/config_source.proto\u001a$envoy/config/core/v3/extension.proto\u001a#envoy/config/core/v3/protocol.proto\u001a5envoy/config/core/v3/substitution_format_string.proto\u001a!envoy/config/route/v3/route.proto\u001a(envoy/config/route/v3/scoped_route.proto\u001a'envoy/config/trace/v3/http_tracer.proto\u001a&envoy/type/tracing/v3/custom_tag.proto\u001a\u001benvoy/type/v3/percent.proto\u001a\u0019google/protobuf/any.proto\u001a\u001egoogle/protobuf/duration.proto\u001a\u001cgoogle/protobuf/struct.proto\u001a\u001egoogle/protobuf/wrappers.proto\u001a#udpa/core/v1/resource_locator.proto\u001a#envoy/annotations/deprecation.proto\u001a\u001eudpa/annotations/migrate.proto\u001a\u001fudpa/annotations/security.proto\u001a\u001dudpa/annotations/status.proto\u001a!udpa/annotations/versioning.proto\u001a\u0017validate/validate.proto\"È\"\n\u0015HttpConnectionManager\u0012z\n\ncodec_type\u0018\u0001 \u0001(\u000e2\\.envoy.extensions.filters.network.http_connection_manager.v3.HttpConnectionManager.CodecTypeB\búB\u0005\u0082\u0001\u0002\u0010\u0001\u0012\u001c\n\u000bstat_prefix\u0018\u0002 \u0001(\tB\u0007úB\u0004r\u0002 \u0001\u0012O\n\u0003rds\u0018\u0003 \u0001(\u000b2@.envoy.extensions.filters.network.http_connection_manager.v3.RdsH\u0000\u0012A\n\froute_config\u0018\u0004 \u0001(\u000b2).envoy.config.route.v3.RouteConfigurationH\u0000\u0012b\n\rscoped_routes\u0018\u001f \u0001(\u000b2I.envoy.extensions.filters.network.http_connection_manager.v3.ScopedRoutesH\u0000\u0012]\n\fhttp_filters\u0018\u0005 \u0003(\u000b2G.envoy.extensions.filters.network.http_connection_manager.v3.HttpFilter\u00122\n\u000eadd_user_agent\u0018\u0006 \u0001(\u000b2\u001a.google.protobuf.BoolValue\u0012k\n\u0007tracing\u0018\u0007 \u0001(\u000b2Z.envoy.extensions.filters.network.http_connection_manager.v3.HttpConnectionManager.Tracing\u0012X\n\u001ccommon_http_protocol_options\u0018# \u0001(\u000b2).envoy.config.core.v3.HttpProtocolOptionsB\u0007\u008a\u0093·*\u0002\b\u0001\u0012I\n\u0015http_protocol_options\u0018\b \u0001(\u000b2*.envoy.config.core.v3.Http1ProtocolOptions\u0012S\n\u0016http2_protocol_options\u0018\t \u0001(\u000b2*.envoy.config.core.v3.Http2ProtocolOptionsB\u0007\u008a\u0093·*\u0002\b\u0001\u0012\u0013\n\u000bserver_name\u0018\n \u0001(\t\u0012\u009d\u0001\n\u001cserver_header_transformation\u0018\" \u0001(\u000e2m.envoy.extensions.filters.network.http_connection_manager.v3.HttpConnectionManager.ServerHeaderTransformationB\búB\u0005\u0082\u0001\u0002\u0010\u0001\u0012G\n\u0016max_request_headers_kb\u0018\u001d \u0001(\u000b2\u001c.google.protobuf.UInt32ValueB\túB\u0006*\u0004\u0018` \u0000\u0012?\n\u0013stream_idle_timeout\u0018\u0018 \u0001(\u000b2\u0019.google.protobuf.DurationB\u0007\u008a\u0093·*\u0002\b\u0001\u0012;\n\u000frequest_timeout\u0018\u001c \u0001(\u000b2\u0019.google.protobuf.DurationB\u0007\u008a\u0093·*\u0002\b\u0001\u00120\n\rdrain_timeout\u0018\f \u0001(\u000b2\u0019.google.protobuf.Duration\u00128\n\u0015delayed_close_timeout\u0018\u001a \u0001(\u000b2\u0019.google.protobuf.Duration\u00128\n\naccess_log\u0018\r \u0003(\u000b2$.envoy.config.accesslog.v3.AccessLog\u0012?\n\u0012use_remote_address\u0018\u000e \u0001(\u000b2\u001a.google.protobuf.BoolValueB\u0007\u008a\u0093·*\u0002\b\u0001\u0012\u001c\n\u0014xff_num_trusted_hops\u0018\u0013 \u0001(\r\u0012\u0089\u0001\n\u0017internal_address_config\u0018\u0019 \u0001(\u000b2h.envoy.extensions.filters.network.http_connection_manager.v3.HttpConnectionManager.InternalAddressConfig\u0012\u0017\n\u000fskip_xff_append\u0018\u0015 \u0001(\b\u0012\u000b\n\u0003via\u0018\u0016 \u0001(\t\u00127\n\u0013generate_request_id\u0018\u000f \u0001(\u000b2\u001a.google.protobuf.BoolValue\u0012$\n\u001cpreserve_external_request_id\u0018  \u0001(\b\u0012)\n!always_set_request_id_in_response\u0018% \u0001(\b\u0012\u009a\u0001\n\u001bforward_client_cert_details\u0018\u0010 \u0001(\u000e2k.envoy.extensions.filters.network.http_connection_manager.v3.HttpConnectionManager.ForwardClientCertDetailsB\búB\u0005\u0082\u0001\u0002\u0010\u0001\u0012\u0097\u0001\n\u001fset_current_client_cert_details\u0018\u0011 \u0001(\u000b2n.envoy.extensions.filters.network.http_connection_manager.v3.HttpConnectionManager.SetCurrentClientCertDetails\u0012\u001a\n\u0012proxy_100_continue\u0018\u0012 \u0001(\b\u00129\n1represent_ipv4_remote_address_as_ipv4_mapped_ipv6\u0018\u0014 \u0001(\b\u0012y\n\u000fupgrade_configs\u0018\u0017 \u0003(\u000b2`.envoy.extensions.filters.network.http_connection_manager.v3.HttpConnectionManager.UpgradeConfig\u00122\n\u000enormalize_path\u0018\u001e \u0001(\u000b2\u001a.google.protobuf.BoolValue\u0012\u0015\n\rmerge_slashes\u0018! \u0001(\b\u0012m\n\u0014request_id_extension\u0018$ \u0001(\u000b2O.envoy.extensions.filters.network.http_connection_manager.v3.RequestIDExtension\u0012i\n\u0012local_reply_config\u0018& \u0001(\u000b2M.envoy.extensions.filters.network.http_connection_manager.v3.LocalReplyConfig\u0012 \n\u0018strip_matching_host_port\u0018' \u0001(\b\u0012H\n$stream_error_on_invalid_http_message\u0018( \u0001(\u000b2\u001a.google.protobuf.BoolValue\u001a\u0094\u0004\n\u0007Tracing\u0012/\n\u000fclient_sampling\u0018\u0003 \u0001(\u000b2\u0016.envoy.type.v3.Percent\u0012/\n\u000frandom_sampling\u0018\u0004 \u0001(\u000b2\u0016.envoy.type.v3.Percent\u00120\n\u0010overall_sampling\u0018\u0005 \u0001(\u000b2\u0016.envoy.type.v3.Percent\u0012\u000f\n\u0007verbose\u0018\u0006 \u0001(\b\u00129\n\u0013max_path_tag_length\u0018\u0007 \u0001(\u000b2\u001c.google.protobuf.UInt32Value\u00125\n\u000bcustom_tags\u0018\b \u0003(\u000b2 .envoy.type.tracing.v3.CustomTag\u00125\n\bprovider\u0018\t \u0001(\u000b2#.envoy.config.trace.v3.Tracing.Http\"(\n\rOperationName\u0012\u000b\n\u0007INGRESS\u0010\u0000\u0012\n\n\u0006EGRESS\u0010\u0001:[\u009aÅ\u0088\u001eV\nTenvoy.config.filter.network.http_connection_manager.v2.HttpConnectionManager.TracingJ\u0004\b\u0001\u0010\u0002J\u0004\b\u0002\u0010\u0003R\u000eoperation_nameR\u0018request_headers_for_tags\u001a\u0098\u0001\n\u0015InternalAddressConfig\u0012\u0014\n\funix_sockets\u0018\u0001 \u0001(\b:i\u009aÅ\u0088\u001ed\nbenvoy.config.filter.network.http_connection_manager.v2.HttpConnectionManager.InternalAddressConfig\u001aø\u0001\n\u001bSetCurrentClientCertDetails\u0012+\n\u0007subject\u0018\u0001 \u0001(\u000b2\u001a.google.protobuf.BoolValue\u0012\f\n\u0004cert\u0018\u0003 \u0001(\b\u0012\r\n\u0005chain\u0018\u0006 \u0001(\b\u0012\u000b\n\u0003dns\u0018\u0004 \u0001(\b\u0012\u000b\n\u0003uri\u0018\u0005 \u0001(\b:o\u009aÅ\u0088\u001ej\nhenvoy.config.filter.network.http_connection_manager.v2.HttpConnectionManager.SetCurrentClientCertDetailsJ\u0004\b\u0002\u0010\u0003\u001a\u008f\u0002\n\rUpgradeConfig\u0012\u0014\n\fupgrade_type\u0018\u0001 \u0001(\t\u0012X\n\u0007filters\u0018\u0002 \u0003(\u000b2G.envoy.extensions.filters.network.http_connection_manager.v3.HttpFilter\u0012+\n\u0007enabled\u0018\u0003 \u0001(\u000b2\u001a.google.protobuf.BoolValue:a\u009aÅ\u0088\u001e\\\nZenvoy.config.filter.network.http_connection_manager.v2.HttpConnectionManager.UpgradeConfig\"6\n\tCodecType\u0012\b\n\u0004AUTO\u0010\u0000\u0012\t\n\u0005HTTP1\u0010\u0001\u0012\t\n\u0005HTTP2\u0010\u0002\u0012\t\n\u0005HTTP3\u0010\u0003\"S\n\u001aServerHeaderTransformation\u0012\r\n\tOVERWRITE\u0010\u0000\u0012\u0014\n\u0010APPEND_IF_ABSENT\u0010\u0001\u0012\u0010\n\fPASS_THROUGH\u0010\u0002\"y\n\u0018ForwardClientCertDetails\u0012\f\n\bSANITIZE\u0010\u0000\u0012\u0010\n\fFORWARD_ONLY\u0010\u0001\u0012\u0012\n\u000eAPPEND_FORWARD\u0010\u0002\u0012\u0010\n\fSANITIZE_SET\u0010\u0003\u0012\u0017\n\u0013ALWAYS_FORWARD_ONLY\u0010\u0004:S\u009aÅ\u0088\u001eN\nLenvoy.config.filter.network.http_connection_manager.v2.HttpConnectionManagerB\u0016\n\u000froute_specifier\u0012\u0003øB\u0001J\u0004\b\u001b\u0010\u001cJ\u0004\b\u000b\u0010\fR\fidle_timeout\"µ\u0001\n\u0010LocalReplyConfig\u0012\\\n\u0007mappers\u0018\u0001 \u0003(\u000b2K.envoy.extensions.filters.network.http_connection_manager.v3.ResponseMapper\u0012C\n\u000bbody_format\u0018\u0002 \u0001(\u000b2..envoy.config.core.v3.SubstitutionFormatString\"à\u0002\n\u000eResponseMapper\u0012D\n\u0006filter\u0018\u0001 \u0001(\u000b2*.envoy.config.accesslog.v3.AccessLogFilterB\búB\u0005\u008a\u0001\u0002\u0010\u0001\u0012>\n\u000bstatus_code\u0018\u0002 \u0001(\u000b2\u001c.google.protobuf.UInt32ValueB\u000búB\b*\u0006\u0010Ø\u0004(È\u0001\u0012.\n\u0004body\u0018\u0003 \u0001(\u000b2 .envoy.config.core.v3.DataSource\u0012L\n\u0014body_format_override\u0018\u0004 \u0001(\u000b2..envoy.config.core.v3.SubstitutionFormatString\u0012J\n\u000eheaders_to_add\u0018\u0005 \u0003(\u000b2'.envoy.config.core.v3.HeaderValueOptionB\túB\u0006\u0092\u0001\u0003\u0010è\u0007\"\u0095\u0002\n\u0003Rds\u0012C\n\rconfig_source\u0018\u0001 \u0001(\u000b2\".envoy.config.core.v3.ConfigSourceB\búB\u0005\u008a\u0001\u0002\u0010\u0001\u00121\n\u0011route_config_name\u0018\u0002 \u0001(\tB\u0016ò\u0098þ\u008f\u0005\u0010\u0012\u000ename_specifier\u0012S\n\u0014rds_resource_locator\u0018\u0003 \u0001(\u000b2\u001d.udpa.core.v1.ResourceLocatorB\u0016ò\u0098þ\u008f\u0005\u0010\u0012\u000ename_specifier:A\u009aÅ\u0088\u001e<\n:envoy.config.filter.network.http_connection_manager.v2.Rds\"Ü\u0001\n\u001dScopedRouteConfigurationsList\u0012^\n\u001bscoped_route_configurations\u0018\u0001 \u0003(\u000b2/.envoy.config.route.v3.ScopedRouteConfigurationB\búB\u0005\u0092\u0001\u0002\b\u0001:[\u009aÅ\u0088\u001eV\nTenvoy.config.filter.network.http_connection_manager.v2.ScopedRouteConfigurationsList\"½\r\n\fScopedRoutes\u0012\u0015\n\u0004name\u0018\u0001 \u0001(\tB\u0007úB\u0004r\u0002 \u0001\u0012~\n\u0011scope_key_builder\u0018\u0002 \u0001(\u000b2Y.envoy.extensions.filters.network.http_connection_manager.v3.ScopedRoutes.ScopeKeyBuilderB\búB\u0005\u008a\u0001\u0002\u0010\u0001\u0012G\n\u0011rds_config_source\u0018\u0003 \u0001(\u000b2\".envoy.config.core.v3.ConfigSourceB\búB\u0005\u008a\u0001\u0002\u0010\u0001\u0012\u0086\u0001\n scoped_route_configurations_list\u0018\u0004 \u0001(\u000b2Z.envoy.extensions.filters.network.http_connection_manager.v3.ScopedRouteConfigurationsListH\u0000\u0012\\\n\nscoped_rds\u0018\u0005 \u0001(\u000b2F.envoy.extensions.filters.network.http_connection_manager.v3.ScopedRdsH\u0000\u001a\u0080\t\n\u000fScopeKeyBuilder\u0012\u0086\u0001\n\tfragments\u0018\u0001 \u0003(\u000b2i.envoy.extensions.filters.network.http_connection_manager.v3.ScopedRoutes.ScopeKeyBuilder.FragmentBuilderB\búB\u0005\u0092\u0001\u0002\b\u0001\u001a\u0087\u0007\n\u000fFragmentBuilder\u0012 \u0001\n\u0016header_value_extractor\u0018\u0001 \u0001(\u000b2~.envoy.extensions.filters.network.http_connection_manager.v3.ScopedRoutes.ScopeKeyBuilder.FragmentBuilder.HeaderValueExtractorH\u0000\u001a×\u0004\n\u0014HeaderValueExtractor\u0012\u0015\n\u0004name\u0018\u0001 \u0001(\tB\u0007úB\u0004r\u0002 \u0001\u0012\u0019\n\u0011element_separator\u0018\u0002 \u0001(\t\u0012\u000f\n\u0005index\u0018\u0003 \u0001(\rH\u0000\u0012\u009c\u0001\n\u0007element\u0018\u0004 \u0001(\u000b2\u0088\u0001.envoy.extensions.filters.network.http_connection_manager.v3.ScopedRoutes.ScopeKeyBuilder.FragmentBuilder.HeaderValueExtractor.KvElementH\u0000\u001aË\u0001\n\tKvElement\u0012\u001a\n\tseparator\u0018\u0001 \u0001(\tB\u0007úB\u0004r\u0002 \u0001\u0012\u0014\n\u0003key\u0018\u0002 \u0001(\tB\u0007úB\u0004r\u0002 \u0001:\u008b\u0001\u009aÅ\u0088\u001e\u0085\u0001\n\u0082\u0001envoy.config.filter.network.http_connection_manager.v2.ScopedRoutes.ScopeKeyBuilder.FragmentBuilder.HeaderValueExtractor.KvElement:\u007f\u009aÅ\u0088\u001ez\nxenvoy.config.filter.network.http_connection_manager.v2.ScopedRoutes.ScopeKeyBuilder.FragmentBuilder.HeaderValueExtractorB\u000e\n\fextract_type:j\u009aÅ\u0088\u001ee\ncenvoy.config.filter.network.http_connection_manager.v2.ScopedRoutes.ScopeKeyBuilder.FragmentBuilderB\u000b\n\u0004type\u0012\u0003øB\u0001:Z\u009aÅ\u0088\u001eU\nSenvoy.config.filter.network.http_connection_manager.v2.ScopedRoutes.ScopeKeyBuilder:J\u009aÅ\u0088\u001eE\nCenvoy.config.filter.network.http_connection_manager.v2.ScopedRoutesB\u0017\n\u0010config_specifier\u0012\u0003øB\u0001\"¤\u0001\n\tScopedRds\u0012N\n\u0018scoped_rds_config_source\u0018\u0001 \u0001(\u000b2\".envoy.config.core.v3.ConfigSourceB\búB\u0005\u008a\u0001\u0002\u0010\u0001:G\u009aÅ\u0088\u001eB\n@envoy.config.filter.network.http_connection_manager.v2.ScopedRds\"\u0087\u0002\n\nHttpFilter\u0012\u0015\n\u0004name\u0018\u0001 \u0001(\tB\u0007úB\u0004r\u0002 \u0001\u0012,\n\ftyped_config\u0018\u0004 \u0001(\u000b2\u0014.google.protobuf.AnyH\u0000\u0012G\n\u0010config_discovery\u0018\u0005 \u0001(\u000b2+.envoy.config.core.v3.ExtensionConfigSourceH\u0000:H\u009aÅ\u0088\u001eC\nAenvoy.config.filter.network.http_connection_manager.v2.HttpFilterB\r\n\u000bconfig_typeJ\u0004\b\u0003\u0010\u0004J\u0004\b\u0002\u0010\u0003R\u0006config\"\u0092\u0001\n\u0012RequestIDExtension\u0012*\n\ftyped_config\u0018\u0001 \u0001(\u000b2\u0014.google.protobuf.Any:P\u009aÅ\u0088\u001eK\nIenvoy.config.filter.network.http_connection_manager.v2.RequestIDExtensionBq\nIio.envoyproxy.envoy.extensions.filters.network.http_connection_manager.v3B\u001aHttpConnectionManagerProtoP\u0001º\u0080ÈÑ\u0006\u0002\u0010\u0002b\u0006proto3"}, new Descriptors.FileDescriptor[]{AccesslogProto.getDescriptor(), BaseProto.getDescriptor(), ConfigSourceProto.getDescriptor(), ExtensionProto.getDescriptor(), ProtocolProto.getDescriptor(), SubstitutionFormatStringProto.getDescriptor(), RouteProto.getDescriptor(), ScopedRouteProto.getDescriptor(), HttpTracerProto.getDescriptor(), CustomTagProto.getDescriptor(), PercentProto.getDescriptor(), AnyProto.getDescriptor(), DurationProto.getDescriptor(), StructProto.getDescriptor(), WrappersProto.getDescriptor(), ResourceLocatorProto.getDescriptor(), Deprecation.getDescriptor(), Migrate.getDescriptor(), Security.getDescriptor(), Status.getDescriptor(), Versioning.getDescriptor(), Validate.getDescriptor()});

    static {
        Descriptors.Descriptor descriptor2 = (Descriptors.Descriptor) getDescriptor().getMessageTypes().get(0);
        internal_static_envoy_extensions_filters_network_http_connection_manager_v3_HttpConnectionManager_descriptor = descriptor2;
        internal_static_envoy_extensions_filters_network_http_connection_manager_v3_HttpConnectionManager_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor2, new String[]{"CodecType", "StatPrefix", "Rds", "RouteConfig", "ScopedRoutes", "HttpFilters", "AddUserAgent", "Tracing", "CommonHttpProtocolOptions", "HttpProtocolOptions", "Http2ProtocolOptions", "ServerName", "ServerHeaderTransformation", "MaxRequestHeadersKb", "StreamIdleTimeout", "RequestTimeout", "DrainTimeout", "DelayedCloseTimeout", "AccessLog", "UseRemoteAddress", "XffNumTrustedHops", "InternalAddressConfig", "SkipXffAppend", "Via", "GenerateRequestId", "PreserveExternalRequestId", "AlwaysSetRequestIdInResponse", "ForwardClientCertDetails", "SetCurrentClientCertDetails", "Proxy100Continue", "RepresentIpv4RemoteAddressAsIpv4MappedIpv6", "UpgradeConfigs", "NormalizePath", "MergeSlashes", "RequestIdExtension", "LocalReplyConfig", "StripMatchingHostPort", "StreamErrorOnInvalidHttpMessage", "RouteSpecifier"});
        Descriptors.Descriptor descriptor3 = (Descriptors.Descriptor) descriptor2.getNestedTypes().get(0);
        internal_static_envoy_extensions_filters_network_http_connection_manager_v3_HttpConnectionManager_Tracing_descriptor = descriptor3;
        internal_static_envoy_extensions_filters_network_http_connection_manager_v3_HttpConnectionManager_Tracing_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor3, new String[]{"ClientSampling", "RandomSampling", "OverallSampling", "Verbose", "MaxPathTagLength", "CustomTags", "Provider"});
        Descriptors.Descriptor descriptor4 = (Descriptors.Descriptor) descriptor2.getNestedTypes().get(1);
        internal_static_envoy_extensions_filters_network_http_connection_manager_v3_HttpConnectionManager_InternalAddressConfig_descriptor = descriptor4;
        internal_static_envoy_extensions_filters_network_http_connection_manager_v3_HttpConnectionManager_InternalAddressConfig_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor4, new String[]{"UnixSockets"});
        Descriptors.Descriptor descriptor5 = (Descriptors.Descriptor) descriptor2.getNestedTypes().get(2);
        internal_static_envoy_extensions_filters_network_http_connection_manager_v3_HttpConnectionManager_SetCurrentClientCertDetails_descriptor = descriptor5;
        internal_static_envoy_extensions_filters_network_http_connection_manager_v3_HttpConnectionManager_SetCurrentClientCertDetails_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor5, new String[]{"Subject", "Cert", "Chain", "Dns", "Uri"});
        Descriptors.Descriptor descriptor6 = (Descriptors.Descriptor) descriptor2.getNestedTypes().get(3);
        internal_static_envoy_extensions_filters_network_http_connection_manager_v3_HttpConnectionManager_UpgradeConfig_descriptor = descriptor6;
        internal_static_envoy_extensions_filters_network_http_connection_manager_v3_HttpConnectionManager_UpgradeConfig_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor6, new String[]{"UpgradeType", "Filters", "Enabled"});
        Descriptors.Descriptor descriptor7 = (Descriptors.Descriptor) getDescriptor().getMessageTypes().get(1);
        internal_static_envoy_extensions_filters_network_http_connection_manager_v3_LocalReplyConfig_descriptor = descriptor7;
        internal_static_envoy_extensions_filters_network_http_connection_manager_v3_LocalReplyConfig_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor7, new String[]{"Mappers", "BodyFormat"});
        Descriptors.Descriptor descriptor8 = (Descriptors.Descriptor) getDescriptor().getMessageTypes().get(2);
        internal_static_envoy_extensions_filters_network_http_connection_manager_v3_ResponseMapper_descriptor = descriptor8;
        internal_static_envoy_extensions_filters_network_http_connection_manager_v3_ResponseMapper_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor8, new String[]{"Filter", "StatusCode", "Body", "BodyFormatOverride", "HeadersToAdd"});
        Descriptors.Descriptor descriptor9 = (Descriptors.Descriptor) getDescriptor().getMessageTypes().get(3);
        internal_static_envoy_extensions_filters_network_http_connection_manager_v3_Rds_descriptor = descriptor9;
        internal_static_envoy_extensions_filters_network_http_connection_manager_v3_Rds_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor9, new String[]{"ConfigSource", "RouteConfigName", "RdsResourceLocator"});
        Descriptors.Descriptor descriptor10 = (Descriptors.Descriptor) getDescriptor().getMessageTypes().get(4);
        internal_static_envoy_extensions_filters_network_http_connection_manager_v3_ScopedRouteConfigurationsList_descriptor = descriptor10;
        internal_static_envoy_extensions_filters_network_http_connection_manager_v3_ScopedRouteConfigurationsList_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor10, new String[]{"ScopedRouteConfigurations"});
        Descriptors.Descriptor descriptor11 = (Descriptors.Descriptor) getDescriptor().getMessageTypes().get(5);
        internal_static_envoy_extensions_filters_network_http_connection_manager_v3_ScopedRoutes_descriptor = descriptor11;
        internal_static_envoy_extensions_filters_network_http_connection_manager_v3_ScopedRoutes_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor11, new String[]{"Name", "ScopeKeyBuilder", "RdsConfigSource", "ScopedRouteConfigurationsList", "ScopedRds", "ConfigSpecifier"});
        Descriptors.Descriptor descriptor12 = (Descriptors.Descriptor) descriptor11.getNestedTypes().get(0);
        internal_static_envoy_extensions_filters_network_http_connection_manager_v3_ScopedRoutes_ScopeKeyBuilder_descriptor = descriptor12;
        internal_static_envoy_extensions_filters_network_http_connection_manager_v3_ScopedRoutes_ScopeKeyBuilder_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor12, new String[]{"Fragments"});
        Descriptors.Descriptor descriptor13 = (Descriptors.Descriptor) descriptor12.getNestedTypes().get(0);
        internal_static_envoy_extensions_filters_network_http_connection_manager_v3_ScopedRoutes_ScopeKeyBuilder_FragmentBuilder_descriptor = descriptor13;
        internal_static_envoy_extensions_filters_network_http_connection_manager_v3_ScopedRoutes_ScopeKeyBuilder_FragmentBuilder_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor13, new String[]{"HeaderValueExtractor", "Type"});
        Descriptors.Descriptor descriptor14 = (Descriptors.Descriptor) descriptor13.getNestedTypes().get(0);
        internal_static_envoy_extensions_filters_network_http_connection_manager_v3_ScopedRoutes_ScopeKeyBuilder_FragmentBuilder_HeaderValueExtractor_descriptor = descriptor14;
        internal_static_envoy_extensions_filters_network_http_connection_manager_v3_ScopedRoutes_ScopeKeyBuilder_FragmentBuilder_HeaderValueExtractor_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor14, new String[]{"Name", "ElementSeparator", "Index", "Element", "ExtractType"});
        Descriptors.Descriptor descriptor15 = (Descriptors.Descriptor) descriptor14.getNestedTypes().get(0);
        internal_static_envoy_extensions_filters_network_http_connection_manager_v3_ScopedRoutes_ScopeKeyBuilder_FragmentBuilder_HeaderValueExtractor_KvElement_descriptor = descriptor15;
        internal_static_envoy_extensions_filters_network_http_connection_manager_v3_ScopedRoutes_ScopeKeyBuilder_FragmentBuilder_HeaderValueExtractor_KvElement_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor15, new String[]{"Separator", "Key"});
        Descriptors.Descriptor descriptor16 = (Descriptors.Descriptor) getDescriptor().getMessageTypes().get(6);
        internal_static_envoy_extensions_filters_network_http_connection_manager_v3_ScopedRds_descriptor = descriptor16;
        internal_static_envoy_extensions_filters_network_http_connection_manager_v3_ScopedRds_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor16, new String[]{"ScopedRdsConfigSource"});
        Descriptors.Descriptor descriptor17 = (Descriptors.Descriptor) getDescriptor().getMessageTypes().get(7);
        internal_static_envoy_extensions_filters_network_http_connection_manager_v3_HttpFilter_descriptor = descriptor17;
        internal_static_envoy_extensions_filters_network_http_connection_manager_v3_HttpFilter_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor17, new String[]{"Name", "TypedConfig", "ConfigDiscovery", "ConfigType"});
        Descriptors.Descriptor descriptor18 = (Descriptors.Descriptor) getDescriptor().getMessageTypes().get(8);
        internal_static_envoy_extensions_filters_network_http_connection_manager_v3_RequestIDExtension_descriptor = descriptor18;
        internal_static_envoy_extensions_filters_network_http_connection_manager_v3_RequestIDExtension_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor18, new String[]{"TypedConfig"});
        ExtensionRegistry extensionRegistryNewInstance = ExtensionRegistry.newInstance();
        extensionRegistryNewInstance.add(Migrate.fieldMigrate);
        extensionRegistryNewInstance.add(Status.fileStatus);
        extensionRegistryNewInstance.add(Security.security);
        extensionRegistryNewInstance.add(Versioning.versioning);
        extensionRegistryNewInstance.add(Validate.required);
        extensionRegistryNewInstance.add(Validate.rules);
        Descriptors.FileDescriptor.internalUpdateFileDescriptor(descriptor, extensionRegistryNewInstance);
        AccesslogProto.getDescriptor();
        BaseProto.getDescriptor();
        ConfigSourceProto.getDescriptor();
        ExtensionProto.getDescriptor();
        ProtocolProto.getDescriptor();
        SubstitutionFormatStringProto.getDescriptor();
        RouteProto.getDescriptor();
        ScopedRouteProto.getDescriptor();
        HttpTracerProto.getDescriptor();
        CustomTagProto.getDescriptor();
        PercentProto.getDescriptor();
        AnyProto.getDescriptor();
        DurationProto.getDescriptor();
        StructProto.getDescriptor();
        WrappersProto.getDescriptor();
        ResourceLocatorProto.getDescriptor();
        Deprecation.getDescriptor();
        Migrate.getDescriptor();
        Security.getDescriptor();
        Status.getDescriptor();
        Versioning.getDescriptor();
        Validate.getDescriptor();
    }

    private HttpConnectionManagerProto() {
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
