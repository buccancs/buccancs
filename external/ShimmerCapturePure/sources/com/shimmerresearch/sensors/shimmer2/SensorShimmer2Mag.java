package com.shimmerresearch.sensors.shimmer2;

import com.shimmerresearch.driver.Configuration;
import com.shimmerresearch.driver.ObjectCluster;
import com.shimmerresearch.driver.ShimmerDevice;
import com.shimmerresearch.driver.calibration.CalibDetails;
import com.shimmerresearch.driver.calibration.CalibDetailsKinematic;
import com.shimmerresearch.driverUtilities.SensorDetails;
import com.shimmerresearch.sensors.AbstractSensor;
import com.shimmerresearch.sensors.ActionSetting;

import java.util.LinkedHashMap;
import java.util.TreeMap;

/* loaded from: classes2.dex */
public class SensorShimmer2Mag extends AbstractSensor {
    protected static final double[][] AlignmentMatrixMagShimmer2 = {new double[]{1.0d, 0.0d, 0.0d}, new double[]{0.0d, 1.0d, 0.0d}, new double[]{0.0d, 0.0d, -1.0d}};
    protected static final double[][] SensitivityMatrixMagShimmer2 = {new double[]{580.0d, 0.0d, 0.0d}, new double[]{0.0d, 580.0d, 0.0d}, new double[]{0.0d, 0.0d, 580.0d}};
    protected static final double[][] OffsetVectorMagShimmer2 = {new double[]{0.0d}, new double[]{0.0d}, new double[]{0.0d}};
    protected static final double[][] SensitivityMatrixMag0p8GaShimmer2 = {new double[]{1370.0d, 0.0d, 0.0d}, new double[]{0.0d, 1370.0d, 0.0d}, new double[]{0.0d, 0.0d, 1370.0d}};
    protected static final double[][] SensitivityMatrixMag1p3GaShimmer2 = {new double[]{1090.0d, 0.0d, 0.0d}, new double[]{0.0d, 1090.0d, 0.0d}, new double[]{0.0d, 0.0d, 1090.0d}};
    protected static final double[][] SensitivityMatrixMag1p9GaShimmer2 = {new double[]{820.0d, 0.0d, 0.0d}, new double[]{0.0d, 820.0d, 0.0d}, new double[]{0.0d, 0.0d, 820.0d}};
    protected static final double[][] SensitivityMatrixMag2p5GaShimmer2 = {new double[]{660.0d, 0.0d, 0.0d}, new double[]{0.0d, 660.0d, 0.0d}, new double[]{0.0d, 0.0d, 660.0d}};
    protected static final double[][] SensitivityMatrixMag4p0GaShimmer2 = {new double[]{440.0d, 0.0d, 0.0d}, new double[]{0.0d, 440.0d, 0.0d}, new double[]{0.0d, 0.0d, 440.0d}};
    protected static final double[][] SensitivityMatrixMag4p7GaShimmer2 = {new double[]{390.0d, 0.0d, 0.0d}, new double[]{0.0d, 390.0d, 0.0d}, new double[]{0.0d, 0.0d, 390.0d}};
    protected static final double[][] SensitivityMatrixMag5p6GaShimmer2 = {new double[]{330.0d, 0.0d, 0.0d}, new double[]{0.0d, 330.0d, 0.0d}, new double[]{0.0d, 0.0d, 330.0d}};
    protected static final double[][] SensitivityMatrixMag8p1GaShimmer2 = {new double[]{230.0d, 0.0d, 0.0d}, new double[]{0.0d, 230.0d, 0.0d}, new double[]{0.0d, 0.0d, 230.0d}};
    private static final long serialVersionUID = -1017796687253609998L;
    public CalibDetailsKinematic mCurrentCalibDetailsMag;
    public boolean mIsUsingDefaultMagParam;
    protected TreeMap<Integer, CalibDetails> mCalibMapMagShimmer2r;
    protected boolean mLowPowerMag;
    protected int mMagRange;
    protected int mShimmer2MagRate;
    private CalibDetailsKinematic calibDetailsShimmer2rMag0p8;
    private CalibDetailsKinematic calibDetailsShimmer2rMag1p3;
    private CalibDetailsKinematic calibDetailsShimmer2rMag1p9;
    private CalibDetailsKinematic calibDetailsShimmer2rMag2p5;
    private CalibDetailsKinematic calibDetailsShimmer2rMag4p0;
    private CalibDetailsKinematic calibDetailsShimmer2rMag4p7;
    private CalibDetailsKinematic calibDetailsShimmer2rMag5p6;
    private CalibDetailsKinematic calibDetailsShimmer2rMag8p1;

