package io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core;

import com.google.protobuf.Descriptors;
import com.google.protobuf.DurationProto;
import com.google.protobuf.ExtensionRegistry;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageV3;
import com.google.protobuf.WrappersProto;
import io.grpc.xds.shaded.io.envoyproxy.pgv.validate.Validate;
import udpa.annotations.Migrate;
import udpa.annotations.Status;

/* loaded from: classes3.dex */
public final class ProtocolProto {
    static final Descriptors.Descriptor internal_static_envoy_api_v2_core_GrpcProtocolOptions_descriptor;
    static final GeneratedMessageV3.FieldAccessorTable internal_static_envoy_api_v2_core_GrpcProtocolOptions_fieldAccessorTable;
    static final Descriptors.Descriptor internal_static_envoy_api_v2_core_Http1ProtocolOptions_HeaderKeyFormat_ProperCaseWords_descriptor;
    static final GeneratedMessageV3.FieldAccessorTable internal_static_envoy_api_v2_core_Http1ProtocolOptions_HeaderKeyFormat_ProperCaseWords_fieldAccessorTable;
    static final Descriptors.Descriptor internal_static_envoy_api_v2_core_Http1ProtocolOptions_HeaderKeyFormat_descriptor;
    static final GeneratedMessageV3.FieldAccessorTable internal_static_envoy_api_v2_core_Http1ProtocolOptions_HeaderKeyFormat_fieldAccessorTable;
    static final Descriptors.Descriptor internal_static_envoy_api_v2_core_Http1ProtocolOptions_descriptor;
    static final GeneratedMessageV3.FieldAccessorTable internal_static_envoy_api_v2_core_Http1ProtocolOptions_fieldAccessorTable;
    static final Descriptors.Descriptor internal_static_envoy_api_v2_core_Http2ProtocolOptions_SettingsParameter_descriptor;
    static final GeneratedMessageV3.FieldAccessorTable internal_static_envoy_api_v2_core_Http2ProtocolOptions_SettingsParameter_fieldAccessorTable;
    static final Descriptors.Descriptor internal_static_envoy_api_v2_core_Http2ProtocolOptions_descriptor;
    static final GeneratedMessageV3.FieldAccessorTable internal_static_envoy_api_v2_core_Http2ProtocolOptions_fieldAccessorTable;
    static final Descriptors.Descriptor internal_static_envoy_api_v2_core_HttpProtocolOptions_descriptor;
    static final GeneratedMessageV3.FieldAccessorTable internal_static_envoy_api_v2_core_HttpProtocolOptions_fieldAccessorTable;
    static final Descriptors.Descriptor internal_static_envoy_api_v2_core_TcpProtocolOptions_descriptor;
    static final GeneratedMessageV3.FieldAccessorTable internal_static_envoy_api_v2_core_TcpProtocolOptions_fieldAccessorTable;
    static final Descriptors.Descriptor internal_static_envoy_api_v2_core_UpstreamHttpProtocolOptions_descriptor;
    static final GeneratedMessageV3.FieldAccessorTable internal_static_envoy_api_v2_core_UpstreamHttpProtocolOptions_fieldAccessorTable;
    private static Descriptors.FileDescriptor descriptor = Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(new String[]{"\n envoy/api/v2/core/protocol.proto\u0012\u0011envoy.api.v2.core\u001a\u001egoogle/protobuf/duration.proto\u001a\u001egoogle/protobuf/wrappers.proto\u001a\u001eudpa/annotations/migrate.proto\u001a\u001dudpa/annotations/status.proto\u001a\u0017validate/validate.proto\"\u0014\n\u0012TcpProtocolOptions\"L\n\u001bUpstreamHttpProtocolOptions\u0012\u0010\n\bauto_sni\u0018\u0001 \u0001(\b\u0012\u001b\n\u0013auto_san_validation\u0018\u0002 \u0001(\b\"º\u0003\n\u0013HttpProtocolOptions\u0012/\n\fidle_timeout\u0018\u0001 \u0001(\u000b2\u0019.google.protobuf.Duration\u0012:\n\u0017max_connection_duration\u0018\u0003 \u0001(\u000b2\u0019.google.protobuf.Duration\u0012@\n\u0011max_headers_count\u0018\u0002 \u0001(\u000b2\u001c.google.protobuf.UInt32ValueB\u0007úB\u0004*\u0002(\u0001\u00126\n\u0013max_stream_duration\u0018\u0004 \u0001(\u000b2\u0019.google.protobuf.Duration\u0012l\n\u001fheaders_with_underscores_action\u0018\u0005 \u0001(\u000e2C.envoy.api.v2.core.HttpProtocolOptions.HeadersWithUnderscoresAction\"N\n\u001cHeadersWithUnderscoresAction\u0012\t\n\u0005ALLOW\u0010\u0000\u0012\u0012\n\u000eREJECT_REQUEST\u0010\u0001\u0012\u000f\n\u000bDROP_HEADER\u0010\u0002\"\u0098\u0003\n\u0014Http1ProtocolOptions\u00126\n\u0012allow_absolute_url\u0018\u0001 \u0001(\u000b2\u001a.google.protobuf.BoolValue\u0012\u0016\n\u000eaccept_http_10\u0018\u0002 \u0001(\b\u0012 \n\u0018default_host_for_http_10\u0018\u0003 \u0001(\t\u0012R\n\u0011header_key_format\u0018\u0004 \u0001(\u000b27.envoy.api.v2.core.Http1ProtocolOptions.HeaderKeyFormat\u0012\u0017\n\u000fenable_trailers\u0018\u0005 \u0001(\b\u001a \u0001\n\u000fHeaderKeyFormat\u0012d\n\u0011proper_case_words\u0018\u0001 \u0001(\u000b2G.envoy.api.v2.core.Http1ProtocolOptions.HeaderKeyFormat.ProperCaseWordsH\u0000\u001a\u0011\n\u000fProperCaseWordsB\u0014\n\rheader_format\u0012\u0003øB\u0001\"³\b\n\u0014Http2ProtocolOptions\u00126\n\u0010hpack_table_size\u0018\u0001 \u0001(\u000b2\u001c.google.protobuf.UInt32Value\u0012K\n\u0016max_concurrent_streams\u0018\u0002 \u0001(\u000b2\u001c.google.protobuf.UInt32ValueB\rúB\n*\b\u0018ÿÿÿÿ\u0007(\u0001\u0012Q\n\u001ainitial_stream_window_size\u0018\u0003 \u0001(\u000b2\u001c.google.protobuf.UInt32ValueB\u000fúB\f*\n\u0018ÿÿÿÿ\u0007(ÿÿ\u0003\u0012U\n\u001einitial_connection_window_size\u0018\u0004 \u0001(\u000b2\u001c.google.protobuf.UInt32ValueB\u000fúB\f*\n\u0018ÿÿÿÿ\u0007(ÿÿ\u0003\u0012\u0015\n\rallow_connect\u0018\u0005 \u0001(\b\u0012\u0016\n\u000eallow_metadata\u0018\u0006 \u0001(\b\u0012B\n\u0013max_outbound_frames\u0018\u0007 \u0001(\u000b2\u001c.google.protobuf.UInt32ValueB\u0007úB\u0004*\u0002(\u0001\u0012J\n\u001bmax_outbound_control_frames\u0018\b \u0001(\u000b2\u001c.google.protobuf.UInt32ValueB\u0007úB\u0004*\u0002(\u0001\u0012W\n1max_consecutive_inbound_frames_with_empty_payload\u0018\t \u0001(\u000b2\u001c.google.protobuf.UInt32Value\u0012L\n&max_inbound_priority_frames_per_stream\u0018\n \u0001(\u000b2\u001c.google.protobuf.UInt32Value\u0012c\n4max_inbound_window_update_frames_per_data_frame_sent\u0018\u000b \u0001(\u000b2\u001c.google.protobuf.UInt32ValueB\u0007úB\u0004*\u0002(\u0001\u0012.\n&stream_error_on_invalid_http_messaging\u0018\f \u0001(\b\u0012]\n\u001acustom_settings_parameters\u0018\r \u0003(\u000b29.envoy.api.v2.core.Http2ProtocolOptions.SettingsParameter\u001a\u0091\u0001\n\u0011SettingsParameter\u0012E\n\nidentifier\u0018\u0001 \u0001(\u000b2\u001c.google.protobuf.UInt32ValueB\u0013úB\b*\u0006\u0018\u0080\u0080\u0004(\u0001úB\u0005\u008a\u0001\u0002\u0010\u0001\u00125\n\u0005value\u0018\u0002 \u0001(\u000b2\u001c.google.protobuf.UInt32ValueB\búB\u0005\u008a\u0001\u0002\u0010\u0001\"^\n\u0013GrpcProtocolOptions\u0012G\n\u0016http2_protocol_options\u0018\u0001 \u0001(\u000b2'.envoy.api.v2.core.Http2ProtocolOptionsBV\n\u001fio.envoyproxy.envoy.api.v2.coreB\rProtocolProtoP\u0001ò\u0098þ\u008f\u0005\u0016\u0012\u0014envoy.config.core.v3º\u0080ÈÑ\u0006\u0002\u0010\u0001b\u0006proto3"}, new Descriptors.FileDescriptor[]{DurationProto.getDescriptor(), WrappersProto.getDescriptor(), Migrate.getDescriptor(), Status.getDescriptor(), Validate.getDescriptor()});

