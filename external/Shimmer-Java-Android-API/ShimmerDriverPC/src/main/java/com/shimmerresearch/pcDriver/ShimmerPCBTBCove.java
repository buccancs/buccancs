package com.shimmerresearch.pcDriver;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

import javax.microedition.io.Connector;
import javax.microedition.io.StreamConnection;

import com.shimmerresearch.bluetooth.BluetoothProgressReportPerCmd;
import com.shimmerresearch.bluetooth.BluetoothProgressReportPerDevice;
import com.shimmerresearch.bluetooth.ShimmerBluetooth;
import com.shimmerresearch.bluetooth.ShimmerBluetooth.BT_STATE;
import com.shimmerresearch.bluetooth.ShimmerBluetooth.ProcessingThread;
import com.shimmerresearch.driver.Callable;
import com.shimmerresearch.driver.Configuration.COMMUNICATION_TYPE;
import com.shimmerresearch.driver.CallbackObject;
import com.shimmerresearch.driver.ObjectCluster;
import com.shimmerresearch.driver.ShimmerDevice;
import com.shimmerresearch.driver.ShimmerMsg;
import com.shimmerresearch.driverUtilities.ShimmerVerObject;


public class ShimmerPCBTBCove extends ShimmerBluetooth implements Serializable {
    public static final int MSG_IDENTIFIER_STATE_CHANGE = 0;
    public static final int MSG_IDENTIFIER_NOTIFICATION_MESSAGE = 1;
    public static final int MSG_IDENTIFIER_DATA_PACKET = 2;
    public static final int MSG_IDENTIFIER_PACKET_RECEPTION_RATE = 3;
    public static final int MSG_IDENTIFIER_PROGRESS_REPORT_PER_DEVICE = 4;
    public static final int MSG_IDENTIFIER_PROGRESS_REPORT_ALL = 5;
    public static final int MSG_IDENTIFIER_PACKET_RECEPTION_RATE_CURRENT = 6;
    public static final int NOTIFICATION_SHIMMER_STOP_STREAMING = 0;
    public static final int NOTIFICATION_SHIMMER_START_STREAMING = 1;
    public static final int NOTIFICATION_SHIMMER_FULLY_INITIALIZED = 2;
    public final static int NOTIFICATION_SHIMMER_STATE_CHANGE = 3;
    private static final long serialVersionUID = -7067087273053149229L;
    public String message;
    StreamConnection conn = null;
    ObjectCluster objectClusterTemp = null;
    DataInputStream mIN;
    OutputStream mOUT;

    public ShimmerPCBTBCove(String myName, Boolean continousSync) {
        mShimmerUserAssignedName = myName;
        setContinuousSync(continousSync);
        setSetupDeviceWhileConnecting(false);
    }

    public ShimmerPCBTBCove(String myName, double samplingRate, int accelRange, int gsrRange, int setEnabledSensors, boolean continousSync) {
        setSamplingRateShimmer(samplingRate);
        setAccelRange(accelRange);
        setGSRRange(gsrRange);
        mSetEnabledSensors = setEnabledSensors;
        mShimmerUserAssignedName = myName;
        setSetupDeviceWhileConnecting(true);
        setContinuousSync(continousSync);
    }

