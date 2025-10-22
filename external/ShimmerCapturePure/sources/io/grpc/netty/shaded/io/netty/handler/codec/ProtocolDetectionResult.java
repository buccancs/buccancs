package io.grpc.netty.shaded.io.netty.handler.codec;

import io.grpc.netty.shaded.io.netty.util.internal.ObjectUtil;

/* loaded from: classes3.dex */
public final class ProtocolDetectionResult<T> {
    private static final ProtocolDetectionResult NEEDS_MORE_DATA = new ProtocolDetectionResult(ProtocolDetectionState.NEEDS_MORE_DATA, null);
    private static final ProtocolDetectionResult INVALID = new ProtocolDetectionResult(ProtocolDetectionState.INVALID, null);
    private final T result;
    private final ProtocolDetectionState state;

    private ProtocolDetectionResult(ProtocolDetectionState protocolDetectionState, T t) {
        this.state = protocolDetectionState;
        this.result = t;
    }

    public static <T> ProtocolDetectionResult<T> invalid() {
        return INVALID;
    }

    public static <T> ProtocolDetectionResult<T> needsMoreData() {
        return NEEDS_MORE_DATA;
    }

    public static <T> ProtocolDetectionResult<T> detected(T t) {
        return new ProtocolDetectionResult<>(ProtocolDetectionState.DETECTED, ObjectUtil.checkNotNull(t, "protocol"));
    }

    public T detectedProtocol() {
        return this.result;
    }

    public ProtocolDetectionState state() {
        return this.state;
    }
}
