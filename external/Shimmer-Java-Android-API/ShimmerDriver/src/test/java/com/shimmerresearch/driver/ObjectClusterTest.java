package com.shimmerresearch.driver;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.shimmerresearch.bluetooth.ShimmerBluetooth.BT_STATE;
import com.shimmerresearch.grpc.ShimmerGRPC.ObjectCluster2;
import com.shimmerresearch.grpc.ShimmerGRPC.ObjectCluster2.Builder;
import com.shimmerresearch.grpc.ShimmerGRPC.ObjectCluster2.FormatCluster2;
import com.shimmerresearch.grpc.ShimmerGRPC.ObjectCluster2.FormatCluster2.DataCluster2;
import com.shimmerresearch.sensors.SensorShimmerClock;
import com.shimmerresearch.driverUtilities.ChannelDetails;
import com.shimmerresearch.driverUtilities.ChannelDetails.CHANNEL_TYPE;

final public class ObjectClusterTest implements Cloneable, Serializable {

    private static final long serialVersionUID = -7601464501144773539L;

    public static List<OBJECTCLUSTER_TYPE> mListOfOCTypesEnabled = Arrays.asList(
            OBJECTCLUSTER_TYPE.ARRAYS,
            OBJECTCLUSTER_TYPE.FORMAT_CLUSTER,
            OBJECTCLUSTER_TYPE.PROTOBUF);
    public ArrayList<SensorData> mSensorDataList = new ArrayList<SensorData>();
    public HashMap<String, HashMap<String, FormatCluster>> mHashMap = new HashMap<>();
    public HashMap<String, FormatCluster[]> mHashMapArray = new HashMap<>();
    public SensorData[] mSensorDataArray = new SensorData[50];
    public int mSensorDataArrayIndex = 0;

    public int dataStructureSelector = 1;    //1 = ArrayList, 2 = HashMap, 3 = HashMapArray, 4 = Arrays, 5 = Multimap
    public Multimap<String, FormatCluster> mPropertyCluster = HashMultimap.create();
    public byte[] mRawData;
    public double[] mUncalData = new double[50];
    public double[] mCalData = new double[50];
        @Deprecated
    public String[] mSensorNames;
    public String[] mUnitCal = new String[50];
    public String[] mUnitUncal = new String[50];
    public String[] mSensorNamesCal = new String[50];    //JOS: would 2D arrays be better here?
    public String[] mSensorNamesUncal = new String[50];
    public String[] mSensorNamesCalResize;
    public String[] mUnitCalResize;
    public double[] mCalDataResize;
    public double[] mCalDataNew = new double[50];
    public double[] mUncalDataNew = new double[50];
    public byte[] mSystemTimeStamp = new byte[8];
    public boolean mIsValidObjectCluster = true;
    public boolean useList = false;

    public int mPacketIdValue = 0;
    public BT_STATE mState;
    public int calArrayIndex = 0;
    public int calArrayIndexResize = 0;
    public int uncalArrayIndex = 0;
    SensorDataArray sensorDataArray = new SensorDataArray(50);
        private List<String> listOfChannelNames = new ArrayList<String>();
    private String mMyName;
    private String mBluetoothAddress;
        private Builder mObjectClusterBuilder;
    private int indexKeeper = 0;
    private double mTimeStampMilliSecs;

    public ObjectClusterTest() {
    }

    public ObjectClusterTest(String myName) {
        mMyName = myName;
    }

    public ObjectClusterTest(String myName, String myBlueAdd) {
        this(myName);
        mBluetoothAddress = myBlueAdd;
    }

    public ObjectClusterTest(String myName, String myBlueAdd, BT_STATE state) {
        this(myName, myBlueAdd);
        mState = state;
    }

    public ObjectClusterTest(ObjectCluster2 ojc2) {
        ojc2.getDataMap().get("");
        for (String channelName : ojc2.getDataMap().keySet()) {
            FormatCluster2 fc = ojc2.getDataMap().get(channelName);
            for (String formatName : fc.getFormatMap().keySet()) {
                DataCluster2 data = fc.getFormatMap().get(formatName);
                addDataToMap(channelName, formatName, data.getUnit(), data.getData(), data.getDataArrayList());
            }
        }
        mBluetoothAddress = ojc2.getBluetoothAddress();
        mMyName = ojc2.getName();
    }

