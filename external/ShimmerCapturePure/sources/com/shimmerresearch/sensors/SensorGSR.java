package com.shimmerresearch.sensors;

import com.shimmerresearch.bluetooth.BtCommandDetails;
import com.shimmerresearch.driver.Configuration;
import com.shimmerresearch.driver.ObjectCluster;
import com.shimmerresearch.driver.ShimmerDevice;
import com.shimmerresearch.driverUtilities.ChannelDetails;
import com.shimmerresearch.driverUtilities.ConfigOptionDetails;
import com.shimmerresearch.driverUtilities.ConfigOptionDetailsSensor;
import com.shimmerresearch.driverUtilities.SensorDetails;
import com.shimmerresearch.driverUtilities.SensorDetailsRef;
import com.shimmerresearch.driverUtilities.SensorGroupingDetails;
import com.shimmerresearch.driverUtilities.ShimmerVerObject;
import com.shimmerresearch.driverUtilities.UtilParseData;
import com.shimmerresearch.driverUtilities.UtilShimmer;
import com.shimmerresearch.sensors.AbstractSensor;
import com.shimmerresearch.sensors.SensorADC;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes2.dex */
public class SensorGSR extends AbstractSensor {
    public static final byte GET_GSR_RANGE_COMMAND = 35;
    public static final byte GSR_RANGE_RESPONSE = 34;
    public static final int GSR_UNCAL_LIMIT_RANGE3 = 683;
    public static final String[] ListofGSRRangeConductance;
    public static final Integer[] ListofGSRRangeConfigValues;
    public static final String[] ListofGSRRangeResistance;
    public static final byte SET_GSR_RANGE_COMMAND = 33;
    public static final double[] SHIMMER3_GSR_REF_RESISTORS_KOHMS = {40.2d, 287.0d, 1000.0d, 3300.0d};
    public static final double[][] SHIMMER3_GSR_RESISTANCE_MIN_MAX_KOHMS = {new double[]{8.0d, 63.0d}, new double[]{63.0d, 220.0d}, new double[]{220.0d, 680.0d}, new double[]{680.0d, 4700.0d}};
    public static final ChannelDetails channelGsrAdc;
    public static final ChannelDetails channelGsrKOhms;
    public static final ChannelDetails channelGsrMicroSiemens;
    public static final ChannelDetails channelGsrMicroSiemensGq;
    public static final ChannelDetails channelGsrRange;
    public static final ConfigOptionDetailsSensor configOptionGsrRange;
    public static final Map<Byte, BtCommandDetails> mBtGetCommandMap;
    public static final Map<Byte, BtCommandDetails> mBtSetCommandMap;
    public static final Map<String, ChannelDetails> mChannelMapRef;
    public static final Map<String, ChannelDetails> mChannelMapRefGq;
    public static final Map<Integer, SensorDetailsRef> mSensorMapRef;
    public static final SensorDetailsRef sensorGsrRef;
    private static final long serialVersionUID = 1773291747371088953L;
    public static boolean isShimmer3and4UsingShimmer2rVal = true;

