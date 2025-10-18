package com.shimmerresearch.comms.radioProtocol;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.shimmerresearch.bluetooth.BluetoothProgressReportPerCmd;
import com.shimmerresearch.bluetooth.ShimmerBluetooth;
import com.shimmerresearch.bluetooth.ShimmerBluetooth.BT_STATE;
import com.shimmerresearch.comms.serialPortInterface.InterfaceSerialPortHal;
import com.shimmerresearch.comms.serialPortInterface.AbstractSerialPortHal;
import com.shimmerresearch.comms.serialPortInterface.ByteLevelDataCommListener;
import com.shimmerresearch.driver.BasicProcessWithCallBack;
import com.shimmerresearch.driver.ShimmerMsg;
import com.shimmerresearch.exceptions.ShimmerException;


public class CommsProtocolRadio extends BasicProcessWithCallBack {

    private static final long serialVersionUID = -5368287098255841194L;

    public int mPacketSize;
    public transient List<RadioListener> mRadioListenerList = new ArrayList<RadioListener>();
    public transient AbstractCommsProtocol mRadioProtocol = null;
    public InterfaceSerialPortHal mRadioHal;

    public CommsProtocolRadio(InterfaceSerialPortHal radioHal, AbstractCommsProtocol radioProtocol) {
        if (radioHal != null) {
            mRadioHal = radioHal;
            mRadioHal.clearByteLevelDataCommListener();
            mRadioHal.setTimeout(AbstractSerialPortHal.SERIAL_PORT_TIMEOUT_2000);

            if (radioProtocol != null) {
                mRadioProtocol = radioProtocol;
                mRadioProtocol.setByteLevelDataComm(mRadioHal);
            }
            initialize();
        }
    }


    private void initialize() {
        mRadioHal.setVerboseMode(false, false);
        mRadioHal.addByteLevelDataCommListener(new RadioByteLevelListener());

        if (mRadioHal.isConnected()) {
            mRadioHal.eventDeviceConnected();
        }
    }


    public void addRadioListener(RadioListener radioListener) {
        mRadioListenerList.add(radioListener);
    }

    public void removeRadioListenerList() {
        mRadioListenerList.clear();
    }


    public void connect() throws ShimmerException {
        try {
            mRadioHal.connect();
        } catch (
                ShimmerException dE) {
            disconnect();
            throw (dE);
        }
    }

    ;

    public void disconnect() throws ShimmerException {
        if (mRadioProtocol != null) {
            mRadioProtocol.stopProtocol();
        }
        if (mRadioHal != null) {
            try {
                mRadioHal.disconnect();
            } catch (
                    ShimmerException e) {
                eventError(e);
                throw (e);
            } finally {
            }
        }
    }

    ;

    public boolean isConnected() {
        if (mRadioHal != null) {
            return mRadioHal.isConnected();
        }
        return false;
    }

    public void stopStreaming() {
        mRadioProtocol.stopStreaming();
    }

    public void startStreaming() {
        mRadioProtocol.startStreaming();
    }

    public void startSDLogging() {
        mRadioProtocol.startSDLogging();
    }

    public void stopSDLogging() {
        mRadioProtocol.stopSDLogging();
    }

    public void startDataLogAndStreaming() {
        mRadioProtocol.startDataLogAndStreaming();
    }

    public void stopLoggingAndStreaming() {
        mRadioProtocol.stopStreamingAndLogging();
    }


    /**
     * Transmits a command to the Shimmer device to enable the sensors. To enable multiple sensors an or operator should be used (e.g. writeEnabledSensors(SENSOR_ACCEL|SENSOR_GYRO|SENSOR_MAG)). Command should not be used consecutively. Valid values are SENSOR_ACCEL, SENSOR_GYRO, SENSOR_MAG, SENSOR_ECG, SENSOR_EMG, SENSOR_GSR, SENSOR_EXP_BOARD_A7, SENSOR_EXP_BOARD_A0, SENSOR_BRIDGE_AMP and SENSOR_HEART.
     * SENSOR_BATT
     *
     * @param enabledSensors e.g SENSOR_ACCEL|SENSOR_GYRO|SENSOR_MAG
     */
    public void writeEnabledSensors(long enabledSensors) {
        mRadioProtocol.writeEnabledSensors(enabledSensors);
    }

