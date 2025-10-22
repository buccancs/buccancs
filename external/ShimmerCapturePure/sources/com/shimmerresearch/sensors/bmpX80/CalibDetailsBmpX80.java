package com.shimmerresearch.sensors.bmpX80;

import com.shimmerresearch.driver.calibration.CalibDetails;

/* loaded from: classes2.dex */
public abstract class CalibDetailsBmpX80 extends CalibDetails {
    private static final long serialVersionUID = 8601750188557924758L;
    public byte[] mPressureCalRawParams = new byte[23];

    public abstract double[] calibratePressureSensorData(double d, double d2);

    public byte[] getPressureRawCoefficients() {
        return this.mPressureCalRawParams;
    }

    public void setPressureRawCoefficients(byte[] bArr) {
        this.mPressureCalRawParams = bArr;
    }
}
