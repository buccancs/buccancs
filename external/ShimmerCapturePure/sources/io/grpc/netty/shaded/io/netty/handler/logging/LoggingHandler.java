package io.grpc.netty.shaded.io.netty.handler.logging;

import com.google.api.client.http.HttpMethods;
import io.grpc.netty.shaded.io.netty.buffer.ByteBuf;
import io.grpc.netty.shaded.io.netty.buffer.ByteBufHolder;
import io.grpc.netty.shaded.io.netty.buffer.ByteBufUtil;
import io.grpc.netty.shaded.io.netty.channel.ChannelDuplexHandler;
import io.grpc.netty.shaded.io.netty.channel.ChannelHandler;
import io.grpc.netty.shaded.io.netty.channel.ChannelHandlerContext;
import io.grpc.netty.shaded.io.netty.channel.ChannelPromise;
import io.grpc.netty.shaded.io.netty.util.internal.ObjectUtil;
import io.grpc.netty.shaded.io.netty.util.internal.StringUtil;
import io.grpc.netty.shaded.io.netty.util.internal.logging.InternalLogLevel;
import io.grpc.netty.shaded.io.netty.util.internal.logging.InternalLogger;
import io.grpc.netty.shaded.io.netty.util.internal.logging.InternalLoggerFactory;

import java.net.SocketAddress;

@ChannelHandler.Sharable
/* loaded from: classes3.dex */
public class LoggingHandler extends ChannelDuplexHandler {
    private static final LogLevel DEFAULT_LEVEL = LogLevel.DEBUG;
    protected final InternalLogLevel internalLevel;
    protected final InternalLogger logger;
    private final ByteBufFormat byteBufFormat;
    private final LogLevel level;

    public LoggingHandler() {
        this(DEFAULT_LEVEL);
    }

    public LoggingHandler(LogLevel logLevel) {
        this(logLevel, ByteBufFormat.HEX_DUMP);
    }

    public LoggingHandler(LogLevel logLevel, ByteBufFormat byteBufFormat) {
        this.level = (LogLevel) ObjectUtil.checkNotNull(logLevel, "level");
        this.byteBufFormat = (ByteBufFormat) ObjectUtil.checkNotNull(byteBufFormat, "byteBufFormat");
        this.logger = InternalLoggerFactory.getInstance(getClass());
        this.internalLevel = logLevel.toInternalLevel();
    }

    public LoggingHandler(Class<?> cls) {
        this(cls, DEFAULT_LEVEL);
    }

    public LoggingHandler(Class<?> cls, LogLevel logLevel) {
        this(cls, logLevel, ByteBufFormat.HEX_DUMP);
    }

    public LoggingHandler(Class<?> cls, LogLevel logLevel, ByteBufFormat byteBufFormat) {
        ObjectUtil.checkNotNull(cls, "clazz");
        this.level = (LogLevel) ObjectUtil.checkNotNull(logLevel, "level");
        this.byteBufFormat = (ByteBufFormat) ObjectUtil.checkNotNull(byteBufFormat, "byteBufFormat");
        this.logger = InternalLoggerFactory.getInstance(cls);
        this.internalLevel = logLevel.toInternalLevel();
    }

    public LoggingHandler(String str) {
        this(str, DEFAULT_LEVEL);
    }

    public LoggingHandler(String str, LogLevel logLevel) {
        this(str, logLevel, ByteBufFormat.HEX_DUMP);
    }

    public LoggingHandler(String str, LogLevel logLevel, ByteBufFormat byteBufFormat) {
        ObjectUtil.checkNotNull(str, "name");
        this.level = (LogLevel) ObjectUtil.checkNotNull(logLevel, "level");
        this.byteBufFormat = (ByteBufFormat) ObjectUtil.checkNotNull(byteBufFormat, "byteBufFormat");
        this.logger = InternalLoggerFactory.getInstance(str);
        this.internalLevel = logLevel.toInternalLevel();
    }

    private static String formatSimple(ChannelHandlerContext channelHandlerContext, String str, Object obj) {
        String string = channelHandlerContext.channel().toString();
        String strValueOf = String.valueOf(obj);
        StringBuilder sb = new StringBuilder(string.length() + 1 + str.length() + 2 + strValueOf.length());
        sb.append(string);
        sb.append(' ');
        sb.append(str);
        sb.append(": ");
        sb.append(strValueOf);
        return sb.toString();
    }

