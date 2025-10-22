package io.opencensus.common;

import io.opencensus.common.ServerStatsFieldEnums;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* loaded from: classes4.dex */
public final class ServerStatsEncoding {
    public static final byte CURRENT_VERSION = 0;

    private ServerStatsEncoding() {
    }

    public static byte[] toBytes(ServerStats serverStats) {
        ByteBuffer byteBufferAllocate = ByteBuffer.allocate(ServerStatsFieldEnums.getTotalSize() + 1);
        byteBufferAllocate.order(ByteOrder.LITTLE_ENDIAN);
        byteBufferAllocate.put((byte) 0);
        byteBufferAllocate.put((byte) ServerStatsFieldEnums.Id.SERVER_STATS_LB_LATENCY_ID.value());
        byteBufferAllocate.putLong(serverStats.getLbLatencyNs());
        byteBufferAllocate.put((byte) ServerStatsFieldEnums.Id.SERVER_STATS_SERVICE_LATENCY_ID.value());
        byteBufferAllocate.putLong(serverStats.getServiceLatencyNs());
        byteBufferAllocate.put((byte) ServerStatsFieldEnums.Id.SERVER_STATS_TRACE_OPTION_ID.value());
        byteBufferAllocate.put(serverStats.getTraceOption());
        return byteBufferAllocate.array();
    }

    public static ServerStats parseBytes(byte[] bArr) throws ServerStatsDeserializationException {
        ByteBuffer byteBufferWrap = ByteBuffer.wrap(bArr);
        byteBufferWrap.order(ByteOrder.LITTLE_ENDIAN);
        if (!byteBufferWrap.hasRemaining()) {
            throw new ServerStatsDeserializationException("Serialized ServerStats buffer is empty");
        }
        byte b = byteBufferWrap.get();
        if (b > 0 || b < 0) {
            throw new ServerStatsDeserializationException("Invalid ServerStats version: " + ((int) b));
        }
        long j = 0;
        long j2 = 0;
        byte b2 = 0;
        while (byteBufferWrap.hasRemaining()) {
            ServerStatsFieldEnums.Id idValueOf = ServerStatsFieldEnums.Id.valueOf(byteBufferWrap.get() & 255);
            if (idValueOf == null) {
                byteBufferWrap.position(byteBufferWrap.limit());
            } else {
                int i = AnonymousClass1.$SwitchMap$io$opencensus$common$ServerStatsFieldEnums$Id[idValueOf.ordinal()];
                if (i == 1) {
                    j = byteBufferWrap.getLong();
                } else if (i == 2) {
                    j2 = byteBufferWrap.getLong();
                } else if (i == 3) {
                    b2 = byteBufferWrap.get();
                }
            }
        }
        try {
            return ServerStats.create(j, j2, b2);
        } catch (IllegalArgumentException e) {
            throw new ServerStatsDeserializationException("Serialized ServiceStats contains invalid values: " + e.getMessage());
        }
    }

    /* renamed from: io.opencensus.common.ServerStatsEncoding$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$io$opencensus$common$ServerStatsFieldEnums$Id;

        static {
            int[] iArr = new int[ServerStatsFieldEnums.Id.values().length];
            $SwitchMap$io$opencensus$common$ServerStatsFieldEnums$Id = iArr;
            try {
                iArr[ServerStatsFieldEnums.Id.SERVER_STATS_LB_LATENCY_ID.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$io$opencensus$common$ServerStatsFieldEnums$Id[ServerStatsFieldEnums.Id.SERVER_STATS_SERVICE_LATENCY_ID.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$io$opencensus$common$ServerStatsFieldEnums$Id[ServerStatsFieldEnums.Id.SERVER_STATS_TRACE_OPTION_ID.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }
}
