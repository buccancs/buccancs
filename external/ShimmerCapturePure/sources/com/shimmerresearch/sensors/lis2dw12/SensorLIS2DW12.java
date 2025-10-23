package com.shimmerresearch.sensors.lis2dw12;

import com.shimmerresearch.bluetooth.BtCommandDetails;
import com.shimmerresearch.driver.ConfigByteLayout;
import com.shimmerresearch.driver.Configuration;
import com.shimmerresearch.driver.ObjectCluster;
import com.shimmerresearch.driver.ShimmerDevice;
import com.shimmerresearch.driver.calibration.CalibDetails;
import com.shimmerresearch.driver.calibration.CalibDetailsKinematic;
import com.shimmerresearch.driver.calibration.UtilCalibration;
import com.shimmerresearch.driver.shimmer2r3.ConfigByteLayoutShimmer3;
import com.shimmerresearch.driverUtilities.ChannelDetails;
import com.shimmerresearch.driverUtilities.ConfigOptionDetails;
import com.shimmerresearch.driverUtilities.ConfigOptionDetailsSensor;
import com.shimmerresearch.driverUtilities.ConfigOptionObject;
import com.shimmerresearch.driverUtilities.SensorDetails;
import com.shimmerresearch.driverUtilities.SensorDetailsRef;
import com.shimmerresearch.driverUtilities.SensorGroupingDetails;
import com.shimmerresearch.driverUtilities.ShimmerVerObject;
import com.shimmerresearch.driverUtilities.UtilShimmer;
import com.shimmerresearch.sensors.AbstractSensor;
import com.shimmerresearch.sensors.ActionSetting;
import com.shimmerresearch.sensors.kionix.SensorKionixAccel;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.lang3.ArrayUtils;

/* loaded from: classes2.dex */
public class SensorLIS2DW12 extends AbstractSensor {
    public static final double[][] DefaultAlignmentLIS2DW12;
    public static final double[][] DefaultAlignmentMatrixWideRangeAccelShimmer3R;
    public static final double[][] DefaultOffsetVectorWideRangeAccelShimmer3R;
    public static final double[][] DefaultSensitivityMatrixWideRangeAccel16gShimmer3R;
    public static final double[][] DefaultSensitivityMatrixWideRangeAccel2gShimmer3R;
    public static final double[][] DefaultSensitivityMatrixWideRangeAccel4gShimmer3R;
    public static final double[][] DefaultSensitivityMatrixWideRangeAccel8gShimmer3R;
    public static final byte GET_WR_ACCEL_CALIBRATION_COMMAND = 28;
    public static final byte GET_WR_ACCEL_HRMODE_COMMAND = 72;
    public static final byte GET_WR_ACCEL_LPMODE_COMMAND = 69;
    public static final byte GET_WR_ACCEL_RANGE_COMMAND = 11;
    public static final byte GET_WR_ACCEL_SAMPLING_RATE_COMMAND = 66;
    public static final String[] ListofLIS2DW12AccelRange;
    public static final Integer[] ListofLIS2DW12AccelRangeConfigValues;
    public static final String[] ListofLIS2DW12AccelRateHpm;
    public static final Integer[] ListofLIS2DW12AccelRateHpmConfigValues;
    public static final String[] ListofLIS2DW12AccelRateLpm;
    public static final Integer[] ListofLIS2DW12AccelRateLpmConfigValues;
    public static final byte SET_WR_ACCEL_CALIBRATION_COMMAND = 26;
    public static final byte SET_WR_ACCEL_HRMODE_COMMAND = 70;
    public static final byte SET_WR_ACCEL_LPMODE_COMMAND = 67;
    public static final byte SET_WR_ACCEL_RANGE_COMMAND = 9;
    public static final byte SET_WR_ACCEL_SAMPLING_RATE_COMMAND = 64;
    public static final byte WR_ACCEL_CALIBRATION_RESPONSE = 27;
    public static final byte WR_ACCEL_HRMODE_RESPONSE = 71;
    public static final byte WR_ACCEL_LPMODE_RESPONSE = 68;
    public static final byte WR_ACCEL_RANGE_RESPONSE = 10;
    public static final byte WR_ACCEL_SAMPLING_RATE_RESPONSE = 65;
    public static final ChannelDetails channelLIS2DW12AccelX;
    public static final ChannelDetails channelLIS2DW12AccelY;
    public static final ChannelDetails channelLIS2DW12AccelZ;
    public static final ConfigOptionDetailsSensor configOptionAccelLpm;
    public static final ConfigOptionDetailsSensor configOptionAccelRange;
    public static final ConfigOptionDetailsSensor configOptionAccelRate;
    public static final Map<Byte, BtCommandDetails> mBtGetCommandMap;
    public static final Map<Byte, BtCommandDetails> mBtSetCommandMap;
    public static final Map<String, ChannelDetails> mChannelMapRef;
    public static final Map<Integer, SensorDetailsRef> mSensorMapRef;
    public static final SensorGroupingDetails sensorGroupLpmAccel;
    public static final SensorDetailsRef sensorLIS2DW12Accel;
    private static final long serialVersionUID = 5066903487855750207L;