    public ByteBufFormat byteBufFormat() {
        return this.byteBufFormat;
    }

    public LogLevel level() {
        return this.level;
    }

    @Override
    // io.grpc.netty.shaded.io.netty.channel.ChannelInboundHandlerAdapter, io.grpc.netty.shaded.io.netty.channel.ChannelInboundHandler
    public void channelRegistered(ChannelHandlerContext channelHandlerContext) throws Exception {
        if (this.logger.isEnabled(this.internalLevel)) {
            this.logger.log(this.internalLevel, format(channelHandlerContext, "REGISTERED"));
        }
        channelHandlerContext.fireChannelRegistered();
    }

    @Override
    // io.grpc.netty.shaded.io.netty.channel.ChannelInboundHandlerAdapter, io.grpc.netty.shaded.io.netty.channel.ChannelInboundHandler
    public void channelUnregistered(ChannelHandlerContext channelHandlerContext) throws Exception {
        if (this.logger.isEnabled(this.internalLevel)) {
            this.logger.log(this.internalLevel, format(channelHandlerContext, "UNREGISTERED"));
        }
        channelHandlerContext.fireChannelUnregistered();
    }

    @Override
    // io.grpc.netty.shaded.io.netty.channel.ChannelInboundHandlerAdapter, io.grpc.netty.shaded.io.netty.channel.ChannelInboundHandler
    public void channelActive(ChannelHandlerContext channelHandlerContext) throws Exception {
        if (this.logger.isEnabled(this.internalLevel)) {
            this.logger.log(this.internalLevel, format(channelHandlerContext, "ACTIVE"));
        }
        channelHandlerContext.fireChannelActive();
    }

    @Override
    // io.grpc.netty.shaded.io.netty.channel.ChannelInboundHandlerAdapter, io.grpc.netty.shaded.io.netty.channel.ChannelInboundHandler
    public void channelInactive(ChannelHandlerContext channelHandlerContext) throws Exception {
        if (this.logger.isEnabled(this.internalLevel)) {
            this.logger.log(this.internalLevel, format(channelHandlerContext, "INACTIVE"));
        }
        channelHandlerContext.fireChannelInactive();
    }

    @Override
    // io.grpc.netty.shaded.io.netty.channel.ChannelInboundHandlerAdapter, io.grpc.netty.shaded.io.netty.channel.ChannelHandlerAdapter, io.grpc.netty.shaded.io.netty.channel.ChannelHandler, io.grpc.netty.shaded.io.netty.channel.ChannelInboundHandler
    public void exceptionCaught(ChannelHandlerContext channelHandlerContext, Throwable th) throws Exception {
        if (this.logger.isEnabled(this.internalLevel)) {
            this.logger.log(this.internalLevel, format(channelHandlerContext, "EXCEPTION", th), th);
        }
        channelHandlerContext.fireExceptionCaught(th);
    }

    @Override
    // io.grpc.netty.shaded.io.netty.channel.ChannelInboundHandlerAdapter, io.grpc.netty.shaded.io.netty.channel.ChannelInboundHandler
    public void userEventTriggered(ChannelHandlerContext channelHandlerContext, Object obj) throws Exception {
        if (this.logger.isEnabled(this.internalLevel)) {
            this.logger.log(this.internalLevel, format(channelHandlerContext, "USER_EVENT", obj));
        }
        channelHandlerContext.fireUserEventTriggered(obj);
    }

    @Override
    // io.grpc.netty.shaded.io.netty.channel.ChannelDuplexHandler, io.grpc.netty.shaded.io.netty.channel.ChannelOutboundHandler
    public void bind(ChannelHandlerContext channelHandlerContext, SocketAddress socketAddress, ChannelPromise channelPromise) throws Exception {
        if (this.logger.isEnabled(this.internalLevel)) {
            this.logger.log(this.internalLevel, format(channelHandlerContext, "BIND", socketAddress));
        }
        channelHandlerContext.bind(socketAddress, channelPromise);
    }

