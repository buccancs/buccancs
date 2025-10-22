package io.grpc.xds.shaded.io.envoyproxy.envoy.type.matcher;

import com.google.protobuf.MessageOrBuilder;
import io.grpc.xds.shaded.io.envoyproxy.envoy.type.DoubleRange;
import io.grpc.xds.shaded.io.envoyproxy.envoy.type.DoubleRangeOrBuilder;
import io.grpc.xds.shaded.io.envoyproxy.envoy.type.matcher.DoubleMatcher;

/* loaded from: classes4.dex */
public interface DoubleMatcherOrBuilder extends MessageOrBuilder {
    double getExact();

    DoubleMatcher.MatchPatternCase getMatchPatternCase();

    DoubleRange getRange();

    DoubleRangeOrBuilder getRangeOrBuilder();

    boolean hasRange();
}
