package com.shimmerresearch.thirdpartyDevices.noninOnyxII;

import com.shimmerresearch.bluetooth.ShimmerBluetooth;
import com.shimmerresearch.comms.serialPortInterface.AbstractSerialPortHal;
import com.shimmerresearch.comms.serialPortInterface.ByteLevelDataCommListener;
import com.shimmerresearch.comms.serialPortInterface.SerialPortListener;
import com.shimmerresearch.driver.Configuration;
import com.shimmerresearch.driver.ObjectCluster;
import com.shimmerresearch.driver.ShimmerDevice;
import com.shimmerresearch.driver.ShimmerDeviceCallbackAdapter;
import com.shimmerresearch.driver.ShimmerMsg;
import com.shimmerresearch.driver.ShimmerObject;
import com.shimmerresearch.driverUtilities.HwDriverShimmerDeviceDetails;
import com.shimmerresearch.driverUtilities.ShimmerVerObject;
import com.shimmerresearch.exceptions.ShimmerException;
import com.shimmerresearch.sensors.AbstractSensor;
import com.shimmerresearch.sensors.SensorSystemTimeStamp;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.LinkedHashMap;

/* loaded from: classes2.dex */
public class NoninOnyxIIDevice extends ShimmerDevice implements SerialPortListener {
    private static final byte NONIN_RESPONSE_ACK = 6;
    private static final byte NONIN_RESPONSE_NACK = 21;
    private static final long serialVersionUID = -4620570962788027578L;
    private static final byte[] NONIN_CMD_START_STREAMING = {2, ShimmerObject.START_SDBT_COMMAND, 2, 2, 8, 3};
    public static ShimmerVerObject SVO = new ShimmerVerObject(1000, -1, -1, -1, -1);
    public transient AbstractSerialPortHal mSerialPortComm;
    protected ShimmerDeviceCallbackAdapter mDeviceCallbackAdapter = new ShimmerDeviceCallbackAdapter(this);

    public NoninOnyxIIDevice(String str, String str2) {
        this.mComPort = str;
        this.mUniqueID = str2;
        setDefaultShimmerConfiguration();
        this.mIsTrialDetailsStoredOnDevice = false;
    }

    @Override // com.shimmerresearch.driver.ShimmerDevice
    public byte[] configBytesGenerate(boolean z, Configuration.COMMUNICATION_TYPE communication_type) {
        return null;
    }

    @Override // com.shimmerresearch.driver.ShimmerDevice
    public void configBytesParse(byte[] bArr, Configuration.COMMUNICATION_TYPE communication_type) {
    }

    @Override // com.shimmerresearch.driver.ShimmerDevice
    public void createConfigBytesLayout() {
    }

    @Override // com.shimmerresearch.driver.ShimmerDevice
    protected void interpretDataPacketFormat(Object obj, Configuration.COMMUNICATION_TYPE communication_type) {
    }

    @Override // com.shimmerresearch.driver.BasicProcessWithCallBack
    protected void processMsgFromCallback(ShimmerMsg shimmerMsg) {
    }

    @Override // com.shimmerresearch.driver.ShimmerDevice
    public void setDefaultShimmerConfiguration() {
        super.setDefaultShimmerConfiguration();
        addCommunicationRoute(Configuration.COMMUNICATION_TYPE.BLUETOOTH);
        setShimmerUserAssignedName(this.mUniqueID);
        setSamplingRateShimmer(1.0d);
        setMacIdFromUart(this.mUniqueID);
        setShimmerUserAssignedNameWithMac(HwDriverShimmerDeviceDetails.SH_SEARCH.BT.MANUFACTURER_NONIN);
        setShimmerVersionObjectAndCreateSensorMap(SVO);
    }

    @Override // com.shimmerresearch.driver.ShimmerDevice
    public void connect() throws ShimmerException {
        try {
            AbstractSerialPortHal abstractSerialPortHal = this.mSerialPortComm;
            if (abstractSerialPortHal != null) {
                abstractSerialPortHal.connect();
            }
        } catch (ShimmerException e) {
            consolePrintLn("Failed to BT connect");
            consolePrintLn(e.getErrStringFormatted());
        }
    }

    @Override // com.shimmerresearch.driver.ShimmerDevice
    public void disconnect() throws ShimmerException {
        try {
            AbstractSerialPortHal abstractSerialPortHal = this.mSerialPortComm;
            if (abstractSerialPortHal != null) {
                abstractSerialPortHal.disconnect();
            }
        } catch (ShimmerException e) {
            consolePrintLn("Failed to BT disconnect");
            e.printStackTrace();
        }
    }

