package com.shimmerresearch.sensors.kionix;

import com.shimmerresearch.driver.Configuration;
import com.shimmerresearch.driver.ShimmerDevice;
import com.shimmerresearch.driver.calibration.CalibDetails;
import com.shimmerresearch.driver.calibration.CalibDetailsKinematic;
import com.shimmerresearch.driver.calibration.OldCalDetails;
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
public class SensorKionixKXRB52042 extends SensorKionixAccel {
    public static final double[][] AlignmentMatrixLowNoiseAccelShimmer3 = {new double[]{0.0d, -1.0d, 0.0d}, new double[]{-1.0d, 0.0d, 0.0d}, new double[]{0.0d, 0.0d, -1.0d}};
    public static final double[][] OffsetVectorLowNoiseAccelShimmer3 = {new double[]{2047.0d}, new double[]{2047.0d}, new double[]{2047.0d}};
    public static final double[][] SensitivityMatrixLowNoiseAccel2gShimmer3 = {new double[]{83.0d, 0.0d, 0.0d}, new double[]{0.0d, 83.0d, 0.0d}, new double[]{0.0d, 0.0d, 83.0d}};
    public static final ChannelDetails channelAccelX;
    public static final ChannelDetails channelAccelY;
    public static final ChannelDetails channelAccelZ;
    public static final Map<String, ChannelDetails> mChannelMapRef;
    public static final Map<String, OldCalDetails> mOldCalRangeMap;
    public static final Map<Integer, SensorDetailsRef> mSensorMapRef;
    public static final SensorGroupingDetails sensorGroupLnAccelKXRB52042;
    public static final SensorDetailsRef sensorKionixKXRB52042;
    private static final long serialVersionUID = -4053257599631109173L;

    static {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put(SensorKionixAccel.OldCalRangeLN2g, new OldCalDetails(SensorKionixAccel.OldCalRangeLN2g, 2, 0));
        mOldCalRangeMap = Collections.unmodifiableMap(linkedHashMap);
        SensorDetailsRef sensorDetailsRef = new SensorDetailsRef(128L, 128L, "Low-Noise Accelerometer", Configuration.Shimmer3.CompatibilityInfoForMaps.listOfCompatibleVersionInfoKionixKXRB52042, null, Arrays.asList(SensorKionixAccel.ObjectClusterSensorName.ACCEL_LN_X, SensorKionixAccel.ObjectClusterSensorName.ACCEL_LN_Y, SensorKionixAccel.ObjectClusterSensorName.ACCEL_LN_Z));
        sensorKionixKXRB52042 = sensorDetailsRef;
        LinkedHashMap linkedHashMap2 = new LinkedHashMap();
        linkedHashMap2.put(2, sensorDetailsRef);
        mSensorMapRef = Collections.unmodifiableMap(linkedHashMap2);
        ChannelDetails channelDetails = new ChannelDetails(SensorKionixAccel.ObjectClusterSensorName.ACCEL_LN_X, SensorKionixAccel.ObjectClusterSensorName.ACCEL_LN_X, DatabaseChannelHandles.LN_ACC_X, ChannelDetails.CHANNEL_DATA_TYPE.UINT12, 2, ChannelDetails.CHANNEL_DATA_ENDIAN.LSB, "m/(s^2)", (List<ChannelDetails.CHANNEL_TYPE>) Arrays.asList(ChannelDetails.CHANNEL_TYPE.CAL, ChannelDetails.CHANNEL_TYPE.UNCAL), 0);
        channelAccelX = channelDetails;
        ChannelDetails channelDetails2 = new ChannelDetails(SensorKionixAccel.ObjectClusterSensorName.ACCEL_LN_Y, SensorKionixAccel.ObjectClusterSensorName.ACCEL_LN_Y, DatabaseChannelHandles.LN_ACC_Y, ChannelDetails.CHANNEL_DATA_TYPE.UINT12, 2, ChannelDetails.CHANNEL_DATA_ENDIAN.LSB, "m/(s^2)", (List<ChannelDetails.CHANNEL_TYPE>) Arrays.asList(ChannelDetails.CHANNEL_TYPE.CAL, ChannelDetails.CHANNEL_TYPE.UNCAL), 1);
        channelAccelY = channelDetails2;
        ChannelDetails channelDetails3 = new ChannelDetails(SensorKionixAccel.ObjectClusterSensorName.ACCEL_LN_Z, SensorKionixAccel.ObjectClusterSensorName.ACCEL_LN_Z, DatabaseChannelHandles.LN_ACC_Z, ChannelDetails.CHANNEL_DATA_TYPE.UINT12, 2, ChannelDetails.CHANNEL_DATA_ENDIAN.LSB, "m/(s^2)", (List<ChannelDetails.CHANNEL_TYPE>) Arrays.asList(ChannelDetails.CHANNEL_TYPE.CAL, ChannelDetails.CHANNEL_TYPE.UNCAL), 2);
        channelAccelZ = channelDetails3;
        LinkedHashMap linkedHashMap3 = new LinkedHashMap();
        linkedHashMap3.put(SensorKionixAccel.ObjectClusterSensorName.ACCEL_LN_X, channelDetails);
        linkedHashMap3.put(SensorKionixAccel.ObjectClusterSensorName.ACCEL_LN_Y, channelDetails2);
        linkedHashMap3.put(SensorKionixAccel.ObjectClusterSensorName.ACCEL_LN_Z, channelDetails3);
        mChannelMapRef = Collections.unmodifiableMap(linkedHashMap3);
        sensorGroupLnAccelKXRB52042 = new SensorGroupingDetails("Low-Noise Accelerometer", Arrays.asList(2), Configuration.Shimmer3.CompatibilityInfoForMaps.listOfCompatibleVersionInfoKionixKXRB52042);
    }

