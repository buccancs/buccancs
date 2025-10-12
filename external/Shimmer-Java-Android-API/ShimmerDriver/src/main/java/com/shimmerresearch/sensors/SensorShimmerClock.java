package com.shimmerresearch.sensors;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

import com.shimmerresearch.driver.Configuration;
import com.shimmerresearch.driver.FormatCluster;
import com.shimmerresearch.driver.ObjectCluster;
import com.shimmerresearch.driver.ShimmerDevice;
import com.shimmerresearch.driver.Configuration.CHANNEL_UNITS;
import com.shimmerresearch.driver.Configuration.COMMUNICATION_TYPE;
import com.shimmerresearch.driver.Configuration.Shimmer3;
import com.shimmerresearch.driver.Configuration.Shimmer3.CompatibilityInfoForMaps;
import com.shimmerresearch.driverUtilities.ChannelDetails;
import com.shimmerresearch.driverUtilities.SensorDetails;
import com.shimmerresearch.driverUtilities.SensorDetailsRef;
import com.shimmerresearch.driverUtilities.SensorGroupingDetails;
import com.shimmerresearch.driverUtilities.ShimmerVerDetails.HW_ID;
import com.shimmerresearch.driverUtilities.UtilParseData;
import com.shimmerresearch.driverUtilities.UtilShimmer;
import com.shimmerresearch.driverUtilities.ChannelDetails.CHANNEL_SOURCE;
import com.shimmerresearch.driverUtilities.ChannelDetails.CHANNEL_TYPE;
import com.shimmerresearch.driverUtilities.ChannelDetails.CHANNEL_DATA_ENDIAN;
import com.shimmerresearch.driverUtilities.ChannelDetails.CHANNEL_DATA_TYPE;

public class SensorShimmerClock extends AbstractSensor {

