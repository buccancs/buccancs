package com.shimmerresearch.verisense.communication;

/* loaded from: classes2.dex */
public interface ByteCommunicationListener {
    void eventConnected();

    void eventDisconnected();

    void eventNewBytesReceived(byte[] bArr);
}
