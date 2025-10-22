package io.grpc.services;

import com.google.common.base.Charsets;
import com.google.common.base.Preconditions;
import com.google.common.base.Splitter;
import com.google.protobuf.ByteString;
import com.google.protobuf.Duration;
import com.google.protobuf.util.Durations;
import com.google.protobuf.util.Timestamps;
import com.shimmerresearch.driver.Configuration;
import io.grpc.Attributes;
import io.grpc.CallOptions;
import io.grpc.Channel;
import io.grpc.ClientCall;
import io.grpc.ClientInterceptor;
import io.grpc.Context;
import io.grpc.Deadline;
import io.grpc.ForwardingClientCall;
import io.grpc.ForwardingClientCallListener;
import io.grpc.ForwardingServerCall;
import io.grpc.ForwardingServerCallListener;
import io.grpc.Grpc;
import io.grpc.InternalMetadata;
import io.grpc.Metadata;
import io.grpc.MethodDescriptor;
import io.grpc.ServerCall;
import io.grpc.ServerCallHandler;
import io.grpc.ServerInterceptor;
import io.grpc.Status;
import io.grpc.binarylog.v1.Address;
import io.grpc.binarylog.v1.ClientHeader;
import io.grpc.binarylog.v1.GrpcLogEntry;
import io.grpc.binarylog.v1.Message;
import io.grpc.binarylog.v1.Metadata;
import io.grpc.binarylog.v1.ServerHeader;
import io.grpc.binarylog.v1.Trailer;
import io.grpc.netty.shaded.io.netty.util.internal.StringUtil;

import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Nullable;

/* loaded from: classes3.dex */
final class BinlogHelper {
    static final Metadata.Key<byte[]> STATUS_DETAILS_KEY;
    private static final Set<String> ALWAYS_INCLUDED_METADATA;
    private static final Set<String> NEVER_INCLUDED_METADATA;
    private static final Logger logger = Logger.getLogger(BinlogHelper.class.getName());

    static {
        Metadata.Key<byte[]> keyOf = Metadata.Key.of("grpc-status-details-bin", Metadata.BINARY_BYTE_MARSHALLER);
        STATUS_DETAILS_KEY = keyOf;
        NEVER_INCLUDED_METADATA = new HashSet(Collections.singletonList(keyOf.name()));
        ALWAYS_INCLUDED_METADATA = new HashSet(Collections.singletonList("grpc-trace-bin"));
    }

    final SinkWriter writer;

    BinlogHelper(SinkWriter sinkWriter) {
        this.writer = sinkWriter;
    }

