package io.grpc.alts.internal;

import com.google.common.base.Preconditions;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.security.GeneralSecurityException;

/* loaded from: classes2.dex */
public final class AltsFraming {
    private static final int FRAME_LENGTH_HEADER_SIZE = 4;
    private static final int FRAME_MESSAGE_TYPE_HEADER_SIZE = 4;
    private static final int INITIAL_BUFFER_CAPACITY = 65536;
    private static final int MAX_DATA_LENGTH = 1048576;
    private static final int MESSAGE_TYPE = 6;

    private AltsFraming() {
    }

    static int getFrameLengthHeaderSize() {
        return 4;
    }

    static int getFrameMessageTypeHeaderSize() {
        return 4;
    }

    static int getFramingOverhead() {
        return 8;
    }

    static int getMaxDataLength() {
        return 1048576;
    }

    static ByteBuffer toFrame(ByteBuffer byteBuffer, int i) throws GeneralSecurityException {
        Preconditions.checkNotNull(byteBuffer);
        if (i > byteBuffer.remaining()) {
            i = byteBuffer.remaining();
        }
        Producer producer = new Producer();
        ByteBuffer byteBufferDuplicate = byteBuffer.duplicate();
        byteBufferDuplicate.limit(byteBuffer.position() + i);
        producer.readBytes(byteBufferDuplicate);
        producer.flush();
        byteBuffer.position(byteBufferDuplicate.position());
        return producer.getRawFrame();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void copy(ByteBuffer byteBuffer, ByteBuffer byteBuffer2) {
        if (byteBuffer.hasRemaining() && byteBuffer2.hasRemaining()) {
            if (byteBuffer.remaining() >= byteBuffer2.remaining()) {
                byteBuffer.put(byteBuffer2);
                return;
            }
            int iMin = Math.min(byteBuffer.remaining(), byteBuffer2.remaining());
            ByteBuffer byteBufferSlice = byteBuffer2.slice();
            byteBufferSlice.limit(iMin);
            byteBuffer.put(byteBufferSlice);
            byteBuffer2.position(byteBuffer2.position() + iMin);
        }
    }

    static final class Producer {
        private ByteBuffer buffer;
        private boolean isComplete;

        Producer(int i) {
            this.buffer = ByteBuffer.allocate(i);
            reset();
            Preconditions.checkArgument(i > getFramePrefixLength() + getFrameSuffixLength());
        }

        Producer() {
            this(65536);
        }

        int getFramePrefixLength() {
            return 8;
        }

        int getFrameSuffixLength() {
            return 0;
        }

        boolean readBytes(ByteBuffer byteBuffer) throws GeneralSecurityException {
            Preconditions.checkNotNull(byteBuffer);
            if (this.isComplete) {
                return true;
            }
            AltsFraming.copy(this.buffer, byteBuffer);
            if (!this.buffer.hasRemaining()) {
                flush();
            }
            return this.isComplete;
        }

        void flush() throws GeneralSecurityException {
            if (this.isComplete) {
                return;
            }
            int iPosition = this.buffer.position() + getFrameSuffixLength();
            this.buffer.flip();
            ByteBuffer byteBuffer = this.buffer;
            byteBuffer.limit(byteBuffer.limit() + getFrameSuffixLength());
            this.buffer.order(ByteOrder.LITTLE_ENDIAN);
            this.buffer.putInt(iPosition - 4);
            this.buffer.putInt(6);
            this.buffer.position(0);
            this.isComplete = true;
        }

        private void reset() {
            this.buffer.clear();
            this.buffer.position(getFramePrefixLength());
            ByteBuffer byteBuffer = this.buffer;
            byteBuffer.limit(byteBuffer.limit() - getFrameSuffixLength());
            this.isComplete = false;
        }

        ByteBuffer getRawFrame() {
            if (!this.isComplete) {
                return null;
            }
            ByteBuffer byteBufferDuplicate = this.buffer.duplicate();
            reset();
            return byteBufferDuplicate;
        }
    }

    public static final class Parser {
        private ByteBuffer buffer = ByteBuffer.allocate(65536);
        private boolean isComplete = false;

        public Parser() {
            Preconditions.checkArgument(65536 > getFramePrefixLength() + getFrameSuffixLength());
        }

        int getFramePrefixLength() {
            return 8;
        }

        int getFrameSuffixLength() {
            return 0;
        }

        public boolean isComplete() {
            return this.isComplete;
        }

        public boolean readBytes(ByteBuffer byteBuffer) throws GeneralSecurityException {
            Preconditions.checkNotNull(byteBuffer);
            if (this.isComplete) {
                return true;
            }
            while (this.buffer.position() < 4 && byteBuffer.hasRemaining()) {
                this.buffer.put(byteBuffer.get());
            }
            if (this.buffer.position() == 4 && byteBuffer.hasRemaining()) {
                ByteBuffer byteBufferDuplicate = this.buffer.duplicate();
                byteBufferDuplicate.flip();
                byteBufferDuplicate.order(ByteOrder.LITTLE_ENDIAN);
                int i = byteBufferDuplicate.getInt();
                if (i < 4 || i > 1048576) {
                    throw new IllegalArgumentException("Invalid frame length " + i);
                }
                int i2 = i + 4;
                if (this.buffer.capacity() < i2) {
                    ByteBuffer byteBufferAllocate = ByteBuffer.allocate(i2);
                    this.buffer = byteBufferAllocate;
                    byteBufferAllocate.order(ByteOrder.LITTLE_ENDIAN);
                    this.buffer.putInt(i);
                }
                this.buffer.limit(i2);
            }
            AltsFraming.copy(this.buffer, byteBuffer);
            if (!this.buffer.hasRemaining()) {
                this.buffer.flip();
                this.isComplete = true;
            }
            return this.isComplete;
        }

        private void reset() {
            this.buffer.clear();
            this.isComplete = false;
        }

        public ByteBuffer getRawFrame() {
            if (!this.isComplete) {
                return null;
            }
            ByteBuffer byteBufferDuplicate = this.buffer.duplicate();
            reset();
            return byteBufferDuplicate;
        }
    }
}
