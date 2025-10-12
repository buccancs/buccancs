package com.shimmerresearch.guiUtilities.plot;

import info.monitorenter.gui.chart.Chart2D;
import info.monitorenter.gui.chart.IAxis;
import info.monitorenter.gui.chart.IAxis.AxisTitle;
import info.monitorenter.gui.chart.IAxisLabelFormatter;
import info.monitorenter.gui.chart.IAxisScalePolicy;
import info.monitorenter.gui.chart.IRangePolicy;
import info.monitorenter.gui.chart.ITrace2D;
import info.monitorenter.gui.chart.ITracePainter;
import info.monitorenter.gui.chart.ITracePoint2D;
import info.monitorenter.gui.chart.axis.AAxis;
import info.monitorenter.gui.chart.axis.AxisLinear;
import info.monitorenter.gui.chart.axis.scalepolicy.AxisScalePolicyAutomaticBestFit;
import info.monitorenter.gui.chart.axis.scalepolicy.AxisScalePolicyManualTicks;
import info.monitorenter.gui.chart.labelformatters.ALabelFormatter;
import info.monitorenter.gui.chart.labelformatters.LabelFormatterAutoUnits;
import info.monitorenter.gui.chart.labelformatters.LabelFormatterDate;
import info.monitorenter.gui.chart.labelformatters.LabelFormatterNumber;
import info.monitorenter.gui.chart.labelformatters.LabelFormatterSimple;
import info.monitorenter.gui.chart.labelformatters.LabelFormatterUnit;
import info.monitorenter.gui.chart.rangepolicies.RangePolicyFixedViewport;
import info.monitorenter.gui.chart.rangepolicies.RangePolicyUnbounded;
import info.monitorenter.gui.chart.traces.Trace2DLtd;
import info.monitorenter.gui.chart.traces.painters.TracePainterDisc;
import info.monitorenter.gui.chart.traces.painters.TracePainterFill;
import info.monitorenter.gui.chart.traces.painters.TracePainterLine;
import info.monitorenter.gui.chart.traces.painters.TracePainterVerticalBar;
import info.monitorenter.util.Range;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;
import java.util.TimeZone;
import java.util.Timer;
import java.util.TimerTask;

import org.apache.commons.collections.buffer.CircularFifoBuffer;

import com.shimmerresearch.driver.FormatCluster;
import com.shimmerresearch.driver.ObjectCluster;
import com.shimmerresearch.driverUtilities.FftCalculateDetails;
import com.shimmerresearch.driverUtilities.UtilShimmer;
import com.shimmerresearch.driverUtilities.ChannelDetails.CHANNEL_AXES;
import com.shimmerresearch.guiUtilities.AbstractPlotManager;

public class BasicPlotManagerPC extends AbstractPlotManager {

    public static float DEFAULT_LINE_THICKNESS = 2;
    public List<ITrace2D> mListofTraces = Collections.synchronizedList(new ArrayList<ITrace2D>());
    public HashMap<String, CircularFifoBuffer> mMapOfCirculurBufferedTraceDataPoints = new HashMap<String, CircularFifoBuffer>();
    public HashMap<String, Integer> mMapofDefaultXAxisSizes = new HashMap<String, Integer>();
    public int mWindowSize = 0;
    public HashMap<String, Double> mMapofHalfWindowSize = new HashMap<String, Double>();
    public boolean mIsLegendLabelsPainted = true;
    public boolean mIsScaleLabelsPainted = true;
    public boolean mIsAxisLabelsPainted = true;
    public boolean mIsGridOn = false;
    public boolean mIsHRVisible = false;
    public boolean mEnablePCTS = true;
    public boolean mSetTraceName = true;
    public PlotCustomFeature pcf = null;
    public LinkedHashMap<String, FftCalculateDetails> mMapOfFftsToPlot = new LinkedHashMap<String, FftCalculateDetails>();
    public HashMap<String, Double> mMapOfLastDataPoints = new HashMap<String, Double>();
    protected String mEventMarkerCheck = "";
    protected double mCurrentXValue = 0;
    protected boolean mIsPlotPaused = false;
    protected boolean isFirstPointOnFillTrace = true;
    protected boolean isSingleEventMarkerTest = true;
    transient protected Timer mTimerCalculateFft;
    int mXAxisLimit = 500;
    double mXAxisTimeDuration = 5;
    int numberOfRowPropertiestoCheck = 2;
    boolean mClearGraphatLimit = false;
    Chart2D mChart = null;
    private boolean mIsDebugMode = false;
    private boolean mIsTraceDataBuffered = false;
    private String mTitle = "";
    private AAxis<IAxisScalePolicy> yAxisRight;
    private IAxis<?> xAxis;
    private CHANNEL_AXES mXAisType = CHANNEL_AXES.TIME;
    private int mTimerPeriodCalculateFft = 1000;
    private int mTimerDelayCalculateFft = 1000;
    private boolean mIsFftShowingDc = true;
    private int mFftOverlapPercent = 0;
    private TimeZone timeZone = Calendar.getInstance().getTimeZone();

    private UtilShimmer utilShimmer = new UtilShimmer(this.getClass().getSimpleName(), true);

        public BasicPlotManagerPC() {
        mMapofXAxisGeneratedValue.clear();
        initializeAxesForTimeBig();
    }


        public BasicPlotManagerPC(List<String[]> propertiestoPlot, int limit, Chart2D chart) throws Exception {
        mXAxisLimit = limit;
        mChart = chart;
        mChart.setCursor(new Cursor(Cursor.DEFAULT_CURSOR)); // Dec 2016: RM put this in as the default cursor for jchart2d is cross-hair
        mChart.getAxisY().setFormatter(new LabelFormatterNumber());

        if (propertiestoPlot != null) {
            for (int i = 0; i < propertiestoPlot.size(); i++) {
                addSignal(propertiestoPlot.get(i), chart);
            }
        }
        initializeAxesForTimeBig();

    }

        public ITrace2D addSignal(String[] signal, Chart2D chart) throws Exception {
        return this.addSignal(signal, chart, mXAxisLimit);
    }


        public ITrace2D addSignalAsBarPlot(String[] signal, Chart2D chart, int windowSize) throws Exception {
        return this.addSignalAsBarPlot(signal, chart, mXAxisLimit, windowSize);
    }

        public ITrace2D addSignal(String[] signal, Chart2D chart, boolean usePaintIndividualPointsOnly) throws Exception {
        ITrace2D trace = this.addSignal(signal, chart);

        if (usePaintIndividualPointsOnly) {
            trace.setTracePainter(new TracePainterDisc(4));
        }

        return trace;
    }

        public ITrace2D addSignalAsBarPlot(String[] signal, Chart2D chart, int plotMaxSize, int windowSize) throws Exception {
        ITrace2D trace;
        if (!checkIfPropertyExist(signal)) {
            trace = addBarTrace(chart, plotMaxSize);
            String name = addSignalCommon(chart, trace, signal, plotMaxSize);

            if (windowSize != 0) {
                double mhalf = ((double) windowSize) / 2.0;
                mMapofHalfWindowSize.put(name, mhalf);
            }
        } else {
            throw new Exception("Error: " + joinChannelStringArray(signal) + " Signal/Property already exist.");
        }
        return trace;
    }

        public ITrace2D addSignal(String[] signal, Chart2D chart, int plotMaxSize) throws Exception {
        ITrace2D trace;
        if (!checkIfPropertyExist(signal)) {

            if (mDefaultLineStyle == PLOT_LINE_STYLE.CONTINUOUS
                    || mDefaultLineStyle == PLOT_LINE_STYLE.INDIVIDUAL_POINTS) {
                trace = addNormalTraceLeft(chart, plotMaxSize);

                if (mDefaultLineStyle == PLOT_LINE_STYLE.INDIVIDUAL_POINTS) {
                    trace.setTracePainter(new TracePainterDisc(4));
                }
            } else if (mDefaultLineStyle == PLOT_LINE_STYLE.BAR) {
                trace = addBarTrace(chart, plotMaxSize);
            } else {
                trace = addNormalTraceLeft(chart, plotMaxSize);
            }
            addSignalCommon(chart, trace, signal, plotMaxSize);
            setTraceSize(trace, plotMaxSize);
        } else {
            throw new Exception("Error: " + joinChannelStringArray(signal) + " Signal/Property already exist.");
        }


        return trace;
    }

