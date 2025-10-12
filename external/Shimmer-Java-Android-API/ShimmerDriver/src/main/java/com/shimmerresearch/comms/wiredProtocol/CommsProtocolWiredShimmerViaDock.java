package com.shimmerresearch.comms.wiredProtocol;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import com.shimmerresearch.comms.StringListener;
import com.shimmerresearch.comms.serialPortInterface.InterfaceSerialPortHal;
import com.shimmerresearch.driver.ShimmerMsg;
import com.shimmerresearch.driver.shimmer2r3.BluetoothModuleVersionDetails;
import com.shimmerresearch.driverUtilities.ExpansionBoardDetails;
import com.shimmerresearch.driverUtilities.ShimmerBattStatusDetails;
import com.shimmerresearch.driverUtilities.ShimmerVerObject;
import com.shimmerresearch.driverUtilities.UtilShimmer;
import com.shimmerresearch.verisense.communication.ByteCommunicationListener;

import bolts.TaskCompletionSource;

public class CommsProtocolWiredShimmerViaDock extends AbstractCommsProtocolWired {

    StringListener mStringTestListener;
    boolean ackNotReceivedForTestCommand = true;

    public CommsProtocolWiredShimmerViaDock(String comPort, String uniqueId, InterfaceSerialPortHal serialPortInterface) {
        super(comPort, uniqueId, serialPortInterface);
        serialPortInterface.registerSerialPortRxEventCallback(this);
    }

        public String readMacId() throws DockException {
        int errorCode = ErrorCodesWiredProtocol.SHIMMERUART_CMD_ERR_MAC_ID_GET;
        byte[] rxBuf = processShimmerGetCommand(UartPacketDetails.UART_COMPONENT_AND_PROPERTY.MAIN_PROCESSOR.MAC, errorCode);

        if (rxBuf.length >= 6) {
            rxBuf = Arrays.copyOf(rxBuf, 6);
        } else {
            throw new DockException(mUniqueId, mComPort, errorCode, ErrorCodesWiredProtocol.SHIMMERUART_COMM_ERR_MESSAGE_CONTENTS);
        }

        return UtilShimmer.bytesToHexString(rxBuf);
    }

        public ShimmerBattStatusDetails readBattStatus() throws ExecutionException {
        int errorCode = ErrorCodesWiredProtocol.SHIMMERUART_CMD_ERR_BATT_STATUS_GET;
        byte[] rxBuf = processShimmerGetCommand(UartPacketDetails.UART_COMPONENT_AND_PROPERTY.BAT.VALUE, errorCode);

        if (rxBuf.length < 3) {
            throw new DockException(mUniqueId, mComPort, errorCode, ErrorCodesWiredProtocol.SHIMMERUART_COMM_ERR_MESSAGE_CONTENTS);
        }

        ShimmerBattStatusDetails shimmerUartBattStatusDetails = new ShimmerBattStatusDetails(rxBuf);
        return shimmerUartBattStatusDetails;
    }

        public ShimmerVerObject readHwFwVersion() throws ExecutionException {
        int errorCode = ErrorCodesWiredProtocol.SHIMMERUART_CMD_ERR_VERSION_INFO_GET;
        byte[] rxBuf = processShimmerGetCommand(UartPacketDetails.UART_COMPONENT_AND_PROPERTY.MAIN_PROCESSOR.VER, errorCode);

        if (rxBuf.length < 7) {
            throw new DockException(mUniqueId, mComPort, errorCode, ErrorCodesWiredProtocol.SHIMMERUART_COMM_ERR_MESSAGE_CONTENTS);
        }

        ShimmerVerObject shimmerVerDetails = new ShimmerVerObject(rxBuf);
        return shimmerVerDetails;
    }

        public long readRealWorldClockConfigTime() throws ExecutionException {
        int errorCode = ErrorCodesWiredProtocol.SHIMMERUART_CMD_ERR_RTC_CONFIG_TIME_GET;
        byte[] rxBuf = processShimmerGetCommand(UartPacketDetails.UART_COMPONENT_AND_PROPERTY.MAIN_PROCESSOR.RTC_CFG_TIME, errorCode);

        long responseTime = 0;
        if (rxBuf.length >= 8) {
            byte[] rwcTimeArray = Arrays.copyOf(rxBuf, 8);
            responseTime = UtilShimmer.convertShimmerRtcDataBytesToMilliSecondsLSB(rwcTimeArray);
        } else {
            throw new DockException(mUniqueId, mComPort, errorCode, ErrorCodesWiredProtocol.SHIMMERUART_COMM_ERR_MESSAGE_CONTENTS);
        }

        return responseTime;
    }

