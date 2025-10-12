package com.shimmerresearch.comms.radioProtocol;

import java.io.ByteArrayOutputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;
import java.util.Timer;
import java.util.TimerTask;

import org.apache.commons.lang3.ArrayUtils;

import com.shimmerresearch.bluetooth.BluetoothProgressReportPerCmd;
import com.shimmerresearch.bluetooth.ShimmerBluetooth.BT_STATE;
import com.shimmerresearch.comms.radioProtocol.ShimmerLiteProtocolInstructionSet.LiteProtocolInstructionSet.*;
import com.shimmerresearch.comms.serialPortInterface.InterfaceSerialPortHal;
import com.shimmerresearch.driverUtilities.ExpansionBoardDetails;
import com.shimmerresearch.driverUtilities.ShimmerBattStatusDetails;
import com.shimmerresearch.driverUtilities.ShimmerVerObject;
import com.shimmerresearch.driverUtilities.UtilShimmer;
import com.shimmerresearch.driverUtilities.ShimmerVerDetails.FW_ID;
import com.shimmerresearch.driverUtilities.ShimmerVerDetails.HW_ID;
import com.shimmerresearch.exceptions.ShimmerException;
import com.shimmerresearch.sensors.SensorSTC3100Details;

public class LiteProtocol extends AbstractCommsProtocol {

    public static final int DELAY_BETWEEN_CONFIG_BYTE_WRITES = 100;
    public static final int DELAY_AFTER_CONFIG_BYTE_WRITE = 500;
    public static final int TIMER_READ_STATUS_PERIOD = 5000;
    public static final int TIMER_READ_BATT_STATUS_PERIOD = 60000;
    public static final int TIMER_CHECK_ALIVE_PERIOD = 2000;
    public static final int TIMER_CONNECTING_TIMEOUT = 20000;
    private final static int NUMBER_OF_TX_RETRIES_LIMIT = 0;
    private static final int MAX_CALIB_DUMP_MAX = 4096;
    private final int ACK_TIMER_DURATION = 10;                                    // Duration to wait for an ack packet (seconds)
    public int mHardwareVersion;
    public UtilShimmer mUtilShimmer = new UtilShimmer(getClass().getSimpleName(), true);

    public String mComPort = "";
    public String mMyBluetoothAddress = "";
    public String mConnectionHandle = "";
    public boolean mIsSensing = false;
    public boolean mIsSDLogging = false;
    public boolean mIsStreaming = false;
    public boolean mIsInitialised = false;
    public boolean mIsDocked = false;
    public boolean mHaveAttemptedToReadConfig = false;
    protected List<byte[]> mListofInstructions = new ArrayList<byte[]>();
    protected int mCurrentCommand;
    transient protected IOThread mIOThread;
    protected boolean mWaitForAck = false;
    protected boolean mWaitForResponse = false;
    protected boolean mTransactionCompleted = true;                                    // Variable is used to ensure a command has finished execution prior to executing the next command (see initialize())
    protected boolean mDummy = false;
    protected Stack<Byte> byteStack = new Stack<Byte>();
    protected boolean mOperationUnderway = false;
    transient protected Timer mTimerCheckForAckOrResp;
    transient protected Timer mTimerCheckAlive;
    transient protected Timer mTimerReadStatus;
    transient protected Timer mTimerReadBattStatus;
    protected String mDirectoryName;
    protected int numBytesToReadFromExpBoard = 0;
    transient ByteArrayOutputStream mByteArrayOutputStream = new ByteArrayOutputStream();
    List<Long> mListofPCTimeStamps = new ArrayList<Long>();

    private boolean mInstructionStackLock = false;
    private int mNumberofTXRetriesCount = 1;
    private int mCountDeadConnection = 0;
    private boolean mCheckIfConnectionisAlive = true;
    private ShimmerVerObject mShimmerVerObject;
    private boolean mUseShimmerBluetoothApproach = true;
    private boolean mUseLegacyDelayToDelayForResponse = false;
    private int mTempChipID;

    public LiteProtocol(String connectionHandle) {
        super();
        mConnectionHandle = connectionHandle;
        mUtilShimmer.setParentClassName(getClass().getSimpleName() + "-" + mConnectionHandle);
    }


    public LiteProtocol(InterfaceSerialPortHal commsInterface) {
        super(commsInterface);
    }

    protected void writeInstruction(int commandValue) {
        writeInstruction(new byte[]{(byte) (commandValue & 0xFF)});
    }

    @Override
    public void writeInstruction(byte[] instruction) {
        getListofInstructions().add(instruction);
    }

    public int getFirmwareIdentifier() {
        if (mShimmerVerObject != null) {
            return mShimmerVerObject.getFirmwareIdentifier();
        }
        return FW_ID.UNKNOWN;
    }

    ;

    public int getShimmerVersion() {
        return mHardwareVersion;
    }

    public boolean isInstructionStackLock() {
        return mInstructionStackLock;
    }

    @Override
    public void setInstructionStackLock(boolean state) {
        this.mInstructionStackLock = state;
    }

    public List<byte[]> getListofInstructions() {
        return mListofInstructions;
    }

    private void clearSerialBuffer() throws ShimmerException {
		/* JC: not working well on android
		if(bytesAvailableToBeRead()){
			byte[] buffer = readBytes(availableBytes());
			printLogDataForDebugging("Discarding:\t\t" + UtilShimmer.bytesToHexStringWithSpacesFormatted(buffer));
		}
		*/

        while (availableBytes() != 0) {
            if (bytesAvailableToBeRead()) {
                int available = availableBytes();
                byte[] tb = readBytes(available);
                String msg = "Clearing Serial Buffer : " + UtilShimmer.bytesToHexStringWithSpacesFormatted(tb);
                printLogDataForDebugging(msg);
            }
        }
    }

    @Override
    public void initialize() {
        mIamAlive = false;
        getListofInstructions().clear();
        mFirstTime = true;

        startIoThread();

        stopTimerReadStatus();
        stopTimerCheckForAckOrResp();
        setInstructionStackLock(false);

        dummyreadSamplingRate();
        readShimmerVersionNew();
    }


    @Override
    public void stopProtocol() {
        stopAllTimers();

        stopIoThread();

        setIsStreaming(false);
        setIsInitialised(false);
    }

    private void startIoThread() {
        stopIoThread();

        mIOThread = new IOThread();
        mIOThread.setName(getClass().getSimpleName() + "-" + mConnectionHandle);
        mIOThread.start();
    }

    private void stopIoThread() {
        if (mIOThread != null) {
            mIOThread.stop = true;
            mIOThread = null;
        }
    }

