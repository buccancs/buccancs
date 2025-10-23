package com.shimmerresearch.verisense.payloaddesign;

import com.shimmerresearch.driver.Configuration;
import com.shimmerresearch.driverUtilities.UtilShimmer;
import com.shimmerresearch.sensors.AbstractSensor;
import com.shimmerresearch.verisense.UtilVerisenseDriver;
import com.shimmerresearch.verisense.VerisenseDevice;
import com.shimmerresearch.verisense.payloaddesign.DataBlockDetails;
import com.shimmerresearch.verisense.sensors.SensorVerisenseClock;

import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.apache.commons.lang3.StringUtils;

/* loaded from: classes2.dex */
public class PayloadContentsDetailsV8orAbove extends PayloadContentsDetails {
    private static final boolean RESET_GYRO_ON_THE_FLY_CALIB_DURING_TIME_GAPS = false;
    private static final long serialVersionUID = -3062638445721582576L;

    public PayloadContentsDetailsV8orAbove(VerisenseDevice verisenseDevice) {
        super(verisenseDevice);
    }

    public static List<AbstractSensor.SENSORS> getListOfSensorsNotInNewPayload(TreeMap<AbstractSensor.SENSORS, List<DataSegmentDetails>> treeMap, TreeMap<AbstractSensor.SENSORS, List<DataSegmentDetails>> treeMap2) {
        ArrayList arrayList = new ArrayList();
        for (AbstractSensor.SENSORS sensors : treeMap.keySet()) {
            if (!treeMap2.containsKey(sensors)) {
                arrayList.add(sensors);
            }
        }
        return arrayList;
    }

    public static List<AbstractSensor.SENSORS> getListOfSensorsWithTimeGapBetweenPayloads(TreeMap<AbstractSensor.SENSORS, List<DataSegmentDetails>> treeMap, TreeMap<AbstractSensor.SENSORS, List<DataSegmentDetails>> treeMap2) {
        ArrayList arrayList = new ArrayList();
        for (AbstractSensor.SENSORS sensors : treeMap2.keySet()) {
            if (!isContinuityBetweenPrevAndCurrentPayloadsForSensor(sensors, treeMap, treeMap2)) {
                arrayList.add(sensors);
            }
        }
        return arrayList;
    }

    public static boolean isContinuityBetweenPrevAndCurrentPayloadsForSensor(AbstractSensor.SENSORS sensors, TreeMap<AbstractSensor.SENSORS, List<DataSegmentDetails>> treeMap, TreeMap<AbstractSensor.SENSORS, List<DataSegmentDetails>> treeMap2) {
        List<DataSegmentDetails> list = treeMap.get(sensors);
        if (list == null) {
            System.out.println("CSV Split logic - sensor not present in previous payload = " + sensors);
            return false;
        }
        List<DataSegmentDetails> list2 = treeMap2.get(sensors);
        if (list2 == null) {
            System.out.println("CSV Split logic - sensor not present in new payload = " + sensors);
            return false;
        }
        String strIsDataBlockContinuous = UtilCsvSplitting.isDataBlockContinuous(sensors, list.get(list.size() - 1), list2.get(0).getListOfDataBlocks().get(0));
        if (strIsDataBlockContinuous.isEmpty()) {
            return true;
        }
        System.out.println("CSV Split logic - unexpected time gap between payloads for sensor = " + sensors);
        System.out.println(strIsDataBlockContinuous);
        return false;
    }

