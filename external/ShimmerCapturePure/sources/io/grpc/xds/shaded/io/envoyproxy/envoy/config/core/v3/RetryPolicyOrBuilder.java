package io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3;

import com.google.protobuf.MessageOrBuilder;
import com.google.protobuf.UInt32Value;
import com.google.protobuf.UInt32ValueOrBuilder;

/* loaded from: classes6.dex */
public interface RetryPolicyOrBuilder extends MessageOrBuilder {
    UInt32Value getNumRetries();

    UInt32ValueOrBuilder getNumRetriesOrBuilder();

    BackoffStrategy getRetryBackOff();

    BackoffStrategyOrBuilder getRetryBackOffOrBuilder();

    boolean hasNumRetries();

    boolean hasRetryBackOff();
}
