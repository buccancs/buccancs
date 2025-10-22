package io.grpc.netty.shaded.io.netty.handler.codec.socksx.v4;

import io.grpc.netty.shaded.io.netty.util.internal.ObjectUtil;

/* loaded from: classes3.dex */
public class Socks4CommandStatus implements Comparable<Socks4CommandStatus> {
    public static final Socks4CommandStatus SUCCESS = new Socks4CommandStatus(90, "SUCCESS");
    public static final Socks4CommandStatus REJECTED_OR_FAILED = new Socks4CommandStatus(91, "REJECTED_OR_FAILED");
    public static final Socks4CommandStatus IDENTD_UNREACHABLE = new Socks4CommandStatus(92, "IDENTD_UNREACHABLE");
    public static final Socks4CommandStatus IDENTD_AUTH_FAILURE = new Socks4CommandStatus(93, "IDENTD_AUTH_FAILURE");
    private final byte byteValue;
    private final String name;
    private String text;

    public Socks4CommandStatus(int i) {
        this(i, "UNKNOWN");
    }

    public Socks4CommandStatus(int i, String str) {
        this.name = (String) ObjectUtil.checkNotNull(str, "name");
        this.byteValue = (byte) i;
    }

    public static Socks4CommandStatus valueOf(byte b) {
        switch (b) {
            case RESET_TO_DEFAULT_CONFIGURATION_COMMAND_VALUE:
                return SUCCESS;
            case 91:
                return REJECTED_OR_FAILED;
            case 92:
                return IDENTD_UNREACHABLE;
            case 93:
                return IDENTD_AUTH_FAILURE;
            default:
                return new Socks4CommandStatus(b);
        }
    }

    public byte byteValue() {
        return this.byteValue;
    }

    public int hashCode() {
        return this.byteValue;
    }

    public boolean isSuccess() {
        return this.byteValue == 90;
    }

    public boolean equals(Object obj) {
        return (obj instanceof Socks4CommandStatus) && this.byteValue == ((Socks4CommandStatus) obj).byteValue;
    }

    @Override // java.lang.Comparable
    public int compareTo(Socks4CommandStatus socks4CommandStatus) {
        return this.byteValue - socks4CommandStatus.byteValue;
    }

    public String toString() {
        String str = this.text;
        if (str != null) {
            return str;
        }
        String str2 = this.name + '(' + (this.byteValue & 255) + ')';
        this.text = str2;
        return str2;
    }
}
