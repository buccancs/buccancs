package io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3;

import com.google.protobuf.AnyProto;
import com.google.protobuf.Descriptors;
import com.google.protobuf.DurationProto;
import com.google.protobuf.ExtensionRegistry;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageV3;
import com.google.protobuf.StructProto;
import com.google.protobuf.WrappersProto;
import io.grpc.xds.shaded.io.envoyproxy.envoy.type.v3.PercentProto;
import io.grpc.xds.shaded.io.envoyproxy.envoy.type.v3.SemanticVersionProto;
import io.grpc.xds.shaded.io.envoyproxy.pgv.validate.Validate;
import udpa.annotations.Migrate;
import udpa.annotations.Status;
import udpa.annotations.Versioning;

/* loaded from: classes6.dex */
public final class BaseProto {
    static final Descriptors.Descriptor internal_static_envoy_config_core_v3_AsyncDataSource_descriptor;
    static final GeneratedMessageV3.FieldAccessorTable internal_static_envoy_config_core_v3_AsyncDataSource_fieldAccessorTable;
    static final Descriptors.Descriptor internal_static_envoy_config_core_v3_BuildVersion_descriptor;
    static final GeneratedMessageV3.FieldAccessorTable internal_static_envoy_config_core_v3_BuildVersion_fieldAccessorTable;
    static final Descriptors.Descriptor internal_static_envoy_config_core_v3_ControlPlane_descriptor;
    static final GeneratedMessageV3.FieldAccessorTable internal_static_envoy_config_core_v3_ControlPlane_fieldAccessorTable;
    static final Descriptors.Descriptor internal_static_envoy_config_core_v3_DataSource_descriptor;
    static final GeneratedMessageV3.FieldAccessorTable internal_static_envoy_config_core_v3_DataSource_fieldAccessorTable;
    static final Descriptors.Descriptor internal_static_envoy_config_core_v3_Extension_descriptor;
    static final GeneratedMessageV3.FieldAccessorTable internal_static_envoy_config_core_v3_Extension_fieldAccessorTable;
    static final Descriptors.Descriptor internal_static_envoy_config_core_v3_HeaderMap_descriptor;
    static final GeneratedMessageV3.FieldAccessorTable internal_static_envoy_config_core_v3_HeaderMap_fieldAccessorTable;
    static final Descriptors.Descriptor internal_static_envoy_config_core_v3_HeaderValueOption_descriptor;
    static final GeneratedMessageV3.FieldAccessorTable internal_static_envoy_config_core_v3_HeaderValueOption_fieldAccessorTable;
    static final Descriptors.Descriptor internal_static_envoy_config_core_v3_HeaderValue_descriptor;
    static final GeneratedMessageV3.FieldAccessorTable internal_static_envoy_config_core_v3_HeaderValue_fieldAccessorTable;
    static final Descriptors.Descriptor internal_static_envoy_config_core_v3_Locality_descriptor;
    static final GeneratedMessageV3.FieldAccessorTable internal_static_envoy_config_core_v3_Locality_fieldAccessorTable;
    static final Descriptors.Descriptor internal_static_envoy_config_core_v3_Metadata_FilterMetadataEntry_descriptor;
    static final GeneratedMessageV3.FieldAccessorTable internal_static_envoy_config_core_v3_Metadata_FilterMetadataEntry_fieldAccessorTable;
    static final Descriptors.Descriptor internal_static_envoy_config_core_v3_Metadata_descriptor;
    static final GeneratedMessageV3.FieldAccessorTable internal_static_envoy_config_core_v3_Metadata_fieldAccessorTable;
    static final Descriptors.Descriptor internal_static_envoy_config_core_v3_Node_descriptor;
    static final GeneratedMessageV3.FieldAccessorTable internal_static_envoy_config_core_v3_Node_fieldAccessorTable;
    static final Descriptors.Descriptor internal_static_envoy_config_core_v3_RemoteDataSource_descriptor;
    static final GeneratedMessageV3.FieldAccessorTable internal_static_envoy_config_core_v3_RemoteDataSource_fieldAccessorTable;
    static final Descriptors.Descriptor internal_static_envoy_config_core_v3_RetryPolicy_descriptor;
    static final GeneratedMessageV3.FieldAccessorTable internal_static_envoy_config_core_v3_RetryPolicy_fieldAccessorTable;
    static final Descriptors.Descriptor internal_static_envoy_config_core_v3_RuntimeDouble_descriptor;
    static final GeneratedMessageV3.FieldAccessorTable internal_static_envoy_config_core_v3_RuntimeDouble_fieldAccessorTable;
    static final Descriptors.Descriptor internal_static_envoy_config_core_v3_RuntimeFeatureFlag_descriptor;
    static final GeneratedMessageV3.FieldAccessorTable internal_static_envoy_config_core_v3_RuntimeFeatureFlag_fieldAccessorTable;
    static final Descriptors.Descriptor internal_static_envoy_config_core_v3_RuntimeFractionalPercent_descriptor;
    static final GeneratedMessageV3.FieldAccessorTable internal_static_envoy_config_core_v3_RuntimeFractionalPercent_fieldAccessorTable;
    static final Descriptors.Descriptor internal_static_envoy_config_core_v3_RuntimeUInt32_descriptor;
    static final GeneratedMessageV3.FieldAccessorTable internal_static_envoy_config_core_v3_RuntimeUInt32_fieldAccessorTable;
    static final Descriptors.Descriptor internal_static_envoy_config_core_v3_TransportSocket_descriptor;
    static final GeneratedMessageV3.FieldAccessorTable internal_static_envoy_config_core_v3_TransportSocket_fieldAccessorTable;
    private static Descriptors.FileDescriptor descriptor = Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(new String[]{"\n\u001fenvoy/config/core/v3/base.proto\u0012\u0014envoy.config.core.v3\u001a\"envoy/config/core/v3/address.proto\u001a\"envoy/config/core/v3/backoff.proto\u001a#envoy/config/core/v3/http_uri.proto\u001a\u001benvoy/type/v3/percent.proto\u001a$envoy/type/v3/semantic_version.proto\u001a\u0019google/protobuf/any.proto\u001a\u001egoogle/protobuf/duration.proto\u001a\u001cgoogle/protobuf/struct.proto\u001a\u001egoogle/protobuf/wrappers.proto\u001a\u001eudpa/annotations/migrate.proto\u001a\u001dudpa/annotations/status.proto\u001a!udpa/annotations/versioning.proto\u001a\u0017validate/validate.proto\"]\n\bLocality\u0012\u000e\n\u0006region\u0018\u0001 \u0001(\t\u0012\f\n\u0004zone\u0018\u0002 \u0001(\t\u0012\u0010\n\bsub_zone\u0018\u0003 \u0001(\t:!\u009aÅ\u0088\u001e\u001c\n\u001aenvoy.api.v2.core.Locality\"\u0091\u0001\n\fBuildVersion\u0012/\n\u0007version\u0018\u0001 \u0001(\u000b2\u001e.envoy.type.v3.SemanticVersion\u0012)\n\bmetadata\u0018\u0002 \u0001(\u000b2\u0017.google.protobuf.Struct:%\u009aÅ\u0088\u001e \n\u001eenvoy.api.v2.core.BuildVersion\"¯\u0001\n\tExtension\u0012\f\n\u0004name\u0018\u0001 \u0001(\t\u0012\u0010\n\bcategory\u0018\u0002 \u0001(\t\u0012\u0017\n\u000ftype_descriptor\u0018\u0003 \u0001(\t\u00123\n\u0007version\u0018\u0004 \u0001(\u000b2\".envoy.config.core.v3.BuildVersion\u0012\u0010\n\bdisabled\u0018\u0005 \u0001(\b:\"\u009aÅ\u0088\u001e\u001d\n\u001benvoy.api.v2.core.Extension\"Ø\u0003\n\u0004Node\u0012\n\n\u0002id\u0018\u0001 \u0001(\t\u0012\u000f\n\u0007cluster\u0018\u0002 \u0001(\t\u0012)\n\bmetadata\u0018\u0003 \u0001(\u000b2\u0017.google.protobuf.Struct\u00120\n\blocality\u0018\u0004 \u0001(\u000b2\u001e.envoy.config.core.v3.Locality\u0012\u0017\n\u000fuser_agent_name\u0018\u0006 \u0001(\t\u0012\u001c\n\u0012user_agent_version\u0018\u0007 \u0001(\tH\u0000\u0012F\n\u0018user_agent_build_version\u0018\b \u0001(\u000b2\".envoy.config.core.v3.BuildVersionH\u0000\u00123\n\nextensions\u0018\t \u0003(\u000b2\u001f.envoy.config.core.v3.Extension\u0012\u0017\n\u000fclient_features\u0018\n \u0003(\t\u0012:\n\u0013listening_addresses\u0018\u000b \u0003(\u000b2\u001d.envoy.config.core.v3.Address:\u001d\u009aÅ\u0088\u001e\u0018\n\u0016envoy.api.v2.core.NodeB\u0019\n\u0017user_agent_version_typeJ\u0004\b\u0005\u0010\u0006R\rbuild_version\"Ê\u0001\n\bMetadata\u0012K\n\u000ffilter_metadata\u0018\u0001 \u0003(\u000b22.envoy.config.core.v3.Metadata.FilterMetadataEntry\u001aN\n\u0013FilterMetadataEntry\u0012\u000b\n\u0003key\u0018\u0001 \u0001(\t\u0012&\n\u0005value\u0018\u0002 \u0001(\u000b2\u0017.google.protobuf.Struct:\u00028\u0001:!\u009aÅ\u0088\u001e\u001c\n\u001aenvoy.api.v2.core.Metadata\"l\n\rRuntimeUInt32\u0012\u0015\n\rdefault_value\u0018\u0002 \u0001(\r\u0012\u001c\n\u000bruntime_key\u0018\u0003 \u0001(\tB\u0007úB\u0004r\u0002 \u0001:&\u009aÅ\u0088\u001e!\n\u001fenvoy.api.v2.core.RuntimeUInt32\"l\n\rRuntimeDouble\u0012\u0015\n\rdefault_value\u0018\u0001 \u0001(\u0001\u0012\u001c\n\u000bruntime_key\u0018\u0002 \u0001(\tB\u0007úB\u0004r\u0002 \u0001:&\u009aÅ\u0088\u001e!\n\u001fenvoy.api.v2.core.RuntimeDouble\"\u009c\u0001\n\u0012RuntimeFeatureFlag\u0012;\n\rdefault_value\u0018\u0001 \u0001(\u000b2\u001a.google.protobuf.BoolValueB\búB\u0005\u008a\u0001\u0002\u0010\u0001\u0012\u001c\n\u000bruntime_key\u0018\u0002 \u0001(\tB\u0007úB\u0004r\u0002 \u0001:+\u009aÅ\u0088\u001e&\n$envoy.api.v2.core.RuntimeFeatureFlag\"s\n\u000bHeaderValue\u0012\u001e\n\u0003key\u0018\u0001 \u0001(\tB\u0011úB\u000er\f \u0001(\u0080\u0080\u0001À\u0001\u0001È\u0001\u0000\u0012\u001e\n\u0005value\u0018\u0002 \u0001(\tB\u000fúB\fr\n(\u0080\u0080\u0001À\u0001\u0002È\u0001\u0000:$\u009aÅ\u0088\u001e\u001f\n\u001denvoy.api.v2.core.HeaderValue\"¨\u0001\n\u0011HeaderValueOption\u0012;\n\u0006header\u0018\u0001 \u0001(\u000b2!.envoy.config.core.v3.HeaderValueB\búB\u0005\u008a\u0001\u0002\u0010\u0001\u0012*\n\u0006append\u0018\u0002 \u0001(\u000b2\u001a.google.protobuf.BoolValue:*\u009aÅ\u0088\u001e%\n#envoy.api.v2.core.HeaderValueOption\"c\n\tHeaderMap\u00122\n\u0007headers\u0018\u0001 \u0003(\u000b2!.envoy.config.core.v3.HeaderValue:\"\u009aÅ\u0088\u001e\u001d\n\u001benvoy.api.v2.core.HeaderMap\"£\u0001\n\nDataSource\u0012\u001b\n\bfilename\u0018\u0001 \u0001(\tB\u0007úB\u0004r\u0002 \u0001H\u0000\u0012\u001f\n\finline_bytes\u0018\u0002 \u0001(\fB\u0007úB\u0004z\u0002\u0010\u0001H\u0000\u0012 \n\rinline_string\u0018\u0003 \u0001(\tB\u0007úB\u0004r\u0002 \u0001H\u0000:#\u009aÅ\u0088\u001e\u001e\n\u001cenvoy.api.v2.core.DataSourceB\u0010\n\tspecifier\u0012\u0003øB\u0001\"º\u0001\n\u000bRetryPolicy\u0012=\n\u000eretry_back_off\u0018\u0001 \u0001(\u000b2%.envoy.config.core.v3.BackoffStrategy\u0012F\n\u000bnum_retries\u0018\u0002 \u0001(\u000b2\u001c.google.protobuf.UInt32ValueB\u0013ò\u0098þ\u008f\u0005\r\n\u000bmax_retries:$\u009aÅ\u0088\u001e\u001f\n\u001denvoy.api.v2.core.RetryPolicy\"Ê\u0001\n\u0010RemoteDataSource\u00129\n\bhttp_uri\u0018\u0001 \u0001(\u000b2\u001d.envoy.config.core.v3.HttpUriB\búB\u0005\u008a\u0001\u0002\u0010\u0001\u0012\u0017\n\u0006sha256\u0018\u0002 \u0001(\tB\u0007úB\u0004r\u0002 \u0001\u00127\n\fretry_policy\u0018\u0003 \u0001(\u000b2!.envoy.config.core.v3.RetryPolicy:)\u009aÅ\u0088\u001e$\n\"envoy.api.v2.core.RemoteDataSource\"º\u0001\n\u000fAsyncDataSource\u00121\n\u0005local\u0018\u0001 \u0001(\u000b2 .envoy.config.core.v3.DataSourceH\u0000\u00128\n\u0006remote\u0018\u0002 \u0001(\u000b2&.envoy.config.core.v3.RemoteDataSourceH\u0000:(\u009aÅ\u0088\u001e#\n!envoy.api.v2.core.AsyncDataSourceB\u0010\n\tspecifier\u0012\u0003øB\u0001\"\u009d\u0001\n\u000fTransportSocket\u0012\u0015\n\u0004name\u0018\u0001 \u0001(\tB\u0007úB\u0004r\u0002 \u0001\u0012,\n\ftyped_config\u0018\u0003 \u0001(\u000b2\u0014.google.protobuf.AnyH\u0000:(\u009aÅ\u0088\u001e#\n!envoy.api.v2.core.TransportSocketB\r\n\u000bconfig_typeJ\u0004\b\u0002\u0010\u0003R\u0006config\"¥\u0001\n\u0018RuntimeFractionalPercent\u0012A\n\rdefault_value\u0018\u0001 \u0001(\u000b2 .envoy.type.v3.FractionalPercentB\búB\u0005\u008a\u0001\u0002\u0010\u0001\u0012\u0013\n\u000bruntime_key\u0018\u0002 \u0001(\t:1\u009aÅ\u0088\u001e,\n*envoy.api.v2.core.RuntimeFractionalPercent\"I\n\fControlPlane\u0012\u0012\n\nidentifier\u0018\u0001 \u0001(\t:%\u009aÅ\u0088\u001e \n\u001eenvoy.api.v2.core.ControlPlane*(\n\u000fRoutingPriority\u0012\u000b\n\u0007DEFAULT\u0010\u0000\u0012\b\n\u0004HIGH\u0010\u0001*\u0089\u0001\n\rRequestMethod\u0012\u0016\n\u0012METHOD_UNSPECIFIED\u0010\u0000\u0012\u0007\n\u0003GET\u0010\u0001\u0012\b\n\u0004HEAD\u0010\u0002\u0012\b\n\u0004POST\u0010\u0003\u0012\u0007\n\u0003PUT\u0010\u0004\u0012\n\n\u0006DELETE\u0010\u0005\u0012\u000b\n\u0007CONNECT\u0010\u0006\u0012\u000b\n\u0007OPTIONS\u0010\u0007\u0012\t\n\u0005TRACE\u0010\b\u0012\t\n\u0005PATCH\u0010\t*>\n\u0010TrafficDirection\u0012\u000f\n\u000bUNSPECIFIED\u0010\u0000\u0012\u000b\n\u0007INBOUND\u0010\u0001\u0012\f\n\bOUTBOUND\u0010\u0002B9\n\"io.envoyproxy.envoy.config.core.v3B\tBaseProtoP\u0001º\u0080ÈÑ\u0006\u0002\u0010\u0002b\u0006proto3"}, new Descriptors.FileDescriptor[]{AddressProto.getDescriptor(), BackoffProto.getDescriptor(), HttpUriProto.getDescriptor(), PercentProto.getDescriptor(), SemanticVersionProto.getDescriptor(), AnyProto.getDescriptor(), DurationProto.getDescriptor(), StructProto.getDescriptor(), WrappersProto.getDescriptor(), Migrate.getDescriptor(), Status.getDescriptor(), Versioning.getDescriptor(), Validate.getDescriptor()});

