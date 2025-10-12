package com.shimmerresearch.comms.wiredProtocol;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.apache.commons.lang3.ArrayUtils;

import com.shimmerresearch.comms.serialPortInterface.AbstractSerialPortHal.SHIMMER_UART_BAUD_RATES;
import com.shimmerresearch.comms.serialPortInterface.InterfaceSerialPortHal;
import com.shimmerresearch.comms.serialPortInterface.SerialPortListener;
import com.shimmerresearch.comms.wiredProtocol.UartPacketDetails.UART_PACKET_CMD;
import com.shimmerresearch.driver.BasicProcessWithCallBack;
import com.shimmerresearch.driver.MsgDock;
import com.shimmerresearch.driver.ShimmerMsg;
import com.shimmerresearch.driverUtilities.UtilShimmer;
import com.shimmerresearch.exceptions.ShimmerException;
import com.shimmerresearch.verisense.communication.ByteCommunicationListener;

public abstract class AbstractCommsProtocolWired extends BasicProcessWithCallBack implements SerialPortListener {

    public final static String TEST_ENDING = "TEST END *************************************//";
    public final static byte[] TEST_ACK = new byte[]{36, -1, -39, -78};
    public final static int TIMEOUT_IN_SHIMMER_TEST = 60; //assumes test will complete in 60 seconds;
    protected final static int SERIAL_PORT_TIMEOUT = 500; // was 2000
    public InterfaceSerialPortHal mSerialPortInterface;
    public String mUniqueId = "";
    public String mComPort = "";
    public boolean mIsDebugMode = false;
    public boolean mVerboseMode = false;
        public boolean mLeavePortOpen = true;
    public DockException mThrownException = null;
    protected boolean mTestStreaming = false;
    ByteCommunicationListener mTestByteListener;
    byte[] carriedRxBuf = new byte[]{};
        private boolean mIsUARTInUse = false;
    private int mBaudToUse = SHIMMER_UART_BAUD_RATES.SHIMMER3_DOCKED;
    private UtilShimmer mUtilShimmer = new UtilShimmer(getClass().getSimpleName(), mVerboseMode);
    private List<UartRxPacketObject> mListOfUartRxPacketObjects = Collections.synchronizedList(new ArrayList<UartRxPacketObject>());
    private boolean mSendCallbackRxOverride = false;
    private UART_PACKET_CMD currentPacketCmd;
    private UartComponentPropertyDetails currentMsgArg;

    public AbstractCommsProtocolWired(String comPort, String uniqueId, int baudToUse, InterfaceSerialPortHal shimmerSerialPortInterface) {
        mComPort = comPort;
        mUniqueId = uniqueId;
        mBaudToUse = baudToUse;

        mSerialPortInterface = shimmerSerialPortInterface;
        mSerialPortInterface.registerSerialPortRxEventCallback(this);

        setVerbose(mVerboseMode, mIsDebugMode);
        setThreadName(mUniqueId + "-" + this.getClass().getSimpleName());
    }

    public AbstractCommsProtocolWired(String comPort, String uniqueId, InterfaceSerialPortHal shimmerSerialPortInterface) {
        this(comPort, uniqueId, SHIMMER_UART_BAUD_RATES.SHIMMER3_DOCKED, shimmerSerialPortInterface);
    }

    public void setListener(ByteCommunicationListener listener) {
        mTestByteListener = listener;
    }

    public boolean isUARTinUse() {
        return mIsUARTInUse;
    }

    public boolean isSerialPortReaderStarted() {
        return mSerialPortInterface.isSerialPortReaderStarted();
    }

    public void processShimmerGetCommandNoWait(UartComponentPropertyDetails compPropDetails, int errorCode, byte[] payload) throws DockException {
        if (!mLeavePortOpen) openSafely();
        try {
            shimmerUartGetCommandNoWait(compPropDetails, payload);
        } catch (DockException de) {
            de.mErrorCode = errorCode;
            throw (de);
        } finally {
            if (!mLeavePortOpen) closeSafely();
        }
    }

