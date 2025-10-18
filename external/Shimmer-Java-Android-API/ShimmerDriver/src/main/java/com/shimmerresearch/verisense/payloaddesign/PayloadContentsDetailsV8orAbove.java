package com.shimmerresearch.verisense.payloaddesign;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

import com.shimmerresearch.driver.ObjectCluster;
import com.shimmerresearch.driver.Configuration.COMMUNICATION_TYPE;
import com.shimmerresearch.driverUtilities.ChannelDetails;
import com.shimmerresearch.driverUtilities.UtilShimmer;
import com.shimmerresearch.sensors.AbstractSensor.SENSORS;
import com.shimmerresearch.verisense.UtilVerisenseDriver;
import com.shimmerresearch.verisense.VerisenseDevice;
import com.shimmerresearch.verisense.payloaddesign.AsmBinaryFileConstants.BYTE_COUNT;
import com.shimmerresearch.verisense.payloaddesign.DataBlockDetails.DATABLOCK_SENSOR_ID;
import com.shimmerresearch.verisense.sensors.SensorVerisenseClock;

public class PayloadContentsDetailsV8orAbove extends PayloadContentsDetails {

    private static final long serialVersionUID = -3062638445721582576L;

    private static final boolean RESET_GYRO_ON_THE_FLY_CALIB_DURING_TIME_GAPS = false;

    public PayloadContentsDetailsV8orAbove(VerisenseDevice verisenseDevice) {
        super(verisenseDevice);
    }

    public static List<SENSORS> getListOfSensorsNotInNewPayload(TreeMap<SENSORS, List<DataSegmentDetails>> previousMapOfDataBlocks,
                                                                TreeMap<SENSORS, List<DataSegmentDetails>> currentMapOfDataBlocks) {
        List<SENSORS> listOfSensorsNotInPayload = new ArrayList<SENSORS>();
        for (SENSORS sensorClassKey : previousMapOfDataBlocks.keySet()) {
            if (!currentMapOfDataBlocks.containsKey(sensorClassKey)) {
                listOfSensorsNotInPayload.add(sensorClassKey);
            }
        }
        return listOfSensorsNotInPayload;
    }

    public static List<SENSORS> getListOfSensorsWithTimeGapBetweenPayloads(TreeMap<SENSORS, List<DataSegmentDetails>> previousMapOfDataBlocks, TreeMap<SENSORS, List<DataSegmentDetails>> currentMapOfDataBlocks) {
        List<SENSORS> listOfSensorsWithTimeGaps = new ArrayList<SENSORS>();
        for (SENSORS sensorClassKey : currentMapOfDataBlocks.keySet()) {
            if (!isContinuityBetweenPrevAndCurrentPayloadsForSensor(sensorClassKey, previousMapOfDataBlocks, currentMapOfDataBlocks)) {
                listOfSensorsWithTimeGaps.add(sensorClassKey);
            }
        }
        return listOfSensorsWithTimeGaps;
    }

    public static boolean isContinuityBetweenPrevAndCurrentPayloadsForSensor(SENSORS sensorClassKey,
                                                                             TreeMap<SENSORS, List<DataSegmentDetails>> previousMapOfDataSegments,
                                                                             TreeMap<SENSORS, List<DataSegmentDetails>> currentMapOfDataSegments) {

        List<DataSegmentDetails> previousDataSegmentList = previousMapOfDataSegments.get(sensorClassKey);
        if (previousDataSegmentList == null) {
            System.out.println("CSV Split logic - sensor not present in previous payload = " + sensorClassKey);
            return false;
        }

        List<DataSegmentDetails> currentDataSegment = currentMapOfDataSegments.get(sensorClassKey);
        if (currentDataSegment == null) {
            System.out.println("CSV Split logic - sensor not present in new payload = " + sensorClassKey);
            return false;
        }

        DataSegmentDetails dataSegmentDetailsPrevious = previousDataSegmentList.get(previousDataSegmentList.size() - 1);

        List<DataBlockDetails> currentListOfDataBlocks = currentDataSegment.get(0).getListOfDataBlocks();
        DataBlockDetails nextDataBlockDetails = currentListOfDataBlocks.get(0);

        String result = UtilCsvSplitting.isDataBlockContinuous(sensorClassKey, dataSegmentDetailsPrevious, nextDataBlockDetails);
        if (!result.isEmpty()) {
            System.out.println("CSV Split logic - unexpected time gap between payloads for sensor = " + sensorClassKey);
            System.out.println(result);
            return false;
        }
        return true;
    }

