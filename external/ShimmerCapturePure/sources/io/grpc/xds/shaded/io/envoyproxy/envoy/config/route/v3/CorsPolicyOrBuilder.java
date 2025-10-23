package io.grpc.xds.shaded.io.envoyproxy.envoy.config.route.v3;

import com.google.protobuf.BoolValue;
import com.google.protobuf.BoolValueOrBuilder;
import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;
import io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.RuntimeFractionalPercent;
import io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.RuntimeFractionalPercentOrBuilder;
import io.grpc.xds.shaded.io.envoyproxy.envoy.config.route.v3.CorsPolicy;
import io.grpc.xds.shaded.io.envoyproxy.envoy.type.matcher.v3.StringMatcher;
import io.grpc.xds.shaded.io.envoyproxy.envoy.type.matcher.v3.StringMatcherOrBuilder;

import java.util.List;

/* loaded from: classes4.dex */
public interface CorsPolicyOrBuilder extends MessageOrBuilder {
    BoolValue getAllowCredentials();

    BoolValueOrBuilder getAllowCredentialsOrBuilder();

    String getAllowHeaders();

    ByteString getAllowHeadersBytes();

    String getAllowMethods();

    ByteString getAllowMethodsBytes();

    StringMatcher getAllowOriginStringMatch(int i);

    int getAllowOriginStringMatchCount();

    List<StringMatcher> getAllowOriginStringMatchList();

    StringMatcherOrBuilder getAllowOriginStringMatchOrBuilder(int i);

    List<? extends StringMatcherOrBuilder> getAllowOriginStringMatchOrBuilderList();

    CorsPolicy.EnabledSpecifierCase getEnabledSpecifierCase();

    String getExposeHeaders();

    ByteString getExposeHeadersBytes();

    RuntimeFractionalPercent getFilterEnabled();

    RuntimeFractionalPercentOrBuilder getFilterEnabledOrBuilder();

    String getMaxAge();

    ByteString getMaxAgeBytes();

    RuntimeFractionalPercent getShadowEnabled();

    RuntimeFractionalPercentOrBuilder getShadowEnabledOrBuilder();

    boolean hasAllowCredentials();

    boolean hasFilterEnabled();

    boolean hasShadowEnabled();
}
