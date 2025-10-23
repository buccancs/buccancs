package io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;

/* loaded from: classes3.dex */
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
