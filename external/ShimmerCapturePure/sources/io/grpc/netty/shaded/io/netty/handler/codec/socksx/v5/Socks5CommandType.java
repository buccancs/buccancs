package io.grpc.netty.shaded.io.netty.handler.codec.socksx.v5;

import com.google.api.client.http.HttpMethods;
import io.grpc.netty.shaded.io.netty.util.internal.ObjectUtil;

/* loaded from: classes3.dex */
public class Socks5CommandType implements Comparable<Socks5CommandType> {
    public static final Socks5CommandType CONNECT = new Socks5CommandType(1, HttpMethods.CONNECT);
    public static final Socks5CommandType BIND = new Socks5CommandType(2, "BIND");
    public static final Socks5CommandType UDP_ASSOCIATE = new Socks5CommandType(3, "UDP_ASSOCIATE");
    private final byte byteValue;
    private final String name;
    private String text;

    public Socks5CommandType(int i) {
        this(i, "UNKNOWN");
    }

    public Socks5CommandType(int i, String str) {
        this.name = (String) ObjectUtil.checkNotNull(str, "name");
        this.byteValue = (byte) i;
    }

    public static Socks5CommandType valueOf(byte b) {
        return b != 1 ? b != 2 ? b != 3 ? new Socks5CommandType(b) : UDP_ASSOCIATE : BIND : CONNECT;
    }

    public byte byteValue() {
        return this.byteValue;
    }

    public int hashCode() {
        return this.byteValue;
    }

    public boolean equals(Object obj) {
        return (obj instanceof Socks5CommandType) && this.byteValue == ((Socks5CommandType) obj).byteValue;
    }

    @Override // java.lang.Comparable
    public int compareTo(Socks5CommandType socks5CommandType) {
        return this.byteValue - socks5CommandType.byteValue;
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