    public static final SensorDetailsRef sensorShimmerClock = new SensorDetailsRef(
            GuiLabelSensors.TIMESTAMP,
            CompatibilityInfoForMaps.listOfCompatibleVersionInfoAnyExpBoardStandardFW,
            Arrays.asList(SensorShimmerClock.ObjectClusterSensorName.TIMESTAMP,
                    SensorShimmerClock.ObjectClusterSensorName.TIMESTAMP_DIFFERENCE,
                    SensorShimmerClock.ObjectClusterSensorName.REAL_TIME_CLOCK,
                    SensorShimmerClock.ObjectClusterSensorName.TIMESTAMP_OFFSET
            ));
    public static final SensorDetailsRef sensorShimmerStreamingProperties = new SensorDetailsRef(
            GuiLabelSensors.DEVICE_PROPERTIES,
            CompatibilityInfoForMaps.listOfCompatibleVersionInfoAnyExpBoardStandardFW,
            Arrays.asList(
                    SensorSystemTimeStamp.ObjectClusterSensorName.SYSTEM_TIMESTAMP,

                    Configuration.Shimmer3.ObjectClusterSensorName.BATT_PERCENTAGE,
                    Configuration.Shimmer3.ObjectClusterSensorName.PACKET_RECEPTION_RATE_CURRENT,
                    Configuration.Shimmer3.ObjectClusterSensorName.PACKET_RECEPTION_RATE_OVERALL,
                    Configuration.Shimmer3.ObjectClusterSensorName.EVENT_MARKER,
                    ShimmerStreamingProperties.ObjectClusterSensorName.RSSI,
                    ShimmerStreamingProperties.ObjectClusterSensorName.SENSOR_DISTANCE

            ));
    public static final Map<Integer, SensorDetailsRef> mSensorMapRef;
    public static final SensorGroupingDetails sensorGroupStreamingProperties = new SensorGroupingDetails(
            LABEL_SENSOR_TILE.STREAMING_PROPERTIES,
            Arrays.asList(Configuration.Shimmer3.SENSOR_ID.HOST_SHIMMER_STREAMING_PROPERTIES),
            CompatibilityInfoForMaps.listOfCompatibleVersionInfoAnyExpBoardStandardFW,
            true);
    public static final ChannelDetails channelSystemTimestamp = SensorSystemTimeStamp.channelSystemTimestamp;
    public static final ChannelDetails channelSystemTimestampPlot = SensorSystemTimeStamp.channelSystemTimestampPlot;
    public static final ChannelDetails channelSystemTimestampDiff = SensorSystemTimeStamp.channelSystemTimestampDiff;
    public static final ChannelDetails channelShimmerClock3byte = new ChannelDetails(
            ObjectClusterSensorName.TIMESTAMP,
            ObjectClusterSensorName.TIMESTAMP,
            DatabaseChannelHandles.TIMESTAMP,
            CHANNEL_DATA_TYPE.UINT24, 3, CHANNEL_DATA_ENDIAN.LSB,
            CHANNEL_UNITS.CLOCK_UNIT,
            Arrays.asList(CHANNEL_TYPE.CAL, CHANNEL_TYPE.UNCAL), false, true);
    public static final ChannelDetails channelShimmerTsDiffernce = new ChannelDetails(
            ObjectClusterSensorName.TIMESTAMP_DIFFERENCE,
            ObjectClusterSensorName.TIMESTAMP_DIFFERENCE,
            DatabaseChannelHandlesCommon.NONE,
            CHANNEL_UNITS.CLOCK_UNIT,
            Arrays.asList(CHANNEL_TYPE.CAL), false, false);
    public static final ChannelDetails channelShimmerClock2byte = new ChannelDetails(
            ObjectClusterSensorName.TIMESTAMP,
            ObjectClusterSensorName.TIMESTAMP,
            DatabaseChannelHandles.TIMESTAMP,
            CHANNEL_DATA_TYPE.UINT16, 2, CHANNEL_DATA_ENDIAN.LSB,
            CHANNEL_UNITS.CLOCK_UNIT,
            Arrays.asList(CHANNEL_TYPE.CAL, CHANNEL_TYPE.UNCAL), false, true);
    public static final ChannelDetails channelShimmerClockOffset = new ChannelDetails(
            ObjectClusterSensorName.TIMESTAMP_OFFSET,
            ObjectClusterSensorName.TIMESTAMP_OFFSET,
            DatabaseChannelHandles.OFFSET_TIMESTAMP,
            CHANNEL_UNITS.NO_UNITS,
            Arrays.asList(CHANNEL_TYPE.UNCAL), false, true);
    public static final ChannelDetails channelRealTimeClock = new ChannelDetails(
            ObjectClusterSensorName.REAL_TIME_CLOCK,
            ObjectClusterSensorName.REAL_TIME_CLOCK,
            DatabaseChannelHandles.REAL_TIME_CLOCK,
            CHANNEL_UNITS.MILLISECONDS,
            Arrays.asList(CHANNEL_TYPE.UNCAL, CHANNEL_TYPE.CAL), false, true);
    public static final ChannelDetails channelBattPercentage = new ChannelDetails(
            Configuration.Shimmer3.ObjectClusterSensorName.BATT_PERCENTAGE,
            Configuration.Shimmer3.ObjectClusterSensorName.BATT_PERCENTAGE,
            DatabaseChannelHandlesCommon.NONE,
            CHANNEL_UNITS.PERCENT,
            Arrays.asList(CHANNEL_TYPE.CAL), true, false);
    public static final ChannelDetails channelReceptionRateCurrent = new ChannelDetails(
            Configuration.Shimmer3.ObjectClusterSensorName.PACKET_RECEPTION_RATE_CURRENT,
            "Packet Reception Rate (per second)",
            DatabaseChannelHandlesCommon.NONE,
            CHANNEL_UNITS.PERCENT,
            Arrays.asList(CHANNEL_TYPE.CAL), true, false);
    public static final ChannelDetails channelReceptionRateTrial = new ChannelDetails(
            Configuration.Shimmer3.ObjectClusterSensorName.PACKET_RECEPTION_RATE_OVERALL,
            "Packet Reception Rate (overall)",
            DatabaseChannelHandlesCommon.NONE,
            CHANNEL_UNITS.PERCENT,
            Arrays.asList(CHANNEL_TYPE.CAL), true, false);
    public static final ChannelDetails channelEventMarker = new ChannelDetails(
            ShimmerStreamingProperties.ObjectClusterSensorName.EVENT_MARKER,
            ShimmerStreamingProperties.GuiLabelSensors.EVENT_MARKER,
            ShimmerStreamingProperties.DatabaseChannelHandles.EVENT_CHANNEL,
            CHANNEL_UNITS.NO_UNITS,
            Arrays.asList(CHANNEL_TYPE.CAL), false, false);
    private static final long serialVersionUID = 4841055784366989272L;

