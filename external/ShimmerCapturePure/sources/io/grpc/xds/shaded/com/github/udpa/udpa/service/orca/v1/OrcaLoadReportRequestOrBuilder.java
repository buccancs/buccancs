package io.grpc.xds.shaded.com.github.udpa.udpa.service.orca.v1;

import com.google.protobuf.ByteString;
import com.google.protobuf.Duration;
import com.google.protobuf.DurationOrBuilder;
import com.google.protobuf.MessageOrBuilder;

import java.util.List;

/* loaded from: classes3.dex */
public interface OrcaLoadReportRequestOrBuilder extends MessageOrBuilder {
    Duration getReportInterval();

    DurationOrBuilder getReportIntervalOrBuilder();

    String getRequestCostNames(int i);

    ByteString getRequestCostNamesBytes(int i);

    int getRequestCostNamesCount();

    /* renamed from: getRequestCostNamesList */
    List<String> mo10404getRequestCostNamesList();

    boolean hasReportInterval();
}
