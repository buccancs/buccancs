package io.grpc.netty.shaded.io.netty.channel;

import io.grpc.netty.shaded.io.netty.buffer.ByteBufUtil;
import io.grpc.netty.shaded.io.netty.util.internal.MacAddressUtil;
import io.grpc.netty.shaded.io.netty.util.internal.PlatformDependent;
import io.grpc.netty.shaded.io.netty.util.internal.SystemPropertyUtil;
import io.grpc.netty.shaded.io.netty.util.internal.logging.InternalLogger;
import io.grpc.netty.shaded.io.netty.util.internal.logging.InternalLoggerFactory;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

/* loaded from: classes3.dex */
public final class DefaultChannelId implements ChannelId {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final byte[] MACHINE_ID;
    private static final int PROCESS_ID;
    private static final int PROCESS_ID_LEN = 4;
    private static final int RANDOM_LEN = 4;
    private static final int SEQUENCE_LEN = 4;
    private static final int TIMESTAMP_LEN = 8;
    private static final InternalLogger logger = InternalLoggerFactory.getInstance((Class<?>) DefaultChannelId.class);
    private static final AtomicInteger nextSequence = new AtomicInteger();
    private static final long serialVersionUID = 3884076183504074063L;

    static {
        int i;
        String str = SystemPropertyUtil.get("io.grpc.netty.shaded.io.netty.processId");
        int iDefaultProcessId = -1;
        if (str != null) {
            try {
                i = Integer.parseInt(str);
            } catch (NumberFormatException unused) {
                i = -1;
            }
            if (i < 0) {
                logger.warn("-Dio.netty.processId: {} (malformed)", str);
            } else {
                InternalLogger internalLogger = logger;
                if (internalLogger.isDebugEnabled()) {
                    internalLogger.debug("-Dio.netty.processId: {} (user-set)", Integer.valueOf(i));
                }
                iDefaultProcessId = i;
            }
        }
        if (iDefaultProcessId < 0) {
            iDefaultProcessId = defaultProcessId();
            InternalLogger internalLogger2 = logger;
            if (internalLogger2.isDebugEnabled()) {
                internalLogger2.debug("-Dio.netty.processId: {} (auto-detected)", Integer.valueOf(iDefaultProcessId));
            }
        }
        PROCESS_ID = iDefaultProcessId;
        String str2 = SystemPropertyUtil.get("io.grpc.netty.shaded.io.netty.machineId");
        byte[] mac = null;
        if (str2 != null) {
            try {
                mac = MacAddressUtil.parseMAC(str2);
            } catch (Exception e) {
                logger.warn("-Dio.netty.machineId: {} (malformed)", str2, e);
            }
            if (mac != null) {
                logger.debug("-Dio.netty.machineId: {} (user-set)", str2);
            }
        }
        if (mac == null) {
            mac = MacAddressUtil.defaultMachineId();
            InternalLogger internalLogger3 = logger;
            if (internalLogger3.isDebugEnabled()) {
                internalLogger3.debug("-Dio.netty.machineId: {} (auto-detected)", MacAddressUtil.formatAddress(mac));
            }
        }
        MACHINE_ID = mac;
    }

    private final byte[] data;
    private final int hashCode;
    private transient String longValue;
    private transient String shortValue;

    private DefaultChannelId() {
        byte[] bArr = MACHINE_ID;
        byte[] bArr2 = new byte[bArr.length + 20];
        this.data = bArr2;
        System.arraycopy(bArr, 0, bArr2, 0, bArr.length);
        writeInt(writeLong(writeInt(writeInt(bArr.length, PROCESS_ID), nextSequence.getAndIncrement()), Long.reverse(System.nanoTime()) ^ System.currentTimeMillis()), PlatformDependent.threadLocalRandom().nextInt());
        this.hashCode = Arrays.hashCode(bArr2);
    }

