package io.grpc.xds.shaded.io.envoyproxy.envoy.config.route.v3;

import com.google.protobuf.BoolValue;
import com.google.protobuf.BoolValueOrBuilder;
import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;

/* loaded from: classes4.dex */
public interface DecoratorOrBuilder extends MessageOrBuilder {
    String getOperation();

    ByteString getOperationBytes();

    BoolValue getPropagate();

    BoolValueOrBuilder getPropagateOrBuilder();

    boolean hasPropagate();
}
