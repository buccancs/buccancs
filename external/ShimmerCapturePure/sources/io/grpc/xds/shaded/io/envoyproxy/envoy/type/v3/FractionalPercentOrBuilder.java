package io.grpc.xds.shaded.io.envoyproxy.envoy.type.v3;

import com.google.protobuf.MessageOrBuilder;
import io.grpc.xds.shaded.io.envoyproxy.envoy.type.v3.FractionalPercent;

/* loaded from: classes4.dex */
public interface FractionalPercentOrBuilder extends MessageOrBuilder {
    FractionalPercent.DenominatorType getDenominator();

    int getDenominatorValue();

    int getNumerator();
}
