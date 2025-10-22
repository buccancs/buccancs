package com.shimmerresearch.verisense.sensors;

import com.shimmerresearch.driver.Configuration;
import com.shimmerresearch.driverUtilities.ConfigOptionDetails;
import com.shimmerresearch.driverUtilities.ConfigOptionDetailsSensor;
import com.shimmerresearch.driverUtilities.UtilShimmer;
import com.shimmerresearch.sensors.AbstractSensor;
import com.shimmerresearch.sensors.ActionSetting;
import com.shimmerresearch.verisense.VerisenseDevice;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes2.dex */
public abstract class SensorMAX86XXX extends AbstractSensor {
    public static final int MAX_FIFOS_IN_PAYLOAD_1_CHANNEL = 641;
    public static final int MAX_FIFOS_IN_PAYLOAD_2_CHANNELS = 320;
    public static final int MAX_FIFOS_IN_PAYLOAD_3_CHANNELS = 213;
    public static final int MAX_FIFOS_IN_PAYLOAD_4_CHANNELS = 160;
    public static final int MAX_SAMPLES_PER_FIFO = 17;
    public static final double[] LED_RANGE_STEPS_MILLIAMPS = {0.2d, 0.4d, 0.6d, 0.8d};
    public static final ConfigOptionDetailsSensor CONFIG_OPTION_PPG_PULSE_WIDTH = new ConfigOptionDetailsSensor(GuiLabelConfigCommonMax86.MAX86XXX_PPG_PULSE_WIDTH, DatabaseConfigHandle.MAX86XXX_PPG_PULSE_WIDTH, MAX86XXX_PULSE_WIDTH.getLabels(), MAX86XXX_PULSE_WIDTH.getConfigValues(), ConfigOptionDetails.GUI_COMPONENT_TYPE.COMBOBOX, Configuration.Verisense.CompatibilityInfoForMaps.listOfCompatibleVersionInfoMAX86150);
    public static final ConfigOptionDetailsSensor CONFIG_OPTION_PPG_ADC_RESOLUTION = new ConfigOptionDetailsSensor(GuiLabelConfigCommonMax86.MAX86XXX_PPG_ADC_RESOLUTION, DatabaseConfigHandle.MAX86XXX_PPG_ADC_RESOLUTION, MAX86XXX_ADC_RESOLUTION.getLabels(), MAX86XXX_ADC_RESOLUTION.getConfigValues(), ConfigOptionDetails.GUI_COMPONENT_TYPE.COMBOBOX, Configuration.Verisense.CompatibilityInfoForMaps.listOfCompatibleVersionInfoMAX86150);
    public static final ConfigOptionDetailsSensor CONFIG_OPTION_PPG_SAMPLE_AVG = new ConfigOptionDetailsSensor(GuiLabelConfigCommonMax86.MAX86XXX_PPG_SMPAVE, DatabaseConfigHandle.MAX86XXX_PPG_SMPAVE, MAX86XXX_SAMPLE_AVG.getLabels(), MAX86XXX_SAMPLE_AVG.getConfigValues(), ConfigOptionDetails.GUI_COMPONENT_TYPE.COMBOBOX, Configuration.Verisense.CompatibilityInfoForMaps.listOfCompatibleVersionInfoMAX86150);
    private static final long serialVersionUID = 5304298774921285416L;
    protected MAX86XXX_ADC_RESOLUTION adcResolution;
    protected int ppgLedAmplitudeIrConfigValue;
    protected int ppgLedAmplitudeRangeIr;
    protected int ppgLedAmplitudeRangeRed;
    protected int ppgLedAmplitudeRedConfigValue;
    protected MAX86XXX_PULSE_WIDTH pulseWidth;
    protected MAX86XXX_SAMPLE_AVG sampleAverage;

