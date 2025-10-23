package io.grpc.xds.shaded.io.envoyproxy.envoy.config.route.v3;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;
import io.grpc.xds.shaded.io.envoyproxy.envoy.config.route.v3.QueryParameterMatcher;
import io.grpc.xds.shaded.io.envoyproxy.envoy.type.matcher.v3.StringMatcher;
import io.grpc.xds.shaded.io.envoyproxy.envoy.type.matcher.v3.StringMatcherOrBuilder;

/* loaded from: classes4.dex */
public interface QueryParameterMatcherOrBuilder extends MessageOrBuilder {
    String getName();

    ByteString getNameBytes();

    boolean getPresentMatch();

    QueryParameterMatcher.QueryParameterMatchSpecifierCase getQueryParameterMatchSpecifierCase();

    StringMatcher getStringMatch();

    StringMatcherOrBuilder getStringMatchOrBuilder();

    boolean hasStringMatch();
}