    static {
        String[] strArr = {SensorKionixAccel.LN_ACCEL_RANGE_STRING, "± 4g", "± 8g", "± 16g"};
        ListofLIS2DW12AccelRange = strArr;
        double[][] dArr = {new double[]{0.0d, -1.0d, 0.0d}, new double[]{-1.0d, 0.0d, 0.0d}, new double[]{0.0d, 0.0d, -1.0d}};
        DefaultAlignmentLIS2DW12 = dArr;
        DefaultAlignmentMatrixWideRangeAccelShimmer3R = dArr;
        DefaultOffsetVectorWideRangeAccelShimmer3R = new double[][]{new double[]{0.0d}, new double[]{0.0d}, new double[]{0.0d}};
        DefaultSensitivityMatrixWideRangeAccel2gShimmer3R = new double[][]{new double[]{1671.0d, 0.0d, 0.0d}, new double[]{0.0d, 1671.0d, 0.0d}, new double[]{0.0d, 0.0d, 1671.0d}};
        DefaultSensitivityMatrixWideRangeAccel4gShimmer3R = new double[][]{new double[]{836.0d, 0.0d, 0.0d}, new double[]{0.0d, 836.0d, 0.0d}, new double[]{0.0d, 0.0d, 836.0d}};
        DefaultSensitivityMatrixWideRangeAccel8gShimmer3R = new double[][]{new double[]{418.0d, 0.0d, 0.0d}, new double[]{0.0d, 418.0d, 0.0d}, new double[]{0.0d, 0.0d, 418.0d}};
        DefaultSensitivityMatrixWideRangeAccel16gShimmer3R = new double[][]{new double[]{209.0d, 0.0d, 0.0d}, new double[]{0.0d, 209.0d, 0.0d}, new double[]{0.0d, 0.0d, 209.0d}};
        SensorDetailsRef sensorDetailsRef = new SensorDetailsRef(4096L, 4096L, "Wide-Range Accelerometer", Configuration.Shimmer3.CompatibilityInfoForMaps.listOfCompatibleVersionInfoLIS2DW12, Arrays.asList("Wide Range Accel Range", "Wide Range Accel Rate"), Arrays.asList(ObjectClusterSensorName.ACCEL_WR_X, ObjectClusterSensorName.ACCEL_WR_Y, ObjectClusterSensorName.ACCEL_WR_Z));
        sensorLIS2DW12Accel = sensorDetailsRef;
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put(39, sensorDetailsRef);
        mSensorMapRef = Collections.unmodifiableMap(linkedHashMap);
        LinkedHashMap linkedHashMap2 = new LinkedHashMap();
        linkedHashMap2.put((byte) 11, new BtCommandDetails((byte) 11, "GET_WR_ACCEL_RANGE_COMMAND", (byte) 10));
        linkedHashMap2.put((byte) 28, new BtCommandDetails((byte) 28, "GET_WR_ACCEL_CALIBRATION_COMMAND", (byte) 27));
        linkedHashMap2.put((byte) 66, new BtCommandDetails((byte) 66, "GET_WR_ACCEL_SAMPLING_RATE_COMMAND", (byte) 65));
        linkedHashMap2.put((byte) 69, new BtCommandDetails((byte) 69, "GET_WR_ACCEL_LPMODE_COMMAND", (byte) 68));
        linkedHashMap2.put((byte) 72, new BtCommandDetails((byte) 72, "GET_WR_ACCEL_HRMODE_COMMAND", (byte) 71));
        mBtGetCommandMap = Collections.unmodifiableMap(linkedHashMap2);
        LinkedHashMap linkedHashMap3 = new LinkedHashMap();
        linkedHashMap3.put((byte) 9, new BtCommandDetails((byte) 9, "SET_WR_ACCEL_RANGE_COMMAND"));
        linkedHashMap3.put((byte) 26, new BtCommandDetails((byte) 26, "SET_WR_ACCEL_CALIBRATION_COMMAND"));
        linkedHashMap3.put((byte) 64, new BtCommandDetails((byte) 64, "SET_WR_ACCEL_SAMPLING_RATE_COMMAND"));
        linkedHashMap3.put((byte) 67, new BtCommandDetails((byte) 67, "SET_WR_ACCEL_LPMODE_COMMAND"));
        linkedHashMap3.put((byte) 70, new BtCommandDetails((byte) 70, "SET_WR_ACCEL_HRMODE_COMMAND"));
        mBtSetCommandMap = Collections.unmodifiableMap(linkedHashMap3);
        Integer[] numArr = {0, 1, 2, 3};
        ListofLIS2DW12AccelRangeConfigValues = numArr;
        String[] strArr2 = {"Power-down", "12.5Hz", "12.5Hz", "25.0Hz", "50.0Hz", "100.0Hz", "200.0Hz", "400.0Hz", "800.0Hz", "1600.0Hz"};
        ListofLIS2DW12AccelRateHpm = strArr2;
        Integer[] numArr2 = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        ListofLIS2DW12AccelRateHpmConfigValues = numArr2;
        String[] strArr3 = {"Power-down", "1.6Hz", "12.5Hz", "25.0Hz", "50.0Hz", "100.0Hz", "200.0Hz", "200.0Hz", "200.0Hz", "200.0Hz"};
        ListofLIS2DW12AccelRateLpm = strArr3;
        Integer[] numArr3 = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        ListofLIS2DW12AccelRateLpmConfigValues = numArr3;
        configOptionAccelRange = new ConfigOptionDetailsSensor("Wide Range Accel Range", DatabaseConfigHandle.WR_ACC_RANGE, strArr, numArr, ConfigOptionDetails.GUI_COMPONENT_TYPE.COMBOBOX, Configuration.Shimmer3.CompatibilityInfoForMaps.listOfCompatibleVersionInfoLIS2DW12);
        configOptionAccelRate = new ConfigOptionDetailsSensor("Wide Range Accel Rate", DatabaseConfigHandle.WR_ACC_RATE, strArr2, numArr2, ConfigOptionDetails.GUI_COMPONENT_TYPE.COMBOBOX, Configuration.Shimmer3.CompatibilityInfoForMaps.listOfCompatibleVersionInfoLIS2DW12, (List<ConfigOptionObject>) Arrays.asList(new ConfigOptionObject(1, strArr3, numArr3)));
        configOptionAccelLpm = new ConfigOptionDetailsSensor("Wide Range Accel Low-Power Mode", DatabaseConfigHandle.WR_ACC_LPM, ConfigOptionDetails.GUI_COMPONENT_TYPE.CHECKBOX);
        ChannelDetails channelDetails = new ChannelDetails(ObjectClusterSensorName.ACCEL_WR_X, ObjectClusterSensorName.ACCEL_WR_X, "LIS2DW12_ACC_X", ChannelDetails.CHANNEL_DATA_TYPE.INT16, 2, ChannelDetails.CHANNEL_DATA_ENDIAN.LSB, "m/(s^2)", (List<ChannelDetails.CHANNEL_TYPE>) Arrays.asList(ChannelDetails.CHANNEL_TYPE.CAL, ChannelDetails.CHANNEL_TYPE.UNCAL), 4);
        channelLIS2DW12AccelX = channelDetails;
        ChannelDetails channelDetails2 = new ChannelDetails(ObjectClusterSensorName.ACCEL_WR_Y, ObjectClusterSensorName.ACCEL_WR_Y, "LIS2DW12_ACC_Y", ChannelDetails.CHANNEL_DATA_TYPE.INT16, 2, ChannelDetails.CHANNEL_DATA_ENDIAN.LSB, "m/(s^2)", (List<ChannelDetails.CHANNEL_TYPE>) Arrays.asList(ChannelDetails.CHANNEL_TYPE.CAL, ChannelDetails.CHANNEL_TYPE.UNCAL), 5);
        channelLIS2DW12AccelY = channelDetails2;
        ChannelDetails channelDetails3 = new ChannelDetails(ObjectClusterSensorName.ACCEL_WR_Z, ObjectClusterSensorName.ACCEL_WR_Z, "LIS2DW12_ACC_Z", ChannelDetails.CHANNEL_DATA_TYPE.INT16, 2, ChannelDetails.CHANNEL_DATA_ENDIAN.LSB, "m/(s^2)", (List<ChannelDetails.CHANNEL_TYPE>) Arrays.asList(ChannelDetails.CHANNEL_TYPE.CAL, ChannelDetails.CHANNEL_TYPE.UNCAL), 6);
        channelLIS2DW12AccelZ = channelDetails3;
        LinkedHashMap linkedHashMap4 = new LinkedHashMap();
        linkedHashMap4.put(ObjectClusterSensorName.ACCEL_WR_X, channelDetails);
        linkedHashMap4.put(ObjectClusterSensorName.ACCEL_WR_Y, channelDetails2);
        linkedHashMap4.put(ObjectClusterSensorName.ACCEL_WR_Z, channelDetails3);
        mChannelMapRef = Collections.unmodifiableMap(linkedHashMap4);
        sensorGroupLpmAccel = new SensorGroupingDetails("Wide-Range Accelerometer", Arrays.asList(39), Configuration.Shimmer3.CompatibilityInfoForMaps.listOfCompatibleVersionInfoLIS2DW12);
    }

