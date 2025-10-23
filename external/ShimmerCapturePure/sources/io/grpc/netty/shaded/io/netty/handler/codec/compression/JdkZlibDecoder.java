package io.grpc.netty.shaded.io.netty.handler.codec.compression;

import io.grpc.netty.shaded.io.netty.buffer.ByteBuf;
import io.grpc.netty.shaded.io.netty.channel.ChannelHandlerContext;
import io.grpc.netty.shaded.io.netty.util.internal.ObjectUtil;

import java.util.List;
import java.util.zip.CRC32;
import java.util.zip.DataFormatException;
import java.util.zip.Inflater;

/* loaded from: classes3.dex */
public class JdkZlibDecoder extends ZlibDecoder {
    private static final int FCOMMENT = 16;
    private static final int FEXTRA = 4;
    private static final int FHCRC = 2;
    private static final int FNAME = 8;
    private static final int FRESERVED = 224;
    private final ByteBufChecksum crc;
    private final boolean decompressConcatenated;
    private final byte[] dictionary;
    private boolean decideZlibOrNone;
    private volatile boolean finished;
    private int flags;
    private GzipState gzipState;
    private Inflater inflater;
    private int xlen;

    public JdkZlibDecoder() {
        this(ZlibWrapper.ZLIB, null, false, 0);
    }

    public JdkZlibDecoder(int i) {
        this(ZlibWrapper.ZLIB, null, false, i);
    }

    public JdkZlibDecoder(byte[] bArr) {
        this(ZlibWrapper.ZLIB, bArr, false, 0);
    }

    public JdkZlibDecoder(byte[] bArr, int i) {
        this(ZlibWrapper.ZLIB, bArr, false, i);
    }

    public JdkZlibDecoder(ZlibWrapper zlibWrapper) {
        this(zlibWrapper, null, false, 0);
    }

    public JdkZlibDecoder(ZlibWrapper zlibWrapper, int i) {
        this(zlibWrapper, null, false, i);
    }

    public JdkZlibDecoder(ZlibWrapper zlibWrapper, boolean z) {
        this(zlibWrapper, null, z, 0);
    }

    public JdkZlibDecoder(ZlibWrapper zlibWrapper, boolean z, int i) {
        this(zlibWrapper, null, z, i);
    }

    public JdkZlibDecoder(boolean z) {
        this(ZlibWrapper.GZIP, null, z, 0);
    }

    public JdkZlibDecoder(boolean z, int i) {
        this(ZlibWrapper.GZIP, null, z, i);
    }

    private JdkZlibDecoder(ZlibWrapper zlibWrapper, byte[] bArr, boolean z, int i) {
        super(i);
        this.gzipState = GzipState.HEADER_START;
        this.flags = -1;
        this.xlen = -1;
        ObjectUtil.checkNotNull(zlibWrapper, "wrapper");
        this.decompressConcatenated = z;
        int i2 = AnonymousClass1.$SwitchMap$io$netty$handler$codec$compression$ZlibWrapper[zlibWrapper.ordinal()];
        if (i2 == 1) {
            this.inflater = new Inflater(true);
            this.crc = ByteBufChecksum.wrapChecksum(new CRC32());
        } else if (i2 == 2) {
            this.inflater = new Inflater(true);
            this.crc = null;
        } else if (i2 == 3) {
            this.inflater = new Inflater();
            this.crc = null;
        } else {
            if (i2 != 4) {
                throw new IllegalArgumentException("Only GZIP or ZLIB is supported, but you used " + zlibWrapper);
            }
            this.decideZlibOrNone = true;
            this.crc = null;
        }
        this.dictionary = bArr;
    }

