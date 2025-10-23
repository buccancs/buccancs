package io.opencensus.common;

import org.apache.commons.math3.geometry.VectorFormat;

/* loaded from: classes4.dex */
final class AutoValue_ServerStats extends ServerStats {
    private final long lbLatencyNs;
    private final long serviceLatencyNs;
    private final byte traceOption;

    AutoValue_ServerStats(long j, long j2, byte b) {
        this.lbLatencyNs = j;
        this.serviceLatencyNs = j2;
        this.traceOption = b;
    }

    @Override // io.opencensus.common.ServerStats
    public long getLbLatencyNs() {
        return this.lbLatencyNs;
    }

    @Override // io.opencensus.common.ServerStats
    public long getServiceLatencyNs() {
        return this.serviceLatencyNs;
    }

    @Override // io.opencensus.common.ServerStats
    public byte getTraceOption() {
        return this.traceOption;
    }

    public int hashCode() {
        long j = this.lbLatencyNs;
        long j2 = ((int) (1000003 ^ (j ^ (j >>> 32)))) * 1000003;
        long j3 = this.serviceLatencyNs;
        return this.traceOption ^ (((int) (j2 ^ (j3 ^ (j3 >>> 32)))) * 1000003);
    }

    public String toString() {
        return "ServerStats{lbLatencyNs=" + this.lbLatencyNs + ", serviceLatencyNs=" + this.serviceLatencyNs + ", traceOption=" + ((int) this.traceOption) + VectorFormat.DEFAULT_SUFFIX;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ServerStats)) {
            return false;
        }
        ServerStats serverStats = (ServerStats) obj;
        return this.lbLatencyNs == serverStats.getLbLatencyNs() && this.serviceLatencyNs == serverStats.getServiceLatencyNs() && this.traceOption == serverStats.getTraceOption();
    }
}