    @Override
    // io.grpc.netty.shaded.io.netty.channel.ChannelDuplexHandler, io.grpc.netty.shaded.io.netty.channel.ChannelOutboundHandler
    public void connect(ChannelHandlerContext channelHandlerContext, SocketAddress socketAddress, SocketAddress socketAddress2, ChannelPromise channelPromise) throws Exception {
        if (this.logger.isEnabled(this.internalLevel)) {
            this.logger.log(this.internalLevel, format(channelHandlerContext, HttpMethods.CONNECT, socketAddress, socketAddress2));
        }
        channelHandlerContext.connect(socketAddress, socketAddress2, channelPromise);
    }

    @Override
    // io.grpc.netty.shaded.io.netty.channel.ChannelDuplexHandler, io.grpc.netty.shaded.io.netty.channel.ChannelOutboundHandler
    public void disconnect(ChannelHandlerContext channelHandlerContext, ChannelPromise channelPromise) throws Exception {
        if (this.logger.isEnabled(this.internalLevel)) {
            this.logger.log(this.internalLevel, format(channelHandlerContext, "DISCONNECT"));
        }
        channelHandlerContext.disconnect(channelPromise);
    }

    @Override
    // io.grpc.netty.shaded.io.netty.channel.ChannelDuplexHandler, io.grpc.netty.shaded.io.netty.channel.ChannelOutboundHandler
    public void close(ChannelHandlerContext channelHandlerContext, ChannelPromise channelPromise) throws Exception {
        if (this.logger.isEnabled(this.internalLevel)) {
            this.logger.log(this.internalLevel, format(channelHandlerContext, "CLOSE"));
        }
        channelHandlerContext.close(channelPromise);
    }

    @Override
    // io.grpc.netty.shaded.io.netty.channel.ChannelDuplexHandler, io.grpc.netty.shaded.io.netty.channel.ChannelOutboundHandler
    public void deregister(ChannelHandlerContext channelHandlerContext, ChannelPromise channelPromise) throws Exception {
        if (this.logger.isEnabled(this.internalLevel)) {
            this.logger.log(this.internalLevel, format(channelHandlerContext, "DEREGISTER"));
        }
        channelHandlerContext.deregister(channelPromise);
    }

    @Override
    // io.grpc.netty.shaded.io.netty.channel.ChannelInboundHandlerAdapter, io.grpc.netty.shaded.io.netty.channel.ChannelInboundHandler
    public void channelReadComplete(ChannelHandlerContext channelHandlerContext) throws Exception {
        if (this.logger.isEnabled(this.internalLevel)) {
            this.logger.log(this.internalLevel, format(channelHandlerContext, "READ COMPLETE"));
        }
        channelHandlerContext.fireChannelReadComplete();
    }

    @Override
    // io.grpc.netty.shaded.io.netty.channel.ChannelInboundHandlerAdapter, io.grpc.netty.shaded.io.netty.channel.ChannelInboundHandler
    public void channelRead(ChannelHandlerContext channelHandlerContext, Object obj) throws Exception {
        if (this.logger.isEnabled(this.internalLevel)) {
            this.logger.log(this.internalLevel, format(channelHandlerContext, "READ", obj));
        }
        channelHandlerContext.fireChannelRead(obj);
    }

    @Override
    // io.grpc.netty.shaded.io.netty.channel.ChannelDuplexHandler, io.grpc.netty.shaded.io.netty.channel.ChannelOutboundHandler
    public void write(ChannelHandlerContext channelHandlerContext, Object obj, ChannelPromise channelPromise) throws Exception {
        if (this.logger.isEnabled(this.internalLevel)) {
            this.logger.log(this.internalLevel, format(channelHandlerContext, "WRITE", obj));
        }
        channelHandlerContext.write(obj, channelPromise);
    }

    @Override
    // io.grpc.netty.shaded.io.netty.channel.ChannelInboundHandlerAdapter, io.grpc.netty.shaded.io.netty.channel.ChannelInboundHandler
    public void channelWritabilityChanged(ChannelHandlerContext channelHandlerContext) throws Exception {
        if (this.logger.isEnabled(this.internalLevel)) {
            this.logger.log(this.internalLevel, format(channelHandlerContext, "WRITABILITY CHANGED"));
        }
        channelHandlerContext.fireChannelWritabilityChanged();
    }

    @Override
    // io.grpc.netty.shaded.io.netty.channel.ChannelDuplexHandler, io.grpc.netty.shaded.io.netty.channel.ChannelOutboundHandler
    public void flush(ChannelHandlerContext channelHandlerContext) throws Exception {
        if (this.logger.isEnabled(this.internalLevel)) {
            this.logger.log(this.internalLevel, format(channelHandlerContext, "FLUSH"));
        }
        channelHandlerContext.flush();
    }

