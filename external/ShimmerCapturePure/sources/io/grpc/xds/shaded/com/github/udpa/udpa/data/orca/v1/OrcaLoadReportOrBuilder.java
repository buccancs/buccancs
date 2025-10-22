package io.grpc.xds.shaded.com.github.udpa.udpa.data.orca.v1;

import com.google.protobuf.MessageOrBuilder;

import java.util.Map;

/* loaded from: classes3.dex */
public interface OrcaLoadReportOrBuilder extends MessageOrBuilder {
    boolean containsRequestCost(String str);

    boolean containsUtilization(String str);

    double getCpuUtilization();

    double getMemUtilization();

    @Deprecated
    Map<String, Double> getRequestCost();

    int getRequestCostCount();

    Map<String, Double> getRequestCostMap();

    double getRequestCostOrDefault(String str, double d);

    double getRequestCostOrThrow(String str);

    long getRps();

    @Deprecated
    Map<String, Double> getUtilization();

    int getUtilizationCount();

    Map<String, Double> getUtilizationMap();

    double getUtilizationOrDefault(String str, double d);

    double getUtilizationOrThrow(String str);
}
