package com.shimmerresearch.driver.calibration;

import com.shimmerresearch.driver.calibration.CalibDetails;
import com.shimmerresearch.driverUtilities.ChannelDetails;
import com.shimmerresearch.driverUtilities.UtilParseData;
import com.shimmerresearch.driverUtilities.UtilShimmer;

import java.io.Serializable;
import java.util.Arrays;
import java.util.LinkedHashMap;

import org.apache.commons.lang3.StringUtils;

/* loaded from: classes2.dex */
public class CalibDetailsKinematic extends CalibDetails implements Serializable {
    private static final long serialVersionUID = -3556098650349506733L;
    public ChannelDetails.CHANNEL_DATA_TYPE mAlignmentDataType;
    public double mAlignmentMax;
    public double mAlignmentMin;
    public int mAlignmentPrecision;
    public CALIBRATION_SCALE_FACTOR mAlignmentScaleFactor;
    public byte[] mCalRawParamsFromShimmer;
    public CalibArraysKinematic mCurrentCalibration;
    public CalibArraysKinematic mDefaultCalibration;
    public CalibArraysKinematic mEmptyCalibration;
    public ChannelDetails.CHANNEL_DATA_TYPE mOffsetDataType;
    public double mOffsetMax;
    public double mOffsetMin;
    public int mOffsetPrecision;
    public CALIBRATION_SCALE_FACTOR mOffsetScaleFactor;
    public ChannelDetails.CHANNEL_DATA_TYPE mSensitivityDataType;
    public double mSensitivityMax;
    public double mSensitivityMin;
    public int mSensitivityPrecision;
    public CALIBRATION_SCALE_FACTOR mSensitivityScaleFactor;

    public CalibDetailsKinematic(int i, String str) {
        this.mCurrentCalibration = new CalibArraysKinematic();
        this.mDefaultCalibration = new CalibArraysKinematic();
        this.mEmptyCalibration = new CalibArraysKinematic(new double[][]{new double[]{0.0d, 0.0d, 0.0d}, new double[]{0.0d, 0.0d, 0.0d}, new double[]{0.0d, 0.0d, 0.0d}}, new double[][]{new double[]{0.0d, 0.0d, 0.0d}, new double[]{0.0d, 0.0d, 0.0d}, new double[]{0.0d, 0.0d, 0.0d}}, new double[][]{new double[]{0.0d, 0.0d, 0.0d}, new double[]{0.0d, 0.0d, 0.0d}, new double[]{0.0d, 0.0d, 0.0d}});
        this.mOffsetScaleFactor = CALIBRATION_SCALE_FACTOR.NONE;
        this.mSensitivityScaleFactor = CALIBRATION_SCALE_FACTOR.NONE;
        this.mAlignmentScaleFactor = CALIBRATION_SCALE_FACTOR.ONE_HUNDRED;
        this.mAlignmentDataType = ChannelDetails.CHANNEL_DATA_TYPE.INT8;
        this.mAlignmentMax = 0.0d;
        this.mAlignmentMin = 0.0d;
        this.mAlignmentPrecision = 2;
        this.mSensitivityDataType = ChannelDetails.CHANNEL_DATA_TYPE.INT16;
        this.mSensitivityMax = 0.0d;
        this.mSensitivityMin = 0.0d;
        this.mSensitivityPrecision = 0;
        this.mOffsetDataType = ChannelDetails.CHANNEL_DATA_TYPE.INT16;
        this.mOffsetMax = 0.0d;
        this.mOffsetMin = 0.0d;
        this.mOffsetPrecision = 0;
        this.mCalRawParamsFromShimmer = new byte[21];
        this.mRangeValue = i;
        this.mRangeString = str;
        updateOffsetMaxMin();
        updateSensitivityMaxMin();
        updateAlignmentMaxMin();
    }

    public CalibDetailsKinematic(int i, String str, double[][] dArr, double[][] dArr2, double[][] dArr3) {
        this(i, str);
        setDefaultValues(dArr3, dArr2, dArr);
    }

    public CalibDetailsKinematic(int i, String str, double[][] dArr, double[][] dArr2, double[][] dArr3, double[][] dArr4, double[][] dArr5, double[][] dArr6) {
        this(i, str, dArr, dArr2, dArr3);
        setCurrentValues(dArr6, dArr5, dArr4, true);
    }

