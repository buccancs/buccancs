package io.grpc.channelz.v1;

import com.google.protobuf.MessageOrBuilder;

/* loaded from: classes2.dex */
public interface SocketOptionTcpInfoOrBuilder extends MessageOrBuilder {
    int getTcpiAdvmss();

    int getTcpiAto();

    int getTcpiBackoff();

    int getTcpiCaState();

    int getTcpiFackets();

    int getTcpiLastAckRecv();

    int getTcpiLastAckSent();

    int getTcpiLastDataRecv();

    int getTcpiLastDataSent();

    int getTcpiLost();

    int getTcpiOptions();

    int getTcpiPmtu();

    int getTcpiProbes();

    int getTcpiRcvMss();

    int getTcpiRcvSsthresh();

    int getTcpiRcvWscale();

    int getTcpiReordering();

    int getTcpiRetrans();

    int getTcpiRetransmits();

    int getTcpiRto();

    int getTcpiRtt();

    int getTcpiRttvar();

    int getTcpiSacked();

    int getTcpiSndCwnd();

    int getTcpiSndMss();

    int getTcpiSndSsthresh();

    int getTcpiSndWscale();

    int getTcpiState();

    int getTcpiUnacked();
}