    public void toggleLed() {
        mRadioProtocol.toggleLed();
    }

    public void readFWVersion() {
        mRadioProtocol.readFWVersion();
    }

    public void readShimmerVersion() {
        mRadioProtocol.readShimmerVersionNew();
    }

    public void readInfoMem(int startAddress, int size, int maxMemAddress) {
        mRadioProtocol.readInfoMem(startAddress, size);
    }

    public void writeInfoMem(int startAddress, byte[] buf, int maxMemAddress) {
        mRadioProtocol.writeInfoMem(startAddress, buf);
    }

    public void readCalibrationDump() {
        mRadioProtocol.readCalibrationDump();
    }

    public void writeCalibrationDump(byte[] calibDump) {
        mRadioProtocol.writeCalibrationDump(calibDump);
    }

    public void readPressureCalibrationCoefficients() {
        mRadioProtocol.readPressureCalibrationCoefficients();
    }

    public void readExpansionBoardID() {
        mRadioProtocol.readExpansionBoardID();
    }

    public void readLEDCommand() {
        mRadioProtocol.readLEDCommand();
    }

    public void readStatusLogAndStream() {
        mRadioProtocol.readStatusLogAndStream();
    }

    public void readBattery() {
        mRadioProtocol.readBattery();
    }

    public void readRealTimeClock() {
        mRadioProtocol.readRealTimeClock();
    }

    public void inquiry() {
        mRadioProtocol.inquiry();
    }

    public void operationPrepare() {
        mRadioProtocol.operationPrepare();
    }

    public void operationStart(BT_STATE btState) {
        mRadioProtocol.operationStart(btState);
    }

    public void setPacketSize(int expectedDataPacketSize) {
        if (mRadioProtocol != null) {
            mRadioProtocol.setPacketSize(expectedDataPacketSize);
        }
    }

    public void startTimerCheckIfAlive() {
        mRadioProtocol.startTimerCheckIfAlive();
    }

    public void stopTimerCheckIfAlive() {
        mRadioProtocol.stopTimerCheckIfAlive();
    }

    public void startTimerReadStatus() {
        mRadioProtocol.startTimerReadStatus();
    }

    public void stopTimerReadStatus() {
        mRadioProtocol.stopTimerReadStatus();
    }

    public void startTimerReadBattStatus() {
        mRadioProtocol.startTimerReadBattStatus();
    }

    public void stopTimerReadBattStatus() {
        mRadioProtocol.stopTimerReadBattStatus();
    }

    @Override
    protected void processMsgFromCallback(ShimmerMsg shimmerMSG) {

    }

    public void eventError(ShimmerException dE) {
        for (RadioListener rl : mRadioListenerList) {
            if (rl != null) {
                rl.eventError(dE);
            }
        }
    }

    public class RadioByteLevelListener implements ByteLevelDataCommListener {

        @Override
        public void eventConnected() {
            for (RadioListener rl : mRadioListenerList) {
                rl.connected();
            }

            try {
                mRadioProtocol.setProtocolListener(new CommsProtocolListener());
            } catch (
                    ShimmerException e) {
                eventError(e);
            }


            mRadioProtocol.initialize();
        }

        @Override
        public void eventDisconnected() {
            for (RadioListener rl : mRadioListenerList) {
                rl.disconnected();
            }
        }
    }

    public class CommsProtocolListener implements ProtocolListener {

        @Override
        public void eventAckReceived(int lastSentInstruction) {
            for (RadioListener rl : mRadioListenerList) {
                rl.eventAckReceived(lastSentInstruction);
            }
        }

        @Override
        public void eventNewPacket(byte[] packet, long pcTimestamp) {
            for (RadioListener rl : mRadioListenerList) {
                rl.eventNewPacket(packet, pcTimestamp);
            }
        }

