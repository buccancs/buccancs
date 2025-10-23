package io.grpc.netty.shaded.io.netty.buffer.search;

import io.grpc.netty.shaded.io.netty.util.internal.PlatformDependent;

/* loaded from: classes3.dex */
public class KmpSearchProcessorFactory extends AbstractSearchProcessorFactory {
    private final int[] jumpTable;
    private final byte[] needle;

    KmpSearchProcessorFactory(byte[] bArr) {
        this.needle = (byte[]) bArr.clone();
        int i = 1;
        this.jumpTable = new int[bArr.length + 1];
        int i2 = 0;
        while (i < bArr.length) {
            while (i2 > 0 && bArr[i2] != bArr[i]) {
                i2 = this.jumpTable[i2];
            }
            if (bArr[i2] == bArr[i]) {
                i2++;
            }
            i++;
            this.jumpTable[i] = i2;
        }
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.search.SearchProcessorFactory
    public Processor newSearchProcessor() {
        return new Processor(this.needle, this.jumpTable);
    }

    public static class Processor implements SearchProcessor {
        private final int[] jumpTable;
        private final byte[] needle;
        private long currentPosition;

        Processor(byte[] bArr, int[] iArr) {
            this.needle = bArr;
            this.jumpTable = iArr;
        }

        @Override // io.grpc.netty.shaded.io.netty.buffer.search.SearchProcessor
        public void reset() {
            this.currentPosition = 0L;
        }

        @Override // io.grpc.netty.shaded.io.netty.util.ByteProcessor
        public boolean process(byte b) {
            while (true) {
                long j = this.currentPosition;
                if (j <= 0 || PlatformDependent.getByte(this.needle, j) == b) {
                    break;
                }
                this.currentPosition = PlatformDependent.getInt(this.jumpTable, this.currentPosition);
            }
            if (PlatformDependent.getByte(this.needle, this.currentPosition) == b) {
                this.currentPosition++;
            }
            if (this.currentPosition != this.needle.length) {
                return true;
            }
            this.currentPosition = PlatformDependent.getInt(this.jumpTable, r0);
            return false;
        }
    }
}
