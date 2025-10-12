
package com.shimmerresearch.pcDriver;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Arrays;

import com.shimmerresearch.bluetooth.BluetoothProgressReportPerCmd;
import com.shimmerresearch.bluetooth.BluetoothProgressReportPerDevice;
import com.shimmerresearch.bluetooth.ShimmerBluetooth;
import com.shimmerresearch.comms.serialPortInterface.AbstractSerialPortHal;
import com.shimmerresearch.driver.Configuration.COMMUNICATION_TYPE;
import com.shimmerresearch.driver.Configuration.Shimmer3;
import com.shimmerresearch.driver.shimmer2r3.ConfigByteLayoutShimmer3;
import com.shimmerresearch.driverUtilities.SensorDetails;
import com.shimmerresearch.driverUtilities.UtilShimmer;
import com.shimmerresearch.driverUtilities.ShimmerVerDetails.HW_ID;
import com.shimmerresearch.exceptions.ShimmerException;
import com.shimmerresearch.sensors.SensorEXG;
import com.shimmerresearch.sensors.SensorGSR;
import com.shimmerresearch.sensors.lsm303.SensorLSM303;
import com.shimmerresearch.sensors.mpu9x50.SensorMPU9X50;
import com.shimmerresearch.shimmer3.communication.ByteCommunication;
import com.shimmerresearch.shimmer3.communication.ByteCommunicationJSSC;
import com.shimmerresearch.shimmerConfig.FixedShimmerConfigs.FIXED_SHIMMER_CONFIG_MODE;
import com.shimmerresearch.driver.CallbackObject;
import com.shimmerresearch.driver.ObjectCluster;
import com.shimmerresearch.driver.ShimmerDeviceCallbackAdapter;
import com.shimmerresearch.driver.ShimmerMsg;

import jssc.SerialPort;
import jssc.SerialPortException;
import jssc.SerialPortTimeoutException;

public class ShimmerPC extends ShimmerBluetooth implements Serializable {

        private static final long serialVersionUID = -5927054314345918072L;
    public static boolean CONSOLE_PRINT_TX_RX_BYTES = false;
    public BluetoothProgressReportPerDevice progressReportPerDevice;
    public String message;
    protected transient ByteCommunication mByteCommunication;

    protected transient ShimmerDeviceCallbackAdapter mDeviceCallbackAdapter = new ShimmerDeviceCallbackAdapter(this);
    ObjectCluster objectClusterTemp = null;

    private boolean mTesting = false;

    public ShimmerPC() {
        super();
    }

        public ShimmerPC(String comPort) {
        this();
        setComPort(comPort);
        setSamplingRateShimmer(128);
    }

        @Deprecated
    public ShimmerPC(String myName, Boolean continousSync) {
        this();
        setShimmerUserAssignedName(myName);
        setContinuousSync(continousSync);

        setSamplingRateShimmer(128);
    }

        public ShimmerPC(String comPort, String myBluetoothAddress, String myName, Boolean continousSync) {
        this(myName, continousSync);
        setComPort(comPort);
        mMyBluetoothAddress = myBluetoothAddress;
    }