    protected void processResponseCommand(int responseCommand) {
        byte[] length = null;
        byte[] rxBuf = null;

        try {
            if (responseCommand == InstructionsResponse.INQUIRY_RESPONSE_VALUE) {
                delayForBtResponse(500);
                List<Byte> buffer = new ArrayList<Byte>();
				/*
				if (Math.random()>0.5 && mIsInitialised==true){
					if(bytesAvailableToBeRead()
							&& mCurrentCommand!=TEST_CONNECTION_COMMAND
							&& mCurrentCommand!=GET_STATUS_COMMAND
							&& mCurrentCommand!= GET_VBATT_COMMAND
							&& mCurrentCommand!=START_STREAMING_COMMAND
							&& mCurrentCommand!=STOP_STREAMING_COMMAND
							&& mCurrentCommand!=SET_RWC_COMMAND
							&& mCurrentCommand!=GET_RWC_COMMAND){
						readByte();
					}
				}
				*/

                int lengthSettings = 8;
                int lengthChannels = 6;
                if (mShimmerVerObject.isShimmerGen2()) {
                    lengthSettings = 5;
                    lengthChannels = 3;
                } else if (mShimmerVerObject.getHardwareVersion() == HW_ID.ARDUINO) {
                    lengthSettings = 10;
                    lengthChannels = 0;
                }
                for (int i = 0; i < lengthSettings; i++) {
                    buffer.add(readByte());
                }
                for (int i = 0; i < (int) buffer.get(lengthChannels); i++) {
                    buffer.add(readByte());
                }

                rxBuf = new byte[buffer.size()];
                for (int i = 0; i < rxBuf.length; i++) {
                    rxBuf[i] = (byte) buffer.get(i);
                }

                printLogDataForDebugging("Inquiry Response Received: " + UtilShimmer.bytesToHexStringWithSpacesFormatted(rxBuf));

                eventResponseReceived(responseCommand, rxBuf);
            } else if (responseCommand == InstructionsResponse.SAMPLING_RATE_RESPONSE_VALUE) {
                if (!mIsStreaming) {

                    byte[] bufferSR = null;
                    if (mShimmerVerObject.isShimmerGen2()) {
                        bufferSR = readBytes(1);
                    } else {
                        bufferSR = readBytes(2);
                    }
                    eventResponseReceived(responseCommand, bufferSR);
                }

            } else if (responseCommand == InstructionsResponse.FW_VERSION_RESPONSE_VALUE) {
                delayForBtResponse(200);
                rxBuf = readBytes(6);
                int firmwareIdentifier = (int) ((rxBuf[1] & 0xFF) << 8) + (int) (rxBuf[0] & 0xFF);
                int firmwareVersionMajor = (int) ((rxBuf[3] & 0xFF) << 8) + (int) (rxBuf[2] & 0xFF);
                int firmwareVersionMinor = ((int) ((rxBuf[4] & 0xFF)));
                int firmwareVersionInternal = (int) (rxBuf[5] & 0xFF);

                ShimmerVerObject shimmerVerObject = new ShimmerVerObject(getHardwareVersion(), firmwareIdentifier, firmwareVersionMajor, firmwareVersionMinor, firmwareVersionInternal);
                this.mShimmerVerObject = shimmerVerObject;

                printLogDataForDebugging("FW Version Response Received. FW Code: " + shimmerVerObject.getFirmwareVersionCode());
                printLogDataForDebugging("FW Version Response Received: " + shimmerVerObject.getFirmwareVersionParsed());

                eventResponseReceived(responseCommand, shimmerVerObject);
            } else if (responseCommand == InstructionsResponse.CONFIG_BYTE0_RESPONSE_VALUE) {
                byte[] bufferConfigByte0 = null;
                if (mShimmerVerObject.isShimmerGen2()) {
                    bufferConfigByte0 = readBytes(1);
                } else {
                    bufferConfigByte0 = readBytes(4);
                }
                eventResponseReceived(responseCommand, bufferConfigByte0);
            }
//
            else if (responseCommand == InstructionsResponse.GET_SHIMMER_VERSION_RESPONSE_VALUE) {
                delayForBtResponse(100);
                byte[] bufferShimmerVersion = new byte[1];
                bufferShimmerVersion = readBytes(1);

                setHardwareVersion((int) bufferShimmerVersion[0]);
                eventResponseReceived(responseCommand, (int) bufferShimmerVersion[0]);


                printLogDataForDebugging("Shimmer Version (HW) Response Received: " + UtilShimmer.bytesToHexStringWithSpacesFormatted(bufferShimmerVersion));

                readFWVersion();
            } else if (responseCommand == InstructionsResponse.BLINK_LED_RESPONSE_VALUE) {
                byte[] byteled = readBytes(1);
                eventResponseReceived(responseCommand, (int) ((byte) (byteled[0] & 0xFF)));
            } else if (responseCommand == InstructionsResponse.BUFFER_SIZE_RESPONSE_VALUE) {
                byte[] byteled = readBytes(1);
                int bufferSize = byteled[0] & 0xFF;
                eventResponseReceived(responseCommand, bufferSize);
            } else if (responseCommand == InstructionsResponse.BMP180_CALIBRATION_COEFFICIENTS_RESPONSE_VALUE) {
                delayForBtResponse(100);
                byte[] pressureResoRes = new byte[22];
                pressureResoRes = readBytes(22);
                byte[] mPressureCalRawParams = new byte[23];
                System.arraycopy(pressureResoRes, 0, mPressureCalRawParams, 1, 22);
                mPressureCalRawParams[0] = (byte) responseCommand;
                eventResponseReceived(responseCommand, pressureResoRes);
            } else if (responseCommand == InstructionsResponse.DAUGHTER_CARD_ID_RESPONSE_VALUE) {
                byte[] expBoardArray = readBytes(numBytesToReadFromExpBoard + 1);
                byte[] expBoardArraySplit = Arrays.copyOfRange(expBoardArray, 1, 4);
                eventResponseReceived(responseCommand, new ExpansionBoardDetails(expBoardArraySplit));
            } else if (responseCommand == InstructionsResponse.BAUD_RATE_RESPONSE_VALUE) {
                byte[] bufferBaud = readBytes(1);
                int mBluetoothBaudRate = bufferBaud[0] & 0xFF;
                eventResponseReceived(responseCommand, mBluetoothBaudRate);
            } else if (responseCommand == InstructionsResponse.TRIAL_CONFIG_RESPONSE_VALUE) {
                rxBuf = readBytes(3);
                eventResponseReceived(responseCommand, rxBuf);
            } else if (responseCommand == InstructionsResponse.CENTER_RESPONSE_VALUE) {
                length = readBytes(1);
                rxBuf = readBytes(length[0]);
                String center = new String(rxBuf);
                eventResponseReceived(responseCommand, center);
            } else if (responseCommand == InstructionsResponse.SHIMMERNAME_RESPONSE_VALUE) {
                length = readBytes(1);
                rxBuf = readBytes(length[0]);
                String name = new String(rxBuf);
                eventResponseReceived(responseCommand, name);
            } else if (responseCommand == InstructionsResponse.EXPID_RESPONSE_VALUE) {
                length = readBytes(1);
                rxBuf = readBytes(length[0]);
                String expId = new String(rxBuf);
                eventResponseReceived(responseCommand, expId);
            } else if (responseCommand == InstructionsResponse.CONFIGTIME_RESPONSE_VALUE) {
                length = readBytes(1);
                rxBuf = readBytes(length[0]);
                String time = new String(rxBuf);
                eventResponseReceived(responseCommand, time);
            } else if (responseCommand == InstructionsResponse.RWC_RESPONSE_VALUE) {
                rxBuf = readBytes(8);

                rxBuf = Arrays.copyOf(rxBuf, 8);
                ArrayUtils.reverse(rxBuf);
                long responseTime = (long) (((double) (ByteBuffer.wrap(rxBuf).getLong()) / 32.768)); // / 1000

                eventResponseReceived(responseCommand, responseTime);
            } else if (responseCommand == InstructionsResponse.INSTREAM_CMD_RESPONSE_VALUE) {
                processInstreamResponse();
            } else if (responseCommand == InstructionsResponse.UNIQUE_SERIAL_RESPONSE_VALUE) {
                rxBuf = readBytes(8);
                eventResponseReceived(responseCommand, rxBuf);
            } else if (responseCommand == InstructionsResponse.INFOMEM_RESPONSE_VALUE) {
                rxBuf = readBytes(1);
                int lengthToRead = (int) (rxBuf[0] & 0xFF);
                rxBuf = readBytes(lengthToRead);
                printLogDataForDebugging("INFOMEM_RESPONSE Received: " + UtilShimmer.bytesToHexStringWithSpacesFormatted(rxBuf));

                mMemBuffer = ArrayUtils.addAll(mMemBuffer, rxBuf);

                MemReadDetails memReadDetails = mMapOfMemReadDetails.get(InstructionsGet.GET_INFOMEM_COMMAND_VALUE);
                if (memReadDetails != null) {
                    int currentEndAddress = memReadDetails.mCurrentMemAddress + memReadDetails.mCurrentMemLengthToRead;
                    if (currentEndAddress >= memReadDetails.mEndMemAddress) {
                        eventResponseReceived(responseCommand, mMemBuffer);
                        clearMemReadBuffer(InstructionsGet.GET_INFOMEM_COMMAND_VALUE);
                    }
                }
            } else if (responseCommand == InstructionsResponse.RSP_CALIB_DUMP_COMMAND_VALUE) {
                rxBuf = readBytes(3);
                int currentMemLength = rxBuf[0] & 0xFF;
                int currentMemOffset = ((rxBuf[2] & 0xFF) << 8) | (rxBuf[1] & 0xFF);

                byte[] rxBufFull = rxBuf;

                rxBuf = readBytes(currentMemLength);
                mMemBuffer = ArrayUtils.addAll(mMemBuffer, rxBuf);

                rxBufFull = ArrayUtils.addAll(rxBufFull, rxBuf);
                printLogDataForDebugging("CALIB_DUMP Received:\t" + UtilShimmer.bytesToHexStringWithSpacesFormatted(rxBuf));
                printLogDataForDebugging("CALIB_DUMP concat:\t" + UtilShimmer.bytesToHexStringWithSpacesFormatted(mMemBuffer));

                MemReadDetails memReadDetails = mMapOfMemReadDetails.get(InstructionsGet.GET_CALIB_DUMP_COMMAND_VALUE);
                if (memReadDetails != null) {
                    if (memReadDetails.mCurrentMemAddress == 0) {
                        int totalCalibLength = (rxBuf[1] & 0xFF) << 8 | (rxBuf[0] & 0xFF);
                        memReadDetails.setTotalMemLengthToRead(totalCalibLength + 2);

                        if (memReadDetails.getTotalMemLengthToRead() > memReadDetails.mCurrentMemLengthToRead) {
                            int nextAddress = memReadDetails.mCurrentMemLengthToRead;
                            int remainingBytes = memReadDetails.getTotalMemLengthToRead() - memReadDetails.mCurrentMemLengthToRead;
                            readCalibrationDump(nextAddress, remainingBytes);
                            rePioritiseReadCalibDumpInstructions();
                        }
                    }

                    int currentEndAddress = memReadDetails.mCurrentMemAddress + memReadDetails.mCurrentMemLengthToRead;
                    if (currentEndAddress >= memReadDetails.mEndMemAddress) {
                        eventResponseReceived(responseCommand, mMemBuffer);
                        clearMemReadBuffer(InstructionsGet.GET_CALIB_DUMP_COMMAND_VALUE);
                    }
                }
            } else if (responseCommand == InstructionsResponse.RSP_I2C_BATT_STATUS_COMMAND_VALUE) {
                byte[] responseData = readBytes(10);
                System.err.println("STC3100 response = " + UtilShimmer.bytesToHexStringWithSpacesFormatted(responseData));
            } else {
                printLogDataForDebugging("Unhandled BT response: " + UtilShimmer.bytesToHexStringWithSpacesFormatted(new byte[]{(byte) responseCommand}));
            }

        } catch (ShimmerException dE) {
            mUtilShimmer.consolePrintShimmerException(dE);
        }

    }

