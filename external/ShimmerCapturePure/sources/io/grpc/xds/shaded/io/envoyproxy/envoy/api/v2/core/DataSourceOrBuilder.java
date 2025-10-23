package io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;
import io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.DataSource;

/* loaded from: classes3.dex */
public interface DataSourceOrBuilder extends MessageOrBuilder {
    String getFilename();

    ByteString getFilenameBytes();

    ByteString getInlineBytes();

    String getInlineString();

    ByteString getInlineStringBytes();

    DataSource.SpecifierCase getSpecifierCase();
}
