package com.shimmerresearch.driver;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.ByteBuffer;
import java.text.Normalizer.Form;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.concurrent.ThreadLocalRandom;

import javax.annotation.Signed;
import javax.print.DocFlavor.STRING;

import org.apache.commons.math.stat.descriptive.moment.FirstMoment;

import com.shimmerresearch.driver.Configuration.CHANNEL_UNITS;
import com.shimmerresearch.driver.Configuration.Shimmer3;
import com.shimmerresearch.driver.ObjectClusterTest.OBJECTCLUSTER_TYPE;
import com.shimmerresearch.driverUtilities.ChannelDetails;
import com.shimmerresearch.driverUtilities.ChannelDetails.CHANNEL_TYPE;
import com.shimmerresearch.grpc.ShimmerGRPC.ObjectCluster2;
import com.shimmerresearch.grpc.ShimmerGRPC.ObjectCluster2.Builder;
import com.shimmerresearch.grpc.ShimmerGRPC.ObjectCluster2.FormatCluster2;
import com.shimmerresearch.grpc.ShimmerGRPC.ObjectCluster2.FormatCluster2.DataCluster2;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.shimmerresearch.bluetooth.ShimmerBluetooth.BT_STATE;
import com.shimmerresearch.driver.FormatCluster;
import com.shimmerresearch.sensors.SensorShimmerClock;

public abstract class TestObjectCluster {//

    public static final int NUM_ARRAY_LIST = 0;
    public static final int NUM_NESTED_HASHMAP = 1;
    public static final int NUM_HASHMAP_ARRAY = 2;


    public static final int NUM_SENSORDATA_ARRAY = 3;
    public static final int NUM_MULTIMAP = 4;
    public static final int NUM_ARRAYS = 5;
    public static final int NUM_ARRAYS_RESIZE = 6;
    public static final int NUM_NEW_ARRAYS = 7;
    public static final int NUM_OBJECTCLUSTERS = 30;
    public static final int NUM_DATA_STRUCTURES = 8;
    public static long[] allInsertionTimes = new long[8];
    public static long[] allRandomRetrievalTimes = new long[8];
    public static long[] allStructuredRetrievalTimes = new long[8];
    public static String[] DATA_STRUCTURE_NAMES = {"ArrayList & SensorData", "Nested HashMap", "HashMap & Array", "Arrays & SensorData", "MultiMap", "Arrays", "Arrays with Resizing", "New Arrays"};

    private static void storeIndexTest(ObjectClusterTest ObjectClusterTest) {
        int lastValue = 9998;
        int index = 0;
        long cumulativeListRetrievalTime = 0;
        long cumulativeMultimapRetrievalTime = 0;
        boolean firstRun = true;

        for (int a = 0; a < 20; a++) {

            long worstCaseListRetrievalTime = 0;
            long worstCaseMultimapRetrievalTime = 0;
            String retrievalKey = SensorShimmerClock.ObjectClusterSensorName.TIMESTAMP_OFFSET + Integer.toString(lastValue);

            long startListRetrievalTime = System.nanoTime();
            if (firstRun) {
                for (int i = 0; i < 9999; i++) {
                    SensorData sensorData = ObjectClusterTest.mSensorDataList.get(i);
                    if (sensorData.mSensorName.contains(retrievalKey)) {
                        double listData = sensorData.mSensorData;
                        index = i;
                        worstCaseListRetrievalTime = System.nanoTime();
                    }
                }
                firstRun = false;
            } else {
                SensorData sensorData = ObjectClusterTest.mSensorDataList.get(index);
                double listData = sensorData.mSensorData;
                worstCaseListRetrievalTime = System.nanoTime();
            }

            cumulativeListRetrievalTime = cumulativeListRetrievalTime + (worstCaseListRetrievalTime - startListRetrievalTime);

            long startMultimapRetrievalTime = System.nanoTime();
            Collection<FormatCluster> allFormats = ObjectClusterTest.getCollectionOfFormatClusters(retrievalKey);
            FormatCluster formatCluster = ((FormatCluster) ObjectClusterTest.returnFormatCluster(allFormats, CHANNEL_TYPE.UNCAL.toString()));
            double multimapData = formatCluster.mData;
            worstCaseMultimapRetrievalTime = System.nanoTime();

            cumulativeMultimapRetrievalTime = cumulativeMultimapRetrievalTime + (worstCaseMultimapRetrievalTime - startMultimapRetrievalTime);

            System.err.println("Time to retrieve ArrayList: " + (worstCaseListRetrievalTime - startListRetrievalTime) + " Time to retrieve Multimap: " + (worstCaseMultimapRetrievalTime - startMultimapRetrievalTime));
        }

        System.err.println("Averages over 20x, ArrayList Time: " + (cumulativeListRetrievalTime / 20) + " Multimap Time: " + (cumulativeMultimapRetrievalTime / 20));

    }

