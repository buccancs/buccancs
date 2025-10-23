package io.grpc.xds.shaded.io.envoyproxy.envoy.config.accesslog.v3;

import com.google.protobuf.MessageOrBuilder;
import io.grpc.xds.shaded.io.envoyproxy.envoy.config.accesslog.v3.GrpcStatusFilter;

import java.util.List;

/* loaded from: classes2.dex */
public interface GrpcStatusFilterOrBuilder extends MessageOrBuilder {
    boolean getExclude();

    GrpcStatusFilter.Status getStatuses(int i);

    int getStatusesCount();

    List<GrpcStatusFilter.Status> getStatusesList();

    int getStatusesValue(int i);

    List<Integer> getStatusesValueList();
}
