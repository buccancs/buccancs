package com.shimmerresearch.androidradiodriver;

import com.shimmerresearch.bluetooth.ShimmerRadioInitializer;
import com.shimmerresearch.comms.serialPortInterface.AbstractSerialPortHal;


public class ShimmerRadioInitializerAndroid extends ShimmerRadioInitializer {

    public ShimmerRadioInitializerAndroid(String bluetoothAddress) {
        useLegacyDelayBeforeBtRead(true);
        this.serialCommPort = new ShimmerSerialPortAndroid(bluetoothAddress);
    }

    @Override
    public AbstractSerialPortHal getSerialCommPort() {
        return this.serialCommPort;
    }
}
