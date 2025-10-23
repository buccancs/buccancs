package io.grpc.netty;

import com.google.api.client.http.HttpMethods;
import com.google.common.base.Preconditions;
import com.google.common.collect.UnmodifiableIterator;
import io.grpc.InternalChannelz;
import io.grpc.InternalMetadata;
import io.grpc.Metadata;
import io.grpc.Status;
import io.grpc.internal.GrpcUtil;
import io.grpc.internal.SharedResourceHolder;
import io.grpc.internal.TransportFrameUtil;
import io.grpc.netty.GrpcHttp2HeadersUtils;
import io.grpc.netty.NettySocketSupport;
import io.netty.buffer.ByteBufAllocator;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.Channel;
import io.netty.channel.ChannelConfig;
import io.netty.channel.ChannelFactory;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.ReflectiveChannelFactory;
import io.netty.channel.ServerChannel;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http2.Http2Exception;
import io.netty.handler.codec.http2.Http2Headers;
import io.netty.util.AsciiString;
import io.netty.util.CharsetUtil;
import io.netty.util.NettyRuntime;
import io.netty.util.concurrent.DefaultThreadFactory;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.UnresolvedAddressException;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.CheckReturnValue;
import javax.annotation.Nullable;

import org.apache.http.HttpHost;

/* loaded from: classes2.dex */
class Utils {
    public static final AsciiString CONTENT_TYPE_GRPC;
    public static final AsciiString CONTENT_TYPE_HEADER;
    public static final SharedResourceHolder.Resource<EventLoopGroup> DEFAULT_BOSS_EVENT_LOOP_GROUP;
    public static final Class<? extends Channel> DEFAULT_CLIENT_CHANNEL_TYPE;
    public static final ChannelFactory<? extends ServerChannel> DEFAULT_SERVER_CHANNEL_FACTORY;
    public static final SharedResourceHolder.Resource<EventLoopGroup> DEFAULT_WORKER_EVENT_LOOP_GROUP;
    public static final AsciiString HTTP;
    public static final AsciiString HTTPS;
    public static final AsciiString HTTP_GET_METHOD;
    public static final AsciiString HTTP_METHOD;
    public static final SharedResourceHolder.Resource<EventLoopGroup> NIO_BOSS_EVENT_LOOP_GROUP;
    public static final SharedResourceHolder.Resource<EventLoopGroup> NIO_WORKER_EVENT_LOOP_GROUP;
    public static final AsciiString STATUS_OK;
    public static final AsciiString TE_HEADER;
    public static final AsciiString TE_TRAILERS;
    public static final AsciiString USER_AGENT;
    @Nullable
    private static final Constructor<? extends EventLoopGroup> EPOLL_EVENT_LOOP_GROUP_CONSTRUCTOR;
    private static final Logger logger;

