package io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core;

import com.google.protobuf.AnyProto;
import com.google.protobuf.Descriptors;
import com.google.protobuf.DurationProto;
import com.google.protobuf.ExtensionRegistry;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageV3;
import com.google.protobuf.StructProto;
import com.google.protobuf.WrappersProto;
import io.grpc.xds.shaded.io.envoyproxy.envoy.type.PercentProto;
import io.grpc.xds.shaded.io.envoyproxy.envoy.type.SemanticVersionProto;
import io.grpc.xds.shaded.io.envoyproxy.pgv.validate.Validate;
import udpa.annotations.Migrate;
import udpa.annotations.Status;

/* loaded from: classes3.dex */
public final class BaseProto {
    static final Descriptors.Descriptor internal_static_envoy_api_v2_core_AsyncDataSource_descriptor;
    static final GeneratedMessageV3.FieldAccessorTable internal_static_envoy_api_v2_core_AsyncDataSource_fieldAccessorTable;
    static final Descriptors.Descriptor internal_static_envoy_api_v2_core_BuildVersion_descriptor;
    static final GeneratedMessageV3.FieldAccessorTable internal_static_envoy_api_v2_core_BuildVersion_fieldAccessorTable;
    static final Descriptors.Descriptor internal_static_envoy_api_v2_core_ControlPlane_descriptor;
    static final GeneratedMessageV3.FieldAccessorTable internal_static_envoy_api_v2_core_ControlPlane_fieldAccessorTable;
    static final Descriptors.Descriptor internal_static_envoy_api_v2_core_DataSource_descriptor;
    static final GeneratedMessageV3.FieldAccessorTable internal_static_envoy_api_v2_core_DataSource_fieldAccessorTable;
    static final Descriptors.Descriptor internal_static_envoy_api_v2_core_Extension_descriptor;
    static final GeneratedMessageV3.FieldAccessorTable internal_static_envoy_api_v2_core_Extension_fieldAccessorTable;
    static final Descriptors.Descriptor internal_static_envoy_api_v2_core_HeaderMap_descriptor;
    static final GeneratedMessageV3.FieldAccessorTable internal_static_envoy_api_v2_core_HeaderMap_fieldAccessorTable;
    static final Descriptors.Descriptor internal_static_envoy_api_v2_core_HeaderValueOption_descriptor;
    static final GeneratedMessageV3.FieldAccessorTable internal_static_envoy_api_v2_core_HeaderValueOption_fieldAccessorTable;
    static final Descriptors.Descriptor internal_static_envoy_api_v2_core_HeaderValue_descriptor;
    static final GeneratedMessageV3.FieldAccessorTable internal_static_envoy_api_v2_core_HeaderValue_fieldAccessorTable;
    static final Descriptors.Descriptor internal_static_envoy_api_v2_core_Locality_descriptor;
    static final GeneratedMessageV3.FieldAccessorTable internal_static_envoy_api_v2_core_Locality_fieldAccessorTable;
    static final Descriptors.Descriptor internal_static_envoy_api_v2_core_Metadata_FilterMetadataEntry_descriptor;
    static final GeneratedMessageV3.FieldAccessorTable internal_static_envoy_api_v2_core_Metadata_FilterMetadataEntry_fieldAccessorTable;
    static final Descriptors.Descriptor internal_static_envoy_api_v2_core_Metadata_descriptor;
    static final GeneratedMessageV3.FieldAccessorTable internal_static_envoy_api_v2_core_Metadata_fieldAccessorTable;
    static final Descriptors.Descriptor internal_static_envoy_api_v2_core_Node_descriptor;
    static final GeneratedMessageV3.FieldAccessorTable internal_static_envoy_api_v2_core_Node_fieldAccessorTable;
    static final Descriptors.Descriptor internal_static_envoy_api_v2_core_RemoteDataSource_descriptor;
    static final GeneratedMessageV3.FieldAccessorTable internal_static_envoy_api_v2_core_RemoteDataSource_fieldAccessorTable;
    static final Descriptors.Descriptor internal_static_envoy_api_v2_core_RetryPolicy_descriptor;
    static final GeneratedMessageV3.FieldAccessorTable internal_static_envoy_api_v2_core_RetryPolicy_fieldAccessorTable;
    static final Descriptors.Descriptor internal_static_envoy_api_v2_core_RuntimeDouble_descriptor;
    static final GeneratedMessageV3.FieldAccessorTable internal_static_envoy_api_v2_core_RuntimeDouble_fieldAccessorTable;
    static final Descriptors.Descriptor internal_static_envoy_api_v2_core_RuntimeFeatureFlag_descriptor;
    static final GeneratedMessageV3.FieldAccessorTable internal_static_envoy_api_v2_core_RuntimeFeatureFlag_fieldAccessorTable;
    static final Descriptors.Descriptor internal_static_envoy_api_v2_core_RuntimeFractionalPercent_descriptor;
    static final GeneratedMessageV3.FieldAccessorTable internal_static_envoy_api_v2_core_RuntimeFractionalPercent_fieldAccessorTable;
    static final Descriptors.Descriptor internal_static_envoy_api_v2_core_RuntimeUInt32_descriptor;
    static final GeneratedMessageV3.FieldAccessorTable internal_static_envoy_api_v2_core_RuntimeUInt32_fieldAccessorTable;
    static final Descriptors.Descriptor internal_static_envoy_api_v2_core_TransportSocket_descriptor;
    static final GeneratedMessageV3.FieldAccessorTable internal_static_envoy_api_v2_core_TransportSocket_fieldAccessorTable;
    private static Descriptors.FileDescriptor descriptor = Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(new String[]{"\n\u001cenvoy/api/v2/core/base.proto\u0012\u0011envoy.api.v2.core\u001a\u001fenvoy/api/v2/core/address.proto\u001a\u001fenvoy/api/v2/core/backoff.proto\u001a envoy/api/v2/core/http_uri.proto\u001a\u0018envoy/type/percent.proto\u001a!envoy/type/semantic_version.proto\u001a\u0019google/protobuf/any.proto\u001a\u001egoogle/protobuf/duration.proto\u001a\u001cgoogle/protobuf/struct.proto\u001a\u001egoogle/protobuf/wrappers.proto\u001a\u001eudpa/annotations/migrate.proto\u001a\u001dudpa/annotations/status.proto\u001a\u0017validate/validate.proto\u001a%envoy/api/v2/core/socket_option.proto\":\n\bLocality\u0012\u000e\n\u0006region\u0018\u0001 \u0001(\t\u0012\f\n\u0004zone\u0018\u0002 \u0001(\t\u0012\u0010\n\bsub_zone\u0018\u0003 \u0001(\t\"g\n\fBuildVersion\u0012,\n\u0007version\u0018\u0001 \u0001(\u000b2\u001b.envoy.type.SemanticVersion\u0012)\n\bmetadata\u0018\u0002 \u0001(\u000b2\u0017.google.protobuf.Struct\"\u0088\u0001\n\tExtension\u0012\f\n\u0004name\u0018\u0001 \u0001(\t\u0012\u0010\n\bcategory\u0018\u0002 \u0001(\t\u0012\u0017\n\u000ftype_descriptor\u0018\u0003 \u0001(\t\u00120\n\u0007version\u0018\u0004 \u0001(\u000b2\u001f.envoy.api.v2.core.BuildVersion\u0012\u0010\n\bdisabled\u0018\u0005 \u0001(\b\"³\u0003\n\u0004Node\u0012\n\n\u0002id\u0018\u0001 \u0001(\t\u0012\u000f\n\u0007cluster\u0018\u0002 \u0001(\t\u0012)\n\bmetadata\u0018\u0003 \u0001(\u000b2\u0017.google.protobuf.Struct\u0012-\n\blocality\u0018\u0004 \u0001(\u000b2\u001b.envoy.api.v2.core.Locality\u0012\u0019\n\rbuild_version\u0018\u0005 \u0001(\tB\u0002\u0018\u0001\u0012\u0017\n\u000fuser_agent_name\u0018\u0006 \u0001(\t\u0012\u001c\n\u0012user_agent_version\u0018\u0007 \u0001(\tH\u0000\u0012C\n\u0018user_agent_build_version\u0018\b \u0001(\u000b2\u001f.envoy.api.v2.core.BuildVersionH\u0000\u00120\n\nextensions\u0018\t \u0003(\u000b2\u001c.envoy.api.v2.core.Extension\u0012\u0017\n\u000fclient_features\u0018\n \u0003(\t\u00127\n\u0013listening_addresses\u0018\u000b \u0003(\u000b2\u001a.envoy.api.v2.core.AddressB\u0019\n\u0017user_agent_version_type\"¤\u0001\n\bMetadata\u0012H\n\u000ffilter_metadata\u0018\u0001 \u0003(\u000b2/.envoy.api.v2.core.Metadata.FilterMetadataEntry\u001aN\n\u0013FilterMetadataEntry\u0012\u000b\n\u0003key\u0018\u0001 \u0001(\t\u0012&\n\u0005value\u0018\u0002 \u0001(\u000b2\u0017.google.protobuf.Struct:\u00028\u0001\"D\n\rRuntimeUInt32\u0012\u0015\n\rdefault_value\u0018\u0002 \u0001(\r\u0012\u001c\n\u000bruntime_key\u0018\u0003 \u0001(\tB\u0007úB\u0004r\u0002 \u0001\"D\n\rRuntimeDouble\u0012\u0015\n\rdefault_value\u0018\u0001 \u0001(\u0001\u0012\u001c\n\u000bruntime_key\u0018\u0002 \u0001(\tB\u0007úB\u0004r\u0002 \u0001\"o\n\u0012RuntimeFeatureFlag\u0012;\n\rdefault_value\u0018\u0001 \u0001(\u000b2\u001a.google.protobuf.BoolValueB\búB\u0005\u008a\u0001\u0002\u0010\u0001\u0012\u001c\n\u000bruntime_key\u0018\u0002 \u0001(\tB\u0007úB\u0004r\u0002 \u0001\"M\n\u000bHeaderValue\u0012\u001e\n\u0003key\u0018\u0001 \u0001(\tB\u0011úB\u000er\f \u0001(\u0080\u0080\u0001À\u0001\u0001È\u0001\u0000\u0012\u001e\n\u0005value\u0018\u0002 \u0001(\tB\u000fúB\fr\n(\u0080\u0080\u0001À\u0001\u0002È\u0001\u0000\"y\n\u0011HeaderValueOption\u00128\n\u0006header\u0018\u0001 \u0001(\u000b2\u001e.envoy.api.v2.core.HeaderValueB\búB\u0005\u008a\u0001\u0002\u0010\u0001\u0012*\n\u0006append\u0018\u0002 \u0001(\u000b2\u001a.google.protobuf.BoolValue\"<\n\tHeaderMap\u0012/\n\u0007headers\u0018\u0001 \u0003(\u000b2\u001e.envoy.api.v2.core.HeaderValue\"~\n\nDataSource\u0012\u001b\n\bfilename\u0018\u0001 \u0001(\tB\u0007úB\u0004r\u0002 \u0001H\u0000\u0012\u001f\n\finline_bytes\u0018\u0002 \u0001(\fB\u0007úB\u0004z\u0002\u0010\u0001H\u0000\u0012 \n\rinline_string\u0018\u0003 \u0001(\tB\u0007úB\u0004r\u0002 \u0001H\u0000B\u0010\n\tspecifier\u0012\u0003øB\u0001\"|\n\u000bRetryPolicy\u0012:\n\u000eretry_back_off\u0018\u0001 \u0001(\u000b2\".envoy.api.v2.core.BackoffStrategy\u00121\n\u000bnum_retries\u0018\u0002 \u0001(\u000b2\u001c.google.protobuf.UInt32Value\"\u0099\u0001\n\u0010RemoteDataSource\u00126\n\bhttp_uri\u0018\u0001 \u0001(\u000b2\u001a.envoy.api.v2.core.HttpUriB\búB\u0005\u008a\u0001\u0002\u0010\u0001\u0012\u0017\n\u0006sha256\u0018\u0002 \u0001(\tB\u0007úB\u0004r\u0002 \u0001\u00124\n\fretry_policy\u0018\u0003 \u0001(\u000b2\u001e.envoy.api.v2.core.RetryPolicy\"\u008a\u0001\n\u000fAsyncDataSource\u0012.\n\u0005local\u0018\u0001 \u0001(\u000b2\u001d.envoy.api.v2.core.DataSourceH\u0000\u00125\n\u0006remote\u0018\u0002 \u0001(\u000b2#.envoy.api.v2.core.RemoteDataSourceH\u0000B\u0010\n\tspecifier\u0012\u0003øB\u0001\"\u0094\u0001\n\u000fTransportSocket\u0012\u0015\n\u0004name\u0018\u0001 \u0001(\tB\u0007úB\u0004r\u0002 \u0001\u0012-\n\u0006config\u0018\u0002 \u0001(\u000b2\u0017.google.protobuf.StructB\u0002\u0018\u0001H\u0000\u0012,\n\ftyped_config\u0018\u0003 \u0001(\u000b2\u0014.google.protobuf.AnyH\u0000B\r\n\u000bconfig_type\"o\n\u0018RuntimeFractionalPercent\u0012>\n\rdefault_value\u0018\u0001 \u0001(\u000b2\u001d.envoy.type.FractionalPercentB\búB\u0005\u008a\u0001\u0002\u0010\u0001\u0012\u0013\n\u000bruntime_key\u0018\u0002 \u0001(\t\"\"\n\fControlPlane\u0012\u0012\n\nidentifier\u0018\u0001 \u0001(\t*(\n\u000fRoutingPriority\u0012\u000b\n\u0007DEFAULT\u0010\u0000\u0012\b\n\u0004HIGH\u0010\u0001*\u0089\u0001\n\rRequestMethod\u0012\u0016\n\u0012METHOD_UNSPECIFIED\u0010\u0000\u0012\u0007\n\u0003GET\u0010\u0001\u0012\b\n\u0004HEAD\u0010\u0002\u0012\b\n\u0004POST\u0010\u0003\u0012\u0007\n\u0003PUT\u0010\u0004\u0012\n\n\u0006DELETE\u0010\u0005\u0012\u000b\n\u0007CONNECT\u0010\u0006\u0012\u000b\n\u0007OPTIONS\u0010\u0007\u0012\t\n\u0005TRACE\u0010\b\u0012\t\n\u0005PATCH\u0010\t*>\n\u0010TrafficDirection\u0012\u000f\n\u000bUNSPECIFIED\u0010\u0000\u0012\u000b\n\u0007INBOUND\u0010\u0001\u0012\f\n\bOUTBOUND\u0010\u0002BR\n\u001fio.envoyproxy.envoy.api.v2.coreB\tBaseProtoP\u0001ò\u0098þ\u008f\u0005\u0016\u0012\u0014envoy.config.core.v3º\u0080ÈÑ\u0006\u0002\u0010\u0001P\fb\u0006proto3"}, new Descriptors.FileDescriptor[]{AddressProto.getDescriptor(), BackoffProto.getDescriptor(), HttpUriProto.getDescriptor(), PercentProto.getDescriptor(), SemanticVersionProto.getDescriptor(), AnyProto.getDescriptor(), DurationProto.getDescriptor(), StructProto.getDescriptor(), WrappersProto.getDescriptor(), Migrate.getDescriptor(), Status.getDescriptor(), Validate.getDescriptor(), SocketOptionProto.getDescriptor()});

