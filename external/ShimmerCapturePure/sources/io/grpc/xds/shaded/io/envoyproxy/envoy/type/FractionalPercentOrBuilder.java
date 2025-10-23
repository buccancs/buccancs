package io.grpc.xds.shaded.io.envoyproxy.envoy.type;

import com.google.protobuf.MessageOrBuilder;
import io.grpc.xds.shaded.io.envoyproxy.envoy.type.FractionalPercent;

/* loaded from: classes4.dex */
public interface FractionalPercentOrBuilder extends MessageOrBuilder {
    FractionalPercent.DenominatorType getDenominator();

    int getDenominatorValue();

    int getNumerator();
}