    public CalibDetailsKinematic(int i, String str, double[][] dArr, double[][] dArr2, double[][] dArr3, CALIBRATION_SCALE_FACTOR calibration_scale_factor) {
        this(i, str, dArr, dArr2, dArr3);
        setSensitivityScaleFactor(calibration_scale_factor);
    }

    public CalibDetailsKinematic(int i, String str, double[][] dArr, double[][] dArr2, double[][] dArr3, double[][] dArr4, double[][] dArr5, double[][] dArr6, CALIBRATION_SCALE_FACTOR calibration_scale_factor) {
        this(i, str, dArr, dArr2, dArr3);
        setCurrentValues(dArr6, dArr5, dArr4, true);
        setSensitivityScaleFactor(calibration_scale_factor);
    }

    public CalibDetailsKinematic(byte[] bArr) {
        this.mCurrentCalibration = new CalibArraysKinematic();
        this.mDefaultCalibration = new CalibArraysKinematic();
        this.mEmptyCalibration = new CalibArraysKinematic(new double[][]{new double[]{0.0d, 0.0d, 0.0d}, new double[]{0.0d, 0.0d, 0.0d}, new double[]{0.0d, 0.0d, 0.0d}}, new double[][]{new double[]{0.0d, 0.0d, 0.0d}, new double[]{0.0d, 0.0d, 0.0d}, new double[]{0.0d, 0.0d, 0.0d}}, new double[][]{new double[]{0.0d, 0.0d, 0.0d}, new double[]{0.0d, 0.0d, 0.0d}, new double[]{0.0d, 0.0d, 0.0d}});
        this.mOffsetScaleFactor = CALIBRATION_SCALE_FACTOR.NONE;
        this.mSensitivityScaleFactor = CALIBRATION_SCALE_FACTOR.NONE;
        this.mAlignmentScaleFactor = CALIBRATION_SCALE_FACTOR.ONE_HUNDRED;
        this.mAlignmentDataType = ChannelDetails.CHANNEL_DATA_TYPE.INT8;
        this.mAlignmentMax = 0.0d;
        this.mAlignmentMin = 0.0d;
        this.mAlignmentPrecision = 2;
        this.mSensitivityDataType = ChannelDetails.CHANNEL_DATA_TYPE.INT16;
        this.mSensitivityMax = 0.0d;
        this.mSensitivityMin = 0.0d;
        this.mSensitivityPrecision = 0;
        this.mOffsetDataType = ChannelDetails.CHANNEL_DATA_TYPE.INT16;
        this.mOffsetMax = 0.0d;
        this.mOffsetMin = 0.0d;
        this.mOffsetPrecision = 0;
        this.mCalRawParamsFromShimmer = new byte[21];
        parseCalParamByteArray(bArr, CalibDetails.CALIB_READ_SOURCE.UNKNOWN);
    }

    public static String generateDebugStringPerProperty(String str, double[][] dArr) {
        String str2 = str + " =\n";
        if (dArr == null) {
            return str2 + "NULL\n";
        }
        return str2 + UtilShimmer.doubleArrayToString(dArr);
    }

    public static boolean areCalibArraysEqual(double[][] dArr, double[][] dArr2) {
        if (dArr == null || dArr2 == null) {
            return false;
        }
        return Arrays.deepEquals(dArr, dArr2);
    }

    public static int calculatePrecision(double d) {
        return ((int) (Math.log10(d) + 1.0d)) - 1;
    }