    public SensorShimmer2Mag(ShimmerDevice shimmerDevice) {
        super(AbstractSensor.SENSORS.SHIMMER2R_MAG, shimmerDevice);
        this.mShimmer2MagRate = 0;
        this.mMagRange = 1;
        this.mLowPowerMag = false;
        this.mIsUsingDefaultMagParam = true;
        String str = Configuration.Shimmer2.ListofMagRange[0];
        double[][] dArr = AlignmentMatrixMagShimmer2;
        double[][] dArr2 = SensitivityMatrixMag0p8GaShimmer2;
        double[][] dArr3 = OffsetVectorMagShimmer2;
        this.calibDetailsShimmer2rMag0p8 = new CalibDetailsKinematic(0, str, dArr, dArr2, dArr3);
        this.calibDetailsShimmer2rMag1p3 = new CalibDetailsKinematic(1, Configuration.Shimmer2.ListofMagRange[1], dArr, SensitivityMatrixMag1p3GaShimmer2, dArr3);
        this.calibDetailsShimmer2rMag1p9 = new CalibDetailsKinematic(2, Configuration.Shimmer2.ListofMagRange[2], dArr, SensitivityMatrixMag1p9GaShimmer2, dArr3);
        this.calibDetailsShimmer2rMag2p5 = new CalibDetailsKinematic(3, Configuration.Shimmer2.ListofMagRange[3], dArr, SensitivityMatrixMag2p5GaShimmer2, dArr3);
        this.calibDetailsShimmer2rMag4p0 = new CalibDetailsKinematic(4, Configuration.Shimmer2.ListofMagRange[4], dArr, SensitivityMatrixMag4p0GaShimmer2, dArr3);
        this.calibDetailsShimmer2rMag4p7 = new CalibDetailsKinematic(5, Configuration.Shimmer2.ListofMagRange[5], dArr, SensitivityMatrixMag4p7GaShimmer2, dArr3);
        this.calibDetailsShimmer2rMag5p6 = new CalibDetailsKinematic(6, Configuration.Shimmer2.ListofMagRange[6], dArr, SensitivityMatrixMag5p6GaShimmer2, dArr3);
        this.calibDetailsShimmer2rMag8p1 = new CalibDetailsKinematic(7, Configuration.Shimmer2.ListofMagRange[7], dArr, SensitivityMatrixMag8p1GaShimmer2, dArr3);
        TreeMap<Integer, CalibDetails> treeMap = new TreeMap<>();
        this.mCalibMapMagShimmer2r = treeMap;
        treeMap.put(Integer.valueOf(this.calibDetailsShimmer2rMag0p8.mRangeValue), this.calibDetailsShimmer2rMag0p8);
        this.mCalibMapMagShimmer2r.put(Integer.valueOf(this.calibDetailsShimmer2rMag1p3.mRangeValue), this.calibDetailsShimmer2rMag1p3);
        this.mCalibMapMagShimmer2r.put(Integer.valueOf(this.calibDetailsShimmer2rMag1p9.mRangeValue), this.calibDetailsShimmer2rMag1p9);
        this.mCalibMapMagShimmer2r.put(Integer.valueOf(this.calibDetailsShimmer2rMag2p5.mRangeValue), this.calibDetailsShimmer2rMag2p5);
        this.mCalibMapMagShimmer2r.put(Integer.valueOf(this.calibDetailsShimmer2rMag4p0.mRangeValue), this.calibDetailsShimmer2rMag4p0);
        this.mCalibMapMagShimmer2r.put(Integer.valueOf(this.calibDetailsShimmer2rMag4p7.mRangeValue), this.calibDetailsShimmer2rMag4p7);
        this.mCalibMapMagShimmer2r.put(Integer.valueOf(this.calibDetailsShimmer2rMag5p6.mRangeValue), this.calibDetailsShimmer2rMag5p6);
        this.mCalibMapMagShimmer2r.put(Integer.valueOf(this.calibDetailsShimmer2rMag8p1.mRangeValue), this.calibDetailsShimmer2rMag8p1);
        this.mCurrentCalibDetailsMag = null;
        initialise();
    }

    @Override // com.shimmerresearch.sensors.AbstractSensor
    public boolean checkConfigOptionValues(String str) {
        return false;
    }

    @Override // com.shimmerresearch.sensors.AbstractSensor
    public void checkShimmerConfigBeforeConfiguring() {
    }

    @Override // com.shimmerresearch.sensors.AbstractSensor
    public void configBytesGenerate(ShimmerDevice shimmerDevice, byte[] bArr, Configuration.COMMUNICATION_TYPE communication_type) {
    }

    @Override // com.shimmerresearch.sensors.AbstractSensor
    public void configBytesParse(ShimmerDevice shimmerDevice, byte[] bArr, Configuration.COMMUNICATION_TYPE communication_type) {
    }

    @Override // com.shimmerresearch.sensors.AbstractSensor
    public LinkedHashMap<String, Object> generateConfigMap() {
        return null;
    }

    @Override // com.shimmerresearch.sensors.AbstractSensor
    public void generateConfigOptionsMap() {
    }

    @Override // com.shimmerresearch.sensors.AbstractSensor
    public void generateSensorGroupMapping() {
    }

