package com.shimmerresearch.verisense.payloaddesign;

import com.shimmerresearch.driver.Configuration;
import com.shimmerresearch.verisense.UtilVerisenseDriver;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes2.dex */
public class DataSegmentDetails implements Serializable {
    private static final long serialVersionUID = -4767277394199485045L;
    public List<DataBlockDetails> listOfDataBlocks = new ArrayList();
    public double calculatedSamplingRate = Double.NaN;
    public int sampleCount = 0;

    public static double getStartTimeMsFromDataBlockList(List<DataBlockDetails> list, boolean z) {
        if (list == null || list.size() <= 0) {
            return Double.MAX_VALUE;
        }
        DataBlockDetails dataBlockDetails = list.get(0);
        if (z) {
            return dataBlockDetails.getTimeDetailsRwc().getStartTimeMs();
        }
        return dataBlockDetails.getTimeDetailsUcClock().getStartTimeMs();
    }

    public static double getEndTimeMsFromDataBlockList(List<DataBlockDetails> list, boolean z) {
        if (list == null || list.size() <= 0) {
            return Double.MIN_VALUE;
        }
        DataBlockDetails dataBlockDetails = list.get(list.size() - 1);
        if (z) {
            return dataBlockDetails.getTimeDetailsRwc().getEndTimeMs();
        }
        return dataBlockDetails.getTimeDetailsUcClock().getEndTimeMs();
    }

    public static void printListOfDataBlockDetails(List<DataBlockDetails> list, boolean z) {
        String str;
        int i = 1;
        double endTimeRwcMs = Double.NaN;
        for (DataBlockDetails dataBlockDetails : list) {
            double startTimeRwcMs = !Double.isNaN(endTimeRwcMs) ? dataBlockDetails.getStartTimeRwcMs() - endTimeRwcMs : Double.NaN;
            PrintStream printStream = System.out;
            int i2 = i + 1;
            String strGenerateDebugStr = dataBlockDetails.generateDebugStr();
            if (z) {
                str = ", TimeDiff=" + (Double.isNaN(startTimeRwcMs) ? "N/A" : startTimeRwcMs + Configuration.CHANNEL_UNITS.MILLISECONDS);
            } else {
                str = "";
            }
            printStream.println(i + ") " + strGenerateDebugStr + str);
            i = i2;
            endTimeRwcMs = dataBlockDetails.getEndTimeRwcMs();
        }
    }

    public double getCalculatedSamplingRate() {
        return this.calculatedSamplingRate;
    }

    public List<DataBlockDetails> getListOfDataBlocks() {
        return this.listOfDataBlocks;
    }

    public int getSampleCount() {
        return this.sampleCount;
    }

    public void updateSampleCount() {
        this.sampleCount = 0;
        Iterator<DataBlockDetails> it2 = this.listOfDataBlocks.iterator();
        while (it2.hasNext()) {
            this.sampleCount += it2.next().getSampleCount();
        }
    }

    public double getStartTimeRwcMs() {
        return getStartTimeMsFromDataBlockList(this.listOfDataBlocks, true);
    }

    public double getEndTimeRwcMs() {
        return getEndTimeMsFromDataBlockList(this.listOfDataBlocks, true);
    }

    public double getStartTimeUcClockMs() {
        return getStartTimeMsFromDataBlockList(this.listOfDataBlocks, false);
    }

    public double getEndTimeUcClockMs() {
        return getEndTimeMsFromDataBlockList(this.listOfDataBlocks, false);
    }

    public void updateCalculatedSamplingRate() {
        if (getSampleCount() > 1) {
            this.calculatedSamplingRate = UtilVerisenseDriver.calcSamplingRate(getStartTimeRwcMs(), getEndTimeRwcMs(), getSampleCount());
        }
    }

    public String generateReport() {
        return "DataBlocks=" + getDataBlockCount() + ", Samples=" + getSampleCount() + ", Timimg [Start=" + UtilVerisenseDriver.convertMilliSecondsToCsvHeaderFormat((long) getStartTimeRwcMs()) + ", End=" + UtilVerisenseDriver.convertMilliSecondsToCsvHeaderFormat((long) getEndTimeRwcMs()) + ", Duration=" + UtilVerisenseDriver.convertSecondsToHHmmssSSS((getEndTimeRwcMs() - getStartTimeRwcMs()) / 1000.0d) + "]";
    }

    public int getDataBlockCount() {
        return this.listOfDataBlocks.size();
    }

    public void addDataBlock(DataBlockDetails dataBlockDetails) {
        this.listOfDataBlocks.add(dataBlockDetails);
    }

    public void addDataBlocks(List<DataBlockDetails> list) {
        this.listOfDataBlocks.addAll(list);
    }

    public void clearOjcArray() {
        Iterator<DataBlockDetails> it2 = this.listOfDataBlocks.iterator();
        while (it2.hasNext()) {
            it2.next().setupOjcArray(0);
        }
    }

    public DataSegmentDetails deepClone() throws IOException {
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            new ObjectOutputStream(byteArrayOutputStream).writeObject(this);
            return (DataSegmentDetails) new ObjectInputStream(new ByteArrayInputStream(byteArrayOutputStream.toByteArray())).readObject();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } catch (ClassNotFoundException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public boolean isResultOfSplitAtMiddayOrMidnight() {
        if (this.listOfDataBlocks.size() > 0) {
            return this.listOfDataBlocks.get(0).isResultOfSplitAtMiddayOrMidnight();
        }
        return false;
    }
}
