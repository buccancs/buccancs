package com.shimmerresearch.sensors;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.shimmerresearch.bluetooth.ShimmerBluetooth;
import com.shimmerresearch.driver.Configuration;
import com.shimmerresearch.driver.FormatCluster;
import com.shimmerresearch.driver.ConfigByteLayout;
import com.shimmerresearch.driver.Configuration.CHANNEL_UNITS;
import com.shimmerresearch.driver.Configuration.COMMUNICATION_TYPE;
import com.shimmerresearch.driver.Configuration.Shimmer3.CompatibilityInfoForMaps;
import com.shimmerresearch.driver.shimmer2r3.ConfigByteLayoutShimmer3;
import com.shimmerresearch.driver.shimmerGq.ConfigByteLayoutShimmerGq802154;
import com.shimmerresearch.driver.ObjectCluster;
import com.shimmerresearch.driver.ShimmerDevice;
import com.shimmerresearch.driverUtilities.ChannelDetails;
import com.shimmerresearch.driverUtilities.ConfigOptionDetails;
import com.shimmerresearch.driverUtilities.ConfigOptionDetailsSensor;
import com.shimmerresearch.driverUtilities.ConfigOptionObject;
import com.shimmerresearch.driverUtilities.SensorDetails;
import com.shimmerresearch.driverUtilities.SensorDetailsRef;
import com.shimmerresearch.driverUtilities.SensorGroupingDetails;
import com.shimmerresearch.driverUtilities.ChannelDetails.CHANNEL_DATA_ENDIAN;
import com.shimmerresearch.driverUtilities.ChannelDetails.CHANNEL_DATA_TYPE;
import com.shimmerresearch.driverUtilities.ChannelDetails.CHANNEL_TYPE;
import com.shimmerresearch.driverUtilities.ShimmerVerDetails.FW_ID;
import com.shimmerresearch.driverUtilities.ShimmerVerObject;
import com.shimmerresearch.exgConfig.ExGConfigBytesDetails;
import com.shimmerresearch.exgConfig.ExGConfigOption;
import com.shimmerresearch.exgConfig.ExGConfigBytesDetails.EXG_SETTINGS;
import com.shimmerresearch.exgConfig.ExGConfigBytesDetails.EXG_SETTING_OPTIONS;
import com.shimmerresearch.exgConfig.ExGConfigOptionDetails.EXG_CHIP_INDEX;

public class SensorEXG extends AbstractSensor {

