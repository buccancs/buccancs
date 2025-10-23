package io.grpc.xds.shaded.io.envoyproxy.envoy.config.accesslog.v3;

import com.google.protobuf.MessageOrBuilder;

import java.util.List;

/* loaded from: classes2.dex */
public interface OrFilterOrBuilder extends MessageOrBuilder {
    AccessLogFilter getFilters(int i);

    int getFiltersCount();

    List<AccessLogFilter> getFiltersList();

    AccessLogFilterOrBuilder getFiltersOrBuilder(int i);

    List<? extends AccessLogFilterOrBuilder> getFiltersOrBuilderList();
}
