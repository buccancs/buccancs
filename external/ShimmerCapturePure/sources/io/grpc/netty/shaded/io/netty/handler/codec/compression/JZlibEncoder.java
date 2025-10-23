package io.grpc.netty.shaded.io.netty.handler.codec.compression;

import com.jcraft.jzlib.Deflater;
import com.jcraft.jzlib.JZlib;
import io.grpc.netty.shaded.io.netty.buffer.ByteBuf;
import io.grpc.netty.shaded.io.netty.buffer.Unpooled;
import io.grpc.netty.shaded.io.netty.channel.ChannelFuture;
import io.grpc.netty.shaded.io.netty.channel.ChannelFutureListener;
import io.grpc.netty.shaded.io.netty.channel.ChannelHandlerContext;
import io.grpc.netty.shaded.io.netty.channel.ChannelPromise;
import io.grpc.netty.shaded.io.netty.channel.ChannelPromiseNotifier;
import io.grpc.netty.shaded.io.netty.util.concurrent.EventExecutor;
import io.grpc.netty.shaded.io.netty.util.concurrent.Future;
import io.grpc.netty.shaded.io.netty.util.concurrent.GenericFutureListener;
import io.grpc.netty.shaded.io.netty.util.internal.EmptyArrays;
import io.grpc.netty.shaded.io.netty.util.internal.ObjectUtil;

import java.util.concurrent.TimeUnit;

/* loaded from: classes3.dex */
public class JZlibEncoder extends ZlibEncoder {
    private final int wrapperOverhead;
    private final Deflater z;
    private volatile ChannelHandlerContext ctx;
    private volatile boolean finished;

    public JZlibEncoder() {
        this(6);
    }

    public JZlibEncoder(int i) {
        this(ZlibWrapper.ZLIB, i);
    }

    public JZlibEncoder(ZlibWrapper zlibWrapper) {
        this(zlibWrapper, 6);
    }

    public JZlibEncoder(ZlibWrapper zlibWrapper, int i) {
        this(zlibWrapper, i, 15, 8);
    }

    public JZlibEncoder(ZlibWrapper zlibWrapper, int i, int i2, int i3) {
        Deflater deflater = new Deflater();
        this.z = deflater;
        if (i < 0 || i > 9) {
            throw new IllegalArgumentException("compressionLevel: " + i + " (expected: 0-9)");
        }
        if (i2 < 9 || i2 > 15) {
            throw new IllegalArgumentException("windowBits: " + i2 + " (expected: 9-15)");
        }
        if (i3 < 1 || i3 > 9) {
            throw new IllegalArgumentException("memLevel: " + i3 + " (expected: 1-9)");
        }
        ObjectUtil.checkNotNull(zlibWrapper, "wrapper");
        if (zlibWrapper == ZlibWrapper.ZLIB_OR_NONE) {
            throw new IllegalArgumentException("wrapper '" + ZlibWrapper.ZLIB_OR_NONE + "' is not allowed for compression.");
        }
        int iInit = deflater.init(i, i2, i3, ZlibUtil.convertWrapperType(zlibWrapper));
        if (iInit != 0) {
            ZlibUtil.fail(deflater, "initialization failure", iInit);
        }
        this.wrapperOverhead = ZlibUtil.wrapperOverhead(zlibWrapper);
    }

    public JZlibEncoder(byte[] bArr) {
        this(6, bArr);
    }

    public JZlibEncoder(int i, byte[] bArr) {
        this(i, 15, 8, bArr);
    }