    /**
     * Shimmer 3 Constructor
     *
     * @param myname              To allow the user to set a unique identifier for each Shimmer device
     * @param samplingRate        Defines the sampling rate
     * @param accelRange          Defines the Acceleration range. Valid range setting values for the Shimmer 2 are 0 (+/- 1.5g), 1 (+/- 2g), 2 (+/- 4g) and 3 (+/- 6g). Valid range setting values for the Shimmer 2r are 0 (+/- 1.5g) and 3 (+/- 6g).
     * @param gsrRange            Numeric value defining the desired gsr range. Valid range settings are 0 (10kOhm to 56kOhm),  1 (56kOhm to 220kOhm), 2 (220kOhm to 680kOhm), 3 (680kOhm to 4.7MOhm) and 4 (Auto Range).
     * @param setEnabledSensors   Defines the sensors to be enabled (e.g. 'Shimmer.SENSOR_ACCEL|Shimmer.SENSOR_GYRO' enables the Accelerometer and Gyroscope)
     * @param countiousSync       A boolean value defining whether received packets should be checked continuously for the correct start and end of packet.
     * @param enableLowPowerAccel Enables low power Accel on the wide range accelerometer
     * @param enableLowPowerGyro  Enables low power Gyro
     * @param enableLowPowerMag   Enables low power Mag
     * @param gyroRange           Sets the Gyro Range of the accelerometer
     * @param magRange            Sets the Mag Range
     * @param exg1                Sets the register of EXG chip 1
     * @param exg2                Setes the register of EXG chip 2
     */
    @Deprecated //no longer allowed to enable low power
    public ShimmerPC(String userAssignedName, double samplingRate, int accelRange, int gsrRange, int setEnabledSensors, boolean continousSync, boolean enableLowPowerAccel, boolean enableLowPowerGyro, boolean enableLowPowerMag, int gyroRange, int magRange, byte[] exg1, byte[] exg2, int orientation) {
        super(userAssignedName, samplingRate, null, accelRange, gsrRange, gyroRange, magRange);
        setContinuousSync(continousSync);


        setupOrientation(orientation, samplingRate);

        addFixedShimmerConfig(Shimmer3.GuiLabelConfig.ENABLED_SENSORS, setEnabledSensors);

        addFixedShimmerConfig(SensorLSM303.GuiLabelConfig.LSM303_MAG_LPM, enableLowPowerMag);
        addFixedShimmerConfig(SensorLSM303.GuiLabelConfig.LSM303_ACCEL_LPM, enableLowPowerAccel);

        addFixedShimmerConfig(SensorMPU9X50.GuiLabelConfig.MPU9X50_GYRO_LPM, enableLowPowerGyro);
//
        addFixedShimmerConfig(SensorEXG.GuiLabelConfig.EXG_BYTES, Arrays.asList(exg1, exg2));

    }

    /**
     * Shimmer 3 Constructor
     *
     * @param myname            To allow the user to set a unique identifier for each Shimmer device
     * @param samplingRate      Defines the sampling rate
     * @param accelRange        Defines the Acceleration range. Valid range setting values for the Shimmer 2 are 0 (+/- 1.5g), 1 (+/- 2g), 2 (+/- 4g) and 3 (+/- 6g). Valid range setting values for the Shimmer 2r are 0 (+/- 1.5g) and 3 (+/- 6g).
     * @param gsrRange          Numeric value defining the desired gsr range. Valid range settings are 0 (10kOhm to 56kOhm),  1 (56kOhm to 220kOhm), 2 (220kOhm to 680kOhm), 3 (680kOhm to 4.7MOhm) and 4 (Auto Range).
     * @param setEnabledSensors Defines the sensors to be enabled (e.g. 'Shimmer.SENSOR_ACCEL|Shimmer.SENSOR_GYRO' enables the Accelerometer and Gyroscope)
     * @param gyroRange         Sets the Gyro Range of the accelerometer
     * @param magRange          Sets the Mag Range
     */
    public ShimmerPC(String userAssignedName, double samplingRate, int accelRange, int gsrRange, Integer[] sensorIdsToEnable, int gyroRange, int magRange, int orientation, int pressureResolution) {
        super(userAssignedName, samplingRate, sensorIdsToEnable, accelRange, gsrRange, gyroRange, magRange, pressureResolution);
        setupOrientation(orientation, samplingRate);
    }

