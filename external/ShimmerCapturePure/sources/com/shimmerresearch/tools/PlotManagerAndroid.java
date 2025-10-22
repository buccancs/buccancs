package com.shimmerresearch.tools;

import android.graphics.Color;
import com.androidplot.ui.SizeLayoutType;
import com.androidplot.ui.SizeMetrics;
import com.androidplot.xy.LineAndPointFormatter;
import com.androidplot.xy.XYPlot;
import com.shimmerresearch.driver.FormatCluster;
import com.shimmerresearch.driver.ObjectCluster;
import com.shimmerresearch.guiUtilities.AbstractPlotManager;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import pl.flex_it.androidplot.XYSeriesShimmer;

/* loaded from: classes2.dex */
public class PlotManagerAndroid extends AbstractPlotManager {
    public static List<XYSeriesShimmer> mListofSeries = new ArrayList();
    boolean mClearGraphatLimit;
    XYPlot mDynamicPlot;
    int mNumberOfRowPropertiestoCheck;
    int mXAxisLimit;

    public PlotManagerAndroid() {
        this.mNumberOfRowPropertiestoCheck = 3;
        this.mDynamicPlot = null;
        this.mXAxisLimit = 500;
        this.mClearGraphatLimit = false;
    }

    public PlotManagerAndroid(boolean z) {
        this.mNumberOfRowPropertiestoCheck = 3;
        this.mDynamicPlot = null;
        this.mXAxisLimit = 500;
        this.mClearGraphatLimit = z;
    }

    public PlotManagerAndroid(int i) {
        this.mNumberOfRowPropertiestoCheck = 3;
        this.mDynamicPlot = null;
        this.mClearGraphatLimit = false;
        this.mXAxisLimit = i;
    }

    public PlotManagerAndroid(int i, boolean z) {
        this.mNumberOfRowPropertiestoCheck = 3;
        this.mDynamicPlot = null;
        this.mClearGraphatLimit = z;
        this.mXAxisLimit = i;
    }

    public PlotManagerAndroid(List<String[]> list, int i, XYPlot xYPlot) throws Exception {
        this.mNumberOfRowPropertiestoCheck = 3;
        this.mDynamicPlot = null;
        this.mClearGraphatLimit = false;
        this.mXAxisLimit = i;
        for (int i2 = 0; i2 < list.size(); i2++) {
            addSignal(list.get(i2), xYPlot);
        }
    }

    public PlotManagerAndroid(List<String[]> list, int i, XYPlot xYPlot, boolean z) throws Exception {
        this.mNumberOfRowPropertiestoCheck = 3;
        this.mDynamicPlot = null;
        this.mXAxisLimit = i;
        this.mClearGraphatLimit = z;
        for (int i2 = 0; i2 < list.size(); i2++) {
            addSignal(list.get(i2), xYPlot);
        }
    }

    public PlotManagerAndroid(List<String[]> list, List<int[]> list2, int i, XYPlot xYPlot) throws Exception {
        this.mNumberOfRowPropertiestoCheck = 3;
        this.mDynamicPlot = null;
        this.mClearGraphatLimit = false;
        this.mXAxisLimit = i;
        for (int i2 = 0; i2 < list.size(); i2++) {
            addSignal(list.get(i2), xYPlot, list2.get(i2));
        }
    }

    public PlotManagerAndroid(List<String[]> list, List<int[]> list2, int i, XYPlot xYPlot, boolean z) throws Exception {
        this.mNumberOfRowPropertiestoCheck = 3;
        this.mDynamicPlot = null;
        this.mXAxisLimit = i;
        this.mClearGraphatLimit = z;
        for (int i2 = 0; i2 < list.size(); i2++) {
            addSignal(list.get(i2), xYPlot, list2.get(i2));
        }
    }

    @Override // com.shimmerresearch.guiUtilities.AbstractPlotManager
    public void setTraceLineStyleAll(AbstractPlotManager.PLOT_LINE_STYLE plot_line_style) {
    }

    public void setXAxisLimit(int i, boolean z) {
        this.mXAxisLimit = i;
        for (XYSeriesShimmer xYSeriesShimmer : mListofSeries) {
            xYSeriesShimmer.setXAxisLimit(this.mXAxisLimit);
            xYSeriesShimmer.setClearGraphatLimit(z);
        }
    }

    public void updateDynamicPlot(XYPlot xYPlot) {
        this.mDynamicPlot = xYPlot;
        for (int i = 0; i < mListofSeries.size(); i++) {
            int[] iArr = this.mListOfTraceColorsCurrentlyUsed.get(i);
            new LineAndPointFormatter(Integer.valueOf(iArr[0]), Integer.valueOf(iArr[1]), Integer.valueOf(iArr[2]));
            this.mDynamicPlot.addSeries(mListofSeries.get(i), new LineAndPointFormatter(Integer.valueOf(Color.rgb(iArr[0], iArr[1], iArr[2])), null, null));
        }
    }

