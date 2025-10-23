package io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;

/* loaded from: classes3.dex */
public interface ExtensionOrBuilder extends MessageOrBuilder {
    String getCategory();

    ByteString getCategoryBytes();

    boolean getDisabled();

    String getName();

    ByteString getNameBytes();

    String getTypeDescriptor();

    ByteString getTypeDescriptorBytes();

    BuildVersion getVersion();

    BuildVersionOrBuilder getVersionOrBuilder();

    boolean hasVersion();
}