    private void processSpecialGetCmdsAfterAck(int currentCommand) {
        byte[] insBytes = getListofInstructions().get(0);
        if (mUseShimmerBluetoothApproach) {
            if (currentCommand == InstructionsGet.GET_EXG_REGS_COMMAND_VALUE) {
                mTempChipID = insBytes[1];
            } else if (currentCommand == InstructionsGet.GET_INFOMEM_COMMAND_VALUE || currentCommand == InstructionsGet.GET_CALIB_DUMP_COMMAND_VALUE) {
                MemReadDetails memReadDetails = mMapOfMemReadDetails.get(currentCommand);
                if (memReadDetails != null) {
                    memReadDetails.mCurrentMemAddress = ((insBytes[3] & 0xFF) << 8) + (insBytes[2] & 0xFF);
                    memReadDetails.mCurrentMemLengthToRead = (insBytes[1] & 0xFF);
                }
            }
        } else {
            eventAckReceived(insBytes[0]);
        }
    }


    protected boolean isKnownResponseByte(byte response) {
        return ((InstructionsResponse.valueOf(response & 0xff) == null) ? false : true);
    }


    private boolean isKnownGetCommand(int getCmd) {
        return isKnownGetCommandByte((byte) getCmd);
    }

    protected boolean isKnownGetCommandByte(byte getCmd) {
        return ((InstructionsGet.valueOf(getCmd & 0xff) == null) ? false : true);
    }

    private boolean isKnownSetCommand(int setCmd) {
        return isKnownSetCommandByte((byte) setCmd);
    }

    protected boolean isKnownSetCommandByte(byte setCmd) {
        return ((InstructionsSet.valueOf(setCmd & 0xff) == null) ? false : true);
    }

    private boolean bytesAvailableToBeRead() throws ShimmerException {
        if (mCommsInterface.isConnected()) {
            return mCommsInterface.bytesAvailableToBeRead();
        }
        return false;
    }

    protected void writeBytes(byte[] insBytes) throws ShimmerException {
        mCommsInterface.txBytes(insBytes);
    }

    protected byte[] readBytes(int i) throws ShimmerException {
        return mCommsInterface.rxBytes(i);
    }

    private byte readByte() throws ShimmerException {
        byte[] rxBytes = readBytes(1);
        return rxBytes[0];
    }

    private int availableBytes() throws ShimmerException {
        return mCommsInterface.availableBytes();
    }

    public void eventLogAndStreamStatusChanged(int currentCommand) {
        mProtocolListener.eventLogAndStreamStatusChangedCallback(currentCommand);
    }

    private void isNowStreaming() {
        mProtocolListener.isNowStreaming();
    }

    private void hasStopStreaming() {
        mProtocolListener.hasStopStreaming();
    }

    private void eventAckReceived(int lastSentInstruction) {
        mProtocolListener.eventAckReceived(lastSentInstruction);
    }

    private void sendProgressReport(BluetoothProgressReportPerCmd bluetoothProgressReportPerCmd) {
        mProtocolListener.sendProgressReport(bluetoothProgressReportPerCmd);
    }

    private void eventNewResponse(byte[] response) {
        mProtocolListener.eventNewResponse(response);
    }

    protected void eventResponseReceived(int responseCommand, Object parsedResponse) {
        mProtocolListener.eventResponseReceived(responseCommand, parsedResponse);
    }

    @Deprecated
    protected void eventAckInstruction(byte[] bs) {
        mProtocolListener.eventAckInstruction(bs);
    }

    private void eventNewPacket(byte[] newPacket, long pcTimestamp) {
        mProtocolListener.eventNewPacket(newPacket, pcTimestamp);
    }

    private void startOperation(BT_STATE currentOperation, int totalNumOfCmds) {
        mProtocolListener.startOperation(currentOperation, totalNumOfCmds);
    }

    private void sendStatusMSGtoUI(String msg) {
        mProtocolListener.sendStatusMSGtoUI(msg);
    }

    private void eventDockedStateChange() {
        mProtocolListener.eventDockedStateChange();
    }

    private void threadSleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    protected String btCommandToString(int cmd) {
        InstructionsResponse instructionResponse = InstructionsResponse.valueOf(cmd & 0xff);
        if (instructionResponse != null) {
            return instructionResponse.name();
        } else {
            InstructionsGet instructionGet = InstructionsGet.valueOf(cmd & 0xff);
            if (instructionGet != null) {
                return instructionGet.name();
            } else {
                InstructionsSet instructionSet = InstructionsSet.valueOf(cmd & 0xff);
                if (instructionSet != null) {
                    return instructionSet.name();
                } else {
                    return Integer.toHexString(mCurrentCommand);
                }
            }
        }
    }

    public void printLogDataForDebugging(String msg) {
        mUtilShimmer.consolePrintLn(msg);
    }

    private void delayForBtResponse(long millis) {
        if (mUseLegacyDelayToDelayForResponse)
            threadSleep(millis);
    }

    private void processAckFromSetCommandAndInstructions(int currentCommand) {
        if (getListofInstructions().size() > 0) {
            byte[] currentInstruction = getListofInstructions().get(0);
            if (currentInstruction != null) {

                processAckFromSetCommand(currentCommand);

                if (getListofInstructions().size() > 0) {
                    getListofInstructions().remove(0);
                }

                eventAckInstruction(currentInstruction);
            }
        }
    }

    protected void processAckFromSetCommand(int currentCommand) {
        if (currentCommand == InstructionsSet.START_STREAMING_COMMAND_VALUE
                || currentCommand == InstructionsSet.START_SDBT_COMMAND_VALUE) {
            mByteArrayOutputStream.reset();
            setIsStreaming(true);
            if (currentCommand == InstructionsSet.START_SDBT_COMMAND_VALUE) {
                setIsSDLogging(true);
                eventLogAndStreamStatusChanged(mCurrentCommand);
            }
            byteStack.clear();
            isNowStreaming();
        } else if ((currentCommand == InstructionsSet.STOP_STREAMING_COMMAND_VALUE)
                || (currentCommand == InstructionsSet.STOP_SDBT_COMMAND_VALUE)) {
            setIsStreaming(false);
            if (currentCommand == InstructionsSet.STOP_SDBT_COMMAND_VALUE) {
                setIsSDLogging(false);
                eventLogAndStreamStatusChanged(mCurrentCommand);
            }
            mByteArrayOutputStream = new ByteArrayOutputStream();
            byteStack.clear();

            try {
                clearSerialBuffer();
            } catch (ShimmerException e) {
                e.printStackTrace();
            }

            hasStopStreaming();
            getListofInstructions().removeAll(Collections.singleton(null));
        } else if (currentCommand == InstructionsSet.START_LOGGING_ONLY_COMMAND_VALUE) {
            setIsSDLogging(true);
            eventLogAndStreamStatusChanged(mCurrentCommand);
        } else if (currentCommand == InstructionsSet.STOP_LOGGING_ONLY_COMMAND_VALUE) {
            setIsSDLogging(false);
            eventLogAndStreamStatusChanged(mCurrentCommand);
        } else if (currentCommand == InstructionsSet.SET_INFOMEM_COMMAND_VALUE
                || currentCommand == InstructionsSet.SET_CALIB_DUMP_COMMAND_VALUE) {

            mNumOfMemSetCmds -= 1;
            if (!mShimmerVerObject.isBtMemoryUpdateCommandSupported()) {
                if (mNumOfMemSetCmds == 0) {
                    threadSleep(DELAY_BETWEEN_CONFIG_BYTE_WRITES);
                } else {
                    threadSleep(DELAY_AFTER_CONFIG_BYTE_WRITE);
                }
            }
        } else if (currentCommand == InstructionsSet.UPD_CONFIG_MEMORY_COMMAND_VALUE) {
            if (mShimmerVerObject.isBtMemoryUpdateCommandSupported()) {
                threadSleep(DELAY_AFTER_CONFIG_BYTE_WRITE);
            }
        } else {
        }
    }

    public void readRealTimeClock() {
        if ((getHardwareVersion() == HW_ID.SHIMMER_3 && getFirmwareIdentifier() == FW_ID.LOGANDSTREAM)
                || (getHardwareVersion() == HW_ID.SHIMMER_4_SDK && getFirmwareIdentifier() == FW_ID.SHIMMER4_SDK_STOCK)) {
            writeInstruction(InstructionsGet.GET_RWC_COMMAND_VALUE);
        }
    }

    public void writeRealTimeClock() {
        if ((getHardwareVersion() == HW_ID.SHIMMER_3 && getFirmwareIdentifier() == FW_ID.LOGANDSTREAM)
                || (getHardwareVersion() == HW_ID.SHIMMER_4_SDK && getFirmwareIdentifier() == FW_ID.SHIMMER4_SDK_STOCK)) {
            byte[] bytearraycommand = new byte[9];
            bytearraycommand[0] = (byte) InstructionsSet.SET_RWC_COMMAND_VALUE;
            getListofInstructions().add(bytearraycommand);
        }
    }

    private void clearBuffers() {
        mByteArrayOutputStream.reset();
        mListofPCTimeStamps.clear();
    }

    private void discardFirstBufferByte() {
        byte[] bTemp = mByteArrayOutputStream.toByteArray();
        mByteArrayOutputStream.reset();
        mByteArrayOutputStream.write(bTemp, 1, bTemp.length - 1);
        mListofPCTimeStamps.remove(0);
        printLogDataForDebugging("Throw Byte" + UtilShimmer.byteToHexStringFormatted(bTemp[0]));
    }

