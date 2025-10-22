package io.grpc.xds.shaded.io.envoyproxy.envoy.config.route.v3;

import com.google.protobuf.MessageOrBuilder;
import com.google.protobuf.UInt32Value;
import com.google.protobuf.UInt32ValueOrBuilder;
import io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.TypedExtensionConfig;
import io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.TypedExtensionConfigOrBuilder;

import java.util.List;

/* loaded from: classes4.dex */
public interface InternalRedirectPolicyOrBuilder extends MessageOrBuilder {
    boolean getAllowCrossSchemeRedirect();

    UInt32Value getMaxInternalRedirects();

    UInt32ValueOrBuilder getMaxInternalRedirectsOrBuilder();

    TypedExtensionConfig getPredicates(int i);

    int getPredicatesCount();

    List<TypedExtensionConfig> getPredicatesList();

    TypedExtensionConfigOrBuilder getPredicatesOrBuilder(int i);

    List<? extends TypedExtensionConfigOrBuilder> getPredicatesOrBuilderList();

    int getRedirectResponseCodes(int i);

    int getRedirectResponseCodesCount();

    List<Integer> getRedirectResponseCodesList();

    boolean hasMaxInternalRedirects();
}
