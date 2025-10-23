package io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route;

import com.google.protobuf.BoolValue;
import com.google.protobuf.BoolValueOrBuilder;
import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;
import io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.QueryParameterMatcher;
import io.grpc.xds.shaded.io.envoyproxy.envoy.type.matcher.StringMatcher;
import io.grpc.xds.shaded.io.envoyproxy.envoy.type.matcher.StringMatcherOrBuilder;

/* loaded from: classes5.dex */
public interface QueryParameterMatcherOrBuilder extends MessageOrBuilder {
    String getName();

    ByteString getNameBytes();

    boolean getPresentMatch();

    QueryParameterMatcher.QueryParameterMatchSpecifierCase getQueryParameterMatchSpecifierCase();

    @Deprecated
    BoolValue getRegex();

    @Deprecated
    BoolValueOrBuilder getRegexOrBuilder();

    StringMatcher getStringMatch();

    StringMatcherOrBuilder getStringMatchOrBuilder();

    @Deprecated
    String getValue();

    @Deprecated
    ByteString getValueBytes();

    @Deprecated
    boolean hasRegex();

    boolean hasStringMatch();
}
