package com.shimmerresearch.sensors.lsm303;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.lang3.ArrayUtils;

import com.shimmerresearch.driver.Configuration.CHANNEL_UNITS;
import com.shimmerresearch.driver.Configuration.COMMUNICATION_TYPE;
import com.shimmerresearch.driver.Configuration.Shimmer3.CompatibilityInfoForMaps;
import com.shimmerresearch.driver.calibration.CalibDetails;
import com.shimmerresearch.driver.calibration.CalibDetailsKinematic;
import com.shimmerresearch.driver.Configuration;
import com.shimmerresearch.driver.ShimmerDevice;
import com.shimmerresearch.driverUtilities.ChannelDetails;
import com.shimmerresearch.driverUtilities.ConfigOptionDetailsSensor;
import com.shimmerresearch.driverUtilities.ConfigOptionObject;
import com.shimmerresearch.driverUtilities.SensorDetailsRef;
import com.shimmerresearch.driverUtilities.SensorGroupingDetails;
import com.shimmerresearch.driverUtilities.ChannelDetails.CHANNEL_DATA_ENDIAN;
import com.shimmerresearch.driverUtilities.ChannelDetails.CHANNEL_DATA_TYPE;
import com.shimmerresearch.driverUtilities.ChannelDetails.CHANNEL_TYPE;
import com.shimmerresearch.sensors.AbstractSensor;
import com.shimmerresearch.sensors.ActionSetting;

public class SensorLSM303AH extends SensorLSM303 {

    public static final double[][] DefaultAlignmentLSM303AH = {{0, -1, 0}, {1, 0, 0}, {0, 0, -1}};


    public static final double[][] DefaultAlignmentMatrixWideRangeAccelShimmer3 = DefaultAlignmentLSM303AH;
    public static final double[][] DefaultOffsetVectorWideRangeAccelShimmer3 = {{0}, {0}, {0}};
    public static final double[][] DefaultSensitivityMatrixWideRangeAccel2gShimmer3 = {{1671, 0, 0}, {0, 1671, 0}, {0, 0, 1671}};
    public static final double[][] DefaultSensitivityMatrixWideRangeAccel4gShimmer3 = {{836, 0, 0}, {0, 836, 0}, {0, 0, 836}};
    public static final double[][] DefaultSensitivityMatrixWideRangeAccel8gShimmer3 = {{418, 0, 0}, {0, 418, 0}, {0, 0, 418}};
    public static final double[][] DefaultSensitivityMatrixWideRangeAccel16gShimmer3 = {{209, 0, 0}, {0, 209, 0}, {0, 0, 209}};
    public static final double[][] DefaultAlignmentMatrixMagShimmer3 = DefaultAlignmentLSM303AH;
    public static final double[][] DefaultOffsetVectorMagShimmer3 = {{0}, {0}, {0}};
    public static final double[][] DefaultSensitivityMatrixMag50GaShimmer3 = {{667, 0, 0}, {0, 667, 0}, {0, 0, 667}};
    public static final Integer[] ListofLSM303AccelRangeConfigValues = {0, 2, 3, 1};
    public static final String[] ListofLSM303AHAccelRateHr = {"Power-down", "12.5Hz", "25.0Hz", "50.0Hz", "100.0Hz", "200.0Hz", "400.0Hz", "800.0Hz", "1600.0Hz", "3200.0Hz", "6400.0Hz"};

    public static final Integer[] ListofLSM303AHAccelRateHrConfigValues = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
    public static final String[] ListofLSM303AHAccelRateLpm = {"Power-down", "1.0Hz", "12.5Hz", "25.0Hz", "50.0Hz", "100.0Hz", "200.0Hz", "400.0Hz", "800.0Hz"};
    public static final Integer[] ListofLSM303AHAccelRateLpmConfigValues = {0, 8, 9, 10, 11, 12, 13, 14, 15};
    public static final ConfigOptionDetailsSensor configOptionAccelRange = new ConfigOptionDetailsSensor(
            SensorLSM303.GuiLabelConfig.LSM303_ACCEL_RANGE,
            SensorLSM303AH.DatabaseConfigHandle.WR_ACC_RANGE,
            ListofLSM303AccelRange,
            ListofLSM303AccelRangeConfigValues,
            ConfigOptionDetailsSensor.GUI_COMPONENT_TYPE.COMBOBOX,
            CompatibilityInfoForMaps.listOfCompatibleVersionInfoLSM303AH);
    public static final ConfigOptionDetailsSensor configOptionAccelRate = new ConfigOptionDetailsSensor(
            SensorLSM303.GuiLabelConfig.LSM303_ACCEL_RATE,
            SensorLSM303AH.DatabaseConfigHandle.WR_ACC_RATE,
            ListofLSM303AHAccelRateHr,
            ListofLSM303AHAccelRateHrConfigValues,
            ConfigOptionDetailsSensor.GUI_COMPONENT_TYPE.COMBOBOX,
            CompatibilityInfoForMaps.listOfCompatibleVersionInfoLSM303AH,
            Arrays.asList(
                    new ConfigOptionObject(ConfigOptionDetailsSensor.VALUE_INDEXES.LSM303_ACCEL_RATE.IS_LPM,
                            ListofLSM303AHAccelRateLpm,
                            ListofLSM303AHAccelRateLpmConfigValues)));