    @Override
    public void parsePayloadContentsMetaData(int binFileByteIndex) throws IOException {
        int currentByteIndexInPayload = 0;
        int dataBlockIndexInPayload = 0;

        while (true) {
            int dataBlockStartByteIndexInFile = binFileByteIndex + currentByteIndexInPayload;

            DataBlockDetails dataBlockDetails;
            try {
                dataBlockDetails = verisenseDevice.parseDataBlockMetaData(byteBuffer, currentByteIndexInPayload, dataBlockStartByteIndexInFile, dataBlockIndexInPayload, getPayloadIndex());
                dataBlockIndexInPayload++;
            } catch (
                    Exception e) {
                printDataBlockMetadataReport();
                throw (e);
            }


            listOfDataBlocksInOrder.add(dataBlockDetails);
            setOfPayloadSensorIds.add(dataBlockDetails.datablockSensorId);

            int dataBlockTotalSize = BYTE_COUNT.PAYLOAD_CONTENTS_GEN8_SENSOR_ID + BYTE_COUNT.PAYLOAD_CONTENTS_RTC_BYTES_TICKS + dataBlockDetails.qtySensorDataBytesInDatablock;
            currentByteIndexInPayload += dataBlockTotalSize;

            if (isParserAtEndOfBuffer(byteBuffer.length, currentByteIndexInPayload)) {
                break;
            }
        }

        long rwcTimeMinutes = VerisenseTimeDetails.parseTimeMinutesAtIndex(byteBuffer, currentByteIndexInPayload);
        currentByteIndexInPayload += BYTE_COUNT.PAYLOAD_CONTENTS_RTC_BYTES_MINUTES;

        long rwcTimeTicks = 0;
        if (verisenseDevice.isPayloadDesignV9orAbove()) {
            rwcTimeTicks = VerisenseTimeDetails.parseTimeTicksAtIndex(byteBuffer, currentByteIndexInPayload);
            currentByteIndexInPayload += BYTE_COUNT.PAYLOAD_CONTENTS_RTC_BYTES_TICKS;
        } else {
            rwcTimeTicks = listOfDataBlocksInOrder.get(listOfDataBlocksInOrder.size() - 1).getTimeDetailsRwc().getEndTimeTicks();
        }

        currentByteIndexInPayload = parseTemperatureBytes(currentByteIndexInPayload);
        currentByteIndexInPayload = parseBatteryVoltageBytes(currentByteIndexInPayload);

        if (verisenseDevice.isPayloadDesignV10orAbove()) {
            currentByteIndexInPayload = parseMicrocontrollerClockBytes(currentByteIndexInPayload);
        }


        if (verisenseDevice.isPayloadDesignV11orAbove()) {
            backfillDataBlockUcClockTimestamps();
            calculatePayloadStartTimeMsUcClock();

            double rwcOffsetMs = SensorVerisenseClock.convertRtcMinutesAndTicksToMs(rwcTimeMinutes, rwcTimeTicks);
            getTimeDetailsRwc().setEndTimeMs(getTimeDetailsUcClock().getEndTimeMs() + rwcOffsetMs);
            applyRwcOffsetToDataBlockRwcClockTimestamps(rwcOffsetMs);

            calculatePayloadStartTimeMsRwc();
        } else {
            getTimeDetailsRwc().setEndTimeAndCalculateMs(rwcTimeMinutes, rwcTimeTicks);
            backfillDataBlockRwcTimestamps();
            calculatePayloadStartTimeMsRwc();

            if (verisenseDevice.isPayloadDesignV10orAbove()) {
                VerisenseTimeDetails timeDetailsUcClock = getTimeDetailsUcClock();
                timeDetailsUcClock.setStartTimeMs(timeDetailsUcClock.getEndTimeMs() - calculatePayloadDurationMs());

                double rwcOffsetMs = getTimeDetailsRwc().getEndTimeMs() - getTimeDetailsUcClock().getEndTimeMs();
                applyRwcOffsetToDataBlockUcClockTimestamps(rwcOffsetMs);
            }
        }

        if (SPLIT_CSVS_AT_MIDDAY_AND_MIDNIGHT && UtilVerisenseDriver.isTransitionMidDayOrMidnight(getStartTimeRwcMs(), getEndTimeRwcMs())) {
            splitDataBlocksAtMiddayMidnight(listOfDataBlocksInOrder, verisenseDevice.getMapOfSensorIdsPerDataBlock().keySet());
        }

        UtilCsvSplitting.populateExpectedPayloadTsDiffLimitMapIfNeeded(verisenseDevice, verisenseDevice.getMapOfSensorIdsPerDataBlock());
        calculateAndSetPayloadPackagingDelayMs();
    }