    private void processDataPacket(byte[] bufferTemp) {
        byte[] newPacket = new byte[mPacketSize];
        System.arraycopy(bufferTemp, 1, newPacket, 0, mPacketSize);

        long pcTimestamp = System.currentTimeMillis();
        eventNewPacket(newPacket, pcTimestamp);
    }

    private void clearSingleDataPacketFromBuffers(byte[] bufferTemp, int packetSize) {
        mByteArrayOutputStream.reset();
        mByteArrayOutputStream.write(bufferTemp[packetSize]);
        for (int i = 0; i < packetSize; i++) {
            mListofPCTimeStamps.remove(0);
        }
    }

    private void processInstreamResponse() {
        try {
            byte[] inStreamResponseCommandArray = readBytes(1);
            processInstreamResponse(inStreamResponseCommandArray);
        } catch (ShimmerException e) {
            e.printStackTrace();
        }
    }

    protected void processInstreamResponse(byte[] inStreamResponseCommandArray) throws ShimmerException {
        int inStreamResponseCommand = ((int) inStreamResponseCommandArray[0]) & 0xFF;
        printLogDataForDebugging("In-stream received = " + btCommandToString(inStreamResponseCommand));

        if (inStreamResponseCommand == InstructionsResponse.DIR_RESPONSE_VALUE) {
            byte[] responseData = readBytes(1);
            int directoryNameLength = responseData[0];
            byte[] bufferDirectoryName = new byte[directoryNameLength];
            bufferDirectoryName = readBytes(directoryNameLength);
            String tempDirectory = new String(bufferDirectoryName);
            mDirectoryName = tempDirectory;
            printLogDataForDebugging("Directory Name = " + mDirectoryName);

            if (!mUseShimmerBluetoothApproach) {
                bufferDirectoryName = ArrayUtils.addAll(responseData, bufferDirectoryName);
                eventNewResponse(bufferDirectoryName);
            } else {
                eventResponseReceived(inStreamResponseCommand, tempDirectory);
            }
        } else if (inStreamResponseCommand == InstructionsResponse.STATUS_RESPONSE_VALUE) {
            if (mUseShimmerBluetoothApproach) {
                byte[] responseData = readBytes(1);
                parseStatusByte(responseData[0]);
            }

            if (!isSensing()) {
                if (mUseShimmerBluetoothApproach) {
                    if (!isInitialised()) {
                        writeRealTimeClock();
                    }
                } else {
                    writeRealTimeClock();
                }
            }
            eventLogAndStreamStatusChanged(mCurrentCommand);
        } else if (inStreamResponseCommand == InstructionsResponse.VBATT_RESPONSE_VALUE) {
            byte[] responseData = readBytes(3);
            if (mUseShimmerBluetoothApproach) {
                ShimmerBattStatusDetails battStatusDetails = new ShimmerBattStatusDetails(((responseData[1] & 0xFF) << 8) + (responseData[0] & 0xFF), responseData[2]);
                eventResponseReceived(inStreamResponseCommand, battStatusDetails);
                printLogDataForDebugging("Battery Status:"
                        + "\tVoltage=" + battStatusDetails.getBattVoltageParsed()
                        + "\tCharging status=" + battStatusDetails.getChargingStatusParsed()
                        + "\tBatt %=" + battStatusDetails.getEstimatedChargePercentageParsed());
            } else {
                responseData = ArrayUtils.addAll(inStreamResponseCommandArray, responseData);
                eventNewResponse(responseData);
            }
        } else if (inStreamResponseCommand == InstructionsResponse.RSP_I2C_BATT_STATUS_COMMAND_VALUE) {
            byte[] responseData = readBytes(10);
            SensorSTC3100Details sensorSTC3100Details = new SensorSTC3100Details(responseData);
            eventResponseReceived(inStreamResponseCommand, sensorSTC3100Details);
        }
    }

    private void parseStatusByte(byte statusByte) {
        Boolean savedDockedState = isDocked();

        setIsDocked(((statusByte & 0x01) > 0) ? true : false);
        setIsSensing(((statusByte & 0x02) > 0) ? true : false);
        setIsSDLogging(((statusByte & 0x08) > 0) ? true : false);
        setIsStreaming(((statusByte & 0x10) > 0) ? true : false);

        printLogDataForDebugging("Status Response = " + UtilShimmer.byteToHexStringFormatted(statusByte)
                + " | IsDocked = " + isDocked()
                + " | IsSensing = " + isSensing()
                + " | IsSDLogging = " + isSDLogging()
                + " | IsStreaming = " + isStreaming()
        );

        if (savedDockedState != isDocked()) {
            eventDockedStateChange();
        }
    }

    public void readSamplingRate() {
        writeInstruction(InstructionsGet.GET_SAMPLING_RATE_COMMAND_VALUE);
    }

    protected void dummyreadSamplingRate() {
        mDummy = true;
        writeInstruction(InstructionsGet.GET_SAMPLING_RATE_COMMAND_VALUE);
    }

    public void writeShimmerAndSensorsSamplingRate(byte[] samplingRateBytes) {

        writePacket(InstructionsSet.SET_SAMPLING_RATE_COMMAND_VALUE, samplingRateBytes);
        readSamplingRate();
    }

    private byte[] buildCmdArray(int cmd, byte[] payload) {
        byte[] packet = new byte[payload.length + 1];
        packet[0] = (byte) cmd;
        System.arraycopy(payload, 0, packet, 1, payload.length);
        return packet;
    }

    private void writePacket(int cmd, byte[] payload) {
        writeInstruction(buildCmdArray(cmd, payload));
    }

    @Override
    public void toggleLed() {
        byte[] instructionLED = {InstructionsSet.TOGGLE_LED_COMMAND_VALUE};
        writeInstruction(instructionLED);
    }

    @Override
    public void readFWVersion() {
        mDummy = false;
        writeInstruction(InstructionsGet.GET_FW_VERSION_COMMAND_VALUE);
    }

    @Override
    public void readShimmerVersionNew() {
        mDummy = false;

        writeInstruction(InstructionsGet.GET_SHIMMER_VERSION_COMMAND_NEW_VALUE);
    }

    @Override
    public void readPressureCalibrationCoefficients() {
        if ((getHardwareVersion() == HW_ID.SHIMMER_3)
                || (getHardwareVersion() == HW_ID.SHIMMER_4_SDK)) {
            if (getFirmwareVersionCode() > 1) {
                writeInstruction(InstructionsGet.GET_BMP180_CALIBRATION_COEFFICIENTS_COMMAND_VALUE);
            }
        }
    }

    public void readBaudRate() {
        if (getFirmwareVersionCode() >= 5) {
            writeInstruction(InstructionsGet.GET_BAUD_RATE_COMMAND_VALUE);
        }
    }

    public void writeBaudRate(int value) {
        if (getFirmwareVersionCode() >= 5) {
            if (value >= 0 && value <= 10) {
                writeInstruction(new byte[]{InstructionsSet.SET_BAUD_RATE_COMMAND_VALUE, (byte) value});
                delayForBtResponse(200);
                reconnect();
            }
        }
    }

    private void reconnect() {

        try {
            mCommsInterface.disconnect();
            threadSleep(300);
            mCommsInterface.connect();
        } catch (ShimmerException e) {
            e.printStackTrace();
        }
    }

    public void readConfigByte0() {
        writeInstruction(InstructionsGet.GET_CONFIG_BYTE0_COMMAND_VALUE);
    }

    public void writeConfigByte0(byte[] configByte0) {
        writePacket(InstructionsSet.SET_CONFIG_BYTE0_COMMAND_VALUE, configByte0);
        readConfigByte0();
    }

    public void readBufferSize() {
        writeInstruction(InstructionsGet.GET_BUFFER_SIZE_COMMAND_VALUE);
    }

    public void writeBufferSize(int size) {
        writeInstruction(new byte[]{InstructionsSet.SET_BUFFER_SIZE_COMMAND_VALUE, (byte) size});
    }

    @Override
    public void readCalibrationDump() {
        if (this.getFirmwareVersionCode() >= 7) {
            readMem(InstructionsGet.GET_CALIB_DUMP_COMMAND_VALUE, 0, 128, MAX_CALIB_DUMP_MAX);
        }
    }

    @Override
    public void readCalibrationDump(int address, int size) {
        if (this.getFirmwareVersionCode() >= 7) {
            readMem(InstructionsGet.GET_CALIB_DUMP_COMMAND_VALUE, address, size, MAX_CALIB_DUMP_MAX);
        }
    }

    public void rePioritiseReadCalibDumpInstructions() {
        List<byte[]> listOfInstructions = new ArrayList<byte[]>();

        Iterator<byte[]> iterator = mListofInstructions.iterator();
        while (iterator.hasNext()) {
            byte[] instruction = iterator.next();
            if (instruction[0] == (byte) InstructionsGet.GET_CALIB_DUMP_COMMAND_VALUE) {
                listOfInstructions.add(instruction);
                iterator.remove();
            }
        }

        if (listOfInstructions.size() > 0) {
            mListofInstructions.addAll(0, listOfInstructions);
        }
    }

    @Override
    public void writeCalibrationDump(byte[] calibDump) {
        if (this.getFirmwareVersionCode() >= 7) {
            writeMem(InstructionsSet.SET_CALIB_DUMP_COMMAND_VALUE, 0, calibDump, MAX_CALIB_DUMP_MAX);
            writeUpdateConfigMemory();
            readCalibrationDump();
        }
    }

    @Override
    protected void readInfoMem(int address, int size) {
        readMem(InstructionsGet.GET_INFOMEM_COMMAND_VALUE, address, size, 512);
    }

