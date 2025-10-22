package com.shimmerresearch.verisense.communication;

import com.shimmerresearch.exceptions.ShimmerException;

/* loaded from: classes2.dex */
public abstract class AbstractByteCommunication {
    protected ByteCommunicationListener mByteCommunicationListener;

    public abstract void connect() throws ShimmerException;

    public abstract void disconnect() throws ShimmerException;

    public abstract String getUuid();

    public void removeRadioListenerList() {
        this.mByteCommunicationListener = null;
    }

    public void setByteCommunicationListener(ByteCommunicationListener byteCommunicationListener) {
        this.mByteCommunicationListener = byteCommunicationListener;
    }

    public abstract void stop();

    public abstract void writeBytes(byte[] bArr);
}
