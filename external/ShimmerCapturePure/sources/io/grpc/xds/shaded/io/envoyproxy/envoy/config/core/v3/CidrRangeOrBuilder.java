package io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;
import com.google.protobuf.UInt32Value;
import com.google.protobuf.UInt32ValueOrBuilder;

/* loaded from: classes6.dex */
public interface CidrRangeOrBuilder extends MessageOrBuilder {
    String getAddressPrefix();

    ByteString getAddressPrefixBytes();

    UInt32Value getPrefixLen();

    UInt32ValueOrBuilder getPrefixLenOrBuilder();

    boolean hasPrefixLen();
}
