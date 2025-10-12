package com.shimmerresearch.sensors;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

import com.shimmerresearch.bluetooth.BtCommandDetails;
import com.shimmerresearch.driver.Configuration.CHANNEL_UNITS;
import com.shimmerresearch.driver.Configuration.COMMUNICATION_TYPE;
import com.shimmerresearch.driver.Configuration.Shimmer3.CompatibilityInfoForMaps;
import com.shimmerresearch.driver.ObjectCluster;
import com.shimmerresearch.driverUtilities.ChannelDetails;
import com.shimmerresearch.driverUtilities.ChannelDetails.CHANNEL_SOURCE;
import com.shimmerresearch.driverUtilities.ConfigOptionDetailsSensor;
import com.shimmerresearch.driverUtilities.SensorDetailsRef;
import com.shimmerresearch.driverUtilities.SensorDetails;
import com.shimmerresearch.driverUtilities.SensorGroupingDetails;
import com.shimmerresearch.driverUtilities.ShimmerVerObject;
import com.shimmerresearch.driverUtilities.UtilParseData;
import com.shimmerresearch.driverUtilities.UtilShimmer;
import com.shimmerresearch.sensors.SensorADC.MICROCONTROLLER_ADC_PROPERTIES;
import com.shimmerresearch.driverUtilities.ChannelDetails.CHANNEL_TYPE;
import com.shimmerresearch.driverUtilities.ChannelDetails.CHANNEL_DATA_ENDIAN;
import com.shimmerresearch.driverUtilities.ChannelDetails.CHANNEL_DATA_TYPE;
import com.shimmerresearch.driver.Configuration;
import com.shimmerresearch.driver.ShimmerDevice;

public class SensorGSR extends AbstractSensor {

    public static final double[] SHIMMER3_GSR_REF_RESISTORS_KOHMS = new double[]{
            40.2,        //Range 0
            287.0,        //Range 1
            1000.0,    //Range 2
            3300.0};    //Range 3

