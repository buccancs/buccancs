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
import java.util.List;

import pl.flex_it.androidplot.XYSeriesShimmer;

public class PlotManagerAndroid extends AbstractPlotManager {

    public static List<XYSeriesShimmer> mListofSeries = new ArrayList<XYSeriesShimmer>();
    int mNumberOfRowPropertiestoCheck = 3;
    XYPlot mDynamicPlot = null;
    int mXAxisLimit = 500;
    boolean mClearGraphatLimit = false;

    public PlotManagerAndroid() {

    }

    public PlotManagerAndroid(boolean clearGraphatLimit) {
        mClearGraphatLimit = clearGraphatLimit;
    }

    public PlotManagerAndroid(int limit) {
        mXAxisLimit = limit;
    }

    public PlotManagerAndroid(int limit, boolean clearGraphatLimit) {
        mClearGraphatLimit = clearGraphatLimit;
        mXAxisLimit = limit;
    }

    public PlotManagerAndroid(List<String[]> propertiestoPlot, int limit, XYPlot chart) throws Exception {
        mXAxisLimit = limit;
        for (int i = 0; i < propertiestoPlot.size(); i++) {
            addSignal(propertiestoPlot.get(i), chart);
        }
    }

    public PlotManagerAndroid(List<String[]> propertiestoPlot, int limit, XYPlot chart, boolean clearGraphatLimit) throws Exception {
        mXAxisLimit = limit;
        mClearGraphatLimit = clearGraphatLimit;
        for (int i = 0; i < propertiestoPlot.size(); i++) {
            addSignal(propertiestoPlot.get(i), chart);
        }
    }

    public PlotManagerAndroid(List<String[]> propertiestoPlot, List<int[]> listofColors, int limit, XYPlot chart) throws Exception {
        mXAxisLimit = limit;
        for (int i = 0; i < propertiestoPlot.size(); i++) {
            addSignal(propertiestoPlot.get(i), chart, listofColors.get(i));
        }
    }

    public PlotManagerAndroid(List<String[]> propertiestoPlot, List<int[]> listofColors, int limit, XYPlot chart, boolean clearGraphatLimit) throws Exception {
        mXAxisLimit = limit;
        mClearGraphatLimit = clearGraphatLimit;
        for (int i = 0; i < propertiestoPlot.size(); i++) {
            addSignal(propertiestoPlot.get(i), chart, listofColors.get(i));
        }
    }

    public void setXAxisLimit(int limit, boolean clearGraph) {
        mXAxisLimit = limit;
        for (XYSeriesShimmer xys : mListofSeries) {
            xys.setXAxisLimit(mXAxisLimit);
            xys.setClearGraphatLimit(clearGraph);
        }
    }

    @Override
    public void setTraceLineStyleAll(PLOT_LINE_STYLE plot_line_style) {

    }

    public void updateDynamicPlot(XYPlot chart) {
        mDynamicPlot = chart;
        for (int i = 0; i < mListofSeries.size(); i++) {
            int[] colorrgbaray = mListOfTraceColorsCurrentlyUsed.get(i);
            LineAndPointFormatter lapf = new LineAndPointFormatter(colorrgbaray[0], colorrgbaray[1], colorrgbaray[2]);
            lapf = new LineAndPointFormatter(Color.rgb(colorrgbaray[0], colorrgbaray[1], colorrgbaray[2]), null, null);
            mDynamicPlot.addSeries(mListofSeries.get(i), lapf);
        }
    }

    public void addSignal(String[] signal, XYPlot chart) throws Exception {
        if (!checkIfPropertyExist(signal)) {
            int index = mListofSeries.size();
            String name = signal[0] + " " + signal[1] + " " + signal[2] + " " + signal[3];
            List<Number> d = new ArrayList<Number>();
            XYSeriesShimmer xys = new XYSeriesShimmer(d);
            xys.setTitle(name);
            xys.setXAxisLimit(mXAxisLimit);
            xys.setClearGraphatLimit(mClearGraphatLimit);
            mListofSeries.add(xys);
            super.addSignalGenerateRandomColor(signal);
            int[] colorrgbaray = mListOfTraceColorsCurrentlyUsed.get(index);
            LineAndPointFormatter lapf = new LineAndPointFormatter(colorrgbaray[0], colorrgbaray[1], colorrgbaray[2]);
            lapf = new LineAndPointFormatter(Color.rgb(colorrgbaray[0], colorrgbaray[1], colorrgbaray[2]), null, null);
            chart.addSeries(mListofSeries.get(index), lapf);
            mDynamicPlot = chart;

            for (XYSeriesShimmer xy : mListofSeries) {
                xy.clearData();
            }
        } else {
            throw new Exception("Error: " + joinChannelStringArray(signal) + " Signal/Property already exist.");
        }
    }