    public static CalibArraysKinematic parseCalibDetailsKinematicFromDbStatic(LinkedHashMap<String, Object> linkedHashMap, String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, String str11, String str12, String str13, String str14, String str15, String str16) {
        if (!linkedHashMap.containsKey(str2) || !linkedHashMap.containsKey(str3) || !linkedHashMap.containsKey(str4) || !linkedHashMap.containsKey(str5) || !linkedHashMap.containsKey(str6) || !linkedHashMap.containsKey(str7) || !linkedHashMap.containsKey(str8) || !linkedHashMap.containsKey(str9) || !linkedHashMap.containsKey(str10) || !linkedHashMap.containsKey(str11) || !linkedHashMap.containsKey(str12) || !linkedHashMap.containsKey(str13) || !linkedHashMap.containsKey(str14) || !linkedHashMap.containsKey(str15) || !linkedHashMap.containsKey(str16)) {
            return null;
        }
        CalibArraysKinematic calibArraysKinematic = new CalibArraysKinematic();
        calibArraysKinematic.updateOffsetVector(((Double) linkedHashMap.get(str2)).doubleValue(), ((Double) linkedHashMap.get(str3)).doubleValue(), ((Double) linkedHashMap.get(str4)).doubleValue());
        calibArraysKinematic.updateSensitivityMatrix(((Double) linkedHashMap.get(str5)).doubleValue(), ((Double) linkedHashMap.get(str6)).doubleValue(), ((Double) linkedHashMap.get(str7)).doubleValue());
        calibArraysKinematic.updateAlignmentMatrix(((Double) linkedHashMap.get(str8)).doubleValue(), ((Double) linkedHashMap.get(str9)).doubleValue(), ((Double) linkedHashMap.get(str10)).doubleValue(), ((Double) linkedHashMap.get(str11)).doubleValue(), ((Double) linkedHashMap.get(str12)).doubleValue(), ((Double) linkedHashMap.get(str13)).doubleValue(), ((Double) linkedHashMap.get(str14)).doubleValue(), ((Double) linkedHashMap.get(str15)).doubleValue(), ((Double) linkedHashMap.get(str16)).doubleValue());
        if (!str.isEmpty() && linkedHashMap.containsKey(str)) {
            calibArraysKinematic.setCalibTime((long) ((Double) linkedHashMap.get(str)).doubleValue());
        }
        return calibArraysKinematic;
    }

    public void setCurrentValues(double[][] dArr, double[][] dArr2, double[][] dArr3, boolean z) {
        setCurrentAlignmentMatrix(dArr3, z);
        setCurrentSensitivityMatrix(dArr2, z);
        setCurrentOffsetVector(dArr, z);
    }

    public void setDefaultValues(double[][] dArr, double[][] dArr2, double[][] dArr3) {
        this.mDefaultCalibration.setValues(dArr, dArr2, dArr3);
    }

    @Override // com.shimmerresearch.driver.calibration.CalibDetails
    public void resetToDefaultParameters() {
        super.resetToDefaultParametersCommon();
        setCurrentOffsetVector(UtilShimmer.deepCopyDoubleMatrix(this.mDefaultCalibration.mOffsetVector), true);
        setCurrentSensitivityMatrix(UtilShimmer.deepCopyDoubleMatrix(this.mDefaultCalibration.mSensitivityMatrix), true);
        setCurrentAlignmentMatrix(UtilShimmer.deepCopyDoubleMatrix(this.mDefaultCalibration.mAlignmentMatrix), true);
    }

    public boolean isCurrentValuesSet() {
        return this.mCurrentCalibration.isCurrentValuesSet();
    }

    public boolean isAllCalibrationValid() {
        return (isSensitivityWithinRangeOfDefault() && this.mCurrentCalibration.isAllCalibrationValid()) || isUsingDefaultParameters();
    }

    public boolean isSensitivityWithinRangeOfDefault() {
        double[][] defaultSensitivityMatrix = getDefaultSensitivityMatrix();
        double[][] validSensitivityMatrix = getValidSensitivityMatrix();
        boolean z = true;
        for (int i = 0; i < defaultSensitivityMatrix.length; i++) {
            int i2 = 0;
            while (true) {
                double[] dArr = defaultSensitivityMatrix[i];
                if (i2 < dArr.length) {
                    double d = dArr[i2];
                    double d2 = validSensitivityMatrix[i][i2];
                    double d3 = 0.25d * d;
                    double d4 = d * 1.75d;
                    if (d2 != 0.0d && (d2 >= d4 || d2 <= d3)) {
                        break;
                    }
                    i2++;
                }
            }
            z = false;
        }
        return z;
    }

    public boolean isUsingDefaultParameters() {
        return isAlignmentUsingDefault() && isSensitivityUsingDefault() && isOffsetVectorUsingDefault();
    }

    public boolean isAlignmentUsingDefault() {
        return areCalibArraysEqual(this.mCurrentCalibration.mAlignmentMatrix, this.mDefaultCalibration.mAlignmentMatrix);
    }

    public boolean isSensitivityUsingDefault() {
        return areCalibArraysEqual(this.mCurrentCalibration.mSensitivityMatrix, this.mDefaultCalibration.mSensitivityMatrix);
    }