    /**
     * Shimmer2, Constructor. Prepares a new Bluetooth session. Additional fields allows the device to be set up immediately.
     *
     * @param myname            To allow the user to set a unique identifier for each Shimmer device
     * @param samplingRate      Defines the sampling rate
     * @param accelRange        Defines the Acceleration range. Valid range setting values for the Shimmer 2 are 0 (+/- 1.5g), 1 (+/- 2g), 2 (+/- 4g) and 3 (+/- 6g). Valid range setting values for the Shimmer 2r are 0 (+/- 1.5g) and 3 (+/- 6g).
     * @param gsrRange          Numeric value defining the desired gsr range. Valid range settings are 0 (10kOhm to 56kOhm),  1 (56kOhm to 220kOhm), 2 (220kOhm to 680kOhm), 3 (680kOhm to 4.7MOhm) and 4 (Auto Range).
     * @param setEnabledSensors Defines the sensors to be enabled (e.g. 'Shimmer.SENSOR_ACCEL|Shimmer.SENSOR_GYRO' enables the Accelerometer and Gyroscope)
     * @param countiousSync     A boolean value defining whether received packets should be checked continuously for the correct start and end of packet.
     * @param magGain           Set mag gain
     */
    @Deprecated //because continousSync does nothing
    public ShimmerPC(String myName, double samplingRate, int accelRange, int gsrRange, int setEnabledSensors, boolean continousSync, int magGain, int orientation) {
        super(myName, samplingRate, setEnabledSensors, accelRange, gsrRange, magGain);


        setupOrientation(orientation, samplingRate);

    }

    /**
     * Shimmer2, Constructor. Prepares a new Bluetooth session. Additional fields allows the device to be set up immediately. Note enabled sensors are only for legacy Shimmer2, no sensor map is supported
     *
     * @param myname            To allow the user to set a unique identifier for each Shimmer device
     * @param samplingRate      Defines the sampling rate
     * @param accelRange        Defines the Acceleration range. Valid range setting values for the Shimmer 2 are 0 (+/- 1.5g), 1 (+/- 2g), 2 (+/- 4g) and 3 (+/- 6g). Valid range setting values for the Shimmer 2r are 0 (+/- 1.5g) and 3 (+/- 6g).
     * @param gsrRange          Numeric value defining the desired gsr range. Valid range settings are 0 (10kOhm to 56kOhm),  1 (56kOhm to 220kOhm), 2 (220kOhm to 680kOhm), 3 (680kOhm to 4.7MOhm) and 4 (Auto Range).
     * @param setEnabledSensors Defines the sensors to be enabled (e.g. 'Shimmer.SENSOR_ACCEL|Shimmer.SENSOR_GYRO' enables the Accelerometer and Gyroscope)
     * @param magGain           Set mag gain
     */
    public ShimmerPC(String myName, double samplingRate, int accelRange, int gsrRange, int setEnabledSensors, int magGain, int orientation) {
        super(myName, samplingRate, setEnabledSensors, accelRange, gsrRange, magGain);
        setupOrientation(orientation, samplingRate);
    }


        @Deprecated
    public ShimmerPC(String myName, double samplingRate, int accelRange, int gsrRange, int setEnabledSensors, boolean continousSync) {
        this(myName, continousSync);



        setFixedShimmerConfig(FIXED_SHIMMER_CONFIG_MODE.USER);
        addFixedShimmerConfig(Shimmer3.GuiLabelConfig.SHIMMER_AND_SENSORS_SAMPLING_RATE, samplingRate);
        addFixedShimmerConfig(Shimmer3.GuiLabelConfig.ENABLED_SENSORS, setEnabledSensors);
        addFixedShimmerConfig(SensorLSM303.GuiLabelConfig.LSM303_ACCEL_RANGE, accelRange);
        addFixedShimmerConfig(SensorGSR.GuiLabelConfig.GSR_RANGE, gsrRange);
    }


        public ShimmerPC(String dockId, int slotNumber, COMMUNICATION_TYPE communicationType) {
        setDockInfo(dockId, slotNumber);
        addCommunicationRoute(communicationType);
        setSamplingRateShimmer(128);
    }

        public ShimmerPC(String dockId, int slotNumber, String macId, COMMUNICATION_TYPE communicationType) {
        this(dockId, slotNumber, communicationType);
        setMacIdFromUart(macId);
    }


