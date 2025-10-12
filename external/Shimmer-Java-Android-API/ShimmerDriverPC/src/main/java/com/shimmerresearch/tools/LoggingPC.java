package com.shimmerresearch.tools;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;

import com.google.common.collect.Multimap;
import com.shimmerresearch.driver.FormatCluster;
import com.shimmerresearch.driver.ObjectCluster;
import com.shimmerresearch.driverUtilities.ChannelDetails.CHANNEL_TYPE;


public class LoggingPC {
    public Boolean mFileExists = false;
    boolean mFirstWrite = true;
    String[] mSensorNames;
    String[] mSensorFormats;
    String[] mSensorUnits;
    String mFileName = "";
    BufferedWriter writer = null;
    File outputFile;
    String mDelimiter = ",";

    public LoggingPC(String fileName) {

        mFileName = fileName;
        if (mFileName.contains(".csv")) {
            outputFile = new File(mFileName);
        } else {
            outputFile = new File(mFileName + ".csv");
        }
        if (outputFile.exists()) {
            System.out.println("File Exists");
            mFileExists = true;
        } else {
            mFileExists = false;
        }

    }

    public LoggingPC(String fileName, String delimiter) {
        mDelimiter = delimiter;
        mFileName = fileName;
        if (mFileName.contains(".csv")) {
            outputFile = new File(mFileName);
        } else {
            outputFile = new File(mFileName + ".csv");
        }
        if (outputFile.exists()) {
            System.out.println("File Exists");
            mFileExists = true;
        } else {
            mFileExists = false;
        }
    }


    public void logData(ObjectCluster objectCluster) {
        ObjectCluster objectClusterLog = objectCluster;
        try {

            if (mFirstWrite == true) {
                writer = new BufferedWriter(new FileWriter(outputFile, true));

                Multimap<String, FormatCluster> m = objectClusterLog.getPropertyCluster();

                int size = m.size();
                System.out.print(size);
                mSensorNames = new String[size];
                mSensorFormats = new String[size];
                mSensorUnits = new String[size];
                int i = 0;
                int p = 0;
                for (String key : m.keys()) {

                    if (compareStringArray(mSensorNames, key) == true) {
                        for (FormatCluster formatCluster : m.get(key)) {
                            mSensorFormats[p] = formatCluster.mFormat;
                            mSensorUnits[p] = formatCluster.mUnits;
                            p++;
                        }

                    }

                    mSensorNames[i] = key;
                    i++;
                }


                writer = new BufferedWriter(new FileWriter(outputFile, false));

                for (int k = 0; k < mSensorNames.length; k++) {
                    writer.write(objectClusterLog.getShimmerName());
                    writer.write(mDelimiter);
                }
                writer.newLine();

                for (int k = 0; k < mSensorNames.length; k++) {
                    writer.write(mSensorNames[k]);
                    writer.write(mDelimiter);
                }
                writer.newLine();

                for (int k = 0; k < mSensorFormats.length; k++) {
                    writer.write(mSensorFormats[k]);

                    writer.write(mDelimiter);
                }
                writer.newLine();

                for (int k = 0; k < mSensorUnits.length; k++) {
                    if (mSensorUnits[k] == "u8") {
                        writer.write("");
                    } else if (mSensorUnits[k] == "i8") {
                        writer.write("");
                    } else if (mSensorUnits[k] == "u12") {
                        writer.write("");
                    } else if (mSensorUnits[k] == "u16") {
                        writer.write("");
                    } else if (mSensorUnits[k] == "i16") {
                        writer.write("");
                    } else {
                        writer.write(mSensorUnits[k]);
                    }
                    writer.write(mDelimiter);
                }
                writer.newLine();
                mFirstWrite = false;
            }

            for (int r = 0; r < mSensorNames.length; r++) {
                Collection<FormatCluster> dataFormats = objectClusterLog.getCollectionOfFormatClusters(mSensorNames[r]);
                FormatCluster formatCluster = (FormatCluster) returnFormatCluster(dataFormats, mSensorFormats[r], mSensorUnits[r]);
                writer.write(Double.toString(formatCluster.mData));
                writer.write(mDelimiter);
            }
            writer.newLine();


        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Shimmer" + "Error with bufferedwriter");
        }
    }