    public void processShimmerSetCommandNoWait(UartComponentPropertyDetails compPropDetails, int errorCode, byte[] payload) throws DockException {
        if (!mLeavePortOpen) openSafely();
        try {
            shimmerUartSetCommandNoWait(compPropDetails, payload);
        } catch (DockException de) {
            de.mErrorCode = errorCode;
            throw (de);
        } finally {
            if (!mLeavePortOpen) closeSafely();
        }
    }

    public void processShimmerCommandNoWait(UART_PACKET_CMD packetCmd, UartComponentPropertyDetails compPropDetails, int errorCode, byte[] payload) throws DockException {
        if (!mLeavePortOpen) openSafely();
        try {
            shimmerUartCommandNoWait(packetCmd, compPropDetails, payload);
        } catch (DockException de) {
            de.mErrorCode = errorCode;
            throw (de);
        } finally {
            if (!mLeavePortOpen) closeSafely();
        }
    }

    public byte[] processShimmerGetCommand(UartComponentPropertyDetails compPropDetails, int errorCode) throws DockException {
        return processShimmerGetCommand(compPropDetails, errorCode, null);
    }

    public byte[] processShimmerGetCommand(UartComponentPropertyDetails compPropDetails, int errorCode, byte[] payload) throws DockException {
        byte[] rxBuf;
        if (!mLeavePortOpen) openSafely();
        try {
            rxBuf = shimmerUartGetCommand(compPropDetails, payload);
        } catch (DockException de) {
            de.mErrorCode = errorCode;
            throw (de);
        } finally {
            if (!mLeavePortOpen) closeSafely();
        }
        return rxBuf;
    }

    public void processShimmerSetCommand(UartComponentPropertyDetails compPropDetails, byte[] txBuf, int errorCode) throws DockException {
        if (!mLeavePortOpen) openSafely();
        try {
            shimmerUartSetCommand(compPropDetails, txBuf);
        } catch (DockException de) {
            de.mErrorCode = errorCode;
            throw (de);
        } finally {
            if (!mLeavePortOpen) closeSafely();
        }
    }

    public byte[] processShimmerMemGetCommand(UartComponentPropertyDetails compPropDetails, int address, int size, int errorCode) throws DockException {
        byte[] rxBuf;
        if (!mLeavePortOpen) openSafely();
        try {
            rxBuf = shimmerUartGetMemCommand(compPropDetails, address, size);
        } catch (DockException de) {
            de.mErrorCode = errorCode;
            throw (de);
        } finally {
            if (!mLeavePortOpen) closeSafely();
        }
        return rxBuf;
    }

    public void processShimmerMemSetCommand(UartComponentPropertyDetails compPropDetails, int address, byte[] buf, int errorCode) throws DockException {
        if (!mLeavePortOpen) openSafely();
        try {
            shimmerUartSetMemCommand(compPropDetails, address, buf);
        } catch (DockException de) {
            de.mErrorCode = errorCode;
            throw (de);
        } finally {
            if (!mLeavePortOpen) closeSafely();
        }
    }

        public void openSafely() throws DockException {
        try {
            consolePrintLn("Opening port - " + mUniqueId + " - " + mComPort);
            mSerialPortInterface.connect();
        } catch (ShimmerException devE) {
            DockException de = new DockException(devE);
            closeSafely();
            throw (de);
        }
        if (!mLeavePortOpen) mIsUARTInUse = true;
    }

        public void closeSafely() throws DockException {
        try {
            mSerialPortInterface.closeSafely();
        } catch (ShimmerException devE) {
            DockException de = new DockException(devE);
            throw (de);
        } finally {
            mIsUARTInUse = false;
        }
    }

    public byte[] uartGetCommand(UartComponentPropertyDetails cPD) throws DockException {
        return uartGetCommand(cPD, null);
    }

        public byte[] uartGetCommand(UartComponentPropertyDetails cPD, byte[] payload) throws DockException {
        byte[] rxBuf;

        if (!mLeavePortOpen) openSafely();
        try {
            rxBuf = shimmerUartGetCommand(cPD, payload);
        } catch (DockException e) {
            throw (e);
        } finally {
            if (!mLeavePortOpen) closeSafely();
        }

        return rxBuf;
    }