    public CalibDetailsKinematic mCurrentCalibDetailsAccelWr;
    public boolean mIsUsingDefaultWRAccelParam;
    protected int mAccelRange;
    protected boolean mHighPerModeAccelWR;
    protected boolean mHighResAccelWR;
    protected int mLIS2DW12DigitalAccelRate;
    protected boolean mLowPowerAccelWR;
    protected int mSensorIdAccel;
    private CalibDetailsKinematic calibDetailsAccelWr16g;
    private CalibDetailsKinematic calibDetailsAccelWr2g;
    private CalibDetailsKinematic calibDetailsAccelWr4g;
    private CalibDetailsKinematic calibDetailsAccelWr8g;

    public SensorLIS2DW12() {
        super(AbstractSensor.SENSORS.LIS2DW12);
        this.mSensorIdAccel = -1;
        this.mAccelRange = 0;
        this.mIsUsingDefaultWRAccelParam = true;
        this.mHighResAccelWR = true;
        this.mHighPerModeAccelWR = true;
        this.mLowPowerAccelWR = false;
        this.mLIS2DW12DigitalAccelRate = 0;
        Integer[] numArr = ListofLIS2DW12AccelRangeConfigValues;
        int iIntValue = numArr[0].intValue();
        String[] strArr = ListofLIS2DW12AccelRange;
        String str = strArr[0];
        double[][] dArr = DefaultAlignmentMatrixWideRangeAccelShimmer3R;
        double[][] dArr2 = DefaultSensitivityMatrixWideRangeAccel2gShimmer3R;
        double[][] dArr3 = DefaultOffsetVectorWideRangeAccelShimmer3R;
        this.calibDetailsAccelWr2g = new CalibDetailsKinematic(iIntValue, str, dArr, dArr2, dArr3);
        this.calibDetailsAccelWr4g = new CalibDetailsKinematic(numArr[1].intValue(), strArr[1], dArr, DefaultSensitivityMatrixWideRangeAccel4gShimmer3R, dArr3);
        this.calibDetailsAccelWr8g = new CalibDetailsKinematic(numArr[2].intValue(), strArr[2], dArr, DefaultSensitivityMatrixWideRangeAccel8gShimmer3R, dArr3);
        this.calibDetailsAccelWr16g = new CalibDetailsKinematic(numArr[3].intValue(), strArr[3], dArr, DefaultSensitivityMatrixWideRangeAccel16gShimmer3R, dArr3);
        this.mCurrentCalibDetailsAccelWr = this.calibDetailsAccelWr2g;
        initialise();
    }

    public SensorLIS2DW12(ShimmerVerObject shimmerVerObject) {
        super(AbstractSensor.SENSORS.LIS2DW12, shimmerVerObject);
        this.mSensorIdAccel = -1;
        this.mAccelRange = 0;
        this.mIsUsingDefaultWRAccelParam = true;
        this.mHighResAccelWR = true;
        this.mHighPerModeAccelWR = true;
        this.mLowPowerAccelWR = false;
        this.mLIS2DW12DigitalAccelRate = 0;
        Integer[] numArr = ListofLIS2DW12AccelRangeConfigValues;
        int iIntValue = numArr[0].intValue();
        String[] strArr = ListofLIS2DW12AccelRange;
        String str = strArr[0];
        double[][] dArr = DefaultAlignmentMatrixWideRangeAccelShimmer3R;
        double[][] dArr2 = DefaultSensitivityMatrixWideRangeAccel2gShimmer3R;
        double[][] dArr3 = DefaultOffsetVectorWideRangeAccelShimmer3R;
        this.calibDetailsAccelWr2g = new CalibDetailsKinematic(iIntValue, str, dArr, dArr2, dArr3);
        this.calibDetailsAccelWr4g = new CalibDetailsKinematic(numArr[1].intValue(), strArr[1], dArr, DefaultSensitivityMatrixWideRangeAccel4gShimmer3R, dArr3);
        this.calibDetailsAccelWr8g = new CalibDetailsKinematic(numArr[2].intValue(), strArr[2], dArr, DefaultSensitivityMatrixWideRangeAccel8gShimmer3R, dArr3);
        this.calibDetailsAccelWr16g = new CalibDetailsKinematic(numArr[3].intValue(), strArr[3], dArr, DefaultSensitivityMatrixWideRangeAccel16gShimmer3R, dArr3);
        this.mCurrentCalibDetailsAccelWr = this.calibDetailsAccelWr2g;
        initialise();
    }

    public SensorLIS2DW12(ShimmerDevice shimmerDevice) {
        super(AbstractSensor.SENSORS.LIS2DW12, shimmerDevice);
        this.mSensorIdAccel = -1;
        this.mAccelRange = 0;
        this.mIsUsingDefaultWRAccelParam = true;
        this.mHighResAccelWR = true;
        this.mHighPerModeAccelWR = true;
        this.mLowPowerAccelWR = false;
        this.mLIS2DW12DigitalAccelRate = 0;
        Integer[] numArr = ListofLIS2DW12AccelRangeConfigValues;
        int iIntValue = numArr[0].intValue();
        String[] strArr = ListofLIS2DW12AccelRange;
        String str = strArr[0];
        double[][] dArr = DefaultAlignmentMatrixWideRangeAccelShimmer3R;
        double[][] dArr2 = DefaultSensitivityMatrixWideRangeAccel2gShimmer3R;
        double[][] dArr3 = DefaultOffsetVectorWideRangeAccelShimmer3R;
        this.calibDetailsAccelWr2g = new CalibDetailsKinematic(iIntValue, str, dArr, dArr2, dArr3);
        this.calibDetailsAccelWr4g = new CalibDetailsKinematic(numArr[1].intValue(), strArr[1], dArr, DefaultSensitivityMatrixWideRangeAccel4gShimmer3R, dArr3);
        this.calibDetailsAccelWr8g = new CalibDetailsKinematic(numArr[2].intValue(), strArr[2], dArr, DefaultSensitivityMatrixWideRangeAccel8gShimmer3R, dArr3);
        this.calibDetailsAccelWr16g = new CalibDetailsKinematic(numArr[3].intValue(), strArr[3], dArr, DefaultSensitivityMatrixWideRangeAccel16gShimmer3R, dArr3);
        this.mCurrentCalibDetailsAccelWr = this.calibDetailsAccelWr2g;
        initialise();
    }

