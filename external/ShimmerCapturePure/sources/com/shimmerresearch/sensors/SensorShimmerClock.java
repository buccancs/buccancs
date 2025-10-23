package com.shimmerresearch.sensors;

import com.shimmerresearch.driver.Configuration;
import com.shimmerresearch.driver.ShimmerDevice;
import com.shimmerresearch.driverUtilities.ChannelDetails;
import com.shimmerresearch.driverUtilities.SensorDetailsRef;
import com.shimmerresearch.driverUtilities.SensorGroupingDetails;
import com.shimmerresearch.driverUtilities.UtilShimmer;
import com.shimmerresearch.sensors.AbstractSensor;
import com.shimmerresearch.sensors.SensorSystemTimeStamp;
import com.shimmerresearch.sensors.ShimmerStreamingProperties;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes2.dex */
public class SensorShimmerClock extends AbstractSensor {
    public static final ChannelDetails channelBattPercentage;
    public static final ChannelDetails channelEventMarker;
    public static final ChannelDetails channelRealTimeClock;
    public static final ChannelDetails channelReceptionRateCurrent;
    public static final ChannelDetails channelReceptionRateTrial;
    public static final ChannelDetails channelShimmerClock2byte;
    public static final ChannelDetails channelShimmerClock3byte;
    public static final ChannelDetails channelShimmerClockOffset;
    public static final ChannelDetails channelShimmerTsDiffernce;
    public static final ChannelDetails channelSystemTimestamp;
    public static final ChannelDetails channelSystemTimestampDiff;
    public static final ChannelDetails channelSystemTimestampPlot;
    public static final Map<Integer, SensorDetailsRef> mSensorMapRef;
    public static final SensorGroupingDetails sensorGroupStreamingProperties;
    public static final SensorDetailsRef sensorShimmerClock;
    public static final SensorDetailsRef sensorShimmerStreamingProperties;
    private static final long serialVersionUID = 4841055784366989272L;