    static {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put((byte) 35, new BtCommandDetails((byte) 35, "GET_GSR_RANGE_COMMAND", (byte) 34));
        mBtGetCommandMap = Collections.unmodifiableMap(linkedHashMap);
        LinkedHashMap linkedHashMap2 = new LinkedHashMap();
        linkedHashMap2.put((byte) 33, new BtCommandDetails((byte) 33, "SET_GSR_RANGE_COMMAND"));
        mBtSetCommandMap = Collections.unmodifiableMap(linkedHashMap2);
        String[] strArr = {"8kΩ to 63kΩ", "63kΩ to 220kΩ", "220kΩ to 680kΩ", "680kΩ to 4.7MΩ", "Auto Range"};
        ListofGSRRangeResistance = strArr;
        ListofGSRRangeConductance = new String[]{"125µS to 15.9µS", "15.9µS to 4.5µS", "4.5µS to 1.5µS", "1.5µS to 0.2µS", "Auto Range"};
        Integer[] numArr = {0, 1, 2, 3, 4};
        ListofGSRRangeConfigValues = numArr;
        configOptionGsrRange = new ConfigOptionDetailsSensor("GSR Range", DatabaseConfigHandle.GSR_RANGE, strArr, numArr, ConfigOptionDetails.GUI_COMPONENT_TYPE.COMBOBOX, Configuration.Shimmer3.CompatibilityInfoForMaps.listOfCompatibleVersionInfoGsr);
        SensorDetailsRef sensorDetailsRef = new SensorDetailsRef(4L, 4L, "GSR", Configuration.Shimmer3.CompatibilityInfoForMaps.listOfCompatibleVersionInfoGsr, Arrays.asList(17, 13, 100, 101, 102, 116, 103, 106, 16, 15), Arrays.asList("GSR Range"), Arrays.asList(ObjectClusterSensorName.GSR_RESISTANCE, ObjectClusterSensorName.GSR_CONDUCTANCE, ObjectClusterSensorName.GSR_RANGE, ObjectClusterSensorName.GSR_GQ), true);
        sensorGsrRef = sensorDetailsRef;
        LinkedHashMap linkedHashMap3 = new LinkedHashMap();
        linkedHashMap3.put(19, sensorDetailsRef);
        mSensorMapRef = Collections.unmodifiableMap(linkedHashMap3);
        ChannelDetails channelDetails = new ChannelDetails(ObjectClusterSensorName.GSR_RESISTANCE, ObjectClusterSensorName.GSR_RESISTANCE, "F5437a_Int_A1_GSR", ChannelDetails.CHANNEL_DATA_TYPE.UINT16, 2, ChannelDetails.CHANNEL_DATA_ENDIAN.LSB, Configuration.CHANNEL_UNITS.KOHMS, (List<ChannelDetails.CHANNEL_TYPE>) Arrays.asList(ChannelDetails.CHANNEL_TYPE.CAL, ChannelDetails.CHANNEL_TYPE.UNCAL), 28);
        channelGsrKOhms = channelDetails;
        ChannelDetails channelDetails2 = new ChannelDetails(ObjectClusterSensorName.GSR_GQ, ObjectClusterSensorName.GSR_GQ, "F5437a_Int_A1_GSR", ChannelDetails.CHANNEL_DATA_TYPE.UINT16, 2, ChannelDetails.CHANNEL_DATA_ENDIAN.LSB, Configuration.CHANNEL_UNITS.U_SIEMENS, (List<ChannelDetails.CHANNEL_TYPE>) Arrays.asList(ChannelDetails.CHANNEL_TYPE.CAL, ChannelDetails.CHANNEL_TYPE.UNCAL), 28);
        channelGsrMicroSiemensGq = channelDetails2;
        ChannelDetails channelDetails3 = new ChannelDetails(ObjectClusterSensorName.GSR_CONDUCTANCE, ObjectClusterSensorName.GSR_CONDUCTANCE, DatabaseChannelHandles.GSR_CONDUCTANCE, Configuration.CHANNEL_UNITS.U_SIEMENS, (List<ChannelDetails.CHANNEL_TYPE>) Arrays.asList(ChannelDetails.CHANNEL_TYPE.CAL, ChannelDetails.CHANNEL_TYPE.UNCAL));
        channelGsrMicroSiemens = channelDetails3;
        ChannelDetails channelDetails4 = new ChannelDetails(ObjectClusterSensorName.GSR_RANGE, ObjectClusterSensorName.GSR_RANGE, Configuration.CHANNEL_UNITS.NO_UNITS, Arrays.asList(ChannelDetails.CHANNEL_TYPE.CAL, ChannelDetails.CHANNEL_TYPE.UNCAL));
        channelGsrRange = channelDetails4;
        ChannelDetails channelDetails5 = new ChannelDetails(ObjectClusterSensorName.GSR_ADC_VALUE, ObjectClusterSensorName.GSR_ADC_VALUE, ObjectClusterSensorName.GSR_ADC_VALUE, Configuration.CHANNEL_UNITS.MILLIVOLTS, (List<ChannelDetails.CHANNEL_TYPE>) Arrays.asList(ChannelDetails.CHANNEL_TYPE.CAL, ChannelDetails.CHANNEL_TYPE.UNCAL));
        channelGsrAdc = channelDetails5;
        LinkedHashMap linkedHashMap4 = new LinkedHashMap();
        linkedHashMap4.put(ObjectClusterSensorName.GSR_RESISTANCE, channelDetails);
        linkedHashMap4.put(ObjectClusterSensorName.GSR_CONDUCTANCE, channelDetails3);
        linkedHashMap4.put(ObjectClusterSensorName.GSR_RANGE, channelDetails4);
        linkedHashMap4.put(ObjectClusterSensorName.GSR_ADC_VALUE, channelDetails5);
        mChannelMapRef = Collections.unmodifiableMap(linkedHashMap4);
        LinkedHashMap linkedHashMap5 = new LinkedHashMap();
        linkedHashMap5.put(ObjectClusterSensorName.GSR_RESISTANCE, channelDetails2);
        mChannelMapRefGq = Collections.unmodifiableMap(linkedHashMap5);
    }