    public static final String[] ListofLSM303AHMagRate = {"10.0Hz", "20.0Hz", "50.0Hz", "100.0Hz"};
    public static final Integer[] ListofLSM303AHMagRateConfigValues = {0, 1, 2, 3};


    public static final String[] ListofLSM303AHMagRange = {"+/- 49.152Ga"};
    public static final Integer[] ListofLSM303AHMagRangeConfigValues = {0};
    public static final ConfigOptionDetailsSensor configOptionMagRange = new ConfigOptionDetailsSensor(
            SensorLSM303.GuiLabelConfig.LSM303_MAG_RANGE,
            SensorLSM303AH.DatabaseConfigHandle.MAG_RANGE,
            ListofLSM303AHMagRange,
            ListofLSM303AHMagRangeConfigValues,
            ConfigOptionDetailsSensor.GUI_COMPONENT_TYPE.COMBOBOX,
            CompatibilityInfoForMaps.listOfCompatibleVersionInfoLSM303AH);
    public static final ConfigOptionDetailsSensor configOptionMagRate = new ConfigOptionDetailsSensor(
            SensorLSM303.GuiLabelConfig.LSM303_MAG_RATE,
            SensorLSM303AH.DatabaseConfigHandle.MAG_RATE,
            ListofLSM303AHMagRate,
            ListofLSM303AHMagRateConfigValues,
            ConfigOptionDetailsSensor.GUI_COMPONENT_TYPE.COMBOBOX,
            CompatibilityInfoForMaps.listOfCompatibleVersionInfoLSM303AH);
    public static final ConfigOptionDetailsSensor configOptionAccelLpm = new ConfigOptionDetailsSensor(
            SensorLSM303.GuiLabelConfig.LSM303_ACCEL_LPM,
            SensorLSM303AH.DatabaseConfigHandle.WR_ACC_LPM,
            ConfigOptionDetailsSensor.GUI_COMPONENT_TYPE.CHECKBOX);
    public static final SensorDetailsRef sensorLSM303AHAccel = new SensorDetailsRef(
            0x10 << 8,
            0x10 << 8,
            GuiLabelSensors.ACCEL_WR,
            CompatibilityInfoForMaps.listOfCompatibleVersionInfoLSM303AH,
            Arrays.asList(GuiLabelConfig.LSM303_ACCEL_RANGE,
                    GuiLabelConfig.LSM303_ACCEL_RATE),
            Arrays.asList(ObjectClusterSensorName.ACCEL_WR_X,
                    ObjectClusterSensorName.ACCEL_WR_Y,
                    ObjectClusterSensorName.ACCEL_WR_Z));
    public static final SensorDetailsRef sensorLSM303AHMag = new SensorDetailsRef(
            0x20,
            0x20,
            GuiLabelSensors.MAG,
            CompatibilityInfoForMaps.listOfCompatibleVersionInfoLSM303AH,
            Arrays.asList(GuiLabelConfig.LSM303_MAG_RANGE,
                    GuiLabelConfig.LSM303_MAG_RATE),
            Arrays.asList(ObjectClusterSensorName.MAG_X,
                    ObjectClusterSensorName.MAG_Y,
                    ObjectClusterSensorName.MAG_Z));
    public static final Map<Integer, SensorDetailsRef> mSensorMapRef;
    public static final ChannelDetails channelLSM303AHAccelX = new ChannelDetails(
            ObjectClusterSensorName.ACCEL_WR_X,
            ObjectClusterSensorName.ACCEL_WR_X,
            DatabaseChannelHandles.WR_ACC_X,
            CHANNEL_DATA_TYPE.INT16, 2, CHANNEL_DATA_ENDIAN.LSB,
            CHANNEL_UNITS.METER_PER_SECOND_SQUARE,
            Arrays.asList(CHANNEL_TYPE.CAL, CHANNEL_TYPE.UNCAL),
            0x04);
    public static final ChannelDetails channelLSM303AHAccelY = new ChannelDetails(
            ObjectClusterSensorName.ACCEL_WR_Y,
            ObjectClusterSensorName.ACCEL_WR_Y,
            DatabaseChannelHandles.WR_ACC_Y,
            CHANNEL_DATA_TYPE.INT16, 2, CHANNEL_DATA_ENDIAN.LSB,
            CHANNEL_UNITS.METER_PER_SECOND_SQUARE,
            Arrays.asList(CHANNEL_TYPE.CAL, CHANNEL_TYPE.UNCAL),
            0x05);
    public static final ChannelDetails channelLSM30AH3AccelZ = new ChannelDetails(
            ObjectClusterSensorName.ACCEL_WR_Z,
            ObjectClusterSensorName.ACCEL_WR_Z,
            DatabaseChannelHandles.WR_ACC_Z,
            CHANNEL_DATA_TYPE.INT16, 2, CHANNEL_DATA_ENDIAN.LSB,
            CHANNEL_UNITS.METER_PER_SECOND_SQUARE,
            Arrays.asList(CHANNEL_TYPE.CAL, CHANNEL_TYPE.UNCAL),
            0x06);
    public static final ChannelDetails channelLSM303AHMagX = new ChannelDetails(
            ObjectClusterSensorName.MAG_X,
            ObjectClusterSensorName.MAG_X,
            DatabaseChannelHandles.MAG_X,
            CHANNEL_DATA_TYPE.INT16, 2, CHANNEL_DATA_ENDIAN.MSB,
            CHANNEL_UNITS.LOCAL_FLUX,
            Arrays.asList(CHANNEL_TYPE.CAL, CHANNEL_TYPE.UNCAL),
            0x07);
    public static final ChannelDetails channelLSM303AHMagY = new ChannelDetails(
            ObjectClusterSensorName.MAG_Y,
            ObjectClusterSensorName.MAG_Y,
            DatabaseChannelHandles.MAG_Y,
            CHANNEL_DATA_TYPE.INT16, 2, CHANNEL_DATA_ENDIAN.MSB,
            CHANNEL_UNITS.LOCAL_FLUX,
            Arrays.asList(CHANNEL_TYPE.CAL, CHANNEL_TYPE.UNCAL),
            0x08);
    public static final ChannelDetails channelLSM303AHMagZ = new ChannelDetails(
            ObjectClusterSensorName.MAG_Z,
            ObjectClusterSensorName.MAG_Z,
            DatabaseChannelHandles.MAG_Z,
            CHANNEL_DATA_TYPE.INT16, 2, CHANNEL_DATA_ENDIAN.MSB,
            CHANNEL_UNITS.LOCAL_FLUX,
            Arrays.asList(CHANNEL_TYPE.CAL, CHANNEL_TYPE.UNCAL),
            0x09);