        public long readCurrentTime() throws ExecutionException {
        int errorCode = ErrorCodesWiredProtocol.SHIMMERUART_CMD_ERR_RTC_CURRENT_TIME_GET;
        byte[] rxBuf = processShimmerGetCommand(UartPacketDetails.UART_COMPONENT_AND_PROPERTY.MAIN_PROCESSOR.CURR_LOCAL_TIME, errorCode);

        long responseTime = 0;
        if (rxBuf.length >= 8) {
            byte[] rwcTimeArray = Arrays.copyOf(rxBuf, 8);
            responseTime = UtilShimmer.convertShimmerRtcDataBytesToMilliSecondsLSB(rwcTimeArray);
        } else {
            throw new DockException(mUniqueId, mComPort, errorCode, ErrorCodesWiredProtocol.SHIMMERUART_COMM_ERR_MESSAGE_CONTENTS);
        }

        return responseTime;
    }

        public long writeRealWorldClockFromPcTime() throws ExecutionException {
        return writeRealWorldClock(System.currentTimeMillis());
    }

        public long writeRealWorldClock(long miliseconds) throws ExecutionException {
        byte[] rwcTimeArray = UtilShimmer.convertMilliSecondsToShimmerRtcDataBytesLSB(miliseconds);

        int errorCode = ErrorCodesWiredProtocol.SHIMMERUART_CMD_ERR_RTC_CONFIG_TIME_SET;
        processShimmerSetCommand(UartPacketDetails.UART_COMPONENT_AND_PROPERTY.MAIN_PROCESSOR.RTC_CFG_TIME, rwcTimeArray, errorCode);

        return miliseconds;
    }

        public byte[] readInfoMem(int address, int size) throws ExecutionException {
        int errorCode = ErrorCodesWiredProtocol.SHIMMERUART_CMD_ERR_INFOMEM_GET;
        byte[] rxBuf = processShimmerMemGetCommand(UartPacketDetails.UART_COMPONENT_AND_PROPERTY.MAIN_PROCESSOR.INFOMEM, address, size, errorCode);

        return rxBuf;
    }

        public void writeInfoMem(int address, byte[] buf) throws ExecutionException {
        int errorCode = ErrorCodesWiredProtocol.SHIMMERUART_CMD_ERR_INFOMEM_SET;
        processShimmerMemSetCommand(UartPacketDetails.UART_COMPONENT_AND_PROPERTY.MAIN_PROCESSOR.INFOMEM, address, buf, errorCode);
    }

        public ExpansionBoardDetails readDaughterCardID() throws ExecutionException {
        int errorCode = ErrorCodesWiredProtocol.SHIMMERUART_CMD_ERR_DAUGHTER_ID_GET;
        byte[] rxBuf = processShimmerMemGetCommand(UartPacketDetails.UART_COMPONENT_AND_PROPERTY.DAUGHTER_CARD.CARD_ID, 0, 16, errorCode);

        ExpansionBoardDetails shimmerUartExpansionBoardDetails = new ExpansionBoardDetails(rxBuf);
        return shimmerUartExpansionBoardDetails;
    }



        public void writeDaughterCardId(int address, byte[] buf) throws ExecutionException {
        int errorCode = ErrorCodesWiredProtocol.SHIMMERUART_CMD_ERR_DAUGHTER_ID_SET;
        processShimmerMemSetCommand(UartPacketDetails.UART_COMPONENT_AND_PROPERTY.DAUGHTER_CARD.CARD_ID, address, buf, errorCode);
    }


        public byte[] readDaughterCardMemory(int address, int size) throws ExecutionException {

        int errorCode = ErrorCodesWiredProtocol.SHIMMERUART_CMD_ERR_DAUGHTER_MEM_GET;
        byte[] rxBuf = processShimmerMemGetCommand(UartPacketDetails.UART_COMPONENT_AND_PROPERTY.DAUGHTER_CARD.CARD_MEM, address, size, errorCode);

        return rxBuf;
    }

        public void writeDaughterCardMemory(int address, byte[] buf) throws ExecutionException {

        int errorCode = ErrorCodesWiredProtocol.SHIMMERUART_CMD_ERR_DAUGHTER_MEM_SET;
        processShimmerMemSetCommand(UartPacketDetails.UART_COMPONENT_AND_PROPERTY.DAUGHTER_CARD.CARD_MEM, address, buf, errorCode);
    }

