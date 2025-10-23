package io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;

/* loaded from: classes6.dex */
public interface HeaderValueOrBuilder extends MessageOrBuilder {
    String getKey();

    ByteString getKeyBytes();

    String getValue();

    ByteString getValueBytes();
}
