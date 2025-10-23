package com.shimmerresearch.driver.calibration;

import com.shimmerresearch.driverUtilities.UtilShimmer;

import java.io.Serializable;

import org.apache.commons.lang3.ArrayUtils;

/* loaded from: classes2.dex */
public abstract class CalibDetails implements Serializable {
    private static final long serialVersionUID = 3071359258303179516L;
    public String mRangeString = "";
    public int mRangeValue = 0;
    public long mCalibTimeMs = 0;
    public CALIB_READ_SOURCE mCalibReadSource = CALIB_READ_SOURCE.UNKNOWN;

    public abstract byte[] generateCalParamByteArray();

    public CALIB_READ_SOURCE getCalibReadSource() {
        return this.mCalibReadSource;
    }

    protected void setCalibReadSource(CALIB_READ_SOURCE calib_read_source) {
        this.mCalibReadSource = calib_read_source;
    }

    public long getCalibTimeMs() {
        return this.mCalibTimeMs;
    }

    public void setCalibTimeMs(long j) {
        this.mCalibTimeMs = j;
    }

    public abstract void parseCalParamByteArray(byte[] bArr, CALIB_READ_SOURCE calib_read_source);

    public abstract void resetToDefaultParameters();

    public String getCalibTimeParsed() {
        return UtilShimmer.convertMilliSecondsToDateString(getCalibTimeMs(), false);
    }

    public boolean isCalibTimeZero() {
        return getCalibTimeMs() == 0;
    }

    public byte[] generateCalibDump() {
        byte[] bArr = {(byte) (this.mRangeValue & 255)};
        byte[] bArrConvertMilliSecondsToShimmerRtcDataBytesLSB = UtilShimmer.convertMilliSecondsToShimmerRtcDataBytesLSB(getCalibTimeMs());
        byte[] bArrGenerateCalParamByteArray = generateCalParamByteArray();
        return ArrayUtils.addAll(ArrayUtils.addAll(ArrayUtils.addAll(bArr, (byte) bArrGenerateCalParamByteArray.length), bArrConvertMilliSecondsToShimmerRtcDataBytesLSB), bArrGenerateCalParamByteArray);
    }

    public void parseCalibDump(byte[] bArr, byte[] bArr2, CALIB_READ_SOURCE calib_read_source) {
        long jConvertShimmerRtcDataBytesToMilliSecondsLSB = UtilShimmer.convertShimmerRtcDataBytesToMilliSecondsLSB(bArr);
        if ((jConvertShimmerRtcDataBytesToMilliSecondsLSB <= getCalibTimeMs() && calib_read_source.ordinal() < getCalibReadSource().ordinal()) || UtilShimmer.isAllFF(bArr2) || UtilShimmer.isAllZeros(bArr2)) {
            return;
        }
        setCalibTimeMs(jConvertShimmerRtcDataBytesToMilliSecondsLSB);
        parseCalParamByteArray(bArr2, calib_read_source);
    }

    public void resetToDefaultParametersCommon() {
        setCalibTimeMs(0L);
    }

    public enum CALIB_READ_SOURCE {
        UNKNOWN,
        SD_HEADER,
        LEGACY_BT_COMMAND,
        INFOMEM,
        RADIO_DUMP,
        FILE_DUMP,
        USER_MODIFIED
    }
}