    /**
     * Shimmer3 Constructor. Prepares a new Bluetooth session. Additional fields allows the device to be set up immediately.
     *
     * @param context           The UI Activity Context
     * @param handler           A Handler to send messages back to the UI Activity
     * @param myname            To allow the user to set a unique identifier for each Shimmer device
     * @param samplingRate      Defines the sampling rate
     * @param accelRange        Defines the Acceleration range. Valid range setting values for the Shimmer 2 are 0 (+/- 1.5g), 1 (+/- 2g), 2 (+/- 4g) and 3 (+/- 6g). Valid range setting values for the Shimmer 2r are 0 (+/- 1.5g) and 3 (+/- 6g).
     * @param gsrRange          Numeric value defining the desired gsr range. Valid range settings are 0 (10kOhm to 56kOhm),  1 (56kOhm to 220kOhm), 2 (220kOhm to 680kOhm), 3 (680kOhm to 4.7MOhm) and 4 (Auto Range).
     * @param setEnabledSensors Defines the sensors to be enabled (e.g. 'Shimmer.SENSOR_ACCEL|Shimmer.SENSOR_GYRO' enables the Accelerometer and Gyroscope)
     * @param countiousSync     A boolean value defining whether received packets should be checked continuously for the correct start and end of packet.
     */
    public ShimmerPCBTBCove(String myName, double samplingRate, int accelRange, int gsrRange, int setEnabledSensors, boolean continousSync, boolean enableLowPowerAccel, boolean enableLowPowerGyro, boolean enableLowPowerMag, int gyroRange, int magRange, byte[] exg1, byte[] exg2) {
        mBluetoothRadioState = BT_STATE.DISCONNECTED;
        setSamplingRateShimmer(samplingRate);
        setAccelRange(accelRange);
        setGSRRange(gsrRange);
        mSetEnabledSensors = setEnabledSensors;
        mShimmerUserAssignedName = myName;
        setSetupDeviceWhileConnecting(true);
        setContinuousSync(continousSync);
        setLowPowerMag(enableLowPowerMag);
        setLowPowerAccelWR(enableLowPowerAccel);
        setLowPowerGyro(enableLowPowerGyro);
        setGyroRange(gyroRange);
        setLSM303MagRange(magRange);
        mSetupEXG = true;
        mEXG1RegisterArray = exg1;
        mEXG2RegisterArray = exg2;
    }

    /**
     * Shimmer2, Constructor.Additional fields allows the device to be set up immediately.
     *
     * @param myname            To allow the user to set a unique identifier for each Shimmer device
     * @param samplingRate      Defines the sampling rate
     * @param accelRange        Defines the Acceleration range. Valid range setting values for the Shimmer 2 are 0 (+/- 1.5g), 1 (+/- 2g), 2 (+/- 4g) and 3 (+/- 6g). Valid range setting values for the Shimmer 2r are 0 (+/- 1.5g) and 3 (+/- 6g).
     * @param gsrRange          Numeric value defining the desired gsr range. Valid range settings are 0 (10kOhm to 56kOhm),  1 (56kOhm to 220kOhm), 2 (220kOhm to 680kOhm), 3 (680kOhm to 4.7MOhm) and 4 (Auto Range).
     * @param setEnabledSensors Defines the sensors to be enabled (e.g. 'Shimmer.SENSOR_ACCEL|Shimmer.SENSOR_GYRO' enables the Accelerometer and Gyroscope)
     * @param countiousSync     A boolean value defining whether received packets should be checked continuously for the correct start and end of packet.
     */
    public ShimmerPCBTBCove(String myName, double samplingRate, int accelRange, int gsrRange, int setEnabledSensors, boolean continousSync, int magGain) {
        mBluetoothRadioState = BT_STATE.DISCONNECTED;
        setSamplingRateShimmer(samplingRate);
        setAccelRange(accelRange);
        setLSM303MagRange(magGain);
        setGSRRange(gsrRange);
        mSetEnabledSensors = setEnabledSensors;
        mShimmerUserAssignedName = myName;
        setSetupDeviceWhileConnecting(true);
        setContinuousSync(continousSync);
    }

    public synchronized void connect(final String address, String empty) {
        setIamAlive(false);
        if (conn == null) {
            mMyBluetoothAddress = address;
            getListofInstructions().clear();
            try {
                setBluetoothRadioState(BT_STATE.CONNECTING);
                conn = (StreamConnection) Connector.open(address);
                mIN = new DataInputStream(conn.openInputStream());
                mOUT = conn.openOutputStream();
                if (mIOThread != null) {
                    mIOThread = null;
                    mPThread = null;
                }
                mIOThread = new IOThread();
                mIOThread.start();
                mPThread = new ProcessingThread();
                mPThread.start();
                initialize();
                setBluetoothRadioState(BT_STATE.CONNECTED);
            } catch (IOException e) {
                System.err.print(e.toString());
                System.out.println("Connection Lost");
            }

        }
    }

