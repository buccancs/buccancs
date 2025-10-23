package io.grpc.health.v1;

import com.google.protobuf.Descriptors;
import com.google.protobuf.ExtensionRegistry;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageV3;

/* loaded from: classes2.dex */
public final class HealthProto {
    static final Descriptors.Descriptor internal_static_grpc_health_v1_HealthCheckRequest_descriptor;
    static final GeneratedMessageV3.FieldAccessorTable internal_static_grpc_health_v1_HealthCheckRequest_fieldAccessorTable;
    static final Descriptors.Descriptor internal_static_grpc_health_v1_HealthCheckResponse_descriptor;
    static final GeneratedMessageV3.FieldAccessorTable internal_static_grpc_health_v1_HealthCheckResponse_fieldAccessorTable;
    private static Descriptors.FileDescriptor descriptor = Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(new String[]{"\n\u001bgrpc/health/v1/health.proto\u0012\u000egrpc.health.v1\"%\n\u0012HealthCheckRequest\u0012\u000f\n\u0007service\u0018\u0001 \u0001(\t\"©\u0001\n\u0013HealthCheckResponse\u0012A\n\u0006status\u0018\u0001 \u0001(\u000e21.grpc.health.v1.HealthCheckResponse.ServingStatus\"O\n\rServingStatus\u0012\u000b\n\u0007UNKNOWN\u0010\u0000\u0012\u000b\n\u0007SERVING\u0010\u0001\u0012\u000f\n\u000bNOT_SERVING\u0010\u0002\u0012\u0013\n\u000fSERVICE_UNKNOWN\u0010\u00032®\u0001\n\u0006Health\u0012P\n\u0005Check\u0012\".grpc.health.v1.HealthCheckRequest\u001a#.grpc.health.v1.HealthCheckResponse\u0012R\n\u0005Watch\u0012\".grpc.health.v1.HealthCheckRequest\u001a#.grpc.health.v1.HealthCheckResponse0\u0001Ba\n\u0011io.grpc.health.v1B\u000bHealthProtoP\u0001Z,google.golang.org/grpc/health/grpc_health_v1ª\u0002\u000eGrpc.Health.V1b\u0006proto3"}, new Descriptors.FileDescriptor[0]);

    static {
        Descriptors.Descriptor descriptor2 = (Descriptors.Descriptor) getDescriptor().getMessageTypes().get(0);
        internal_static_grpc_health_v1_HealthCheckRequest_descriptor = descriptor2;
        internal_static_grpc_health_v1_HealthCheckRequest_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor2, new String[]{"Service"});
        Descriptors.Descriptor descriptor3 = (Descriptors.Descriptor) getDescriptor().getMessageTypes().get(1);
        internal_static_grpc_health_v1_HealthCheckResponse_descriptor = descriptor3;
        internal_static_grpc_health_v1_HealthCheckResponse_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor3, new String[]{"Status"});
    }

    private HealthProto() {
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
