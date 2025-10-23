package io.grpc.xds.shaded.io.envoyproxy.envoy.type.matcher.v3;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;
import io.grpc.xds.shaded.io.envoyproxy.envoy.type.matcher.v3.StringMatcher;

/* loaded from: classes4.dex */
public interface StringMatcherOrBuilder extends MessageOrBuilder {
    String getExact();

    ByteString getExactBytes();

    boolean getIgnoreCase();

    StringMatcher.MatchPatternCase getMatchPatternCase();

    String getPrefix();

    ByteString getPrefixBytes();

    RegexMatcher getSafeRegex();

    RegexMatcherOrBuilder getSafeRegexOrBuilder();

    String getSuffix();

    ByteString getSuffixBytes();

    boolean hasSafeRegex();
}
