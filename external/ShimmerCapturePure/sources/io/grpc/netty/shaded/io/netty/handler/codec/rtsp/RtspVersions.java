package io.grpc.netty.shaded.io.netty.handler.codec.rtsp;

import io.grpc.netty.shaded.io.netty.handler.codec.http.HttpVersion;
import io.grpc.netty.shaded.io.netty.util.internal.ObjectUtil;
import org.bouncycastle.i18n.TextBundle;

/* loaded from: classes3.dex */
public final class RtspVersions {
    public static final HttpVersion RTSP_1_0 = new HttpVersion("RTSP", 1, 0, true);

    private RtspVersions() {
    }

    public static HttpVersion valueOf(String str) {
        ObjectUtil.checkNotNull(str, TextBundle.TEXT_ENTRY);
        String upperCase = str.trim().toUpperCase();
        return "RTSP/1.0".equals(upperCase) ? RTSP_1_0 : new HttpVersion(upperCase, true);
    }
}
