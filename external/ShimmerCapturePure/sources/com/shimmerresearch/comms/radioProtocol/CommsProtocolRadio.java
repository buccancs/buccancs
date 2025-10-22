package com.shimmerresearch.comms.radioProtocol;

import com.shimmerresearch.bluetooth.BluetoothProgressReportPerCmd;
import com.shimmerresearch.bluetooth.ShimmerBluetooth;
import com.shimmerresearch.comms.serialPortInterface.AbstractSerialPortHal;
import com.shimmerresearch.comms.serialPortInterface.ByteLevelDataCommListener;
import com.shimmerresearch.comms.serialPortInterface.InterfaceSerialPortHal;
import com.shimmerresearch.driver.BasicProcessWithCallBack;
import com.shimmerresearch.driver.ShimmerMsg;
import com.shimmerresearch.exceptions.ShimmerException;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes2.dex */
public class CommsProtocolRadio extends BasicProcessWithCallBack {
    private static final long serialVersionUID = -5368287098255841194L;
    public int mPacketSize;
    public InterfaceSerialPortHal mRadioHal;
    public transient List<RadioListener> mRadioListenerList = new ArrayList();
    public transient AbstractCommsProtocol mRadioProtocol;

    public CommsProtocolRadio(InterfaceSerialPortHal interfaceSerialPortHal, AbstractCommsProtocol abstractCommsProtocol) {
        this.mRadioProtocol = null;
        if (interfaceSerialPortHal != null) {
            this.mRadioHal = interfaceSerialPortHal;
            interfaceSerialPortHal.clearByteLevelDataCommListener();
            this.mRadioHal.setTimeout(AbstractSerialPortHal.SERIAL_PORT_TIMEOUT_2000);
            if (abstractCommsProtocol != null) {
                this.mRadioProtocol = abstractCommsProtocol;
                abstractCommsProtocol.setByteLevelDataComm(this.mRadioHal);
            }
            initialize();
        }
    }

    @Override // com.shimmerresearch.driver.BasicProcessWithCallBack
    protected void processMsgFromCallback(ShimmerMsg shimmerMsg) {
    }

    private void initialize() {
        this.mRadioHal.setVerboseMode(false, false);
        this.mRadioHal.addByteLevelDataCommListener(new RadioByteLevelListener());
        if (this.mRadioHal.isConnected()) {
            this.mRadioHal.eventDeviceConnected();
        }
    }

    public void addRadioListener(RadioListener radioListener) {
        this.mRadioListenerList.add(radioListener);
    }

    public void removeRadioListenerList() {
        this.mRadioListenerList.clear();
    }

    public void connect() throws ShimmerException {
        try {
            this.mRadioHal.connect();
        } catch (ShimmerException e) {
            disconnect();
            throw e;
        }
    }

    public void disconnect() throws ShimmerException {
        AbstractCommsProtocol abstractCommsProtocol = this.mRadioProtocol;
        if (abstractCommsProtocol != null) {
            abstractCommsProtocol.stopProtocol();
        }
        InterfaceSerialPortHal interfaceSerialPortHal = this.mRadioHal;
        if (interfaceSerialPortHal != null) {
            try {
                interfaceSerialPortHal.disconnect();
            } catch (ShimmerException e) {
                eventError(e);
                throw e;
            }
        }
    }

    public boolean isConnected() {
        InterfaceSerialPortHal interfaceSerialPortHal = this.mRadioHal;
        if (interfaceSerialPortHal != null) {
            return interfaceSerialPortHal.isConnected();
        }
        return false;
    }

    public void stopStreaming() {
        this.mRadioProtocol.stopStreaming();
    }

    public void startStreaming() {
        this.mRadioProtocol.startStreaming();
    }

    public void startSDLogging() {
        this.mRadioProtocol.startSDLogging();
    }

    public void stopSDLogging() {
        this.mRadioProtocol.stopSDLogging();
    }