    @Override // com.shimmerresearch.verisense.payloaddesign.PayloadContentsDetails
    public void parsePayloadContentsMetaData(int i) throws Exception {
        long endTimeTicks;
        int i2 = 0;
        int i3 = 0;
        do {
            try {
                DataBlockDetails dataBlockMetaData = this.verisenseDevice.parseDataBlockMetaData(this.byteBuffer, i2, i + i2, i3, getPayloadIndex());
                i3++;
                this.listOfDataBlocksInOrder.add(dataBlockMetaData);
                this.setOfPayloadSensorIds.add(dataBlockMetaData.datablockSensorId);
                i2 += dataBlockMetaData.qtySensorDataBytesInDatablock + 4;
            } catch (Exception e) {
                printDataBlockMetadataReport();
                throw e;
            }
        } while (!isParserAtEndOfBuffer(this.byteBuffer.length, i2));
        long timeMinutesAtIndex = VerisenseTimeDetails.parseTimeMinutesAtIndex(this.byteBuffer, i2);
        int i4 = i2 + 4;
        if (this.verisenseDevice.isPayloadDesignV9orAbove()) {
            endTimeTicks = VerisenseTimeDetails.parseTimeTicksAtIndex(this.byteBuffer, i4);
            i4 = i2 + 7;
        } else {
            endTimeTicks = this.listOfDataBlocksInOrder.get(this.listOfDataBlocksInOrder.size() - 1).getTimeDetailsRwc().getEndTimeTicks();
        }
        int batteryVoltageBytes = parseBatteryVoltageBytes(parseTemperatureBytes(i4));
        if (this.verisenseDevice.isPayloadDesignV10orAbove()) {
            parseMicrocontrollerClockBytes(batteryVoltageBytes);
        }
        if (this.verisenseDevice.isPayloadDesignV11orAbove()) {
            backfillDataBlockUcClockTimestamps();
            calculatePayloadStartTimeMsUcClock();
            double dConvertRtcMinutesAndTicksToMs = SensorVerisenseClock.convertRtcMinutesAndTicksToMs(timeMinutesAtIndex, endTimeTicks);
            getTimeDetailsRwc().setEndTimeMs(getTimeDetailsUcClock().getEndTimeMs() + dConvertRtcMinutesAndTicksToMs);
            applyRwcOffsetToDataBlockRwcClockTimestamps(dConvertRtcMinutesAndTicksToMs);
            calculatePayloadStartTimeMsRwc();
        } else {
            getTimeDetailsRwc().setEndTimeAndCalculateMs(timeMinutesAtIndex, endTimeTicks);
            backfillDataBlockRwcTimestamps();
            calculatePayloadStartTimeMsRwc();
            if (this.verisenseDevice.isPayloadDesignV10orAbove()) {
                VerisenseTimeDetails timeDetailsUcClock = getTimeDetailsUcClock();
                timeDetailsUcClock.setStartTimeMs(timeDetailsUcClock.getEndTimeMs() - calculatePayloadDurationMs());
                applyRwcOffsetToDataBlockUcClockTimestamps(getTimeDetailsRwc().getEndTimeMs() - getTimeDetailsUcClock().getEndTimeMs());
            }
        }
        if (SPLIT_CSVS_AT_MIDDAY_AND_MIDNIGHT && UtilVerisenseDriver.isTransitionMidDayOrMidnight(getStartTimeRwcMs(), getEndTimeRwcMs())) {
            splitDataBlocksAtMiddayMidnight(this.listOfDataBlocksInOrder, this.verisenseDevice.getMapOfSensorIdsPerDataBlock().keySet());
        }
        UtilCsvSplitting.populateExpectedPayloadTsDiffLimitMapIfNeeded(this.verisenseDevice, this.verisenseDevice.getMapOfSensorIdsPerDataBlock());
        calculateAndSetPayloadPackagingDelayMs();
    }

    private void printDataBlockMetadataReport() {
        System.err.println("\nDataBlockDetails = null while parsing Metadata, stopping parsing early");
        System.err.println("Parsing History for payload index " + getPayloadIndex() + StringUtils.LF);
        System.err.println("dataBlockStartByteIndexInPayload, dataBlockStartByteIndexInFile, dataBlockIndexInPayload, datablockSensorId, dataBlockSize, endTimeTicks");
        for (DataBlockDetails dataBlockDetails : this.listOfDataBlocksInOrder) {
            long endTimeTicks = (this.verisenseDevice.isPayloadDesignV11orAbove() ? dataBlockDetails.getTimeDetailsUcClock() : dataBlockDetails.getTimeDetailsRwc()).getEndTimeTicks();
            System.err.println(dataBlockDetails.dataBlockStartByteIndexInPayload + "/" + this.byteBuffer.length + ",\t" + UtilShimmer.intToHexStringFormatted(dataBlockDetails.dataBlockStartByteIndexInFile, 4, true) + ",\t" + dataBlockDetails.getDataBlockIndexInPayload() + ",\t" + dataBlockDetails.datablockSensorId + StringUtils.SPACE + UtilShimmer.byteToHexStringFormatted((byte) dataBlockDetails.datablockSensorId.ordinal()) + ",\t" + dataBlockDetails.qtySensorDataBytesInDatablock + ",\t" + endTimeTicks);
        }
        System.err.println("End of report");
    }