    @Override
    public void writeInfoMem(int startAddress, byte[] buf) {
        writeMem(InstructionsSet.SET_INFOMEM_COMMAND_VALUE, startAddress, buf, 512);
        writeUpdateConfigMemory();
    }

    private void writeUpdateConfigMemory() {
        if (mShimmerVerObject.isSupportedCalibDump()) {
            writeInstruction(InstructionsSet.UPD_CONFIG_MEMORY_COMMAND_VALUE);
        }
    }

    @Override
    public void readMemCommand(int command, int address, int size) {
        if (this.getFirmwareVersionCode() >= 6) {
            byte[] memLengthToRead = new byte[]{(byte) size};
            byte[] memAddressToRead = ByteBuffer.allocate(2).putShort((short) (address & 0xFFFF)).array();
            ArrayUtils.reverse(memAddressToRead);

            byte[] instructionBuffer = new byte[1 + memLengthToRead.length + memAddressToRead.length];
            instructionBuffer[0] = (byte) command;
            System.arraycopy(memLengthToRead, 0, instructionBuffer, 1, memLengthToRead.length);
            System.arraycopy(memAddressToRead, 0, instructionBuffer, 1 + memLengthToRead.length, memAddressToRead.length);

            writeInstruction(instructionBuffer);
        }
    }

    public void writeMemCommand(int command, int address, byte[] infoMemBytes) {
        byte[] memLengthToWrite = new byte[]{(byte) infoMemBytes.length};
        byte[] memAddressToWrite = ByteBuffer.allocate(2).putShort((short) (address & 0xFFFF)).array();
        ArrayUtils.reverse(memAddressToWrite);

        byte[] instructionBuffer = new byte[1 + memLengthToWrite.length + memAddressToWrite.length + infoMemBytes.length];
        instructionBuffer[0] = (byte) command;
        System.arraycopy(memLengthToWrite, 0, instructionBuffer, 1, memLengthToWrite.length);
        System.arraycopy(memAddressToWrite, 0, instructionBuffer, 1 + memLengthToWrite.length, memAddressToWrite.length);
        System.arraycopy(infoMemBytes, 0, instructionBuffer, 1 + memLengthToWrite.length + memAddressToWrite.length, infoMemBytes.length);

        writeInstruction(instructionBuffer);
    }

    @Override
    public void operationPrepare() {
        stopAllTimers();

        getListofInstructions().clear();
        while (getListofInstructions().size() > 0) ;
        setInstructionStackLock(true);
        mOperationUnderway = true;
    }

    public void operationWaitForFinish() {
        setInstructionStackLock(false);
        while (getListofInstructions().size() > 0) ;
    }


    @Override
    public void operationStart(BT_STATE btState) {
        startOperation(btState, getListofInstructions().size());
        setInstructionStackLock(false);
    }

    public void operationFinished() {
        mOperationUnderway = false;
    }

    @Override
    public void startStreaming() {

        initialiseStreaming();

        mByteArrayOutputStream.reset();
        mListofPCTimeStamps.clear();
        writeInstruction(InstructionsSet.START_STREAMING_COMMAND_VALUE);
    }

    @Override
    public void startDataLogAndStreaming() {
        if ((getShimmerVersion() == HW_ID.SHIMMER_3 && getFirmwareIdentifier() == FW_ID.LOGANDSTREAM)
                || (getShimmerVersion() == HW_ID.SHIMMER_4_SDK && getFirmwareIdentifier() == FW_ID.SHIMMER4_SDK_STOCK)) {
            initialiseStreaming();

            writeInstruction(InstructionsSet.START_SDBT_COMMAND_VALUE);
        }
    }

    private void initialiseStreaming() {
        if (((getShimmerVersion() == HW_ID.SHIMMER_3 && getFirmwareIdentifier() == FW_ID.LOGANDSTREAM)
                || (getShimmerVersion() == HW_ID.SHIMMER_4_SDK && getFirmwareIdentifier() == FW_ID.SHIMMER4_SDK_STOCK))
                && getFirmwareVersionCode() >= 6) {
            readRealTimeClock();
        }

        stopTimerReadStatus();

        mProtocolListener.initialiseStreamingCallback();
        System.out.println("initialiseStreamingCallback:\tRETURNED");
    }

    @Override
    public void startSDLogging() {
        if (((getShimmerVersion() == HW_ID.SHIMMER_3 && getFirmwareIdentifier() == FW_ID.LOGANDSTREAM)
                || (getShimmerVersion() == HW_ID.SHIMMER_4_SDK && getFirmwareIdentifier() == FW_ID.SHIMMER4_SDK_STOCK))
                && getFirmwareVersionCode() >= 6) {
            writeInstruction(InstructionsSet.START_LOGGING_ONLY_COMMAND_VALUE);
        }
    }

    @Override
    public void stopSDLogging() {
        if (((getShimmerVersion() == HW_ID.SHIMMER_3 && getFirmwareIdentifier() == FW_ID.LOGANDSTREAM)
                || (getShimmerVersion() == HW_ID.SHIMMER_4_SDK && getFirmwareIdentifier() == FW_ID.SHIMMER4_SDK_STOCK))
                && getFirmwareVersionCode() >= 6) {
            writeInstruction(InstructionsSet.STOP_LOGGING_ONLY_COMMAND_VALUE);
        }
    }

    @Override
    public void stopStreaming() {
        if (isStreaming()) {
            writeInstruction(InstructionsSet.STOP_STREAMING_COMMAND_VALUE);

            stopTimerReadStatus();
        }
    }

    @Override
    public void stopStreamingAndLogging() {
        if ((getShimmerVersion() == HW_ID.SHIMMER_3 && getFirmwareIdentifier() == FW_ID.LOGANDSTREAM)
                || (getShimmerVersion() == HW_ID.SHIMMER_4_SDK && getFirmwareIdentifier() == FW_ID.SHIMMER4_SDK_STOCK)) {
            writeInstruction(InstructionsSet.STOP_SDBT_COMMAND_VALUE);
            stopTimerReadStatus();
        }
    }

    @Override
    public void writeBattStatusPeriod(int periodInSec) {
        if (getShimmerVersion() == HW_ID.SHIMMER_4_SDK) {
            byte[] buf = new byte[2];
            buf[0] = (byte) (periodInSec & 0xFF);
            buf[1] = (byte) ((periodInSec >> 8) & 0xFF);
            writeInstruction(new byte[]{(byte) (InstructionsSet.SET_I2C_BATT_STATUS_FREQ_COMMAND_VALUE & 0xFF), buf[0], buf[1]});
        }
    }

    @Override
    public void readBattStatusPeriod() {
        if (getShimmerVersion() == HW_ID.SHIMMER_4_SDK) {
            writeInstruction(InstructionsGet.GET_I2C_BATT_STATUS_COMMAND_VALUE);
        }
    }

    public void stopAllTimers() {
        stopTimerReadStatus();
        stopTimerCheckIfAlive();
        stopTimerCheckForAckOrResp();
        stopTimerReadBattStatus();
    }

    public void stopTimerCheckForAckOrResp() {
        if (mTimerCheckForAckOrResp != null) {
            mTimerCheckForAckOrResp.cancel();
            mTimerCheckForAckOrResp.purge();
            mTimerCheckForAckOrResp = null;
        }
    }


    public synchronized void startTimerCheckForAckOrResp(int seconds) {
        if (mTimerCheckForAckOrResp != null) {
            mTimerCheckForAckOrResp.cancel();
            mTimerCheckForAckOrResp.purge();
            mTimerCheckForAckOrResp = null;
        }
        printLogDataForDebugging("Waiting for ack/response for command:\t" + btCommandToString(mCurrentCommand));
        mTimerCheckForAckOrResp = new Timer("Shimmer_" + getConnectionHandle() + "_TimerCheckForResp");
        mTimerCheckForAckOrResp.schedule(new checkForAckOrRespTask(), seconds * 1000);
    }

    protected void processCheckForAckOrRespPerCmd(int currentCommand) {
        if ((currentCommand & 0xFF) == InstructionsSet.START_LOGGING_ONLY_COMMAND_VALUE) {

            printLogDataForDebugging("START_LOGGING_ONLY_COMMAND response not received. Send feedback to the GUI without killing the connection");

            setIsSDLogging(true);
            eventLogAndStreamStatusChanged(currentCommand);
            mWaitForAck = false;
            mWaitForResponse = false;

            getListofInstructions().remove(0);
            mTransactionCompleted = true;
            setInstructionStackLock(false);

            return;
        } else if ((currentCommand & 0xFF) == InstructionsSet.STOP_LOGGING_ONLY_COMMAND_VALUE) {

            printLogDataForDebugging("STOP_LOGGING_ONLY_COMMAND response not received. Send feedback to the GUI without killing the connection");

            setIsSDLogging(false);
            eventLogAndStreamStatusChanged(currentCommand);
            mWaitForAck = false;
            mWaitForResponse = false;

            getListofInstructions().remove(0);
            mTransactionCompleted = true;
            setInstructionStackLock(false);

            return;
        }


        if ((currentCommand & 0xFF) == InstructionsGet.GET_FW_VERSION_COMMAND_VALUE) {
            mFirstTime = false;
//
        } else if ((currentCommand & 0xFF) == InstructionsGet.GET_SAMPLING_RATE_COMMAND_VALUE && !isInitialised()) {
            mFirstTime = false;
        } else if ((currentCommand & 0xFF) == InstructionsGet.GET_SHIMMER_VERSION_COMMAND_NEW_VALUE) { //in case the new command doesn't work, try the old command
            mFirstTime = false;
            getListofInstructions().clear();
            readShimmerVersionDeprecated();
        }
    }

