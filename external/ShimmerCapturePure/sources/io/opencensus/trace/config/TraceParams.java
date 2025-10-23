package io.opencensus.trace.config;

import io.opencensus.internal.Utils;
import io.opencensus.trace.Sampler;
import io.opencensus.trace.config.AutoValue_TraceParams;
import io.opencensus.trace.samplers.Samplers;

/* loaded from: classes4.dex */
public abstract class TraceParams {
    public static final TraceParams DEFAULT;
    private static final double DEFAULT_PROBABILITY = 1.0E-4d;
    private static final Sampler DEFAULT_SAMPLER;
    private static final int DEFAULT_SPAN_MAX_NUM_ANNOTATIONS = 32;
    private static final int DEFAULT_SPAN_MAX_NUM_ATTRIBUTES = 32;
    private static final int DEFAULT_SPAN_MAX_NUM_LINKS = 32;
    private static final int DEFAULT_SPAN_MAX_NUM_MESSAGE_EVENTS = 128;

    static {
        Sampler samplerProbabilitySampler = Samplers.probabilitySampler(DEFAULT_PROBABILITY);
        DEFAULT_SAMPLER = samplerProbabilitySampler;
        DEFAULT = builder().setSampler(samplerProbabilitySampler).setMaxNumberOfAttributes(32).setMaxNumberOfAnnotations(32).setMaxNumberOfMessageEvents(128).setMaxNumberOfLinks(32).build();
    }

    private static Builder builder() {
        return new AutoValue_TraceParams.Builder();
    }

    public abstract int getMaxNumberOfAnnotations();

    public abstract int getMaxNumberOfAttributes();

    public abstract int getMaxNumberOfLinks();

    public abstract int getMaxNumberOfMessageEvents();

    public abstract Sampler getSampler();

    public abstract Builder toBuilder();

    @Deprecated
    public int getMaxNumberOfNetworkEvents() {
        return getMaxNumberOfMessageEvents();
    }

    public static abstract class Builder {
        abstract TraceParams autoBuild();

        public abstract Builder setMaxNumberOfAnnotations(int i);

        public abstract Builder setMaxNumberOfAttributes(int i);

        public abstract Builder setMaxNumberOfLinks(int i);

        public abstract Builder setMaxNumberOfMessageEvents(int i);

        public abstract Builder setSampler(Sampler sampler);

        @Deprecated
        public Builder setMaxNumberOfNetworkEvents(int i) {
            return setMaxNumberOfMessageEvents(i);
        }

        public TraceParams build() {
            TraceParams traceParamsAutoBuild = autoBuild();
            Utils.checkArgument(traceParamsAutoBuild.getMaxNumberOfAttributes() > 0, "maxNumberOfAttributes");
            Utils.checkArgument(traceParamsAutoBuild.getMaxNumberOfAnnotations() > 0, "maxNumberOfAnnotations");
            Utils.checkArgument(traceParamsAutoBuild.getMaxNumberOfMessageEvents() > 0, "maxNumberOfMessageEvents");
            Utils.checkArgument(traceParamsAutoBuild.getMaxNumberOfLinks() > 0, "maxNumberOfLinks");
            return traceParamsAutoBuild;
        }
    }
}
