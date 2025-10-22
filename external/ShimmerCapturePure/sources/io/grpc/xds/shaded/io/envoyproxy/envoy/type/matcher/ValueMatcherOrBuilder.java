package io.grpc.xds.shaded.io.envoyproxy.envoy.type.matcher;

import com.google.protobuf.MessageOrBuilder;
import io.grpc.xds.shaded.io.envoyproxy.envoy.type.matcher.ValueMatcher;

/* loaded from: classes4.dex */
public interface ValueMatcherOrBuilder extends MessageOrBuilder {
    boolean getBoolMatch();

    DoubleMatcher getDoubleMatch();

    DoubleMatcherOrBuilder getDoubleMatchOrBuilder();

    ListMatcher getListMatch();

    ListMatcherOrBuilder getListMatchOrBuilder();

    ValueMatcher.MatchPatternCase getMatchPatternCase();

    ValueMatcher.NullMatch getNullMatch();

    ValueMatcher.NullMatchOrBuilder getNullMatchOrBuilder();

    boolean getPresentMatch();

    StringMatcher getStringMatch();

    StringMatcherOrBuilder getStringMatchOrBuilder();

    boolean hasDoubleMatch();

    boolean hasListMatch();

    boolean hasNullMatch();

    boolean hasStringMatch();
}
