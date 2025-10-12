package com.shimmerresearch.sensors.shimmer2;

import java.util.LinkedHashMap;
import java.util.TreeMap;

import org.apache.commons.lang3.ArrayUtils;

import com.shimmerresearch.driver.Configuration.COMMUNICATION_TYPE;
import com.shimmerresearch.driver.calibration.CalibDetails;
import com.shimmerresearch.driver.calibration.CalibDetailsKinematic;
import com.shimmerresearch.driver.Configuration;
import com.shimmerresearch.driver.ObjectCluster;
import com.shimmerresearch.driver.ShimmerDevice;
import com.shimmerresearch.driverUtilities.ConfigOptionDetailsSensor;
import com.shimmerresearch.driverUtilities.SensorDetails;
import com.shimmerresearch.driverUtilities.ShimmerVerDetails.HW_ID;
import com.shimmerresearch.sensors.AbstractSensor;
import com.shimmerresearch.sensors.ActionSetting;
import com.shimmerresearch.sensors.AbstractSensor.GuiLabelConfigCommon;
import com.shimmerresearch.sensors.mpu9x50.SensorMPU9X50;

public class SensorMMA736x extends AbstractSensor {

    public static final String[] ListofMMA7361AccelRange = {"+/- 1.5g", "+/- 2g", "+/- 4g", "+/- 6g"};

    public static final Integer[] ListofMMA7361AccelRangeConfigValues = {0, 1, 2, 3};
    public static final String[] ListofMMA7360AccelRange = {"+/- 1.5g", "+/- 6g"};
    public static final Integer[] ListofMMA7360AccelRangeConfigValues = {0, 1};
    public static final ConfigOptionDetailsSensor configOptionAccelLpm = new ConfigOptionDetailsSensor(
            SensorMMA736x.GuiLabelConfig.ACCEL_LOW_POWER_MODE,
            SensorMMA736x.DatabaseConfigHandle.ACCEL_LOW_POWER_MODE,
            ConfigOptionDetailsSensor.GUI_COMPONENT_TYPE.CHECKBOX);
    protected static final double[][] AlignmentMatrixAccelShimmer2 = {{-1, 0, 0}, {0, -1, 0}, {0, 0, 1}};
    protected static final double[][] OffsetVectorAccelShimmer2 = {{2048}, {2048}, {2048}};
    protected static final double[][] SensitivityMatrixAccel1p5gShimmer2 = {{101, 0, 0}, {0, 101, 0}, {0, 0, 101}};
    protected static final double[][] SensitivityMatrixAccel2gShimmer2 = {{76, 0, 0}, {0, 76, 0}, {0, 0, 76}};
    protected static final double[][] SensitivityMatrixAccel4gShimmer2 = {{38, 0, 0}, {0, 38, 0}, {0, 0, 38}};
    protected static final double[][] SensitivityMatrixAccel6gShimmer2 = {{25, 0, 0}, {0, 25, 0}, {0, 0, 25}};
    private static final long serialVersionUID = 30169412053282585L;
    public boolean mIsUsingDefaultLNAccelParam = true;
    protected TreeMap<Integer, CalibDetails> mCalibMapAccelShimmer2 = new TreeMap<Integer, CalibDetails>();
    protected TreeMap<Integer, CalibDetails> mCalibMapAccelShimmer2r = new TreeMap<Integer, CalibDetails>();
    private int mAccelRange = 0;
    private CalibDetailsKinematic calibDetailsShimmer2r1p5g = new CalibDetailsKinematic(
            0, Configuration.Shimmer2.ListofAccelRange[0],
            AlignmentMatrixAccelShimmer2, SensitivityMatrixAccel1p5gShimmer2, OffsetVectorAccelShimmer2);
    private CalibDetailsKinematic calibDetailsShimmer2r2g = new CalibDetailsKinematic(
            1, "+/- 2g",
            AlignmentMatrixAccelShimmer2, SensitivityMatrixAccel2gShimmer2, OffsetVectorAccelShimmer2);
    public CalibDetailsKinematic mCurrentCalibDetailsAccel = calibDetailsShimmer2r2g;
    private CalibDetailsKinematic calibDetailsShimmer2r4g = new CalibDetailsKinematic(
            2, "+/- 4g",
            AlignmentMatrixAccelShimmer2, SensitivityMatrixAccel4gShimmer2, OffsetVectorAccelShimmer2);

    private CalibDetailsKinematic calibDetailsShimmer2r6g = new CalibDetailsKinematic(
            3, Configuration.Shimmer2.ListofAccelRange[1],
            AlignmentMatrixAccelShimmer2, SensitivityMatrixAccel6gShimmer2, OffsetVectorAccelShimmer2);

    {
        mCalibMapAccelShimmer2.put(calibDetailsShimmer2r1p5g.mRangeValue, calibDetailsShimmer2r1p5g);
        mCalibMapAccelShimmer2.put(calibDetailsShimmer2r2g.mRangeValue, calibDetailsShimmer2r2g);
        mCalibMapAccelShimmer2.put(calibDetailsShimmer2r4g.mRangeValue, calibDetailsShimmer2r4g);
        mCalibMapAccelShimmer2.put(calibDetailsShimmer2r6g.mRangeValue, calibDetailsShimmer2r6g);
    }

    {
        mCalibMapAccelShimmer2r.put(calibDetailsShimmer2r1p5g.mRangeValue, calibDetailsShimmer2r1p5g);
        mCalibMapAccelShimmer2r.put(calibDetailsShimmer2r6g.mRangeValue, calibDetailsShimmer2r6g);
    }

    public SensorMMA736x(ShimmerDevice shimmerDevice) {
        super(SENSORS.MMA776X, shimmerDevice);
        initialise();
    }

