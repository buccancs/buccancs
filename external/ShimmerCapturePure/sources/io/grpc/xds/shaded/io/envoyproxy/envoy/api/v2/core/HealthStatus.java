package io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core;

import com.google.protobuf.Descriptors;
import com.google.protobuf.Internal;
import com.google.protobuf.ProtocolMessageEnum;

/* loaded from: classes3.dex */
public enum HealthStatus implements ProtocolMessageEnum {
    UNKNOWN(0),
    HEALTHY(1),
    UNHEALTHY(2),
    DRAINING(3),
    TIMEOUT(4),
    DEGRADED(5),
    UNRECOGNIZED(-1);

    public static final int DEGRADED_VALUE = 5;
    public static final int DRAINING_VALUE = 3;
    public static final int HEALTHY_VALUE = 1;
    public static final int TIMEOUT_VALUE = 4;
    public static final int UNHEALTHY_VALUE = 2;
    public static final int UNKNOWN_VALUE = 0;
    private static final Internal.EnumLiteMap<HealthStatus> internalValueMap = new Internal.EnumLiteMap<HealthStatus>() { // from class: io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.HealthStatus.1
        public HealthStatus findValueByNumber(int i) {
            return HealthStatus.forNumber(i);
        }
    };
    private static final HealthStatus[] VALUES = values();
    private final int value;

    HealthStatus(int i) {
        this.value = i;
    }

    public static HealthStatus forNumber(int i) {
        if (i == 0) {
            return UNKNOWN;
        }
        if (i == 1) {
            return HEALTHY;
        }
        if (i == 2) {
            return UNHEALTHY;
        }
        if (i == 3) {
            return DRAINING;
        }
        if (i == 4) {
            return TIMEOUT;
        }
        if (i != 5) {
            return null;
        }
        return DEGRADED;
    }

    public static Internal.EnumLiteMap<HealthStatus> internalGetValueMap() {
        return internalValueMap;
    }

    @Deprecated
    public static HealthStatus valueOf(int i) {
        return forNumber(i);
    }

    public static final Descriptors.EnumDescriptor getDescriptor() {
        return (Descriptors.EnumDescriptor) HealthCheckProto.getDescriptor().getEnumTypes().get(0);
    }

    public static HealthStatus valueOf(Descriptors.EnumValueDescriptor enumValueDescriptor) {
        if (enumValueDescriptor.getType() == getDescriptor()) {
            return enumValueDescriptor.getIndex() == -1 ? UNRECOGNIZED : VALUES[enumValueDescriptor.getIndex()];
        }
        throw new IllegalArgumentException("EnumValueDescriptor is not for this type.");
    }

    public final int getNumber() {
        if (this != UNRECOGNIZED) {
            return this.value;
        }
        throw new IllegalArgumentException("Can't get the number of an unknown enum value.");
    }

    public final Descriptors.EnumValueDescriptor getValueDescriptor() {
        if (this == UNRECOGNIZED) {
            throw new IllegalStateException("Can't get the descriptor of an unrecognized enum value.");
        }
        return (Descriptors.EnumValueDescriptor) getDescriptor().getValues().get(ordinal());
    }

    public final Descriptors.EnumDescriptor getDescriptorForType() {
        return getDescriptor();
    }
}