    public static void hashMapStoreTest() {
        HashMap hashMap1 = null;
        Multimap multiMap1 = null;

        int numberOfIterations = 100000;
        long cumulativeHashMapStorageTime = 0;
        long cumulativeMultimapStorageTime = 0;

        for (int a = 0; a < 20; a++) {
            HashMap<String, HashMap<CHANNEL_TYPE, FormatCluster>> hashMap = new HashMap<String, HashMap<CHANNEL_TYPE, FormatCluster>>();
            Multimap<String, FormatCluster> multiMap = HashMultimap.create();


            long startHashMapStorage = System.nanoTime();
            for (int i = 0; i < numberOfIterations; i++) {
                String sensorName = SensorShimmerClock.ObjectClusterSensorName.TIMESTAMP_OFFSET + Integer.toString(i);
                HashMap<CHANNEL_TYPE, FormatCluster> formatMap = new HashMap<CHANNEL_TYPE, FormatCluster>();
                formatMap.put(CHANNEL_TYPE.UNCAL, new FormatCluster(CHANNEL_TYPE.UNCAL.toString(), CHANNEL_UNITS.NO_UNITS, 1.0));
                hashMap.put(sensorName, formatMap);
            }
            long endHashMapStorage = System.nanoTime();

            long startMultiMapStorage = System.nanoTime();
            for (int i = 0; i < numberOfIterations; i++) {
                String sensorName = SensorShimmerClock.ObjectClusterSensorName.TIMESTAMP_OFFSET + Integer.toString(i);
                multiMap.put(sensorName, new FormatCluster(CHANNEL_TYPE.UNCAL.toString(), CHANNEL_UNITS.NO_UNITS, 2.0));
            }
            long endMultiMapStorage = System.nanoTime();

            cumulativeHashMapStorageTime = cumulativeHashMapStorageTime + (endHashMapStorage - startHashMapStorage);
            cumulativeMultimapStorageTime = cumulativeMultimapStorageTime + (endMultiMapStorage - startMultiMapStorage);

            System.err.println("Time to put HashMap: " + (endHashMapStorage - startHashMapStorage) + " Time to put Multimap: " + (endMultiMapStorage - startMultiMapStorage));

            if (a == 19) {
                hashMap1 = hashMap;
                multiMap1 = multiMap;
            }

        }


        System.err.println("Averages over 20x, HashMap put Time: " + (cumulativeHashMapStorageTime / 20) + " Multimap Time: " + (cumulativeMultimapStorageTime / 20));

        hashMapRetrieveTest(hashMap1, multiMap1);
    }

    public static void hashMapRetrieveTest(HashMap hashMap, Multimap multiMap) {

        System.out.print("-------------------------------------------------------------------------\n\n");
        String retrievalKey = SensorShimmerClock.ObjectClusterSensorName.TIMESTAMP_OFFSET + Integer.toString(99999);

        long cumulativeHashMapRetrievalTime = 0;
        long cumulativeMultimapRetrievalTime = 0;

        for (int a = 0; a < 20; a++) {
            HashMap hashMap1 = hashMap;
            Multimap multiMap1 = multiMap;

            long hashMapStartRetrieval = System.nanoTime();
            HashMap<CHANNEL_TYPE, FormatCluster> formatMap = (HashMap<CHANNEL_TYPE, FormatCluster>) hashMap1.get(retrievalKey);
            FormatCluster formatCluster = formatMap.get(CHANNEL_TYPE.UNCAL);
            double data = formatCluster.mData;
            long hashMapEndRetrieval = System.nanoTime();

            long multiMapStartRetrieval = System.nanoTime();
            Collection<FormatCluster> allFormats = multiMap.get(retrievalKey);
            FormatCluster multiMapFormatCluster = ((FormatCluster) ObjectClusterTest.returnFormatCluster(allFormats, CHANNEL_TYPE.UNCAL.toString()));
            double multimapData = formatCluster.mData;
            long multiMapEndRetrieval = System.nanoTime();

            cumulativeHashMapRetrievalTime = cumulativeHashMapRetrievalTime + (hashMapEndRetrieval - hashMapStartRetrieval);
            cumulativeMultimapRetrievalTime = cumulativeMultimapRetrievalTime + (multiMapEndRetrieval - multiMapStartRetrieval);

            System.err.println("HashMap retrieval time: " + (hashMapEndRetrieval - hashMapStartRetrieval) + " Multimap retrieval time: " + (multiMapEndRetrieval - multiMapStartRetrieval));

        }
        System.err.println("Averages over 20x, HashMap retrieval time: " + cumulativeHashMapRetrievalTime / 20 + " Multimap retrieval time: " + cumulativeMultimapRetrievalTime / 20);


    }

