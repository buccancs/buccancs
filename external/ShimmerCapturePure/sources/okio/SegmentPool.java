package okio;

import io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2CodecUtil;

import javax.annotation.Nullable;

/* loaded from: classes5.dex */
final class SegmentPool {
    static final long MAX_SIZE = 65536;
    static long byteCount;

    @Nullable
    static Segment next;

    private SegmentPool() {
    }

    static Segment take() {
        synchronized (SegmentPool.class) {
            Segment segment = next;
            if (segment != null) {
                next = segment.next;
                segment.next = null;
                byteCount -= Http2CodecUtil.DEFAULT_HEADER_LIST_SIZE;
                return segment;
            }
            return new Segment();
        }
    }

    static void recycle(Segment segment) {
        if (segment.next != null || segment.prev != null) {
            throw new IllegalArgumentException();
        }
        if (segment.shared) {
            return;
        }
        synchronized (SegmentPool.class) {
            long j = byteCount;
            if (j + Http2CodecUtil.DEFAULT_HEADER_LIST_SIZE > MAX_SIZE) {
                return;
            }
            byteCount = j + Http2CodecUtil.DEFAULT_HEADER_LIST_SIZE;
            segment.next = next;
            segment.limit = 0;
            segment.pos = 0;
            next = segment;
        }
    }
}
