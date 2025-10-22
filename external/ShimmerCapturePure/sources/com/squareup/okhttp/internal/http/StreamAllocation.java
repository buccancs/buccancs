package com.squareup.okhttp.internal.http;

import com.squareup.okhttp.Address;
import com.squareup.okhttp.ConnectionPool;
import com.squareup.okhttp.internal.Internal;
import com.squareup.okhttp.internal.RouteDatabase;
import com.squareup.okhttp.internal.io.RealConnection;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.lang.ref.WeakReference;
import java.net.ProtocolException;
import java.net.SocketTimeoutException;
import java.security.cert.CertificateException;
import java.util.concurrent.TimeUnit;
import javax.net.ssl.SSLHandshakeException;
import javax.net.ssl.SSLPeerUnverifiedException;

import okio.Sink;

/* loaded from: classes2.dex */
public final class StreamAllocation {
    public final Address address;
    private final ConnectionPool connectionPool;
    private boolean canceled;
    private RealConnection connection;
    private boolean released;
    private RouteSelector routeSelector;
    private HttpStream stream;

    public StreamAllocation(ConnectionPool connectionPool, Address address) {
        this.connectionPool = connectionPool;
        this.address = address;
    }

    public HttpStream newStream(int i, int i2, int i3, boolean z, boolean z2) throws RouteException, IOException {
        HttpStream http1xStream;
        try {
            RealConnection realConnectionFindHealthyConnection = findHealthyConnection(i, i2, i3, z, z2);
            if (realConnectionFindHealthyConnection.framedConnection != null) {
                http1xStream = new Http2xStream(this, realConnectionFindHealthyConnection.framedConnection);
            } else {
                realConnectionFindHealthyConnection.getSocket().setSoTimeout(i2);
                realConnectionFindHealthyConnection.source.timeout().timeout(i2, TimeUnit.MILLISECONDS);
                realConnectionFindHealthyConnection.sink.timeout().timeout(i3, TimeUnit.MILLISECONDS);
                http1xStream = new Http1xStream(this, realConnectionFindHealthyConnection.source, realConnectionFindHealthyConnection.sink);
            }
            synchronized (this.connectionPool) {
                realConnectionFindHealthyConnection.streamCount++;
                this.stream = http1xStream;
            }
            return http1xStream;
        } catch (IOException e) {
            throw new RouteException(e);
        }
    }

    private RealConnection findHealthyConnection(int i, int i2, int i3, boolean z, boolean z2) throws RouteException, IOException {
        while (true) {
            RealConnection realConnectionFindConnection = findConnection(i, i2, i3, z);
            synchronized (this.connectionPool) {
                if (realConnectionFindConnection.streamCount == 0) {
                    return realConnectionFindConnection;
                }
                if (realConnectionFindConnection.isHealthy(z2)) {
                    return realConnectionFindConnection;
                }
                connectionFailed();
            }
        }
    }

    private RealConnection findConnection(int i, int i2, int i3, boolean z) throws RouteException, IOException {
        synchronized (this.connectionPool) {
            if (this.released) {
                throw new IllegalStateException("released");
            }
            if (this.stream != null) {
                throw new IllegalStateException("stream != null");
            }
            if (this.canceled) {
                throw new IOException("Canceled");
            }
            RealConnection realConnection = this.connection;
            if (realConnection != null && !realConnection.noNewStreams) {
                return realConnection;
            }
            RealConnection realConnection2 = Internal.instance.get(this.connectionPool, this.address, this);
            if (realConnection2 != null) {
                this.connection = realConnection2;
                return realConnection2;
            }
            if (this.routeSelector == null) {
                this.routeSelector = new RouteSelector(this.address, routeDatabase());
            }
            RealConnection realConnection3 = new RealConnection(this.routeSelector.next());
            acquire(realConnection3);
            synchronized (this.connectionPool) {
                Internal.instance.put(this.connectionPool, realConnection3);
                this.connection = realConnection3;
                if (this.canceled) {
                    throw new IOException("Canceled");
                }
            }
            realConnection3.connect(i, i2, i3, this.address.getConnectionSpecs(), z);
            routeDatabase().connected(realConnection3.getRoute());
            return realConnection3;
        }
    }