    public boolean isOffsetVectorUsingDefault() {
        return areCalibArraysEqual(this.mCurrentCalibration.mOffsetVector, this.mDefaultCalibration.mOffsetVector);
    }

    @Override // com.shimmerresearch.driver.calibration.CalibDetails
    public void parseCalParamByteArray(byte[] bArr, CalibDetails.CALIB_READ_SOURCE calib_read_source) {
        if (calib_read_source.ordinal() < getCalibReadSource().ordinal() || UtilShimmer.isAllFF(bArr) || UtilShimmer.isAllZeros(bArr)) {
            return;
        }
        setCalibReadSource(calib_read_source);
        this.mCalRawParamsFromShimmer = bArr;
        int[] dataPacketReverse = UtilParseData.formatDataPacketReverse(bArr, new String[]{"i16", "i16", "i16", "i16", "i16", "i16", "i8", "i8", "i8", "i8", "i8", "i8", "i8", "i8", "i8"});
        double[] dArr = new double[9];
        for (int i = 0; i < 9; i++) {
            dArr[i] = dataPacketReverse[i + 6] / this.mAlignmentScaleFactor.scaleFactor;
        }
        double[][] dArr2 = {new double[]{dArr[0], dArr[1], dArr[2]}, new double[]{dArr[3], dArr[4], dArr[5]}, new double[]{dArr[6], dArr[7], dArr[8]}};
        double[][] dArr3 = {new double[]{dataPacketReverse[3], 0.0d, 0.0d}, new double[]{0.0d, dataPacketReverse[4], 0.0d}, new double[]{0.0d, 0.0d, dataPacketReverse[5]}};
        double[][] dArr4 = {new double[]{dataPacketReverse[0]}, new double[]{dataPacketReverse[1]}, new double[]{dataPacketReverse[2]}};
        for (int i2 = 0; i2 <= 2; i2++) {
            double[] dArr5 = dArr3[i2];
            dArr5[i2] = dArr5[i2] / this.mSensitivityScaleFactor.scaleFactor;
        }
        setCurrentAlignmentMatrix(dArr2, true);
        setCurrentSensitivityMatrix(dArr3, true);
        setCurrentOffsetVector(dArr4, true);
    }

    @Override // com.shimmerresearch.driver.calibration.CalibDetails
    public byte[] generateCalParamByteArray() {
        if (isCurrentValuesSet()) {
            return generateCalParamByteArray(this.mCurrentCalibration.mOffsetVector, this.mCurrentCalibration.mSensitivityMatrix, this.mCurrentCalibration.mAlignmentMatrix);
        }
        return generateCalParamByteArray(this.mDefaultCalibration.mOffsetVector, this.mDefaultCalibration.mSensitivityMatrix, this.mDefaultCalibration.mAlignmentMatrix);
    }

    public byte[] generateCalParamByteArray(double[][] dArr, double[][] dArr2, double[][] dArr3) {
        double[][] dArrDeepCopyDoubleMatrix = UtilShimmer.deepCopyDoubleMatrix(dArr2);
        for (int i = 0; i <= 2; i++) {
            dArrDeepCopyDoubleMatrix[i][i] = Math.round(r2[i] * this.mSensitivityScaleFactor.scaleFactor);
        }
        double[][] dArrDeepCopyDoubleMatrix2 = UtilShimmer.deepCopyDoubleMatrix(dArr3);
        for (int i2 = 0; i2 <= 2; i2++) {
            dArrDeepCopyDoubleMatrix2[i2][0] = Math.round(r4[0] * this.mAlignmentScaleFactor.scaleFactor);
            dArrDeepCopyDoubleMatrix2[i2][1] = Math.round(r4[1] * this.mAlignmentScaleFactor.scaleFactor);
            dArrDeepCopyDoubleMatrix2[i2][2] = Math.round(r3[2] * this.mAlignmentScaleFactor.scaleFactor);
        }
        byte[] bArr = new byte[21];
        for (int i3 = 0; i3 < 3; i3++) {
            int i4 = i3 * 2;
            double d = dArr[i3][0];
            bArr[i4] = (byte) ((((int) d) >> 8) & 255);
            bArr[i4 + 1] = (byte) (((int) d) & 255);
        }
        for (int i5 = 0; i5 < 3; i5++) {
            int i6 = i5 * 2;
            double d2 = dArrDeepCopyDoubleMatrix[i5][i5];
            bArr[i6 + 6] = (byte) ((((int) d2) >> 8) & 255);
            bArr[i6 + 7] = (byte) (((int) d2) & 255);
        }
        for (int i7 = 0; i7 < 3; i7++) {
            int i8 = i7 * 3;
            double[] dArr4 = dArrDeepCopyDoubleMatrix2[i7];
            bArr[i8 + 12] = (byte) (((int) dArr4[0]) & 255);
            bArr[i8 + 13] = (byte) (((int) dArr4[1]) & 255);
            bArr[i8 + 14] = (byte) (((int) dArr4[2]) & 255);
        }
        return bArr;
    }

