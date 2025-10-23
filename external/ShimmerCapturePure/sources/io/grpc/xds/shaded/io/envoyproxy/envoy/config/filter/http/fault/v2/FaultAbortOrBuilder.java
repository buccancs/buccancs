package io.grpc.xds.shaded.io.envoyproxy.envoy.config.filter.http.fault.v2;

import com.google.protobuf.MessageOrBuilder;
import io.grpc.xds.shaded.io.envoyproxy.envoy.config.filter.http.fault.v2.FaultAbort;
import io.grpc.xds.shaded.io.envoyproxy.envoy.type.FractionalPercent;
import io.grpc.xds.shaded.io.envoyproxy.envoy.type.FractionalPercentOrBuilder;

/* loaded from: classes6.dex */
public interface FaultAbortOrBuilder extends MessageOrBuilder {
    FaultAbort.ErrorTypeCase getErrorTypeCase();

    FaultAbort.HeaderAbort getHeaderAbort();

    FaultAbort.HeaderAbortOrBuilder getHeaderAbortOrBuilder();

    int getHttpStatus();

    FractionalPercent getPercentage();

    FractionalPercentOrBuilder getPercentageOrBuilder();

    boolean hasHeaderAbort();

    boolean hasPercentage();
}
