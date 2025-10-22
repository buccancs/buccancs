package com.shimmerresearch.sensors.bmpX80;

import com.shimmerresearch.driver.calibration.CalibDetails;
import com.shimmerresearch.driverUtilities.UtilParseData;
import com.shimmerresearch.driverUtilities.UtilShimmer;

/* loaded from: classes2.dex */
public class CalibDetailsBmp180 extends CalibDetailsBmpX80 {
    private static final long serialVersionUID = 6119627638201576905L;
    public double AC1 = 408.0d;
    public double AC2 = -72.0d;
    public double AC3 = -14383.0d;
    public double AC4 = 332741.0d;
    public double AC5 = 32757.0d;
    public double AC6 = 23153.0d;
    public double B1 = 6190.0d;
    public double B2 = 4.0d;
    public double MB = -32767.0d;
    public double MC = -8711.0d;
    public double MD = 2868.0d;

    @Override // com.shimmerresearch.driver.calibration.CalibDetails
    public byte[] generateCalParamByteArray() {
        return null;
    }

    @Override // com.shimmerresearch.driver.calibration.CalibDetails
    public void resetToDefaultParameters() {
    }

    public void setPressureCalib(double d, double d2, double d3, double d4, double d5, double d6, double d7, double d8, double d9, double d10, double d11) {
        this.AC1 = d;
        this.AC2 = d2;
        this.AC3 = d3;
        this.AC4 = d4;
        this.AC5 = d5;
        this.AC6 = d6;
        this.B1 = d7;
        this.B2 = d8;
        this.MB = d9;
        this.MC = d10;
        this.MD = d11;
    }

    @Override // com.shimmerresearch.driver.calibration.CalibDetails
    public void parseCalParamByteArray(byte[] bArr, CalibDetails.CALIB_READ_SOURCE calib_read_source) {
        if (calib_read_source.ordinal() <= getCalibReadSource().ordinal() || UtilShimmer.isAllFF(bArr) || UtilShimmer.isAllZeros(bArr)) {
            return;
        }
        setPressureRawCoefficients(bArr);
        setCalibReadSource(calib_read_source);
        this.AC1 = UtilParseData.calculatetwoscomplement((bArr[1] & 255) + ((bArr[0] & 255) << 8), 16);
        this.AC2 = UtilParseData.calculatetwoscomplement((bArr[3] & 255) + ((bArr[2] & 255) << 8), 16);
        this.AC3 = UtilParseData.calculatetwoscomplement((bArr[5] & 255) + ((bArr[4] & 255) << 8), 16);
        this.AC4 = (bArr[7] & 255) + ((bArr[6] & 255) << 8);
        this.AC5 = (bArr[9] & 255) + ((bArr[8] & 255) << 8);
        this.AC6 = (bArr[11] & 255) + ((bArr[10] & 255) << 8);
        this.B1 = UtilParseData.calculatetwoscomplement((bArr[13] & 255) + ((bArr[12] & 255) << 8), 16);
        this.B2 = UtilParseData.calculatetwoscomplement((bArr[15] & 255) + ((bArr[14] & 255) << 8), 16);
        this.MB = UtilParseData.calculatetwoscomplement((bArr[17] & 255) + ((bArr[16] & 255) << 8), 16);
        this.MC = UtilParseData.calculatetwoscomplement((bArr[19] & 255) + ((bArr[18] & 255) << 8), 16);
        this.MD = UtilParseData.calculatetwoscomplement((bArr[21] & 255) + ((bArr[20] & 255) << 8), 16);
    }

    @Override // com.shimmerresearch.sensors.bmpX80.CalibDetailsBmpX80
    public double[] calibratePressureSensorData(double d, double d2) {
        double d3 = (d2 - this.AC6) * (this.AC5 / 32768.0d);
        double d4 = d3 + ((this.MC * 2048.0d) / (this.MD + d3));
        double d5 = (8.0d + d4) / 16.0d;
        double d6 = d4 - 4000.0d;
        double dPow = ((((this.AC1 * 4.0d) + (((this.B2 * (Math.pow(d6, 2.0d) / 4096.0d)) / 2048.0d) + ((this.AC2 * d6) / 2048.0d))) * (1 << this.mRangeValue)) + 2.0d) / 4.0d;
        double dPow2 = (this.AC4 * ((((((this.AC3 * d6) / 8192.0d) + ((this.B1 * (Math.pow(d6, 2.0d) / 4096.0d)) / 65536.0d)) + 2.0d) / 4.0d) + 32768.0d)) / 32768.0d;
        double d7 = (d - dPow) * (50000 >> this.mRangeValue);
        double d8 = d7 < 2.147483648E9d ? (d7 * 2.0d) / dPow2 : (d7 / dPow2) * 2.0d;
        double d9 = d8 / 256.0d;
        return new double[]{d8 + ((((((d9 * d9) * 3038.0d) / 65536.0d) + (((-7357.0d) * d8) / 65536.0d)) + 3791.0d) / 16.0d), d5 / 10.0d};
    }
}
