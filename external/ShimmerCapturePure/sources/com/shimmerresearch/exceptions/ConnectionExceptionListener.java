package com.shimmerresearch.exceptions;

/* loaded from: classes2.dex */
public interface ConnectionExceptionListener {
    void onConnectStartException(String str);

    void onConnectionException(Exception exc);

    void onConnectionStart(String str);
}
