package io.grpc.services;

import io.grpc.Context;

import java.util.Map;

/* loaded from: classes3.dex */
public final class InternalCallMetricRecorder {
    public static final Context.Key<CallMetricRecorder> CONTEXT_KEY = CallMetricRecorder.CONTEXT_KEY;

    private InternalCallMetricRecorder() {
    }

    public static CallMetricRecorder newCallMetricRecorder() {
        return new CallMetricRecorder();
    }

    public static Map<String, Double> finalizeAndDump(CallMetricRecorder callMetricRecorder) {
        return callMetricRecorder.finalizeAndDump();
    }
}
