package io.grpc.netty.shaded.io.netty.handler.codec.http2;

/* loaded from: classes3.dex */
public enum Http2Error {
    NO_ERROR(0),
    PROTOCOL_ERROR(1),
    INTERNAL_ERROR(2),
    FLOW_CONTROL_ERROR(3),
    SETTINGS_TIMEOUT(4),
    STREAM_CLOSED(5),
    FRAME_SIZE_ERROR(6),
    REFUSED_STREAM(7),
    CANCEL(8),
    COMPRESSION_ERROR(9),
    CONNECT_ERROR(10),
    ENHANCE_YOUR_CALM(11),
    INADEQUATE_SECURITY(12),
    HTTP_1_1_REQUIRED(13);

    private static final Http2Error[] INT_TO_ENUM_MAP;

    static {
        Http2Error[] http2ErrorArrValues = values();
        Http2Error[] http2ErrorArr = new Http2Error[http2ErrorArrValues.length];
        for (Http2Error http2Error : http2ErrorArrValues) {
            http2ErrorArr[(int) http2Error.code()] = http2Error;
        }
        INT_TO_ENUM_MAP = http2ErrorArr;
    }

    private final long code;

    Http2Error(long j) {
        this.code = j;
    }

    public static Http2Error valueOf(long j) {
        Http2Error[] http2ErrorArr = INT_TO_ENUM_MAP;
        if (j >= http2ErrorArr.length || j < 0) {
            return null;
        }
        return http2ErrorArr[(int) j];
    }

    public long code() {
        return this.code;
    }
}