    public void setTestRadio(ByteCommunication radio) {
        mTesting = true;
        mByteCommunication = radio;
    }

        @Override
    public synchronized void connect(final String address, String unusedVariable) {

        Thread thread = new Thread() {
            public void run() {
                setBluetoothRadioState(BT_STATE.CONNECTING);

                startTimerConnectingTimeout();

                setIamAlive(false);
                getListofInstructions().clear();

                if (mByteCommunication == null || mTesting) {
                    setComPort(address);
                    if (!mTesting) {
                        mByteCommunication = new ByteCommunicationJSSC(address);
                    } else { // do nothingit should already be set

                    }

                    try {
						                        consolePrintLn("Connecting to Shimmer on " + address);
                        mByteCommunication.openPort();
                        consolePrintLn("Port Status : " + Boolean.toString(mByteCommunication.isOpened()));
                        if (mIOThread != null) {
                            mIOThread = null;
                            mPThread = null;
                        }

                        if (mByteCommunication.isOpened() && mBluetoothRadioState != BT_STATE.DISCONNECTED) {
                            setIsConnected(true);

                            mIOThread = new IOThread();
                            mIOThread.setName(getClass().getSimpleName() + "-" + mMyBluetoothAddress + "-" + getComPort());
                            mIOThread.start();

                            if (mUseProcessingThread) {
                                mPThread = new ProcessingThread();
                                mPThread.start();
                            }
                            initialize();
                        } else {
                            disconnectNoException();
                        }
                    } catch (SerialPortException ex) {
                        consolePrintException(ex.getMessage(), ex.getStackTrace());

                        disconnectNoException();
                        setBluetoothRadioState(BT_STATE.CONNECTION_FAILED);
                    }
                } else {
                }

            }

        };

        thread.setName("ShimmerPC-" + getMacId() + "-" + mShimmerUserAssignedName);

        if (!isConnected()) {
            thread.start();
        }

    }

    @Override
    public boolean bytesAvailableToBeRead() {
        try {
            if (mByteCommunication != null) {
                if (mByteCommunication.getInputBufferBytesCount() != 0) {
                    return true;
                }
            }
        } catch (SerialPortException | NullPointerException ex) {
            consolePrintException(ex.getMessage(), ex.getStackTrace());

            connectionLost();
        }
        return false;
    }

    public int availableBytes() {
        try {
            if (mByteCommunication != null) {
                return mByteCommunication.getInputBufferBytesCount();
            } else {
                return 0;
            }
        } catch (SerialPortException | NullPointerException ex) {
            consolePrintException(ex.getMessage(), ex.getStackTrace());
            connectionLost();
            return 0;
        }
    }

    @Override
    public void writeBytes(byte[] data) {
        try {
            if (mByteCommunication != null) {
                if (CONSOLE_PRINT_TX_RX_BYTES) {
                    consolePrintLn("TX: " + UtilShimmer.bytesToHexStringWithSpacesFormatted(data));
                }
                mByteCommunication.writeBytes(data);
            }
        } catch (SerialPortException | NullPointerException ex) {
            consolePrintLn("Tried to writeBytes but port is closed");
            consolePrintException(ex.getMessage(), ex.getStackTrace());
            connectionLost();
        }
    }

