package com.shimmerresearch.sensors.kionix;

import com.shimmerresearch.driver.Configuration;
import com.shimmerresearch.driver.ShimmerDevice;
import com.shimmerresearch.driver.calibration.CalibDetails;
import com.shimmerresearch.driver.calibration.CalibDetailsKinematic;
import com.shimmerresearch.driverUtilities.ChannelDetails;
import com.shimmerresearch.driverUtilities.SensorDetailsRef;
import com.shimmerresearch.driverUtilities.SensorGroupingDetails;
import com.shimmerresearch.driverUtilities.ShimmerVerObject;
import com.shimmerresearch.sensors.AbstractSensor;
import com.shimmerresearch.sensors.kionix.SensorKionixAccel;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/* loaded from: classes2.dex */
public class SensorKionixKXTC92050 extends SensorKionixAccel {
    public static final double[][] AlignmentMatrixLowNoiseAccelShimmer3 = {new double[]{0.0d, -1.0d, 0.0d}, new double[]{-1.0d, 0.0d, 0.0d}, new double[]{0.0d, 0.0d, -1.0d}};
    public static final double[][] OffsetVectorLowNoiseAccelShimmer3 = {new double[]{2253.0d}, new double[]{2253.0d}, new double[]{2253.0d}};
    public static final double[][] SensitivityMatrixLowNoiseAccel2gShimmer3 = {new double[]{92.0d, 0.0d, 0.0d}, new double[]{0.0d, 92.0d, 0.0d}, new double[]{0.0d, 0.0d, 92.0d}};
    public static final ChannelDetails channelAccelX;
    public static final ChannelDetails channelAccelY;
    public static final ChannelDetails channelAccelZ;
    public static final Map<String, ChannelDetails> mChannelMapRef;
    public static final Map<Integer, SensorDetailsRef> mSensorMapRef;
    public static final SensorGroupingDetails sensorGroupLnAccelKXTC92050;
    public static final SensorDetailsRef sensorKionixKXTC92050;
    private static final long serialVersionUID = -4547873490496111518L;

    static {
        SensorDetailsRef sensorDetailsRef = new SensorDetailsRef(128L, 128L, "Low-Noise Accelerometer", Configuration.Shimmer3.CompatibilityInfoForMaps.listOfCompatibleVersionInfoKionixKXTC92050, null, Arrays.asList(SensorKionixAccel.ObjectClusterSensorName.ACCEL_LN_X, SensorKionixAccel.ObjectClusterSensorName.ACCEL_LN_Y, SensorKionixAccel.ObjectClusterSensorName.ACCEL_LN_Z));
        sensorKionixKXTC92050 = sensorDetailsRef;
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put(2, sensorDetailsRef);
        mSensorMapRef = Collections.unmodifiableMap(linkedHashMap);
        ChannelDetails channelDetails = new ChannelDetails(SensorKionixAccel.ObjectClusterSensorName.ACCEL_LN_X, SensorKionixAccel.ObjectClusterSensorName.ACCEL_LN_X, DatabaseChannelHandles.LN_ACC_X, ChannelDetails.CHANNEL_DATA_TYPE.UINT12, 2, ChannelDetails.CHANNEL_DATA_ENDIAN.LSB, "m/(s^2)", (List<ChannelDetails.CHANNEL_TYPE>) Arrays.asList(ChannelDetails.CHANNEL_TYPE.CAL, ChannelDetails.CHANNEL_TYPE.UNCAL), 0);
        channelAccelX = channelDetails;
        ChannelDetails channelDetails2 = new ChannelDetails(SensorKionixAccel.ObjectClusterSensorName.ACCEL_LN_Y, SensorKionixAccel.ObjectClusterSensorName.ACCEL_LN_Y, DatabaseChannelHandles.LN_ACC_Y, ChannelDetails.CHANNEL_DATA_TYPE.UINT12, 2, ChannelDetails.CHANNEL_DATA_ENDIAN.LSB, "m/(s^2)", (List<ChannelDetails.CHANNEL_TYPE>) Arrays.asList(ChannelDetails.CHANNEL_TYPE.CAL, ChannelDetails.CHANNEL_TYPE.UNCAL), 1);
        channelAccelY = channelDetails2;
        ChannelDetails channelDetails3 = new ChannelDetails(SensorKionixAccel.ObjectClusterSensorName.ACCEL_LN_Z, SensorKionixAccel.ObjectClusterSensorName.ACCEL_LN_Z, DatabaseChannelHandles.LN_ACC_Z, ChannelDetails.CHANNEL_DATA_TYPE.UINT12, 2, ChannelDetails.CHANNEL_DATA_ENDIAN.LSB, "m/(s^2)", (List<ChannelDetails.CHANNEL_TYPE>) Arrays.asList(ChannelDetails.CHANNEL_TYPE.CAL, ChannelDetails.CHANNEL_TYPE.UNCAL), 2);
        channelAccelZ = channelDetails3;
        LinkedHashMap linkedHashMap2 = new LinkedHashMap();
        linkedHashMap2.put(SensorKionixAccel.ObjectClusterSensorName.ACCEL_LN_X, channelDetails);
        linkedHashMap2.put(SensorKionixAccel.ObjectClusterSensorName.ACCEL_LN_Y, channelDetails2);
        linkedHashMap2.put(SensorKionixAccel.ObjectClusterSensorName.ACCEL_LN_Z, channelDetails3);
        mChannelMapRef = Collections.unmodifiableMap(linkedHashMap2);
        sensorGroupLnAccelKXTC92050 = new SensorGroupingDetails("Low-Noise Accelerometer", Arrays.asList(2), Configuration.Shimmer3.CompatibilityInfoForMaps.listOfCompatibleVersionInfoKionixKXTC92050);
    }

