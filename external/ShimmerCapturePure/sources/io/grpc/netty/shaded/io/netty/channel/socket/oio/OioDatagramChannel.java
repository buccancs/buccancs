package io.grpc.netty.shaded.io.netty.channel.socket.oio;

import io.grpc.netty.shaded.io.netty.buffer.ByteBuf;
import io.grpc.netty.shaded.io.netty.buffer.ByteBufUtil;
import io.grpc.netty.shaded.io.netty.channel.AddressedEnvelope;
import io.grpc.netty.shaded.io.netty.channel.ChannelException;
import io.grpc.netty.shaded.io.netty.channel.ChannelFuture;
import io.grpc.netty.shaded.io.netty.channel.ChannelMetadata;
import io.grpc.netty.shaded.io.netty.channel.ChannelOption;
import io.grpc.netty.shaded.io.netty.channel.ChannelOutboundBuffer;
import io.grpc.netty.shaded.io.netty.channel.ChannelPromise;
import io.grpc.netty.shaded.io.netty.channel.RecvByteBufAllocator;
import io.grpc.netty.shaded.io.netty.channel.oio.AbstractOioMessageChannel;
import io.grpc.netty.shaded.io.netty.channel.socket.DatagramChannel;
import io.grpc.netty.shaded.io.netty.channel.socket.DatagramChannelConfig;
import io.grpc.netty.shaded.io.netty.util.internal.EmptyArrays;
import io.grpc.netty.shaded.io.netty.util.internal.PlatformDependent;
import io.grpc.netty.shaded.io.netty.util.internal.StringUtil;
import io.grpc.netty.shaded.io.netty.util.internal.logging.InternalLogger;
import io.grpc.netty.shaded.io.netty.util.internal.logging.InternalLoggerFactory;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.MulticastSocket;
import java.net.NetworkInterface;
import java.net.SocketAddress;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.nio.channels.NotYetConnectedException;
import java.util.List;
import java.util.Locale;

import kotlin.text.Typography;

@Deprecated
/* loaded from: classes3.dex */
public class OioDatagramChannel extends AbstractOioMessageChannel implements DatagramChannel {
    private static final InternalLogger logger = InternalLoggerFactory.getInstance((Class<?>) OioDatagramChannel.class);
    private static final ChannelMetadata METADATA = new ChannelMetadata(true);
    private static final String EXPECTED_TYPES = " (expected: " + StringUtil.simpleClassName((Class<?>) io.grpc.netty.shaded.io.netty.channel.socket.DatagramPacket.class) + ", " + StringUtil.simpleClassName((Class<?>) AddressedEnvelope.class) + Typography.less + StringUtil.simpleClassName((Class<?>) ByteBuf.class) + ", " + StringUtil.simpleClassName((Class<?>) SocketAddress.class) + ">, " + StringUtil.simpleClassName((Class<?>) ByteBuf.class) + ')';
    private final OioDatagramChannelConfig config;
    private final MulticastSocket socket;
    private final DatagramPacket tmpPacket;

    public OioDatagramChannel() {
        this(newSocket());
    }

    public OioDatagramChannel(MulticastSocket multicastSocket) {
        super(null);
        this.tmpPacket = new DatagramPacket(EmptyArrays.EMPTY_BYTES, 0);
        try {
            try {
                multicastSocket.setSoTimeout(1000);
                multicastSocket.setBroadcast(false);
                this.socket = multicastSocket;
                this.config = new DefaultOioDatagramChannelConfig(this, multicastSocket);
            } catch (SocketException e) {
                throw new ChannelException("Failed to configure the datagram socket timeout.", e);
            }
        } catch (Throwable th) {
            multicastSocket.close();
            throw th;
        }
    }

