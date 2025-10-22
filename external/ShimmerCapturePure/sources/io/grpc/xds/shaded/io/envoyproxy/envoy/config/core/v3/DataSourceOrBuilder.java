package io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;
import io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.DataSource;

/* loaded from: classes6.dex */
public interface DataSourceOrBuilder extends MessageOrBuilder {
    String getFilename();

    ByteString getFilenameBytes();

    ByteString getInlineBytes();

    String getInlineString();

    ByteString getInlineStringBytes();

    DataSource.SpecifierCase getSpecifierCase();
}