    @Override
    protected byte[] readBytes(int numberOfBytes) {
        if (numberOfBytes <= 0) {
            consolePrintLn("Tried to readBytes but numberOfBytes is a negative number");
            return null;
        }

        try {
            if (mByteCommunication != null) {
                if (mByteCommunication.isOpened()) {
                    byte[] data = mByteCommunication.readBytes(numberOfBytes, AbstractSerialPortHal.SERIAL_PORT_TIMEOUT_2000);
                    if (CONSOLE_PRINT_TX_RX_BYTES) {
                        consolePrintLn("RX: " + UtilShimmer.bytesToHexStringWithSpacesFormatted(data));
                    }
                    return (data);
                } else {
                    consolePrintLn("Tried to readBytes but port is closed");
                }
            }
        } catch (SerialPortException | NullPointerException e) {
            connectionLost();
            consolePrintLn("Tried to readBytes but serial port error");
            consolePrintException(e.getMessage(), e.getStackTrace());
        } catch (SerialPortTimeoutException e) {
            consolePrintLn("Tried to readBytes but serial port timed out");
            consolePrintException(e.getMessage(), e.getStackTrace());

            if (mBluetoothRadioState == BT_STATE.CONNECTING
                    || mBluetoothRadioState == BT_STATE.CONFIGURING) {
                connectionLost();
            }
        }
        return null;
    }

    @Override
    protected byte readByte() {
        byte[] b = readBytes(1);
        return b[0];
    }

    @Override
    protected void inquiryDone() {
        mDeviceCallbackAdapter.inquiryDone();
        isReadyForStreaming();

    }

    @Override
    protected void isReadyForStreaming() {
        mDeviceCallbackAdapter.isReadyForStreaming();
        restartTimersIfNull();

//
//
//
    }

    @Override
    public void calculatePacketReceptionRateCurrent(int intervalMs) {
        super.calculatePacketReceptionRateCurrent(intervalMs);
        mDeviceCallbackAdapter.sendCallbackPacketReceptionRateCurrent();
    }

    @Override
    protected void dataHandler(ObjectCluster ojc) {
        mDeviceCallbackAdapter.dataHandler(ojc);
    }

    public byte[] returnRawData() {
        if (objectClusterTemp != null) {
            byte[] data = objectClusterTemp.mRawData;
            return data;

        } else
            return null;
    }

    @Override
    @Deprecated //Use disconnect() instead
    public void stop() {
        disconnectNoException();
    }

    @Override
    public void disconnect() throws ShimmerException {
        stopAllTimers();
        closeConnection();
        setBluetoothRadioState(BT_STATE.DISCONNECTED);
    }