        public void uartSetCommand(UartComponentPropertyDetails cPD, byte[] txBuf) throws ExecutionException {
        if (!mLeavePortOpen) openSafely();
        try {
            shimmerUartSetCommand(cPD, txBuf);
        } catch (ExecutionException e) {
            throw (e);
        } finally {
            if (!mLeavePortOpen) closeSafely();
        }
    }

    protected void shimmerUartGetCommandNoWait(UartComponentPropertyDetails msgArg, byte[] payload) throws DockException {
        mThrownException = null;
        txPacket(UartPacketDetails.UART_PACKET_CMD.READ, msgArg, payload);
    }

    protected void shimmerUartSetCommandNoWait(UartComponentPropertyDetails msgArg, byte[] payload) throws DockException {
        mThrownException = null;
        txPacket(UartPacketDetails.UART_PACKET_CMD.WRITE, msgArg, payload);
    }

    protected void shimmerUartCommandNoWait(UART_PACKET_CMD packetCmd, UartComponentPropertyDetails msgArg, byte[] payload) throws DockException {
        mThrownException = null;
        txPacket(packetCmd, msgArg, payload);
    }

        protected byte[] shimmerUartGetCommand(UartComponentPropertyDetails msgArg, byte[] payload) throws DockException {
        return shimmerUartCommandTxRx(UartPacketDetails.UART_PACKET_CMD.READ, msgArg, payload);
    }

        protected byte[] shimmerUartGetMemCommand(UartComponentPropertyDetails msgArg, int address, int size) throws DockException {

        byte[] memLengthToRead = new byte[]{(byte) size};

        byte[] memAddressToRead = ByteBuffer.allocate(2).putShort((short) (address & 0xFFFF)).array();
        if (msgArg == UartPacketDetails.UART_COMPONENT_AND_PROPERTY.DAUGHTER_CARD.CARD_ID) {
            memAddressToRead = new byte[]{(byte) (address & 0xFF)};
        }

        ArrayUtils.reverse(memAddressToRead);

        byte[] payload = new byte[memLengthToRead.length + memAddressToRead.length];
        System.arraycopy(memLengthToRead, 0, payload, 0, memLengthToRead.length);
        System.arraycopy(memAddressToRead, 0, payload, memLengthToRead.length, memAddressToRead.length);

        return shimmerUartCommandTxRx(UartPacketDetails.UART_PACKET_CMD.READ, msgArg, payload);
    }

        protected byte[] shimmerUartSetCommand(UartComponentPropertyDetails msgArg, byte[] valueBuffer) throws DockException {
        clearAllAcks();
        return shimmerUartCommandTxRx(UartPacketDetails.UART_PACKET_CMD.WRITE, msgArg, valueBuffer);
    }

    private void clearAllAcks() {
        synchronized (mListOfUartRxPacketObjects) {
            for (Iterator<UartRxPacketObject> iterator = mListOfUartRxPacketObjects.iterator(); iterator.hasNext(); ) {
                UartRxPacketObject uRPO = iterator.next();
                if (uRPO.mUartCommandByte == UartPacketDetails.UART_PACKET_CMD.ACK_RESPONSE.toCmdByte()) {
                    iterator.remove();
                }
            }
        }
    }


        protected byte[] shimmerUartSetMemCommand(UartComponentPropertyDetails msgArg, int address, byte[] valueBuffer) throws DockException {

        byte[] memLengthToWrite = new byte[]{(byte) valueBuffer.length};

        byte[] memAddressToWrite = ByteBuffer.allocate(2).putShort((short) (address & 0xFFFF)).array();
        if (msgArg == UartPacketDetails.UART_COMPONENT_AND_PROPERTY.DAUGHTER_CARD.CARD_ID) {
            memAddressToWrite = new byte[]{(byte) (address & 0xFF)};
        }
        ArrayUtils.reverse(memAddressToWrite);

        byte[] payload = new byte[memLengthToWrite.length + memAddressToWrite.length + valueBuffer.length];
        System.arraycopy(memLengthToWrite, 0, payload, 0, memLengthToWrite.length);
        System.arraycopy(memAddressToWrite, 0, payload, memLengthToWrite.length, memAddressToWrite.length);
        System.arraycopy(valueBuffer, 0, payload, memLengthToWrite.length + memAddressToWrite.length, valueBuffer.length);

        clearAllAcks();
        return shimmerUartCommandTxRx(UartPacketDetails.UART_PACKET_CMD.WRITE, msgArg, payload);
    }


