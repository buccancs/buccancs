package com.shimmerresearch.android.shimmerService;

import android.app.Service;
import android.bluetooth.BluetoothAdapter;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;
import com.shimmerresearch.algorithms.Filter;
import com.shimmerresearch.android.Shimmer;
import com.shimmerresearch.android.manager.ShimmerBluetoothManagerAndroid;
import com.shimmerresearch.biophysicalprocessing.ECGtoHRAdaptive;
import com.shimmerresearch.biophysicalprocessing.PPGtoHRAlgorithm;
import com.shimmerresearch.bluetooth.ShimmerBluetooth;
import com.shimmerresearch.bluetooth.ShimmerBluetooth.BT_STATE;
import com.shimmerresearch.driver.*;
import com.shimmerresearch.driverUtilities.BluetoothDeviceDetails;
import com.shimmerresearch.driverUtilities.ChannelDetails.CHANNEL_TYPE;
import com.shimmerresearch.tools.Logging;
import com.shimmerresearch.tools.PlotManagerAndroid;
import com.shimmerresearch.verisense.VerisenseDevice;

import java.text.SimpleDateFormat;
import java.util.*;

import static com.shimmerresearch.bluetooth.ShimmerBluetooth.MSG_IDENTIFIER_NOTIFICATION_MESSAGE;