    public static final Map<String, ChannelDetails> mChannelMapRef;
    public static final SensorGroupingDetails sensorGroupLsmAccel = new SensorGroupingDetails(
            LABEL_SENSOR_TILE.WIDE_RANGE_ACCEL,
            Arrays.asList(Configuration.Shimmer3.SENSOR_ID.SHIMMER_LSM303_ACCEL),
            CompatibilityInfoForMaps.listOfCompatibleVersionInfoLSM303AH);
    public static final SensorGroupingDetails sensorGroupLsmMag = new SensorGroupingDetails(
            LABEL_SENSOR_TILE.MAG,
            Arrays.asList(Configuration.Shimmer3.SENSOR_ID.SHIMMER_LSM303_MAG),
            CompatibilityInfoForMaps.listOfCompatibleVersionInfoLSM303AH);
    private static final long serialVersionUID = 5566898733753766631L;

    static {
        Map<Integer, SensorDetailsRef> aMap = new LinkedHashMap<Integer, SensorDetailsRef>();
        aMap.put(Configuration.Shimmer3.SENSOR_ID.SHIMMER_LSM303_ACCEL, SensorLSM303AH.sensorLSM303AHAccel);
        aMap.put(Configuration.Shimmer3.SENSOR_ID.SHIMMER_LSM303_MAG, SensorLSM303AH.sensorLSM303AHMag);
        mSensorMapRef = Collections.unmodifiableMap(aMap);
    }

