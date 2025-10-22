package io.grpc.netty.shaded.io.netty.internal.tcnative;

/* loaded from: classes3.dex */
abstract class SSLTask implements Runnable {
    private final long ssl;
    private boolean complete;
    private int returnValue;

    protected SSLTask(long j) {
        this.ssl = j;
    }

    protected abstract int runTask(long j);

    @Override // java.lang.Runnable
    public final void run() {
        if (this.complete) {
            return;
        }
        this.complete = true;
        this.returnValue = runTask(this.ssl);
    }
}
