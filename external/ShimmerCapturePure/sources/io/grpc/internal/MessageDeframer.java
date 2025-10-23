package io.grpc.internal;

import com.google.common.base.Preconditions;
import io.grpc.Codec;
import io.grpc.Decompressor;
import io.grpc.Status;
import io.grpc.internal.StreamListener;

import java.io.Closeable;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.DataFormatException;
import javax.annotation.Nullable;

/* loaded from: classes2.dex */
public class MessageDeframer implements Closeable, Deframer {
    private static final int COMPRESSED_FLAG_MASK = 1;
    private static final int HEADER_LENGTH = 5;
    private static final int MAX_BUFFER_SIZE = 2097152;
    private static final int RESERVED_MASK = 254;
    private final StatsTraceContext statsTraceCtx;
    private final TransportTracer transportTracer;
    private boolean compressedFlag;
    private Decompressor decompressor;
    private GzipInflatingBuffer fullStreamDecompressor;
    private int inboundBodyWireSize;
    private byte[] inflatedBuffer;
    private int inflatedIndex;
    private Listener listener;
    private int maxInboundMessageSize;
    private CompositeReadableBuffer nextFrame;
    private long pendingDeliveries;
    private State state = State.HEADER;
    private int requiredLength = 5;
    private CompositeReadableBuffer unprocessed = new CompositeReadableBuffer();
    private boolean inDelivery = false;
    private int currentMessageSeqNo = -1;
    private boolean closeWhenComplete = false;
    private volatile boolean stopDelivery = false;

    public MessageDeframer(Listener listener, Decompressor decompressor, int i, StatsTraceContext statsTraceContext, TransportTracer transportTracer) {
        this.listener = (Listener) Preconditions.checkNotNull(listener, "sink");
        this.decompressor = (Decompressor) Preconditions.checkNotNull(decompressor, "decompressor");
        this.maxInboundMessageSize = i;
        this.statsTraceCtx = (StatsTraceContext) Preconditions.checkNotNull(statsTraceContext, "statsTraceCtx");
        this.transportTracer = (TransportTracer) Preconditions.checkNotNull(transportTracer, "transportTracer");
    }

    boolean hasPendingDeliveries() {
        return this.pendingDeliveries != 0;
    }

    public boolean isClosed() {
        return this.unprocessed == null && this.fullStreamDecompressor == null;
    }

    void setListener(Listener listener) {
        this.listener = listener;
    }

    @Override // io.grpc.internal.Deframer
    public void setMaxInboundMessageSize(int i) {
        this.maxInboundMessageSize = i;
    }

    void stopDelivery() {
        this.stopDelivery = true;
    }

    @Override // io.grpc.internal.Deframer
    public void setDecompressor(Decompressor decompressor) {
        Preconditions.checkState(this.fullStreamDecompressor == null, "Already set full stream decompressor");
        this.decompressor = (Decompressor) Preconditions.checkNotNull(decompressor, "Can't pass an empty decompressor");
    }

    @Override // io.grpc.internal.Deframer
    public void setFullStreamDecompressor(GzipInflatingBuffer gzipInflatingBuffer) {
        Preconditions.checkState(this.decompressor == Codec.Identity.NONE, "per-message decompressor already set");
        Preconditions.checkState(this.fullStreamDecompressor == null, "full stream decompressor already set");
        this.fullStreamDecompressor = (GzipInflatingBuffer) Preconditions.checkNotNull(gzipInflatingBuffer, "Can't pass a null full stream decompressor");
        this.unprocessed = null;
    }

    @Override // io.grpc.internal.Deframer
    public void request(int i) {
        Preconditions.checkArgument(i > 0, "numMessages must be > 0");
        if (isClosed()) {
            return;
        }
        this.pendingDeliveries += i;
        deliver();
    }