    static {
        Map<Integer, SensorDetailsRef> aMap = new LinkedHashMap<Integer, SensorDetailsRef>();
        aMap.put(Configuration.Shimmer3.SENSOR_ID.HOST_SYSTEM_TIMESTAMP, SensorSystemTimeStamp.sensorSystemTimeStampRef);
        aMap.put(Configuration.Shimmer3.SENSOR_ID.SHIMMER_TIMESTAMP, SensorShimmerClock.sensorShimmerClock);
        aMap.put(Configuration.Shimmer3.SENSOR_ID.HOST_SHIMMER_STREAMING_PROPERTIES, SensorShimmerClock.sensorShimmerStreamingProperties);
        mSensorMapRef = Collections.unmodifiableMap(aMap);
    }

    public int OFFSET_LENGTH = 9;
    protected double mLastReceivedTimeStampTicksUnwrapped = 0;


    protected double mCurrentTimeStampCycle = 0;
    protected long mInitialTimeStampTicksSd = 0;
    @Deprecated
    protected double mLastReceivedCalibratedTimeStamp = -1;
    protected boolean mStreamingStartTimeSaved = false;
    protected double mStreamingStartTimeMilliSecs;
    protected int mTimeStampTicksMaxValue = 16777216;
    protected boolean mFirstTime = true;
    protected long mRTCOffset = 0;
    double mFirstTsOffsetFromInitialTsTicks = 0;
    double mSystemTimeStamp = 0;
    double mPreviousTimeStamp = 0;
    double mSystemTimeStampPrevious = 0;


    double mLastSavedCalibratedTimeStamp = 0.0;
    private boolean mIsFirstSystemTimestampOffsetStored = false;
    private double mOffsetFirstTime = -1;
    private boolean mIsFirstSystemTimestampOffsetPlotStored = false;
    private double mFirstSystemTimestampPlot = -1;

    {
        sensorShimmerClock.mIsApiSensor = true;
    }

    {
        sensorShimmerStreamingProperties.mIsApiSensor = true;
    }

    {
        channelShimmerClock3byte.mChannelSource = CHANNEL_SOURCE.SHIMMER;
        channelShimmerClock3byte.mChannelFormatDerivedFromShimmerDataPacket = CHANNEL_TYPE.UNCAL;
    }

    {
        channelShimmerTsDiffernce.mChannelSource = CHANNEL_SOURCE.API;
    }

    {
        channelShimmerClock2byte.mChannelSource = CHANNEL_SOURCE.SHIMMER;
    }

    {
        channelShimmerClockOffset.mChannelSource = CHANNEL_SOURCE.API;
    }

    {
        channelRealTimeClock.mChannelSource = CHANNEL_SOURCE.API;
    }

    {
        channelBattPercentage.mChannelSource = CHANNEL_SOURCE.API;
    }

    {
        channelReceptionRateCurrent.mChannelSource = CHANNEL_SOURCE.API;
    }

    {
        channelReceptionRateTrial.mChannelSource = CHANNEL_SOURCE.API;
    }

    {
        channelEventMarker.mChannelSource = CHANNEL_SOURCE.API;
    }

    public SensorShimmerClock(ShimmerDevice shimmerDevice) {
        super(SENSORS.CLOCK, shimmerDevice);
        initialise();
    }