    public static void main(String[] args) throws InterruptedException {


        for (int i = 0; i < 1000; i++) {
            ObjectClusterTest[][] objcArray = testDataStructureOptionsInsertionUpdated(false);
            testDataStructureOptionsOrderedRetrievalUpdated(objcArray, false);
            testDataStructureOptionsRandomRetrievalUpdated(objcArray, false);
        }

        System.err.println("---------------------------------------------------------------------------------\n");
        System.err.println("-----------------------AVERAGES OVER 1,000 RUNS PER TEST-----------------------\n");
        System.err.println("-----------------------30 OBJECTCLUSTERS PER TEST WITH 30 CHANNELS OF DATA EACH\n");
        System.err.println("----------------------- TIMINGS: -----------------------\n");

        Thread.sleep(50);


        for (int i = 0; i < allInsertionTimes.length; i++) {
            allInsertionTimes[i] = allInsertionTimes[i] / 1000;
            allStructuredRetrievalTimes[i] = allStructuredRetrievalTimes[i] / 1000;
            allRandomRetrievalTimes[i] = allRandomRetrievalTimes[i] / 1000;
        }


        for (int i = 0; i < allInsertionTimes.length; i++) {
            System.out.println("Data Structure " + (i + 1) + " average insertion time: " + allInsertionTimes[i] + "ns" + "\t\t (" + DATA_STRUCTURE_NAMES[i] + ")");
        }
        Thread.sleep(50);
        System.err.println("----------------------------------------------\n");

        for (int i = 0; i < allStructuredRetrievalTimes.length; i++) {
            System.out.println("Data Structure " + (i + 1) + " average structured retrieval time: " + allStructuredRetrievalTimes[i] + "ns" + "\t\t (" + DATA_STRUCTURE_NAMES[i] + ")");
        }
        Thread.sleep(50);
        System.err.println("----------------------------------------------\n");

        for (int i = 0; i < allRandomRetrievalTimes.length; i++) {
            System.out.println("Data Structure " + (i + 1) + " average random retrieval time: " + allRandomRetrievalTimes[i] + "ns" + "\t\t (" + DATA_STRUCTURE_NAMES[i] + ")");
        }

        Thread.sleep(50);

        System.err.println("\n----------------------- COMPARISON VS MULTIMAP BASELINE: -----------------------\n");
        for (int i = 0; i < allInsertionTimes.length; i++) {
            if (i != 4) {
                double factor = 1 / ((allInsertionTimes[i] * 1.0f) / allInsertionTimes[4]);
                System.out.println("Data Structure " + (i + 1) + " insertion average improvement: " + factor + "x" + "\t\t (" + DATA_STRUCTURE_NAMES[i] + ")");
            } else {
                System.out.println("Data Structure " + (i + 1) + " insertion average improvement: " + "1.00000000000000x" + "\t\t (" + DATA_STRUCTURE_NAMES[i] + ")");
            }
        }
        System.err.println("----------------------------------------------\n");

        for (int i = 0; i < allStructuredRetrievalTimes.length; i++) {
            if (i != 4) {
                double factor = 1 / ((allStructuredRetrievalTimes[i] * 1.0f) / allStructuredRetrievalTimes[4]);
                System.out.println("Data Structure " + (i + 1) + " sequential retrieval average improvement: " + factor + "x" + "\t\t (" + DATA_STRUCTURE_NAMES[i] + ")");
            } else {
                System.out.println("Data Structure " + (i + 1) + " sequential retrieval average improvement: " + "1.00000000000000x" + "\t\t (" + DATA_STRUCTURE_NAMES[i] + ")");
            }
        }
        System.err.println("----------------------------------------------\n");

        for (int i = 0; i < allRandomRetrievalTimes.length; i++) {
            if (i != 4) {
                double factor = 1 / ((allRandomRetrievalTimes[i] * 1.0f) / allRandomRetrievalTimes[4]);
                System.out.println("Data Structure " + (i + 1) + " random retrieval average improvement: " + factor + "x" + "\t\t (" + DATA_STRUCTURE_NAMES[i] + ")");
            } else {
                System.out.println("Data Structure " + (i + 1) + " random retrieval average improvement: " + "1.00000000000000x" + "\t\t (" + DATA_STRUCTURE_NAMES[i] + ")");
            }
        }
    }

    public static ObjectClusterTest[] testDataStructureOptionsInsertion() {
        long timings[] = new long[5];
        long cumulativeTimings[] = new long[6];
        ObjectClusterTest[] objcArray = new ObjectClusterTest[30];

        for (int i = 0; i < objcArray.length; i++) {
            ObjectClusterTest objc = new ObjectClusterTest();
            objc.setShimmerName("Test" + String.valueOf(i));
            objc.setMacAddress(String.valueOf(i));

            for (int numDataStructures = 1; numDataStructures < 6; numDataStructures++) {
                objc.dataStructureSelector = numDataStructures;

                long startInsertion = System.nanoTime();
                for (int channels = 0; channels < 20; channels++) {
                    objc.addDataToMap(String.valueOf(channels), CHANNEL_TYPE.CAL.toString(), CHANNEL_UNITS.NO_UNITS, channels);
                }
                long endInsertion = System.nanoTime();

                cumulativeTimings[numDataStructures] = cumulativeTimings[numDataStructures] + (endInsertion - startInsertion);
                System.out.println("Data Structure " + numDataStructures + " insertion time: " + (endInsertion - startInsertion));
            }

            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.err.println("----------------------- end test " + i + " -----------------------\n");
            objcArray[i] = objc;
        }

        System.err.println("-----------------------Cumulative average over 30 ObjectClusters-----------------------\n");
        for (int a = 1; a < cumulativeTimings.length; a++) {
            System.out.println("Option " + a + " cumulative avg insertion time: " + (cumulativeTimings[a] / 30));
        }
        System.err.println("-----------------------End Insertion Test-----------------------\n");
        return objcArray;

    }