    static {
        Descriptors.Descriptor descriptor2 = (Descriptors.Descriptor) getDescriptor().getMessageTypes().get(0);
        internal_static_envoy_api_v2_core_TcpProtocolOptions_descriptor = descriptor2;
        internal_static_envoy_api_v2_core_TcpProtocolOptions_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor2, new String[0]);
        Descriptors.Descriptor descriptor3 = (Descriptors.Descriptor) getDescriptor().getMessageTypes().get(1);
        internal_static_envoy_api_v2_core_UpstreamHttpProtocolOptions_descriptor = descriptor3;
        internal_static_envoy_api_v2_core_UpstreamHttpProtocolOptions_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor3, new String[]{"AutoSni", "AutoSanValidation"});
        Descriptors.Descriptor descriptor4 = (Descriptors.Descriptor) getDescriptor().getMessageTypes().get(2);
        internal_static_envoy_api_v2_core_HttpProtocolOptions_descriptor = descriptor4;
        internal_static_envoy_api_v2_core_HttpProtocolOptions_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor4, new String[]{"IdleTimeout", "MaxConnectionDuration", "MaxHeadersCount", "MaxStreamDuration", "HeadersWithUnderscoresAction"});
        Descriptors.Descriptor descriptor5 = (Descriptors.Descriptor) getDescriptor().getMessageTypes().get(3);
        internal_static_envoy_api_v2_core_Http1ProtocolOptions_descriptor = descriptor5;
        internal_static_envoy_api_v2_core_Http1ProtocolOptions_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor5, new String[]{"AllowAbsoluteUrl", "AcceptHttp10", "DefaultHostForHttp10", "HeaderKeyFormat", "EnableTrailers"});
        Descriptors.Descriptor descriptor6 = (Descriptors.Descriptor) descriptor5.getNestedTypes().get(0);
        internal_static_envoy_api_v2_core_Http1ProtocolOptions_HeaderKeyFormat_descriptor = descriptor6;
        internal_static_envoy_api_v2_core_Http1ProtocolOptions_HeaderKeyFormat_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor6, new String[]{"ProperCaseWords", "HeaderFormat"});
        Descriptors.Descriptor descriptor7 = (Descriptors.Descriptor) descriptor6.getNestedTypes().get(0);
        internal_static_envoy_api_v2_core_Http1ProtocolOptions_HeaderKeyFormat_ProperCaseWords_descriptor = descriptor7;
        internal_static_envoy_api_v2_core_Http1ProtocolOptions_HeaderKeyFormat_ProperCaseWords_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor7, new String[0]);
        Descriptors.Descriptor descriptor8 = (Descriptors.Descriptor) getDescriptor().getMessageTypes().get(4);
        internal_static_envoy_api_v2_core_Http2ProtocolOptions_descriptor = descriptor8;
        internal_static_envoy_api_v2_core_Http2ProtocolOptions_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor8, new String[]{"HpackTableSize", "MaxConcurrentStreams", "InitialStreamWindowSize", "InitialConnectionWindowSize", "AllowConnect", "AllowMetadata", "MaxOutboundFrames", "MaxOutboundControlFrames", "MaxConsecutiveInboundFramesWithEmptyPayload", "MaxInboundPriorityFramesPerStream", "MaxInboundWindowUpdateFramesPerDataFrameSent", "StreamErrorOnInvalidHttpMessaging", "CustomSettingsParameters"});
        Descriptors.Descriptor descriptor9 = (Descriptors.Descriptor) descriptor8.getNestedTypes().get(0);
        internal_static_envoy_api_v2_core_Http2ProtocolOptions_SettingsParameter_descriptor = descriptor9;
        internal_static_envoy_api_v2_core_Http2ProtocolOptions_SettingsParameter_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor9, new String[]{"Identifier", "Value"});
        Descriptors.Descriptor descriptor10 = (Descriptors.Descriptor) getDescriptor().getMessageTypes().get(5);
        internal_static_envoy_api_v2_core_GrpcProtocolOptions_descriptor = descriptor10;
        internal_static_envoy_api_v2_core_GrpcProtocolOptions_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor10, new String[]{"Http2ProtocolOptions"});
        ExtensionRegistry extensionRegistryNewInstance = ExtensionRegistry.newInstance();
        extensionRegistryNewInstance.add(Migrate.fileMigrate);
        extensionRegistryNewInstance.add(Status.fileStatus);
        extensionRegistryNewInstance.add(Validate.required);
        extensionRegistryNewInstance.add(Validate.rules);
        Descriptors.FileDescriptor.internalUpdateFileDescriptor(descriptor, extensionRegistryNewInstance);
        DurationProto.getDescriptor();
        WrappersProto.getDescriptor();
        Migrate.getDescriptor();
        Status.getDescriptor();
        Validate.getDescriptor();
    }

    private ProtocolProto() {
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