    static {
        Map<String, ChannelDetails> aMap = new LinkedHashMap<String, ChannelDetails>();
        aMap.put(SensorLSM303AH.ObjectClusterSensorName.ACCEL_WR_X, SensorLSM303AH.channelLSM303AHAccelX);
        aMap.put(SensorLSM303AH.ObjectClusterSensorName.ACCEL_WR_Y, SensorLSM303AH.channelLSM303AHAccelY);
        aMap.put(SensorLSM303AH.ObjectClusterSensorName.ACCEL_WR_Z, SensorLSM303AH.channelLSM30AH3AccelZ);
        aMap.put(SensorLSM303AH.ObjectClusterSensorName.MAG_X, SensorLSM303AH.channelLSM303AHMagX);
        aMap.put(SensorLSM303AH.ObjectClusterSensorName.MAG_Z, SensorLSM303AH.channelLSM303AHMagZ);
        aMap.put(SensorLSM303AH.ObjectClusterSensorName.MAG_Y, SensorLSM303AH.channelLSM303AHMagY);
        mChannelMapRef = Collections.unmodifiableMap(aMap);
    }

    private CalibDetailsKinematic calibDetailsAccelWr2g = new CalibDetailsKinematic(
            ListofLSM303AccelRangeConfigValues[0],
            ListofLSM303AccelRange[0],
            DefaultAlignmentMatrixWideRangeAccelShimmer3,
            DefaultSensitivityMatrixWideRangeAccel2gShimmer3,
            DefaultOffsetVectorWideRangeAccelShimmer3);
    private CalibDetailsKinematic calibDetailsAccelWr4g = new CalibDetailsKinematic(
            ListofLSM303AccelRangeConfigValues[1],
            ListofLSM303AccelRange[1],
            DefaultAlignmentMatrixWideRangeAccelShimmer3,
            DefaultSensitivityMatrixWideRangeAccel4gShimmer3,
            DefaultOffsetVectorWideRangeAccelShimmer3);
    private CalibDetailsKinematic calibDetailsAccelWr8g = new CalibDetailsKinematic(
            ListofLSM303AccelRangeConfigValues[2],
            ListofLSM303AccelRange[2],
            DefaultAlignmentMatrixWideRangeAccelShimmer3,
            DefaultSensitivityMatrixWideRangeAccel8gShimmer3,
            DefaultOffsetVectorWideRangeAccelShimmer3);
    private CalibDetailsKinematic calibDetailsAccelWr16g = new CalibDetailsKinematic(
            ListofLSM303AccelRangeConfigValues[3],
            ListofLSM303AccelRange[3],
            DefaultAlignmentMatrixWideRangeAccelShimmer3,
            DefaultSensitivityMatrixWideRangeAccel16gShimmer3,
            DefaultOffsetVectorWideRangeAccelShimmer3);
    private CalibDetailsKinematic calibDetailsMag50Ga = new CalibDetailsKinematic(
            ListofLSM303AHMagRangeConfigValues[0],
            ListofLSM303AHMagRange[0],
            DefaultAlignmentMatrixMagShimmer3,
            DefaultSensitivityMatrixMag50GaShimmer3,
            DefaultOffsetVectorMagShimmer3);
    public CalibDetailsKinematic mCurrentCalibDetailsMag = calibDetailsMag50Ga;

    public SensorLSM303AH() {
        super();
        initialise();
    }

    public SensorLSM303AH(ShimmerDevice shimmerDevice) {
        super(shimmerDevice);
        initialise();
    }

