package com.shimmerresearch.verisense.sensors;

import com.shimmerresearch.driver.Configuration;
import com.shimmerresearch.driver.ShimmerDevice;
import com.shimmerresearch.driverUtilities.SensorDetailsRef;
import com.shimmerresearch.driverUtilities.SensorGroupingDetails;
import com.shimmerresearch.driverUtilities.ShimmerVerObject;
import com.shimmerresearch.driverUtilities.UtilShimmer;
import com.shimmerresearch.sensors.AbstractSensor;
import com.shimmerresearch.sensors.SensorGSR;
import com.shimmerresearch.verisense.VerisenseDevice;
import com.shimmerresearch.verisense.sensors.SensorBattVoltageVerisense;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import kotlin.UByte$$ExternalSyntheticBackport0;

/* loaded from: classes2.dex */
public class SensorGSRVerisense extends SensorGSR {
    public static final SensorDetailsRef SENSOR_GSR_VERISENSE;
    public static final Map<Integer, SensorDetailsRef> SENSOR_MAP_REF_VERISENSE;
    public static final double[] VERISENSE_PULSE_PLUS_GSR_REF_RESISTORS_KOHMS = {21.0d, 150.0d, 562.0d, 1740.0d};
    public static final int VERISENSE_PULSE_PLUS_GSR_UNCAL_LIMIT_RANGE3 = 1134;
    private static final long serialVersionUID = -3937042079000714506L;

    static {
        SensorDetailsRef sensorDetailsRef = new SensorDetailsRef(32768L, 32768L, "GSR", Configuration.Verisense.CompatibilityInfoForMaps.listOfCompatibleVersionInfoGsr, new ArrayList(), Arrays.asList("GSR Range"), Arrays.asList(SensorGSR.ObjectClusterSensorName.GSR_RESISTANCE, SensorGSR.ObjectClusterSensorName.GSR_CONDUCTANCE, SensorGSR.ObjectClusterSensorName.GSR_RANGE), true);
        SENSOR_GSR_VERISENSE = sensorDetailsRef;
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put(2014, sensorDetailsRef);
        SENSOR_MAP_REF_VERISENSE = Collections.unmodifiableMap(linkedHashMap);
    }

    private SensorBattVoltageVerisense.ADC_OVERSAMPLING_RATES adcOversamplingRate;
    private GSR_RANGE gsrRange;
    private SensorBattVoltageVerisense.ADC_SAMPLING_RATES sensorSamplingRate;

    public SensorGSRVerisense(ShimmerVerObject shimmerVerObject) {
        super(shimmerVerObject);
        this.gsrRange = GSR_RANGE.AUTO_RANGE;
        this.sensorSamplingRate = SensorBattVoltageVerisense.ADC_SAMPLING_RATES.OFF;
        this.adcOversamplingRate = SensorBattVoltageVerisense.ADC_OVERSAMPLING_RATES.DISABLED;
        if (shimmerVerObject.getHardwareVersion() == 68) {
            setCurrentGsrRefResistorsKohms(VERISENSE_PULSE_PLUS_GSR_REF_RESISTORS_KOHMS);
            setCurrentGsrUncalLimitRange3(VERISENSE_PULSE_PLUS_GSR_UNCAL_LIMIT_RANGE3);
        }
    }

    public SensorBattVoltageVerisense.ADC_OVERSAMPLING_RATES getAdcOversamplingRate() {
        return this.adcOversamplingRate;
    }

    public void setAdcOversamplingRate(SensorBattVoltageVerisense.ADC_OVERSAMPLING_RATES adc_oversampling_rates) {
        this.adcOversamplingRate = adc_oversampling_rates;
    }

    public GSR_RANGE getGsrRange() {
        return this.gsrRange;
    }

    public void setGsrRange(GSR_RANGE gsr_range) {
        this.gsrRange = gsr_range;
        super.setGSRRange(gsr_range.configValue.intValue());
    }

    public SensorBattVoltageVerisense.ADC_SAMPLING_RATES getSensorSamplingRate() {
        return this.sensorSamplingRate;
    }

    public void setSensorSamplingRate(SensorBattVoltageVerisense.ADC_SAMPLING_RATES adc_sampling_rates) {
        this.sensorSamplingRate = adc_sampling_rates;
    }

    @Override // com.shimmerresearch.sensors.SensorGSR, com.shimmerresearch.sensors.AbstractSensor
    public void setSensorSamplingRate(double d) {
        setSensorSamplingRate(SensorBattVoltageVerisense.ADC_SAMPLING_RATES.getConfigValueForFreq(d));
        super.setSensorSamplingRate(this.sensorSamplingRate.freqHz);
    }

    @Override // com.shimmerresearch.sensors.SensorGSR, com.shimmerresearch.sensors.AbstractSensor
    public void generateSensorMap() {
        super.createLocalSensorMapWithCustomParser(SENSOR_MAP_REF_VERISENSE, mChannelMapRef);
    }

