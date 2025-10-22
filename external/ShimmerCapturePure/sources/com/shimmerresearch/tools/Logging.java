package com.shimmerresearch.tools;

import android.content.Context;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;
import androidx.documentfile.provider.DocumentFile;
import com.google.common.collect.Multimap;
import com.shimmerresearch.android.shimmerService.ShimmerService;
import com.shimmerresearch.driver.FormatCluster;
import com.shimmerresearch.driver.ObjectCluster;
import com.shimmerresearch.driver.ShimmerDevice;
import com.shimmerresearch.verisense.UtilVerisenseDriver;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Collection;

/* loaded from: classes2.dex */
public class Logging {
    Context mContext;
    String mDelimiter;
    String mFileName;
    boolean mFirstWrite;
    DocumentFile mNewFile;
    String[] mSensorFormats;
    String[] mSensorNames;
    String[] mSensorUnits;
    File outputFile;
    BufferedWriter writer;

    @Deprecated
    public Logging(String str) {
        this.mFirstWrite = true;
        this.writer = null;
        this.mDelimiter = UtilVerisenseDriver.CSV_DELIMITER;
        this.mNewFile = null;
        this.mContext = null;
        this.mFileName = str;
        File externalStorageDirectory = Environment.getExternalStorageDirectory();
        Log.d("AbsolutePath", externalStorageDirectory.getAbsolutePath());
        this.outputFile = new File(externalStorageDirectory, this.mFileName + ".dat");
    }

    @Deprecated
    public Logging(String str, String str2) {
        this.mFirstWrite = true;
        this.writer = null;
        this.mNewFile = null;
        this.mContext = null;
        this.mFileName = str;
        this.mDelimiter = str2;
        File externalStorageDirectory = Environment.getExternalStorageDirectory();
        Log.d("AbsolutePath", externalStorageDirectory.getAbsolutePath());
        this.outputFile = new File(externalStorageDirectory, this.mFileName + ".dat");
    }

    @Deprecated
    public Logging(String str, String str2, String str3) {
        this.mFirstWrite = true;
        this.writer = null;
        this.mNewFile = null;
        this.mContext = null;
        this.mFileName = str;
        this.mDelimiter = str2;
        File file = new File(Environment.getExternalStorageDirectory() + "/" + str3);
        if (!file.exists()) {
            file.mkdir();
        }
        this.outputFile = new File(file, this.mFileName + "." + ShimmerService.FILE_TYPE.DAT.getName());
    }