    static {
        SensorDetailsRef sensorDetailsRef = new SensorDetailsRef("Timestamp", Configuration.Shimmer3.CompatibilityInfoForMaps.listOfCompatibleVersionInfoAnyExpBoardStandardFW, (List<String>) Arrays.asList("Timestamp", ObjectClusterSensorName.TIMESTAMP_DIFFERENCE, ObjectClusterSensorName.REAL_TIME_CLOCK, ObjectClusterSensorName.TIMESTAMP_OFFSET));
        sensorShimmerClock = sensorDetailsRef;
        SensorDetailsRef sensorDetailsRef2 = new SensorDetailsRef("Device Properties", Configuration.Shimmer3.CompatibilityInfoForMaps.listOfCompatibleVersionInfoAnyExpBoardStandardFW, (List<String>) Arrays.asList("System_Timestamp", "Batt_Percentage", "Packet_Reception_Rate_Current", "Packet_Reception_Rate_Trial", Configuration.Shimmer3.ObjectClusterSensorName.EVENT_MARKER, ShimmerStreamingProperties.ObjectClusterSensorName.RSSI, ShimmerStreamingProperties.ObjectClusterSensorName.SENSOR_DISTANCE));
        sensorShimmerStreamingProperties = sensorDetailsRef2;
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put(Integer.valueOf(Configuration.Shimmer3.SENSOR_ID.HOST_SYSTEM_TIMESTAMP), SensorSystemTimeStamp.sensorSystemTimeStampRef);
        linkedHashMap.put(Integer.valueOf(Configuration.Shimmer3.SENSOR_ID.SHIMMER_TIMESTAMP), sensorDetailsRef);
        linkedHashMap.put(-100, sensorDetailsRef2);
        mSensorMapRef = Collections.unmodifiableMap(linkedHashMap);
        sensorGroupStreamingProperties = new SensorGroupingDetails("Device Properties", (List<Integer>) Arrays.asList(-100), Configuration.Shimmer3.CompatibilityInfoForMaps.listOfCompatibleVersionInfoAnyExpBoardStandardFW, true);
        channelSystemTimestamp = SensorSystemTimeStamp.channelSystemTimestamp;
        channelSystemTimestampPlot = SensorSystemTimeStamp.channelSystemTimestampPlot;
        channelSystemTimestampDiff = SensorSystemTimeStamp.channelSystemTimestampDiff;
        channelShimmerClock3byte = new ChannelDetails("Timestamp", "Timestamp", DatabaseChannelHandles.TIMESTAMP, ChannelDetails.CHANNEL_DATA_TYPE.UINT24, 3, ChannelDetails.CHANNEL_DATA_ENDIAN.LSB, Configuration.CHANNEL_UNITS.CLOCK_UNIT, Arrays.asList(ChannelDetails.CHANNEL_TYPE.CAL, ChannelDetails.CHANNEL_TYPE.UNCAL), false, true);
        channelShimmerTsDiffernce = new ChannelDetails(ObjectClusterSensorName.TIMESTAMP_DIFFERENCE, ObjectClusterSensorName.TIMESTAMP_DIFFERENCE, "", Configuration.CHANNEL_UNITS.CLOCK_UNIT, Arrays.asList(ChannelDetails.CHANNEL_TYPE.CAL), false, false);
        channelShimmerClock2byte = new ChannelDetails("Timestamp", "Timestamp", DatabaseChannelHandles.TIMESTAMP, ChannelDetails.CHANNEL_DATA_TYPE.UINT16, 2, ChannelDetails.CHANNEL_DATA_ENDIAN.LSB, Configuration.CHANNEL_UNITS.CLOCK_UNIT, Arrays.asList(ChannelDetails.CHANNEL_TYPE.CAL, ChannelDetails.CHANNEL_TYPE.UNCAL), false, true);
        channelShimmerClockOffset = new ChannelDetails(ObjectClusterSensorName.TIMESTAMP_OFFSET, ObjectClusterSensorName.TIMESTAMP_OFFSET, DatabaseChannelHandles.OFFSET_TIMESTAMP, Configuration.CHANNEL_UNITS.NO_UNITS, Arrays.asList(ChannelDetails.CHANNEL_TYPE.UNCAL), false, true);
        channelRealTimeClock = new ChannelDetails(ObjectClusterSensorName.REAL_TIME_CLOCK, ObjectClusterSensorName.REAL_TIME_CLOCK, DatabaseChannelHandles.REAL_TIME_CLOCK, Configuration.CHANNEL_UNITS.MILLISECONDS, Arrays.asList(ChannelDetails.CHANNEL_TYPE.UNCAL, ChannelDetails.CHANNEL_TYPE.CAL), false, true);
        channelBattPercentage = new ChannelDetails("Batt_Percentage", "Batt_Percentage", "", Configuration.CHANNEL_UNITS.PERCENT, Arrays.asList(ChannelDetails.CHANNEL_TYPE.CAL), true, false);
        channelReceptionRateCurrent = new ChannelDetails("Packet_Reception_Rate_Current", "Packet Reception Rate (per second)", "", Configuration.CHANNEL_UNITS.PERCENT, Arrays.asList(ChannelDetails.CHANNEL_TYPE.CAL), true, false);
        channelReceptionRateTrial = new ChannelDetails("Packet_Reception_Rate_Trial", "Packet Reception Rate (overall)", "", Configuration.CHANNEL_UNITS.PERCENT, Arrays.asList(ChannelDetails.CHANNEL_TYPE.CAL), true, false);
        channelEventMarker = new ChannelDetails(ShimmerStreamingProperties.ObjectClusterSensorName.EVENT_MARKER, ShimmerStreamingProperties.GuiLabelSensors.EVENT_MARKER, ShimmerStreamingProperties.DatabaseChannelHandles.EVENT_CHANNEL, Configuration.CHANNEL_UNITS.NO_UNITS, Arrays.asList(ChannelDetails.CHANNEL_TYPE.CAL), false, false);
    }

    public int OFFSET_LENGTH;
    protected double mCurrentTimeStampCycle;
    protected boolean mFirstTime;
    protected long mInitialTimeStampTicksSd;
    @Deprecated
    protected double mLastReceivedCalibratedTimeStamp;
    protected double mLastReceivedTimeStampTicksUnwrapped;
    protected long mRTCOffset;
    protected double mStreamingStartTimeMilliSecs;
    protected boolean mStreamingStartTimeSaved;
    protected int mTimeStampTicksMaxValue;
    double mFirstTsOffsetFromInitialTsTicks;
    double mLastSavedCalibratedTimeStamp;
    double mPreviousTimeStamp;
    double mSystemTimeStamp;
    double mSystemTimeStampPrevious;
    private double mFirstSystemTimestampPlot;
    private boolean mIsFirstSystemTimestampOffsetPlotStored;
    private boolean mIsFirstSystemTimestampOffsetStored;
    private double mOffsetFirstTime;

