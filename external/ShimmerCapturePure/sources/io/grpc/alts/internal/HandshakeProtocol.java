package io.grpc.alts.internal;

import com.google.protobuf.Descriptors;
import com.google.protobuf.Internal;
import com.google.protobuf.ProtocolMessageEnum;

/* loaded from: classes2.dex */
public enum HandshakeProtocol implements ProtocolMessageEnum {
    HANDSHAKE_PROTOCOL_UNSPECIFIED(0),
    TLS(1),
    ALTS(2),
    UNRECOGNIZED(-1);

    public static final int ALTS_VALUE = 2;
    public static final int HANDSHAKE_PROTOCOL_UNSPECIFIED_VALUE = 0;
    public static final int TLS_VALUE = 1;
    private static final Internal.EnumLiteMap<HandshakeProtocol> internalValueMap = new Internal.EnumLiteMap<HandshakeProtocol>() { // from class: io.grpc.alts.internal.HandshakeProtocol.1
        public HandshakeProtocol findValueByNumber(int i) {
            return HandshakeProtocol.forNumber(i);
        }
    };
    private static final HandshakeProtocol[] VALUES = values();
    private final int value;

    HandshakeProtocol(int i) {
        this.value = i;
    }

    public static HandshakeProtocol forNumber(int i) {
        if (i == 0) {
            return HANDSHAKE_PROTOCOL_UNSPECIFIED;
        }
        if (i == 1) {
            return TLS;
        }
        if (i != 2) {
            return null;
        }
        return ALTS;
    }

    public static Internal.EnumLiteMap<HandshakeProtocol> internalGetValueMap() {
        return internalValueMap;
    }

    @Deprecated
    public static HandshakeProtocol valueOf(int i) {
        return forNumber(i);
    }

    public static final Descriptors.EnumDescriptor getDescriptor() {
        return (Descriptors.EnumDescriptor) HandshakerProto.getDescriptor().getEnumTypes().get(0);
    }

    public static HandshakeProtocol valueOf(Descriptors.EnumValueDescriptor enumValueDescriptor) {
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
