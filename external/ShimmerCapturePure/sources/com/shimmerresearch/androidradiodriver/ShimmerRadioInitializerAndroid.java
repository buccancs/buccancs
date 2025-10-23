package com.shimmerresearch.androidradiodriver;

import com.shimmerresearch.bluetooth.ShimmerRadioInitializer;
import com.shimmerresearch.comms.serialPortInterface.AbstractSerialPortHal;

/* loaded from: classes2.dex */
public class ShimmerRadioInitializerAndroid extends ShimmerRadioInitializer {
    public ShimmerRadioInitializerAndroid(String str) {
        useLegacyDelayBeforeBtRead(true);
        this.serialCommPort = new ShimmerSerialPortAndroid(str);
    }

    @Override // com.shimmerresearch.bluetooth.ShimmerRadioInitializer
    public AbstractSerialPortHal getSerialCommPort() {
        return this.serialCommPort;
    }
}
