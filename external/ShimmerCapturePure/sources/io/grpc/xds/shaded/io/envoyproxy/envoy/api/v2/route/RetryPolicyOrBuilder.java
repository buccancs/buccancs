package io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route;

import com.google.protobuf.ByteString;
import com.google.protobuf.Duration;
import com.google.protobuf.DurationOrBuilder;
import com.google.protobuf.MessageOrBuilder;
import com.google.protobuf.UInt32Value;
import com.google.protobuf.UInt32ValueOrBuilder;
import io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.RetryPolicy;

import java.util.List;

/* loaded from: classes2.dex */
public interface RetryPolicyOrBuilder extends MessageOrBuilder {
    long getHostSelectionRetryMaxAttempts();

    UInt32Value getNumRetries();

    UInt32ValueOrBuilder getNumRetriesOrBuilder();

    Duration getPerTryTimeout();

    DurationOrBuilder getPerTryTimeoutOrBuilder();

    HeaderMatcher getRetriableHeaders(int i);

    int getRetriableHeadersCount();

    List<HeaderMatcher> getRetriableHeadersList();

    HeaderMatcherOrBuilder getRetriableHeadersOrBuilder(int i);

    List<? extends HeaderMatcherOrBuilder> getRetriableHeadersOrBuilderList();

    HeaderMatcher getRetriableRequestHeaders(int i);

    int getRetriableRequestHeadersCount();

    List<HeaderMatcher> getRetriableRequestHeadersList();

    HeaderMatcherOrBuilder getRetriableRequestHeadersOrBuilder(int i);

    List<? extends HeaderMatcherOrBuilder> getRetriableRequestHeadersOrBuilderList();

    int getRetriableStatusCodes(int i);

    int getRetriableStatusCodesCount();

    List<Integer> getRetriableStatusCodesList();

    RetryPolicy.RetryBackOff getRetryBackOff();

    RetryPolicy.RetryBackOffOrBuilder getRetryBackOffOrBuilder();

    RetryPolicy.RetryHostPredicate getRetryHostPredicate(int i);

    int getRetryHostPredicateCount();

    List<RetryPolicy.RetryHostPredicate> getRetryHostPredicateList();

    RetryPolicy.RetryHostPredicateOrBuilder getRetryHostPredicateOrBuilder(int i);

    List<? extends RetryPolicy.RetryHostPredicateOrBuilder> getRetryHostPredicateOrBuilderList();

    String getRetryOn();

    ByteString getRetryOnBytes();

    RetryPolicy.RetryPriority getRetryPriority();

    RetryPolicy.RetryPriorityOrBuilder getRetryPriorityOrBuilder();

    boolean hasNumRetries();

    boolean hasPerTryTimeout();

    boolean hasRetryBackOff();

    boolean hasRetryPriority();
}
