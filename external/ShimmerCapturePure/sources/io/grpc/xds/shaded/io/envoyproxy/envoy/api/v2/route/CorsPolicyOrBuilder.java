package io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route;

import com.google.protobuf.BoolValue;
import com.google.protobuf.BoolValueOrBuilder;
import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;
import io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.RuntimeFractionalPercent;
import io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.RuntimeFractionalPercentOrBuilder;
import io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.CorsPolicy;
import io.grpc.xds.shaded.io.envoyproxy.envoy.type.matcher.StringMatcher;
import io.grpc.xds.shaded.io.envoyproxy.envoy.type.matcher.StringMatcherOrBuilder;

import java.util.List;

/* loaded from: classes5.dex */
public interface CorsPolicyOrBuilder extends MessageOrBuilder {
    BoolValue getAllowCredentials();

    BoolValueOrBuilder getAllowCredentialsOrBuilder();

    String getAllowHeaders();

    ByteString getAllowHeadersBytes();

    String getAllowMethods();

    ByteString getAllowMethodsBytes();

    @Deprecated
    String getAllowOrigin(int i);

    @Deprecated
    ByteString getAllowOriginBytes(int i);

    @Deprecated
    int getAllowOriginCount();

    @Deprecated
        /* renamed from: getAllowOriginList */
    List<String> mo17856getAllowOriginList();

    @Deprecated
    String getAllowOriginRegex(int i);

    @Deprecated
    ByteString getAllowOriginRegexBytes(int i);

    @Deprecated
    int getAllowOriginRegexCount();

    @Deprecated
        /* renamed from: getAllowOriginRegexList */
    List<String> mo17857getAllowOriginRegexList();

    StringMatcher getAllowOriginStringMatch(int i);

    int getAllowOriginStringMatchCount();

    List<StringMatcher> getAllowOriginStringMatchList();

    StringMatcherOrBuilder getAllowOriginStringMatchOrBuilder(int i);

    List<? extends StringMatcherOrBuilder> getAllowOriginStringMatchOrBuilderList();

    @Deprecated
    BoolValue getEnabled();

    @Deprecated
    BoolValueOrBuilder getEnabledOrBuilder();

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

    @Deprecated
    boolean hasEnabled();

    boolean hasFilterEnabled();

    boolean hasShadowEnabled();
}
