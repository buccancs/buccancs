package io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core;

import com.google.protobuf.MessageOrBuilder;
import io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.AsyncDataSource;

/* loaded from: classes3.dex */
public interface AsyncDataSourceOrBuilder extends MessageOrBuilder {
    DataSource getLocal();

    DataSourceOrBuilder getLocalOrBuilder();

    RemoteDataSource getRemote();

    RemoteDataSourceOrBuilder getRemoteOrBuilder();

    AsyncDataSource.SpecifierCase getSpecifierCase();

    boolean hasLocal();

    boolean hasRemote();
}
