package io.grpc.xds;

import java.util.concurrent.ThreadLocalRandom;

/* loaded from: classes3.dex */
interface ThreadSafeRandom {
    int nextInt(int i);

    public static final class ThreadSafeRandomImpl implements ThreadSafeRandom {
        static final ThreadSafeRandom instance = new ThreadSafeRandomImpl();

        private ThreadSafeRandomImpl() {
        }

        @Override // io.grpc.xds.ThreadSafeRandom
        public int nextInt(int i) {
            return ThreadLocalRandom.current().nextInt(i);
        }
    }
}