        public static FormatCluster returnFormatCluster(Collection<FormatCluster> collectionFormatCluster, String format) {
        FormatCluster returnFormatCluster = null;

        Iterator<FormatCluster> iFormatCluster = collectionFormatCluster.iterator();
        while (iFormatCluster.hasNext()) {
            FormatCluster formatCluster = iFormatCluster.next();
            if (formatCluster.mFormat.equals(format)) {
                returnFormatCluster = formatCluster;
            }
        }
        return returnFormatCluster;
    }

    private static List<String[]> getListofEnabledSensorSignalsandFormats(String myName, String[] sensorNames, String[] sensorFormats, String[] sensorUnits, String[] sensorIsUsingDefaultCal) {
        List<String[]> listofSignals = new ArrayList<String[]>();
        for (int i = 0; i < sensorNames.length; i++) {
            String[] channel = new String[]{myName, sensorNames[i], sensorFormats[i], sensorUnits[i], sensorIsUsingDefaultCal[i]};
            listofSignals.add(channel);
        }

        return listofSignals;
    }

        public static List<OBJECTCLUSTER_TYPE> getListOfOCTypesEnabled() {
        return mListOfOCTypesEnabled;
    }

        public static void setListOfOCTypesEnabled(List<OBJECTCLUSTER_TYPE> listOfOCTypesEnabled) {
        ObjectClusterTest.mListOfOCTypesEnabled = listOfOCTypesEnabled;
    }

    public static ObjectCluster[] generateRandomObjectClusterArray(String deviceName, String signalName, int numSamples, int minValue, int maxValue) {
        Random rand = new Random();

        double[] dataArray = new double[numSamples];
        for (int i = 0; i < numSamples; i++) {
            dataArray[i] = rand.nextInt(maxValue);
        }


        double timestamp = 0.00001;
        ObjectCluster[] ojcArray = new ObjectCluster[numSamples];
        for (int i = 0; i < numSamples; i++) {
            ObjectCluster ojc = new ObjectCluster(deviceName);
            ojc.createArrayData(1);
            ojc.addData(signalName, CHANNEL_TYPE.CAL, "", dataArray[i]);
            ojc.addCalData(SensorShimmerClock.channelSystemTimestampPlot, timestamp);
            timestamp += 1;
            ojcArray[i] = ojc;
        }

        return ojcArray;
    }


    public String getShimmerName() {
        return mMyName;
    }

    public void setShimmerName(String name) {
        mMyName = name;
    }

    public String getMacAddress() {
        return mBluetoothAddress;
    }

    public void setMacAddress(String macAddress) {
        mBluetoothAddress = macAddress;
    }

    public double getFormatClusterValueDefaultFormat(ChannelDetails channelDetails) {
        return getFormatClusterValue(channelDetails.mObjectClusterName, channelDetails.mListOfChannelTypes.get(0).toString());
    }

    public double getFormatClusterValue(ChannelDetails channelDetails, CHANNEL_TYPE channelType) {
        return getFormatClusterValue(channelDetails.mObjectClusterName, channelType.toString());
    }

    public double getFormatClusterValue(String channelName, String format) {
        FormatCluster formatCluster = getLastFormatCluster(channelName, format);
        if (formatCluster != null) {
            return formatCluster.mData;
        }
        return Double.NaN;
    }

    public FormatCluster getLastFormatCluster(String channelName, String format) {
        Collection<FormatCluster> formatClusterCollection = getCollectionOfFormatClusters(channelName);
        if (formatClusterCollection != null) {
            FormatCluster formatCluster = ObjectCluster.returnFormatCluster(formatClusterCollection, format);
            if (formatCluster != null) {
                return formatCluster;
            }
        }
        return null;
    }

