package io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.cluster;

import com.google.protobuf.Any;
import com.google.protobuf.AnyOrBuilder;
import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;

/* loaded from: classes3.dex */
public interface FilterOrBuilder extends MessageOrBuilder {
    String getName();

    ByteString getNameBytes();

    Any getTypedConfig();

    AnyOrBuilder getTypedConfigOrBuilder();

    boolean hasTypedConfig();
}
