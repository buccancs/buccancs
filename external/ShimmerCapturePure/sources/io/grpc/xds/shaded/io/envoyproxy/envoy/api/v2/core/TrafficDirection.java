package io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core;

import com.google.protobuf.Descriptors;
import com.google.protobuf.Internal;
import com.google.protobuf.ProtocolMessageEnum;

/* loaded from: classes3.dex */
public enum TrafficDirection implements ProtocolMessageEnum {
    UNSPECIFIED(0),
    INBOUND(1),
    OUTBOUND(2),
    UNRECOGNIZED(-1);

    public static final int INBOUND_VALUE = 1;
    public static final int OUTBOUND_VALUE = 2;
    public static final int UNSPECIFIED_VALUE = 0;
    private static final Internal.EnumLiteMap<TrafficDirection> internalValueMap = new Internal.EnumLiteMap<TrafficDirection>() { // from class: io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.TrafficDirection.1
        public TrafficDirection findValueByNumber(int i) {
            return TrafficDirection.forNumber(i);
        }
    };
    private static final TrafficDirection[] VALUES = values();
    private final int value;

    TrafficDirection(int i) {
        this.value = i;
    }

    public static TrafficDirection forNumber(int i) {
        if (i == 0) {
            return UNSPECIFIED;
        }
        if (i == 1) {
            return INBOUND;
        }
        if (i != 2) {
            return null;
        }
        return OUTBOUND;
    }

    public static Internal.EnumLiteMap<TrafficDirection> internalGetValueMap() {
        return internalValueMap;
    }

    @Deprecated
    public static TrafficDirection valueOf(int i) {
        return forNumber(i);
    }

    public static final Descriptors.EnumDescriptor getDescriptor() {
        return (Descriptors.EnumDescriptor) BaseProto.getDescriptor().getEnumTypes().get(2);
    }

    public static TrafficDirection valueOf(Descriptors.EnumValueDescriptor enumValueDescriptor) {
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