        protected byte[] shimmerUartCommandTxRx(UART_PACKET_CMD packetCmd, UartComponentPropertyDetails msgArg, byte[] payload) throws DockException {
        mListOfUartRxPacketObjects.clear();

        txPacket(packetCmd, msgArg, payload);
        mThrownException = null;
        return waitForResponse(packetCmd, msgArg);
    }

        private void txPacket(UART_PACKET_CMD packetCmd, UartComponentPropertyDetails msgArg, byte[] valueBuffer) throws DockException {
        consolePrintTxPacketInfo(packetCmd, msgArg, valueBuffer);
        byte[] txPacket = assembleTxPacket(packetCmd.toCmdByte(), msgArg, valueBuffer);
        mUtilShimmer.consolePrintLn(mUniqueId + " TX(" + txPacket.length + ")" + UtilShimmer.bytesToHexStringWithSpacesFormatted(txPacket));
        try {
            mSerialPortInterface.txBytes(txPacket);
        } catch (ShimmerException devE) {
            DockException de = new DockException(devE);
            throw (de);
        }
    }

        protected byte[] assembleTxPacket(int command, UartComponentPropertyDetails msgArg, byte[] value) {
        byte[] header = (UartPacketDetails.PACKET_HEADER).getBytes();
        byte[] cmd = new byte[]{(byte) command};
        int msgLength = 0;
        if (msgArg != null) {
            msgLength += msgArg.mCompPropByteArray.length;
        }
        if (value != null) {
            msgLength += value.length;
        }
        byte[] msgLengthByte = new byte[]{(byte) msgLength};

        int txBufPreCrcSize = header.length + cmd.length;
        if (msgLength > 0) {
            txBufPreCrcSize += msgLengthByte.length;
        }
        if (msgArg != null) {
            txBufPreCrcSize += msgArg.mCompPropByteArray.length;
        }
        if (value != null) {
            txBufPreCrcSize += value.length;
        }
        byte[] txBufPreCrc = new byte[txBufPreCrcSize];

        int currentIndex = 0;
        System.arraycopy(header, 0, txBufPreCrc, currentIndex, header.length);
        currentIndex += header.length;
        System.arraycopy(cmd, 0, txBufPreCrc, currentIndex, cmd.length);
        currentIndex += cmd.length;
        if (msgLength > 0) {
            System.arraycopy(msgLengthByte, 0, txBufPreCrc, currentIndex, msgLengthByte.length);
            currentIndex += msgLengthByte.length;
        }
        if (msgArg != null) {
            System.arraycopy(msgArg.mCompPropByteArray, 0, txBufPreCrc, currentIndex, msgArg.mCompPropByteArray.length);
            currentIndex += msgArg.mCompPropByteArray.length;
        }
        if (value != null) {
            System.arraycopy(value, 0, txBufPreCrc, currentIndex, value.length);
            currentIndex += value.length;
        }

        byte[] calculatedCrc = ShimmerCrc.shimmerUartCrcCalc(txBufPreCrc, txBufPreCrc.length);

        byte[] txBufPostCrc = new byte[txBufPreCrc.length + 2];
        System.arraycopy(txBufPreCrc, 0, txBufPostCrc, 0, txBufPreCrc.length);
        System.arraycopy(calculatedCrc, 0, txBufPostCrc, txBufPreCrc.length, calculatedCrc.length);

        return txBufPostCrc;
    }

//
//
//
//
//