        public ITrace2D addSignalUsingRightYAxis(String[] signal, Chart2D chart, int plotMaxSize, String title, int minRange, int maxRange) throws Exception {
        ITrace2D trace;
        if (!checkIfPropertyExist(signal)) {
            yAxisRight = createRightYAxis(chart);
            chart.setAxisYRight(yAxisRight, 0);
            trace = addNormalTraceRight(chart, plotMaxSize);
            addSignalCommon(chart, trace, signal, plotMaxSize);
            setTraceSize(trace, plotMaxSize);
        } else {
            throw new Exception("Error: " + joinChannelStringArray(signal) + " Signal/Property already exist.");
        }
        return trace;
    }

    private AAxis<IAxisScalePolicy> createRightYAxis(Chart2D chart) {
        AAxis<IAxisScalePolicy> yAxisRight = new AxisLinear<IAxisScalePolicy>();
        yAxisRight.setAxisScalePolicy(new AxisScalePolicyAutomaticBestFit());
        yAxisRight.setFormatter(new LabelFormatterNumber());
        yAxisRight.setPaintGrid(false);

        return yAxisRight;
    }

    private String addSignalCommon(Chart2D chart, ITrace2D trace, String[] signal, int plotMaxSize) {
        mListofTraces.add(trace);
        super.addSignalUseDefaultColors(signal);
        int i = mListOfTraceColorsCurrentlyUsed.size() - 1;
        int[] colorrgbaray = mListOfTraceColorsCurrentlyUsed.get(i);
        Color color = new Color(colorrgbaray[0], colorrgbaray[1], colorrgbaray[2]);
        mListofTraces.get(i).setColor(color);
        String traceName = joinChannelStringArray(signal);
        if (mSetTraceName) {
            mListofTraces.get(i).setName(traceName);
        } else {
            mListofTraces.get(i).setName("");
        }
        mChart = chart;
        mMapofDefaultXAxisSizes.put(traceName, plotMaxSize);

        if (isXAxisFrequency()) {
            FftCalculateDetails fftCalculateDetails = new FftCalculateDetails(signal[0], signal);
            fftCalculateDetails.setFftOverlapPercent(mFftOverlapPercent);
            mMapOfFftsToPlot.put(traceName, fftCalculateDetails);
        }

        return traceName;
    }

    private ITrace2D addNormalTraceLeft(Chart2D chart, int plotMaxSize) {
        ITrace2D trace = createNormalTrace(plotMaxSize);
        chart.addTrace(trace);
        return trace;
    }

    private ITrace2D addNormalTraceRight(Chart2D chart, int plotMaxSize) {
        ITrace2D trace = createNormalTrace(plotMaxSize);
        chart.addTrace(trace, chart.getAxisX(), yAxisRight);
        return trace;
    }

    private ITrace2D addBarTrace(Chart2D chart, int plotMaxSize) {
        ITrace2D trace = createBarTrace(chart, plotMaxSize);
        chart.addTrace(trace);
        return trace;
    }

    private ITrace2D createNormalTrace(int plotMaxSize) {
        Trace2DLtd trace = new Trace2DLtd(plotMaxSize);
        BasicStroke stroke = ((BasicStroke) trace.getStroke());
        BasicStroke newStroke = new BasicStroke(DEFAULT_LINE_THICKNESS, stroke.getEndCap(), stroke.getLineJoin(), stroke.getMiterLimit(), stroke.getDashArray(), stroke.getDashPhase());
        trace.setStroke(newStroke);
        return trace;
    }

    private ITrace2D createBarTrace(Chart2D chart, int plotMaxSize) {
        ITrace2D trace = new Trace2DLtd(plotMaxSize);
        trace.setTracePainter(new TracePainterVerticalBar(chart));
        return trace;
    }

        private ITrace2D addSignalToExistingChartInternal(String[] signal, int plotMaxSize, Color color) throws Exception {
        if (!checkIfPropertyExist(signal)) {
            ITrace2D trace = new Trace2DLtd(plotMaxSize);
            mChart.addTrace(trace);

            mListofTraces.add(trace);
            super.addSignalGenerateRandomColor(signal);
            int i = mListOfTraceColorsCurrentlyUsed.size() - 1;
            int[] colorrgbaray = mListOfTraceColorsCurrentlyUsed.get(i);
            mListofTraces.get(i).setColor(color);
            String name = joinChannelStringArray(signal);
            if (mSetTraceName) {
                mListofTraces.get(i).setName(name);
            } else {
                mListofTraces.get(i).setName("");
            }
            return trace;
        } else {
            throw new Exception("Error: " + joinChannelStringArray(signal) + " Signal/Property already exist.");
        }
    }

    public void addTrace2D(ITrace2D trace, String[] signal, int plotMaxSize) {
        mChart.addTrace(trace);
        mListofTraces.add(trace);
        setTraceSize(trace, plotMaxSize);
        String name = joinChannelStringArray(signal);
        mMapofDefaultXAxisSizes.put(name, plotMaxSize);
        super.addSignal(signal);
    }

    public Chart2D getChart() {
        return mChart;
    }