    @Override // com.shimmerresearch.sensors.SensorGSR, com.shimmerresearch.sensors.AbstractSensor
    public void configBytesParse(ShimmerDevice shimmerDevice, byte[] bArr, Configuration.COMMUNICATION_TYPE communication_type) {
        ConfigByteLayoutGsr configByteLayoutGsr = new ConfigByteLayoutGsr(shimmerDevice, communication_type);
        if (configByteLayoutGsr.idxGsrRange < 0 || configByteLayoutGsr.idxAdcRate < 0) {
            return;
        }
        setGsrRange(GSR_RANGE.getForConfigValue((bArr[configByteLayoutGsr.idxGsrRange] >> configByteLayoutGsr.bitShiftGSRRange) & configByteLayoutGsr.maskGSRRange));
        setSensorSamplingRate(SensorBattVoltageVerisense.ADC_SAMPLING_RATES.getForConfigValue(bArr[configByteLayoutGsr.idxAdcRate] & configByteLayoutGsr.maskAdcRate));
    }

    @Override // com.shimmerresearch.sensors.SensorGSR, com.shimmerresearch.sensors.AbstractSensor
    public void configBytesGenerate(ShimmerDevice shimmerDevice, byte[] bArr, Configuration.COMMUNICATION_TYPE communication_type) {
        ConfigByteLayoutGsr configByteLayoutGsr = new ConfigByteLayoutGsr(shimmerDevice, communication_type);
        if (configByteLayoutGsr.idxGsrRange < 0 || configByteLayoutGsr.idxAdcRate < 0) {
            return;
        }
        int i = configByteLayoutGsr.idxGsrRange;
        bArr[i] = (byte) (bArr[i] & (~(configByteLayoutGsr.maskGSRRange << configByteLayoutGsr.bitShiftGSRRange)));
        int i2 = configByteLayoutGsr.idxGsrRange;
        bArr[i2] = (byte) (bArr[i2] | ((byte) ((getGsrRange().configValue.intValue() & configByteLayoutGsr.maskGSRRange) << configByteLayoutGsr.bitShiftGSRRange)));
        int i3 = configByteLayoutGsr.idxAdcRate;
        bArr[i3] = (byte) (bArr[i3] & (~configByteLayoutGsr.maskAdcRate));
        int i4 = configByteLayoutGsr.idxAdcRate;
        bArr[i4] = (byte) (bArr[i4] | (configByteLayoutGsr.maskAdcRate & ((byte) getSensorSamplingRate().configValue)));
    }

    @Override // com.shimmerresearch.sensors.SensorGSR, com.shimmerresearch.sensors.AbstractSensor
    public Object setConfigValueUsingConfigLabel(Integer num, String str, Object obj) {
        str.hashCode();
        if (str.equals(AbstractSensor.GuiLabelConfigCommon.RATE)) {
            setSensorSamplingRate(((Double) obj).doubleValue());
            return obj;
        }
        super.setConfigValueUsingConfigLabel(num, str, obj);
        return null;
    }

    @Override // com.shimmerresearch.sensors.SensorGSR, com.shimmerresearch.sensors.AbstractSensor
    public Object getConfigValueUsingConfigLabel(Integer num, String str) {
        str.hashCode();
        if (str.equals(AbstractSensor.GuiLabelConfigCommon.RATE)) {
            return Double.valueOf(getSensorSamplingRateHz());
        }
        return super.getConfigValueUsingConfigLabel(num, str);
    }

    @Override // com.shimmerresearch.sensors.AbstractSensor
    public List<ISensorConfig> getSensorConfig() {
        List<ISensorConfig> sensorConfig = super.getSensorConfig();
        sensorConfig.add(getGsrRange());
        sensorConfig.add(getAdcOversamplingRate());
        sensorConfig.add(getSensorSamplingRate());
        return sensorConfig;
    }

    @Override // com.shimmerresearch.sensors.AbstractSensor
    public void setSensorConfig(ISensorConfig iSensorConfig) {
        if (iSensorConfig instanceof GSR_RANGE) {
            setGsrRange((GSR_RANGE) iSensorConfig);
            return;
        }
        if (iSensorConfig instanceof SensorBattVoltageVerisense.ADC_SAMPLING_RATES) {
            setSensorSamplingRate((SensorBattVoltageVerisense.ADC_SAMPLING_RATES) iSensorConfig);
        } else if (iSensorConfig instanceof SensorBattVoltageVerisense.ADC_OVERSAMPLING_RATES) {
            setAdcOversamplingRate((SensorBattVoltageVerisense.ADC_OVERSAMPLING_RATES) iSensorConfig);
        } else {
            super.setSensorConfig(iSensorConfig);
        }
    }