        public void removePropertyFormat(String propertyname, String formatname) {
        Collection<FormatCluster> colFormats = mPropertyCluster.get(propertyname);
        FormatCluster formatCluster = ((FormatCluster) ObjectCluster.returnFormatCluster(colFormats, formatname)); // retrieve format;
        mPropertyCluster.remove(propertyname, formatCluster);
    }

        public byte[] serialize() {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(this);
            return baos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<String[]> generateArrayOfChannelsSorted() {
        List<String[]> listofSignals = new ArrayList<String[]>();
        int size = 0;
        for (String fckey : getChannelNamesFromKeySet()) {
            size++;
        }

        String[] properties = new String[size];
        int y = 0;
        for (String fckey : getChannelNamesFromKeySet()) {
            properties[y] = fckey;
            y++;
        }

        Arrays.sort(properties);

        int index = 0;
        String property;
        for (int k = 0; k < size; k++) {
            property = properties[k];
            Collection<FormatCluster> ofFormatstemp = getCollectionOfFormatClusters(property);
            int tempSize = 0;
            for (FormatCluster fctemp : ofFormatstemp) {
                tempSize++;
            }

            String[] formats = new String[tempSize];
            String[] units = new String[tempSize];
            int p = 0;
            for (FormatCluster fctemp : ofFormatstemp) {
                formats[p] = fctemp.mFormat;
                p++;

            }

            Arrays.sort(formats);
            for (int u = 0; u < formats.length; u++) {
                for (FormatCluster fctemp : ofFormatstemp) {
                    if (fctemp.mFormat.equals(formats[u])) {
                        units[u] = fctemp.mUnits;
                    }
                }
            }

            for (int u = 0; u < formats.length; u++) {
                String[] channel = {mMyName, property, formats[u], units[u]};
                listofSignals.add(channel);
                System.out.println("Index" + index);

            }


        }
        return listofSignals;
    }

    public List<String[]> generateArrayOfChannels() {
        Multimap<String, FormatCluster> m = mPropertyCluster;

        int size = m.size();
        System.out.print(size);
        mSensorNames = new String[size];
        String[] sensorFormats = new String[size];
        String[] sensorUnits = new String[size];
        String[] sensorIsUsingDefaultCal = new String[size];
        int i = 0;
        int p = 0;
        for (String key : m.keys()) {

            if (compareStringArray(mSensorNames, key) == true) {
                for (FormatCluster formatCluster : m.get(key)) {
                    sensorFormats[p] = formatCluster.mFormat;
                    sensorUnits[p] = formatCluster.mUnits;
                    sensorIsUsingDefaultCal[p] = (formatCluster.mIsUsingDefaultCalibration ? "*" : "");
                    p++;
                }

            }

            mSensorNames[i] = key;
            i++;
        }
        return getListofEnabledSensorSignalsandFormats(mMyName, mSensorNames, sensorFormats, sensorUnits, sensorIsUsingDefaultCal);
    }

    private boolean compareStringArray(String[] stringArray, String string) {
        boolean uniqueString = true;
        int size = stringArray.length;
        for (int i = 0; i < size; i++) {
            if (stringArray[i] == string) {
                uniqueString = false;
            }

        }
        return uniqueString;
    }

    public void createArrayData(int length) {
        if (mListOfOCTypesEnabled.contains(OBJECTCLUSTER_TYPE.ARRAYS)) {
            mUncalData = new double[length];
            mCalData = new double[length];
            mSensorNames = new String[length];
            mUnitCal = new String[length];
            mUnitUncal = new String[length];
        }
    }

    public void addData(ChannelDetails channelDetails, double uncalData, double calData) {
        addData(channelDetails, uncalData, calData, false);
    }

    public void addData(ChannelDetails channelDetails, double uncalData, double calData, boolean usingDefaultParameters) {
        addData(channelDetails, uncalData, calData, indexKeeper, usingDefaultParameters);
    }

    public void addData(ChannelDetails channelDetails, double uncalData, double calData, int index, boolean usingDefaultParameters) {
        if (channelDetails.mListOfChannelTypes.contains(CHANNEL_TYPE.UNCAL)) {
            addUncalData(channelDetails, uncalData, index);
        }
        if (channelDetails.mListOfChannelTypes.contains(CHANNEL_TYPE.CAL)) {
            addCalData(channelDetails, calData, index, usingDefaultParameters);
        }
    }

    public void addCalData(ChannelDetails channelDetails, double calData) {
        addCalData(channelDetails, calData, indexKeeper);
    }

    public void addCalData(ChannelDetails channelDetails, double calData, int index) {
        addCalData(channelDetails, calData, index, false);
    }

    public void addCalData(ChannelDetails channelDetails, double calData, int index, boolean usingDefaultParameters) {
        addData(channelDetails.mObjectClusterName, CHANNEL_TYPE.CAL, channelDetails.mDefaultCalUnits, calData, index, usingDefaultParameters);
    }

    public void addUncalData(ChannelDetails channelDetails, double uncalData) {
        addUncalData(channelDetails, uncalData, indexKeeper);
    }

    public void addUncalData(ChannelDetails channelDetails, double uncalData, int index) {
        addData(channelDetails.mObjectClusterName, CHANNEL_TYPE.UNCAL, channelDetails.mDefaultUncalUnit, uncalData, index);
    }

    public void addData(String objectClusterName, CHANNEL_TYPE channelType, String units, double data) {
        addData(objectClusterName, channelType, units, data, indexKeeper);
    }

    public void addData(String objectClusterName, CHANNEL_TYPE channelType, String units, double data, int index) {
        addData(objectClusterName, channelType, units, data, index, false);
    }

    public void addData(String objectClusterName, CHANNEL_TYPE channelType, String units, double data, int index, boolean isUsingDefaultCalib) {
        if (mListOfOCTypesEnabled.contains(OBJECTCLUSTER_TYPE.ARRAYS)) {
            if (channelType == CHANNEL_TYPE.CAL) {
                mCalData[index] = data;
                mUnitCal[index] = units;
            } else if (channelType == CHANNEL_TYPE.UNCAL) {
                mUncalData[index] = data;
                mUnitUncal[index] = units;
            }
            else if (channelType == CHANNEL_TYPE.DERIVED) {
                mCalData[index] = data;
                mUnitCal[index] = units;
                mUncalData[index] = data;
                mUnitUncal[index] = units;
            }
            mSensorNames[index] = objectClusterName;

        }

        if (mListOfOCTypesEnabled.contains(OBJECTCLUSTER_TYPE.FORMAT_CLUSTER)) {
            addDataToMap(objectClusterName, channelType.toString(), units, data, isUsingDefaultCalib);
        }

        if (mListOfOCTypesEnabled.contains(OBJECTCLUSTER_TYPE.PROTOBUF)) {
        }
    }

    public void incrementIndexKeeper() {
        if (mListOfOCTypesEnabled.contains(OBJECTCLUSTER_TYPE.ARRAYS)) {
            if (indexKeeper < mCalData.length) {
                indexKeeper++;
            }
        }
    }

    public int getIndexKeeper() {
        return indexKeeper;
    }

    public void setIndexKeeper(int indexKeeper) {
        this.indexKeeper = indexKeeper;
    }

    public void addCalDataToMap(ChannelDetails channelDetails, double data) {
        addDataToMap(channelDetails.mObjectClusterName, CHANNEL_TYPE.CAL.toString(), channelDetails.mDefaultCalUnits, data);
    }

    public void addUncalDataToMap(ChannelDetails channelDetails, double data) {
        addDataToMap(channelDetails.mObjectClusterName, CHANNEL_TYPE.UNCAL.toString(), channelDetails.mDefaultCalUnits, data);
    }

    public void addDataToMap(String channelName, String channelType, String units, double data) {


        addDataToMap(channelName, channelType, units, data, false);
    }

    public void addDataToMap(String channelName, String channelType, String units, double data, boolean isUsingDefaultCalib) {





        mPropertyCluster.put(channelName, new FormatCluster(channelType, units, data, isUsingDefaultCalib));
    }

    public void addDataToArrayList(String channelName, String channelType, String units, double data) {
        mSensorDataList.add(new SensorData(channelName, channelType, units, data, false));
    }

    public void addDataToNestedHashMap(String channelName, String channelType, String units, double data) {
        HashMap<String, FormatCluster> formatMap = new HashMap<>(3);
        formatMap.put(channelType, new FormatCluster(channelType, units, data, false));
        mHashMap.put(channelName, formatMap);
    }

    public void addDataToHashMapArray(String channelName, String channelType, String units, double data) {
        FormatCluster[] formatClusterArray = new FormatCluster[2];
        formatClusterArray[0] = new FormatCluster(channelType, units, data, false);
        mHashMapArray.put(channelName, formatClusterArray);
    }

    public void addDataToSensorDataArray(String channelName, String channelType, String units, double data) {
        mSensorDataArray[mSensorDataArrayIndex] = new SensorData(channelName, channelType, units, data, false);
        mSensorDataArrayIndex++;
    }

    public void addDataToArrays(String channelName, String channelType, String units, double data) {
        if (channelType.equals(CHANNEL_TYPE.CAL.toString())) {
            mSensorNamesCal[calArrayIndex] = channelName;
            mUnitCal[calArrayIndex] = units;
            mCalData[calArrayIndex] = data;
            calArrayIndex++;
        } else {
            mSensorNamesUncal[uncalArrayIndex] = channelName;
            mUnitUncal[uncalArrayIndex] = units;
            mUncalData[uncalArrayIndex] = data;
            uncalArrayIndex++;
        }
    }

    public void addDataToArraysWithResize(String channelName, String channelType, String units, double data) {
        if (channelType.equals(CHANNEL_TYPE.CAL.toString())) {
            mSensorNamesCalResize[calArrayIndexResize] = channelName;
            mUnitCalResize[calArrayIndexResize] = units;
            mCalDataResize[calArrayIndexResize] = data;
            calArrayIndexResize++;
        } else {
        }
    }

    public void addDataToNewArrays(String channelName, String channelType, String units, double data) {
        if (channelType.equals(CHANNEL_TYPE.CAL.toString())) {
            sensorDataArray.mSensorNames[sensorDataArray.mCalArraysIndex] = channelName;    //TODO JOS: This will need to be updated according to updated SensorDataArray for production ObjectCluster class
            sensorDataArray.mCalUnits[sensorDataArray.mCalArraysIndex] = units;
            sensorDataArray.mCalData[sensorDataArray.mCalArraysIndex] = data;
            sensorDataArray.mIsUsingDefaultCalibrationParams[sensorDataArray.mCalArraysIndex] = false;
            sensorDataArray.mCalArraysIndex++;
        } else if (channelType.equals(CHANNEL_TYPE.UNCAL.toString())) {
            sensorDataArray.mSensorNames[sensorDataArray.mUncalArraysIndex] = channelName;
            sensorDataArray.mUncalUnits[sensorDataArray.mUncalArraysIndex] = units;
            sensorDataArray.mUncalData[sensorDataArray.mUncalArraysIndex] = data;
            sensorDataArray.mUncalArraysIndex++;
        }
    }

    public void updateArraySensorDataIndex() {
        for (int i = 0; i < mSensorDataArray.length; i++) {
            if (mSensorDataArray[i] == null) {
                mSensorDataArrayIndex = i;
                return;
            }
        }
    }

    @Deprecated
    public void addDataToMap(String channelName, String channelType, String units, List<Double> data) {
        mPropertyCluster.put(channelName, new FormatCluster(channelType, units, data));
        addChannelNameToList(channelName);
    }

    @Deprecated
    public void addDataToMap(String channelName, String channelType, String units, double data, List<Double> dataArray) {
        mPropertyCluster.put(channelName, new FormatCluster(channelType, units, data, dataArray));
        addChannelNameToList(channelName);
    }

    private void addChannelNameToList(String channelName) {
        if (!listOfChannelNames.contains(channelName)) {
            listOfChannelNames.add(channelName);
        }
    }

    @Deprecated
    public void removeAll(String channelName) {
        mPropertyCluster.removeAll(channelName);
        listOfChannelNames = new ArrayList<String>();
    }

    public Collection<FormatCluster> getCollectionOfFormatClusters(String channelName) {
        return mPropertyCluster.get(channelName);
    }

    public Set<String> getChannelNamesFromKeySet() {
        return mPropertyCluster.keySet();
    }

    public List<String> getChannelNamesByInsertionOrder() {
        return listOfChannelNames;
    }

    public Multimap<String, FormatCluster> getPropertyCluster() {
        return mPropertyCluster;
    }

    public ObjectCluster2 buildProtoBufMsg() {
        mObjectClusterBuilder = ObjectCluster2.newBuilder();
        for (String channelName : mPropertyCluster.keys()) {
            Collection<FormatCluster> fcs = mPropertyCluster.get(channelName);
            FormatCluster2.Builder fcb = FormatCluster2.newBuilder();
            for (FormatCluster fc : fcs) {
                DataCluster2.Builder dcb = DataCluster2.newBuilder();
                if (fc.mData != Double.NaN) {
                    dcb.setData(fc.mData);
                }
                if (fc.mDataObject != null && fc.mDataObject.size() > 0) {
                    dcb.addAllDataArray(fc.mDataObject);
                }
                dcb.setUnit(fc.mUnits);
                fcb.getMutableFormatMap().put(fc.mFormat, dcb.build());
            }
            mObjectClusterBuilder.getMutableDataMap().put(channelName, fcb.build());
        }
        if (mBluetoothAddress != null)
            mObjectClusterBuilder.setBluetoothAddress(mBluetoothAddress);
        if (mMyName != null)
            mObjectClusterBuilder.setName(mMyName);
        mObjectClusterBuilder.setCalibratedTimeStamp(mTimeStampMilliSecs);
        ByteBuffer bb = ByteBuffer.allocate(8);
        bb.put(mSystemTimeStamp);
        bb.flip();
        long systemTimeStamp = bb.getLong();
        mObjectClusterBuilder.setSystemTime(systemTimeStamp);
        return mObjectClusterBuilder.build();
    }

    public double getTimestampMilliSecs() {
        return mTimeStampMilliSecs;
    }

    public void setTimeStampMilliSecs(double timeStampMilliSecs) {
        this.mTimeStampMilliSecs = timeStampMilliSecs;
    }

    public void consolePrintChannelsAndDataSingleLine() {
        System.out.println("ShimmerName:" + mMyName);
        System.out.println("Channels in ObjectCluster:");
        String channelsCal = "Cal:\t";
        String channelsUncal = "Uncal:\t";
        for (String channel : getChannelNamesByInsertionOrder()) {
            channelsCal += channel + "=" + getFormatClusterValue(channel, CHANNEL_TYPE.CAL.toString()) + "\t";
            channelsUncal += channel + "=" + getFormatClusterValue(channel, CHANNEL_TYPE.UNCAL.toString()) + "\t";
        }
        System.out.println(channelsCal);
        System.out.println(channelsUncal);
        System.out.println("");
    }

    public void consolePrintChannelsAndDataGrouped() {
        System.out.println("Channels in ObjectCluster:");
        for (String channel : getChannelNamesByInsertionOrder()) {
            System.out.println("\t" + channel + ":\t(" + getFormatClusterValue(channel, CHANNEL_TYPE.UNCAL.toString()) + "," + getFormatClusterValue(channel, CHANNEL_TYPE.CAL.toString()) + ")");
        }
        System.out.println("");
    }

    public ObjectCluster deepClone() {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(this);

            ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
            ObjectInputStream ois = new ObjectInputStream(bais);
            return (ObjectCluster) ois.readObject();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public enum OBJECTCLUSTER_TYPE {
        ARRAYS,
        FORMAT_CLUSTER,
        PROTOBUF
    }

}