    public static int getAccelRateFromFreq(boolean isEnabled, double freq, boolean isLowPowerMode) {
        int accelRate = 0;

        if (isEnabled) {
            if (isLowPowerMode) {
                if (freq < 1.0) {
                    accelRate = 8;
                } else if (freq < 12.5) {
                    accelRate = 9;
                } else if (freq < 25) {
                    accelRate = 10;
                } else if (freq < 50) {
                    accelRate = 11;
                } else if (freq < 100) {
                    accelRate = 12;
                } else if (freq < 200) {
                    accelRate = 13;
                } else if (freq < 400) {
                    accelRate = 14;
                } else {
                    accelRate = 15;
                }
            } else {
                if (freq < 12.5) {
                    accelRate = 1;
                } else if (freq < 25) {
                    accelRate = 2;
                } else if (freq < 50) {
                    accelRate = 3;
                } else if (freq < 100) {
                    accelRate = 4;
                } else if (freq < 200) {
                    accelRate = 5;
                } else if (freq < 400) {
                    accelRate = 6;
                } else if (freq < 800) {
                    accelRate = 7;
                } else if (freq < 1600) {
                    accelRate = 8;
                } else if (freq < 3200) {
                    accelRate = 9;
                } else {
                    accelRate = 10;
                }
            }
        }
        return accelRate;
    }

    public static String parseFromDBColumnToGUIChannel(String databaseChannelHandle) {

        return AbstractSensor.parseFromDBColumnToGUIChannel(mChannelMapRef, databaseChannelHandle);
    }


    public static String parseFromGUIChannelsToDBColumn(String objectClusterName) {

        return AbstractSensor.parseFromGUIChannelsToDBColumn(mChannelMapRef, objectClusterName);
    }

    @Override
    public void generateSensorMap() {
        super.createLocalSensorMapWithCustomParser(mSensorMapRef, mChannelMapRef);
    }

    @Override
    public void generateConfigOptionsMap() {
        addConfigOption(configOptionAccelRange);
        addConfigOption(configOptionMagRange);
        addConfigOption(configOptionAccelRate);
        addConfigOption(configOptionMagRate);
        addConfigOption(configOptionAccelLpm);
    }

    @Override
    public void generateSensorGroupMapping() {
        mSensorGroupingMap = new LinkedHashMap<Integer, SensorGroupingDetails>();
        mSensorGroupingMap.put(Configuration.Shimmer3.LABEL_SENSOR_TILE.WIDE_RANGE_ACCEL.ordinal(), sensorGroupLsmAccel);
        mSensorGroupingMap.put(Configuration.Shimmer3.LABEL_SENSOR_TILE.MAG.ordinal(), sensorGroupLsmMag);
        super.updateSensorGroupingMap();
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
        LinkedHashMap<String, Object> mapOfConfig = new LinkedHashMap<String, Object>();

        mapOfConfig.put(SensorLSM303AH.DatabaseConfigHandle.WR_ACC_RATE, getLSM303DigitalAccelRate());
        mapOfConfig.put(SensorLSM303AH.DatabaseConfigHandle.WR_ACC_RANGE, getAccelRange());
        mapOfConfig.put(SensorLSM303AH.DatabaseConfigHandle.WR_ACC_LPM, getLowPowerAccelEnabled());
        mapOfConfig.put(SensorLSM303AH.DatabaseConfigHandle.WR_ACC_HRM, getHighResAccelWREnabled());

        super.addCalibDetailsToDbMap(mapOfConfig,
                getCurrentCalibDetailsAccelWr(),
                SensorLSM303AH.DatabaseConfigHandle.LIST_OF_CALIB_HANDLES_WR_ACCEL,
                SensorLSM303AH.DatabaseConfigHandle.WR_ACC_CALIB_TIME);

        mapOfConfig.put(SensorLSM303AH.DatabaseConfigHandle.MAG_RANGE, getMagRange());
        mapOfConfig.put(SensorLSM303AH.DatabaseConfigHandle.MAG_RATE, getLSM303MagRate());

        super.addCalibDetailsToDbMap(mapOfConfig,
                getCurrentCalibDetailsMag(),
                SensorLSM303AH.DatabaseConfigHandle.LIST_OF_CALIB_HANDLES_MAG,
                SensorLSM303AH.DatabaseConfigHandle.MAG_CALIB_TIME);

        return mapOfConfig;
    }