    public void streamFinished(HttpStream httpStream) throws IOException {
        synchronized (this.connectionPool) {
            if (httpStream != null) {
                if (httpStream == this.stream) {
                }
            }
            throw new IllegalStateException("expected " + this.stream + " but was " + httpStream);
        }
        deallocate(false, false, true);
    }

    public HttpStream stream() {
        HttpStream httpStream;
        synchronized (this.connectionPool) {
            httpStream = this.stream;
        }
        return httpStream;
    }

    private RouteDatabase routeDatabase() {
        return Internal.instance.routeDatabase(this.connectionPool);
    }

    public synchronized RealConnection connection() {
        return this.connection;
    }

    public void release() {
        deallocate(false, true, false);
    }

    public void noNewStreams() throws IOException {
        deallocate(true, false, false);
    }

    /* JADX WARN: Removed duplicated region for block: B:30:0x0054  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void deallocate(boolean r3, boolean r4, boolean r5) throws java.io.IOException {
        /*
            r2 = this;
            com.squareup.okhttp.ConnectionPool r0 = r2.connectionPool
            monitor-enter(r0)
            r1 = 0
            if (r5 == 0) goto Lb
            r2.stream = r1     // Catch: java.lang.Throwable -> L9
            goto Lb
        L9:
            r3 = move-exception
            goto L63
        Lb:
            r5 = 1
            if (r4 == 0) goto L10
            r2.released = r5     // Catch: java.lang.Throwable -> L9
        L10:
            com.squareup.okhttp.internal.io.RealConnection r4 = r2.connection     // Catch: java.lang.Throwable -> L9
            if (r4 == 0) goto L58
            if (r3 == 0) goto L18
            r4.noNewStreams = r5     // Catch: java.lang.Throwable -> L9
        L18:
            com.squareup.okhttp.internal.http.HttpStream r3 = r2.stream     // Catch: java.lang.Throwable -> L9
            if (r3 != 0) goto L58
            boolean r3 = r2.released     // Catch: java.lang.Throwable -> L9
            if (r3 != 0) goto L26
            com.squareup.okhttp.internal.io.RealConnection r3 = r2.connection     // Catch: java.lang.Throwable -> L9
            boolean r3 = r3.noNewStreams     // Catch: java.lang.Throwable -> L9
            if (r3 == 0) goto L58
        L26:
            com.squareup.okhttp.internal.io.RealConnection r3 = r2.connection     // Catch: java.lang.Throwable -> L9
            r2.release(r3)     // Catch: java.lang.Throwable -> L9
            com.squareup.okhttp.internal.io.RealConnection r3 = r2.connection     // Catch: java.lang.Throwable -> L9
            int r3 = r3.streamCount     // Catch: java.lang.Throwable -> L9
            if (r3 <= 0) goto L33
            r2.routeSelector = r1     // Catch: java.lang.Throwable -> L9
        L33:
            com.squareup.okhttp.internal.io.RealConnection r3 = r2.connection     // Catch: java.lang.Throwable -> L9
            java.util.List<java.lang.ref.Reference<com.squareup.okhttp.internal.http.StreamAllocation>> r3 = r3.allocations     // Catch: java.lang.Throwable -> L9
            boolean r3 = r3.isEmpty()     // Catch: java.lang.Throwable -> L9
            if (r3 == 0) goto L54
            com.squareup.okhttp.internal.io.RealConnection r3 = r2.connection     // Catch: java.lang.Throwable -> L9
            long r4 = java.lang.System.nanoTime()     // Catch: java.lang.Throwable -> L9
            r3.idleAtNanos = r4     // Catch: java.lang.Throwable -> L9
            com.squareup.okhttp.internal.Internal r3 = com.squareup.okhttp.internal.Internal.instance     // Catch: java.lang.Throwable -> L9
            com.squareup.okhttp.ConnectionPool r4 = r2.connectionPool     // Catch: java.lang.Throwable -> L9
            com.squareup.okhttp.internal.io.RealConnection r5 = r2.connection     // Catch: java.lang.Throwable -> L9
            boolean r3 = r3.connectionBecameIdle(r4, r5)     // Catch: java.lang.Throwable -> L9
            if (r3 == 0) goto L54
            com.squareup.okhttp.internal.io.RealConnection r3 = r2.connection     // Catch: java.lang.Throwable -> L9
            goto L55
        L54:
            r3 = r1
        L55:
            r2.connection = r1     // Catch: java.lang.Throwable -> L9
            r1 = r3
        L58:
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L9
            if (r1 == 0) goto L62
            java.net.Socket r3 = r1.getSocket()
            com.squareup.okhttp.internal.Util.closeQuietly(r3)
        L62:
            return
        L63:
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L9
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.squareup.okhttp.internal.http.StreamAllocation.deallocate(boolean, boolean, boolean):void");
    }

    public void cancel() {
        HttpStream httpStream;
        RealConnection realConnection;
        synchronized (this.connectionPool) {
            this.canceled = true;
            httpStream = this.stream;
            realConnection = this.connection;
        }
        if (httpStream != null) {
            httpStream.cancel();
        } else if (realConnection != null) {
            realConnection.cancel();
        }
    }

    private void connectionFailed(IOException iOException) throws IOException {
        synchronized (this.connectionPool) {
            if (this.routeSelector != null) {
                if (this.connection.streamCount == 0) {
                    this.routeSelector.connectFailed(this.connection.getRoute(), iOException);
                } else {
                    this.routeSelector = null;
                }
            }
        }
        connectionFailed();
    }

    public void connectionFailed() throws IOException {
        deallocate(true, false, true);
    }

    public void acquire(RealConnection realConnection) {
        realConnection.allocations.add(new WeakReference(this));
    }

    private void release(RealConnection realConnection) {
        int size = realConnection.allocations.size();
        for (int i = 0; i < size; i++) {
            if (realConnection.allocations.get(i).get() == this) {
                realConnection.allocations.remove(i);
                return;
            }
        }
        throw new IllegalStateException();
    }

    public boolean recover(RouteException routeException) throws IOException {
        if (this.connection != null) {
            connectionFailed(routeException.getLastConnectException());
        }
        RouteSelector routeSelector = this.routeSelector;
        return (routeSelector == null || routeSelector.hasNext()) && isRecoverable(routeException);
    }

    public boolean recover(IOException iOException, Sink sink) throws IOException {
        RealConnection realConnection = this.connection;
        if (realConnection != null) {
            int i = realConnection.streamCount;
            connectionFailed(iOException);
            if (i == 1) {
                return false;
            }
        }
        boolean z = sink == null || (sink instanceof RetryableSink);
        RouteSelector routeSelector = this.routeSelector;
        return (routeSelector == null || routeSelector.hasNext()) && isRecoverable(iOException) && z;
    }

    private boolean isRecoverable(IOException iOException) {
        return ((iOException instanceof ProtocolException) || (iOException instanceof InterruptedIOException)) ? false : true;
    }

    private boolean isRecoverable(RouteException routeException) {
        IOException lastConnectException = routeException.getLastConnectException();
        if (lastConnectException instanceof ProtocolException) {
            return false;
        }
        if (lastConnectException instanceof InterruptedIOException) {
            return lastConnectException instanceof SocketTimeoutException;
        }
        return (((lastConnectException instanceof SSLHandshakeException) && (lastConnectException.getCause() instanceof CertificateException)) || (lastConnectException instanceof SSLPeerUnverifiedException)) ? false : true;
    }

    public String toString() {
        return this.address.toString();
    }
}