    private byte[] waitForResponse(UART_PACKET_CMD packetCmd, UartComponentPropertyDetails msgArg) throws DockException {
        this.currentPacketCmd = packetCmd;
        this.currentMsgArg = msgArg;

        try {
            boolean flag = true;

            int loopCount = 0;
            int waitIntervalMs = 100;
            int loopCountTotal = SERIAL_PORT_TIMEOUT / waitIntervalMs;

            while (flag) {
                mUtilShimmer.millisecondDelay(waitIntervalMs);
                loopCount += 1;
                if (loopCount >= loopCountTotal) {
                    break;
                }

                UartRxPacketObject uRPO = checkIfListContainsResponse(packetCmd, msgArg);
                if (uRPO != null) {
                    if (this.mIsDebugMode) {
                        consolePrintLn("RxObjectsSize=" + mListOfUartRxPacketObjects.size());
                    }
                    return uRPO.getPayload();
                } else if (mThrownException != null) {
                    throw mThrownException;
                }
            }

            UartComponentPropertyDetails uCPD = UartPacketDetails.getUartPropertyParsed(msgArg.mComponent.toCmdByte(), msgArg.mPropertyByte);
            String propertyString = Integer.toString(msgArg.mProperty);
            if (uCPD != null) {
                propertyString = uCPD.mPropertyName;
            }
            consolePrintLn("TIMEOUT_FAIL" + "\tComponent:" + msgArg.mComponent.toString() + "\tProperty:" + propertyString);
            if (this.mIsDebugMode) {
                consolePrintLn("RxObjectsSize=" + mListOfUartRxPacketObjects.size());
            }

            DockException de = new DockException(mComPort, ErrorCodesWiredProtocol.SHIMMERUART_COMM_ERR_TIMEOUT, ErrorCodesWiredProtocol.SHIMMERUART_COMM_ERR_TIMEOUT, mUniqueId);
            throw (de);
        } catch (DockException de) {
            throw (de);
        } finally {
            currentPacketCmd = null;
            currentMsgArg = null;
        }
    }

    private UartRxPacketObject checkIfListContainsResponse(UART_PACKET_CMD packetCmd, UartComponentPropertyDetails msgArg) {
        if (mListOfUartRxPacketObjects.size() == 0) {
            return null;
        }

        synchronized (mListOfUartRxPacketObjects) {
            Iterator<UartRxPacketObject> iterator = mListOfUartRxPacketObjects.iterator();
            while (iterator.hasNext()) {
                UartRxPacketObject uRPO = iterator.next();
                if (packetCmd == UartPacketDetails.UART_PACKET_CMD.WRITE
                        && uRPO.mUartCommandByte == UartPacketDetails.UART_PACKET_CMD.ACK_RESPONSE.toCmdByte()) {
                    return uRPO;
                } else if ((packetCmd == UartPacketDetails.UART_PACKET_CMD.READ)
                        && uRPO.mUartCommandByte == UartPacketDetails.UART_PACKET_CMD.DATA_RESPONSE.toCmdByte()
                        && msgArg.mComponentByte == uRPO.mUartComponentByte
                        && msgArg.mPropertyByte == uRPO.mUartPropertyByte) {
                    return uRPO;
                }
            }
        }

        return null;
    }

    private void processUnexpectedResponse(UartRxPacketObject uRPO) throws DockException {
        byte commandByte = uRPO.mUartCommandByte;

        if (commandByte == UartPacketDetails.UART_PACKET_CMD.BAD_CMD_RESPONSE.toCmdByte()) {
            throw new DockException(mUniqueId, mComPort, ErrorCodesWiredProtocol.SHIMMERUART_COMM_ERR_RESPONSE_BAD_CMD, ErrorCodesWiredProtocol.SHIMMERUART_COMM_ERR_RESPONSE_BAD_CMD);
        } else if (commandByte == UartPacketDetails.UART_PACKET_CMD.BAD_ARG_RESPONSE.toCmdByte()) {
            throw new DockException(mUniqueId, mComPort, ErrorCodesWiredProtocol.SHIMMERUART_COMM_ERR_RESPONSE_BAD_ARG, ErrorCodesWiredProtocol.SHIMMERUART_COMM_ERR_RESPONSE_BAD_ARG);
        } else if (commandByte == UartPacketDetails.UART_PACKET_CMD.BAD_CRC_RESPONSE.toCmdByte()) {
            throw new DockException(mUniqueId, mComPort, ErrorCodesWiredProtocol.SHIMMERUART_COMM_ERR_RESPONSE_BAD_CRC, ErrorCodesWiredProtocol.SHIMMERUART_COMM_ERR_RESPONSE_BAD_CRC);
        } else {
            if (commandByte != UartPacketDetails.UART_PACKET_CMD.ACK_RESPONSE.toCmdByte()
                    && commandByte != UartPacketDetails.UART_PACKET_CMD.DATA_RESPONSE.toCmdByte()
                    && commandByte != UartPacketDetails.UART_PACKET_CMD.READ.toCmdByte()
                    && commandByte != UartPacketDetails.UART_PACKET_CMD.WRITE.toCmdByte()) {
                throw new DockException(mUniqueId, mComPort, ErrorCodesWiredProtocol.SHIMMERUART_COMM_ERR_RESPONSE_UNEXPECTED, ErrorCodesWiredProtocol.SHIMMERUART_COMM_ERR_RESPONSE_UNEXPECTED);
            }
        }

    }

