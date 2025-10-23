package io.grpc.xds.shaded.io.envoyproxy.envoy.type.matcher;

import com.google.protobuf.MessageOrBuilder;

import java.util.List;

/* loaded from: classes4.dex */
public interface ListStringMatcherOrBuilder extends MessageOrBuilder {
    StringMatcher getPatterns(int i);

    int getPatternsCount();

    List<StringMatcher> getPatternsList();

    StringMatcherOrBuilder getPatternsOrBuilder(int i);

    List<? extends StringMatcherOrBuilder> getPatternsOrBuilderList();
}