    public double[][] getValidAlignmentMatrix() {
        double[][] currentAlignmentMatrix = getCurrentAlignmentMatrix();
        return currentAlignmentMatrix != null ? currentAlignmentMatrix : getDefaultAlignmentMatrix();
    }

    private double[][] getCurrentAlignmentMatrix() {
        CalibArraysKinematic calibArraysKinematic = this.mCurrentCalibration;
        if (calibArraysKinematic == null || calibArraysKinematic.mAlignmentMatrix == null) {
            return null;
        }
        return this.mCurrentCalibration.mAlignmentMatrix;
    }

    public double[][] getValidSensitivityMatrix() {
        double[][] currentSensitivityMatrix = getCurrentSensitivityMatrix();
        return currentSensitivityMatrix != null ? currentSensitivityMatrix : getDefaultSensitivityMatrix();
    }

    public double[][] getCurrentSensitivityMatrix() {
        CalibArraysKinematic calibArraysKinematic = this.mCurrentCalibration;
        if (calibArraysKinematic == null || calibArraysKinematic.mSensitivityMatrix == null) {
            return null;
        }
        return this.mCurrentCalibration.mSensitivityMatrix;
    }

    public double[][] getValidOffsetVector() {
        double[][] currentOffsetVector = getCurrentOffsetVector();
        return currentOffsetVector != null ? currentOffsetVector : getDefaultOffsetVector();
    }

    public double[][] getCurrentOffsetVector() {
        CalibArraysKinematic calibArraysKinematic = this.mCurrentCalibration;
        if (calibArraysKinematic == null || calibArraysKinematic.mOffsetVector == null) {
            return null;
        }
        return this.mCurrentCalibration.mOffsetVector;
    }

    public double[][] getEmptyOffsetVector() {
        CalibArraysKinematic calibArraysKinematic = this.mEmptyCalibration;
        if (calibArraysKinematic == null) {
            return null;
        }
        return calibArraysKinematic.mOffsetVector;
    }

    public double[][] getEmptySensitivityMatrix() {
        CalibArraysKinematic calibArraysKinematic = this.mEmptyCalibration;
        if (calibArraysKinematic == null) {
            return null;
        }
        return calibArraysKinematic.mSensitivityMatrix;
    }

    public double[][] getEmptyAlignmentMatrix() {
        CalibArraysKinematic calibArraysKinematic = this.mEmptyCalibration;
        if (calibArraysKinematic == null) {
            return null;
        }
        return calibArraysKinematic.mAlignmentMatrix;
    }

    public double[][] getDefaultOffsetVector() {
        CalibArraysKinematic calibArraysKinematic = this.mDefaultCalibration;
        if (calibArraysKinematic == null) {
            return null;
        }
        return calibArraysKinematic.mOffsetVector;
    }

    public double[][] getDefaultSensitivityMatrix() {
        CalibArraysKinematic calibArraysKinematic = this.mDefaultCalibration;
        if (calibArraysKinematic == null) {
            return null;
        }
        return calibArraysKinematic.mSensitivityMatrix;
    }

    public double[][] getDefaultAlignmentMatrix() {
        CalibArraysKinematic calibArraysKinematic = this.mDefaultCalibration;
        if (calibArraysKinematic == null) {
            return null;
        }
        return calibArraysKinematic.mAlignmentMatrix;
    }

    public double[][] getCurrentMatrixMultipliedInverseAMSM() {
        CalibArraysKinematic calibArraysKinematic = this.mCurrentCalibration;
        if (calibArraysKinematic == null || calibArraysKinematic.mMatrixMultipliedInverseAMSM == null) {
            return null;
        }
        return this.mCurrentCalibration.mMatrixMultipliedInverseAMSM;
    }

