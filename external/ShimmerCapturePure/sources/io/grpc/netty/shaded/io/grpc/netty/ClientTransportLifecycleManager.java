package io.grpc.netty.shaded.io.grpc.netty;

import io.grpc.Status;
import io.grpc.internal.ManagedClientTransport;

/* loaded from: classes2.dex */
final class ClientTransportLifecycleManager {
    private final ManagedClientTransport.Listener listener;
    private Status shutdownStatus;
    private Throwable shutdownThrowable;
    private boolean transportInUse;
    private boolean transportReady;
    private boolean transportShutdown;
    private boolean transportTerminated;

    public ClientTransportLifecycleManager(ManagedClientTransport.Listener listener) {
        this.listener = listener;
    }

    public Status getShutdownStatus() {
        return this.shutdownStatus;
    }

    public Throwable getShutdownThrowable() {
        return this.shutdownThrowable;
    }

    public void notifyReady() {
        if (this.transportReady || this.transportShutdown) {
            return;
        }
        this.transportReady = true;
        this.listener.transportReady();
    }

    public void notifyGracefulShutdown(Status status) {
        if (this.transportShutdown) {
            return;
        }
        this.transportShutdown = true;
        this.listener.transportShutdown(status);
    }

    public void notifyShutdown(Status status) {
        notifyGracefulShutdown(status);
        if (this.shutdownStatus != null) {
            return;
        }
        this.shutdownStatus = status;
        this.shutdownThrowable = status.asException();
    }

    public void notifyInUse(boolean z) {
        if (z == this.transportInUse) {
            return;
        }
        this.transportInUse = z;
        this.listener.transportInUse(z);
    }

    public void notifyTerminated(Status status) {
        if (this.transportTerminated) {
            return;
        }
        this.transportTerminated = true;
        notifyShutdown(status);
        this.listener.transportTerminated();
    }
}
