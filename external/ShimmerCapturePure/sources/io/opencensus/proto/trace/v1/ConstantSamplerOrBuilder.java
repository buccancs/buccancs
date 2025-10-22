package io.opencensus.proto.trace.v1;

import com.google.protobuf.MessageOrBuilder;
import io.opencensus.proto.trace.v1.ConstantSampler;

/* loaded from: classes4.dex */
public interface ConstantSamplerOrBuilder extends MessageOrBuilder {
    ConstantSampler.ConstantDecision getDecision();

    int getDecisionValue();
}