    @Override // com.shimmerresearch.sensors.AbstractSensor
    public void generateSensorMap() {
    }

    public int getMagRange() {
        return this.mMagRange;
    }

    public void setMagRange(int i) {
        this.mMagRange = i;
    }

    public int getMagRate() {
        return this.mShimmer2MagRate;
    }

    @Override // com.shimmerresearch.sensors.AbstractSensor
    public Object getSettings(String str, Configuration.COMMUNICATION_TYPE communication_type) {
        return null;
    }

    public boolean isLowPowerMagEnabled() {
        return this.mLowPowerMag;
    }

    @Override // com.shimmerresearch.sensors.AbstractSensor
    public void parseConfigMap(LinkedHashMap<String, Object> linkedHashMap) {
    }

    @Override // com.shimmerresearch.sensors.AbstractSensor
    public ObjectCluster processDataCustom(SensorDetails sensorDetails, byte[] bArr, Configuration.COMMUNICATION_TYPE communication_type, ObjectCluster objectCluster, boolean z, double d) {
        return null;
    }

    @Override // com.shimmerresearch.sensors.AbstractSensor
    public boolean processResponse(int i, Object obj, Configuration.COMMUNICATION_TYPE communication_type) {
        return false;
    }

    @Override // com.shimmerresearch.sensors.AbstractSensor
    public boolean setDefaultConfigForSensor(int i, boolean z) {
        return false;
    }

    @Override // com.shimmerresearch.sensors.AbstractSensor
    public ActionSetting setSettings(String str, Object obj, Configuration.COMMUNICATION_TYPE communication_type) {
        return null;
    }

    public void setShimmer2rMagRate(int i) {
        this.mShimmer2MagRate = i;
    }

    @Override // com.shimmerresearch.sensors.AbstractSensor
    public Object setConfigValueUsingConfigLabel(Integer num, String str, Object obj) {
        str.hashCode();
        if (str.equals("Mag Range")) {
            setMagRange(((Integer) obj).intValue());
            return null;
        }
        return super.setConfigValueUsingConfigLabelCommon(num, str, obj);
    }

    @Override // com.shimmerresearch.sensors.AbstractSensor
    public Object getConfigValueUsingConfigLabel(Integer num, String str) {
        str.hashCode();
        if (str.equals("Range")) {
            return null;
        }
        return super.getConfigValueUsingConfigLabelCommon(num, str);
    }

    @Override // com.shimmerresearch.sensors.AbstractSensor
    public void setSensorSamplingRate(double d) {
        setShimmer2rMagRateFromFreq(d);
        checkLowPowerMag();
    }

    @Override // com.shimmerresearch.sensors.AbstractSensor
    public void generateCalibMap() {
        super.generateCalibMap();
        TreeMap<Integer, CalibDetails> treeMap = this.mShimmerDevice.getHardwareVersion() == 1 ? this.mCalibMapMagShimmer2r : this.mCalibMapMagShimmer2r;
        if (treeMap != null) {
            setCalibrationMapPerSensor(2, treeMap);
        }
        updateCurrentCalibInUse();
    }

    @Override // com.shimmerresearch.sensors.AbstractSensor
    public void setCalibrationMapPerSensor(int i, TreeMap<Integer, CalibDetails> treeMap) {
        super.setCalibrationMapPerSensor(i, treeMap);
        updateCurrentCalibInUse();
    }

    public void setLowPowerMag(boolean z) {
        this.mLowPowerMag = z;
        setShimmer2rMagRateFromFreq(this.mShimmerDevice.getSamplingRateShimmer());
    }

    public boolean checkLowPowerMag() {
        setLowPowerMag(getMagRate() <= 4);
        return isLowPowerMagEnabled();
    }

    public void setShimmer2rMagRateFromFreq(double d) {
        if (isLowPowerMagEnabled()) {
            setShimmer2rMagRate(4);
            return;
        }
        if (d >= 50.0d) {
            setShimmer2rMagRate(6);
            return;
        }
        if (d >= 20.0d) {
            setShimmer2rMagRate(5);
        } else if (d >= 10.0d) {
            setShimmer2rMagRate(4);
        } else {
            setShimmer2rMagRate(3);
        }
    }

    public void updateCurrentCalibInUse() {
        this.mCurrentCalibDetailsMag = getCurrentCalibDetailsIfKinematic(2, getMagRange());
    }

    public CalibDetailsKinematic getCurrentCalibDetailsMag() {
        if (this.mCurrentCalibDetailsMag == null) {
            updateCurrentCalibInUse();
        }
        return this.mCurrentCalibDetailsMag;
    }

    public void updateIsUsingDefaultMagParam() {
        this.mIsUsingDefaultMagParam = getCurrentCalibDetailsMag().isUsingDefaultParameters();
    }

    public class GuiLabelConfig {
        public static final String MAG_LOW_POWER_MODE = "Mag Low Power Mode";
        public static final String MAG_RANGE = "Mag Range";

        public GuiLabelConfig() {
        }
    }
}