    static {
        Descriptors.Descriptor descriptor2 = (Descriptors.Descriptor) getDescriptor().getMessageTypes().get(0);
        internal_static_envoy_config_core_v3_Locality_descriptor = descriptor2;
        internal_static_envoy_config_core_v3_Locality_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor2, new String[]{"Region", "Zone", "SubZone"});
        Descriptors.Descriptor descriptor3 = (Descriptors.Descriptor) getDescriptor().getMessageTypes().get(1);
        internal_static_envoy_config_core_v3_BuildVersion_descriptor = descriptor3;
        internal_static_envoy_config_core_v3_BuildVersion_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor3, new String[]{"Version", "Metadata"});
        Descriptors.Descriptor descriptor4 = (Descriptors.Descriptor) getDescriptor().getMessageTypes().get(2);
        internal_static_envoy_config_core_v3_Extension_descriptor = descriptor4;
        internal_static_envoy_config_core_v3_Extension_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor4, new String[]{"Name", "Category", "TypeDescriptor", "Version", "Disabled"});
        Descriptors.Descriptor descriptor5 = (Descriptors.Descriptor) getDescriptor().getMessageTypes().get(3);
        internal_static_envoy_config_core_v3_Node_descriptor = descriptor5;
        internal_static_envoy_config_core_v3_Node_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor5, new String[]{"Id", "Cluster", "Metadata", "Locality", "UserAgentName", "UserAgentVersion", "UserAgentBuildVersion", "Extensions", "ClientFeatures", "ListeningAddresses", "UserAgentVersionType"});
        Descriptors.Descriptor descriptor6 = (Descriptors.Descriptor) getDescriptor().getMessageTypes().get(4);
        internal_static_envoy_config_core_v3_Metadata_descriptor = descriptor6;
        internal_static_envoy_config_core_v3_Metadata_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor6, new String[]{"FilterMetadata"});
        Descriptors.Descriptor descriptor7 = (Descriptors.Descriptor) descriptor6.getNestedTypes().get(0);
        internal_static_envoy_config_core_v3_Metadata_FilterMetadataEntry_descriptor = descriptor7;
        internal_static_envoy_config_core_v3_Metadata_FilterMetadataEntry_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor7, new String[]{"Key", "Value"});
        Descriptors.Descriptor descriptor8 = (Descriptors.Descriptor) getDescriptor().getMessageTypes().get(5);
        internal_static_envoy_config_core_v3_RuntimeUInt32_descriptor = descriptor8;
        internal_static_envoy_config_core_v3_RuntimeUInt32_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor8, new String[]{"DefaultValue", "RuntimeKey"});
        Descriptors.Descriptor descriptor9 = (Descriptors.Descriptor) getDescriptor().getMessageTypes().get(6);
        internal_static_envoy_config_core_v3_RuntimeDouble_descriptor = descriptor9;
        internal_static_envoy_config_core_v3_RuntimeDouble_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor9, new String[]{"DefaultValue", "RuntimeKey"});
        Descriptors.Descriptor descriptor10 = (Descriptors.Descriptor) getDescriptor().getMessageTypes().get(7);
        internal_static_envoy_config_core_v3_RuntimeFeatureFlag_descriptor = descriptor10;
        internal_static_envoy_config_core_v3_RuntimeFeatureFlag_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor10, new String[]{"DefaultValue", "RuntimeKey"});
        Descriptors.Descriptor descriptor11 = (Descriptors.Descriptor) getDescriptor().getMessageTypes().get(8);
        internal_static_envoy_config_core_v3_HeaderValue_descriptor = descriptor11;
        internal_static_envoy_config_core_v3_HeaderValue_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor11, new String[]{"Key", "Value"});
        Descriptors.Descriptor descriptor12 = (Descriptors.Descriptor) getDescriptor().getMessageTypes().get(9);
        internal_static_envoy_config_core_v3_HeaderValueOption_descriptor = descriptor12;
        internal_static_envoy_config_core_v3_HeaderValueOption_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor12, new String[]{"Header", "Append"});
        Descriptors.Descriptor descriptor13 = (Descriptors.Descriptor) getDescriptor().getMessageTypes().get(10);
        internal_static_envoy_config_core_v3_HeaderMap_descriptor = descriptor13;
        internal_static_envoy_config_core_v3_HeaderMap_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor13, new String[]{"Headers"});
        Descriptors.Descriptor descriptor14 = (Descriptors.Descriptor) getDescriptor().getMessageTypes().get(11);
        internal_static_envoy_config_core_v3_DataSource_descriptor = descriptor14;
        internal_static_envoy_config_core_v3_DataSource_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor14, new String[]{"Filename", "InlineBytes", "InlineString", "Specifier"});
        Descriptors.Descriptor descriptor15 = (Descriptors.Descriptor) getDescriptor().getMessageTypes().get(12);
        internal_static_envoy_config_core_v3_RetryPolicy_descriptor = descriptor15;
        internal_static_envoy_config_core_v3_RetryPolicy_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor15, new String[]{"RetryBackOff", "NumRetries"});
        Descriptors.Descriptor descriptor16 = (Descriptors.Descriptor) getDescriptor().getMessageTypes().get(13);
        internal_static_envoy_config_core_v3_RemoteDataSource_descriptor = descriptor16;
        internal_static_envoy_config_core_v3_RemoteDataSource_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor16, new String[]{"HttpUri", "Sha256", "RetryPolicy"});
        Descriptors.Descriptor descriptor17 = (Descriptors.Descriptor) getDescriptor().getMessageTypes().get(14);
        internal_static_envoy_config_core_v3_AsyncDataSource_descriptor = descriptor17;
        internal_static_envoy_config_core_v3_AsyncDataSource_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor17, new String[]{"Local", "Remote", "Specifier"});
        Descriptors.Descriptor descriptor18 = (Descriptors.Descriptor) getDescriptor().getMessageTypes().get(15);
        internal_static_envoy_config_core_v3_TransportSocket_descriptor = descriptor18;
        internal_static_envoy_config_core_v3_TransportSocket_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor18, new String[]{"Name", "TypedConfig", "ConfigType"});
        Descriptors.Descriptor descriptor19 = (Descriptors.Descriptor) getDescriptor().getMessageTypes().get(16);
        internal_static_envoy_config_core_v3_RuntimeFractionalPercent_descriptor = descriptor19;
        internal_static_envoy_config_core_v3_RuntimeFractionalPercent_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor19, new String[]{"DefaultValue", "RuntimeKey"});
        Descriptors.Descriptor descriptor20 = (Descriptors.Descriptor) getDescriptor().getMessageTypes().get(17);
        internal_static_envoy_config_core_v3_ControlPlane_descriptor = descriptor20;
        internal_static_envoy_config_core_v3_ControlPlane_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor20, new String[]{"Identifier"});
        ExtensionRegistry extensionRegistryNewInstance = ExtensionRegistry.newInstance();
        extensionRegistryNewInstance.add(Migrate.fieldMigrate);
        extensionRegistryNewInstance.add(Status.fileStatus);
        extensionRegistryNewInstance.add(Versioning.versioning);
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
        Versioning.getDescriptor();
        Validate.getDescriptor();
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
