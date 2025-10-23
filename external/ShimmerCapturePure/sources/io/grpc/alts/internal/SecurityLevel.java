package io.grpc.alts.internal;

import com.google.protobuf.Descriptors;
import com.google.protobuf.Internal;
import com.google.protobuf.ProtocolMessageEnum;

/* loaded from: classes2.dex */
public enum SecurityLevel implements ProtocolMessageEnum {
    SECURITY_NONE(0),
    INTEGRITY_ONLY(1),
    INTEGRITY_AND_PRIVACY(2),
    UNRECOGNIZED(-1);

    public static final int INTEGRITY_AND_PRIVACY_VALUE = 2;
    public static final int INTEGRITY_ONLY_VALUE = 1;
    public static final int SECURITY_NONE_VALUE = 0;
    private static final Internal.EnumLiteMap<SecurityLevel> internalValueMap = new Internal.EnumLiteMap<SecurityLevel>() { // from class: io.grpc.alts.internal.SecurityLevel.1
        public SecurityLevel findValueByNumber(int i) {
            return SecurityLevel.forNumber(i);
        }
    };
    private static final SecurityLevel[] VALUES = values();
    private final int value;

    SecurityLevel(int i) {
        this.value = i;
    }

    public static SecurityLevel forNumber(int i) {
        if (i == 0) {
            return SECURITY_NONE;
        }
        if (i == 1) {
            return INTEGRITY_ONLY;
        }
        if (i != 2) {
            return null;
        }
        return INTEGRITY_AND_PRIVACY;
    }

    public static Internal.EnumLiteMap<SecurityLevel> internalGetValueMap() {
        return internalValueMap;
    }

    @Deprecated
    public static SecurityLevel valueOf(int i) {
        return forNumber(i);
    }

    public static final Descriptors.EnumDescriptor getDescriptor() {
        return (Descriptors.EnumDescriptor) TransportSecurityCommonProto.getDescriptor().getEnumTypes().get(0);
    }

    public static SecurityLevel valueOf(Descriptors.EnumValueDescriptor enumValueDescriptor) {
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