        public byte[] read802154RadioSettings() throws ExecutionException {
        int errorCode = ErrorCodesWiredProtocol.SHIMMERUART_CMD_ERR_RADIO_802154_GET_SETTINGS;
        byte[] rxBuf = processShimmerGetCommand(UartPacketDetails.UART_COMPONENT_AND_PROPERTY.RADIO_802154.SETTINGS, errorCode);

        if (rxBuf.length >= 9) {
            rxBuf = Arrays.copyOf(rxBuf, 9);
        } else {
            throw new DockException(mUniqueId, mComPort, errorCode, ErrorCodesWiredProtocol.SHIMMERUART_COMM_ERR_MESSAGE_CONTENTS);
        }

        return rxBuf;
    }

        public BluetoothModuleVersionDetails readBtFwVersion() throws ExecutionException {
        int errorCode = ErrorCodesWiredProtocol.SHIMMERUART_CMD_ERR_BT_FW_VERSION_INFO_GET;
        byte[] rxBuf = processShimmerGetCommand(UartPacketDetails.UART_COMPONENT_AND_PROPERTY.BLUETOOTH.VER, errorCode);

        if (rxBuf.length == 0) {
            throw new DockException(mUniqueId, mComPort, errorCode, ErrorCodesWiredProtocol.SHIMMERUART_COMM_ERR_MESSAGE_CONTENTS);
        }
        BluetoothModuleVersionDetails bluetoothModuleVersionDetails = new BluetoothModuleVersionDetails();
        bluetoothModuleVersionDetails.parseBtModuleVerBytes(rxBuf);

        return bluetoothModuleVersionDetails;
    }

    public void addTestStringListener(StringListener stringTestListener) {
        mStringTestListener = stringTestListener;
    }

        public boolean readMainTest(UartComponentPropertyDetails details) throws ExecutionException {
        int errorCode = ErrorCodesWiredProtocol.SHIMMERUART_CMD_ERR_VERSION_INFO_GET;
        TaskCompletionSource tcs = new TaskCompletionSource<>();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ackNotReceivedForTestCommand = true;
        setListener(new ByteCommunicationListener() {

            @Override
            public void eventNewBytesReceived(byte[] rxBytes) {
                System.out.println("Test : " + UtilShimmer.bytesToHexString(rxBytes));
                try {
                    outputStream.write(rxBytes);
                    String result = new String(outputStream.toByteArray());
                    if (ackNotReceivedForTestCommand) {
                        byte[] originalBytes = outputStream.toByteArray();
                        boolean match = UtilShimmer.doesFirstBytesMatch(originalBytes, TEST_ACK);
                        if (match) {
                            outputStream.reset();
                            outputStream.write(originalBytes, TEST_ACK.length, originalBytes.length - TEST_ACK.length);
                            result = new String(outputStream.toByteArray());
                            ackNotReceivedForTestCommand = false;
                        }


                    }

                    System.out.println(result);
                    if (mStringTestListener != null) {
                        mStringTestListener.eventNewStringRx(result);
                    }
                    if (result.contains(TEST_ENDING)) {
                        tcs.setResult(true);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    tcs.setResult(false);
                }

            }

            @Override
            public void eventDisconnected() {

            }

            @Override
            public void eventConnected() {

            }
        });
        mTestStreaming = true;
        processShimmerSetCommandNoWait(details, errorCode, null);
        boolean completed = false;
        try {
            completed = tcs.getTask().waitForCompletion(TIMEOUT_IN_SHIMMER_TEST, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
            mTestStreaming = false;
            return false;
        }
        mTestStreaming = false;
        return completed;
    }

    public boolean isTestStreaming() {
        return mTestStreaming;
    }

        public void writeJumpToBootloaderMode(byte secondsDelay) throws ExecutionException {
        int errorCode = ErrorCodesWiredProtocol.SHIMMERUART_CMD_ERR_ENTER_BOOTLOADER_SET;
        processShimmerSetCommand(UartPacketDetails.UART_COMPONENT_AND_PROPERTY.MAIN_PROCESSOR.ENTER_BOOTLOADER, new byte[]{secondsDelay}, errorCode);
    }

    @Override
    protected void processMsgFromCallback(ShimmerMsg shimmerMSG) {

    }


}