    public static void testDataStructureOptionsRetrievalOrdered(ObjectClusterTest[] objcArray) {
        long cumulativeTimings[] = new long[6];
        double[] dataArray = new double[20];
        int testsCount = 1;

        for (ObjectClusterTest objc : objcArray) {
            for (int numDataStructures = 1; numDataStructures < 6; numDataStructures++) {

                if (numDataStructures == 1) {

                    long startArrayList = System.nanoTime();
                    int count = 0;
                    for (SensorData sd : objc.mSensorDataList) {
                        dataArray[count] = sd.mSensorData;
                        count++;
                    }
                    long endArrayList = System.nanoTime();
                    System.out.println("Option 1 ordered retrieval time: " + (endArrayList - startArrayList));
                    cumulativeTimings[numDataStructures] = cumulativeTimings[numDataStructures] + (endArrayList - startArrayList);

                } else if (numDataStructures == 2) {

                    long startNestedHashMap = System.nanoTime();
                    int count = 0;
                    for (HashMap<String, FormatCluster> formatMap : objc.mHashMap.values()) {
                        for (FormatCluster fc : formatMap.values()) {
                            dataArray[count] = fc.mData;
                        }
                        count++;
                    }
                    long endNestedHashMap = System.nanoTime();

                    System.out.println("Option 2 ordered retrieval time: " + (endNestedHashMap - startNestedHashMap));
                    cumulativeTimings[numDataStructures] = cumulativeTimings[numDataStructures] + (endNestedHashMap - startNestedHashMap);

                } else if (numDataStructures == 3) {

                    long startHashMapArray = System.nanoTime();
                    int count = 0;
                    for (FormatCluster[] fc : objc.mHashMapArray.values()) {
                        dataArray[count] = fc[0].mData;
                        count++;
                    }
                    long endHashMapArray = System.nanoTime();

                    System.out.println("Option 3 ordered retrieval time: " + (endHashMapArray - startHashMapArray));
                    cumulativeTimings[numDataStructures] = cumulativeTimings[numDataStructures] + (endHashMapArray - startHashMapArray);

                } else if (numDataStructures == 4) {

                    long startArrays = System.nanoTime();
                    int count = 0;
                    for (SensorData sd : objc.mSensorDataArray) {
                        if (sd != null) {
                            dataArray[count] = sd.mSensorData;
                            count++;
                        }
                    }
                    long endArrays = System.nanoTime();

                    System.out.println("Option 4 ordered retrieval time: " + (endArrays - startArrays));
                    cumulativeTimings[numDataStructures] = cumulativeTimings[numDataStructures] + (endArrays - startArrays);

                } else if (numDataStructures == 5) {

                    long startMultimap = System.nanoTime();
                    int count = 0;
                    for (int channel = 0; channel < 20; channel++) {
                        Collection<FormatCluster> allFormats = objc.getCollectionOfFormatClusters(String.valueOf(channel));
                        FormatCluster fc = ((FormatCluster) ObjectClusterTest.returnFormatCluster(allFormats, "CAL"));
                        dataArray[count] = fc.mData;
                        count++;
                    }
                    long endMultimap = System.nanoTime();

                    System.out.println("Option 5 ordered retrieval time: " + (endMultimap - startMultimap));
                    cumulativeTimings[numDataStructures] = cumulativeTimings[numDataStructures] + (endMultimap - startMultimap);

                }
            }
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.err.println("----------------------- end test " + testsCount + " -----------------------\n");
            testsCount++;
        }

        System.err.println("-----------------------Cumulative average over 30 ObjectClusters-----------------------\n");
        for (int a = 1; a < cumulativeTimings.length; a++) {
            System.out.println("Option " + a + " cumulative avg retrieval time: " + (cumulativeTimings[a] / 30));
        }

    }