    public static final String[] ListofDefaultEXG = {"ECG", "EMG", "Test Signal"};
    public static final String[] ListOfExGGain = {"6", "1", "2", "3", "4", "8", "12"};
    public static final Integer[] ListOfExGGainConfigValues = {0, 1, 2, 3, 4, 5, 6};
    public static final String[] ListOfECGReferenceElectrode = {"Inverse Wilson CT", "Fixed Potential"};
    public static final Integer[] ListOfECGReferenceElectrodeConfigValues = {13, 0};
    public static final String[] ListOfEMGReferenceElectrode = {"Fixed Potential", "Inverse of Ch1"};
    public static final Integer[] ListOfEMGReferenceElectrodeConfigValues = {0, 3};
    public static final String[] ListOfExGReferenceElectrodeAll = {"Fixed Potential", "Inverse of Ch1", "Inverse Wilson CT", "3-Ch Single-ended"};//,"Inputs Shorted"
    public static final Integer[] ListOfExGReferenceElectrodeConfigValuesAll = {0, 3, 13, 7};
    public static final String[] ListOfRespReferenceElectrode = {"Fixed Potential"};
    public static final Integer[] ListOfRespReferenceElectrodeConfigValues = {0};
    public static final String[] ListOfTestReferenceElectrode = {"Test Signal"};
    public static final Integer[] ListOfTestReferenceElectrodeConfigValues = {0};
    public static final String[] ListOfUnipolarReferenceElectrode = {"Fixed Potential", "Inverse of Ch1+Ch2"};
    public static final Integer[] ListOfUnipolarReferenceElectrodeConfigValues = {0, 5};
    public static final String[] ListOfExGLeadOffDetection = {"Off", "DC Current"};
    public static final Integer[] ListOfExGLeadOffDetectionConfigValues = {0, 1};
    public static final String[] ListOfExGLeadOffCurrent = {"6 nA", "22 nA", "6 uA", "22 uA"};
    public static final Integer[] ListOfExGLeadOffCurrentConfigValues = {0, 1, 2, 3};
    public static final String[] ListOfExGLeadOffComparator = {"Pos:95%-Neg:5%", "Pos:92.5%-Neg:7.5%", "Pos:90%-Neg:10%", "Pos:87.5%-Neg:12.5%", "Pos:85%-Neg:15%", "Pos:80%-Neg:20%", "Pos:75%-Neg:25%", "Pos:70%-Neg:30%"};
    public static final Integer[] ListOfExGLeadOffComparatorConfigValues = {0, 1, 2, 3, 4, 5, 6, 7};
    public static final String[] ListOfExGResolutions = {"16-bit", "24-bit"};
    public static final Integer[] ListOfExGResolutionsConfigValues = {0, 1};
    public static final String[] ListOfExGRespirationDetectFreq = {"32 kHz", "64 kHz"};
    public static final Integer[] ListOfExGRespirationDetectFreqConfigValues = {0, 1};
    public static final String[] ListOfExGRespirationDetectPhase32khz = {"0\u00B0", "11.25\u00B0", "22.5\u00B0", "33.75\u00B0", "45\u00B0", "56.25\u00B0", "67.5\u00B0", "78.75\u00B0", "90\u00B0", "101.25\u00B0", "112.5\u00B0", "123.75\u00B0", "135\u00B0", "146.25\u00B0", "157.5\u00B0", "168.75\u00B0"};
    public static final Integer[] ListOfExGRespirationDetectPhase32khzConfigValues = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15};
    public static final String[] ListOfExGRespirationDetectPhase64khz = {"0\u00B0", "22.5\u00B0", "45\u00B0", "67.5\u00B0", "90\u00B0", "112.5\u00B0", "135\u00B0", "157.5\u00B0"};
    public static final Integer[] ListOfExGRespirationDetectPhase64khzConfigValues = {0, 1, 2, 3, 4, 5, 6, 7};
    public static final String[] ListOfExGRate = {"125 Hz", "250 Hz", "500 Hz", "1 kHz", "2 kHz", "4 kHz", "8 kHz"};
    public static final Integer[] ListOfExGRateConfigValues = {0, 1, 2, 3, 4, 5, 6};
    public static final String BIT_16 = "_16BIT";
    public static final String BIT_24 = "_24BIT";
    public static final SensorDetailsRef sDRefEcg = new SensorDetailsRef(0, 0, GuiLabelSensors.ECG,
            CompatibilityInfoForMaps.listOfCompatibleVersionInfoExgEcg,
            Arrays.asList(
                    Configuration.Shimmer3.SENSOR_ID.SHIMMER_INT_EXP_ADC_A1,
                    Configuration.Shimmer3.SENSOR_ID.SHIMMER_INT_EXP_ADC_A12,
                    Configuration.Shimmer3.SENSOR_ID.SHIMMER_INT_EXP_ADC_A13,
                    Configuration.Shimmer3.SENSOR_ID.SHIMMER_INT_EXP_ADC_A14,
                    Configuration.Shimmer3.SENSOR_ID.SHIMMER_GSR,
                    Configuration.Shimmer3.SENSOR_ID.SHIMMER_RESISTANCE_AMP,
                    Configuration.Shimmer3.SENSOR_ID.SHIMMER_BRIDGE_AMP,
                    Configuration.Shimmer3.SENSOR_ID.HOST_EMG,
                    Configuration.Shimmer3.SENSOR_ID.HOST_EXG_TEST,
                    Configuration.Shimmer3.SENSOR_ID.HOST_EXG_CUSTOM,
                    Configuration.Shimmer3.SENSOR_ID.HOST_EXG_RESPIRATION,
                    Configuration.Shimmer3.SENSOR_ID.HOST_EXG_THREE_UNIPOLAR),
            Arrays.asList(
                    GuiLabelConfig.EXG_GAIN,
                    GuiLabelConfig.EXG_RESOLUTION,
                    GuiLabelConfig.EXG_REFERENCE_ELECTRODE,
                    GuiLabelConfig.EXG_LEAD_OFF_DETECTION,
                    GuiLabelConfig.EXG_LEAD_OFF_CURRENT,
                    GuiLabelConfig.EXG_LEAD_OFF_COMPARATOR,
                    GuiLabelConfig.EXG_RESPIRATION_DETECT_FREQ,
                    GuiLabelConfig.EXG_RESPIRATION_DETECT_PHASE),
            Arrays.asList(
                    SensorEXG.ObjectClusterSensorName.EXG1_STATUS,

                    SensorEXG.ObjectClusterSensorName.ECG_LL_RA_16BIT,
                    SensorEXG.ObjectClusterSensorName.ECG_LA_RA_16BIT,
                    SensorEXG.ObjectClusterSensorName.ECG_LL_LA_16BIT,
                    SensorEXG.ObjectClusterSensorName.ECG_VX_RL_16BIT,
                    SensorEXG.ObjectClusterSensorName.ECG_CHIP2_CH1_DUMMY_16BIT,

                    SensorEXG.ObjectClusterSensorName.EXG2_STATUS,

                    SensorEXG.ObjectClusterSensorName.ECG_LL_RA_24BIT,
                    SensorEXG.ObjectClusterSensorName.ECG_LA_RA_24BIT,
                    SensorEXG.ObjectClusterSensorName.ECG_LL_LA_24BIT,
                    SensorEXG.ObjectClusterSensorName.ECG_VX_RL_24BIT,
                    SensorEXG.ObjectClusterSensorName.ECG_CHIP2_CH1_DUMMY_24BIT
            ),
            true);


    public static final SensorDetailsRef sDRefEcgGq = new SensorDetailsRef(0, 0, GuiLabelSensors.ECG,
            CompatibilityInfoForMaps.listOfCompatibleVersionInfoExgEcg,
            null,
            Arrays.asList(
                    GuiLabelConfig.EXG_GAIN,
                    GuiLabelConfig.EXG_RESOLUTION,
                    GuiLabelConfig.EXG_REFERENCE_ELECTRODE,
                    GuiLabelConfig.EXG_LEAD_OFF_DETECTION,
                    GuiLabelConfig.EXG_LEAD_OFF_CURRENT,
                    GuiLabelConfig.EXG_LEAD_OFF_COMPARATOR,
                    GuiLabelConfig.EXG_RESPIRATION_DETECT_FREQ,
                    GuiLabelConfig.EXG_RESPIRATION_DETECT_PHASE),
            Arrays.asList(
                    SensorEXG.ObjectClusterSensorName.EXG1_STATUS,
                    SensorEXG.ObjectClusterSensorName.ECG_GQ,
                    SensorEXG.ObjectClusterSensorName.ECG_LA_RL_24BIT),
            true);
    public static final SensorDetailsRef sDRefExgRespiration = new SensorDetailsRef(0, 0, GuiLabelSensors.EXG_RESPIRATION,
            CompatibilityInfoForMaps.listOfCompatibleVersionInfoExgRespiration,
            Arrays.asList(
                    Configuration.Shimmer3.SENSOR_ID.SHIMMER_INT_EXP_ADC_A1,
                    Configuration.Shimmer3.SENSOR_ID.SHIMMER_INT_EXP_ADC_A12,
                    Configuration.Shimmer3.SENSOR_ID.SHIMMER_INT_EXP_ADC_A13,
                    Configuration.Shimmer3.SENSOR_ID.SHIMMER_INT_EXP_ADC_A14,
                    Configuration.Shimmer3.SENSOR_ID.SHIMMER_GSR,
                    Configuration.Shimmer3.SENSOR_ID.SHIMMER_RESISTANCE_AMP,
                    Configuration.Shimmer3.SENSOR_ID.SHIMMER_BRIDGE_AMP,
                    Configuration.Shimmer3.SENSOR_ID.HOST_ECG,
                    Configuration.Shimmer3.SENSOR_ID.HOST_EMG,
                    Configuration.Shimmer3.SENSOR_ID.HOST_EXG_CUSTOM,
                    Configuration.Shimmer3.SENSOR_ID.HOST_EXG_TEST,
                    Configuration.Shimmer3.SENSOR_ID.HOST_EXG_THREE_UNIPOLAR),
            Arrays.asList(
                    GuiLabelConfig.EXG_GAIN,
                    GuiLabelConfig.EXG_RESOLUTION,
                    GuiLabelConfig.EXG_REFERENCE_ELECTRODE,
                    GuiLabelConfig.EXG_RESPIRATION_DETECT_FREQ,
                    GuiLabelConfig.EXG_RESPIRATION_DETECT_PHASE),
            Arrays.asList(
                    SensorEXG.ObjectClusterSensorName.EXG1_STATUS,

                    SensorEXG.ObjectClusterSensorName.ECG_LL_RA_16BIT,
                    SensorEXG.ObjectClusterSensorName.ECG_LA_RA_16BIT,
                    SensorEXG.ObjectClusterSensorName.ECG_LL_LA_16BIT,
                    SensorEXG.ObjectClusterSensorName.ECG_VX_RL_16BIT,
                    SensorEXG.ObjectClusterSensorName.ECG_RESP_16BIT,

                    SensorEXG.ObjectClusterSensorName.EXG2_STATUS,

                    SensorEXG.ObjectClusterSensorName.ECG_LL_RA_24BIT,
                    SensorEXG.ObjectClusterSensorName.ECG_LA_RA_24BIT,
                    SensorEXG.ObjectClusterSensorName.ECG_LL_LA_24BIT,
                    SensorEXG.ObjectClusterSensorName.ECG_VX_RL_24BIT,
                    SensorEXG.ObjectClusterSensorName.ECG_RESP_24BIT),
            true);
    public static final SensorDetailsRef sDRefExgTest = new SensorDetailsRef(0, 0, GuiLabelSensors.EXG_TEST,
            CompatibilityInfoForMaps.listOfCompatibleVersionInfoExgTest,
            Arrays.asList(
                    Configuration.Shimmer3.SENSOR_ID.SHIMMER_INT_EXP_ADC_A1,
                    Configuration.Shimmer3.SENSOR_ID.SHIMMER_INT_EXP_ADC_A12,
                    Configuration.Shimmer3.SENSOR_ID.SHIMMER_INT_EXP_ADC_A13,
                    Configuration.Shimmer3.SENSOR_ID.SHIMMER_INT_EXP_ADC_A14,
                    Configuration.Shimmer3.SENSOR_ID.SHIMMER_GSR,
                    Configuration.Shimmer3.SENSOR_ID.SHIMMER_RESISTANCE_AMP,
                    Configuration.Shimmer3.SENSOR_ID.SHIMMER_BRIDGE_AMP,
                    Configuration.Shimmer3.SENSOR_ID.HOST_ECG,
                    Configuration.Shimmer3.SENSOR_ID.HOST_EMG,
                    Configuration.Shimmer3.SENSOR_ID.HOST_EXG_CUSTOM,
                    Configuration.Shimmer3.SENSOR_ID.HOST_EXG_RESPIRATION,
                    Configuration.Shimmer3.SENSOR_ID.HOST_EXG_THREE_UNIPOLAR),
            Arrays.asList(
                    GuiLabelConfig.EXG_GAIN,
                    GuiLabelConfig.EXG_RESOLUTION),
            Arrays.asList(
                    SensorEXG.ObjectClusterSensorName.EXG1_STATUS,
                    SensorEXG.ObjectClusterSensorName.EXG_TEST_CHIP1_CH1_16BIT,
                    SensorEXG.ObjectClusterSensorName.EXG_TEST_CHIP1_CH2_16BIT,
                    SensorEXG.ObjectClusterSensorName.EXG_TEST_CHIP1_CH1_24BIT,
                    SensorEXG.ObjectClusterSensorName.EXG_TEST_CHIP1_CH2_24BIT,

                    SensorEXG.ObjectClusterSensorName.EXG2_STATUS,
                    SensorEXG.ObjectClusterSensorName.EXG_TEST_CHIP2_CH1_24BIT,
                    SensorEXG.ObjectClusterSensorName.EXG_TEST_CHIP2_CH2_24BIT,
                    SensorEXG.ObjectClusterSensorName.EXG_TEST_CHIP2_CH1_16BIT,
                    SensorEXG.ObjectClusterSensorName.EXG_TEST_CHIP2_CH2_16BIT),
            true);
    public static final SensorDetailsRef sDRefEmg = new SensorDetailsRef(0, 0, GuiLabelSensors.EMG,
            CompatibilityInfoForMaps.listOfCompatibleVersionInfoExgEmg,
            Arrays.asList(
                    Configuration.Shimmer3.SENSOR_ID.SHIMMER_INT_EXP_ADC_A1,
                    Configuration.Shimmer3.SENSOR_ID.SHIMMER_INT_EXP_ADC_A12,
                    Configuration.Shimmer3.SENSOR_ID.SHIMMER_INT_EXP_ADC_A13,
                    Configuration.Shimmer3.SENSOR_ID.SHIMMER_INT_EXP_ADC_A14,
                    Configuration.Shimmer3.SENSOR_ID.SHIMMER_GSR,
                    Configuration.Shimmer3.SENSOR_ID.SHIMMER_RESISTANCE_AMP,
                    Configuration.Shimmer3.SENSOR_ID.SHIMMER_BRIDGE_AMP,
                    Configuration.Shimmer3.SENSOR_ID.HOST_ECG,
                    Configuration.Shimmer3.SENSOR_ID.HOST_EXG_TEST,
                    Configuration.Shimmer3.SENSOR_ID.HOST_EXG_CUSTOM,
                    Configuration.Shimmer3.SENSOR_ID.HOST_EXG_RESPIRATION,
                    Configuration.Shimmer3.SENSOR_ID.HOST_EXG_THREE_UNIPOLAR),
            Arrays.asList(
                    GuiLabelConfig.EXG_GAIN,
                    GuiLabelConfig.EXG_RESOLUTION,
                    GuiLabelConfig.EXG_REFERENCE_ELECTRODE,
                    GuiLabelConfig.EXG_LEAD_OFF_DETECTION,
                    GuiLabelConfig.EXG_LEAD_OFF_CURRENT,
                    GuiLabelConfig.EXG_LEAD_OFF_COMPARATOR),
            Arrays.asList(
                    ObjectClusterSensorName.EXG1_STATUS,
                    ObjectClusterSensorName.EMG_CH1_16BIT,
                    ObjectClusterSensorName.EMG_CH2_16BIT,
                    ObjectClusterSensorName.EMG_CH1_24BIT,
                    ObjectClusterSensorName.EMG_CH2_24BIT),
            true);
    public static final SensorDetailsRef sDRefExgCustom = new SensorDetailsRef(0, 0, GuiLabelSensors.EXG_CUSTOM,
            CompatibilityInfoForMaps.listOfCompatibleVersionInfoExgGeneral,
            Arrays.asList(
                    Configuration.Shimmer3.SENSOR_ID.SHIMMER_INT_EXP_ADC_A1,
                    Configuration.Shimmer3.SENSOR_ID.SHIMMER_INT_EXP_ADC_A12,
                    Configuration.Shimmer3.SENSOR_ID.SHIMMER_INT_EXP_ADC_A13,
                    Configuration.Shimmer3.SENSOR_ID.SHIMMER_INT_EXP_ADC_A14,
                    Configuration.Shimmer3.SENSOR_ID.SHIMMER_GSR,
                    Configuration.Shimmer3.SENSOR_ID.SHIMMER_RESISTANCE_AMP,
                    Configuration.Shimmer3.SENSOR_ID.SHIMMER_BRIDGE_AMP,
                    Configuration.Shimmer3.SENSOR_ID.HOST_ECG,
                    Configuration.Shimmer3.SENSOR_ID.HOST_EMG,
                    Configuration.Shimmer3.SENSOR_ID.HOST_EXG_TEST,
                    Configuration.Shimmer3.SENSOR_ID.HOST_EXG_RESPIRATION,
                    Configuration.Shimmer3.SENSOR_ID.HOST_EXG_THREE_UNIPOLAR),
            Arrays.asList(
                    GuiLabelConfig.EXG_GAIN,
                    GuiLabelConfig.EXG_RESOLUTION,
                    GuiLabelConfig.EXG_REFERENCE_ELECTRODE,
                    GuiLabelConfig.EXG_LEAD_OFF_DETECTION,
                    GuiLabelConfig.EXG_LEAD_OFF_CURRENT,
                    GuiLabelConfig.EXG_LEAD_OFF_COMPARATOR,
                    GuiLabelConfig.EXG_RESPIRATION_DETECT_FREQ,
                    GuiLabelConfig.EXG_RESPIRATION_DETECT_PHASE),
            Arrays.asList(
                    SensorEXG.ObjectClusterSensorName.EXG1_STATUS,
                    SensorEXG.ObjectClusterSensorName.EXG1_CH1_16BIT,
                    SensorEXG.ObjectClusterSensorName.EXG1_CH2_16BIT,
                    SensorEXG.ObjectClusterSensorName.EXG1_CH1_24BIT,
                    SensorEXG.ObjectClusterSensorName.EXG1_CH2_24BIT,

                    SensorEXG.ObjectClusterSensorName.EXG2_STATUS,
                    SensorEXG.ObjectClusterSensorName.EXG2_CH1_16BIT,
                    SensorEXG.ObjectClusterSensorName.EXG2_CH2_16BIT,
                    SensorEXG.ObjectClusterSensorName.EXG2_CH1_24BIT,
                    SensorEXG.ObjectClusterSensorName.EXG2_CH2_24BIT),
            true);
    public static final SensorDetailsRef sDRefExgThreeUnipolarInput = new SensorDetailsRef(0, 0, GuiLabelSensors.EXG_THREE_UNIPOLAR,
            CompatibilityInfoForMaps.listOfCompatibleVersionInfoExgThreeUnipolar,
            Arrays.asList(
                    Configuration.Shimmer3.SENSOR_ID.SHIMMER_INT_EXP_ADC_A1,
                    Configuration.Shimmer3.SENSOR_ID.SHIMMER_INT_EXP_ADC_A12,
                    Configuration.Shimmer3.SENSOR_ID.SHIMMER_INT_EXP_ADC_A13,
                    Configuration.Shimmer3.SENSOR_ID.SHIMMER_INT_EXP_ADC_A14,
                    Configuration.Shimmer3.SENSOR_ID.SHIMMER_GSR,
                    Configuration.Shimmer3.SENSOR_ID.SHIMMER_RESISTANCE_AMP,
                    Configuration.Shimmer3.SENSOR_ID.SHIMMER_BRIDGE_AMP,
                    Configuration.Shimmer3.SENSOR_ID.HOST_ECG,
                    Configuration.Shimmer3.SENSOR_ID.HOST_EMG,
                    Configuration.Shimmer3.SENSOR_ID.HOST_EXG_TEST,
                    Configuration.Shimmer3.SENSOR_ID.HOST_EXG_RESPIRATION,
                    Configuration.Shimmer3.SENSOR_ID.HOST_EXG_CUSTOM),
            Arrays.asList(
                    GuiLabelConfig.EXG_GAIN,
                    GuiLabelConfig.EXG_RESOLUTION,
                    GuiLabelConfig.EXG_REFERENCE_ELECTRODE,
                    GuiLabelConfig.EXG_LEAD_OFF_DETECTION,
                    GuiLabelConfig.EXG_LEAD_OFF_CURRENT,
                    GuiLabelConfig.EXG_LEAD_OFF_COMPARATOR,
                    GuiLabelConfig.EXG_RESPIRATION_DETECT_FREQ,
                    GuiLabelConfig.EXG_RESPIRATION_DETECT_PHASE),
            Arrays.asList(
                    SensorEXG.ObjectClusterSensorName.EXG1_STATUS,
                    SensorEXG.ObjectClusterSensorName.EXG1_CH1_16BIT,
                    SensorEXG.ObjectClusterSensorName.EXG1_CH2_16BIT,
                    SensorEXG.ObjectClusterSensorName.EXG1_CH1_24BIT,
                    SensorEXG.ObjectClusterSensorName.EXG1_CH2_24BIT,

                    SensorEXG.ObjectClusterSensorName.EXG2_STATUS,
                    SensorEXG.ObjectClusterSensorName.EXG2_CH2_16BIT,
                    SensorEXG.ObjectClusterSensorName.EXG2_CH2_24BIT),
            true);
    public static final Map<Integer, SensorDetailsRef> mSensorMapRef;
    public static final ChannelDetails cDExg1Status = new ChannelDetails(
            ObjectClusterSensorName.EXG1_STATUS,
            ObjectClusterSensorName.EXG1_STATUS,
            DatabaseChannelHandles.EXG1_STATUS,
            CHANNEL_DATA_TYPE.UINT8, 1, CHANNEL_DATA_ENDIAN.LSB,
            CHANNEL_UNITS.NO_UNITS,
            Arrays.asList(CHANNEL_TYPE.CAL, CHANNEL_TYPE.UNCAL),
            0x1D);
    public static final ChannelDetails cDExg2Status = new ChannelDetails(
            ObjectClusterSensorName.EXG2_STATUS,
            ObjectClusterSensorName.EXG2_STATUS,
            DatabaseChannelHandles.EXG2_STATUS,
            CHANNEL_DATA_TYPE.UINT8, 1, CHANNEL_DATA_ENDIAN.LSB,
            CHANNEL_UNITS.NO_UNITS,
            Arrays.asList(CHANNEL_TYPE.CAL, CHANNEL_TYPE.UNCAL),
            0x20);
    public static final ChannelDetails cDExg1Ch1_24bit = new ChannelDetails(
            ObjectClusterSensorName.EXG1_CH1_24BIT,
            ObjectClusterSensorName.EXG1_CH1_24BIT,
            DatabaseChannelHandles.EXG1_CH1_24BITS,
            CHANNEL_DATA_TYPE.INT24, 3, CHANNEL_DATA_ENDIAN.MSB,
            CHANNEL_UNITS.MILLIVOLTS,
            Arrays.asList(CHANNEL_TYPE.CAL, CHANNEL_TYPE.UNCAL),
            0x1E);
    public static final ChannelDetails cDExg1Ch2_24bit = new ChannelDetails(
            ObjectClusterSensorName.EXG1_CH2_24BIT,
            ObjectClusterSensorName.EXG1_CH2_24BIT,
            DatabaseChannelHandles.EXG1_CH2_24BITS,
            CHANNEL_DATA_TYPE.INT24, 3, CHANNEL_DATA_ENDIAN.MSB,
            CHANNEL_UNITS.MILLIVOLTS,
            Arrays.asList(CHANNEL_TYPE.CAL, CHANNEL_TYPE.UNCAL),
            0x1F);
    public static final ChannelDetails cDExg2Ch1_24bit = new ChannelDetails(
            SensorEXG.ObjectClusterSensorName.EXG2_CH1_24BIT,
            SensorEXG.ObjectClusterSensorName.EXG2_CH1_24BIT,
            SensorEXG.DatabaseChannelHandles.EXG2_CH1_24BITS,
            CHANNEL_DATA_TYPE.INT24, 3, CHANNEL_DATA_ENDIAN.MSB,
            CHANNEL_UNITS.MILLIVOLTS,
            Arrays.asList(CHANNEL_TYPE.CAL, CHANNEL_TYPE.UNCAL),
            0x21);
    public static final ChannelDetails cDExg2Ch2_24bit = new ChannelDetails(
            SensorEXG.ObjectClusterSensorName.EXG2_CH2_24BIT,
            SensorEXG.ObjectClusterSensorName.EXG2_CH2_24BIT,
            SensorEXG.DatabaseChannelHandles.EXG2_CH2_24BITS,
            CHANNEL_DATA_TYPE.INT24, 3, CHANNEL_DATA_ENDIAN.MSB,
            CHANNEL_UNITS.MILLIVOLTS,
            Arrays.asList(CHANNEL_TYPE.CAL, CHANNEL_TYPE.UNCAL),
            0x22);
    public static final ChannelDetails cDExg1Ch1_16bit = new ChannelDetails(
            SensorEXG.ObjectClusterSensorName.EXG1_CH1_16BIT,
            SensorEXG.ObjectClusterSensorName.EXG1_CH1_16BIT,
            DatabaseChannelHandles.EXG1_CH1_16BITS,
            CHANNEL_DATA_TYPE.INT16, 2, CHANNEL_DATA_ENDIAN.MSB,
            CHANNEL_UNITS.MILLIVOLTS,
            Arrays.asList(CHANNEL_TYPE.CAL, CHANNEL_TYPE.UNCAL),
            0x23);
    public static final ChannelDetails cDExg1Ch2_16bit = new ChannelDetails(
            ObjectClusterSensorName.EXG1_CH2_16BIT,
            ObjectClusterSensorName.EXG1_CH2_16BIT,
            DatabaseChannelHandles.EXG1_CH2_16BITS,
            CHANNEL_DATA_TYPE.INT16, 2, CHANNEL_DATA_ENDIAN.MSB,
            CHANNEL_UNITS.MILLIVOLTS,
            Arrays.asList(CHANNEL_TYPE.CAL, CHANNEL_TYPE.UNCAL),
            0x24);
    public static final ChannelDetails cDExg2Ch1_16bit = new ChannelDetails(
            ObjectClusterSensorName.EXG2_CH1_16BIT,
            ObjectClusterSensorName.EXG2_CH1_16BIT,
            DatabaseChannelHandles.EXG2_CH1_16BITS,
            CHANNEL_DATA_TYPE.INT16, 2, CHANNEL_DATA_ENDIAN.MSB,
            CHANNEL_UNITS.MILLIVOLTS,
            Arrays.asList(CHANNEL_TYPE.CAL, CHANNEL_TYPE.UNCAL),
            0x25);
    public static final ChannelDetails cDExg2Ch2_16bit = new ChannelDetails(
            ObjectClusterSensorName.EXG2_CH2_16BIT,
            ObjectClusterSensorName.EXG2_CH2_16BIT,
            DatabaseChannelHandles.EXG2_CH2_16BITS,
            CHANNEL_DATA_TYPE.INT16, 2, CHANNEL_DATA_ENDIAN.MSB,
            CHANNEL_UNITS.MILLIVOLTS,
            Arrays.asList(CHANNEL_TYPE.CAL, CHANNEL_TYPE.UNCAL),
            0x26);
    public static final ChannelDetails cDEmgCh1_16bit = new ChannelDetails(
            ObjectClusterSensorName.EMG_CH1_16BIT,
            ObjectClusterSensorName.EMG_CH1_16BIT,
            DatabaseChannelHandles.EXG1_CH1_16BITS,
            CHANNEL_DATA_TYPE.INT16, 2, CHANNEL_DATA_ENDIAN.MSB,
            CHANNEL_UNITS.MILLIVOLTS,
            Arrays.asList(CHANNEL_TYPE.CAL, CHANNEL_TYPE.UNCAL));
    public static final ChannelDetails cDEmgCh2_16bit = new ChannelDetails(
            ObjectClusterSensorName.EMG_CH2_16BIT,
            ObjectClusterSensorName.EMG_CH2_16BIT,
            DatabaseChannelHandles.EXG1_CH2_16BITS,
            CHANNEL_DATA_TYPE.INT16, 2, CHANNEL_DATA_ENDIAN.MSB,
            CHANNEL_UNITS.MILLIVOLTS,
            Arrays.asList(CHANNEL_TYPE.CAL, CHANNEL_TYPE.UNCAL));
    public static final ChannelDetails cDEmgCh1_24bit = new ChannelDetails(
            ObjectClusterSensorName.EMG_CH1_24BIT,
            ObjectClusterSensorName.EMG_CH1_24BIT,
            DatabaseChannelHandles.EXG1_CH1_24BITS,
            CHANNEL_DATA_TYPE.INT24, 3, CHANNEL_DATA_ENDIAN.MSB,
            CHANNEL_UNITS.MILLIVOLTS,
            Arrays.asList(CHANNEL_TYPE.CAL, CHANNEL_TYPE.UNCAL));
    public static final ChannelDetails cDEmgCh2_24bit = new ChannelDetails(
            ObjectClusterSensorName.EMG_CH2_24BIT,
            ObjectClusterSensorName.EMG_CH2_24BIT,
            DatabaseChannelHandles.EXG1_CH2_24BITS,
            CHANNEL_DATA_TYPE.INT24, 3, CHANNEL_DATA_ENDIAN.MSB,
            CHANNEL_UNITS.MILLIVOLTS,
            Arrays.asList(CHANNEL_TYPE.CAL, CHANNEL_TYPE.UNCAL));
    public static final ChannelDetails cDEcg_LL_RA_16bit = new ChannelDetails(
            ObjectClusterSensorName.ECG_LL_RA_16BIT,
            ObjectClusterSensorName.ECG_LL_RA_16BIT,
            DatabaseChannelHandles.EXG1_CH1_16BITS,
            CHANNEL_DATA_TYPE.INT16, 2, CHANNEL_DATA_ENDIAN.MSB,
            CHANNEL_UNITS.MILLIVOLTS,
            Arrays.asList(CHANNEL_TYPE.CAL, CHANNEL_TYPE.UNCAL));
    public static final ChannelDetails cDEcg_LA_RA_16bit = new ChannelDetails(
            ObjectClusterSensorName.ECG_LA_RA_16BIT,
            ObjectClusterSensorName.ECG_LA_RA_16BIT,
            DatabaseChannelHandles.EXG1_CH2_16BITS,
            CHANNEL_DATA_TYPE.INT16, 2, CHANNEL_DATA_ENDIAN.MSB,
            CHANNEL_UNITS.MILLIVOLTS,
            Arrays.asList(CHANNEL_TYPE.CAL, CHANNEL_TYPE.UNCAL));
    public static final ChannelDetails cDEcg_VX_RL_16bit = new ChannelDetails(
            ObjectClusterSensorName.ECG_VX_RL_16BIT,
            ObjectClusterSensorName.ECG_VX_RL_16BIT,
            DatabaseChannelHandles.EXG2_CH2_16BITS,
            CHANNEL_DATA_TYPE.INT16, 2, CHANNEL_DATA_ENDIAN.MSB,
            CHANNEL_UNITS.MILLIVOLTS,
            Arrays.asList(CHANNEL_TYPE.CAL, CHANNEL_TYPE.UNCAL));
    public static final ChannelDetails cDEcg_RESP_16bit = new ChannelDetails(
            ObjectClusterSensorName.ECG_RESP_16BIT,
            ObjectClusterSensorName.ECG_RESP_16BIT,
            DatabaseChannelHandles.EXG2_CH1_16BITS,
            CHANNEL_DATA_TYPE.INT16, 2, CHANNEL_DATA_ENDIAN.MSB,
            CHANNEL_UNITS.MILLIVOLTS,
            Arrays.asList(CHANNEL_TYPE.CAL, CHANNEL_TYPE.UNCAL));
    public static final ChannelDetails cDEcg_CHIP2_CH1_DUMMY_16BIT = new ChannelDetails(
            ObjectClusterSensorName.ECG_CHIP2_CH1_DUMMY_16BIT,
            ObjectClusterSensorName.ECG_CHIP2_CH1_DUMMY_16BIT,
            DatabaseChannelHandles.EXG2_CH1_16BITS,
            CHANNEL_DATA_TYPE.INT16, 2, CHANNEL_DATA_ENDIAN.MSB,
            CHANNEL_UNITS.MILLIVOLTS,
            Arrays.asList(CHANNEL_TYPE.CAL, CHANNEL_TYPE.UNCAL),
            false, false);
    public static final ChannelDetails cDEcg_LL_RA_24bit = new ChannelDetails(
            ObjectClusterSensorName.ECG_LL_RA_24BIT,
            ObjectClusterSensorName.ECG_LL_RA_24BIT,
            DatabaseChannelHandles.EXG1_CH1_24BITS,
            CHANNEL_DATA_TYPE.INT24, 3, CHANNEL_DATA_ENDIAN.MSB,
            CHANNEL_UNITS.MILLIVOLTS,
            Arrays.asList(CHANNEL_TYPE.CAL, CHANNEL_TYPE.UNCAL));
    public static final ChannelDetails cDEcg_LA_RA_24bit = new ChannelDetails(
            ObjectClusterSensorName.ECG_LA_RA_24BIT,
            ObjectClusterSensorName.ECG_LA_RA_24BIT,
            DatabaseChannelHandles.EXG1_CH2_24BITS,
            CHANNEL_DATA_TYPE.INT24, 3, CHANNEL_DATA_ENDIAN.MSB,
            CHANNEL_UNITS.MILLIVOLTS,
            Arrays.asList(CHANNEL_TYPE.CAL, CHANNEL_TYPE.UNCAL));
    public static final ChannelDetails cDEcg_VX_RL_24bit = new ChannelDetails(
            ObjectClusterSensorName.ECG_VX_RL_24BIT,
            ObjectClusterSensorName.ECG_VX_RL_24BIT,
            DatabaseChannelHandles.EXG2_CH2_24BITS,
            CHANNEL_DATA_TYPE.INT24, 3, CHANNEL_DATA_ENDIAN.MSB,
            CHANNEL_UNITS.MILLIVOLTS,
            Arrays.asList(CHANNEL_TYPE.CAL, CHANNEL_TYPE.UNCAL));
    public static final ChannelDetails cDEcg_RESP_24bit = new ChannelDetails(
            ObjectClusterSensorName.ECG_RESP_24BIT,
            ObjectClusterSensorName.ECG_RESP_24BIT,
            DatabaseChannelHandles.EXG2_CH1_24BITS,
            CHANNEL_DATA_TYPE.INT24, 3, CHANNEL_DATA_ENDIAN.MSB,
            CHANNEL_UNITS.MILLIVOLTS,
            Arrays.asList(CHANNEL_TYPE.CAL, CHANNEL_TYPE.UNCAL));
    public static final ChannelDetails cDEcg_CHIP2_CH1_DUMMY_24BIT = new ChannelDetails(
            ObjectClusterSensorName.ECG_CHIP2_CH1_DUMMY_24BIT,
            ObjectClusterSensorName.ECG_CHIP2_CH1_DUMMY_24BIT,
            DatabaseChannelHandles.EXG2_CH1_24BITS,
            CHANNEL_DATA_TYPE.INT16, 3, CHANNEL_DATA_ENDIAN.MSB,
            CHANNEL_UNITS.MILLIVOLTS,
            Arrays.asList(CHANNEL_TYPE.CAL, CHANNEL_TYPE.UNCAL),
            false, false);

    public static final ChannelDetails cDEcg_LL_LA_16bit = new ChannelDetails(
            ObjectClusterSensorName.ECG_LL_LA_16BIT,
            ObjectClusterSensorName.ECG_LL_LA_16BIT,
            DatabaseChannelHandles.ECG_LL_LA_16BITS,
            CHANNEL_UNITS.MILLIVOLTS,
            Arrays.asList(CHANNEL_TYPE.CAL));
    public static final ChannelDetails cDEcg_LL_LA_24bit = new ChannelDetails(
            ObjectClusterSensorName.ECG_LL_LA_24BIT,
            ObjectClusterSensorName.ECG_LL_LA_24BIT,
            DatabaseChannelHandles.ECG_LL_LA_24BITS,
            CHANNEL_UNITS.MILLIVOLTS,
            Arrays.asList(CHANNEL_TYPE.CAL));
    public static final ChannelDetails cDEcg_LA_RA_24bit_GQ = new ChannelDetails(
            SensorEXG.ObjectClusterSensorName.ECG_GQ,
            SensorEXG.ObjectClusterSensorName.ECG_GQ,
            SensorEXG.DatabaseChannelHandles.EXG1_CH1_24BITS,
            CHANNEL_DATA_TYPE.INT24, 3, CHANNEL_DATA_ENDIAN.MSB,
            CHANNEL_UNITS.MILLIVOLTS,
            Arrays.asList(CHANNEL_TYPE.CAL, CHANNEL_TYPE.UNCAL));
    public static final ChannelDetails cDEcg_LA_RL_24bit_GQ = new ChannelDetails(
            SensorEXG.ObjectClusterSensorName.ECG_LA_RL_24BIT,
            SensorEXG.ObjectClusterSensorName.ECG_LA_RL_24BIT,
            SensorEXG.DatabaseChannelHandles.EXG1_CH2_24BITS,
            CHANNEL_DATA_TYPE.INT24, 3, CHANNEL_DATA_ENDIAN.MSB,
            CHANNEL_UNITS.MILLIVOLTS,
            Arrays.asList(CHANNEL_TYPE.CAL, CHANNEL_TYPE.UNCAL));
    public static final ChannelDetails cDExg_Test_CHIP1_CH1_16bit = new ChannelDetails(
            ObjectClusterSensorName.EXG_TEST_CHIP1_CH1_16BIT,
            ObjectClusterSensorName.EXG_TEST_CHIP1_CH1_16BIT,
            DatabaseChannelHandles.EXG1_CH1_16BITS,
            CHANNEL_DATA_TYPE.INT16, 2, CHANNEL_DATA_ENDIAN.MSB,
            CHANNEL_UNITS.MILLIVOLTS,
            Arrays.asList(CHANNEL_TYPE.CAL, CHANNEL_TYPE.UNCAL));
    public static final ChannelDetails cDExg_Test_CHIP1_CH2_16bit = new ChannelDetails(
            ObjectClusterSensorName.EXG_TEST_CHIP1_CH2_16BIT,
            ObjectClusterSensorName.EXG_TEST_CHIP1_CH2_16BIT,
            DatabaseChannelHandles.EXG1_CH2_16BITS,
            CHANNEL_DATA_TYPE.INT16, 2, CHANNEL_DATA_ENDIAN.MSB,
            CHANNEL_UNITS.MILLIVOLTS,
            Arrays.asList(CHANNEL_TYPE.CAL, CHANNEL_TYPE.UNCAL));
    public static final ChannelDetails cDExg_Test_CHIP2_CH1_16bit = new ChannelDetails(
            ObjectClusterSensorName.EXG_TEST_CHIP2_CH1_16BIT,
            ObjectClusterSensorName.EXG_TEST_CHIP2_CH1_16BIT,
            DatabaseChannelHandles.EXG2_CH1_16BITS,
            CHANNEL_DATA_TYPE.INT16, 2, CHANNEL_DATA_ENDIAN.MSB,
            CHANNEL_UNITS.MILLIVOLTS,
            Arrays.asList(CHANNEL_TYPE.CAL, CHANNEL_TYPE.UNCAL));
    public static final ChannelDetails cDExg_Test_CHIP2_CH2_16bit = new ChannelDetails(
            ObjectClusterSensorName.EXG_TEST_CHIP2_CH2_16BIT,
            ObjectClusterSensorName.EXG_TEST_CHIP2_CH2_16BIT,
            DatabaseChannelHandles.EXG2_CH2_16BITS,
            CHANNEL_DATA_TYPE.INT16, 2, CHANNEL_DATA_ENDIAN.MSB,
            CHANNEL_UNITS.MILLIVOLTS,
            Arrays.asList(CHANNEL_TYPE.CAL, CHANNEL_TYPE.UNCAL));
    public static final ChannelDetails cDExg_Test_CHIP1_CH1_24bit = new ChannelDetails(
            ObjectClusterSensorName.EXG_TEST_CHIP1_CH1_24BIT,
            ObjectClusterSensorName.EXG_TEST_CHIP1_CH1_24BIT,
            DatabaseChannelHandles.EXG1_CH1_24BITS,
            CHANNEL_DATA_TYPE.INT24, 3, CHANNEL_DATA_ENDIAN.MSB,
            CHANNEL_UNITS.MILLIVOLTS,
            Arrays.asList(CHANNEL_TYPE.CAL, CHANNEL_TYPE.UNCAL));
    public static final ChannelDetails cDExg_Test_CHIP1_CH2_24bit = new ChannelDetails(
            ObjectClusterSensorName.EXG_TEST_CHIP1_CH2_24BIT,
            ObjectClusterSensorName.EXG_TEST_CHIP1_CH2_24BIT,
            DatabaseChannelHandles.EXG1_CH2_24BITS,
            CHANNEL_DATA_TYPE.INT24, 3, CHANNEL_DATA_ENDIAN.MSB,
            CHANNEL_UNITS.MILLIVOLTS,
            Arrays.asList(CHANNEL_TYPE.CAL, CHANNEL_TYPE.UNCAL));
    public static final ChannelDetails cDExg_Test_CHIP2_CH1_24bit = new ChannelDetails(
            ObjectClusterSensorName.EXG_TEST_CHIP2_CH1_24BIT,
            ObjectClusterSensorName.EXG_TEST_CHIP2_CH1_24BIT,
            DatabaseChannelHandles.EXG2_CH1_24BITS,
            CHANNEL_DATA_TYPE.INT24, 3, CHANNEL_DATA_ENDIAN.MSB,
            CHANNEL_UNITS.MILLIVOLTS,
            Arrays.asList(CHANNEL_TYPE.CAL, CHANNEL_TYPE.UNCAL));
    public static final ChannelDetails cDExg_Test_CHIP2_CH2_24bit = new ChannelDetails(
            ObjectClusterSensorName.EXG_TEST_CHIP2_CH2_24BIT,
            ObjectClusterSensorName.EXG_TEST_CHIP2_CH2_24BIT,
            DatabaseChannelHandles.EXG2_CH2_24BITS,
            CHANNEL_DATA_TYPE.INT24, 3, CHANNEL_DATA_ENDIAN.MSB,
            CHANNEL_UNITS.MILLIVOLTS,
            Arrays.asList(CHANNEL_TYPE.CAL, CHANNEL_TYPE.UNCAL));
    public static final Map<String, ChannelDetails> mChannelMapRef;
    public static final Map<String, ChannelDetails> mChannelMapRefGq;
    public static final ConfigOptionDetailsSensor configOptionExgGain = new ConfigOptionDetailsSensor(
            SensorEXG.GuiLabelConfig.EXG_GAIN,
            null,
            ListOfExGGain,
            ListOfExGGainConfigValues,
            ConfigOptionDetailsSensor.GUI_COMPONENT_TYPE.COMBOBOX,
            CompatibilityInfoForMaps.listOfCompatibleVersionInfoExgGeneral);
    public static final ConfigOptionDetailsSensor configOptionExgResolution = new ConfigOptionDetailsSensor(
            SensorEXG.GuiLabelConfig.EXG_RESOLUTION,
            null,
            ListOfExGResolutions,
            ListOfExGResolutionsConfigValues,
            ConfigOptionDetailsSensor.GUI_COMPONENT_TYPE.COMBOBOX,
            CompatibilityInfoForMaps.listOfCompatibleVersionInfoExgGeneral);
    public static final ConfigOptionDetailsSensor configOptionExgRefElectrode = new ConfigOptionDetailsSensor(
            SensorEXG.GuiLabelConfig.EXG_REFERENCE_ELECTRODE,
            null,
            ListOfECGReferenceElectrode,
            ListOfECGReferenceElectrodeConfigValues,
            ConfigOptionDetailsSensor.GUI_COMPONENT_TYPE.COMBOBOX,
            CompatibilityInfoForMaps.listOfCompatibleVersionInfoExgGeneral,
            Arrays.asList(
                    new ConfigOptionObject(ConfigOptionDetailsSensor.VALUE_INDEXES.EXG_REFERENCE_ELECTRODE.EMG, ListOfEMGReferenceElectrode, ListOfEMGReferenceElectrodeConfigValues),
                    new ConfigOptionObject(ConfigOptionDetailsSensor.VALUE_INDEXES.EXG_REFERENCE_ELECTRODE.CUSTOM, ListOfExGReferenceElectrodeAll, ListOfExGReferenceElectrodeConfigValuesAll),
                    new ConfigOptionObject(ConfigOptionDetailsSensor.VALUE_INDEXES.EXG_REFERENCE_ELECTRODE.RESP, ListOfRespReferenceElectrode, ListOfRespReferenceElectrodeConfigValues),
                    new ConfigOptionObject(ConfigOptionDetailsSensor.VALUE_INDEXES.EXG_REFERENCE_ELECTRODE.TEST, ListOfTestReferenceElectrode, ListOfTestReferenceElectrodeConfigValues),
                    new ConfigOptionObject(ConfigOptionDetailsSensor.VALUE_INDEXES.EXG_REFERENCE_ELECTRODE.UNIPOLAR, ListOfUnipolarReferenceElectrode, ListOfUnipolarReferenceElectrodeConfigValues)));
    public static final ConfigOptionDetailsSensor configOptionExgBytes = new ConfigOptionDetailsSensor(
            SensorEXG.GuiLabelConfig.EXG_BYTES,
            null,
            ConfigOptionDetailsSensor.GUI_COMPONENT_TYPE.JPANEL,
            CompatibilityInfoForMaps.listOfCompatibleVersionInfoExgGeneral);
    public static final ConfigOptionDetailsSensor configOptionExgRate = new ConfigOptionDetailsSensor(
            SensorEXG.GuiLabelConfig.EXG_RATE,
            null,
            ListOfExGRate,
            ListOfExGRateConfigValues,
            ConfigOptionDetailsSensor.GUI_COMPONENT_TYPE.COMBOBOX,
            CompatibilityInfoForMaps.listOfCompatibleVersionInfoExgGeneral);
    public static final ConfigOptionDetailsSensor configOptionExgLeadOffDetection = new ConfigOptionDetailsSensor(
            SensorEXG.GuiLabelConfig.EXG_LEAD_OFF_DETECTION,
            null,
            ListOfExGLeadOffDetection,
            ListOfExGLeadOffDetectionConfigValues,
            ConfigOptionDetailsSensor.GUI_COMPONENT_TYPE.COMBOBOX,
            CompatibilityInfoForMaps.listOfCompatibleVersionInfoExgGeneral);
    public static final ConfigOptionDetailsSensor configOptionExgLeadOffCurrent = new ConfigOptionDetailsSensor(
            SensorEXG.GuiLabelConfig.EXG_LEAD_OFF_CURRENT,
            null,
            ListOfExGLeadOffCurrent,
            ListOfExGLeadOffCurrentConfigValues,
            ConfigOptionDetailsSensor.GUI_COMPONENT_TYPE.COMBOBOX,
            CompatibilityInfoForMaps.listOfCompatibleVersionInfoExgGeneral);
    public static final ConfigOptionDetailsSensor configOptionExgLeadOffComparator = new ConfigOptionDetailsSensor(
            SensorEXG.GuiLabelConfig.EXG_LEAD_OFF_COMPARATOR,
            null,
            ListOfExGLeadOffComparator,
            ListOfExGLeadOffComparatorConfigValues,
            ConfigOptionDetailsSensor.GUI_COMPONENT_TYPE.COMBOBOX,
            CompatibilityInfoForMaps.listOfCompatibleVersionInfoExgGeneral);
    public static final ConfigOptionDetailsSensor configOptionExgRespirationDetectFreq = new ConfigOptionDetailsSensor(
            SensorEXG.GuiLabelConfig.EXG_RESPIRATION_DETECT_FREQ,
            null,
            ListOfExGRespirationDetectFreq,
            ListOfExGRespirationDetectFreqConfigValues,
            ConfigOptionDetailsSensor.GUI_COMPONENT_TYPE.COMBOBOX,
            CompatibilityInfoForMaps.listOfCompatibleVersionInfoExgRespiration);
    public static final ConfigOptionDetailsSensor configOptionExgRespirationDetectPhase = new ConfigOptionDetailsSensor(
            SensorEXG.GuiLabelConfig.EXG_RESPIRATION_DETECT_PHASE,
            null,
            ListOfExGRespirationDetectPhase32khz,
            ListOfExGRespirationDetectPhase32khzConfigValues,
            ConfigOptionDetailsSensor.GUI_COMPONENT_TYPE.COMBOBOX,
            CompatibilityInfoForMaps.listOfCompatibleVersionInfoExgRespiration,
            Arrays.asList(
                    new ConfigOptionObject(ConfigOptionDetailsSensor.VALUE_INDEXES.EXG_RESPIRATION_DETECT_PHASE.PHASE_64KHZ, ListOfExGRespirationDetectPhase64khz, ListOfExGRespirationDetectPhase64khzConfigValues)));
        private static final long serialVersionUID = -9150699518448307506L;
    private static List<String> listOfChannels_Chip1Ch1_24Bit = Arrays.asList(
            SensorEXG.ObjectClusterSensorName.ECG_LL_RA_24BIT,
            SensorEXG.ObjectClusterSensorName.EMG_CH1_24BIT,
            SensorEXG.ObjectClusterSensorName.EXG_TEST_CHIP1_CH1_24BIT,
            SensorEXG.ObjectClusterSensorName.EXG1_CH1_24BIT);
    private static List<String> listOfChannels_Chip1Ch1_16Bit = Arrays.asList(
            SensorEXG.ObjectClusterSensorName.ECG_LL_RA_16BIT,
            SensorEXG.ObjectClusterSensorName.EMG_CH1_16BIT,
            SensorEXG.ObjectClusterSensorName.EXG_TEST_CHIP1_CH1_16BIT,
            SensorEXG.ObjectClusterSensorName.EXG1_CH1_16BIT);

    private static List<String> listOfChannels_Chip1Ch2_24Bit = Arrays.asList(
            SensorEXG.ObjectClusterSensorName.ECG_LA_RA_24BIT,
            SensorEXG.ObjectClusterSensorName.EMG_CH2_24BIT,
            SensorEXG.ObjectClusterSensorName.EXG_TEST_CHIP1_CH2_24BIT,
            SensorEXG.ObjectClusterSensorName.EXG1_CH2_24BIT);
    private static List<String> listOfChannels_Chip1Ch2_16Bit = Arrays.asList(
            SensorEXG.ObjectClusterSensorName.ECG_LA_RA_16BIT,
            SensorEXG.ObjectClusterSensorName.EMG_CH2_16BIT,
            SensorEXG.ObjectClusterSensorName.EXG_TEST_CHIP1_CH2_16BIT,
            SensorEXG.ObjectClusterSensorName.EXG1_CH2_16BIT);
    private static List<String> listOfChannels_Chip2Ch1_24Bit = Arrays.asList(
            SensorEXG.ObjectClusterSensorName.ECG_RESP_24BIT,
            SensorEXG.ObjectClusterSensorName.ECG_CHIP2_CH1_DUMMY_24BIT,
            SensorEXG.ObjectClusterSensorName.EXG_TEST_CHIP2_CH1_24BIT,
            SensorEXG.ObjectClusterSensorName.EXG2_CH1_24BIT);
    private static List<String> listOfChannels_Chip2Ch1_16Bit = Arrays.asList(
            SensorEXG.ObjectClusterSensorName.ECG_RESP_16BIT,
            SensorEXG.ObjectClusterSensorName.ECG_CHIP2_CH1_DUMMY_16BIT,
            SensorEXG.ObjectClusterSensorName.EXG_TEST_CHIP2_CH1_16BIT,
            SensorEXG.ObjectClusterSensorName.EXG2_CH1_16BIT);
    private static List<String> listOfChannels_Chip2Ch2_24Bit = Arrays.asList(
            SensorEXG.ObjectClusterSensorName.ECG_VX_RL_24BIT,
            SensorEXG.ObjectClusterSensorName.EXG_TEST_CHIP2_CH2_24BIT,
            SensorEXG.ObjectClusterSensorName.EXG2_CH2_24BIT);
    private static List<String> listOfChannels_Chip2Ch2_16Bit = Arrays.asList(
            SensorEXG.ObjectClusterSensorName.ECG_VX_RL_16BIT,
            SensorEXG.ObjectClusterSensorName.EXG_TEST_CHIP2_CH2_16BIT,
            SensorEXG.ObjectClusterSensorName.EXG2_CH2_16BIT);
    private static List<String> listOfChannels_Derived_24Bit = Arrays.asList(
            SensorEXG.ObjectClusterSensorName.ECG_LL_LA_24BIT);
    private static List<String> listOfChannels_Derived_16Bit = Arrays.asList(
            SensorEXG.ObjectClusterSensorName.ECG_LL_LA_16BIT);

    static {
        Map<Integer, SensorDetailsRef> aMap = new LinkedHashMap<Integer, SensorDetailsRef>();
        aMap.put(Configuration.Shimmer3.SENSOR_ID.HOST_ECG, SensorEXG.sDRefEcg);
        aMap.put(Configuration.Shimmer3.SENSOR_ID.HOST_EXG_TEST, SensorEXG.sDRefExgTest);
        aMap.put(Configuration.Shimmer3.SENSOR_ID.HOST_EXG_RESPIRATION, SensorEXG.sDRefExgRespiration);
        aMap.put(Configuration.Shimmer3.SENSOR_ID.HOST_EMG, SensorEXG.sDRefEmg);
        aMap.put(Configuration.Shimmer3.SENSOR_ID.HOST_EXG_CUSTOM, SensorEXG.sDRefExgCustom);
        aMap.put(Configuration.Shimmer3.SENSOR_ID.HOST_EXG_THREE_UNIPOLAR, SensorEXG.sDRefExgThreeUnipolarInput);
        mSensorMapRef = Collections.unmodifiableMap(aMap);
    }

    static {
        Map<String, ChannelDetails> aMap = new LinkedHashMap<String, ChannelDetails>();
        aMap.put(ObjectClusterSensorName.EXG1_STATUS, SensorEXG.cDExg1Status);
        aMap.put(ObjectClusterSensorName.EXG2_STATUS, SensorEXG.cDExg2Status);

        aMap.put(ObjectClusterSensorName.EXG1_CH1_24BIT, SensorEXG.cDExg1Ch1_24bit);
        aMap.put(ObjectClusterSensorName.EXG1_CH2_24BIT, SensorEXG.cDExg1Ch2_24bit);

        aMap.put(ObjectClusterSensorName.EXG2_CH1_24BIT, SensorEXG.cDExg2Ch1_24bit);
        aMap.put(ObjectClusterSensorName.EXG2_CH2_24BIT, SensorEXG.cDExg2Ch2_24bit);

        aMap.put(ObjectClusterSensorName.EXG1_CH1_16BIT, SensorEXG.cDExg1Ch1_16bit);
        aMap.put(ObjectClusterSensorName.EXG1_CH2_16BIT, SensorEXG.cDExg1Ch2_16bit);

        aMap.put(ObjectClusterSensorName.EXG2_CH1_16BIT, SensorEXG.cDExg2Ch1_16bit);
        aMap.put(ObjectClusterSensorName.EXG2_CH2_16BIT, SensorEXG.cDExg2Ch2_16bit);

        aMap.put(ObjectClusterSensorName.EMG_CH1_16BIT, SensorEXG.cDEmgCh1_16bit);
        aMap.put(ObjectClusterSensorName.EMG_CH2_16BIT, SensorEXG.cDEmgCh2_16bit);
        aMap.put(ObjectClusterSensorName.EMG_CH1_24BIT, SensorEXG.cDEmgCh1_24bit);
        aMap.put(ObjectClusterSensorName.EMG_CH2_24BIT, SensorEXG.cDEmgCh2_24bit);

        aMap.put(ObjectClusterSensorName.ECG_LL_RA_16BIT, SensorEXG.cDEcg_LL_RA_16bit);
        aMap.put(ObjectClusterSensorName.ECG_LA_RA_16BIT, SensorEXG.cDEcg_LA_RA_16bit);
        aMap.put(ObjectClusterSensorName.ECG_VX_RL_16BIT, SensorEXG.cDEcg_VX_RL_16bit);
        aMap.put(ObjectClusterSensorName.ECG_RESP_16BIT, SensorEXG.cDEcg_RESP_16bit);
        aMap.put(ObjectClusterSensorName.ECG_LL_RA_24BIT, SensorEXG.cDEcg_LL_RA_24bit);
        aMap.put(ObjectClusterSensorName.ECG_LA_RA_24BIT, SensorEXG.cDEcg_LA_RA_24bit);
        aMap.put(ObjectClusterSensorName.ECG_VX_RL_24BIT, SensorEXG.cDEcg_VX_RL_24bit);
        aMap.put(ObjectClusterSensorName.ECG_RESP_24BIT, SensorEXG.cDEcg_RESP_24bit);

        aMap.put(ObjectClusterSensorName.ECG_CHIP2_CH1_DUMMY_16BIT, SensorEXG.cDEcg_CHIP2_CH1_DUMMY_16BIT);
        aMap.put(ObjectClusterSensorName.ECG_CHIP2_CH1_DUMMY_24BIT, SensorEXG.cDEcg_CHIP2_CH1_DUMMY_24BIT);


        aMap.put(ObjectClusterSensorName.ECG_LL_LA_16BIT, SensorEXG.cDEcg_LL_LA_16bit);
        aMap.put(ObjectClusterSensorName.ECG_LL_LA_24BIT, SensorEXG.cDEcg_LL_LA_24bit);

        aMap.put(ObjectClusterSensorName.EXG_TEST_CHIP1_CH1_16BIT, SensorEXG.cDExg_Test_CHIP1_CH1_16bit);
        aMap.put(ObjectClusterSensorName.EXG_TEST_CHIP1_CH2_16BIT, SensorEXG.cDExg_Test_CHIP1_CH2_16bit);
        aMap.put(ObjectClusterSensorName.EXG_TEST_CHIP2_CH1_16BIT, SensorEXG.cDExg_Test_CHIP2_CH1_16bit);
        aMap.put(ObjectClusterSensorName.EXG_TEST_CHIP2_CH2_16BIT, SensorEXG.cDExg_Test_CHIP2_CH2_16bit);
        aMap.put(ObjectClusterSensorName.EXG_TEST_CHIP1_CH1_24BIT, SensorEXG.cDExg_Test_CHIP1_CH1_24bit);
        aMap.put(ObjectClusterSensorName.EXG_TEST_CHIP1_CH2_24BIT, SensorEXG.cDExg_Test_CHIP1_CH2_24bit);
        aMap.put(ObjectClusterSensorName.EXG_TEST_CHIP2_CH1_24BIT, SensorEXG.cDExg_Test_CHIP2_CH1_24bit);
        aMap.put(ObjectClusterSensorName.EXG_TEST_CHIP2_CH2_24BIT, SensorEXG.cDExg_Test_CHIP2_CH2_24bit);
        mChannelMapRef = Collections.unmodifiableMap(aMap);
    }

    static {
        Map<String, ChannelDetails> aMap = new LinkedHashMap<String, ChannelDetails>();
        aMap.put(SensorEXG.cDExg1Status.mObjectClusterName, SensorEXG.cDExg1Status);
        aMap.put(SensorEXG.cDEcg_LA_RA_24bit_GQ.mObjectClusterName, SensorEXG.cDEcg_LA_RA_24bit_GQ);
        aMap.put(SensorEXG.cDEcg_LA_RL_24bit_GQ.mObjectClusterName, SensorEXG.cDEcg_LA_RL_24bit_GQ);
        mChannelMapRefGq = Collections.unmodifiableMap(aMap);
    }

    protected ExGConfigBytesDetails mExGConfigBytesDetails = new ExGConfigBytesDetails();
    protected byte[] mEXG1RegisterArray = new byte[10];
    protected byte[] mEXG2RegisterArray = new byte[10];
    protected int mExGResolution = 1;
    @Deprecated
    protected int mEXG1RateSetting;
    @Deprecated
    protected int mEXG1CH1GainSetting;
    @Deprecated
    protected int mEXG1CH1GainValue;
    @Deprecated
    protected int mEXG1CH2GainSetting;
    @Deprecated
    protected int mEXG1CH2GainValue;
    @Deprecated
    protected int mEXG2RateSetting;
    @Deprecated
    protected int mEXG2CH1GainSetting;
    @Deprecated
    protected int mEXG2CH1GainValue;
    @Deprecated
    protected int mEXG2CH2PowerDown;
    @Deprecated
    protected int mEXG2CH2GainSetting;
    @Deprecated
    protected int mEXG2CH2GainValue;
    @Deprecated
    protected int mEXGReferenceElectrode = -1;
    @Deprecated
    protected int mLeadOffDetectionMode;
    @Deprecated
    protected int mEXG1LeadOffCurrentMode;
    @Deprecated
    protected int mEXG2LeadOffCurrentMode;
    @Deprecated
    protected int mEXG1Comparators;
    @Deprecated
    protected int mEXG2Comparators;
    @Deprecated
    protected int mEXGRLDSense;
    @Deprecated
    protected int mEXG1LeadOffSenseSelection;
    @Deprecated
    protected int mEXG2LeadOffSenseSelection;
    @Deprecated
    protected int mEXGLeadOffDetectionCurrent;
    @Deprecated
    protected int mEXGLeadOffComparatorTreshold;
    @Deprecated
    protected int mEXG2RespirationDetectState;
    @Deprecated
    protected int mEXG2RespirationDetectFreq;
    @Deprecated
    protected int mEXG2RespirationDetectPhase;
    private boolean mIsExg1_24bitEnabled = false;
    private boolean mIsExg2_24bitEnabled = false;

    private boolean mIsExg1_16bitEnabled = false;
    private boolean mIsExg2_16bitEnabled = false;

        public SensorEXG(ShimmerDevice shimmerDevice) {
        super(SENSORS.EXG, shimmerDevice);
        initialise();
    }

    public static void addExgConfigToDbConfigMap(LinkedHashMap<String, Object> mapOfConfig, byte[] exg1Array, byte[] exg2Array) {
        mapOfConfig.put(DatabaseConfigHandle.EXG1_CONFIG_1, (double) (exg1Array[0] & 0xFF));
        mapOfConfig.put(DatabaseConfigHandle.EXG1_CONFIG_2, (double) (exg1Array[1] & 0xFF));
        mapOfConfig.put(DatabaseConfigHandle.EXG1_LEAD_OFF, (double) (exg1Array[2] & 0xFF));
        mapOfConfig.put(DatabaseConfigHandle.EXG1_CH1_SET, (double) (exg1Array[3] & 0xFF));
        mapOfConfig.put(DatabaseConfigHandle.EXG1_CH2_SET, (double) (exg1Array[4] & 0xFF));
        mapOfConfig.put(DatabaseConfigHandle.EXG1_RLD_SENSE, (double) (exg1Array[5] & 0xFF));
        mapOfConfig.put(DatabaseConfigHandle.EXG1_LEAD_OFF_SENSE, (double) (exg1Array[6] & 0xFF));
        mapOfConfig.put(DatabaseConfigHandle.EXG1_LEAD_OFF_STATUS, (double) (exg1Array[7] & 0xFF));
        mapOfConfig.put(DatabaseConfigHandle.EXG1_RESPIRATION_1, (double) (exg1Array[8] & 0xFF));
        mapOfConfig.put(DatabaseConfigHandle.EXG1_RESPIRATION_2, (double) (exg1Array[9] & 0xFF));

        mapOfConfig.put(DatabaseConfigHandle.EXG2_CONFIG_1, (double) (exg2Array[0] & 0xFF));
        mapOfConfig.put(DatabaseConfigHandle.EXG2_CONFIG_2, (double) (exg2Array[1] & 0xFF));
        mapOfConfig.put(DatabaseConfigHandle.EXG2_LEAD_OFF, (double) (exg2Array[2] & 0xFF));
        mapOfConfig.put(DatabaseConfigHandle.EXG2_CH1_SET, (double) (exg2Array[3] & 0xFF));
        mapOfConfig.put(DatabaseConfigHandle.EXG2_CH2_SET, (double) (exg2Array[4] & 0xFF));
        mapOfConfig.put(DatabaseConfigHandle.EXG2_RLD_SENSE, (double) (exg2Array[5] & 0xFF));
        mapOfConfig.put(DatabaseConfigHandle.EXG2_LEAD_OFF_SENSE, (double) (exg2Array[6] & 0xFF));
        mapOfConfig.put(DatabaseConfigHandle.EXG2_LEAD_OFF_STATUS, (double) (exg2Array[7] & 0xFF));
        mapOfConfig.put(DatabaseConfigHandle.EXG2_RESPIRATION_1, (double) (exg2Array[8] & 0xFF));
        mapOfConfig.put(DatabaseConfigHandle.EXG2_RESPIRATION_2, (double) (exg2Array[9] & 0xFF));
    }

    public static boolean checkIsAnyExgChannelEnabled(Map<Integer, SensorDetails> sensorEnabledMap) {
        for (Integer sensorId : sensorEnabledMap.keySet()) {
            if (sensorEnabledMap.get(sensorId).isEnabled()) {
                if ((sensorId == Configuration.Shimmer3.SENSOR_ID.HOST_ECG)
                        || (sensorId == Configuration.Shimmer3.SENSOR_ID.HOST_EMG)
                        || (sensorId == Configuration.Shimmer3.SENSOR_ID.HOST_EXG_TEST)
                        || (sensorId == Configuration.Shimmer3.SENSOR_ID.HOST_EXG_CUSTOM)
                        || (sensorId == Configuration.Shimmer3.SENSOR_ID.HOST_EXG_RESPIRATION)
                        || (sensorId == Configuration.Shimmer3.SENSOR_ID.HOST_EXG_THREE_UNIPOLAR)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static int convertEXGGainSettingToValue(int setting) {
        if (setting == 0) {
            return 6;
        } else if (setting == 1) {
            return 1;
        } else if (setting == 2) {
            return 2;
        } else if (setting == 3) {
            return 3;
        } else if (setting == 4) {
            return 4;
        } else if (setting == 5) {
            return 8;
        } else if (setting == 6) {
            return 12;
        } else {
            return -1;
        }
    }

    public static boolean is24BitExgChannel(String channelName) {
        if (listOfChannels_Chip1Ch1_24Bit.contains(channelName)
                || listOfChannels_Chip1Ch2_24Bit.contains(channelName)
                || listOfChannels_Chip2Ch1_24Bit.contains(channelName)
                || listOfChannels_Chip2Ch2_24Bit.contains(channelName)
                || listOfChannels_Derived_24Bit.contains(channelName)) {
            return true;
        }
        return false;
    }

    public static boolean is16BitExgChannel(String channelName) {
        if (listOfChannels_Chip1Ch1_16Bit.contains(channelName)
                || listOfChannels_Chip1Ch2_16Bit.contains(channelName)
                || listOfChannels_Chip2Ch1_16Bit.contains(channelName)
                || listOfChannels_Chip2Ch2_16Bit.contains(channelName)
                || listOfChannels_Derived_16Bit.contains(channelName)) {
            return true;
        }
        return false;
    }



    public static boolean isChip1Ch1Channel(String channelName) {
        if (listOfChannels_Chip1Ch1_16Bit.contains(channelName)
                || listOfChannels_Chip1Ch1_24Bit.contains(channelName)) {
            return true;
        }
        return false;
    }

    public static boolean isChip1Ch2Channel(String channelName) {
        if (listOfChannels_Chip1Ch2_16Bit.contains(channelName)
                || listOfChannels_Chip1Ch2_24Bit.contains(channelName)) {
            return true;
        }
        return false;
    }

    public static boolean isChip2Ch1Channel(String channelName) {
        if (listOfChannels_Chip2Ch1_16Bit.contains(channelName)
                || listOfChannels_Chip2Ch1_24Bit.contains(channelName)) {
            return true;
        }
        return false;
    }

    public static boolean isChip2Ch2Channel(String channelName) {
        if (listOfChannels_Chip2Ch2_16Bit.contains(channelName)
                || listOfChannels_Chip2Ch2_24Bit.contains(channelName)) {
            return true;
        }
        return false;
    }

    public static void updateSensorMapForExgResolution(ShimmerDevice shimmerDevice, int exgResolution) {

        if (shimmerDevice.isShimmerGenGq()) {

        } else {
            LinkedHashMap<Integer, SensorDetails> sensorMap = shimmerDevice.getSensorMap();
            List<Integer> listOfSensorIds = Arrays.asList(
                    Configuration.Shimmer3.SENSOR_ID.HOST_ECG,
                    Configuration.Shimmer3.SENSOR_ID.HOST_EMG,
                    Configuration.Shimmer3.SENSOR_ID.HOST_EXG_RESPIRATION,
                    Configuration.Shimmer3.SENSOR_ID.HOST_EXG_CUSTOM,
                    Configuration.Shimmer3.SENSOR_ID.HOST_EXG_TEST,
                    Configuration.Shimmer3.SENSOR_ID.HOST_EXG_THREE_UNIPOLAR);

            for (Integer sensorId : listOfSensorIds) {
                SensorDetails sensorDetails = sensorMap.get(sensorId);
                SensorDetailsRef sensorDetailsRef = mSensorMapRef.get(sensorId);

                if (sensorDetails != null && sensorDetailsRef != null) {
                    List<ChannelDetails> listOfRefChannels = new ArrayList<ChannelDetails>();
                    for (String channelMapKey : sensorDetailsRef.mListOfChannelsRef) {
                        ChannelDetails channelDetails = mChannelMapRef.get(channelMapKey);
                        if (channelDetails != null) {
                            String channelName = channelDetails.mObjectClusterName;

                            if ((exgResolution == 1 && SensorEXG.is16BitExgChannel(channelName))
                                    || (exgResolution == 0 && SensorEXG.is24BitExgChannel(channelName))) {
                                continue;
                            }

                            listOfRefChannels.add(channelDetails);
                        }
                    }
                    sensorDetails.mListOfChannels = listOfRefChannels;

                }
            }
        }
    }

    public static byte[] parseExgConfigFromDb(
            LinkedHashMap<String, Object> mapOfConfigPerShimmer, EXG_CHIP_INDEX chipIndex,
            String config1, String config2, String leadOff, String ch1Set, String ch2Set,
            String rldSense, String leadOffSense, String leadOffStatus, String respiration1, String respiration2) {

        if (mapOfConfigPerShimmer.containsKey(config1)
                && mapOfConfigPerShimmer.containsKey(config2)
                && mapOfConfigPerShimmer.containsKey(leadOff)
                && mapOfConfigPerShimmer.containsKey(ch1Set)
                && mapOfConfigPerShimmer.containsKey(ch2Set)
                && mapOfConfigPerShimmer.containsKey(rldSense)
                && mapOfConfigPerShimmer.containsKey(leadOffSense)
                && mapOfConfigPerShimmer.containsKey(leadOffStatus)
                && mapOfConfigPerShimmer.containsKey(respiration1)
                && mapOfConfigPerShimmer.containsKey(respiration2)) {
            byte[] exgBytes = new byte[10];
            exgBytes[0] = (byte) ((Double) mapOfConfigPerShimmer.get(config1)).intValue();
            exgBytes[1] = (byte) ((Double) mapOfConfigPerShimmer.get(config2)).intValue();
            exgBytes[2] = (byte) ((Double) mapOfConfigPerShimmer.get(leadOff)).intValue();
            exgBytes[3] = (byte) ((Double) mapOfConfigPerShimmer.get(ch1Set)).intValue();
            exgBytes[4] = (byte) ((Double) mapOfConfigPerShimmer.get(ch2Set)).intValue();
            exgBytes[5] = (byte) ((Double) mapOfConfigPerShimmer.get(rldSense)).intValue();
            exgBytes[6] = (byte) ((Double) mapOfConfigPerShimmer.get(leadOffSense)).intValue();
            exgBytes[7] = (byte) ((Double) mapOfConfigPerShimmer.get(leadOffStatus)).intValue();
            exgBytes[8] = (byte) ((Double) mapOfConfigPerShimmer.get(respiration1)).intValue();
            exgBytes[9] = (byte) ((Double) mapOfConfigPerShimmer.get(respiration2)).intValue();
            return exgBytes;
        }
        return null;
    }

    @Override
    public void generateSensorMap() {
        if (mShimmerVerObject.isShimmerGenGq()) {
            LinkedHashMap<Integer, SensorDetailsRef> sensorMapRef = new LinkedHashMap<Integer, SensorDetailsRef>();
            sensorMapRef.put(Configuration.Shimmer3.SENSOR_ID.HOST_ECG, SensorEXG.sDRefEcgGq);
            super.createLocalSensorMapWithCustomParser(sensorMapRef, mChannelMapRefGq);
        } else {
            super.createLocalSensorMapWithCustomParser(mSensorMapRef, mChannelMapRef);
        }
    }

    @Override
    public void generateSensorGroupMapping() {
        mSensorGroupingMap = new LinkedHashMap<Integer, SensorGroupingDetails>();

        int groupIndex = Configuration.Shimmer3.LABEL_SENSOR_TILE.EXG.ordinal();
        if (mShimmerVerObject.isShimmerGenGq()) {
            mSensorGroupingMap.put(groupIndex, new SensorGroupingDetails(
                    LABEL_SENSOR_TILE.EXG,
                    Arrays.asList(Configuration.Shimmer3.SENSOR_ID.HOST_ECG),
                    CompatibilityInfoForMaps.listOfCompatibleVersionInfoExgGeneral));
        } else if (mShimmerVerObject.isShimmerGen3() || mShimmerVerObject.isShimmerGen4()) {
            mSensorGroupingMap.put(groupIndex, new SensorGroupingDetails(
                    LABEL_SENSOR_TILE.EXG,
                    Arrays.asList(Configuration.Shimmer3.SENSOR_ID.HOST_ECG,
                            Configuration.Shimmer3.SENSOR_ID.HOST_EMG,
                            Configuration.Shimmer3.SENSOR_ID.HOST_EXG_TEST,
                            Configuration.Shimmer3.SENSOR_ID.HOST_EXG_CUSTOM,
                            Configuration.Shimmer3.SENSOR_ID.HOST_EXG_RESPIRATION,
                            Configuration.Shimmer3.SENSOR_ID.HOST_EXG_THREE_UNIPOLAR),
                    CompatibilityInfoForMaps.listOfCompatibleVersionInfoExgGeneral));
        }

        super.updateSensorGroupingMap();
    }

    @Override
    public void generateConfigOptionsMap() {
        addConfigOption(configOptionExgGain);
        addConfigOption(configOptionExgResolution);
        addConfigOption(configOptionExgRefElectrode);
        addConfigOption(configOptionExgBytes);
        addConfigOption(configOptionExgRate);
        addConfigOption(configOptionExgLeadOffDetection);
        addConfigOption(configOptionExgLeadOffCurrent);
        addConfigOption(configOptionExgLeadOffComparator);
        addConfigOption(configOptionExgRespirationDetectFreq);
        addConfigOption(configOptionExgRespirationDetectPhase);
    }

    @Override
    public ObjectCluster processDataCustom(SensorDetails sensorDetails, byte[] rawData, COMMUNICATION_TYPE commType, ObjectCluster objectCluster, boolean isTimeSyncEnabled, double pcTimestampMs) {
        objectCluster = sensorDetails.processDataCommon(rawData, commType, objectCluster, isTimeSyncEnabled, pcTimestampMs);

        if (mEnableCalibration) {
            for (int i = sensorDetails.mListOfChannels.size() - 1; i >= 0; i--) {
                ChannelDetails channelDetails = sensorDetails.mListOfChannels.get(i);

                FormatCluster formatClusterUncal = ((FormatCluster) ObjectCluster.returnFormatCluster(objectCluster.getCollectionOfFormatClusters(channelDetails.mObjectClusterName), channelDetails.mChannelFormatDerivedFromShimmerDataPacket.toString()));
                if (formatClusterUncal != null) {
                    double unCalData = formatClusterUncal.mData;
                    if (channelDetails.mObjectClusterName.equals(ObjectClusterSensorName.EXG1_STATUS)
                            || channelDetails.mObjectClusterName.equals(ObjectClusterSensorName.EXG2_STATUS)) {
                        objectCluster.addCalData(channelDetails, unCalData, objectCluster.getIndexKeeper() - i);
                    } else {
                        double calData = unCalData * computeCalConstantForChannel(channelDetails.mObjectClusterName);
                        objectCluster.addCalData(channelDetails, calData, objectCluster.getIndexKeeper() - i);
                    }
                } else {
                    System.out.println(getClass().getSimpleName() + "\tNULL FORMAT CLUSTER\t" + channelDetails.mObjectClusterName);
                }
            }
        }

        if (mIsDebugOutput) {
            super.consolePrintChannelsCal(objectCluster, Arrays.asList(
                    new String[]{ObjectClusterSensorName.EXG_TEST_CHIP1_CH1_24BIT, CHANNEL_TYPE.CAL.toString()},
                    new String[]{ObjectClusterSensorName.EXG_TEST_CHIP1_CH2_24BIT, CHANNEL_TYPE.CAL.toString()},
                    new String[]{ObjectClusterSensorName.EXG_TEST_CHIP2_CH1_24BIT, CHANNEL_TYPE.CAL.toString()},
                    new String[]{ObjectClusterSensorName.EXG_TEST_CHIP2_CH2_24BIT, CHANNEL_TYPE.CAL.toString()}
            ));
        }
//
//
//
//
//
//
//
//
//
//
//
//
//
//

        return objectCluster;
    }

    @Override
    public Object getSettings(String componentName, COMMUNICATION_TYPE commType) {
        return null;
    }

    @Override
    public ActionSetting setSettings(String componentName, Object valueToSet, COMMUNICATION_TYPE commType) {

        ActionSetting actionSetting = new ActionSetting(commType);
        switch (componentName) {
            case (GuiLabelConfig.EXG_GAIN):
                if (commType == COMMUNICATION_TYPE.BLUETOOTH) {
                    setExGGainSetting((int) valueToSet);
                    byte[] reg = mEXG1RegisterArray;
                    byte[] command = new byte[]{ShimmerBluetooth.SET_EXG_REGS_COMMAND, (byte) (EXG_CHIP_INDEX.CHIP1.ordinal()), 0, 10, reg[0], reg[1], reg[2], reg[3], reg[4], reg[5], reg[6], reg[7], reg[8], reg[9]};
                    actionSetting.mActionListByteArray.add(command);

                    reg = mEXG1RegisterArray;
                    command = new byte[]{ShimmerBluetooth.SET_EXG_REGS_COMMAND, (byte) (EXG_CHIP_INDEX.CHIP2.ordinal()), 0, 10, reg[0], reg[1], reg[2], reg[3], reg[4], reg[5], reg[6], reg[7], reg[8], reg[9]};
                    actionSetting.mActionListByteArray.add(command);

                    break;
                } else if (commType == COMMUNICATION_TYPE.DOCK) {

                } else if (commType == COMMUNICATION_TYPE.CLASS) {
                    setExGGainSetting((int) valueToSet);
                }
                break;
        }
        return actionSetting;

    }

    @Override
    public LinkedHashMap<String, Object> generateConfigMap() {
        LinkedHashMap<String, Object> mapOfConfig = new LinkedHashMap<String, Object>();

        addExgConfigToDbConfigMap(mapOfConfig, getEXG1RegisterArray(), getEXG2RegisterArray());

        return mapOfConfig;
    }

    @Override
    public void parseConfigMap(LinkedHashMap<String, Object> mapOfConfigPerShimmer) {

        byte[] exg1Bytes = SensorEXG.parseExgConfigFromDb(mapOfConfigPerShimmer, EXG_CHIP_INDEX.CHIP1,
                DatabaseConfigHandle.EXG1_CONFIG_1,
                DatabaseConfigHandle.EXG1_CONFIG_2,
                DatabaseConfigHandle.EXG1_LEAD_OFF,
                DatabaseConfigHandle.EXG1_CH1_SET,
                DatabaseConfigHandle.EXG1_CH2_SET,
                DatabaseConfigHandle.EXG1_RLD_SENSE,
                DatabaseConfigHandle.EXG1_LEAD_OFF_SENSE,
                DatabaseConfigHandle.EXG1_LEAD_OFF_STATUS,
                DatabaseConfigHandle.EXG1_RESPIRATION_1,
                DatabaseConfigHandle.EXG1_RESPIRATION_2);
        byte[] exg2Bytes = SensorEXG.parseExgConfigFromDb(mapOfConfigPerShimmer, EXG_CHIP_INDEX.CHIP2,
                DatabaseConfigHandle.EXG2_CONFIG_1,
                DatabaseConfigHandle.EXG2_CONFIG_2,
                DatabaseConfigHandle.EXG2_LEAD_OFF,
                DatabaseConfigHandle.EXG2_CH1_SET,
                DatabaseConfigHandle.EXG2_CH2_SET,
                DatabaseConfigHandle.EXG2_RLD_SENSE,
                DatabaseConfigHandle.EXG2_LEAD_OFF_SENSE,
                DatabaseConfigHandle.EXG2_LEAD_OFF_STATUS,
                DatabaseConfigHandle.EXG2_RESPIRATION_1,
                DatabaseConfigHandle.EXG2_RESPIRATION_2);
        exgBytesGetConfigFrom(exg1Bytes, exg2Bytes);
    }


    @Override
    public void configBytesGenerate(ShimmerDevice shimmerDevice, byte[] configBytes, COMMUNICATION_TYPE commType) {
        mShimmerVerObject = shimmerDevice.mShimmerVerObject;

        int idxEXGADS1292RChip1Config1 = 10;
        int idxEXGADS1292RChip2Config1 = 20;



        exgBytesGetFromConfig();
        System.arraycopy(mEXG1RegisterArray, 0, configBytes, idxEXGADS1292RChip1Config1, 10);
        if (isTwoChipExg()) {
            System.arraycopy(mEXG2RegisterArray, 0, configBytes, idxEXGADS1292RChip2Config1, 10);
        }
    }

    @Override
    public void configBytesParse(ShimmerDevice shimmerDevice, byte[] configBytes, COMMUNICATION_TYPE commType) {

        int idxEXGADS1292RChip1Config1 = 10;
        int idxEXGADS1292RChip2Config1 = 20;

        System.arraycopy(configBytes, idxEXGADS1292RChip1Config1, mEXG1RegisterArray, 0, 10);
        if (isTwoChipExg()) {
            System.arraycopy(configBytes, idxEXGADS1292RChip2Config1, mEXG2RegisterArray, 0, 10);
        }
        exgBytesGetConfigFrom(mEXG1RegisterArray, mEXG2RegisterArray);

        handleSpecCasesAfterSensorMapUpdateFromEnabledSensors();
    }


    @Override
    public boolean processResponse(int responseCommand, Object parsedResponse, COMMUNICATION_TYPE commType) {
        return false;
    }

    @Override
    public void checkShimmerConfigBeforeConfiguring() {
//

        if (!SensorEXG.checkIsAnyExgChannelEnabled(mSensorMap)) {
            clearExgConfig();
        }

    }

        public void exgBytesGetConfigFrom(EXG_CHIP_INDEX chipIndex, byte[] byteArray) {
        int index = 1;
        if (byteArray.length == 10) {
            index = 0;
        }

        if (chipIndex == EXG_CHIP_INDEX.CHIP1) {
            System.arraycopy(byteArray, index, mEXG1RegisterArray, 0, 10);
            mEXG1RateSetting = mEXG1RegisterArray[0] & 7;
            mEXGLeadOffDetectionCurrent = (mEXG1RegisterArray[2] >> 2) & 3;
            mEXGLeadOffComparatorTreshold = (mEXG1RegisterArray[2] >> 5) & 7;
            mEXG1CH1GainSetting = (mEXG1RegisterArray[3] >> 4) & 7;
            mEXG1CH1GainValue = convertEXGGainSettingToValue(mEXG1CH1GainSetting);
            mEXG1CH2GainSetting = (mEXG1RegisterArray[4] >> 4) & 7;
            mEXG1CH2GainValue = convertEXGGainSettingToValue(mEXG1CH2GainSetting);
            mEXGReferenceElectrode = mEXG1RegisterArray[5] & 0x0F;
            mEXG1LeadOffCurrentMode = mEXG1RegisterArray[2] & 1;
            mEXG1Comparators = mEXG1RegisterArray[1] & 0x40;
            mEXGRLDSense = mEXG1RegisterArray[5] & 0x10;
            mEXG1LeadOffSenseSelection = mEXG1RegisterArray[6] & 0x0f;

            mExGConfigBytesDetails.updateFromRegisterArray(EXG_CHIP_INDEX.CHIP1, mEXG1RegisterArray);
        } else if (chipIndex == EXG_CHIP_INDEX.CHIP2) {
            System.arraycopy(byteArray, index, mEXG2RegisterArray, 0, 10);
            mEXG2RateSetting = mEXG2RegisterArray[0] & 7;
            mEXG2CH1GainSetting = (mEXG2RegisterArray[3] >> 4) & 7;
            mEXG2CH2PowerDown = (mEXG2RegisterArray[3] >> 7) & 1;
            mEXG2CH1GainValue = convertEXGGainSettingToValue(mEXG2CH1GainSetting);
            mEXG2CH2GainSetting = (mEXG2RegisterArray[4] >> 4) & 7;
            mEXG2CH2GainValue = convertEXGGainSettingToValue(mEXG2CH2GainSetting);
            mEXG2LeadOffCurrentMode = mEXG2RegisterArray[2] & 1;
            mEXG2Comparators = mEXG2RegisterArray[1] & 0x40;
            mEXG2LeadOffSenseSelection = mEXG2RegisterArray[6] & 0x0f;

            mEXG2RespirationDetectState = (mEXG2RegisterArray[8] >> 6) & 0x03;
            mEXG2RespirationDetectPhase = (mEXG2RegisterArray[8] >> 2) & 0x0F;
            mEXG2RespirationDetectFreq = (mEXG2RegisterArray[9] >> 2) & 0x01;

            mExGConfigBytesDetails.updateFromRegisterArray(EXG_CHIP_INDEX.CHIP2, mEXG2RegisterArray);
        }

    }

    public void exgBytesGetConfigFrom(byte[] EXG1RegisterArray, byte[] EXG2RegisterArray) {
        if (EXG1RegisterArray != null) {
            setEXG1RegisterArray(EXG1RegisterArray);
        }
        if (EXG2RegisterArray != null) {
            setEXG2RegisterArray(EXG2RegisterArray);
        }
        internalCheckExgModeAndUpdateSensorMap();
    }

        public void exgBytesGetFromConfig() {
        mEXG1RegisterArray = mExGConfigBytesDetails.getEXG1RegisterArray();
        mEXG2RegisterArray = mExGConfigBytesDetails.getEXG2RegisterArray();
        exgBytesGetConfigFrom(mEXG1RegisterArray, mEXG2RegisterArray);
    }

        public void setDefaultECGConfiguration(double shimmerSamplingRate) {

        clearExgConfig();
        setExgChannelBitsPerMode(Configuration.Shimmer3.SENSOR_ID.HOST_ECG);

        setExgPropertyBothChips(EXG_SETTING_OPTIONS.REG1.CONVERSION_MODES.CONTINUOUS);
        setExgPropertyBothChips(EXG_SETTING_OPTIONS.REG2.REFERENCE_BUFFER.ON);
        setExgPropertyBothChips(EXG_SETTING_OPTIONS.REG2.VOLTAGE_REFERENCE.VREF_2_42V);

        setExgPropertyBothChips(EXG_SETTING_OPTIONS.REG4.CH1_PGA_GAIN.GAIN_4);
        setExgPropertyBothChips(EXG_SETTING_OPTIONS.REG5.CH2_PGA_GAIN.GAIN_4);

        setExgPropertySingleChip(EXG_CHIP_INDEX.CHIP2, EXG_SETTING_OPTIONS.REG5.CH2_INPUT_SELECTION.RLDIN_CONNECTED_TO_NEG_INPUT);

        setExgPropertySingleChip(EXG_CHIP_INDEX.CHIP1, EXG_SETTING_OPTIONS.REG6.RLD_BUFFER_POWER.ENABLED);
        setExgPropertySingleChip(EXG_CHIP_INDEX.CHIP1, EXG_SETTING_OPTIONS.REG6.RLD_NEG_INPUTS_CH2.RLD_CONNECTED_TO_IN2N);
        setExgPropertySingleChip(EXG_CHIP_INDEX.CHIP1, EXG_SETTING_OPTIONS.REG6.RLD_POS_INPUTS_CH2.RLD_CONNECTED_TO_IN2P);
        setExgPropertySingleChip(EXG_CHIP_INDEX.CHIP1, EXG_SETTING_OPTIONS.REG6.RLD_POS_INPUTS_CH1.RLD_CONNECTED_TO_IN1P);

        setExgPropertySingleChip(EXG_CHIP_INDEX.CHIP1, EXG_SETTING_OPTIONS.REG10.RLD_REFERENCE_SIGNAL.HALF_OF_SUPPLY);

        setDefaultExgCommon(shimmerSamplingRate);
    }

        protected void setDefaultEMGConfiguration(double shimmerSamplingRate) {

        clearExgConfig();
        setExgChannelBitsPerMode(Configuration.Shimmer3.SENSOR_ID.HOST_EMG);

        setExgPropertyBothChips(EXG_SETTING_OPTIONS.REG1.CONVERSION_MODES.CONTINUOUS);
        setExgPropertyBothChips(EXG_SETTING_OPTIONS.REG2.REFERENCE_BUFFER.ON);
        setExgPropertyBothChips(EXG_SETTING_OPTIONS.REG2.VOLTAGE_REFERENCE.VREF_2_42V);

        setExgPropertySingleChip(EXG_CHIP_INDEX.CHIP1, EXG_SETTING_OPTIONS.REG4.CH1_PGA_GAIN.GAIN_12);
        setExgPropertySingleChip(EXG_CHIP_INDEX.CHIP1, EXG_SETTING_OPTIONS.REG5.CH2_PGA_GAIN.GAIN_12);
        setExgPropertySingleChip(EXG_CHIP_INDEX.CHIP1, EXG_SETTING_OPTIONS.REG4.CH1_INPUT_SELECTION.ROUTE_CH3_TO_CH1);
        setExgPropertySingleChip(EXG_CHIP_INDEX.CHIP1, EXG_SETTING_OPTIONS.REG5.CH2_INPUT_SELECTION.NORMAL);

        setExgPropertySingleChip(EXG_CHIP_INDEX.CHIP2, EXG_SETTING_OPTIONS.REG4.CH1_POWER_DOWN.POWER_DOWN);
        setExgPropertySingleChip(EXG_CHIP_INDEX.CHIP2, EXG_SETTING_OPTIONS.REG4.CH1_INPUT_SELECTION.SHORTED);
        setExgPropertySingleChip(EXG_CHIP_INDEX.CHIP2, EXG_SETTING_OPTIONS.REG5.CH2_POWER_DOWN.POWER_DOWN);
        setExgPropertySingleChip(EXG_CHIP_INDEX.CHIP2, EXG_SETTING_OPTIONS.REG5.CH2_INPUT_SELECTION.SHORTED);

        setExgPropertySingleChip(EXG_CHIP_INDEX.CHIP1, EXG_SETTING_OPTIONS.REG6.RLD_BUFFER_POWER.ENABLED);
        setExgPropertySingleChip(EXG_CHIP_INDEX.CHIP1, EXG_SETTING_OPTIONS.REG10.RLD_REFERENCE_SIGNAL.HALF_OF_SUPPLY);

        setDefaultExgCommon(shimmerSamplingRate);
    }

        protected void setEXGTestSignal(double shimmerSamplingRate) {

        clearExgConfig();
        setExgChannelBitsPerMode(Configuration.Shimmer3.SENSOR_ID.HOST_EXG_TEST);

        setExgPropertyBothChips(EXG_SETTING_OPTIONS.REG1.CONVERSION_MODES.CONTINUOUS);
        setExgPropertyBothChips(EXG_SETTING_OPTIONS.REG2.REFERENCE_BUFFER.ON);
        setExgPropertyBothChips(EXG_SETTING_OPTIONS.REG2.VOLTAGE_REFERENCE.VREF_2_42V);

        setExgPropertyBothChips(EXG_SETTING_OPTIONS.REG2.TEST_SIGNAL_SELECTION.ON);
        setExgPropertyBothChips(EXG_SETTING_OPTIONS.REG2.TEST_SIGNAL_FREQUENCY.SQUARE_WAVE_1KHZ);

        setExgPropertyBothChips(EXG_SETTING_OPTIONS.REG4.CH1_INPUT_SELECTION.TEST_SIGNAL);
        setExgPropertyBothChips(EXG_SETTING_OPTIONS.REG5.CH2_INPUT_SELECTION.TEST_SIGNAL);

        setDefaultExgCommon(shimmerSamplingRate);
    }

        protected void setDefaultRespirationConfiguration(double shimmerSamplingRate) {

        clearExgConfig();
        setExgChannelBitsPerMode(Configuration.Shimmer3.SENSOR_ID.HOST_EXG_RESPIRATION);

        setExgPropertyBothChips(EXG_SETTING_OPTIONS.REG1.CONVERSION_MODES.CONTINUOUS);
        setExgPropertyBothChips(EXG_SETTING_OPTIONS.REG2.REFERENCE_BUFFER.ON);
        setExgPropertyBothChips(EXG_SETTING_OPTIONS.REG2.VOLTAGE_REFERENCE.VREF_2_42V);

        setExgPropertyBothChips(EXG_SETTING_OPTIONS.REG4.CH1_PGA_GAIN.GAIN_4);
        setExgPropertyBothChips(EXG_SETTING_OPTIONS.REG5.CH2_PGA_GAIN.GAIN_4);


        setExgPropertySingleChip(EXG_CHIP_INDEX.CHIP1, EXG_SETTING_OPTIONS.REG6.RLD_BUFFER_POWER.ENABLED);
        setExgPropertySingleChip(EXG_CHIP_INDEX.CHIP1, EXG_SETTING_OPTIONS.REG10.RLD_REFERENCE_SIGNAL.HALF_OF_SUPPLY);

        setExgPropertySingleChip(EXG_CHIP_INDEX.CHIP2, EXG_SETTING_OPTIONS.REG9.RESPIRATION_DEMOD_CIRCUITRY.ON);
        setExgPropertySingleChip(EXG_CHIP_INDEX.CHIP2, EXG_SETTING_OPTIONS.REG9.RESPIRATION_MOD_CIRCUITRY.ON);
        setExgPropertySingleChip(EXG_CHIP_INDEX.CHIP2, EXG_SETTING_OPTIONS.REG9.RESPIRATION_PHASE_AT_32KHZ.PHASE_112_5);

        setDefaultExgCommon(shimmerSamplingRate);
    }

        protected void setEXGCustom(double shimmerSamplingRate) {

        clearExgConfig();
        setExgChannelBitsPerMode(Configuration.Shimmer3.SENSOR_ID.HOST_EXG_CUSTOM);

        setExgPropertyBothChips(EXG_SETTING_OPTIONS.REG1.CONVERSION_MODES.CONTINUOUS);
        setExgPropertyBothChips(EXG_SETTING_OPTIONS.REG2.REFERENCE_BUFFER.ON);
        setExgPropertyBothChips(EXG_SETTING_OPTIONS.REG2.VOLTAGE_REFERENCE.VREF_2_42V);

        setExgPropertyBothChips(EXG_SETTING_OPTIONS.REG2.TEST_SIGNAL_SELECTION.ON);
        setExgPropertyBothChips(EXG_SETTING_OPTIONS.REG2.TEST_SIGNAL_FREQUENCY.SQUARE_WAVE_1KHZ);

        setExgPropertyBothChips(EXG_SETTING_OPTIONS.REG4.CH1_INPUT_SELECTION.RLDIN_CONNECTED_TO_NEG_INPUT);
        setExgPropertyBothChips(EXG_SETTING_OPTIONS.REG5.CH2_INPUT_SELECTION.RLDIN_CONNECTED_TO_NEG_INPUT);

        setDefaultExgCommon(shimmerSamplingRate);
    }

    public void setExgGq(double shimmerSamplingRate) {

        clearExgConfig();
        setExgChannelBitsPerMode(Configuration.Shimmer3.SENSOR_ID.HOST_EMG);

        setExgPropertySingleChip(EXG_CHIP_INDEX.CHIP1, EXG_SETTING_OPTIONS.REG1.CONVERSION_MODES.CONTINUOUS);
        setExgPropertySingleChip(EXG_CHIP_INDEX.CHIP1, EXG_SETTING_OPTIONS.REG2.REFERENCE_BUFFER.ON);
        setExgPropertySingleChip(EXG_CHIP_INDEX.CHIP1, EXG_SETTING_OPTIONS.REG2.VOLTAGE_REFERENCE.VREF_2_42V);

        setExgPropertySingleChip(EXG_CHIP_INDEX.CHIP1, EXG_SETTING_OPTIONS.REG6.RLD_BUFFER_POWER.ENABLED);
        setExgPropertySingleChip(EXG_CHIP_INDEX.CHIP1, EXG_SETTING_OPTIONS.REG10.RLD_REFERENCE_SIGNAL.HALF_OF_SUPPLY);

        setExgPropertySingleChip(EXG_CHIP_INDEX.CHIP1, EXG_SETTING_OPTIONS.REG4.CH1_PGA_GAIN.GAIN_4);
        setExgPropertySingleChip(EXG_CHIP_INDEX.CHIP1, EXG_SETTING_OPTIONS.REG4.CH1_POWER_DOWN.NORMAL_OPERATION);

        setExgPropertySingleChip(EXG_CHIP_INDEX.CHIP1, EXG_SETTING_OPTIONS.REG4.CH1_INPUT_SELECTION.NORMAL);

        setExgPropertySingleChip(EXG_CHIP_INDEX.CHIP1, EXG_SETTING_OPTIONS.REG5.CH2_PGA_GAIN.GAIN_4);
        setExgPropertySingleChip(EXG_CHIP_INDEX.CHIP1, EXG_SETTING_OPTIONS.REG5.CH2_POWER_DOWN.POWER_DOWN);
        setExgPropertySingleChip(EXG_CHIP_INDEX.CHIP1, EXG_SETTING_OPTIONS.REG5.CH2_INPUT_SELECTION.NORMAL);


        setDefaultExgCommon(shimmerSamplingRate);
    }

    public void setExgThreeUnipolarInput(double shimmerSamplingRate) {
        clearExgConfig();
        setExgChannelBitsPerMode(Configuration.Shimmer3.SENSOR_ID.HOST_EXG_THREE_UNIPOLAR);

        setExgPropertyBothChips(EXG_SETTING_OPTIONS.REG1.CONVERSION_MODES.CONTINUOUS);
        setExgPropertyBothChips(EXG_SETTING_OPTIONS.REG2.REFERENCE_BUFFER.ON);
        setExgPropertyBothChips(EXG_SETTING_OPTIONS.REG2.VOLTAGE_REFERENCE.VREF_2_42V);

        setExgPropertyBothChips(EXG_SETTING_OPTIONS.REG4.CH1_PGA_GAIN.GAIN_4);
        setExgPropertyBothChips(EXG_SETTING_OPTIONS.REG5.CH2_PGA_GAIN.GAIN_4);

        setExgPropertySingleChip(EXG_CHIP_INDEX.CHIP1, EXG_SETTING_OPTIONS.REG4.CH1_INPUT_SELECTION.RLDIN_CONNECTED_TO_NEG_INPUT);
        setExgPropertySingleChip(EXG_CHIP_INDEX.CHIP1, EXG_SETTING_OPTIONS.REG5.CH2_INPUT_SELECTION.RLDIN_CONNECTED_TO_NEG_INPUT);
        setExgPropertySingleChip(EXG_CHIP_INDEX.CHIP2, EXG_SETTING_OPTIONS.REG4.CH1_INPUT_SELECTION.SHORTED);
        setExgPropertySingleChip(EXG_CHIP_INDEX.CHIP2, EXG_SETTING_OPTIONS.REG5.CH2_INPUT_SELECTION.RLDIN_CONNECTED_TO_NEG_INPUT);

        setExgPropertySingleChip(EXG_CHIP_INDEX.CHIP1, EXG_SETTING_OPTIONS.REG6.RLD_BUFFER_POWER.ENABLED);
        setExgPropertySingleChip(EXG_CHIP_INDEX.CHIP1, EXG_SETTING_OPTIONS.REG10.RLD_REFERENCE_SIGNAL.HALF_OF_SUPPLY);

        setDefaultExgCommon(shimmerSamplingRate);
    }

    private void setDefaultExgCommon(double shimmerSamplingRate) {
        if (ShimmerVerObject.isSupportedExgChipClocksJoined(mShimmerVerObject, mShimmerDevice.getExpansionBoardDetails())) {
            setExgPropertySingleChip(EXG_CHIP_INDEX.CHIP1, EXG_SETTING_OPTIONS.REG2.OSCILLATOR_CLOCK_CONNECTION.ON);
        }
        setExGRateFromFreq(shimmerSamplingRate);
        exgBytesGetConfigFrom(mEXG1RegisterArray, mEXG2RegisterArray);
    }

    protected void clearExgConfig() {
        setExgChannelBitsPerMode(-1);
        mExGConfigBytesDetails.startNewExGConig();

        mEXG1RegisterArray = mExGConfigBytesDetails.getEXG1RegisterArray();
        mEXG2RegisterArray = mExGConfigBytesDetails.getEXG2RegisterArray();
        exgBytesGetConfigFrom(mEXG1RegisterArray, mEXG2RegisterArray);
    }

    protected boolean isExgPropertyEnabled(EXG_CHIP_INDEX chipIndex, ExGConfigOption option) {
        return mExGConfigBytesDetails.isExgPropertyEnabled(chipIndex, option);
    }

    protected void setExgPropertyBothChips(ExGConfigOption option) {
        mExGConfigBytesDetails.setExgPropertyBothChips(option);
        mEXG1RegisterArray = mExGConfigBytesDetails.getEXG1RegisterArray();
        mEXG2RegisterArray = mExGConfigBytesDetails.getEXG2RegisterArray();

        exgBytesGetConfigFrom(mEXG1RegisterArray, mEXG2RegisterArray);
    }

        protected void setExgPropertySingleChip(EXG_CHIP_INDEX chipIndex, ExGConfigOption option) {
        mExGConfigBytesDetails.setExgPropertySingleChip(chipIndex, option);
        if (chipIndex == EXG_CHIP_INDEX.CHIP1) {
            mEXG1RegisterArray = mExGConfigBytesDetails.getEXG1RegisterArray();
        } else if (chipIndex == EXG_CHIP_INDEX.CHIP2) {
            mEXG2RegisterArray = mExGConfigBytesDetails.getEXG2RegisterArray();
        }
        updateExgVariables(chipIndex);
    }

        public void setExgPropertySingleChipValue(EXG_CHIP_INDEX chipIndex, String propertyName, int value) {
        mExGConfigBytesDetails.setExgPropertyValue(chipIndex, propertyName, value);
        if (chipIndex == EXG_CHIP_INDEX.CHIP1) {
            mEXG1RegisterArray = mExGConfigBytesDetails.getEXG1RegisterArray();
        } else if (chipIndex == EXG_CHIP_INDEX.CHIP2) {
            mEXG2RegisterArray = mExGConfigBytesDetails.getEXG2RegisterArray();
        }
        updateExgVariables(chipIndex);
    }

    private void updateExgVariables(EXG_CHIP_INDEX chipIndex) {
        if (chipIndex == EXG_CHIP_INDEX.CHIP1) {
            exgBytesGetConfigFrom(EXG_CHIP_INDEX.CHIP1, mEXG1RegisterArray);
        } else if (chipIndex == EXG_CHIP_INDEX.CHIP2) {
            exgBytesGetConfigFrom(EXG_CHIP_INDEX.CHIP2, mEXG2RegisterArray);
        }
    }

    public int getExgPropertySingleChip(EXG_CHIP_INDEX chipIndex, String propertyName) {
        return mExGConfigBytesDetails.getExgPropertySingleChip(chipIndex, propertyName);
    }

    public HashMap<String, Integer> getMapOfExGSettingsChip1() {
        return mExGConfigBytesDetails.mMapOfExGSettingsChip1ThisShimmer;
    }

    public HashMap<String, Integer> getMapOfExGSettingsChip2() {
        return mExGConfigBytesDetails.mMapOfExGSettingsChip2ThisShimmer;
    }

    protected void checkExgResolutionFromEnabledSensorsVar() {
        ConfigByteLayout infoMemLayout = mShimmerDevice.getConfigByteLayout();
        long enabledSensors = mShimmerDevice.getEnabledSensors();

        if (infoMemLayout instanceof ConfigByteLayoutShimmer3) {
            ConfigByteLayoutShimmer3 infoMemLayoutCast = (ConfigByteLayoutShimmer3) mShimmerDevice.getConfigByteLayout();
            mIsExg1_24bitEnabled = ((enabledSensors & infoMemLayoutCast.maskExg1_24bitFlag) > 0) ? true : false;
            mIsExg2_24bitEnabled = ((enabledSensors & infoMemLayoutCast.maskExg2_24bitFlag) > 0) ? true : false;
            mIsExg1_16bitEnabled = ((enabledSensors & infoMemLayoutCast.maskExg1_16bitFlag) > 0) ? true : false;
            mIsExg2_16bitEnabled = ((enabledSensors & infoMemLayoutCast.maskExg2_16bitFlag) > 0) ? true : false;
        } else if (infoMemLayout instanceof ConfigByteLayoutShimmerGq802154) {
            ConfigByteLayoutShimmerGq802154 infoMemLayoutCast = (ConfigByteLayoutShimmerGq802154) mShimmerDevice.getConfigByteLayout();
            mIsExg1_24bitEnabled = ((enabledSensors & infoMemLayoutCast.maskExg1_24bitFlag) > 0) ? true : false;
            mIsExg2_24bitEnabled = ((enabledSensors & infoMemLayoutCast.maskExg2_24bitFlag) > 0) ? true : false;
            mIsExg1_16bitEnabled = ((enabledSensors & infoMemLayoutCast.maskExg1_16bitFlag) > 0) ? true : false;
            mIsExg2_16bitEnabled = ((enabledSensors & infoMemLayoutCast.maskExg2_16bitFlag) > 0) ? true : false;
        }

        if (mIsExg1_16bitEnabled || mIsExg2_16bitEnabled) {
            mExGResolution = 0;
        } else if (mIsExg1_24bitEnabled || mIsExg2_24bitEnabled) {
            mExGResolution = 1;
        }
    }

    private void updateEnabledSensorsFromExgResolution() {
        ConfigByteLayout infoMemLayout = mShimmerDevice.getConfigByteLayout();
        long enabledSensors = mShimmerDevice.getEnabledSensors();

        if (infoMemLayout instanceof ConfigByteLayoutShimmer3) {
            ConfigByteLayoutShimmer3 infoMemLayoutCast = (ConfigByteLayoutShimmer3) mShimmerDevice.getConfigByteLayout();


            enabledSensors &= ~infoMemLayoutCast.maskExg1_24bitFlag;
            enabledSensors |= (mIsExg1_24bitEnabled ? infoMemLayoutCast.maskExg1_24bitFlag : 0);

            enabledSensors &= ~infoMemLayoutCast.maskExg2_24bitFlag;
            enabledSensors |= (mIsExg2_24bitEnabled ? infoMemLayoutCast.maskExg2_24bitFlag : 0);

            enabledSensors &= ~infoMemLayoutCast.maskExg1_16bitFlag;
            enabledSensors |= (mIsExg1_16bitEnabled ? infoMemLayoutCast.maskExg1_16bitFlag : 0);

            enabledSensors &= ~infoMemLayoutCast.maskExg2_16bitFlag;
            enabledSensors |= (mIsExg2_16bitEnabled ? infoMemLayoutCast.maskExg2_16bitFlag : 0);
        } else if (infoMemLayout instanceof ConfigByteLayoutShimmerGq802154) {
            ConfigByteLayoutShimmerGq802154 infoMemLayoutCast = (ConfigByteLayoutShimmerGq802154) mShimmerDevice.getConfigByteLayout();


            enabledSensors &= ~infoMemLayoutCast.maskExg1_24bitFlag;
            enabledSensors |= (mIsExg1_24bitEnabled ? infoMemLayoutCast.maskExg1_24bitFlag : 0);

            enabledSensors &= ~infoMemLayoutCast.maskExg2_24bitFlag;
            enabledSensors |= (mIsExg2_24bitEnabled ? infoMemLayoutCast.maskExg2_24bitFlag : 0);

            enabledSensors &= ~infoMemLayoutCast.maskExg1_16bitFlag;
            enabledSensors |= (mIsExg1_16bitEnabled ? infoMemLayoutCast.maskExg1_16bitFlag : 0);

            enabledSensors &= ~infoMemLayoutCast.maskExg2_16bitFlag;
            enabledSensors |= (mIsExg2_16bitEnabled ? infoMemLayoutCast.maskExg2_16bitFlag : 0);
        }

        mShimmerDevice.setEnabledSensors(enabledSensors);
    }

    private void setExgChannelBitsPerMode(int sensorId) {
        mIsExg1_24bitEnabled = false;
        mIsExg2_24bitEnabled = false;
        mIsExg1_16bitEnabled = false;
        mIsExg2_16bitEnabled = false;

        boolean chip1Enabled = false;
        boolean chip2Enabled = false;
        if (sensorId == -1) {
            chip1Enabled = false;
            chip2Enabled = false;
        } else if ((sensorId == Configuration.Shimmer3.SENSOR_ID.HOST_ECG)
                || (sensorId == Configuration.Shimmer3.SENSOR_ID.HOST_EXG_RESPIRATION)
                || (sensorId == Configuration.Shimmer3.SENSOR_ID.HOST_EXG_CUSTOM)
                || (sensorId == Configuration.Shimmer3.SENSOR_ID.HOST_EXG_TEST)
                || (sensorId == Configuration.Shimmer3.SENSOR_ID.HOST_EXG_THREE_UNIPOLAR)) {
            chip1Enabled = true;
            chip2Enabled = true;
        } else if (sensorId == Configuration.Shimmer3.SENSOR_ID.HOST_EMG) {
            chip1Enabled = true;
            chip2Enabled = false;
        }

        if (mExGResolution == 1) {
            mIsExg1_24bitEnabled = chip1Enabled;
            mIsExg2_24bitEnabled = chip2Enabled;
        } else {
            mIsExg1_16bitEnabled = chip1Enabled;
            mIsExg2_16bitEnabled = chip2Enabled;
        }
    }

        public int getEXG1RateSetting() {
        return getExgPropertySingleChip(EXG_CHIP_INDEX.CHIP1, EXG_SETTINGS.REG1_DATA_RATE);
    }

        public int getEXGReferenceElectrode() {
        return mExGConfigBytesDetails.getEXGReferenceElectrode();
    }

        protected void setEXGReferenceElectrode(int valueToSet) {
        setExgPropertySingleChipValue(EXG_CHIP_INDEX.CHIP1, EXG_SETTINGS.REG6_CH2_RLD_NEG_INPUTS, ((valueToSet & 0x08) == 0x08) ? 1 : 0);
        setExgPropertySingleChipValue(EXG_CHIP_INDEX.CHIP1, EXG_SETTINGS.REG6_CH2_RLD_POS_INPUTS, ((valueToSet & 0x04) == 0x04) ? 1 : 0);
        setExgPropertySingleChipValue(EXG_CHIP_INDEX.CHIP1, EXG_SETTINGS.REG6_CH1_RLD_NEG_INPUTS, ((valueToSet & 0x02) == 0x02) ? 1 : 0);
        setExgPropertySingleChipValue(EXG_CHIP_INDEX.CHIP1, EXG_SETTINGS.REG6_CH1_RLD_POS_INPUTS, ((valueToSet & 0x01) == 0x01) ? 1 : 0);
    }

        public int getEXGLeadOffDetectionCurrent() {
        return getExgPropertySingleChip(EXG_CHIP_INDEX.CHIP1, EXG_SETTINGS.REG3_LEAD_OFF_CURRENT);
    }

        protected void setEXGLeadOffDetectionCurrent(int mEXGLeadOffDetectionCurrent) {
        setExgPropertySingleChipValue(EXG_CHIP_INDEX.CHIP1, EXG_SETTINGS.REG3_LEAD_OFF_CURRENT, mEXGLeadOffDetectionCurrent);
        setExgPropertySingleChipValue(EXG_CHIP_INDEX.CHIP2, EXG_SETTINGS.REG3_LEAD_OFF_CURRENT, mEXGLeadOffDetectionCurrent);
    }

        public int getEXGLeadOffComparatorTreshold() {
        return getExgPropertySingleChip(EXG_CHIP_INDEX.CHIP1, EXG_SETTINGS.REG3_COMPARATOR_THRESHOLD);
    }

        protected void setEXGLeadOffComparatorTreshold(int mEXGLeadOffComparatorTreshold) {
        setExgPropertySingleChipValue(EXG_CHIP_INDEX.CHIP1, EXG_SETTINGS.REG3_COMPARATOR_THRESHOLD, mEXGLeadOffComparatorTreshold);
        setExgPropertySingleChipValue(EXG_CHIP_INDEX.CHIP2, EXG_SETTINGS.REG3_COMPARATOR_THRESHOLD, mEXGLeadOffComparatorTreshold);
    }

        public int getEXG2RespirationDetectFreq() {
        return getExgPropertySingleChip(EXG_CHIP_INDEX.CHIP2, EXG_SETTINGS.REG10_RESPIRATION_CONTROL_FREQUENCY);
    }

        protected void setEXG2RespirationDetectFreq(int mEXG2RespirationDetectFreq) {
        setExgPropertySingleChipValue(EXG_CHIP_INDEX.CHIP2, EXG_SETTINGS.REG10_RESPIRATION_CONTROL_FREQUENCY, mEXG2RespirationDetectFreq);
        checkWhichExgRespPhaseValuesToUse();

        if (isExgRespirationDetectFreq32kHz()) {
            setExgPropertySingleChip(EXG_CHIP_INDEX.CHIP2, EXG_SETTING_OPTIONS.REG9.RESPIRATION_PHASE_AT_32KHZ.PHASE_112_5);
        } else {
            setExgPropertySingleChip(EXG_CHIP_INDEX.CHIP2, EXG_SETTING_OPTIONS.REG9.RESPIRATION_PHASE_AT_64KHZ.PHASE_157_5);
        }
    }

        public int getEXG2RespirationDetectPhase() {
        return getExgPropertySingleChip(EXG_CHIP_INDEX.CHIP2, EXG_SETTINGS.REG9_RESPIRATION_PHASE);
    }

        protected void setEXG2RespirationDetectPhase(int mEXG2RespirationDetectPhase) {
        setExgPropertySingleChipValue(EXG_CHIP_INDEX.CHIP2, EXG_SETTINGS.REG9_RESPIRATION_PHASE, mEXG2RespirationDetectPhase);
    }

        public byte[] getEXG1RegisterArray() {
        return mEXG1RegisterArray;
    }

        protected void setEXG1RegisterArray(byte[] EXG1RegisterArray) {
        setEXGRegisterArray(EXG_CHIP_INDEX.CHIP1, EXG1RegisterArray);
    }

        public byte[] getEXG2RegisterArray() {
        return mEXG2RegisterArray;
    }

        protected void setEXG2RegisterArray(byte[] EXG2RegisterArray) {
        setEXGRegisterArray(EXG_CHIP_INDEX.CHIP2, EXG2RegisterArray);
    }

        public int getExGResolution() {
        return mExGResolution;
    }

    protected void setExGResolution(int i) {
        mExGResolution = i;

        if (i == 0) {
			/*if ((mEnabledSensors & SENSOR_EXG1_16BIT)>0){
				mEnabledSensors=mEnabledSensors^SENSOR_EXG1_16BIT;
			}
			if ((mEnabledSensors & SENSOR_EXG2_16BIT)>0){
				mEnabledSensors=mEnabledSensors^SENSOR_EXG2_16BIT;
			}
			mEnabledSensors = SENSOR_EXG1_16BIT|SENSOR_EXG2_16BIT;
			*/

            if (mIsExg1_24bitEnabled) {
                mIsExg1_24bitEnabled = false;
                mIsExg1_16bitEnabled = true;
            }
            if (mIsExg2_24bitEnabled) {
                mIsExg2_24bitEnabled = false;
                mIsExg2_16bitEnabled = true;
            }
        } else if (i == 1) {
			/*if ((mEnabledSensors & SENSOR_EXG1_24BIT)>0){
				mEnabledSensors=mEnabledSensors^SENSOR_EXG1_24BIT;
			}
			if ((mEnabledSensors & SENSOR_EXG2_24BIT)>0){
				mEnabledSensors=mEnabledSensors^SENSOR_EXG2_24BIT;
			}
			mEnabledSensors = SENSOR_EXG1_24BIT|SENSOR_EXG2_24BIT;
			*/
            if (mIsExg1_16bitEnabled) {
                mIsExg1_24bitEnabled = true;
                mIsExg1_16bitEnabled = false;
            }
            if (mIsExg2_16bitEnabled) {
                mIsExg2_24bitEnabled = true;
                mIsExg2_16bitEnabled = false;
            }
        }

        updateEnabledSensorsFromExgResolution();

    }

    public int getExg1CH1GainConfigValue() {
        return getExgPropertySingleChip(EXG_CHIP_INDEX.CHIP1, EXG_SETTINGS.REG4_CHANNEL_1_PGA_GAIN);
    }

    public int getExg1CH2GainConfigValue() {
        return getExgPropertySingleChip(EXG_CHIP_INDEX.CHIP1, EXG_SETTINGS.REG5_CHANNEL_2_PGA_GAIN);
    }

    public int getExg2CH1GainConfigValue() {
        return getExgPropertySingleChip(EXG_CHIP_INDEX.CHIP2, EXG_SETTINGS.REG4_CHANNEL_1_PGA_GAIN);
    }

    public int getExg2CH2GainConfigValue() {
        return getExgPropertySingleChip(EXG_CHIP_INDEX.CHIP2, EXG_SETTINGS.REG5_CHANNEL_2_PGA_GAIN);
    }

    public boolean areExgChannelGainsEqual(List<EXG_CHIP_INDEX> listOfChipsToCheck) {
        boolean areEqual = true;
        if (listOfChipsToCheck.contains(EXG_CHIP_INDEX.CHIP1)) {
            if (getExg1CH1GainConfigValue() != getExg1CH2GainConfigValue()) {
                areEqual = false;
            }
        }

        if (listOfChipsToCheck.contains(EXG_CHIP_INDEX.CHIP2)) {
            if (getExg2CH1GainConfigValue() != getExg2CH2GainConfigValue()) {
                areEqual = false;
            }
        }

        if (listOfChipsToCheck.contains(EXG_CHIP_INDEX.CHIP1) && listOfChipsToCheck.contains(EXG_CHIP_INDEX.CHIP2)) {
            if (getExg1CH1GainConfigValue() != getExg2CH1GainConfigValue()) {
                areEqual = false;
            }
        }
        return areEqual;
    }

    protected void setExGGainSetting(EXG_CHIP_INDEX chipID, int channel, int value) {
        if (chipID == EXG_CHIP_INDEX.CHIP1) {
            if (channel == 1) {
                setExgPropertySingleChipValue(EXG_CHIP_INDEX.CHIP1, EXG_SETTINGS.REG4_CHANNEL_1_PGA_GAIN, (int) value);
            } else if (channel == 2) {
                setExgPropertySingleChipValue(EXG_CHIP_INDEX.CHIP1, EXG_SETTINGS.REG5_CHANNEL_2_PGA_GAIN, (int) value);
            }
        } else if (chipID == EXG_CHIP_INDEX.CHIP2) {
            if (channel == 1) {
                setExgPropertySingleChipValue(EXG_CHIP_INDEX.CHIP2, EXG_SETTINGS.REG4_CHANNEL_1_PGA_GAIN, (int) value);
            } else if (channel == 2) {
                setExgPropertySingleChipValue(EXG_CHIP_INDEX.CHIP2, EXG_SETTINGS.REG5_CHANNEL_2_PGA_GAIN, (int) value);
            }
        }
    }

    public int getExGGainSetting() {
        return this.mEXG1CH1GainSetting;
    }

    protected void setExGGainSetting(int value) {
        setExgPropertySingleChipValue(EXG_CHIP_INDEX.CHIP1, EXG_SETTINGS.REG4_CHANNEL_1_PGA_GAIN, (int) value);
        setExgPropertySingleChipValue(EXG_CHIP_INDEX.CHIP1, EXG_SETTINGS.REG5_CHANNEL_2_PGA_GAIN, (int) value);
        setExgPropertySingleChipValue(EXG_CHIP_INDEX.CHIP2, EXG_SETTINGS.REG4_CHANNEL_1_PGA_GAIN, (int) value);
        setExgPropertySingleChipValue(EXG_CHIP_INDEX.CHIP2, EXG_SETTINGS.REG5_CHANNEL_2_PGA_GAIN, (int) value);
    }

    protected void setEXGRegisterArray(EXG_CHIP_INDEX chipIndex, byte[] EXGRegisterArray) {
        if (chipIndex == EXG_CHIP_INDEX.CHIP1) {
            this.mEXG1RegisterArray = EXGRegisterArray;
        } else if (chipIndex == EXG_CHIP_INDEX.CHIP2) {
            this.mEXG2RegisterArray = EXGRegisterArray;
        }
        updateExgVariables(chipIndex);
    }

        protected void enableDefaultECGConfiguration(double shimmerSamplingRate) {
        setDefaultECGConfiguration(shimmerSamplingRate);
    }

        protected void enableDefaultEMGConfiguration(double shimmerSamplingRate) {
        setDefaultEMGConfiguration(shimmerSamplingRate);
    }

        protected void enableEXGTestSignal(double shimmerSamplingRate) {
        setEXGTestSignal(shimmerSamplingRate);
    }

        protected void setEXGRateSetting(int valueToSet) {
        setExgPropertySingleChipValue(EXG_CHIP_INDEX.CHIP1, EXG_SETTINGS.REG1_DATA_RATE, (int) valueToSet);
        setExgPropertySingleChipValue(EXG_CHIP_INDEX.CHIP2, EXG_SETTINGS.REG1_DATA_RATE, (int) valueToSet);
    }

        protected void setEXGRateSetting(EXG_CHIP_INDEX chipID, int valueToSet) {
        if (chipID == EXG_CHIP_INDEX.CHIP1) {
            setExgPropertySingleChipValue(EXG_CHIP_INDEX.CHIP1, EXG_SETTINGS.REG1_DATA_RATE, (int) valueToSet);
        } else if (chipID == EXG_CHIP_INDEX.CHIP2) {
            setExgPropertySingleChipValue(EXG_CHIP_INDEX.CHIP2, EXG_SETTINGS.REG1_DATA_RATE, (int) valueToSet);
        }
    }

    protected int getEXGLeadOffCurrentMode() {
        if (isExgPropertyEnabled(EXG_CHIP_INDEX.CHIP1, EXG_SETTING_OPTIONS.REG6.RLD_LEAD_OFF_SENSE_FUNCTION.ON)
                || isExgPropertyEnabled(EXG_CHIP_INDEX.CHIP1, EXG_SETTING_OPTIONS.REG7.LEAD_OFF_DETECT_NEG_INPUTS_CH2.ON)
                || isExgPropertyEnabled(EXG_CHIP_INDEX.CHIP1, EXG_SETTING_OPTIONS.REG7.LEAD_OFF_DETECT_POS_INPUTS_CH2.ON)
                || isExgPropertyEnabled(EXG_CHIP_INDEX.CHIP1, EXG_SETTING_OPTIONS.REG7.LEAD_OFF_DETECT_NEG_INPUTS_CH1.ON)
                || isExgPropertyEnabled(EXG_CHIP_INDEX.CHIP1, EXG_SETTING_OPTIONS.REG7.LEAD_OFF_DETECT_POS_INPUTS_CH1.ON)
                || isExgPropertyEnabled(EXG_CHIP_INDEX.CHIP2, EXG_SETTING_OPTIONS.REG7.LEAD_OFF_DETECT_NEG_INPUTS_CH2.ON)
                || isExgPropertyEnabled(EXG_CHIP_INDEX.CHIP2, EXG_SETTING_OPTIONS.REG7.LEAD_OFF_DETECT_POS_INPUTS_CH2.ON)
                || isExgPropertyEnabled(EXG_CHIP_INDEX.CHIP2, EXG_SETTING_OPTIONS.REG7.LEAD_OFF_DETECT_NEG_INPUTS_CH1.ON)
                || isExgPropertyEnabled(EXG_CHIP_INDEX.CHIP2, EXG_SETTING_OPTIONS.REG7.LEAD_OFF_DETECT_POS_INPUTS_CH1.ON)
        ) {
            if (isExgPropertyEnabled(EXG_CHIP_INDEX.CHIP1, EXG_SETTING_OPTIONS.REG3.LEAD_OFF_FREQUENCY.DC)) {
                return 1;
            } else if (isExgPropertyEnabled(EXG_CHIP_INDEX.CHIP1, EXG_SETTING_OPTIONS.REG3.LEAD_OFF_FREQUENCY.AC)) {
                return 2;
            }
        }
        return 0;
    }

    public void setEXGLeadOffCurrentMode(int mode) {
        if (mode == 0) {
            setExgPropertyBothChips(EXG_SETTING_OPTIONS.REG3.LEAD_OFF_FREQUENCY.DC);
            setExgPropertyBothChips(EXG_SETTING_OPTIONS.REG2.LEAD_OFF_COMPARATORS.OFF);
            setExgPropertySingleChip(EXG_CHIP_INDEX.CHIP1, EXG_SETTING_OPTIONS.REG6.RLD_LEAD_OFF_SENSE_FUNCTION.OFF);
            setExgPropertyBothChips(EXG_SETTING_OPTIONS.REG7.LEAD_OFF_DETECT_NEG_INPUTS_CH2.OFF);
            setExgPropertyBothChips(EXG_SETTING_OPTIONS.REG7.LEAD_OFF_DETECT_POS_INPUTS_CH2.OFF);
            setExgPropertyBothChips(EXG_SETTING_OPTIONS.REG7.LEAD_OFF_DETECT_NEG_INPUTS_CH1.OFF);
            setExgPropertyBothChips(EXG_SETTING_OPTIONS.REG7.LEAD_OFF_DETECT_POS_INPUTS_CH1.OFF);
            if (isEXGUsingDefaultEMGConfiguration()) {
                setExgPropertySingleChip(EXG_CHIP_INDEX.CHIP2, EXG_SETTING_OPTIONS.REG5.CH2_POWER_DOWN.POWER_DOWN);
            }
        } else if (mode == 1) {
            setExgPropertySingleChip(EXG_CHIP_INDEX.CHIP1, EXG_SETTING_OPTIONS.REG3.LEAD_OFF_FREQUENCY.DC);
            setExgPropertySingleChip(EXG_CHIP_INDEX.CHIP1, EXG_SETTING_OPTIONS.REG2.LEAD_OFF_COMPARATORS.ON);
            setExgPropertySingleChip(EXG_CHIP_INDEX.CHIP1, EXG_SETTING_OPTIONS.REG6.RLD_LEAD_OFF_SENSE_FUNCTION.ON);

            if (isEXGUsingDefaultThreeUnipolarConfiguration()) {
                setExgPropertySingleChip(EXG_CHIP_INDEX.CHIP1, EXG_SETTING_OPTIONS.REG7.LEAD_OFF_DETECT_NEG_INPUTS_CH2.OFF);
                setExgPropertySingleChip(EXG_CHIP_INDEX.CHIP1, EXG_SETTING_OPTIONS.REG7.LEAD_OFF_DETECT_POS_INPUTS_CH2.ON);
                setExgPropertySingleChip(EXG_CHIP_INDEX.CHIP1, EXG_SETTING_OPTIONS.REG7.LEAD_OFF_DETECT_NEG_INPUTS_CH1.OFF);
                setExgPropertySingleChip(EXG_CHIP_INDEX.CHIP1, EXG_SETTING_OPTIONS.REG7.LEAD_OFF_DETECT_POS_INPUTS_CH1.ON);
            } else {
                setExgPropertySingleChip(EXG_CHIP_INDEX.CHIP1, EXG_SETTING_OPTIONS.REG7.LEAD_OFF_DETECT_NEG_INPUTS_CH2.OFF);
                setExgPropertySingleChip(EXG_CHIP_INDEX.CHIP1, EXG_SETTING_OPTIONS.REG7.LEAD_OFF_DETECT_POS_INPUTS_CH2.ON);
                setExgPropertySingleChip(EXG_CHIP_INDEX.CHIP1, EXG_SETTING_OPTIONS.REG7.LEAD_OFF_DETECT_NEG_INPUTS_CH1.ON);
                setExgPropertySingleChip(EXG_CHIP_INDEX.CHIP1, EXG_SETTING_OPTIONS.REG7.LEAD_OFF_DETECT_POS_INPUTS_CH1.ON);
            }

            setExgPropertySingleChip(EXG_CHIP_INDEX.CHIP1, ExGConfigBytesDetails.EXG_SETTING_OPTIONS.REG3.LEAD_OFF_CURRENT.CURRENT_22NA);
            setExgPropertySingleChip(EXG_CHIP_INDEX.CHIP1, ExGConfigBytesDetails.EXG_SETTING_OPTIONS.REG3.COMPARATOR_THRESHOLD.POS90NEG10);

            if (isTwoChipExg()) {
                setExgPropertySingleChip(EXG_CHIP_INDEX.CHIP2, EXG_SETTING_OPTIONS.REG3.LEAD_OFF_FREQUENCY.DC);
                setExgPropertySingleChip(EXG_CHIP_INDEX.CHIP2, EXG_SETTING_OPTIONS.REG2.LEAD_OFF_COMPARATORS.ON);

                setExgPropertySingleChip(EXG_CHIP_INDEX.CHIP2, EXG_SETTING_OPTIONS.REG7.LEAD_OFF_DETECT_NEG_INPUTS_CH2.OFF);
                setExgPropertySingleChip(EXG_CHIP_INDEX.CHIP2, EXG_SETTING_OPTIONS.REG7.LEAD_OFF_DETECT_POS_INPUTS_CH2.ON);
                setExgPropertySingleChip(EXG_CHIP_INDEX.CHIP2, EXG_SETTING_OPTIONS.REG7.LEAD_OFF_DETECT_NEG_INPUTS_CH1.OFF);
                setExgPropertySingleChip(EXG_CHIP_INDEX.CHIP2, EXG_SETTING_OPTIONS.REG7.LEAD_OFF_DETECT_POS_INPUTS_CH1.OFF);

                setExgPropertySingleChip(EXG_CHIP_INDEX.CHIP2, ExGConfigBytesDetails.EXG_SETTING_OPTIONS.REG3.LEAD_OFF_CURRENT.CURRENT_22NA);
                setExgPropertySingleChip(EXG_CHIP_INDEX.CHIP2, ExGConfigBytesDetails.EXG_SETTING_OPTIONS.REG3.COMPARATOR_THRESHOLD.POS90NEG10);
            }

            if (isEXGUsingDefaultEMGConfiguration()) {
                setExgPropertySingleChip(EXG_CHIP_INDEX.CHIP2, EXG_SETTING_OPTIONS.REG5.CH2_POWER_DOWN.NORMAL_OPERATION);
            }
        } else if (mode == 2) {
            setExgPropertyBothChips(EXG_SETTING_OPTIONS.REG3.LEAD_OFF_FREQUENCY.AC);
            setExgPropertyBothChips(EXG_SETTING_OPTIONS.REG2.LEAD_OFF_COMPARATORS.ON);
            setExgPropertySingleChip(EXG_CHIP_INDEX.CHIP1, EXG_SETTING_OPTIONS.REG6.RLD_LEAD_OFF_SENSE_FUNCTION.ON);

            if (isEXGUsingDefaultThreeUnipolarConfiguration()) {
                setExgPropertySingleChip(EXG_CHIP_INDEX.CHIP1, EXG_SETTING_OPTIONS.REG7.LEAD_OFF_DETECT_NEG_INPUTS_CH2.OFF);
                setExgPropertySingleChip(EXG_CHIP_INDEX.CHIP1, EXG_SETTING_OPTIONS.REG7.LEAD_OFF_DETECT_POS_INPUTS_CH2.ON);
                setExgPropertySingleChip(EXG_CHIP_INDEX.CHIP1, EXG_SETTING_OPTIONS.REG7.LEAD_OFF_DETECT_NEG_INPUTS_CH1.OFF);
                setExgPropertySingleChip(EXG_CHIP_INDEX.CHIP1, EXG_SETTING_OPTIONS.REG7.LEAD_OFF_DETECT_POS_INPUTS_CH1.ON);
            } else {
                setExgPropertySingleChip(EXG_CHIP_INDEX.CHIP1, EXG_SETTING_OPTIONS.REG7.LEAD_OFF_DETECT_NEG_INPUTS_CH2.OFF);
                setExgPropertySingleChip(EXG_CHIP_INDEX.CHIP1, EXG_SETTING_OPTIONS.REG7.LEAD_OFF_DETECT_POS_INPUTS_CH2.ON);
                setExgPropertySingleChip(EXG_CHIP_INDEX.CHIP1, EXG_SETTING_OPTIONS.REG7.LEAD_OFF_DETECT_NEG_INPUTS_CH1.ON);
                setExgPropertySingleChip(EXG_CHIP_INDEX.CHIP1, EXG_SETTING_OPTIONS.REG7.LEAD_OFF_DETECT_POS_INPUTS_CH1.ON);
            }

            setExgPropertySingleChip(EXG_CHIP_INDEX.CHIP2, EXG_SETTING_OPTIONS.REG7.LEAD_OFF_DETECT_NEG_INPUTS_CH2.OFF);
            setExgPropertySingleChip(EXG_CHIP_INDEX.CHIP2, EXG_SETTING_OPTIONS.REG7.LEAD_OFF_DETECT_POS_INPUTS_CH2.ON);
            setExgPropertySingleChip(EXG_CHIP_INDEX.CHIP2, EXG_SETTING_OPTIONS.REG7.LEAD_OFF_DETECT_NEG_INPUTS_CH1.OFF);
            setExgPropertySingleChip(EXG_CHIP_INDEX.CHIP2, EXG_SETTING_OPTIONS.REG7.LEAD_OFF_DETECT_POS_INPUTS_CH1.OFF);

            if (isEXGUsingDefaultEMGConfiguration()) {
                setExgPropertySingleChip(EXG_CHIP_INDEX.CHIP2, EXG_SETTING_OPTIONS.REG5.CH2_POWER_DOWN.NORMAL_OPERATION);
            }
        }
    }

    @Deprecated
    public boolean isEXGUsingDefaultECGConfigurationForSDFW() {
        if (((mEXG1RegisterArray[3] & 0x0F) == 0) && ((mEXG1RegisterArray[4] & 0x0F) == 0) && ((mEXG2RegisterArray[3] & 0x0F) == 0) && ((mEXG2RegisterArray[4] & 0x0F) == 7)) {
            return true;
        }
        return false;

    }

    public boolean isEXGUsingDefaultECGGqConfiguration() {
        if (mShimmerVerObject.getFirmwareIdentifier() == FW_ID.GQ_802154) {
            if ((getExgPropertySingleChip(EXG_CHIP_INDEX.CHIP1, EXG_SETTINGS.REG4_CHANNEL_1_INPUT_SELECTION) == EXG_SETTING_OPTIONS.REG4.CH1_INPUT_SELECTION.NORMAL.configValueInt)
                    && (getExgPropertySingleChip(EXG_CHIP_INDEX.CHIP1, EXG_SETTINGS.REG5_CHANNEL_2_INPUT_SELECTION) == EXG_SETTING_OPTIONS.REG5.CH2_INPUT_SELECTION.NORMAL.configValueInt)) {
                return true;
            }
        }
        return false;
    }

    public boolean isEXGUsingDefaultECGConfiguration() {
        if ((mIsExg1_16bitEnabled && mIsExg2_16bitEnabled) || (mIsExg1_24bitEnabled && mIsExg2_24bitEnabled)) {
            if ((getExgPropertySingleChip(EXG_CHIP_INDEX.CHIP1, EXG_SETTINGS.REG4_CHANNEL_1_INPUT_SELECTION) == EXG_SETTING_OPTIONS.REG4.CH1_INPUT_SELECTION.NORMAL.configValueInt)
                    && (getExgPropertySingleChip(EXG_CHIP_INDEX.CHIP1, EXG_SETTINGS.REG5_CHANNEL_2_INPUT_SELECTION) == EXG_SETTING_OPTIONS.REG5.CH2_INPUT_SELECTION.NORMAL.configValueInt)
                    && (getExgPropertySingleChip(EXG_CHIP_INDEX.CHIP2, EXG_SETTINGS.REG4_CHANNEL_1_INPUT_SELECTION) == EXG_SETTING_OPTIONS.REG4.CH1_INPUT_SELECTION.NORMAL.configValueInt)
                    && (getExgPropertySingleChip(EXG_CHIP_INDEX.CHIP2, EXG_SETTINGS.REG5_CHANNEL_2_INPUT_SELECTION) == EXG_SETTING_OPTIONS.REG5.CH2_INPUT_SELECTION.RLDIN_CONNECTED_TO_NEG_INPUT.configValueInt)) {

                return true;
            }
        }
        return false;
    }

    public boolean isEXGUsingDefaultEMGConfiguration() {
        if ((mIsExg1_16bitEnabled && !mIsExg2_16bitEnabled) || (mIsExg1_24bitEnabled && !mIsExg2_24bitEnabled)) {
            if ((getExgPropertySingleChip(EXG_CHIP_INDEX.CHIP1, EXG_SETTINGS.REG4_CHANNEL_1_INPUT_SELECTION) == EXG_SETTING_OPTIONS.REG4.CH1_INPUT_SELECTION.ROUTE_CH3_TO_CH1.configValueInt)
                    && (getExgPropertySingleChip(EXG_CHIP_INDEX.CHIP1, EXG_SETTINGS.REG5_CHANNEL_2_INPUT_SELECTION) == EXG_SETTING_OPTIONS.REG5.CH2_INPUT_SELECTION.NORMAL.configValueInt)
                    && (getExgPropertySingleChip(EXG_CHIP_INDEX.CHIP2, EXG_SETTINGS.REG4_CHANNEL_1_INPUT_SELECTION) == EXG_SETTING_OPTIONS.REG4.CH1_INPUT_SELECTION.SHORTED.configValueInt)
                    && (getExgPropertySingleChip(EXG_CHIP_INDEX.CHIP2, EXG_SETTINGS.REG5_CHANNEL_2_INPUT_SELECTION) == EXG_SETTING_OPTIONS.REG5.CH2_INPUT_SELECTION.SHORTED.configValueInt)) {
                return true;
            }
        }
        return false;
    }

    public boolean isEXGUsingDefaultTestSignalConfiguration() {
        if ((mIsExg1_16bitEnabled && mIsExg2_16bitEnabled) || (mIsExg1_24bitEnabled && mIsExg2_24bitEnabled)) {
            if ((getExgPropertySingleChip(EXG_CHIP_INDEX.CHIP1, EXG_SETTINGS.REG4_CHANNEL_1_INPUT_SELECTION) == EXG_SETTING_OPTIONS.REG4.CH1_INPUT_SELECTION.TEST_SIGNAL.configValueInt)
                    && (getExgPropertySingleChip(EXG_CHIP_INDEX.CHIP1, EXG_SETTINGS.REG5_CHANNEL_2_INPUT_SELECTION) == EXG_SETTING_OPTIONS.REG5.CH2_INPUT_SELECTION.TEST_SIGNAL.configValueInt)
                    && (getExgPropertySingleChip(EXG_CHIP_INDEX.CHIP2, EXG_SETTINGS.REG4_CHANNEL_1_INPUT_SELECTION) == EXG_SETTING_OPTIONS.REG4.CH1_INPUT_SELECTION.TEST_SIGNAL.configValueInt)
                    && (getExgPropertySingleChip(EXG_CHIP_INDEX.CHIP2, EXG_SETTINGS.REG5_CHANNEL_2_INPUT_SELECTION) == EXG_SETTING_OPTIONS.REG5.CH2_INPUT_SELECTION.TEST_SIGNAL.configValueInt)) {
                return true;
            }
        }
        return false;
    }

    public boolean isEXGUsingDefaultRespirationConfiguration() {
        if ((mIsExg1_16bitEnabled && mIsExg2_16bitEnabled) || (mIsExg1_24bitEnabled && mIsExg2_24bitEnabled)) {
            if ((getExgPropertySingleChip(EXG_CHIP_INDEX.CHIP2, EXG_SETTINGS.REG9_RESPIRATION_MOD_CIRCUITRY) == EXG_SETTING_OPTIONS.REG9.RESPIRATION_MOD_CIRCUITRY.ON.configValueInt)
                    && (getExgPropertySingleChip(EXG_CHIP_INDEX.CHIP2, EXG_SETTINGS.REG9_RESPIRATION_DEMOD_CIRCUITRY) == EXG_SETTING_OPTIONS.REG9.RESPIRATION_DEMOD_CIRCUITRY.ON.configValueInt)) {
                return true;
            }
        }
        return false;
    }

    public boolean isEXGUsingDefaultThreeUnipolarConfiguration() {
        if ((mIsExg1_16bitEnabled && mIsExg2_16bitEnabled) || (mIsExg1_24bitEnabled && mIsExg2_24bitEnabled)) {
            if ((getExgPropertySingleChip(EXG_CHIP_INDEX.CHIP1, EXG_SETTINGS.REG4_CHANNEL_1_INPUT_SELECTION) == EXG_SETTING_OPTIONS.REG4.CH1_INPUT_SELECTION.RLDIN_CONNECTED_TO_NEG_INPUT.configValueInt)
                    && (getExgPropertySingleChip(EXG_CHIP_INDEX.CHIP1, EXG_SETTINGS.REG5_CHANNEL_2_INPUT_SELECTION) == EXG_SETTING_OPTIONS.REG5.CH2_INPUT_SELECTION.RLDIN_CONNECTED_TO_NEG_INPUT.configValueInt)
                    && (getExgPropertySingleChip(EXG_CHIP_INDEX.CHIP2, EXG_SETTINGS.REG4_CHANNEL_1_INPUT_SELECTION) == EXG_SETTING_OPTIONS.REG4.CH1_INPUT_SELECTION.SHORTED.configValueInt)
                    && (getExgPropertySingleChip(EXG_CHIP_INDEX.CHIP2, EXG_SETTINGS.REG5_CHANNEL_2_INPUT_SELECTION) == EXG_SETTING_OPTIONS.REG5.CH2_INPUT_SELECTION.RLDIN_CONNECTED_TO_NEG_INPUT.configValueInt)) {
                return true;
            }
        }
        return false;
    }

        public boolean isEXGUsingCustomSignalConfiguration() {
        if (mIsExg1_16bitEnabled || mIsExg2_16bitEnabled || mIsExg1_24bitEnabled || mIsExg2_24bitEnabled) {
            return true;
        }
        return false;
    }

        public boolean isExgRespirationDetectFreq32kHz() {
        if (getEXG2RespirationDetectFreq() == 0)
            return true;
        else
            return false;
    }

        public int setExGRateFromFreq(double freq) {

        int valueToSet = 0x00;
        if (freq <= 125) {
            valueToSet = 0x00;
        } else if (freq <= 250) {
            valueToSet = 0x01;
        } else if (freq <= 500) {
            valueToSet = 0x02;
        } else if (freq <= 1000) {
            valueToSet = 0x03;
        } else if (freq <= 2000) {
            valueToSet = 0x04;
        } else if (freq <= 4000) {
            valueToSet = 0x05;
        } else if (freq <= 8000) {
            valueToSet = 0x06;
        } else {
            valueToSet = 0x02;
        }
        setEXGRateSetting(valueToSet);
        return mEXG1RateSetting;
    }

    private void internalCheckExgModeAndUpdateSensorMap() {
        Map<Integer, SensorDetails> sensorMap = mShimmerDevice.getSensorMap();
        if (sensorMap != null && sensorMap.keySet().size() > 0) {
            if (mShimmerVerObject.isShimmerGenGq()) {
                sensorMap.get(Configuration.Shimmer3.SENSOR_ID.HOST_ECG).setIsEnabled(isEXGUsingDefaultECGGqConfiguration());
            } else if ((mShimmerVerObject.isShimmerGen3() || mShimmerVerObject.isShimmerGen4())) {
                if (isEXGUsingDefaultRespirationConfiguration()) {
                    sensorMap.get(Configuration.Shimmer3.SENSOR_ID.HOST_ECG).setIsEnabled(false);
                    sensorMap.get(Configuration.Shimmer3.SENSOR_ID.HOST_EMG).setIsEnabled(false);
                    sensorMap.get(Configuration.Shimmer3.SENSOR_ID.HOST_EXG_TEST).setIsEnabled(false);
                    sensorMap.get(Configuration.Shimmer3.SENSOR_ID.HOST_EXG_CUSTOM).setIsEnabled(false);
                    sensorMap.get(Configuration.Shimmer3.SENSOR_ID.HOST_EXG_RESPIRATION).setIsEnabled(true);
                    sensorMap.get(Configuration.Shimmer3.SENSOR_ID.HOST_EXG_THREE_UNIPOLAR).setIsEnabled(false);
                } else if (isEXGUsingDefaultECGConfiguration()) {
                    sensorMap.get(Configuration.Shimmer3.SENSOR_ID.HOST_ECG).setIsEnabled(true);
                    sensorMap.get(Configuration.Shimmer3.SENSOR_ID.HOST_EMG).setIsEnabled(false);
                    sensorMap.get(Configuration.Shimmer3.SENSOR_ID.HOST_EXG_TEST).setIsEnabled(false);
                    sensorMap.get(Configuration.Shimmer3.SENSOR_ID.HOST_EXG_CUSTOM).setIsEnabled(false);
                    sensorMap.get(Configuration.Shimmer3.SENSOR_ID.HOST_EXG_RESPIRATION).setIsEnabled(false);
                    sensorMap.get(Configuration.Shimmer3.SENSOR_ID.HOST_EXG_THREE_UNIPOLAR).setIsEnabled(false);
                } else if (isEXGUsingDefaultEMGConfiguration()) {
                    sensorMap.get(Configuration.Shimmer3.SENSOR_ID.HOST_ECG).setIsEnabled(false);
                    sensorMap.get(Configuration.Shimmer3.SENSOR_ID.HOST_EMG).setIsEnabled(true);
                    sensorMap.get(Configuration.Shimmer3.SENSOR_ID.HOST_EXG_TEST).setIsEnabled(false);
                    sensorMap.get(Configuration.Shimmer3.SENSOR_ID.HOST_EXG_CUSTOM).setIsEnabled(false);
                    sensorMap.get(Configuration.Shimmer3.SENSOR_ID.HOST_EXG_RESPIRATION).setIsEnabled(false);
                    sensorMap.get(Configuration.Shimmer3.SENSOR_ID.HOST_EXG_THREE_UNIPOLAR).setIsEnabled(false);
                } else if (isEXGUsingDefaultTestSignalConfiguration()) {
                    sensorMap.get(Configuration.Shimmer3.SENSOR_ID.HOST_ECG).setIsEnabled(false);
                    sensorMap.get(Configuration.Shimmer3.SENSOR_ID.HOST_EMG).setIsEnabled(false);
                    sensorMap.get(Configuration.Shimmer3.SENSOR_ID.HOST_EXG_TEST).setIsEnabled(true);
                    sensorMap.get(Configuration.Shimmer3.SENSOR_ID.HOST_EXG_CUSTOM).setIsEnabled(false);
                    sensorMap.get(Configuration.Shimmer3.SENSOR_ID.HOST_EXG_RESPIRATION).setIsEnabled(false);
                    sensorMap.get(Configuration.Shimmer3.SENSOR_ID.HOST_EXG_THREE_UNIPOLAR).setIsEnabled(false);
                } else if (isEXGUsingDefaultThreeUnipolarConfiguration()) {
                    sensorMap.get(Configuration.Shimmer3.SENSOR_ID.HOST_ECG).setIsEnabled(false);
                    sensorMap.get(Configuration.Shimmer3.SENSOR_ID.HOST_EMG).setIsEnabled(false);
                    sensorMap.get(Configuration.Shimmer3.SENSOR_ID.HOST_EXG_TEST).setIsEnabled(false);
                    sensorMap.get(Configuration.Shimmer3.SENSOR_ID.HOST_EXG_CUSTOM).setIsEnabled(false);
                    sensorMap.get(Configuration.Shimmer3.SENSOR_ID.HOST_EXG_RESPIRATION).setIsEnabled(false);
                    sensorMap.get(Configuration.Shimmer3.SENSOR_ID.HOST_EXG_THREE_UNIPOLAR).setIsEnabled(true);
                } else if (isEXGUsingCustomSignalConfiguration()) {
                    sensorMap.get(Configuration.Shimmer3.SENSOR_ID.HOST_ECG).setIsEnabled(false);
                    sensorMap.get(Configuration.Shimmer3.SENSOR_ID.HOST_EMG).setIsEnabled(false);
                    sensorMap.get(Configuration.Shimmer3.SENSOR_ID.HOST_EXG_TEST).setIsEnabled(false);
                    sensorMap.get(Configuration.Shimmer3.SENSOR_ID.HOST_EXG_CUSTOM).setIsEnabled(true);
                    sensorMap.get(Configuration.Shimmer3.SENSOR_ID.HOST_EXG_RESPIRATION).setIsEnabled(false);
                    sensorMap.get(Configuration.Shimmer3.SENSOR_ID.HOST_EXG_THREE_UNIPOLAR).setIsEnabled(false);
                } else {
                    if (sensorMap.get(Configuration.Shimmer3.SENSOR_ID.HOST_ECG) != null) {
                        sensorMap.get(Configuration.Shimmer3.SENSOR_ID.HOST_ECG).setIsEnabled(false);
                        sensorMap.get(Configuration.Shimmer3.SENSOR_ID.HOST_EMG).setIsEnabled(false);
                        sensorMap.get(Configuration.Shimmer3.SENSOR_ID.HOST_EXG_TEST).setIsEnabled(false);
                        sensorMap.get(Configuration.Shimmer3.SENSOR_ID.HOST_EXG_CUSTOM).setIsEnabled(false);
                        sensorMap.get(Configuration.Shimmer3.SENSOR_ID.HOST_EXG_RESPIRATION).setIsEnabled(false);
                        sensorMap.get(Configuration.Shimmer3.SENSOR_ID.HOST_EXG_THREE_UNIPOLAR).setIsEnabled(false);
                    }
                }
            }
        }
    }

    private void checkWhichExgRespPhaseValuesToUse() {
        String stringKey = GuiLabelConfig.EXG_RESPIRATION_DETECT_PHASE;
        if (mConfigOptionsMap != null) {
            ConfigOptionDetails configOptions = mConfigOptionsMap.get(stringKey);
            if (configOptions != null) {
                int nonStandardIndex = -1;
                if (isExgRespirationDetectFreq32kHz()) {
                    nonStandardIndex = ConfigOptionDetailsSensor.VALUE_INDEXES.EXG_RESPIRATION_DETECT_PHASE.PHASE_32KHZ;
                } else {
                    nonStandardIndex = ConfigOptionDetailsSensor.VALUE_INDEXES.EXG_RESPIRATION_DETECT_PHASE.PHASE_64KHZ;
                }

                if (nonStandardIndex != -1 && configOptions instanceof ConfigOptionDetailsSensor) {
                    ((ConfigOptionDetailsSensor) configOptions).setIndexOfValuesToUse(nonStandardIndex);
                }

                Integer[] configvalues = configOptions.getConfigValues();
                if (!Arrays.asList(configvalues).contains(getEXG2RespirationDetectPhase())) {
                    setEXG2RespirationDetectPhase(configvalues[0]);
                }
            }
        }
    }

    private void checkWhichExgRefElectrodeValuesToUse() {
        String stringKey = GuiLabelConfig.EXG_REFERENCE_ELECTRODE;
        if (mConfigOptionsMap != null) {
            ConfigOptionDetails configOptions = mConfigOptionsMap.get(stringKey);
            if (configOptions != null) {
                int nonStandardIndex = -1;
                if (isEXGUsingDefaultRespirationConfiguration()) {
                    nonStandardIndex = ConfigOptionDetailsSensor.VALUE_INDEXES.EXG_REFERENCE_ELECTRODE.RESP;
                } else if (isEXGUsingDefaultECGConfiguration() || isEXGUsingDefaultECGGqConfiguration()) {
                    nonStandardIndex = ConfigOptionDetailsSensor.VALUE_INDEXES.EXG_REFERENCE_ELECTRODE.ECG;
                } else if (isEXGUsingDefaultEMGConfiguration()) {
                    nonStandardIndex = ConfigOptionDetailsSensor.VALUE_INDEXES.EXG_REFERENCE_ELECTRODE.EMG;
                } else if (isEXGUsingDefaultTestSignalConfiguration()) {
                    nonStandardIndex = ConfigOptionDetailsSensor.VALUE_INDEXES.EXG_REFERENCE_ELECTRODE.TEST;
                } else if (isEXGUsingDefaultThreeUnipolarConfiguration()) {
                    nonStandardIndex = ConfigOptionDetailsSensor.VALUE_INDEXES.EXG_REFERENCE_ELECTRODE.UNIPOLAR;
                } else {
                    nonStandardIndex = ConfigOptionDetailsSensor.VALUE_INDEXES.EXG_REFERENCE_ELECTRODE.CUSTOM;
                }

                if (nonStandardIndex != -1 && configOptions instanceof ConfigOptionDetailsSensor) {
                    ((ConfigOptionDetailsSensor) configOptions).setIndexOfValuesToUse(nonStandardIndex);
                }

                Integer[] configvalues = configOptions.getConfigValues();
                if (!Arrays.asList(configvalues).contains(getEXGReferenceElectrode())) {
                    setEXGReferenceElectrode(configvalues[0]);
                }
            }
        }
    }

    @Override
    public Object setConfigValueUsingConfigLabel(Integer sensorId, String configLabel, Object valueToSet) {
        Object returnValue = null;
        switch (configLabel) {
            case (GuiLabelConfig.EXG_RESOLUTION):
                setExGResolution((int) valueToSet);
                returnValue = valueToSet;
                break;

            case (GuiLabelConfig.EXG_GAIN):
                setExGGainSetting((int) valueToSet);
                returnValue = valueToSet;
                break;
            case (GuiLabelConfig.EXG_RATE):
                setEXGRateSetting((int) valueToSet);
                returnValue = valueToSet;
                break;
            case (GuiLabelConfig.EXG_REFERENCE_ELECTRODE):
                setEXGReferenceElectrode((int) valueToSet);
                returnValue = valueToSet;
                break;
            case (GuiLabelConfig.EXG_LEAD_OFF_DETECTION):
                setEXGLeadOffCurrentMode((int) valueToSet);
                returnValue = valueToSet;
                break;
            case (GuiLabelConfig.EXG_LEAD_OFF_CURRENT):
                setEXGLeadOffDetectionCurrent((int) valueToSet);
                returnValue = valueToSet;
                break;
            case (GuiLabelConfig.EXG_LEAD_OFF_COMPARATOR):
                setEXGLeadOffComparatorTreshold((int) valueToSet);
                returnValue = valueToSet;
                break;
            case (GuiLabelConfig.EXG_RESPIRATION_DETECT_FREQ):
                setEXG2RespirationDetectFreq((int) valueToSet);
                returnValue = valueToSet;
                break;
            case (GuiLabelConfig.EXG_RESPIRATION_DETECT_PHASE):
                setEXG2RespirationDetectPhase((int) valueToSet);
                returnValue = valueToSet;
                break;

            default:
                break;
        }

        if ((configLabel.equals(GuiLabelConfig.EXG_RESPIRATION_DETECT_PHASE))
                || (configLabel.equals(GuiLabelConfig.EXG_REFERENCE_ELECTRODE))) {
            checkConfigOptionValues(configLabel);
        }

        return returnValue;
    }

    @Override
    public Object getConfigValueUsingConfigLabel(Integer sensorId, String configLabel) {

        if ((configLabel.equals(GuiLabelConfig.EXG_RESPIRATION_DETECT_PHASE))
                || (configLabel.equals(GuiLabelConfig.EXG_REFERENCE_ELECTRODE))) {
            checkConfigOptionValues(configLabel);
        }

        Object returnValue = null;
        switch (configLabel) {
            case (GuiLabelConfig.EXG_GAIN):
                returnValue = getExGGainSetting();
                break;
            case (GuiLabelConfig.EXG_RESOLUTION):
                returnValue = getExGResolution();
                break;
            case (GuiLabelConfig.EXG_RATE):
                returnValue = getEXG1RateSetting();
                break;
            case (GuiLabelConfig.EXG_REFERENCE_ELECTRODE):
                returnValue = getEXGReferenceElectrode();
                break;
            case (GuiLabelConfig.EXG_LEAD_OFF_DETECTION):
                returnValue = getEXGLeadOffCurrentMode();
                break;
            case (GuiLabelConfig.EXG_LEAD_OFF_CURRENT):
                returnValue = getEXGLeadOffDetectionCurrent();
                break;
            case (GuiLabelConfig.EXG_LEAD_OFF_COMPARATOR):
                returnValue = getEXGLeadOffComparatorTreshold();
                break;
            case (GuiLabelConfig.EXG_RESPIRATION_DETECT_FREQ):
                returnValue = getEXG2RespirationDetectFreq();
                break;
            case (GuiLabelConfig.EXG_RESPIRATION_DETECT_PHASE):
                returnValue = getEXG2RespirationDetectPhase();
                break;
            default:
                break;
        }


        return returnValue;
    }

    @Override
    public void setSensorSamplingRate(double samplingRateHz) {
        setExGRateFromFreq(samplingRateHz);
    }

    @Override
    public boolean setDefaultConfigForSensor(int sensorId, boolean isSensorEnabled) {
        if (mShimmerDevice.getSensorMap().containsKey(sensorId)) {
            if (isSensorEnabled) {
                if (sensorId == Configuration.Shimmer3.SENSOR_ID.HOST_EXG_RESPIRATION) {
                    setDefaultRespirationConfiguration(mShimmerDevice.getSamplingRateShimmer());
                } else if (sensorId == Configuration.Shimmer3.SENSOR_ID.HOST_ECG) {
                    setDefaultECGConfiguration(mShimmerDevice.getSamplingRateShimmer());
                } else if (sensorId == Configuration.Shimmer3.SENSOR_ID.HOST_EMG) {
                    setDefaultEMGConfiguration(mShimmerDevice.getSamplingRateShimmer());
                } else if (sensorId == Configuration.Shimmer3.SENSOR_ID.HOST_EXG_TEST) {
                    setEXGTestSignal(mShimmerDevice.getSamplingRateShimmer());
                } else if (sensorId == Configuration.Shimmer3.SENSOR_ID.HOST_EXG_THREE_UNIPOLAR) {
                    setExgThreeUnipolarInput(mShimmerDevice.getSamplingRateShimmer());
                } else if (sensorId == Configuration.Shimmer3.SENSOR_ID.HOST_EXG_CUSTOM) {
                    setEXGCustom(mShimmerDevice.getSamplingRateShimmer());
                }
            } else {
                if (!checkIsAnyExgChannelEnabled(mShimmerDevice.getSensorMap())) {
                    clearExgConfig();
                }
            }

            return true;
        }
        return false;
    }

    @Override
    public boolean checkConfigOptionValues(String stringKey) {
        if (mConfigOptionsMap != null) {
            ConfigOptionDetails configOptions = mConfigOptionsMap.get(stringKey);
            if (configOptions != null) {
                if (stringKey.equals(GuiLabelConfig.EXG_RESPIRATION_DETECT_PHASE)) {
                    checkWhichExgRespPhaseValuesToUse();
                } else if (stringKey.equals(GuiLabelConfig.EXG_REFERENCE_ELECTRODE)) {
                    checkWhichExgRefElectrodeValuesToUse();
                }
                return true;
            }
        }
        return false;
    }

    private double computeCalConstantForChannel(String channelName) {
        double calConstant = 1.0;
        int exgGainValuePerChannel = getExgGainPerChannel(channelName);

        double denominator = (Math.pow(2, 15) - 1);
        if (is24BitExgChannel(channelName)) {
            denominator = (Math.pow(2, 23) - 1);
        }

        calConstant = (((2.42 * 1000) / exgGainValuePerChannel) / denominator);
        return calConstant;
    }

    private int getExg1Ch1GainSetting() {
        return convertEXGGainSettingToValue(getExg1CH1GainConfigValue());
    }

    private int getExg1Ch2GainSetting() {
        return convertEXGGainSettingToValue(getExg1CH2GainConfigValue());
    }

    private int getExg2Ch1GainSetting() {
        return convertEXGGainSettingToValue(getExg2CH1GainConfigValue());
    }

    private int getExg2Ch2GainSetting() {
        return convertEXGGainSettingToValue(getExg2CH2GainConfigValue());
    }

        private int getExgGainPerChannel(String channelName) {
        int exgChGainValue = getExg1Ch1GainSetting();
        if (isChip1Ch2Channel(channelName)) {
            exgChGainValue = getExg1Ch2GainSetting();
        } else if (isChip2Ch1Channel(channelName)) {
            exgChGainValue = getExg2Ch1GainSetting();
        } else if (isChip2Ch2Channel(channelName)) {
            exgChGainValue = getExg2Ch2GainSetting();
        }
        return exgChGainValue;
    }

    @Override
    public void handleSpecialCasesAfterSensorMapCreate() {
        SensorEXG.updateSensorMapForExgResolution(mShimmerDevice, getExGResolution());
    }

    @Override
    public void handleSpecCasesBeforeSensorMapUpdateGeneral(ShimmerDevice shimmerDevice) {
        checkExgResolutionFromEnabledSensorsVar();
    }

    @Override
    public boolean handleSpecCasesBeforeSensorMapUpdatePerSensor(ShimmerDevice shimmerDevice, Integer sensorId) {
        if (sensorId == Configuration.Shimmer3.SENSOR_ID.HOST_ECG
                || sensorId == Configuration.Shimmer3.SENSOR_ID.HOST_EMG
                || sensorId == Configuration.Shimmer3.SENSOR_ID.HOST_EXG_TEST
                || sensorId == Configuration.Shimmer3.SENSOR_ID.HOST_EXG_CUSTOM
                || sensorId == Configuration.Shimmer3.SENSOR_ID.HOST_EXG_RESPIRATION
                || sensorId == Configuration.Shimmer3.SENSOR_ID.HOST_EXG_THREE_UNIPOLAR) {
            mShimmerDevice.getSensorMap().get(sensorId).setIsEnabled(false);
            return true;
        }
        return false;
    }

    @Override
    public void handleSpecCasesAfterSensorMapUpdateFromEnabledSensors() {
        internalCheckExgModeAndUpdateSensorMap();
        updateEnabledSensorsFromExgResolution();
    }

    @Override
    public void handleSpecCasesUpdateEnabledSensors() {
        updateEnabledSensorsFromExgResolution();
    }

    private boolean isTwoChipExg() {
        return !mShimmerVerObject.isShimmerGenGq();
    }

    public static class ObjectClusterSensorName {
        public static String ECG_GQ = "ECG";

        public static String EXG1_STATUS = "ECG_EMG_Status1";
        public static String EXG2_STATUS = "ECG_EMG_Status2";
        public static String EXG1_CH1_24BIT = "ExG1_CH1_24BIT";
        public static String EXG1_CH2_24BIT = "ExG1_CH2_24BIT";
        public static String EXG1_CH1_16BIT = "ExG1_CH1_16BIT";
        public static String EXG1_CH2_16BIT = "ExG1_CH2_16BIT";
        public static String EXG2_CH1_24BIT = "ExG2_CH1_24BIT";
        public static String EXG2_CH2_24BIT = "ExG2_CH2_24BIT";
        public static String EXG2_CH1_16BIT = "ExG2_CH1_16BIT";
        public static String EXG2_CH2_16BIT = "ExG2_CH2_16BIT";
        public static String EMG_CH1_24BIT = "EMG_CH1_24BIT";
        public static String EMG_CH2_24BIT = "EMG_CH2_24BIT";
        public static String EMG_CH1_16BIT = "EMG_CH1_16BIT";
        public static String EMG_CH2_16BIT = "EMG_CH2_16BIT";
        public static String ECG_LA_RA_24BIT = "ECG_LA-RA_24BIT";
        public static String ECG_LA_RL_24BIT = "ECG_LA-RL_24BIT";
        public static String ECG_LL_RA_24BIT = "ECG_LL-RA_24BIT";
        public static String ECG_LL_LA_24BIT = "ECG_LL-LA_24BIT";
        public static String ECG_RESP_24BIT = "ECG_RESP_24BIT";
        public static String ECG_VX_RL_24BIT = "ECG_Vx-RL_24BIT";
        public static String ECG_LA_RA_16BIT = "ECG_LA-RA_16BIT";
        public static String ECG_LA_RL_16BIT = "ECG_LA-RL_16BIT";
        public static String ECG_LL_RA_16BIT = "ECG_LL-RA_16BIT";
        public static String ECG_LL_LA_16BIT = "ECG_LL-LA_16BIT";
        public static String ECG_RESP_16BIT = "ECG_RESP_16BIT";
        public static String ECG_VX_RL_16BIT = "ECG_Vx-RL_16BIT";
        public static String EXG_TEST_CHIP1_CH1_24BIT = "Test_CHIP1_CH1_24BIT";
        public static String EXG_TEST_CHIP1_CH2_24BIT = "Test_CHIP1_CH2_24BIT";
        public static String EXG_TEST_CHIP2_CH1_24BIT = "Test_CHIP2_CH1_24BIT";
        public static String EXG_TEST_CHIP2_CH2_24BIT = "Test_CHIP2_CH2_24BIT";
        public static String EXG_TEST_CHIP1_CH1_16BIT = "Test_CHIP1_CH1_16BIT";
        public static String EXG_TEST_CHIP1_CH2_16BIT = "Test_CHIP1_CH2_16BIT";
        public static String EXG_TEST_CHIP2_CH1_16BIT = "Test_CHIP2_CH1_16BIT";
        public static String EXG_TEST_CHIP2_CH2_16BIT = "Test_CHIP2_CH2_16BIT";

        public static String ECG_CHIP2_CH1_DUMMY_16BIT = "ECG_CHIP1_CH1_DUMMY_16BIT";
        public static String ECG_CHIP2_CH1_DUMMY_24BIT = "ECG_CHIP1_CH1_DUMMY_24BIT";
    }

    public static class DatabaseChannelHandles {
        public static final String EXG1_CH1_24BITS = "ADS1292R_1_CH1_24BIT";
        public static final String EXG1_CH2_24BITS = "ADS1292R_1_CH2_24BIT";
        public static final String EXG2_CH1_24BITS = "ADS1292R_2_CH1_24BIT";
        public static final String EXG2_CH2_24BITS = "ADS1292R_2_CH2_24BIT";
        public static final String EXG1_CH1_16BITS = "ADS1292R_1_CH1_16BIT";
        public static final String EXG1_CH2_16BITS = "ADS1292R_1_CH2_16BIT";
        public static final String EXG2_CH1_16BITS = "ADS1292R_2_CH1_16BIT";
        public static final String EXG2_CH2_16BITS = "ADS1292R_2_CH2_16BIT";
        public static final String EXG1_STATUS = "ADS1292R_1_STATUS";
        public static final String EXG2_STATUS = "ADS1292R_2_STATUS";

        public static final String ECG_LL_LA_16BITS = SensorEXG.ObjectClusterSensorName.ECG_LL_LA_16BIT.replace("-", "_");
        public static final String ECG_LL_LA_24BITS = SensorEXG.ObjectClusterSensorName.ECG_LL_LA_24BIT.replace("-", "_");
    }

    public static final class DatabaseConfigHandle {
        public static final String EXG1_24BITS = "ADS1292R_1_24BIT";
        public static final String EXG2_24BITS = "ADS1292R_2_24BIT";
        public static final String EXG1_16BITS = "ADS1292R_1_16BIT";
        public static final String EXG2_16BITS = "ADS1292R_2_16BIT";


        public static final String EXG1_CONFIG_1 = "ADS1292R_1_Config1";
        public static final String EXG1_CONFIG_2 = "ADS1292R_1_Config2";
        public static final String EXG1_LEAD_OFF = "ADS1292R_1_LOff";
        public static final String EXG1_CH1_SET = "ADS1292R_1_Ch1_Set";
        public static final String EXG1_CH2_SET = "ADS1292R_1_Ch2_Set";
        public static final String EXG1_RLD_SENSE = "ADS1292R_1_RLD_Sense";
        public static final String EXG1_LEAD_OFF_SENSE = "ADS1292R_1_LOff_Sense";
        public static final String EXG1_LEAD_OFF_STATUS = "ADS1292R_1_LOff_Status";
        public static final String EXG1_RESPIRATION_1 = "ADS1292R_1_Resp1";
        public static final String EXG1_RESPIRATION_2 = "ADS1292R_1_Resp2";
        public static final String EXG2_CONFIG_1 = "ADS1292R_2_Config1";
        public static final String EXG2_CONFIG_2 = "ADS1292R_2_Config2";
        public static final String EXG2_LEAD_OFF = "ADS1292R_2_LOff";
        public static final String EXG2_CH1_SET = "ADS1292R_2_Ch1_Set";
        public static final String EXG2_CH2_SET = "ADS1292R_2_Ch2_Set";
        public static final String EXG2_RLD_SENSE = "ADS1292R_2_RLD_Sense";
        public static final String EXG2_LEAD_OFF_SENSE = "ADS1292R_2_LOff_Sense";
        public static final String EXG2_LEAD_OFF_STATUS = "ADS1292R_2_LOff_Status";
        public static final String EXG2_RESPIRATION_1 = "ADS1292R_2_Resp1";
        public static final String EXG2_RESPIRATION_2 = "ADS1292R_2_Resp2";
    }

    public class GuiLabelConfig {
        public static final String EXG_RESOLUTION = "Resolution";
        public static final String EXG_GAIN = "Gain";
        public static final String EXG_BYTES = "Bytes";

        public static final String EXG_RATE = "ExG Rate";
        public static final String EXG_REFERENCE_ELECTRODE = "Reference Electrode";
        public static final String EXG_LEAD_OFF_DETECTION = "Lead-Off Detection";
        public static final String EXG_LEAD_OFF_CURRENT = "Lead-Off Current";
        public static final String EXG_LEAD_OFF_COMPARATOR = "Lead-Off Comparator Threshold";
        public static final String EXG_RESPIRATION_DETECT_FREQ = "Respiration Detection Freq";
        public static final String EXG_RESPIRATION_DETECT_PHASE = "Respiration Detection Phase";
    }

    public class GuiLabelSensors {
        public static final String EMG = "EMG";
        public static final String ECG = "ECG";
        public static final String EXG_TEST = "ExG Test";
        public static final String EXG_RESPIRATION = "Respiration";
        public static final String EXG1_24BIT = "EXG1 24BIT";
        public static final String EXG2_24BIT = "EXG2 24BIT";
        public static final String EXG1_16BIT = "EXG1 16BIT";
        public static final String EXG2_16BIT = "EXG2 16BIT";
        public static final String EXG_CUSTOM = "Custom";
        public static final String EXG_THREE_UNIPOLAR = "Three Unipolar Inputs";
    }


    public class LABEL_SENSOR_TILE {
        public static final String EXG = "ECG/EMG";
    }
}