    public JZlibEncoder(int i, int i2, int i3, byte[] bArr) {
        Deflater deflater = new Deflater();
        this.z = deflater;
        if (i < 0 || i > 9) {
            throw new IllegalArgumentException("compressionLevel: " + i + " (expected: 0-9)");
        }
        if (i2 < 9 || i2 > 15) {
            throw new IllegalArgumentException("windowBits: " + i2 + " (expected: 9-15)");
        }
        if (i3 < 1 || i3 > 9) {
            throw new IllegalArgumentException("memLevel: " + i3 + " (expected: 1-9)");
        }
        ObjectUtil.checkNotNull(bArr, "dictionary");
        int iDeflateInit = deflater.deflateInit(i, i2, i3, JZlib.W_ZLIB);
        if (iDeflateInit != 0) {
            ZlibUtil.fail(deflater, "initialization failure", iDeflateInit);
        } else {
            int iDeflateSetDictionary = deflater.deflateSetDictionary(bArr, bArr.length);
            if (iDeflateSetDictionary != 0) {
                ZlibUtil.fail(deflater, "failed to set the dictionary", iDeflateSetDictionary);
            }
        }
        this.wrapperOverhead = ZlibUtil.wrapperOverhead(ZlibWrapper.ZLIB);
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
        return close(ctx().channel().newPromise());
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.compression.ZlibEncoder
    public ChannelFuture close(final ChannelPromise channelPromise) {
        ChannelHandlerContext channelHandlerContextCtx = ctx();
        EventExecutor eventExecutorExecutor = channelHandlerContextCtx.executor();
        if (eventExecutorExecutor.inEventLoop()) {
            return finishEncode(channelHandlerContextCtx, channelPromise);
        }
        final ChannelPromise channelPromiseNewPromise = channelHandlerContextCtx.newPromise();
        eventExecutorExecutor.execute(new Runnable() { // from class: io.grpc.netty.shaded.io.netty.handler.codec.compression.JZlibEncoder.1
            @Override // java.lang.Runnable
            public void run() {
                JZlibEncoder jZlibEncoder = JZlibEncoder.this;
                jZlibEncoder.finishEncode(jZlibEncoder.ctx(), channelPromiseNewPromise).addListener((GenericFutureListener<? extends Future<? super Void>>) new ChannelPromiseNotifier(channelPromise));
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
        if (this.finished) {
            byteBuf2.writeBytes(byteBuf);
            return;
        }
        int i = byteBuf.readableBytes();
        if (i == 0) {
            return;
        }
        try {
            boolean zHasArray = byteBuf.hasArray();
            this.z.avail_in = i;
            if (zHasArray) {
                this.z.next_in = byteBuf.array();
                this.z.next_in_index = byteBuf.arrayOffset() + byteBuf.readerIndex();
            } else {
                byte[] bArr = new byte[i];
                byteBuf.getBytes(byteBuf.readerIndex(), bArr);
                this.z.next_in = bArr;
                this.z.next_in_index = 0;
            }
            int i2 = this.z.next_in_index;
            int iCeil = ((int) Math.ceil(i * 1.001d)) + 12 + this.wrapperOverhead;
            byteBuf2.ensureWritable(iCeil);
            this.z.avail_out = iCeil;
            this.z.next_out = byteBuf2.array();
            this.z.next_out_index = byteBuf2.arrayOffset() + byteBuf2.writerIndex();
            int i3 = this.z.next_out_index;
            try {
                int iDeflate = this.z.deflate(2);
                if (iDeflate != 0) {
                    ZlibUtil.fail(this.z, "compression failure", iDeflate);
                }
                int i4 = this.z.next_out_index - i3;
                if (i4 > 0) {
                    byteBuf2.writerIndex(byteBuf2.writerIndex() + i4);
                }
            } finally {
                byteBuf.skipBytes(this.z.next_in_index - i2);
            }
        } finally {
            this.z.next_in = null;
            this.z.next_out = null;
        }
    }

    @Override
    // io.grpc.netty.shaded.io.netty.channel.ChannelOutboundHandlerAdapter, io.grpc.netty.shaded.io.netty.channel.ChannelOutboundHandler
    public void close(final ChannelHandlerContext channelHandlerContext, final ChannelPromise channelPromise) {
        ChannelFuture channelFutureFinishEncode = finishEncode(channelHandlerContext, channelHandlerContext.newPromise());
        channelFutureFinishEncode.addListener((GenericFutureListener<? extends Future<? super Void>>) new ChannelFutureListener() { // from class: io.grpc.netty.shaded.io.netty.handler.codec.compression.JZlibEncoder.2
            @Override // io.grpc.netty.shaded.io.netty.util.concurrent.GenericFutureListener
            public void operationComplete(ChannelFuture channelFuture) throws Exception {
                channelHandlerContext.close(channelPromise);
            }
        });
        if (channelFutureFinishEncode.isDone()) {
            return;
        }
        channelHandlerContext.executor().schedule(new Runnable() { // from class: io.grpc.netty.shaded.io.netty.handler.codec.compression.JZlibEncoder.3
            @Override // java.lang.Runnable
            public void run() {
                channelHandlerContext.close(channelPromise);
            }
        }, 10L, TimeUnit.SECONDS);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public ChannelFuture finishEncode(ChannelHandlerContext channelHandlerContext, ChannelPromise channelPromise) {
        ByteBuf byteBufWrappedBuffer;
        if (this.finished) {
            channelPromise.setSuccess();
            return channelPromise;
        }
        this.finished = true;
        try {
            this.z.next_in = EmptyArrays.EMPTY_BYTES;
            this.z.next_in_index = 0;
            this.z.avail_in = 0;
            byte[] bArr = new byte[32];
            this.z.next_out = bArr;
            this.z.next_out_index = 0;
            this.z.avail_out = 32;
            int iDeflate = this.z.deflate(4);
            if (iDeflate != 0 && iDeflate != 1) {
                channelPromise.setFailure((Throwable) ZlibUtil.deflaterException(this.z, "compression failure", iDeflate));
                return channelPromise;
            }
            if (this.z.next_out_index != 0) {
                byteBufWrappedBuffer = Unpooled.wrappedBuffer(bArr, 0, this.z.next_out_index);
            } else {
                byteBufWrappedBuffer = Unpooled.EMPTY_BUFFER;
            }
            this.z.deflateEnd();
            this.z.next_in = null;
            this.z.next_out = null;
            return channelHandlerContext.writeAndFlush(byteBufWrappedBuffer, channelPromise);
        } finally {
            this.z.deflateEnd();
            this.z.next_in = null;
            this.z.next_out = null;
        }
    }
}