    @Override
    public void parseConfigMap(LinkedHashMap<String, Object> mapOfConfigPerShimmer) {

        if (mapOfConfigPerShimmer.containsKey(SensorLSM303AH.DatabaseConfigHandle.WR_ACC_LPM)) {
            setLowPowerAccelWR(((Double) mapOfConfigPerShimmer.get(SensorLSM303AH.DatabaseConfigHandle.WR_ACC_LPM)) > 0 ? true : false);
        }
        if (mapOfConfigPerShimmer.containsKey(SensorLSM303AH.DatabaseConfigHandle.WR_ACC_HRM)) {
            setHighResAccelWR(((Double) mapOfConfigPerShimmer.get(SensorLSM303AH.DatabaseConfigHandle.WR_ACC_HRM)) > 0 ? true : false);
        }
        if (mapOfConfigPerShimmer.containsKey(SensorLSM303AH.DatabaseConfigHandle.WR_ACC_RATE)) {
            setLSM303DigitalAccelRate(((Double) mapOfConfigPerShimmer.get(SensorLSM303AH.DatabaseConfigHandle.WR_ACC_RATE)).intValue());
        }
        if (mapOfConfigPerShimmer.containsKey(SensorLSM303AH.DatabaseConfigHandle.WR_ACC_RANGE)) {
            setLSM303AccelRange(((Double) mapOfConfigPerShimmer.get(SensorLSM303AH.DatabaseConfigHandle.WR_ACC_RANGE)).intValue());
        }

        parseCalibDetailsKinematicFromDb(mapOfConfigPerShimmer,
                Configuration.Shimmer3.SENSOR_ID.SHIMMER_LSM303_ACCEL,
                getAccelRange(),
                SensorLSM303AH.DatabaseConfigHandle.LIST_OF_CALIB_HANDLES_WR_ACCEL,
                SensorLSM303AH.DatabaseConfigHandle.WR_ACC_CALIB_TIME);

        if (mapOfConfigPerShimmer.containsKey(SensorLSM303AH.DatabaseConfigHandle.MAG_RANGE)) {
            setLSM303MagRange(((Double) mapOfConfigPerShimmer.get(SensorLSM303AH.DatabaseConfigHandle.MAG_RANGE)).intValue());
        }
        if (mapOfConfigPerShimmer.containsKey(SensorLSM303AH.DatabaseConfigHandle.MAG_RATE)) {
            setLSM303MagRate(((Double) mapOfConfigPerShimmer.get(SensorLSM303AH.DatabaseConfigHandle.MAG_RATE)).intValue());
        }

        parseCalibDetailsKinematicFromDb(mapOfConfigPerShimmer,
                Configuration.Shimmer3.SENSOR_ID.SHIMMER_LSM303_MAG,
                getMagRange(),
                SensorLSM303AH.DatabaseConfigHandle.LIST_OF_CALIB_HANDLES_MAG,
                SensorLSM303AH.DatabaseConfigHandle.MAG_CALIB_TIME);
    }

    @Override
    public void initialise() {
        mSensorIdAccel = Configuration.Shimmer3.SENSOR_ID.SHIMMER_LSM303_ACCEL;
        mSensorIdMag = Configuration.Shimmer3.SENSOR_ID.SHIMMER_LSM303_MAG;
        super.initialise();

        mMagRange = ListofLSM303AHMagRangeConfigValues[0];

        updateCurrentAccelWrCalibInUse();
        updateCurrentMagCalibInUse();
    }


    @Override
    public void generateCalibMap() {
        super.generateCalibMap();

        TreeMap<Integer, CalibDetails> calibMapAccelWr = new TreeMap<Integer, CalibDetails>();
        calibMapAccelWr.put(calibDetailsAccelWr2g.mRangeValue, calibDetailsAccelWr2g);
        calibMapAccelWr.put(calibDetailsAccelWr4g.mRangeValue, calibDetailsAccelWr4g);
        calibMapAccelWr.put(calibDetailsAccelWr8g.mRangeValue, calibDetailsAccelWr8g);
        calibMapAccelWr.put(calibDetailsAccelWr16g.mRangeValue, calibDetailsAccelWr16g);
        setCalibrationMapPerSensor(mSensorIdAccel, calibMapAccelWr);

        updateCurrentAccelWrCalibInUse();

        TreeMap<Integer, CalibDetails> calibMapMag = new TreeMap<Integer, CalibDetails>();
        calibMapMag.put(calibDetailsMag50Ga.mRangeValue, calibDetailsMag50Ga);
        setCalibrationMapPerSensor(mSensorIdMag, calibMapMag);

        updateCurrentMagCalibInUse();
    }

    @Override
    public void setLSM303AccelRange(int valueToSet) {
        if (ArrayUtils.contains(ListofLSM303AccelRangeConfigValues, valueToSet)) {
            mAccelRange = valueToSet;
            updateCurrentAccelWrCalibInUse();
        }
    }

