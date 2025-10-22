package io.grpc.netty.shaded.io.netty.handler.codec.compression;

import com.google.common.base.Ascii;
import com.shimmerresearch.driver.ShimmerObject;
import io.grpc.netty.shaded.io.netty.buffer.ByteBuf;
import io.grpc.netty.shaded.io.netty.channel.ChannelFuture;
import io.grpc.netty.shaded.io.netty.channel.ChannelFutureListener;
import io.grpc.netty.shaded.io.netty.channel.ChannelHandlerContext;
import io.grpc.netty.shaded.io.netty.channel.ChannelPromise;
import io.grpc.netty.shaded.io.netty.channel.ChannelPromiseNotifier;
import io.grpc.netty.shaded.io.netty.util.concurrent.EventExecutor;
import io.grpc.netty.shaded.io.netty.util.concurrent.Future;
import io.grpc.netty.shaded.io.netty.util.concurrent.GenericFutureListener;
import io.grpc.netty.shaded.io.netty.util.internal.ObjectUtil;
import io.grpc.netty.shaded.io.netty.util.internal.PlatformDependent;

import java.util.concurrent.TimeUnit;
import java.util.zip.CRC32;
import java.util.zip.Deflater;

/* loaded from: classes3.dex */
public class JdkZlibEncoder extends ZlibEncoder {
    private static final byte[] gzipHeader = {Ascii.US, ShimmerObject.SET_CRC_COMMAND, 8, 0, 0, 0, 0, 0, 0, 0};
    private final CRC32 crc;
    private final Deflater deflater;
    private final ZlibWrapper wrapper;
    private volatile ChannelHandlerContext ctx;
    private volatile boolean finished;
    private boolean writeHeader;

    public JdkZlibEncoder() {
        this(6);
    }

    public JdkZlibEncoder(int i) {
        this(ZlibWrapper.ZLIB, i);
    }

    public JdkZlibEncoder(ZlibWrapper zlibWrapper) {
        this(zlibWrapper, 6);
    }

    public JdkZlibEncoder(ZlibWrapper zlibWrapper, int i) {
        this.crc = new CRC32();
        this.writeHeader = true;
        if (i < 0 || i > 9) {
            throw new IllegalArgumentException("compressionLevel: " + i + " (expected: 0-9)");
        }
        ObjectUtil.checkNotNull(zlibWrapper, "wrapper");
        if (zlibWrapper == ZlibWrapper.ZLIB_OR_NONE) {
            throw new IllegalArgumentException("wrapper '" + ZlibWrapper.ZLIB_OR_NONE + "' is not allowed for compression.");
        }
        this.wrapper = zlibWrapper;
        this.deflater = new Deflater(i, zlibWrapper != ZlibWrapper.ZLIB);
    }

    public JdkZlibEncoder(byte[] bArr) {
        this(6, bArr);
    }

    public JdkZlibEncoder(int i, byte[] bArr) {
        this.crc = new CRC32();
        this.writeHeader = true;
        if (i < 0 || i > 9) {
            throw new IllegalArgumentException("compressionLevel: " + i + " (expected: 0-9)");
        }
        ObjectUtil.checkNotNull(bArr, "dictionary");
        this.wrapper = ZlibWrapper.ZLIB;
        Deflater deflater = new Deflater(i);
        this.deflater = deflater;
        deflater.setDictionary(bArr);
    }

