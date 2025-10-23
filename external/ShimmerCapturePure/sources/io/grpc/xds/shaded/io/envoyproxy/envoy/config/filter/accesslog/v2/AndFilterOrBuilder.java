package io.grpc.xds.shaded.io.envoyproxy.envoy.config.filter.accesslog.v2;

import com.google.protobuf.MessageOrBuilder;

import java.util.List;

/* loaded from: classes6.dex */
public interface AndFilterOrBuilder extends MessageOrBuilder {
    AccessLogFilter getFilters(int i);

    int getFiltersCount();

    List<AccessLogFilter> getFiltersList();

    AccessLogFilterOrBuilder getFiltersOrBuilder(int i);

    List<? extends AccessLogFilterOrBuilder> getFiltersOrBuilderList();
}
