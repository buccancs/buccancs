package io.grpc.alts.internal;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;

/* loaded from: classes2.dex */
public interface EndpointOrBuilder extends MessageOrBuilder {
    String getIpAddress();

    ByteString getIpAddressBytes();

    int getPort();

    NetworkProtocol getProtocol();

    int getProtocolValue();
}
