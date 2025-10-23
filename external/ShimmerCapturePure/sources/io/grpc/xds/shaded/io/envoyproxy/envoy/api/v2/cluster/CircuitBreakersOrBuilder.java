package io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.cluster;

import com.google.protobuf.MessageOrBuilder;
import io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.cluster.CircuitBreakers;

import java.util.List;

/* loaded from: classes3.dex */
public interface CircuitBreakersOrBuilder extends MessageOrBuilder {
    CircuitBreakers.Thresholds getThresholds(int i);

    int getThresholdsCount();

    List<CircuitBreakers.Thresholds> getThresholdsList();

    CircuitBreakers.ThresholdsOrBuilder getThresholdsOrBuilder(int i);

    List<? extends CircuitBreakers.ThresholdsOrBuilder> getThresholdsOrBuilderList();
}