    public void disconnectNoException() {
        try {
            disconnect();
        } catch (ShimmerException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void connectionLost() {
        disconnectNoException();
        setBluetoothRadioState(BT_STATE.CONNECTION_LOST);
    }

    private void closeConnection() {
        try {
            if (mIOThread != null) {
                mIOThread.stop = true;

                while (mIOThread != null && mIOThread.isAlive()) ;

                mIOThread = null;

                if (mUseProcessingThread) {
                    mPThread.stop = true;
                    mPThread = null;
                }
            }
            mIsStreaming = false;
            mIsInitialised = false;

            setBluetoothRadioState(BT_STATE.DISCONNECTED);
            if (mByteCommunication != null) {
                if (mByteCommunication.isOpened()) {
                    mByteCommunication.purgePort(1);
                    mByteCommunication.purgePort(2);
                    mByteCommunication.closePort();
                }
            }
            mByteCommunication = null;
        } catch (Exception ex) {
            consolePrintException(ex.getMessage(), ex.getStackTrace());
            setBluetoothRadioState(BT_STATE.DISCONNECTED);
        }
    }

    public void setSerialPort(SerialPort sp) {
        if (mByteCommunication == null) {
            mByteCommunication = new ByteCommunicationJSSC(sp);
        }
        if (mByteCommunication instanceof ByteCommunicationJSSC) {
            ((ByteCommunicationJSSC) mByteCommunication).setSerialPort(sp);
            getSamplingRateShimmer();

            if (mByteCommunication.isOpened()) {
                setBluetoothRadioState(BT_STATE.CONNECTING);
            }
            if (mByteCommunication.isOpened() && mBluetoothRadioState != BT_STATE.DISCONNECTED) {
                setIsConnected(true);

                mIOThread = new IOThread();
                mIOThread.start();
                if (mUseProcessingThread) {
                    mPThread = new ProcessingThread();
                    mPThread.start();
                }
                initialize();
            }
        }
    }

    @Override
    protected void sendStatusMSGtoUI(String msg) {

    }

    @Override
    protected void sendStatusMsgPacketLossDetected() {

    }

    @Override
    protected void printLogDataForDebugging(String msg) {
        consolePrintLn(msg);
    }

    @Override
    public boolean setBluetoothRadioState(BT_STATE state) {
        boolean isChanged = super.setBluetoothRadioState(state);
        mDeviceCallbackAdapter.setBluetoothRadioState(state, isChanged);
        return isChanged;

//
//
//
    }

    @Override
    public void startOperation(BT_STATE currentOperation) {
        this.startOperation(currentOperation, 1);
        consolePrintLn(currentOperation + " START");
    }


    @Override
    public void startOperation(BT_STATE currentOperation, int totalNumOfCmds) {
        mDeviceCallbackAdapter.startOperation(currentOperation, totalNumOfCmds);

//
//
    }

    @Override
    public void finishOperation(BT_STATE state) {
        mDeviceCallbackAdapter.finishOperation(state);

//
//
//
    }

    @Override
    protected void hasStopStreaming() {
        mDeviceCallbackAdapter.hasStopStreaming();

    }

    @Override
    protected void isNowStreaming() {
        mDeviceCallbackAdapter.isNowStreaming();

//
//
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
                if (!mIsStreaming && !isSDLogging() && isConnected() && mBluetoothRadioState != BT_STATE.CONNECTED) {
                    setBluetoothRadioState(BT_STATE.CONNECTED);
                }
//

                CallbackObject callBackObject = new CallbackObject(NOTIFICATION_SHIMMER_STATE_CHANGE, mBluetoothRadioState, getMacId(), getComPort());
                sendCallBackMsg(MSG_IDENTIFIER_STATE_CHANGE, callBackObject);
            }
        }

    }

    @Override
    protected void processMsgFromCallback(ShimmerMsg shimmerMSG) {
    }

    @Override
    protected void sendProgressReport(BluetoothProgressReportPerCmd pRPC) {
        mDeviceCallbackAdapter.sendProgressReport(pRPC);

//
//
//
    }

    @Override
    public ShimmerPC deepClone() {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(this);

            ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
            ObjectInputStream ois = new ObjectInputStream(bais);
            return (ShimmerPC) ois.readObject();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    protected void interpretDataPacketFormat(Object object, COMMUNICATION_TYPE commType) {

    }

    @Override
    protected void batteryStatusChanged() {
        mDeviceCallbackAdapter.batteryStatusChanged();

    }

    @Override
    protected void dockedStateChange() {
        mDeviceCallbackAdapter.dockedStateChange();

    }

    @Override
    public void createConfigBytesLayout() {
        if (mShimmerVerObject.mHardwareVersion == HW_ID.UNKNOWN) {
            mConfigByteLayout = new ConfigByteLayoutShimmer3(getFirmwareIdentifier(), getFirmwareVersionMajor(), getFirmwareVersionMinor(), getFirmwareVersionInternal(), HW_ID.SHIMMER_3);
        } else {
            mConfigByteLayout = new ConfigByteLayoutShimmer3(getFirmwareIdentifier(), getFirmwareVersionMajor(), getFirmwareVersionMinor(), getFirmwareVersionInternal(), mShimmerVerObject.mHardwareVersion);
        }
    }

    public boolean isChannelEnabled(int sensorKey) {
        return super.isSensorEnabled(COMMUNICATION_TYPE.BLUETOOTH, sensorKey);
    }

    @Override
    public String getSensorLabel(int sensorKey) {
        super.getSensorLabel(sensorKey);
        SensorDetails sensor = mSensorMap.get(sensorKey);
        if (sensor != null) {
            return sensor.mSensorDetailsRef.mGuiFriendlyLabel;
        }
        return null;
    }

}