    public static final double[][] SHIMMER3_GSR_RESISTANCE_MIN_MAX_KOHMS = new double[][]{
            {8.0, 63.0},        //Range 0
            {63.0, 220.0},        //Range 1
            {220.0, 680.0},    //Range 2
            {680.0, 4700.0}};    //Range 3
    public static final int GSR_UNCAL_LIMIT_RANGE3 = 683;
    public static final byte SET_GSR_RANGE_COMMAND = (byte) 0x21;
    public static final byte GSR_RANGE_RESPONSE = (byte) 0x22;
    public static final byte GET_GSR_RANGE_COMMAND = (byte) 0x23;
    public static final Map<Byte, BtCommandDetails> mBtGetCommandMap;
    public static final Map<Byte, BtCommandDetails> mBtSetCommandMap;
    public static final String[] ListofGSRRangeResistance = {
            "8k" + UtilShimmer.UNICODE_OHMS + " to 63k" + UtilShimmer.UNICODE_OHMS,
            "63k" + UtilShimmer.UNICODE_OHMS + " to 220k" + UtilShimmer.UNICODE_OHMS,
            "220k" + UtilShimmer.UNICODE_OHMS + " to 680k" + UtilShimmer.UNICODE_OHMS,
            "680k" + UtilShimmer.UNICODE_OHMS + " to 4.7M" + UtilShimmer.UNICODE_OHMS,
            "Auto Range"};
    public static final String[] ListofGSRRangeConductance = {
            "125" + UtilShimmer.UNICODE_MICRO + "S to 15.9" + UtilShimmer.UNICODE_MICRO + "S",
            "15.9" + UtilShimmer.UNICODE_MICRO + "S to 4.5" + UtilShimmer.UNICODE_MICRO + "S",
            "4.5" + UtilShimmer.UNICODE_MICRO + "S to 1.5" + UtilShimmer.UNICODE_MICRO + "S",
            "1.5" + UtilShimmer.UNICODE_MICRO + "S to 0.2" + UtilShimmer.UNICODE_MICRO + "S",
            "Auto Range"};
    public static final Integer[] ListofGSRRangeConfigValues = {0, 1, 2, 3, 4};
    public static final ConfigOptionDetailsSensor configOptionGsrRange = new ConfigOptionDetailsSensor(
            SensorGSR.GuiLabelConfig.GSR_RANGE,
            SensorGSR.DatabaseConfigHandle.GSR_RANGE,
            ListofGSRRangeResistance,
            ListofGSRRangeConfigValues,
            ConfigOptionDetailsSensor.GUI_COMPONENT_TYPE.COMBOBOX,
            CompatibilityInfoForMaps.listOfCompatibleVersionInfoGsr);
    public static final SensorDetailsRef sensorGsrRef = new SensorDetailsRef(
            0x04 << (0 * 8),
            0x04 << (0 * 8),
            GuiLabelSensors.GSR,
            CompatibilityInfoForMaps.listOfCompatibleVersionInfoGsr,
            Arrays.asList(
                    Configuration.Shimmer3.SENSOR_ID.SHIMMER_INT_EXP_ADC_A1,
                    Configuration.Shimmer3.SENSOR_ID.SHIMMER_INT_EXP_ADC_A14,
                    Configuration.Shimmer3.SENSOR_ID.HOST_ECG,
                    Configuration.Shimmer3.SENSOR_ID.HOST_EMG,
                    Configuration.Shimmer3.SENSOR_ID.HOST_EXG_TEST,
                    Configuration.Shimmer3.SENSOR_ID.HOST_EXG_CUSTOM,
                    Configuration.Shimmer3.SENSOR_ID.HOST_EXG_RESPIRATION,
                    Configuration.Shimmer3.SENSOR_ID.HOST_EXG_THREE_UNIPOLAR,
                    Configuration.Shimmer3.SENSOR_ID.SHIMMER_RESISTANCE_AMP,
                    Configuration.Shimmer3.SENSOR_ID.SHIMMER_BRIDGE_AMP),
            Arrays.asList(GuiLabelConfig.GSR_RANGE),
            Arrays.asList(
                    ObjectClusterSensorName.GSR_RESISTANCE,
                    ObjectClusterSensorName.GSR_CONDUCTANCE,
                    ObjectClusterSensorName.GSR_RANGE,
                    ObjectClusterSensorName.GSR_GQ
            ),
            true);
    public static final Map<Integer, SensorDetailsRef> mSensorMapRef;
    public static final ChannelDetails channelGsrKOhms = new ChannelDetails(
            ObjectClusterSensorName.GSR_RESISTANCE,
            ObjectClusterSensorName.GSR_RESISTANCE,
            DatabaseChannelHandles.GSR_RESISTANCE,
            CHANNEL_DATA_TYPE.UINT16, 2, CHANNEL_DATA_ENDIAN.LSB,
            CHANNEL_UNITS.KOHMS,
            Arrays.asList(CHANNEL_TYPE.CAL, CHANNEL_TYPE.UNCAL),
            0x1C);
    public static final ChannelDetails channelGsrMicroSiemensGq = new ChannelDetails(
            ObjectClusterSensorName.GSR_GQ,
            ObjectClusterSensorName.GSR_GQ,
            DatabaseChannelHandles.GSR_GQ,
            CHANNEL_DATA_TYPE.UINT16, 2, CHANNEL_DATA_ENDIAN.LSB,
            CHANNEL_UNITS.U_SIEMENS,
            Arrays.asList(CHANNEL_TYPE.CAL, CHANNEL_TYPE.UNCAL),
            0x1C);
    public static final ChannelDetails channelGsrMicroSiemens = new ChannelDetails(
            ObjectClusterSensorName.GSR_CONDUCTANCE,
            ObjectClusterSensorName.GSR_CONDUCTANCE,
            DatabaseChannelHandles.GSR_CONDUCTANCE,
            CHANNEL_UNITS.U_SIEMENS,
            Arrays.asList(CHANNEL_TYPE.CAL, CHANNEL_TYPE.UNCAL));
    public static final ChannelDetails channelGsrRange = new ChannelDetails(
            ObjectClusterSensorName.GSR_RANGE,
            ObjectClusterSensorName.GSR_RANGE,
            CHANNEL_UNITS.NO_UNITS,
            Arrays.asList(CHANNEL_TYPE.CAL, CHANNEL_TYPE.UNCAL));
    public static final ChannelDetails channelGsrAdc = new ChannelDetails(
            ObjectClusterSensorName.GSR_ADC_VALUE,
            ObjectClusterSensorName.GSR_ADC_VALUE,
            ObjectClusterSensorName.GSR_ADC_VALUE,
            CHANNEL_UNITS.MILLIVOLTS,
            Arrays.asList(CHANNEL_TYPE.CAL, CHANNEL_TYPE.UNCAL));
        public static final Map<String, ChannelDetails> mChannelMapRef;
    public static final Map<String, ChannelDetails> mChannelMapRefGq;
    private static final long serialVersionUID = 1773291747371088953L;
        public static boolean isShimmer3and4UsingShimmer2rVal = true;

