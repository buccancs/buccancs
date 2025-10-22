package io.grpc.xds.shaded.io.envoyproxy.envoy.service.load_stats.v2;

import com.google.protobuf.ByteString;
import com.google.protobuf.Duration;
import com.google.protobuf.DurationOrBuilder;
import com.google.protobuf.MessageOrBuilder;

import java.util.List;

/* loaded from: classes4.dex */
public interface LoadStatsResponseOrBuilder extends MessageOrBuilder {
    String getClusters(int i);

    ByteString getClustersBytes(int i);

    int getClustersCount();

    /* renamed from: getClustersList */
    List<String> mo32667getClustersList();

    Duration getLoadReportingInterval();

    DurationOrBuilder getLoadReportingIntervalOrBuilder();

    boolean getReportEndpointGranularity();

    boolean getSendAllClusters();

    boolean hasLoadReportingInterval();
}
