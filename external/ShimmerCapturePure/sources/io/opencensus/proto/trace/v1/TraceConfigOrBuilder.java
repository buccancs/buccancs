package io.opencensus.proto.trace.v1;

import com.google.protobuf.MessageOrBuilder;
import io.opencensus.proto.trace.v1.TraceConfig;

/* loaded from: classes4.dex */
public interface TraceConfigOrBuilder extends MessageOrBuilder {
    ConstantSampler getConstantSampler();

    ConstantSamplerOrBuilder getConstantSamplerOrBuilder();

    long getMaxNumberOfAnnotations();

    long getMaxNumberOfAttributes();

    long getMaxNumberOfLinks();

    long getMaxNumberOfMessageEvents();

    ProbabilitySampler getProbabilitySampler();

    ProbabilitySamplerOrBuilder getProbabilitySamplerOrBuilder();

    RateLimitingSampler getRateLimitingSampler();

    RateLimitingSamplerOrBuilder getRateLimitingSamplerOrBuilder();

    TraceConfig.SamplerCase getSamplerCase();

    boolean hasConstantSampler();

    boolean hasProbabilitySampler();

    boolean hasRateLimitingSampler();
}