    protected String format(ChannelHandlerContext channelHandlerContext, String str) {
        String string = channelHandlerContext.channel().toString();
        StringBuilder sb = new StringBuilder(string.length() + 1 + str.length());
        sb.append(string);
        sb.append(' ');
        sb.append(str);
        return sb.toString();
    }

    protected String format(ChannelHandlerContext channelHandlerContext, String str, Object obj) {
        if (obj instanceof ByteBuf) {
            return formatByteBuf(channelHandlerContext, str, (ByteBuf) obj);
        }
        if (obj instanceof ByteBufHolder) {
            return formatByteBufHolder(channelHandlerContext, str, (ByteBufHolder) obj);
        }
        return formatSimple(channelHandlerContext, str, obj);
    }

    protected String format(ChannelHandlerContext channelHandlerContext, String str, Object obj, Object obj2) {
        if (obj2 == null) {
            return formatSimple(channelHandlerContext, str, obj);
        }
        String string = channelHandlerContext.channel().toString();
        String strValueOf = String.valueOf(obj);
        String string2 = obj2.toString();
        StringBuilder sb = new StringBuilder(string.length() + 1 + str.length() + 2 + strValueOf.length() + 2 + string2.length());
        sb.append(string);
        sb.append(' ');
        sb.append(str);
        sb.append(": ");
        sb.append(strValueOf);
        sb.append(", ");
        sb.append(string2);
        return sb.toString();
    }

    private String formatByteBuf(ChannelHandlerContext channelHandlerContext, String str, ByteBuf byteBuf) {
        String string = channelHandlerContext.channel().toString();
        int i = byteBuf.readableBytes();
        if (i == 0) {
            StringBuilder sb = new StringBuilder(string.length() + 1 + str.length() + 4);
            sb.append(string);
            sb.append(' ');
            sb.append(str);
            sb.append(": 0B");
            return sb.toString();
        }
        int length = string.length() + 1 + str.length() + 13;
        if (this.byteBufFormat == ByteBufFormat.HEX_DUMP) {
            length += (((i / 16) + (i % 15 == 0 ? 0 : 1) + 4) * 80) + 2;
        }
        StringBuilder sb2 = new StringBuilder(length);
        sb2.append(string);
        sb2.append(' ');
        sb2.append(str);
        sb2.append(": ");
        sb2.append(i);
        sb2.append('B');
        if (this.byteBufFormat == ByteBufFormat.HEX_DUMP) {
            sb2.append(StringUtil.NEWLINE);
            ByteBufUtil.appendPrettyHexDump(sb2, byteBuf);
        }
        return sb2.toString();
    }

    private String formatByteBufHolder(ChannelHandlerContext channelHandlerContext, String str, ByteBufHolder byteBufHolder) {
        String string = channelHandlerContext.channel().toString();
        String string2 = byteBufHolder.toString();
        ByteBuf byteBufContent = byteBufHolder.content();
        int i = byteBufContent.readableBytes();
        if (i == 0) {
            StringBuilder sb = new StringBuilder(string.length() + 1 + str.length() + 2 + string2.length() + 4);
            sb.append(string);
            sb.append(' ');
            sb.append(str);
            sb.append(", ");
            sb.append(string2);
            sb.append(", 0B");
            return sb.toString();
        }
        int length = string.length() + 1 + str.length() + 2 + string2.length() + 13;
        if (this.byteBufFormat == ByteBufFormat.HEX_DUMP) {
            length += (((i / 16) + (i % 15 == 0 ? 0 : 1) + 4) * 80) + 2;
        }
        StringBuilder sb2 = new StringBuilder(length);
        sb2.append(string);
        sb2.append(' ');
        sb2.append(str);
        sb2.append(": ");
        sb2.append(string2);
        sb2.append(", ");
        sb2.append(i);
        sb2.append('B');
        if (this.byteBufFormat == ByteBufFormat.HEX_DUMP) {
            sb2.append(StringUtil.NEWLINE);
            ByteBufUtil.appendPrettyHexDump(sb2, byteBufContent);
        }
        return sb2.toString();
    }
}
