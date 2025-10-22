package io.grpc.netty.shaded.io.netty.handler.codec.compression;

import io.grpc.netty.shaded.io.netty.buffer.ByteBuf;

/* loaded from: classes3.dex */
public final class Snappy {
    private static final int COPY_1_BYTE_OFFSET = 1;
    private static final int COPY_2_BYTE_OFFSET = 2;
    private static final int COPY_4_BYTE_OFFSET = 3;
    private static final int LITERAL = 0;
    private static final int MAX_HT_SIZE = 16384;
    private static final int MIN_COMPRESSIBLE_BYTES = 15;
    private static final int NOT_ENOUGH_INPUT = -1;
    private static final int PREAMBLE_NOT_FULL = -1;
    private State state = State.READY;
    private byte tag;
    private int written;

    static int maskChecksum(long j) {
        return (int) (((j << 17) | (j >> 15)) - 1568478504);
    }

    private static int hash(ByteBuf byteBuf, int i, int i2) {
        return (byteBuf.getInt(i) * 506832829) >>> i2;
    }

    private static short[] getHashTable(int i) {
        int i2 = 256;
        while (i2 < 16384 && i2 < i) {
            i2 <<= 1;
        }
        return new short[i2];
    }

    private static int findMatchingLength(ByteBuf byteBuf, int i, int i2, int i3) {
        int i4 = 0;
        while (i2 <= i3 - 4 && byteBuf.getInt(i2) == byteBuf.getInt(i + i4)) {
            i2 += 4;
            i4 += 4;
        }
        while (i2 < i3 && byteBuf.getByte(i + i4) == byteBuf.getByte(i2)) {
            i2++;
            i4++;
        }
        return i4;
    }

    private static int bitsToEncode(int i) {
        int iHighestOneBit = Integer.highestOneBit(i);
        int i2 = 0;
        while (true) {
            iHighestOneBit >>= 1;
            if (iHighestOneBit == 0) {
                return i2;
            }
            i2++;
        }
    }

    static void encodeLiteral(ByteBuf byteBuf, ByteBuf byteBuf2, int i) {
        if (i < 61) {
            byteBuf2.writeByte((i - 1) << 2);
        } else {
            int i2 = i - 1;
            int iBitsToEncode = bitsToEncode(i2) / 8;
            int i3 = iBitsToEncode + 1;
            byteBuf2.writeByte((iBitsToEncode + 60) << 2);
            for (int i4 = 0; i4 < i3; i4++) {
                byteBuf2.writeByte((i2 >> (i4 * 8)) & 255);
            }
        }
        byteBuf2.writeBytes(byteBuf, i);
    }

    private static void encodeCopyWithOffset(ByteBuf byteBuf, int i, int i2) {
        if (i2 < 12 && i < 2048) {
            byteBuf.writeByte(((i2 - 4) << 2) | 1 | ((i >> 8) << 5));
            byteBuf.writeByte(i & 255);
        } else {
            byteBuf.writeByte(((i2 - 1) << 2) | 2);
            byteBuf.writeByte(i & 255);
            byteBuf.writeByte((i >> 8) & 255);
        }
    }

    private static void encodeCopy(ByteBuf byteBuf, int i, int i2) {
        while (i2 >= 68) {
            encodeCopyWithOffset(byteBuf, i, 64);
            i2 -= 64;
        }
        if (i2 > 64) {
            encodeCopyWithOffset(byteBuf, i, 60);
            i2 -= 60;
        }
        encodeCopyWithOffset(byteBuf, i, i2);
    }

    private static int readPreamble(ByteBuf byteBuf) {
        int i = 0;
        int i2 = 0;
        while (byteBuf.isReadable()) {
            short unsignedByte = byteBuf.readUnsignedByte();
            int i3 = i2 + 1;
            i |= (unsignedByte & 127) << (i2 * 7);
            if ((unsignedByte & 128) == 0) {
                return i;
            }
            if (i3 >= 4) {
                throw new DecompressionException("Preamble is greater than 4 bytes");
            }
            i2 = i3;
        }
        return 0;
    }