    public SensorShimmerClock(ShimmerDevice shimmerDevice) {
        super(AbstractSensor.SENSORS.CLOCK, shimmerDevice);
        this.mLastReceivedTimeStampTicksUnwrapped = 0.0d;
        this.mCurrentTimeStampCycle = 0.0d;
        this.mInitialTimeStampTicksSd = 0L;
        this.mLastReceivedCalibratedTimeStamp = -1.0d;
        this.mStreamingStartTimeSaved = false;
        this.mTimeStampTicksMaxValue = 16777216;
        this.mFirstTime = true;
        this.mFirstTsOffsetFromInitialTsTicks = 0.0d;
        this.mSystemTimeStamp = 0.0d;
        this.OFFSET_LENGTH = 9;
        this.mRTCOffset = 0L;
        this.mIsFirstSystemTimestampOffsetStored = false;
        this.mOffsetFirstTime = -1.0d;
        this.mIsFirstSystemTimestampOffsetPlotStored = false;
        this.mFirstSystemTimestampPlot = -1.0d;
        this.mPreviousTimeStamp = 0.0d;
        this.mSystemTimeStampPrevious = 0.0d;
        this.mLastSavedCalibratedTimeStamp = 0.0d;
        sensorShimmerClock.mIsApiSensor = true;
        sensorShimmerStreamingProperties.mIsApiSensor = true;
        ChannelDetails channelDetails = channelShimmerClock3byte;
        channelDetails.mChannelSource = ChannelDetails.CHANNEL_SOURCE.SHIMMER;
        channelDetails.mChannelFormatDerivedFromShimmerDataPacket = ChannelDetails.CHANNEL_TYPE.UNCAL;
        channelShimmerTsDiffernce.mChannelSource = ChannelDetails.CHANNEL_SOURCE.API;
        channelShimmerClock2byte.mChannelSource = ChannelDetails.CHANNEL_SOURCE.SHIMMER;
        channelShimmerClockOffset.mChannelSource = ChannelDetails.CHANNEL_SOURCE.API;
        channelRealTimeClock.mChannelSource = ChannelDetails.CHANNEL_SOURCE.API;
        channelBattPercentage.mChannelSource = ChannelDetails.CHANNEL_SOURCE.API;
        channelReceptionRateCurrent.mChannelSource = ChannelDetails.CHANNEL_SOURCE.API;
        channelReceptionRateTrial.mChannelSource = ChannelDetails.CHANNEL_SOURCE.API;
        channelEventMarker.mChannelSource = ChannelDetails.CHANNEL_SOURCE.API;
        initialise();
    }

