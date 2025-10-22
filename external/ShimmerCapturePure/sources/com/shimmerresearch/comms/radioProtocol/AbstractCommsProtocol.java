package com.shimmerresearch.comms.radioProtocol;

import com.shimmerresearch.bluetooth.ShimmerBluetooth;
import com.shimmerresearch.comms.serialPortInterface.InterfaceSerialPortHal;
import com.shimmerresearch.exceptions.ShimmerException;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/* loaded from: classes2.dex */
public abstract class AbstractCommsProtocol {
    public boolean mFirstTime;
    public boolean mIamAlive;
    protected InterfaceSerialPortHal mCommsInterface;
    protected HashMap<Integer, MemReadDetails> mMapOfMemReadDetails;
    protected byte[] mMemBuffer;
    protected int mNumOfMemSetCmds;
    protected int mPacketSize;
    protected ProtocolListener mProtocolListener;

    public AbstractCommsProtocol() {
        this.mPacketSize = 0;
        this.mMemBuffer = new byte[0];
        this.mMapOfMemReadDetails = new HashMap<>();
        this.mFirstTime = false;
        this.mIamAlive = false;
    }

    public AbstractCommsProtocol(InterfaceSerialPortHal interfaceSerialPortHal) {
        this.mPacketSize = 0;
        this.mMemBuffer = new byte[0];
        this.mMapOfMemReadDetails = new HashMap<>();
        this.mFirstTime = false;
        this.mIamAlive = false;
        setByteLevelDataComm(interfaceSerialPortHal);
    }

    public abstract List<byte[]> getListofInstructions();

    public int getPacketSize() {
        return this.mPacketSize;
    }

    public void setPacketSize(int i) {
        this.mPacketSize = i;
    }

    public abstract void initialize();

    public abstract void inquiry();

    public abstract void operationPrepare();

    public abstract void operationStart(ShimmerBluetooth.BT_STATE bt_state);

    public abstract void readBattStatusPeriod();

    public abstract void readBattery();

    public abstract void readCalibrationDump();

    public abstract void readCalibrationDump(int i, int i2);

    public abstract void readExpansionBoardID();

    public abstract void readFWVersion();

    protected abstract void readInfoMem(int i, int i2);

    public abstract void readLEDCommand();

    public abstract void readMemCommand(int i, int i2, int i3);

    public abstract void readPressureCalibrationCoefficients();

    public abstract void readRealTimeClock();

    public abstract void readShimmerVersionNew();

    public abstract void readStatusLogAndStream();

    public abstract void restartTimersIfNull();

    public void setByteLevelDataComm(InterfaceSerialPortHal interfaceSerialPortHal) {
        this.mCommsInterface = interfaceSerialPortHal;
    }

    public abstract void setInstructionStackLock(boolean z);

    public abstract void startDataLogAndStreaming();

    public abstract void startSDLogging();

    public abstract void startStreaming();

    public abstract void startTimerCheckIfAlive();

    public abstract void startTimerReadBattStatus();

    public abstract void startTimerReadStatus();

    public abstract void stopProtocol();

    public abstract void stopSDLogging();

    public abstract void stopStreaming();

    public abstract void stopStreamingAndLogging();

    public abstract void stopTimerCheckIfAlive();

    public abstract void stopTimerReadBattStatus();

    public abstract void stopTimerReadStatus();

    public abstract void toggleLed();

    public abstract void writeBattStatusPeriod(int i);

    public abstract void writeCalibrationDump(byte[] bArr);

    public abstract void writeEnabledSensors(long j);

    protected abstract void writeInfoMem(int i, byte[] bArr);

    public abstract void writeInstruction(byte[] bArr);

    public abstract void writeMemCommand(int i, int i2, byte[] bArr);

    public void setProtocolListener(ProtocolListener protocolListener) throws ShimmerException {
        if (this.mProtocolListener != null) {
            throw new ShimmerException("Only One Listener Allowed");
        }
        this.mProtocolListener = protocolListener;
    }

    public void writeMem(int i, int i2, byte[] bArr, int i3) {
        int i4 = 0;
        this.mNumOfMemSetCmds = 0;
        if (bArr.length > (i3 - i2) + 1) {
            return;
        }
        int length = bArr.length;
        while (length > 0) {
            int i5 = 128;
            if (length <= 128) {
                i5 = length;
            }
            int i6 = i4 + i5;
            writeMemCommand(i, i2, Arrays.copyOfRange(bArr, i4, i6));
            this.mNumOfMemSetCmds++;
            i2 += i5;
            length -= i5;
            i4 = i6;
        }
    }

    public void readMem(int i, int i2, int i3, int i4) {
        this.mMapOfMemReadDetails.put(Integer.valueOf(i), new MemReadDetails(i, i2, i3, i4));
        if (i3 > (i4 - i2) + 1) {
            return;
        }
        while (i3 > 0) {
            int i5 = 128;
            if (i3 <= 128) {
                i5 = i3;
            }
            readMemCommand(i, i2, i5);
            i3 -= i5;
            i2 += i5;
        }
    }

    protected void clearMemReadBuffers() {
        this.mMapOfMemReadDetails.clear();
        this.mMemBuffer = new byte[0];
    }

    protected void clearMemReadBuffer(int i) {
        this.mMapOfMemReadDetails.remove(Integer.valueOf(i));
        this.mMemBuffer = new byte[0];
    }
}