    public void addSignal(String[] signal, XYPlot chart, int[] color) throws Exception {
        if (!checkIfPropertyExist(signal)) {

            String name = joinChannelStringArray(signal);
            List<Number> d = new ArrayList<Number>();
            int index = mListofSeries.size();
            XYSeriesShimmer xys = new XYSeriesShimmer(d);
            xys.setTitle(name);
            xys.setXAxisLimit(mXAxisLimit);
            mListofSeries.add(xys);
            super.addSignalandColor(signal, color);
            int[] colorrgbaray = mListOfTraceColorsCurrentlyUsed.get(index);
            LineAndPointFormatter lapf = new LineAndPointFormatter(colorrgbaray[0], colorrgbaray[1], colorrgbaray[2]);
            lapf = new LineAndPointFormatter(Color.rgb(colorrgbaray[0], colorrgbaray[1], colorrgbaray[2]), null, null);
            chart.addSeries(mListofSeries.get(index), lapf);
            mDynamicPlot = chart;

            for (XYSeriesShimmer xy : mListofSeries) {
                xy.clearData();
            }
        } else {
            throw new Exception("Error: " + joinChannelStringArray(signal) + " Signal/Property already exist.");
        }
    }

    public void removeAllSignals() {
        super.removeAllSignals();
        if (mDynamicPlot != null) {
            mDynamicPlot.clear();
        }
        mListofSeries.clear();
    }


    public void removeSignal(String[] signal) {
        for (int i = 0; i < mListofPropertiestoPlot.size(); i++) {
            String[] prop = mListofPropertiestoPlot.get(i);
            boolean found = true;
            for (int p = 0; p < mNumberOfRowPropertiestoCheck; p++) {
                if (prop[p].equals(signal[p])) {

                } else {
                    found = false;
                }
            }
            if (found) {
                mDynamicPlot.removeSeries(mListofSeries.get(i));
                mListofSeries.remove(i);
                super.removeSignal(i);
            }

        }
    }

    public void removeSignalFromDevice(String namedevice) {

        List<XYSeriesShimmer> tempListOfSeries = new ArrayList<XYSeriesShimmer>();
        ArrayList<String[]> propertiesToRemove = new ArrayList<String[]>();
        ArrayList<Integer> listOfIndex = new ArrayList<Integer>();
        ArrayList<int[]> tempListOfColors = new ArrayList<int[]>();
        for (int i = 0; i < mListofPropertiestoPlot.size(); i++) {
            String[] prop = mListofPropertiestoPlot.get(i);
            if (prop[0].equals(namedevice)) {
                listOfIndex.add(i);
                propertiesToRemove.add(prop);
                tempListOfSeries.add(mListofSeries.get(i));
                tempListOfColors.add(mListOfTraceColorsCurrentlyUsed.get(i));
            }
        }


        for (int j = listOfIndex.size() - 1; j >= 0; j--) {
            mDynamicPlot.removeSeries(mListofSeries.get(listOfIndex.get(j)));
        }
        mListofSeries.removeAll(tempListOfSeries);
        super.removeCollectionOfSignal(propertiesToRemove, tempListOfColors);

    }

    public void filterDataAndPlot(ObjectCluster ojc) throws Exception {

        for (int i = 0; i < mListofPropertiestoPlot.size(); i++) {
            String[] props = mListofPropertiestoPlot.get(i);
            if (ojc.getShimmerName().equals(props[0])) {

                FormatCluster f = ObjectCluster.returnFormatCluster(ojc.getCollectionOfFormatClusters(props[1]), props[2]);
                if (f != null) {
                    mListofSeries.get(i).addData(f.mData);
                } else {
                    throw new Exception("Signal not found");
                }
            }
        }
        if (mDynamicPlot != null) {
            mDynamicPlot.redraw();
        }
    }

    public void setLegendTextSize(float textSize) {
        mDynamicPlot.getLegendWidget().getTextPaint().setTextSize(textSize);
    }

    public void setLegendWidth(float width) {
        mDynamicPlot.getLegendWidget().setWidth(width);
    }

    public void setLegendHeight(float height) {
        mDynamicPlot.getLegendWidget().setHeight(height);
    }

    public void setLegendIconSize(float height, SizeLayoutType heightLayoutType, float width, SizeLayoutType widthLayoutType) {
        mDynamicPlot.getLegendWidget().setIconSizeMetrics(new SizeMetrics(height, heightLayoutType, width, widthLayoutType));
    }

}


/*package com.shimmerresearch.advance;

import info.monitorenter.gui.chart.ITrace2D;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import com.shimmerresearch.driver.FormatCluster;
import com.shimmerresearch.driver.ObjectCluster;

public class PlotManager extends AbstractPlotManager {
	
	int x = 0;
	
	
	public PlotManager(List<String[]> propertiestoPlot) {
		super(propertiestoPlot);
		x=0;
	}

	public PlotManager(List<String[]> propertiestoPlot,List<int[]> listofColors) {
		super(propertiestoPlot,listofColors);
		x=0;
	}
	
	public List<ITrace2D> FilterData(ObjectCluster ojc,List<ITrace2D> traces) {

		for (int i=0;i<mListofPropertiestoPlot.size();i++){
			String[] props = mListofPropertiestoPlot.get(i);
			if (ojc.mMyName.equals(props[0])){
				FormatCluster f = ObjectCluster.returnFormatCluster(ojc.mPropertyCluster.get(props[1]), props[2]);
				int [] colorrgbaray = mListofColors.get(i);
				Color color = new Color(colorrgbaray[0], colorrgbaray[1], colorrgbaray[2]);
				traces.get(i).setColor(color);
				String name = props[0]+ " " + props[1]+" " + props[2] + " " + props[3];
				traces.get(i).setName(name);
				traces.get(i).addPoint(x, f.mData);
			}
		}
		x++;
		return traces;
		
	}

}
*/