    private double calculateTimeStampUnwrapped(double d) {
        return d + (this.mTimeStampTicksMaxValue * this.mCurrentTimeStampCycle);
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
    public Object getConfigValueUsingConfigLabel(Integer num, String str) {
        return null;
    }

    public long getInitialTimeStampTicksSd() {
        return this.mInitialTimeStampTicksSd;
    }

    public void setInitialTimeStampTicksSd(long j) {
        this.mInitialTimeStampTicksSd = j;
    }

    public double getLastReceivedTimeStampTicksUnwrapped() {
        return this.mLastReceivedTimeStampTicksUnwrapped;
    }

    public void setLastReceivedTimeStampTicksUnwrapped(double d) {
        this.mLastReceivedTimeStampTicksUnwrapped = d;
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
    public Object setConfigValueUsingConfigLabel(Integer num, String str, Object obj) {
        return null;
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
        linkedHashMap.put("System_Timestamp", channelSystemTimestamp);
        linkedHashMap.put(SensorSystemTimeStamp.ObjectClusterSensorName.SYSTEM_TIMESTAMP_PLOT, channelSystemTimestampPlot);
        linkedHashMap.put(SensorSystemTimeStamp.ObjectClusterSensorName.SYSTEM_TIMESTAMP_DIFFERENCE, channelSystemTimestampDiff);
        linkedHashMap.put(SensorSystemTimeStamp.ObjectClusterSensorName.SYSTEM_TIMESTAMP_PLOT_ZEROED, SensorSystemTimeStamp.channelSystemTimestampPlotZeroed);
        if (this.mShimmerVerObject.isShimmerGenGq()) {
            linkedHashMap.put(ShimmerStreamingProperties.ObjectClusterSensorName.RSSI, ShimmerStreamingProperties.channelRssi);
            linkedHashMap.put(ShimmerStreamingProperties.ObjectClusterSensorName.SENSOR_DISTANCE, ShimmerStreamingProperties.channelSensorDistance);
        } else {
            if (this.mShimmerVerObject.getFirmwareVersionCode() >= 6 || this.mShimmerVerObject.mHardwareVersion == 1003) {
                linkedHashMap.put("Timestamp", channelShimmerClock3byte);
                this.mTimeStampTicksMaxValue = (int) Math.pow(2.0d, 24.0d);
            } else {
                linkedHashMap.put("Timestamp", channelShimmerClock2byte);
                this.mTimeStampTicksMaxValue = (int) Math.pow(2.0d, 16.0d);
            }
            linkedHashMap.put(ObjectClusterSensorName.TIMESTAMP_DIFFERENCE, channelShimmerTsDiffernce);
            linkedHashMap.put(ObjectClusterSensorName.TIMESTAMP_OFFSET, channelShimmerClockOffset);
            linkedHashMap.put(ObjectClusterSensorName.REAL_TIME_CLOCK, channelRealTimeClock);
            linkedHashMap.put("Batt_Percentage", channelBattPercentage);
            linkedHashMap.put("Packet_Reception_Rate_Current", channelReceptionRateCurrent);
            linkedHashMap.put("Packet_Reception_Rate_Trial", channelReceptionRateTrial);
        }
        linkedHashMap.put(ShimmerStreamingProperties.ObjectClusterSensorName.EVENT_MARKER, channelEventMarker);
        super.createLocalSensorMapWithCustomParser(mSensorMapRef, linkedHashMap);
    }

    @Override // com.shimmerresearch.sensors.AbstractSensor
    public void generateSensorGroupMapping() {
        this.mSensorGroupingMap.put(Integer.valueOf(Configuration.Shimmer3.LABEL_SENSOR_TILE.STREAMING_PROPERTIES.ordinal()), sensorGroupStreamingProperties);
    }

    /* JADX WARN: Removed duplicated region for block: B:104:0x0272  */
    /* JADX WARN: Type inference failed for: r13v0 */
    /* JADX WARN: Type inference failed for: r13v2, types: [boolean, int] */
    /* JADX WARN: Type inference failed for: r13v7 */
    @Override // com.shimmerresearch.sensors.AbstractSensor
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public com.shimmerresearch.driver.ObjectCluster processDataCustom(com.shimmerresearch.driverUtilities.SensorDetails r21, byte[] r22, com.shimmerresearch.driver.Configuration.COMMUNICATION_TYPE r23, com.shimmerresearch.driver.ObjectCluster r24, boolean r25, double r26) {
        /*
            Method dump skipped, instructions count: 851
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.shimmerresearch.sensors.SensorShimmerClock.processDataCustom(com.shimmerresearch.driverUtilities.SensorDetails, byte[], com.shimmerresearch.driver.Configuration$COMMUNICATION_TYPE, com.shimmerresearch.driver.ObjectCluster, boolean, double):com.shimmerresearch.driver.ObjectCluster");
    }

    @Override // com.shimmerresearch.sensors.AbstractSensor
    public boolean setDefaultConfigForSensor(int i, boolean z) {
        return this.mSensorMap.containsKey(Integer.valueOf(i));
    }

    @Override // com.shimmerresearch.sensors.AbstractSensor
    public void parseConfigMap(LinkedHashMap<String, Object> linkedHashMap) {
        if (linkedHashMap.containsKey(DatabaseConfigHandle.INITIAL_TIMESTAMP)) {
            setInitialTimeStampTicksSd(((Double) linkedHashMap.get(DatabaseConfigHandle.INITIAL_TIMESTAMP)).longValue());
        }
    }

    protected double unwrapTimeStamp(double d) {
        double dCalculateTimeStampUnwrapped = calculateTimeStampUnwrapped(d);
        if (getLastReceivedTimeStampTicksUnwrapped() > dCalculateTimeStampUnwrapped) {
            this.mCurrentTimeStampCycle += 1.0d;
            dCalculateTimeStampUnwrapped = calculateTimeStampUnwrapped(d);
        }
        setLastReceivedTimeStampTicksUnwrapped(dCalculateTimeStampUnwrapped);
        return dCalculateTimeStampUnwrapped;
    }

    private void calculateTrialPacketLoss(double d) {
        if (!this.mStreamingStartTimeSaved) {
            this.mStreamingStartTimeSaved = true;
            this.mStreamingStartTimeMilliSecs = d;
        }
        double d2 = this.mStreamingStartTimeMilliSecs;
        if (d2 > 0.0d) {
            long samplingRateShimmer = (long) ((d - d2) / ((1.0d / this.mShimmerDevice.getSamplingRateShimmer()) * 1000.0d));
            this.mShimmerDevice.setPacketExpectedCountOverall(samplingRateShimmer);
            long packetReceivedCountOverall = this.mShimmerDevice.getPacketReceivedCountOverall();
            this.mShimmerDevice.setPacketLossCountPerTrial(samplingRateShimmer + packetReceivedCountOverall);
            this.mShimmerDevice.setPacketReceptionRateOverall((packetReceivedCountOverall / samplingRateShimmer) * 100.0d);
        }
    }

    @Deprecated
    public double calculatePacketReceptionRateCurrent(int i) {
        double samplingRateShimmer = (i / 1000.0d) * getSamplingRateShimmer();
        double d = this.mLastReceivedCalibratedTimeStamp;
        if (d != -1.0d) {
            this.mShimmerDevice.setPacketReceptionRateCurrent(((((d - this.mLastSavedCalibratedTimeStamp) / 1000.0d) * getSamplingRateShimmer()) / samplingRateShimmer) * 100.0d);
        }
        this.mShimmerDevice.setPacketReceptionRateCurrent(UtilShimmer.nudgeDouble(this.mShimmerDevice.getPacketReceptionRateCurrent(), 0.0d, 100.0d));
        this.mLastSavedCalibratedTimeStamp = this.mLastReceivedCalibratedTimeStamp;
        return this.mShimmerDevice.getPacketReceptionRateCurrent();
    }

    public void resetShimmerClock() {
        this.mIsFirstSystemTimestampOffsetStored = false;
        this.mIsFirstSystemTimestampOffsetPlotStored = false;
        resetCalibratedTimeStamp();
    }

    public void resetCalibratedTimeStamp() {
        setLastReceivedTimeStampTicksUnwrapped(0.0d);
        this.mLastReceivedCalibratedTimeStamp = -1.0d;
        this.mStreamingStartTimeSaved = false;
        this.mStreamingStartTimeMilliSecs = -1.0d;
        this.mCurrentTimeStampCycle = 0.0d;
    }

    public static class DatabaseChannelHandles {
        public static final String OFFSET_TIMESTAMP = "OFFSET";
        public static final String REAL_TIME_CLOCK = "Real_Time_Clock";
        public static final String TIMESTAMP = "TimeStamp";
        public static final String TIMESTAMP_EXPORT = "Timestamp";
    }

    public static final class DatabaseConfigHandle {
        public static final String INITIAL_TIMESTAMP = "Initial_Timestamp";
    }

    public static class GuiLabelSensors {
        public static final String DEVICE_PROPERTIES = "Device Properties";
        public static final String SYSTEM_TIMESTAMP = "System_Timestamp";
        public static final String TIMESTAMP = "Timestamp";
    }

    public static class LABEL_SENSOR_TILE {
        public static final String STREAMING_PROPERTIES = "Device Properties";
    }

    public static class ObjectClusterSensorName {
        public static final String REAL_TIME_CLOCK = "RealTime";
        public static final String TIMESTAMP = "Timestamp";
        public static final String TIMESTAMP_DIFFERENCE = "Timestamp Difference";
        public static final String TIMESTAMP_OFFSET = "Offset";
    }
}