    public double[][] getDefaultMatrixMultipliedInverseAMSM() {
        CalibArraysKinematic calibArraysKinematic = this.mDefaultCalibration;
        if (calibArraysKinematic == null) {
            return null;
        }
        return calibArraysKinematic.mMatrixMultipliedInverseAMSM;
    }

    public double[][] getValidMatrixMultipliedInverseAMSM() {
        double[][] currentMatrixMultipliedInverseAMSM = getCurrentMatrixMultipliedInverseAMSM();
        return currentMatrixMultipliedInverseAMSM != null ? currentMatrixMultipliedInverseAMSM : getDefaultMatrixMultipliedInverseAMSM();
    }

    public void updateCurrentOffsetVector(double d, double d2, double d3) {
        this.mCurrentCalibration.updateOffsetVector(d, d2, d3);
    }

    private void setCurrentOffsetVector(double[][] dArr, boolean z) {
        if (z) {
            this.mCurrentCalibration.setOffsetVector(UtilShimmer.nudgeDoubleArray(this.mOffsetMax, this.mOffsetMin, this.mOffsetPrecision, dArr));
        } else {
            this.mCurrentCalibration.setOffsetVector(dArr);
        }
    }

    public void updateCurrentSensitivityMatrix(double d, double d2, double d3) {
        this.mCurrentCalibration.updateSensitivityMatrix(d, d2, d3);
    }

    public void setCurrentSensitivityMatrix(double[][] dArr, boolean z) {
        if (z) {
            this.mCurrentCalibration.setSensitivityMatrix(UtilShimmer.nudgeDoubleArray(this.mSensitivityMax, this.mSensitivityMin, this.mSensitivityPrecision, dArr));
        } else {
            this.mCurrentCalibration.setSensitivityMatrix(dArr);
        }
    }

    public void updateCurrentAlignmentMatrix(double d, double d2, double d3, double d4, double d5, double d6, double d7, double d8, double d9) {
        this.mCurrentCalibration.updateAlignmentMatrix(d, d2, d3, d4, d5, d6, d7, d8, d9);
    }

    public void setCurrentAlignmentMatrix(double[][] dArr, boolean z) {
        if (z) {
            this.mCurrentCalibration.setAlignmentMatrix(UtilShimmer.nudgeDoubleArray(this.mAlignmentMax, this.mAlignmentMin, this.mAlignmentPrecision, dArr));
        } else {
            this.mCurrentCalibration.setAlignmentMatrix(dArr);
        }
    }

    private void setSensitivityPrecision(int i) {
        this.mSensitivityPrecision = i;
        updateSensitivityMaxMin();
    }

    public void setAlignmentScaleFactor(CALIBRATION_SCALE_FACTOR calibration_scale_factor) {
        this.mAlignmentScaleFactor = calibration_scale_factor;
        setAlignmentPrecision(calculatePrecision(calibration_scale_factor.scaleFactor));
    }

    private void setAlignmentPrecision(int i) {
        this.mAlignmentPrecision = i;
        updateAlignmentMaxMin();
    }

    public void setOffsetScaleFactor(CALIBRATION_SCALE_FACTOR calibration_scale_factor) {
        this.mOffsetScaleFactor = calibration_scale_factor;
        setOffsetPrecision(calculatePrecision(calibration_scale_factor.scaleFactor));
    }

    public void setOffsetPrecision(int i) {
        this.mOffsetPrecision = i;
        updateOffsetMaxMin();
    }

    public void updateOffsetMaxMin() {
        double maxVal = this.mOffsetDataType.getMaxVal() / this.mOffsetScaleFactor.scaleFactor;
        this.mOffsetMax = maxVal;
        this.mOffsetMax = UtilShimmer.applyPrecisionCorrection(maxVal, this.mOffsetPrecision);
        double minVal = this.mOffsetDataType.getMinVal() / this.mOffsetScaleFactor.scaleFactor;
        this.mOffsetMin = minVal;
        this.mOffsetMin = UtilShimmer.applyPrecisionCorrection(minVal, this.mOffsetPrecision);
    }

    public void updateSensitivityMaxMin() {
        double maxVal = this.mSensitivityDataType.getMaxVal() / this.mSensitivityScaleFactor.scaleFactor;
        this.mSensitivityMax = maxVal;
        this.mSensitivityMax = UtilShimmer.applyPrecisionCorrection(maxVal, this.mSensitivityPrecision);
        double minVal = this.mSensitivityDataType.getMinVal() / this.mSensitivityScaleFactor.scaleFactor;
        this.mSensitivityMin = minVal;
        this.mSensitivityMin = UtilShimmer.applyPrecisionCorrection(minVal, this.mSensitivityPrecision);
    }