    public void startDataLogAndStreaming() {
        this.mRadioProtocol.startDataLogAndStreaming();
    }

    public void stopLoggingAndStreaming() {
        this.mRadioProtocol.stopStreamingAndLogging();
    }

    public void writeEnabledSensors(long j) {
        this.mRadioProtocol.writeEnabledSensors(j);
    }

    public void toggleLed() {
        this.mRadioProtocol.toggleLed();
    }

    public void readFWVersion() {
        this.mRadioProtocol.readFWVersion();
    }

    public void readShimmerVersion() {
        this.mRadioProtocol.readShimmerVersionNew();
    }

    public void readInfoMem(int i, int i2, int i3) {
        this.mRadioProtocol.readInfoMem(i, i2);
    }

    public void writeInfoMem(int i, byte[] bArr, int i2) {
        this.mRadioProtocol.writeInfoMem(i, bArr);
    }

    public void readCalibrationDump() {
        this.mRadioProtocol.readCalibrationDump();
    }

    public void writeCalibrationDump(byte[] bArr) {
        this.mRadioProtocol.writeCalibrationDump(bArr);
    }

    public void readPressureCalibrationCoefficients() {
        this.mRadioProtocol.readPressureCalibrationCoefficients();
    }

    public void readExpansionBoardID() {
        this.mRadioProtocol.readExpansionBoardID();
    }

    public void readLEDCommand() {
        this.mRadioProtocol.readLEDCommand();
    }

    public void readStatusLogAndStream() {
        this.mRadioProtocol.readStatusLogAndStream();
    }

    public void readBattery() {
        this.mRadioProtocol.readBattery();
    }

    public void readRealTimeClock() {
        this.mRadioProtocol.readRealTimeClock();
    }

    public void inquiry() {
        this.mRadioProtocol.inquiry();
    }

    public void operationPrepare() {
        this.mRadioProtocol.operationPrepare();
    }

    public void operationStart(ShimmerBluetooth.BT_STATE bt_state) {
        this.mRadioProtocol.operationStart(bt_state);
    }

    public void setPacketSize(int i) {
        AbstractCommsProtocol abstractCommsProtocol = this.mRadioProtocol;
        if (abstractCommsProtocol != null) {
            abstractCommsProtocol.setPacketSize(i);
        }
    }

    public void startTimerCheckIfAlive() {
        this.mRadioProtocol.startTimerCheckIfAlive();
    }

    public void stopTimerCheckIfAlive() {
        this.mRadioProtocol.stopTimerCheckIfAlive();
    }

    public void startTimerReadStatus() {
        this.mRadioProtocol.startTimerReadStatus();
    }

    public void stopTimerReadStatus() {
        this.mRadioProtocol.stopTimerReadStatus();
    }

    public void startTimerReadBattStatus() {
        this.mRadioProtocol.startTimerReadBattStatus();
    }

    public void stopTimerReadBattStatus() {
        this.mRadioProtocol.stopTimerReadBattStatus();
    }

    public void eventError(ShimmerException shimmerException) {
        for (RadioListener radioListener : this.mRadioListenerList) {
            if (radioListener != null) {
                radioListener.eventError(shimmerException);
            }
        }
    }

    public class RadioByteLevelListener implements ByteLevelDataCommListener {
        public RadioByteLevelListener() {
        }

        @Override // com.shimmerresearch.comms.serialPortInterface.ByteLevelDataCommListener
        public void eventConnected() {
            Iterator<RadioListener> it2 = CommsProtocolRadio.this.mRadioListenerList.iterator();
            while (it2.hasNext()) {
                it2.next().connected();
            }
            try {
                CommsProtocolRadio.this.mRadioProtocol.setProtocolListener(CommsProtocolRadio.this.new CommsProtocolListener());
            } catch (ShimmerException e) {
                CommsProtocolRadio.this.eventError(e);
            }
            CommsProtocolRadio.this.mRadioProtocol.initialize();
        }

