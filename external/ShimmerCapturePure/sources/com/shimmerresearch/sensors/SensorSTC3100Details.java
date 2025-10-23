package com.shimmerresearch.sensors;

import com.shimmerresearch.driverUtilities.ChannelDetails;
import com.shimmerresearch.driverUtilities.UtilParseData;

import java.io.Serializable;

/* loaded from: classes2.dex */
public class SensorSTC3100Details implements Serializable {
    private static final long serialVersionUID = 3566114309466948096L;
    private static double R_SENSE = 33.0d;
    public double mBattCharge;
    public double mBattCounter;
    public double mBattCurrent;
    public double mBattTemperature;
    public double mBattVoltage;
    private byte[] mRegBytes;

    public SensorSTC3100Details() {
        this.mBattCharge = 0.0d;
        this.mBattCounter = 0.0d;
        this.mBattCurrent = 0.0d;
        this.mBattVoltage = 0.0d;
        this.mBattTemperature = 0.0d;
        this.mRegBytes = new byte[0];
    }

    public SensorSTC3100Details(byte[] bArr) {
        this.mBattCharge = 0.0d;
        this.mBattCounter = 0.0d;
        this.mBattCurrent = 0.0d;
        this.mBattVoltage = 0.0d;
        this.mBattTemperature = 0.0d;
        this.mRegBytes = bArr;
        if (bArr.length >= 10) {
            this.mBattCharge = (UtilParseData.parseData(new byte[]{bArr[0], bArr[1]}, ChannelDetails.CHANNEL_DATA_TYPE.INT16, ChannelDetails.CHANNEL_DATA_ENDIAN.LSB) * 6.7d) / R_SENSE;
            byte[] bArr2 = this.mRegBytes;
            this.mBattCounter = ((bArr2[3] & 255) << 8) | (bArr2[2] & 255);
            this.mBattCurrent = (UtilParseData.parseData(new byte[]{bArr2[4], bArr2[5]}, ChannelDetails.CHANNEL_DATA_TYPE.INT14, ChannelDetails.CHANNEL_DATA_ENDIAN.LSB) * 11.77d) / R_SENSE;
            byte[] bArr3 = this.mRegBytes;
            this.mBattVoltage = UtilParseData.parseData(new byte[]{bArr3[6], bArr3[7]}, ChannelDetails.CHANNEL_DATA_TYPE.INT12, ChannelDetails.CHANNEL_DATA_ENDIAN.LSB) * 2.44d;
            byte[] bArr4 = this.mRegBytes;
            this.mBattTemperature = UtilParseData.parseData(new byte[]{bArr4[8], bArr4[9]}, ChannelDetails.CHANNEL_DATA_TYPE.INT12, ChannelDetails.CHANNEL_DATA_ENDIAN.LSB) * 0.125d;
        }
    }

    public String getDebugString() {
        return "STC3100: | Charge=" + String.format("%1$.2f", Double.valueOf(this.mBattCharge)) + " mA.h | Counter=" + this.mBattCounter + " | Current=" + String.format("%1$.2f", Double.valueOf(this.mBattCurrent)) + " mA | Voltage=" + String.format("%1$.2f", Double.valueOf(this.mBattVoltage)) + " mV | Temp=" + String.format("%1$.2f", Double.valueOf(this.mBattTemperature)) + " C";
    }
}