    @Override
    public void generateSensorMap() {
        Map<String, ChannelDetails> channelMapRef = new LinkedHashMap<String, ChannelDetails>();


        channelMapRef.put(SensorSystemTimeStamp.ObjectClusterSensorName.SYSTEM_TIMESTAMP, SensorShimmerClock.channelSystemTimestamp);
        channelMapRef.put(SensorSystemTimeStamp.ObjectClusterSensorName.SYSTEM_TIMESTAMP_PLOT, SensorShimmerClock.channelSystemTimestampPlot);
        channelMapRef.put(SensorSystemTimeStamp.ObjectClusterSensorName.SYSTEM_TIMESTAMP_DIFFERENCE, SensorShimmerClock.channelSystemTimestampDiff);
        channelMapRef.put(SensorSystemTimeStamp.ObjectClusterSensorName.SYSTEM_TIMESTAMP_PLOT_ZEROED, SensorSystemTimeStamp.channelSystemTimestampPlotZeroed);

        if (mShimmerVerObject.isShimmerGenGq()) {
            channelMapRef.put(ShimmerStreamingProperties.ObjectClusterSensorName.RSSI, ShimmerStreamingProperties.channelRssi);
            channelMapRef.put(ShimmerStreamingProperties.ObjectClusterSensorName.SENSOR_DISTANCE, ShimmerStreamingProperties.channelSensorDistance);
        } else {// if(mShimmerVerObject.isShimmerGen3() || mShimmerVerObject.isShimmerGen4()){
            if (mShimmerVerObject.getFirmwareVersionCode() >= 6 || mShimmerVerObject.mHardwareVersion == HW_ID.ARDUINO) {
                channelMapRef.put(SensorShimmerClock.ObjectClusterSensorName.TIMESTAMP, SensorShimmerClock.channelShimmerClock3byte);
                mTimeStampTicksMaxValue = (int) Math.pow(2, 24);
            } else {
                channelMapRef.put(SensorShimmerClock.ObjectClusterSensorName.TIMESTAMP, SensorShimmerClock.channelShimmerClock2byte);
                mTimeStampTicksMaxValue = (int) Math.pow(2, 16);
            }
            channelMapRef.put(SensorShimmerClock.ObjectClusterSensorName.TIMESTAMP_DIFFERENCE, SensorShimmerClock.channelShimmerTsDiffernce);
            channelMapRef.put(SensorShimmerClock.ObjectClusterSensorName.TIMESTAMP_OFFSET, SensorShimmerClock.channelShimmerClockOffset);
            channelMapRef.put(SensorShimmerClock.ObjectClusterSensorName.REAL_TIME_CLOCK, SensorShimmerClock.channelRealTimeClock);

            channelMapRef.put(SensorBattVoltage.ObjectClusterSensorName.BATT_PERCENTAGE, SensorShimmerClock.channelBattPercentage);

            channelMapRef.put(ShimmerStreamingProperties.ObjectClusterSensorName.PACKET_RECEPTION_RATE_CURRENT, SensorShimmerClock.channelReceptionRateCurrent);
            channelMapRef.put(ShimmerStreamingProperties.ObjectClusterSensorName.PACKET_RECEPTION_RATE_OVERALL, SensorShimmerClock.channelReceptionRateTrial);
        }

        channelMapRef.put(ShimmerStreamingProperties.ObjectClusterSensorName.EVENT_MARKER, SensorShimmerClock.channelEventMarker);

        super.createLocalSensorMapWithCustomParser(mSensorMapRef, channelMapRef);
    }

    @Override
    public void generateConfigOptionsMap() {
    }

    @Override
    public void generateSensorGroupMapping() {
        mSensorGroupingMap.put(Configuration.Shimmer3.LABEL_SENSOR_TILE.STREAMING_PROPERTIES.ordinal(), sensorGroupStreamingProperties);
    }

