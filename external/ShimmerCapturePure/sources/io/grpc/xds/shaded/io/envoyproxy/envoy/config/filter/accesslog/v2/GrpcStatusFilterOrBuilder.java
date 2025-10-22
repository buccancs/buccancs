package io.grpc.xds.shaded.io.envoyproxy.envoy.config.filter.accesslog.v2;

import com.google.protobuf.MessageOrBuilder;
import io.grpc.xds.shaded.io.envoyproxy.envoy.config.filter.accesslog.v2.GrpcStatusFilter;

import java.util.List;

/* loaded from: classes6.dex */
public interface GrpcStatusFilterOrBuilder extends MessageOrBuilder {
    boolean getExclude();

    GrpcStatusFilter.Status getStatuses(int i);

    int getStatusesCount();

    List<GrpcStatusFilter.Status> getStatusesList();

    int getStatusesValue(int i);

    List<Integer> getStatusesValueList();
}