    private void printDataBlockMetadataReport() {
        System.err.println("\nDataBlockDetails = null while parsing Metadata, stopping parsing early");

        System.err.println("Parsing History for payload index " + getPayloadIndex() + "\n");

        System.err.println("dataBlockStartByteIndexInPayload, dataBlockStartByteIndexInFile, dataBlockIndexInPayload, datablockSensorId, dataBlockSize, endTimeTicks");
        for (DataBlockDetails dataBlockDetails : listOfDataBlocksInOrder) {
            long dataBlockEndTimeTicks = verisenseDevice.isPayloadDesignV11orAbove() ? dataBlockDetails.getTimeDetailsUcClock().getEndTimeTicks() : dataBlockDetails.getTimeDetailsRwc().getEndTimeTicks();

            System.err.println(dataBlockDetails.dataBlockStartByteIndexInPayload + "/" + byteBuffer.length
                    + ",\t" + UtilShimmer.intToHexStringFormatted(dataBlockDetails.dataBlockStartByteIndexInFile, 4, true)
                    + ",\t" + dataBlockDetails.getDataBlockIndexInPayload()
                    + ",\t" + dataBlockDetails.datablockSensorId + " " + UtilShimmer.byteToHexStringFormatted((byte) dataBlockDetails.datablockSensorId.ordinal())
                    + ",\t" + dataBlockDetails.qtySensorDataBytesInDatablock
                    + ",\t" + dataBlockEndTimeTicks);
        }
        System.err.println("End of report");
    }

    protected int parseMicrocontrollerClockBytes(int currentByteIndex) {
        long ucEndTimeMinutes = VerisenseTimeDetails.parseTimeMinutesAtIndex(byteBuffer, currentByteIndex);
        currentByteIndex += BYTE_COUNT.PAYLOAD_CONTENTS_RTC_BYTES_MINUTES;
        long ucEndTimeTicks = VerisenseTimeDetails.parseTimeTicksAtIndex(byteBuffer, currentByteIndex);
        currentByteIndex += BYTE_COUNT.PAYLOAD_CONTENTS_RTC_BYTES_TICKS;

        getTimeDetailsUcClock().setEndTimeAndCalculateMs(ucEndTimeMinutes, ucEndTimeTicks);

        return currentByteIndex;
    }