        @Override
        public void eventNewResponse(byte[] respB) {
            for (RadioListener rl : mRadioListenerList) {
                rl.eventNewResponse(respB);
            }
        }

        @Override
        public void eventResponseReceived(int responseCommand, Object parsedResponse) {
            for (RadioListener rl : mRadioListenerList) {
                rl.eventResponseReceived(responseCommand, parsedResponse);
            }
        }


        @Override
        public void hasStopStreaming() {
            for (RadioListener rl : mRadioListenerList) {
                rl.hasStopStreamingCallback();
            }
        }

        @Override
        public void eventLogAndStreamStatusChangedCallback(int lastSentInstruction) {
            for (RadioListener rl : mRadioListenerList) {
                rl.eventLogAndStreamStatusChangedCallback(lastSentInstruction);
            }
        }

        @Override
        public void eventAckInstruction(byte[] bs) {
            for (RadioListener rl : mRadioListenerList) {
            }
        }

        @Override
        public void eventByteResponseWhileStreaming(byte[] b) {

        }

        @Override
        public void isNowStreaming() {
            for (RadioListener rl : mRadioListenerList) {
                rl.isNowStreamingCallback();
            }
        }

        @Override
        public void sendStatusMSGtoUI(String msg) {

        }

        @Override
        public void startOperation(BT_STATE currentOperation, int totalNumOfCmds) {
            for (RadioListener rl : mRadioListenerList) {
                rl.startOperationCallback(currentOperation, totalNumOfCmds);
            }
        }

        @Override
        public void finishOperation(BT_STATE currentOperation) {
            for (RadioListener rl : mRadioListenerList) {
                rl.finishOperationCallback(currentOperation);
            }
        }

        @Override
        public void sendProgressReport(BluetoothProgressReportPerCmd progressReportPerCmd) {
            for (RadioListener rl : mRadioListenerList) {
                rl.sendProgressReportCallback(progressReportPerCmd);
            }
        }

        @Override
        public void eventDockedStateChange() {
            for (RadioListener rl : mRadioListenerList) {
                rl.eventDockedStateChange();
            }
        }

        @Override
        public void initialiseStreamingCallback() {
            int index = 0;
            for (RadioListener rl : mRadioListenerList) {
                System.out.println("initialiseStreamingCallback:\t" + index++);
                rl.initialiseStreamingCallback();
            }
            System.out.println("initialiseStreamingCallback:\tFINISHED");
        }


        @Override
        public void eventSetIsDocked(boolean isDocked) {
            for (RadioListener rl : mRadioListenerList) {
                rl.eventSetIsDocked(isDocked);
            }
        }

        @Override
        public void eventSetIsStreaming(boolean isStreaming) {
            for (RadioListener rl : mRadioListenerList) {
                rl.eventSetIsStreaming(isStreaming);
            }
        }

        @Override
        public void eventSetIsSensing(boolean isSensing) {
            for (RadioListener rl : mRadioListenerList) {
                rl.eventSetIsSensing(isSensing);
            }
        }

        @Override
        public void eventSetIsSDLogging(boolean isSdLogging) {
            for (RadioListener rl : mRadioListenerList) {
                rl.eventSetIsSDLogging(isSdLogging);
            }
        }

        @Override
        public void eventSetIsInitialised(boolean isInitialised) {
            for (RadioListener rl : mRadioListenerList) {
                rl.eventSetIsInitialised(isInitialised);
            }
        }

        @Override
        public void eventSetHaveAttemptedToRead(boolean haveAttemptedToRead) {
            for (RadioListener rl : mRadioListenerList) {
                rl.eventSetHaveAttemptedToRead(haveAttemptedToRead);
            }
        }

        @Override
        public void eventKillConnectionRequest(ShimmerException dE) {
            eventError(dE);

            try {
                disconnect();
            } catch (
                    ShimmerException e) {
                eventError(e);
            }
        }

    }


}