    @Override // com.shimmerresearch.driver.ShimmerDevice
    public void startStreaming() {
        try {
            this.mSerialPortComm.txBytes(NONIN_CMD_START_STREAMING);
            threadSleep(1000L);
            byte[] bArrRxBytes = this.mSerialPortComm.rxBytes(1);
            if (bArrRxBytes.length > 0) {
                byte b = bArrRxBytes[0];
                if (b == 6) {
                    System.out.println("Nonin ACK received");
                    setBluetoothRadioState(ShimmerBluetooth.BT_STATE.STREAMING);
                } else if (b == 21) {
                    System.out.println("Nonin NACK received");
                    disconnect();
                }
            }
        } catch (ShimmerException e) {
            consolePrintLn("Failed to start streaming");
            e.printStackTrace();
        }
    }

    @Override // com.shimmerresearch.comms.serialPortInterface.SerialPortListener
    public void serialPortRxEvent(int i) {
        try {
            long jCurrentTimeMillis = System.currentTimeMillis();
            byte[] bArrRxBytes = this.mSerialPortComm.rxBytes(i);
            if (bArrRxBytes.length == 4) {
                incrementPacketReceivedCountCurrent();
                dataHandler(buildMsg(bArrRxBytes, Configuration.COMMUNICATION_TYPE.BLUETOOTH, false, jCurrentTimeMillis));
            }
        } catch (ShimmerException e) {
            consolePrintLn("Failed to read serial port bytes");
            e.printStackTrace();
        }
    }

    @Override // com.shimmerresearch.driver.ShimmerDevice
    public ShimmerDevice deepClone() throws IOException {
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            new ObjectOutputStream(byteArrayOutputStream).writeObject(this);
            return (NoninOnyxIIDevice) new ObjectInputStream(new ByteArrayInputStream(byteArrayOutputStream.toByteArray())).readObject();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } catch (ClassNotFoundException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    @Override // com.shimmerresearch.driver.ShimmerDevice
    public void sensorAndConfigMapsCreate() {
        this.mMapOfSensorClasses = new LinkedHashMap<>();
        this.mMapOfSensorClasses.put(AbstractSensor.SENSORS.SYSTEM_TIMESTAMP, new SensorSystemTimeStamp(this));
        this.mMapOfSensorClasses.put(AbstractSensor.SENSORS.NONIN_ONYX_II, new SensorNonin(this));
        getSensorClass(AbstractSensor.SENSORS.SYSTEM_TIMESTAMP).setIsEnabledSensor(Configuration.COMMUNICATION_TYPE.BLUETOOTH, true, Configuration.Shimmer3.SENSOR_ID.HOST_SYSTEM_TIMESTAMP);
        getSensorClass(AbstractSensor.SENSORS.NONIN_ONYX_II).setIsEnabledSensor(Configuration.COMMUNICATION_TYPE.BLUETOOTH, true, 1000);
        super.sensorAndConfigMapsCreateCommon();
    }

    @Override // com.shimmerresearch.driver.ShimmerDevice
    protected void dataHandler(ObjectCluster objectCluster) {
        this.mDeviceCallbackAdapter.dataHandler(objectCluster);
    }

    @Override // com.shimmerresearch.driver.ShimmerDevice
    public boolean setBluetoothRadioState(ShimmerBluetooth.BT_STATE bt_state) {
        boolean bluetoothRadioState = super.setBluetoothRadioState(bt_state);
        this.mDeviceCallbackAdapter.setBluetoothRadioState(bt_state, bluetoothRadioState);
        return bluetoothRadioState;
    }

    public void isReadyForStreaming() {
        this.mDeviceCallbackAdapter.isReadyForStreaming();
    }

    @Override // com.shimmerresearch.driver.ShimmerDevice
    public void setRadio(AbstractSerialPortHal abstractSerialPortHal) {
        this.mSerialPortComm = abstractSerialPortHal;
        abstractSerialPortHal.registerSerialPortRxEventCallback(this);
        this.mSerialPortComm.addByteLevelDataCommListener(new ByteLevelDataCommListener() { // from class: com.shimmerresearch.thirdpartyDevices.noninOnyxII.NoninOnyxIIDevice.1
            @Override // com.shimmerresearch.comms.serialPortInterface.ByteLevelDataCommListener
            public void eventDisconnected() {
                System.out.println("eventDisconnected");
                NoninOnyxIIDevice.this.setBluetoothRadioState(ShimmerBluetooth.BT_STATE.DISCONNECTED);
            }

            @Override // com.shimmerresearch.comms.serialPortInterface.ByteLevelDataCommListener
            public void eventConnected() {
                System.out.println("eventConnected");
                NoninOnyxIIDevice.this.setBluetoothRadioState(ShimmerBluetooth.BT_STATE.CONNECTED);
                NoninOnyxIIDevice.this.isReadyForStreaming();
                NoninOnyxIIDevice.this.startStreaming();
            }
        });
    }

    @Override // com.shimmerresearch.driver.ShimmerDevice
    public void calculatePacketReceptionRateCurrent(int i) {
        super.calculatePacketReceptionRateCurrent(i);
        this.mDeviceCallbackAdapter.sendCallbackPacketReceptionRateCurrent();
    }
}
