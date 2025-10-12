package com.shimmerresearch.guiUtilities.plot;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;

import com.shimmerresearch.guiUtilities.AbstractPlotManager.PLOT_LINE_STYLE;

import info.monitorenter.gui.chart.Chart2D;
import info.monitorenter.gui.chart.IAxis;
import info.monitorenter.gui.chart.ITrace2D;
import info.monitorenter.gui.chart.ZoomableChart;
import info.monitorenter.gui.chart.rangepolicies.RangePolicyFixedViewport;
import info.monitorenter.util.Range;

public class QuickPlot {

    public static final int INVALID_CHANNEL_INDEX = -1;
    protected final Chart2D mChart = new ZoomableChart();
    private UtilChart2D utilChart2D = new UtilChart2D();
    private JFrame frame;

    public QuickPlot(String plotName) {
        createFrame(plotName);
    }

        public QuickPlot(String plotName, double[][] data, String[] signalNames, Color[] signalColors) {
        this(plotName);
        createTracesAndAddData(data, signalNames, signalColors, INVALID_CHANNEL_INDEX);
        chartSetupCommon();
    }

    public static void main(String[] args) {
        QuickPlot quickPlot = new QuickPlot("Plot", new double[][]{}, new String[]{}, new Color[]{});
    }

    private void createFrame(String plotName) {
        frame = new JFrame(plotName);
        frame.setSize(800, 600);
        frame.add(mChart);
        frame.setVisible(true);
        //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void createTracesAndAddData(double[][] data, String[] signalNames, Color[] signalColors, int timestampIndex) {
        if (data.length > 0 && data[0].length > 0) {
            ITrace2D[] traces = new ITrace2D[data[0].length];
            for (int y = 0; y < data[0].length; y++) {
                if (y == timestampIndex) {
                    continue;
                }
                ITrace2D trace = UtilChart2D.createTraceAndAddToChart(mChart, data.length, PLOT_LINE_STYLE.CONTINUOUS);

                trace.setName(signalNames[y]);
                if (signalColors != null) {
                    trace.setColor(signalColors[y]);
                } else {
                    //TODO
//					trace.setColor(A);
                }

                trace.setVisible(false);

                traces[y] = trace;
            }

            for (int x = 0; x < data.length; x++) {
                if (timestampIndex == INVALID_CHANNEL_INDEX) {
                    for (int y = 0; y < data[0].length; y++) {
                        traces[y].addPoint(x + 0.0001, data[x][y]);
                    }
                } else {
                    for (int y = 0; y < data[0].length; y++) {
                        if (y != timestampIndex) {
                            traces[y].addPoint(data[x][timestampIndex], data[x][y]);
                        }
                    }
                }
            }

            for (ITrace2D trace : traces) {
                if (trace != null) {
                    trace.setVisible(true);
                }
            }
        }
    }

    private void chartSetupCommon() {
        utilChart2D.setGridOn(mChart, true);

        mChart.addMouseListener(new MouseListener() {

            @Override
            public void mouseReleased(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON3) {
                    zoomOut();
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseClicked(MouseEvent e) {
            }

            private void zoomOut() {
                IAxis<?> yAxisLeft = mChart.getAxisY();
                yAxisLeft.setRangePolicy(new RangePolicyFixedViewport(new Range(yAxisLeft.getMinValue(), yAxisLeft.getMaxValue())));
                IAxis<?> xAxisLeft = mChart.getAxisX();
                xAxisLeft.setRangePolicy(new RangePolicyFixedViewport(new Range(xAxisLeft.getMinValue(), xAxisLeft.getMaxValue())));
            }
        });
    }

    public Chart2D getChart() {
        return mChart;
    }

}