    @Override
    public void generateSensorMap() {

    }

//




    @Override
    public void generateConfigOptionsMap() {
        addConfigOption(configOptionAccelLpm);
    }



    @Override
    public void generateSensorGroupMapping() {

    }

    @Override
    public ObjectCluster processDataCustom(SensorDetails sensorDetails, byte[] rawData, COMMUNICATION_TYPE commType,
                                           ObjectCluster objectCluster, boolean isTimeSyncEnabled, double pctimeStampMs) {
        return null;
    }

    @Override
    public void checkShimmerConfigBeforeConfiguring() {

    }

    @Override
    public void configBytesGenerate(ShimmerDevice shimmerDevice, byte[] configBytes, COMMUNICATION_TYPE commType) {

    }

    @Override
    public void configBytesParse(ShimmerDevice shimmerDevice, byte[] configBytes, COMMUNICATION_TYPE commType) {

    }

    @Override
    public Object setConfigValueUsingConfigLabel(Integer sensorId, String configLabel, Object valueToSet) {
        Object returnValue = null;
        switch (configLabel) {
            case (GuiLabelConfig.ACCEL_RANGE):
                setAccelRange((int) valueToSet);
                break;
            default:
                returnValue = super.setConfigValueUsingConfigLabelCommon(sensorId, configLabel, valueToSet);
                break;
        }
        return returnValue;
    }

    @Override
    public Object getConfigValueUsingConfigLabel(Integer sensorId, String configLabel) {
        Object returnValue = null;
        switch (configLabel) {
            case (GuiLabelConfigCommon.RANGE):
                if (sensorId == Configuration.Shimmer3.SENSOR_ID.SHIMMER_ANALOG_ACCEL) {
                    returnValue = 0;
                }
                break;
            default:
                returnValue = super.getConfigValueUsingConfigLabelCommon(sensorId, configLabel);
                break;
        }
        return returnValue;
    }

    @Override
    public void setSensorSamplingRate(double samplingRateHz) {

    }

    @Override
    public boolean setDefaultConfigForSensor(int sensorId, boolean isSensorEnabled) {
        return false;
    }

    @Override
    public boolean checkConfigOptionValues(String stringKey) {
        return false;
    }

    @Override
    public Object getSettings(String componentName, COMMUNICATION_TYPE commType) {
        return null;
    }

    @Override
    public ActionSetting setSettings(String componentName, Object valueToSet, COMMUNICATION_TYPE commType) {
        return null;
    }

    @Override
    public boolean processResponse(int responseCommand, Object parsedResponse, COMMUNICATION_TYPE commType) {
        return false;
    }

    @Override
    public LinkedHashMap<String, Object> generateConfigMap() {
        return null;
    }

    @Override
    public void parseConfigMap(LinkedHashMap<String, Object> mapOfConfigPerShimmer) {

    }

    @Override
    public void generateCalibMap() {
        super.generateCalibMap();

        TreeMap<Integer, CalibDetails> calibMapAccelWr = null;
        if (mShimmerDevice.getHardwareVersion() == HW_ID.SHIMMER_2) {
            calibMapAccelWr = mCalibMapAccelShimmer2;
        } else {
            calibMapAccelWr = mCalibMapAccelShimmer2r;
        }
        setCalibrationMapPerSensor(Configuration.Shimmer2.SENSOR_ID.ACCEL, calibMapAccelWr);

        updateCurrentAccelCalibInUse();
    }

    public int getAccelRange() {
        return mAccelRange;
    }



    public void setAccelRange(int valueToSet) {
        Integer[] arrayToCheck = ListofMMA7361AccelRangeConfigValues;
        if (getHardwareVersion() == HW_ID.SHIMMER_2) {
            arrayToCheck = ListofMMA7360AccelRangeConfigValues;
        }
        if (ArrayUtils.contains(arrayToCheck, valueToSet)) {
            mAccelRange = valueToSet;
            updateCurrentAccelCalibInUse();
        }
    }




    public void updateCurrentAccelCalibInUse() {
        mCurrentCalibDetailsAccel = getCurrentCalibDetailsIfKinematic(Configuration.Shimmer2.SENSOR_ID.ACCEL, getAccelRange());
    }

    public CalibDetailsKinematic getCurrentCalibDetailsAccelLn() {
        CalibDetails calibPerSensor = getCalibForSensor(Configuration.Shimmer2.SENSOR_ID.ACCEL, getAccelRange());
        if (calibPerSensor != null) {
            return (CalibDetailsKinematic) calibPerSensor;
        }
        return null;
    }

    public CalibDetailsKinematic getCurrentCalibDetailsIfKinematic(int sensorId, int range) {
        CalibDetails calibPerSensor = getCalibForSensor(sensorId, range);
        if (calibPerSensor != null) {
            return (CalibDetailsKinematic) calibPerSensor;
        }
        return null;
    }

    @Override
    public void setCalibrationMapPerSensor(int sensorId, TreeMap<Integer, CalibDetails> mapOfSensorCalibration) {
        super.setCalibrationMapPerSensor(sensorId, mapOfSensorCalibration);
        updateCurrentAccelCalibInUse();
    }

    public void updateIsUsingDefaultLNAccelParam() {
        mIsUsingDefaultLNAccelParam = getCurrentCalibDetailsAccelLn().isUsingDefaultParameters();
    }

    public static final class DatabaseConfigHandle {
        public static final String ACCEL_LOW_POWER_MODE = "MMA736x_Low_Power_Mode";
        public static final String ACCEL_RANGE = "MMA736x_Range";
    }

    public class GuiLabelConfig {
        public static final String ACCEL_LOW_POWER_MODE = "Accel Low Power Mode";
        public static final String ACCEL_RANGE = "Accel Range";
    }

}