    public CalibDetailsKinematic mCurrentCalibDetailsAccelLn;
    private CalibDetailsKinematic calibDetailsAccelLn2g;

    public SensorKionixKXTC92050() {
        super(AbstractSensor.SENSORS.KIONIXKXTC92050);
        CalibDetailsKinematic calibDetailsKinematic = new CalibDetailsKinematic(0, SensorKionixAccel.LN_ACCEL_RANGE_STRING, AlignmentMatrixLowNoiseAccelShimmer3, SensitivityMatrixLowNoiseAccel2gShimmer3, OffsetVectorLowNoiseAccelShimmer3);
        this.calibDetailsAccelLn2g = calibDetailsKinematic;
        this.mCurrentCalibDetailsAccelLn = calibDetailsKinematic;
        initialise();
    }

    public SensorKionixKXTC92050(ShimmerVerObject shimmerVerObject) {
        super(AbstractSensor.SENSORS.KIONIXKXTC92050, shimmerVerObject);
        CalibDetailsKinematic calibDetailsKinematic = new CalibDetailsKinematic(0, SensorKionixAccel.LN_ACCEL_RANGE_STRING, AlignmentMatrixLowNoiseAccelShimmer3, SensitivityMatrixLowNoiseAccel2gShimmer3, OffsetVectorLowNoiseAccelShimmer3);
        this.calibDetailsAccelLn2g = calibDetailsKinematic;
        this.mCurrentCalibDetailsAccelLn = calibDetailsKinematic;
        initialise();
    }

    public SensorKionixKXTC92050(ShimmerDevice shimmerDevice) {
        super(AbstractSensor.SENSORS.KIONIXKXTC92050, shimmerDevice);
        CalibDetailsKinematic calibDetailsKinematic = new CalibDetailsKinematic(0, SensorKionixAccel.LN_ACCEL_RANGE_STRING, AlignmentMatrixLowNoiseAccelShimmer3, SensitivityMatrixLowNoiseAccel2gShimmer3, OffsetVectorLowNoiseAccelShimmer3);
        this.calibDetailsAccelLn2g = calibDetailsKinematic;
        this.mCurrentCalibDetailsAccelLn = calibDetailsKinematic;
        initialise();
    }

    public static String parseFromDBColumnToGUIChannel(String str) {
        return AbstractSensor.parseFromDBColumnToGUIChannel(mChannelMapRef, str);
    }

    public static String parseFromGUIChannelsToDBColumn(String str) {
        return AbstractSensor.parseFromGUIChannelsToDBColumn(mChannelMapRef, str);
    }

    @Override // com.shimmerresearch.sensors.AbstractSensor
    public void generateSensorMap() {
        super.createLocalSensorMapWithCustomParser(mSensorMapRef, mChannelMapRef);
    }

    @Override // com.shimmerresearch.sensors.AbstractSensor
    public LinkedHashMap<String, Object> generateConfigMap() {
        LinkedHashMap<String, Object> linkedHashMap = new LinkedHashMap<>();
        SensorKionixAccel.addCalibDetailsToDbMap(linkedHashMap, getCurrentCalibDetailsAccelLn(), DatabaseConfigHandle.LIST_OF_CALIB_HANDLES_LN_ACC, DatabaseConfigHandle.LN_ACC_CALIB_TIME);
        return linkedHashMap;
    }