    @Override
    public void serialPortRxEvent(int eventLength) {
        try {
            byte[] rxBuf = mSerialPortInterface.rxBytes(eventLength);
            if (mTestStreaming) {
                if (mTestByteListener != null) {
                    mTestByteListener.eventNewBytesReceived(rxBuf);
                }
                return;
            }
            if (mIsDebugMode) {
                consolePrintLn("serialEvent Received(" + rxBuf.length + "):" + UtilShimmer.bytesToHexStringWithSpacesFormatted(rxBuf));
            }
            processRxBuf(rxBuf);
        } catch (Exception ex) {
            consolePrintLn("Serial port ERROR");
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
    }

    private void processRxBuf(byte[] rxBuf) throws ShimmerException {

        byte headerByte = rxBuf[0];
        if (headerByte == UartPacketDetails.PACKET_HEADER.toCharArray()[0]) {
            long timestampMs = System.currentTimeMillis();

            if (rxBuf.length < 3) {
                int lengthToRead = 3 - rxBuf.length;
                byte[] tempBuf = mSerialPortInterface.rxBytes(lengthToRead);
                rxBuf = combineByteArrays(rxBuf, tempBuf);
            }

            byte cmdByte = rxBuf[1];
            boolean continueWithParsing = true;

            int expectedResponseLength = 0;
            if (continueWithParsing) {
                if (cmdByte == UartPacketDetails.UART_PACKET_CMD.DATA_RESPONSE.toCmdByte()
                        || cmdByte == UartPacketDetails.UART_PACKET_CMD.READ.toCmdByte()
                        || cmdByte == UartPacketDetails.UART_PACKET_CMD.WRITE.toCmdByte()) {
                    int payloadLength = rxBuf[2] & 0xFF;
                    if (payloadLength < 0) {
                        consolePrintLn("Invalid payload length: " + payloadLength);
                        carriedRxBuf = removeFirstByteFromArray(rxBuf);
                        continueWithParsing = false;
                    } else {
                        expectedResponseLength = UartPacketDetails.PACKET_OVERHEAD_RESPONSE_DATA + payloadLength;
                    }
                } else if (cmdByte == UartPacketDetails.UART_PACKET_CMD.ACK_RESPONSE.toCmdByte()
                        || cmdByte == UartPacketDetails.UART_PACKET_CMD.BAD_ARG_RESPONSE.toCmdByte()
                        || cmdByte == UartPacketDetails.UART_PACKET_CMD.BAD_CMD_RESPONSE.toCmdByte()
                        || cmdByte == UartPacketDetails.UART_PACKET_CMD.BAD_CRC_RESPONSE.toCmdByte()) {
                    expectedResponseLength = UartPacketDetails.PACKET_OVERHEAD_RESPONSE_OTHER;
                } else {
                    consolePrintLn("Unknown command: " + cmdByte);
                    carriedRxBuf = removeFirstByteFromArray(rxBuf);
                    continueWithParsing = false;
                }
            }

            byte[] packet = null;
            if (continueWithParsing) {
                if (rxBuf.length == expectedResponseLength) {
                    packet = rxBuf;
                } else if (rxBuf.length > expectedResponseLength) {
                    packet = new byte[expectedResponseLength];
                    System.arraycopy(rxBuf, 0, packet, 0, expectedResponseLength);

                    int carriedOverLenth = rxBuf.length - expectedResponseLength;
                    carriedRxBuf = new byte[carriedOverLenth];
                    System.arraycopy(rxBuf, expectedResponseLength, carriedRxBuf, 0, carriedOverLenth);

                    if (mIsDebugMode) {
                        consolePrintLn("Overflow: All bytes(" + rxBuf.length + "):\t" + UtilShimmer.bytesToHexStringWithSpacesFormatted(rxBuf));
                        consolePrintLn("Overflow: 1st packet(" + packet.length + "):\t" + UtilShimmer.bytesToHexStringWithSpacesFormatted(packet));
                        consolePrintLn("Overflow: 2nd packet(" + carriedRxBuf.length + "):\t" + UtilShimmer.bytesToHexStringWithSpacesFormatted(carriedRxBuf));
                        consolePrintLn("");
                    }
                } else if (rxBuf.length < expectedResponseLength) {
                    byte[] data = mSerialPortInterface.rxBytes(expectedResponseLength - rxBuf.length);
                    packet = combineByteArrays(rxBuf, data);
                    if (mIsDebugMode && packet != null) {
                        consolePrintLn("Underflow: 1st buf(" + rxBuf.length + "):\t" + UtilShimmer.bytesToHexStringWithSpacesFormatted(rxBuf));
                        consolePrintLn("Underflow: 2st buf(" + data.length + "):\t" + UtilShimmer.bytesToHexStringWithSpacesFormatted(data));
                        consolePrintLn("Underflow: Combined(" + packet.length + "):\t" + UtilShimmer.bytesToHexStringWithSpacesFormatted(packet));
                        consolePrintLn("");
                    }
                }
            }

            if (continueWithParsing && packet != null) {
                try {
                    parseSinglePacket(packet, timestampMs);
                } catch (DockException de) {
                    mThrownException = de;

                    if (currentPacketCmd != null && currentMsgArg != null) {
                        System.out.println(mUniqueId + "\tProblem parsing received packet while waiting for:");
                        System.out.println(mUniqueId + "\t" + assemblePrintTxPacketInfo(currentPacketCmd, currentMsgArg, null));
                        System.out.println(de.getErrStringFormatted());
                    } else {
                        System.out.println(mUniqueId + mComPort + "\tProblem parsing packet: " + UtilShimmer.bytesToHexStringWithSpacesFormatted(packet));
                        System.out.println(de.getErrStringFormatted());


                    }
                }
            }
        } else {
            carriedRxBuf = removeFirstByteFromArray(rxBuf);
        }

        if (carriedRxBuf.length > 0) {
            byte[] tempBuf = carriedRxBuf;
            carriedRxBuf = new byte[]{};
            processRxBuf(tempBuf);
        }

    }

        private void parseSinglePacket(byte[] rxBuf, long timestampMs) throws DockException {
        UartRxPacketObject uRPO = new UartRxPacketObject(rxBuf, timestampMs);

        try {
            if (!ShimmerCrc.shimmerUartCrcCheck(uRPO.mRxPacket)) {
                consolePrintLn("RX\tERR_CRC");
                throw new DockException(mUniqueId, mComPort, ErrorCodesWiredProtocol.SHIMMERUART_COMM_ERR_CRC, ErrorCodesWiredProtocol.SHIMMERUART_COMM_ERR_CRC);
            }
            consolePrintRxPacketInfo(uRPO);
        } catch (DockException dE) {
            byte[] tempBuf = removeFirstByteFromArray(rxBuf);
            insertToCarrierBuffer(tempBuf);
            throw (dE);
        }

        processParsedPacket(uRPO, timestampMs);
    }

    private void processParsedPacket(UartRxPacketObject uRPO, long timestampMs) throws DockException {
        try {
            try {
                processUnexpectedResponse(uRPO);
            } catch (DockException de) {
                throw de;
            }

            if (mSendCallbackRxOverride) {
                wrapMsgSpanAndSend(MsgDock.MSG_ID_SHIMMERUART_PACKET_RX, uRPO);
            } else {
                synchronized (mListOfUartRxPacketObjects) {
                    mListOfUartRxPacketObjects.add(uRPO);
                }
            }
        } catch (DockException dE) {
            throw (dE);
        } finally {
            if (uRPO.mLeftOverBytes != null) {
                insertToCarrierBuffer(uRPO.mLeftOverBytes);
            }
        }
    }

        private byte[] removeFirstByteFromArray(byte[] rxBuf) {
        int lengthToCarry = rxBuf.length - 1;
        byte[] processedArray = new byte[lengthToCarry];
        System.arraycopy(rxBuf, 1, processedArray, 0, lengthToCarry);
        return processedArray;
    }

    private byte[] combineByteArrays(byte[] buf1, byte[] buf2) {
        boolean condition1 = (buf1 != null && buf1.length > 0) ? true : false;
        boolean condition2 = (buf2 != null && buf2.length > 0) ? true : false;

        if (condition1 && condition2) {
            byte[] packet = new byte[buf1.length + buf2.length];
            System.arraycopy(buf1, 0, packet, 0, buf1.length);
            System.arraycopy(buf2, 0, packet, buf1.length, buf2.length);
            return packet;
        } else if (condition1) {
            return buf1;
        } else if (condition2) {
            return buf2;
        }

        consolePrintLn("ERROR\1st Buf Length:" + buf1.length + "\t2nd Buf Length:" + buf2.length);
        return null;
    }

    public void insertToCarrierBuffer(byte[] leadingBytes) {
        if (leadingBytes.length > 0) {
            if (carriedRxBuf.length > 0) {
                carriedRxBuf = combineByteArrays(leadingBytes, carriedRxBuf);
            } else {
                carriedRxBuf = leadingBytes;
            }
        }
    }


//
//


    private void wrapMsgSpanAndSend(int msgId, Object object) {
        ShimmerMsg msg = new ShimmerMsg(msgId, object);
        sendCallBackMsg(msg);
    }

        public boolean isSendCallbackRxOverride() {
        return mSendCallbackRxOverride;
    }

        public void setSendCallbackRxOverride(boolean state) {
        this.mSendCallbackRxOverride = state;
    }


    public void setVerbose(boolean verboseMode, boolean isDebugMode) {
        mVerboseMode = verboseMode;
        mIsDebugMode = isDebugMode;
        mUtilShimmer.setVerboseMode(mVerboseMode);
        mSerialPortInterface.setVerboseMode(mVerboseMode, mIsDebugMode);
    }

        private void consolePrintTxPacketInfo(UART_PACKET_CMD packetCmd, UartComponentPropertyDetails msgArg, byte[] valueBuffer) {
        if (mVerboseMode) {
            consolePrintLn(assemblePrintTxPacketInfo(packetCmd, msgArg, valueBuffer));
        }
    }

    private String assemblePrintTxPacketInfo(UART_PACKET_CMD packetCmd, UartComponentPropertyDetails msgArg, byte[] valueBuffer) {
        String consoleString = "TX\tCommand:" + packetCmd.toString()
                + "\tComponent:" + (msgArg == null ? "null" : msgArg.mComponent.toString())
                + "\tProperty:" + (msgArg == null ? "null" : msgArg.mPropertyName)
                ;

        if (packetCmd != UartPacketDetails.UART_PACKET_CMD.READ) {
            consoleString += "\tPayload";
            if (valueBuffer != null) {
                consoleString += "(" + valueBuffer.length + "):";
                String txBytes = UtilShimmer.bytesToHexStringWithSpacesFormatted(valueBuffer);
                if (txBytes != null) {
                    consoleString += txBytes;
                }
            } else {
                consoleString += ":none";
            }
        }
        return consoleString;
    }

        private void consolePrintRxPacketInfo(UartRxPacketObject uRPO) {
        if (mVerboseMode) {
            consolePrintLn("RX\t" + uRPO.getConsoleString());
        }
    }

    private void consolePrintLn(String string) {
        mUtilShimmer.consolePrintLn(mUniqueId + "\t" + string);
    }

}
