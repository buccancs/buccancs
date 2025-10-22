package io.grpc.netty.shaded.io.netty.handler.codec.http;

import java.util.Set;

@Deprecated
/* loaded from: classes3.dex */
public interface Cookie extends io.grpc.netty.shaded.io.netty.handler.codec.http.cookie.Cookie {
    @Deprecated
    String comment();

    @Deprecated
    String commentUrl();

    @Deprecated
    String getComment();

    @Deprecated
    void setComment(String str);

    @Deprecated
    String getCommentUrl();

    @Deprecated
    void setCommentUrl(String str);

    @Deprecated
    String getDomain();

    @Deprecated
    long getMaxAge();

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.cookie.Cookie
    @Deprecated
    void setMaxAge(long j);

    @Deprecated
    String getName();

    @Deprecated
    String getPath();

    @Deprecated
    Set<Integer> getPorts();

    @Deprecated
    void setPorts(Iterable<Integer> iterable);

    @Deprecated
    void setPorts(int... iArr);

    @Deprecated
    String getValue();

    @Deprecated
    int getVersion();

    @Deprecated
    void setVersion(int i);

    @Deprecated
    boolean isDiscard();

    @Deprecated
    void setDiscard(boolean z);

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.cookie.Cookie
    @Deprecated
    long maxAge();

    @Deprecated
    Set<Integer> ports();

    @Deprecated
    int version();
}