    private CalibDetailsKinematic calibDetailsAccelLn2g;

    public SensorKionixKXRB52042() {
        super(AbstractSensor.SENSORS.KIONIXKXRB52042);
        this.calibDetailsAccelLn2g = new CalibDetailsKinematic(0, SensorKionixAccel.LN_ACCEL_RANGE_STRING, AlignmentMatrixLowNoiseAccelShimmer3, SensitivityMatrixLowNoiseAccel2gShimmer3, OffsetVectorLowNoiseAccelShimmer3);
        initialise();
    }

    public SensorKionixKXRB52042(ShimmerVerObject shimmerVerObject) {
        super(AbstractSensor.SENSORS.KIONIXKXRB52042, shimmerVerObject);
        this.calibDetailsAccelLn2g = new CalibDetailsKinematic(0, SensorKionixAccel.LN_ACCEL_RANGE_STRING, AlignmentMatrixLowNoiseAccelShimmer3, SensitivityMatrixLowNoiseAccel2gShimmer3, OffsetVectorLowNoiseAccelShimmer3);
        initialise();
    }

    public SensorKionixKXRB52042(ShimmerDevice shimmerDevice) {
        super(AbstractSensor.SENSORS.KIONIXKXRB52042, shimmerDevice);
        this.calibDetailsAccelLn2g = new CalibDetailsKinematic(0, SensorKionixAccel.LN_ACCEL_RANGE_STRING, AlignmentMatrixLowNoiseAccelShimmer3, SensitivityMatrixLowNoiseAccel2gShimmer3, OffsetVectorLowNoiseAccelShimmer3);
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
        if (this.mShimmerVerObject.isShimmerGen3() || this.mShimmerVerObject.isShimmerGen4()) {
            this.mSensorGroupingMap.put(Integer.valueOf(Configuration.Shimmer3.LABEL_SENSOR_TILE.LOW_NOISE_ACCEL.ordinal()), sensorGroupLnAccelKXRB52042);
        }
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
        public static final String LN_ACC_X = "KXRB8_2042_X";
        public static final String LN_ACC_Y = "KXRB8_2042_Y";
        public static final String LN_ACC_Z = "KXRB8_2042_Z";
    }

    public static final class DatabaseConfigHandle {
        public static final String LN_ACC_CALIB_TIME = "KXRB8_2042_Acc_Calib_Time";
        public static final String LN_ACC_OFFSET_X = "KXRB8_2042_Acc_Offset_X";
        public static final String LN_ACC_OFFSET_Y = "KXRB8_2042_Acc_Offset_Y";
        public static final String LN_ACC_OFFSET_Z = "KXRB8_2042_Acc_Offset_Z";
        public static final String LN_ACC_GAIN_X = "KXRB8_2042_Acc_Gain_X";
        public static final String LN_ACC_GAIN_Y = "KXRB8_2042_Acc_Gain_Y";
        public static final String LN_ACC_GAIN_Z = "KXRB8_2042_Acc_Gain_Z";
        public static final String LN_ACC_ALIGN_XX = "KXRB8_2042_Acc_Align_XX";
        public static final String LN_ACC_ALIGN_XY = "KXRB8_2042_Acc_Align_XY";
        public static final String LN_ACC_ALIGN_XZ = "KXRB8_2042_Acc_Align_XZ";
        public static final String LN_ACC_ALIGN_YX = "KXRB8_2042_Acc_Align_YX";
        public static final String LN_ACC_ALIGN_YY = "KXRB8_2042_Acc_Align_YY";
        public static final String LN_ACC_ALIGN_YZ = "KXRB8_2042_Acc_Align_YZ";
        public static final String LN_ACC_ALIGN_ZX = "KXRB8_2042_Acc_Align_ZX";
        public static final String LN_ACC_ALIGN_ZY = "KXRB8_2042_Acc_Align_ZY";
        public static final String LN_ACC_ALIGN_ZZ = "KXRB8_2042_Acc_Align_ZZ";
        public static final List<String> LIST_OF_CALIB_HANDLES_LN_ACC = Arrays.asList(LN_ACC_OFFSET_X, LN_ACC_OFFSET_Y, LN_ACC_OFFSET_Z, LN_ACC_GAIN_X, LN_ACC_GAIN_Y, LN_ACC_GAIN_Z, LN_ACC_ALIGN_XX, LN_ACC_ALIGN_XY, LN_ACC_ALIGN_XZ, LN_ACC_ALIGN_YX, LN_ACC_ALIGN_YY, LN_ACC_ALIGN_YZ, LN_ACC_ALIGN_ZX, LN_ACC_ALIGN_ZY, LN_ACC_ALIGN_ZZ);
    }
}
