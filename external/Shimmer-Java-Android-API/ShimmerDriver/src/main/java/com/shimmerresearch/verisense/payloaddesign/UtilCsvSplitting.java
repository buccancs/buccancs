package com.shimmerresearch.verisense.payloaddesign;

import java.util.HashMap;
import java.util.List;

import com.shimmerresearch.driver.Configuration.CHANNEL_UNITS;
import com.shimmerresearch.sensors.AbstractSensor.SENSORS;
import com.shimmerresearch.verisense.UtilVerisenseDriver;
import com.shimmerresearch.verisense.VerisenseDevice;
import com.shimmerresearch.verisense.payloaddesign.DataBlockDetails.DATABLOCK_SENSOR_ID;

public class UtilCsvSplitting {

    protected static HashMap<SENSORS, double[]> SAMPLING_RATE_LIMITS_PER_SENSOR = new HashMap<SENSORS, double[]>();

    public static boolean isTsDifferenceOutsideOfLimits(double expectedPayloadTsDiffLimits[], double unixTimeInMs_1, double unixTimeInMs_2) {
        double differenceInMillisec = Math.abs(unixTimeInMs_1 - unixTimeInMs_2);
        if (differenceInMillisec < expectedPayloadTsDiffLimits[0] || differenceInMillisec > expectedPayloadTsDiffLimits[1]) {
            return true;
        }
        return false;
    }

    public static String isSamplingRateOutsideOfLimits(double[] samplingRateLimits, DataBlockDetails previousBlockDetails, DataBlockDetails nextBlockDetails, SENSORS sensorClassKey) {
        double calculatedSamplingRate = UtilVerisenseDriver.calcSamplingRate(previousBlockDetails.getEndTimeRwcMs(), nextBlockDetails.getEndTimeRwcMs(), nextBlockDetails.getSampleCount());
        if (Double.isNaN(calculatedSamplingRate)) {
            return ("WARNING!!! Unable to calculate sampling rate");
        }

        if (isSamplingRateOutsideOfLimits(samplingRateLimits, calculatedSamplingRate)) {

            double timeGapS = Math.abs((nextBlockDetails.getStartTimeRwcMs() - previousBlockDetails.getEndTimeRwcMs()) / 1000);

            String timeGapLocation = (previousBlockDetails.getPayloadIndex() == nextBlockDetails.getPayloadIndex() ? "datablocks" : "payloads");
            return ("WARNING!!! Unexpected sampling rate or time-gap detected for sensor " + sensorClassKey + " in between " + timeGapLocation + ": "
                    + "\n  |_1) " + previousBlockDetails.generateDebugStr()
                    + "\n  |_2) " + nextBlockDetails.generateDebugStr()

                    + "\n    |_Time between datablocks=" + UtilVerisenseDriver.convertSecondsToHHmmssSSS(timeGapS) + " (HH:mm:ss.SSS)"
                    + "\n    |_Detected=" + freqToStr(calculatedSamplingRate) //+ " (" + timeToStr(1/calculatedSamplingRate) + ")"
                    + ", Limits: Min=" + freqToStr(samplingRateLimits[0]) + " (" + timeToStr(1 / samplingRateLimits[0]) + ")"
                    + ", Max=" + freqToStr(samplingRateLimits[1]) + " (" + timeToStr(1 / samplingRateLimits[1]) + ")"
            );
        }
        return "";
    }

    public static boolean isSamplingRateOutsideOfLimits(double[] samplingRateLimits, double samplingRate) {
        if (samplingRate < samplingRateLimits[0] || samplingRate > samplingRateLimits[1]) {
            return true;
        }
        return false;
    }

    public static void populateExpectedPayloadTsDiffLimitMapIfNeeded(VerisenseDevice verisenseDevice, HashMap<DATABLOCK_SENSOR_ID, List<SENSORS>> mapOfSensorIdsPerDataBlock) {
        for (List<SENSORS> listOfSensorClassKeys : mapOfSensorIdsPerDataBlock.values()) {
            for (SENSORS sensorClassKey : listOfSensorClassKeys) {
                if (!UtilCsvSplitting.SAMPLING_RATE_LIMITS_PER_SENSOR.containsKey(sensorClassKey)) {
                    double configuredSamplingRate = verisenseDevice.getSamplingRateForSensor(sensorClassKey);
                    double[] samplingRateLimits = calculateSamplingRateLimits(configuredSamplingRate);
                    UtilCsvSplitting.SAMPLING_RATE_LIMITS_PER_SENSOR.put(sensorClassKey, samplingRateLimits);
                }
            }
        }
    }

    public static double[] calculateSamplingRateLimits(double configuredSamplingRate) {
        return new double[]{configuredSamplingRate * FILE_GAP_TOLERANCE_MULTIPLIER.LOWER, configuredSamplingRate * FILE_GAP_TOLERANCE_MULTIPLIER.UPPER};
    }

    public static void clearMapOfSamplingRateLimitsPerSensor() {
        SAMPLING_RATE_LIMITS_PER_SENSOR.clear();
    }

    public static String isDataBlockContinuous(SENSORS sensorClassKey, DataSegmentDetails dataSegmentDetailsPrevious, DataBlockDetails nextDataBlockDetails) {
        DataBlockDetails previousDataBlockDetails = dataSegmentDetailsPrevious.getListOfDataBlocks().get(dataSegmentDetailsPrevious.getDataBlockCount() - 1);

        double[] samplingRateLimits = UtilCsvSplitting.SAMPLING_RATE_LIMITS_PER_SENSOR.get(sensorClassKey);
        if (samplingRateLimits == null) {
            return ("WARNING!!! Sampling Rate Limits not set for sensor = " + sensorClassKey);
        }
        return UtilCsvSplitting.isSamplingRateOutsideOfLimits(samplingRateLimits, previousDataBlockDetails, nextDataBlockDetails, sensorClassKey);
    }

    private static String freqToStr(double freq) {
        return UtilVerisenseDriver.formatDoubleToNdecimalPlaces(freq, 2) + " " + CHANNEL_UNITS.FREQUENCY;
    }

    private static String timeToStr(double ts) {
        return UtilVerisenseDriver.formatDoubleToNdecimalPlaces(ts, 3) + " " + CHANNEL_UNITS.SECONDS;
    }

    public class FILE_GAP_TOLERANCE_MULTIPLIER {
        public static final double UPPER = 1.1;
        public static final double LOWER = 0.9;
    }

}