    @Deprecated
    public Logging(String str, String str2, String str3, ShimmerService.FILE_TYPE file_type) {
        this.mFirstWrite = true;
        this.writer = null;
        this.mNewFile = null;
        this.mContext = null;
        this.mFileName = str;
        this.mDelimiter = str2;
        File externalStoragePublicDirectory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS + "/" + str3);
        if (!externalStoragePublicDirectory.exists() && externalStoragePublicDirectory.mkdir()) {
            System.out.println();
        }
        this.outputFile = new File(externalStoragePublicDirectory, this.mFileName + "." + file_type.getName());
    }

    public Logging(Uri uri, Context context, String str, String str2, String str3, ShimmerService.FILE_TYPE file_type) {
        this.mFirstWrite = true;
        this.writer = null;
        this.mNewFile = null;
        this.mContext = null;
        this.mFileName = str;
        this.mDelimiter = str2;
        DocumentFile documentFileFromTreeUri = DocumentFile.fromTreeUri(context, uri);
        if (file_type.equals(ShimmerService.FILE_TYPE.CSV)) {
            this.mNewFile = documentFileFromTreeUri.createFile("text/comma-separated-values", str);
        } else if (file_type.equals(ShimmerService.FILE_TYPE.DAT)) {
            this.mNewFile = documentFileFromTreeUri.createFile("application/dat", str + "." + ShimmerService.FILE_TYPE.DAT.toString().toLowerCase());
        }
        this.mContext = context;
    }

    public String getName() {
        return this.mFileName;
    }

    public File getOutputFile() {
        return this.outputFile;
    }

    public void logData(ObjectCluster objectCluster) throws IOException {
        try {
            int i = 0;
            if (this.mFirstWrite) {
                if (this.mNewFile != null) {
                    this.writer = new BufferedWriter(new OutputStreamWriter(this.mContext.getContentResolver().openOutputStream(this.mNewFile.getUri())));
                } else {
                    this.writer = new BufferedWriter(new FileWriter(this.outputFile, true));
                }
                Multimap<String, FormatCluster> propertyCluster = objectCluster.getPropertyCluster();
                int size = propertyCluster.size();
                System.out.print(size);
                this.mSensorNames = new String[size];
                this.mSensorFormats = new String[size];
                this.mSensorUnits = new String[size];
                int i2 = 0;
                int i3 = 0;
                for (String str : propertyCluster.keys()) {
                    if (compareStringArray(this.mSensorNames, str)) {
                        for (FormatCluster formatCluster : propertyCluster.get(str)) {
                            this.mSensorFormats[i3] = formatCluster.mFormat;
                            this.mSensorUnits[i3] = formatCluster.mUnits;
                            i3++;
                        }
                    }
                    this.mSensorNames[i2] = str;
                    i2++;
                }
                for (int i4 = 0; i4 < this.mSensorNames.length; i4++) {
                    this.writer.write(objectCluster.getShimmerName());
                    this.writer.write(this.mDelimiter);
                }
                this.writer.newLine();
                int i5 = 0;
                while (true) {
                    String[] strArr = this.mSensorNames;
                    if (i5 >= strArr.length) {
                        break;
                    }
                    this.writer.write(strArr[i5]);
                    this.writer.write(this.mDelimiter);
                    i5++;
                }
                this.writer.newLine();
                int i6 = 0;
                while (true) {
                    String[] strArr2 = this.mSensorFormats;
                    if (i6 >= strArr2.length) {
                        break;
                    }
                    this.writer.write(strArr2[i6]);
                    this.writer.write(this.mDelimiter);
                    i6++;
                }
                this.writer.newLine();
                int i7 = 0;
                while (true) {
                    String[] strArr3 = this.mSensorUnits;
                    if (i7 >= strArr3.length) {
                        break;
                    }
                    String str2 = strArr3[i7];
                    if (str2 == "u8") {
                        this.writer.write("");
                    } else if (str2 == "i8") {
                        this.writer.write("");
                    } else if (str2 == "u12") {
                        this.writer.write("");
                    } else if (str2 == "u16") {
                        this.writer.write("");
                    } else if (str2 == "i16") {
                        this.writer.write("");
                    } else {
                        this.writer.write(str2);
                    }
                    this.writer.write(this.mDelimiter);
                    i7++;
                }
                this.writer.newLine();
                this.mFirstWrite = false;
            }
            while (true) {
                String[] strArr4 = this.mSensorNames;
                if (i < strArr4.length) {
                    this.writer.write(Double.toString(returnFormatCluster(objectCluster.getCollectionOfFormatClusters(strArr4[i]), this.mSensorFormats[i], this.mSensorUnits[i]).mData));
                    this.writer.write(this.mDelimiter);
                    i++;
                } else {
                    this.writer.newLine();
                    return;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            Log.d(ShimmerDevice.DEFAULT_SHIMMER_NAME, "Error with bufferedwriter");
        }
    }

    public void closeFile() throws IOException {
        BufferedWriter bufferedWriter = this.writer;
        if (bufferedWriter != null) {
            try {
                bufferedWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public String getAbsoluteName() throws Exception {
        if (this.mNewFile != null) {
            throw new Exception("Unsupported when using treeURI");
        }
        return this.outputFile.getAbsolutePath();
    }

    private boolean compareStringArray(String[] strArr, String str) {
        boolean z = true;
        for (String str2 : strArr) {
            if (str2 == str) {
                z = false;
            }
        }
        return z;
    }

    private FormatCluster returnFormatCluster(Collection<FormatCluster> collection, String str, String str2) {
        FormatCluster formatCluster = null;
        for (FormatCluster formatCluster2 : collection) {
            if (formatCluster2.mFormat == str && formatCluster2.mUnits == str2) {
                formatCluster = formatCluster2;
            }
        }
        return formatCluster;
    }
}
