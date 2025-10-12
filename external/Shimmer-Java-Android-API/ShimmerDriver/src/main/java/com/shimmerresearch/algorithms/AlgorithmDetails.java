package com.shimmerresearch.algorithms;

import java.io.Serializable;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.ArrayList;

import com.shimmerresearch.driver.ShimmerDevice;
import com.shimmerresearch.driverUtilities.ChannelDetails;
import com.shimmerresearch.driverUtilities.ChannelDetails.CHANNEL_TYPE;
import com.shimmerresearch.driverUtilities.SensorDetails;

public class AlgorithmDetails implements Serializable {

    private static final long serialVersionUID = -8249918413235100868L;

        public String mAlgorithmName = "";
    public String mGuiFriendlyName = "";
    public String mDatabaseChannelHandle = "";
    public List<ChannelDetails> mListOfChannelDetails = new ArrayList<ChannelDetails>();

    public String mUnits = "";
    public CHANNEL_TYPE mChannelType = CHANNEL_TYPE.CAL;
    public long mDerivedSensorBitmapID = 0;

    public List<Integer> mListOfRequiredSensors = new ArrayList<Integer>();
    public List<String> mListOfAssociatedSensorChannels = new ArrayList<String>();
    @Deprecated
    public List<Integer> mListOfCompatableExpBoards = new ArrayList<Integer>();

    public SENSOR_CHECK_METHOD mSensorCheckMethod = SENSOR_CHECK_METHOD.ALL;

        public AlgorithmDetails(List<Integer> listOfRequiredSensors, String units) {
        if (listOfRequiredSensors != null) {
            mListOfRequiredSensors = listOfRequiredSensors;
        }
        mUnits = units;
    }

        public AlgorithmDetails(
            List<Integer> listOfRequiredSensors,
            String units,
            SENSOR_CHECK_METHOD sensorCheckMethod) {
        this(listOfRequiredSensors, units);
        mSensorCheckMethod = sensorCheckMethod;
    }


        public AlgorithmDetails(
            String objectClusterName,
            String guiFriendlyName,
            List<String> listOfAssociatedSensorChannels,
            long derivedSensorBitmapId,
            List<Integer> listOfRequiredSensors,
            String units) {
        this(listOfRequiredSensors, units);
        mAlgorithmName = objectClusterName;
        mGuiFriendlyName = guiFriendlyName;
        mListOfAssociatedSensorChannels = listOfAssociatedSensorChannels;
        mDerivedSensorBitmapID = derivedSensorBitmapId;
        mListOfChannelDetails.add(generateChannelDetails());
    }

    public AlgorithmDetails(
            String objectClusterName,
            String guiFriendlyName,
            String databaseChannelHandle,
            List<String> listOfAssociatedSensorChannels,
            long derivedSensorBitmapId,
            List<Integer> listOfRequiredSensors,
            String units) {
        this(objectClusterName, guiFriendlyName, listOfAssociatedSensorChannels, derivedSensorBitmapId, listOfRequiredSensors, units);
        mDatabaseChannelHandle = databaseChannelHandle;

        mListOfChannelDetails.clear();
        mListOfChannelDetails.add(generateChannelDetails());
    }

        public AlgorithmDetails(
            String objectClusterName,
            String guiFriendlyName,
            List<String> listOfAssociatedSensorChannels,
            long derivedSensorBitmapId,
            List<Integer> listOfRequiredSensors,
            String units,
            List<ChannelDetails> listOfAlgortihmChannels) {
        this(objectClusterName, guiFriendlyName, listOfAssociatedSensorChannels, derivedSensorBitmapId, listOfRequiredSensors, units);

        mListOfChannelDetails = listOfAlgortihmChannels;
    }


    public AlgorithmDetails(
            String objectClusterName,
            String guiFriendlyName,
            List<String> listOfAssociatedSensorChannels,
            long derivedSensorBitmapId,
            List<Integer> listOfRequiredSensors,
            String units,
            SENSOR_CHECK_METHOD sensorCheckMethod,
            List<ChannelDetails> listOfAlgortihmChannels) {
        this(objectClusterName, guiFriendlyName, listOfAssociatedSensorChannels, derivedSensorBitmapId, listOfRequiredSensors, units);
        mSensorCheckMethod = sensorCheckMethod;
        mListOfChannelDetails = listOfAlgortihmChannels;
    }

        public AlgorithmDetails(
            String objectClusterName,
            String guiFriendlyName,
            List<String> listOfAssociatedSensorChannels,
            long derivedSensorBitmapId,
            List<Integer> listOfCompatibleExpBoards,
            List<Integer> listOfRequiredSensors,
            String units) {
        this(objectClusterName, guiFriendlyName, listOfAssociatedSensorChannels, derivedSensorBitmapId, listOfRequiredSensors, units);
        mListOfCompatableExpBoards = listOfCompatibleExpBoards;
    }

    public static LinkedHashMap<String, AlgorithmDetails> loadAlgorithmsWhereSensorsAreAvailable(ShimmerDevice shimmerDevice, Map<String, AlgorithmDetails> algorithMap) {
        LinkedHashMap<String, AlgorithmDetails> mapOfSupportedAlgorithms = new LinkedHashMap<String, AlgorithmDetails>();

        LinkedHashMap<Integer, SensorDetails> sensorMap = shimmerDevice.getSensorMap();

        algorithmLoop:
        for (Entry<String, AlgorithmDetails> algorithmDetailsEntry : algorithMap.entrySet()) {
            AlgorithmDetails algorithmDetails = algorithmDetailsEntry.getValue();

            if (algorithmDetails.mSensorCheckMethod == SENSOR_CHECK_METHOD.ANY) {
                for (Integer sensorMapKey : algorithmDetails.mListOfRequiredSensors) {
                    if (sensorMap.containsKey(sensorMapKey)) {
                        mapOfSupportedAlgorithms.put(algorithmDetailsEntry.getKey(), algorithmDetails);
                        continue algorithmLoop;
                    }
                }
            } else if (algorithmDetails.mSensorCheckMethod == SENSOR_CHECK_METHOD.ALL) {
                for (Integer sensorMapKey : algorithmDetails.mListOfRequiredSensors) {
                    if (!sensorMap.containsKey(sensorMapKey)) {
                        continue algorithmLoop;
                    }
                }
                mapOfSupportedAlgorithms.put(algorithmDetailsEntry.getKey(), algorithmDetails);
            }
        }

        return mapOfSupportedAlgorithms;
    }

    public String[] getSignalStringArray() {
        String[] signalStringArray = new String[4];
        signalStringArray[0] = "TEMP_SHIMMER_NAME";
        signalStringArray[1] = mAlgorithmName;
        signalStringArray[2] = mChannelType.toString();
        signalStringArray[3] = mUnits;
        return signalStringArray;
    }

    private ChannelDetails generateChannelDetails() {
        ChannelDetails cD = new ChannelDetails(
                mAlgorithmName,
                mGuiFriendlyName,
                mDatabaseChannelHandle,
                mUnits,
                Arrays.asList(mChannelType));
        return cD;
    }

    public List<ChannelDetails> getChannelDetails() {
        return mListOfChannelDetails;
    }

    public enum SENSOR_CHECK_METHOD {
        ALL,
        ANY
    }
}