    @Override // io.grpc.internal.Deframer
    public void deframe(ReadableBuffer readableBuffer) throws Throwable {
        Preconditions.checkNotNull(readableBuffer, "data");
        boolean z = true;
        try {
            if (!isClosedOrScheduledToClose()) {
                GzipInflatingBuffer gzipInflatingBuffer = this.fullStreamDecompressor;
                if (gzipInflatingBuffer != null) {
                    gzipInflatingBuffer.addGzippedBytes(readableBuffer);
                } else {
                    this.unprocessed.addBuffer(readableBuffer);
                }
                try {
                    deliver();
                    return;
                } catch (Throwable th) {
                    th = th;
                    z = false;
                    if (z) {
                        readableBuffer.close();
                    }
                    throw th;
                }
            }
            readableBuffer.close();
        } catch (Throwable th2) {
            th = th2;
        }
    }

    @Override // io.grpc.internal.Deframer
    public void closeWhenComplete() {
        if (isClosed()) {
            return;
        }
        if (isStalled()) {
            close();
        } else {
            this.closeWhenComplete = true;
        }
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable, io.grpc.internal.Deframer
    public void close() {
        if (isClosed()) {
            return;
        }
        CompositeReadableBuffer compositeReadableBuffer = this.nextFrame;
        boolean z = true;
        boolean z2 = compositeReadableBuffer != null && compositeReadableBuffer.readableBytes() > 0;
        try {
            GzipInflatingBuffer gzipInflatingBuffer = this.fullStreamDecompressor;
            if (gzipInflatingBuffer != null) {
                if (!z2 && !gzipInflatingBuffer.hasPartialData()) {
                    z = false;
                }
                this.fullStreamDecompressor.close();
                z2 = z;
            }
            CompositeReadableBuffer compositeReadableBuffer2 = this.unprocessed;
            if (compositeReadableBuffer2 != null) {
                compositeReadableBuffer2.close();
            }
            CompositeReadableBuffer compositeReadableBuffer3 = this.nextFrame;
            if (compositeReadableBuffer3 != null) {
                compositeReadableBuffer3.close();
            }
            this.fullStreamDecompressor = null;
            this.unprocessed = null;
            this.nextFrame = null;
            this.listener.deframerClosed(z2);
        } catch (Throwable th) {
            this.fullStreamDecompressor = null;
            this.unprocessed = null;
            this.nextFrame = null;
            throw th;
        }
    }

    private boolean isClosedOrScheduledToClose() {
        return isClosed() || this.closeWhenComplete;
    }

    private boolean isStalled() {
        GzipInflatingBuffer gzipInflatingBuffer = this.fullStreamDecompressor;
        if (gzipInflatingBuffer != null) {
            return gzipInflatingBuffer.isStalled();
        }
        return this.unprocessed.readableBytes() == 0;
    }

    /* JADX WARN: Code restructure failed: missing block: B:22:0x0054, code lost:

        if (r7.stopDelivery == false) goto L26;
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x0056, code lost:

        close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x005b, code lost:

        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x005e, code lost:

        if (r7.closeWhenComplete == false) goto L31;
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x0064, code lost:

        if (isStalled() == false) goto L31;
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x0066, code lost:

        close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x006b, code lost:

        return;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void deliver() {
        /*
            r7 = this;
            boolean r0 = r7.inDelivery
            if (r0 == 0) goto L5
            return
        L5:
            r0 = 1
            r7.inDelivery = r0
        L8:
            r1 = 0
            boolean r2 = r7.stopDelivery     // Catch: java.lang.Throwable -> L6c
            if (r2 != 0) goto L52
            long r2 = r7.pendingDeliveries     // Catch: java.lang.Throwable -> L6c
            r4 = 0
            int r6 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r6 <= 0) goto L52
            boolean r2 = r7.readRequiredBytes()     // Catch: java.lang.Throwable -> L6c
            if (r2 == 0) goto L52
            int[] r2 = io.grpc.internal.MessageDeframer.AnonymousClass1.$SwitchMap$io$grpc$internal$MessageDeframer$State     // Catch: java.lang.Throwable -> L6c
            io.grpc.internal.MessageDeframer$State r3 = r7.state     // Catch: java.lang.Throwable -> L6c
            int r3 = r3.ordinal()     // Catch: java.lang.Throwable -> L6c
            r2 = r2[r3]     // Catch: java.lang.Throwable -> L6c
            if (r2 == r0) goto L4e
            r3 = 2
            if (r2 != r3) goto L35
            r7.processBody()     // Catch: java.lang.Throwable -> L6c
            long r2 = r7.pendingDeliveries     // Catch: java.lang.Throwable -> L6c
            r4 = 1
            long r2 = r2 - r4
            r7.pendingDeliveries = r2     // Catch: java.lang.Throwable -> L6c
            goto L8
        L35:
            java.lang.AssertionError r0 = new java.lang.AssertionError     // Catch: java.lang.Throwable -> L6c
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L6c
            r2.<init>()     // Catch: java.lang.Throwable -> L6c
            java.lang.String r3 = "Invalid state: "
            r2.append(r3)     // Catch: java.lang.Throwable -> L6c
            io.grpc.internal.MessageDeframer$State r3 = r7.state     // Catch: java.lang.Throwable -> L6c
            r2.append(r3)     // Catch: java.lang.Throwable -> L6c
            java.lang.String r2 = r2.toString()     // Catch: java.lang.Throwable -> L6c
            r0.<init>(r2)     // Catch: java.lang.Throwable -> L6c
            throw r0     // Catch: java.lang.Throwable -> L6c
        L4e:
            r7.processHeader()     // Catch: java.lang.Throwable -> L6c
            goto L8
        L52:
            boolean r0 = r7.stopDelivery     // Catch: java.lang.Throwable -> L6c
            if (r0 == 0) goto L5c
            r7.close()     // Catch: java.lang.Throwable -> L6c
            r7.inDelivery = r1
            return
        L5c:
            boolean r0 = r7.closeWhenComplete     // Catch: java.lang.Throwable -> L6c
            if (r0 == 0) goto L69
            boolean r0 = r7.isStalled()     // Catch: java.lang.Throwable -> L6c
            if (r0 == 0) goto L69
            r7.close()     // Catch: java.lang.Throwable -> L6c
        L69:
            r7.inDelivery = r1
            return
        L6c:
            r0 = move-exception
            r7.inDelivery = r1
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: io.grpc.internal.MessageDeframer.deliver():void");
    }

    private boolean readRequiredBytes() throws Throwable {
        int andResetDeflatedBytesConsumed;
        int i = 0;
        try {
            if (this.nextFrame == null) {
                this.nextFrame = new CompositeReadableBuffer();
            }
            int andResetBytesConsumed = 0;
            andResetDeflatedBytesConsumed = 0;
            while (true) {
                try {
                    int i2 = this.requiredLength - this.nextFrame.readableBytes();
                    if (i2 <= 0) {
                        if (andResetBytesConsumed <= 0) {
                            return true;
                        }
                        this.listener.bytesRead(andResetBytesConsumed);
                        if (this.state != State.BODY) {
                            return true;
                        }
                        if (this.fullStreamDecompressor != null) {
                            this.statsTraceCtx.inboundWireSize(andResetDeflatedBytesConsumed);
                            this.inboundBodyWireSize += andResetDeflatedBytesConsumed;
                            return true;
                        }
                        this.statsTraceCtx.inboundWireSize(andResetBytesConsumed);
                        this.inboundBodyWireSize += andResetBytesConsumed;
                        return true;
                    }
                    if (this.fullStreamDecompressor != null) {
                        try {
                            byte[] bArr = this.inflatedBuffer;
                            if (bArr == null || this.inflatedIndex == bArr.length) {
                                this.inflatedBuffer = new byte[Math.min(i2, 2097152)];
                                this.inflatedIndex = 0;
                            }
                            int iInflateBytes = this.fullStreamDecompressor.inflateBytes(this.inflatedBuffer, this.inflatedIndex, Math.min(i2, this.inflatedBuffer.length - this.inflatedIndex));
                            andResetBytesConsumed += this.fullStreamDecompressor.getAndResetBytesConsumed();
                            andResetDeflatedBytesConsumed += this.fullStreamDecompressor.getAndResetDeflatedBytesConsumed();
                            if (iInflateBytes == 0) {
                                if (andResetBytesConsumed > 0) {
                                    this.listener.bytesRead(andResetBytesConsumed);
                                    if (this.state == State.BODY) {
                                        if (this.fullStreamDecompressor != null) {
                                            this.statsTraceCtx.inboundWireSize(andResetDeflatedBytesConsumed);
                                            this.inboundBodyWireSize += andResetDeflatedBytesConsumed;
                                        } else {
                                            this.statsTraceCtx.inboundWireSize(andResetBytesConsumed);
                                            this.inboundBodyWireSize += andResetBytesConsumed;
                                        }
                                    }
                                }
                                return false;
                            }
                            this.nextFrame.addBuffer(ReadableBuffers.wrap(this.inflatedBuffer, this.inflatedIndex, iInflateBytes));
                            this.inflatedIndex += iInflateBytes;
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        } catch (DataFormatException e2) {
                            throw new RuntimeException(e2);
                        }
                    } else {
                        if (this.unprocessed.readableBytes() == 0) {
                            if (andResetBytesConsumed > 0) {
                                this.listener.bytesRead(andResetBytesConsumed);
                                if (this.state == State.BODY) {
                                    if (this.fullStreamDecompressor != null) {
                                        this.statsTraceCtx.inboundWireSize(andResetDeflatedBytesConsumed);
                                        this.inboundBodyWireSize += andResetDeflatedBytesConsumed;
                                    } else {
                                        this.statsTraceCtx.inboundWireSize(andResetBytesConsumed);
                                        this.inboundBodyWireSize += andResetBytesConsumed;
                                    }
                                }
                            }
                            return false;
                        }
                        int iMin = Math.min(i2, this.unprocessed.readableBytes());
                        andResetBytesConsumed += iMin;
                        this.nextFrame.addBuffer(this.unprocessed.readBytes(iMin));
                    }
                } catch (Throwable th) {
                    int i3 = andResetBytesConsumed;
                    th = th;
                    i = i3;
                    if (i > 0) {
                        this.listener.bytesRead(i);
                        if (this.state == State.BODY) {
                            if (this.fullStreamDecompressor != null) {
                                this.statsTraceCtx.inboundWireSize(andResetDeflatedBytesConsumed);
                                this.inboundBodyWireSize += andResetDeflatedBytesConsumed;
                            } else {
                                this.statsTraceCtx.inboundWireSize(i);
                                this.inboundBodyWireSize += i;
                            }
                        }
                    }
                    throw th;
                }
            }
        } catch (Throwable th2) {
            th = th2;
            andResetDeflatedBytesConsumed = 0;
        }
    }

    private void processHeader() {
        int unsignedByte = this.nextFrame.readUnsignedByte();
        if ((unsignedByte & RESERVED_MASK) != 0) {
            throw Status.INTERNAL.withDescription("gRPC frame header malformed: reserved bits not zero").asRuntimeException();
        }
        this.compressedFlag = (unsignedByte & 1) != 0;
        int i = this.nextFrame.readInt();
        this.requiredLength = i;
        if (i < 0 || i > this.maxInboundMessageSize) {
            throw Status.RESOURCE_EXHAUSTED.withDescription(String.format("gRPC message exceeds maximum size %d: %d", Integer.valueOf(this.maxInboundMessageSize), Integer.valueOf(this.requiredLength))).asRuntimeException();
        }
        int i2 = this.currentMessageSeqNo + 1;
        this.currentMessageSeqNo = i2;
        this.statsTraceCtx.inboundMessage(i2);
        this.transportTracer.reportMessageReceived();
        this.state = State.BODY;
    }

    private void processBody() {
        this.statsTraceCtx.inboundMessageRead(this.currentMessageSeqNo, this.inboundBodyWireSize, -1L);
        this.inboundBodyWireSize = 0;
        InputStream compressedBody = this.compressedFlag ? getCompressedBody() : getUncompressedBody();
        this.nextFrame = null;
        this.listener.messagesAvailable(new SingleMessageProducer(compressedBody, null));
        this.state = State.HEADER;
        this.requiredLength = 5;
    }

    private InputStream getUncompressedBody() {
        this.statsTraceCtx.inboundUncompressedSize(this.nextFrame.readableBytes());
        return ReadableBuffers.openStream(this.nextFrame, true);
    }

    private InputStream getCompressedBody() {
        if (this.decompressor == Codec.Identity.NONE) {
            throw Status.INTERNAL.withDescription("Can't decode compressed gRPC message as compression not configured").asRuntimeException();
        }
        try {
            return new SizeEnforcingInputStream(this.decompressor.decompress(ReadableBuffers.openStream(this.nextFrame, true)), this.maxInboundMessageSize, this.statsTraceCtx);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private enum State {
        HEADER,
        BODY
    }

    public interface Listener {
        void bytesRead(int i);

        void deframeFailed(Throwable th);

        void deframerClosed(boolean z);

        void messagesAvailable(StreamListener.MessageProducer messageProducer);
    }

    /* renamed from: io.grpc.internal.MessageDeframer$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$io$grpc$internal$MessageDeframer$State;

        static {
            int[] iArr = new int[State.values().length];
            $SwitchMap$io$grpc$internal$MessageDeframer$State = iArr;
            try {
                iArr[State.HEADER.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$io$grpc$internal$MessageDeframer$State[State.BODY.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    static final class SizeEnforcingInputStream extends FilterInputStream {
        private final int maxMessageSize;
        private final StatsTraceContext statsTraceCtx;
        private long count;
        private long mark;
        private long maxCount;

        SizeEnforcingInputStream(InputStream inputStream, int i, StatsTraceContext statsTraceContext) {
            super(inputStream);
            this.mark = -1L;
            this.maxMessageSize = i;
            this.statsTraceCtx = statsTraceContext;
        }

        @Override // java.io.FilterInputStream, java.io.InputStream
        public int read() throws IOException {
            int i = this.in.read();
            if (i != -1) {
                this.count++;
            }
            verifySize();
            reportCount();
            return i;
        }

        @Override // java.io.FilterInputStream, java.io.InputStream
        public int read(byte[] bArr, int i, int i2) throws IOException {
            int i3 = this.in.read(bArr, i, i2);
            if (i3 != -1) {
                this.count += i3;
            }
            verifySize();
            reportCount();
            return i3;
        }

        @Override // java.io.FilterInputStream, java.io.InputStream
        public long skip(long j) throws IOException {
            long jSkip = this.in.skip(j);
            this.count += jSkip;
            verifySize();
            reportCount();
            return jSkip;
        }

        @Override // java.io.FilterInputStream, java.io.InputStream
        public synchronized void mark(int i) {
            this.in.mark(i);
            this.mark = this.count;
        }

        @Override // java.io.FilterInputStream, java.io.InputStream
        public synchronized void reset() throws IOException {
            if (!this.in.markSupported()) {
                throw new IOException("Mark not supported");
            }
            if (this.mark == -1) {
                throw new IOException("Mark not set");
            }
            this.in.reset();
            this.count = this.mark;
        }

        private void reportCount() {
            long j = this.count;
            long j2 = this.maxCount;
            if (j > j2) {
                this.statsTraceCtx.inboundUncompressedSize(j - j2);
                this.maxCount = this.count;
            }
        }

        private void verifySize() {
            if (this.count > this.maxMessageSize) {
                throw Status.RESOURCE_EXHAUSTED.withDescription(String.format("Compressed gRPC message exceeds maximum size %d: %d bytes read", Integer.valueOf(this.maxMessageSize), Long.valueOf(this.count))).asRuntimeException();
            }
        }
    }

    private static class SingleMessageProducer implements StreamListener.MessageProducer {
        private InputStream message;

        /* synthetic */ SingleMessageProducer(InputStream inputStream, AnonymousClass1 anonymousClass1) {
            this(inputStream);
        }

        private SingleMessageProducer(InputStream inputStream) {
            this.message = inputStream;
        }

        @Override // io.grpc.internal.StreamListener.MessageProducer
        @Nullable
        public InputStream next() {
            InputStream inputStream = this.message;
            this.message = null;
            return inputStream;
        }
    }
}
