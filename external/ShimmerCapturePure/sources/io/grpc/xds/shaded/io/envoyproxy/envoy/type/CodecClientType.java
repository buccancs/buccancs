package io.grpc.xds.shaded.io.envoyproxy.envoy.type;

import com.google.protobuf.Descriptors;
import com.google.protobuf.Internal;
import com.google.protobuf.ProtocolMessageEnum;

/* loaded from: classes4.dex */
public enum CodecClientType implements ProtocolMessageEnum {
    HTTP1(0),
    HTTP2(1),
    HTTP3(2),
    UNRECOGNIZED(-1);

    public static final int HTTP1_VALUE = 0;
    public static final int HTTP2_VALUE = 1;
    public static final int HTTP3_VALUE = 2;
    private static final Internal.EnumLiteMap<CodecClientType> internalValueMap = new Internal.EnumLiteMap<CodecClientType>() { // from class: io.grpc.xds.shaded.io.envoyproxy.envoy.type.CodecClientType.1
        public CodecClientType findValueByNumber(int i) {
            return CodecClientType.forNumber(i);
        }
    };
    private static final CodecClientType[] VALUES = values();
    private final int value;

    CodecClientType(int i) {
        this.value = i;
    }

    public static CodecClientType forNumber(int i) {
        if (i == 0) {
            return HTTP1;
        }
        if (i == 1) {
            return HTTP2;
        }
        if (i != 2) {
            return null;
        }
        return HTTP3;
    }

    public static Internal.EnumLiteMap<CodecClientType> internalGetValueMap() {
        return internalValueMap;
    }

    @Deprecated
    public static CodecClientType valueOf(int i) {
        return forNumber(i);
    }

    public static final Descriptors.EnumDescriptor getDescriptor() {
        return (Descriptors.EnumDescriptor) HttpProto.getDescriptor().getEnumTypes().get(0);
    }

    public static CodecClientType valueOf(Descriptors.EnumValueDescriptor enumValueDescriptor) {
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
