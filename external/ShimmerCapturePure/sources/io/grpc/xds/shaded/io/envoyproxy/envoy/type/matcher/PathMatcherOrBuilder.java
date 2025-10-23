package io.grpc.xds.shaded.io.envoyproxy.envoy.type.matcher;

import com.google.protobuf.MessageOrBuilder;
import io.grpc.xds.shaded.io.envoyproxy.envoy.type.matcher.PathMatcher;

/* loaded from: classes4.dex */
public interface PathMatcherOrBuilder extends MessageOrBuilder {
    StringMatcher getPath();

    StringMatcherOrBuilder getPathOrBuilder();

    PathMatcher.RuleCase getRuleCase();

    boolean hasPath();
}