public class ShimmerService extends Service {
    private static final String TAG = "ShimmerService";
    private final IBinder mBinder = new LocalBinder();
    public Logging shimmerLog1 = null;
    public HashMap<String, Object> mMultiShimmer = new HashMap<String, Object>(7);
    public HashMap<String, Logging> mLogShimmer = new HashMap<String, Logging>(7);
    public String mLogFileName = "Default";
    public Uri mFileURI = null;
    public ContentResolver mResolver = null;
    public Context mContext = null;
    public PlotManagerAndroid mPlotManager;
    public FILE_TYPE mLoggingFileType = FILE_TYPE.CSV;
    protected Handler mHandlerGraph = null;
    protected ShimmerBluetoothManagerAndroid btManager;
    Filter mFilter;
    Filter mLPFilterECG;
    Filter mHPFilterECG;
    PPGtoHRAlgorithm mPPGtoHR;
    ECGtoHRAdaptive mECGtoHR;
    double[] mLPFc = {5};
    private boolean mEnableLogging = false;
    private String mLogFolderName = "ShimmerCapture";
    private BluetoothAdapter mBluetoothAdapter = null;
    private List<Handler> mHandlerList = new ArrayList<Handler>();
    private boolean mGraphing = false;
    private double[] mLPFcECG = {51.2};
    private double[] mHPFcECG = {0.5};
    private int mNumberOfBeatsToAvg = 2;
    private int mNumberOfBeatsToAvgECG = 2;
    private int mECGTrainingInterval = 10;
    private boolean mPPGtoHREnabled = false;
    private boolean mECGtoHREnabled = false;
    private boolean mConvertGSRtoSiemens = true;
    private String mPPGtoHRSignalName = Configuration.Shimmer3.ObjectClusterSensorName.INT_EXP_ADC_A13;
    private String mECGtoHRSignalName = Configuration.Shimmer3.ObjectClusterSensorName.ECG_LA_RA_24BIT;
    public final Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            for (Handler handler : mHandlerList) {
                handler.obtainMessage(msg.what, msg.arg1, msg.arg2, msg.obj).sendToTarget();
            }
            switch (msg.what) {
                case ShimmerBluetooth.MSG_IDENTIFIER_DATA_PACKET:
                    handleMsgDataPacket(msg);
                    break;
                case Shimmer.MESSAGE_TOAST:
                    Log.d("toast", msg.getData().getString(Shimmer.TOAST));
                    Toast.makeText(getApplicationContext(), msg.getData().getString(Shimmer.TOAST),
                            Toast.LENGTH_SHORT).show();
                    if (msg.getData().getString(Shimmer.TOAST).equals("Device connection was lost")) {

                    }
                    break;
                case ShimmerBluetooth.MSG_IDENTIFIER_STATE_CHANGE:
                    handleMsgStateChange(msg);
                    break;
                case Shimmer.MESSAGE_STOP_STREAMING_COMPLETE:
                    String address = msg.getData().getString("Bluetooth Address");
                    boolean stop = msg.getData().getBoolean("Stop Streaming");
                    if (stop == true) {
                        closeAndRemoveFile(address);
                    }
                    break;
                case Shimmer.MESSAGE_LOG_AND_STREAM_STATUS_CHANGED:
                    mHandlerGraph.obtainMessage(Shimmer.MESSAGE_LOG_AND_STREAM_STATUS_CHANGED, msg.arg1, msg.arg2).sendToTarget();
                    break;
                case MSG_IDENTIFIER_NOTIFICATION_MESSAGE:
                    handleNotificationMsg(msg);
                    break;

            }
        }
    };

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    public boolean isGSRtoSiemensEnabled() {
        return mConvertGSRtoSiemens;
    }

    @Override
    public void onCreate() {
        Toast.makeText(this, "Shimmer Service Created", Toast.LENGTH_LONG).show();
        Log.d(TAG, "onCreate");

        try {
            btManager = new ShimmerBluetoothManagerAndroid(this, mHandler);
        } catch (Exception e) {
            Log.e(TAG, "ERROR! " + e);
            Toast.makeText(this, "Error! Could not create Bluetooth Manager!", Toast.LENGTH_LONG).show();
        }

    }

    public void setPPGtoHRSignal(String ppgtoHR) {
        mPPGtoHRSignalName = ppgtoHR;
    }

    public void setECGtoHRSignal(String ecgtoHR) {
        mECGtoHRSignalName = ecgtoHR;
    }

    @Override
    public void onDestroy() {
        Toast.makeText(this, "Shimmer Service Stopped", Toast.LENGTH_LONG).show();
        Log.d(TAG, "onDestroy");
        btManager.disconnectAllDevices();
    }

    public void disconnectAllDevices() {
        btManager.disconnectAllDevices();
        mMultiShimmer.clear();
        mLogShimmer.clear();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("LocalService", "Received start id " + startId + ": " + intent);

        return START_NOT_STICKY;
    }

    @Override
    public void onStart(Intent intent, int startid) {
        Toast.makeText(this, "My Service Started", Toast.LENGTH_LONG).show();

        Log.d(TAG, "onStart");

    }

    public void enablePPGtoHR(String bluetoothAddress, boolean enable) {
        if (enable) {
            double sR = getSamplingRate(bluetoothAddress);
            mPPGtoHR = new PPGtoHRAlgorithm(sR, mNumberOfBeatsToAvg, true);
            try {
                mFilter = new Filter(Filter.LOW_PASS, sR, mLPFc);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        mPPGtoHREnabled = enable;
    }

    public void enableECGtoHR(String bluetoothAddress, boolean enable) {
        if (enable) {
            double sR = getSamplingRate(bluetoothAddress);
            mECGtoHR = new ECGtoHRAdaptive(sR);
            try {
                mLPFilterECG = new Filter(Filter.LOW_PASS, sR, mLPFcECG);
            } catch (Exception e) {
                e.printStackTrace();
            }

            try {
                mHPFilterECG = new Filter(Filter.HIGH_PASS, sR, mHPFcECG);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        mECGtoHREnabled = enable;
    }

    public boolean isPPGtoHREnabled() {
        return mPPGtoHREnabled;
    }

    public boolean isECGtoHREnabled() {
        return mECGtoHREnabled;
    }

    public void connectShimmer(final String bluetoothAddress, Context context) {
        btManager.connectShimmerThroughBTAddress(bluetoothAddress, "", context);
    }

    public void connectShimmer(final String bluetoothAddress, final String deviceName, Context context) {
        btManager.connectShimmerThroughBTAddress(bluetoothAddress, deviceName, context);
    }

    public void connectShimmer(final String bluetoothAddress, final String deviceName, ShimmerBluetoothManagerAndroid.BT_TYPE preferredBtType, Context context) {
        boolean isVerisense = false;
        if (deviceName != null) {
            if (deviceName.contains(VerisenseDevice.VERISENSE_PREFIX)) {
                isVerisense = true;
            }
        }

        if (isVerisense) {
            btManager.connectVerisenseDevice(new BluetoothDeviceDetails("", bluetoothAddress, deviceName));
        } else {
            btManager.connectShimmerThroughBTAddress(bluetoothAddress, deviceName, preferredBtType);
        }


    }

    public void connectShimmer(final String bluetoothAddress) {
        btManager.connectShimmerThroughBTAddress(bluetoothAddress);
    }

    public void onStop() {
        Toast.makeText(this, "My Service Stopped", Toast.LENGTH_LONG).show();
        Log.d(TAG, "onDestroy");

        btManager.disconnectAllDevices();
    }

    @Deprecated
    public void toggleAllLEDS() {
        btManager.toggleAllLEDS();
    }

    @Deprecated
    public void toggleLED(String bluetoothAddress) {
        btManager.toggleLED(bluetoothAddress);
    }

    public void clickToggle() {
        btManager.toggleAllLEDS();
    }

    public void handleMsgDataPacket(Message msg) {
        if ((msg.obj instanceof ObjectCluster)) {
            ObjectCluster objectCluster = (ObjectCluster) msg.obj;

            if (mPPGtoHREnabled) {
                Collection<FormatCluster> dataFormats = objectCluster.getCollectionOfFormatClusters(mPPGtoHRSignalName);
                FormatCluster formatCluster = ((FormatCluster) ObjectCluster.returnFormatCluster(dataFormats, CHANNEL_TYPE.CAL.toString()));
                if (formatCluster != null) {
                    double ppgdata = formatCluster.mData;

                    try {
                        ppgdata = mFilter.filterData(ppgdata);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    dataFormats = objectCluster.getCollectionOfFormatClusters(Configuration.Shimmer3.ObjectClusterSensorName.TIMESTAMP);
                    formatCluster = ((FormatCluster) ObjectCluster.returnFormatCluster(dataFormats, CHANNEL_TYPE.CAL.toString()));

                    double calts = formatCluster.mData;

                    double hr = mPPGtoHR.ppgToHrConversion(ppgdata, calts);
                    System.out.print("Heart Rate: " + Integer.toString((int) hr) + "\n");

                    objectCluster.addData(Configuration.Shimmer3.ObjectClusterSensorName.PPG_TO_HR, CHANNEL_TYPE.CAL, Configuration.CHANNEL_UNITS.BEATS_PER_MINUTE, hr);
                }
            }

            if (mECGtoHREnabled) {
                Collection<FormatCluster> dataFormats = objectCluster.getCollectionOfFormatClusters(mECGtoHRSignalName);
                FormatCluster formatCluster = ((FormatCluster) ObjectCluster.returnFormatCluster(dataFormats, CHANNEL_TYPE.CAL.toString()));
                if (formatCluster != null) {
                    double ecgdata = formatCluster.mData;

                    try {
                        ecgdata = mLPFilterECG.filterData(ecgdata);
                        ecgdata = mHPFilterECG.filterData(ecgdata);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    dataFormats = objectCluster.getCollectionOfFormatClusters(Configuration.Shimmer3.ObjectClusterSensorName.TIMESTAMP);
                    formatCluster = ((FormatCluster) ObjectCluster.returnFormatCluster(dataFormats, CHANNEL_TYPE.CAL.toString()));

                    double calts = formatCluster.mData;

                    double hr = mECGtoHR.ecgToHrConversion(ecgdata, calts);
                    System.out.print("Heart Rate: " + Integer.toString((int) hr) + "\n");
                    objectCluster.addData(Configuration.Shimmer3.GuiLabelSensors.ECG_TO_HR, CHANNEL_TYPE.CAL, Configuration.CHANNEL_UNITS.BEATS_PER_MINUTE, hr);
                }
            }


            if (mConvertGSRtoSiemens) {
                Collection<FormatCluster> dataFormatsGSR = objectCluster.getCollectionOfFormatClusters(Configuration.Shimmer3.ObjectClusterSensorName.GSR_RESISTANCE);
                FormatCluster formatClusterGSR = ((FormatCluster) ObjectCluster.returnFormatCluster(dataFormatsGSR, CHANNEL_TYPE.CAL.toString()));
                if (formatClusterGSR != null) {
                    double gsrdata = formatClusterGSR.mData * 1000;
                    double conductance = 1 / gsrdata;
                    conductance = conductance * 1000000;
                    objectCluster.addData(Configuration.Shimmer3.ObjectClusterSensorName.GSR_CONDUCTANCE, CHANNEL_TYPE.CAL, "microSiemens", conductance);
                }
            }

            try {
                mPlotManager.filterDataAndPlot((ObjectCluster) msg.obj);
            } catch (Exception e) {
                e.printStackTrace();
            }


            objectCluster.removeAll(Configuration.Shimmer3.ObjectClusterSensorName.BATT_PERCENTAGE);
            objectCluster.removeAll(Configuration.Shimmer3.ObjectClusterSensorName.PACKET_RECEPTION_RATE_OVERALL);
            objectCluster.removeAll(Configuration.Shimmer3.ObjectClusterSensorName.PACKET_RECEPTION_RATE_CURRENT);
            objectCluster.removeAll(Configuration.Shimmer3.ObjectClusterSensorName.SYSTEM_TIMESTAMP);

            if (mEnableLogging == true) {
                shimmerLog1 = mLogShimmer.get(objectCluster.getMacAddress());
                if (shimmerLog1 != null) {
                    shimmerLog1.logData(objectCluster);
                } else {
                    char[] bA = objectCluster.getMacAddress().toCharArray();
                    Logging shimmerLog;
                    if (mLogFileName.equals("Default")) {
                        if (mFileURI == null) {
                            shimmerLog = new Logging(fromMilisecToDate(System.currentTimeMillis()) + " Device" + bA[12] + bA[13] + bA[15] + bA[16], ",", mLogFolderName, mLoggingFileType);
                        } else {
                            shimmerLog = new Logging(mFileURI, mContext, fromMilisecToDate(System.currentTimeMillis()) + " Device" + bA[12] + bA[13] + bA[15] + bA[16], ",", mLogFolderName, mLoggingFileType);
                        }
                    } else {
                        if (mFileURI == null) {
                            shimmerLog = new Logging(fromMilisecToDate(System.currentTimeMillis()) + mLogFileName, ",", mLogFolderName, mLoggingFileType);
                        } else {
                            shimmerLog = new Logging(mFileURI, mContext, fromMilisecToDate(System.currentTimeMillis()) + mLogFileName, ",", mLogFolderName, mLoggingFileType);
                        }
                    }
                    mLogShimmer.remove(objectCluster.getMacAddress());
                    if (mLogShimmer.get(objectCluster.getMacAddress()) == null) {
                        mLogShimmer.put(objectCluster.getMacAddress(), shimmerLog);
                    }
                }
            }

            if (mGraphing == true) {
                mHandlerGraph.obtainMessage(ShimmerBluetooth.MSG_IDENTIFIER_DATA_PACKET, objectCluster)
                        .sendToTarget();
            }
        }
    }

    public void handleMsgStateChange(Message msg) {

        Intent intent = new Intent("com.shimmerresearch.service.ShimmerService");
        Log.d("ShimmerGraph", "Sending");
        if (mHandlerGraph != null) {
            mHandlerGraph.obtainMessage(msg.what, msg.arg1, -1, msg.obj).sendToTarget();
        }
        if (msg.arg1 == Shimmer.MSG_STATE_STOP_STREAMING) {
            closeAndRemoveFile(((ObjectCluster) msg.obj).getMacAddress());
        } else {
            BT_STATE state = null;
            String macAddress = "";
            String shimmerName = "";
            if (msg.obj instanceof ObjectCluster) {
                state = ((ObjectCluster) msg.obj).mState;
                macAddress = ((ObjectCluster) msg.obj).getMacAddress();
                shimmerName = ((ObjectCluster) msg.obj).getShimmerName();
            } else if (msg.obj instanceof CallbackObject) {
                state = ((CallbackObject) msg.obj).mState;
                macAddress = ((CallbackObject) msg.obj).mBluetoothAddress;
                shimmerName = "";
            }
            switch (state) {
                case CONNECTED:
                    ShimmerDevice shimmerDevice = btManager.getShimmerDeviceBtConnectedFromMac(macAddress);
                    mMultiShimmer.remove(macAddress);
                    if (mMultiShimmer.get(macAddress) == null) {
                        mMultiShimmer.put(macAddress, shimmerDevice);
                    }
                    intent.putExtra("ShimmerBluetoothAddress", macAddress);
                    intent.putExtra("ShimmerDeviceName", shimmerName);
                    intent.putExtra("ShimmerState", BT_STATE.CONNECTED);
                    sendBroadcast(intent);
                    break;
                case CONNECTING:
                    intent.putExtra("ShimmerBluetoothAddress", macAddress);
                    intent.putExtra("ShimmerDeviceName", shimmerName);
                    intent.putExtra("ShimmerState", BT_STATE.CONNECTING);
                    sendBroadcast(intent);
                    break;
                case STREAMING:
                    intent.putExtra("ShimmerBluetoothAddress", macAddress);
                    intent.putExtra("ShimmerDeviceName", shimmerName);
                    intent.putExtra("ShimmerState", BT_STATE.STREAMING);
                    sendBroadcast(intent);
                    break;
                case STREAMING_AND_SDLOGGING:
                    intent.putExtra("ShimmerBluetoothAddress", macAddress);
                    intent.putExtra("ShimmerDeviceName", shimmerName);
                    intent.putExtra("ShimmerState", BT_STATE.STREAMING_AND_SDLOGGING);
                    sendBroadcast(intent);
                    break;
                case SDLOGGING:
                    shimmerDevice = btManager.getShimmerDeviceBtConnectedFromMac(macAddress);
                    mMultiShimmer.remove(macAddress);
                    if (mMultiShimmer.get(macAddress) == null) {
                        mMultiShimmer.put(macAddress, shimmerDevice);
                    }
                    Log.d("Shimmer", ((ObjectCluster) msg.obj).getMacAddress() + "  " + ((ObjectCluster) msg.obj).getShimmerName());
                    intent.putExtra("ShimmerBluetoothAddress", ((ObjectCluster) msg.obj).getMacAddress());
                    intent.putExtra("ShimmerDeviceName", ((ObjectCluster) msg.obj).getShimmerName());
                    intent.putExtra("ShimmerState", BT_STATE.SDLOGGING);
                    sendBroadcast(intent);
                    break;
                case DISCONNECTED:
                    btManager.removeShimmerDeviceBtConnected(macAddress);
                    intent.putExtra("ShimmerBluetoothAddress", macAddress);
                    intent.putExtra("ShimmerDeviceName", shimmerName);
                    intent.putExtra("ShimmerState", BT_STATE.DISCONNECTED);
                    sendBroadcast(intent);
                    break;
            }

        }
    }

    public void handleNotificationMsg(Message msg) {
    }

    public void stopStreamingAllDevices() {
        btManager.stopStreamingAllDevices();
    }

    public void startStreamingAllDevices() {
        btManager.startStreamingAllDevices();
    }

    public void setEnableLogging(boolean enableLogging, FILE_TYPE fileType) {
        setEnableLogging(enableLogging);
        mLoggingFileType = fileType;
    }

    public void setEnableLogging(boolean enableLogging, FILE_TYPE fileType, String folderName) {
        setEnableLogging(enableLogging, fileType);
        mLogFolderName = folderName;
    }

    public String getLogFolderName() {
        return mLogFolderName;
    }

    public void setLogFolderName(String folderName) {
        mLogFolderName = folderName;
    }

    public void setLoggingFileType(FILE_TYPE fileType) {
        mLoggingFileType = fileType;
    }

    public boolean getEnableLogging() {
        return mEnableLogging;
    }

    public void setEnableLogging(boolean enableLogging) {
        mEnableLogging = enableLogging;
        Log.d("Shimmer", "Logging :" + Boolean.toString(mEnableLogging));
    }

    public void setAllSampingRate(double samplingRate) {

        Collection<Object> colS = mMultiShimmer.values();
        Iterator<Object> iterator = colS.iterator();
        while (iterator.hasNext()) {
            Shimmer stemp = (Shimmer) iterator.next();
            if ((stemp.getBluetoothRadioState() == BT_STATE.CONNECTED || stemp.getBluetoothRadioState() == BT_STATE.SDLOGGING)) {
                stemp.writeShimmerAndSensorsSamplingRate(samplingRate);
                if (mPPGtoHREnabled) {
                    mPPGtoHR = new PPGtoHRAlgorithm(samplingRate, mNumberOfBeatsToAvg, true);
                    try {
                        mFilter = new Filter(Filter.LOW_PASS, samplingRate, mLPFc);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                if (mECGtoHREnabled) {
                    mECGtoHR = new ECGtoHRAdaptive(samplingRate);
                    try {
                        mLPFilterECG = new Filter(Filter.LOW_PASS, samplingRate, mLPFcECG);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    try {
                        mHPFilterECG = new Filter(Filter.HIGH_PASS, samplingRate, mHPFcECG);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

            }
        }
    }

    @Deprecated
    public void setAllAccelRange(int accelRange) {
        btManager.setAllAccelRange(accelRange);
    }

    @Deprecated
    public void setAllGSRRange(int gsrRange) {
        btManager.setAllGSRRange(gsrRange);
    }

    @Deprecated
    public void setAllEnabledSensors(int enabledSensors) {
        btManager.setAllEnabledSensors(enabledSensors);
    }

    public void setEnabledSensors(long enabledSensors, String bluetoothAddress) {
        Collection<Object> colS = mMultiShimmer.values();
        Iterator<Object> iterator = colS.iterator();
        while (iterator.hasNext()) {
            Shimmer stemp = (Shimmer) iterator.next();
            if ((stemp.getBluetoothRadioState() == BT_STATE.CONNECTED || stemp.getBluetoothRadioState() == BT_STATE.SDLOGGING) && stemp.getBluetoothAddress().equals(bluetoothAddress)) {

                if (((enabledSensors & Shimmer.SENSOR_EXG1_16BIT) > 0 && (enabledSensors & Shimmer.SENSOR_EXG2_16BIT) > 0) || ((enabledSensors & Shimmer.SENSOR_EXG1_24BIT) > 0 && (enabledSensors & Shimmer.SENSOR_EXG2_24BIT) > 0)) {

                } else {
                    mECGtoHREnabled = false;
                }

                if (stemp.getInternalExpPower() == 1 && (((enabledSensors & Shimmer.SENSOR_INT_ADC_A1) > 0) || ((enabledSensors & Shimmer.SENSOR_INT_ADC_A12) > 0) | ((enabledSensors & Shimmer.SENSOR_INT_ADC_A13) > 0) || ((enabledSensors & Shimmer.SENSOR_INT_ADC_A14) > 0))) {

                } else {
                    mPPGtoHREnabled = false;
                }
                stemp.writeEnabledSensors(enabledSensors);
            }
        }
    }

    @Deprecated
    public void writePMux(String bluetoothAddress, int setBit) {
        btManager.writePMux(bluetoothAddress, setBit);
    }

    @Deprecated
    public void write5VReg(String bluetoothAddress, int setBit) {
        btManager.write5VReg(bluetoothAddress, setBit);
    }

    @Deprecated
    public List<String[]> getListofEnabledSensorSignals(String bluetoothAddress) {
        List<String[]> listofSensors = new ArrayList<String[]>();
        listofSensors = btManager.getListofEnabledSensorSignals(bluetoothAddress);
        return listofSensors;
    }

    @Deprecated
    public long getEnabledSensors(String bluetoothAddress) {
        long enabledSensors = btManager.getEnabledSensors(bluetoothAddress);
        return enabledSensors;
    }

    public void writeSamplingRate(String bluetoothAddress, double samplingRate) {
        Collection<Object> colS = mMultiShimmer.values();
        Iterator<Object> iterator = colS.iterator();
        while (iterator.hasNext()) {
            Shimmer stemp = (Shimmer) iterator.next();
            if ((stemp.getBluetoothRadioState() == BT_STATE.CONNECTED || stemp.getBluetoothRadioState() == BT_STATE.SDLOGGING) && stemp.getBluetoothAddress().equals(bluetoothAddress)) {
                stemp.writeShimmerAndSensorsSamplingRate(samplingRate);
                if (mPPGtoHREnabled) {
                    mPPGtoHR = new PPGtoHRAlgorithm(samplingRate, mNumberOfBeatsToAvg, true);
                    try {
                        mFilter = new Filter(Filter.LOW_PASS, samplingRate, mLPFc);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                if (mECGtoHREnabled) {
                    mECGtoHR = new ECGtoHRAdaptive(samplingRate);
                    try {
                        mLPFilterECG = new Filter(Filter.LOW_PASS, samplingRate, mLPFcECG);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    try {
                        mHPFilterECG = new Filter(Filter.HIGH_PASS, samplingRate, mHPFcECG);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    @Deprecated
    public void writeAccelRange(String bluetoothAddress, int accelRange) {
        btManager.writeAccelRange(bluetoothAddress, accelRange);
    }

    @Deprecated
    public void writeGyroRange(String bluetoothAddress, int range) {
        btManager.writeGyroRange(bluetoothAddress, range);
    }

    @Deprecated
    public void writePressureResolution(String bluetoothAddress, int resolution) {
        btManager.writePressureResolution(bluetoothAddress, resolution);
    }

    @Deprecated
    public void writeMagRange(String bluetoothAddress, int range) {
        btManager.writeMagRange(bluetoothAddress, range);
    }

    @Deprecated
    public void writeGSRRange(String bluetoothAddress, int gsrRange) {
        btManager.writeGSRRange(bluetoothAddress, gsrRange);
    }

    @Deprecated
    public double getSamplingRate(String bluetoothAddress) {
        double SRate = -1;
        SRate = btManager.getSamplingRate(bluetoothAddress);
        return SRate;
    }

    @Deprecated
    public int getAccelRange(String bluetoothAddress) {
        return btManager.getAccelRange(bluetoothAddress);
    }

    @Deprecated
    public BT_STATE getShimmerState(String bluetoothAddress) {
        BT_STATE status = BT_STATE.DISCONNECTED;
        status = btManager.getShimmerState(bluetoothAddress);
        return status;
    }

    @Deprecated
    public int getGSRRange(String bluetoothAddress) {
        return btManager.getGSRRange(bluetoothAddress);
    }

    @Deprecated
    public int get5VReg(String bluetoothAddress) {
        int fiveVReg = -1;
        fiveVReg = btManager.get5VReg(bluetoothAddress);
        return fiveVReg;
    }

    @Deprecated
    public boolean isLowPowerMagEnabled(String bluetoothAddress) {
        boolean enabled = false;
        enabled = btManager.isLowPowerMagEnabled(bluetoothAddress);
        return enabled;
    }

    @Deprecated
    public int getpmux(String bluetoothAddress) {
        int pmux = -1;
        pmux = btManager.getpmux(bluetoothAddress);
        return pmux;
    }

    public void startStreaming(String bluetoothAddress) throws Exception {
        btManager.startStreaming(bluetoothAddress);
    }

    @Deprecated
    public void startLogging(String bluetoothAddress) {
        btManager.startLogging(bluetoothAddress);
    }

/*	TODO: Remove this?
	public void startStreaming(String bluetoothAddress) {
				Collection<Object> colS=mMultiShimmer.values();
				Iterator<Object> iterator = colS.iterator();
				while (iterator.hasNext()) {
					ShimmerDevice stemp=(ShimmerDevice) iterator.next();
					String address = stemp.getMacId();
					address = address.replace(":","");
					bluetoothAddress = bluetoothAddress.replace(":","");
					if ((stemp.getBluetoothRadioState()==BT_STATE.CONNECTED || stemp.getBluetoothRadioState()==BT_STATE.SDLOGGING) && address.equals(bluetoothAddress)){
						if (mPPGtoHREnabled){
							enablePPGtoHR(stemp.getMacId(),true);
						}
						if (mECGtoHREnabled){
							enableECGtoHR(stemp.getMacId(),true);
						}
						if (stemp instanceof Shimmer) {
							((Shimmer)stemp).startStreaming();
						} else if (stemp instanceof Shimmer4Android){
						}
					}
				}
	}
*/

    @Deprecated
    public void stopLogging(String bluetoothAddress) {
        btManager.stopLogging(bluetoothAddress);
    }

    @Deprecated
    public void startLogAndStreaming(String bluetoothAddress) {
        btManager.startLoggingAndStreaming(bluetoothAddress);
    }

    @Deprecated
    public long sensorConflictCheckandCorrection(String bluetoothAddress, long enabledSensors, int sensorToCheck) {
        long newSensorBitmap = 0;
        newSensorBitmap = btManager.sensorConflictCheckandCorrection(bluetoothAddress, enabledSensors, sensorToCheck);
        return newSensorBitmap;
    }

    @Deprecated
    public List<String> getListofEnabledSensors(String bluetoothAddress) {
        List<String> listofSensors = null;
        listofSensors = btManager.getListofEnabledSensors(bluetoothAddress);
        return listofSensors;
    }

    @Deprecated
    public boolean bluetoothAddressComparator(String bluetoothAddress, String address) {
        return btManager.bluetoothAddressComparator(bluetoothAddress, address);
    }

    public void stopStreaming(String bluetoothAddress) throws Exception {
        btManager.stopStreaming(bluetoothAddress);
    }

    @Deprecated
    public void setBlinkLEDCMD(String bluetoothAddress) {
        btManager.setBlinkLEDCMD(bluetoothAddress);
    }

/*	TODO: Remove this?
	public void stopStreaming(String bluetoothAddress) {
				Collection<Object> colS=mMultiShimmer.values();
				Iterator<Object> iterator = colS.iterator();
				while (iterator.hasNext()) {
					ShimmerDevice stemp=(ShimmerDevice) iterator.next();
					String address = stemp.getMacId();
					address = address.replace(":","");
					bluetoothAddress = bluetoothAddress.replace(":","");
					if ((stemp.getBluetoothRadioState()==BT_STATE.STREAMING || stemp.getBluetoothRadioState()==BT_STATE.STREAMING_AND_SDLOGGING) && address.equals(bluetoothAddress)){
						if (stemp instanceof Shimmer){
							((Shimmer)stemp).stopStreaming();
						} else if (stemp instanceof Shimmer4Android){
						}
						if (mPlotManager!=null){
							mPlotManager.removeAllSignals();
						}

					}
				}
	}
*/

    @Deprecated
    public void enableLowPowerMag(String bluetoothAddress, boolean enable) {
        btManager.enableLowPowerMag(bluetoothAddress, enable);
    }

    @Deprecated
    public void setBattLimitWarning(String bluetoothAddress, double limit) {
        btManager.setBattLimitWarning(bluetoothAddress, limit);
    }

    @Deprecated
    public double getBattLimitWarning(String bluetoothAddress) {
        return btManager.getBattLimitWarning(bluetoothAddress);
    }

    @Deprecated
    public double getPacketReceptionRate(String bluetoothAddress) {
        double rate = btManager.getPacketReceptionRate(bluetoothAddress);
        return rate;
    }

    public void disconnectShimmer(String bluetoothAddress) {
        Collection<Object> colS = mMultiShimmer.values();
        Iterator<Object> iterator = colS.iterator();
        while (iterator.hasNext()) {
            Shimmer stemp = (Shimmer) iterator.next();
            if ((stemp.getBluetoothRadioState() == BT_STATE.CONNECTED || stemp.getBluetoothRadioState() == BT_STATE.SDLOGGING) && stemp.getBluetoothAddress().equals(bluetoothAddress)) {
                stemp.stop();

            }
        }
        mLogShimmer.remove(bluetoothAddress);
        mMultiShimmer.remove(bluetoothAddress);
    }

    public Handler getGraphHandler() {
        return mHandlerGraph;
    }

    public void setGraphHandler(Handler handler) {
        mHandlerGraph = handler;
    }

    public void addHandlerToList(Handler handler) {
        mHandlerList.add(handler);
    }

    public void enableGraphingHandler(boolean setting) {
        mGraphing = setting;
    }

    @Deprecated
    public boolean DevicesConnected(String bluetoothAddress) {
        return btManager.DevicesConnected(bluetoothAddress);
    }

    @Deprecated
    public boolean DeviceIsLogging(String bluetoothAddress) {
        return btManager.DeviceIsLogging(bluetoothAddress);
    }

    @Deprecated
    public boolean DeviceIsStreaming(String bluetoothAddress) {
        return btManager.DeviceIsStreaming(bluetoothAddress);
    }

    public void setLoggingName(String name) {
        mLogFileName = name;
    }

    public void closeAndRemoveFile(String bluetoothAddress) {
        if (mLogShimmer.get(bluetoothAddress) != null) {
            mLogShimmer.get(bluetoothAddress).closeFile();

            try {
                MediaScannerConnection.scanFile(this, new String[]{mLogShimmer.get(bluetoothAddress).getAbsoluteName()}, null, null);
            } catch (Exception e) {
                System.out.println(e);
            }
            mLogShimmer.remove(bluetoothAddress);

        }
    }

    @Deprecated
    public String getFWVersion(String bluetoothAddress) {
        return btManager.getFWVersion(bluetoothAddress);
    }

    @Deprecated
    public int getShimmerVersion(String bluetoothAddress) {
        return btManager.getShimmerVersion(bluetoothAddress);
    }

    public ShimmerDevice getShimmer(String bluetoothAddress) {
        return btManager.getShimmer(bluetoothAddress);
    }

    public void test() {
        Log.d("ShimmerTest", "Test");
    }

    public boolean isEXGUsingTestSignal24Configuration(String bluetoothAddress) {

        Shimmer tmp = (Shimmer) mMultiShimmer.get(bluetoothAddress);
        return tmp.isEXGUsingTestSignal24Configuration();
    }

    public boolean isEXGUsingTestSignal16Configuration(String bluetoothAddress) {

        Shimmer tmp = (Shimmer) mMultiShimmer.get(bluetoothAddress);
        return tmp.isEXGUsingTestSignal16Configuration();
    }

    public boolean isEXGUsingECG24Configuration(String bluetoothAddress) {

        Shimmer tmp = (Shimmer) mMultiShimmer.get(bluetoothAddress);
        return tmp.isEXGUsingECG24Configuration();
    }

    public boolean isEXGUsingECG16Configuration(String bluetoothAddress) {

        Shimmer tmp = (Shimmer) mMultiShimmer.get(bluetoothAddress);
        return tmp.isEXGUsingECG16Configuration();
    }

    public boolean isEXGUsingEMG24Configuration(String bluetoothAddress) {

        Shimmer tmp = (Shimmer) mMultiShimmer.get(bluetoothAddress);
        return tmp.isEXGUsingEMG24Configuration();
    }

    public boolean isEXGUsingEMG16Configuration(String bluetoothAddress) {

        Shimmer tmp = (Shimmer) mMultiShimmer.get(bluetoothAddress);
        return tmp.isEXGUsingEMG16Configuration();
    }

    @Deprecated
    public void writeEXGSetting(String bluetoothAddress, int setting) {
        btManager.writeEXGSetting(bluetoothAddress, setting);
    }

    private String fromMilisecToDate(long miliseconds) {

        String date = "";
        Date dateToParse = new Date(miliseconds);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        date = dateFormat.format(dateToParse);

        return date;
    }

    @Deprecated
    public boolean isUsingLogAndStreamFW(String bluetoothAddress) {
        return btManager.isUsingLogAndStreamFW(bluetoothAddress);
    }

    @Deprecated
    public void readStatusLogAndStream(String bluetoothAddress) {
        btManager.readStatusLogAndStream(bluetoothAddress);
    }

    @Deprecated
    public boolean isSensing(String bluetoothAddress) {
        return btManager.isSensing(bluetoothAddress);
    }

    @Deprecated
    public boolean isDocked(String bluetoothAddress) {
        return btManager.isDocked(bluetoothAddress);
    }

    public ShimmerBluetoothManagerAndroid getBluetoothManager() {
        return btManager;
    }

    public List<ShimmerDevice> getListOfConnectedDevices() {
        return btManager.getListOfConnectedDevices();
    }

    public void configureShimmer(ShimmerDevice device) {
        btManager.configureShimmer(device);
    }

    public void configureShimmers(List<ShimmerDevice> listOfShimmers) {
        btManager.configureShimmers(listOfShimmers);
    }

    public Handler getHandler() {
        return mHandler;
    }

    public void createNewBluetoothManager() {

        try {
            btManager = new ShimmerBluetoothManagerAndroid(this, mHandler);
        } catch (Exception e) {
            Log.e(TAG, "ERROR! " + e);
            Toast.makeText(this, "Error! Could not create Bluetooth Manager!", Toast.LENGTH_LONG).show();
        }

    }

    public PlotManagerAndroid getPlotManager() {
        return mPlotManager;
    }

    public enum FILE_TYPE {
        DAT("dat", 0),
        CSV("csv", 1);

        private String fileName;
        private int fileTypeOrder;

        FILE_TYPE(String fileName, int fileType) {
            this.fileName = fileName;
            this.fileTypeOrder = fileType;
        }

        public String getName() {
            return fileName;
        }

        public int getFileTypeOrder() {
            return fileTypeOrder;
        }
    }

    public class LocalBinder extends Binder {
        public ShimmerService getService() {
            return ShimmerService.this;
        }
    }


}