    public static void testDataStructureOptionsRetrievalRandom(ObjectClusterTest[] objcArray) {

        long cumulativeTimings[] = new long[6];
        double[] dataArray = new double[50];
        int testsCount = 0;

        for (ObjectClusterTest objc : objcArray) {
            for (int numDataStructures = 1; numDataStructures < 6; numDataStructures++) {

                if (numDataStructures == 1) {

                    long startArrayList = System.nanoTime();
                    for (int j = 0; j < 50; j++) {
                        int randomNum = ThreadLocalRandom.current().nextInt(0, 20);
                        dataArray[j] = objc.mSensorDataList.get(randomNum).mSensorData;
                    }
                    long endArrayList = System.nanoTime();
                    System.out.println("Option 1 random retrieval time: " + (endArrayList - startArrayList));
                    cumulativeTimings[numDataStructures] = cumulativeTimings[numDataStructures] + (endArrayList - startArrayList);

                } else if (numDataStructures == 2) {

                    long startNestedHashMap = System.nanoTime();
                    for (int j = 0; j < 50; j++) {
                        int randomNum = ThreadLocalRandom.current().nextInt(0, 20);
                        dataArray[j] = objc.mHashMap.get(String.valueOf(randomNum)).get(CHANNEL_TYPE.CAL.toString()).mData;
                    }
                    long endNestedHashMap = System.nanoTime();

                    System.out.println("Option 2 random retrieval time: " + (endNestedHashMap - startNestedHashMap));
                    cumulativeTimings[numDataStructures] = cumulativeTimings[numDataStructures] + (endNestedHashMap - startNestedHashMap);

                } else if (numDataStructures == 3) {

                    long startHashMapArray = System.nanoTime();
                    for (int j = 0; j < 50; j++) {
                        int randomNum = ThreadLocalRandom.current().nextInt(0, 20);
                        FormatCluster[] fc = objc.mHashMapArray.get(String.valueOf(randomNum));
                        dataArray[j] = fc[0].mData;
                    }
                    long endHashMapArray = System.nanoTime();

                    System.out.println("Option 3 random retrieval time: " + (endHashMapArray - startHashMapArray));
                    cumulativeTimings[numDataStructures] = cumulativeTimings[numDataStructures] + (endHashMapArray - startHashMapArray);

                } else if (numDataStructures == 4) {

                    long startArrays = System.nanoTime();
                    for (int j = 0; j < 50; j++) {
                        int randomNum = ThreadLocalRandom.current().nextInt(0, 20);
                        dataArray[j] = objc.mSensorDataArray[randomNum].mSensorData;
                    }
                    long endArrays = System.nanoTime();

                    System.out.println("Option 4 random retrieval time: " + (endArrays - startArrays));
                    cumulativeTimings[numDataStructures] = cumulativeTimings[numDataStructures] + (endArrays - startArrays);

                } else if (numDataStructures == 5) {

                    long startMultimap = System.nanoTime();
                    for (int j = 0; j < 50; j++) {
                        int randomNum = ThreadLocalRandom.current().nextInt(0, 20);
                        Collection<FormatCluster> allFormats = objc.getCollectionOfFormatClusters(String.valueOf(randomNum));
                        FormatCluster fc = ((FormatCluster) ObjectClusterTest.returnFormatCluster(allFormats, "CAL"));
                        dataArray[j] = fc.mData;
                    }
                    long endMultimap = System.nanoTime();

                    System.out.println("Option 5 random retrieval time: " + (endMultimap - startMultimap));
                    cumulativeTimings[numDataStructures] = cumulativeTimings[numDataStructures] + (endMultimap - startMultimap);

                }
            }

            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.err.println("----------------------- end test " + testsCount + " -----------------------\n");
            testsCount++;
        }

        System.err.println("-----------------------Cumulative average over 30 ObjectClusters-----------------------\n");
        for (int a = 1; a < cumulativeTimings.length; a++) {
            System.out.println("Option " + a + " cumulative avg random retrieval time: " + (cumulativeTimings[a] / 30));
        }
    }

