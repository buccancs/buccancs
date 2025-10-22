package io.grpc.services;

import io.grpc.BinaryLog;

import java.io.IOException;

/* loaded from: classes3.dex */
public final class BinaryLogs {
    private BinaryLogs() {
    }

    public static BinaryLog createBinaryLog() throws IOException {
        return new BinaryLogProviderImpl();
    }

    @Deprecated
    public static BinaryLog createBinaryLog(BinaryLogSink binaryLogSink) throws IOException {
        return new BinaryLogProviderImpl(binaryLogSink);
    }

    public static BinaryLog createBinaryLog(BinaryLogSink binaryLogSink, String str) throws IOException {
        return new BinaryLogProviderImpl(binaryLogSink, str);
    }
}
