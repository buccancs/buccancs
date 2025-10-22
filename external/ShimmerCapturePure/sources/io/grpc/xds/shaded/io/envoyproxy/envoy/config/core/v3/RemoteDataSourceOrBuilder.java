package io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;

/* loaded from: classes6.dex */
public interface RemoteDataSourceOrBuilder extends MessageOrBuilder {
    HttpUri getHttpUri();

    HttpUriOrBuilder getHttpUriOrBuilder();

    RetryPolicy getRetryPolicy();

    RetryPolicyOrBuilder getRetryPolicyOrBuilder();

    String getSha256();

    ByteString getSha256Bytes();

    boolean hasHttpUri();

    boolean hasRetryPolicy();
}
