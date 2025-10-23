package com.shimmerresearch.comms.wiredProtocol;

import com.shimmerresearch.comms.serialPortInterface.AbstractSerialPortHal;
import com.shimmerresearch.comms.serialPortInterface.InterfaceSerialPortHal;
import com.shimmerresearch.comms.serialPortInterface.SerialPortListener;
import com.shimmerresearch.comms.wiredProtocol.UartPacketDetails;
import com.shimmerresearch.driver.BasicProcessWithCallBack;
import com.shimmerresearch.driver.ShimmerMsg;
import com.shimmerresearch.driver.ShimmerObject;
import com.shimmerresearch.driverUtilities.UtilShimmer;
import com.shimmerresearch.exceptions.ShimmerException;
import com.shimmerresearch.sensors.lisxmdl.SensorLIS3MDL;
import com.shimmerresearch.verisense.communication.ByteCommunicationListener;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.apache.commons.lang3.ArrayUtils;

/* loaded from: classes2.dex */
public abstract class AbstractCommsProtocolWired extends BasicProcessWithCallBack implements SerialPortListener {
    public static final byte[] TEST_ACK = {ShimmerObject.GET_SHIMMER_VERSION_COMMAND, -1, -39, SensorLIS3MDL.SET_ALT_MAG_SAMPLING_RATE_COMMAND};
    public static final String TEST_ENDING = "TEST END *************************************//";
    public static final int TIMEOUT_IN_SHIMMER_TEST = 60;
    protected static final int SERIAL_PORT_TIMEOUT = 500;
    public String mComPort;
    public boolean mIsDebugMode;
    public boolean mLeavePortOpen;
    public InterfaceSerialPortHal mSerialPortInterface;
    public DockException mThrownException;
    public String mUniqueId;
    public boolean mVerboseMode;
    protected boolean mTestStreaming;
    byte[] carriedRxBuf;
    ByteCommunicationListener mTestByteListener;
    private UartComponentPropertyDetails currentMsgArg;
    private UartPacketDetails.UART_PACKET_CMD currentPacketCmd;
    private int mBaudToUse;
    private boolean mIsUARTInUse;
    private List<UartRxPacketObject> mListOfUartRxPacketObjects;
    private boolean mSendCallbackRxOverride;
    private UtilShimmer mUtilShimmer;

    public AbstractCommsProtocolWired(String str, String str2, int i, InterfaceSerialPortHal interfaceSerialPortHal) {
        this.mIsUARTInUse = false;
        this.mTestStreaming = false;
        this.mUniqueId = "";
        this.mComPort = "";
        this.mBaudToUse = AbstractSerialPortHal.SHIMMER_UART_BAUD_RATES.SHIMMER3_DOCKED;
        this.carriedRxBuf = new byte[0];
        this.mIsDebugMode = false;
        this.mVerboseMode = false;
        this.mUtilShimmer = new UtilShimmer(getClass().getSimpleName(), Boolean.valueOf(this.mVerboseMode));
        this.mLeavePortOpen = true;
        this.mListOfUartRxPacketObjects = Collections.synchronizedList(new ArrayList());
        this.mThrownException = null;
        this.mSendCallbackRxOverride = false;
        this.mComPort = str;
        this.mUniqueId = str2;
        this.mBaudToUse = i;
        this.mSerialPortInterface = interfaceSerialPortHal;
        interfaceSerialPortHal.registerSerialPortRxEventCallback(this);
        setVerbose(this.mVerboseMode, this.mIsDebugMode);
        setThreadName(this.mUniqueId + "-" + getClass().getSimpleName());
    }

    public AbstractCommsProtocolWired(String str, String str2, InterfaceSerialPortHal interfaceSerialPortHal) {
        this(str, str2, AbstractSerialPortHal.SHIMMER_UART_BAUD_RATES.SHIMMER3_DOCKED, interfaceSerialPortHal);
    }

    public boolean isSendCallbackRxOverride() {
        return this.mSendCallbackRxOverride;
    }

    public void setSendCallbackRxOverride(boolean z) {
        this.mSendCallbackRxOverride = z;
    }

    public boolean isUARTinUse() {
        return this.mIsUARTInUse;
    }

    public void setListener(ByteCommunicationListener byteCommunicationListener) {
        this.mTestByteListener = byteCommunicationListener;
    }