    @Override
    public ObjectCluster processDataCustom(SensorDetails sensorDetails, byte[] sensorByteArray, COMMUNICATION_TYPE commType, ObjectCluster objectCluster, boolean isTimeSyncEnabled, double pcTimestampMs) {


        if (sensorDetails.isEnabled(commType)) {

            if (sensorDetails.mSensorDetailsRef.mGuiFriendlyLabel.equals(GuiLabelSensors.TIMESTAMP)) {
                for (ChannelDetails channelDetails : sensorDetails.mListOfChannels) {
                    if (channelDetails.mObjectClusterName.equals(ObjectClusterSensorName.TIMESTAMP)) {

                        byte[] channelByteArray = new byte[channelDetails.mDefaultNumBytes];
                        System.arraycopy(sensorByteArray, 0, channelByteArray, 0, channelDetails.mDefaultNumBytes);
                        double shimmerTimestampTicks = UtilParseData.parseData(channelByteArray, channelDetails.mDefaultChannelDataType, channelDetails.mDefaultChannelDataEndian);

                        if (mFirstTime && commType == COMMUNICATION_TYPE.SD) {
                            mFirstTsOffsetFromInitialTsTicks = shimmerTimestampTicks;
                            mFirstTime = false;
                        }

                        double timestampUnwrappedTicks = unwrapTimeStamp(shimmerTimestampTicks);
                        double timestampUnwrappedMilliSecs = timestampUnwrappedTicks / mShimmerDevice.getRtcClockFreq() * 1000;

                        double timestampCalToSave = timestampUnwrappedMilliSecs;
                        double timestampUnCalToSave = shimmerTimestampTicks;

                        calculateTrialPacketLoss(timestampUnwrappedMilliSecs);

                        double timestampUnwrappedWithOffsetTicks = 0;
                        if (commType == COMMUNICATION_TYPE.SD) {
                            timestampUnwrappedWithOffsetTicks = timestampUnwrappedTicks + getInitialTimeStampTicksSd();

                            if (mShimmerDevice.isLegacySdLog()) {
                                timestampUnCalToSave = shimmerTimestampTicks;
                            } else {
                                timestampUnwrappedWithOffsetTicks -= mFirstTsOffsetFromInitialTsTicks;
                                timestampUnCalToSave = timestampUnwrappedWithOffsetTicks;
                            }

                            if (mEnableCalibration) {
                                double timestampUnwrappedWithOffsetMilliSecs = timestampUnwrappedWithOffsetTicks / mShimmerDevice.getRtcClockFreq() * 1000;
                                timestampCalToSave = timestampUnwrappedWithOffsetMilliSecs;
                            }
                        } else if (commType == COMMUNICATION_TYPE.BLUETOOTH) {
                            timestampUnCalToSave = shimmerTimestampTicks;
                            if (mEnableCalibration) {
                                timestampCalToSave = timestampUnwrappedMilliSecs;
                                objectCluster.setTimeStampMilliSecs(timestampUnwrappedMilliSecs);
                            }
                        }

                        objectCluster.addData(channelDetails, timestampUnCalToSave, timestampCalToSave);
                        objectCluster.incrementIndexKeeper();
                    } else if (channelDetails.mObjectClusterName.equals(ObjectClusterSensorName.TIMESTAMP_DIFFERENCE)) {
                        FormatCluster fCal = ObjectCluster.returnFormatCluster(objectCluster.getCollectionOfFormatClusters(ObjectClusterSensorName.TIMESTAMP), CHANNEL_TYPE.CAL.toString());
                        FormatCluster fUncal = ObjectCluster.returnFormatCluster(objectCluster.getCollectionOfFormatClusters(ObjectClusterSensorName.TIMESTAMP), CHANNEL_TYPE.UNCAL.toString());

                        objectCluster.addCalData(channelDetails, fUncal.mData - mPreviousTimeStamp);
                        objectCluster.incrementIndexKeeper();

                        mPreviousTimeStamp = fUncal.mData;
                    } else if (channelDetails.mObjectClusterName.equals(ObjectClusterSensorName.REAL_TIME_CLOCK)) {

                        if (commType == COMMUNICATION_TYPE.SD) {
                            FormatCluster fUncal = ObjectCluster.returnFormatCluster(objectCluster.getCollectionOfFormatClusters(ObjectClusterSensorName.TIMESTAMP), CHANNEL_TYPE.UNCAL.toString());
                            FormatCluster fCal = ObjectCluster.returnFormatCluster(objectCluster.getCollectionOfFormatClusters(ObjectClusterSensorName.TIMESTAMP), CHANNEL_TYPE.CAL.toString());
                            if (fUncal != null && fCal != null) {
                                long timestampUncal = (long) fUncal.mData;
                                long timestampCal = (long) fUncal.mData;

                                if (mRTCOffset != 0) {
                                    double samplingClockFreq = mShimmerDevice.getSamplingClockFreq();

                                    long rtctimestamp = timestampUncal + mRTCOffset;
                                    double rtctimestampcal = Double.NaN;
                                    if (mEnableCalibration) {
                                        rtctimestampcal = timestampCal;
                                        if (mInitialTimeStampTicksSd != 0) {
                                            rtctimestampcal += ((double) mInitialTimeStampTicksSd / samplingClockFreq * 1000.0);
                                        }
                                        if (mRTCOffset != 0) {
                                            rtctimestampcal += ((double) mRTCOffset / samplingClockFreq * 1000.0);
                                        }
                                        if (mFirstTsOffsetFromInitialTsTicks != 0) {
                                            rtctimestampcal -= (mFirstTsOffsetFromInitialTsTicks / samplingClockFreq * 1000.0);
                                        }
                                    }
                                    objectCluster.addData(channelRealTimeClock, (double) rtctimestamp, rtctimestampcal);
                                    objectCluster.incrementIndexKeeper();
                                }
                            }

                        }

                    } else if (channelDetails.mObjectClusterName.equals(ObjectClusterSensorName.TIMESTAMP_OFFSET)) {

                        if (commType == COMMUNICATION_TYPE.SD) {
                            if (isTimeSyncEnabled) {
                                double offsetValue = Double.NaN;

                                double newOffset = 0.0;

                                if (((OFFSET_LENGTH == 9) && (newOffset != 1152921504606846975L))
                                        || (newOffset != 4294967295L)) {
                                    offsetValue = (double) newOffset;
                                }
                                objectCluster.addData(channelShimmerClockOffset, offsetValue, Double.NaN);
                                objectCluster.incrementIndexKeeper();


                            }
                        }
                    }

                }
            } else if (sensorDetails.mSensorDetailsRef.mGuiFriendlyLabel.equals(GuiLabelSensors.SYSTEM_TIMESTAMP)) {
                for (ChannelDetails channelDetails : sensorDetails.mListOfChannels) {

                    if (channelDetails.mObjectClusterName.equals(SensorSystemTimeStamp.ObjectClusterSensorName.SYSTEM_TIMESTAMP)) {
                        double systemTime = pcTimestampMs;
                        if (commType == COMMUNICATION_TYPE.SD) {
                            systemTime = System.currentTimeMillis();
                        }
                        objectCluster.setSystemTimeStamp(systemTime);
                        objectCluster.addData(channelDetails, Double.NaN, systemTime);
                        objectCluster.incrementIndexKeeper();
                    } else if (channelDetails.mObjectClusterName.equals(SensorSystemTimeStamp.ObjectClusterSensorName.SYSTEM_TIMESTAMP_PLOT)) {
                        if (mShimmerVerObject.isShimmerGenGq()) {
                            double systemTime = 0;
                            FormatCluster f = ObjectCluster.returnFormatCluster(objectCluster.getCollectionOfFormatClusters(Shimmer3.ObjectClusterSensorName.SYSTEM_TIMESTAMP), CHANNEL_TYPE.CAL.toString());
                            if (f != null) {
                                systemTime = f.mData;
                            }
                            objectCluster.addCalData(channelDetails, systemTime);
                            objectCluster.incrementIndexKeeper();
                        } else {
                            if (!mIsFirstSystemTimestampOffsetStored) {
                                mIsFirstSystemTimestampOffsetStored = true;
                                mSystemTimeStamp = objectCluster.mSystemTimeStamp;
                                mOffsetFirstTime = mSystemTimeStamp - objectCluster.getTimestampMilliSecs();
                            }

                            double calTimestamp = objectCluster.getTimestampMilliSecs();
                            double systemTimestampPlot = calTimestamp + mOffsetFirstTime;

                            if (!mIsFirstSystemTimestampOffsetPlotStored) {
                                mIsFirstSystemTimestampOffsetPlotStored = true;
                                mFirstSystemTimestampPlot = systemTimestampPlot;
                            }

                            objectCluster.addCalData(channelDetails, systemTimestampPlot);
                            objectCluster.incrementIndexKeeper();
                        }
                    } else if (channelDetails.mObjectClusterName.equals(SensorSystemTimeStamp.ObjectClusterSensorName.SYSTEM_TIMESTAMP_DIFFERENCE)) {
                        FormatCluster fCal = objectCluster.getLastFormatCluster(SensorSystemTimeStamp.ObjectClusterSensorName.SYSTEM_TIMESTAMP_PLOT, CHANNEL_TYPE.CAL.toString());

                        objectCluster.addCalData(channelDetails, fCal.mData - mSystemTimeStampPrevious);
                        objectCluster.incrementIndexKeeper();

                        mSystemTimeStampPrevious = fCal.mData;
                    } else if (channelDetails.mObjectClusterName.equals(SensorSystemTimeStamp.ObjectClusterSensorName.SYSTEM_TIMESTAMP_PLOT_ZEROED)) {
                        double systemTimestampPlotZeroed = 0;
                        if (mIsFirstSystemTimestampOffsetPlotStored) {
                            double systemTimestampPlot = objectCluster.getFormatClusterValue(SensorSystemTimeStamp.ObjectClusterSensorName.SYSTEM_TIMESTAMP_PLOT, CHANNEL_TYPE.CAL.toString());
                            if (!Double.isNaN(systemTimestampPlot)) {
                                systemTimestampPlotZeroed = systemTimestampPlot - mFirstSystemTimestampPlot;
                            }
                        }
                        objectCluster.addCalData(channelDetails, systemTimestampPlotZeroed);
                        objectCluster.incrementIndexKeeper();
                    }
                }
            } else if (sensorDetails.mSensorDetailsRef.mGuiFriendlyLabel.equals(GuiLabelSensors.DEVICE_PROPERTIES)) {
                for (ChannelDetails channelDetails : sensorDetails.mListOfChannels) {
                    if (channelDetails.mObjectClusterName.equals(Configuration.Shimmer3.ObjectClusterSensorName.BATT_PERCENTAGE)) {
                        objectCluster.addCalData(channelBattPercentage, mShimmerDevice.getEstimatedChargePercentage());
                        objectCluster.incrementIndexKeeper();
                    } else if (channelDetails.mObjectClusterName.equals(Configuration.Shimmer3.ObjectClusterSensorName.PACKET_RECEPTION_RATE_CURRENT)) {
                        double packetReceptionRateCurrent = (double) mShimmerDevice.getPacketReceptionRateCurrent();
                        if (!Double.isNaN(packetReceptionRateCurrent) && !Double.isInfinite(packetReceptionRateCurrent)) {
                            objectCluster.addCalData(channelReceptionRateCurrent, packetReceptionRateCurrent);
                            objectCluster.incrementIndexKeeper();
                        }
                    } else if (channelDetails.mObjectClusterName.equals(Configuration.Shimmer3.ObjectClusterSensorName.PACKET_RECEPTION_RATE_OVERALL)) {
                        double packetReceptionRateOverall = (double) mShimmerDevice.getPacketReceptionRateOverall();
                        if (!Double.isNaN(packetReceptionRateOverall) && !Double.isInfinite(packetReceptionRateOverall)) {
                            objectCluster.addCalData(channelReceptionRateTrial, packetReceptionRateOverall);
                            objectCluster.incrementIndexKeeper();
                        }
                    } else if (channelDetails.mObjectClusterName.equals(Configuration.Shimmer3.ObjectClusterSensorName.EVENT_MARKER)) {
                        objectCluster.addCalData(channelEventMarker, mShimmerDevice.mEventMarkers);
                        objectCluster.incrementIndexKeeper();
                        mShimmerDevice.untriggerEventIfLastOneWasPulse();

                    }
                }

                if (mIsDebugOutput) {
                    super.consolePrintChannelsCal(objectCluster, Arrays.asList(
                            new String[]{Configuration.Shimmer3.ObjectClusterSensorName.BATT_PERCENTAGE, CHANNEL_TYPE.CAL.toString()},
                            new String[]{Configuration.Shimmer3.ObjectClusterSensorName.PACKET_RECEPTION_RATE_CURRENT, CHANNEL_TYPE.CAL.toString()},
                            new String[]{Configuration.Shimmer3.ObjectClusterSensorName.PACKET_RECEPTION_RATE_OVERALL, CHANNEL_TYPE.CAL.toString()}
                    ));
                }
            }
        }


        return objectCluster;
    }