    @Override // com.shimmerresearch.sensors.AbstractSensor
    public void setSamplingRateFromShimmer(double d) {
        if (UByte$$ExternalSyntheticBackport0.m38878m(d)) {
            setSensorSamplingRate(SensorBattVoltageVerisense.ADC_SAMPLING_RATES.getConfigValueForFreq(d));
            super.setSamplingRateFromShimmer(this.sensorSamplingRate.freqHz);
        }
    }

    public double getSensorSamplingRateHz() {
        return this.sensorSamplingRate.freqHz;
    }

    @Override // com.shimmerresearch.sensors.SensorGSR, com.shimmerresearch.sensors.AbstractSensor
    public boolean setDefaultConfigForSensor(int i, boolean z) {
        if (!this.mSensorMap.containsKey(Integer.valueOf(i))) {
            return false;
        }
        if (z) {
            setSensorSamplingRate(SensorBattVoltageVerisense.ADC_SAMPLING_RATES.FREQ_51_2_HZ);
        } else {
            setSensorSamplingRate(SensorBattVoltageVerisense.ADC_SAMPLING_RATES.OFF);
        }
        setGsrRange(GSR_RANGE.AUTO_RANGE);
        setAdcOversamplingRate(SensorBattVoltageVerisense.ADC_OVERSAMPLING_RATES.X64);
        return true;
    }

    @Override // com.shimmerresearch.sensors.SensorGSR, com.shimmerresearch.sensors.AbstractSensor
    public void generateSensorGroupMapping() {
        int iOrdinal = Configuration.Verisense.LABEL_SENSOR_TILE.GSR.ordinal();
        if (this.mShimmerVerObject.isShimmerGenVerisense()) {
            this.mSensorGroupingMap.put(Integer.valueOf(iOrdinal), new SensorGroupingDetails(SensorGSR.LABEL_SENSOR_TILE.GSR, Arrays.asList(2014), Configuration.Verisense.CompatibilityInfoForMaps.listOfCompatibleVersionInfoGsr));
        }
        super.updateSensorGroupingMap();
    }

    public enum GSR_RANGE implements ISensorConfig {
        RANGE_0("8kΩ to 63kΩ", 0, 8.0d, 63.0d),
        RANGE_1("63kΩ to 220kΩ", 1, 63.0d, 220.0d),
        RANGE_2("220kΩ to 680kΩ", 2, 220.0d, 680.0d),
        RANGE_3("680kΩ to 4.7MΩ", 3, 680.0d, 4700.0d),
        AUTO_RANGE("Auto-Range", 4, 8.0d, 4700.0d);

        static Map<Integer, GSR_RANGE> BY_CONFIG_VALUE;
        static Map<String, Integer> REF_MAP = new HashMap();

        static {
            for (GSR_RANGE gsr_range : values()) {
                REF_MAP.put(gsr_range.label, gsr_range.configValue);
            }
            BY_CONFIG_VALUE = new HashMap();
            for (GSR_RANGE gsr_range2 : values()) {
                BY_CONFIG_VALUE.put(gsr_range2.configValue, gsr_range2);
            }
        }

        Integer configValue;
        String label;
        double maxInputKohms;
        double minInputKohms;

        GSR_RANGE(String str, int i, double d, double d2) {
            this.label = str;
            this.configValue = Integer.valueOf(i);
            this.minInputKohms = d;
            this.maxInputKohms = d2;
        }

        public static String[] getLabels() {
            return (String[]) REF_MAP.keySet().toArray(new String[REF_MAP.keySet().size()]);
        }

        public static Integer[] getConfigValues() {
            return (Integer[]) REF_MAP.values().toArray(new Integer[REF_MAP.values().size()]);
        }

        public static GSR_RANGE getForConfigValue(int i) {
            return BY_CONFIG_VALUE.get(Integer.valueOf(UtilShimmer.nudgeInteger(i, RANGE_0.configValue.intValue(), AUTO_RANGE.configValue.intValue())));
        }
    }

    private class ConfigByteLayoutGsr {
        public int idxAdcRate;
        public int idxGsrRange;
        public int maskAdcRate = 63;
        public int maskGSRRange = 7;
        public int bitShiftGSRRange = 5;

        public ConfigByteLayoutGsr(ShimmerDevice shimmerDevice, Configuration.COMMUNICATION_TYPE communication_type) {
            this.idxAdcRate = -1;
            this.idxGsrRange = -1;
            if ((shimmerDevice instanceof VerisenseDevice) && ((VerisenseDevice) shimmerDevice).isPayloadDesignV12orAbove()) {
                if (communication_type == Configuration.COMMUNICATION_TYPE.SD) {
                    this.idxAdcRate = 24;
                    this.idxGsrRange = 16;
                } else {
                    this.idxAdcRate = 50;
                    this.idxGsrRange = 51;
                }
            }
        }
    }
}
