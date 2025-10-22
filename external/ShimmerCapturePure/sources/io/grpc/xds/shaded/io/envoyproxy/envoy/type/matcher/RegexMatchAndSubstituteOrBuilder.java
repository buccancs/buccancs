package io.grpc.xds.shaded.io.envoyproxy.envoy.type.matcher;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;

/* loaded from: classes4.dex */
public interface RegexMatchAndSubstituteOrBuilder extends MessageOrBuilder {
    RegexMatcher getPattern();

    RegexMatcherOrBuilder getPatternOrBuilder();

    String getSubstitution();

    ByteString getSubstitutionBytes();

    boolean hasPattern();
}