    public int mGSRRange;
    private double[] currentGsrRefResistorsKohms;
    private double[][] currentGsrResistanceKohmsMinMax;
    private int currentGsrUncalLimitRange3;
    private SensorADC.MICROCONTROLLER_ADC_PROPERTIES microcontrollerAdcProperties;

    public SensorGSR(ShimmerVerObject shimmerVerObject) {
        super(AbstractSensor.SENSORS.GSR, shimmerVerObject);
        this.mGSRRange = 4;
        this.microcontrollerAdcProperties = null;
        this.currentGsrRefResistorsKohms = SHIMMER3_GSR_REF_RESISTORS_KOHMS;
        this.currentGsrResistanceKohmsMinMax = SHIMMER3_GSR_RESISTANCE_MIN_MAX_KOHMS;
        this.currentGsrUncalLimitRange3 = GSR_UNCAL_LIMIT_RANGE3;
        ChannelDetails channelDetails = channelGsrMicroSiemens;
        channelDetails.mChannelSource = ChannelDetails.CHANNEL_SOURCE.API;
        channelDetails.mChannelSource = ChannelDetails.CHANNEL_SOURCE.API;
        channelDetails.mChannelSource = ChannelDetails.CHANNEL_SOURCE.API;
        initialise();
        this.microcontrollerAdcProperties = SensorADC.MICROCONTROLLER_ADC_PROPERTIES.getMicrocontrollerAdcPropertiesForShimmerVersionObject(shimmerVerObject);
    }

    @Deprecated
    public static double calibrateGsrDataToSiemens(double d, double d2, double d3) {
        return (d2 * (((int) d) & 4095)) + d3;
    }

    public static double convertUSiemensTokOhm(double d) {
        return 1000.0d / d;
    }

    public static double convertkOhmToUSiemens(double d) {
        return 1000.0d / d;
    }

    public static double nudgeGsrResistance(double d, int i, double[][] dArr) {
        if (i == 4) {
            return Math.max(dArr[0][0], d);
        }
        double[] dArr2 = dArr[i];
        return UtilShimmer.nudgeDouble(d, dArr2[0], dArr2[1]);
    }

    @Deprecated
    public static double calibrateGsrDataToResistance(double d, double d2, double d3) {
        return (1.0d / calibrateGsrDataToSiemens(d, d2, d3)) * 1000.0d;
    }

    public static double calibrateGsrDataToKOhmsUsingAmplifierEq(double d, int i, SensorADC.MICROCONTROLLER_ADC_PROPERTIES microcontroller_adc_properties, double[] dArr) {
        return dArr[i] / ((SensorADC.calibrateAdcChannelToVolts(d, microcontroller_adc_properties) / 0.5d) - 1.0d);
    }

    public static int uncalibrateGsrDataTokOhmsUsingAmplifierEq(double d, SensorADC.MICROCONTROLLER_ADC_PROPERTIES microcontroller_adc_properties, double[] dArr, double[][] dArr2) {
        int i = 0;
        int i2 = 0;
        while (true) {
            if (i2 >= dArr2.length) {
                break;
            }
            double[] dArr3 = dArr2[i2];
            if (d > dArr3[0] && d < dArr3[1]) {
                i = i2;
                break;
            }
            i2++;
        }
        return SensorADC.uncalibrateAdcChannelFromVolts(((dArr[i] / d) + 1.0d) * 0.5d, microcontroller_adc_properties) + (i << 14);
    }