    public static int getAccelRateFromFreq(boolean z, double d, boolean z2) {
        if (!z) {
            return 0;
        }
        if (z2 || d <= 12.5d) {
            return 1;
        }
        if (d <= 25.0d) {
            return 3;
        }
        if (d <= 50.0d) {
            return 4;
        }
        if (d <= 100.0d) {
            return 5;
        }
        if (d <= 200.0d) {
            return 6;
        }
        if (d <= 400.0d) {
            return 7;
        }
        return d <= 800.0d ? 8 : 9;
    }

    public static String parseFromDBColumnToGUIChannel(String str) {
        return AbstractSensor.parseFromDBColumnToGUIChannel(mChannelMapRef, str);
    }

    public static String parseFromGUIChannelsToDBColumn(String str) {
        return AbstractSensor.parseFromGUIChannelsToDBColumn(mChannelMapRef, str);
    }

    public int getAccelRange() {
        return this.mAccelRange;
    }

    public CalibDetailsKinematic getCurrentCalibDetailsAccelWr() {
        return this.mCurrentCalibDetailsAccelWr;
    }

    public int getHighPerModeAccelWREnabled() {
        return this.mHighPerModeAccelWR ? 1 : 0;
    }

    public int getLIS2DW12DigitalAccelRate() {
        return this.mLIS2DW12DigitalAccelRate;
    }

    public void setLIS2DW12DigitalAccelRate(int i) {
        setLIS2DW12DigitalAccelRateInternal(i);
        int i2 = 0;
        if (this.mLowPowerAccelWR) {
            Integer[] numArr = ListofLIS2DW12AccelRateLpmConfigValues;
            int length = numArr.length;
            while (i2 < length) {
                if (numArr[i2].intValue() == i) {
                    return;
                } else {
                    i2++;
                }
            }
            setLIS2DW12DigitalAccelRateInternal(ListofLIS2DW12AccelRateLpmConfigValues[r5.length - 1].intValue());
            return;
        }
        Integer[] numArr2 = ListofLIS2DW12AccelRateHpmConfigValues;
        int length2 = numArr2.length;
        while (i2 < length2) {
            if (numArr2[i2].intValue() == i) {
                return;
            } else {
                i2++;
            }
        }
        setLIS2DW12DigitalAccelRateInternal(ListofLIS2DW12AccelRateHpmConfigValues[r5.length - 1].intValue());
    }

    @Override // com.shimmerresearch.sensors.AbstractSensor
    public Object getSettings(String str, Configuration.COMMUNICATION_TYPE communication_type) {
        return null;
    }

    public boolean isLIS2DW12DigitalAccelHPM() {
        return this.mHighPerModeAccelWR;
    }

    public boolean isLIS2DW12DigitalAccelLPM() {
        return this.mLowPowerAccelWR;
    }

    @Override // com.shimmerresearch.sensors.AbstractSensor
    public boolean processResponse(int i, Object obj, Configuration.COMMUNICATION_TYPE communication_type) {
        return false;
    }

    public void setHighResAccelWR(int i) {
        this.mHighResAccelWR = i > 0;
    }

    public void setHighResAccelWR(boolean z) {
        this.mHighResAccelWR = z;
    }

    public void setLIS2DW12DigitalAccelRateInternal(int i) {
        this.mLIS2DW12DigitalAccelRate = i;
    }

    @Override // com.shimmerresearch.sensors.AbstractSensor
    public void generateSensorMap() {
        super.createLocalSensorMapWithCustomParser(mSensorMapRef, mChannelMapRef);
    }

    @Override // com.shimmerresearch.sensors.AbstractSensor
    public void generateConfigOptionsMap() {
        addConfigOption(configOptionAccelRange);
        addConfigOption(configOptionAccelRate);
        addConfigOption(configOptionAccelLpm);
    }

    @Override // com.shimmerresearch.sensors.AbstractSensor
    public void generateSensorGroupMapping() {
        this.mSensorGroupingMap = new LinkedHashMap<>();
        this.mSensorGroupingMap.put(Integer.valueOf(Configuration.Shimmer3.LABEL_SENSOR_TILE.WIDE_RANGE_ACCEL_3R.ordinal()), sensorGroupLpmAccel);
        super.updateSensorGroupingMap();
    }

    @Override // com.shimmerresearch.sensors.AbstractSensor
    public ObjectCluster processDataCustom(SensorDetails sensorDetails, byte[] bArr, Configuration.COMMUNICATION_TYPE communication_type, ObjectCluster objectCluster, boolean z, double d) {
        ObjectCluster objectClusterProcessDataCommon = sensorDetails.processDataCommon(bArr, communication_type, objectCluster, z, d);
        if (mEnableCalibration && sensorDetails.mSensorDetailsRef.mGuiFriendlyLabel.equals("Wide-Range Accelerometer") && this.mCurrentCalibDetailsAccelWr != null) {
            double[] dArr = new double[3];
            for (ChannelDetails channelDetails : sensorDetails.mListOfChannels) {
                if (channelDetails.mObjectClusterName.equals(ObjectClusterSensorName.ACCEL_WR_X)) {
                    dArr[0] = ObjectCluster.returnFormatCluster(objectClusterProcessDataCommon.getCollectionOfFormatClusters(channelDetails.mObjectClusterName), channelDetails.mChannelFormatDerivedFromShimmerDataPacket.toString()).mData;
                } else if (channelDetails.mObjectClusterName.equals(ObjectClusterSensorName.ACCEL_WR_Y)) {
                    dArr[1] = ObjectCluster.returnFormatCluster(objectClusterProcessDataCommon.getCollectionOfFormatClusters(channelDetails.mObjectClusterName), channelDetails.mChannelFormatDerivedFromShimmerDataPacket.toString()).mData;
                } else if (channelDetails.mObjectClusterName.equals(ObjectClusterSensorName.ACCEL_WR_Z)) {
                    dArr[2] = ObjectCluster.returnFormatCluster(objectClusterProcessDataCommon.getCollectionOfFormatClusters(channelDetails.mObjectClusterName), channelDetails.mChannelFormatDerivedFromShimmerDataPacket.toString()).mData;
                }
            }
            double[] dArrCalibrateInertialSensorData = UtilCalibration.calibrateInertialSensorData(dArr, this.mCurrentCalibDetailsAccelWr);
            if (sensorDetails.mSensorDetailsRef.mGuiFriendlyLabel.equals("Wide-Range Accelerometer")) {
                for (ChannelDetails channelDetails2 : sensorDetails.mListOfChannels) {
                    if (channelDetails2.mObjectClusterName.equals(ObjectClusterSensorName.ACCEL_WR_X)) {
                        objectClusterProcessDataCommon.addCalData(channelDetails2, dArrCalibrateInertialSensorData[0], objectClusterProcessDataCommon.getIndexKeeper() - 3, isUsingDefaultWRAccelParam());
                    } else if (channelDetails2.mObjectClusterName.equals(ObjectClusterSensorName.ACCEL_WR_Y)) {
                        objectClusterProcessDataCommon.addCalData(channelDetails2, dArrCalibrateInertialSensorData[1], objectClusterProcessDataCommon.getIndexKeeper() - 2, isUsingDefaultWRAccelParam());
                    } else if (channelDetails2.mObjectClusterName.equals(ObjectClusterSensorName.ACCEL_WR_Z)) {
                        objectClusterProcessDataCommon.addCalData(channelDetails2, dArrCalibrateInertialSensorData[2], objectClusterProcessDataCommon.getIndexKeeper() - 1, isUsingDefaultWRAccelParam());
                    }
                }
            }
            if (this.mIsDebugOutput) {
                super.consolePrintChannelsCal(objectClusterProcessDataCommon, Arrays.asList(new String[]{ObjectClusterSensorName.ACCEL_WR_X, ChannelDetails.CHANNEL_TYPE.UNCAL.toString()}, new String[]{ObjectClusterSensorName.ACCEL_WR_Y, ChannelDetails.CHANNEL_TYPE.UNCAL.toString()}, new String[]{ObjectClusterSensorName.ACCEL_WR_Z, ChannelDetails.CHANNEL_TYPE.UNCAL.toString()}, new String[]{ObjectClusterSensorName.ACCEL_WR_X, ChannelDetails.CHANNEL_TYPE.CAL.toString()}, new String[]{ObjectClusterSensorName.ACCEL_WR_Y, ChannelDetails.CHANNEL_TYPE.CAL.toString()}, new String[]{ObjectClusterSensorName.ACCEL_WR_Z, ChannelDetails.CHANNEL_TYPE.CAL.toString()}));
            }
        }
        return objectClusterProcessDataCommon;
    }

