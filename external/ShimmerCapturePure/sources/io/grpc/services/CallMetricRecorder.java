package io.grpc.services;

import androidx.lifecycle.LifecycleKt$$ExternalSyntheticBackportWithForwarding0;
import io.grpc.Context;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicReference;

/* loaded from: classes3.dex */
public final class CallMetricRecorder {
    static final Context.Key<CallMetricRecorder> CONTEXT_KEY = Context.key("io.grpc.services.CallMetricRecorder");
    private static final CallMetricRecorder NOOP = new CallMetricRecorder().disable();
    private final AtomicReference<ConcurrentHashMap<String, Double>> metrics = new AtomicReference<>();
    private volatile boolean disabled;

    CallMetricRecorder() {
    }

    public static CallMetricRecorder getCurrent() {
        CallMetricRecorder callMetricRecorder = CONTEXT_KEY.get();
        return callMetricRecorder != null ? callMetricRecorder : NOOP;
    }

    private CallMetricRecorder disable() {
        this.disabled = true;
        return this;
    }

    boolean isDisabled() {
        return this.disabled;
    }

    public CallMetricRecorder recordCallMetric(String str, double d) {
        if (this.disabled) {
            return this;
        }
        if (this.metrics.get() == null) {
            LifecycleKt$$ExternalSyntheticBackportWithForwarding0.m(this.metrics, null, new ConcurrentHashMap());
        }
        this.metrics.get().put(str, Double.valueOf(d));
        return this;
    }

    Map<String, Double> finalizeAndDump() {
        this.disabled = true;
        ConcurrentHashMap<String, Double> concurrentHashMap = this.metrics.get();
        if (concurrentHashMap == null) {
            return Collections.emptyMap();
        }
        return Collections.unmodifiableMap(concurrentHashMap);
    }
}
