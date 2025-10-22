package com.shimmerresearch.sensors.bmpX80;

import com.shimmerresearch.driver.ShimmerObject;
import com.shimmerresearch.driver.calibration.CalibDetails;
import com.shimmerresearch.driverUtilities.UtilParseData;
import com.shimmerresearch.driverUtilities.UtilShimmer;
import com.shimmerresearch.sensors.adxl371.SensorADXL371;

import java.awt.EventQueue;

/* loaded from: classes2.dex */
public class CalibDetailsBmp390 extends CalibDetailsBmpX80 {
    private static final long serialVersionUID = 4602512529781344807L;
    public double Bmp3QuantizedCalibData_ParP1;
    public double Bmp3QuantizedCalibData_ParP10;
    public double Bmp3QuantizedCalibData_ParP11;
    public double Bmp3QuantizedCalibData_ParP2;
    public double Bmp3QuantizedCalibData_ParP3;
    public double Bmp3QuantizedCalibData_ParP4;
    public double Bmp3QuantizedCalibData_ParP5;
    public double Bmp3QuantizedCalibData_ParP6;
    public double Bmp3QuantizedCalibData_ParP7;
    public double Bmp3QuantizedCalibData_ParP8;
    public double Bmp3QuantizedCalibData_ParP9;
    public double Bmp3QuantizedCalibData_ParT1;
    public double Bmp3QuantizedCalibData_ParT2;
    public double Bmp3QuantizedCalibData_ParT3;
    public double Bmp3QuantizedCalibData_TLin;
    public short Bmp3RegCalibData_ParP1;
    public byte Bmp3RegCalibData_ParP10;
    public byte Bmp3RegCalibData_ParP11;
    public short Bmp3RegCalibData_ParP2;
    public byte Bmp3RegCalibData_ParP3;
    public byte Bmp3RegCalibData_ParP4;
    public int Bmp3RegCalibData_ParP5;
    public int Bmp3RegCalibData_ParP6;
    public byte Bmp3RegCalibData_ParP7;
    public byte Bmp3RegCalibData_ParP8;
    public short Bmp3RegCalibData_ParP9;
    public short Bmp3RegCalibData_ParT1;
    public short Bmp3RegCalibData_ParT2;
    public byte Bmp3RegCalibData_ParT3;
    public double par_P1;
    public double par_P10;
    public double par_P11;
    public double par_P2;
    public double par_P3;
    public double par_P4;
    public double par_P5;
    public double par_P6;
    public double par_P7;
    public double par_P8;
    public double par_P9;
    public double par_T1;
    public double par_T2;
    public double par_T3;

