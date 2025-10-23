package io.grpc.xds.shaded.io.envoyproxy.envoy.config.trace.v2;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;
import com.google.protobuf.Struct;
import com.google.protobuf.StructOrBuilder;

/* loaded from: classes4.dex */
public interface DynamicOtConfigOrBuilder extends MessageOrBuilder {
    Struct getConfig();

    StructOrBuilder getConfigOrBuilder();

    String getLibrary();

    ByteString getLibraryBytes();

    boolean hasConfig();
}