    @Override
    public void configBytesGenerate(ShimmerDevice shimmerDevice, byte[] mInfoMemBytes, COMMUNICATION_TYPE commType) {
    }

    @Override
    public void configBytesParse(ShimmerDevice shimmerDevice, byte[] mInfoMemBytes, COMMUNICATION_TYPE commType) {
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
        if (mSensorMap.containsKey(sensorId)) {
            return true;
        }
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
    public LinkedHashMap<String, Object> generateConfigMap() {
        return null;
    }

    @Override
    public void parseConfigMap(
            LinkedHashMap<String, Object> mapOfConfigPerShimmer) {
        if (mapOfConfigPerShimmer.containsKey(DatabaseConfigHandle.INITIAL_TIMESTAMP)) {
            setInitialTimeStampTicksSd(((Double) mapOfConfigPerShimmer.get(DatabaseConfigHandle.INITIAL_TIMESTAMP)).longValue());
        }

    }

    protected double unwrapTimeStamp(double timeStampTicks) {
        double timestampUnwrappedTicks = calculateTimeStampUnwrapped(timeStampTicks);

        if (getLastReceivedTimeStampTicksUnwrapped() > timestampUnwrappedTicks) {
            mCurrentTimeStampCycle += 1;
            timestampUnwrappedTicks = calculateTimeStampUnwrapped(timeStampTicks);
        }

        setLastReceivedTimeStampTicksUnwrapped(timestampUnwrappedTicks);

        return timestampUnwrappedTicks;
    }

    private double calculateTimeStampUnwrapped(double timeStampTicks) {
        return timeStampTicks + (mTimeStampTicksMaxValue * mCurrentTimeStampCycle);
    }

    private void calculateTrialPacketLoss(double timestampUnwrappedMilliSecs) {
        if (!mStreamingStartTimeSaved) {
            mStreamingStartTimeSaved = true;
            mStreamingStartTimeMilliSecs = timestampUnwrappedMilliSecs;
        }

        if (mStreamingStartTimeMilliSecs > 0) {
            double timeDifference = timestampUnwrappedMilliSecs - mStreamingStartTimeMilliSecs;
            double expectedTimeDifference = (1 / mShimmerDevice.getSamplingRateShimmer()) * 1000;

            long packetExpectedCount = (long) (timeDifference / expectedTimeDifference);
            mShimmerDevice.setPacketExpectedCountOverall(packetExpectedCount);

            long packetReceivedCount = mShimmerDevice.getPacketReceivedCountOverall();

            long packetLossCountPerTrial = packetExpectedCount + packetReceivedCount;
            mShimmerDevice.setPacketLossCountPerTrial(packetLossCountPerTrial);

            double packetReceptionRateTrial = ((double) packetReceivedCount / (double) packetExpectedCount) * 100;
            mShimmerDevice.setPacketReceptionRateOverall(packetReceptionRateTrial);
        }
    }

    @Override
    public boolean processResponse(int responseCommand, Object parsedResponse, COMMUNICATION_TYPE commType) {
        return false;
    }

    @Override
    public void checkShimmerConfigBeforeConfiguring() {

    }

    @Deprecated
    public double calculatePacketReceptionRateCurrent(int intervalMs) {
        double numPacketsShouldHaveReceived = (((double) intervalMs) / 1000) * getSamplingRateShimmer();

        if (mLastReceivedCalibratedTimeStamp != -1) {
            double timeDifference = mLastReceivedCalibratedTimeStamp - mLastSavedCalibratedTimeStamp;
            double numPacketsReceived = ((timeDifference / 1000) * getSamplingRateShimmer());
            mShimmerDevice.setPacketReceptionRateCurrent((numPacketsReceived / numPacketsShouldHaveReceived) * 100.0);
        }

        mShimmerDevice.setPacketReceptionRateCurrent(UtilShimmer.nudgeDouble(mShimmerDevice.getPacketReceptionRateCurrent(), 0.0, 100.0));

        mLastSavedCalibratedTimeStamp = mLastReceivedCalibratedTimeStamp;
        return mShimmerDevice.getPacketReceptionRateCurrent();
    }

    public void resetShimmerClock() {
        mIsFirstSystemTimestampOffsetStored = false;
        mIsFirstSystemTimestampOffsetPlotStored = false;
        resetCalibratedTimeStamp();
    }

    public void resetCalibratedTimeStamp() {
        setLastReceivedTimeStampTicksUnwrapped(0);
        mLastReceivedCalibratedTimeStamp = -1;

        mStreamingStartTimeSaved = false;
        mStreamingStartTimeMilliSecs = -1;

        mCurrentTimeStampCycle = 0;
    }

    public double getLastReceivedTimeStampTicksUnwrapped() {
        return mLastReceivedTimeStampTicksUnwrapped;
    }

    public void setLastReceivedTimeStampTicksUnwrapped(double lastReceivedTimeStampTicksUnwrapped) {
        mLastReceivedTimeStampTicksUnwrapped = lastReceivedTimeStampTicksUnwrapped;
    }

    public long getInitialTimeStampTicksSd() {
        return mInitialTimeStampTicksSd;
    }


    public void setInitialTimeStampTicksSd(long initialTimeStamp) {
        mInitialTimeStampTicksSd = initialTimeStamp;
    }

    public static class GuiLabelSensors {
        public static final String SYSTEM_TIMESTAMP = SensorSystemTimeStamp.ObjectClusterSensorName.SYSTEM_TIMESTAMP;
        public static final String TIMESTAMP = ObjectClusterSensorName.TIMESTAMP;
        public static final String DEVICE_PROPERTIES = "Device Properties";
    }

    public static class LABEL_SENSOR_TILE {
        public static final String STREAMING_PROPERTIES = GuiLabelSensors.DEVICE_PROPERTIES;
    }

    public static class ObjectClusterSensorName {
        public static final String TIMESTAMP = "Timestamp";

        public static final String TIMESTAMP_DIFFERENCE = "Timestamp Difference";
        public static final String REAL_TIME_CLOCK = "RealTime";

        public static final String TIMESTAMP_OFFSET = "Offset";
    }

    public static class DatabaseChannelHandles {
        public static final String TIMESTAMP = "TimeStamp";
        public static final String TIMESTAMP_EXPORT = "Timestamp";
        public static final String OFFSET_TIMESTAMP = "OFFSET";//"Offset";

        public static final String REAL_TIME_CLOCK = "Real_Time_Clock";
    }

    public static final class DatabaseConfigHandle {
        public static final String INITIAL_TIMESTAMP = "Initial_Timestamp";

    }

}