    @Override
    // io.grpc.netty.shaded.io.netty.channel.ChannelHandlerAdapter, io.grpc.netty.shaded.io.netty.channel.ChannelHandler
    public void handlerAdded(ChannelHandlerContext channelHandlerContext) throws Exception {
        this.ctx = channelHandlerContext;
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.compression.ZlibEncoder
    public boolean isClosed() {
        return this.finished;
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.compression.ZlibEncoder
    public ChannelFuture close() {
        return close(ctx().newPromise());
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.compression.ZlibEncoder
    public ChannelFuture close(final ChannelPromise channelPromise) {
        ChannelHandlerContext channelHandlerContextCtx = ctx();
        EventExecutor eventExecutorExecutor = channelHandlerContextCtx.executor();
        if (eventExecutorExecutor.inEventLoop()) {
            return finishEncode(channelHandlerContextCtx, channelPromise);
        }
        final ChannelPromise channelPromiseNewPromise = channelHandlerContextCtx.newPromise();
        eventExecutorExecutor.execute(new Runnable() { // from class: io.grpc.netty.shaded.io.netty.handler.codec.compression.JdkZlibEncoder.1
            @Override // java.lang.Runnable
            public void run() {
                JdkZlibEncoder jdkZlibEncoder = JdkZlibEncoder.this;
                jdkZlibEncoder.finishEncode(jdkZlibEncoder.ctx(), channelPromiseNewPromise).addListener((GenericFutureListener<? extends Future<? super Void>>) new ChannelPromiseNotifier(channelPromise));
            }
        });
        return channelPromiseNewPromise;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public ChannelHandlerContext ctx() {
        ChannelHandlerContext channelHandlerContext = this.ctx;
        if (channelHandlerContext != null) {
            return channelHandlerContext;
        }
        throw new IllegalStateException("not added to a pipeline");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // io.grpc.netty.shaded.io.netty.handler.codec.MessageToByteEncoder
    public void encode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, ByteBuf byteBuf2) throws Exception {
        byte[] bArrArray;
        int iArrayOffset;
        if (this.finished) {
            byteBuf2.writeBytes(byteBuf);
            return;
        }
        int i = byteBuf.readableBytes();
        if (i == 0) {
            return;
        }
        if (byteBuf.hasArray()) {
            bArrArray = byteBuf.array();
            iArrayOffset = byteBuf.arrayOffset() + byteBuf.readerIndex();
            byteBuf.skipBytes(i);
        } else {
            bArrArray = new byte[i];
            byteBuf.readBytes(bArrArray);
            iArrayOffset = 0;
        }
        if (this.writeHeader) {
            this.writeHeader = false;
            if (this.wrapper == ZlibWrapper.GZIP) {
                byteBuf2.writeBytes(gzipHeader);
            }
        }
        if (this.wrapper == ZlibWrapper.GZIP) {
            this.crc.update(bArrArray, iArrayOffset, i);
        }
        this.deflater.setInput(bArrArray, iArrayOffset, i);
        while (true) {
            deflate(byteBuf2);
            if (this.deflater.needsInput()) {
                return;
            }
            if (!byteBuf2.isWritable()) {
                byteBuf2.ensureWritable(byteBuf2.writerIndex());
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // io.grpc.netty.shaded.io.netty.handler.codec.MessageToByteEncoder
    public final ByteBuf allocateBuffer(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, boolean z) throws Exception {
        int iCeil = (int) Math.ceil(byteBuf.readableBytes() * 1.001d);
        int length = iCeil + 12;
        if (this.writeHeader) {
            int i = AnonymousClass4.$SwitchMap$io$netty$handler$codec$compression$ZlibWrapper[this.wrapper.ordinal()];
            if (i == 1) {
                length += gzipHeader.length;
            } else if (i == 2) {
                length = iCeil + 14;
            }
        }
        return channelHandlerContext.alloc().heapBuffer(length);
    }

    @Override
    // io.grpc.netty.shaded.io.netty.channel.ChannelOutboundHandlerAdapter, io.grpc.netty.shaded.io.netty.channel.ChannelOutboundHandler
    public void close(final ChannelHandlerContext channelHandlerContext, final ChannelPromise channelPromise) throws Exception {
        ChannelFuture channelFutureFinishEncode = finishEncode(channelHandlerContext, channelHandlerContext.newPromise());
        channelFutureFinishEncode.addListener((GenericFutureListener<? extends Future<? super Void>>) new ChannelFutureListener() { // from class: io.grpc.netty.shaded.io.netty.handler.codec.compression.JdkZlibEncoder.2
            @Override // io.grpc.netty.shaded.io.netty.util.concurrent.GenericFutureListener
            public void operationComplete(ChannelFuture channelFuture) throws Exception {
                channelHandlerContext.close(channelPromise);
            }
        });
        if (channelFutureFinishEncode.isDone()) {
            return;
        }
        channelHandlerContext.executor().schedule(new Runnable() { // from class: io.grpc.netty.shaded.io.netty.handler.codec.compression.JdkZlibEncoder.3
            @Override // java.lang.Runnable
            public void run() {
                channelHandlerContext.close(channelPromise);
            }
        }, 10L, TimeUnit.SECONDS);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public ChannelFuture finishEncode(ChannelHandlerContext channelHandlerContext, ChannelPromise channelPromise) {
        if (this.finished) {
            channelPromise.setSuccess();
            return channelPromise;
        }
        this.finished = true;
        ByteBuf byteBufHeapBuffer = channelHandlerContext.alloc().heapBuffer();
        if (this.writeHeader && this.wrapper == ZlibWrapper.GZIP) {
            this.writeHeader = false;
            byteBufHeapBuffer.writeBytes(gzipHeader);
        }
        this.deflater.finish();
        while (!this.deflater.finished()) {
            deflate(byteBufHeapBuffer);
            if (!byteBufHeapBuffer.isWritable()) {
                channelHandlerContext.write(byteBufHeapBuffer);
                byteBufHeapBuffer = channelHandlerContext.alloc().heapBuffer();
            }
        }
        if (this.wrapper == ZlibWrapper.GZIP) {
            int value = (int) this.crc.getValue();
            int totalIn = this.deflater.getTotalIn();
            byteBufHeapBuffer.writeByte(value);
            byteBufHeapBuffer.writeByte(value >>> 8);
            byteBufHeapBuffer.writeByte(value >>> 16);
            byteBufHeapBuffer.writeByte(value >>> 24);
            byteBufHeapBuffer.writeByte(totalIn);
            byteBufHeapBuffer.writeByte(totalIn >>> 8);
            byteBufHeapBuffer.writeByte(totalIn >>> 16);
            byteBufHeapBuffer.writeByte(totalIn >>> 24);
        }
        this.deflater.end();
        return channelHandlerContext.writeAndFlush(byteBufHeapBuffer, channelPromise);
    }

    private void deflate(ByteBuf byteBuf) {
        int iDeflate;
        if (PlatformDependent.javaVersion() < 7) {
            deflateJdk6(byteBuf);
        }
        do {
            int iWriterIndex = byteBuf.writerIndex();
            iDeflate = this.deflater.deflate(byteBuf.array(), byteBuf.arrayOffset() + iWriterIndex, byteBuf.writableBytes(), 2);
            byteBuf.writerIndex(iWriterIndex + iDeflate);
        } while (iDeflate > 0);
    }

    private void deflateJdk6(ByteBuf byteBuf) {
        int iDeflate;
        do {
            int iWriterIndex = byteBuf.writerIndex();
            iDeflate = this.deflater.deflate(byteBuf.array(), byteBuf.arrayOffset() + iWriterIndex, byteBuf.writableBytes());
            byteBuf.writerIndex(iWriterIndex + iDeflate);
        } while (iDeflate > 0);
    }

    /* renamed from: io.grpc.netty.shaded.io.netty.handler.codec.compression.JdkZlibEncoder$4, reason: invalid class name */
    static /* synthetic */ class AnonymousClass4 {
        static final /* synthetic */ int[] $SwitchMap$io$netty$handler$codec$compression$ZlibWrapper;

        static {
            int[] iArr = new int[ZlibWrapper.values().length];
            $SwitchMap$io$netty$handler$codec$compression$ZlibWrapper = iArr;
            try {
                iArr[ZlibWrapper.GZIP.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$io$netty$handler$codec$compression$ZlibWrapper[ZlibWrapper.ZLIB.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }
}