    private void splitDataBlocksAtMiddayMidnight(List<DataBlockDetails> listOfDataBlocks, Set<DATABLOCK_SENSOR_ID> setOfSensorIds) {
        boolean aDataBlockWasSplitSampleBySample = false;
        for (DATABLOCK_SENSOR_ID datablockSensorId : setOfSensorIds) {
            int dataBlockIndex = 0;

            ListIterator<DataBlockDetails> iterator = listOfDataBlocks.listIterator();
            while (iterator.hasNext()) {
                DataBlockDetails dataBlockDetails = iterator.next();
                if (dataBlockDetails.datablockSensorId == datablockSensorId) {
                    double startTimeMs = dataBlockDetails.getStartTimeRwcMs();
                    double endTimeMsToCheck;

                    DataBlockDetails dataBlockDetailsNext = searchForNextDatablockForDataBlockId(listOfDataBlocks, dataBlockIndex + 1, datablockSensorId);

                    if (dataBlockDetailsNext == null) {
                        endTimeMsToCheck = dataBlockDetails.getEndTimeRwcMs();
                    } else {
                        endTimeMsToCheck = dataBlockDetailsNext.getStartTimeRwcMs();
                    }

                    if (UtilVerisenseDriver.isTransitionMidDayOrMidnight(startTimeMs, endTimeMsToCheck)) {
                        DataBlockDetails dataBlockDetailsSplit = checkAndSplitIndividualDataBlock(dataBlockDetails, dataBlockIndex);
                        if (dataBlockDetailsSplit != null) {
                            iterator.add(dataBlockDetailsSplit);
                            aDataBlockWasSplitSampleBySample = true;
                        } else {
                            if (dataBlockDetailsNext != null) {
                                dataBlockDetailsNext.setFirstUnsplitDataBlockAfterMiddayMidnightTransition();
                            }
                        }
                    }
                }
                dataBlockIndex++;
            }

        }

        if (aDataBlockWasSplitSampleBySample) {
            for (int i = 0; i < listOfDataBlocks.size(); i++) {
                listOfDataBlocks.get(i).setDataBlockIndexInPayload(i);
            }
        }

    }

    private DataBlockDetails searchForNextDatablockForDataBlockId(List<DataBlockDetails> listOfDataBlocks,
                                                                  int startSearchIndex, DATABLOCK_SENSOR_ID datablockSensorId) {
        for (int i = startSearchIndex; i < listOfDataBlocks.size(); i++) {
            DataBlockDetails nextDataBlock = listOfDataBlocks.get(i);
            if (nextDataBlock.datablockSensorId == datablockSensorId) {
                return nextDataBlock;
            }
        }
        return null;
    }

    private DataBlockDetails checkAndSplitIndividualDataBlock(DataBlockDetails dataBlockDetailsOriginal, int dataBlockIndex) {

        double timestampDiffMs = dataBlockDetailsOriginal.getTimestampDiffInS() * 1000;

        double timestampMsCurrentRwc = dataBlockDetailsOriginal.getTimeDetailsRwc().getStartTimeMs();
        double timestampMsNextRwc = timestampMsCurrentRwc + timestampDiffMs;

        double timestampMsCurrentUcClock = Double.NaN, timestampMsNextUcClock = Double.NaN;
        if (verisenseDevice.isPayloadDesignV10orAbove()) {
            timestampMsCurrentUcClock = dataBlockDetailsOriginal.getTimeDetailsUcClock().getStartTimeMs();
            timestampMsNextUcClock = timestampMsCurrentUcClock + timestampDiffMs;
        }

        for (int sampleIndex = 0; sampleIndex < dataBlockDetailsOriginal.getSampleCount() - 1; sampleIndex++) {

            System.out.println("Checking..." + UtilVerisenseDriver.convertMilliSecondsToCsvHeaderFormat((long) timestampMsCurrentRwc) + "\tvs.\t" + UtilVerisenseDriver.convertMilliSecondsToCsvHeaderFormat((long) timestampMsNextRwc));

            if (UtilVerisenseDriver.isTransitionMidDayOrMidnight(timestampMsCurrentRwc, timestampMsNextRwc)) {
                System.out.println("Midday/Midnight transition detected within data block for Sensor=" + dataBlockDetailsOriginal.listOfSensorClassKeys + ", DataBlockIndex = " + dataBlockIndex + ", SampleIndex=" + sampleIndex
                        + ", Timing [CurrentSample=" + UtilVerisenseDriver.convertMilliSecondsToCsvHeaderFormat((long) timestampMsCurrentRwc)
                        + ", NextSample=" + UtilVerisenseDriver.convertMilliSecondsToCsvHeaderFormat((long) timestampMsNextRwc) + "]");
                System.out.println("  |_Splitting DataBlock:");

                DataBlockDetails dataBlockDetailsSplit = dataBlockDetailsOriginal.deepClone();

                System.out.println("    |_Original=" + dataBlockDetailsOriginal.generateDebugStr());

                dataBlockDetailsOriginal.splitAndEndBeforeSampleIndex(sampleIndex + 1, timestampMsCurrentRwc, timestampMsCurrentUcClock);
                dataBlockDetailsSplit.splitAndStartAtSampleIndex(sampleIndex + 1, timestampMsNextRwc, timestampMsNextUcClock);

                System.out.println("    |_Split1=" + dataBlockDetailsOriginal.generateDebugStr());
                System.out.println("    |_Split2=" + dataBlockDetailsSplit.generateDebugStr());

                return dataBlockDetailsSplit;
            }

            timestampMsCurrentRwc += timestampDiffMs;
            timestampMsNextRwc += timestampDiffMs;
            if (verisenseDevice.isPayloadDesignV10orAbove()) {
                timestampMsCurrentUcClock += timestampDiffMs;
                timestampMsNextUcClock += timestampDiffMs;
            }
        }
        return null;
    }

