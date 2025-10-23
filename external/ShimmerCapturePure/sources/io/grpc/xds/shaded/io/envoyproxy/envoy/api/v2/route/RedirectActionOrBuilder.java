package io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;
import io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.RedirectAction;

/* loaded from: classes5.dex */
public interface RedirectActionOrBuilder extends MessageOrBuilder {
    String getHostRedirect();

    ByteString getHostRedirectBytes();

    boolean getHttpsRedirect();

    String getPathRedirect();

    ByteString getPathRedirectBytes();

    RedirectAction.PathRewriteSpecifierCase getPathRewriteSpecifierCase();

    int getPortRedirect();

    String getPrefixRewrite();

    ByteString getPrefixRewriteBytes();

    RedirectAction.RedirectResponseCode getResponseCode();

    int getResponseCodeValue();

    String getSchemeRedirect();

    ByteString getSchemeRedirectBytes();

    RedirectAction.SchemeRewriteSpecifierCase getSchemeRewriteSpecifierCase();

    boolean getStripQuery();
}
