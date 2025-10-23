package io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3;

import com.google.protobuf.MessageOrBuilder;
import io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.AsyncDataSource;

/* loaded from: classes6.dex */
public interface AsyncDataSourceOrBuilder extends MessageOrBuilder {
    DataSource getLocal();

    DataSourceOrBuilder getLocalOrBuilder();

    RemoteDataSource getRemote();

    RemoteDataSourceOrBuilder getRemoteOrBuilder();

    AsyncDataSource.SpecifierCase getSpecifierCase();

    boolean hasLocal();

    boolean hasRemote();
}
