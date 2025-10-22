package io.opencensus.trace.samplers;

import org.apache.commons.math3.geometry.VectorFormat;

/* loaded from: classes4.dex */
final class AutoValue_ProbabilitySampler extends ProbabilitySampler {
    private final long idUpperBound;
    private final double probability;

    AutoValue_ProbabilitySampler(double d, long j) {
        this.probability = d;
        this.idUpperBound = j;
    }

    @Override
        // io.opencensus.trace.samplers.ProbabilitySampler
    long getIdUpperBound() {
        return this.idUpperBound;
    }

    @Override
        // io.opencensus.trace.samplers.ProbabilitySampler
    double getProbability() {
        return this.probability;
    }

    public String toString() {
        return "ProbabilitySampler{probability=" + this.probability + ", idUpperBound=" + this.idUpperBound + VectorFormat.DEFAULT_SUFFIX;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ProbabilitySampler)) {
            return false;
        }
        ProbabilitySampler probabilitySampler = (ProbabilitySampler) obj;
        return Double.doubleToLongBits(this.probability) == Double.doubleToLongBits(probabilitySampler.getProbability()) && this.idUpperBound == probabilitySampler.getIdUpperBound();
    }

    public int hashCode() {
        long jDoubleToLongBits = ((int) (1000003 ^ ((Double.doubleToLongBits(this.probability) >>> 32) ^ Double.doubleToLongBits(this.probability)))) * 1000003;
        long j = this.idUpperBound;
        return (int) (jDoubleToLongBits ^ (j ^ (j >>> 32)));
    }
}