    @Override // com.shimmerresearch.sensors.AbstractSensor
    public void checkShimmerConfigBeforeConfiguring() {
        if (isSensorEnabled(this.mSensorIdAccel)) {
            return;
        }
        setDefaultLIS2DW12AccelSensorConfig(false);
    }

    @Override // com.shimmerresearch.sensors.AbstractSensor
    public void configBytesGenerate(ShimmerDevice shimmerDevice, byte[] bArr, Configuration.COMMUNICATION_TYPE communication_type) {
        ConfigByteLayout configByteLayout = shimmerDevice.getConfigByteLayout();
        if (configByteLayout instanceof ConfigByteLayoutShimmer3) {
            ConfigByteLayoutShimmer3 configByteLayoutShimmer3 = (ConfigByteLayoutShimmer3) configByteLayout;
            int i = configByteLayoutShimmer3.idxConfigSetupByte0;
            bArr[i] = (byte) (bArr[i] | ((byte) ((getLIS2DW12DigitalAccelRate() & configByteLayoutShimmer3.maskLSM303DLHCAccelSamplingRate) << configByteLayoutShimmer3.bitShiftLSM303DLHCAccelSamplingRate)));
            int i2 = configByteLayoutShimmer3.idxConfigSetupByte0;
            bArr[i2] = (byte) (bArr[i2] | ((byte) ((getAccelRange() & configByteLayoutShimmer3.maskLSM303DLHCAccelRange) << configByteLayoutShimmer3.bitShiftLSM303DLHCAccelRange)));
            if (isLowPowerAccelWR()) {
                int i3 = configByteLayoutShimmer3.idxConfigSetupByte0;
                bArr[i3] = (byte) (bArr[i3] | (configByteLayoutShimmer3.maskLSM303DLHCAccelLPM << configByteLayoutShimmer3.bitShiftLSM303DLHCAccelLPM));
            }
            if (isHighPerModeAccelWR()) {
                int i4 = configByteLayoutShimmer3.idxConfigSetupByte0;
                bArr[i4] = (byte) (bArr[i4] | (configByteLayoutShimmer3.maskLSM303DLHCAccelHRM << configByteLayoutShimmer3.bitShiftLSM303DLHCAccelHRM));
            }
            System.arraycopy(generateCalParamLIS2DW12Accel(), 0, bArr, configByteLayoutShimmer3.idxLSM303DLHCAccelCalibration, configByteLayoutShimmer3.lengthGeneralCalibrationBytes);
        }
    }