        @Override // com.shimmerresearch.comms.serialPortInterface.ByteLevelDataCommListener
        public void eventDisconnected() {
            Iterator<RadioListener> it2 = CommsProtocolRadio.this.mRadioListenerList.iterator();
            while (it2.hasNext()) {
                it2.next().disconnected();
            }
        }
    }

    public class CommsProtocolListener implements ProtocolListener {
        public CommsProtocolListener() {
        }

        @Override // com.shimmerresearch.comms.radioProtocol.ProtocolListener
        public void eventByteResponseWhileStreaming(byte[] bArr) {
        }

        @Override // com.shimmerresearch.comms.radioProtocol.ProtocolListener
        public void sendStatusMSGtoUI(String str) {
        }

        @Override // com.shimmerresearch.comms.radioProtocol.ProtocolListener
        public void eventAckReceived(int i) {
            Iterator<RadioListener> it2 = CommsProtocolRadio.this.mRadioListenerList.iterator();
            while (it2.hasNext()) {
                it2.next().eventAckReceived(i);
            }
        }

        @Override // com.shimmerresearch.comms.radioProtocol.ProtocolListener
        public void eventNewPacket(byte[] bArr, long j) {
            Iterator<RadioListener> it2 = CommsProtocolRadio.this.mRadioListenerList.iterator();
            while (it2.hasNext()) {
                it2.next().eventNewPacket(bArr, j);
            }
        }

        @Override // com.shimmerresearch.comms.radioProtocol.ProtocolListener
        public void eventNewResponse(byte[] bArr) {
            Iterator<RadioListener> it2 = CommsProtocolRadio.this.mRadioListenerList.iterator();
            while (it2.hasNext()) {
                it2.next().eventNewResponse(bArr);
            }
        }

        @Override // com.shimmerresearch.comms.radioProtocol.ProtocolListener
        public void eventResponseReceived(int i, Object obj) {
            Iterator<RadioListener> it2 = CommsProtocolRadio.this.mRadioListenerList.iterator();
            while (it2.hasNext()) {
                it2.next().eventResponseReceived(i, obj);
            }
        }

        @Override // com.shimmerresearch.comms.radioProtocol.ProtocolListener
        public void hasStopStreaming() {
            Iterator<RadioListener> it2 = CommsProtocolRadio.this.mRadioListenerList.iterator();
            while (it2.hasNext()) {
                it2.next().hasStopStreamingCallback();
            }
        }

        @Override // com.shimmerresearch.comms.radioProtocol.ProtocolListener
        public void eventLogAndStreamStatusChangedCallback(int i) {
            Iterator<RadioListener> it2 = CommsProtocolRadio.this.mRadioListenerList.iterator();
            while (it2.hasNext()) {
                it2.next().eventLogAndStreamStatusChangedCallback(i);
            }
        }

        @Override // com.shimmerresearch.comms.radioProtocol.ProtocolListener
        public void eventAckInstruction(byte[] bArr) {
            for (RadioListener radioListener : CommsProtocolRadio.this.mRadioListenerList) {
            }
        }

        @Override // com.shimmerresearch.comms.radioProtocol.ProtocolListener
        public void isNowStreaming() {
            Iterator<RadioListener> it2 = CommsProtocolRadio.this.mRadioListenerList.iterator();
            while (it2.hasNext()) {
                it2.next().isNowStreamingCallback();
            }
        }

        @Override // com.shimmerresearch.comms.radioProtocol.ProtocolListener
        public void startOperation(ShimmerBluetooth.BT_STATE bt_state, int i) {
            Iterator<RadioListener> it2 = CommsProtocolRadio.this.mRadioListenerList.iterator();
            while (it2.hasNext()) {
                it2.next().startOperationCallback(bt_state, i);
            }
        }