    public static ObjectClusterTest[][] testDataStructureOptionsInsertionUpdated(boolean printToConsole) {

        long cumulativeTimings[] = new long[NUM_DATA_STRUCTURES];
        long cumulativeTimingsOverall[] = new long[NUM_DATA_STRUCTURES];
        ObjectClusterTest[][] objcArray = new ObjectClusterTest[NUM_DATA_STRUCTURES][NUM_OBJECTCLUSTERS];

        for (int i = 0; i < objcArray.length; i++) {

            long startInsertion = 0;
            long endInsertion = 0;
            long difference = 0;

            if (printToConsole) {
                System.err.println("\n----------------------- Data Structure " + i + " -----------------------\n");
            }

            for (int j = 0; j < NUM_OBJECTCLUSTERS; j++) {

                ObjectClusterTest objc = new ObjectClusterTest();
                objc.setShimmerName("DataStructure" + String.valueOf(i));
                objc.setMacAddress(String.valueOf(i));

                for (int numOfChannels = 0; numOfChannels < 30; numOfChannels++) {
                    if (i == 0) {
                        startInsertion = System.nanoTime();
                        objc.addDataToArrayList(String.valueOf(numOfChannels), CHANNEL_TYPE.CAL.toString(), CHANNEL_UNITS.NO_UNITS, numOfChannels);
                        endInsertion = System.nanoTime();
                    } else if (i == 1) {
                        startInsertion = System.nanoTime();
                        objc.addDataToNestedHashMap(String.valueOf(numOfChannels), CHANNEL_TYPE.CAL.toString(), CHANNEL_UNITS.NO_UNITS, numOfChannels);
                        endInsertion = System.nanoTime();
                    } else if (i == 2) {
                        startInsertion = System.nanoTime();
                        objc.addDataToHashMapArray(String.valueOf(numOfChannels), CHANNEL_TYPE.CAL.toString(), CHANNEL_UNITS.NO_UNITS, numOfChannels);
                        endInsertion = System.nanoTime();
                    } else if (i == 3) {
                        startInsertion = System.nanoTime();
                        objc.addDataToSensorDataArray(String.valueOf(numOfChannels), CHANNEL_TYPE.CAL.toString(), CHANNEL_UNITS.NO_UNITS, numOfChannels);
                        endInsertion = System.nanoTime();
                    } else if (i == 4) {
                        startInsertion = System.nanoTime();
                        objc.addDataToMap(String.valueOf(numOfChannels), CHANNEL_TYPE.CAL.toString(), CHANNEL_UNITS.NO_UNITS, numOfChannels);
                        endInsertion = System.nanoTime();
                    } else if (i == 5) {
                        startInsertion = System.nanoTime();
                        objc.addDataToArrays(String.valueOf(numOfChannels), CHANNEL_TYPE.CAL.toString(), CHANNEL_UNITS.NO_UNITS, numOfChannels);
                        endInsertion = System.nanoTime();
                    } else if (i == 6) {
                        startInsertion = System.nanoTime();
                        testArrayResizing(objc);
                        objc.addDataToArraysWithResize(String.valueOf(numOfChannels), CHANNEL_TYPE.CAL.toString(), CHANNEL_UNITS.NO_UNITS, numOfChannels);
                        endInsertion = System.nanoTime();
                    } else if (i == 7) {
                        startInsertion = System.nanoTime();
                        objc.addDataToNewArrays(String.valueOf(numOfChannels), CHANNEL_TYPE.CAL.toString(), CHANNEL_UNITS.NO_UNITS, numOfChannels);
                        endInsertion = System.nanoTime();
                    }

                    difference = endInsertion - startInsertion;
                    cumulativeTimings[i] = cumulativeTimings[i] + difference;
                }

                objcArray[i][j] = objc;
                if (printToConsole) {
                    System.out.println("Data Structure Type " + i + " avg insertion time of 30 channels: " + cumulativeTimings[i] / 30 + "ns");
                }
                cumulativeTimingsOverall[i] = cumulativeTimingsOverall[i] + cumulativeTimings[i];
                cumulativeTimings[i] = 0;
            }

            if (printToConsole) {
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }

        for (int a = 0; a < cumulativeTimingsOverall.length; a++) {
            cumulativeTimingsOverall[a] = (cumulativeTimingsOverall[a] / 30);
            allInsertionTimes[a] = allInsertionTimes[a] + cumulativeTimingsOverall[a];
        }

        if (printToConsole) {
            System.err.println("----------------------- Cumulative average over 30 ObjectClusters, 30 channels each -----------------------\n");

            for (int a = 0; a < cumulativeTimingsOverall.length; a++) {
                System.out.println("Option " + a + " cumulative avg insertion time: " + cumulativeTimingsOverall[a] + "ns");
            }
            System.err.println("----------------------- Comparison of averages over 30 ObjectClusters against Multimap baseline -----------------------\n");
            long baseline = cumulativeTimingsOverall[4];
            for (int a = 0; a < cumulativeTimingsOverall.length; a++) {
                if (a != 4) {
                    double improvement = 1 / ((cumulativeTimingsOverall[a] * 1.0f) / baseline);
                    System.err.println("Improvement of data structure " + a + " vs Multimap baseline: " + improvement + "x" + "\t\t" + DATA_STRUCTURE_NAMES[a]);
                } else {
                    System.err.println("Improvement of data structure " + a + " vs Multimap baseline: 1.00000000000000x" + "\t\t\t" + DATA_STRUCTURE_NAMES[4]);
                }
            }

            System.err.println("-----------------------End Insertion Test-----------------------\n");
        }
        return objcArray;

    }


    public static void testArrayResizing(ObjectClusterTest obj) {

        if (obj.mCalDataResize == null) {
            obj.mCalDataResize = new double[1];
            obj.mSensorNamesCalResize = new String[1];
            obj.mUnitCalResize = new String[1];
        }

        int currentLength = obj.mCalDataResize.length;
        if (obj.calArrayIndexResize == (currentLength - 1)) {
            String[] tempNamesArray = new String[currentLength + 5];
            String[] tempUnitArray = new String[currentLength + 5];
            double[] tempDataArray = new double[currentLength + 5];

            System.arraycopy(obj.mCalDataResize, 0, tempDataArray, 0, currentLength);
            System.arraycopy(obj.mUnitCalResize, 0, tempUnitArray, 0, currentLength);
            System.arraycopy(obj.mSensorNamesCalResize, 0, tempNamesArray, 0, currentLength);

            obj.mSensorNamesCalResize = tempNamesArray;
            obj.mCalDataResize = tempDataArray;
            obj.mUnitCalResize = tempUnitArray;
        }
    }


    public static void testDataStructureOptionsOrderedRetrievalUpdated(ObjectClusterTest[][] objcArray, boolean printToConsole) {
        long cumulativeTimings[] = new long[NUM_DATA_STRUCTURES];
        long cumulativeTimingsOverall[] = new long[NUM_DATA_STRUCTURES];
        double[] dataArray = new double[50];

        for (int i = 0; i < objcArray.length; i++) {

            long startRetrieval = 0;
            long endRetrieval = 0;
            long difference = 0;

            if (printToConsole) {
                System.err.println("\n----------------------- Data Structure " + i + " -----------------------\n");
            }

            for (int j = 0; j < NUM_OBJECTCLUSTERS; j++) {
                ObjectClusterTest objc = objcArray[i][j];
                if (i == 0) {
                    startRetrieval = System.nanoTime();
                    int count = 0;
                    for (SensorData sd : objc.mSensorDataList) {
                        dataArray[count] = sd.mSensorData;
                        count++;
                    }
                    endRetrieval = System.nanoTime();
                } else if (i == 1) {
                    startRetrieval = System.nanoTime();
                    int count = 0;
                    for (HashMap<String, FormatCluster> formatMap : objc.mHashMap.values()) {
                        for (FormatCluster fc : formatMap.values()) {
                            dataArray[count] = fc.mData;
                        }
                        count++;
                    }
                    endRetrieval = System.nanoTime();
                } else if (i == 2) {
                    startRetrieval = System.nanoTime();
                    int count = 0;
                    for (FormatCluster[] fc : objc.mHashMapArray.values()) {
                        dataArray[count] = fc[0].mData;
                        count++;
                    }
                    endRetrieval = System.nanoTime();
                } else if (i == 3) {
                    startRetrieval = System.nanoTime();
                    int count = 0;
                    for (SensorData sd : objc.mSensorDataArray) {
                        if (sd != null) {
                            dataArray[count] = sd.mSensorData;
                            count++;
                        }
                    }
                    endRetrieval = System.nanoTime();
                } else if (i == 4) {
                    startRetrieval = System.nanoTime();
                    int count = 0;
                    for (int channel = 0; channel < 30; channel++) {
                        Collection<FormatCluster> allFormats = objc.getCollectionOfFormatClusters(String.valueOf(channel));
                        FormatCluster fc = ((FormatCluster) ObjectClusterTest.returnFormatCluster(allFormats, "CAL"));
                        dataArray[count] = fc.mData;
                        count++;
                    }
                    endRetrieval = System.nanoTime();
                } else if (i == 5) {
                    startRetrieval = System.nanoTime();
                    int count = 0;
                    for (int numOfData = 0; numOfData < objc.calArrayIndex; numOfData++) {
                        double data = objc.mCalData[numOfData];
                        dataArray[count] = data;
                        String channelName = objc.mSensorNamesCal[numOfData];
                        String unit = objc.mUnitCal[numOfData];
                        count++;
                    }
                    endRetrieval = System.nanoTime();
                } else if (i == 6) {
                    startRetrieval = System.nanoTime();
                    int count = 0;
                    for (Double data : objc.mCalDataResize) {
                        if (data != null) {
                            dataArray[count] = data;
                            count++;
                        }
                    }
                    endRetrieval = System.nanoTime();
                } else if (i == 7) {
                    startRetrieval = System.nanoTime();
                    int count = 0;
                    for (int numOfData = 0; numOfData < objc.sensorDataArray.mCalArraysIndex; numOfData++) {
                        String channelName = objc.sensorDataArray.mSensorNames[numOfData];
                        String channelUnits = objc.sensorDataArray.mCalUnits[numOfData];
                        dataArray[count] = objc.sensorDataArray.mCalData[numOfData];
                        count++;
                    }
                    endRetrieval = System.nanoTime();
                }

                difference = endRetrieval - startRetrieval;
                cumulativeTimings[i] = cumulativeTimings[i] + difference;

                if (printToConsole) {
                    System.out.println("Data Structure Type " + i + " avg insertion time of 30 channels: " + cumulativeTimings[i] / 30 + "ns");
                }
                cumulativeTimingsOverall[i] = cumulativeTimingsOverall[i] + cumulativeTimings[i];
                cumulativeTimings[i] = 0;
            }

            if (printToConsole) {
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        for (int a = 0; a < cumulativeTimingsOverall.length; a++) {
            cumulativeTimingsOverall[a] = (cumulativeTimingsOverall[a] / 30);
            allStructuredRetrievalTimes[a] = allStructuredRetrievalTimes[a] + cumulativeTimingsOverall[a];
        }

        if (printToConsole) {
            System.err.println("----------------------- Cumulative average over 30 ObjectClusters, 30 channels each -----------------------\n");

            for (int a = 0; a < cumulativeTimingsOverall.length; a++) {
                System.out.println("Option " + a + " cumulative avg structured retrieval time: " + cumulativeTimingsOverall[a] + "ns");
            }
            System.err.println("----------------------- Comparison of averages over 30 ObjectClusters against Multimap baseline -----------------------\n");
            long baseline = cumulativeTimingsOverall[4];
            for (int a = 0; a < cumulativeTimingsOverall.length; a++) {
                if (a != 4) {
                    double improvement = 1 / ((cumulativeTimingsOverall[a] * 1.0f) / baseline);
                    System.err.println("Improvement of data structure " + a + " vs Multimap baseline: " + improvement + "x" + "\t\t" + DATA_STRUCTURE_NAMES[a]);
                } else {
                    System.err.println("Improvement of data structure " + a + " vs Multimap baseline: 1.00000000000000x" + "\t\t\t" + DATA_STRUCTURE_NAMES[4]);
                }
            }

            System.err.println("-----------------------End Structured Retrieval Test-----------------------\n");
        }

    }


    public static void testDataStructureOptionsRandomRetrievalUpdated(ObjectClusterTest[][] objcArray, boolean printToConsole) {
        long cumulativeTimings[] = new long[NUM_DATA_STRUCTURES];
        long cumulativeTimingsOverall[] = new long[NUM_DATA_STRUCTURES];
        double[] dataArray = new double[60];

        for (int i = 0; i < objcArray.length; i++) {

            long startRetrieval = 0;
            long endRetrieval = 0;
            long difference = 0;

            if (printToConsole) {
                System.err.println("\n----------------------- Data Structure " + i + " -----------------------\n");
            }

            for (int j = 0; j < NUM_OBJECTCLUSTERS; j++) {
                ObjectClusterTest objc = objcArray[i][j];
                if (i == 0) {
                    startRetrieval = System.nanoTime();
                    for (int k = 0; k < 60; k++) {
                        int randomNum = ThreadLocalRandom.current().nextInt(0, 30);
                        dataArray[k] = objc.mSensorDataList.get(randomNum).mSensorData;
                    }
                    endRetrieval = System.nanoTime();
                } else if (i == 1) {
                    startRetrieval = System.nanoTime();
                    for (int k = 0; k < 60; k++) {
                        int randomNum = ThreadLocalRandom.current().nextInt(0, 30);
                        dataArray[k] = objc.mHashMap.get(String.valueOf(randomNum)).get(CHANNEL_TYPE.CAL.toString()).mData;
                    }
                    endRetrieval = System.nanoTime();
                } else if (i == 2) {
                    startRetrieval = System.nanoTime();
                    for (int k = 0; k < 60; k++) {
                        int randomNum = ThreadLocalRandom.current().nextInt(0, 30);
                        FormatCluster[] fc = objc.mHashMapArray.get(String.valueOf(randomNum));
                        dataArray[k] = fc[0].mData;
                    }
                    endRetrieval = System.nanoTime();
                } else if (i == 3) {
                    startRetrieval = System.nanoTime();
                    for (int k = 0; k < 60; k++) {
                        int randomNum = ThreadLocalRandom.current().nextInt(0, 30);
                        dataArray[k] = objc.mSensorDataArray[randomNum].mSensorData;
                    }
                    endRetrieval = System.nanoTime();
                } else if (i == 4) {
                    startRetrieval = System.nanoTime();
                    for (int k = 0; k < 60; k++) {
                        int randomNum = ThreadLocalRandom.current().nextInt(0, 30);
                        Collection<FormatCluster> allFormats = objc.getCollectionOfFormatClusters(String.valueOf(randomNum));
                        FormatCluster fc = ((FormatCluster) ObjectClusterTest.returnFormatCluster(allFormats, "CAL"));
                        dataArray[k] = fc.mData;
                    }
                    endRetrieval = System.nanoTime();
                } else if (i == 5) {
                    String channelName = "";
                    String units = "";
                    startRetrieval = System.nanoTime();
                    for (int k = 0; k < 60; k++) {
                        int randomNum = ThreadLocalRandom.current().nextInt(0, 30);
                        dataArray[k] = objc.mCalData[randomNum];
                    }
                    endRetrieval = System.nanoTime();
                } else if (i == 6) {
                    startRetrieval = System.nanoTime();
                    for (int k = 0; k < 60; k++) {
                        int randomNum = ThreadLocalRandom.current().nextInt(0, 30);
                        dataArray[k] = objc.mCalDataResize[randomNum];
                    }
                    endRetrieval = System.nanoTime();
                } else if (i == 7) {
                    startRetrieval = System.nanoTime();
                    for (int k = 0; k < 60; k++) {
                        int randomNum = ThreadLocalRandom.current().nextInt(0, 30);
                        dataArray[k] = objc.sensorDataArray.mCalData[randomNum];
                    }
                    endRetrieval = System.nanoTime();
                }

                difference = endRetrieval - startRetrieval;
                cumulativeTimings[i] = cumulativeTimings[i] + difference;

                if (printToConsole) {
                    System.out.println("Data Structure Type " + i + " avg random retrieval time of 30 channels retrieved 60 times: " + cumulativeTimings[i] / 30 + "ns");
                }
                cumulativeTimingsOverall[i] = cumulativeTimingsOverall[i] + cumulativeTimings[i];
                cumulativeTimings[i] = 0;
            }

            if (printToConsole) {
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }

        for (int a = 0; a < cumulativeTimingsOverall.length; a++) {
            cumulativeTimingsOverall[a] = (cumulativeTimingsOverall[a] / 30);
            allRandomRetrievalTimes[a] = allRandomRetrievalTimes[a] + cumulativeTimingsOverall[a];
        }

        if (printToConsole) {
            System.err.println("----------------------- Cumulative average over 30 ObjectClusters, 30 channels each -----------------------\n");

            for (int a = 0; a < cumulativeTimingsOverall.length; a++) {
                System.out.println("Option " + a + " cumulative avg random retrieval time: " + cumulativeTimingsOverall[a] + "ns");
            }
            System.err.println("----------------------- Comparison of averages over 30 ObjectClusters against Multimap baseline -----------------------\n");
            long baseline = cumulativeTimingsOverall[4];
            for (int a = 0; a < cumulativeTimingsOverall.length; a++) {
                if (a != 4) {
                    double improvement = 1 / ((cumulativeTimingsOverall[a] * 1.0f) / baseline);
                    System.err.println("Improvement of data structure " + a + " vs Multimap baseline: " + improvement + "x" + "\t\t" + DATA_STRUCTURE_NAMES[a]);
                } else {
                    System.err.println("Improvement of data structure " + a + " vs Multimap baseline: 1.00000000000000x" + "\t\t\t" + DATA_STRUCTURE_NAMES[4]);
                }
            }

            System.err.println("-----------------------End Random Retrieval Test-----------------------\n");
        }
    }

}



