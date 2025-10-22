package io.grpc.xds.shaded.io.envoyproxy.envoy.config.route.v3;

import com.google.protobuf.Any;
import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;
import com.google.protobuf.UInt32Value;
import com.google.protobuf.UInt32ValueOrBuilder;
import io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.HeaderValueOption;
import io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.HeaderValueOptionOrBuilder;
import io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.Metadata;
import io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.MetadataOrBuilder;
import io.grpc.xds.shaded.io.envoyproxy.envoy.config.route.v3.Route;

import java.util.List;
import java.util.Map;

/* loaded from: classes4.dex */
public interface RouteOrBuilder extends MessageOrBuilder {
    boolean containsTypedPerFilterConfig(String str);

    Route.ActionCase getActionCase();

    Decorator getDecorator();

    DecoratorOrBuilder getDecoratorOrBuilder();

    DirectResponseAction getDirectResponse();

    DirectResponseActionOrBuilder getDirectResponseOrBuilder();

    FilterAction getFilterAction();

    FilterActionOrBuilder getFilterActionOrBuilder();

    RouteMatch getMatch();

    RouteMatchOrBuilder getMatchOrBuilder();

    Metadata getMetadata();

    MetadataOrBuilder getMetadataOrBuilder();

    String getName();

    ByteString getNameBytes();

    UInt32Value getPerRequestBufferLimitBytes();

    UInt32ValueOrBuilder getPerRequestBufferLimitBytesOrBuilder();

    RedirectAction getRedirect();

    RedirectActionOrBuilder getRedirectOrBuilder();

    HeaderValueOption getRequestHeadersToAdd(int i);

    int getRequestHeadersToAddCount();

    List<HeaderValueOption> getRequestHeadersToAddList();

    HeaderValueOptionOrBuilder getRequestHeadersToAddOrBuilder(int i);

    List<? extends HeaderValueOptionOrBuilder> getRequestHeadersToAddOrBuilderList();

    String getRequestHeadersToRemove(int i);

    ByteString getRequestHeadersToRemoveBytes(int i);

    int getRequestHeadersToRemoveCount();

    /* renamed from: getRequestHeadersToRemoveList */
    List<String> mo28908getRequestHeadersToRemoveList();

    HeaderValueOption getResponseHeadersToAdd(int i);

    int getResponseHeadersToAddCount();

    List<HeaderValueOption> getResponseHeadersToAddList();

    HeaderValueOptionOrBuilder getResponseHeadersToAddOrBuilder(int i);

    List<? extends HeaderValueOptionOrBuilder> getResponseHeadersToAddOrBuilderList();

    String getResponseHeadersToRemove(int i);

    ByteString getResponseHeadersToRemoveBytes(int i);

    int getResponseHeadersToRemoveCount();

    /* renamed from: getResponseHeadersToRemoveList */
    List<String> mo28909getResponseHeadersToRemoveList();

    RouteAction getRoute();

    RouteActionOrBuilder getRouteOrBuilder();

    Tracing getTracing();

    TracingOrBuilder getTracingOrBuilder();

    @Deprecated
    Map<String, Any> getTypedPerFilterConfig();

    int getTypedPerFilterConfigCount();

    Map<String, Any> getTypedPerFilterConfigMap();

    Any getTypedPerFilterConfigOrDefault(String str, Any any);

    Any getTypedPerFilterConfigOrThrow(String str);

    boolean hasDecorator();

    boolean hasDirectResponse();

    boolean hasFilterAction();

    boolean hasMatch();

    boolean hasMetadata();

    boolean hasPerRequestBufferLimitBytes();

    boolean hasRedirect();

    boolean hasRoute();

    boolean hasTracing();
}