    @Deprecated
    public static double[] getGSRCoefficientsFromUsingGSRRange(ShimmerVerObject shimmerVerObject, int i) {
        double d;
        double d2;
        if (i == 0) {
            if (shimmerVerObject.isShimmerGen2() || isShimmer3and4UsingShimmer2rVal) {
                d = 0.0373d;
                d2 = -24.9915d;
            } else {
                d = 0.0363d;
                d2 = -24.8617d;
            }
        } else if (i == 1) {
            if (shimmerVerObject.isShimmerGen2() || isShimmer3and4UsingShimmer2rVal) {
                d = 0.0054d;
                d2 = -3.5194d;
            } else {
                d = 0.0051d;
                d2 = -3.8357d;
            }
        } else if (i == 2) {
            double d3 = (shimmerVerObject.isShimmerGen2() || isShimmer3and4UsingShimmer2rVal) ? -1.0163d : -1.0067d;
            d = 0.0015d;
            d2 = d3;
        } else if (i != 3) {
            d = 0.0d;
            d2 = 0.0d;
        } else if (shimmerVerObject.isShimmerGen2() || isShimmer3and4UsingShimmer2rVal) {
            d = 4.558E-4d;
            d2 = -0.3014d;
        } else {
            d = 4.4513E-4d;
            d2 = -0.3193d;
        }
        return new double[]{d, d2};
    }

    private void setDefaultGsrSensorConfig(boolean z) {
        if (z) {
            return;
        }
        this.mGSRRange = 4;
    }

    public double[] getCurrentGsrRefResistorsKohms() {
        return this.currentGsrRefResistorsKohms;
    }

    public void setCurrentGsrRefResistorsKohms(double[] dArr) {
        this.currentGsrRefResistorsKohms = dArr;
    }

    public double[][] getCurrentGsrResistanceKohmsMinMax() {
        return this.currentGsrResistanceKohmsMinMax;
    }

    public void setCurrentGsrResistanceKohmsMinMax(double[][] dArr) {
        this.currentGsrResistanceKohmsMinMax = dArr;
    }

    public int getCurrentGsrUncalLimitRange3() {
        return this.currentGsrUncalLimitRange3;
    }

    public void setCurrentGsrUncalLimitRange3(int i) {
        this.currentGsrUncalLimitRange3 = i;
    }

    public int getGSRRange() {
        return this.mGSRRange;
    }

    public void setGSRRange(int i) {
        this.mGSRRange = i;
    }

    @Override // com.shimmerresearch.sensors.AbstractSensor
    public Object getSettings(String str, Configuration.COMMUNICATION_TYPE communication_type) {
        return null;
    }

    @Override // com.shimmerresearch.sensors.AbstractSensor
    public boolean processResponse(int i, Object obj, Configuration.COMMUNICATION_TYPE communication_type) {
        return false;
    }

    @Override // com.shimmerresearch.sensors.AbstractSensor
    public void setSensorSamplingRate(double d) {
    }

    @Override // com.shimmerresearch.sensors.AbstractSensor
    public void generateSensorMap() {
        if (this.mShimmerVerObject.isShimmerGenGq()) {
            LinkedHashMap linkedHashMap = new LinkedHashMap();
            ChannelDetails channelDetails = channelGsrMicroSiemensGq;
            linkedHashMap.put(channelDetails.mObjectClusterName, channelDetails);
            super.createLocalSensorMapWithCustomParser(mSensorMapRef, linkedHashMap);
            return;
        }
        super.createLocalSensorMapWithCustomParser(mSensorMapRef, mChannelMapRef);
    }

    @Override // com.shimmerresearch.sensors.AbstractSensor
    public void generateConfigOptionsMap() {
        addConfigOption(configOptionGsrRange);
    }

    @Override // com.shimmerresearch.sensors.AbstractSensor
    public void generateSensorGroupMapping() {
        int iOrdinal = Configuration.Shimmer3.LABEL_SENSOR_TILE.GSR.ordinal();
        if (this.mShimmerVerObject.isShimmerGenGq()) {
            this.mSensorGroupingMap.put(Integer.valueOf(iOrdinal), new SensorGroupingDetails(LABEL_SENSOR_TILE.GSR, Arrays.asList(19), Configuration.Shimmer3.CompatibilityInfoForMaps.listOfCompatibleVersionInfoGsr));
        } else if (this.mShimmerVerObject.isShimmerGen3() || this.mShimmerVerObject.isShimmerGen4()) {
            this.mSensorGroupingMap.put(Integer.valueOf(iOrdinal), new SensorGroupingDetails(LABEL_SENSOR_TILE.GSR, Arrays.asList(19, 105), Configuration.Shimmer3.CompatibilityInfoForMaps.listOfCompatibleVersionInfoGsr));
        }
        super.updateSensorGroupingMap();
    }