    static int decodeLiteral(byte b, ByteBuf byteBuf, ByteBuf byteBuf2) {
        byteBuf.markReaderIndex();
        int unsignedByte = (b >> 2) & 63;
        switch (unsignedByte) {
            case 60:
                if (!byteBuf.isReadable()) {
                    return -1;
                }
                unsignedByte = byteBuf.readUnsignedByte();
                break;
            case 61:
                if (byteBuf.readableBytes() >= 2) {
                    unsignedByte = byteBuf.readUnsignedShortLE();
                    break;
                } else {
                    return -1;
                }
            case 62:
                if (byteBuf.readableBytes() >= 3) {
                    unsignedByte = byteBuf.readUnsignedMediumLE();
                    break;
                } else {
                    return -1;
                }
            case 63:
                if (byteBuf.readableBytes() >= 4) {
                    unsignedByte = byteBuf.readIntLE();
                    break;
                } else {
                    return -1;
                }
        }
        int i = unsignedByte + 1;
        if (byteBuf.readableBytes() < i) {
            byteBuf.resetReaderIndex();
            return -1;
        }
        byteBuf2.writeBytes(byteBuf, i);
        return i;
    }

    private static int decodeCopyWith1ByteOffset(byte b, ByteBuf byteBuf, ByteBuf byteBuf2, int i) {
        if (!byteBuf.isReadable()) {
            return -1;
        }
        int iWriterIndex = byteBuf2.writerIndex();
        int i2 = ((b & 28) >> 2) + 4;
        int unsignedByte = (((b & 224) << 8) >> 5) | byteBuf.readUnsignedByte();
        validateOffset(unsignedByte, i);
        byteBuf2.markReaderIndex();
        if (unsignedByte < i2) {
            for (int i3 = i2 / unsignedByte; i3 > 0; i3--) {
                byteBuf2.readerIndex(iWriterIndex - unsignedByte);
                byteBuf2.readBytes(byteBuf2, unsignedByte);
            }
            int i4 = i2 % unsignedByte;
            if (i4 != 0) {
                byteBuf2.readerIndex(iWriterIndex - unsignedByte);
                byteBuf2.readBytes(byteBuf2, i4);
            }
        } else {
            byteBuf2.readerIndex(iWriterIndex - unsignedByte);
            byteBuf2.readBytes(byteBuf2, i2);
        }
        byteBuf2.resetReaderIndex();
        return i2;
    }

    private static int decodeCopyWith2ByteOffset(byte b, ByteBuf byteBuf, ByteBuf byteBuf2, int i) {
        if (byteBuf.readableBytes() < 2) {
            return -1;
        }
        int iWriterIndex = byteBuf2.writerIndex();
        int i2 = ((b >> 2) & 63) + 1;
        int unsignedShortLE = byteBuf.readUnsignedShortLE();
        validateOffset(unsignedShortLE, i);
        byteBuf2.markReaderIndex();
        if (unsignedShortLE < i2) {
            for (int i3 = i2 / unsignedShortLE; i3 > 0; i3--) {
                byteBuf2.readerIndex(iWriterIndex - unsignedShortLE);
                byteBuf2.readBytes(byteBuf2, unsignedShortLE);
            }
            int i4 = i2 % unsignedShortLE;
            if (i4 != 0) {
                byteBuf2.readerIndex(iWriterIndex - unsignedShortLE);
                byteBuf2.readBytes(byteBuf2, i4);
            }
        } else {
            byteBuf2.readerIndex(iWriterIndex - unsignedShortLE);
            byteBuf2.readBytes(byteBuf2, i2);
        }
        byteBuf2.resetReaderIndex();
        return i2;
    }

    private static int decodeCopyWith4ByteOffset(byte b, ByteBuf byteBuf, ByteBuf byteBuf2, int i) {
        if (byteBuf.readableBytes() < 4) {
            return -1;
        }
        int iWriterIndex = byteBuf2.writerIndex();
        int i2 = ((b >> 2) & 63) + 1;
        int intLE = byteBuf.readIntLE();
        validateOffset(intLE, i);
        byteBuf2.markReaderIndex();
        if (intLE < i2) {
            for (int i3 = i2 / intLE; i3 > 0; i3--) {
                byteBuf2.readerIndex(iWriterIndex - intLE);
                byteBuf2.readBytes(byteBuf2, intLE);
            }
            int i4 = i2 % intLE;
            if (i4 != 0) {
                byteBuf2.readerIndex(iWriterIndex - intLE);
                byteBuf2.readBytes(byteBuf2, i4);
            }
        } else {
            byteBuf2.readerIndex(iWriterIndex - intLE);
            byteBuf2.readBytes(byteBuf2, i2);
        }
        byteBuf2.resetReaderIndex();
        return i2;
    }