    private static MulticastSocket newSocket() {
        try {
            return new MulticastSocket((SocketAddress) null);
        } catch (Exception e) {
            throw new ChannelException("failed to create a new socket", e);
        }
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.Channel
    public DatagramChannelConfig config() {
        return this.config;
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.Channel
    public ChannelMetadata metadata() {
        return METADATA;
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.Channel
    public boolean isOpen() {
        return !this.socket.isClosed();
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.Channel
    public boolean isActive() {
        return isOpen() && ((((Boolean) this.config.getOption(ChannelOption.DATAGRAM_CHANNEL_ACTIVE_ON_REGISTRATION)).booleanValue() && isRegistered()) || this.socket.isBound());
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.socket.DatagramChannel
    public boolean isConnected() {
        return this.socket.isConnected();
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.AbstractChannel
    protected SocketAddress localAddress0() {
        return this.socket.getLocalSocketAddress();
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.AbstractChannel
    protected SocketAddress remoteAddress0() {
        return this.socket.getRemoteSocketAddress();
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.AbstractChannel
    protected void doBind(SocketAddress socketAddress) throws Exception {
        this.socket.bind(socketAddress);
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.AbstractChannel, io.grpc.netty.shaded.io.netty.channel.Channel
    public InetSocketAddress localAddress() {
        return (InetSocketAddress) super.localAddress();
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.AbstractChannel, io.grpc.netty.shaded.io.netty.channel.Channel
    public InetSocketAddress remoteAddress() {
        return (InetSocketAddress) super.remoteAddress();
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.oio.AbstractOioChannel
    protected void doConnect(SocketAddress socketAddress, SocketAddress socketAddress2) throws Exception {
        if (socketAddress2 != null) {
            this.socket.bind(socketAddress2);
        }
        try {
            this.socket.connect(socketAddress);
        } catch (Throwable th) {
            try {
                this.socket.close();
            } catch (Throwable th2) {
                logger.warn("Failed to close a socket.", th2);
            }
            throw th;
        }
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.AbstractChannel
    protected void doDisconnect() throws Exception {
        this.socket.disconnect();
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.AbstractChannel
    protected void doClose() throws Exception {
        this.socket.close();
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.oio.AbstractOioMessageChannel
    protected int doReadMessages(List<Object> list) throws Exception {
        DatagramChannelConfig datagramChannelConfigConfig = config();
        RecvByteBufAllocator.Handle handleRecvBufAllocHandle = unsafe().recvBufAllocHandle();
        ByteBuf byteBufHeapBuffer = datagramChannelConfigConfig.getAllocator().heapBuffer(handleRecvBufAllocHandle.guess());
        try {
            try {
                try {
                    this.tmpPacket.setAddress(null);
                    this.tmpPacket.setData(byteBufHeapBuffer.array(), byteBufHeapBuffer.arrayOffset(), byteBufHeapBuffer.capacity());
                    this.socket.receive(this.tmpPacket);
                    InetSocketAddress inetSocketAddress = (InetSocketAddress) this.tmpPacket.getSocketAddress();
                    handleRecvBufAllocHandle.lastBytesRead(this.tmpPacket.getLength());
                    list.add(new io.grpc.netty.shaded.io.netty.channel.socket.DatagramPacket(byteBufHeapBuffer.writerIndex(handleRecvBufAllocHandle.lastBytesRead()), localAddress(), inetSocketAddress));
                    return 1;
                } catch (Throwable th) {
                    PlatformDependent.throwException(th);
                    byteBufHeapBuffer.release();
                    return -1;
                }
            } catch (SocketException e) {
                if (!e.getMessage().toLowerCase(Locale.US).contains("socket closed")) {
                    throw e;
                }
                byteBufHeapBuffer.release();
                return -1;
            } catch (SocketTimeoutException unused) {
                byteBufHeapBuffer.release();
                return 0;
            }
        } catch (Throwable th2) {
            byteBufHeapBuffer.release();
            throw th2;
        }
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.AbstractChannel
    protected void doWrite(ChannelOutboundBuffer channelOutboundBuffer) throws Exception {
        ByteBuf byteBuf;
        SocketAddress socketAddressRecipient;
        while (true) {
            Object objCurrent = channelOutboundBuffer.current();
            if (objCurrent == null) {
                return;
            }
            if (objCurrent instanceof AddressedEnvelope) {
                AddressedEnvelope addressedEnvelope = (AddressedEnvelope) objCurrent;
                socketAddressRecipient = addressedEnvelope.recipient();
                byteBuf = (ByteBuf) addressedEnvelope.content();
            } else {
                byteBuf = (ByteBuf) objCurrent;
                socketAddressRecipient = null;
            }
            int i = byteBuf.readableBytes();
            if (socketAddressRecipient != null) {
                try {
                    this.tmpPacket.setSocketAddress(socketAddressRecipient);
                } catch (Exception e) {
                    channelOutboundBuffer.remove(e);
                }
            } else {
                if (!isConnected()) {
                    throw new NotYetConnectedException();
                }
                this.tmpPacket.setAddress(null);
            }
            if (byteBuf.hasArray()) {
                this.tmpPacket.setData(byteBuf.array(), byteBuf.arrayOffset() + byteBuf.readerIndex(), i);
            } else {
                this.tmpPacket.setData(ByteBufUtil.getBytes(byteBuf, byteBuf.readerIndex(), i));
            }
            this.socket.send(this.tmpPacket);
            channelOutboundBuffer.remove();
        }
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.AbstractChannel
    protected Object filterOutboundMessage(Object obj) {
        if ((obj instanceof io.grpc.netty.shaded.io.netty.channel.socket.DatagramPacket) || (obj instanceof ByteBuf)) {
            return obj;
        }
        if ((obj instanceof AddressedEnvelope) && (((AddressedEnvelope) obj).content() instanceof ByteBuf)) {
            return obj;
        }
        throw new UnsupportedOperationException("unsupported message type: " + StringUtil.simpleClassName(obj) + EXPECTED_TYPES);
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.socket.DatagramChannel
    public ChannelFuture joinGroup(InetAddress inetAddress) {
        return joinGroup(inetAddress, newPromise());
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.socket.DatagramChannel
    public ChannelFuture joinGroup(InetAddress inetAddress, ChannelPromise channelPromise) throws IOException {
        ensureBound();
        try {
            this.socket.joinGroup(inetAddress);
            channelPromise.setSuccess();
        } catch (IOException e) {
            channelPromise.setFailure((Throwable) e);
        }
        return channelPromise;
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.socket.DatagramChannel
    public ChannelFuture joinGroup(InetSocketAddress inetSocketAddress, NetworkInterface networkInterface) {
        return joinGroup(inetSocketAddress, networkInterface, newPromise());
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.socket.DatagramChannel
    public ChannelFuture joinGroup(InetSocketAddress inetSocketAddress, NetworkInterface networkInterface, ChannelPromise channelPromise) throws IOException {
        ensureBound();
        try {
            this.socket.joinGroup(inetSocketAddress, networkInterface);
            channelPromise.setSuccess();
        } catch (IOException e) {
            channelPromise.setFailure((Throwable) e);
        }
        return channelPromise;
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.socket.DatagramChannel
    public ChannelFuture joinGroup(InetAddress inetAddress, NetworkInterface networkInterface, InetAddress inetAddress2) {
        return newFailedFuture(new UnsupportedOperationException());
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.socket.DatagramChannel
    public ChannelFuture joinGroup(InetAddress inetAddress, NetworkInterface networkInterface, InetAddress inetAddress2, ChannelPromise channelPromise) {
        channelPromise.setFailure((Throwable) new UnsupportedOperationException());
        return channelPromise;
    }

    private void ensureBound() {
        if (isActive()) {
            return;
        }
        throw new IllegalStateException(DatagramChannel.class.getName() + " must be bound to join a group.");
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.socket.DatagramChannel
    public ChannelFuture leaveGroup(InetAddress inetAddress) {
        return leaveGroup(inetAddress, newPromise());
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.socket.DatagramChannel
    public ChannelFuture leaveGroup(InetAddress inetAddress, ChannelPromise channelPromise) throws IOException {
        try {
            this.socket.leaveGroup(inetAddress);
            channelPromise.setSuccess();
        } catch (IOException e) {
            channelPromise.setFailure((Throwable) e);
        }
        return channelPromise;
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.socket.DatagramChannel
    public ChannelFuture leaveGroup(InetSocketAddress inetSocketAddress, NetworkInterface networkInterface) {
        return leaveGroup(inetSocketAddress, networkInterface, newPromise());
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.socket.DatagramChannel
    public ChannelFuture leaveGroup(InetSocketAddress inetSocketAddress, NetworkInterface networkInterface, ChannelPromise channelPromise) throws IOException {
        try {
            this.socket.leaveGroup(inetSocketAddress, networkInterface);
            channelPromise.setSuccess();
        } catch (IOException e) {
            channelPromise.setFailure((Throwable) e);
        }
        return channelPromise;
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.socket.DatagramChannel
    public ChannelFuture leaveGroup(InetAddress inetAddress, NetworkInterface networkInterface, InetAddress inetAddress2) {
        return newFailedFuture(new UnsupportedOperationException());
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.socket.DatagramChannel
    public ChannelFuture leaveGroup(InetAddress inetAddress, NetworkInterface networkInterface, InetAddress inetAddress2, ChannelPromise channelPromise) {
        channelPromise.setFailure((Throwable) new UnsupportedOperationException());
        return channelPromise;
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.socket.DatagramChannel
    public ChannelFuture block(InetAddress inetAddress, NetworkInterface networkInterface, InetAddress inetAddress2) {
        return newFailedFuture(new UnsupportedOperationException());
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.socket.DatagramChannel
    public ChannelFuture block(InetAddress inetAddress, NetworkInterface networkInterface, InetAddress inetAddress2, ChannelPromise channelPromise) {
        channelPromise.setFailure((Throwable) new UnsupportedOperationException());
        return channelPromise;
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.socket.DatagramChannel
    public ChannelFuture block(InetAddress inetAddress, InetAddress inetAddress2) {
        return newFailedFuture(new UnsupportedOperationException());
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.socket.DatagramChannel
    public ChannelFuture block(InetAddress inetAddress, InetAddress inetAddress2, ChannelPromise channelPromise) {
        channelPromise.setFailure((Throwable) new UnsupportedOperationException());
        return channelPromise;
    }
}
