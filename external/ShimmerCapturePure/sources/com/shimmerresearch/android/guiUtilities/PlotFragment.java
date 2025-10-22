package com.shimmerresearch.android.guiUtilities;

import android.app.Fragment;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.androidplot.Plot;
import com.androidplot.ui.DynamicTableModel;
import com.androidplot.ui.SizeLayoutType;
import com.androidplot.ui.SizeMetrics;
import com.androidplot.xy.BoundaryMode;
import com.androidplot.xy.LineAndPointFormatter;
import com.androidplot.xy.XYPlot;
import com.androidplot.xy.XYStepMode;
import com.shimmerresearch.android.shimmerService.ShimmerService;
import com.shimmerresearch.androidinstrumentdriver.R;
import com.shimmerresearch.bluetooth.ShimmerBluetooth;
import com.shimmerresearch.driver.CallbackObject;
import com.shimmerresearch.driver.Configuration;
import com.shimmerresearch.driver.ObjectCluster;
import com.shimmerresearch.tools.PlotManagerAndroid;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import javax.annotation.Nullable;

import pl.flex_it.androidplot.XYSeriesShimmer;

/* loaded from: classes2.dex */
public class PlotFragment extends Fragment {
    public static HashMap<String, List<Number>> mPlotDataMap = new HashMap<>(4);
    public static HashMap<String, XYSeriesShimmer> mPlotSeriesMap = new HashMap<>(4);
    public static int X_AXIS_LENGTH = 500;
    static Context context = null;
    static String deviceState = "";
    static LineAndPointFormatter lineAndPointFormatter1;
    static LineAndPointFormatter lineAndPointFormatter2;
    static LineAndPointFormatter lineAndPointFormatter3;
    static String mBluetoothAddress;
    static TextView textViewDeviceName;
    static TextView textViewDeviceState;
    private static String LOG_TAG = "PlotFragment";
    private static Paint LPFpaint = null;
    private static XYPlot dynamicPlot;
    private static ShimmerService shimmerService;
    private static Handler graphHandler = new Handler() { // from class: com.shimmerresearch.android.guiUtilities.PlotFragment.1
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            ShimmerBluetooth.BT_STATE bt_state;
            int i = message.what;
            if (i != 0) {
                if (i == 2) {
                    boolean z = message.obj instanceof ObjectCluster;
                    return;
                }
                if (i != 5) {
                    if (i != 999) {
                        return;
                    }
                    Toast.makeText(PlotFragment.context, message.getData().getString("toast"), 0).show();
                    return;
                } else {
                    Toast.makeText(PlotFragment.context, "Connected to " + PlotFragment.mBluetoothAddress, 0).show();
                    return;
                }
            }
            if (message.obj instanceof ObjectCluster) {
                bt_state = ((ObjectCluster) message.obj).mState;
                PlotFragment.mBluetoothAddress = ((ObjectCluster) message.obj).getMacAddress();
                ((ObjectCluster) message.obj).getShimmerName();
            } else if (message.obj instanceof CallbackObject) {
                bt_state = ((CallbackObject) message.obj).mState;
                PlotFragment.mBluetoothAddress = ((CallbackObject) message.obj).mBluetoothAddress;
            } else {
                bt_state = null;
            }
            switch (AnonymousClass2.$SwitchMap$com$shimmerresearch$bluetooth$ShimmerBluetooth$BT_STATE[bt_state.ordinal()]) {
                case 1:
                    Log.d(PlotFragment.LOG_TAG, "Message Fully Initialized Received from Shimmer driver");
                    PlotFragment.shimmerService.enableGraphingHandler(true);
                    PlotFragment.deviceState = "Connected";
                    PlotFragment.textViewDeviceName.setText(PlotFragment.mBluetoothAddress);
                    PlotFragment.textViewDeviceState.setText(PlotFragment.deviceState);
                    break;
                case 2:
                    Log.d(PlotFragment.LOG_TAG, "Message Fully Initialized Received from Shimmer driver");
                    PlotFragment.shimmerService.enableGraphingHandler(true);
                    PlotFragment.deviceState = "Connected";
                    PlotFragment.textViewDeviceName.setText(PlotFragment.mBluetoothAddress);
                    PlotFragment.textViewDeviceState.setText(PlotFragment.deviceState);
                    break;
                case 3:
                    Log.d(PlotFragment.LOG_TAG, "Driver is attempting to establish connection with Shimmer device");
                    PlotFragment.deviceState = "Connecting";
                    PlotFragment.textViewDeviceName.setText(PlotFragment.mBluetoothAddress);
                    PlotFragment.textViewDeviceState.setText(PlotFragment.deviceState);
                    break;
                case 4:
                    PlotFragment.deviceState = "Streaming";
                    PlotFragment.textViewDeviceName.setText(PlotFragment.mBluetoothAddress);
                    PlotFragment.textViewDeviceState.setText(PlotFragment.deviceState);
                    List<String> listofEnabledSensors = PlotFragment.shimmerService.getBluetoothManager().getListofEnabledSensors(PlotFragment.mBluetoothAddress);
                    if (listofEnabledSensors != null) {
                        if (PlotFragment.shimmerService.getBluetoothManager().getShimmerVersion(PlotFragment.mBluetoothAddress) == 3) {
                            listofEnabledSensors.remove("ECG");
                            listofEnabledSensors.remove("EMG");
                            if (listofEnabledSensors.contains("EXG1")) {
                                listofEnabledSensors.remove("EXG1");
                                listofEnabledSensors.remove("EXG2");
                                if (PlotFragment.shimmerService.isEXGUsingECG24Configuration(PlotFragment.mBluetoothAddress)) {
                                    listofEnabledSensors.add("ECG");
                                    listofEnabledSensors.add(Configuration.Shimmer3.ObjectClusterSensorName.ECG_LA_RA_24BIT);
                                    listofEnabledSensors.add(Configuration.Shimmer3.ObjectClusterSensorName.ECG_LL_RA_24BIT);
                                    listofEnabledSensors.add(Configuration.Shimmer3.ObjectClusterSensorName.ECG_LL_LA_24BIT);
                                    listofEnabledSensors.add(Configuration.Shimmer3.ObjectClusterSensorName.ECG_VX_RL_24BIT);
                                } else if (PlotFragment.shimmerService.isEXGUsingEMG24Configuration(PlotFragment.mBluetoothAddress)) {
                                    listofEnabledSensors.add("EMG");
                                    listofEnabledSensors.add(Configuration.Shimmer3.ObjectClusterSensorName.EMG_CH1_24BIT);
                                    listofEnabledSensors.add(Configuration.Shimmer3.ObjectClusterSensorName.EMG_CH2_24BIT);
                                } else if (PlotFragment.shimmerService.isEXGUsingTestSignal24Configuration(PlotFragment.mBluetoothAddress)) {
                                    listofEnabledSensors.add("ExG Test Signal");
                                }
                            }
                            if (listofEnabledSensors.contains("EXG1 16Bit")) {
                                listofEnabledSensors.remove("EXG1 16Bit");
                                listofEnabledSensors.remove("EXG2 16Bit");
                                if (PlotFragment.shimmerService.isEXGUsingECG16Configuration(PlotFragment.mBluetoothAddress)) {
                                    listofEnabledSensors.add("ECG 16Bit");
                                    listofEnabledSensors.add(Configuration.Shimmer3.ObjectClusterSensorName.ECG_LA_RA_16BIT);
                                    listofEnabledSensors.add(Configuration.Shimmer3.ObjectClusterSensorName.ECG_LL_RA_16BIT);
                                    listofEnabledSensors.add(Configuration.Shimmer3.ObjectClusterSensorName.ECG_VX_RL_16BIT);
                                } else if (PlotFragment.shimmerService.isEXGUsingEMG16Configuration(PlotFragment.mBluetoothAddress)) {
                                    listofEnabledSensors.add("EMG 16Bit");
                                    listofEnabledSensors.add(Configuration.Shimmer3.ObjectClusterSensorName.EMG_CH1_16BIT);
                                    listofEnabledSensors.add(Configuration.Shimmer3.ObjectClusterSensorName.EMG_CH2_16BIT);
                                } else if (PlotFragment.shimmerService.isEXGUsingTestSignal16Configuration(PlotFragment.mBluetoothAddress)) {
                                    listofEnabledSensors.add("ExG Test Signal 16Bit");
                                }
                            }
                        }
                        listofEnabledSensors.add("Timestamp");
                        break;
                    }
                    break;
                case 5:
                    PlotFragment.deviceState = "Streaming";
                    PlotFragment.textViewDeviceName.setText(PlotFragment.mBluetoothAddress);
                    PlotFragment.textViewDeviceState.setText(PlotFragment.deviceState);
                    break;
                case 6:
                    Log.d(PlotFragment.LOG_TAG, "Shimmer No State");
                    PlotFragment.mBluetoothAddress = null;
                    PlotFragment.deviceState = "Disconnected";
                    PlotFragment.textViewDeviceName.setText("Unknown");
                    PlotFragment.textViewDeviceState.setText(PlotFragment.deviceState);
                    break;
            }
        }
    };
    Button signalsToPlotButton;
    private Paint outlinePaint;
    private Paint transparentPaint;

    public static PlotFragment newInstance() {
        PlotFragment plotFragment = new PlotFragment();
        plotFragment.setArguments(new Bundle());
        return plotFragment;
    }

    public XYPlot getDynamicPlot() {
        return dynamicPlot;
    }

    @Override // android.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        context = getActivity();
        return layoutInflater.inflate(R.layout.fragment_plot, viewGroup, false);
    }

    @Override // android.app.Fragment
    public void onActivityCreated(@Nullable Bundle bundle) {
        initPlot();
        textViewDeviceName = (TextView) getView().findViewById(R.id.textViewDeviceName);
        textViewDeviceState = (TextView) getView().findViewById(R.id.textViewDeviceState);
        super.onActivityCreated(bundle);
    }

    private void initPlot() {
        XYPlot xYPlot = (XYPlot) getView().findViewById(R.id.dynamicPlot);
        dynamicPlot = xYPlot;
        xYPlot.getGraphWidget().setDomainValueFormat(new DecimalFormat("0"));
        LineAndPointFormatter lineAndPointFormatter = new LineAndPointFormatter(Integer.valueOf(Color.rgb(51, 153, 255)), null, null);
        lineAndPointFormatter1 = lineAndPointFormatter;
        Paint linePaint = lineAndPointFormatter.getLinePaint();
        LPFpaint = linePaint;
        linePaint.setStrokeWidth(3.0f);
        lineAndPointFormatter1.setLinePaint(LPFpaint);
        LineAndPointFormatter lineAndPointFormatter4 = new LineAndPointFormatter(Integer.valueOf(Color.rgb(245, 146, 107)), null, null);
        lineAndPointFormatter2 = lineAndPointFormatter4;
        Paint linePaint2 = lineAndPointFormatter4.getLinePaint();
        LPFpaint = linePaint2;
        linePaint2.setStrokeWidth(3.0f);
        lineAndPointFormatter2.setLinePaint(LPFpaint);
        LineAndPointFormatter lineAndPointFormatter5 = new LineAndPointFormatter(Integer.valueOf(Color.rgb(150, 150, 150)), null, null);
        lineAndPointFormatter3 = lineAndPointFormatter5;
        Paint linePaint3 = lineAndPointFormatter5.getLinePaint();
        LPFpaint = linePaint3;
        linePaint3.setStrokeWidth(3.0f);
        lineAndPointFormatter3.setLinePaint(LPFpaint);
        Paint paint = new Paint();
        this.transparentPaint = paint;
        paint.setColor(0);
        dynamicPlot.setDomainStepMode(XYStepMode.SUBDIVIDE);
        dynamicPlot.getLegendWidget().setTableModel(new DynamicTableModel(1, 4));
        getActivity().getWindowManager().getDefaultDisplay().getSize(new Point());
        dynamicPlot.getLegendWidget().setSize(new SizeMetrics(r4.x / 2, SizeLayoutType.ABSOLUTE, r4.y / 3, SizeLayoutType.ABSOLUTE));
        dynamicPlot.setTicksPerDomainLabel(5);
        dynamicPlot.setTicksPerRangeLabel(3);
        dynamicPlot.disableAllMarkup();
        Paint paint2 = new Paint();
        paint2.setColor(Color.parseColor("#969696"));
        Paint paint3 = new Paint();
        paint3.setColor(0);
        dynamicPlot.setRangeBoundaries(-15, 15, BoundaryMode.AUTO);
        dynamicPlot.setDomainBoundaries(0, Integer.valueOf(X_AXIS_LENGTH), BoundaryMode.FIXED);
        dynamicPlot.setBorderStyle(Plot.BorderStyle.NONE, null, null);
        dynamicPlot.getBackgroundPaint().setColor(0);
        dynamicPlot.setBackgroundColor(0);
        dynamicPlot.getGraphWidget().getGridBackgroundPaint().setColor(0);
        dynamicPlot.getGraphWidget().getBackgroundPaint().setColor(0);
        dynamicPlot.getGraphWidget().setGridLinePaint(this.transparentPaint);
        dynamicPlot.getGraphWidget().setDomainOriginLabelPaint(paint3);
        dynamicPlot.getGraphWidget().setDomainOriginLinePaint(paint2);
        dynamicPlot.getGraphWidget().setDomainLabelPaint(paint3);
        dynamicPlot.getGraphWidget().getDomainLabelPaint().setTextSize(20.0f);
        dynamicPlot.getDomainLabelWidget().pack();
        Paint domainOriginLinePaint = dynamicPlot.getGraphWidget().getDomainOriginLinePaint();
        this.outlinePaint = domainOriginLinePaint;
        domainOriginLinePaint.setStrokeWidth(3.0f);
        dynamicPlot.getGraphWidget().setClippingEnabled(false);
        dynamicPlot.getGraphWidget().setRangeOriginLabelPaint(paint2);
        dynamicPlot.getGraphWidget().setRangeOriginLinePaint(paint2);
        dynamicPlot.getGraphWidget().setRangeLabelPaint(paint2);
        dynamicPlot.getGraphWidget().getRangeLabelPaint().setTextSize(20.0f);
        dynamicPlot.getRangeLabelWidget().pack();
        Paint rangeOriginLinePaint = dynamicPlot.getGraphWidget().getRangeOriginLinePaint();
        this.outlinePaint = rangeOriginLinePaint;
        rangeOriginLinePaint.setStrokeWidth(3.0f);
        dynamicPlot.getLayoutManager().remove(dynamicPlot.getRangeLabelWidget());
        dynamicPlot.getLayoutManager().remove(dynamicPlot.getDomainLabelWidget());
    }

    public void setShimmerService(ShimmerService shimmerService2) {
        shimmerService = shimmerService2;
        shimmerService2.setGraphHandler(graphHandler);
        if (shimmerService.mPlotManager == null) {
            shimmerService.mPlotManager = new PlotManagerAndroid(false);
        }
        shimmerService.mPlotManager.updateDynamicPlot(dynamicPlot);
    }

    public void clearPlot() {
        mPlotSeriesMap.clear();
        mPlotDataMap.clear();
        dynamicPlot.clear();
    }

    /* renamed from: com.shimmerresearch.android.guiUtilities.PlotFragment$2, reason: invalid class name */
    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$com$shimmerresearch$bluetooth$ShimmerBluetooth$BT_STATE;

        static {
            int[] iArr = new int[ShimmerBluetooth.BT_STATE.values().length];
            $SwitchMap$com$shimmerresearch$bluetooth$ShimmerBluetooth$BT_STATE = iArr;
            try {
                iArr[ShimmerBluetooth.BT_STATE.CONNECTED.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$shimmerresearch$bluetooth$ShimmerBluetooth$BT_STATE[ShimmerBluetooth.BT_STATE.SDLOGGING.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$shimmerresearch$bluetooth$ShimmerBluetooth$BT_STATE[ShimmerBluetooth.BT_STATE.CONNECTING.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$shimmerresearch$bluetooth$ShimmerBluetooth$BT_STATE[ShimmerBluetooth.BT_STATE.STREAMING.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$shimmerresearch$bluetooth$ShimmerBluetooth$BT_STATE[ShimmerBluetooth.BT_STATE.STREAMING_AND_SDLOGGING.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$shimmerresearch$bluetooth$ShimmerBluetooth$BT_STATE[ShimmerBluetooth.BT_STATE.DISCONNECTED.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
        }
    }
}
