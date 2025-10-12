package com.shimmerresearch.guiUtilities.plot;

import java.awt.BasicStroke;

import com.shimmerresearch.guiUtilities.AbstractPlotManager.PLOT_LINE_STYLE;

import info.monitorenter.gui.chart.Chart2D;
import info.monitorenter.gui.chart.IAxis;
import info.monitorenter.gui.chart.IAxisScalePolicy;
import info.monitorenter.gui.chart.ITrace2D;
import info.monitorenter.gui.chart.axis.AAxis;
import info.monitorenter.gui.chart.axis.AxisLinear;
import info.monitorenter.gui.chart.axis.scalepolicy.AxisScalePolicyAutomaticBestFit;
import info.monitorenter.gui.chart.traces.Trace2DLtd;
import info.monitorenter.gui.chart.traces.painters.TracePainterDisc;
import info.monitorenter.gui.chart.traces.painters.TracePainterFill;
import info.monitorenter.gui.chart.traces.painters.TracePainterLine;
import info.monitorenter.gui.chart.traces.painters.TracePainterVerticalBar;

public class UtilChart2D {

    public static float DEFAULT_LINE_THICKNESS = 2;

    public boolean mIsGridOn = false;

    public static ITrace2D createTraceAndAddToChart(Chart2D chart, int plotMaxSize, PLOT_LINE_STYLE plotLineStyle) {

        ITrace2D trace = createTraceAndSetStyle(chart, plotMaxSize, plotLineStyle);
        chart.addTrace(trace);

        return trace;
    }

    private static ITrace2D createTraceAndSetStyle(Chart2D chart, int plotMaxSize, PLOT_LINE_STYLE plotLineStyle) {
        ITrace2D trace = new Trace2DLtd(plotMaxSize);
        setTraceLineStyle(chart, trace, plotLineStyle);
        return trace;
    }

    public static ITrace2D addContinuousTraceLeft(Chart2D chart, int plotMaxSize) {
        ITrace2D trace = createContinuousTrace(plotMaxSize);
        chart.addTrace(trace);
        return trace;
    }

    public static ITrace2D addContinuousTraceRight(Chart2D chart, int plotMaxSize, AAxis<IAxisScalePolicy> yAxisRight) {
        ITrace2D trace = createContinuousTrace(plotMaxSize);
        chart.addTrace(trace, chart.getAxisX(), yAxisRight);
        return trace;
    }

    public static ITrace2D addBarTrace(Chart2D chart, int plotMaxSize) {
        ITrace2D trace = createBarTrace(chart, plotMaxSize);
        chart.addTrace(trace);
        return trace;
    }

    public static ITrace2D createContinuousTrace(int plotMaxSize) {

        ITrace2D trace = createTraceAndSetStyle(null, plotMaxSize, PLOT_LINE_STYLE.CONTINUOUS);
        return trace;
    }

    public static ITrace2D createBarTrace(Chart2D chart, int plotMaxSize) {
        ITrace2D trace = createTraceAndSetStyle(chart, plotMaxSize, PLOT_LINE_STYLE.BAR);
        return trace;
    }

    public static AAxis<IAxisScalePolicy> createRightYAxis(Chart2D chart) {
        AAxis<IAxisScalePolicy> yAxisRight = new AxisLinear<IAxisScalePolicy>();
        yAxisRight.setAxisScalePolicy(new AxisScalePolicyAutomaticBestFit());
        yAxisRight.setPaintGrid(false);

        return yAxisRight;
    }

    public static void setTraceLineStyle(Chart2D chart, ITrace2D trace, PLOT_LINE_STYLE plotLineStyle) {
        trace.setTracePainter(new TracePainterLine());
        trace.setStroke(new BasicStroke());

        if (plotLineStyle == PLOT_LINE_STYLE.CONTINUOUS
                || plotLineStyle == PLOT_LINE_STYLE.INDIVIDUAL_POINTS
                || plotLineStyle == PLOT_LINE_STYLE.DASHED
                || plotLineStyle == PLOT_LINE_STYLE.DOTTED
                || plotLineStyle == PLOT_LINE_STYLE.INDIVIDUAL_POINTS) {
            BasicStroke strokeOld = ((BasicStroke) trace.getStroke());
            BasicStroke strokeNew = null;

            if (plotLineStyle == PLOT_LINE_STYLE.CONTINUOUS
                    || plotLineStyle == PLOT_LINE_STYLE.INDIVIDUAL_POINTS) {
                strokeNew = new BasicStroke(
                        UtilChart2D.DEFAULT_LINE_THICKNESS,
                        strokeOld.getEndCap(),
                        strokeOld.getLineJoin(),
                        strokeOld.getMiterLimit(),
                        strokeOld.getDashArray(),
                        strokeOld.getDashPhase());
                trace.setStroke(strokeNew);

                if (plotLineStyle == PLOT_LINE_STYLE.INDIVIDUAL_POINTS) {
                    trace.setTracePainter(new TracePainterDisc(4));
                }
            } else if (plotLineStyle == PLOT_LINE_STYLE.DASHED) {
                float dash1[] = {10.0f};
                strokeNew = new BasicStroke(strokeOld.getLineWidth(),
                        BasicStroke.CAP_BUTT,
                        BasicStroke.JOIN_MITER,
                        10.0f, dash1, 0.0f);
                trace.setStroke(strokeNew);
            } else if (plotLineStyle == PLOT_LINE_STYLE.DOTTED) {
                strokeNew = new BasicStroke(
                        1,
                        BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{1, 2}, 0);
						                trace.setStroke(strokeNew);
            }
        } else if (plotLineStyle == PLOT_LINE_STYLE.BAR) {
            trace.setTracePainter(new TracePainterVerticalBar(chart));
        } else if (plotLineStyle == PLOT_LINE_STYLE.FILL) {
            trace.setTracePainter(new TracePainterFill(chart));
        }
    }

    public void toggleGrid(Chart2D mChart) {
        if (mChart != null) {
            setGridOn(mChart, !mIsGridOn);
        }
    }

        public void setGridOn(Chart2D mChart, boolean state) {
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

    public boolean isGridOn() {
        return mIsGridOn;
    }

}