    static {
        Logger logger2 = Logger.getLogger(Utils.class.getName());
        logger = logger2;
        STATUS_OK = AsciiString.of("200");
        HTTP_METHOD = AsciiString.of("POST");
        HTTP_GET_METHOD = AsciiString.of(HttpMethods.GET);
        HTTPS = AsciiString.of("https");
        HTTP = AsciiString.of(HttpHost.DEFAULT_SCHEME_NAME);
        CONTENT_TYPE_HEADER = AsciiString.of(GrpcUtil.CONTENT_TYPE_KEY.name());
        CONTENT_TYPE_GRPC = AsciiString.of(GrpcUtil.CONTENT_TYPE_GRPC);
        TE_HEADER = AsciiString.of(GrpcUtil.TE_HEADER.name());
        TE_TRAILERS = AsciiString.of("trailers");
        USER_AGENT = AsciiString.of(GrpcUtil.USER_AGENT_KEY.name());
        DefaultEventLoopGroupResource defaultEventLoopGroupResource = new DefaultEventLoopGroupResource(1, "grpc-nio-boss-ELG", EventLoopGroupType.NIO);
        NIO_BOSS_EVENT_LOOP_GROUP = defaultEventLoopGroupResource;
        DefaultEventLoopGroupResource defaultEventLoopGroupResource2 = new DefaultEventLoopGroupResource(0, "grpc-nio-worker-ELG", EventLoopGroupType.NIO);
        NIO_WORKER_EVENT_LOOP_GROUP = defaultEventLoopGroupResource2;
        if (isEpollAvailable()) {
            DEFAULT_CLIENT_CHANNEL_TYPE = epollChannelType();
            DEFAULT_SERVER_CHANNEL_FACTORY = new ReflectiveChannelFactory(epollServerChannelType());
            EPOLL_EVENT_LOOP_GROUP_CONSTRUCTOR = epollEventLoopGroupConstructor();
            DEFAULT_BOSS_EVENT_LOOP_GROUP = new DefaultEventLoopGroupResource(1, "grpc-default-boss-ELG", EventLoopGroupType.EPOLL);
            DEFAULT_WORKER_EVENT_LOOP_GROUP = new DefaultEventLoopGroupResource(0, "grpc-default-worker-ELG", EventLoopGroupType.EPOLL);
            return;
        }
        logger2.log(Level.FINE, "Epoll is not available, using Nio.", getEpollUnavailabilityCause());
        DEFAULT_SERVER_CHANNEL_FACTORY = nioServerChannelFactory();
        DEFAULT_CLIENT_CHANNEL_TYPE = NioSocketChannel.class;
        DEFAULT_BOSS_EVENT_LOOP_GROUP = defaultEventLoopGroupResource;
        DEFAULT_WORKER_EVENT_LOOP_GROUP = defaultEventLoopGroupResource2;
        EPOLL_EVENT_LOOP_GROUP_CONSTRUCTOR = null;
    }

    private Utils() {
    }

