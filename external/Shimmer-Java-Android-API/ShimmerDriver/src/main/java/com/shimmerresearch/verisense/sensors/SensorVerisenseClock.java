package com.shimmerresearch.verisense.sensors;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

import com.shimmerresearch.driver.Configuration.CHANNEL_UNITS;
import com.shimmerresearch.driver.Configuration.COMMUNICATION_TYPE;
import com.shimmerresearch.driver.Configuration.Verisense;
import com.shimmerresearch.bluetooth.SystemTimestampPlot;
import com.shimmerresearch.driver.Configuration;
import com.shimmerresearch.driver.ObjectCluster;
import com.shimmerresearch.driver.ShimmerDevice;
import com.shimmerresearch.driverUtilities.ChannelDetails;
import com.shimmerresearch.driverUtilities.SensorDetails;
import com.shimmerresearch.driverUtilities.SensorDetailsRef;
import com.shimmerresearch.driverUtilities.ChannelDetails.CHANNEL_TYPE;
import com.shimmerresearch.sensors.AbstractSensor;
import com.shimmerresearch.sensors.ActionSetting;
import com.shimmerresearch.sensors.SensorShimmerClock;
import com.shimmerresearch.sensors.SensorSystemTimeStamp;
import com.shimmerresearch.sensors.ShimmerStreamingProperties;
import com.shimmerresearch.sensors.SensorShimmerClock.GuiLabelSensors;
import com.shimmerresearch.verisense.VerisenseDevice;
import com.shimmerresearch.verisense.payloaddesign.AsmBinaryFileConstants;

public class SensorVerisenseClock extends AbstractSensor {

    public static final String CHANNEL_UNITS_TIMESTAMP = "Unix_" + CHANNEL_UNITS.MILLISECONDS;
    public static final String CHANNEL_UNITS_TIMESTAMP_LOCAL = CHANNEL_UNITS_TIMESTAMP + "_plus_local_time_zone_offset";
    public static final ChannelDetails channelTimestampLocal = new ChannelDetails(
            SensorShimmerClock.ObjectClusterSensorName.TIMESTAMP,
            SensorShimmerClock.ObjectClusterSensorName.TIMESTAMP,
            SensorShimmerClock.DatabaseChannelHandles.TIMESTAMP,
            CHANNEL_UNITS_TIMESTAMP_LOCAL,
            Arrays.asList(CHANNEL_TYPE.CAL), false, true);
    public static final ChannelDetails channelTimestamp = new ChannelDetails(
            SensorShimmerClock.ObjectClusterSensorName.TIMESTAMP,
            SensorShimmerClock.ObjectClusterSensorName.TIMESTAMP,
            SensorShimmerClock.DatabaseChannelHandles.TIMESTAMP,
            CHANNEL_UNITS_TIMESTAMP,
            Arrays.asList(CHANNEL_TYPE.CAL), false, true);
    public static final SensorDetailsRef sensorVerisenseClock = new SensorDetailsRef(
            GuiLabelSensors.TIMESTAMP,
            Verisense.CompatibilityInfoForMaps.listOfCompatibleVersionInfoVbatt,
            Arrays.asList(SensorShimmerClock.ObjectClusterSensorName.TIMESTAMP));
    public static final Map<Integer, SensorDetailsRef> mSensorMapRef;
    private static final long serialVersionUID = -2624896596566484434L;

    static {
        Map<Integer, SensorDetailsRef> aMap = new LinkedHashMap<Integer, SensorDetailsRef>();
        aMap.put(Configuration.Verisense.SENSOR_ID.VERISENSE_TIMESTAMP, SensorVerisenseClock.sensorVerisenseClock);
        mSensorMapRef = Collections.unmodifiableMap(aMap);
    }

    private SystemTimestampPlot systemTimestampPlot = new SystemTimestampPlot();

    {
        sensorVerisenseClock.mIsApiSensor = true;
    }

    public SensorVerisenseClock(VerisenseDevice verisenseDevice) {
        super(SENSORS.CLOCK, verisenseDevice);
        initialise();
    }

    public static double convertRtcMinutesAndTicksToMs(long rtcEndTimeMinutes, long rtcEndTimeTicks) {
        double rtcEndTimeMs = ((rtcEndTimeMinutes * 60) + ((double) rtcEndTimeTicks / AsmBinaryFileConstants.TICKS_PER_SECOND)) * 1000;
        return rtcEndTimeMs;
    }

    public static ChannelDetails getChannelDetailsForFwVerVersion(ShimmerDevice shimmerDevice) {
        if (shimmerDevice instanceof VerisenseDevice) {
            return getChannelDetails(((VerisenseDevice) shimmerDevice).isCsvHeaderDesignAzMarkingPoint());
        }
        return null;
    }

    public static ChannelDetails getChannelDetails(boolean isLegacyCsvHeaderDesign) {
        if (isLegacyCsvHeaderDesign) {
            return SensorVerisenseClock.channelTimestamp;
        } else {
            return SensorVerisenseClock.channelTimestampLocal;
        }
    }

    @Override
    public void generateSensorMap() {
        Map<String, ChannelDetails> channelMapRef = new LinkedHashMap<String, ChannelDetails>();


        channelMapRef.put(SensorShimmerClock.ObjectClusterSensorName.TIMESTAMP, getChannelDetailsForFwVerVersion(mShimmerDevice));


        super.createLocalSensorMapWithCustomParser(mSensorMapRef, channelMapRef);
    }

    @Override
    public void generateConfigOptionsMap() {

    }

    @Override
    public void generateSensorGroupMapping() {

    }

    @Override
    public ObjectCluster processDataCustom(SensorDetails sensorDetails, byte[] rawData, COMMUNICATION_TYPE commType, ObjectCluster objectCluster, boolean isTimeSyncEnabled, double pctimeStampMs) {
        if (sensorDetails.isEnabled(commType)) {
            if (sensorDetails.mSensorDetailsRef.mGuiFriendlyLabel.equals(GuiLabelSensors.TIMESTAMP)) {

                objectCluster.addCalData(getChannelDetailsForFwVerVersion(mShimmerDevice), pctimeStampMs);
                objectCluster.setTimeStampMilliSecs(pctimeStampMs);

                if (commType != COMMUNICATION_TYPE.SD) {
                    objectCluster = systemTimestampPlot.processSystemTimestampPlot(objectCluster);
                }

            }
        }
        return objectCluster;
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
        return null;
    }

    @Override
    public Object getConfigValueUsingConfigLabel(Integer sensorId, String configLabel) {
        return null;
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

    public SystemTimestampPlot getSystemTimestampPlot() {
        return systemTimestampPlot;
    }

}