    static {
        Map<Byte, BtCommandDetails> aMap = new LinkedHashMap<Byte, BtCommandDetails>();
        aMap.put(GET_GSR_RANGE_COMMAND, new BtCommandDetails(GET_GSR_RANGE_COMMAND, "GET_GSR_RANGE_COMMAND", GSR_RANGE_RESPONSE));
        mBtGetCommandMap = Collections.unmodifiableMap(aMap);
    }

    static {
        Map<Byte, BtCommandDetails> aMap = new LinkedHashMap<Byte, BtCommandDetails>();
        aMap.put(SET_GSR_RANGE_COMMAND, new BtCommandDetails(SET_GSR_RANGE_COMMAND, "SET_GSR_RANGE_COMMAND"));
        mBtSetCommandMap = Collections.unmodifiableMap(aMap);
    }

    static {
        Map<Integer, SensorDetailsRef> aMap = new LinkedHashMap<Integer, SensorDetailsRef>();
        aMap.put(Configuration.Shimmer3.SENSOR_ID.SHIMMER_GSR, SensorGSR.sensorGsrRef);
        mSensorMapRef = Collections.unmodifiableMap(aMap);
    }

    static {
        Map<String, ChannelDetails> aMap = new LinkedHashMap<String, ChannelDetails>();
        aMap.put(ObjectClusterSensorName.GSR_RESISTANCE, SensorGSR.channelGsrKOhms);
        aMap.put(ObjectClusterSensorName.GSR_CONDUCTANCE, SensorGSR.channelGsrMicroSiemens);
        aMap.put(ObjectClusterSensorName.GSR_RANGE, SensorGSR.channelGsrRange);
        aMap.put(ObjectClusterSensorName.GSR_ADC_VALUE, SensorGSR.channelGsrAdc);
        mChannelMapRef = Collections.unmodifiableMap(aMap);
    }

    static {
        Map<String, ChannelDetails> aMap = new LinkedHashMap<String, ChannelDetails>();
        aMap.put(ObjectClusterSensorName.GSR_RESISTANCE, SensorGSR.channelGsrMicroSiemensGq);
        mChannelMapRefGq = Collections.unmodifiableMap(aMap);
    }

    public int mGSRRange = 4;                    // 4 = Auto
    private MICROCONTROLLER_ADC_PROPERTIES microcontrollerAdcProperties = null;

    private double[] currentGsrRefResistorsKohms = SHIMMER3_GSR_REF_RESISTORS_KOHMS;
    private double[][] currentGsrResistanceKohmsMinMax = SHIMMER3_GSR_RESISTANCE_MIN_MAX_KOHMS;
    private int currentGsrUncalLimitRange3 = GSR_UNCAL_LIMIT_RANGE3;

    {
        channelGsrMicroSiemens.mChannelSource = CHANNEL_SOURCE.API;
    }

    {
        channelGsrMicroSiemens.mChannelSource = CHANNEL_SOURCE.API;
    }

    {
        channelGsrMicroSiemens.mChannelSource = CHANNEL_SOURCE.API;
    }

        public SensorGSR(ShimmerVerObject svo) {
        super(SENSORS.GSR, svo);
        initialise();

        microcontrollerAdcProperties = MICROCONTROLLER_ADC_PROPERTIES.getMicrocontrollerAdcPropertiesForShimmerVersionObject(svo);
    }

