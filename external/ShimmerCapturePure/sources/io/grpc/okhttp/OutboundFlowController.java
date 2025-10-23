package io.grpc.okhttp;

import androidx.core.app.NotificationCompat;
import com.google.common.base.Preconditions;
import io.grpc.okhttp.internal.framed.FrameWriter;

import java.io.IOException;
import javax.annotation.Nullable;

import okio.Buffer;

/* loaded from: classes3.dex */
class OutboundFlowController {
    private final FrameWriter frameWriter;
    private final OkHttpClientTransport transport;
    private final OutboundFlowState connectionState = new OutboundFlowState(0, 65535);
    private int initialWindowSize = 65535;

    OutboundFlowController(OkHttpClientTransport okHttpClientTransport, FrameWriter frameWriter) {
        this.transport = (OkHttpClientTransport) Preconditions.checkNotNull(okHttpClientTransport, NotificationCompat.CATEGORY_TRANSPORT);
        this.frameWriter = (FrameWriter) Preconditions.checkNotNull(frameWriter, "frameWriter");
    }

    boolean initialOutboundWindowSize(int i) {
        if (i < 0) {
            throw new IllegalArgumentException("Invalid initial window size: " + i);
        }
        int i2 = i - this.initialWindowSize;
        this.initialWindowSize = i;
        for (OkHttpClientStream okHttpClientStream : this.transport.getActiveStreams()) {
            OutboundFlowState outboundFlowState = (OutboundFlowState) okHttpClientStream.getOutboundFlowState();
            if (outboundFlowState == null) {
                okHttpClientStream.setOutboundFlowState(new OutboundFlowState(this, okHttpClientStream, this.initialWindowSize));
            } else {
                outboundFlowState.incrementStreamWindow(i2);
            }
        }
        return i2 > 0;
    }

    int windowUpdate(@Nullable OkHttpClientStream okHttpClientStream, int i) {
        if (okHttpClientStream == null) {
            int iIncrementStreamWindow = this.connectionState.incrementStreamWindow(i);
            writeStreams();
            return iIncrementStreamWindow;
        }
        OutboundFlowState outboundFlowStateState = state(okHttpClientStream);
        int iIncrementStreamWindow2 = outboundFlowStateState.incrementStreamWindow(i);
        WriteStatus writeStatus = new WriteStatus();
        outboundFlowStateState.writeBytes(outboundFlowStateState.writableWindow(), writeStatus);
        if (writeStatus.hasWritten()) {
            flush();
        }
        return iIncrementStreamWindow2;
    }

    void data(boolean z, int i, Buffer buffer, boolean z2) {
        Preconditions.checkNotNull(buffer, "source");
        OkHttpClientStream stream = this.transport.getStream(i);
        if (stream == null) {
            return;
        }
        OutboundFlowState outboundFlowStateState = state(stream);
        int iWritableWindow = outboundFlowStateState.writableWindow();
        boolean zHasPendingData = outboundFlowStateState.hasPendingData();
        int size = (int) buffer.size();
        if (!zHasPendingData && iWritableWindow >= size) {
            outboundFlowStateState.write(buffer, size, z);
        } else {
            if (!zHasPendingData && iWritableWindow > 0) {
                outboundFlowStateState.write(buffer, iWritableWindow, false);
            }
            outboundFlowStateState.enqueue(buffer, (int) buffer.size(), z);
        }
        if (z2) {
            flush();
        }
    }