    @Override
    public void setLSM303DigitalAccelRate(int valueToSet) {
        super.setLSM303DigitalAccelRateInternal(valueToSet);
        if (mLowPowerAccelWR) {
            for (Integer i : SensorLSM303AH.ListofLSM303AHAccelRateLpmConfigValues) {
                if (i == valueToSet) {
                    return;
                }
            }
            super.setLSM303DigitalAccelRateInternal(SensorLSM303AH.ListofLSM303AHAccelRateLpmConfigValues[SensorLSM303AH.ListofLSM303AHAccelRateLpmConfigValues.length - 1]);
        } else {
            for (Integer i : SensorLSM303AH.ListofLSM303AHAccelRateHrConfigValues) {
                if (i == valueToSet) {
                    return;
                }
            }
            super.setLSM303DigitalAccelRateInternal(SensorLSM303AH.ListofLSM303AHAccelRateHrConfigValues[SensorLSM303AH.ListofLSM303AHAccelRateHrConfigValues.length - 1]);
        }
    }

    @Override
    public boolean checkLowPowerMag() {
        setLowPowerMag((getLSM303MagRate() == 0) ? true : false);
        return isLowPowerMagEnabled();
    }

    @Override
    public void setLSM303MagRange(int valueToSet) {
    }

    @Override
    public int getAccelRateFromFreqForSensor(boolean isEnabled, double freq, boolean isLowPowerMode) {
        return SensorLSM303AH.getAccelRateFromFreq(isEnabled, freq, isLowPowerMode);
    }

    @Override
    public int getMagRateFromFreqForSensor(boolean isEnabled, double freq, boolean isLowPowerMode) {
        int magRate = 0;

        if (isEnabled) {
            if (freq < 10.0) {
                magRate = 0;
            } else if (freq < 20.0 || isLowPowerMode) {
                magRate = 1;
            } else if (freq < 50.0) {
                magRate = 2;
            } else {
                magRate = 3;
            }
        }
        return magRate;
    }

    public static class DatabaseChannelHandles {
        public static final String WR_ACC_X = "LSM303AHTR_ACC_X";
        public static final String WR_ACC_Y = "LSM303AHTR_ACC_Y";
        public static final String WR_ACC_Z = "LSM303AHTR_ACC_Z";
        public static final String MAG_X = "LSM303AHTR_MAG_X";
        public static final String MAG_Y = "LSM303AHTR_MAG_Y";
        public static final String MAG_Z = "LSM303AHTR_MAG_Z";
    }

    public static final class DatabaseConfigHandle {
        public static final String MAG_RANGE = "LSM303AHTR_Mag_Range";
        public static final String MAG_RATE = "LSM303AHTR_Mag_Rate";

        public static final String WR_ACC = "LSM303AHTR_Acc";
        public static final String WR_ACC_RATE = "LSM303AHTR_Acc_Rate";
        public static final String WR_ACC_RANGE = "LSM303AHTR_Acc_Range";

        public static final String WR_ACC_LPM = "LSM303AHTR_Acc_LPM";
        public static final String WR_ACC_HRM = "LSM303AHTR_Acc_HRM";

        public static final String WR_ACC_CALIB_TIME = "LSM303AHTR_Acc_Calib_Time";
        public static final String WR_ACC_OFFSET_X = "LSM303AHTR_Acc_Offset_X";
        public static final String WR_ACC_OFFSET_Y = "LSM303AHTR_Acc_Offset_Y";
        public static final String WR_ACC_OFFSET_Z = "LSM303AHTR_Acc_Offset_Z";
        public static final String WR_ACC_GAIN_X = "LSM303AHTR_Acc_Gain_X";
        public static final String WR_ACC_GAIN_Y = "LSM303AHTR_Acc_Gain_Y";
        public static final String WR_ACC_GAIN_Z = "LSM303AHTR_Acc_Gain_Z";
        public static final String WR_ACC_ALIGN_XX = "LSM303AHTR_Acc_Align_XX";
        public static final String WR_ACC_ALIGN_XY = "LSM303AHTR_Acc_Align_XY";
        public static final String WR_ACC_ALIGN_XZ = "LSM303AHTR_Acc_Align_XZ";
        public static final String WR_ACC_ALIGN_YX = "LSM303AHTR_Acc_Align_YX";
        public static final String WR_ACC_ALIGN_YY = "LSM303AHTR_Acc_Align_YY";
        public static final String WR_ACC_ALIGN_YZ = "LSM303AHTR_Acc_Align_YZ";
        public static final String WR_ACC_ALIGN_ZX = "LSM303AHTR_Acc_Align_ZX";
        public static final String WR_ACC_ALIGN_ZY = "LSM303AHTR_Acc_Align_ZY";
        public static final String WR_ACC_ALIGN_ZZ = "LSM303AHTR_Acc_Align_ZZ";