    public void logFilteredData(ObjectCluster objectCluster, double[] exg1_24bit, double[] exg2_24bit, double[] exg1_16bit, double[] exg2_16bit) {
        ObjectCluster objectClusterLog = objectCluster;
        try {

            if (mFirstWrite == true) {
                writer = new BufferedWriter(new FileWriter(outputFile, true));

                Multimap<String, FormatCluster> m = objectClusterLog.getPropertyCluster();

                int size = m.size();
                System.out.print(size);
                mSensorNames = new String[size];
                mSensorFormats = new String[size];
                mSensorUnits = new String[size];
                int i = 0;
                int p = 0;
                for (String key : m.keys()) {

                    if (compareStringArray(mSensorNames, key) == true) {
                        for (FormatCluster formatCluster : m.get(key)) {
                            mSensorFormats[p] = formatCluster.mFormat;
                            mSensorUnits[p] = formatCluster.mUnits;
                            p++;
                        }

                    }

                    mSensorNames[i] = key;
                    i++;
                }


                writer = new BufferedWriter(new FileWriter(outputFile, false));

                for (int k = 0; k < mSensorNames.length; k++) {
                    writer.write(objectClusterLog.getShimmerName());
                    writer.write(mDelimiter);
                }
                writer.newLine();

                for (int k = 0; k < mSensorNames.length; k++) {
                    writer.write(mSensorNames[k]);
                    writer.write(mDelimiter);
                }
                writer.newLine();

                for (int k = 0; k < mSensorFormats.length; k++) {
                    writer.write(mSensorFormats[k]);

                    writer.write(mDelimiter);
                }
                writer.newLine();

                for (int k = 0; k < mSensorUnits.length; k++) {
                    if (mSensorUnits[k] == "u8") {
                        writer.write("");
                    } else if (mSensorUnits[k] == "i8") {
                        writer.write("");
                    } else if (mSensorUnits[k] == "u12") {
                        writer.write("");
                    } else if (mSensorUnits[k] == "u16") {
                        writer.write("");
                    } else if (mSensorUnits[k] == "i16") {
                        writer.write("");
                    } else {
                        writer.write(mSensorUnits[k]);
                    }
                    writer.write(mDelimiter);
                }
                writer.newLine();
                mFirstWrite = false;
            }

            for (int r = 0; r < mSensorNames.length; r++) {
                Collection<FormatCluster> dataFormats = objectClusterLog.getCollectionOfFormatClusters(mSensorNames[r]);
                FormatCluster formatCluster = (FormatCluster) returnFormatCluster(dataFormats, mSensorFormats[r], mSensorUnits[r]);

                if (mSensorNames[r] == "EXG1 CH1" && mSensorFormats[r] == "CAL") {
                    writer.write(Double.toString(exg1_24bit[0]));
                } else if (mSensorNames[r] == "EXG1 CH1" && mSensorFormats[r] == CHANNEL_TYPE.UNCAL.toString()) {
                    writer.write(Double.toString(exg1_24bit[1]));
                } else if (mSensorNames[r] == "EXG1 CH2" && mSensorFormats[r] == "CAL") {
                    writer.write(Double.toString(exg1_24bit[2]));
                } else if (mSensorNames[r] == "EXG1 CH2" && mSensorFormats[r] == CHANNEL_TYPE.UNCAL.toString()) {
                    writer.write(Double.toString(exg1_24bit[3]));
                } else if (mSensorNames[r] == "EXG2 CH1" && mSensorFormats[r] == "CAL") {
                    writer.write(Double.toString(exg2_24bit[0]));
                } else if (mSensorNames[r] == "EXG2 CH1" && mSensorFormats[r] == CHANNEL_TYPE.UNCAL.toString()) {
                    writer.write(Double.toString(exg2_24bit[1]));
                } else if (mSensorNames[r] == "EXG2 CH2" && mSensorFormats[r] == "CAL") {
                    writer.write(Double.toString(exg2_24bit[2]));
                } else if (mSensorNames[r] == "EXG2 CH2" && mSensorFormats[r] == CHANNEL_TYPE.UNCAL.toString()) {
                    writer.write(Double.toString(exg2_24bit[3]));
                } else if (mSensorNames[r] == "EXG1 CH1 16Bit" && mSensorFormats[r] == "CAL") {
                    writer.write(Double.toString(exg1_16bit[0]));
                } else if (mSensorNames[r] == "EXG1 CH1 16Bit" && mSensorFormats[r] == CHANNEL_TYPE.UNCAL.toString()) {
                    writer.write(Double.toString(exg1_16bit[1]));
                } else if (mSensorNames[r] == "EXG1 CH2 16Bit" && mSensorFormats[r] == "CAL") {
                    writer.write(Double.toString(exg1_16bit[2]));
                } else if (mSensorNames[r] == "EXG1 CH2 16Bit" && mSensorFormats[r] == CHANNEL_TYPE.UNCAL.toString()) {
                    writer.write(Double.toString(exg1_16bit[3]));
                } else if (mSensorNames[r] == "EXG2 CH1 16Bit" && mSensorFormats[r] == "CAL") {
                    writer.write(Double.toString(exg2_16bit[0]));
                } else if (mSensorNames[r] == "EXG2 CH1 16Bit" && mSensorFormats[r] == CHANNEL_TYPE.UNCAL.toString()) {
                    writer.write(Double.toString(exg2_16bit[1]));
                } else if (mSensorNames[r] == "EXG2 CH2 16Bit" && mSensorFormats[r] == "CAL") {
                    writer.write(Double.toString(exg2_16bit[2]));
                } else if (mSensorNames[r] == "EXG2 CH2 16Bit" && mSensorFormats[r] == CHANNEL_TYPE.UNCAL.toString()) {
                    writer.write(Double.toString(exg2_16bit[3]));
                } else {
                    writer.write(Double.toString(formatCluster.mData));
                }
                writer.write(mDelimiter);
            }
            writer.newLine();


        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Shimmer" + "Error with bufferedwriter");
        }
    }

    public void closeFile() {
        if (writer != null) {
            try {
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public String getName() {
        return mFileName;
    }

    public String getAbsoluteName() {
        return outputFile.getAbsolutePath();
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

    private FormatCluster returnFormatCluster(Collection<FormatCluster> collectionFormatCluster, String format, String units) {
        Iterator<FormatCluster> iFormatCluster = collectionFormatCluster.iterator();
        FormatCluster formatCluster;
        FormatCluster returnFormatCluster = null;

        while (iFormatCluster.hasNext()) {
            formatCluster = (FormatCluster) iFormatCluster.next();
            if (formatCluster.mFormat == format && formatCluster.mUnits == units) {
                returnFormatCluster = formatCluster;
            }
        }
        return returnFormatCluster;
    }

}
