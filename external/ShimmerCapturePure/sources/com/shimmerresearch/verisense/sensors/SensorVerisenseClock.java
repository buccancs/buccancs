package com.shimmerresearch.verisense.sensors;

import com.shimmerresearch.bluetooth.SystemTimestampPlot;
import com.shimmerresearch.driver.Configuration;
import com.shimmerresearch.driver.ObjectCluster;
import com.shimmerresearch.driver.ShimmerDevice;
import com.shimmerresearch.driverUtilities.ChannelDetails;
import com.shimmerresearch.driverUtilities.SensorDetails;
import com.shimmerresearch.driverUtilities.SensorDetailsRef;
import com.shimmerresearch.sensors.AbstractSensor;
import com.shimmerresearch.sensors.ActionSetting;
import com.shimmerresearch.sensors.SensorShimmerClock;
import com.shimmerresearch.verisense.VerisenseDevice;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes2.dex */
public class SensorVerisenseClock extends AbstractSensor {
    public static final Map<Integer, SensorDetailsRef> mSensorMapRef;
    public static final SensorDetailsRef sensorVerisenseClock;
    public static final String CHANNEL_UNITS_TIMESTAMP = "Unix_ms";
    public static final ChannelDetails channelTimestamp = new ChannelDetails("Timestamp", "Timestamp", SensorShimmerClock.DatabaseChannelHandles.TIMESTAMP, CHANNEL_UNITS_TIMESTAMP, Arrays.asList(ChannelDetails.CHANNEL_TYPE.CAL), false, true);
    public static final String CHANNEL_UNITS_TIMESTAMP_LOCAL = "Unix_ms_plus_local_time_zone_offset";
    public static final ChannelDetails channelTimestampLocal = new ChannelDetails("Timestamp", "Timestamp", SensorShimmerClock.DatabaseChannelHandles.TIMESTAMP, CHANNEL_UNITS_TIMESTAMP_LOCAL, Arrays.asList(ChannelDetails.CHANNEL_TYPE.CAL), false, true);
    private static final long serialVersionUID = -2624896596566484434L;

    static {
        SensorDetailsRef sensorDetailsRef = new SensorDetailsRef("Timestamp", Configuration.Verisense.CompatibilityInfoForMaps.listOfCompatibleVersionInfoVbatt, (List<String>) Arrays.asList("Timestamp"));
        sensorVerisenseClock = sensorDetailsRef;
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put(2000, sensorDetailsRef);
        mSensorMapRef = Collections.unmodifiableMap(linkedHashMap);
    }

    private SystemTimestampPlot systemTimestampPlot;

    public SensorVerisenseClock(VerisenseDevice verisenseDevice) {
        super(AbstractSensor.SENSORS.CLOCK, verisenseDevice);
        this.systemTimestampPlot = new SystemTimestampPlot();
        sensorVerisenseClock.mIsApiSensor = true;
        initialise();
    }

    public static double convertRtcMinutesAndTicksToMs(long j, long j2) {
        return ((j * 60) + (j2 / 32768.0d)) * 1000.0d;
    }

    public static ChannelDetails getChannelDetails(boolean z) {
        return z ? channelTimestamp : channelTimestampLocal;
    }

    public static ChannelDetails getChannelDetailsForFwVerVersion(ShimmerDevice shimmerDevice) {
        if (shimmerDevice instanceof VerisenseDevice) {
            return getChannelDetails(((VerisenseDevice) shimmerDevice).isCsvHeaderDesignAzMarkingPoint());
        }
        return null;
    }

    @Override // com.shimmerresearch.sensors.AbstractSensor
    public boolean checkConfigOptionValues(String str) {
        return false;
    }

    @Override // com.shimmerresearch.sensors.AbstractSensor
    public void checkShimmerConfigBeforeConfiguring() {
    }

    @Override // com.shimmerresearch.sensors.AbstractSensor
    public void configBytesGenerate(ShimmerDevice shimmerDevice, byte[] bArr, Configuration.COMMUNICATION_TYPE communication_type) {
    }

    @Override // com.shimmerresearch.sensors.AbstractSensor
    public void configBytesParse(ShimmerDevice shimmerDevice, byte[] bArr, Configuration.COMMUNICATION_TYPE communication_type) {
    }

    @Override // com.shimmerresearch.sensors.AbstractSensor
    public LinkedHashMap<String, Object> generateConfigMap() {
        return null;
    }

    @Override // com.shimmerresearch.sensors.AbstractSensor
    public void generateConfigOptionsMap() {
    }

    @Override // com.shimmerresearch.sensors.AbstractSensor
    public void generateSensorGroupMapping() {
    }

    @Override // com.shimmerresearch.sensors.AbstractSensor
    public Object getConfigValueUsingConfigLabel(Integer num, String str) {
        return null;
    }

    @Override // com.shimmerresearch.sensors.AbstractSensor
    public Object getSettings(String str, Configuration.COMMUNICATION_TYPE communication_type) {
        return null;
    }

    public SystemTimestampPlot getSystemTimestampPlot() {
        return this.systemTimestampPlot;
    }

    @Override // com.shimmerresearch.sensors.AbstractSensor
    public void parseConfigMap(LinkedHashMap<String, Object> linkedHashMap) {
    }

    @Override // com.shimmerresearch.sensors.AbstractSensor
    public boolean processResponse(int i, Object obj, Configuration.COMMUNICATION_TYPE communication_type) {
        return false;
    }

    @Override // com.shimmerresearch.sensors.AbstractSensor
    public Object setConfigValueUsingConfigLabel(Integer num, String str, Object obj) {
        return null;
    }

    @Override // com.shimmerresearch.sensors.AbstractSensor
    public boolean setDefaultConfigForSensor(int i, boolean z) {
        return false;
    }

    @Override // com.shimmerresearch.sensors.AbstractSensor
    public void setSensorSamplingRate(double d) {
    }

    @Override // com.shimmerresearch.sensors.AbstractSensor
    public ActionSetting setSettings(String str, Object obj, Configuration.COMMUNICATION_TYPE communication_type) {
        return null;
    }

    @Override // com.shimmerresearch.sensors.AbstractSensor
    public void generateSensorMap() {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("Timestamp", getChannelDetailsForFwVerVersion(this.mShimmerDevice));
        super.createLocalSensorMapWithCustomParser(mSensorMapRef, linkedHashMap);
    }

    @Override // com.shimmerresearch.sensors.AbstractSensor
    public ObjectCluster processDataCustom(SensorDetails sensorDetails, byte[] bArr, Configuration.COMMUNICATION_TYPE communication_type, ObjectCluster objectCluster, boolean z, double d) {
        if (!sensorDetails.isEnabled(communication_type) || !sensorDetails.mSensorDetailsRef.mGuiFriendlyLabel.equals("Timestamp")) {
            return objectCluster;
        }
        objectCluster.addCalData(getChannelDetailsForFwVerVersion(this.mShimmerDevice), d);
        objectCluster.setTimeStampMilliSecs(d);
        return communication_type != Configuration.COMMUNICATION_TYPE.SD ? this.systemTimestampPlot.processSystemTimestampPlot(objectCluster) : objectCluster;
    }
}