    public static ByteBufAllocator getByteBufAllocator(boolean z) {
        if (Boolean.parseBoolean(System.getProperty("io.grpc.netty.useCustomAllocator", "true"))) {
            boolean zDefaultPreferDirect = PooledByteBufAllocator.defaultPreferDirect();
            logger.log(Level.FINE, String.format("Using custom allocator: forceHeapBuffer=%s, defaultPreferDirect=%s", Boolean.valueOf(z), Boolean.valueOf(zDefaultPreferDirect)));
            if (z || !zDefaultPreferDirect) {
                return ByteBufAllocatorPreferHeapHolder.allocator;
            }
            return ByteBufAllocatorPreferDirectHolder.allocator;
        }
        logger.log(Level.FINE, "Using default allocator");
        return ByteBufAllocator.DEFAULT;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static ByteBufAllocator createByteBufAllocator(boolean z) {
        int i;
        Logger logger2 = logger;
        logger2.log(Level.FINE, "Creating allocator, preferDirect=" + z);
        if (System.getProperty("io.netty.allocator.maxOrder") == null) {
            logger2.log(Level.FINE, "Forcing maxOrder=8");
            i = 8;
        } else {
            int iDefaultMaxOrder = PooledByteBufAllocator.defaultMaxOrder();
            logger2.log(Level.FINE, "Using default maxOrder=" + iDefaultMaxOrder);
            i = iDefaultMaxOrder;
        }
        return new PooledByteBufAllocator(z, PooledByteBufAllocator.defaultNumHeapArena(), z ? PooledByteBufAllocator.defaultNumDirectArena() : 0, PooledByteBufAllocator.defaultPageSize(), i, PooledByteBufAllocator.defaultTinyCacheSize(), PooledByteBufAllocator.defaultSmallCacheSize(), PooledByteBufAllocator.defaultNormalCacheSize(), PooledByteBufAllocator.defaultUseCacheForAllThreads());
    }

    public static Metadata convertHeaders(Http2Headers http2Headers) {
        if (http2Headers instanceof GrpcHttp2HeadersUtils.GrpcHttp2InboundHeaders) {
            GrpcHttp2HeadersUtils.GrpcHttp2InboundHeaders grpcHttp2InboundHeaders = (GrpcHttp2HeadersUtils.GrpcHttp2InboundHeaders) http2Headers;
            return InternalMetadata.newMetadata(grpcHttp2InboundHeaders.numHeaders(), grpcHttp2InboundHeaders.namesAndValues());
        }
        return InternalMetadata.newMetadata(convertHeadersToArray(http2Headers));
    }

    @CheckReturnValue
    private static byte[][] convertHeadersToArray(Http2Headers http2Headers) {
        byte[][] bArr = new byte[http2Headers.size() * 2][];
        Iterator it2 = http2Headers.iterator();
        int i = 0;
        while (it2.hasNext()) {
            Map.Entry entry = (Map.Entry) it2.next();
            int i2 = i + 1;
            bArr[i] = bytes((CharSequence) entry.getKey());
            i += 2;
            bArr[i2] = bytes((CharSequence) entry.getValue());
        }
        return TransportFrameUtil.toRawSerializedHeaders(bArr);
    }

    private static byte[] bytes(CharSequence charSequence) {
        if (charSequence instanceof AsciiString) {
            AsciiString asciiString = (AsciiString) charSequence;
            return asciiString.isEntireArrayUsed() ? asciiString.array() : asciiString.toByteArray();
        }
        return charSequence.toString().getBytes(CharsetUtil.UTF_8);
    }

    public static Http2Headers convertClientHeaders(Metadata metadata, AsciiString asciiString, AsciiString asciiString2, AsciiString asciiString3, AsciiString asciiString4, AsciiString asciiString5) {
        Preconditions.checkNotNull(asciiString2, "defaultPath");
        Preconditions.checkNotNull(asciiString3, "authority");
        Preconditions.checkNotNull(asciiString4, "method");
        metadata.discardAll(GrpcUtil.CONTENT_TYPE_KEY);
        metadata.discardAll(GrpcUtil.TE_HEADER);
        metadata.discardAll(GrpcUtil.USER_AGENT_KEY);
        return GrpcHttp2OutboundHeaders.clientRequestHeaders(TransportFrameUtil.toHttp2Headers(metadata), asciiString3, asciiString2, asciiString4, asciiString, asciiString5);
    }

    public static Http2Headers convertServerHeaders(Metadata metadata) {
        metadata.discardAll(GrpcUtil.CONTENT_TYPE_KEY);
        metadata.discardAll(GrpcUtil.TE_HEADER);
        metadata.discardAll(GrpcUtil.USER_AGENT_KEY);
        return GrpcHttp2OutboundHeaders.serverResponseHeaders(TransportFrameUtil.toHttp2Headers(metadata));
    }

    public static Metadata convertTrailers(Http2Headers http2Headers) {
        if (http2Headers instanceof GrpcHttp2HeadersUtils.GrpcHttp2InboundHeaders) {
            GrpcHttp2HeadersUtils.GrpcHttp2InboundHeaders grpcHttp2InboundHeaders = (GrpcHttp2HeadersUtils.GrpcHttp2InboundHeaders) http2Headers;
            return InternalMetadata.newMetadata(grpcHttp2InboundHeaders.numHeaders(), grpcHttp2InboundHeaders.namesAndValues());
        }
        return InternalMetadata.newMetadata(convertHeadersToArray(http2Headers));
    }

    public static Http2Headers convertTrailers(Metadata metadata, boolean z) {
        if (!z) {
            return convertServerHeaders(metadata);
        }
        return GrpcHttp2OutboundHeaders.serverResponseTrailers(TransportFrameUtil.toHttp2Headers(metadata));
    }

    public static Status statusFromThrowable(Throwable th) {
        Status statusFromThrowable = Status.fromThrowable(th);
        if (statusFromThrowable.getCode() != Status.Code.UNKNOWN) {
            return statusFromThrowable;
        }
        if (th instanceof ClosedChannelException) {
            ClosedChannelException closedChannelException = new ClosedChannelException();
            closedChannelException.initCause(th);
            return Status.UNKNOWN.withDescription("channel closed").withCause(closedChannelException);
        }
        if (th instanceof IOException) {
            return Status.UNAVAILABLE.withDescription("io exception").withCause(th);
        }
        if (th instanceof UnresolvedAddressException) {
            return Status.UNAVAILABLE.withDescription("unresolved address").withCause(th);
        }
        return th instanceof Http2Exception ? Status.INTERNAL.withDescription("http2 exception").withCause(th) : statusFromThrowable;
    }

    static boolean isEpollAvailable() {
        try {
            return ((Boolean) Class.forName("io.netty.channel.epoll.Epoll").getDeclaredMethod("isAvailable", new Class[0]).invoke(null, new Object[0])).booleanValue();
        } catch (ClassNotFoundException unused) {
            return false;
        } catch (Exception e) {
            throw new RuntimeException("Exception while checking Epoll availability", e);
        }
    }

    private static Throwable getEpollUnavailabilityCause() {
        try {
            return (Throwable) Class.forName("io.netty.channel.epoll.Epoll").getDeclaredMethod("unavailabilityCause", new Class[0]).invoke(null, new Object[0]);
        } catch (Exception e) {
            return e;
        }
    }

    private static Class<? extends Channel> epollChannelType() {
        try {
            return Class.forName("io.netty.channel.epoll.EpollSocketChannel").asSubclass(Channel.class);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Cannot load EpollSocketChannel", e);
        }
    }

    private static Constructor<? extends EventLoopGroup> epollEventLoopGroupConstructor() {
        try {
            return Class.forName("io.netty.channel.epoll.EpollEventLoopGroup").asSubclass(EventLoopGroup.class).getConstructor(Integer.TYPE, ThreadFactory.class);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Cannot load EpollEventLoopGroup", e);
        } catch (NoSuchMethodException e2) {
            throw new RuntimeException("EpollEventLoopGroup constructor not found", e2);
        }
    }

    private static Class<? extends ServerChannel> epollServerChannelType() {
        try {
            return Class.forName("io.netty.channel.epoll.EpollServerSocketChannel").asSubclass(ServerChannel.class);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Cannot load EpollServerSocketChannel", e);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static EventLoopGroup createEpollEventLoopGroup(int i, ThreadFactory threadFactory) {
        Constructor<? extends EventLoopGroup> constructor = EPOLL_EVENT_LOOP_GROUP_CONSTRUCTOR;
        Preconditions.checkState(constructor != null, "Epoll is not available");
        try {
            return constructor.newInstance(Integer.valueOf(i), threadFactory);
        } catch (Exception e) {
            throw new RuntimeException("Cannot create Epoll EventLoopGroup", e);
        }
    }

    private static ChannelFactory<ServerChannel> nioServerChannelFactory() {
        return new ChannelFactory<ServerChannel>() { // from class: io.grpc.netty.Utils.1
            public ServerChannel newChannel() {
                return new NioServerSocketChannel();
            }
        };
    }

    @Nullable
    static ChannelOption<Integer> maybeGetTcpUserTimeoutOption() {
        return getEpollChannelOption("TCP_USER_TIMEOUT");
    }

    @Nullable
    private static <T> ChannelOption<T> getEpollChannelOption(String str) {
        if (!isEpollAvailable()) {
            return null;
        }
        try {
            return (ChannelOption) Class.forName("io.netty.channel.epoll.EpollChannelOption").getField(str).get(null);
        } catch (Exception e) {
            throw new RuntimeException("ChannelOption(" + str + ") is not available", e);
        }
    }

    static InternalChannelz.SocketOptions getSocketOptions(Channel channel) {
        ChannelConfig channelConfigConfig = channel.config();
        InternalChannelz.SocketOptions.Builder builder = new InternalChannelz.SocketOptions.Builder();
        Integer num = (Integer) channelConfigConfig.getOption(ChannelOption.SO_LINGER);
        if (num != null) {
            builder.setSocketOptionLingerSeconds(num);
        }
        Integer num2 = (Integer) channelConfigConfig.getOption(ChannelOption.SO_TIMEOUT);
        if (num2 != null) {
            builder.setSocketOptionTimeoutMillis(num2);
        }
        for (Map.Entry entry : channelConfigConfig.getOptions().entrySet()) {
            ChannelOption channelOption = (ChannelOption) entry.getKey();
            if (!channelOption.equals(ChannelOption.SO_LINGER) && !channelOption.equals(ChannelOption.SO_TIMEOUT)) {
                builder.addOption(channelOption.name(), String.valueOf(entry.getValue()));
            }
        }
        NettySocketSupport.NativeSocketOptions nativeSocketOptions = NettySocketSupport.getNativeSocketOptions(channel);
        if (nativeSocketOptions != null) {
            builder.setTcpInfo(nativeSocketOptions.tcpInfo);
            UnmodifiableIterator<Map.Entry<String, String>> it2 = nativeSocketOptions.otherInfo.entrySet().iterator();
            while (it2.hasNext()) {
                Map.Entry<String, String> next = it2.next();
                builder.addOption(next.getKey(), next.getValue());
            }
        }
        return builder.build();
    }

    private enum EventLoopGroupType {
        NIO,
        EPOLL
    }

    private static final class ByteBufAllocatorPreferDirectHolder {
        private static final ByteBufAllocator allocator = Utils.createByteBufAllocator(true);

        private ByteBufAllocatorPreferDirectHolder() {
        }
    }

    private static final class ByteBufAllocatorPreferHeapHolder {
        private static final ByteBufAllocator allocator = Utils.createByteBufAllocator(false);

        private ByteBufAllocatorPreferHeapHolder() {
        }
    }

    private static final class DefaultEventLoopGroupResource implements SharedResourceHolder.Resource<EventLoopGroup> {
        private final EventLoopGroupType eventLoopGroupType;
        private final String name;
        private final int numEventLoops;

        DefaultEventLoopGroupResource(int i, String str, EventLoopGroupType eventLoopGroupType) {
            this.name = str;
            if (i == 0 && System.getProperty("io.netty.eventLoopThreads") == null) {
                this.numEventLoops = NettyRuntime.availableProcessors();
            } else {
                this.numEventLoops = i;
            }
            this.eventLoopGroupType = eventLoopGroupType;
        }

        public String toString() {
            return this.name;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // io.grpc.internal.SharedResourceHolder.Resource
        public EventLoopGroup create() {
            DefaultThreadFactory defaultThreadFactory = new DefaultThreadFactory(this.name, true);
            int i = AnonymousClass2.$SwitchMap$io$grpc$netty$Utils$EventLoopGroupType[this.eventLoopGroupType.ordinal()];
            if (i == 1) {
                return new NioEventLoopGroup(this.numEventLoops, defaultThreadFactory);
            }
            if (i == 2) {
                return Utils.createEpollEventLoopGroup(this.numEventLoops, defaultThreadFactory);
            }
            throw new AssertionError("Unknown/Unsupported EventLoopGroupType: " + this.eventLoopGroupType);
        }

        @Override // io.grpc.internal.SharedResourceHolder.Resource
        public void close(EventLoopGroup eventLoopGroup) {
            eventLoopGroup.shutdownGracefully(0L, 0L, TimeUnit.SECONDS);
        }
    }

    /* renamed from: io.grpc.netty.Utils$2, reason: invalid class name */
    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$io$grpc$netty$Utils$EventLoopGroupType;

        static {
            int[] iArr = new int[EventLoopGroupType.values().length];
            $SwitchMap$io$grpc$netty$Utils$EventLoopGroupType = iArr;
            try {
                iArr[EventLoopGroupType.NIO.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$io$grpc$netty$Utils$EventLoopGroupType[EventLoopGroupType.EPOLL.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }
}