    protected int parseMicrocontrollerClockBytes(int i) {
        long timeMinutesAtIndex = VerisenseTimeDetails.parseTimeMinutesAtIndex(this.byteBuffer, i);
        long timeTicksAtIndex = VerisenseTimeDetails.parseTimeTicksAtIndex(this.byteBuffer, i + 4);
        int i2 = i + 7;
        getTimeDetailsUcClock().setEndTimeAndCalculateMs(timeMinutesAtIndex, timeTicksAtIndex);
        return i2;
    }

    private void splitDataBlocksAtMiddayMidnight(List<DataBlockDetails> list, Set<DataBlockDetails.DATABLOCK_SENSOR_ID> set) throws IOException {
        double startTimeRwcMs;
        boolean z = false;
        for (DataBlockDetails.DATABLOCK_SENSOR_ID datablock_sensor_id : set) {
            ListIterator<DataBlockDetails> listIterator = list.listIterator();
            int i = 0;
            while (listIterator.hasNext()) {
                DataBlockDetails next = listIterator.next();
                if (next.datablockSensorId == datablock_sensor_id) {
                    double startTimeRwcMs2 = next.getStartTimeRwcMs();
                    DataBlockDetails dataBlockDetailsSearchForNextDatablockForDataBlockId = searchForNextDatablockForDataBlockId(list, i + 1, datablock_sensor_id);
                    if (dataBlockDetailsSearchForNextDatablockForDataBlockId == null) {
                        startTimeRwcMs = next.getEndTimeRwcMs();
                    } else {
                        startTimeRwcMs = dataBlockDetailsSearchForNextDatablockForDataBlockId.getStartTimeRwcMs();
                    }
                    if (UtilVerisenseDriver.isTransitionMidDayOrMidnight(startTimeRwcMs2, startTimeRwcMs)) {
                        DataBlockDetails dataBlockDetailsCheckAndSplitIndividualDataBlock = checkAndSplitIndividualDataBlock(next, i);
                        if (dataBlockDetailsCheckAndSplitIndividualDataBlock != null) {
                            listIterator.add(dataBlockDetailsCheckAndSplitIndividualDataBlock);
                            z = true;
                        } else if (dataBlockDetailsSearchForNextDatablockForDataBlockId != null) {
                            dataBlockDetailsSearchForNextDatablockForDataBlockId.setFirstUnsplitDataBlockAfterMiddayMidnightTransition();
                        }
                    }
                }
                i++;
            }
        }
        if (z) {
            for (int i2 = 0; i2 < list.size(); i2++) {
                list.get(i2).setDataBlockIndexInPayload(i2);
            }
        }
    }

    private DataBlockDetails searchForNextDatablockForDataBlockId(List<DataBlockDetails> list, int i, DataBlockDetails.DATABLOCK_SENSOR_ID datablock_sensor_id) {
        while (i < list.size()) {
            DataBlockDetails dataBlockDetails = list.get(i);
            if (dataBlockDetails.datablockSensorId == datablock_sensor_id) {
                return dataBlockDetails;
            }
            i++;
        }
        return null;
    }