    void flush() {
        try {
            this.frameWriter.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private OutboundFlowState state(OkHttpClientStream okHttpClientStream) {
        OutboundFlowState outboundFlowState = (OutboundFlowState) okHttpClientStream.getOutboundFlowState();
        if (outboundFlowState != null) {
            return outboundFlowState;
        }
        OutboundFlowState outboundFlowState2 = new OutboundFlowState(this, okHttpClientStream, this.initialWindowSize);
        okHttpClientStream.setOutboundFlowState(outboundFlowState2);
        return outboundFlowState2;
    }

    void writeStreams() {
        int i;
        OkHttpClientStream[] activeStreams = this.transport.getActiveStreams();
        int iWindow = this.connectionState.window();
        int length = activeStreams.length;
        while (true) {
            i = 0;
            if (length <= 0 || iWindow <= 0) {
                break;
            }
            int iCeil = (int) Math.ceil(iWindow / length);
            for (int i2 = 0; i2 < length && iWindow > 0; i2++) {
                OkHttpClientStream okHttpClientStream = activeStreams[i2];
                OutboundFlowState outboundFlowStateState = state(okHttpClientStream);
                int iMin = Math.min(iWindow, Math.min(outboundFlowStateState.unallocatedBytes(), iCeil));
                if (iMin > 0) {
                    outboundFlowStateState.allocateBytes(iMin);
                    iWindow -= iMin;
                }
                if (outboundFlowStateState.unallocatedBytes() > 0) {
                    activeStreams[i] = okHttpClientStream;
                    i++;
                }
            }
            length = i;
        }
        WriteStatus writeStatus = new WriteStatus();
        OkHttpClientStream[] activeStreams2 = this.transport.getActiveStreams();
        int length2 = activeStreams2.length;
        while (i < length2) {
            OutboundFlowState outboundFlowStateState2 = state(activeStreams2[i]);
            outboundFlowStateState2.writeBytes(outboundFlowStateState2.allocatedBytes(), writeStatus);
            outboundFlowStateState2.clearAllocatedBytes();
            i++;
        }
        if (writeStatus.hasWritten()) {
            flush();
        }
    }

    private static final class WriteStatus {
        int numWrites;

        private WriteStatus() {
        }

        boolean hasWritten() {
            return this.numWrites > 0;
        }

        void incrementNumWrites() {
            this.numWrites++;
        }
    }

    private final class OutboundFlowState {
        final Buffer pendingWriteBuffer;
        final int streamId;
        int allocatedBytes;
        boolean pendingBufferHasEndOfStream;
        OkHttpClientStream stream;
        int window;

        OutboundFlowState(int i, int i2) {
            this.pendingBufferHasEndOfStream = false;
            this.streamId = i;
            this.window = i2;
            this.pendingWriteBuffer = new Buffer();
        }

        OutboundFlowState(OutboundFlowController outboundFlowController, OkHttpClientStream okHttpClientStream, int i) {
            this(okHttpClientStream.id(), i);
            this.stream = okHttpClientStream;
        }

        void allocateBytes(int i) {
            this.allocatedBytes += i;
        }

        int allocatedBytes() {
            return this.allocatedBytes;
        }

        void clearAllocatedBytes() {
            this.allocatedBytes = 0;
        }

        int window() {
            return this.window;
        }

        int unallocatedBytes() {
            return streamableBytes() - this.allocatedBytes;
        }

        int incrementStreamWindow(int i) {
            if (i <= 0 || Integer.MAX_VALUE - i >= this.window) {
                int i2 = this.window + i;
                this.window = i2;
                return i2;
            }
            throw new IllegalArgumentException("Window size overflow for stream: " + this.streamId);
        }

        int writableWindow() {
            return Math.min(this.window, OutboundFlowController.this.connectionState.window());
        }

        int streamableBytes() {
            return Math.max(0, Math.min(this.window, (int) this.pendingWriteBuffer.size()));
        }

        boolean hasPendingData() {
            return this.pendingWriteBuffer.size() > 0;
        }

        int writeBytes(int i, WriteStatus writeStatus) {
            int iMin = Math.min(i, writableWindow());
            int size = 0;
            while (hasPendingData() && iMin > 0) {
                if (iMin >= this.pendingWriteBuffer.size()) {
                    size += (int) this.pendingWriteBuffer.size();
                    Buffer buffer = this.pendingWriteBuffer;
                    write(buffer, (int) buffer.size(), this.pendingBufferHasEndOfStream);
                } else {
                    size += iMin;
                    write(this.pendingWriteBuffer, iMin, false);
                }
                writeStatus.incrementNumWrites();
                iMin = Math.min(i - size, writableWindow());
            }
            return size;
        }

        void write(Buffer buffer, int i, boolean z) {
            do {
                int iMin = Math.min(i, OutboundFlowController.this.frameWriter.maxDataLength());
                int i2 = -iMin;
                OutboundFlowController.this.connectionState.incrementStreamWindow(i2);
                incrementStreamWindow(i2);
                try {
                    OutboundFlowController.this.frameWriter.data(buffer.size() == ((long) iMin) && z, this.streamId, buffer, iMin);
                    this.stream.transportState().onSentBytes(iMin);
                    i -= iMin;
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            } while (i > 0);
        }

        void enqueue(Buffer buffer, int i, boolean z) {
            this.pendingWriteBuffer.write(buffer, i);
            this.pendingBufferHasEndOfStream |= z;
        }
    }
}
