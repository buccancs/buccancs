package io.grpc.binarylog.v1;

import com.google.protobuf.ByteString;
import io.grpc.binarylog.v1.Address;

/* loaded from: classes2.dex */
public interface AddressOrBuilder extends com.google.protobuf.MessageOrBuilder {
    String getAddress();

    ByteString getAddressBytes();

    int getIpPort();

    Address.Type getType();

    int getTypeValue();
}