    private DataBlockDetails checkAndSplitIndividualDataBlock(DataBlockDetails dataBlockDetails, int i) throws IOException {
        double startTimeMs;
        double d;
        double timestampDiffInS = dataBlockDetails.getTimestampDiffInS() * 1000.0d;
        double startTimeMs2 = dataBlockDetails.getTimeDetailsRwc().getStartTimeMs();
        double d2 = startTimeMs2 + timestampDiffInS;
        if (this.verisenseDevice.isPayloadDesignV10orAbove()) {
            startTimeMs = dataBlockDetails.getTimeDetailsUcClock().getStartTimeMs();
            d = startTimeMs + timestampDiffInS;
        } else {
            startTimeMs = Double.NaN;
            d = Double.NaN;
        }
        int i2 = 0;
        double d3 = startTimeMs2;
        double d4 = startTimeMs;
        double d5 = d;
        while (i2 < dataBlockDetails.getSampleCount() - 1) {
            long j = (long) d3;
            long j2 = (long) d2;
            double d6 = timestampDiffInS;
            System.out.println("Checking..." + UtilVerisenseDriver.convertMilliSecondsToCsvHeaderFormat(j) + "\tvs.\t" + UtilVerisenseDriver.convertMilliSecondsToCsvHeaderFormat(j2));
            if (UtilVerisenseDriver.isTransitionMidDayOrMidnight(d3, d2)) {
                System.out.println("Midday/Midnight transition detected within data block for Sensor=" + dataBlockDetails.listOfSensorClassKeys + ", DataBlockIndex = " + i + ", SampleIndex=" + i2 + ", Timing [CurrentSample=" + UtilVerisenseDriver.convertMilliSecondsToCsvHeaderFormat(j) + ", NextSample=" + UtilVerisenseDriver.convertMilliSecondsToCsvHeaderFormat(j2) + "]");
                System.out.println("  |_Splitting DataBlock:");
                DataBlockDetails dataBlockDetailsDeepClone = dataBlockDetails.deepClone();
                PrintStream printStream = System.out;
                String strGenerateDebugStr = dataBlockDetails.generateDebugStr();
                StringBuilder sb = new StringBuilder("    |_Original=");
                sb.append(strGenerateDebugStr);
                printStream.println(sb.toString());
                int i3 = i2 + 1;
                dataBlockDetails.splitAndEndBeforeSampleIndex(i3, d3, d4);
                dataBlockDetailsDeepClone.splitAndStartAtSampleIndex(i3, d2, d5);
                System.out.println("    |_Split1=" + dataBlockDetails.generateDebugStr());
                System.out.println("    |_Split2=" + dataBlockDetailsDeepClone.generateDebugStr());
                return dataBlockDetailsDeepClone;
            }
            d3 += d6;
            d2 += d6;
            if (this.verisenseDevice.isPayloadDesignV10orAbove()) {
                d4 += d6;
                d5 += d6;
            }
            i2++;
            timestampDiffInS = d6;
        }
        return null;
    }

    private boolean isParserAtEndOfBuffer(int i, int i2) {
        int i3;
        if (this.verisenseDevice.isPayloadDesignV10orAbove()) {
            i3 = 18;
        } else {
            i3 = this.verisenseDevice.isPayloadDesignV9orAbove() ? 11 : 8;
        }
        return i - i2 < i3 + 2;
    }

    private void backfillDataBlockRwcTimestamps() {
        backfillDataBlockUcClockOrRwcTimestamps(false);
    }

    private void backfillDataBlockUcClockTimestamps() {
        backfillDataBlockUcClockOrRwcTimestamps(true);
    }