    public boolean isSerialPortReaderStarted() {
        return this.mSerialPortInterface.isSerialPortReaderStarted();
    }

    public void processShimmerGetCommandNoWait(UartComponentPropertyDetails uartComponentPropertyDetails, int i, byte[] bArr) throws DockException {
        if (!this.mLeavePortOpen) {
            openSafely();
        }
        try {
            try {
                shimmerUartGetCommandNoWait(uartComponentPropertyDetails, bArr);
            } catch (DockException e) {
                e.mErrorCode = i;
                throw e;
            }
        } finally {
            if (!this.mLeavePortOpen) {
                closeSafely();
            }
        }
    }

    public void processShimmerSetCommandNoWait(UartComponentPropertyDetails uartComponentPropertyDetails, int i, byte[] bArr) throws DockException {
        if (!this.mLeavePortOpen) {
            openSafely();
        }
        try {
            try {
                shimmerUartSetCommandNoWait(uartComponentPropertyDetails, bArr);
            } catch (DockException e) {
                e.mErrorCode = i;
                throw e;
            }
        } finally {
            if (!this.mLeavePortOpen) {
                closeSafely();
            }
        }
    }

    public void processShimmerCommandNoWait(UartPacketDetails.UART_PACKET_CMD uart_packet_cmd, UartComponentPropertyDetails uartComponentPropertyDetails, int i, byte[] bArr) throws DockException {
        if (!this.mLeavePortOpen) {
            openSafely();
        }
        try {
            try {
                shimmerUartCommandNoWait(uart_packet_cmd, uartComponentPropertyDetails, bArr);
            } catch (DockException e) {
                e.mErrorCode = i;
                throw e;
            }
        } finally {
            if (!this.mLeavePortOpen) {
                closeSafely();
            }
        }
    }

    public byte[] processShimmerGetCommand(UartComponentPropertyDetails uartComponentPropertyDetails, int i) throws DockException {
        return processShimmerGetCommand(uartComponentPropertyDetails, i, null);
    }

    public byte[] processShimmerGetCommand(UartComponentPropertyDetails uartComponentPropertyDetails, int i, byte[] bArr) throws DockException {
        if (!this.mLeavePortOpen) {
            openSafely();
        }
        try {
            try {
                return shimmerUartGetCommand(uartComponentPropertyDetails, bArr);
            } catch (DockException e) {
                e.mErrorCode = i;
                throw e;
            }
        } finally {
            if (!this.mLeavePortOpen) {
                closeSafely();
            }
        }
    }

    public void processShimmerSetCommand(UartComponentPropertyDetails uartComponentPropertyDetails, byte[] bArr, int i) throws DockException {
        if (!this.mLeavePortOpen) {
            openSafely();
        }
        try {
            try {
                shimmerUartSetCommand(uartComponentPropertyDetails, bArr);
            } catch (DockException e) {
                e.mErrorCode = i;
                throw e;
            }
        } finally {
            if (!this.mLeavePortOpen) {
                closeSafely();
            }
        }
    }

    public byte[] processShimmerMemGetCommand(UartComponentPropertyDetails uartComponentPropertyDetails, int i, int i2, int i3) throws DockException {
        if (!this.mLeavePortOpen) {
            openSafely();
        }
        try {
            try {
                return shimmerUartGetMemCommand(uartComponentPropertyDetails, i, i2);
            } catch (DockException e) {
                e.mErrorCode = i3;
                throw e;
            }
        } finally {
            if (!this.mLeavePortOpen) {
                closeSafely();
            }
        }
    }

    public void processShimmerMemSetCommand(UartComponentPropertyDetails uartComponentPropertyDetails, int i, byte[] bArr, int i2) throws DockException {
        if (!this.mLeavePortOpen) {
            openSafely();
        }
        try {
            try {
                shimmerUartSetMemCommand(uartComponentPropertyDetails, i, bArr);
            } catch (DockException e) {
                e.mErrorCode = i2;
                throw e;
            }
        } finally {
            if (!this.mLeavePortOpen) {
                closeSafely();
            }
        }
    }

    public void openSafely() throws DockException {
        try {
            consolePrintLn("Opening port - " + this.mUniqueId + " - " + this.mComPort);
            this.mSerialPortInterface.connect();
            if (this.mLeavePortOpen) {
                return;
            }
            this.mIsUARTInUse = true;
        } catch (ShimmerException e) {
            DockException dockException = new DockException(e);
            closeSafely();
            throw dockException;
        }
    }