        public void removeAllSignals() {
        mCurrentXValue = 0;
        super.removeAllSignals();
        if (mChart != null) {
            try {
                mChart.removeAllTraces();
                mChart.removeAll();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        mListofTraces.clear();
        mMapofXAxisGeneratedValue.clear();
        mMapofDefaultXAxisSizes.clear();
        mMapOfFftsToPlot.clear();
        mMapOfLastDataPoints.clear();
    }

        private void removeSignalInternal(String[] signal) {
        synchronized (mListofPropertiestoPlot) {
            Iterator<String[]> entries = mListofPropertiestoPlot.iterator();
            int i = 0;
            while (entries.hasNext()) {
                String[] prop = entries.next();

                boolean found = true;
                for (int p = 0; p < numberOfRowPropertiestoCheck; p++) {
                    if (!prop[p].equals(signal[p])) {
                        found = false;
                        break;
                    }
                }
                if (found) {
                    String traceName = joinChannelStringArray(signal);

                    removeSignalCommon(traceName);

                    mChart.removeTrace(mListofTraces.get(i));
                    mListofTraces.remove(i);
                    super.removeSignal(i);
                }
                i++;
            }
        }
    }

    private void removeSignalCommon(String traceName) {
        mMapOfFftsToPlot.remove(traceName);
        mMapOfLastDataPoints.remove(traceName);
    }

        public void removeSignal(String[] signal) {
        synchronized (mListofPropertiestoPlot) {
            for (int i = 0; i < mListofPropertiestoPlot.size(); i++) {
                String[] prop = mListofPropertiestoPlot.get(i);
                boolean found = true;
                for (int p = 0; p < numberOfRowPropertiestoCheck; p++) {
                    if (!prop[p].equals(signal[p])) {
                        found = false;
                        break;
                    }
                }
                if (found) {
                    String traceName = joinChannelStringArray(signal);
                    mMapofDefaultXAxisSizes.remove(traceName);
                    mListofTraces.get(i).removeAllPoints(); // added this line for ConsensysGQ as we keep hold the trace for the single HR and GSR plot

                    removeSignalCommon(traceName);

                    mChart.removeTrace(mListofTraces.get(i));
                    mListofTraces.remove(i);
                    super.removeSignal(i);
                }
            }
        }
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public void setYAxisLabel(String label) {
        setYAxisLabel(label, null);
    }

    public void setYAxisLabel(String label, Font font) {
        IAxis<?> y = mChart.getAxisY();
        AxisTitle axisTitle = new AxisTitle(label);
        if (font != null) {
            axisTitle.setTitleFont(font);
        }
        y.setAxisTitle(axisTitle);
    }

    public void setXAxisLabel(String label) {
        setXAxisLabel(label, null);
    }

    public void setXAxisLabel(String label, Font font) {
        IAxis<?> x = mChart.getAxisX();
        AxisTitle axisTitle = new AxisTitle(label);
        if (font != null) {
            axisTitle.setTitleFont(font);
        }
        x.setAxisTitle(axisTitle);
    }

    public void setXAxisRange(double minX, double maxY) {
        IAxis<?> x = mChart.getAxisX();
        x.setRangePolicy(new RangePolicyFixedViewport(new Range(minX, maxY)));
    }

    public void setXAxisRangeBasedOnXDuration() {
        setXAxisRange(mCurrentXValue - (mXAxisTimeDuration * 1000), mCurrentXValue);
    }

        public void setXAxisRangeBasedOnXDurationSubtractSingleSamplingRate(double samplingRate) {
        double minTime = mCurrentXValue - (mXAxisTimeDuration * 1000);
        double samplingDurationInMs = (1 / samplingRate) * 1000;
        minTime = minTime + samplingDurationInMs;
        setXAxisRange(minTime, mCurrentXValue);
    }

    public void setYAxisRange(double miny, double maxy) {
        IAxis<?> yAxisLeft = mChart.getAxisY();
        yAxisLeft.setRangePolicy(new RangePolicyFixedViewport(new Range(miny, maxy)));
    }

    public void setYAxisMajorTickSpacing(double tickSpacing) {
        try {
            IAxis<IAxisScalePolicy> yAxisLeft = (IAxis<IAxisScalePolicy>) mChart.getAxisY();
            yAxisLeft.setAxisScalePolicy(new AxisScalePolicyManualTicks());
            yAxisLeft.setMajorTickSpacing(tickSpacing);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setYAxisMinorTickSpacing(double tickSpacing) {
        try {
            IAxis<IAxisScalePolicy> yAxisLeft = (IAxis<IAxisScalePolicy>) mChart.getAxisY();
            yAxisLeft.setAxisScalePolicy(new AxisScalePolicyManualTicks());
            yAxisLeft.setMinorTickSpacing(tickSpacing);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setYAxisTickSize(double miny, double maxy) {
        IAxis<?> yAxisLeft = mChart.getAxisY();
        yAxisLeft.setRangePolicy(new RangePolicyFixedViewport(new Range(miny, maxy)));
    }

        public int getXAxisLimit() {
        return mXAxisLimit;
    }

        public void setXAxisLimit(int xAxisLimit) {
        this.mXAxisLimit = xAxisLimit;
    }

    public void initializeAxes(int pxWidth) {
        if (isXAxisTime()) {
            if (pxWidth < 300) {
                initializeAxesForTimeSmall();
            } else if (pxWidth < 600) {
                initializeAxesForTimeMedium();
            } else {
                initializeAxesForTimeBig();
            }
        } else if (isXAxisFrequency()) {
            initializeAxesAutoUnits();
            setXAxisLabel("Freq (Hz)", null);
        } else if (isXAxisValue()) {
            initializeAxesAutoUnits();
        }
    }

    public void initializeAxesForTimeBig() {
        initializeAxesForTime("HH:mm:ss");
    }

    public void initializeAxesForTimeMedium() {
        initializeAxesForTime("mm:ss");
    }

    public void initializeAxesForTimeSmall() {
        initializeAxesForTime("ss");
    }

    public void initializeAxesAutoUnits() {
        initializeAxesCommon(new LabelFormatterAutoUnits());
    }

    private void initializeAxesForTime(String format) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        simpleDateFormat.setTimeZone(timeZone);
        initializeAxesCommon(new LabelFormatterDate(simpleDateFormat));
    }

    private void initializeAxesCommon(IAxisLabelFormatter xAxisLblFormatter) {
        if (mEnablePCTS && mChart != null) {
            xAxis = mChart.getAxisX();
            xAxis.setFormatter(xAxisLblFormatter);

//
//
//

        }
    }

    public void setTimeZone(TimeZone timeZone) {
        this.timeZone = timeZone;
    }

    public void clearTimeZone() {
        this.timeZone = TimeZone.getTimeZone("GMT");
    }

    public void changeTraceColor(String traceName, int[] colorArray) {
        int index = getTraceIndexFromName(traceName);
        if (index != -1) {
            mListOfTraceColorsCurrentlyUsed.set(index, colorArray);
            mListofTraces.get(index).setColor(new Color(colorArray[0], colorArray[1], colorArray[2]));
        }
    }

    public int getIndex(String name) {
        int index = 0;
        synchronized (mListofPropertiestoPlot) {
            Iterator<String[]> entries = mListofPropertiestoPlot.iterator();
            while (entries.hasNext()) {
                String n = joinChannelStringArray(entries.next());
                if (n.equals(name)) {
                    return index;
                }
                index++;
            }
        }
        return -1;
    }

    private int getTraceIndexFromName(String traceName) {
        synchronized (mListofTraces) {
            Iterator<ITrace2D> entries = mListofTraces.iterator();
            int i = 0;
            while (entries.hasNext()) {
                ITrace2D trace = entries.next();
                if (trace != null) {
                    if (trace.getName().equals(traceName)) {
                        return i;
                    }
                }
                i++;
            }
            return -1;
        }
    }

    public ITrace2D getTraceFromName(String traceName) {
        synchronized (mListofTraces) {
            Iterator<ITrace2D> entries = mListofTraces.iterator();
            while (entries.hasNext()) {
                ITrace2D trace = entries.next();
                if (trace != null) {
                    if (trace.getName().equals(traceName)) {
                        return trace;
                    }
                }
            }
            return null;
        }
    }

    public void changeTraceColor(int index, int[] colorArray) {
        mListOfTraceColorsCurrentlyUsed.set(index, colorArray);
        mListofTraces.get(index).setColor(new Color(colorArray[0], colorArray[1], colorArray[2]));
    }

    public void changeAllTraceColor(int[] colorArray) {
        synchronized (mListOfTraceColorsCurrentlyUsed) {
            Iterator<int[]> entries = mListOfTraceColorsCurrentlyUsed.iterator();
            while (entries.hasNext()) {
                int[] i = entries.next();
                i = colorArray;
            }
        }

        synchronized (mListofTraces) {
            Iterator<ITrace2D> entries = mListofTraces.iterator();
            while (entries.hasNext()) {
                ITrace2D trace = entries.next();
                if (trace != null) {
                    trace.setColor(new Color(colorArray[0], colorArray[1], colorArray[2]));
                }
            }
        }
    }

    public Color getTraceColour(String traceName) {
        synchronized (mListofTraces) {
            Iterator<ITrace2D> entries = mListofTraces.iterator();
            while (entries.hasNext()) {
                ITrace2D trace = entries.next();
                if (trace != null) {
                    if (trace.getName().equals(traceName)) {
                        return trace.getColor();
                    }
                }
            }
            return Color.white;
        }
    }

    public void setTraceThickness(String traceName, float thickness) {
        synchronized (mListofTraces) {
            Iterator<ITrace2D> entries = mListofTraces.iterator();
            while (entries.hasNext()) {
                ITrace2D trace = entries.next();
                if (trace != null) {
                    if (trace.getName().equals(traceName)) {
                        BasicStroke stroke = ((BasicStroke) trace.getStroke());
                        BasicStroke newstroke = new BasicStroke(thickness, stroke.getEndCap(), stroke.getLineJoin(), stroke.getMiterLimit(), stroke.getDashArray(), stroke.getDashPhase());
                        trace.setStroke(newstroke);
                    }
                }
            }
        }
    }

    public void setAllTraceThickness(float thickness) {
        synchronized (mListofTraces) {
            Iterator<ITrace2D> entries = mListofTraces.iterator();
            while (entries.hasNext()) {
                ITrace2D trace = entries.next();
                if (trace != null) {
                    BasicStroke stroke = ((BasicStroke) trace.getStroke());
                    BasicStroke newstroke = new BasicStroke(thickness, stroke.getEndCap(), stroke.getLineJoin(), stroke.getMiterLimit(), stroke.getDashArray(), stroke.getDashPhase());
                    trace.setStroke(newstroke);
                }
            }
        }
    }

    public void increaseAllTraceThickness() {
        synchronized (mListofTraces) {
            Iterator<ITrace2D> entries = mListofTraces.iterator();
            while (entries.hasNext()) {
                ITrace2D trace = entries.next();
                if (trace != null) {
                    BasicStroke stroke = ((BasicStroke) trace.getStroke());
                    BasicStroke newstroke = new BasicStroke(stroke.getLineWidth() + 1, stroke.getEndCap(), stroke.getLineJoin(), stroke.getMiterLimit(), stroke.getDashArray(), stroke.getDashPhase());
                    trace.setStroke(newstroke);
                }
            }
        }
    }

    public void reduceAllTraceThickness() {
        synchronized (mListofTraces) {
            Iterator<ITrace2D> entries = mListofTraces.iterator();
            while (entries.hasNext()) {
                ITrace2D trace = entries.next();
                if (trace != null) {
                    BasicStroke stroke = ((BasicStroke) trace.getStroke());
                    if (stroke.getLineWidth() >= 1) {
                        BasicStroke newstroke = new BasicStroke(stroke.getLineWidth() - 1, stroke.getEndCap(), stroke.getLineJoin(), stroke.getMiterLimit(), stroke.getDashArray(), stroke.getDashPhase());
                        trace.setStroke(newstroke);
                    }
                }
            }
        }
    }

    public float getTraceThickness(String traceName) {
        synchronized (mListofTraces) {
            Iterator<ITrace2D> entries = mListofTraces.iterator();
            while (entries.hasNext()) {
                ITrace2D trace = entries.next();
                if (trace != null) {
                    if (trace.getName().equals(traceName)) {
                        BasicStroke stroke = ((BasicStroke) trace.getStroke());
                        return stroke.getLineWidth();
                    }
                }
            }
            return -1;
        }
    }

    @Override
    public void setTraceLineStyleAll(PLOT_LINE_STYLE lineStyle) {
        mDefaultLineStyle = lineStyle;
        synchronized (mListofTraces) {
            Iterator<ITrace2D> entries = mListofTraces.iterator();
            while (entries.hasNext()) {
                ITrace2D trace = entries.next();
                if (trace != null) {
                    setTraceLineStyle(trace, mDefaultLineStyle);
                }
            }
        }
    }

//
//
//

    public void setTraceLineStyle(String traceName, PLOT_LINE_STYLE plotLineStyle) {
        ITrace2D trace = getTraceFromName(traceName);
        if (trace != null) {
            setTraceLineStyle(trace, plotLineStyle);
        }
    }

    public void setTraceLineStyle(ITrace2D trace, PLOT_LINE_STYLE selectedLineStyle) {
        trace.setTracePainter(new TracePainterLine());
        trace.setStroke(new BasicStroke());

        if (selectedLineStyle == PLOT_LINE_STYLE.CONTINUOUS
                || selectedLineStyle == PLOT_LINE_STYLE.INDIVIDUAL_POINTS
                || selectedLineStyle == PLOT_LINE_STYLE.DASHED
                || selectedLineStyle == PLOT_LINE_STYLE.DOTTED
                || selectedLineStyle == PLOT_LINE_STYLE.INDIVIDUAL_POINTS) {
            BasicStroke strokeOld = ((BasicStroke) trace.getStroke());
            BasicStroke strokeNew = null;

            if (selectedLineStyle == PLOT_LINE_STYLE.CONTINUOUS
                    || selectedLineStyle == PLOT_LINE_STYLE.INDIVIDUAL_POINTS) {
                strokeNew = new BasicStroke(
                        DEFAULT_LINE_THICKNESS,
                        strokeOld.getEndCap(),
                        strokeOld.getLineJoin(),
                        strokeOld.getMiterLimit(),
                        strokeOld.getDashArray(),
                        strokeOld.getDashPhase());
                trace.setStroke(strokeNew);

                if (selectedLineStyle == PLOT_LINE_STYLE.INDIVIDUAL_POINTS) {
                    trace.setTracePainter(new TracePainterDisc(4));
                }
            } else if (selectedLineStyle == PLOT_LINE_STYLE.DASHED) {
                float dash1[] = {10.0f};
                strokeNew = new BasicStroke(strokeOld.getLineWidth(),
                        BasicStroke.CAP_BUTT,
                        BasicStroke.JOIN_MITER,
                        10.0f, dash1, 0.0f);
                trace.setStroke(strokeNew);
            } else if (selectedLineStyle == PLOT_LINE_STYLE.DOTTED) {
                strokeNew = new BasicStroke(
                        1,
                        BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{1, 2}, 0);
						                trace.setStroke(strokeNew);
            }
        } else if (selectedLineStyle == PLOT_LINE_STYLE.BAR) {
            trace.setTracePainter(new TracePainterVerticalBar(mChart));
        } else if (selectedLineStyle == PLOT_LINE_STYLE.FILL) {
            trace.setTracePainter(new TracePainterFill(mChart));
        }
    }

        public void setYAxisScale(boolean isLeftYAxis, SCALE_SETTING scaleSetting, Object yAxisMin, Object yAxisMax) {
        double yMin = 0;
        double yMax = 0;
        if (!mListofTraces.isEmpty()) {
            if (scaleSetting == SCALE_SETTING.AUTO) {

                IAxis<?> axisToUse = null;
                if (isLeftYAxis /*&& yAxisLeft != null*/) {
                    axisToUse = mChart.getAxisY();
                } else if (yAxisRight != null) {
                    axisToUse = yAxisRight;
                }

                axisToUse.setRangePolicy(new RangePolicyUnbounded());

            } else if (scaleSetting == SCALE_SETTING.FIXED) {
                yMin = (double) yAxisMin;
                yMax = (double) yAxisMax;
                if (yAxisMin != null && yAxisMax != null) {
                    setYAxisMinMax(isLeftYAxis, yMin, yMax);
                }
            } else if (scaleSetting == SCALE_SETTING.CUSTOM) {

                if (yAxisMin != null && yAxisMax == null) {  // y-axis min only
                    yMin = (double) yAxisMin;
                    if (yMin < 0) {
                        setYAxisMinMax(isLeftYAxis, yMin, -yMin);
                    } else {
                        setYAxisMinMax(isLeftYAxis, yMin, yMin * 2);
                    }
                } else if (yAxisMin == null && yAxisMax != null) {  // y-axis max only
                    yMax = (double) yAxisMax;
                    if (yMax > 0) {
                        setYAxisMinMax(isLeftYAxis, -yMax, yMax);
                    } else {
                        setYAxisMinMax(isLeftYAxis, (-yMax * yMax), yMax);
                    }

                } else if (yAxisMin != null && yAxisMax != null) {  // y-axis both
                    yMin = (double) yAxisMin;
                    yMax = (double) yAxisMax;

                    setYAxisMinMax(isLeftYAxis, yMin, yMax);
                }
            }
        }
    }

    private void setYAxisMinMax(boolean isLeftYAxis, double minY, double maxY) {
        if (Double.isFinite(minY) && Double.isFinite(maxY)) {
            Range range = new Range(minY, maxY);
            RangePolicyFixedViewport rangePolicy = new RangePolicyFixedViewport(range);


            IAxis<?> axisToUse = null;
            if (isLeftYAxis /*&& yAxisLeft != null*/) {
                axisToUse = mChart.getAxisY();
            } else if (yAxisRight != null) {
                axisToUse = yAxisRight;
            }

            if (axisToUse != null) {

                IRangePolicy currentRangePolicy = axisToUse.getRangePolicy();
                if (currentRangePolicy instanceof RangePolicyUnbounded) {


                    try {
                        axisToUse.setRangePolicy(rangePolicy);
                        return;
                    } catch (IllegalArgumentException e) {
                    }
                }

                axisToUse.setRangePolicy(rangePolicy);
            }

        } else {
            utilShimmer.consolePrintErrLn("\tPlot Y Axis error:\t minY=" + minY + "\tminY=" + maxY);
        }
    }

    public void adjustTraceLength(double percentage) {
        List<Color> listColor = new ArrayList<Color>();
        List<String[]> listNameArray = new ArrayList<String[]>();
        List<Set<ITracePainter<?>>> listTracePainters = new ArrayList<Set<ITracePainter<?>>>();

        synchronized (mListofTraces) {
            Iterator<ITrace2D> entries = mListofTraces.iterator();
            while (entries.hasNext()) {
                ITrace2D trace = entries.next();
                if (trace != null) {
                    String name = trace.getName();
                    int newSize = (int) (mMapofDefaultXAxisSizes.get(name) * percentage);
                    String[] namearray = name.split(" ");
                    listNameArray.add(namearray);
                    listColor.add(trace.getColor());

                    listTracePainters.add(trace.getTracePainters());
                }
            }
        }

        for (int i = 0; i < listColor.size(); i++) {
            String[] namearray = listNameArray.get(i);
            removeSignalInternal(namearray);
        }
        for (int i = 0; i < listColor.size(); i++) {
            String[] namearray = listNameArray.get(i);
            String name = joinChannelStringArray(namearray);
            int newSize = (int) (mMapofDefaultXAxisSizes.get(name) * percentage);
            Color color = listColor.get(i);
            try {
                ITrace2D trace = addSignalToExistingChartInternal(namearray, newSize, color);

                Set<ITracePainter<?>> tracePaintersPerTrace = listTracePainters.get(i);
                for (ITracePainter<?> iTP : tracePaintersPerTrace) {
                    trace.addTracePainter(iTP);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void adjustTraceLengthofSignalUsingSetSize(double percentage, String signal) {
        synchronized (mListofTraces) {
            Iterator<ITrace2D> entries = mListofTraces.iterator();
            while (entries.hasNext()) {
                ITrace2D trace = entries.next();
                if (trace != null) {
                    String name = trace.getName();
                    if (mMapofDefaultXAxisSizes.get(name) != null && trace.getName().contains(signal)) {
                        int newSize = (int) Math.round((mMapofDefaultXAxisSizes.get(name) * percentage));
                        setTraceSize(trace, newSize);
                    } else {
                    }
                }
            }
        }

    }

    private void setTraceSize(ITrace2D trace, int newSize) {
        utilShimmer.consolePrintErrLn(
                "setTraceSize()\tTrace: " + trace.getName()
                        + " CurrentSize: " + trace.getSize()
                        + " NewSize: " + newSize);
        ((Trace2DLtd) trace).setMaxSize(newSize);
    }

    public synchronized void adjustTraceLengthUsingSetSize(double percentage) {
        synchronized (mListofTraces) {
            Iterator<ITrace2D> entries = mListofTraces.iterator();
            while (entries.hasNext()) {
                ITrace2D trace = entries.next();
                if (trace != null) {
                    String name = trace.getName();
                    if (mMapofDefaultXAxisSizes.get(name) != null) {
                        int newSize = (int) Math.round((mMapofDefaultXAxisSizes.get(name) * percentage));
                        setTraceSize(trace, newSize);
                    }
                }
            }
        }
    }

    public int getTraceLengthMaxSize(String name) {
        return mMapofDefaultXAxisSizes.get(name);
    }

    public int getMinTraceLengthFromTraces() {
        int min = 0;
        if (mListofTraces.size() > 0) {
            min = mListofTraces.get(0).getMaxSize();
        }

        synchronized (mListofTraces) {
            Iterator<ITrace2D> entries = mListofTraces.iterator();
            while (entries.hasNext()) {
                ITrace2D trace = entries.next();
                if (trace != null) {
                    if (min > trace.getMaxSize()) {
                        min = trace.getMaxSize();
                    }
                }
            }
            return min;
        }
    }

    public int getMaxTraceLengthFromTraces() {
        int max = 0;
        if (mListofTraces.size() > 0) {
            max = mListofTraces.get(0).getMaxSize();
        }
        synchronized (mListofTraces) {
            Iterator<ITrace2D> entries = mListofTraces.iterator();
            while (entries.hasNext()) {
                ITrace2D trace = entries.next();
                if (trace != null) {
                    if (max < trace.getMaxSize()) {
                        max = trace.getMaxSize();
                    }
                }
            }
            return max;
        }
    }

    public List<String> getTraceNamesWithMaxTraceLengthFromTraces() {
        int max = 0;
        if (mListofTraces.size() > 0) {
            max = mListofTraces.get(0).getMaxSize();
        }

        synchronized (mListofTraces) {
            Iterator<ITrace2D> entries1 = mListofTraces.iterator();
            while (entries1.hasNext()) {
                ITrace2D trace = entries1.next();
                if (trace != null) {
                    if (max < trace.getMaxSize()) {
                        max = trace.getMaxSize();
                    }
                }
            }
        }

        List<String> listS = new ArrayList<String>();
        synchronized (mListofTraces) {
            Iterator<ITrace2D> entries2 = mListofTraces.iterator();
            while (entries2.hasNext()) {
                ITrace2D trace = entries2.next();
                if (trace != null) {
                    if (max == trace.getMaxSize()) {
                        listS.add(trace.getName());
                    }
                }
            }
        }

        return listS;
    }

    public int getMaxTraceLength(String name) {
        synchronized (mListofTraces) {
            Iterator<ITrace2D> iterator = mListofTraces.iterator();
            while (iterator.hasNext()) {
                ITrace2D trace = iterator.next();
                if (trace.getName().contains(name)) {
                    return trace.getMaxSize();
                }
            }
            return -1;
        }
    }

        public void toggleLegendLabelsPainted() {
        if (mChart != null) {
            setLegendLabelsPainted(!mIsLegendLabelsPainted);
        }
    }

    public boolean isLegendLabelsPainted() {
        return mIsLegendLabelsPainted;
    }

    public void setLegendLabelsPainted(boolean state) {
        mIsLegendLabelsPainted = state;

        mChart.setPaintLabels(mIsLegendLabelsPainted);
    }

        public void toggleScaleLabelsPainted() {
        if (mChart != null) {
            setScaleLabelsPainted(!mIsScaleLabelsPainted);
        }
    }

    public void setXAxisScaleLabelPainted(boolean state) {
        IAxis<?> axisX = mChart.getAxisX();
        axisX.setPaintScale(state);
    }

    public void setYAxisScaleLabelPainted(boolean state) {
        IAxis<?> axisY = mChart.getAxisY();
        axisY.setPaintScale(state);
    }

    public boolean isScaleLabelsPainted() {
        return mIsScaleLabelsPainted;
    }

    public void setScaleLabelsPainted(boolean state) {
        mIsScaleLabelsPainted = state;

        IAxis<?> axisX = mChart.getAxisX();
        axisX.setPaintScale(mIsScaleLabelsPainted);

        IAxis<?> axisY = mChart.getAxisY();
        axisY.setPaintScale(mIsScaleLabelsPainted);

        if (yAxisRight != null) {
            yAxisRight.setPaintScale(mIsScaleLabelsPainted);
        }
    }

    public void toggleAxisLabelsPainted() {
        if (mChart != null) {
            setAxisLabelsPainted(!mIsAxisLabelsPainted);
        }
    }

    public void setAxisLinePainted(boolean isAxisLinePainted) {
        IAxis<?> axisX = mChart.getAxisX();
        IAxis<?> axisY = mChart.getAxisY();

        axisX.setVisible(false);
        axisY.setVisible(false);
    }

    public void setYaxisTitles(String yAxisTitleLeft, String yAxisTitleRight) {
        setYaxisTitleLeft(yAxisTitleLeft);
        setYaxisTitleRight(yAxisTitleRight);
    }

    public void setYaxisTitleLeft(String yAxisTitleLeft) {
        mChart.getAxisY().getAxisTitle().setTitle(yAxisTitleLeft);
    }

    public void setYaxisTitleRight(String yAxisTitleRight) {
        if (yAxisRight != null) {
            yAxisRight.getAxisTitle().setTitle(yAxisTitleRight);
        }
    }

    public boolean isAxisLabelsPainted() {
        return mIsAxisLabelsPainted;
    }

    public void setAxisLabelsPainted(boolean state) {
        mIsAxisLabelsPainted = state;

        IAxis<?> axisX = mChart.getAxisX();
        IAxis<?> axisY = mChart.getAxisY();

        String axisXtitle = null;
        String axisYtitle = null;
        if (mIsAxisLabelsPainted) {
            axisXtitle = "X";
            axisYtitle = "Y";
        }

        axisX.getAxisTitle().setTitle(axisXtitle);
        axisY.getAxisTitle().setTitle(axisYtitle);
        if (yAxisRight != null) {
            yAxisRight.getAxisTitle().setTitle(null);
        }
    }

        public void toggleGrid() {
        if (mChart != null) {
            setGridOn(!mIsGridOn);
        }
    }

    public void turnOnGridWithSpacingValue(double spacingValue) {
        ((IAxis<IAxisScalePolicy>) mChart.getAxisX()).setAxisScalePolicy(new AxisScalePolicyManualTicks());
        mChart.getAxisX().setMinorTickSpacing(spacingValue);
    }

    public boolean isGridOn() {
        return mIsGridOn;
    }

        public void setGridOn(boolean state) {
        if (mChart != null) {
            mIsGridOn = state;
            try {
                IAxis<?> axisX = mChart.getAxisX();
                if (axisX != null) {
                    axisX.setPaintGrid(mIsGridOn);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                IAxis<?> axisY = mChart.getAxisY();
                if (axisY != null) {
                    axisY.setPaintGrid(mIsGridOn);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void togglePause() {
        mIsPlotPaused = !mIsPlotPaused;
    }

    public boolean isPlotPaused() {
        return mIsPlotPaused;
    }

    public void setIsPlotPaused(boolean state) {
        mIsPlotPaused = state;
    }

    public boolean isHRVisible() {
        return mIsHRVisible;
    }

    public void setIsHRVisible(boolean state) {
        mIsHRVisible = state;
    }

    public Color getAxisColor() {
        return null;
    }

    public void setAxisColor(int[] newColor) {
//
    }

    public boolean changeChannelType(String[] oldName, String[] newName) {

        int channelIndex = getIndex(joinChannelStringArray(oldName));
        if (channelIndex >= 0) {
            String[] channel = mListofPropertiestoPlot.get(channelIndex);
            mListofPropertiestoPlot.remove(channelIndex);
            channel[2] = newName[2];
            mListofPropertiestoPlot.add(channelIndex, channel);

            int value = mMapofDefaultXAxisSizes.get(joinChannelStringArray(oldName));
            mMapofDefaultXAxisSizes.remove(joinChannelStringArray(oldName));
            mMapofDefaultXAxisSizes.put(joinChannelStringArray(newName), value);

            return true;
        }
        return false;
    }

    public boolean areArraysEqual(String[] array1, String[] array2) {
        if (array1.length != array2.length) {
            return false;
        }

        for (int i = 0; i < array1.length; i++) {
            if (!(array1[i].equals(array2[i]))) {
                return false;
            }
        }
        return true;
    }

    public synchronized void clearAllDataBuffer() {
        synchronized (mListofTraces) {
            Iterator<ITrace2D> iterator = mListofTraces.iterator();
            while (iterator.hasNext()) {
                ITrace2D trace = iterator.next();
                trace.removeAllPoints();
            }
        }

        if (mIsTraceDataBuffered) {
            for (CircularFifoBuffer circularFifoBuffer : mMapOfCirculurBufferedTraceDataPoints.values()) {
                circularFifoBuffer.clear();
            }
        }
        setXAxisDuration(mXAxisTimeDuration);
        mCurrentXValue = 0;
        isFirstPointOnFillTrace = true;
    }

    public void clearDataBufferAndMakeTraceVisible(String deviceName) {
        clearDataBufferAndSetTraceVisibility(deviceName, true);
    }

    public void clearDataBufferAndMakeTraceInvi(String deviceName) {
        clearDataBufferAndSetTraceVisibility(deviceName, false);
    }

    private void clearDataBufferAndSetTraceVisibility(String deviceName, boolean isVisible) {
        synchronized (mListofTraces) {
            Iterator<ITrace2D> entries = mListofTraces.iterator();
            while (entries.hasNext()) {
                ITrace2D trace = entries.next();
                if (trace != null) {
                    String[] props = trace.getName().split(" ");
                    if (props[0].equals(deviceName) /*|| trace.getName().contains(InternalFrameWithPlotManager.EVENT_MARKER_PLOT_TITLE)*/) {
                        trace.removeAllPoints();
                        trace.removeAllPointHighlighters();
                        trace.setVisible(isVisible);
                    }
                }
            }
        }
    }

    public void setSingleTraceIsVisible(String channelName, boolean isVisible) {
        synchronized (mListofTraces) {
            Iterator<ITrace2D> entries = mListofTraces.iterator();
            while (entries.hasNext()) {
                ITrace2D trace = entries.next();
                if (trace != null) {
                    if (trace.getName().equals(channelName)) {
                        trace.setVisible(isVisible);
                    }
                }
            }
        }
    }

    public boolean isAnyTraceVisible() {
        synchronized (mListofTraces) {
            Iterator<ITrace2D> entries = mListofTraces.iterator();
            while (entries.hasNext()) {
                ITrace2D trace = entries.next();
                if (trace != null) {
                    if (trace.isVisible()) {
                        return true;
                    }
                }
            }
            return false;
        }
    }

    public String getFirstTraceName() {
        if (mListofTraces != null && mListofTraces.size() > 0) {
            return mListofTraces.get(0).getName();
        }
        return null;
    }

    public void resizeBarPlots() {
        int width = mChart.getWidth();
        synchronized (mListofTraces) {
            Iterator<ITrace2D> entries = mListofTraces.iterator();
            while (entries.hasNext()) {
                ITrace2D trace = entries.next();
                if (trace != null) {
                    int size = trace.getSize() - 1;
                    if (size != 0) {
                        for (ITracePainter<?> t : trace.getTracePainters()) {
                            if (t instanceof TracePainterVerticalBar) {
                                ((TracePainterVerticalBar) t).setBarWidth((int) Math.ceil(width / size));
                            }
                        }
                    }
                }
            }
        }
    }

    protected void addTracePoint(ITrace2D currentTrace, double xData, double yData) {
        addPointToTrace(currentTrace, xData, yData);
        saveLastDataPoint(currentTrace.getName(), yData);
    }

    private void saveLastDataPoint(String name, double yData) {
        mMapOfLastDataPoints.put(name, yData);
    }

    public void addPointToTrace(ITrace2D trace, double xData, double yData) {
        if (xData == 0.0 || Double.isNaN(xData) || Double.isInfinite(xData)) {
            xData = 0.000001;
        }

        if (yData == 0.0 || Double.isNaN(yData) || Double.isInfinite(yData)) {
            yData = 0.000001;
        }

        if (mIsTraceDataBuffered) {
            String traceName = trace.getName();
            if (traceName != null) {
                if (!mMapOfCirculurBufferedTraceDataPoints.containsKey(traceName)) {
                    mMapOfCirculurBufferedTraceDataPoints.put(traceName, new CircularFifoBuffer(trace.getMaxSize()));
                }
                CircularFifoBuffer circularFifoBuffer = mMapOfCirculurBufferedTraceDataPoints.get(traceName);
                if (circularFifoBuffer != null) {
                    circularFifoBuffer.add(new Point2D.Double(xData, yData));
                }
            }
        }
        trace.addPoint(xData, yData);
    }

    public CircularFifoBuffer getCirculurBufferedTraceData(String traceName) {
        CircularFifoBuffer circularFifoBuffer = mMapOfCirculurBufferedTraceDataPoints.get(traceName);
        if (circularFifoBuffer != null) {
            return circularFifoBuffer;
        }
        return null;
    }

    public void setIsTraceDataBuffered(boolean isTraceDataBuffered) {
        mIsTraceDataBuffered = isTraceDataBuffered;
    }

    protected double checkAndCorrectData(String shimmerUserAssignedName, String channelName, String traceName, double data) throws Exception {
        double yData = data;
        if (Double.isNaN(yData)) {
            throw new Exception("Signal data is NaN: (" + traceName + ")");
        } else if (Double.isInfinite(yData)) {
            throw new Exception("Signal data is Infinite: (" + traceName + ")");
        }
        else if (yData == 0.0) {
            yData = 0.000001;
        }

        return yData;
    }

    @Deprecated
    public void filterDataAndPlotList(ObjectCluster ojc, int index) throws Exception {
        if (!mIsPlotPaused) {
            String shimmerName = ojc.getShimmerName();
            double xData = getXDataForPlotting(shimmerName, ojc, index);


            synchronized (mListofPropertiestoPlot) {
                Iterator<String[]> entries = mListofPropertiestoPlot.iterator();
                int i = 0;
                while (entries.hasNext()) {
                    String[] props = entries.next();

                    if (shimmerName.equals(props[0])) {
                        FormatCluster f = ObjectCluster.returnFormatCluster(ojc.getCollectionOfFormatClusters(props[1]), props[2]);
                        if (f != null && f.getDataObject() != null) {
                            mCurrentXValue = xData;
                            double yData = f.getDataObject().get(index);
                            ITrace2D trace = mListofTraces.get(i);
                            addTracePoint(trace, xData, yData);


                        } else {
                            throwExceptionSignalNotFound(props, ojc);
                        }
                    }
                    i++;
                }
            }
        }
    }


    private void throwExceptionSignalNotFound(String[] props, ObjectCluster ojc) throws Exception {
        throwExceptionSignalNotFound(joinChannelStringArray(props), ojc);
    }

    private void throwExceptionSignalNotFound(String traceName, ObjectCluster ojc) throws Exception {
        utilShimmer.consolePrintLn("mChart.getName(): " + mChart.getName());
        if (ojc != null) {
            ojc.consolePrintChannelsAndDataSingleLine();
        }
    }

    private double getXDataForPlotting(String shimmerName, ObjectCluster ojc, int index) {
        double xData = 0;
        if (mMapofXAxis.size() > 0) {
            if (mMapofXAxis.get(shimmerName) == null) {
                if (mMapofXAxisGeneratedValue.get(shimmerName) == null) {
                    mMapofXAxisGeneratedValue.put(shimmerName, xData);
                } else {
                    xData = mMapofXAxisGeneratedValue.get(shimmerName);
                }

                if (xData == mCurrentXValue) {
                    xData = xData + 1;
                } else {
                    xData = mCurrentXValue;
                }
                mMapofXAxisGeneratedValue.remove(shimmerName);
                mMapofXAxisGeneratedValue.put(shimmerName, xData);
            } else {
                String[] props = mMapofXAxis.get(shimmerName);
                FormatCluster f = ObjectCluster.returnFormatCluster(ojc.getCollectionOfFormatClusters(props[1]), props[2]);
                xData = f.mDataObject.get(index);
            }
        } else {
            utilShimmer.consolePrintErrLn("ERROR PLOTMANGERPC -> NO X DATA LOADED AT ALL");
        }
        return xData;
    }

    protected void printSignalProps(ObjectCluster ojc, ITrace2D currentTrace, String[] props, double xData, double yData) {
        if (mIsDebugMode) {
            utilShimmer.consolePrintErrLn(
                    "ChartName:" + mChart.getName()
                            + "\tShimmerName:" + ojc.getShimmerName()
                            + "\ttrace size:" + currentTrace.getSize() + "."
                            + "\tprops1:" + props[1] + "."
                            + "\tprops2:" + props[2] + "."
                            + "\tx-value:" + xData + "."
                            + "\ty-value:" + yData + ".");
        }
    }

    public String getPlotTitleWithSignal(String signal) {
        return getTitle() + "_" + signal;
    }

    protected FormatCluster getFormatCluster(String[] props, ObjectCluster ojc) {
        return ObjectCluster.returnFormatCluster(ojc.getPropertyCluster().get(props[1]), props[2]);
    }

    protected String getHRvalue(FormatCluster f) {
        DecimalFormat dc = new DecimalFormat("0");
        String formattedText = " " + dc.format(f.mData) + " ";  // Padding String so that single ECGtoValue wont overflow on gui
        return formattedText;
    }

    public void startTimerCalculateFft() {
        stopTimerCalculateFft();

        if (mTimerCalculateFft == null) {
            mTimerCalculateFft = new Timer(mChart.getName() + "_FFT_Timer");
            mTimerCalculateFft.schedule(new calculateFftTimerTask(), mTimerDelayCalculateFft, mTimerPeriodCalculateFft);
        }
    }

    public void stopTimerCalculateFft() {
        if (mTimerCalculateFft != null) {
            mTimerCalculateFft.cancel();
            mTimerCalculateFft.purge();
            mTimerCalculateFft = null;
        }
    }

    public void setTraceVisible(String channelName) {
        synchronized (mListofTraces) {
            Iterator<ITrace2D> entries = mListofTraces.iterator();
            while (entries.hasNext()) {
                ITrace2D trace = entries.next();
                if (trace != null) {
                    String[] props = trace.getName().split(" ");
                    if (props.length > 1) {
                        if (props[1].equals(channelName)
                                || channelName.equals("all")
                                || trace.getName().contains(mEventMarkerCheck)) {
                            trace.setVisible(true);
                        } else {
                            trace.setVisible(false);
                        }
                    } else {
                        trace.setVisible(false);
                    }
                }
            }
        }
    }

        public void filterDataAndPlot(ObjectCluster ojc) throws Exception {
        if (!mIsPlotPaused) {
            String shimmerName = ojc.getShimmerName();

            double xData = getXDataForPlotting(shimmerName, ojc);

            if (xData == 0) {
                return;
            }
            ;


            boolean isXAxisTime = isXAxisTime();
            boolean isXAxisFrequency = isXAxisFrequency();
            boolean isXAxisValue = isXAxisValue();
            synchronized (mListofPropertiestoPlot) {
                Iterator<String[]> entries = mListofPropertiestoPlot.iterator();
                int indexOfTrace = 0;
                boolean isDummyPointAddedToFillTrace = false;

                while (entries.hasNext()) {
                    String[] props = entries.next();

                    String traceName = joinChannelStringArray(props);

                    boolean eventMarker = false;

                    if (isEventMarkerData(shimmerName, props[0])) {
                        if (xData > mCurrentXValue) {
                            eventMarker = true;
                        } else { // skip any data which is in the past, as there are multiple shimmer devices, this is possible
                            FormatCluster f = ObjectCluster.returnFormatCluster(ojc.getCollectionOfFormatClusters(props[1]), props[2]);
                            if (f == null) {
                                indexOfTrace++;
                                throwExceptionSignalNotFound(traceName, ojc);
                                continue;
                            }

                            double yData = f.mData;
                            try {
                                yData = checkAndCorrectData(ojc.getShimmerName(), props[1], traceName, f.mData);
                            } catch (Exception e) {
                                indexOfTrace++;
                                continue;
                            }

                            if (yData != -1) { //marker detected
                                xData = mCurrentXValue; //ensure the timestamp doesnt go back in time
                                eventMarker = true;
                            } else {
                                eventMarker = false;
                            }
                        }
                    }

                    if (shimmerName.equals(props[0]) || eventMarker) {

                        FormatCluster f = ObjectCluster.returnFormatCluster(ojc.getCollectionOfFormatClusters(props[1]), props[2]);
                        if (f == null) {
                            indexOfTrace++;
                            throwExceptionSignalNotFound(traceName, ojc);
                            continue;
                        }

                        double yData = f.mData;
                        try {
                            yData = checkAndCorrectData(shimmerName, props[1], traceName, f.mData);
                        } catch (Exception e) {
                            indexOfTrace++;
                            continue;
                        }

                        if (indexOfTrace > mListofTraces.size()) {
                            throw new Exception("Trace does not exist: (" + traceName + ")");
                        }
                        ITrace2D currentTrace = mListofTraces.get(indexOfTrace);

                        mCurrentXValue = xData;

                        printSignalProps(ojc, currentTrace, props, xData, yData);

                        updateHrPanelIfVisible(props, ojc);

                        Double halfWindowSize = mMapofHalfWindowSize.get(traceName);
                        if (halfWindowSize != null) {
                            if (addDummyPointToFillTraceIfRequired(currentTrace, xData - halfWindowSize)) {
                                isDummyPointAddedToFillTrace = true;
                            }
                            addPointToTrace(currentTrace, xData - halfWindowSize, yData);
                        } else {
                            if (isXAxisTime) {
                                if (addDummyPointToFillTraceIfRequired(currentTrace, xData)) {
                                    isDummyPointAddedToFillTrace = true;
                                }
                                addTracePoint(currentTrace, xData, yData);
                            } else if (isXAxisFrequency) {
                                FftCalculateDetails fftCalculateDetails = mMapOfFftsToPlot.get(traceName);
                                if (fftCalculateDetails != null) {
                                    fftCalculateDetails.addData(xData, yData);
                                }
                            } else if (isXAxisValue) {
                                if (addDummyPointToFillTraceIfRequired(currentTrace, xData)) {
                                    isDummyPointAddedToFillTrace = true;
                                }
                                addTracePoint(currentTrace, xData, yData);
                            }
                        }

                    }
                    indexOfTrace++;
                }
                if (isDummyPointAddedToFillTrace) {
                    isFirstPointOnFillTrace = false;
                }
            }
        }
        if (pcf != null) {
            pcf.custom(this);
        }
    }

    public void setEventMarkerDataCheck(boolean isSingleEventMarkerTest) {
        this.isSingleEventMarkerTest = isSingleEventMarkerTest;
    }

    private boolean isEventMarkerData(String shimmerName, String signalName) {
        if (isSingleEventMarkerTest) {
            return mEventMarkerCheck.equals(signalName);
        } else {
            return mEventMarkerCheck.equals(signalName) && shimmerName.equals(signalName);
        }
    }

        public boolean addDummyPointToFillTraceIfRequired(ITrace2D currentTrace, double xData) {
        return false;
    }

    public void filterDataAndPlotBasic(List<String[]> listOfSignals, List<double[]> dataArray) throws Exception {

        for (int x = 0; x < listOfSignals.size(); x++) {
            String[] signal = listOfSignals.get(x);
            synchronized (mListofPropertiestoPlot) {
                Iterator<String[]> entries = mListofPropertiestoPlot.iterator();
                int i = 0;
                while (entries.hasNext()) {
                    String[] props = entries.next();

                    if (props[0].equals(signal[0])
                            && props[1].equals(signal[1])
                            && props[2].equals(signal[2])
                            && props[3].equals(signal[3])) {
                        ITrace2D trace = mListofTraces.get(i);

                        for (double[] data : dataArray) {
                            trace.addPoint(data[0], data[x + 2]);
                        }
                    }
                    i++;
                }
            }
        }
    }

    protected void updateHrPanelIfVisible(String[] props, ObjectCluster ojc) {

    }

    private double getXDataForPlotting(String shimmerName, ObjectCluster ojc) throws Exception {
        double xData = 0;
        if (mMapofXAxis.size() > 0) {
            if (mMapofXAxis.get(shimmerName) == null && !mMapofXAxis.containsKey(mEventMarkerCheck)) {
                if (mMapofXAxisGeneratedValue.get(shimmerName) == null) {
                    mMapofXAxisGeneratedValue.put(shimmerName, xData);
                } else {
                    xData = mMapofXAxisGeneratedValue.get(shimmerName);
                }

                if (xData == mCurrentXValue) {
                    xData = xData + 1;
                } else {
                    xData = mCurrentXValue;
                }
                mMapofXAxisGeneratedValue.remove(shimmerName);
                mMapofXAxisGeneratedValue.put(shimmerName, xData);
            } else {
                String[] props = mMapofXAxis.get(shimmerName);
                if (props == null) {
                    props = mMapofXAxis.get(mEventMarkerCheck);
                }

                FormatCluster f = ObjectCluster.returnFormatCluster(ojc.getCollectionOfFormatClusters(props[1]), props[2]);
                if (f != null) {
                    xData = f.mData;
                } else {
                    utilShimmer.consolePrintErrLn("ERROR PLOTMANGERPC -> NO X DATA - "
                            + "\nDeviceName=" + shimmerName
                            + "\tSignalName=" + Arrays.toString(props)
                            + "\tmEventMarkerCheck=" + mEventMarkerCheck);
                    throw new Exception("No X data: (" + joinChannelStringArray(props) + ")");
                }
            }
        } else {
            utilShimmer.consolePrintErrLn("ERROR PLOTMANGERPC -> NO X DATA LOADED AT ALL");
        }
        return xData;
    }

    public void setXAisType(CHANNEL_AXES xAxisType) {
        mXAisType = xAxisType;
        initializeAxes(1000);//Any value
    }

    public boolean isXAxisValue() {
        return (mXAisType == CHANNEL_AXES.VALUE ? true : false);
    }

    public boolean isXAxisTime() {
        return (mXAisType == CHANNEL_AXES.TIME ? true : false);
    }

    public boolean isXAxisFrequency() {
        return (mXAisType == CHANNEL_AXES.FREQUENCY ? true : false);
    }

    public boolean isFftShowingDc() {
        return mIsFftShowingDc;
    }

    public void setIsFftShowingDc(boolean state) {
        mIsFftShowingDc = state;
    }

    public double getFftIntevalInSec() {
        return (mTimerPeriodCalculateFft / 1000);
    }

    public void setFftInterval(double chosenScale) {
        stopTimerCalculateFft();
        mTimerPeriodCalculateFft = (int) (chosenScale * 1000);
        mTimerDelayCalculateFft = (int) (chosenScale * 1000);
        startTimerCalculateFft();
    }

    public int getFftOverlapPercent() {
        return mFftOverlapPercent;
    }

    public void setFftOverlapPercent(int chosenScale) {
        mFftOverlapPercent = chosenScale;

        Iterator<FftCalculateDetails> iterator = mMapOfFftsToPlot.values().iterator();
        while (iterator.hasNext()) {
            FftCalculateDetails fftCalculateDetails = iterator.next();
            fftCalculateDetails.setFftOverlapPercent(mFftOverlapPercent);
        }
    }

        public BufferedImage getSnapShot() {

        synchronized (this) {
            Color savedColour = mChart.getBackground();

            mChart.setBackground(null);
            mChart.setOpaque(false);

            BufferedImage img = new BufferedImage(mChart.getWidth(), mChart.getHeight(), BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2d = (Graphics2D) img.getGraphics();
            g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            mChart.paint(g2d);

            mChart.setBackground(savedColour);
            mChart.setOpaque(true);

            return img;
        }
    }

    public void printListOfTraces() {
        synchronized (mListofTraces) {
            utilShimmer.consolePrintLn("List Of Traces");
            Iterator<ITrace2D> entries = mListofTraces.iterator();
            while (entries.hasNext()) {
                ITrace2D trace = entries.next();
                if (trace != null) {
                    utilShimmer.consolePrintLn("\tLabel: " + trace.getLabel());
                }
            }
            utilShimmer.consolePrintLn("");
        }
    }

    public void addChart(Chart2D chart) {
        mChart = chart;
    }

        protected void filterOldDataOutOfTrace() {
        for (ITrace2D trace : mListofTraces) {
            Iterator itr = trace.iterator();
            boolean reset = false;
            while (itr.hasNext()) {
                ITracePoint2D itp = (ITracePoint2D) itr.next();
                if (itp.getX() < (mCurrentXValue - (mXAxisTimeDuration * 1000))) {
                    reset = true;
                    break;
                } else {
                    break;
                }
            }
            if (reset) {
                trace.setVisible(false);
            } else {
                trace.setVisible(true);
            }
        }
    }

        public void setXAxisDuration(double duration) {
        mXAxisTimeDuration = duration;
    }

        public enum SCALE_SETTING {
        AUTO,
        FIXED,
        CUSTOM
    }

        public class calculateFftTimerTask extends TimerTask {

        @Override
        public void run() {
            if (!isPlotPaused()) {

                Iterator<FftCalculateDetails> iterator = mMapOfFftsToPlot.values().iterator();
                while (iterator.hasNext()) {
                    FftCalculateDetails fftCalculateDetails = iterator.next();


                    double[][] results = fftCalculateDetails.calculateFftAndGenerateArray(mTimerPeriodCalculateFft);
                    String traceName = fftCalculateDetails.getTraceNameJoined();
                    ITrace2D trace = getTraceFromName(traceName);


                    if (trace != null) {
                        int startBin = (mIsFftShowingDc ? 0 : 1);
                        if (results.length == 2 && results[0].length > startBin) {

                            trace.removeAllPoints();

                            for (int x = startBin; x < results[0].length; x++) {
                                addPointToTrace(trace, results[0][x], results[1][x]);
                            }
                        }
                    }



                    fftCalculateDetails.clearBuffers();
                }
            }

        }
    }


}
