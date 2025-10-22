package io.grpc.netty.shaded.io.netty.internal.tcnative;

import java.nio.ByteBuffer;

/* loaded from: classes3.dex */
public final class Buffer {
    private Buffer() {
    }

    public static native long address(ByteBuffer byteBuffer);

    public static native long size(ByteBuffer byteBuffer);
}