    private static void validateOffset(int i, int i2) {
        if (i == 0) {
            throw new DecompressionException("Offset is less than minimum permissible value");
        }
        if (i < 0) {
            throw new DecompressionException("Offset is greater than maximum value supported by this implementation");
        }
        if (i > i2) {
            throw new DecompressionException("Offset exceeds size of chunk");
        }
    }

    static int calculateChecksum(ByteBuf byteBuf) {
        return calculateChecksum(byteBuf, byteBuf.readerIndex(), byteBuf.readableBytes());
    }

    static int calculateChecksum(ByteBuf byteBuf, int i, int i2) {
        Crc32c crc32c = new Crc32c();
        try {
            crc32c.update(byteBuf, i, i2);
            return maskChecksum(crc32c.getValue());
        } finally {
            crc32c.reset();
        }
    }

    static void validateChecksum(int i, ByteBuf byteBuf) {
        validateChecksum(i, byteBuf, byteBuf.readerIndex(), byteBuf.readableBytes());
    }

    static void validateChecksum(int i, ByteBuf byteBuf, int i2, int i3) {
        int iCalculateChecksum = calculateChecksum(byteBuf, i2, i3);
        if (iCalculateChecksum == i) {
            return;
        }
        throw new DecompressionException("mismatching checksum: " + Integer.toHexString(iCalculateChecksum) + " (expected: " + Integer.toHexString(i) + ')');
    }

    public void reset() {
        this.state = State.READY;
        this.tag = (byte) 0;
        this.written = 0;
    }