    public static double convertkOhmToUSiemens(double gsrResistanceKOhms) {
        return 1000.0 / gsrResistanceKOhms;
    }

    public static double convertUSiemensTokOhm(double gsrUSiemens) {
        return 1000.0 / gsrUSiemens;
    }

    public static double nudgeGsrResistance(double gsrResistanceKOhms, int gsrRangeSetting, double[][] gsrResistanceKohmsMinMax) {
        if (gsrRangeSetting == 4) {
                        return Math.max(gsrResistanceKohmsMinMax[0][0], gsrResistanceKOhms);
        } else {
            double[] minMax = gsrResistanceKohmsMinMax[gsrRangeSetting];
            return UtilShimmer.nudgeDouble(gsrResistanceKOhms, minMax[0], minMax[1]);
        }
    }

    @Deprecated
    public static double calibrateGsrDataToResistance(double gsrUncalibratedData, double p1, double p2) {

        double gsrCalibratedDatauS = calibrateGsrDataToSiemens(gsrUncalibratedData, p1, p2);
        double gsrCalibratedData = (1 / (gsrCalibratedDatauS) * 1000); //kohms

        return gsrCalibratedData;
    }

    @Deprecated
    public static double calibrateGsrDataToSiemens(double gsrUncalibratedData, double p1, double p2) {
        double gsrUncalibratedDataLcl = (double) ((int) gsrUncalibratedData & 4095);
        double gsrCalibratedData = (((p1 * gsrUncalibratedDataLcl) + p2)); //microsiemens
        return gsrCalibratedData;
    }




        public static double calibrateGsrDataToKOhmsUsingAmplifierEq(double gsrUncalibratedData, int range, MICROCONTROLLER_ADC_PROPERTIES microcontrollerAdcProperties, double[] gsrRefResistorsKohms) {
        double rFeedback = gsrRefResistorsKohms[range];
        double volts = SensorADC.calibrateAdcChannelToVolts(gsrUncalibratedData, microcontrollerAdcProperties);
        double rSource = rFeedback / ((volts / 0.5) - 1.0);
        return rSource;
    }

        public static int uncalibrateGsrDataTokOhmsUsingAmplifierEq(double gsrkOhms, MICROCONTROLLER_ADC_PROPERTIES microcontrollerAdcProperties, double[] gsrRefResistorsKohms, double[][] gsrResistanceKohmsMinMax) {
        int range = 0;
        for (int i = 0; i < gsrResistanceKohmsMinMax.length; i++) {
            double[] minMax = gsrResistanceKohmsMinMax[i];
            if (gsrkOhms > minMax[0] && gsrkOhms < minMax[1]) {
                range = i;
                break;
            }
        }

        double rFeedback = gsrRefResistorsKohms[range];
        double volts = ((rFeedback / gsrkOhms) + 1.0) * 0.5;

        int gsrUncalibratedData = SensorADC.uncalibrateAdcChannelFromVolts(volts, microcontrollerAdcProperties);
        gsrUncalibratedData += (range << 14);
        return gsrUncalibratedData;
    }

    @Deprecated
    public static double[] getGSRCoefficientsFromUsingGSRRange(ShimmerVerObject svo, int currentGSRRange) {
        double p1 = 0.0;
        double p2 = 0.0;

        if (currentGSRRange == 0) {
            if (svo.isShimmerGen2() || SensorGSR.isShimmer3and4UsingShimmer2rVal) {
                p1 = 0.0373;
                p2 = -24.9915;
            } else { //Values have been reverted to 2r values
                p1 = 0.0363;
                p2 = -24.8617;
            }
        } else if (currentGSRRange == 1) {
            if (svo.isShimmerGen2() || SensorGSR.isShimmer3and4UsingShimmer2rVal) {
                p1 = 0.0054;
                p2 = -3.5194;
            } else {
                p1 = 0.0051;
                p2 = -3.8357;
            }
        } else if (currentGSRRange == 2) {
            if (svo.isShimmerGen2() || SensorGSR.isShimmer3and4UsingShimmer2rVal) {
                p1 = 0.0015;
                p2 = -1.0163;
            } else {
                p1 = 0.0015;
                p2 = -1.0067;
            }
        } else if (currentGSRRange == 3) {
            if (svo.isShimmerGen2() || SensorGSR.isShimmer3and4UsingShimmer2rVal) {
                p1 = 4.5580e-04;
                p2 = -0.3014;
            } else {
                p1 = 4.4513e-04;
                p2 = -0.3193;
            }
        }
        return new double[]{p1, p2};
    }