    public void closeSafely() throws DockException {
        try {
            try {
                this.mSerialPortInterface.closeSafely();
            } catch (ShimmerException e) {
                throw new DockException(e);
            }
        } finally {
            this.mIsUARTInUse = false;
        }
    }

    public byte[] uartGetCommand(UartComponentPropertyDetails uartComponentPropertyDetails) throws DockException {
        return uartGetCommand(uartComponentPropertyDetails, null);
    }

    public byte[] uartGetCommand(UartComponentPropertyDetails uartComponentPropertyDetails, byte[] bArr) throws DockException {
        if (!this.mLeavePortOpen) {
            openSafely();
        }
        try {
            try {
                return shimmerUartGetCommand(uartComponentPropertyDetails, bArr);
            } catch (DockException e) {
                throw e;
            }
        } finally {
            if (!this.mLeavePortOpen) {
                closeSafely();
            }
        }
    }

    public void uartSetCommand(UartComponentPropertyDetails uartComponentPropertyDetails, byte[] bArr) throws ExecutionException {
        if (!this.mLeavePortOpen) {
            openSafely();
        }
        try {
            try {
                shimmerUartSetCommand(uartComponentPropertyDetails, bArr);
            } catch (ExecutionException e) {
                throw e;
            }
        } finally {
            if (!this.mLeavePortOpen) {
                closeSafely();
            }
        }
    }

    protected void shimmerUartGetCommandNoWait(UartComponentPropertyDetails uartComponentPropertyDetails, byte[] bArr) throws DockException {
        this.mThrownException = null;
        txPacket(UartPacketDetails.UART_PACKET_CMD.READ, uartComponentPropertyDetails, bArr);
    }

    protected void shimmerUartSetCommandNoWait(UartComponentPropertyDetails uartComponentPropertyDetails, byte[] bArr) throws DockException {
        this.mThrownException = null;
        txPacket(UartPacketDetails.UART_PACKET_CMD.WRITE, uartComponentPropertyDetails, bArr);
    }

    protected void shimmerUartCommandNoWait(UartPacketDetails.UART_PACKET_CMD uart_packet_cmd, UartComponentPropertyDetails uartComponentPropertyDetails, byte[] bArr) throws DockException {
        this.mThrownException = null;
        txPacket(uart_packet_cmd, uartComponentPropertyDetails, bArr);
    }

    protected byte[] shimmerUartGetCommand(UartComponentPropertyDetails uartComponentPropertyDetails, byte[] bArr) throws DockException {
        return shimmerUartCommandTxRx(UartPacketDetails.UART_PACKET_CMD.READ, uartComponentPropertyDetails, bArr);
    }

    protected byte[] shimmerUartGetMemCommand(UartComponentPropertyDetails uartComponentPropertyDetails, int i, int i2) throws DockException {
        byte[] bArr = {(byte) i2};
        byte[] bArrArray = ByteBuffer.allocate(2).putShort((short) (65535 & i)).array();
        if (uartComponentPropertyDetails == UartPacketDetails.UART_COMPONENT_AND_PROPERTY.DAUGHTER_CARD.CARD_ID) {
            bArrArray = new byte[]{(byte) (i & 255)};
        }
        ArrayUtils.reverse(bArrArray);
        byte[] bArr2 = new byte[bArrArray.length + 1];
        System.arraycopy(bArr, 0, bArr2, 0, 1);
        System.arraycopy(bArrArray, 0, bArr2, 1, bArrArray.length);
        return shimmerUartCommandTxRx(UartPacketDetails.UART_PACKET_CMD.READ, uartComponentPropertyDetails, bArr2);
    }

    protected byte[] shimmerUartSetCommand(UartComponentPropertyDetails uartComponentPropertyDetails, byte[] bArr) throws DockException {
        clearAllAcks();
        return shimmerUartCommandTxRx(UartPacketDetails.UART_PACKET_CMD.WRITE, uartComponentPropertyDetails, bArr);
    }

    private void clearAllAcks() {
        synchronized (this.mListOfUartRxPacketObjects) {
            Iterator<UartRxPacketObject> it2 = this.mListOfUartRxPacketObjects.iterator();
            while (it2.hasNext()) {
                if (it2.next().mUartCommandByte == UartPacketDetails.UART_PACKET_CMD.ACK_RESPONSE.toCmdByte()) {
                    it2.remove();
                }
            }
        }
    }