    /* JADX WARN: Code restructure failed: missing block: B:15:0x0055, code lost:

        encodeLiteral(r13, r14, r3 - r5);
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x005a, code lost:

        r4 = findMatchingLength(r13, r10 + 4, r3 + 4, r15) + 4;
        r5 = r3 + r4;
        encodeCopy(r14, r3 - r10, r4);
        r13.readerIndex(r13.readerIndex() + r4);
        r3 = r5 - 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x0074, code lost:

        if (r5 < r8) goto L19;
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x0077, code lost:

        r4 = r5 - r0;
        r1[hash(r13, r3, r2)] = (short) (r4 - 1);
        r3 = hash(r13, r5, r2);
        r10 = r0 + r1[r3];
        r1[r3] = (short) r4;
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x0095, code lost:

        if (r13.getInt(r5) == r13.getInt(r10)) goto L22;
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x00a0, code lost:

        r3 = r5;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void encode(io.grpc.netty.shaded.io.netty.buffer.ByteBuf r13, io.grpc.netty.shaded.io.netty.buffer.ByteBuf r14, int r15) {
        /*
            r12 = this;
            r0 = 0
        L1:
            int r1 = r0 * 7
            int r1 = r15 >>> r1
            r2 = r1 & (-128(0xffffffffffffff80, float:NaN))
            if (r2 == 0) goto L13
            r1 = r1 & 127(0x7f, float:1.78E-43)
            r1 = r1 | 128(0x80, float:1.8E-43)
            r14.writeByte(r1)
            int r0 = r0 + 1
            goto L1
        L13:
            r14.writeByte(r1)
            int r0 = r13.readerIndex()
            short[] r1 = getHashTable(r15)
            int r2 = r1.length
            int r2 = java.lang.Integer.numberOfLeadingZeros(r2)
            int r2 = r2 + 1
            int r3 = r15 - r0
            r4 = 15
            if (r3 < r4) goto La6
            int r3 = r0 + 1
            int r4 = hash(r13, r3, r2)
            r5 = r0
        L32:
            r6 = 32
        L34:
            int r7 = r6 + 1
            int r6 = r6 >> 5
            int r6 = r6 + r3
            int r8 = r15 + (-4)
            if (r6 <= r8) goto L3f
        L3d:
            r0 = r5
            goto La6
        L3f:
            int r9 = hash(r13, r6, r2)
            short r10 = r1[r4]
            int r10 = r10 + r0
            int r11 = r3 - r0
            short r11 = (short) r11
            r1[r4] = r11
            int r4 = r13.getInt(r3)
            int r11 = r13.getInt(r10)
            if (r4 != r11) goto La2
            int r4 = r3 - r5
            encodeLiteral(r13, r14, r4)
        L5a:
            int r4 = r10 + 4
            int r5 = r3 + 4
            int r4 = findMatchingLength(r13, r4, r5, r15)
            int r4 = r4 + 4
            int r5 = r3 + r4
            int r3 = r3 - r10
            encodeCopy(r14, r3, r4)
            int r3 = r13.readerIndex()
            int r3 = r3 + r4
            r13.readerIndex(r3)
            int r3 = r5 + (-1)
            if (r5 < r8) goto L77
            goto L3d
        L77:
            int r3 = hash(r13, r3, r2)
            int r4 = r5 - r0
            int r6 = r4 + (-1)
            short r6 = (short) r6
            r1[r3] = r6
            int r3 = hash(r13, r5, r2)
            short r6 = r1[r3]
            int r10 = r0 + r6
            short r4 = (short) r4
            r1[r3] = r4
            int r3 = r13.getInt(r5)
            int r4 = r13.getInt(r10)
            if (r3 == r4) goto La0
            int r3 = r5 + 1
            int r4 = hash(r13, r3, r2)
            int r3 = r5 + 1
            goto L32
        La0:
            r3 = r5
            goto L5a
        La2:
            r3 = r6
            r6 = r7
            r4 = r9
            goto L34
        La6:
            if (r0 >= r15) goto Lac
            int r15 = r15 - r0
            encodeLiteral(r13, r14, r15)
        Lac:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: io.grpc.netty.shaded.io.netty.handler.codec.compression.Snappy.encode(io.grpc.netty.shaded.io.netty.buffer.ByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf, int):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:45:0x0098  */
    /* JADX WARN: Removed duplicated region for block: B:73:0x0097 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void decode(io.grpc.netty.shaded.io.netty.buffer.ByteBuf r7, io.grpc.netty.shaded.io.netty.buffer.ByteBuf r8) {
        /*
            r6 = this;
        L0:
            boolean r0 = r7.isReadable()
            if (r0 == 0) goto Lb5
            int[] r0 = io.grpc.netty.shaded.io.netty.handler.codec.compression.Snappy.AnonymousClass1.$SwitchMap$io$netty$handler$codec$compression$Snappy$State
            io.grpc.netty.shaded.io.netty.handler.codec.compression.Snappy$State r1 = r6.state
            int r1 = r1.ordinal()
            r0 = r0[r1]
            r1 = 2
            r2 = 1
            r3 = -1
            r4 = 3
            if (r0 == r2) goto L78
            if (r0 == r1) goto L7c
            if (r0 == r4) goto L91
            r5 = 4
            if (r0 == r5) goto L65
            r5 = 5
            if (r0 == r5) goto L21
            goto L0
        L21:
            byte r0 = r6.tag
            r5 = r0 & 3
            if (r5 == r2) goto L52
            if (r5 == r1) goto L3f
            if (r5 == r4) goto L2c
            goto L0
        L2c:
            int r1 = r6.written
            int r0 = decodeCopyWith4ByteOffset(r0, r7, r8, r1)
            if (r0 == r3) goto L3e
            io.grpc.netty.shaded.io.netty.handler.codec.compression.Snappy$State r1 = io.grpc.netty.shaded.io.netty.handler.codec.compression.Snappy.State.READING_TAG
            r6.state = r1
            int r1 = r6.written
            int r1 = r1 + r0
            r6.written = r1
            goto L0
        L3e:
            return
        L3f:
            int r1 = r6.written
            int r0 = decodeCopyWith2ByteOffset(r0, r7, r8, r1)
            if (r0 == r3) goto L51
            io.grpc.netty.shaded.io.netty.handler.codec.compression.Snappy$State r1 = io.grpc.netty.shaded.io.netty.handler.codec.compression.Snappy.State.READING_TAG
            r6.state = r1
            int r1 = r6.written
            int r1 = r1 + r0
            r6.written = r1
            goto L0
        L51:
            return
        L52:
            int r1 = r6.written
            int r0 = decodeCopyWith1ByteOffset(r0, r7, r8, r1)
            if (r0 == r3) goto L64
            io.grpc.netty.shaded.io.netty.handler.codec.compression.Snappy$State r1 = io.grpc.netty.shaded.io.netty.handler.codec.compression.Snappy.State.READING_TAG
            r6.state = r1
            int r1 = r6.written
            int r1 = r1 + r0
            r6.written = r1
            goto L0
        L64:
            return
        L65:
            byte r0 = r6.tag
            int r0 = decodeLiteral(r0, r7, r8)
            if (r0 == r3) goto L77
            io.grpc.netty.shaded.io.netty.handler.codec.compression.Snappy$State r1 = io.grpc.netty.shaded.io.netty.handler.codec.compression.Snappy.State.READING_TAG
            r6.state = r1
            int r1 = r6.written
            int r1 = r1 + r0
            r6.written = r1
            goto L0
        L77:
            return
        L78:
            io.grpc.netty.shaded.io.netty.handler.codec.compression.Snappy$State r0 = io.grpc.netty.shaded.io.netty.handler.codec.compression.Snappy.State.READING_PREAMBLE
            r6.state = r0
        L7c:
            int r0 = readPreamble(r7)
            if (r0 != r3) goto L83
            return
        L83:
            if (r0 != 0) goto L8a
            io.grpc.netty.shaded.io.netty.handler.codec.compression.Snappy$State r7 = io.grpc.netty.shaded.io.netty.handler.codec.compression.Snappy.State.READY
            r6.state = r7
            return
        L8a:
            r8.ensureWritable(r0)
            io.grpc.netty.shaded.io.netty.handler.codec.compression.Snappy$State r0 = io.grpc.netty.shaded.io.netty.handler.codec.compression.Snappy.State.READING_TAG
            r6.state = r0
        L91:
            boolean r0 = r7.isReadable()
            if (r0 != 0) goto L98
            return
        L98:
            byte r0 = r7.readByte()
            r6.tag = r0
            r0 = r0 & r4
            if (r0 == 0) goto Laf
            if (r0 == r2) goto La9
            if (r0 == r1) goto La9
            if (r0 == r4) goto La9
            goto L0
        La9:
            io.grpc.netty.shaded.io.netty.handler.codec.compression.Snappy$State r0 = io.grpc.netty.shaded.io.netty.handler.codec.compression.Snappy.State.READING_COPY
            r6.state = r0
            goto L0
        Laf:
            io.grpc.netty.shaded.io.netty.handler.codec.compression.Snappy$State r0 = io.grpc.netty.shaded.io.netty.handler.codec.compression.Snappy.State.READING_LITERAL
            r6.state = r0
            goto L0
        Lb5:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: io.grpc.netty.shaded.io.netty.handler.codec.compression.Snappy.decode(io.grpc.netty.shaded.io.netty.buffer.ByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf):void");
    }

    private enum State {
        READY,
        READING_PREAMBLE,
        READING_TAG,
        READING_LITERAL,
        READING_COPY
    }

    /* renamed from: io.grpc.netty.shaded.io.netty.handler.codec.compression.Snappy$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$io$netty$handler$codec$compression$Snappy$State;

        static {
            int[] iArr = new int[State.values().length];
            $SwitchMap$io$netty$handler$codec$compression$Snappy$State = iArr;
            try {
                iArr[State.READY.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$io$netty$handler$codec$compression$Snappy$State[State.READING_PREAMBLE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$io$netty$handler$codec$compression$Snappy$State[State.READING_TAG.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$io$netty$handler$codec$compression$Snappy$State[State.READING_LITERAL.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$io$netty$handler$codec$compression$Snappy$State[State.READING_COPY.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
        }
    }
}
