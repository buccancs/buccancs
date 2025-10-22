package com.shimmerresearch.driverUtilities;

import com.shimmerresearch.driver.FormatCluster;
import com.shimmerresearch.driver.ObjectCluster;
import com.shimmerresearch.driverUtilities.ChannelDetails;
import com.shimmerresearch.driverUtilities.UtilShimmer;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

/* loaded from: classes2.dex */
public class ParserStreamedDataToCSV {
    public static final String PATH_SEP = System.getProperty("file.separator");
    private BufferedWriter bufferedWriter;
    private String fileName;
    private String filePath;
    private boolean isFileHeaderWritten;
    private List<String> listOfChannelNamesToWriteToCSV;
    private String fileDelimiter = UtilShimmer.ENUM_FILE_DELIMITERS.TAB.delimiter;
    private String fileExtension = UtilShimmer.ENUM_FILE_FORMAT.DAT.fileExtension;
    private boolean isFileDelimiterDescriptorInFile = false;
    private boolean isLimitingDecimalPlaces = false;
    private DecimalFormat decimalFormat = new DecimalFormat("#.00");

    public ParserStreamedDataToCSV(String str, String str2, List<String> list) {
        new ArrayList();
        this.filePath = str;
        this.fileName = str2;
        this.listOfChannelNamesToWriteToCSV = list;
        this.isFileHeaderWritten = false;
        this.bufferedWriter = null;
    }

    public String getFileExtension() {
        return this.fileExtension;
    }

    private void setFileExtension(String str) {
        this.fileExtension = str;
    }

    public String getFileName() {
        return this.fileName;
    }

    public String getFilePath() {
        return this.filePath;
    }

    public String getFileSeparator() {
        return this.fileDelimiter;
    }

    private void setFileSeparator(String str) {
        this.fileDelimiter = str;
    }

    public void setFileDelimiterDescriptorInFile(boolean z) {
        this.isFileDelimiterDescriptorInFile = z;
    }

    public void setIsLimitingDecimalPlaces(boolean z) {
        this.isLimitingDecimalPlaces = z;
    }

    public void setFileSeparatorAndExtension(String str, String str2) {
        setFileSeparator(str);
        setFileExtension(str2);
    }

    public void parseDataToCSV(ObjectCluster objectCluster) throws IOException {
        createCSVAndWriteHeader(objectCluster);
        writeDataToCSVFile(objectCluster);
    }

    public void parseDataToCSV(ObjectCluster[] objectClusterArr) throws IOException {
        for (ObjectCluster objectCluster : objectClusterArr) {
            createCSVAndWriteHeader(objectCluster);
            writeDataToCSVFile(objectCluster);
        }
    }

    private void createCSVAndWriteHeader(ObjectCluster objectCluster) throws IOException {
        if (this.isFileHeaderWritten) {
            return;
        }
        createCSVFile();
        prepareAndWriteHeaderToCSVFile(objectCluster);
    }

    public void createCSVFile() {
        try {
            this.bufferedWriter = new BufferedWriter(new FileWriter(UtilShimmer.createFileAndDeleteIfExists(this.filePath + PATH_SEP + this.fileName + this.fileExtension), true));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void prepareAndWriteHeaderToCSVFile(ObjectCluster objectCluster) throws IOException {
        String str;
        List<String> channelNamesByInsertionOrder = objectCluster.getChannelNamesByInsertionOrder();
        String str2 = "";
        if (isListOfChannelNamesToWriteToCSVInvalid()) {
            str = "";
            for (String str3 : channelNamesByInsertionOrder) {
                str2 = str2 + formatHeaderChannelName(objectCluster.getShimmerName() + "_" + str3);
                str = str + formatHeaderChannelUnits(str3, objectCluster);
            }
        } else {
            String str4 = "";
            for (String str5 : this.listOfChannelNamesToWriteToCSV) {
                if (channelNamesByInsertionOrder.contains(str5)) {
                    str2 = str2 + formatHeaderChannelName(objectCluster.getShimmerName() + "_" + str5);
                    str4 = str4 + formatHeaderChannelUnits(str5, objectCluster);
                }
            }
            str = str4;
        }
        writeDoubleLineHeaderToCSVFile(str2, str);
    }

    public void writeDoubleLineHeaderToCSVFile(String str, String str2) throws IOException {
        try {
            writeSingleLineHeaderToCSVFile(str);
            this.bufferedWriter.write(str2);
            this.bufferedWriter.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.isFileHeaderWritten = true;
    }

    public void writeSingleLineHeaderToCSVFile(String str) throws IOException {
        try {
            if (this.isFileDelimiterDescriptorInFile) {
                this.bufferedWriter.write("\"sep=" + this.fileDelimiter + "\"");
                this.bufferedWriter.newLine();
            }
            this.bufferedWriter.write(str);
            this.bufferedWriter.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.isFileHeaderWritten = true;
    }

    private String formatHeaderChannelName(String str) {
        return str + this.fileDelimiter;
    }

    private String formatHeaderChannelUnits(String str, ObjectCluster objectCluster) {
        FormatCluster lastFormatCluster = objectCluster.getLastFormatCluster(str, ChannelDetails.CHANNEL_TYPE.CAL.toString());
        if (lastFormatCluster != null) {
            return String.valueOf(lastFormatCluster.mUnits) + this.fileDelimiter;
        }
        return StringUtils.SPACE + this.fileDelimiter;
    }

    private void writeDataToCSVFile(ObjectCluster objectCluster) throws IOException {
        String str = "";
        if (isListOfChannelNamesToWriteToCSVInvalid()) {
            Iterator<String> it2 = objectCluster.getChannelNamesByInsertionOrder().iterator();
            while (it2.hasNext()) {
                str = str + getFormattedValue(objectCluster.getFormatClusterValue(it2.next(), ChannelDetails.CHANNEL_TYPE.CAL.toString())) + this.fileDelimiter;
            }
        } else {
            Iterator<String> it3 = this.listOfChannelNamesToWriteToCSV.iterator();
            while (it3.hasNext()) {
                str = str + getFormattedValue(objectCluster.getFormatClusterValue(it3.next(), ChannelDetails.CHANNEL_TYPE.CAL.toString())) + this.fileDelimiter;
            }
        }
        writeSample(str);
    }

    private String getFormattedValue(double d) {
        return (!this.isLimitingDecimalPlaces || Double.isNaN(d)) ? String.valueOf(d) : this.decimalFormat.format(d);
    }

    public void writeSample(String str) throws IOException {
        try {
            this.bufferedWriter.write(str);
            this.bufferedWriter.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void stopWritingDataToCSVFile() throws IOException {
        try {
            BufferedWriter bufferedWriter = this.bufferedWriter;
            if (bufferedWriter != null) {
                bufferedWriter.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean isListOfChannelNamesToWriteToCSVInvalid() {
        List<String> list = this.listOfChannelNamesToWriteToCSV;
        return list == null || list.isEmpty();
    }
}
