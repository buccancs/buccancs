package io.grpc.xds.shaded.io.envoyproxy.envoy.config.filter.accesslog.v2;

import com.google.protobuf.MessageOrBuilder;
import io.grpc.xds.shaded.io.envoyproxy.envoy.config.filter.accesslog.v2.AccessLogFilter;

/* loaded from: classes6.dex */
public interface AccessLogFilterOrBuilder extends MessageOrBuilder {
    AndFilter getAndFilter();

    AndFilterOrBuilder getAndFilterOrBuilder();

    DurationFilter getDurationFilter();

    DurationFilterOrBuilder getDurationFilterOrBuilder();

    ExtensionFilter getExtensionFilter();

    ExtensionFilterOrBuilder getExtensionFilterOrBuilder();

    AccessLogFilter.FilterSpecifierCase getFilterSpecifierCase();

    GrpcStatusFilter getGrpcStatusFilter();

    GrpcStatusFilterOrBuilder getGrpcStatusFilterOrBuilder();

    HeaderFilter getHeaderFilter();

    HeaderFilterOrBuilder getHeaderFilterOrBuilder();

    NotHealthCheckFilter getNotHealthCheckFilter();

    NotHealthCheckFilterOrBuilder getNotHealthCheckFilterOrBuilder();

    OrFilter getOrFilter();

    OrFilterOrBuilder getOrFilterOrBuilder();

    ResponseFlagFilter getResponseFlagFilter();

    ResponseFlagFilterOrBuilder getResponseFlagFilterOrBuilder();

    RuntimeFilter getRuntimeFilter();

    RuntimeFilterOrBuilder getRuntimeFilterOrBuilder();

    StatusCodeFilter getStatusCodeFilter();

    StatusCodeFilterOrBuilder getStatusCodeFilterOrBuilder();

    TraceableFilter getTraceableFilter();

    TraceableFilterOrBuilder getTraceableFilterOrBuilder();

    boolean hasAndFilter();

    boolean hasDurationFilter();

    boolean hasExtensionFilter();

    boolean hasGrpcStatusFilter();

    boolean hasHeaderFilter();

    boolean hasNotHealthCheckFilter();

    boolean hasOrFilter();

    boolean hasResponseFlagFilter();

    boolean hasRuntimeFilter();

    boolean hasStatusCodeFilter();

    boolean hasTraceableFilter();
}