    private static boolean looksLikeZlib(short s) {
        return (s & 30720) == 30720 && s % 31 == 0;
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.compression.ZlibDecoder
    protected void decompressionBufferExhausted(ByteBuf byteBuf) {
        this.finished = true;
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.compression.ZlibDecoder
    public boolean isClosed() {
        return this.finished;
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.ByteToMessageDecoder
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        if (this.finished) {
            byteBuf.skipBytes(byteBuf.readableBytes());
            return;
        }
        int i = byteBuf.readableBytes();
        if (i == 0) {
            return;
        }
        boolean z = false;
        if (this.decideZlibOrNone) {
            if (i < 2) {
                return;
            }
            this.inflater = new Inflater(!looksLikeZlib(byteBuf.getShort(byteBuf.readerIndex())));
            this.decideZlibOrNone = false;
        }
        if (this.crc != null) {
            if (AnonymousClass1.$SwitchMap$io$netty$handler$codec$compression$JdkZlibDecoder$GzipState[this.gzipState.ordinal()] == 1) {
                if (readGZIPFooter(byteBuf)) {
                    this.finished = true;
                    return;
                }
                return;
            } else if (this.gzipState != GzipState.HEADER_END && !readGZIPHeader(byteBuf)) {
                return;
            } else {
                i = byteBuf.readableBytes();
            }
        }
        if (byteBuf.hasArray()) {
            this.inflater.setInput(byteBuf.array(), byteBuf.arrayOffset() + byteBuf.readerIndex(), i);
        } else {
            byte[] bArr = new byte[i];
            byteBuf.getBytes(byteBuf.readerIndex(), bArr);
            this.inflater.setInput(bArr);
        }
        ByteBuf byteBufPrepareDecompressBuffer = prepareDecompressBuffer(channelHandlerContext, null, this.inflater.getRemaining() << 1);
        while (true) {
            try {
                try {
                    if (this.inflater.needsInput()) {
                        break;
                    }
                    byte[] bArrArray = byteBufPrepareDecompressBuffer.array();
                    int iWriterIndex = byteBufPrepareDecompressBuffer.writerIndex();
                    int iArrayOffset = byteBufPrepareDecompressBuffer.arrayOffset() + iWriterIndex;
                    int iInflate = this.inflater.inflate(bArrArray, iArrayOffset, byteBufPrepareDecompressBuffer.writableBytes());
                    if (iInflate > 0) {
                        byteBufPrepareDecompressBuffer.writerIndex(iWriterIndex + iInflate);
                        ByteBufChecksum byteBufChecksum = this.crc;
                        if (byteBufChecksum != null) {
                            byteBufChecksum.update(bArrArray, iArrayOffset, iInflate);
                        }
                    } else if (this.inflater.needsDictionary()) {
                        byte[] bArr2 = this.dictionary;
                        if (bArr2 == null) {
                            throw new DecompressionException("decompression failure, unable to set dictionary as non was specified");
                        }
                        this.inflater.setDictionary(bArr2);
                    }
                    if (!this.inflater.finished()) {
                        byteBufPrepareDecompressBuffer = prepareDecompressBuffer(channelHandlerContext, byteBufPrepareDecompressBuffer, this.inflater.getRemaining() << 1);
                    } else if (this.crc == null) {
                        this.finished = true;
                    } else {
                        z = true;
                    }
                } catch (DataFormatException e) {
                    throw new DecompressionException("decompression failure", e);
                }
            } finally {
                if (byteBufPrepareDecompressBuffer.isReadable()) {
                    list.add(byteBufPrepareDecompressBuffer);
                } else {
                    byteBufPrepareDecompressBuffer.release();
                }
            }
        }
        byteBuf.skipBytes(i - this.inflater.getRemaining());
        if (z) {
            this.gzipState = GzipState.FOOTER_START;
            if (readGZIPFooter(byteBuf)) {
                this.finished = !this.decompressConcatenated;
                if (!this.finished) {
                    this.inflater.reset();
                    this.crc.reset();
                    this.gzipState = GzipState.HEADER_START;
                }
            }
        }
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.ByteToMessageDecoder
    protected void handlerRemoved0(ChannelHandlerContext channelHandlerContext) throws Exception {
        super.handlerRemoved0(channelHandlerContext);
        Inflater inflater = this.inflater;
        if (inflater != null) {
            inflater.end();
        }
    }

    private boolean readGZIPHeader(ByteBuf byteBuf) {
        switch (this.gzipState) {
            case HEADER_START:
                if (byteBuf.readableBytes() < 10) {
                    return false;
                }
                byte b = byteBuf.readByte();
                byte b2 = byteBuf.readByte();
                if (b != 31) {
                    throw new DecompressionException("Input is not in the GZIP format");
                }
                this.crc.update(b);
                this.crc.update(b2);
                short unsignedByte = byteBuf.readUnsignedByte();
                if (unsignedByte != 8) {
                    throw new DecompressionException("Unsupported compression method " + ((int) unsignedByte) + " in the GZIP header");
                }
                this.crc.update(unsignedByte);
                short unsignedByte2 = byteBuf.readUnsignedByte();
                this.flags = unsignedByte2;
                this.crc.update(unsignedByte2);
                if ((this.flags & 224) != 0) {
                    throw new DecompressionException("Reserved flags are set in the GZIP header");
                }
                this.crc.update(byteBuf, byteBuf.readerIndex(), 4);
                byteBuf.skipBytes(4);
                this.crc.update(byteBuf.readUnsignedByte());
                this.crc.update(byteBuf.readUnsignedByte());
                this.gzipState = GzipState.FLG_READ;
            case FLG_READ:
                if ((this.flags & 4) != 0) {
                    if (byteBuf.readableBytes() < 2) {
                        return false;
                    }
                    short unsignedByte3 = byteBuf.readUnsignedByte();
                    short unsignedByte4 = byteBuf.readUnsignedByte();
                    this.crc.update(unsignedByte3);
                    this.crc.update(unsignedByte4);
                    this.xlen = (unsignedByte3 << 8) | unsignedByte4 | this.xlen;
                }
                this.gzipState = GzipState.XLEN_READ;
            case XLEN_READ:
                if (this.xlen != -1) {
                    if (byteBuf.readableBytes() < this.xlen) {
                        return false;
                    }
                    this.crc.update(byteBuf, byteBuf.readerIndex(), this.xlen);
                    byteBuf.skipBytes(this.xlen);
                }
                this.gzipState = GzipState.SKIP_FNAME;
            case SKIP_FNAME:
                if ((this.flags & 8) != 0) {
                    if (!byteBuf.isReadable()) {
                        return false;
                    }
                    do {
                        short unsignedByte5 = byteBuf.readUnsignedByte();
                        this.crc.update(unsignedByte5);
                        if (unsignedByte5 == 0) {
                        }
                    } while (byteBuf.isReadable());
                }
                this.gzipState = GzipState.SKIP_COMMENT;
            case SKIP_COMMENT:
                if ((this.flags & 16) != 0) {
                    if (!byteBuf.isReadable()) {
                        return false;
                    }
                    do {
                        short unsignedByte6 = byteBuf.readUnsignedByte();
                        this.crc.update(unsignedByte6);
                        if (unsignedByte6 == 0) {
                        }
                    } while (byteBuf.isReadable());
                }
                this.gzipState = GzipState.PROCESS_FHCRC;
            case PROCESS_FHCRC:
                if ((this.flags & 2) != 0) {
                    if (byteBuf.readableBytes() < 4) {
                        return false;
                    }
                    verifyCrc(byteBuf);
                }
                this.crc.reset();
                this.gzipState = GzipState.HEADER_END;
                return true;
            case HEADER_END:
                return true;
            default:
                throw new IllegalStateException();
        }
    }

    private boolean readGZIPFooter(ByteBuf byteBuf) {
        if (byteBuf.readableBytes() < 8) {
            return false;
        }
        verifyCrc(byteBuf);
        int unsignedByte = 0;
        for (int i = 0; i < 4; i++) {
            unsignedByte |= byteBuf.readUnsignedByte() << (i * 8);
        }
        int totalOut = this.inflater.getTotalOut();
        if (unsignedByte == totalOut) {
            return true;
        }
        throw new DecompressionException("Number of bytes mismatch. Expected: " + unsignedByte + ", Got: " + totalOut);
    }

    private void verifyCrc(ByteBuf byteBuf) {
        long unsignedByte = 0;
        for (int i = 0; i < 4; i++) {
            unsignedByte |= byteBuf.readUnsignedByte() << (i * 8);
        }
        long value = this.crc.getValue();
        if (unsignedByte == value) {
            return;
        }
        throw new DecompressionException("CRC value mismatch. Expected: " + unsignedByte + ", Got: " + value);
    }

    private enum GzipState {
        HEADER_START,
        HEADER_END,
        FLG_READ,
        XLEN_READ,
        SKIP_FNAME,
        SKIP_COMMENT,
        PROCESS_FHCRC,
        FOOTER_START
    }

    /* renamed from: io.grpc.netty.shaded.io.netty.handler.codec.compression.JdkZlibDecoder$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$io$netty$handler$codec$compression$ZlibWrapper;

        static {
            int[] iArr = new int[GzipState.values().length];
            $SwitchMap$io$netty$handler$codec$compression$JdkZlibDecoder$GzipState = iArr;
            try {
                iArr[GzipState.FOOTER_START.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$io$netty$handler$codec$compression$JdkZlibDecoder$GzipState[GzipState.HEADER_START.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$io$netty$handler$codec$compression$JdkZlibDecoder$GzipState[GzipState.FLG_READ.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$io$netty$handler$codec$compression$JdkZlibDecoder$GzipState[GzipState.XLEN_READ.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$io$netty$handler$codec$compression$JdkZlibDecoder$GzipState[GzipState.SKIP_FNAME.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$io$netty$handler$codec$compression$JdkZlibDecoder$GzipState[GzipState.SKIP_COMMENT.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$io$netty$handler$codec$compression$JdkZlibDecoder$GzipState[GzipState.PROCESS_FHCRC.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$io$netty$handler$codec$compression$JdkZlibDecoder$GzipState[GzipState.HEADER_END.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            int[] iArr2 = new int[ZlibWrapper.values().length];
            $SwitchMap$io$netty$handler$codec$compression$ZlibWrapper = iArr2;
            try {
                iArr2[ZlibWrapper.GZIP.ordinal()] = 1;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                $SwitchMap$io$netty$handler$codec$compression$ZlibWrapper[ZlibWrapper.NONE.ordinal()] = 2;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                $SwitchMap$io$netty$handler$codec$compression$ZlibWrapper[ZlibWrapper.ZLIB.ordinal()] = 3;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                $SwitchMap$io$netty$handler$codec$compression$ZlibWrapper[ZlibWrapper.ZLIB_OR_NONE.ordinal()] = 4;
            } catch (NoSuchFieldError unused12) {
            }
        }
    }
}