    @Override // com.shimmerresearch.sensors.AbstractSensor
    public void configBytesParse(ShimmerDevice shimmerDevice, byte[] bArr, Configuration.COMMUNICATION_TYPE communication_type) {
        ConfigByteLayout configByteLayout = shimmerDevice.getConfigByteLayout();
        if (configByteLayout instanceof ConfigByteLayoutShimmer3) {
            ConfigByteLayoutShimmer3 configByteLayoutShimmer3 = (ConfigByteLayoutShimmer3) configByteLayout;
            setLIS2DW12DigitalAccelRate((bArr[configByteLayoutShimmer3.idxConfigSetupByte0] >> configByteLayoutShimmer3.bitShiftLSM303DLHCAccelSamplingRate) & configByteLayoutShimmer3.maskLSM303DLHCAccelSamplingRate);
            setLIS2DW12AccelRange((bArr[configByteLayoutShimmer3.idxConfigSetupByte0] >> configByteLayoutShimmer3.bitShiftLSM303DLHCAccelRange) & configByteLayoutShimmer3.maskLSM303DLHCAccelRange);
            if (((bArr[configByteLayoutShimmer3.idxConfigSetupByte0] >> configByteLayoutShimmer3.bitShiftLSM303DLHCAccelLPM) & configByteLayoutShimmer3.maskLSM303DLHCAccelLPM) == configByteLayoutShimmer3.maskLSM303DLHCAccelLPM) {
                setLowPowerAccelWR(true);
            } else {
                setLowPowerAccelWR(false);
            }
            if (((bArr[configByteLayoutShimmer3.idxConfigSetupByte0] >> configByteLayoutShimmer3.bitShiftLSM303DLHCAccelHRM) & configByteLayoutShimmer3.maskLSM303DLHCAccelHRM) == configByteLayoutShimmer3.maskLSM303DLHCAccelHRM) {
                setHighPerModeAccelWR(true);
            } else {
                setHighPerModeAccelWR(false);
            }
            if (shimmerDevice.isConnected()) {
                getCurrentCalibDetailsAccelWr().mCalibReadSource = CalibDetails.CALIB_READ_SOURCE.INFOMEM;
            }
            byte[] bArr2 = new byte[configByteLayoutShimmer3.lengthGeneralCalibrationBytes];
            System.arraycopy(bArr, configByteLayoutShimmer3.idxLSM303DLHCAccelCalibration, bArr2, 0, configByteLayoutShimmer3.lengthGeneralCalibrationBytes);
            parseCalibParamFromPacketAccelWR(bArr2, CalibDetails.CALIB_READ_SOURCE.INFOMEM);
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:32:0x0066  */
    @Override // com.shimmerresearch.sensors.AbstractSensor
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Object setConfigValueUsingConfigLabel(java.lang.Integer r5, java.lang.String r6, java.lang.Object r7) {
        /*
            r4 = this;
            r6.hashCode()
            int r0 = r6.hashCode()
            java.lang.String r1 = "Wide Range Accel Range"
            java.lang.String r2 = "Wide Range Accel Rate"
            r3 = -1
            switch(r0) {
                case -2088121569: goto L38;
                case 2539776: goto L2d;
                case 78727453: goto L22;
                case 338100565: goto L19;
                case 1534927304: goto L10;
                default: goto Lf;
            }
        Lf:
            goto L42
        L10:
            boolean r0 = r6.equals(r2)
            if (r0 != 0) goto L17
            goto L42
        L17:
            r3 = 4
            goto L42
        L19:
            boolean r0 = r6.equals(r1)
            if (r0 != 0) goto L20
            goto L42
        L20:
            r3 = 3
            goto L42
        L22:
            java.lang.String r0 = "Range"
            boolean r0 = r6.equals(r0)
            if (r0 != 0) goto L2b
            goto L42
        L2b:
            r3 = 2
            goto L42
        L2d:
            java.lang.String r0 = "Rate"
            boolean r0 = r6.equals(r0)
            if (r0 != 0) goto L36
            goto L42
        L36:
            r3 = 1
            goto L42
        L38:
            java.lang.String r0 = "Wide Range Accel Low-Power Mode"
            boolean r0 = r6.equals(r0)
            if (r0 != 0) goto L41
            goto L42
        L41:
            r3 = 0
        L42:
            switch(r3) {
                case 0: goto L77;
                case 1: goto L66;
                case 2: goto L5a;
                case 3: goto L50;
                case 4: goto L46;
                default: goto L45;
            }
        L45:
            goto L72
        L46:
            java.lang.Integer r7 = (java.lang.Integer) r7
            int r5 = r7.intValue()
            r4.setLIS2DW12DigitalAccelRate(r5)
            goto L80
        L50:
            java.lang.Integer r7 = (java.lang.Integer) r7
            int r5 = r7.intValue()
            r4.setLIS2DW12AccelRange(r5)
            goto L80
        L5a:
            int r0 = r5.intValue()
            int r3 = r4.mSensorIdAccel
            if (r0 != r3) goto L66
            r4.setConfigValueUsingConfigLabel(r1, r7)
            goto L80
        L66:
            int r0 = r5.intValue()
            int r1 = r4.mSensorIdAccel
            if (r0 != r1) goto L72
            r4.setConfigValueUsingConfigLabel(r2, r7)
            goto L80
        L72:
            java.lang.Object r5 = super.setConfigValueUsingConfigLabelCommon(r5, r6, r7)
            goto L81
        L77:
            java.lang.Boolean r7 = (java.lang.Boolean) r7
            boolean r5 = r7.booleanValue()
            r4.setLowPowerAccelWR(r5)
        L80:
            r5 = 0
        L81:
            boolean r7 = r6.equals(r2)
            if (r7 == 0) goto L8a
            r4.checkConfigOptionValues(r6)
        L8a:
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.shimmerresearch.sensors.lis2dw12.SensorLIS2DW12.setConfigValueUsingConfigLabel(java.lang.Integer, java.lang.String, java.lang.Object):java.lang.Object");
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0076  */
    @Override // com.shimmerresearch.sensors.AbstractSensor
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Object getConfigValueUsingConfigLabel(java.lang.Integer r5, java.lang.String r6) {
        /*
            r4 = this;
            java.lang.String r0 = "Wide Range Accel Rate"
            boolean r1 = r6.equals(r0)
            if (r1 == 0) goto Lb
            r4.checkConfigOptionValues(r6)
        Lb:
            r6.hashCode()
            int r1 = r6.hashCode()
            java.lang.String r2 = "Wide Range Accel Range"
            r3 = -1
            switch(r1) {
                case -2088121569: goto L41;
                case 2539776: goto L36;
                case 78727453: goto L2b;
                case 338100565: goto L22;
                case 1534927304: goto L19;
                default: goto L18;
            }
        L18:
            goto L4b
        L19:
            boolean r1 = r6.equals(r0)
            if (r1 != 0) goto L20
            goto L4b
        L20:
            r3 = 4
            goto L4b
        L22:
            boolean r1 = r6.equals(r2)
            if (r1 != 0) goto L29
            goto L4b
        L29:
            r3 = 3
            goto L4b
        L2b:
            java.lang.String r1 = "Range"
            boolean r1 = r6.equals(r1)
            if (r1 != 0) goto L34
            goto L4b
        L34:
            r3 = 2
            goto L4b
        L36:
            java.lang.String r1 = "Rate"
            boolean r1 = r6.equals(r1)
            if (r1 != 0) goto L3f
            goto L4b
        L3f:
            r3 = 1
            goto L4b
        L41:
            java.lang.String r1 = "Wide Range Accel Low-Power Mode"
            boolean r1 = r6.equals(r1)
            if (r1 != 0) goto L4a
            goto L4b
        L4a:
            r3 = 0
        L4b:
            switch(r3) {
                case 0: goto L80;
                case 1: goto L6e;
                case 2: goto L61;
                case 3: goto L58;
                case 4: goto L4f;
                default: goto L4e;
            }
        L4e:
            goto L7b
        L4f:
            int r5 = r4.getLIS2DW12DigitalAccelRate()
            java.lang.Integer r5 = java.lang.Integer.valueOf(r5)
            goto L88
        L58:
            int r5 = r4.getAccelRange()
            java.lang.Integer r5 = java.lang.Integer.valueOf(r5)
            goto L88
        L61:
            int r1 = r5.intValue()
            int r3 = r4.mSensorIdAccel
            if (r1 != r3) goto L6e
            java.lang.Object r5 = r4.getConfigValueUsingConfigLabel(r2)
            goto L88
        L6e:
            int r1 = r5.intValue()
            int r2 = r4.mSensorIdAccel
            if (r1 != r2) goto L7b
            java.lang.Object r5 = r4.getConfigValueUsingConfigLabel(r0)
            goto L88
        L7b:
            java.lang.Object r5 = super.getConfigValueUsingConfigLabelCommon(r5, r6)
            goto L88
        L80:
            boolean r5 = r4.isLIS2DW12DigitalAccelLPM()
            java.lang.Boolean r5 = java.lang.Boolean.valueOf(r5)
        L88:
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.shimmerresearch.sensors.lis2dw12.SensorLIS2DW12.getConfigValueUsingConfigLabel(java.lang.Integer, java.lang.String):java.lang.Object");
    }

    @Override // com.shimmerresearch.sensors.AbstractSensor
    public void setSensorSamplingRate(double d) {
        setLIS2DW12AccelRateFromFreq(d);
    }

    @Override // com.shimmerresearch.sensors.AbstractSensor
    public boolean setDefaultConfigForSensor(int i, boolean z) {
        if (!this.mSensorMap.containsKey(Integer.valueOf(i))) {
            return false;
        }
        if (i != this.mSensorIdAccel) {
            return true;
        }
        setDefaultLIS2DW12AccelSensorConfig(z);
        return true;
    }

    @Override // com.shimmerresearch.sensors.AbstractSensor
    public boolean checkConfigOptionValues(String str) {
        if (!this.mConfigOptionsMap.containsKey(str)) {
            return false;
        }
        if (str == "Wide Range Accel Rate") {
            if (isLIS2DW12DigitalAccelLPM()) {
                this.mConfigOptionsMap.get(str).setIndexOfValuesToUse(1);
            } else {
                this.mConfigOptionsMap.get(str).setIndexOfValuesToUse(0);
            }
        }
        return true;
    }

    @Override // com.shimmerresearch.sensors.AbstractSensor
    public boolean isSensorUsingDefaultCal(int i) {
        if (i == this.mSensorIdAccel) {
            return isUsingDefaultWRAccelParam();
        }
        return false;
    }

    @Override // com.shimmerresearch.sensors.AbstractSensor
    public void setCalibrationMapPerSensor(int i, TreeMap<Integer, CalibDetails> treeMap) {
        super.setCalibrationMapPerSensor(i, treeMap);
        updateCurrentAccelWrCalibInUse();
    }

    @Override // com.shimmerresearch.sensors.AbstractSensor
    public ActionSetting setSettings(String str, Object obj, Configuration.COMMUNICATION_TYPE communication_type) {
        return new ActionSetting(communication_type);
    }

    @Override // com.shimmerresearch.sensors.AbstractSensor
    public LinkedHashMap<String, Object> generateConfigMap() {
        LinkedHashMap<String, Object> linkedHashMap = new LinkedHashMap<>();
        linkedHashMap.put(DatabaseConfigHandle.WR_ACC_RATE, Integer.valueOf(getLIS2DW12DigitalAccelRate()));
        linkedHashMap.put(DatabaseConfigHandle.WR_ACC_RANGE, Integer.valueOf(getAccelRange()));
        linkedHashMap.put(DatabaseConfigHandle.WR_ACC_LPM, Integer.valueOf(getLowPowerAccelEnabled()));
        linkedHashMap.put(DatabaseConfigHandle.WR_ACC_HPM, Integer.valueOf(getHighPerModeAccelWREnabled()));
        AbstractSensor.addCalibDetailsToDbMap(linkedHashMap, getCurrentCalibDetailsAccelWr(), DatabaseConfigHandle.LIST_OF_CALIB_HANDLES_WR_ACCEL, DatabaseConfigHandle.WR_ACC_CALIB_TIME);
        return linkedHashMap;
    }

    @Override // com.shimmerresearch.sensors.AbstractSensor
    public void parseConfigMap(LinkedHashMap<String, Object> linkedHashMap) {
        if (linkedHashMap.containsKey(DatabaseConfigHandle.WR_ACC_LPM)) {
            setLowPowerAccelWR(((Double) linkedHashMap.get(DatabaseConfigHandle.WR_ACC_LPM)).doubleValue() > 0.0d);
        }
        if (linkedHashMap.containsKey(DatabaseConfigHandle.WR_ACC_HPM)) {
            setHighResAccelWR(((Double) linkedHashMap.get(DatabaseConfigHandle.WR_ACC_HPM)).doubleValue() > 0.0d);
        }
        if (linkedHashMap.containsKey(DatabaseConfigHandle.WR_ACC_RATE)) {
            setLIS2DW12DigitalAccelRate(((Double) linkedHashMap.get(DatabaseConfigHandle.WR_ACC_RATE)).intValue());
        }
        if (linkedHashMap.containsKey(DatabaseConfigHandle.WR_ACC_RANGE)) {
            setLIS2DW12AccelRange(((Double) linkedHashMap.get(DatabaseConfigHandle.WR_ACC_RANGE)).intValue());
        }
        parseCalibDetailsKinematicFromDb(linkedHashMap, 39, getAccelRange(), DatabaseConfigHandle.LIST_OF_CALIB_HANDLES_WR_ACCEL, DatabaseConfigHandle.WR_ACC_CALIB_TIME);
    }

    @Override // com.shimmerresearch.sensors.AbstractSensor
    public void initialise() {
        this.mSensorIdAccel = 39;
        super.initialise();
        updateCurrentAccelWrCalibInUse();
    }

    @Override // com.shimmerresearch.sensors.AbstractSensor
    public void generateCalibMap() {
        super.generateCalibMap();
        TreeMap<Integer, CalibDetails> treeMap = new TreeMap<>();
        treeMap.put(Integer.valueOf(this.calibDetailsAccelWr2g.mRangeValue), this.calibDetailsAccelWr2g);
        treeMap.put(Integer.valueOf(this.calibDetailsAccelWr4g.mRangeValue), this.calibDetailsAccelWr4g);
        treeMap.put(Integer.valueOf(this.calibDetailsAccelWr8g.mRangeValue), this.calibDetailsAccelWr8g);
        treeMap.put(Integer.valueOf(this.calibDetailsAccelWr16g.mRangeValue), this.calibDetailsAccelWr16g);
        setCalibrationMapPerSensor(this.mSensorIdAccel, treeMap);
        updateCurrentAccelWrCalibInUse();
    }

    public void updateCurrentAccelWrCalibInUse() {
        this.mCurrentCalibDetailsAccelWr = getCurrentCalibDetailsIfKinematic(this.mSensorIdAccel, getAccelRange());
    }

    public boolean isUsingDefaultWRAccelParam() {
        return this.mCurrentCalibDetailsAccelWr.isUsingDefaultParameters();
    }

    public void updateIsUsingDefaultWRAccelParam() {
        this.mIsUsingDefaultWRAccelParam = getCurrentCalibDetailsAccelWr().isUsingDefaultParameters();
    }

    public int setLIS2DW12AccelRateFromFreq(double d) {
        setLIS2DW12DigitalAccelRateInternal(getAccelRateFromFreqForSensor(isSensorEnabled(this.mSensorIdAccel), d, this.mLowPowerAccelWR));
        return this.mLIS2DW12DigitalAccelRate;
    }

    public int getAccelRateFromFreqForSensor(boolean z, double d, boolean z2) {
        return getAccelRateFromFreq(z, d, z2);
    }

    public void setLIS2DW12AccelRange(int i) {
        if (ArrayUtils.contains(ListofLIS2DW12AccelRangeConfigValues, Integer.valueOf(i))) {
            this.mAccelRange = i;
            updateCurrentAccelWrCalibInUse();
        }
    }

    public void setDefaultLIS2DW12AccelSensorConfig(boolean z) {
        if (z) {
            setLowPowerAccelWR(false);
        } else {
            setLIS2DW12AccelRange(0);
            setLowPowerAccelWR(true);
        }
    }

    public boolean isHighPerModeAccelWR() {
        return isLIS2DW12DigitalAccelHPM();
    }

    public void setHighPerModeAccelWR(int i) {
        this.mHighPerModeAccelWR = i > 0;
    }

    public void setHighPerModeAccelWR(boolean z) {
        this.mHighPerModeAccelWR = z;
    }

    public double getCalibTimeWRAccel() {
        return this.mCurrentCalibDetailsAccelWr.getCalibTimeMs();
    }

    public boolean isUsingValidWRAccelParam() {
        return (UtilShimmer.isAllZeros(getAlignmentMatrixWRAccel()) || UtilShimmer.isAllZeros(getSensitivityMatrixWRAccel())) ? false : true;
    }

    public double[][] getAlignmentMatrixWRAccel() {
        return this.mCurrentCalibDetailsAccelWr.getValidAlignmentMatrix();
    }

    public double[][] getSensitivityMatrixWRAccel() {
        return this.mCurrentCalibDetailsAccelWr.getValidSensitivityMatrix();
    }

    public double[][] getOffsetVectorMatrixWRAccel() {
        return this.mCurrentCalibDetailsAccelWr.getValidOffsetVector();
    }

    public boolean isLowPowerAccelWR() {
        return isLIS2DW12DigitalAccelLPM();
    }

    public void setLowPowerAccelWR(boolean z) {
        this.mLowPowerAccelWR = z;
        this.mHighPerModeAccelWR = !z;
        if (this.mShimmerDevice != null) {
            setLIS2DW12AccelRateFromFreq(getSamplingRateShimmer());
        }
    }

    public boolean isLowPowerAccelEnabled() {
        return isLIS2DW12DigitalAccelLPM();
    }

    public int getLowPowerAccelEnabled() {
        return isLIS2DW12DigitalAccelLPM() ? 1 : 0;
    }

    public void setLowPowerAccelEnabled(int i) {
        this.mLowPowerAccelWR = i > 0;
    }

    public byte[] generateCalParamLIS2DW12Accel() {
        return this.mCurrentCalibDetailsAccelWr.generateCalParamByteArray();
    }

    public void parseCalibParamFromPacketAccelWR(byte[] bArr, CalibDetails.CALIB_READ_SOURCE calib_read_source) {
        this.mCurrentCalibDetailsAccelWr.parseCalParamByteArray(bArr, calib_read_source);
    }

    public void setDefaultCalibrationShimmer3rWideRangeAccel() {
        this.mCurrentCalibDetailsAccelWr.resetToDefaultParameters();
    }

    public static class DatabaseChannelHandles {
        public static final String WR_ACC_X = "LIS2DW12_ACC_X";
        public static final String WR_ACC_Y = "LIS2DW12_ACC_Y";
        public static final String WR_ACC_Z = "LIS2DW12_ACC_Z";
    }

    public static final class DatabaseConfigHandle {
        public static final String WR_ACC = "LIS2DW12_Acc";
        public static final String WR_ACC_CALIB_TIME = "LIS2DW12_Acc_Calib_Time";
        public static final String WR_ACC_HPM = "LIS2DW12_Acc_HPM";
        public static final String WR_ACC_LPM = "LIS2DW12_Acc_LPM";
        public static final String WR_ACC_RANGE = "LIS2DW12_Acc_Range";
        public static final String WR_ACC_RATE = "LIS2DW12_Acc_Rate";
        public static final String WR_ACC_OFFSET_X = "LIS2DW12_Acc_Offset_X";
        public static final String WR_ACC_OFFSET_Y = "LIS2DW12_Acc_Offset_Y";
        public static final String WR_ACC_OFFSET_Z = "LIS2DW12_Acc_Offset_Z";
        public static final String WR_ACC_GAIN_X = "LIS2DW12_Acc_Gain_X";
        public static final String WR_ACC_GAIN_Y = "LIS2DW12_Acc_Gain_Y";
        public static final String WR_ACC_GAIN_Z = "LIS2DW12_Acc_Gain_Z";
        public static final String WR_ACC_ALIGN_XX = "LIS2DW12_Acc_Align_XX";
        public static final String WR_ACC_ALIGN_XY = "LIS2DW12_Acc_Align_XY";
        public static final String WR_ACC_ALIGN_XZ = "LIS2DW12_Acc_Align_XZ";
        public static final String WR_ACC_ALIGN_YX = "LIS2DW12_Acc_Align_YX";
        public static final String WR_ACC_ALIGN_YY = "LIS2DW12_Acc_Align_YY";
        public static final String WR_ACC_ALIGN_YZ = "LIS2DW12_Acc_Align_YZ";
        public static final String WR_ACC_ALIGN_ZX = "LIS2DW12_Acc_Align_ZX";
        public static final String WR_ACC_ALIGN_ZY = "LIS2DW12_Acc_Align_ZY";
        public static final String WR_ACC_ALIGN_ZZ = "LIS2DW12_Acc_Align_ZZ";
        public static final List<String> LIST_OF_CALIB_HANDLES_WR_ACCEL = Arrays.asList(WR_ACC_OFFSET_X, WR_ACC_OFFSET_Y, WR_ACC_OFFSET_Z, WR_ACC_GAIN_X, WR_ACC_GAIN_Y, WR_ACC_GAIN_Z, WR_ACC_ALIGN_XX, WR_ACC_ALIGN_XY, WR_ACC_ALIGN_XZ, WR_ACC_ALIGN_YX, WR_ACC_ALIGN_YY, WR_ACC_ALIGN_YZ, WR_ACC_ALIGN_ZX, WR_ACC_ALIGN_ZY, WR_ACC_ALIGN_ZZ);
    }

    public static class ObjectClusterSensorName {
        public static String ACCEL_WR_X = "Accel_WR_X";
        public static String ACCEL_WR_Y = "Accel_WR_Y";
        public static String ACCEL_WR_Z = "Accel_WR_Z";
    }

    public class GuiLabelConfig {
        public static final String LIS2DW12_ACCEL_LPM = "Wide Range Accel Low-Power Mode";
        public static final String LIS2DW12_ACCEL_RANGE = "Wide Range Accel Range";
        public static final String LIS2DW12_ACCEL_RATE = "Wide Range Accel Rate";

        public GuiLabelConfig() {
        }
    }

    public class GuiLabelSensors {
        public static final String ACCEL_WR = "Wide-Range Accelerometer";

        public GuiLabelSensors() {
        }
    }

    public class LABEL_SENSOR_TILE {
        public static final String ACCEL = "ACCEL";
        public static final String WIDE_RANGE_ACCEL = "Wide-Range Accelerometer";

        public LABEL_SENSOR_TILE() {
        }
    }
}
