package io.grpc.alts.internal;

import com.google.common.base.Preconditions;
import io.grpc.netty.shaded.io.netty.buffer.ByteBuf;
import io.grpc.netty.shaded.io.netty.buffer.ByteBufAllocator;

import java.nio.ByteBuffer;
import java.security.GeneralSecurityException;

/* loaded from: classes2.dex */
public final class NettyTsiHandshaker {
    private final TsiHandshaker internalHandshaker;
    private BufUnwrapper unwrapper = new BufUnwrapper();

    public NettyTsiHandshaker(TsiHandshaker tsiHandshaker) {
        this.internalHandshaker = (TsiHandshaker) Preconditions.checkNotNull(tsiHandshaker);
    }

    private static /* synthetic */ void $closeResource(Throwable th, AutoCloseable autoCloseable) throws Exception {
        if (th == null) {
            autoCloseable.close();
            return;
        }
        try {
            autoCloseable.close();
        } catch (Throwable th2) {
            th.addSuppressed(th2);
        }
    }

    void getBytesToSendToPeer(ByteBuf byteBuf) throws Exception {
        Preconditions.checkState(this.unwrapper != null, "protector already created");
        BufUnwrapper bufUnwrapper = this.unwrapper;
        try {
            int iPosition = 0;
            for (ByteBuffer byteBuffer : bufUnwrapper.writableNioBuffers(byteBuf)) {
                if (byteBuffer.hasRemaining()) {
                    int iPosition2 = byteBuffer.position();
                    this.internalHandshaker.getBytesToSendToPeer(byteBuffer);
                    iPosition += byteBuffer.position() - iPosition2;
                    if (byteBuffer.position() == iPosition2) {
                        break;
                    }
                }
            }
            byteBuf.writerIndex(byteBuf.writerIndex() + iPosition);
            if (bufUnwrapper != null) {
                $closeResource(null, bufUnwrapper);
            }
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                if (bufUnwrapper != null) {
                    $closeResource(th, bufUnwrapper);
                }
                throw th2;
            }
        }
    }

    boolean processBytesFromPeer(ByteBuf byteBuf) throws Exception {
        Preconditions.checkState(this.unwrapper != null, "protector already created");
        BufUnwrapper bufUnwrapper = this.unwrapper;
        try {
            int iPosition = 0;
            boolean z = false;
            for (ByteBuffer byteBuffer : bufUnwrapper.readableNioBuffers(byteBuf)) {
                if (byteBuffer.hasRemaining()) {
                    int iPosition2 = byteBuffer.position();
                    boolean zProcessBytesFromPeer = this.internalHandshaker.processBytesFromPeer(byteBuffer);
                    iPosition += byteBuffer.position() - iPosition2;
                    z = zProcessBytesFromPeer;
                    if (zProcessBytesFromPeer) {
                        break;
                    }
                }
            }
            byteBuf.readerIndex(byteBuf.readerIndex() + iPosition);
            if (bufUnwrapper != null) {
                $closeResource(null, bufUnwrapper);
            }
            return z;
        } finally {
        }
    }

    boolean isInProgress() {
        return this.internalHandshaker.isInProgress();
    }

    TsiPeer extractPeer() throws GeneralSecurityException {
        Preconditions.checkState(!this.internalHandshaker.isInProgress());
        return this.internalHandshaker.extractPeer();
    }

    Object extractPeerObject() throws GeneralSecurityException {
        Preconditions.checkState(!this.internalHandshaker.isInProgress());
        return this.internalHandshaker.extractPeerObject();
    }

    TsiFrameProtector createFrameProtector(int i, ByteBufAllocator byteBufAllocator) {
        this.unwrapper = null;
        return this.internalHandshaker.createFrameProtector(i, byteBufAllocator);
    }

    TsiFrameProtector createFrameProtector(ByteBufAllocator byteBufAllocator) {
        this.unwrapper = null;
        return this.internalHandshaker.createFrameProtector(byteBufAllocator);
    }

    void close() {
        this.internalHandshaker.close();
    }
}