    private boolean isParserAtEndOfBuffer(int bufferLength, int currentByteIndex) {
        int footerLength = 0;
        if (verisenseDevice.isPayloadDesignV10orAbove()) {
            footerLength = BYTE_COUNT.PAYLOAD_CONTENTS_FOOTER_GEN10_OR_ABOVE;
        } else if (verisenseDevice.isPayloadDesignV9orAbove()) {
            footerLength = BYTE_COUNT.PAYLOAD_CONTENTS_FOOTER_GEN1_TO_GEN7_AND_GEN9;
        } else {
            footerLength = BYTE_COUNT.PAYLOAD_CONTENTS_FOOTER_GEN8_ONLY;
        }
        return (bufferLength - currentByteIndex) < (footerLength + BYTE_COUNT.PAYLOAD_CRC);
    }

    private void backfillDataBlockRwcTimestamps() {
        backfillDataBlockUcClockOrRwcTimestamps(false);
    }

    private void backfillDataBlockUcClockTimestamps() {
        backfillDataBlockUcClockOrRwcTimestamps(true);
    }

    private void backfillDataBlockUcClockOrRwcTimestamps(boolean backfilUcClock) {
        VerisenseTimeDetails payloadTimeDetails = backfilUcClock ? getTimeDetailsUcClock() : getTimeDetailsRwc();
        long payloadEndTimeMinutes = payloadTimeDetails.getEndTimeMinutes();
        long payloadEndTimeTicks = payloadTimeDetails.getEndTimeTicks();

        DataBlockDetails lastDataBlock = listOfDataBlocksInOrder.get(listOfDataBlocksInOrder.size() - 1);
        if (verisenseDevice.isPayloadDesignV9orAbove()) {
            long lastDataBlockMinutes = payloadEndTimeMinutes;
            VerisenseTimeDetails dataBlockTimeDetails = backfilUcClock ? lastDataBlock.getTimeDetailsUcClock() : lastDataBlock.getTimeDetailsRwc();

            if (payloadEndTimeTicks < dataBlockTimeDetails.getEndTimeTicks()) {
                lastDataBlockMinutes--;
            }
            if (backfilUcClock) {
                lastDataBlock.setUcClockEndTimeMinutesAndCalculateTimings(lastDataBlockMinutes);
            } else {
                lastDataBlock.setRwcEndTimeMinutesAndCalculateTimings(lastDataBlockMinutes);
            }
        } else {
            lastDataBlock.setRwcEndTimeMinutesAndCalculateTimings(payloadEndTimeMinutes);
        }

        if (listOfDataBlocksInOrder.size() > 1) {
            for (int i = listOfDataBlocksInOrder.size() - 2; i >= 0; i--) {
                DataBlockDetails currentDataBlock = listOfDataBlocksInOrder.get(i);
                DataBlockDetails subsequentDataBlock = listOfDataBlocksInOrder.get(i + 1);
                currentDataBlock.setUcClockOrRwcEndTimeMinutesFromSubsequentDataBlock(subsequentDataBlock, backfilUcClock);
            }
        }
    }