    protected byte[] shimmerUartSetMemCommand(UartComponentPropertyDetails uartComponentPropertyDetails, int i, byte[] bArr) throws DockException {
        byte[] bArr2 = {(byte) bArr.length};
        byte[] bArrArray = ByteBuffer.allocate(2).putShort((short) (65535 & i)).array();
        if (uartComponentPropertyDetails == UartPacketDetails.UART_COMPONENT_AND_PROPERTY.DAUGHTER_CARD.CARD_ID) {
            bArrArray = new byte[]{(byte) (i & 255)};
        }
        ArrayUtils.reverse(bArrArray);
        byte[] bArr3 = new byte[bArrArray.length + 1 + bArr.length];
        System.arraycopy(bArr2, 0, bArr3, 0, 1);
        System.arraycopy(bArrArray, 0, bArr3, 1, bArrArray.length);
        System.arraycopy(bArr, 0, bArr3, 1 + bArrArray.length, bArr.length);
        clearAllAcks();
        return shimmerUartCommandTxRx(UartPacketDetails.UART_PACKET_CMD.WRITE, uartComponentPropertyDetails, bArr3);
    }

    protected byte[] shimmerUartCommandTxRx(UartPacketDetails.UART_PACKET_CMD uart_packet_cmd, UartComponentPropertyDetails uartComponentPropertyDetails, byte[] bArr) throws DockException {
        this.mListOfUartRxPacketObjects.clear();
        txPacket(uart_packet_cmd, uartComponentPropertyDetails, bArr);
        this.mThrownException = null;
        return waitForResponse(uart_packet_cmd, uartComponentPropertyDetails);
    }

    private void txPacket(UartPacketDetails.UART_PACKET_CMD uart_packet_cmd, UartComponentPropertyDetails uartComponentPropertyDetails, byte[] bArr) throws DockException {
        consolePrintTxPacketInfo(uart_packet_cmd, uartComponentPropertyDetails, bArr);
        byte[] bArrAssembleTxPacket = assembleTxPacket(uart_packet_cmd.toCmdByte(), uartComponentPropertyDetails, bArr);
        this.mUtilShimmer.consolePrintLn(this.mUniqueId + " TX(" + bArrAssembleTxPacket.length + ")" + UtilShimmer.bytesToHexStringWithSpacesFormatted(bArrAssembleTxPacket));
        try {
            this.mSerialPortInterface.txBytes(bArrAssembleTxPacket);
        } catch (ShimmerException e) {
            throw new DockException(e);
        }
    }

