

package com.shimmerresearch.tools;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.Collection;
import java.util.Iterator;

import android.content.Context;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;

import androidx.documentfile.provider.DocumentFile;

import com.google.common.collect.Multimap;
import com.shimmerresearch.android.shimmerService.ShimmerService;
import com.shimmerresearch.driver.FormatCluster;
import com.shimmerresearch.driver.ObjectCluster;


public class Logging {
    boolean mFirstWrite = true;
    String[] mSensorNames;
    String[] mSensorFormats;
    String[] mSensorUnits;
    String mFileName = "";
    BufferedWriter writer = null;
    File outputFile;
    String mDelimiter = ","; //default is comma
    DocumentFile mNewFile = null;
    Context mContext = null;

    @Deprecated
        public Logging(String myName) {
        mFileName = myName;
        File root = Environment.getExternalStorageDirectory();
        Log.d("AbsolutePath", root.getAbsolutePath());
        outputFile = new File(root, mFileName + ".dat");
    }

    @Deprecated
    public Logging(String myName, String delimiter) {
        mFileName = myName;
        mDelimiter = delimiter;
        File root = Environment.getExternalStorageDirectory();
        Log.d("AbsolutePath", root.getAbsolutePath());
        outputFile = new File(root, mFileName + ".dat");
    }

    @Deprecated
        public Logging(String myName, String delimiter, String folderName) {
        mFileName = myName;
        mDelimiter = delimiter;
        File root = new File(Environment.getExternalStorageDirectory() + "/" + folderName);
        if (!root.exists()) {
            if (root.mkdir()) ; //directory is created;
        }
        outputFile = new File(root, mFileName + "." + ShimmerService.FILE_TYPE.DAT.getName());
    }

    @Deprecated
        public Logging(String myName, String delimiter, String folderName, ShimmerService.FILE_TYPE fileType) {
        mFileName = myName;
        mDelimiter = delimiter;
        File root;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
            root = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS + "/" + folderName);
        } else {
            root = new File(Environment.getExternalStorageDirectory() + "/" + folderName); //android 13 no longer allows this
        }

        if (!root.exists()) {
            if (root.mkdir()) {
                System.out.println();//directory is created;
            } else {

            }
        }
        outputFile = new File(root, mFileName + "." + fileType.getName());
    }

    public Logging(Uri treeURI, Context context, String myName, String delimiter, String folderName, ShimmerService.FILE_TYPE fileType) {
        mFileName = myName;
        mDelimiter = delimiter;

        File root;

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {

            DocumentFile pickedDir = DocumentFile.fromTreeUri(context, treeURI);
            if (fileType.equals(ShimmerService.FILE_TYPE.CSV)) {
                mNewFile = pickedDir.createFile("text/comma-separated-values", myName);
            } else if (fileType.equals(ShimmerService.FILE_TYPE.DAT)) {
                mNewFile = pickedDir.createFile("application/dat", myName + "." + ShimmerService.FILE_TYPE.DAT.toString().toLowerCase());
            }
            mContext = context;
        }


    }


        public void logData(ObjectCluster objectCluster) {
        ObjectCluster objectClusterLog = objectCluster;

        try {
            if (mFirstWrite == true) {
                if (mNewFile != null) {
                    OutputStream outputStream = mContext.getContentResolver().openOutputStream(mNewFile.getUri());
                    writer = new BufferedWriter(new OutputStreamWriter(outputStream));
                } else {
                    writer = new BufferedWriter(new FileWriter(outputFile, true));
                }
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


                for (int k = 0; k < mSensorNames.length; k++) {
                    writer.write(objectClusterLog.getShimmerName());
                    writer.write(mDelimiter);
                }
                writer.newLine(); // notepad recognized new lines as \r\n

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
                FormatCluster formatCluster = (FormatCluster) returnFormatCluster(dataFormats, mSensorFormats[r], mSensorUnits[r]);  // retrieve the calibrated data
                writer.write(Double.toString(formatCluster.mData));
                writer.write(mDelimiter);
            }
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
            Log.d("Shimmer", "Error with bufferedwriter");
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

    public File getOutputFile() {
        return outputFile;
    }

    public String getAbsoluteName() throws Exception {
        if (mNewFile != null) {
            throw new Exception("Unsupported when using treeURI");
        }
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


