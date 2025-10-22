package io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core;

import com.google.protobuf.MessageOrBuilder;
import com.google.protobuf.UInt32Value;
import com.google.protobuf.UInt32ValueOrBuilder;

/* loaded from: classes3.dex */
public interface RetryPolicyOrBuilder extends MessageOrBuilder {
    UInt32Value getNumRetries();

    UInt32ValueOrBuilder getNumRetriesOrBuilder();

    BackoffStrategy getRetryBackOff();

    BackoffStrategyOrBuilder getRetryBackOffOrBuilder();

    boolean hasNumRetries();

    boolean hasRetryBackOff();
}