    protected byte[] assembleTxPacket(int i, UartComponentPropertyDetails uartComponentPropertyDetails, byte[] bArr) {
        byte[] bytes = UartPacketDetails.PACKET_HEADER.getBytes();
        byte[] bArr2 = {(byte) i};
        int length = uartComponentPropertyDetails != null ? uartComponentPropertyDetails.mCompPropByteArray.length : 0;
        if (bArr != null) {
            length += bArr.length;
        }
        byte[] bArr3 = {(byte) length};
        int length2 = bytes.length;
        int length3 = length2 + 1;
        if (length > 0) {
            length3 = length2 + 2;
        }
        if (uartComponentPropertyDetails != null) {
            length3 += uartComponentPropertyDetails.mCompPropByteArray.length;
        }
        if (bArr != null) {
            length3 += bArr.length;
        }
        byte[] bArr4 = new byte[length3];
        System.arraycopy(bytes, 0, bArr4, 0, bytes.length);
        int length4 = bytes.length;
        System.arraycopy(bArr2, 0, bArr4, length4, 1);
        int length5 = length4 + 1;
        if (length > 0) {
            System.arraycopy(bArr3, 0, bArr4, length5, 1);
            length5 = length4 + 2;
        }
        if (uartComponentPropertyDetails != null) {
            System.arraycopy(uartComponentPropertyDetails.mCompPropByteArray, 0, bArr4, length5, uartComponentPropertyDetails.mCompPropByteArray.length);
            length5 += uartComponentPropertyDetails.mCompPropByteArray.length;
        }
        if (bArr != null) {
            System.arraycopy(bArr, 0, bArr4, length5, bArr.length);
            int length6 = bArr.length;
        }
        byte[] bArrShimmerUartCrcCalc = ShimmerCrc.shimmerUartCrcCalc(bArr4, length3);
        byte[] bArr5 = new byte[length3 + 2];
        System.arraycopy(bArr4, 0, bArr5, 0, length3);
        System.arraycopy(bArrShimmerUartCrcCalc, 0, bArr5, length3, bArrShimmerUartCrcCalc.length);
        return bArr5;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private byte[] waitForResponse(UartPacketDetails.UART_PACKET_CMD uart_packet_cmd, UartComponentPropertyDetails uartComponentPropertyDetails) throws DockException {
        DockException dockException;
        this.currentPacketCmd = uart_packet_cmd;
        this.currentMsgArg = uartComponentPropertyDetails;
        int i = 0;
        do {
            try {
                try {
                    this.mUtilShimmer.millisecondDelay(100);
                    i++;
                    if (i < 5) {
                        UartRxPacketObject uartRxPacketObjectCheckIfListContainsResponse = checkIfListContainsResponse(uart_packet_cmd, uartComponentPropertyDetails);
                        if (uartRxPacketObjectCheckIfListContainsResponse != null) {
                            if (this.mIsDebugMode) {
                                consolePrintLn("RxObjectsSize=" + this.mListOfUartRxPacketObjects.size());
                            }
                            return uartRxPacketObjectCheckIfListContainsResponse.getPayload();
                        }
                        dockException = this.mThrownException;
                    } else {
                        UartComponentPropertyDetails uartPropertyParsed = UartPacketDetails.getUartPropertyParsed(uartComponentPropertyDetails.mComponent.toCmdByte(), uartComponentPropertyDetails.mPropertyByte);
                        String string = Integer.toString(uartComponentPropertyDetails.mProperty);
                        if (uartPropertyParsed != null) {
                            string = uartPropertyParsed.mPropertyName;
                        }
                        consolePrintLn("TIMEOUT_FAIL\tComponent:" + uartComponentPropertyDetails.mComponent.toString() + "\tProperty:" + string);
                        if (this.mIsDebugMode) {
                            consolePrintLn("RxObjectsSize=" + this.mListOfUartRxPacketObjects.size());
                        }
                        throw new DockException(this.mComPort, ErrorCodesWiredProtocol.SHIMMERUART_COMM_ERR_TIMEOUT, ErrorCodesWiredProtocol.SHIMMERUART_COMM_ERR_TIMEOUT, this.mUniqueId);
                    }
                } catch (DockException e) {
                    throw e;
                }
            } finally {
                this.currentPacketCmd = null;
                this.currentMsgArg = null;
            }
        } while (dockException == null);
        throw dockException;
    }

    private UartRxPacketObject checkIfListContainsResponse(UartPacketDetails.UART_PACKET_CMD uart_packet_cmd, UartComponentPropertyDetails uartComponentPropertyDetails) {
        if (this.mListOfUartRxPacketObjects.size() == 0) {
            return null;
        }
        synchronized (this.mListOfUartRxPacketObjects) {
            for (UartRxPacketObject uartRxPacketObject : this.mListOfUartRxPacketObjects) {
                if (uart_packet_cmd == UartPacketDetails.UART_PACKET_CMD.WRITE && uartRxPacketObject.mUartCommandByte == UartPacketDetails.UART_PACKET_CMD.ACK_RESPONSE.toCmdByte()) {
                    return uartRxPacketObject;
                }
                if (uart_packet_cmd == UartPacketDetails.UART_PACKET_CMD.READ && uartRxPacketObject.mUartCommandByte == UartPacketDetails.UART_PACKET_CMD.DATA_RESPONSE.toCmdByte() && uartComponentPropertyDetails.mComponentByte == uartRxPacketObject.mUartComponentByte && uartComponentPropertyDetails.mPropertyByte == uartRxPacketObject.mUartPropertyByte) {
                    return uartRxPacketObject;
                }
            }
            return null;
        }
    }

    private void processUnexpectedResponse(UartRxPacketObject uartRxPacketObject) throws DockException {
        byte b = uartRxPacketObject.mUartCommandByte;
        if (b == UartPacketDetails.UART_PACKET_CMD.BAD_CMD_RESPONSE.toCmdByte()) {
            throw new DockException(this.mUniqueId, this.mComPort, ErrorCodesWiredProtocol.SHIMMERUART_COMM_ERR_RESPONSE_BAD_CMD, ErrorCodesWiredProtocol.SHIMMERUART_COMM_ERR_RESPONSE_BAD_CMD);
        }
        if (b == UartPacketDetails.UART_PACKET_CMD.BAD_ARG_RESPONSE.toCmdByte()) {
            throw new DockException(this.mUniqueId, this.mComPort, ErrorCodesWiredProtocol.SHIMMERUART_COMM_ERR_RESPONSE_BAD_ARG, ErrorCodesWiredProtocol.SHIMMERUART_COMM_ERR_RESPONSE_BAD_ARG);
        }
        if (b == UartPacketDetails.UART_PACKET_CMD.BAD_CRC_RESPONSE.toCmdByte()) {
            throw new DockException(this.mUniqueId, this.mComPort, ErrorCodesWiredProtocol.SHIMMERUART_COMM_ERR_RESPONSE_BAD_CRC, ErrorCodesWiredProtocol.SHIMMERUART_COMM_ERR_RESPONSE_BAD_CRC);
        }
        if (b != UartPacketDetails.UART_PACKET_CMD.ACK_RESPONSE.toCmdByte() && b != UartPacketDetails.UART_PACKET_CMD.DATA_RESPONSE.toCmdByte() && b != UartPacketDetails.UART_PACKET_CMD.READ.toCmdByte() && b != UartPacketDetails.UART_PACKET_CMD.WRITE.toCmdByte()) {
            throw new DockException(this.mUniqueId, this.mComPort, ErrorCodesWiredProtocol.SHIMMERUART_COMM_ERR_RESPONSE_UNEXPECTED, ErrorCodesWiredProtocol.SHIMMERUART_COMM_ERR_RESPONSE_UNEXPECTED);
        }
    }

    @Override // com.shimmerresearch.comms.serialPortInterface.SerialPortListener
    public void serialPortRxEvent(int i) {
        try {
            byte[] bArrRxBytes = this.mSerialPortInterface.rxBytes(i);
            if (this.mTestStreaming) {
                ByteCommunicationListener byteCommunicationListener = this.mTestByteListener;
                if (byteCommunicationListener != null) {
                    byteCommunicationListener.eventNewBytesReceived(bArrRxBytes);
                    return;
                }
                return;
            }
            if (this.mIsDebugMode) {
                consolePrintLn("serialEvent Received(" + bArrRxBytes.length + "):" + UtilShimmer.bytesToHexStringWithSpacesFormatted(bArrRxBytes));
            }
            processRxBuf(bArrRxBytes);
        } catch (Exception e) {
            consolePrintLn("Serial port ERROR");
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:32:0x009e  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x0183  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void processRxBuf(byte[] r13) throws com.shimmerresearch.exceptions.ShimmerException {
        /*
            Method dump skipped, instructions count: 542
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.shimmerresearch.comms.wiredProtocol.AbstractCommsProtocolWired.processRxBuf(byte[]):void");
    }

    private void parseSinglePacket(byte[] bArr, long j) throws DockException {
        UartRxPacketObject uartRxPacketObject = new UartRxPacketObject(bArr, j);
        try {
            if (!ShimmerCrc.shimmerUartCrcCheck(uartRxPacketObject.mRxPacket)) {
                consolePrintLn("RX\tERR_CRC");
                throw new DockException(this.mUniqueId, this.mComPort, ErrorCodesWiredProtocol.SHIMMERUART_COMM_ERR_CRC, ErrorCodesWiredProtocol.SHIMMERUART_COMM_ERR_CRC);
            }
            consolePrintRxPacketInfo(uartRxPacketObject);
            processParsedPacket(uartRxPacketObject, j);
        } catch (DockException e) {
            insertToCarrierBuffer(removeFirstByteFromArray(bArr));
            throw e;
        }
    }

    private void processParsedPacket(UartRxPacketObject uartRxPacketObject, long j) throws DockException {
        try {
            try {
                try {
                    processUnexpectedResponse(uartRxPacketObject);
                    if (this.mSendCallbackRxOverride) {
                        wrapMsgSpanAndSend(52, uartRxPacketObject);
                    } else {
                        synchronized (this.mListOfUartRxPacketObjects) {
                            this.mListOfUartRxPacketObjects.add(uartRxPacketObject);
                        }
                    }
                    if (uartRxPacketObject.mLeftOverBytes != null) {
                        insertToCarrierBuffer(uartRxPacketObject.mLeftOverBytes);
                    }
                } catch (DockException e) {
                    throw e;
                }
            } catch (Throwable th) {
                if (uartRxPacketObject.mLeftOverBytes != null) {
                    insertToCarrierBuffer(uartRxPacketObject.mLeftOverBytes);
                }
                throw th;
            }
        } catch (DockException e2) {
            throw e2;
        }
    }

    private byte[] removeFirstByteFromArray(byte[] bArr) {
        int length = bArr.length - 1;
        byte[] bArr2 = new byte[length];
        System.arraycopy(bArr, 1, bArr2, 0, length);
        return bArr2;
    }

    private byte[] combineByteArrays(byte[] bArr, byte[] bArr2) {
        boolean z = bArr != null && bArr.length > 0;
        boolean z2 = bArr2 != null && bArr2.length > 0;
        if (z && z2) {
            byte[] bArr3 = new byte[bArr.length + bArr2.length];
            System.arraycopy(bArr, 0, bArr3, 0, bArr.length);
            System.arraycopy(bArr2, 0, bArr3, bArr.length, bArr2.length);
            return bArr3;
        }
        if (z) {
            return bArr;
        }
        if (z2) {
            return bArr2;
        }
        consolePrintLn("ERROR\u0001st Buf Length:" + bArr.length + "\t2nd Buf Length:" + bArr2.length);
        return null;
    }

    public void insertToCarrierBuffer(byte[] bArr) {
        if (bArr.length > 0) {
            byte[] bArr2 = this.carriedRxBuf;
            if (bArr2.length > 0) {
                this.carriedRxBuf = combineByteArrays(bArr, bArr2);
            } else {
                this.carriedRxBuf = bArr;
            }
        }
    }

    private void wrapMsgSpanAndSend(int i, Object obj) {
        sendCallBackMsg(new ShimmerMsg(i, obj));
    }

    public void setVerbose(boolean z, boolean z2) {
        this.mVerboseMode = z;
        this.mIsDebugMode = z2;
        this.mUtilShimmer.setVerboseMode(z);
        this.mSerialPortInterface.setVerboseMode(this.mVerboseMode, this.mIsDebugMode);
    }

    private void consolePrintTxPacketInfo(UartPacketDetails.UART_PACKET_CMD uart_packet_cmd, UartComponentPropertyDetails uartComponentPropertyDetails, byte[] bArr) {
        if (this.mVerboseMode) {
            consolePrintLn(assemblePrintTxPacketInfo(uart_packet_cmd, uartComponentPropertyDetails, bArr));
        }
    }

    private String assemblePrintTxPacketInfo(UartPacketDetails.UART_PACKET_CMD uart_packet_cmd, UartComponentPropertyDetails uartComponentPropertyDetails, byte[] bArr) {
        String str = "TX\tCommand:" + uart_packet_cmd.toString() + "\tComponent:" + (uartComponentPropertyDetails == null ? "null" : uartComponentPropertyDetails.mComponent.toString()) + "\tProperty:" + (uartComponentPropertyDetails != null ? uartComponentPropertyDetails.mPropertyName : "null");
        if (uart_packet_cmd == UartPacketDetails.UART_PACKET_CMD.READ) {
            return str;
        }
        String str2 = str + "\tPayload";
        if (bArr != null) {
            String str3 = str2 + "(" + bArr.length + "):";
            String strBytesToHexStringWithSpacesFormatted = UtilShimmer.bytesToHexStringWithSpacesFormatted(bArr);
            if (strBytesToHexStringWithSpacesFormatted != null) {
                str3 = str3 + strBytesToHexStringWithSpacesFormatted;
            }
            return str3;
        }
        return str2 + ":none";
    }

    private void consolePrintRxPacketInfo(UartRxPacketObject uartRxPacketObject) {
        if (this.mVerboseMode) {
            consolePrintLn("RX\t" + uartRxPacketObject.getConsoleString());
        }
    }

    private void consolePrintLn(String str) {
        this.mUtilShimmer.consolePrintLn(this.mUniqueId + "\t" + str);
    }
}