    public SensorMAX86XXX(AbstractSensor.SENSORS sensors, VerisenseDevice verisenseDevice) {
        super(sensors, verisenseDevice);
        this.adcResolution = MAX86XXX_ADC_RESOLUTION.RESOLUTION_15_BIT;
        this.pulseWidth = MAX86XXX_PULSE_WIDTH.PW_400_US;
        this.sampleAverage = MAX86XXX_SAMPLE_AVG.NO_AVERAGING;
        this.ppgLedAmplitudeRedConfigValue = 0;
        this.ppgLedAmplitudeIrConfigValue = 0;
        this.ppgLedAmplitudeRangeRed = 0;
        this.ppgLedAmplitudeRangeIr = 0;
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
    public LinkedHashMap<String, Object> generateConfigMap() {
        return null;
    }

    @Override // com.shimmerresearch.sensors.AbstractSensor
    public void generateSensorGroupMapping() {
    }

    public MAX86XXX_ADC_RESOLUTION getPpgAdcResolution() {
        return this.adcResolution;
    }

    public void setPpgAdcResolution(MAX86XXX_ADC_RESOLUTION max86xxx_adc_resolution) {
        this.adcResolution = max86xxx_adc_resolution;
    }

    public int getPpgLedAmplitudeIrConfigValue() {
        return this.ppgLedAmplitudeIrConfigValue;
    }

    public void setPpgLedAmplitudeIrConfigValue(int i) {
        if (i < 0 || i >= 256) {
            return;
        }
        this.ppgLedAmplitudeIrConfigValue = i;
    }

    public byte getPpgLedAmplitudeRangeConfigValue() {
        return (byte) (((byte) (this.ppgLedAmplitudeRangeIr & 3)) | ((this.ppgLedAmplitudeRangeRed & 3) << 2));
    }

    public void setPpgLedAmplitudeRangeConfigValue(byte b) {
        this.ppgLedAmplitudeRangeIr = b & 3;
        this.ppgLedAmplitudeRangeRed = (b >> 2) & 3;
    }

    public int getPpgLedAmplitudeRedConfigValue() {
        return this.ppgLedAmplitudeRedConfigValue;
    }

    public void setPpgLedAmplitudeRedConfigValue(int i) {
        if (i < 0 || i >= 256) {
            return;
        }
        this.ppgLedAmplitudeRedConfigValue = i;
    }

    public MAX86XXX_PULSE_WIDTH getPpgPulseWidth() {
        return this.pulseWidth;
    }

    public void setPpgPulseWidth(MAX86XXX_PULSE_WIDTH max86xxx_pulse_width) {
        this.pulseWidth = max86xxx_pulse_width;
    }

    public MAX86XXX_SAMPLE_AVG getPpgSampleAverage() {
        return this.sampleAverage;
    }

    public void setPpgSampleAverage(MAX86XXX_SAMPLE_AVG max86xxx_sample_avg) {
        this.sampleAverage = max86xxx_sample_avg;
    }

    public abstract double getSamplingRateFreq();

    @Override // com.shimmerresearch.sensors.AbstractSensor
    public Object getSettings(String str, Configuration.COMMUNICATION_TYPE communication_type) {
        return null;
    }

    @Override // com.shimmerresearch.sensors.AbstractSensor
    public void parseConfigMap(LinkedHashMap<String, Object> linkedHashMap) {
    }

    @Override // com.shimmerresearch.sensors.AbstractSensor
    public boolean processResponse(int i, Object obj, Configuration.COMMUNICATION_TYPE communication_type) {
        return false;
    }

    @Override // com.shimmerresearch.sensors.AbstractSensor
    public ActionSetting setSettings(String str, Object obj, Configuration.COMMUNICATION_TYPE communication_type) {
        return null;
    }

    public void updateCurrentPpgRedCalibInUse() {
    }

    @Override // com.shimmerresearch.sensors.AbstractSensor
    public void generateConfigOptionsMap() {
        addConfigOption(CONFIG_OPTION_PPG_ADC_RESOLUTION);
        addConfigOption(CONFIG_OPTION_PPG_PULSE_WIDTH);
        addConfigOption(CONFIG_OPTION_PPG_SAMPLE_AVG);
    }

    protected double calibratePpg(double d) {
        return ((d / this.adcResolution.bitShift) * this.adcResolution.lsb) / 1000.0d;
    }

    @Override // com.shimmerresearch.sensors.AbstractSensor
    public Object setConfigValueUsingConfigLabel(Integer num, String str, Object obj) {
        str.hashCode();
        switch (str) {
            case "Sample Average":
                setPpgSampleAverageConfigValue(((Integer) obj).intValue());
                return null;
            case "Red LED Amplitude":
                setPpgLedAmplitudeRedConfigValue(((Integer) obj).intValue());
                return null;
            case "IR LED Amplitude":
                setPpgLedAmplitudeIrConfigValue(((Integer) obj).intValue());
                return null;
            case "ADC Range":
                setPpgAdcResolutionConfigValue(((Integer) obj).intValue());
                return null;
            case "Pulse Width":
                setPpgPulseWidthConfigValue(((Integer) obj).intValue());
                return null;
            default:
                return super.setConfigValueUsingConfigLabelCommon(num, str, obj);
        }
    }

    @Override // com.shimmerresearch.sensors.AbstractSensor
    public Object getConfigValueUsingConfigLabel(Integer num, String str) {
        str.hashCode();
        switch (str) {
            case "Sample Average":
                return Integer.valueOf(getPpgSampleAverageConfigValue());
            case "Red LED Amplitude":
                return Integer.valueOf(getPpgLedAmplitudeRedConfigValue());
            case "Rate":
                return Double.valueOf(getSamplingRateFreq());
            case "IR LED Amplitude":
                return Integer.valueOf(getPpgLedAmplitudeIrConfigValue());
            case "ADC Range":
                return Integer.valueOf(getPpgAdcResolutionConfigValue());
            case "Pulse Width":
                return Integer.valueOf(getPpgPulseWidthConfigValue());
            default:
                return super.getConfigValueUsingConfigLabelCommon(num, str);
        }
    }

    @Override // com.shimmerresearch.sensors.AbstractSensor
    public boolean setDefaultConfigForSensor(int i, boolean z) {
        if (!this.mSensorMap.containsKey(Integer.valueOf(i))) {
            return false;
        }
        setPpgAdcResolution(MAX86XXX_ADC_RESOLUTION.RESOLUTION_15_BIT);
        setPpgPulseWidth(MAX86XXX_PULSE_WIDTH.PW_400_US);
        setPpgSampleAverage(MAX86XXX_SAMPLE_AVG.NO_AVERAGING);
        return true;
    }

    @Override // com.shimmerresearch.sensors.AbstractSensor
    public void initialise() {
        super.initialise();
        updateCurrentPpgRedCalibInUse();
    }

    @Override // com.shimmerresearch.sensors.AbstractSensor
    public void generateCalibMap() {
        super.generateCalibMap();
    }

    public int getPpgPulseWidthConfigValue() {
        return getPpgPulseWidth().configValue.intValue();
    }

    public void setPpgPulseWidthConfigValue(int i) {
        setPpgPulseWidth(MAX86XXX_PULSE_WIDTH.getForConfigValue(i));
    }

    public int getPpgSampleAverageConfigValue() {
        return getPpgSampleAverage().configValue.intValue();
    }

    public void setPpgSampleAverageConfigValue(int i) {
        setPpgSampleAverage(MAX86XXX_SAMPLE_AVG.getForConfigValue(i));
    }

    public int getPpgAdcResolutionConfigValue() {
        return getPpgAdcResolution().configValue.intValue();
    }

    public void setPpgAdcResolutionConfigValue(int i) {
        setPpgAdcResolution(MAX86XXX_ADC_RESOLUTION.getForConfigValue(i));
    }

    public String getPpgLedAmplitudeRedString() {
        return calculatePpgLedAmplitude(this.ppgLedAmplitudeRedConfigValue, this.ppgLedAmplitudeRangeRed);
    }

    public String getPpgLedAmplitudeIrString() {
        return calculatePpgLedAmplitude(this.ppgLedAmplitudeIrConfigValue, this.ppgLedAmplitudeRangeIr);
    }

    protected String calculatePpgLedAmplitude(int i, int i2) {
        return String.format("%.1f", Double.valueOf(i * LED_RANGE_STEPS_MILLIAMPS[i2])) + " mA";
    }

    public double convertRateStringToDouble(String str) {
        if (str.contains(Configuration.CHANNEL_UNITS.FREQUENCY)) {
            return Double.parseDouble(str.replace(Configuration.CHANNEL_UNITS.FREQUENCY, ""));
        }
        return 0.0d;
    }

    @Override // com.shimmerresearch.sensors.AbstractSensor
    public List<ISensorConfig> getSensorConfig() {
        List<ISensorConfig> sensorConfig = super.getSensorConfig();
        sensorConfig.add(getPpgAdcResolution());
        sensorConfig.add(getPpgPulseWidth());
        sensorConfig.add(getPpgSampleAverage());
        return sensorConfig;
    }

    @Override // com.shimmerresearch.sensors.AbstractSensor
    public void setSensorConfig(ISensorConfig iSensorConfig) {
        if (iSensorConfig instanceof MAX86XXX_ADC_RESOLUTION) {
            setPpgAdcResolution((MAX86XXX_ADC_RESOLUTION) iSensorConfig);
            return;
        }
        if (iSensorConfig instanceof MAX86XXX_PULSE_WIDTH) {
            setPpgPulseWidth((MAX86XXX_PULSE_WIDTH) iSensorConfig);
        } else if (iSensorConfig instanceof MAX86XXX_SAMPLE_AVG) {
            setPpgSampleAverage((MAX86XXX_SAMPLE_AVG) iSensorConfig);
        } else {
            super.setSensorConfig(iSensorConfig);
        }
    }

    public enum MAX86XXX_ADC_RESOLUTION implements ISensorConfig {
        RESOLUTION_12_BIT("12-bit", 0, Math.pow(2.0d, 7.0d), 7.8125d),
        RESOLUTION_13_BIT("13-bit", 1, Math.pow(2.0d, 6.0d), 15.625d),
        RESOLUTION_14_BIT("14-bit", 2, Math.pow(2.0d, 5.0d), 31.25d),
        RESOLUTION_15_BIT("15-bit", 3, Math.pow(2.0d, 4.0d), 62.5d);

        static Map<Integer, MAX86XXX_ADC_RESOLUTION> BY_CONFIG_VALUE;
        static Map<String, Integer> REF_MAP = new HashMap();

        static {
            for (MAX86XXX_ADC_RESOLUTION max86xxx_adc_resolution : values()) {
                REF_MAP.put(max86xxx_adc_resolution.label, max86xxx_adc_resolution.configValue);
            }
            BY_CONFIG_VALUE = new HashMap();
            for (MAX86XXX_ADC_RESOLUTION max86xxx_adc_resolution2 : values()) {
                BY_CONFIG_VALUE.put(max86xxx_adc_resolution2.configValue, max86xxx_adc_resolution2);
            }
        }

        double bitShift;
        Integer configValue;
        String label;
        double lsb;

        MAX86XXX_ADC_RESOLUTION(String str, int i, double d, double d2) {
            this.label = str;
            this.configValue = Integer.valueOf(i);
            this.bitShift = d;
            this.lsb = d2;
        }

        public static String[] getLabels() {
            return (String[]) REF_MAP.keySet().toArray(new String[REF_MAP.keySet().size()]);
        }

        public static Integer[] getConfigValues() {
            return (Integer[]) REF_MAP.values().toArray(new Integer[REF_MAP.values().size()]);
        }

        public static MAX86XXX_ADC_RESOLUTION getForConfigValue(int i) {
            return BY_CONFIG_VALUE.get(Integer.valueOf(UtilShimmer.nudgeInteger(i, RESOLUTION_12_BIT.configValue.intValue(), RESOLUTION_15_BIT.configValue.intValue())));
        }
    }

    public enum MAX86XXX_PULSE_WIDTH implements ISensorConfig {
        PW_50_US("50us", 0),
        PW_100_US("100us", 1),
        PW_200_US("200us", 2),
        PW_400_US("400us", 3);

        static Map<Integer, MAX86XXX_PULSE_WIDTH> BY_CONFIG_VALUE;
        static Map<String, Integer> REF_MAP = new HashMap();

        static {
            for (MAX86XXX_PULSE_WIDTH max86xxx_pulse_width : values()) {
                REF_MAP.put(max86xxx_pulse_width.label, max86xxx_pulse_width.configValue);
            }
            BY_CONFIG_VALUE = new HashMap();
            for (MAX86XXX_PULSE_WIDTH max86xxx_pulse_width2 : values()) {
                BY_CONFIG_VALUE.put(max86xxx_pulse_width2.configValue, max86xxx_pulse_width2);
            }
        }

        Integer configValue;
        String label;

        MAX86XXX_PULSE_WIDTH(String str, int i) {
            this.label = str;
            this.configValue = Integer.valueOf(i);
        }

        public static String[] getLabels() {
            return (String[]) REF_MAP.keySet().toArray(new String[REF_MAP.keySet().size()]);
        }

        public static Integer[] getConfigValues() {
            return (Integer[]) REF_MAP.values().toArray(new Integer[REF_MAP.values().size()]);
        }

        public static MAX86XXX_PULSE_WIDTH getForConfigValue(int i) {
            return BY_CONFIG_VALUE.get(Integer.valueOf(UtilShimmer.nudgeInteger(i, PW_50_US.configValue.intValue(), PW_400_US.configValue.intValue())));
        }
    }

    public enum MAX86XXX_SAMPLE_AVG implements ISensorConfig {
        NO_AVERAGING("No averaging", 0),
        SAMPLES_2("2", 1),
        SAMPLES_4("4", 2),
        SAMPLES_8("8", 3),
        SAMPLES_16("16", 4),
        SAMPLES_32("32", 5);

        static Map<Integer, MAX86XXX_SAMPLE_AVG> BY_CONFIG_VALUE;
        static Map<String, Integer> REF_MAP = new HashMap();

        static {
            for (MAX86XXX_SAMPLE_AVG max86xxx_sample_avg : values()) {
                REF_MAP.put(max86xxx_sample_avg.label, max86xxx_sample_avg.configValue);
            }
            BY_CONFIG_VALUE = new HashMap();
            for (MAX86XXX_SAMPLE_AVG max86xxx_sample_avg2 : values()) {
                BY_CONFIG_VALUE.put(max86xxx_sample_avg2.configValue, max86xxx_sample_avg2);
            }
        }

        Integer configValue;
        String label;

        MAX86XXX_SAMPLE_AVG(String str, int i) {
            this.label = str;
            this.configValue = Integer.valueOf(i);
        }

        public static String[] getLabels() {
            return (String[]) REF_MAP.keySet().toArray(new String[REF_MAP.keySet().size()]);
        }

        public static Integer[] getConfigValues() {
            return (Integer[]) REF_MAP.values().toArray(new Integer[REF_MAP.values().size()]);
        }

        public static MAX86XXX_SAMPLE_AVG getForConfigValue(int i) {
            return BY_CONFIG_VALUE.get(Integer.valueOf(UtilShimmer.nudgeInteger(i, NO_AVERAGING.configValue.intValue(), SAMPLES_32.configValue.intValue())));
        }
    }

    public static final class DatabaseConfigHandle {
        public static final String MAX86XXX_PPG_ADC_RESOLUTION = "MAX86XXX_PPG_ADC_RANGE";
        public static final String MAX86XXX_PPG_PULSE_AMPLITUDE = "MAX86XXX_PPG_PULSE_AMPLITUDE";
        public static final String MAX86XXX_PPG_PULSE_WIDTH = "MAX86XXX_PPG_PULSE_WIDTH";
        public static final String MAX86XXX_PPG_SMPAVE = "MAX86XXX_PPG_SAMPLE_AVERAGE";
        public static final String MAX86XXX_RATE = "MAX86XXX_PPG_Rate";
    }

    public static class ObjectClusterSensorNameCommon {
        public static String MAX86XXX_PPG_IR = "PPG_IR";
        public static String MAX86XXX_PPG_RED = "PPG_Red";
    }

    public class GuiLabelSensorsCommon {
        public static final String PPG_IR = "PPG IR";
        public static final String PPG_RED = "PPG Red";

        public GuiLabelSensorsCommon() {
        }
    }

    public class GuiLabelConfigCommonMax86 {
        public static final String MAX86XXX_PPG_ADC_RESOLUTION = "ADC Range";
        public static final String MAX86XXX_PPG_LED_IR_AMPLITUDE = "IR LED Amplitude";
        public static final String MAX86XXX_PPG_LED_RED_AMPLITUDE = "Red LED Amplitude";
        public static final String MAX86XXX_PPG_PULSE_WIDTH = "Pulse Width";
        public static final String MAX86XXX_PPG_RATE = "PPG Rate";
        public static final String MAX86XXX_PPG_SMPAVE = "Sample Average";

        public GuiLabelConfigCommonMax86() {
        }
    }
}