    @Override // com.shimmerresearch.sensors.AbstractSensor
    public void parseConfigMap(LinkedHashMap<String, Object> linkedHashMap) {
        parseCalibDetailsKinematicFromDb(linkedHashMap, 2, 0, DatabaseConfigHandle.LIST_OF_CALIB_HANDLES_LN_ACC, DatabaseConfigHandle.LN_ACC_CALIB_TIME);
    }

    @Override // com.shimmerresearch.sensors.AbstractSensor
    public void generateSensorGroupMapping() {
        this.mSensorGroupingMap = new LinkedHashMap<>();
        this.mSensorGroupingMap.put(Integer.valueOf(Configuration.Shimmer3.LABEL_SENSOR_TILE.LOW_NOISE_ACCEL.ordinal()), sensorGroupLnAccelKXTC92050);
        super.updateSensorGroupingMap();
    }

    @Override // com.shimmerresearch.sensors.AbstractSensor
    public void initialise() {
        super.initialise();
        updateCurrentAccelLnCalibInUse();
    }

    @Override // com.shimmerresearch.sensors.AbstractSensor
    public void generateCalibMap() {
        super.generateCalibMap();
        TreeMap<Integer, CalibDetails> treeMap = new TreeMap<>();
        treeMap.put(Integer.valueOf(this.calibDetailsAccelLn2g.mRangeValue), this.calibDetailsAccelLn2g);
        setCalibrationMapPerSensor(2, treeMap);
        updateCurrentAccelLnCalibInUse();
    }

    public static class DatabaseChannelHandles {
        public static final String LN_ACC_X = "KXTC9_2050_X";
        public static final String LN_ACC_Y = "KXTC9_2050_Y";
        public static final String LN_ACC_Z = "KXTC9_2050_Z";
    }

    public static final class DatabaseConfigHandle {
        public static final String LN_ACC_CALIB_TIME = "KXTC9_2050_Acc_Calib_Time";
        public static final String LN_ACC_OFFSET_X = "KXTC9_2050_Acc_Offset_X";
        public static final String LN_ACC_OFFSET_Y = "KXTC9_2050_Acc_Offset_Y";
        public static final String LN_ACC_OFFSET_Z = "KXTC9_2050_Acc_Offset_Z";
        public static final String LN_ACC_GAIN_X = "KXTC9_2050_Acc_Gain_X";
        public static final String LN_ACC_GAIN_Y = "KXTC9_2050_Acc_Gain_Y";
        public static final String LN_ACC_GAIN_Z = "KXTC9_2050_Acc_Gain_Z";
        public static final String LN_ACC_ALIGN_XX = "KXTC9_2050_Acc_Align_XX";
        public static final String LN_ACC_ALIGN_XY = "KXTC9_2050_Acc_Align_XY";
        public static final String LN_ACC_ALIGN_XZ = "KXTC9_2050_Acc_Align_XZ";
        public static final String LN_ACC_ALIGN_YX = "KXTC9_2050_Acc_Align_YX";
        public static final String LN_ACC_ALIGN_YY = "KXTC9_2050_Acc_Align_YY";
        public static final String LN_ACC_ALIGN_YZ = "KXTC9_2050_Acc_Align_YZ";
        public static final String LN_ACC_ALIGN_ZX = "KXTC9_2050_Acc_Align_ZX";
        public static final String LN_ACC_ALIGN_ZY = "KXTC9_2050_Acc_Align_ZY";
        public static final String LN_ACC_ALIGN_ZZ = "KXTC9_2050_Acc_Align_ZZ";
        public static final List<String> LIST_OF_CALIB_HANDLES_LN_ACC = Arrays.asList(LN_ACC_OFFSET_X, LN_ACC_OFFSET_Y, LN_ACC_OFFSET_Z, LN_ACC_GAIN_X, LN_ACC_GAIN_Y, LN_ACC_GAIN_Z, LN_ACC_ALIGN_XX, LN_ACC_ALIGN_XY, LN_ACC_ALIGN_XZ, LN_ACC_ALIGN_YX, LN_ACC_ALIGN_YY, LN_ACC_ALIGN_YZ, LN_ACC_ALIGN_ZX, LN_ACC_ALIGN_ZY, LN_ACC_ALIGN_ZZ);
    }
}
