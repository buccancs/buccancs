package io.grpc.xds.shaded.io.envoyproxy.envoy.type.matcher;

import com.google.protobuf.MessageOrBuilder;
import io.grpc.xds.shaded.io.envoyproxy.envoy.type.matcher.ListMatcher;

/* loaded from: classes4.dex */
public interface ListMatcherOrBuilder extends MessageOrBuilder {
    ListMatcher.MatchPatternCase getMatchPatternCase();

    ValueMatcher getOneOf();

    ValueMatcherOrBuilder getOneOfOrBuilder();

    boolean hasOneOf();
}