        public static final String MAG_CALIB_TIME = "LSM303AHTR_Mag_Calib_Time";
        public static final String MAG_OFFSET_X = "LSM303AHTR_Mag_Offset_X";
        public static final String MAG_OFFSET_Y = "LSM303AHTR_Mag_Offset_Y";
        public static final String MAG_OFFSET_Z = "LSM303AHTR_Mag_Offset_Z";
        public static final String MAG_GAIN_X = "LSM303AHTR_Mag_Gain_X";
        public static final String MAG_GAIN_Y = "LSM303AHTR_Mag_Gain_Y";
        public static final String MAG_GAIN_Z = "LSM303AHTR_Mag_Gain_Z";
        public static final String MAG_ALIGN_XX = "LSM303AHTR_Mag_Align_XX";
        public static final String MAG_ALIGN_XY = "LSM303AHTR_Mag_Align_XY";
        public static final String MAG_ALIGN_XZ = "LSM303AHTR_Mag_Align_XZ";
        public static final String MAG_ALIGN_YX = "LSM303AHTR_Mag_Align_YX";
        public static final String MAG_ALIGN_YY = "LSM303AHTR_Mag_Align_YY";
        public static final String MAG_ALIGN_YZ = "LSM303AHTR_Mag_Align_YZ";
        public static final String MAG_ALIGN_ZX = "LSM303AHTR_Mag_Align_ZX";
        public static final String MAG_ALIGN_ZY = "LSM303AHTR_Mag_Align_ZY";
        public static final String MAG_ALIGN_ZZ = "LSM303AHTR_Mag_Align_ZZ";

        public static final List<String> LIST_OF_CALIB_HANDLES_MAG = Arrays.asList(
                DatabaseConfigHandle.MAG_OFFSET_X, DatabaseConfigHandle.MAG_OFFSET_Y, DatabaseConfigHandle.MAG_OFFSET_Z,
                DatabaseConfigHandle.MAG_GAIN_X, DatabaseConfigHandle.MAG_GAIN_Y, DatabaseConfigHandle.MAG_GAIN_Z,
                DatabaseConfigHandle.MAG_ALIGN_XX, DatabaseConfigHandle.MAG_ALIGN_XY, DatabaseConfigHandle.MAG_ALIGN_XZ,
                DatabaseConfigHandle.MAG_ALIGN_YX, DatabaseConfigHandle.MAG_ALIGN_YY, DatabaseConfigHandle.MAG_ALIGN_YZ,
                DatabaseConfigHandle.MAG_ALIGN_ZX, DatabaseConfigHandle.MAG_ALIGN_ZY, DatabaseConfigHandle.MAG_ALIGN_ZZ);

        public static final List<String> LIST_OF_CALIB_HANDLES_WR_ACCEL = Arrays.asList(
                DatabaseConfigHandle.WR_ACC_OFFSET_X, DatabaseConfigHandle.WR_ACC_OFFSET_Y, DatabaseConfigHandle.WR_ACC_OFFSET_Z,
                DatabaseConfigHandle.WR_ACC_GAIN_X, DatabaseConfigHandle.WR_ACC_GAIN_Y, DatabaseConfigHandle.WR_ACC_GAIN_Z,
                DatabaseConfigHandle.WR_ACC_ALIGN_XX, DatabaseConfigHandle.WR_ACC_ALIGN_XY, DatabaseConfigHandle.WR_ACC_ALIGN_XZ,
                DatabaseConfigHandle.WR_ACC_ALIGN_YX, DatabaseConfigHandle.WR_ACC_ALIGN_YY, DatabaseConfigHandle.WR_ACC_ALIGN_YZ,
                DatabaseConfigHandle.WR_ACC_ALIGN_ZX, DatabaseConfigHandle.WR_ACC_ALIGN_ZY, DatabaseConfigHandle.WR_ACC_ALIGN_ZZ);
    }


}