    @Override
    public void generateSensorMap() {
        if (mShimmerVerObject.isShimmerGenGq()) {
            Map<String, ChannelDetails> channelMapRef = new LinkedHashMap<String, ChannelDetails>();
            channelMapRef.put(SensorGSR.channelGsrMicroSiemensGq.mObjectClusterName, SensorGSR.channelGsrMicroSiemensGq);
            super.createLocalSensorMapWithCustomParser(mSensorMapRef, channelMapRef);
        } else {
            super.createLocalSensorMapWithCustomParser(mSensorMapRef, mChannelMapRef);
        }
    }

    @Override
    public void generateConfigOptionsMap() {
        addConfigOption(configOptionGsrRange);
    }

    @Override
    public void generateSensorGroupMapping() {

        int groupIndex = Configuration.Shimmer3.LABEL_SENSOR_TILE.GSR.ordinal();

        if (mShimmerVerObject.isShimmerGenGq()) {
            mSensorGroupingMap.put(groupIndex, new SensorGroupingDetails(
                    LABEL_SENSOR_TILE.GSR,
                    Arrays.asList(Configuration.Shimmer3.SENSOR_ID.SHIMMER_GSR),
                    CompatibilityInfoForMaps.listOfCompatibleVersionInfoGsr));
        } else if ((mShimmerVerObject.isShimmerGen3() || mShimmerVerObject.isShimmerGen4())) {
            mSensorGroupingMap.put(groupIndex, new SensorGroupingDetails(
                    LABEL_SENSOR_TILE.GSR,
                    Arrays.asList(Configuration.Shimmer3.SENSOR_ID.SHIMMER_GSR,
                            Configuration.Shimmer3.SENSOR_ID.HOST_PPG_DUMMY),
                    CompatibilityInfoForMaps.listOfCompatibleVersionInfoGsr));
        }
        super.updateSensorGroupingMap();
    }

    @Override
    public ObjectCluster processDataCustom(SensorDetails sensorDetails, byte[] sensorByteArray, COMMUNICATION_TYPE commType, ObjectCluster objectCluster, boolean isTimeSyncEnabled, double pcTimestampMs) {
        int index = 0;
        for (ChannelDetails channelDetails : sensorDetails.mListOfChannels) {

            if (channelDetails.mObjectClusterName.equals(ObjectClusterSensorName.GSR_RESISTANCE)
                    || channelDetails.mObjectClusterName.equals(ObjectClusterSensorName.GSR_GQ)) {

                byte[] channelByteArray = new byte[channelDetails.mDefaultNumBytes];
                System.arraycopy(sensorByteArray, index, channelByteArray, 0, channelDetails.mDefaultNumBytes);
                double rawData = UtilParseData.parseData(channelByteArray, channelDetails.mDefaultChannelDataType, channelDetails.mDefaultChannelDataEndian);
                int gsrAdcValueUnCal = ((int) rawData & 4095);
                objectCluster.addUncalData(channelDetails, gsrAdcValueUnCal);

                int currentGSRRange = getGSRRange();
                if (currentGSRRange == 4) {
                    currentGSRRange = (49152 & (int) rawData) >> 14;
                }

                if (sensorDetails.mListOfChannels.contains(channelGsrRange)) {
                    objectCluster.addUncalData(channelGsrRange, currentGSRRange);
                    objectCluster.addCalData(channelGsrRange, currentGSRRange);
                    objectCluster.incrementIndexKeeper();
                }
                if (sensorDetails.mListOfChannels.contains(channelGsrAdc)) {
                    objectCluster.addUncalData(channelGsrAdc, gsrAdcValueUnCal);
                    objectCluster.addCalData(channelGsrAdc, SensorADC.calibrateAdcChannelToMillivolts(gsrAdcValueUnCal, microcontrollerAdcProperties));
                    objectCluster.incrementIndexKeeper();
                }

                double gsrResistanceKOhms = 0.0;
                double gsrConductanceUSiemens = 0.0;
                if (currentGSRRange == 3 && gsrAdcValueUnCal < currentGsrUncalLimitRange3) {
                    gsrAdcValueUnCal = currentGsrUncalLimitRange3;
                }

                gsrResistanceKOhms = SensorGSR.calibrateGsrDataToKOhmsUsingAmplifierEq(gsrAdcValueUnCal, currentGSRRange, microcontrollerAdcProperties, currentGsrRefResistorsKohms);
                gsrResistanceKOhms = SensorGSR.nudgeGsrResistance(gsrResistanceKOhms, getGSRRange(), currentGsrResistanceKohmsMinMax);
                gsrConductanceUSiemens = SensorGSR.convertkOhmToUSiemens(gsrResistanceKOhms);
//

                double calData = 0.0;
                if (channelDetails.mDefaultCalUnits.equals(Configuration.CHANNEL_UNITS.KOHMS)) {
                    calData = gsrResistanceKOhms;
                } else if (channelDetails.mDefaultCalUnits.equals(Configuration.CHANNEL_UNITS.U_SIEMENS)) {
                    calData = gsrConductanceUSiemens;
                }
                objectCluster.addCalData(channelDetails, calData);
                objectCluster.incrementIndexKeeper();


                if (sensorDetails.mListOfChannels.contains(channelGsrMicroSiemens)) {
                    objectCluster.addUncalData(channelGsrMicroSiemens, gsrAdcValueUnCal);
                    objectCluster.addCalData(channelGsrMicroSiemens, gsrConductanceUSiemens);
                    objectCluster.incrementIndexKeeper();
                }

            }
            index = index + channelDetails.mDefaultNumBytes;


            continue;
        }


        return objectCluster;
    }

