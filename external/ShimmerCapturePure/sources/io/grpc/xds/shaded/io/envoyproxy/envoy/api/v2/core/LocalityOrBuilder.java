package io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;

/* loaded from: classes3.dex */
public interface LocalityOrBuilder extends MessageOrBuilder {
    String getRegion();

    ByteString getRegionBytes();

    String getSubZone();

    ByteString getSubZoneBytes();

    String getZone();

    ByteString getZoneBytes();
}