    @Override // com.shimmerresearch.sensors.AbstractSensor
    public ObjectCluster processDataCustom(SensorDetails sensorDetails, byte[] bArr, Configuration.COMMUNICATION_TYPE communication_type, ObjectCluster objectCluster, boolean z, double d) {
        int i;
        int i2 = 0;
        for (ChannelDetails channelDetails : sensorDetails.mListOfChannels) {
            if (channelDetails.mObjectClusterName.equals(ObjectClusterSensorName.GSR_RESISTANCE) || channelDetails.mObjectClusterName.equals(ObjectClusterSensorName.GSR_GQ)) {
                System.arraycopy(bArr, i2, new byte[channelDetails.mDefaultNumBytes], 0, channelDetails.mDefaultNumBytes);
                int data = (int) UtilParseData.parseData(r0, channelDetails.mDefaultChannelDataType, channelDetails.mDefaultChannelDataEndian);
                int i3 = data & 4095;
                double d2 = i3;
                objectCluster.addUncalData(channelDetails, d2);
                int gSRRange = getGSRRange();
                if (gSRRange == 4) {
                    gSRRange = (data & 49152) >> 14;
                }
                List<ChannelDetails> list = sensorDetails.mListOfChannels;
                ChannelDetails channelDetails2 = channelGsrRange;
                if (list.contains(channelDetails2)) {
                    double d3 = gSRRange;
                    objectCluster.addUncalData(channelDetails2, d3);
                    objectCluster.addCalData(channelDetails2, d3);
                    objectCluster.incrementIndexKeeper();
                }
                List<ChannelDetails> list2 = sensorDetails.mListOfChannels;
                ChannelDetails channelDetails3 = channelGsrAdc;
                if (list2.contains(channelDetails3)) {
                    objectCluster.addUncalData(channelDetails3, d2);
                    objectCluster.addCalData(channelDetails3, SensorADC.calibrateAdcChannelToMillivolts(d2, this.microcontrollerAdcProperties));
                    objectCluster.incrementIndexKeeper();
                }
                if (gSRRange == 3 && i3 < (i = this.currentGsrUncalLimitRange3)) {
                    i3 = i;
                }
                double d4 = i3;
                double dNudgeGsrResistance = nudgeGsrResistance(calibrateGsrDataToKOhmsUsingAmplifierEq(d4, gSRRange, this.microcontrollerAdcProperties, this.currentGsrRefResistorsKohms), getGSRRange(), this.currentGsrResistanceKohmsMinMax);
                double dConvertkOhmToUSiemens = convertkOhmToUSiemens(dNudgeGsrResistance);
                if (!channelDetails.mDefaultCalUnits.equals(Configuration.CHANNEL_UNITS.KOHMS)) {
                    dNudgeGsrResistance = channelDetails.mDefaultCalUnits.equals(Configuration.CHANNEL_UNITS.U_SIEMENS) ? dConvertkOhmToUSiemens : 0.0d;
                }
                objectCluster.addCalData(channelDetails, dNudgeGsrResistance);
                objectCluster.incrementIndexKeeper();
                List<ChannelDetails> list3 = sensorDetails.mListOfChannels;
                ChannelDetails channelDetails4 = channelGsrMicroSiemens;
                if (list3.contains(channelDetails4)) {
                    objectCluster.addUncalData(channelDetails4, d4);
                    objectCluster.addCalData(channelDetails4, dConvertkOhmToUSiemens);
                    objectCluster.incrementIndexKeeper();
                }
            }
            i2 += channelDetails.mDefaultNumBytes;
        }
        return objectCluster;
    }

    @Override // com.shimmerresearch.sensors.AbstractSensor
    public void configBytesGenerate(ShimmerDevice shimmerDevice, byte[] bArr, Configuration.COMMUNICATION_TYPE communication_type) {
        bArr[9] = (byte) (bArr[9] | ((byte) ((this.mGSRRange & 7) << 1)));
    }