    public static DefaultChannelId newInstance() {
        return new DefaultChannelId();
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x0069  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x0076  */
    /* JADX WARN: Removed duplicated region for block: B:32:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static int defaultProcessId() throws java.lang.NumberFormatException {
        /*
            r0 = 1
            r1 = 0
            java.lang.Class<io.grpc.netty.shaded.io.netty.channel.DefaultChannelId> r2 = io.grpc.netty.shaded.io.netty.channel.DefaultChannelId.class
            java.lang.ClassLoader r2 = io.grpc.netty.shaded.io.netty.util.internal.PlatformDependent.getClassLoader(r2)     // Catch: java.lang.Throwable -> L35
            java.lang.String r3 = "java.lang.management.ManagementFactory"
            java.lang.Class r3 = java.lang.Class.forName(r3, r0, r2)     // Catch: java.lang.Throwable -> L33
            java.lang.String r4 = "java.lang.management.RuntimeMXBean"
            java.lang.Class r4 = java.lang.Class.forName(r4, r0, r2)     // Catch: java.lang.Throwable -> L33
            java.lang.String r5 = "getRuntimeMXBean"
            java.lang.Class<?>[] r6 = io.grpc.netty.shaded.io.netty.util.internal.EmptyArrays.EMPTY_CLASSES     // Catch: java.lang.Throwable -> L33
            java.lang.reflect.Method r3 = r3.getMethod(r5, r6)     // Catch: java.lang.Throwable -> L33
            java.lang.Object[] r5 = io.grpc.netty.shaded.io.netty.util.internal.EmptyArrays.EMPTY_OBJECTS     // Catch: java.lang.Throwable -> L33
            java.lang.Object r3 = r3.invoke(r1, r5)     // Catch: java.lang.Throwable -> L33
            java.lang.String r5 = "getName"
            java.lang.Class<?>[] r6 = io.grpc.netty.shaded.io.netty.util.internal.EmptyArrays.EMPTY_CLASSES     // Catch: java.lang.Throwable -> L33
            java.lang.reflect.Method r4 = r4.getMethod(r5, r6)     // Catch: java.lang.Throwable -> L33
            java.lang.Object[] r5 = io.grpc.netty.shaded.io.netty.util.internal.EmptyArrays.EMPTY_OBJECTS     // Catch: java.lang.Throwable -> L33
            java.lang.Object r3 = r4.invoke(r3, r5)     // Catch: java.lang.Throwable -> L33
            java.lang.String r3 = (java.lang.String) r3     // Catch: java.lang.Throwable -> L33
            goto L61
        L33:
            r3 = move-exception
            goto L37
        L35:
            r3 = move-exception
            r2 = r1
        L37:
            io.grpc.netty.shaded.io.netty.util.internal.logging.InternalLogger r4 = io.grpc.netty.shaded.io.netty.channel.DefaultChannelId.logger
            java.lang.String r5 = "Could not invoke ManagementFactory.getRuntimeMXBean().getName(); Android?"
            r4.debug(r5, r3)
            java.lang.String r3 = "android.os.Process"
            java.lang.Class r0 = java.lang.Class.forName(r3, r0, r2)     // Catch: java.lang.Throwable -> L57
            java.lang.String r2 = "myPid"
            java.lang.Class<?>[] r3 = io.grpc.netty.shaded.io.netty.util.internal.EmptyArrays.EMPTY_CLASSES     // Catch: java.lang.Throwable -> L57
            java.lang.reflect.Method r0 = r0.getMethod(r2, r3)     // Catch: java.lang.Throwable -> L57
            java.lang.Object[] r2 = io.grpc.netty.shaded.io.netty.util.internal.EmptyArrays.EMPTY_OBJECTS     // Catch: java.lang.Throwable -> L57
            java.lang.Object r0 = r0.invoke(r1, r2)     // Catch: java.lang.Throwable -> L57
            java.lang.String r3 = r0.toString()     // Catch: java.lang.Throwable -> L57
            goto L61
        L57:
            r0 = move-exception
            io.grpc.netty.shaded.io.netty.util.internal.logging.InternalLogger r1 = io.grpc.netty.shaded.io.netty.channel.DefaultChannelId.logger
            java.lang.String r2 = "Could not invoke Process.myPid(); not Android?"
            r1.debug(r2, r0)
            java.lang.String r3 = ""
        L61:
            r0 = 64
            int r0 = r3.indexOf(r0)
            if (r0 < 0) goto L6e
            r1 = 0
            java.lang.String r3 = r3.substring(r1, r0)
        L6e:
            int r0 = java.lang.Integer.parseInt(r3)     // Catch: java.lang.NumberFormatException -> L73
            goto L74
        L73:
            r0 = -1
        L74:
            if (r0 >= 0) goto L89
            java.util.Random r0 = io.grpc.netty.shaded.io.netty.util.internal.PlatformDependent.threadLocalRandom()
            int r0 = r0.nextInt()
            io.grpc.netty.shaded.io.netty.util.internal.logging.InternalLogger r1 = io.grpc.netty.shaded.io.netty.channel.DefaultChannelId.logger
            java.lang.String r2 = "Failed to find the current process ID from '{}'; using a random value: {}"
            java.lang.Integer r4 = java.lang.Integer.valueOf(r0)
            r1.warn(r2, r3, r4)
        L89:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: io.grpc.netty.shaded.io.netty.channel.DefaultChannelId.defaultProcessId():int");
    }

    public int hashCode() {
        return this.hashCode;
    }

    private int writeInt(int i, int i2) {
        byte[] bArr = this.data;
        bArr[i] = (byte) (i2 >>> 24);
        bArr[i + 1] = (byte) (i2 >>> 16);
        int i3 = i + 3;
        bArr[i + 2] = (byte) (i2 >>> 8);
        int i4 = i + 4;
        bArr[i3] = (byte) i2;
        return i4;
    }

    private int writeLong(int i, long j) {
        byte[] bArr = this.data;
        bArr[i] = (byte) (j >>> 56);
        bArr[i + 1] = (byte) (j >>> 48);
        bArr[i + 2] = (byte) (j >>> 40);
        bArr[i + 3] = (byte) (j >>> 32);
        bArr[i + 4] = (byte) (j >>> 24);
        bArr[i + 5] = (byte) (j >>> 16);
        int i2 = i + 7;
        bArr[i + 6] = (byte) (j >>> 8);
        int i3 = i + 8;
        bArr[i2] = (byte) j;
        return i3;
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.ChannelId
    public String asShortText() {
        String str = this.shortValue;
        if (str != null) {
            return str;
        }
        byte[] bArr = this.data;
        String strHexDump = ByteBufUtil.hexDump(bArr, bArr.length - 4, 4);
        this.shortValue = strHexDump;
        return strHexDump;
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.ChannelId
    public String asLongText() {
        String str = this.longValue;
        if (str != null) {
            return str;
        }
        String strNewLongValue = newLongValue();
        this.longValue = strNewLongValue;
        return strNewLongValue;
    }

    private String newLongValue() {
        StringBuilder sb = new StringBuilder((this.data.length * 2) + 5);
        appendHexDumpField(sb, appendHexDumpField(sb, appendHexDumpField(sb, appendHexDumpField(sb, appendHexDumpField(sb, 0, MACHINE_ID.length), 4), 4), 8), 4);
        return sb.substring(0, sb.length() - 1);
    }

    private int appendHexDumpField(StringBuilder sb, int i, int i2) {
        sb.append(ByteBufUtil.hexDump(this.data, i, i2));
        sb.append('-');
        return i + i2;
    }

    @Override // java.lang.Comparable
    public int compareTo(ChannelId channelId) {
        if (this == channelId) {
            return 0;
        }
        if (channelId instanceof DefaultChannelId) {
            byte[] bArr = ((DefaultChannelId) channelId).data;
            int length = this.data.length;
            int length2 = bArr.length;
            int iMin = Math.min(length, length2);
            for (int i = 0; i < iMin; i++) {
                byte b = this.data[i];
                byte b2 = bArr[i];
                if (b != b2) {
                    return (b & 255) - (b2 & 255);
                }
            }
            return length - length2;
        }
        return asLongText().compareTo(channelId.asLongText());
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof DefaultChannelId)) {
            return false;
        }
        DefaultChannelId defaultChannelId = (DefaultChannelId) obj;
        return this.hashCode == defaultChannelId.hashCode && Arrays.equals(this.data, defaultChannelId.data);
    }

    public String toString() {
        return asShortText();
    }
}
