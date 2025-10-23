package io.opencensus.trace;

import io.opencensus.common.Timestamp;
import io.opencensus.internal.Utils;
import io.opencensus.trace.AutoValue_NetworkEvent;

import javax.annotation.Nullable;

@Deprecated
/* loaded from: classes4.dex */
public abstract class NetworkEvent extends BaseMessageEvent {

    NetworkEvent() {
    }

    public static Builder builder(Type type, long j) {
        return new AutoValue_NetworkEvent.Builder().setType((Type) Utils.checkNotNull(type, "type")).setMessageId(j).setUncompressedMessageSize(0L).setCompressedMessageSize(0L);
    }

    public abstract long getCompressedMessageSize();

    @Nullable
    public abstract Timestamp getKernelTimestamp();

    public abstract long getMessageId();

    public abstract Type getType();

    public abstract long getUncompressedMessageSize();

    @Deprecated
    public long getMessageSize() {
        return getUncompressedMessageSize();
    }

    public enum Type {
        SENT,
        RECV
    }

    @Deprecated
    public static abstract class Builder {
        Builder() {
        }

        public abstract NetworkEvent build();

        public abstract Builder setCompressedMessageSize(long j);

        public abstract Builder setKernelTimestamp(@Nullable Timestamp timestamp);

        abstract Builder setMessageId(long j);

        abstract Builder setType(Type type);

        public abstract Builder setUncompressedMessageSize(long j);

        @Deprecated
        public Builder setMessageSize(long j) {
            return setUncompressedMessageSize(j);
        }
    }
}