        @Override // com.shimmerresearch.comms.radioProtocol.ProtocolListener
        public void finishOperation(ShimmerBluetooth.BT_STATE bt_state) {
            Iterator<RadioListener> it2 = CommsProtocolRadio.this.mRadioListenerList.iterator();
            while (it2.hasNext()) {
                it2.next().finishOperationCallback(bt_state);
            }
        }

        @Override // com.shimmerresearch.comms.radioProtocol.ProtocolListener
        public void sendProgressReport(BluetoothProgressReportPerCmd bluetoothProgressReportPerCmd) {
            Iterator<RadioListener> it2 = CommsProtocolRadio.this.mRadioListenerList.iterator();
            while (it2.hasNext()) {
                it2.next().sendProgressReportCallback(bluetoothProgressReportPerCmd);
            }
        }

        @Override // com.shimmerresearch.comms.radioProtocol.ProtocolListener
        public void eventDockedStateChange() {
            Iterator<RadioListener> it2 = CommsProtocolRadio.this.mRadioListenerList.iterator();
            while (it2.hasNext()) {
                it2.next().eventDockedStateChange();
            }
        }

        @Override // com.shimmerresearch.comms.radioProtocol.ProtocolListener
        public void initialiseStreamingCallback() {
            int i = 0;
            for (RadioListener radioListener : CommsProtocolRadio.this.mRadioListenerList) {
                System.out.println("initialiseStreamingCallback:\t" + i);
                radioListener.initialiseStreamingCallback();
                i++;
            }
            System.out.println("initialiseStreamingCallback:\tFINISHED");
        }

        @Override // com.shimmerresearch.comms.radioProtocol.ProtocolListener
        public void eventSetIsDocked(boolean z) {
            Iterator<RadioListener> it2 = CommsProtocolRadio.this.mRadioListenerList.iterator();
            while (it2.hasNext()) {
                it2.next().eventSetIsDocked(z);
            }
        }

        @Override // com.shimmerresearch.comms.radioProtocol.ProtocolListener
        public void eventSetIsStreaming(boolean z) {
            Iterator<RadioListener> it2 = CommsProtocolRadio.this.mRadioListenerList.iterator();
            while (it2.hasNext()) {
                it2.next().eventSetIsStreaming(z);
            }
        }

        @Override // com.shimmerresearch.comms.radioProtocol.ProtocolListener
        public void eventSetIsSensing(boolean z) {
            Iterator<RadioListener> it2 = CommsProtocolRadio.this.mRadioListenerList.iterator();
            while (it2.hasNext()) {
                it2.next().eventSetIsSensing(z);
            }
        }

        @Override // com.shimmerresearch.comms.radioProtocol.ProtocolListener
        public void eventSetIsSDLogging(boolean z) {
            Iterator<RadioListener> it2 = CommsProtocolRadio.this.mRadioListenerList.iterator();
            while (it2.hasNext()) {
                it2.next().eventSetIsSDLogging(z);
            }
        }

        @Override // com.shimmerresearch.comms.radioProtocol.ProtocolListener
        public void eventSetIsInitialised(boolean z) {
            Iterator<RadioListener> it2 = CommsProtocolRadio.this.mRadioListenerList.iterator();
            while (it2.hasNext()) {
                it2.next().eventSetIsInitialised(z);
            }
        }

        @Override // com.shimmerresearch.comms.radioProtocol.ProtocolListener
        public void eventSetHaveAttemptedToRead(boolean z) {
            Iterator<RadioListener> it2 = CommsProtocolRadio.this.mRadioListenerList.iterator();
            while (it2.hasNext()) {
                it2.next().eventSetHaveAttemptedToRead(z);
            }
        }

        @Override // com.shimmerresearch.comms.radioProtocol.ProtocolListener
        public void eventKillConnectionRequest(ShimmerException shimmerException) {
            CommsProtocolRadio.this.eventError(shimmerException);
            try {
                CommsProtocolRadio.this.disconnect();
            } catch (ShimmerException e) {
                CommsProtocolRadio.this.eventError(e);
            }
        }
    }
}