    @Override
    public boolean bytesAvailableToBeRead() {

        try {
            if (mIN.available() != 0) {
                return true;
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Connection Lost");
        }

        return false;
    }

    public int availableBytes() {
        try {
            return mIN.available();
        } catch (IOException e) {
            System.out.println("Connection Lost");
            connectionLost();
            e.printStackTrace();
            return 0;
        }

    }

    @Override
    public void writeBytes(byte[] data) {
        try {
            mOUT.write(data);
        } catch (IOException e) {
            System.out.println("Connection Lost");
            connectionLost();
            e.printStackTrace();
        }
    }

    @Override
    protected byte[] readBytes(int numberofBytes) {
        byte[] b = new byte[numberofBytes];
        try {
            mIN.readFully(b, 0, numberofBytes);
            return (b);
        } catch (IOException e) {
            System.out.println("Connection Lost");
            e.printStackTrace();
        }


        return null;
    }

    @Override
    protected void stop() {
        setBluetoothRadioState(BT_STATE.DISCONNECTED);
    }

    @Override
    protected void isNowStreaming() {

        CallbackObject callBackObject = new CallbackObject(NOTIFICATION_SHIMMER_START_STREAMING, getBluetoothAddress(), mUniqueID);
        sendCallBackMsg(MSG_IDENTIFIER_NOTIFICATION_MESSAGE, callBackObject);

        if (isSDLogging()) {
            setBluetoothRadioState(BT_STATE.STREAMING_AND_SDLOGGING);
        } else {
            setBluetoothRadioState(BT_STATE.STREAMING);
        }

    }

    @Override
    protected byte readByte() {
        byte[] b = readBytes(1);
        return b[0];
    }

    @Override
    protected void inquiryDone() {
        CallbackObject callBackObject = new CallbackObject(NOTIFICATION_SHIMMER_STATE_CHANGE, mBluetoothRadioState, getBluetoothAddress(), mUniqueID);
        sendCallBackMsg(MSG_IDENTIFIER_STATE_CHANGE, callBackObject);
        isReadyForStreaming();
    }

    @Override
    protected void isReadyForStreaming() {
        mIsInitialised = true;
        sensorAndConfigMapsCreate();
        sensorMapUpdateFromEnabledSensorsVars();

        if (mSendProgressReport) {
            finishOperation(BT_STATE.CONNECTING);
        }

        CallbackObject callBackObject = new CallbackObject(NOTIFICATION_SHIMMER_FULLY_INITIALIZED, getBluetoothAddress(), mUniqueID);
        sendCallBackMsg(MSG_IDENTIFIER_NOTIFICATION_MESSAGE, callBackObject);
        if (mTimerCheckAlive == null && mTimerReadStatus == null && mTimerReadBattStatus == null) {
            startTimerCheckIfAlive();
            startTimerReadStatus();
            startTimerReadBattStatus();

        }
        setBluetoothRadioState(BT_STATE.CONNECTED);
    }

    @Override
    protected void dataHandler(ObjectCluster ojc) {
        CallbackObject callBackObject = new CallbackObject(MSG_IDENTIFIER_PACKET_RECEPTION_RATE, getBluetoothAddress(), mUniqueID, getPacketReceptionRateOverall());
        sendCallBackMsg(MSG_IDENTIFIER_PACKET_RECEPTION_RATE, callBackObject);

        sendCallBackMsg(MSG_IDENTIFIER_DATA_PACKET, ojc);
    }

    public byte[] returnRawData() {
        if (objectClusterTemp != null) {
            byte[] data = objectClusterTemp.mRawData;
            return data;

        } else
            return null;
    }

    public synchronized void disconnect() {
        stopAllTimers();

        try {
            if (mIOThread != null) {
                mIOThread.stop = true;
                mIOThread = null;
                mPThread.stop = true;
                mPThread = null;
            }
            mIsStreaming = false;
            mIsInitialised = false;
            mIN.close();
            mOUT.close();
            conn.close();
            conn = null;
            setBluetoothRadioState(BT_STATE.DISCONNECTED);
        } catch (IOException e) {
            setBluetoothRadioState(BT_STATE.DISCONNECTED);
            System.out.println("Connection Lost");
            e.printStackTrace();
        }

    }

    @Override
    protected void sendStatusMsgPacketLossDetected() {

    }

    @Override
    protected void connectionLost() {
        System.out.println("Connection Lost");
        try {
            if (mIOThread != null) {
                mIOThread.stop = true;
                mIOThread = null;
                mPThread.stop = true;
                mPThread = null;
            }
            mIsStreaming = false;
            mIsInitialised = false;
            conn.close();
            conn = null;
            setBluetoothRadioState(BT_STATE.CONNECTION_LOST);
        } catch (IOException e) {
            setBluetoothRadioState(BT_STATE.CONNECTION_LOST);
            System.out.println("Connection Lost");
            e.printStackTrace();
        }

    }

    @Override
    protected void sendStatusMSGtoUI(String msg) {

    }

    @Override
    protected void printLogDataForDebugging(String msg) {
        System.out.println(msg);
    }

    @Override
    public boolean setBluetoothRadioState(BT_STATE state) {
        boolean changed = super.setBluetoothRadioState(state);

        if (mBluetoothRadioState == BT_STATE.CONNECTED) {
            mIsInitialised = true;
            mIsStreaming = false;
        } else if (mBluetoothRadioState == BT_STATE.STREAMING) {
            mIsStreaming = true;
        } else if ((mBluetoothRadioState == BT_STATE.DISCONNECTED)
                || (mBluetoothRadioState == BT_STATE.CONNECTION_LOST)
                || (mBluetoothRadioState == BT_STATE.CONNECTION_FAILED)) {
            setIsConnected(false);
            mIsStreaming = false;
            mIsInitialised = false;
        }

        consolePrintLn("State change: " + mBluetoothRadioState.toString());

        CallbackObject callBackObject = new CallbackObject(NOTIFICATION_SHIMMER_STATE_CHANGE, mBluetoothRadioState, getMacId(), getComPort());
        sendCallBackMsg(MSG_IDENTIFIER_STATE_CHANGE, callBackObject);
        return changed;
    }

    @Override
    protected void hasStopStreaming() {
        CallbackObject callBackObject = new CallbackObject(NOTIFICATION_SHIMMER_STOP_STREAMING, getBluetoothAddress(), mUniqueID);
        sendCallBackMsg(MSG_IDENTIFIER_NOTIFICATION_MESSAGE, callBackObject);
        startTimerReadStatus();
        setBluetoothRadioState(BT_STATE.CONNECTED);
    }

    @Override
    protected void eventLogAndStreamStatusChanged(byte currentCommand) {

        if (currentCommand == STOP_LOGGING_ONLY_COMMAND) {
            if (mIsStreaming) {
                setBluetoothRadioState(BT_STATE.STREAMING);
            } else if (isConnected()) {
                setBluetoothRadioState(BT_STATE.CONNECTED);
            } else {
                setBluetoothRadioState(BT_STATE.DISCONNECTED);
            }
        } else {
            if (mIsStreaming && isSDLogging()) {
                setBluetoothRadioState(BT_STATE.STREAMING_AND_SDLOGGING);
            } else if (mIsStreaming) {
                setBluetoothRadioState(BT_STATE.STREAMING);
            } else if (isSDLogging()) {
                setBluetoothRadioState(BT_STATE.SDLOGGING);
            } else {
                if (!mIsStreaming && !isSDLogging() && isConnected()) {
                    setBluetoothRadioState(BT_STATE.CONNECTED);
                }

                CallbackObject callBackObject = new CallbackObject(NOTIFICATION_SHIMMER_STATE_CHANGE, mBluetoothRadioState, getBluetoothAddress(), mUniqueID);
                sendCallBackMsg(MSG_IDENTIFIER_STATE_CHANGE, callBackObject);
            }
        }

    }

    @Override
    protected void processMsgFromCallback(ShimmerMsg shimmerMSG) {

    }

    @Override
    protected void sendProgressReport(BluetoothProgressReportPerCmd pr) {

    }

    @Override
    protected void startOperation(BT_STATE currentOperation) {

    }

    @Override
    protected void startOperation(BT_STATE currentOperation, int totalNumOfCmds) {

    }

    @Override
    protected void batteryStatusChanged() {

    }

    @Override
    public ShimmerPCBTBCove deepClone() {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(this);

            ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
            ObjectInputStream ois = new ObjectInputStream(bais);
            return (ShimmerPCBTBCove) ois.readObject();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    protected void interpretDataPacketFormat(Object object,
                                             COMMUNICATION_TYPE commType) {

    }

    public void finishOperation(BT_STATE btState) {

    }

    @Override
    protected void dockedStateChange() {

    }

    @Override
    public void createConfigBytesLayout() {

    }

    @Override
    public String getSensorLabel(int sensorKey) {
        return null;
    }

    @Override
    public List<ShimmerVerObject> getListOfCompatibleVersionInfoForSensor(int sensorKey) {
        return null;
    }

    @Override
    public Set<Integer> getSensorIdsSet() {
        return null;
    }

}