    private void applyRwcOffsetToDataBlockUcClockTimestamps(double rwcOffsetMs) {
        for (DataBlockDetails dataBlockDetails : listOfDataBlocksInOrder) {
            VerisenseTimeDetails dataBlockUcTimeDetailsUcClock = dataBlockDetails.getTimeDetailsUcClock();
            VerisenseTimeDetails dataBlockUcTimeDetailsRwcClock = dataBlockDetails.getTimeDetailsRwc();

            dataBlockUcTimeDetailsUcClock.setStartTimeMs(dataBlockUcTimeDetailsRwcClock.getStartTimeMs() - rwcOffsetMs);
            dataBlockUcTimeDetailsUcClock.setEndTimeMs(dataBlockUcTimeDetailsRwcClock.getEndTimeMs() - rwcOffsetMs);
        }
    }

    private void applyRwcOffsetToDataBlockRwcClockTimestamps(double rwcOffsetMs) {
        for (DataBlockDetails dataBlockDetails : listOfDataBlocksInOrder) {
            VerisenseTimeDetails dataBlockUcTimeDetailsUcClock = dataBlockDetails.getTimeDetailsUcClock();
            VerisenseTimeDetails dataBlockUcTimeDetailsRwcClock = dataBlockDetails.getTimeDetailsRwc();

            dataBlockUcTimeDetailsRwcClock.setStartTimeMs(dataBlockUcTimeDetailsUcClock.getStartTimeMs() + rwcOffsetMs);
            dataBlockUcTimeDetailsRwcClock.setEndTimeMs(dataBlockUcTimeDetailsUcClock.getEndTimeMs() + rwcOffsetMs);
        }
    }

    @Override
    public void parsePayloadSensorData() {
        for (SENSORS sensorClassKey : datasetToSave.getMapOfDataSegmentsPerSensor().keySet()) {
            int dataBlockIndex = 0;
            int currentByteIndex = 0;
            while (true) {
                if (dataBlockIndex >= listOfDataBlocksInOrder.size() || listOfDataBlocksInOrder.get(dataBlockIndex) == null) {
                    System.err.println("DataBlockDetails = null while parsing sensor data, stopping parsing early");
                    break;
                }
                DataBlockDetails dataBlockDetails = listOfDataBlocksInOrder.get(dataBlockIndex);

                if (dataBlockDetails.isFirstDataBlockAfterSplitBySampleDueToTimeGapOrOverlap()) {
                    if (sensorClassKey != SENSORS.LSM6DS3 || RESET_GYRO_ON_THE_FLY_CALIB_DURING_TIME_GAPS) {
                        verisenseDevice.resetAlgorithmBuffersAssociatedWithSensor(sensorClassKey);
                    }
                }

                if (!dataBlockDetails.isSecondPartOfSplitDataBlock()) {
                    currentByteIndex += BYTE_COUNT.PAYLOAD_CONTENTS_GEN8_SENSOR_ID + BYTE_COUNT.PAYLOAD_CONTENTS_RTC_BYTES_TICKS;
                }

                if (dataBlockDetails.listOfSensorClassKeys.contains(sensorClassKey)) {
                    verisenseDevice.parseDataBlockData(dataBlockDetails, byteBuffer, currentByteIndex, COMMUNICATION_TYPE.SD);
                }
                currentByteIndex += dataBlockDetails.qtySensorDataBytesInDatablock;

                dataBlockIndex++;

                if (isParserAtEndOfBuffer(byteBuffer.length, currentByteIndex)) {
                    break;
                }
            }
        }

    }

    public List<SENSORS> getListOfSensorsWithTimeGapsWithinThePayload() {
        List<SENSORS> listOfSensorsWithTimeGaps = new ArrayList<SENSORS>();
        for (Entry<SENSORS, List<DataSegmentDetails>> entry : getMapOfDataSegmentsPerSensor().entrySet()) {
            if (entry.getValue().size() > 1) {
                listOfSensorsWithTimeGaps.add(entry.getKey());
            }
        }
        return listOfSensorsWithTimeGaps;
    }

}
