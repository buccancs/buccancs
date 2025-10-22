package io.grpc.xds.shaded.io.envoyproxy.envoy.config.route.v3;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;
import io.grpc.xds.shaded.io.envoyproxy.envoy.config.route.v3.HeaderMatcher;
import io.grpc.xds.shaded.io.envoyproxy.envoy.type.matcher.v3.RegexMatcher;
import io.grpc.xds.shaded.io.envoyproxy.envoy.type.matcher.v3.RegexMatcherOrBuilder;
import io.grpc.xds.shaded.io.envoyproxy.envoy.type.v3.Int64Range;
import io.grpc.xds.shaded.io.envoyproxy.envoy.type.v3.Int64RangeOrBuilder;

/* loaded from: classes4.dex */
public interface HeaderMatcherOrBuilder extends MessageOrBuilder {
    String getExactMatch();

    ByteString getExactMatchBytes();

    HeaderMatcher.HeaderMatchSpecifierCase getHeaderMatchSpecifierCase();

    boolean getInvertMatch();

    String getName();

    ByteString getNameBytes();

    String getPrefixMatch();

    ByteString getPrefixMatchBytes();

    boolean getPresentMatch();

    Int64Range getRangeMatch();

    Int64RangeOrBuilder getRangeMatchOrBuilder();

    RegexMatcher getSafeRegexMatch();

    RegexMatcherOrBuilder getSafeRegexMatchOrBuilder();

    String getSuffixMatch();

    ByteString getSuffixMatchBytes();

    boolean hasRangeMatch();

    boolean hasSafeRegexMatch();
}