    static {
        Descriptors.Descriptor descriptor2 = (Descriptors.Descriptor) getDescriptor().getMessageTypes().get(0);
        internal_static_envoy_api_v2_core_Locality_descriptor = descriptor2;
        internal_static_envoy_api_v2_core_Locality_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor2, new String[]{"Region", "Zone", "SubZone"});
        Descriptors.Descriptor descriptor3 = (Descriptors.Descriptor) getDescriptor().getMessageTypes().get(1);
        internal_static_envoy_api_v2_core_BuildVersion_descriptor = descriptor3;
        internal_static_envoy_api_v2_core_BuildVersion_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor3, new String[]{"Version", "Metadata"});
        Descriptors.Descriptor descriptor4 = (Descriptors.Descriptor) getDescriptor().getMessageTypes().get(2);
        internal_static_envoy_api_v2_core_Extension_descriptor = descriptor4;
        internal_static_envoy_api_v2_core_Extension_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor4, new String[]{"Name", "Category", "TypeDescriptor", "Version", "Disabled"});
        Descriptors.Descriptor descriptor5 = (Descriptors.Descriptor) getDescriptor().getMessageTypes().get(3);
        internal_static_envoy_api_v2_core_Node_descriptor = descriptor5;
        internal_static_envoy_api_v2_core_Node_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor5, new String[]{"Id", "Cluster", "Metadata", "Locality", "BuildVersion", "UserAgentName", "UserAgentVersion", "UserAgentBuildVersion", "Extensions", "ClientFeatures", "ListeningAddresses", "UserAgentVersionType"});
        Descriptors.Descriptor descriptor6 = (Descriptors.Descriptor) getDescriptor().getMessageTypes().get(4);
        internal_static_envoy_api_v2_core_Metadata_descriptor = descriptor6;
        internal_static_envoy_api_v2_core_Metadata_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor6, new String[]{"FilterMetadata"});
        Descriptors.Descriptor descriptor7 = (Descriptors.Descriptor) descriptor6.getNestedTypes().get(0);
        internal_static_envoy_api_v2_core_Metadata_FilterMetadataEntry_descriptor = descriptor7;
        internal_static_envoy_api_v2_core_Metadata_FilterMetadataEntry_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor7, new String[]{"Key", "Value"});
        Descriptors.Descriptor descriptor8 = (Descriptors.Descriptor) getDescriptor().getMessageTypes().get(5);
        internal_static_envoy_api_v2_core_RuntimeUInt32_descriptor = descriptor8;
        internal_static_envoy_api_v2_core_RuntimeUInt32_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor8, new String[]{"DefaultValue", "RuntimeKey"});
        Descriptors.Descriptor descriptor9 = (Descriptors.Descriptor) getDescriptor().getMessageTypes().get(6);
        internal_static_envoy_api_v2_core_RuntimeDouble_descriptor = descriptor9;
        internal_static_envoy_api_v2_core_RuntimeDouble_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor9, new String[]{"DefaultValue", "RuntimeKey"});
        Descriptors.Descriptor descriptor10 = (Descriptors.Descriptor) getDescriptor().getMessageTypes().get(7);
        internal_static_envoy_api_v2_core_RuntimeFeatureFlag_descriptor = descriptor10;
        internal_static_envoy_api_v2_core_RuntimeFeatureFlag_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor10, new String[]{"DefaultValue", "RuntimeKey"});
        Descriptors.Descriptor descriptor11 = (Descriptors.Descriptor) getDescriptor().getMessageTypes().get(8);
        internal_static_envoy_api_v2_core_HeaderValue_descriptor = descriptor11;
        internal_static_envoy_api_v2_core_HeaderValue_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor11, new String[]{"Key", "Value"});
        Descriptors.Descriptor descriptor12 = (Descriptors.Descriptor) getDescriptor().getMessageTypes().get(9);
        internal_static_envoy_api_v2_core_HeaderValueOption_descriptor = descriptor12;
        internal_static_envoy_api_v2_core_HeaderValueOption_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor12, new String[]{"Header", "Append"});
        Descriptors.Descriptor descriptor13 = (Descriptors.Descriptor) getDescriptor().getMessageTypes().get(10);
        internal_static_envoy_api_v2_core_HeaderMap_descriptor = descriptor13;
        internal_static_envoy_api_v2_core_HeaderMap_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor13, new String[]{"Headers"});
        Descriptors.Descriptor descriptor14 = (Descriptors.Descriptor) getDescriptor().getMessageTypes().get(11);
        internal_static_envoy_api_v2_core_DataSource_descriptor = descriptor14;
        internal_static_envoy_api_v2_core_DataSource_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor14, new String[]{"Filename", "InlineBytes", "InlineString", "Specifier"});
        Descriptors.Descriptor descriptor15 = (Descriptors.Descriptor) getDescriptor().getMessageTypes().get(12);
        internal_static_envoy_api_v2_core_RetryPolicy_descriptor = descriptor15;
        internal_static_envoy_api_v2_core_RetryPolicy_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor15, new String[]{"RetryBackOff", "NumRetries"});
        Descriptors.Descriptor descriptor16 = (Descriptors.Descriptor) getDescriptor().getMessageTypes().get(13);
        internal_static_envoy_api_v2_core_RemoteDataSource_descriptor = descriptor16;
        internal_static_envoy_api_v2_core_RemoteDataSource_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor16, new String[]{"HttpUri", "Sha256", "RetryPolicy"});
        Descriptors.Descriptor descriptor17 = (Descriptors.Descriptor) getDescriptor().getMessageTypes().get(14);
        internal_static_envoy_api_v2_core_AsyncDataSource_descriptor = descriptor17;
        internal_static_envoy_api_v2_core_AsyncDataSource_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor17, new String[]{"Local", "Remote", "Specifier"});
        Descriptors.Descriptor descriptor18 = (Descriptors.Descriptor) getDescriptor().getMessageTypes().get(15);
        internal_static_envoy_api_v2_core_TransportSocket_descriptor = descriptor18;
        internal_static_envoy_api_v2_core_TransportSocket_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor18, new String[]{"Name", "Config", "TypedConfig", "ConfigType"});
        Descriptors.Descriptor descriptor19 = (Descriptors.Descriptor) getDescriptor().getMessageTypes().get(16);
        internal_static_envoy_api_v2_core_RuntimeFractionalPercent_descriptor = descriptor19;
        internal_static_envoy_api_v2_core_RuntimeFractionalPercent_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor19, new String[]{"DefaultValue", "RuntimeKey"});
        Descriptors.Descriptor descriptor20 = (Descriptors.Descriptor) getDescriptor().getMessageTypes().get(17);
        internal_static_envoy_api_v2_core_ControlPlane_descriptor = descriptor20;
        internal_static_envoy_api_v2_core_ControlPlane_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor20, new String[]{"Identifier"});
        ExtensionRegistry extensionRegistryNewInstance = ExtensionRegistry.newInstance();
        extensionRegistryNewInstance.add(Migrate.fileMigrate);
        extensionRegistryNewInstance.add(Status.fileStatus);
        extensionRegistryNewInstance.add(Validate.required);
        extensionRegistryNewInstance.add(Validate.rules);
        Descriptors.FileDescriptor.internalUpdateFileDescriptor(descriptor, extensionRegistryNewInstance);
        AddressProto.getDescriptor();
        BackoffProto.getDescriptor();
        HttpUriProto.getDescriptor();
        PercentProto.getDescriptor();
        SemanticVersionProto.getDescriptor();
        AnyProto.getDescriptor();
        DurationProto.getDescriptor();
        StructProto.getDescriptor();
        WrappersProto.getDescriptor();
        Migrate.getDescriptor();
        Status.getDescriptor();
        Validate.getDescriptor();
        SocketOptionProto.getDescriptor();
    }

    private BaseProto() {
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