    public double getSensitivityScaleFactor() {
        return this.mSensitivityScaleFactor.scaleFactor;
    }

    public void setSensitivityScaleFactor(CALIBRATION_SCALE_FACTOR calibration_scale_factor) {
        this.mSensitivityScaleFactor = calibration_scale_factor;
        setSensitivityPrecision(calculatePrecision(calibration_scale_factor.scaleFactor));
    }

    public void updateAlignmentMaxMin() {
        double maxVal = this.mAlignmentDataType.getMaxVal() / this.mAlignmentScaleFactor.scaleFactor;
        this.mAlignmentMax = maxVal;
        this.mAlignmentMax = UtilShimmer.applyPrecisionCorrection(maxVal, this.mAlignmentPrecision);
        double minVal = this.mAlignmentDataType.getMinVal() / this.mAlignmentScaleFactor.scaleFactor;
        this.mAlignmentMin = minVal;
        this.mAlignmentMin = UtilShimmer.applyPrecisionCorrection(minVal, this.mAlignmentPrecision);
    }

    public void updateCurrentCalibration(CalibArraysKinematic calibArraysKinematic) {
        setCurrentOffsetVector(calibArraysKinematic.mOffsetVector, true);
        setCurrentAlignmentMatrix(calibArraysKinematic.mAlignmentMatrix, true);
        setCurrentSensitivityMatrix(calibArraysKinematic.mSensitivityMatrix, true);
        setCalibTimeMs(calibArraysKinematic.getCalibTime());
    }

    public String getDebugString() {
        return (((((("RangeString:" + this.mRangeString + "\tRangeValue:" + this.mRangeValue + StringUtils.LF) + generateDebugStringPerProperty("Default Offset Vector", getDefaultOffsetVector())) + generateDebugStringPerProperty("Current Offset Vector", getValidOffsetVector())) + generateDebugStringPerProperty("Default Sensitivity", getDefaultSensitivityMatrix())) + generateDebugStringPerProperty("CurrentSensitivity", getValidSensitivityMatrix())) + generateDebugStringPerProperty("Default Alignment", getDefaultAlignmentMatrix())) + generateDebugStringPerProperty("Current Alignment", getValidAlignmentMatrix());
    }

    public void parseCalibDetailsKinematicFromDb(LinkedHashMap<String, Object> linkedHashMap, String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, String str11, String str12, String str13, String str14, String str15, String str16) {
        CalibArraysKinematic calibDetailsKinematicFromDbStatic = parseCalibDetailsKinematicFromDbStatic(linkedHashMap, str, str2, str3, str4, str5, str6, str7, str8, str9, str10, str11, str12, str13, str14, str15, str16);
        if (calibDetailsKinematicFromDbStatic != null) {
            updateCurrentCalibration(calibDetailsKinematicFromDbStatic);
        }
    }

    public enum CALIBRATION_SCALE_FACTOR {
        NONE(1.0d),
        TEN(10.0d),
        ONE_HUNDRED(100.0d);

        double scaleFactor;

        CALIBRATION_SCALE_FACTOR(double d) {
            this.scaleFactor = d;
        }
    }

    public class CALIB_KINEMATIC_PARAM {
        public static final String ALIGNMENT_R00 = "r00";
        public static final String ALIGNMENT_R01 = "r01";
        public static final String ALIGNMENT_R02 = "r02";
        public static final String ALIGNMENT_R10 = "r10";
        public static final String ALIGNMENT_R11 = "r11";
        public static final String ALIGNMENT_R12 = "r12";
        public static final String ALIGNMENT_R20 = "r20";
        public static final String ALIGNMENT_R21 = "r21";
        public static final String ALIGNMENT_R22 = "r22";
        public static final String OFFSET_B0 = "b0";
        public static final String OFFSET_B1 = "b1";
        public static final String OFFSET_B2 = "b2";
        public static final String SENSITIVITY_K0 = "k0";
        public static final String SENSITIVITY_K1 = "k1";
        public static final String SENSITIVITY_K2 = "k2";

        public CALIB_KINEMATIC_PARAM() {
        }
    }
}
