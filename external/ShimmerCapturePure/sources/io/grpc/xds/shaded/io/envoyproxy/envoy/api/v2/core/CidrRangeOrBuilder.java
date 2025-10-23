package io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;
import com.google.protobuf.UInt32Value;
import com.google.protobuf.UInt32ValueOrBuilder;

/* loaded from: classes3.dex */
public interface CidrRangeOrBuilder extends MessageOrBuilder {
    String getAddressPrefix();

    ByteString getAddressPrefixBytes();

    UInt32Value getPrefixLen();

    UInt32ValueOrBuilder getPrefixLenOrBuilder();

    boolean hasPrefixLen();
}
