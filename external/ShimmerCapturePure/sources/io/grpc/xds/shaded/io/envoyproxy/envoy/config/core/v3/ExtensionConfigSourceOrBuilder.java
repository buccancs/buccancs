package io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3;

import com.google.protobuf.Any;
import com.google.protobuf.AnyOrBuilder;
import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;

import java.util.List;

/* loaded from: classes6.dex */
public interface ExtensionConfigSourceOrBuilder extends MessageOrBuilder {
    boolean getApplyDefaultConfigWithoutWarming();

    ConfigSource getConfigSource();

    ConfigSourceOrBuilder getConfigSourceOrBuilder();

    Any getDefaultConfig();

    AnyOrBuilder getDefaultConfigOrBuilder();

    String getTypeUrls(int i);

    ByteString getTypeUrlsBytes(int i);

    int getTypeUrlsCount();

    /* renamed from: getTypeUrlsList */
    List<String> mo22026getTypeUrlsList();

    boolean hasConfigSource();

    boolean hasDefaultConfig();
}