    private void killConnection(String info) {
        killConnection(null, info);
    }

    private void killConnection(ShimmerException dE) {
        killConnection(dE, "");
    }

    private void killConnection(ShimmerException dE, String info) {
        printLogDataForDebugging("Killing Connection" + (info.isEmpty() ? "" : (": " + info)));
        mProtocolListener.eventKillConnectionRequest(dE);
    }

    @Override
    public void startTimerReadStatus() {
        if ((getHardwareVersion() == HW_ID.SHIMMER_3 && getFirmwareIdentifier() == FW_ID.LOGANDSTREAM)
                || (getHardwareVersion() == HW_ID.SHIMMER_4_SDK && getFirmwareIdentifier() == FW_ID.SHIMMER4_SDK_STOCK)) {
            if (mTimerReadStatus == null) {
                mTimerReadStatus = new Timer("Shimmer_" + getConnectionHandle() + "_TimerReadStatus");
            } else {
                mTimerReadStatus.cancel();
                mTimerReadStatus.purge();
                mTimerReadStatus = null;
            }
            mTimerReadStatus.schedule(new readStatusTask(), TIMER_READ_STATUS_PERIOD, TIMER_READ_STATUS_PERIOD);
        }
    }

    public void stopTimerReadStatus() {
        if (mTimerReadStatus != null) {
            mTimerReadStatus.cancel();
            mTimerReadStatus.purge();
            mTimerReadStatus = null;
        }
    }

    @Override
    public void startTimerCheckIfAlive() {
        if (mCheckIfConnectionisAlive) {
            if (mTimerCheckAlive == null) {
                mTimerCheckAlive = new Timer("Shimmer_" + getConnectionHandle() + "_TimerCheckAlive");
            }
            int hwId = getHardwareVersion();
            int fwId = getFirmwareIdentifier();
            if ((hwId == HW_ID.SHIMMER_3 && fwId == FW_ID.LOGANDSTREAM)
                    || (hwId == HW_ID.SHIMMER_3 && fwId == FW_ID.BTSTREAM)
                    || (hwId == HW_ID.SHIMMER_4_SDK && fwId == FW_ID.SHIMMER4_SDK_STOCK)
                    || (hwId == HW_ID.SWEATCH)) {
                mTimerCheckAlive.schedule(new checkIfAliveTask(), TIMER_CHECK_ALIVE_PERIOD, TIMER_CHECK_ALIVE_PERIOD);
            }
        }
    }

    @Override
    public void stopTimerCheckIfAlive() {
        if (mTimerCheckAlive != null) {
            mTimerCheckAlive.cancel();
            mTimerCheckAlive.purge();
            mTimerCheckAlive = null;
        }
    }

    @Override
    public void startTimerReadBattStatus() {
        if (((getShimmerVersion() == HW_ID.SHIMMER_3 && getFirmwareIdentifier() == FW_ID.LOGANDSTREAM)
                || (getShimmerVersion() == HW_ID.SHIMMER_4_SDK && getFirmwareIdentifier() == FW_ID.SHIMMER4_SDK_STOCK))
                && (getFirmwareVersionCode() >= 6)) {
            if (mTimerReadBattStatus == null) {
                mTimerReadBattStatus = new Timer("Shimmer_" + getConnectionHandle() + "_TimerBattStatus");
            }
            mTimerReadBattStatus.schedule(new readBattStatusTask(), TIMER_READ_BATT_STATUS_PERIOD, TIMER_READ_BATT_STATUS_PERIOD);
        }
    }

    public void stopTimerReadBattStatus() {
        if (mTimerReadBattStatus != null) {
            mTimerReadBattStatus.cancel();
            mTimerReadBattStatus.purge();
            mTimerReadBattStatus = null;
        }
    }

    @Override
    public void restartTimersIfNull() {
        if (mTimerCheckAlive == null && mTimerReadStatus == null && mTimerReadBattStatus == null) {
            startTimerCheckIfAlive();
            startTimerReadStatus();
            startTimerReadBattStatus();
        }
    }

    @Override
    public void readExpansionBoardID() {
        if (getFirmwareVersionCode() >= 5) {
            numBytesToReadFromExpBoard = 3;
            int offset = 0;
            writeInstruction(new byte[]{InstructionsGet.GET_DAUGHTER_CARD_ID_COMMAND_VALUE, (byte) numBytesToReadFromExpBoard, (byte) offset});
        }
    }

    @Override
    public void readLEDCommand() {
        writeInstruction(InstructionsGet.GET_BLINK_LED_VALUE);
    }

    public void writeLEDCommand(int command) {
        if (mShimmerVerObject.compareVersions(HW_ID.SHIMMER_2R, FW_ID.BOILER_PLATE, 0, 1, 0)) {
        } else {
            writeInstruction(new byte[]{InstructionsSet.SET_BLINK_LED_VALUE, (byte) command});
        }
    }

    @Override
    public void readStatusLogAndStream() {
        if ((getShimmerVersion() == HW_ID.SHIMMER_3 && getFirmwareIdentifier() == FW_ID.LOGANDSTREAM)
                || (getShimmerVersion() == HW_ID.SHIMMER_4_SDK && getFirmwareIdentifier() == FW_ID.SHIMMER4_SDK_STOCK)) {
            writeInstruction(InstructionsGet.GET_STATUS_COMMAND_VALUE);
            printLogDataForDebugging("Instruction added to th e list");
        }
    }

    @Override
    public void readBattery() {
        writeInstruction(InstructionsGet.GET_VBATT_COMMAND_VALUE);
    }

    @Override
    public void inquiry() {
        writeInstruction(InstructionsGet.INQUIRY_COMMAND_VALUE);
    }
//
//
//
//
//
//
//
//
//
//
//
//


    public void writeTestConnectionCommand() {
        if (getFirmwareVersionCode() >= 6) {
            writeInstruction(InstructionsSet.TEST_CONNECTION_COMMAND_VALUE);
        }
    }

    @Deprecated
    public void readShimmerVersionDeprecated() {
        writeInstruction(InstructionsGet.GET_SHIMMER_VERSION_COMMAND_VALUE);
    }

    /**
     * Transmits a command to the Shimmer device to enable the sensors. To enable multiple sensors an or operator should be used (e.g. writeEnabledSensors(SENSOR_ACCEL|SENSOR_GYRO|SENSOR_MAG)). Command should not be used consecutively. Valid values are SENSOR_ACCEL, SENSOR_GYRO, SENSOR_MAG, SENSOR_ECG, SENSOR_EMG, SENSOR_GSR, SENSOR_EXP_BOARD_A7, SENSOR_EXP_BOARD_A0, SENSOR_BRIDGE_AMP and SENSOR_HEART.
     * SENSOR_BATT
     *
     * @param enabledSensors e.g SENSOR_ACCEL|SENSOR_GYRO|SENSOR_MAG
     */
    @Override
    public void writeEnabledSensors(long enabledSensors) {

        byte secondByte = (byte) ((enabledSensors & 0xFF00) >> 8);
        byte firstByte = (byte) (enabledSensors & 0xFF);

        if ((getHardwareVersion() == HW_ID.SHIMMER_3)
                || (getHardwareVersion() == HW_ID.SHIMMER_4_SDK)) {
            byte thirdByte = (byte) ((enabledSensors & 0xFF0000) >> 16);
            writeInstruction(new byte[]{InstructionsSet.SET_SENSORS_COMMAND_VALUE, (byte) firstByte, (byte) secondByte, (byte) thirdByte});
        } else {
            writeInstruction(new byte[]{InstructionsSet.SET_SENSORS_COMMAND_VALUE, (byte) firstByte, (byte) secondByte});
        }
        inquiry();
    }

    public void readUniqueSerial() {
        writeInstruction(new byte[]{InstructionsGet.GET_UNIQUE_SERIAL_COMMAND_VALUE});
    }

    private int getFirmwareVersionCode() {
        if (mShimmerVerObject != null) {
            return mShimmerVerObject.getFirmwareVersionCode();
        }
        return 6;
    }

    private int getHardwareVersion() {
        return mHardwareVersion;
    }

    private void setHardwareVersion(int hardwareVersion) {
        mHardwareVersion = hardwareVersion;
    }

    private String getConnectionHandle() {
        return mConnectionHandle;
    }

    public void setIsDocked(boolean docked) {
        mIsDocked = docked;
        if (mProtocolListener != null) {
            mProtocolListener.eventSetIsDocked(mIsDocked);
        }
    }

    public boolean isDocked() {
        return mIsDocked;
    }

    public boolean isStreaming() {
        return mIsStreaming;
    }

    public void setIsStreaming(boolean state) {
        mIsStreaming = state;
        if (mProtocolListener != null) {
            mProtocolListener.eventSetIsStreaming(mIsStreaming);
        }
    }

    public boolean isSensing() {
        return (mIsSensing || mIsSDLogging || mIsStreaming);
    }

    public void setIsSensing(boolean state) {
        mIsSensing = state;
        if (mProtocolListener != null) {
            mProtocolListener.eventSetIsSensing(mIsSensing);
        }
    }


    public boolean isSDLogging() {
        return mIsSDLogging;
    }

