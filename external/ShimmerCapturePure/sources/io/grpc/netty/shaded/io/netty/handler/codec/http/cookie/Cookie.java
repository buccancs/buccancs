package io.grpc.netty.shaded.io.netty.handler.codec.http.cookie;

/* loaded from: classes3.dex */
public interface Cookie extends Comparable<Cookie> {
    public static final long UNDEFINED_MAX_AGE = Long.MIN_VALUE;

    String domain();

    boolean isHttpOnly();

    void setHttpOnly(boolean z);

    boolean isSecure();

    void setSecure(boolean z);

    long maxAge();

    String name();

    String path();

    void setDomain(String str);

    void setMaxAge(long j);

    void setPath(String str);

    void setValue(String str);

    void setWrap(boolean z);

    String value();

    boolean wrap();
}