    static SocketAddress getPeerSocket(Attributes attributes) {
        return (SocketAddress) attributes.get(Grpc.TRANSPORT_ATTR_REMOTE_ADDR);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Deadline min(@Nullable Deadline deadline, @Nullable Deadline deadline2) {
        return deadline == null ? deadline2 : deadline2 == null ? deadline : deadline.minimum(deadline2);
    }

    static Address socketToProto(SocketAddress socketAddress) {
        Preconditions.checkNotNull(socketAddress, "address");
        Address.Builder builderNewBuilder = Address.newBuilder();
        if (socketAddress instanceof InetSocketAddress) {
            InetSocketAddress inetSocketAddress = (InetSocketAddress) socketAddress;
            InetAddress address = inetSocketAddress.getAddress();
            if (address instanceof Inet4Address) {
                builderNewBuilder.setType(Address.Type.TYPE_IPV4).setAddress(InetAddressUtil.toAddrString(address));
            } else if (address instanceof Inet6Address) {
                builderNewBuilder.setType(Address.Type.TYPE_IPV6).setAddress(InetAddressUtil.toAddrString(address));
            } else {
                logger.log(Level.SEVERE, "unknown type of InetSocketAddress: {}", socketAddress);
                builderNewBuilder.setAddress(socketAddress.toString());
            }
            builderNewBuilder.setIpPort(inetSocketAddress.getPort());
        } else if (socketAddress.getClass().getName().equals("io.netty.channel.unix.DomainSocketAddress")) {
            builderNewBuilder.setType(Address.Type.TYPE_UNIX).setAddress(socketAddress.toString());
        } else {
            builderNewBuilder.setType(Address.Type.TYPE_UNKNOWN).setAddress(socketAddress.toString());
        }
        return builderNewBuilder.m6940build();
    }

    static MaybeTruncated<Metadata.Builder> createMetadataProto(io.grpc.Metadata metadata, int i) {
        Preconditions.checkNotNull(metadata, "metadata");
        boolean z = false;
        Preconditions.checkArgument(i >= 0, "maxHeaderBytes must be non negative");
        Metadata.Builder builderNewBuilder = io.grpc.binarylog.v1.Metadata.newBuilder();
        byte[][] bArrSerialize = InternalMetadata.serialize(metadata);
        if (bArrSerialize != null) {
            boolean z2 = false;
            int i2 = 0;
            for (int i3 = 0; i3 < bArrSerialize.length; i3 += 2) {
                String str = new String(bArrSerialize[i3], Charsets.UTF_8);
                byte[] bArr = bArrSerialize[i3 + 1];
                if (!NEVER_INCLUDED_METADATA.contains(str)) {
                    boolean zContains = ALWAYS_INCLUDED_METADATA.contains(str);
                    int length = str.length() + i2 + bArr.length;
                    if (zContains || length <= i) {
                        builderNewBuilder.addEntryBuilder().setKey(str).setValue(ByteString.copyFrom(bArr));
                        if (!zContains) {
                            i2 = length;
                        }
                    } else {
                        z2 = true;
                    }
                }
            }
            z = z2;
        }
        return new MaybeTruncated<>(builderNewBuilder, z, null);
    }

    static MaybeTruncated<Message.Builder> createMessageProto(byte[] bArr, int i) {
        Preconditions.checkNotNull(bArr, "message");
        Preconditions.checkArgument(i >= 0, "maxMessageBytes must be non negative");
        Message.Builder length = Message.newBuilder().setLength(bArr.length);
        if (i > 0) {
            length.setData(ByteString.copyFrom(bArr, 0, Math.min(i, bArr.length)));
        }
        return new MaybeTruncated<>(length, i < bArr.length, null);
    }

    public ClientInterceptor getClientInterceptor(long j) {
        return new AnonymousClass1(j);
    }

    public ServerInterceptor getServerInterceptor(final long j) {
        return new ServerInterceptor() { // from class: io.grpc.services.BinlogHelper.2
            @Override // io.grpc.ServerInterceptor
            public <ReqT, RespT> ServerCall.Listener<ReqT> interceptCall(final ServerCall<ReqT, RespT> serverCall, io.grpc.Metadata metadata, ServerCallHandler<ReqT, RespT> serverCallHandler) {
                final AtomicLong atomicLong = new AtomicLong(1L);
                SocketAddress peerSocket = BinlogHelper.getPeerSocket(serverCall.getAttributes());
                String fullMethodName = serverCall.getMethodDescriptor().getFullMethodName();
                String authority = serverCall.getAuthority();
                Deadline deadline = Context.current().getDeadline();
                BinlogHelper.this.writer.logClientHeader(atomicLong.getAndIncrement(), fullMethodName, authority, deadline == null ? null : Durations.fromNanos(deadline.timeRemaining(TimeUnit.NANOSECONDS)), metadata, GrpcLogEntry.Logger.LOGGER_SERVER, j, peerSocket);
                return new ForwardingServerCallListener.SimpleForwardingServerCallListener<ReqT>(serverCallHandler.startCall(new ForwardingServerCall.SimpleForwardingServerCall<ReqT, RespT>(serverCall) { // from class: io.grpc.services.BinlogHelper.2.1
                    @Override // io.grpc.ForwardingServerCall, io.grpc.ServerCall
                    public void sendMessage(RespT respt) {
                        BinlogHelper.this.writer.logRpcMessage(atomicLong.getAndIncrement(), GrpcLogEntry.EventType.EVENT_TYPE_SERVER_MESSAGE, serverCall.getMethodDescriptor().getResponseMarshaller(), respt, GrpcLogEntry.Logger.LOGGER_SERVER, j);
                        super.sendMessage(respt);
                    }

                    @Override
                    // io.grpc.ForwardingServerCall.SimpleForwardingServerCall, io.grpc.ForwardingServerCall, io.grpc.PartialForwardingServerCall, io.grpc.ServerCall
                    public void sendHeaders(io.grpc.Metadata metadata2) {
                        BinlogHelper.this.writer.logServerHeader(atomicLong.getAndIncrement(), metadata2, GrpcLogEntry.Logger.LOGGER_SERVER, j, null);
                        super.sendHeaders(metadata2);
                    }

                    @Override
                    // io.grpc.ForwardingServerCall.SimpleForwardingServerCall, io.grpc.ForwardingServerCall, io.grpc.PartialForwardingServerCall, io.grpc.ServerCall
                    public void close(Status status, io.grpc.Metadata metadata2) {
                        BinlogHelper.this.writer.logTrailer(atomicLong.getAndIncrement(), status, metadata2, GrpcLogEntry.Logger.LOGGER_SERVER, j, null);
                        super.close(status, metadata2);
                    }
                }, metadata)) { // from class: io.grpc.services.BinlogHelper.2.2
                    @Override // io.grpc.ForwardingServerCallListener, io.grpc.ServerCall.Listener
                    public void onMessage(ReqT reqt) {
                        BinlogHelper.this.writer.logRpcMessage(atomicLong.getAndIncrement(), GrpcLogEntry.EventType.EVENT_TYPE_CLIENT_MESSAGE, serverCall.getMethodDescriptor().getRequestMarshaller(), reqt, GrpcLogEntry.Logger.LOGGER_SERVER, j);
                        super.onMessage(reqt);
                    }

                    @Override
                    // io.grpc.ForwardingServerCallListener.SimpleForwardingServerCallListener, io.grpc.ForwardingServerCallListener, io.grpc.PartialForwardingServerCallListener, io.grpc.ServerCall.Listener
                    public void onHalfClose() {
                        BinlogHelper.this.writer.logHalfClose(atomicLong.getAndIncrement(), GrpcLogEntry.Logger.LOGGER_SERVER, j);
                        super.onHalfClose();
                    }

                    @Override
                    // io.grpc.ForwardingServerCallListener.SimpleForwardingServerCallListener, io.grpc.ForwardingServerCallListener, io.grpc.PartialForwardingServerCallListener, io.grpc.ServerCall.Listener
                    public void onCancel() {
                        BinlogHelper.this.writer.logCancel(atomicLong.getAndIncrement(), GrpcLogEntry.Logger.LOGGER_SERVER, j);
                        super.onCancel();
                    }
                };
            }
        };
    }

    interface Factory {
        @Nullable
        BinlogHelper getLog(String str);
    }

    interface TimeProvider {
        public static final TimeProvider SYSTEM_TIME_PROVIDER = new TimeProvider() { // from class: io.grpc.services.BinlogHelper.TimeProvider.1
            @Override // io.grpc.services.BinlogHelper.TimeProvider
            public long currentTimeNanos() {
                return TimeUnit.MILLISECONDS.toNanos(System.currentTimeMillis());
            }
        };

        long currentTimeNanos();
    }

    static final class SinkWriterImpl extends SinkWriter {
        private final int maxHeaderBytes;
        private final int maxMessageBytes;
        private final BinaryLogSink sink;
        private TimeProvider timeProvider;

        SinkWriterImpl(BinaryLogSink binaryLogSink, TimeProvider timeProvider, int i, int i2) {
            this.sink = binaryLogSink;
            this.timeProvider = timeProvider;
            this.maxHeaderBytes = i;
            this.maxMessageBytes = i2;
        }

        @Override
            // io.grpc.services.BinlogHelper.SinkWriter
        int getMaxHeaderBytes() {
            return this.maxHeaderBytes;
        }

        @Override
            // io.grpc.services.BinlogHelper.SinkWriter
        int getMaxMessageBytes() {
            return this.maxMessageBytes;
        }

        GrpcLogEntry.Builder newTimestampedBuilder() {
            return GrpcLogEntry.newBuilder().setTimestamp(Timestamps.fromNanos(this.timeProvider.currentTimeNanos()));
        }

        @Override
            // io.grpc.services.BinlogHelper.SinkWriter
        void logClientHeader(long j, String str, @Nullable String str2, @Nullable Duration duration, Metadata metadata, GrpcLogEntry.Logger logger, long j2, @Nullable SocketAddress socketAddress) {
            Preconditions.checkArgument(str != null, "methodName can not be null");
            Preconditions.checkArgument(!str.startsWith("/"), "in grpc-java method names should not have a leading '/'. However this class will add one to be consistent with language agnostic conventions.");
            Preconditions.checkArgument(socketAddress == null || logger == GrpcLogEntry.Logger.LOGGER_SERVER, "peerSocket can only be specified for server");
            MaybeTruncated<Metadata.Builder> maybeTruncatedCreateMetadataProto = BinlogHelper.createMetadataProto(metadata, this.maxHeaderBytes);
            ClientHeader.Builder methodName = ClientHeader.newBuilder().setMetadata(maybeTruncatedCreateMetadataProto.proto).setMethodName("/" + str);
            if (duration != null) {
                methodName.setTimeout(duration);
            }
            if (str2 != null) {
                methodName.setAuthority(str2);
            }
            GrpcLogEntry.Builder callId = newTimestampedBuilder().setSequenceIdWithinCall(j).setType(GrpcLogEntry.EventType.EVENT_TYPE_CLIENT_HEADER).setClientHeader(methodName).setPayloadTruncated(maybeTruncatedCreateMetadataProto.truncated).setLogger(logger).setCallId(j2);
            if (socketAddress != null) {
                callId.setPeer(BinlogHelper.socketToProto(socketAddress));
            }
            this.sink.write(callId.m7033build());
        }

        @Override
            // io.grpc.services.BinlogHelper.SinkWriter
        void logServerHeader(long j, io.grpc.Metadata metadata, GrpcLogEntry.Logger logger, long j2, @Nullable SocketAddress socketAddress) {
            Preconditions.checkArgument(socketAddress == null || logger == GrpcLogEntry.Logger.LOGGER_CLIENT, "peerSocket can only be specified for client");
            MaybeTruncated<Metadata.Builder> maybeTruncatedCreateMetadataProto = BinlogHelper.createMetadataProto(metadata, this.maxHeaderBytes);
            GrpcLogEntry.Builder callId = newTimestampedBuilder().setSequenceIdWithinCall(j).setType(GrpcLogEntry.EventType.EVENT_TYPE_SERVER_HEADER).setServerHeader(ServerHeader.newBuilder().setMetadata(maybeTruncatedCreateMetadataProto.proto)).setPayloadTruncated(maybeTruncatedCreateMetadataProto.truncated).setLogger(logger).setCallId(j2);
            if (socketAddress != null) {
                callId.setPeer(BinlogHelper.socketToProto(socketAddress));
            }
            this.sink.write(callId.m7033build());
        }

        @Override
            // io.grpc.services.BinlogHelper.SinkWriter
        void logTrailer(long j, Status status, io.grpc.Metadata metadata, GrpcLogEntry.Logger logger, long j2, @Nullable SocketAddress socketAddress) {
            Preconditions.checkArgument(socketAddress == null || logger == GrpcLogEntry.Logger.LOGGER_CLIENT, "peerSocket can only be specified for client");
            MaybeTruncated<Metadata.Builder> maybeTruncatedCreateMetadataProto = BinlogHelper.createMetadataProto(metadata, this.maxHeaderBytes);
            Trailer.Builder metadata2 = Trailer.newBuilder().setStatusCode(status.getCode().value()).setMetadata(maybeTruncatedCreateMetadataProto.proto);
            String description = status.getDescription();
            if (description != null) {
                metadata2.setStatusMessage(description);
            }
            byte[] bArr = (byte[]) metadata.get(BinlogHelper.STATUS_DETAILS_KEY);
            if (bArr != null) {
                metadata2.setStatusDetails(ByteString.copyFrom(bArr));
            }
            GrpcLogEntry.Builder callId = newTimestampedBuilder().setSequenceIdWithinCall(j).setType(GrpcLogEntry.EventType.EVENT_TYPE_SERVER_TRAILER).setTrailer(metadata2).setPayloadTruncated(maybeTruncatedCreateMetadataProto.truncated).setLogger(logger).setCallId(j2);
            if (socketAddress != null) {
                callId.setPeer(BinlogHelper.socketToProto(socketAddress));
            }
            this.sink.write(callId.m7033build());
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override
        // io.grpc.services.BinlogHelper.SinkWriter
        <T> void logRpcMessage(long j, GrpcLogEntry.EventType eventType, MethodDescriptor.Marshaller<T> marshaller, T t, GrpcLogEntry.Logger logger, long j2) {
            Preconditions.checkArgument(eventType == GrpcLogEntry.EventType.EVENT_TYPE_CLIENT_MESSAGE || eventType == GrpcLogEntry.EventType.EVENT_TYPE_SERVER_MESSAGE, "event type must correspond to client message or server message");
            if (marshaller != BinaryLogProvider.BYTEARRAY_MARSHALLER) {
                throw new IllegalStateException("Expected the BinaryLog's ByteArrayMarshaller");
            }
            MaybeTruncated<Message.Builder> maybeTruncatedCreateMessageProto = BinlogHelper.createMessageProto((byte[]) t, this.maxMessageBytes);
            this.sink.write(newTimestampedBuilder().setSequenceIdWithinCall(j).setType(eventType).setMessage(maybeTruncatedCreateMessageProto.proto).setPayloadTruncated(maybeTruncatedCreateMessageProto.truncated).setLogger(logger).setCallId(j2).m7033build());
        }

        @Override
            // io.grpc.services.BinlogHelper.SinkWriter
        void logHalfClose(long j, GrpcLogEntry.Logger logger, long j2) {
            this.sink.write(newTimestampedBuilder().setSequenceIdWithinCall(j).setType(GrpcLogEntry.EventType.EVENT_TYPE_CLIENT_HALF_CLOSE).setLogger(logger).setCallId(j2).m7033build());
        }

        @Override
            // io.grpc.services.BinlogHelper.SinkWriter
        void logCancel(long j, GrpcLogEntry.Logger logger, long j2) {
            this.sink.write(newTimestampedBuilder().setSequenceIdWithinCall(j).setType(GrpcLogEntry.EventType.EVENT_TYPE_CANCEL).setLogger(logger).setCallId(j2).m7033build());
        }
    }

    static abstract class SinkWriter {
        SinkWriter() {
        }

        abstract int getMaxHeaderBytes();

        abstract int getMaxMessageBytes();

        abstract void logCancel(long j, GrpcLogEntry.Logger logger, long j2);

        abstract void logClientHeader(long j, String str, @Nullable String str2, @Nullable Duration duration, io.grpc.Metadata metadata, GrpcLogEntry.Logger logger, long j2, @Nullable SocketAddress socketAddress);

        abstract void logHalfClose(long j, GrpcLogEntry.Logger logger, long j2);

        abstract <T> void logRpcMessage(long j, GrpcLogEntry.EventType eventType, MethodDescriptor.Marshaller<T> marshaller, T t, GrpcLogEntry.Logger logger, long j2);

        abstract void logServerHeader(long j, io.grpc.Metadata metadata, GrpcLogEntry.Logger logger, long j2, @Nullable SocketAddress socketAddress);

        abstract void logTrailer(long j, Status status, io.grpc.Metadata metadata, GrpcLogEntry.Logger logger, long j2, @Nullable SocketAddress socketAddress);
    }

    static final class FactoryImpl implements Factory {
        private final Set<String> blacklistedMethods;
        private final BinlogHelper globalLog;
        private final Map<String, BinlogHelper> perMethodLogs;
        private final Map<String, BinlogHelper> perServiceLogs;

        FactoryImpl(BinaryLogSink binaryLogSink, String str) {
            BinlogHelper binlogHelper;
            String strSubstring;
            String strSubstring2;
            Preconditions.checkNotNull(binaryLogSink, "sink");
            HashMap map = new HashMap();
            HashMap map2 = new HashMap();
            HashSet hashSet = new HashSet();
            if (str == null || str.length() <= 0) {
                binlogHelper = null;
            } else {
                BinlogHelper binlogHelperCreateBinaryLog = null;
                for (String str2 : Splitter.on(StringUtil.COMMA).split(str)) {
                    int iIndexOf = str2.indexOf(123);
                    if (iIndexOf == -1) {
                        strSubstring = str2;
                        strSubstring2 = null;
                    } else {
                        if (str2.indexOf(125, iIndexOf) != str2.length() - 1) {
                            throw new IllegalArgumentException("Illegal log config pattern: " + str2);
                        }
                        strSubstring = str2.substring(0, iIndexOf);
                        strSubstring2 = str2.substring(iIndexOf + 1, str2.length() - 1);
                    }
                    if (strSubstring.isEmpty()) {
                        throw new IllegalArgumentException("Illegal log config pattern: " + str2);
                    }
                    if (strSubstring.equals("*")) {
                        Preconditions.checkState(binlogHelperCreateBinaryLog == null, "Duplicate entry, this is fatal: " + str2);
                        binlogHelperCreateBinaryLog = createBinaryLog(binaryLogSink, strSubstring2);
                        BinlogHelper.logger.log(Level.INFO, "Global binlog: {0}", strSubstring2);
                    } else if (isServiceGlob(strSubstring)) {
                        String strExtractFullServiceName = MethodDescriptor.extractFullServiceName(strSubstring);
                        Preconditions.checkState(!map.containsKey(strExtractFullServiceName), "Duplicate entry, this is fatal: " + str2);
                        map.put(strExtractFullServiceName, createBinaryLog(binaryLogSink, strSubstring2));
                        BinlogHelper.logger.log(Level.INFO, "Service binlog: service={0} config={1}", new Object[]{strExtractFullServiceName, strSubstring2});
                    } else if (strSubstring.startsWith("-")) {
                        String strSubstring3 = strSubstring.substring(1);
                        if (strSubstring3.length() != 0) {
                            Preconditions.checkState(!hashSet.contains(strSubstring3), "Duplicate entry, this is fatal: " + str2);
                            Preconditions.checkState(map2.containsKey(strSubstring3) ^ true, "Duplicate entry, this is fatal: " + str2);
                            hashSet.add(strSubstring3);
                        }
                    } else {
                        Preconditions.checkState(!map2.containsKey(strSubstring), "Duplicate entry, this is fatal: " + str2);
                        Preconditions.checkState(hashSet.contains(strSubstring) ^ true, "Duplicate entry, this method was blacklisted: " + str2);
                        map2.put(strSubstring, createBinaryLog(binaryLogSink, strSubstring2));
                        BinlogHelper.logger.log(Level.INFO, "Method binlog: method={0} config={1}", new Object[]{strSubstring, strSubstring2});
                    }
                }
                binlogHelper = binlogHelperCreateBinaryLog;
            }
            this.globalLog = binlogHelper;
            this.perServiceLogs = Collections.unmodifiableMap(map);
            this.perMethodLogs = Collections.unmodifiableMap(map2);
            this.blacklistedMethods = Collections.unmodifiableSet(hashSet);
        }

        @Nullable
        static BinlogHelper createBinaryLog(BinaryLogSink binaryLogSink, @Nullable String str) {
            int iOptionalInt;
            if (str == null) {
                return new BinlogHelper(new SinkWriterImpl(binaryLogSink, TimeProvider.SYSTEM_TIME_PROVIDER, Integer.MAX_VALUE, Integer.MAX_VALUE));
            }
            try {
                String[] strArrSplit = str.split(";", 2);
                int iOptionalInt2 = 0;
                if (strArrSplit.length == 2) {
                    if (!strArrSplit[0].startsWith("h") || !strArrSplit[1].startsWith(Configuration.CHANNEL_UNITS.METER)) {
                        throw new IllegalArgumentException("Illegal log config pattern");
                    }
                    iOptionalInt2 = optionalInt(strArrSplit[0].substring(1));
                    iOptionalInt = optionalInt(strArrSplit[1].substring(1));
                } else if (strArrSplit[0].startsWith("h")) {
                    iOptionalInt2 = optionalInt(strArrSplit[0].substring(1));
                    iOptionalInt = 0;
                } else if (strArrSplit[0].startsWith(Configuration.CHANNEL_UNITS.METER)) {
                    iOptionalInt = optionalInt(strArrSplit[0].substring(1));
                } else {
                    throw new IllegalArgumentException("Illegal log config pattern");
                }
                return new BinlogHelper(new SinkWriterImpl(binaryLogSink, TimeProvider.SYSTEM_TIME_PROVIDER, iOptionalInt2, iOptionalInt));
            } catch (NumberFormatException unused) {
                throw new IllegalArgumentException("Illegal log config pattern");
            }
        }

        static String checkDigits(String str) {
            for (int i = 0; i < str.length(); i++) {
                char cCharAt = str.charAt(i);
                if (cCharAt < '0' || '9' < cCharAt) {
                    throw new IllegalArgumentException("Illegal log config pattern");
                }
            }
            return str;
        }

        static int optionalInt(String str) {
            if (str.isEmpty()) {
                return Integer.MAX_VALUE;
            }
            if (!str.startsWith(":")) {
                throw new IllegalArgumentException("Illegal log config pattern");
            }
            return Integer.parseInt(checkDigits(str.substring(1)));
        }

        static boolean isServiceGlob(String str) {
            return str.endsWith("/*");
        }

        @Override // io.grpc.services.BinlogHelper.Factory
        public BinlogHelper getLog(String str) {
            if (this.blacklistedMethods.contains(str)) {
                return null;
            }
            BinlogHelper binlogHelper = this.perMethodLogs.get(str);
            if (binlogHelper != null) {
                return binlogHelper;
            }
            BinlogHelper binlogHelper2 = this.perServiceLogs.get(MethodDescriptor.extractFullServiceName(str));
            return binlogHelper2 != null ? binlogHelper2 : this.globalLog;
        }
    }

    static final class MaybeTruncated<T> {
        T proto;
        boolean truncated;

        /* synthetic */ MaybeTruncated(Object obj, boolean z, AnonymousClass1 anonymousClass1) {
            this(obj, z);
        }

        private MaybeTruncated(T t, boolean z) {
            this.proto = t;
            this.truncated = z;
        }
    }

    /* renamed from: io.grpc.services.BinlogHelper$1, reason: invalid class name */
    class AnonymousClass1 implements ClientInterceptor {
        final /* synthetic */ long val$callId;
        boolean trailersOnlyResponse = true;

        AnonymousClass1(long j) {
            this.val$callId = j;
        }

        @Override // io.grpc.ClientInterceptor
        public <ReqT, RespT> ClientCall<ReqT, RespT> interceptCall(final MethodDescriptor<ReqT, RespT> methodDescriptor, CallOptions callOptions, Channel channel) {
            final AtomicLong atomicLong = new AtomicLong(1L);
            final String fullMethodName = methodDescriptor.getFullMethodName();
            final String strAuthority = channel.authority();
            final Deadline deadlineMin = BinlogHelper.min(callOptions.getDeadline(), Context.current().getDeadline());
            return new ForwardingClientCall.SimpleForwardingClientCall<ReqT, RespT>(channel.newCall(methodDescriptor, callOptions)) { // from class: io.grpc.services.BinlogHelper.1.1
                @Override // io.grpc.ForwardingClientCall, io.grpc.ClientCall
                public void start(ClientCall.Listener<RespT> listener, io.grpc.Metadata metadata) {
                    Deadline deadline = deadlineMin;
                    BinlogHelper.this.writer.logClientHeader(atomicLong.getAndIncrement(), fullMethodName, strAuthority, deadline == null ? null : Durations.fromNanos(deadline.timeRemaining(TimeUnit.NANOSECONDS)), metadata, GrpcLogEntry.Logger.LOGGER_CLIENT, AnonymousClass1.this.val$callId, null);
                    super.start(new ForwardingClientCallListener.SimpleForwardingClientCallListener<RespT>(listener) { // from class: io.grpc.services.BinlogHelper.1.1.1
                        @Override // io.grpc.ForwardingClientCallListener, io.grpc.ClientCall.Listener
                        public void onMessage(RespT respt) {
                            BinlogHelper.this.writer.logRpcMessage(atomicLong.getAndIncrement(), GrpcLogEntry.EventType.EVENT_TYPE_SERVER_MESSAGE, methodDescriptor.getResponseMarshaller(), respt, GrpcLogEntry.Logger.LOGGER_CLIENT, AnonymousClass1.this.val$callId);
                            super.onMessage(respt);
                        }

                        @Override
                        // io.grpc.ForwardingClientCallListener.SimpleForwardingClientCallListener, io.grpc.ForwardingClientCallListener, io.grpc.PartialForwardingClientCallListener, io.grpc.ClientCall.Listener
                        public void onHeaders(io.grpc.Metadata metadata2) {
                            AnonymousClass1.this.trailersOnlyResponse = false;
                            BinlogHelper.this.writer.logServerHeader(atomicLong.getAndIncrement(), metadata2, GrpcLogEntry.Logger.LOGGER_CLIENT, AnonymousClass1.this.val$callId, BinlogHelper.getPeerSocket(getAttributes()));
                            super.onHeaders(metadata2);
                        }

                        @Override
                        // io.grpc.ForwardingClientCallListener.SimpleForwardingClientCallListener, io.grpc.ForwardingClientCallListener, io.grpc.PartialForwardingClientCallListener, io.grpc.ClientCall.Listener
                        public void onClose(Status status, io.grpc.Metadata metadata2) {
                            BinlogHelper.this.writer.logTrailer(atomicLong.getAndIncrement(), status, metadata2, GrpcLogEntry.Logger.LOGGER_CLIENT, AnonymousClass1.this.val$callId, AnonymousClass1.this.trailersOnlyResponse ? BinlogHelper.getPeerSocket(getAttributes()) : null);
                            super.onClose(status, metadata2);
                        }
                    }, metadata);
                }

                @Override // io.grpc.ForwardingClientCall, io.grpc.ClientCall
                public void sendMessage(ReqT reqt) {
                    BinlogHelper.this.writer.logRpcMessage(atomicLong.getAndIncrement(), GrpcLogEntry.EventType.EVENT_TYPE_CLIENT_MESSAGE, methodDescriptor.getRequestMarshaller(), reqt, GrpcLogEntry.Logger.LOGGER_CLIENT, AnonymousClass1.this.val$callId);
                    super.sendMessage(reqt);
                }

                @Override
                // io.grpc.ForwardingClientCall.SimpleForwardingClientCall, io.grpc.ForwardingClientCall, io.grpc.PartialForwardingClientCall, io.grpc.ClientCall
                public void halfClose() {
                    BinlogHelper.this.writer.logHalfClose(atomicLong.getAndIncrement(), GrpcLogEntry.Logger.LOGGER_CLIENT, AnonymousClass1.this.val$callId);
                    super.halfClose();
                }

                @Override
                // io.grpc.ForwardingClientCall.SimpleForwardingClientCall, io.grpc.ForwardingClientCall, io.grpc.PartialForwardingClientCall, io.grpc.ClientCall
                public void cancel(String str, Throwable th) {
                    BinlogHelper.this.writer.logCancel(atomicLong.getAndIncrement(), GrpcLogEntry.Logger.LOGGER_CLIENT, AnonymousClass1.this.val$callId);
                    super.cancel(str, th);
                }
            };
        }
    }
}