    public void setIsSDLogging(boolean state) {
        UtilShimmer.consolePrintCurrentStackTrace();
        mIsSDLogging = state;
        if (mProtocolListener != null) {
            mProtocolListener.eventSetIsSDLogging(mIsSDLogging);
        }
    }

    public void setIsInitialised(boolean isInitialized) {
        mIsInitialised = isInitialized;
        if (mProtocolListener != null) {
            mProtocolListener.eventSetIsInitialised(mIsInitialised);
        }
    }

    public boolean isInitialised() {
        return mIsInitialised;
    }

    public boolean isHaveAttemptedToRead() {
        return mHaveAttemptedToReadConfig;
    }

    public void setHaveAttemptedToRead(boolean haveAttemptedToRead) {
        mHaveAttemptedToReadConfig = haveAttemptedToRead;
        if (mProtocolListener != null) {
            mProtocolListener.eventSetHaveAttemptedToRead(mHaveAttemptedToReadConfig);
        }
    }

    public class Temp {
        public class InstructionsSet {
        }

        public class InstructionsResponse {
        }

        public class InstructionsGet {
        }
    }

    public class IOThread extends Thread {
        public boolean stop = false;
        byte[] byteBuffer = {0};

        public synchronized void run() {
            while (!stop) {
                try {
                    if (!isInstructionStackLock()) {
                        processNextInstruction();
                    }

                    if (isStreaming()) {
                        processWhileStreaming();
                    } else if (bytesAvailableToBeRead()) {
                        if (mWaitForAck) {
                            processNotStreamingWaitForAck();
                        } else if (mWaitForResponse) {
                            processNotStreamingWaitForResp();
                        }

                        processBytesAvailableAndInstreamSupported();
                    }
                } catch (ShimmerException dE) {

                    killConnection(dE);
                }
            }
        }

        private void processNextInstruction() throws ShimmerException {
            if (!getListofInstructions().isEmpty()) {
                if (getListofInstructions().get(0) == null) {
                    getListofInstructions().remove(0);
                    printLogDataForDebugging("Null Removed");
                }
            }

            if (!getListofInstructions().isEmpty()) {
                if (getListofInstructions().get(0) != null) {
                    byte[] insBytes = (byte[]) getListofInstructions().get(0);
                    mCurrentCommand = ((int) insBytes[0]) & 0xFF;
                    setInstructionStackLock(true);
                    mWaitForAck = true;

                    if (!isStreaming()) {
                        clearSerialBuffer();
                    }
                    if (mCurrentCommand == InstructionsSet.SET_RWC_COMMAND_VALUE) {
                        byte[] rtcTimeArray = UtilShimmer.convertMilliSecondsToShimmerRtcDataBytesLSB(System.currentTimeMillis());
                        System.arraycopy(rtcTimeArray, 0, insBytes, 1, 8);
                    }

                    if (mCurrentCommand == InstructionsSet.STOP_STREAMING_COMMAND_VALUE
                            || mCurrentCommand == InstructionsSet.STOP_SDBT_COMMAND_VALUE) {
                    } else {
                        if (mCurrentCommand == InstructionsSet.SET_SENSORS_COMMAND_VALUE
                                && getShimmerVersion() == HW_ID.SHIMMER_2R) {
                            startTimerCheckForAckOrResp(ACK_TIMER_DURATION + 8);
                        } else if ((mCurrentCommand == InstructionsGet.GET_FW_VERSION_COMMAND_VALUE)
                                || (mCurrentCommand == InstructionsGet.GET_SAMPLING_RATE_COMMAND_VALUE)
                                || (mCurrentCommand == InstructionsGet.GET_SHIMMER_VERSION_COMMAND_NEW_VALUE)) {
                            startTimerCheckForAckOrResp(ACK_TIMER_DURATION);
                        } else {
                            if (isStreaming()) {
                                startTimerCheckForAckOrResp(ACK_TIMER_DURATION);
                            } else {
                                startTimerCheckForAckOrResp(ACK_TIMER_DURATION + 3);
                            }
                        }
                    }

                    threadSleep((int) ((Math.random() + .1) * 100.0));
                    writeBytes(insBytes);
                    printLogDataForDebugging("Command Transmitted: \t\t\t" + btCommandToString(mCurrentCommand) + " " + UtilShimmer.bytesToHexStringWithSpacesFormatted(insBytes));

                    if (mCurrentCommand == InstructionsSet.STOP_STREAMING_COMMAND_VALUE
                            || mCurrentCommand == InstructionsSet.STOP_SDBT_COMMAND_VALUE) {
                        setIsStreaming(false);
                        if (mCurrentCommand == InstructionsSet.STOP_SDBT_COMMAND_VALUE) {
                            setIsSDLogging(false);
                        }

                        if (!mUseShimmerBluetoothApproach) {
                        }

                        getListofInstructions().removeAll(Collections.singleton(null));
                    } else {
						/*
						if((mCurrentCommand==GET_FW_VERSION_COMMAND)
								||(mCurrentCommand==GET_SAMPLING_RATE_COMMAND)
								||(mCurrentCommand==GET_SHIMMER_VERSION_COMMAND_NEW)){
							startTimerCheckForAckOrResp(ACK_TIMER_DURATION);
						}
						else {
							if(mIsStreaming){
								startTimerCheckForAckOrResp(ACK_TIMER_DURATION);
							}
							else {
								startTimerCheckForAckOrResp(ACK_TIMER_DURATION+3);
							}
						}*/
                    }


                    mTransactionCompleted = false;
                }
            } else {
                if (!isStreaming() && !bytesAvailableToBeRead()) {
                    threadSleep(50);
                }
            }
        }

        private void processWhileStreaming() throws ShimmerException {
            byteBuffer = readBytes(1);
            if (byteBuffer != null) {
                mByteArrayOutputStream.write(byteBuffer[0]);
                mListofPCTimeStamps.add(System.currentTimeMillis());
            } else {
                printLogDataForDebugging("readbyte null");
            }

            if (mByteArrayOutputStream.size() >= mPacketSize + 2) {
                processPacket();
            }
        }

        private void processPacket() {
            mIamAlive = true;
            byte[] bufferTemp = mByteArrayOutputStream.toByteArray();

            if (bufferTemp[0] == InstructionsSet.DATA_PACKET_VALUE
                    && bufferTemp[mPacketSize + 1] == InstructionsSet.DATA_PACKET_VALUE) {
                processDataPacket(bufferTemp);
                clearSingleDataPacketFromBuffers(bufferTemp, mPacketSize + 1);
            } else if (bufferTemp[0] == InstructionsSet.DATA_PACKET_VALUE
                    && bufferTemp[mPacketSize + 1] == InstructionsSet.ACK_COMMAND_PROCESSED_VALUE) {
                if (mByteArrayOutputStream.size() > mPacketSize + 2) {

                    if (bufferTemp[mPacketSize + 2] == InstructionsSet.DATA_PACKET_VALUE) {
                        processDataPacket(bufferTemp);
                        clearSingleDataPacketFromBuffers(bufferTemp, mPacketSize + 2);

                        if (isKnownSetCommand(mCurrentCommand)) {
                            stopTimerCheckForAckOrResp();
                            mWaitForAck = false;

                            processAckFromSetCommandAndInstructions(((int) mCurrentCommand) & 0xFF);

                            mTransactionCompleted = true;
                            setInstructionStackLock(false);
                        } else {
                            printLogDataForDebugging("Unknown SET command = " + mCurrentCommand);
                        }
                        printLogDataForDebugging("Ack Received for Command: \t\t\t\t" + btCommandToString(mCurrentCommand));
                    } else if (((getHardwareVersion() == HW_ID.SHIMMER_3 && getFirmwareIdentifier() == FW_ID.LOGANDSTREAM)
                            || (getHardwareVersion() == HW_ID.SHIMMER_4_SDK && getFirmwareIdentifier() == FW_ID.SHIMMER4_SDK_STOCK))
                            && bufferTemp[mPacketSize + 2] == ((byte) (InstructionsResponse.INSTREAM_CMD_RESPONSE_VALUE))) {
                        printLogDataForDebugging("COMMAND TXed and ACK RECEIVED IN STREAM");
                        printLogDataForDebugging("INS CMD RESP");

                        stopTimerCheckForAckOrResp();
                        mWaitForResponse = false;
                        mWaitForAck = false;

                        processInstreamResponse();

                        if (getListofInstructions().size() > 0) {
                            getListofInstructions().remove(0);
                        }

                        mTransactionCompleted = true;
                        setInstructionStackLock(false);

                        processDataPacket(bufferTemp);
                        clearBuffers();
                    } else {
                        printLogDataForDebugging("Unknown parsing error while streaming");
                    }
                }
                if (mByteArrayOutputStream.size() > mPacketSize + 2) {
                    printLogDataForDebugging("Unknown packet error (check with JC):\tExpected: " + (mPacketSize + 2) + "bytes but buffer contains " + mByteArrayOutputStream.size() + "bytes");
                    discardFirstBufferByte();
                }

            } else {
                printLogDataForDebugging("Packet syncing problem, could not lock on to data steam. \nExpected: " + (mPacketSize + 2) + "bytes. Buffer contains " + mByteArrayOutputStream.size() + "bytes\n" + UtilShimmer.bytesToHexStringWithSpacesFormatted(mByteArrayOutputStream.toByteArray()));
                discardFirstBufferByte();
            }
        }