    @Override // com.shimmerresearch.sensors.AbstractSensor
    public void configBytesParse(ShimmerDevice shimmerDevice, byte[] bArr, Configuration.COMMUNICATION_TYPE communication_type) {
        this.mGSRRange = (bArr[9] >> 1) & 7;
    }

    @Override // com.shimmerresearch.sensors.AbstractSensor
    public Object setConfigValueUsingConfigLabel(Integer num, String str, Object obj) {
        str.hashCode();
        if (!str.equals("GSR Range")) {
            return null;
        }
        setGSRRange(((Integer) obj).intValue());
        return obj;
    }

    @Override // com.shimmerresearch.sensors.AbstractSensor
    public Object getConfigValueUsingConfigLabel(Integer num, String str) {
        str.hashCode();
        if (str.equals("GSR Range")) {
            return Integer.valueOf(getGSRRange());
        }
        return null;
    }

    @Override // com.shimmerresearch.sensors.AbstractSensor
    public boolean setDefaultConfigForSensor(int i, boolean z) {
        if (!this.mSensorMap.containsKey(Integer.valueOf(i))) {
            return false;
        }
        setDefaultGsrSensorConfig(z);
        return true;
    }

    @Override // com.shimmerresearch.sensors.AbstractSensor
    public boolean checkConfigOptionValues(String str) {
        return this.mConfigOptionsMap.containsKey(str);
    }

    @Override // com.shimmerresearch.sensors.AbstractSensor
    public ActionSetting setSettings(String str, Object obj, Configuration.COMMUNICATION_TYPE communication_type) {
        ActionSetting actionSetting = new ActionSetting(communication_type);
        str.hashCode();
        if (str.equals("GSR Range") && communication_type != Configuration.COMMUNICATION_TYPE.BLUETOOTH && communication_type != Configuration.COMMUNICATION_TYPE.DOCK) {
            Configuration.COMMUNICATION_TYPE communication_type2 = Configuration.COMMUNICATION_TYPE.CLASS;
        }
        return actionSetting;
    }

    @Override // com.shimmerresearch.sensors.AbstractSensor
    public LinkedHashMap<String, Object> generateConfigMap() {
        LinkedHashMap<String, Object> linkedHashMap = new LinkedHashMap<>();
        linkedHashMap.put(DatabaseConfigHandle.GSR_RANGE, Double.valueOf(getGSRRange()));
        return linkedHashMap;
    }

    @Override // com.shimmerresearch.sensors.AbstractSensor
    public void parseConfigMap(LinkedHashMap<String, Object> linkedHashMap) {
        if (linkedHashMap.containsKey(DatabaseConfigHandle.GSR_RANGE)) {
            setGSRRange(((Double) linkedHashMap.get(DatabaseConfigHandle.GSR_RANGE)).intValue());
        }
    }

    @Override // com.shimmerresearch.sensors.AbstractSensor
    public void checkShimmerConfigBeforeConfiguring() {
        if (isSensorEnabled(19)) {
            return;
        }
        setDefaultGsrSensorConfig(false);
    }

    public static class DatabaseChannelHandles {
        public static final String GSR_CONDUCTANCE = "GSR_Conductance";
        public static final String GSR_GQ = "F5437a_Int_A1_GSR";
        public static final String GSR_RESISTANCE = "F5437a_Int_A1_GSR";
    }

    public static final class DatabaseConfigHandle {
        public static final String GSR_RANGE = "F5437a_Int_A1_GSR_Range";
    }

    public static class ObjectClusterSensorName {
        public static String GSR_ADC_VALUE = "GSR_ADC_Value";
        public static String GSR_CONDUCTANCE = "GSR_Skin_Conductance";
        public static String GSR_GQ = "GSR";
        public static String GSR_RANGE = "GSR_Range";
        public static String GSR_RESISTANCE = "GSR_Skin_Resistance";
    }

    public class GuiLabelConfig {
        public static final String GSR_RANGE = "GSR Range";
        public static final String SAMPLING_RATE_DIVIDER_GSR = "GSR Divider";

        public GuiLabelConfig() {
        }
    }

    public class GuiLabelSensors {
        public static final String GSR = "GSR";

        public GuiLabelSensors() {
        }
    }

    public class LABEL_SENSOR_TILE {
        public static final String GSR = "GSR+";

        public LABEL_SENSOR_TILE() {
        }
    }
}
