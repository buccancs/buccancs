package io.grpc.netty.shaded.io.netty.channel;

import io.grpc.netty.shaded.io.netty.buffer.ByteBuf;
import io.grpc.netty.shaded.io.netty.buffer.ByteBufAllocator;
import io.grpc.netty.shaded.io.netty.util.UncheckedBooleanSupplier;
import io.grpc.netty.shaded.io.netty.util.internal.ObjectUtil;

/* loaded from: classes3.dex */
public interface RecvByteBufAllocator {

    Handle newHandle();

    public interface ExtendedHandle extends Handle {
        boolean continueReading(UncheckedBooleanSupplier uncheckedBooleanSupplier);
    }

    @Deprecated
    public interface Handle {
        ByteBuf allocate(ByteBufAllocator byteBufAllocator);

        int attemptedBytesRead();

        void attemptedBytesRead(int i);

        boolean continueReading();

        int guess();

        void incMessagesRead(int i);

        int lastBytesRead();

        void lastBytesRead(int i);

        void readComplete();

        void reset(ChannelConfig channelConfig);
    }

    public static class DelegatingHandle implements Handle {
        private final Handle delegate;

        public DelegatingHandle(Handle handle) {
            this.delegate = (Handle) ObjectUtil.checkNotNull(handle, "delegate");
        }

        protected final Handle delegate() {
            return this.delegate;
        }

        @Override // io.grpc.netty.shaded.io.netty.channel.RecvByteBufAllocator.Handle
        public ByteBuf allocate(ByteBufAllocator byteBufAllocator) {
            return this.delegate.allocate(byteBufAllocator);
        }

        @Override // io.grpc.netty.shaded.io.netty.channel.RecvByteBufAllocator.Handle
        public int guess() {
            return this.delegate.guess();
        }

        @Override // io.grpc.netty.shaded.io.netty.channel.RecvByteBufAllocator.Handle
        public void reset(ChannelConfig channelConfig) {
            this.delegate.reset(channelConfig);
        }

        @Override // io.grpc.netty.shaded.io.netty.channel.RecvByteBufAllocator.Handle
        public void incMessagesRead(int i) {
            this.delegate.incMessagesRead(i);
        }

        @Override // io.grpc.netty.shaded.io.netty.channel.RecvByteBufAllocator.Handle
        public void lastBytesRead(int i) {
            this.delegate.lastBytesRead(i);
        }

        @Override // io.grpc.netty.shaded.io.netty.channel.RecvByteBufAllocator.Handle
        public int lastBytesRead() {
            return this.delegate.lastBytesRead();
        }

        @Override // io.grpc.netty.shaded.io.netty.channel.RecvByteBufAllocator.Handle
        public boolean continueReading() {
            return this.delegate.continueReading();
        }

        @Override // io.grpc.netty.shaded.io.netty.channel.RecvByteBufAllocator.Handle
        public int attemptedBytesRead() {
            return this.delegate.attemptedBytesRead();
        }

        @Override // io.grpc.netty.shaded.io.netty.channel.RecvByteBufAllocator.Handle
        public void attemptedBytesRead(int i) {
            this.delegate.attemptedBytesRead(i);
        }

        @Override // io.grpc.netty.shaded.io.netty.channel.RecvByteBufAllocator.Handle
        public void readComplete() {
            this.delegate.readComplete();
        }
    }
}
