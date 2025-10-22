package io.grpc.xds.shaded.io.envoyproxy.envoy.type.matcher;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;
import io.grpc.xds.shaded.io.envoyproxy.envoy.type.matcher.RegexMatcher;

/* loaded from: classes4.dex */
public interface RegexMatcherOrBuilder extends MessageOrBuilder {
    RegexMatcher.EngineTypeCase getEngineTypeCase();

    RegexMatcher.GoogleRE2 getGoogleRe2();

    RegexMatcher.GoogleRE2OrBuilder getGoogleRe2OrBuilder();

    String getRegex();

    ByteString getRegexBytes();

    boolean hasGoogleRe2();
}
