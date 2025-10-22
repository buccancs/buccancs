package io.grpc.xds.shaded.com.github.udpa.udpa.data.orca.v1;

import com.google.protobuf.Descriptors;
import com.google.protobuf.ExtensionRegistry;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageV3;
import io.grpc.xds.shaded.io.envoyproxy.pgv.validate.Validate;

/* loaded from: classes3.dex */
public final class OrcaLoadReportProto {
    static final Descriptors.Descriptor internal_static_udpa_data_orca_v1_OrcaLoadReport_RequestCostEntry_descriptor;
    static final GeneratedMessageV3.FieldAccessorTable internal_static_udpa_data_orca_v1_OrcaLoadReport_RequestCostEntry_fieldAccessorTable;
    static final Descriptors.Descriptor internal_static_udpa_data_orca_v1_OrcaLoadReport_UtilizationEntry_descriptor;
    static final GeneratedMessageV3.FieldAccessorTable internal_static_udpa_data_orca_v1_OrcaLoadReport_UtilizationEntry_fieldAccessorTable;
    static final Descriptors.Descriptor internal_static_udpa_data_orca_v1_OrcaLoadReport_descriptor;
    static final GeneratedMessageV3.FieldAccessorTable internal_static_udpa_data_orca_v1_OrcaLoadReport_fieldAccessorTable;
    private static Descriptors.FileDescriptor descriptor = Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(new String[]{"\n(udpa/data/orca/v1/orca_load_report.proto\u0012\u0011udpa.data.orca.v1\u001a\u0017validate/validate.proto\"®\u0003\n\u000eOrcaLoadReport\u00125\n\u000fcpu_utilization\u0018\u0001 \u0001(\u0001B\u001cúB\u000b\u0012\t)\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000úB\u000b\u0012\t\u0019\u0000\u0000\u0000\u0000\u0000\u0000ð?\u00125\n\u000fmem_utilization\u0018\u0002 \u0001(\u0001B\u001cúB\u000b\u0012\t)\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000úB\u000b\u0012\t\u0019\u0000\u0000\u0000\u0000\u0000\u0000ð?\u0012\u000b\n\u0003rps\u0018\u0003 \u0001(\u0004\u0012H\n\frequest_cost\u0018\u0004 \u0003(\u000b22.udpa.data.orca.v1.OrcaLoadReport.RequestCostEntry\u0012o\n\u000butilization\u0018\u0005 \u0003(\u000b22.udpa.data.orca.v1.OrcaLoadReport.UtilizationEntryB&úB\u0010\u009a\u0001\r*\u000b\u0012\t)\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000úB\u0010\u009a\u0001\r*\u000b\u0012\t\u0019\u0000\u0000\u0000\u0000\u0000\u0000ð?\u001a2\n\u0010RequestCostEntry\u0012\u000b\n\u0003key\u0018\u0001 \u0001(\t\u0012\r\n\u0005value\u0018\u0002 \u0001(\u0001:\u00028\u0001\u001a2\n\u0010UtilizationEntry\u0012\u000b\n\u0003key\u0018\u0001 \u0001(\t\u0012\r\n\u0005value\u0018\u0002 \u0001(\u0001:\u00028\u0001B:\n!com.github.udpa.udpa.data.orca.v1B\u0013OrcaLoadReportProtoP\u0001b\u0006proto3"}, new Descriptors.FileDescriptor[]{Validate.getDescriptor()});

    static {
        Descriptors.Descriptor descriptor2 = (Descriptors.Descriptor) getDescriptor().getMessageTypes().get(0);
        internal_static_udpa_data_orca_v1_OrcaLoadReport_descriptor = descriptor2;
        internal_static_udpa_data_orca_v1_OrcaLoadReport_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor2, new String[]{"CpuUtilization", "MemUtilization", "Rps", "RequestCost", "Utilization"});
        Descriptors.Descriptor descriptor3 = (Descriptors.Descriptor) descriptor2.getNestedTypes().get(0);
        internal_static_udpa_data_orca_v1_OrcaLoadReport_RequestCostEntry_descriptor = descriptor3;
        internal_static_udpa_data_orca_v1_OrcaLoadReport_RequestCostEntry_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor3, new String[]{"Key", "Value"});
        Descriptors.Descriptor descriptor4 = (Descriptors.Descriptor) descriptor2.getNestedTypes().get(1);
        internal_static_udpa_data_orca_v1_OrcaLoadReport_UtilizationEntry_descriptor = descriptor4;
        internal_static_udpa_data_orca_v1_OrcaLoadReport_UtilizationEntry_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor4, new String[]{"Key", "Value"});
        ExtensionRegistry extensionRegistryNewInstance = ExtensionRegistry.newInstance();
        extensionRegistryNewInstance.add(Validate.rules);
        Descriptors.FileDescriptor.internalUpdateFileDescriptor(descriptor, extensionRegistryNewInstance);
        Validate.getDescriptor();
    }

    private OrcaLoadReportProto() {
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