    public static void main(String[] strArr) {
        EventQueue.invokeLater(new Runnable() { // from class: com.shimmerresearch.sensors.bmpX80.CalibDetailsBmp390.1
            @Override // java.lang.Runnable
            public void run() {
                try {
                    CalibDetailsBmp390 calibDetailsBmp390 = new CalibDetailsBmp390();
                    calibDetailsBmp390.parseCalParamByteArray(new byte[]{-25, ShimmerObject.BAUD_RATE_RESPONSE, -16, 74, -7, SensorADXL371.GET_ALT_ACCEL_CALIBRATION_COMMAND, 28, ShimmerObject.UPD_CALIB_DUMP_COMMAND, 21, 6, 1, -46, 73, 24, ShimmerObject.INTERNAL_EXP_POWER_ENABLE_RESPONSE, 3, -6, 58, 15, 7, -11}, CalibDetails.CALIB_READ_SOURCE.INFOMEM);
                    String[] strArr2 = {"u24"};
                    long[] data = UtilParseData.parseData(new byte[]{0, 13, 100}, strArr2);
                    long[] data2 = UtilParseData.parseData(new byte[]{0, -70, 127}, strArr2);
                    System.out.println("uncalibResultP = " + data[0]);
                    System.out.println("uncalibResultT = " + data2[0]);
                    double[] dArrCalibratePressureSensorData = calibDetailsBmp390.calibratePressureSensorData((double) data[0], (double) data2[0]);
                    System.out.println("PRESSURE = " + dArrCalibratePressureSensorData[0]);
                    System.out.println("TEMPERATURE = " + dArrCalibratePressureSensorData[1]);
                    long[] data3 = UtilParseData.parseData(new byte[]{0, 23, 100}, strArr2);
                    long[] data4 = UtilParseData.parseData(new byte[]{0, -49, 127}, strArr2);
                    System.out.println("uncalibResultP2 = " + data3[0]);
                    System.out.println("uncalibResultT2 = " + data4[0]);
                    double[] dArrCalibratePressureSensorData2 = calibDetailsBmp390.calibratePressureSensorData((double) data3[0], (double) data4[0]);
                    System.out.println("PRESSURE = " + dArrCalibratePressureSensorData2[0]);
                    System.out.println("TEMPERATURE = " + dArrCalibratePressureSensorData2[1]);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private int concatenateBytes(byte b, byte b2) {
        return ((b & 255) << 8) | (b2 & 255);
    }

    @Override // com.shimmerresearch.driver.calibration.CalibDetails
    public byte[] generateCalParamByteArray() {
        return null;
    }

    @Override // com.shimmerresearch.driver.calibration.CalibDetails
    public void resetToDefaultParameters() {
    }

    public void setPressureCalib(double d, double d2, double d3, double d4, double d5, double d6, double d7, double d8, double d9, double d10, double d11, double d12, double d13, double d14) {
        this.par_T1 = d;
        this.par_T2 = d2;
        this.par_T3 = d3;
        this.par_P1 = d4;
        this.par_P2 = d5;
        this.par_P3 = d6;
        this.par_P4 = d7;
        this.par_P5 = d8;
        this.par_P6 = d9;
        this.par_P7 = d10;
        this.par_P8 = d11;
        this.par_P9 = d12;
        this.par_P10 = d13;
        this.par_P11 = d14;
    }

    @Override // com.shimmerresearch.sensors.bmpX80.CalibDetailsBmpX80
    public double[] calibratePressureSensorData(double d, double d2) {
        double d3 = d2 - this.Bmp3QuantizedCalibData_ParT1;
        double d4 = (this.Bmp3QuantizedCalibData_ParT2 * d3) + (d3 * d3 * this.Bmp3QuantizedCalibData_ParT3);
        this.Bmp3QuantizedCalibData_TLin = d4;
        if (d4 < -40.0d) {
            this.Bmp3QuantizedCalibData_TLin = -40.0d;
        }
        if (this.Bmp3QuantizedCalibData_TLin > 85.0d) {
            this.Bmp3QuantizedCalibData_TLin = 85.0d;
        }
        double d5 = this.Bmp3QuantizedCalibData_ParP6;
        double d6 = this.Bmp3QuantizedCalibData_TLin;
        double dPowBmp3 = this.Bmp3QuantizedCalibData_ParP5 + (d5 * d6) + (this.Bmp3QuantizedCalibData_ParP7 * powBmp3(d6, 2)) + (this.Bmp3QuantizedCalibData_ParP8 * powBmp3(this.Bmp3QuantizedCalibData_TLin, 3));
        double d7 = this.Bmp3QuantizedCalibData_ParP2;
        double d8 = this.Bmp3QuantizedCalibData_TLin;
        double dPowBmp32 = dPowBmp3 + (d * (this.Bmp3QuantizedCalibData_ParP1 + (d7 * d8) + (this.Bmp3QuantizedCalibData_ParP3 * powBmp3(d8, 2)) + (this.Bmp3QuantizedCalibData_ParP4 * powBmp3(this.Bmp3QuantizedCalibData_TLin, 3)))) + (powBmp3(d, 2) * (this.Bmp3QuantizedCalibData_ParP9 + (this.Bmp3QuantizedCalibData_ParP10 * this.Bmp3QuantizedCalibData_TLin))) + (powBmp3(d, 3) * this.Bmp3QuantizedCalibData_ParP11);
        if (dPowBmp32 < 30000.0d) {
            dPowBmp32 = 30000.0d;
        }
        if (dPowBmp32 > 125000.0d) {
            dPowBmp32 = 125000.0d;
        }
        return new double[]{dPowBmp32, this.Bmp3QuantizedCalibData_TLin};
    }

    private double powBmp3(double d, int i) {
        return Math.pow(d, i);
    }

    @Override // com.shimmerresearch.driver.calibration.CalibDetails
    public void parseCalParamByteArray(byte[] bArr, CalibDetails.CALIB_READ_SOURCE calib_read_source) {
        if (calib_read_source.ordinal() <= getCalibReadSource().ordinal() || UtilShimmer.isAllFF(bArr) || UtilShimmer.isAllZeros(bArr)) {
            return;
        }
        setPressureRawCoefficients(bArr);
        setCalibReadSource(calib_read_source);
        short sConcatenateBytes = (short) concatenateBytes(bArr[1], bArr[0]);
        this.Bmp3RegCalibData_ParT1 = sConcatenateBytes;
        this.Bmp3QuantizedCalibData_ParT1 = sConcatenateBytes / 0.00390625d;
        short sConcatenateBytes2 = (short) concatenateBytes(bArr[3], bArr[2]);
        this.Bmp3RegCalibData_ParT2 = sConcatenateBytes2;
        this.Bmp3QuantizedCalibData_ParT2 = sConcatenateBytes2 / 1.073741824E9d;
        byte b = bArr[4];
        this.Bmp3RegCalibData_ParT3 = b;
        this.Bmp3QuantizedCalibData_ParT3 = b / 2.81474976710656E14d;
        this.Bmp3RegCalibData_ParP1 = (short) concatenateBytes(bArr[6], bArr[5]);
        this.Bmp3QuantizedCalibData_ParP1 = (r8 - 16384) / 1048576.0d;
        this.Bmp3RegCalibData_ParP2 = (short) concatenateBytes(bArr[8], bArr[7]);
        this.Bmp3QuantizedCalibData_ParP2 = (r8 - 16384) / 5.36870912E8d;
        byte b2 = bArr[9];
        this.Bmp3RegCalibData_ParP3 = b2;
        this.Bmp3QuantizedCalibData_ParP3 = b2 / 4.294967296E9d;
        byte b3 = bArr[10];
        this.Bmp3RegCalibData_ParP4 = b3;
        this.Bmp3QuantizedCalibData_ParP4 = b3 / 1.37438953472E11d;
        int iConcatenateBytes = concatenateBytes(bArr[12], bArr[11]);
        this.Bmp3RegCalibData_ParP5 = iConcatenateBytes;
        this.Bmp3QuantizedCalibData_ParP5 = iConcatenateBytes / 0.125d;
        int iConcatenateBytes2 = concatenateBytes(bArr[14], bArr[13]);
        this.Bmp3RegCalibData_ParP6 = iConcatenateBytes2;
        this.Bmp3QuantizedCalibData_ParP6 = iConcatenateBytes2 / 64.0d;
        byte b4 = bArr[15];
        this.Bmp3RegCalibData_ParP7 = b4;
        this.Bmp3QuantizedCalibData_ParP7 = b4 / 256.0d;
        byte b5 = bArr[16];
        this.Bmp3RegCalibData_ParP8 = b5;
        this.Bmp3QuantizedCalibData_ParP8 = b5 / 32768.0d;
        short sConcatenateBytes3 = (short) concatenateBytes(bArr[18], bArr[17]);
        this.Bmp3RegCalibData_ParP9 = sConcatenateBytes3;
        this.Bmp3QuantizedCalibData_ParP9 = sConcatenateBytes3 / 2.81474976710656E14d;
        byte b6 = bArr[19];
        this.Bmp3RegCalibData_ParP10 = b6;
        this.Bmp3QuantizedCalibData_ParP10 = b6 / 2.81474976710656E14d;
        byte b7 = bArr[20];
        this.Bmp3RegCalibData_ParP11 = b7;
        this.Bmp3QuantizedCalibData_ParP11 = b7 / 3.6893488147419103E19d;
        System.out.println("par_T1 = " + this.Bmp3QuantizedCalibData_ParT1);
        System.out.println("par_T2 = " + this.Bmp3QuantizedCalibData_ParT2);
        System.out.println("par_T3 = " + this.Bmp3QuantizedCalibData_ParT3);
        System.out.println("par_P1 = " + this.Bmp3QuantizedCalibData_ParP1);
        System.out.println("par_P2 = " + this.Bmp3QuantizedCalibData_ParP2);
        System.out.println("par_P3 = " + this.Bmp3QuantizedCalibData_ParP3);
        System.out.println("par_P4 = " + this.Bmp3QuantizedCalibData_ParP4);
        System.out.println("par_P5 = " + this.Bmp3QuantizedCalibData_ParP5);
        System.out.println("par_P6 = " + this.Bmp3QuantizedCalibData_ParP6);
        System.out.println("par_P7 = " + this.Bmp3QuantizedCalibData_ParP7);
        System.out.println("par_P8 = " + this.Bmp3QuantizedCalibData_ParP8);
        System.out.println("par_P9 = " + this.Bmp3QuantizedCalibData_ParP9);
        System.out.println("par_P10 = " + this.Bmp3QuantizedCalibData_ParP10);
        System.out.println("par_P11 = " + this.Bmp3QuantizedCalibData_ParP11);
    }

    public class Bmp3Constants {
        public static final double BMP3_MAX_PRES_DOUBLE = 125000.0d;
        public static final double BMP3_MAX_TEMP_DOUBLE = 85.0d;
        public static final double BMP3_MIN_PRES_DOUBLE = 30000.0d;
        public static final double BMP3_MIN_TEMP_DOUBLE = -40.0d;
        public static final byte BMP3_OK = 0;
        public static final byte BMP3_W_MAX_PRES = 6;
        public static final byte BMP3_W_MAX_TEMP = 4;
        public static final byte BMP3_W_MIN_PRES = 5;
        public static final byte BMP3_W_MIN_TEMP = 3;

        public Bmp3Constants() {
        }
    }
}