    private void backfillDataBlockUcClockOrRwcTimestamps(boolean z) {
        VerisenseTimeDetails timeDetailsUcClock = z ? getTimeDetailsUcClock() : getTimeDetailsRwc();
        long endTimeMinutes = timeDetailsUcClock.getEndTimeMinutes();
        long endTimeTicks = timeDetailsUcClock.getEndTimeTicks();
        DataBlockDetails dataBlockDetails = this.listOfDataBlocksInOrder.get(this.listOfDataBlocksInOrder.size() - 1);
        if (this.verisenseDevice.isPayloadDesignV9orAbove()) {
            if (endTimeTicks < (z ? dataBlockDetails.getTimeDetailsUcClock() : dataBlockDetails.getTimeDetailsRwc()).getEndTimeTicks()) {
                endTimeMinutes--;
            }
            if (z) {
                dataBlockDetails.setUcClockEndTimeMinutesAndCalculateTimings(endTimeMinutes);
            } else {
                dataBlockDetails.setRwcEndTimeMinutesAndCalculateTimings(endTimeMinutes);
            }
        } else {
            dataBlockDetails.setRwcEndTimeMinutesAndCalculateTimings(endTimeMinutes);
        }
        if (this.listOfDataBlocksInOrder.size() > 1) {
            for (int size = this.listOfDataBlocksInOrder.size() - 2; size >= 0; size--) {
                this.listOfDataBlocksInOrder.get(size).setUcClockOrRwcEndTimeMinutesFromSubsequentDataBlock(this.listOfDataBlocksInOrder.get(size + 1), z);
            }
        }
    }

    private void applyRwcOffsetToDataBlockUcClockTimestamps(double d) {
        for (DataBlockDetails dataBlockDetails : this.listOfDataBlocksInOrder) {
            VerisenseTimeDetails timeDetailsUcClock = dataBlockDetails.getTimeDetailsUcClock();
            VerisenseTimeDetails timeDetailsRwc = dataBlockDetails.getTimeDetailsRwc();
            timeDetailsUcClock.setStartTimeMs(timeDetailsRwc.getStartTimeMs() - d);
            timeDetailsUcClock.setEndTimeMs(timeDetailsRwc.getEndTimeMs() - d);
        }
    }

    private void applyRwcOffsetToDataBlockRwcClockTimestamps(double d) {
        for (DataBlockDetails dataBlockDetails : this.listOfDataBlocksInOrder) {
            VerisenseTimeDetails timeDetailsUcClock = dataBlockDetails.getTimeDetailsUcClock();
            VerisenseTimeDetails timeDetailsRwc = dataBlockDetails.getTimeDetailsRwc();
            timeDetailsRwc.setStartTimeMs(timeDetailsUcClock.getStartTimeMs() + d);
            timeDetailsRwc.setEndTimeMs(timeDetailsUcClock.getEndTimeMs() + d);
        }
    }

    @Override // com.shimmerresearch.verisense.payloaddesign.PayloadContentsDetails
    public void parsePayloadSensorData() {
        for (AbstractSensor.SENSORS sensors : this.datasetToSave.getMapOfDataSegmentsPerSensor().keySet()) {
            int i = 0;
            int i2 = 0;
            while (i < this.listOfDataBlocksInOrder.size() && this.listOfDataBlocksInOrder.get(i) != null) {
                DataBlockDetails dataBlockDetails = this.listOfDataBlocksInOrder.get(i);
                if (dataBlockDetails.isFirstDataBlockAfterSplitBySampleDueToTimeGapOrOverlap() && sensors != AbstractSensor.SENSORS.LSM6DS3) {
                    this.verisenseDevice.resetAlgorithmBuffersAssociatedWithSensor(sensors);
                }
                if (!dataBlockDetails.isSecondPartOfSplitDataBlock()) {
                    i2 += 4;
                }
                if (dataBlockDetails.listOfSensorClassKeys.contains(sensors)) {
                    this.verisenseDevice.parseDataBlockData(dataBlockDetails, this.byteBuffer, i2, Configuration.COMMUNICATION_TYPE.SD);
                }
                i2 += dataBlockDetails.qtySensorDataBytesInDatablock;
                i++;
                if (isParserAtEndOfBuffer(this.byteBuffer.length, i2)) {
                    break;
                }
            }
            System.err.println("DataBlockDetails = null while parsing sensor data, stopping parsing early");
        }
    }

    public List<AbstractSensor.SENSORS> getListOfSensorsWithTimeGapsWithinThePayload() {
        ArrayList arrayList = new ArrayList();
        for (Map.Entry<AbstractSensor.SENSORS, List<DataSegmentDetails>> entry : getMapOfDataSegmentsPerSensor().entrySet()) {
            if (entry.getValue().size() > 1) {
                arrayList.add(entry.getKey());
            }
        }
        return arrayList;
    }
}