    @Override
    public void configBytesGenerate(ShimmerDevice shimmerDevice, byte[] mInfoMemBytes, COMMUNICATION_TYPE commType) {
        int idxConfigSetupByte3 = 9;
        int bitShiftGSRRange = 1;
        int maskGSRRange = 0x07;

        mInfoMemBytes[idxConfigSetupByte3] |= (byte) ((mGSRRange & maskGSRRange) << bitShiftGSRRange);
    }


    @Override
    public void configBytesParse(ShimmerDevice shimmerDevice, byte[] mInfoMemBytes, COMMUNICATION_TYPE commType) {
        int idxConfigSetupByte3 = 9;
        int bitShiftGSRRange = 1;
        int maskGSRRange = 0x07;

        mGSRRange = (mInfoMemBytes[idxConfigSetupByte3] >> bitShiftGSRRange) & maskGSRRange;
    }

    @Override
    public Object setConfigValueUsingConfigLabel(Integer sensorId, String configLabel, Object valueToSet) {
        Object returnValue = null;

        switch (configLabel) {
            case (GuiLabelConfig.GSR_RANGE):
                setGSRRange((int) valueToSet);
                returnValue = valueToSet;
                break;
            default:
                break;
        }


        return returnValue;
    }

    @Override
    public Object getConfigValueUsingConfigLabel(Integer sensorId, String configLabel) {
        Object returnValue = null;
        switch (configLabel) {
            case (GuiLabelConfig.GSR_RANGE):
                returnValue = getGSRRange(); //TODO: check with RM re firmware bug?? -> //RS (25/05/2016): Still relevant?
                break;
            default:
                break;
        }

        return returnValue;
    }

    @Override
    public void setSensorSamplingRate(double samplingRateHz) {

    }

    @Override
    public boolean setDefaultConfigForSensor(int sensorId, boolean isSensorEnabled) {
        if (mSensorMap.containsKey(sensorId)) {
            setDefaultGsrSensorConfig(isSensorEnabled);
            return true;
        }
        return false;
    }

    @Override
    public boolean checkConfigOptionValues(String stringKey) {
        if (mConfigOptionsMap.containsKey(stringKey)) {
            return true;
        }
        return false;
    }

    @Override
    public Object getSettings(String componentName, COMMUNICATION_TYPE commType) {
        return null;
    }