        private void processNotStreamingWaitForAck() throws ShimmerException {
			/*
			if (Math.random()>0.9 && mIsInitialised==true){
				if(bytesAvailableToBeRead()
						&& mCurrentCommand!=TEST_CONNECTION_COMMAND
						&& mCurrentCommand!=GET_STATUS_COMMAND
						&& mCurrentCommand!= GET_VBATT_COMMAND
						&& mCurrentCommand!=START_STREAMING_COMMAND
						&& mCurrentCommand!=STOP_STREAMING_COMMAND
						&& mCurrentCommand!=SET_RWC_COMMAND
						&& mCurrentCommand!=GET_RWC_COMMAND){
					tb=readBytes(1);
					tb=null;
				}
			}
			*/

            if (bytesAvailableToBeRead()) {
                byteBuffer = readBytes(1);
                mNumberofTXRetriesCount = 0;
                mIamAlive = true;

                if (mCurrentCommand == InstructionsSet.STOP_STREAMING_COMMAND_VALUE
                        || mCurrentCommand == InstructionsSet.STOP_SDBT_COMMAND_VALUE) {
                    stopTimerCheckForAckOrResp();
                    setIsStreaming(false);
                    mTransactionCompleted = true;
                    mWaitForAck = false;

                    delayForBtResponse(200);
                    byteStack.clear();

                    clearSerialBuffer();

                    hasStopStreaming();
                    getListofInstructions().remove(0);
                    getListofInstructions().removeAll(Collections.singleton(null));
                    if (mCurrentCommand == InstructionsSet.STOP_SDBT_COMMAND_VALUE) {
                        eventLogAndStreamStatusChanged(mCurrentCommand);
                    }
                    setInstructionStackLock(false);
                }
//

                if (byteBuffer != null) {
                    if ((((int) byteBuffer[0]) & 0xFF) == InstructionsSet.ACK_COMMAND_PROCESSED_VALUE) {

                        mWaitForAck = false;
                        printLogDataForDebugging("Ack Received for Command: \t\t\t" + btCommandToString(mCurrentCommand));

                        if (mCurrentCommand != InstructionsGet.GET_STATUS_COMMAND_VALUE
                                && mCurrentCommand != InstructionsSet.TEST_CONNECTION_COMMAND_VALUE
                                && mCurrentCommand != InstructionsSet.SET_BLINK_LED_VALUE
                                && mOperationUnderway) {
                            sendProgressReport(new BluetoothProgressReportPerCmd(mCurrentCommand, getListofInstructions().size(), mMyBluetoothAddress, mComPort));
                        }

                        if (isKnownSetCommand(mCurrentCommand)) {
                            stopTimerCheckForAckOrResp();

                            if (!mUseShimmerBluetoothApproach) {
                            }

                            processAckFromSetCommandAndInstructions(((int) mCurrentCommand) & 0xFF);
                            mTransactionCompleted = true;
                            setInstructionStackLock(false);
                        } else if (isKnownGetCommand(mCurrentCommand)) {
                            processSpecialGetCmdsAfterAck(((int) mCurrentCommand) & 0xFF);
                            mWaitForResponse = true;
                            getListofInstructions().remove(0);
                        }

                    }
                }
            }
        }

        private void processNotStreamingWaitForResp() throws ShimmerException {
            if (mFirstTime) {
                clearSerialBuffer();


                stopTimerCheckForAckOrResp();
                mWaitForResponse = false;
                mTransactionCompleted = true;
                setInstructionStackLock(false);
                mFirstTime = false;
            } else if (bytesAvailableToBeRead()) {
                byteBuffer = readBytes(1);
                mIamAlive = true;

                if (isKnownResponseByte(byteBuffer[0])) {
                    byte responseCommand = byteBuffer[0];

                    if (mUseShimmerBluetoothApproach) {
                        processResponseCommand(((int) responseCommand) & 0xFF);
                    } else {
                    }
                    stopTimerCheckForAckOrResp();
                    mWaitForResponse = false;
                    mTransactionCompleted = true;
                    setInstructionStackLock(false);
                    printLogDataForDebugging("Response Received:\t\t\t\t" + btCommandToString(responseCommand));

                    if (byteBuffer[0] == InstructionsResponse.FW_VERSION_RESPONSE_VALUE) {
                        eventResponseReceived(byteBuffer[0], null);
                    }
                }
            }
        }

        private void processBytesAvailableAndInstreamSupported() throws ShimmerException {
            if (((getHardwareVersion() == HW_ID.SHIMMER_3 && getFirmwareIdentifier() == FW_ID.LOGANDSTREAM)
                    || (getHardwareVersion() == HW_ID.SHIMMER_4_SDK && getFirmwareIdentifier() == FW_ID.SHIMMER4_SDK_STOCK))
                    && !mWaitForAck
                    && !mWaitForResponse
                    && bytesAvailableToBeRead()) {

                byteBuffer = readBytes(1);
                if (byteBuffer != null) {
                    if ((byteBuffer[0] & 0xFF) == InstructionsSet.ACK_COMMAND_PROCESSED_VALUE) {
                        printLogDataForDebugging("ACK RECEIVED , Connected State!!");
                        byteBuffer = readBytes(1);
                        if (byteBuffer != null) { //an android fix. not fully investigated (JC)
                            if ((byteBuffer[0] & 0xFF) == InstructionsSet.ACK_COMMAND_PROCESSED_VALUE) {
                                byteBuffer = readBytes(1);
                            }
                        }
                    }

                    if ((byteBuffer[0] & 0xFF) == InstructionsResponse.INSTREAM_CMD_RESPONSE_VALUE) {
                        processInstreamResponse();
                    }
                }


                clearSerialBuffer();
            }
        }

    }

    class checkForAckOrRespTask extends TimerTask {

        @Override
        public void run() {
            int storedFirstTime = (mFirstTime ? 1 : 0);

            printLogDataForDebugging("Command:\t" + btCommandToString(mCurrentCommand) + " timeout");
            if (mWaitForAck) {
                printLogDataForDebugging("Ack not received");
            }
            if (mWaitForResponse) {
                printLogDataForDebugging("Response not received");
                sendStatusMSGtoUI("Response not received, please reset Shimmer Device." + mMyBluetoothAddress);
            }

            processCheckForAckOrRespPerCmd(mCurrentCommand);

            if (isStreaming()) {
                stopTimerCheckForAckOrResp();
                mWaitForAck = false;
                mTransactionCompleted = true;
                setInstructionStackLock(false);
                getListofInstructions().clear();
            } else if (storedFirstTime == 0) {

                try {
                    if (bytesAvailableToBeRead()) {
                        readBytes(availableBytes());
                    }
                    stopTimerCheckForAckOrResp();
                    printLogDataForDebugging("RETRY TX COUNT: " + Integer.toString(mNumberofTXRetriesCount));
                    if (mNumberofTXRetriesCount >= NUMBER_OF_TX_RETRIES_LIMIT
                            && mCurrentCommand != InstructionsGet.GET_SHIMMER_VERSION_COMMAND_NEW_VALUE
                            && !isInitialised()) {
                        killConnection("Reached number of TX retries = " + NUMBER_OF_TX_RETRIES_LIMIT);
                    } else if (mNumberofTXRetriesCount >= NUMBER_OF_TX_RETRIES_LIMIT && isInitialised()) {
                        killConnection("Reached number of TX retries = " + NUMBER_OF_TX_RETRIES_LIMIT);
                    } else {
                        mWaitForAck = false;
                        mWaitForResponse = false;
                        mTransactionCompleted = true;
                        setInstructionStackLock(false);
                        startTimerCheckForAckOrResp(ACK_TIMER_DURATION + 3);
                    }
                } catch (ShimmerException e) {
                    e.printStackTrace();
                }

                mNumberofTXRetriesCount++;
            }
        }

    }

    public class readStatusTask extends TimerTask {

        @Override
        public void run() {
            if (getListofInstructions().size() == 0
                    && !getListofInstructions().contains(InstructionsGet.GET_STATUS_COMMAND_VALUE)) {
                readStatusLogAndStream();
            }
        }

    }

    private class checkIfAliveTask extends TimerTask {

        @Override
        public void run() {
            if (mIamAlive) {
                mCountDeadConnection = 0;
                mIamAlive = false;
            } else {
                int hwId = getHardwareVersion();
                int fwId = getFirmwareIdentifier();

                if (isStreaming()) {
                    mCountDeadConnection++;
                }

                if (getFirmwareVersionCode() >= 6 && !isStreaming()) {
                    if (getListofInstructions().size() == 0
                            && !getListofInstructions().contains(InstructionsSet.TEST_CONNECTION_COMMAND_VALUE)) {
                        printLogDataForDebugging("Check Alive Task");
                        if ((getShimmerVersion() == HW_ID.SHIMMER_3 && getFirmwareIdentifier() == FW_ID.LOGANDSTREAM)
                                || (getShimmerVersion() == HW_ID.SHIMMER_4_SDK && getFirmwareIdentifier() == FW_ID.SHIMMER4_SDK_STOCK)) {
                        } else if (getShimmerVersion() == HW_ID.SHIMMER_3 && getFirmwareIdentifier() == FW_ID.BTSTREAM) {
                            writeTestConnectionCommand();
                        }
                    }
                } else {
                    printLogDataForDebugging("Check Alive Task");
                    writeLEDCommand(0);
                }

                if (mCountDeadConnection > 5) {
                    killConnection("Keep Alive timer dead connection count > 5");
                }
            }
        }
    }

    private class readBattStatusTask extends TimerTask {
        @Override
        public void run() {
            printLogDataForDebugging("Read Batt Task");
            readBattery();
        }
    }


}
