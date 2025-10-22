package io.grpc.netty.shaded.io.netty.handler.codec.http2;

import com.fasterxml.jackson.core.JsonPointer;
import io.grpc.netty.shaded.io.netty.channel.ChannelId;

/* loaded from: classes3.dex */
final class Http2StreamChannelId implements ChannelId {
    private static final long serialVersionUID = -6642338822166867585L;
    private final int id;
    private final ChannelId parentId;

    Http2StreamChannelId(ChannelId channelId, int i) {
        this.parentId = channelId;
        this.id = i;
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.ChannelId
    public String asShortText() {
        return this.parentId.asShortText() + JsonPointer.SEPARATOR + this.id;
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.ChannelId
    public String asLongText() {
        return this.parentId.asLongText() + JsonPointer.SEPARATOR + this.id;
    }

    @Override // java.lang.Comparable
    public int compareTo(ChannelId channelId) {
        if (channelId instanceof Http2StreamChannelId) {
            Http2StreamChannelId http2StreamChannelId = (Http2StreamChannelId) channelId;
            int iCompareTo = this.parentId.compareTo(http2StreamChannelId.parentId);
            return iCompareTo == 0 ? this.id - http2StreamChannelId.id : iCompareTo;
        }
        return this.parentId.compareTo(channelId);
    }

    public int hashCode() {
        return (this.id * 31) + this.parentId.hashCode();
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof Http2StreamChannelId)) {
            return false;
        }
        Http2StreamChannelId http2StreamChannelId = (Http2StreamChannelId) obj;
        return this.id == http2StreamChannelId.id && this.parentId.equals(http2StreamChannelId.parentId);
    }

    public String toString() {
        return asShortText();
    }
}