    @Override
    public ActionSetting setSettings(String componentName, Object valueToSet, COMMUNICATION_TYPE commType) {

        ActionSetting actionSetting = new ActionSetting(commType);
        switch (componentName) {
            case (GuiLabelConfig.GSR_RANGE):
                if (commType == COMMUNICATION_TYPE.BLUETOOTH) {

                } else if (commType == COMMUNICATION_TYPE.DOCK) {

                } else if (commType == COMMUNICATION_TYPE.CLASS) {

                }
                break;
        }
        return actionSetting;

    }

    @Override
    public LinkedHashMap<String, Object> generateConfigMap() {
        LinkedHashMap<String, Object> mapOfConfig = new LinkedHashMap<String, Object>();

        mapOfConfig.put(SensorGSR.DatabaseConfigHandle.GSR_RANGE, (double) getGSRRange());

        return mapOfConfig;
    }

    @Override
    public void parseConfigMap(LinkedHashMap<String, Object> mapOfConfigPerShimmer) {
        if (mapOfConfigPerShimmer.containsKey(DatabaseConfigHandle.GSR_RANGE)) {
            setGSRRange(((Double) mapOfConfigPerShimmer.get(DatabaseConfigHandle.GSR_RANGE)).intValue());
        }
    }

    @Override
    public boolean processResponse(int responseCommand, Object parsedResponse, COMMUNICATION_TYPE commType) {
        return false;
    }

    @Override
    public void checkShimmerConfigBeforeConfiguring() {
        if (!isSensorEnabled(Configuration.Shimmer3.SENSOR_ID.SHIMMER_GSR)) {
            setDefaultGsrSensorConfig(false);
        }
    }

    private void setDefaultGsrSensorConfig(boolean isSensorEnabled) {
        if (isSensorEnabled) {
        } else {
            mGSRRange = 4;
        }
    }

    public int getGSRRange() {
        return mGSRRange;
    }

    public void setGSRRange(int valueToSet) {
        mGSRRange = valueToSet;
    }

    public double[] getCurrentGsrRefResistorsKohms() {
        return currentGsrRefResistorsKohms;
    }

    public void setCurrentGsrRefResistorsKohms(double[] currentGsrRefResistorsKohms) {
        this.currentGsrRefResistorsKohms = currentGsrRefResistorsKohms;
    }

    public double[][] getCurrentGsrResistanceKohmsMinMax() {
        return currentGsrResistanceKohmsMinMax;
    }

    public void setCurrentGsrResistanceKohmsMinMax(double[][] currentGsrResistanceKohmsMinMax) {
        this.currentGsrResistanceKohmsMinMax = currentGsrResistanceKohmsMinMax;
    }

    public int getCurrentGsrUncalLimitRange3() {
        return currentGsrUncalLimitRange3;
    }

    public void setCurrentGsrUncalLimitRange3(int currentGsrUncalLimitRange3) {
        this.currentGsrUncalLimitRange3 = currentGsrUncalLimitRange3;
    }

    public static class DatabaseChannelHandles {
        public static final String GSR_RESISTANCE = "F5437a_Int_A1_GSR";
        public static final String GSR_CONDUCTANCE = "GSR_Conductance";

        public static final String GSR_GQ = DatabaseChannelHandles.GSR_RESISTANCE;
    }

    public static final class DatabaseConfigHandle {
        public static final String GSR_RANGE = "F5437a_Int_A1_GSR_Range";
    }

    public static class ObjectClusterSensorName {
        public static String GSR_RESISTANCE = "GSR_Skin_Resistance";
        public static String GSR_CONDUCTANCE = "GSR_Skin_Conductance";
        public static String GSR_RANGE = "GSR_Range";
        public static String GSR_ADC_VALUE = "GSR_ADC_Value";

        public static String GSR_GQ = "GSR";
    }

    public class GuiLabelConfig {
        public static final String GSR_RANGE = "GSR Range";
        public static final String SAMPLING_RATE_DIVIDER_GSR = "GSR Divider";
    }

    public class GuiLabelSensors {
        public static final String GSR = "GSR";
    }

    public class LABEL_SENSOR_TILE {
        public static final String GSR = "GSR+";
    }




}
	
