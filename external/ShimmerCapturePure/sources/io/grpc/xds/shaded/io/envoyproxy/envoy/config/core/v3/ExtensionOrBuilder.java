package io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;

/* loaded from: classes6.dex */
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
