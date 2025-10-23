package io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core;

import com.google.protobuf.Descriptors;
import com.google.protobuf.Internal;
import com.google.protobuf.ProtocolMessageEnum;

/* loaded from: classes3.dex */
public enum RoutingPriority implements ProtocolMessageEnum {
    DEFAULT(0),
    HIGH(1),
    UNRECOGNIZED(-1);

    public static final int DEFAULT_VALUE = 0;
    public static final int HIGH_VALUE = 1;
    private static final Internal.EnumLiteMap<RoutingPriority> internalValueMap = new Internal.EnumLiteMap<RoutingPriority>() { // from class: io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.RoutingPriority.1
        public RoutingPriority findValueByNumber(int i) {
            return RoutingPriority.forNumber(i);
        }
    };
    private static final RoutingPriority[] VALUES = values();
    private final int value;

    RoutingPriority(int i) {
        this.value = i;
    }

    public static RoutingPriority forNumber(int i) {
        if (i == 0) {
            return DEFAULT;
        }
        if (i != 1) {
            return null;
        }
        return HIGH;
    }

    public static Internal.EnumLiteMap<RoutingPriority> internalGetValueMap() {
        return internalValueMap;
    }

    @Deprecated
    public static RoutingPriority valueOf(int i) {
        return forNumber(i);
    }

    public static final Descriptors.EnumDescriptor getDescriptor() {
        return (Descriptors.EnumDescriptor) BaseProto.getDescriptor().getEnumTypes().get(0);
    }

    public static RoutingPriority valueOf(Descriptors.EnumValueDescriptor enumValueDescriptor) {
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