    public void addSignal(String[] strArr, XYPlot xYPlot) throws Exception {
        if (!checkIfPropertyExist(strArr)) {
            int size = mListofSeries.size();
            String str = strArr[0] + StringUtils.SPACE + strArr[1] + StringUtils.SPACE + strArr[2] + StringUtils.SPACE + strArr[3];
            XYSeriesShimmer xYSeriesShimmer = new XYSeriesShimmer(new ArrayList());
            xYSeriesShimmer.setTitle(str);
            xYSeriesShimmer.setXAxisLimit(this.mXAxisLimit);
            xYSeriesShimmer.setClearGraphatLimit(this.mClearGraphatLimit);
            mListofSeries.add(xYSeriesShimmer);
            super.addSignalGenerateRandomColor(strArr);
            int[] iArr = this.mListOfTraceColorsCurrentlyUsed.get(size);
            new LineAndPointFormatter(Integer.valueOf(iArr[0]), Integer.valueOf(iArr[1]), Integer.valueOf(iArr[2]));
            xYPlot.addSeries(mListofSeries.get(size), new LineAndPointFormatter(Integer.valueOf(Color.rgb(iArr[0], iArr[1], iArr[2])), null, null));
            this.mDynamicPlot = xYPlot;
            Iterator<XYSeriesShimmer> it2 = mListofSeries.iterator();
            while (it2.hasNext()) {
                it2.next().clearData();
            }
            return;
        }
        throw new Exception("Error: " + joinChannelStringArray(strArr) + " Signal/Property already exist.");
    }

    public void addSignal(String[] strArr, XYPlot xYPlot, int[] iArr) throws Exception {
        if (!checkIfPropertyExist(strArr)) {
            String strJoinChannelStringArray = joinChannelStringArray(strArr);
            ArrayList arrayList = new ArrayList();
            int size = mListofSeries.size();
            XYSeriesShimmer xYSeriesShimmer = new XYSeriesShimmer(arrayList);
            xYSeriesShimmer.setTitle(strJoinChannelStringArray);
            xYSeriesShimmer.setXAxisLimit(this.mXAxisLimit);
            mListofSeries.add(xYSeriesShimmer);
            super.addSignalandColor(strArr, iArr);
            int[] iArr2 = this.mListOfTraceColorsCurrentlyUsed.get(size);
            new LineAndPointFormatter(Integer.valueOf(iArr2[0]), Integer.valueOf(iArr2[1]), Integer.valueOf(iArr2[2]));
            xYPlot.addSeries(mListofSeries.get(size), new LineAndPointFormatter(Integer.valueOf(Color.rgb(iArr2[0], iArr2[1], iArr2[2])), null, null));
            this.mDynamicPlot = xYPlot;
            Iterator<XYSeriesShimmer> it2 = mListofSeries.iterator();
            while (it2.hasNext()) {
                it2.next().clearData();
            }
            return;
        }
        throw new Exception("Error: " + joinChannelStringArray(strArr) + " Signal/Property already exist.");
    }

    @Override // com.shimmerresearch.guiUtilities.AbstractPlotManager
    public void removeAllSignals() {
        super.removeAllSignals();
        XYPlot xYPlot = this.mDynamicPlot;
        if (xYPlot != null) {
            xYPlot.clear();
        }
        mListofSeries.clear();
    }

    public void removeSignal(String[] strArr) {
        for (int i = 0; i < this.mListofPropertiestoPlot.size(); i++) {
            String[] strArr2 = this.mListofPropertiestoPlot.get(i);
            boolean z = true;
            for (int i2 = 0; i2 < this.mNumberOfRowPropertiestoCheck; i2++) {
                if (!strArr2[i2].equals(strArr[i2])) {
                    z = false;
                }
            }
            if (z) {
                this.mDynamicPlot.removeSeries(mListofSeries.get(i));
                mListofSeries.remove(i);
                super.removeSignal(i);
            }
        }
    }

    public void removeSignalFromDevice(String str) {
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        ArrayList arrayList3 = new ArrayList();
        ArrayList arrayList4 = new ArrayList();
        for (int i = 0; i < this.mListofPropertiestoPlot.size(); i++) {
            String[] strArr = this.mListofPropertiestoPlot.get(i);
            if (strArr[0].equals(str)) {
                arrayList3.add(Integer.valueOf(i));
                arrayList2.add(strArr);
                arrayList.add(mListofSeries.get(i));
                arrayList4.add(this.mListOfTraceColorsCurrentlyUsed.get(i));
            }
        }
        for (int size = arrayList3.size() - 1; size >= 0; size--) {
            this.mDynamicPlot.removeSeries(mListofSeries.get(((Integer) arrayList3.get(size)).intValue()));
        }
        mListofSeries.removeAll(arrayList);
        super.removeCollectionOfSignal(arrayList2, arrayList4);
    }

    public void filterDataAndPlot(ObjectCluster objectCluster) throws Exception {
        for (int i = 0; i < this.mListofPropertiestoPlot.size(); i++) {
            String[] strArr = this.mListofPropertiestoPlot.get(i);
            if (objectCluster.getShimmerName().equals(strArr[0])) {
                FormatCluster formatClusterReturnFormatCluster = ObjectCluster.returnFormatCluster(objectCluster.getCollectionOfFormatClusters(strArr[1]), strArr[2]);
                if (formatClusterReturnFormatCluster != null) {
                    mListofSeries.get(i).addData(Double.valueOf(formatClusterReturnFormatCluster.mData));
                } else {
                    throw new Exception("Signal not found");
                }
            }
        }
        XYPlot xYPlot = this.mDynamicPlot;
        if (xYPlot != null) {
            xYPlot.redraw();
        }
    }

    public void setLegendTextSize(float f) {
        this.mDynamicPlot.getLegendWidget().getTextPaint().setTextSize(f);
    }

    public void setLegendWidth(float f) {
        this.mDynamicPlot.getLegendWidget().setWidth(f);
    }

    public void setLegendHeight(float f) {
        this.mDynamicPlot.getLegendWidget().setHeight(f);
    }

    public void setLegendIconSize(float f, SizeLayoutType sizeLayoutType, float f2, SizeLayoutType sizeLayoutType2) {
        this.mDynamicPlot.getLegendWidget().setIconSizeMetrics(new SizeMetrics(f, sizeLayoutType, f2, sizeLayoutType2));
    }
}
